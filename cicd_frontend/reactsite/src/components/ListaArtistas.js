import React, { Component, useState, useEffect } from 'react';
import getArtistas from '../api/ArtistasApi.js';
import List from 'react-list-select';

const ListaArtistas = (props) => {
    const [listaState, setListaState] = useState({
        repos: [],
        });

    useEffect(() => {
        getArtistas().then((response) => {
            setListaState({ repos: response });
        });
        }, [setListaState]);
    
    function onClick(id) {
        console.log('clicked id: ' + id);  
    }

    function composite(artista) {
        return (
            <div className="artista" role="button" onClick={onClick.bind(this, artista.id)}>
                <div className="nomeArtista">{artista.nome}</div>
            </div>
        )
    }

    let items = listaState.repos.map((artista) => {
        return composite(artista);
    })

    return (
        <div className="artistas">
            <h3>Artistas</h3>
            <List name="listaArtistas" items={items} />
        </div>
    )
};

export default ListaArtistas;