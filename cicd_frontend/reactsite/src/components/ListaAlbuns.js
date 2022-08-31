import React, { useState, useEffect } from 'react';
import getAlbuns from '../api/AlbunsApi.js';
import List from 'react-list-select';

const ListaAlbuns = () => {
    const [listaState, setListaState] = useState({
        repos: [],
        });

    useEffect(() => {
        getAlbuns('1').then((response) => {
            setListaState({ repos: response });
        });
        }, [setListaState]);

    function composite(album) {
        return (
            <div className="album">
                <div className="nomeAlbum">{album.nome}</div>
                <div className="ano">{album.ano}</div>
            </div>
        )
    }

    let items = listaState.repos.map((album) => {
        return composite(album);
    })

    return (
        <div className="albuns">
            <h3>Albuns</h3>
            <List name="listaAlbuns" items={items} />
        </div>
    )
};

export default ListaAlbuns;