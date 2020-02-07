import {config} from '../config';
import {authHeader} from './auth-header';

export const userService = {
    login,
    logout
};

function login(username, password) {

    let body = {
        username: username,
        password: password
    };

    return axios.post(`${config.baseUrl}/authorize`, body, {
        headers: {'Content-Type': 'application/json'}
    })
        .then(handleResponse)
        .then(response => {
            let user = {};
            if (response) {
                user.authdata = window.btoa(username + ':' + password);
                localStorage.setItem('user', JSON.stringify(user));
            }

            return user;
        });
}

function logout() {
    localStorage.removeItem('user');
}


function handleResponse(response) {

    const data = response.data;
    if (response.status !== 200) {
        if (response.status === 401) {
            logout();
            location.reload(true);
        }

        const error = (data && data.message) || response.statusText;
        return Promise.reject(error);
    }

    return data.response;
}
