import { http } from './config'

export default {
    atualizar: (usuario) => {
        return http.put('usuario/update', usuario)
    },

    getCategoria: () => {
        return http.get('categoria/all')
    },

    postCategoria: (categoria) => {
        return http.post('categoria/inserir', categoria)
    },

    postPeca: (peca) => {
        return http.post('peca/inserir', peca)
    },

    getPecaPorCategoria: (categoriaId) => {
        return http.get(`produto/produto/all/${categoriaId}`)
    },

    postOrcamento: (orcamento) => {
        return http.post('orcamento/inserir', orcamento)
    },

    postMesa: (mesa) => {
        return http.post('mesa/inserir', mesa)
    },

    getMesas: () => {
        return http.get('mesa/all')
    }
}
