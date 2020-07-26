import actions from './actions'
import {actionTypes, mutationTypes} from '@/core/constants'

let url, body, mockResponseData, returnedResponse

jest.mock('axios', () => ({
    post(_url, _body) {
        return new Promise((resolve) => {
            url = _url
            body = _body
            resolve({ data: mockResponseData })
        })
    },
    put(_url, _body) {
        return new Promise(resolve => {
            url = _url
            body = _body
            resolve({ data: mockResponseData })
        })
    },
    get(_url) {
        return new Promise((resolve) => {
            url = _url
            resolve({ data: mockResponseData })
        })
    }
}))

describe('Actions', () => {
    const commit = jest.fn()
    const context = jest.fn()
    let state

    beforeEach(() => {
        url = ''
        body = undefined
        mockResponseData = true
        state = {
            loki: {
                product: {
                    id: 401,
                    name: 'patrimonio-mobiliario'
                }
            }
        }
    })

    it('BUSCAR_PRODUTO_POR_NOME', async () => {
        const packageJson = { name: 'patrimonio-mobiliario' }
        returnedResponse = await actions[actionTypes.COMUM.BUSCAR_PRODUTO_POR_NOME]({ commit }, packageJson)
        expect(url).toBe('/hal/public/produtos?produtoNome=patrimonio-mobiliario')
        expect(commit).toHaveBeenCalledWith(mutationTypes.COMUM.SET_PRODUTO, true)
    })

    it('BUSCAR_USUARIO_LOGADO', async () => {
        returnedResponse = await actions[actionTypes.COMUM.BUSCAR_USUARIO_LOGADO]({ commit, state })
        expect(url).toBe(`/hal/usuario/sessao?produtoId=${state.loki.product.id}`)
        expect(commit).toHaveBeenCalledWith(mutationTypes.COMUM.SET_USUARIO_LOGADO, true)
    })

    it('BUSCAR_LINK_EDITAR_USUARIO', async () => {
        const payload = { 'produto': 'patrimonio-mobiliario', 'uri': 'http://localhost/' }

        returnedResponse = await actions[actionTypes.COMUM.BUSCAR_LINK_EDITAR_USUARIO]({ state })
        expect(url).toBe('/hal/editarUsuario')
        expect(body).toEqual(payload)
        expect(returnedResponse).toBeTruthy()
    })
})
