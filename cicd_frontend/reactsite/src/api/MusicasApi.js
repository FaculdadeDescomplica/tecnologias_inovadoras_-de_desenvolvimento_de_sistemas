import SpringApiUrl from './SpringApiUrl.js';
import axios from 'axios';

function getMusicas (idAlbum) {
    return axios.get(SpringApiUrl('/musicas?idAlbum=' + idAlbum)).then(function (response) {
        return response.data;
    });
};

function getMusica (idMusica) {
    return axios.get(SpringApiUrl('/musicas/' + idMusica)).then(function (response) {
        return response.data;
    });
}

export default getMusicas;