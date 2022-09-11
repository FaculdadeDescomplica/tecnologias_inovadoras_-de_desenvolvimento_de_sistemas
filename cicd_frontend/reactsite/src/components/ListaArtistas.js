import React, { useState, useEffect } from 'react';
import { useFetch } from "../hooks/useFetch";

const ListaArtistas = ({ handleArtista, handleAlbum }) => {
    const { data: artistas, dataItem: artista, httpConfig, loading } = useFetch("/artistas", null);
    const [nome, setNome] = useState("");
    
    useEffect(() => {
        const fetchData = async () => {
            if (artista !== null) {
                setNome(artista.nome);
                handleArtista(artista.id);
                handleAlbum(null);
            } else {
                setNome("");
                handleArtista(null);
                handleAlbum(null);
            }
        };

        fetchData();
    }, [handleArtista, handleAlbum, artista]);

    const selecionaArtista = (e) => {
        httpConfig(null, "GET", e.target.id);
    };

    const onChangeArtistaNome = (e) => {
        setNome(e.target.value);
    }

    const adicionarArtista = () => {
        const novoArtista = {
            nome,
        };
        httpConfig(novoArtista, "POST", null);
    }

    const alterarArtista = () => {
        if (artista !== null) {
            const novoArtista = {
                nome,
            };

            httpConfig(novoArtista, "PUT", artista.id);
        }
    }

    const excluirArtista = () => {
        if (artista !== null)
            httpConfig(null, "DELETE", artista.id);
    }

    return (
        <div id="artista">
            <h1>Artistas</h1>
            {loading && <p>Carregando artistas...</p>}
            <ul>
                {artistas && artistas.map((artista) => (
                    <li key={artista.id} id={artista.id} onClick={selecionaArtista}>
                        {artista.nome}
                    </li>
                ))}
            </ul>
            <br/>
            <div id="editarArtista">
                <p>
                    <label>Artista: </label>
                    <input 
                        type="text"
                        value={nome}
                        onChange={onChangeArtistaNome}/>
                </p>
                <button onClick={adicionarArtista}>Adicionar</button>
                <button onClick={alterarArtista}>Alterar</button>
                <button onClick={excluirArtista}>Excluir</button>
            </div>
        </div>
    )
};

export default ListaArtistas;