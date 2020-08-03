import Vue from 'vue'
import {mutationTypes, produto} from '@/core/constants'
import FileManagerUtils from '@/core/utils/FileManagerUtils'

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
        state.comum.notificacao = {
            cor: dados.cor,
            mensagem: dados.mensagem,
            mostrar: dados.mostrar
        }
    },

    [mutationTypes.COMUM.SET_LINK_ARQUIVO](state) {
        Vue.set(state.loki.file, 'api', '/patrimonio-intangivel/api/v1/arquivos')
    },

    [mutationTypes.COMUM.SET_MENU_AVATAR](state) {
        const actions = {
            1: { title: 'Perfil', icon: 'person', path: '/perfil' }
        }
        Vue.set(state.loki, 'avatarActions', actions)
    },

    [mutationTypes.COMUM.SET_PRODUTO](state, data) {
        state.loki.product.id = data.id
        state.loki.product.name = data.nome
        state.loki.product.mainLogo = FileManagerUtils.createUrl(data.atributosExtendidos.logoMenu)
        state.loki.product.symbolLogo = FileManagerUtils.createUrl(data.atributosExtendidos.logoMenuRetraido)
        state.loki.product.logoMobile = FileManagerUtils.createUrl(data.atributosExtendidos.logoMobile)
        state.loki.product.version = produto.VERSAO
        state.loki.product.copywrite = produto.COPYRIGHT
        state.loki.product.logoutUrl = produto.LOGOUT_URL
    },

    [mutationTypes.COMUM.SET_USUARIO_LOGADO](state, usuario) {
        state.comum.usuarioLogado = usuario
    },

    [mutationTypes.COMUM.SET_PARAMETROS](state, parametros) {
        state.parametros = parametros
    }
}
