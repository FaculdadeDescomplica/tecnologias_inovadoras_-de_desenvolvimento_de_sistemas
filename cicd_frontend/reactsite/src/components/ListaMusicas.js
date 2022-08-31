import React, { useState, useEffect } from 'react';
import getMusicas from '../api/MusicasApi.js';
import List from 'react-list-select';

const ListaMusicas = () => {
    const [listaState, setListaState] = useState({
        repos: [],
        });

    useEffect(() => {
        getMusicas('1').then((response) => {
            setListaState({ repos: response });
        });
        }, [setListaState]);

    function composite(musica) {
        return (
            <div className="musica">
                <div className="nomeMusica">{musica.nome}</div>
                <div className="duracao">{musica.duracao}</div>
            </div>
        )
    }

    let items = listaState.repos.map((musica) => {
        return composite(musica);
    })

    return (
        <div className="musicas">
            <h3>Músicas</h3>
            <List name="listaMusicas" items={items} />
        </div>
    )
};

export default ListaMusicas;