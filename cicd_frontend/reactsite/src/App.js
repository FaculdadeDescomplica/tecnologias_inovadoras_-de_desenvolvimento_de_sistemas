import { useState } from 'react';
import ListaArtistas from './components/ListaArtistas.js';
import ListaAlbuns from './components/ListaAlbuns.js';
import ListaMusicas from './components/ListaMusicas.js';
import './App.css';

function App() {

  const[urlArtista, setUrlArtista] = useState();
  const[urlAlbum, setUrlAlbum] = useState();
  
  const handleArtista = (artista) => {
    setUrlArtista(artista);
  }

  const handleAlbum = (album) => {
    setUrlAlbum(album);
  }

  return (
    <div className="App">
      <div className='App-Content'>
          <ListaArtistas handleArtista={handleArtista} handleAlbum={handleAlbum}/>
          <ListaAlbuns urlArtista={urlArtista} handleAlbum={handleAlbum}/>
          <ListaMusicas urlAlbum={urlAlbum}/>
      </div>
    </div>
  );
}

export default App;
