export default {
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
            path: '/cadastros',
            icon: 'mdi-account',
            name: 'Cadastros',
            children: [
                {
                    path: '/clientes',
                    icon: 'mdi-account',
                    name: 'Clientes',
                },
                {
                    path: '/servicos',
                    icon: 'mdi-account',
                    name: 'Serviços',
                },
                {
                    path: '/pecas',
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
    ]
}
