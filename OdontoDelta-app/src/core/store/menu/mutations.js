import {mutationTypes} from '@/core/constants'

export default {
    [mutationTypes.DRAWER.SET_ASIDE](state, closed) {
        state.asideClosed = closed
    },

    [mutationTypes.DRAWER.SET_ASIDE_HIDE](state, hide) {
        state.asideHide = hide
    },

    [mutationTypes.DRAWER.TOOGLE_ASIDE](state) {
        state.asideClosed = !state.asideClosed
    },
}
