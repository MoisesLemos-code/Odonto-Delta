import api from '@/core/apiclient'
import { actionTypes } from '@/core/constants'

export default {

    async [actionTypes.COMUM.EFETUAR_LOGIN]({ commit }, dados) {
        const response = await api.usuario.efetuarLogin(dados)
        return response
    },
}