import React from 'react'
import '../styles/Navbar.css'

function Navbar() {
  return (
    <div className="navbar">
    <div className="left-section">
      <div className="circle-container">
        <img src="/ensa.png" alt="ppp" className="ImgEnsa" />
      </div>
    </div>
    <div className="right-section">
      <div className="user-info">
        <span>Dounia Aissi</span>
      </div>
      <div className="icon-container">
      <img src="/logout.png" alt="ppp" className="icon" />
      </div>
    </div>
  </div>
  )
}

export default Navbar
