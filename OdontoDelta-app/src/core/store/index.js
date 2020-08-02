import Vuex from 'vuex'
import { actions, getters, mutations, state } from './comum'
import usuario from './usuario'
import menu from './menu'

export default new Vuex.Store({
    state,
    getters,
    mutations,
    actions,
    modules: {
        usuario,
        menu
    }
})
