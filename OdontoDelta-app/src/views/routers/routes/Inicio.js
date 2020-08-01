export default [
    {
        path: '/inicio',
        name: 'Inicio',
        component: () => import('@/views/pages/inicio/Inicio.vue'),
        meta: {
            requiresAuth: true,
            page:{
                title: 'Inicio'
            }
        }
    },
]