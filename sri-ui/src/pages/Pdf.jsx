import React from 'react'
import { useParams } from 'react-router-dom'

const Pdf = () => {
    const {path}= useParams() ;
  return (
    <div>
      <div className="pdfViewer">
          <iframe src={`C:/Users/saadf/Documents/sri/src/main/resources/docs/${path}`} title="PDF Viewer" width="100%" height="500px" />
        </div>
    </div>
  )
}

export default Pdf
