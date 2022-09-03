import React, { useState, useEffect } from 'react';
import SpringApiUrl from '../api/SpringApiUrl';
import { useFetch } from "../hooks/useFetch";

const ListaArtistas = ({ handleArtista, handleAlbum }) => {
    const [artistas, setArtistas] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(false);
    const [callFetch, setCallFetch] = useState(false);
    const [artistaSelecionado, setArtistaSelecionado] = useState();
    const [idArtista, setIdArtista] = useState(null);
    const [nome, setNome] = useState("");
    const [config, setConfig] = useState(null);
    const [method, setMethod] = useState(null);
    
    const httpConfig = (data, method) => {
        if (method === "POST") {
            setConfig({
                method: "POST",
                headers: {
                "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
            });
    
            setMethod("POST");
        } else if (method === "PUT") {
            setConfig({
                method: "PUT",
                headers: {
                "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
            });
        
            setMethod("PUT");
        } else if (method === "DELETE") {
            setConfig({
                method: "DELETE",
                headers: {
                "Content-Type": "application/json",
                },
            });
        
            setMethod("DELETE");
        }
    };
    
    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);

            try {
                const response = await fetch(SpringApiUrl('/artistas'));
                const jsonResponse = await response.json();

                setArtistas(jsonResponse);
                
                setError(null);
            } catch (error) {
                console.log(error.message);
                setError("Houve um erro ao carregar os dados!");
            }

            setLoading(false);
        };

        fetchData();
    }, [callFetch]);

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);

            try {
                if (idArtista !== null) {
                    const response = await fetch(SpringApiUrl("/artistas/" + idArtista));
                    const jsonResponse = await response.json();

                    setNome(jsonResponse.nome);
                    
                    setError(null);
                }
            } catch (error) {
                console.log(error.message);
                setError("Houve um erro ao carregar os dados!");
            }

            setLoading(false);
        };

        fetchData();
    }, [idArtista]);

    useEffect(() => {
        const httpRequest = async () => {
          if (method === "POST") {
            setLoading(true);
    
            let fetchOptions = [SpringApiUrl("/artistas"), config];
            const res = await fetch(...fetchOptions);
            const json = await res.json();
    
            setCallFetch(json);
          } else if (method === "PUT") {
            const res = await fetch(SpringApiUrl("/artistas/" + idArtista), config);
    
            const json = await res.json();
    
            setCallFetch(json);
          } else if (method === "DELETE") {
            const res = await fetch(SpringApiUrl("/artistas/" + idArtista), config);
    
            const json = await res.status;
    
            setCallFetch(json);
          }
        };
    
        httpRequest();
    }, [config]);

    const selecionaArtista = (e) => {
        setIdArtista(e.target.id);
        handleArtista("?idArtista=" + e.target.id);
        handleAlbum(undefined);
    };

    const onChangeArtista = (e) => {
        console.log(e.target.value);
        setNome(e.target.value);
    }

    const adicionarArtista = (e) => {
        const novoArtista = {
            nome,
        };
        httpConfig(novoArtista, "POST");
    }

    const alterarArtista = (e) => {
        const novoArtista = {
            nome,
        };
        httpConfig(novoArtista, "PUT");
    }

    const excluirArtista = (e) => {
        httpConfig(null, "DELETE");
        setNome("");
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