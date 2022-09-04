import React, { useState, useEffect } from 'react';
import { useFetch } from "../hooks/useFetch";

const ListaMusicas = ({ idAlbum }) => {
    const { data: musicas, dataItem: musica, httpConfig, loading } = useFetch("/musicas", "?idAlbum=" + idAlbum);
    const [nome, setNome] = useState();
    const [duracao, setDuracao] = useState();

    useEffect(() => {
        const fetchData = async () => {
            if (musicas === null) {
                setNome("");
                setDuracao("");
            }
        };

        fetchData();
    }, [musicas]);

    useEffect(() => {
        const fetchData = async () => {
            if (musica !== null) {
                setNome(musica.nome);
                setDuracao(musica.duracao);
            } else {
                setNome("");
                setDuracao("");
            }
        };

        fetchData();
    }, [musica]);

    const selecionaMusica = (e) => {
        httpConfig(null, "GET", e.target.id);
    };

    const onChangeMusicaNome = (e) => {
        setNome(e.target.value);
    }

    const onChangeMusicaDuracao = (e) => {
        setDuracao(e.target.value);
    }

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
            <h3>Músicas</h3>
            {loading && <p>Carregando músicas...</p>}
            <ul>
                {musicas && musicas.map((musica) => (
                    <li key={musica.id} id={musica.id} onClick={selecionaMusica}>
                        {musica.nome}<br/>
                        Duração: {musica.duracao}
                    </li>
                ))}
            </ul>
            <br/>
            <div id="editarMusica">
                Música: 
                <input 
                    type="text"
                    value={nome}
                    onChange={onChangeMusicaNome}/>
                <br/>
                Duração:
                <input 
                    type="text"
                    value={duracao}
                    onChange={onChangeMusicaDuracao}/>
                <br/>
                <button onClick={adicionarMusica}>Adicionar</button>
                <button onClick={alterarMusica}>Alterar</button>
                <button onClick={excluirMusica}>Excluir</button>
            </div>
        </div>
    )
};

export default ListaMusicas;