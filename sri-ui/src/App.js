import './App.css';
import HomeProf from './pages/HomeProf';
import LoginPage from './pages/Login';
import PdfFile from './pages/PdfFile';
import Search from './pages/Search';

import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <div>
      <Router>
        <div>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/home" element={<HomeProf />} />
        <Route path="/search" element={<Search />} />
        <Route path="pdf/:path" element={<PdfFile />} />
      </Routes>
      </div>
    </Router>
    </div>
  );
}

export default App;
