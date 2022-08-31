import PythonApiUrl from './PythonApiUrl.js';
import axios from 'axios';

function getItens (idPlaylist) {
    return axios.get(PythonApiUrl('/itens?idPlaylist=' + idPlaylist)).then(function (response) {
        return response.data;
    });
};

export default getItens;