import React, { useState, useEffect } from 'react';
import SpringApiUrl from '../api/SpringApiUrl';

const ListaMusicas = ({ urlAlbum }) => {
    const [musicas, setMusicas] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(false);

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);

            try {
                if (urlAlbum !== undefined) {
                    const response = await fetch(SpringApiUrl('/musicas' + urlAlbum));
                    const jsonResponse = await response.json();
                    setMusicas(jsonResponse);
                    
                    setError(null);
                } else {
                    setMusicas(undefined);
                };
            } catch (error) {
                console.log(error.message);
                setError("Houve um erro ao carregar os dados!");
            }

            setLoading(false);
        };

        fetchData();
    }, [urlAlbum]);

    return (
        <div id="musica">
            <h3>Músicas</h3>
            {loading && <p>Carregando músicas...</p>}
            <ul>
                {musicas && musicas.map((musica) => (
                    <li key={musica.id} id={musica.id}>
                        {musica.nome}<br/>
                        Duração: {musica.duracao}
                    </li>
                ))}
            </ul>
        </div>
    )
};

export default ListaMusicas;