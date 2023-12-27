import React from 'react'
import { useParams } from 'react-router-dom';



const PdfFile = () => {
   const {path} = useParams() ;
   
   const pdfPath = path.split('/search/pdf/')[1];
   console.log(pdfPath)
    return (
      <div>
       hello
      </div>
    );
}

export default PdfFile
