import React, { useState, useEffect } from 'react';
import { useFetch } from "../hooks/useFetch";

const ListaArtistas = ({ handleArtista, handleAlbum }) => {
    const { data: artistas, dataItem: artista, httpConfig, loading } = useFetch("/artistas", null);
    const [nome, setNome] = useState("");
    const [contador, setContador] = useState(0);
    
    useEffect(() => {
        async function fetchData() {
            if (artista !== null) {
                setNome(artista.nome);
                handleArtista(artista.id);
                mudaAlbum();
            }
        };

        fetchData();
    }, [artista]);

    const mudaAlbum = () => {
        setContador(contador + 1);
        handleAlbum("none" + contador);
    }

    const selecionarArtista = (e) => {
        httpConfig(null, "GET", e.target.id);
        mudaAlbum();
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
                    <li key={artista.id} id={artista.id} onClick={selecionarArtista}>
                        {artista.nome}
                    </li>
                ))}
            </ul>
            <br/>
            <div>
                <p>
                    <label>
                        <span>Artista: </span>
                        <input 
                            name="artista"
                            placeholder="Digite o nome do artista"
                            type="text"
                            value={nome}
                            onChange={(e) => setNome(e.target.value)}/>
                    </label>
                    
                </p>
                <button onClick={adicionarArtista}>Adicionar</button>
                <button onClick={alterarArtista}>Alterar</button>
                <button onClick={excluirArtista}>Excluir</button>
            </div>
        </div>
    )
};

export default ListaArtistas;