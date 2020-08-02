export default {
    dadosGerais: {},
    resultadoBuscaTodosUsuarios: {
        filtros: {
            conteudo: {
                default: null,
                label: 'Pesquisa',
                value: ''
            }
        },
        paginacao: {
            descending: false,
            groupBy: [],
            groupDesc: [],
            itemsPerPage: 10,
            multiSort: false,
            mustSort: false,
            page: 1,
            rowsPerPage: 10,
            sortBy: ['nome'],
            sortDesc: [false]
        }
    },
    rota: {
        origem: 'UsuarioListagem'
    }
}
