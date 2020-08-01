export default {
    loki: {
        product: {
            id: 1,
            name: 'odonto-delta'
        }
    },
    comum: {
        defaultLoadingMessage: 'Carregando...',
        rota: {
            origem: 'Inicial'
        },
        parametros: {}
    },
    page: {
      title: '',
      subtitle: ''
    },
    notificacao: {
        cor: null,
        mensagem: null,
        mostrar: false
    },
    usuarioLogado: {
        codigo: null,
        nomeCompleto: 'Administrador',
        nome: 'admin',
        perfil: 'admin',
        token: 'aaaa',
        admin: false
    },
    asideClosed: false,
    asideHide: true,
    avatarActions: {
        1: { title: 'Perfil', icon: 'mdi-account', path: '/profile' },
        2: { title: 'Configurações', icon: 'settings', path: '/settings' }
    },
    menuActions: [
        {
            path: '/inicio',
            icon: 'mdi-home',
            name: 'Início'
        },
        {
            path: '/orcamento',
            icon: 'mdi-account-cash',
            name: 'Orçamento'
        },
        {
            path: '/perfil',
            icon: 'mdi-account',
            name: 'Cadastros',
            children: [
                {
                    path: '/perfil',
                    icon: 'mdi-account',
                    name: 'Clientes',
                },
                {
                    path: '/perfil',
                    icon: 'mdi-account',
                    name: 'Serviços',
                },
                {
                    path: '/perfil',
                    icon: 'mdi-account',
                    name: 'Peças',
                }
            ]
        },
        {
            path: '/usuarios',
            icon: 'mdi-account-settings',
            name: 'Gerenciar Usuários'
        },
        {
            path: '/produtos',
            icon: 'mdi-food-fork-drink',
            name: 'Gerenciar Produtos'
        },
        {
            path: '/relatorios',
            icon: 'mdi-clipboard-outline',
            name: 'Relatórios'
        }
    ],
    menuLateralMobile: false
}
