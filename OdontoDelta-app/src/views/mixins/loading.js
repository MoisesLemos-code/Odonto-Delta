import {mapMutations} from 'vuex'
import {mutationTypes} from '@/core/constants'

export default {
    methods: {
        desabilitarLoadingGlobal() {
            this.disableGlobalLoading()
        },
        habilitarLoadingGlobal() {
            this.enableGlobalLoading()
        },
        setMensagemLoading(mensagem) {
            this.setLoadingMessage(mensagem)
        },
        ...mapMutations([
            mutationTypes.COMUM.DISABLE_GLOBAL_LOADING,
            mutationTypes.COMUM.ENABLE_GLOBAL_LOADING,
            mutationTypes.COMUM.SET_LOADING_MESSAGE])
    }
}
