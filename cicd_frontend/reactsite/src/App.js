import React, { useEffect, useState } from 'react';
import ListaArtistas from './components/ListaArtistas.js';
import ListaAlbuns from './components/ListaAlbuns.js';
import ListaMusicas from './components/ListaMusicas.js';
import ListaPlaylists from './components/ListaPlaylists.js';
import ListaItens from './components/ListaItens.js';
import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
      
      <div className='App-Content'>
          <ListaArtistas />
          <ListaAlbuns />
          <ListaMusicas />
          <ListaPlaylists />
          <ListaItens />
      </div>
    </div>
  );
}

export default App;
