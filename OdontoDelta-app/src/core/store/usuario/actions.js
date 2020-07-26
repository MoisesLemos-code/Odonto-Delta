import api from '@/core/apiclient'
import {actionTypes} from '@/core/constants'

export default {

    async [actionTypes.USUARIO.BUSCAR_TODOS_USUARIOS]({state}) {
        return null
    },

    async [actionTypes.USUARIO.SALVAR_USUARIO]({commit}, dados) {
    },

    async [actionTypes.USUARIO.EDITAR_USUARIO]({commit}, dados) {
    },

    async [actionTypes.USUARIO.BUSCAR_USUARIO_POR_ID](context, id) {

    },
}
