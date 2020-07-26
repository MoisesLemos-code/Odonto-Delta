import {exceptions, formatosDefault, fusoHorario, mensagens, notificacoesDefault, produto, reconhecimento} from '@/core/constants'

export default {
    data() {
        return {
            exceptions,
            formatosDefault,
            fusoHorario,
            mensagens,
            notificacoesDefault,
            produto,
            reconhecimento
        }
    },
    methods: {
        formatarLista(constant) {
            const list = Object.getOwnPropertyNames(constant).sort()
            if (list.indexOf('__ob__') >= 0) {
                list.splice(list.indexOf('__ob__'), 1)
            }
            return list
        }
    }
}
