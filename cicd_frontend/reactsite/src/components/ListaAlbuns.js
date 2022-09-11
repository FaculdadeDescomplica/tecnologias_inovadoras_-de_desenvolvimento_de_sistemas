import React, { useState, useEffect } from 'react';
import { useFetch } from "../hooks/useFetch";

const ListaAlbuns = ({ idArtista, handleAlbum }) => {
    const { data: albuns, dataItem: album, httpConfig, loading } = useFetch("/albuns", "?idArtista=" + idArtista);
    const [nome, setNome] = useState();
    const [ano, setAno] = useState();
    const [contador, setContador] = useState(0);
    
    useEffect(() => {
        setNome("");
        setAno("");
        setContador(contador + 1);
        handleAlbum("none" + contador);
    }, [idArtista]);

    useEffect(() => {
        async function  fetchData() {
            if (album !== null) {
                setNome(album.nome);
                setAno(album.ano);
            }
        };

        fetchData();
    }, [album]);

    const selecionaAlbum = (e) => {
        httpConfig(null, "GET", e.target.id);
        handleAlbum(e.target.id);
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
            <h1>Albuns</h1>
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
            <div>
                <p>
                    <label>
                        <span>Album: </span>
                        <input 
                            name="album"
                            placeholder="Digite o nome do album"
                            type="text"
                            value={nome}
                            onChange={(e) => setNome(e.target.value)}/>
                    </label>
                    <br/>
                    <label>
                        <span>Ano: </span>
                        <input 
                            name="ano"
                            placeholder="Digite o ano do album"
                            type="text"
                            value={ano}
                            onChange={(e) => setAno(e.target.value)}/>
                    </label>
                </p>
                <button onClick={adicionarAlbum}>Adicionar</button>
                <button onClick={alterarAlbum}>Alterar</button>
                <button onClick={excluirAlbum}>Excluir</button>
            </div>
        </div>
    )
};

export default ListaAlbuns;