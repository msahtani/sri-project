import React, {useState} from 'react';
import { RiSearchLine } from "react-icons/ri";
import { MdCancel } from "react-icons/md";
import { Link } from 'react-router-dom';
import '../styles/Search.css';
import { TiArrowSortedDown } from "react-icons/ti";
import Navbar from '../Components/Navbar';
import axios from 'axios';
import Navbar2 from '../Components/Navbar2';

function Search() {
  const [showN , setShowN] = useState(false);
  const [showF , setShowF] = useState(false);
  const [showS , setShowS] = useState(false);
  const [showNiv , setShowNiv] = useState("niveau");
  const [showFil , setShowFil] = useState("filiére");
  const [showSem , setShowSem] = useState("semestre");
  const [text , setText] = useState("");
  const [resultF , setResultF] = useState([]);
  const [showMsg , setShowMsg] = useState("")

 
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

  const [selectedPdfPath, setSelectedPdfPath] = useState(null);

  const showPdf = (pdfPath) => {
    setSelectedPdfPath(pdfPath);
  };

  return (
    <div >
      <Navbar2 />
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
          <span  onClick={() => handleOptionSelect('CP1', 'niveau')}>CP1</span>
          <span  onClick={() => handleOptionSelect('CP2', 'niveau')}>CP2</span>
          <span  onClick={() => handleOptionSelect('CI1', 'niveau')} >CI1</span>
          <span  onClick={() => handleOptionSelect('CI2', 'niveau')}>CI2</span>
          <span  onClick={() => handleOptionSelect('CI3', 'niveau')}>CI3</span>
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

    <Link to={`/pdf/${file.path}`}>
    <div className='elemStyle' key={index} >
      <div className='row'>
      <img src="/pdf_icon.png" alt="PDF Icon" height='20px' width='20px' className="pdfIcon" />
      <div className='filename'>{file.name}</div>
      </div>
      
      
    </div>
    </Link>
  ))}
</div>
    </div>
    
  )
}

export default Search
