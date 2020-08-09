import {mutationTypes} from '@/core/constants'

export default {

    [mutationTypes.COMUM.SET_CURRENT_PAGE](state, to) {
        if (to.meta && to.meta.page) {
            state.comum.page.title = to.meta.page.title
            state.comum.page.subtitle = to.meta.page.subtitle
        }
    },

    [mutationTypes.COMUM.EFETUAR_LOGOUT](state){
        state.comum.usuarioLogado = {
            codigo: null,
            nomeCompleto: null,
            nome: null,
            token: null,
            admin: false
        }
    },

    [mutationTypes.COMUM.SET_NOTIFICACAO](state, dados){
        state.notificacao = {
            cor: dados.cor,
            mensagem: dados.mensagem,
            mostrar: dados.mostrar
        }
    },

    [mutationTypes.COMUM.SET_USUARIO_LOGADO](state, usuario) {
        state.comum.usuarioLogado = usuario
    },

    [mutationTypes.COMUM.SET_PARAMETROS](state, parametros) {
        state.comum.parametros = parametros
    },

    [mutationTypes.COMUM.SET_GLOBAL_LOADING](state, loading) {
        if (state.comum.isGlobalLoadingEnabled) {
            state.comum.isLoading = loading
        } else if (!loading) {
            state.comum.isLoading = loading
        }
    },

    [mutationTypes.COMUM.SET_LOADING_MESSAGE](state, message) {
        state.comum.loadingMessage = message
    },
}
