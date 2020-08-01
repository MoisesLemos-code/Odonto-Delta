export default [
    {
        path: '/perfil',
        name: 'RedirecionarEditarUsuario',
        component: () => import('@/views/pages/commons/perfil/RedirecionaEditarUsuario'),
        meta: {
            requiresAuth: true,
            is_admin: true
        }
    },
    {
        path: '*',
        name: 'PaginaNaoEncontrada',
        component: () => import('@/views/pages/commons/pagina-nao-encontrada/PaginaNaoEncontrada'),
        meta: {
            page: {
                title: 'Ooops',
                subtitle: 'Página não encontrada'
            }
        }
    },
]