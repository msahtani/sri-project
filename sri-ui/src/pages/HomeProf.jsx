import React,{useState} from 'react'
import Navbar from '../Components/Navbar';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import "../styles/HomeProf.css"
import axios from 'axios';


const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 600,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
  };



function HomeProf() {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const [selectedFile, setSelectedFile] = useState(null);
  const [showMsg , setShowMsg] = useState("")
  

  const [pdfData , setPdfData] = useState({
      level: '',
      branch:'' ,
      semestre: '',
      module: ''
  })



  const files = [
      { name: 'PDF.pdf', content: 'Contenu du PDF' },
      { name: 'doc.pdf' ,content: 'Contenu du Word' },
      { name: 'doc1.pdf',  content: 'Contenu du PowerPoint' },
      { name: 'doc2.pdf',  content: 'Contenu du PowerPoint' },
      { name: 'doc3.pdf',  content: 'Contenu du PowerPoint' },
      { name: 'doc4.pdf',  content: 'Contenu du PowerPoint' },
    ];


    const handleFileChange = (event) => {
      const file = event.target.files[0];
      setSelectedFile(file);
    };
    const handleInputChange = (event) => {
      const { name, value } = event.target;
      setPdfData({
        ...pdfData,
        [name]: value,
      });
    };

    const handleAddFile = async () => {
      try {

        const formData = new FormData();
        const pdfDocuments = {
          level: pdfData.level ,
          branch: pdfData.branch ,
          semestre: pdfData.semestre ,
          module: pdfData.module
        }
        
        const json = JSON.stringify(pdfDocuments);
      const pdfDocument = new Blob([json], {
        type: 'application/json'
});
formData.append('file', selectedFile);
        
formData.append('pdfDocument', pdfDocument)
        console.log(formData.get("pdfDocument"));
  
        const response = await axios.post('http://localhost:8083/documents/add',formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          }, 
        });
        

        
        if (response.status === 200) {
          console.log('Fichier ajouté avec succès');
          setShowMsg(true)
        }
      } catch (error) {
        console.error('Erreur lors de l\'ajout du fichier :', error.message);
      }
    };
  
  

return (
  <div>
    <Navbar />
    <div onClick={handleOpen} className='addStyle'><label>Ajouter un fichier</label></div>
    <Modal
      open={open}
      onClose={handleClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box sx={style}>
        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
          <div className="containerDiv">
        <h2>Ajouter un fichier</h2>
      <div className="divS">
        <label>Niveau:</label>
        <input type="text" name="level" required className='inputStyle' value={pdfData.level} onChange={handleInputChange}/>
      </div>
      <div className="divS">
        <label>Filière:</label>
        <input type="text" name="branch" required className='inputStyle' value={pdfData.branch} onChange={handleInputChange} />
      </div>
      <div className="divS">
        <label>Semestre:</label>
        <input type="text" name="semestre" required className='inputStyle' value={pdfData.semestre} onChange={handleInputChange}/>
      </div>
      <div className="divS">
        <label>Module:</label>
        <input type="text" name="module"  className='inputStyle' value={pdfData.module} onChange={handleInputChange} />
      </div>
      <input type="file" onChange={handleFileChange} />
      <button className='buttonStyleB' onClick={handleAddFile}>Ajouter</button>
      {showMsg ? <p>Fichier ajouté avec succès</p> : <p></p>}
     </div>
        </Typography>
      </Box>
    </Modal>
    <div className='style'>
      {files.map((file, index) => (
          <div className='elemStyle'>
          {file.name}
          </div>
      ))}
    </div>
  </div>
)
}

export default HomeProf