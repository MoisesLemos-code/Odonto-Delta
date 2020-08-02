import api from '@/core/apiclient'
import {actionTypes, mutationTypes} from '@/core/constants'

export default {

    async [actionTypes.USUARIO.BUSCAR_TODOS_USUARIOS]({state}) {
        const {filtros, paginacao} = state.resultadoBuscaTodosUsuarios
        const {data} = await api.usuario.buscarTodos(filtros, paginacao)
        return data
    },

    async [actionTypes.USUARIO.SALVAR_USUARIO]({commit}, dados) {
        const {data} = await api.usuario.salvar(dados)
        commit(mutationTypes.USUARIO.SET_DADOS_GERAIS, data)
    },

    async [actionTypes.USUARIO.EDITAR_USUARIO]({commit}, dados) {
        const {data} = await api.usuario.editar(dados)
        commit(mutationTypes.USUARIO.SET_DADOS_GERAIS, data)
    },

    async [actionTypes.USUARIO.BUSCAR_USUARIO_POR_ID](context, id) {
        const {data} = await api.usuario.buscarId(id)
        return data
    }
}
