import React, { useState, useEffect } from 'react';
import { useFetch } from "../hooks/useFetch";

const ListaArtistas = ({ handleArtista, handleAlbum }) => {
    const { data: artistas, dataItem: artista, httpConfig, loading, error } = useFetch("/artistas");
    const [nome, setNome] = useState("");
    
    useEffect(() => {
        const fetchData = async () => {
            if (artista !== null) {
                setNome(artista.nome);
                handleArtista("?idArtista=" + artista.id);
                handleAlbum(undefined);
            } else
                setNome("");
        };

        fetchData();
    }, [artista]);

    const selecionaArtista = (e) => {
        httpConfig(null, "GET", e.target.id);
    };

    const onChangeArtista = (e) => {
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
            <h3>Artistas</h3>
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
                Artista: 
                <input 
                    type="text"
                    value={nome}
                    onChange={onChangeArtista}/>
                <button onClick={adicionarArtista}>Adicionar</button>
                <button onClick={alterarArtista}>Alterar</button>
                <button onClick={excluirArtista}>Excluir</button>
            </div>
        </div>
    )
};

export default ListaArtistas;