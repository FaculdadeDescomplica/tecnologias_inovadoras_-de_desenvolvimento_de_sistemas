import React, { useState, useEffect } from 'react';
import getItens from '../api/ItensApi.js';
import getMusica from '../api/MusicasApi.js';
import List from 'react-list-select';

const ListaItens = () => {
    const [listaState, setListaState] = useState({
        repos: [],
    });

    useEffect(() => {
        getItens('1').then((response) => {
            setListaState({ repos: response });
        });
        }, [setListaState]);

    function composite(item) {
        return (
            <div className="item">
                <div className="idMusica">{item.idMusica}</div>
            </div>
        )
    }

    let items = listaState.repos.map((item) => {
        return composite(item);
    })

    return (
        <div className="itens">
            <h3>Itens</h3>
            <List name="listaItens" items={items} />
        </div>
    )
};

export default ListaItens;