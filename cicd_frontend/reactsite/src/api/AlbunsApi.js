import SpringApiUrl from './SpringApiUrl.js';
import axios from 'axios';

function getAlbuns(idArtista) {
    return axios.get(SpringApiUrl('/albuns?idArtista=' + idArtista)).then(function (response) {
        return response.data;
    });
};

export default getAlbuns