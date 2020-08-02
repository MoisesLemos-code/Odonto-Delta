import axios from 'axios'
import ParamUtils from '../utils/ParamUtils'


class UsuarioApiClient {

    async efetuarLogin(dados) {
        return await axios.post('/login', dados)
    }

    async buscarTodos(paginacao, filtros) {
        return await axios.get(`/usuario/page?${ParamUtils.ObjectsToParams([paginacao, filtros])}`)
    }

    async buscarId(id){
        return await axios.get(`/usuario/find/${id}`)
    }

    async salvar(dados){
        return await axios.post('/usuario/insert', dados)
    }

    async editar(dados) {
        return await axios.post(`/usuario/update/${dados.id}`, dados)
    }

    async remover(id){
        return await axios.get(`/usuario/delete/${id}`)
    }
}

export default new UsuarioApiClient()
