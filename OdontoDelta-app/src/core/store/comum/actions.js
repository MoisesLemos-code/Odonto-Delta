import api from '@/core/apiclient'
import { actionTypes, mutationTypes, produto } from '@/core/constants'

export default {

    async [actionTypes.COMUM.BUSCAR_LINK_EDITAR_USUARIO]({ state }) {
        const payload = {
            uri: window.location.origin + window.location.pathname,
            produto: state.loki.product.name
        }
        const { data } = await api.usuario.editar(payload)
        return data
    },

    async [actionTypes.COMUM.BUSCAR_PRODUTO_POR_NOME]({ commit }) {
        const { data } = await api.produto.buscarPorNome(produto.NOME)
        commit(mutationTypes.COMUM.SET_PRODUTO, data)
    },

    async [actionTypes.COMUM.BUSCAR_USUARIO_LOGADO]({ commit, state }) {
        const produtoId = state.loki.product.id
        const { data } = await api.usuario.buscarLogado(produtoId)
        commit(mutationTypes.COMUM.SET_USUARIO_LOGADO, data)
    }
}
