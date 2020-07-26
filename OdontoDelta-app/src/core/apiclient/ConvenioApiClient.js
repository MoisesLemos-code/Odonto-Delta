import axios from 'axios'
import ParamUtils from '../utils/ParamUtils'

class ConvenioApiClient {

    async buscarTodos(filtros, paginacao) {
        return axios.get(`/projeto/api/convenios?${ParamUtils.ObjectsToParams([paginacao, filtros])}`)
    }

    async salvar(dados) {
        const response = dados
        return null
    }

    async editar(dados) {
        const response = dados
        return null
    }

    async buscarId(id) {
        const response = {
            data: {
                id: id,
                numero: '095848',
                nome: 'Caixa Seguradora de Bens Social',
                concedente: {
                    id: 1, nome: 'Bradesco Seguros'
                },
                fonteRecurso: {
                    id: 2, nome: 'Aquisição de protótipos'
                },
                situacao: 'INATIVO',
                dataHoraInicial: '2020-07-15T23:00:00.000-0400',
                dataHoraFinal: '2020-07-16T23:00:00.000-0400'
            }
        }
        return response
    }
}

export default new ConvenioApiClient()
