import React, { useState, useEffect } from 'react';
import { useFetch } from "../hooks/useFetch";

const ListaAlbuns = ({ idArtista, handleAlbum }) => {
    const { data: albuns, dataItem: album, httpConfig, loading } = useFetch("/albuns", "?idArtista=" + idArtista);
    const [nome, setNome] = useState("");
    const [ano, setAno] = useState("");
    
    useEffect(() => {
        const fetchData = async () => {
            if (albuns === null || albuns.length === 0) {
                setNome("");
                setAno("");
            }
            
            handleAlbum(null);
        };

        fetchData();
    }, [idArtista, handleAlbum, albuns]);

    useEffect(() => {
        const fetchData = async () => {
            if (album !== null) {
                setNome(album.nome);
                setAno(album.ano);
                handleAlbum(album.id);
            } else {
                setNome("");
                setAno("");
                handleAlbum(null);
            }
        };

        fetchData();
    }, [handleAlbum, album]);

    const selecionaAlbum = (e) => {
        httpConfig(null, "GET", e.target.id);
    };

    const onChangeAlbumNome = (e) => {
        setNome(e.target.value);
    }

    const onChangeAlbumAno = (e) => {
        setAno(e.target.value);
    }

    const adicionarAlbum = () => {
        const novoAlbum = {
            nome,
            ano,
            idArtista,
        };
        httpConfig(novoAlbum, "POST", null);
    }

    const alterarAlbum = () => {
        if (album !== null) {
            const novoAlbum = {
                nome,
                ano,
                idArtista,
            };
            
            httpConfig(novoAlbum, "PUT", album.id);
        }
    }

    const excluirAlbum = () => {
        if (album !== null)
            httpConfig(null, "DELETE", album.id);
    }

    return (
        <div id="album">
            <h3>Albuns</h3>
            {loading && <p>Carregando albuns...</p>}
            <ul>
                {albuns && albuns.map((album) => (
                    <li key={album.id} id={album.id} onClick={selecionaAlbum}>
                        {album.nome}<br/>
                        Ano: {album.ano}
                    </li>
                ))}
            </ul>
            <br/>
            <div id="editarAlbum">
                Album: 
                <input 
                    type="text"
                    value={nome}
                    onChange={onChangeAlbumNome}/>
                <br/>
                Ano:
                <input 
                    type="text"
                    value={ano}
                    onChange={onChangeAlbumAno}/>
                <br/>
                <button onClick={adicionarAlbum}>Adicionar</button>
                <button onClick={alterarAlbum}>Alterar</button>
                <button onClick={excluirAlbum}>Excluir</button>
            </div>
        </div>
    )
};

export default ListaAlbuns;