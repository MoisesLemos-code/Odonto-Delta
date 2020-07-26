import axios from 'axios'

class UsuarioApiClient {

    async atualiazarSessaoDominio(produtoId, dominioTipoCliente) {
        return await axios.put(`/hal/usuario/sessao/atualizarSessaoMultiplosDominios?produtoId=${produtoId}&dominioTipoCliente=${dominioTipoCliente}`)
    }

    async buscarLogado(produtoId) {
        //await axios.get(`/hal/usuario/sessao?produtoId=${produtoId}`)
        const obj = {
            data: {
                companyId: produtoId,
                domainType: 'DEFAULT',
                lang: 'pt-BR',
                name: 'Administrador do sistema',
                userId: 1,
                userName: 'admin'
            }
        }
        return obj
    }

    async editar(payload) {
        return await axios.post('/hal/editarUsuario', payload)
    }
}

export default new UsuarioApiClient()
