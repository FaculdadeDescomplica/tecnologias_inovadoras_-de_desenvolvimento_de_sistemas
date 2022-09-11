import React, { useState, useEffect } from 'react';
import { useFetch } from "../hooks/useFetch";

const ListaMusicas = ({ idAlbum }) => {
    const { data: musicas, dataItem: musica, httpConfig, loading } = useFetch("/musicas", "?idAlbum=" + idAlbum);
    const [nome, setNome] = useState("");
    const [duracao, setDuracao] = useState("");
    
    useEffect(() => {
        setNome("");
        setDuracao("");
    }, [idAlbum]);
    
    useEffect(() => {
        async function fetchData() {
            if (musica !== null) {
                setNome(musica.nome);
                setDuracao(musica.duracao);
            }
        };

        fetchData();
    }, [musica]);
    
    const adicionarMusica = () => {
        const novaMusica = {
            nome,
            duracao,
            idAlbum,
        };
        httpConfig(novaMusica, "POST", null);
    }

    const alterarMusica = () => {
        if (musica !== null) {
            const novaMusica = {
                nome,
                duracao,
                idAlbum,
            };
            
            httpConfig(novaMusica, "PUT", musica.id);
        }
    }

    const excluirMusica = () => {
        if (musica !== null)
            httpConfig(null, "DELETE", musica.id);
    }

    return (
        <div id="musica">
            <h1>Músicas</h1>
            {loading && <p>Carregando músicas...</p>}
            <ul>
                {musicas && musicas.map((musica) => (
                    <li key={musica.id} id={musica.id} onClick={(e) => httpConfig(null, "GET", e.target.id)}>
                        {musica.nome}<br/>
                        Duração: {musica.duracao}
                    </li>
                ))}
            </ul>
            <br/>
            <div>
                <p>
                    <label>
                        <span>Música: </span>
                        <input 
                            name="musica"
                            placeholder="Digite o nome da música"
                            type="text"
                            value={nome}
                            onChange={(e) => setNome(e.target.value)}/>
                    </label>
                    <br/>
                    <label>
                        <span>Duração: </span>
                        <input 
                            name="duracao"
                            placeholder="Digite a duração da música"
                            type="text"
                            value={duracao}
                            onChange={(e) => setDuracao(e.target.value)}/>
                    </label>
                </p>
                <button onClick={adicionarMusica}>Adicionar</button>
                <button onClick={alterarMusica}>Alterar</button>
                <button onClick={excluirMusica}>Excluir</button>
            </div>
        </div>
    )
};

export default ListaMusicas;