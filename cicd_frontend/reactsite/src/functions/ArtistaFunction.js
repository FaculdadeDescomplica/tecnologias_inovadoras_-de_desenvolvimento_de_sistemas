import React, { useState, useEffect  } from 'react';
import ArtistasApi from '../api/ArtistasApi';

const ArtistaFunction = () => {
    const [listaState, setListaState] = useState({
        repos: [],
        });

    useEffect(() => {
        ArtistasApi().then((response) => {
            setListaState({ repos: response });
        });
        }, [setListaState]);

    function composite(artista) {
        return (
            <div className="artista">
                <div className="id">{artista.id}</div>
                <div className="nome">{artista.nome}</div>
            </div>
        );
    }

    return (
        listaState.repos.map((artista) => {
            return composite(artista);
        })
    );
}

export default ArtistaFunction;