import SpringApiUrl from './SpringApiUrl.js';
import axios from 'axios';

function getArtistas() {
    return axios.get(SpringApiUrl('/artistas')).then(function (response) {
        return response.data;
    });
}

function getArtista(props) {
    return axios.get(SpringApiUrl('/artistas/' + props.id)).then(function (response) {
        return response.data;
    });
}

export default getArtistas;