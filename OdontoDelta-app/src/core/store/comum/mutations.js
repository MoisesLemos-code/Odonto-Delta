import Vue from 'vue'
import {mutationTypes, produto} from '@/core/constants'
import FileManagerUtils from '@/core/utils/FileManagerUtils'

export default {

    [mutationTypes.COMUM.SET_EXPANDIR_MENU](state) {
        state.miniMenuLateral = false
    },

    [mutationTypes.COMUM.SET_RETRAIR_MENU](state) {
        state.miniMenuLateral = true
    },

    [mutationTypes.COMUM.SET_EXPANDIR_MENU_MOBILE](state) {
        state.menuLateral = true
    },

    [mutationTypes.COMUM.SET_RETRAIR_MENU_MOBILE](state) {
        state.menuLateral = false
    },

    [mutationTypes.COMUM.SET_MENU_MOBILE_ON](state) {
        state.menuLateralMobile = true
    },

    [mutationTypes.COMUM.SET_MENU_MOBILE_OFF](state) {
        state.menuLateralMobile = false
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
        const user = {
            id: usuario.userId,
            name: usuario.userName,
            fullName: usuario.name,
            photo: FileManagerUtils.createThumbnailUrl(usuario.imageUrl),
            type: usuario.tipoUsuario,
            domains: usuario.domains,
            domainId: usuario.domainId,
            domainName: usuario.domainName,
            domainType: usuario.domainType,
            authorities: usuario.authorities
        }
        Vue.set(state.loki, 'user', user)
    },

    [mutationTypes.COMUM.SET_PARAMETROS](state, parametros) {
        state.parametros = parametros
    }
}
