import React, {useState} from 'react';
import { RiSearchLine } from "react-icons/ri";
import { MdCancel } from "react-icons/md";
import '../styles/Search.css';
import { TiArrowSortedDown } from "react-icons/ti";
import Navbar from '../Components/Navbar';
import axios from 'axios';

function Search() {
  const [showN , setShowN] = useState(false);
  const [showF , setShowF] = useState(false);
  const [showS , setShowS] = useState(false);
  const [showNiv , setShowNiv] = useState("niveau");
  const [showFil , setShowFil] = useState("filiére");
  const [showSem , setShowSem] = useState("semestre");
  const [text , setText] = useState("");
  const [resultF , setResultF] = useState([]);

  const files = [
    { name: 'PDF.pdf', type: 'pdf', content: 'Contenu du PDF' },
    { name: 'Word.docx', type: 'doc', content: 'Contenu du Word' },
    { name: 'PowerPoint.pptx', type: 'ppt', content: 'Contenu du PowerPoint' },
  ];

  const handlSelect = (select) => {
    setShowN(false);
    setShowF(false);
    setShowS(false);

    if (select === "niveau") {
      setShowN(!showN);
    } else if (select === "fil") {
      setShowF(!showF);
    } else if (select === "sem") {
      setShowS(!showS);
    }
  };

  const handleOptionSelect = (option, category) => {
    if (category === "niveau") {
      setShowNiv(option);
    } else if (category === "fil") {
      setShowFil(option);
    } else if (category === "sem") {
      setShowSem(option);
    }

    setShowN(false);
    setShowF(false);
    setShowS(false);
  };



  const handleSearch = async () => {
      if (showNiv !== "niveau" && showFil !== "filiére" && showSem !== "semestre"){
      try{
        const response = await axios.get('http://localhost:8083/documents/allCondition', {
          params: {
            level : showNiv,
            branch : showFil,
            semestre : showSem,
            request: text
          }
        });
  
        if (response.status === 200) { 
            setResultF(response.data)
        }
      }catch (error) {
        console.log(error.message);
      }
    } else {
      console.log("lkjhgfdfgfhjkl;")
      try{
      const response = await axios.get('http://localhost:8083/documents/all', {
        params: {
          request: text
        }
      });

      if (response.status === 200) { 
          setResultF(response.data)
          console.log(resultF)
      }
    }catch (error) {
      console.log(error.message);
    }
    }
  } 


  const handleRein = () => {
    setShowN(false);
    setShowF(false);
    setShowS(false);
    setShowNiv("niveau");
    setShowFil("filiére");
    setShowSem("semestre");
    setText("");
    setResultF([]);
  };

  return (
    <div >
      <Navbar />
      <div className='styleSearch'>
        <RiSearchLine className='iconSearch'/>
        <input type="text" className='inputSearch' value={text} onChange={(e) => setText(e.target.value)}/>
        <MdCancel className='iconCancel'/>
      </div>
      <div className='container'>
      <div className='SousSelectContainer'>
      <div className='styleSelect' onClick={() => handlSelect("niveau")}>
        <span>{showNiv}</span>
        <TiArrowSortedDown />
      </div>
      {showN && (
        <div className='st'>
          <span  onClick={() => handleOptionSelect('1ère année', 'niveau')}>1ère année</span>
          <span  onClick={() => handleOptionSelect('2ème année', 'niveau')}>2ème année</span>
          <span  onClick={() => handleOptionSelect('3ème année', 'niveau')} >3ème année</span>
          <span  onClick={() => handleOptionSelect('4ème année', 'niveau')}>4ème année</span>
          <span  onClick={() => handleOptionSelect('5ème année', 'niveau')}>5ème année</span>
        </div>
      )}
      </div>
      <div className='SousSelectContainer'>
      <div className='styleSelect' onClick={() => handlSelect("fil")}>
        <span>{showFil}</span>
        <TiArrowSortedDown />
      </div>
      {showF && (
         <div className='st'>
         <span  onClick={() => handleOptionSelect('GI', 'fil')}>GI</span>
         <span  onClick={() => handleOptionSelect('GE', 'fil')}>GE</span>
         <span  onClick={() => handleOptionSelect('GS', 'fil')} >GS</span>
         <span  onClick={() => handleOptionSelect('RSSP', 'fil')}>RSSP</span>
         <span  onClick={() => handleOptionSelect('GRT', 'fil')}>GRT</span>
       </div>
      )}
      </div>
      <div className='SousSelectContainer'>
      <div className='styleSelect' onClick={() => handlSelect("sem")}>
        <span>{showSem}</span>
        <TiArrowSortedDown />
      </div>
      {showS && (
        <div className='st'>
        <span  onClick={() => handleOptionSelect('S1', "sem")}>S1</span>
        <span  onClick={() => handleOptionSelect('S2', "sem")}>S2</span>
        <span  onClick={() => handleOptionSelect('S3', "sem")} >S3</span>
        <span  onClick={() => handleOptionSelect('S4', "sem")}>S4</span>
        <span  onClick={() => handleOptionSelect('S5', "sem")}>S5</span>
        <span  onClick={() => handleOptionSelect('S6', "sem")}>S6</span>
        <span  onClick={() => handleOptionSelect('S7', "sem")}>S7</span>
        <span  onClick={() => handleOptionSelect('S8', "sem")}>S8</span>
        <span  onClick={() => handleOptionSelect('S9', "sem")}>S9</span>
        <span  onClick={() => handleOptionSelect('S10', "sem")}>S10</span>
      </div>
      )}
      </div>
      <button className='styleButon' onClick={handleSearch}>
        Rechercher
      </button>
      <label className='textStyle' onClick={handleRein}>Rénitialiser</label>
      </div>
      <div className='style'>
        {resultF.map((file, index) => (
            <div className='elemStyle'>
            {file.name}
         </div>
        ))}
      </div>
    </div>
    
  )
}

export default Search
