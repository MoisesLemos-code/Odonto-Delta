import {mensagens, mutationTypes, notificacoesDefault} from '@/core/constants'

export default {
    methods: {
        mostrarNotificacaoErro(mensagem) {
            if (mensagem === 'GENERAL') {
                mensagem = mensagens.DISCONNECTED
            }
            this.$store.commit(mutationTypes.COMUM.SET_NOTIFICACAO, {
                mensagem,
                cor: 'error',
                mostrar: true
            })
        },
        mostrarNotificacaoErroDefault() {
            this.$store.commit(mutationTypes.COMUM.SET_NOTIFICACAO, {
                mensagem: notificacoesDefault.ERRO_DEFAULT,
                cor: 'error',
                mostrar: true
            })
        },
        mostrarNotificacaoSucesso(mensagem) {
            this.$store.commit(mutationTypes.COMUM.SET_NOTIFICACAO, {
                mensagem,
                cor: 'success',
                mostrar: true
            })
        },
        mostrarNotificacaoSucessoDefault() {
            this.$store.commit(mutationTypes.COMUM.SET_NOTIFICACAO, {
                mensagem: notificacoesDefault.SUCESSO_DEFAULT,
                cor: 'success',
                mostrar: true
            })
        },
        mostrarNotificacaoAviso(mensagem) {
            this.$store.commit(mutationTypes.COMUM.SET_NOTIFICACAO, {
                mensagem,
                cor: 'warning',
                mostrar: true
            })
        },
    }
}
