import React, { useState, useEffect } from 'react';
import SpringApiUrl from '../api/SpringApiUrl';

const ListaAlbuns = ({ urlArtista, handleAlbum }) => {
    const [albuns, setAlbuns] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(false);
    const [albumSelecionado, setAlbumSelecionado] = useState();

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);

            try {
                if (urlArtista !== undefined) {
                    const response = await fetch(SpringApiUrl('/albuns' + urlArtista));
                    const jsonResponse = await response.json();
                    setAlbuns(jsonResponse);
                    
                    setError(null);
                } else {
                    setAlbuns(undefined);
                };
            } catch (error) {
                console.log(error.message);
                setError("Houve um erro ao carregar os dados!");
            }

            setLoading(false);
        };

        fetchData();
    }, [urlArtista]);

    const selecionaAlbum = (e) => {
        handleAlbum("?idAlbum=" + e.target.id);
    };

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
        </div>
    )
};

export default ListaAlbuns;