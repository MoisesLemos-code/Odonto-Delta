import axios from 'axios'

export const http = axios.create({
    // baseURL: 'https://odonto-delta.herokuapp.com'
    baseURL: 'http://localhost:8080/'
})
