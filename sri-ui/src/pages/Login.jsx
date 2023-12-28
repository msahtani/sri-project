import React, { useState } from 'react';
import '../styles/login.css';
import { BsFillPersonFill } from 'react-icons/bs';
import { AiFillLock , AiFillEye , AiFillEyeInvisible} from 'react-icons/ai';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const LoginPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [showPass, setShowPass] = useState(false);
  const [msgErreur , setMsgErreur] = useState('');
  const [showMsg , setShowMsg] = useState(false);
  const navigate = useNavigate(); 

  const [ role, setRole ] = useState("");


  const handleLogin = async () => {

    if (!email && !password) {
      setShowMsg(true);
      setMsgErreur('Veuillez remplir Email et mot de passe');
      return;
    } else if (!email ) {
      setShowMsg(true);
      setMsgErreur('Veuillez remplir Email');
      return;
    } else if (!password) {
      setShowMsg(true);
      setMsgErreur('Veuillez remplir mot de passe');
      return;
    }

 /*try {
      const response = await axios.post('http://localhost:3000/auth/login', {
        email,
        password,
      });

      if (response.status === 200) {
        const {role } = response.data;
         setRole(role);
         if(role==="Teacher"){ */
          navigate('/home');
   /*     } else {
          navigate('/Home'); 
         
         
      } else {
        setMsgErreur("Email ou Mot de passe est incorrect");
      }
    } catch (error) {
      setMsgErreur("Email ou Mot de passe est incorrect");
    }*/
  }

  return (
    <div className='StyleContainer' >
    <div className='containerLogin'>
      <h2>Connexion</h2>
      <div className='inputStyle'>
      <BsFillPersonFill className='icon'/>
      <input
        type="text"
        placeholder="Nom d'utilisateur"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      </div>
      <div className='inputStyle'>
       <AiFillLock className='iconPass'/>
      <input
        type={showPass ? "text"  : "password" }
        placeholder="Mot de passe"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      {showPass ? <AiFillEye className='icon' onClick={() =>setShowPass(!showPass)}/> : <AiFillEyeInvisible className='icon' onClick={() =>setShowPass(!showPass)}/>}
      </div>
      <button className='buttonStyle' onClick={handleLogin}>Se connecter</button>
      {showMsg? <p className='errorStyle'>{msgErreur}</p> :  <p></p> }
    </div>
    </div>
  );
};

export default LoginPage;