import PythonApiUrl from './PythonApiUrl.js';
import axios from 'axios';

function getPlaylists() {
    return axios.get(PythonApiUrl('/playlists')).then(function (response) {
        return response.data;
    });
};

export default getPlaylists;