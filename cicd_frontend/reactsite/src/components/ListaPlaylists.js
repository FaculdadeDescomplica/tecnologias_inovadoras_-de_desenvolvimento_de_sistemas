import React, { useState, useEffect } from 'react';
import getPlaylists from '../api/PlaylistsApi.js';
import List from 'react-list-select';

const ListaPlaylists = () => {
    const [listaState, setListaState] = useState({
        repos: [],
        });

    useEffect(() => {
        getPlaylists().then((response) => {
            setListaState({ repos: response });
        });
        }, [setListaState]);

    function composite(playlist) {
        return (
            <div className="playlist">
                <div className="descricao">{playlist.descricao}</div>
            </div>
        )
    }

    let items = listaState.repos.map((playlist) => {
        return composite(playlist);
    })

    return (
        <div className="playlists">
            <h3>Playlists</h3>
            <List name="listaPlaylists" items={items} />
        </div>
    )
};

export default ListaPlaylists;