export default [
    {
        path: '/login',
        name: 'Login',
        component: () => import('@/views/pages/login/Login'),
        meta: {
            guest: true,
            page: {
                title: 'Login'
            }
        }
    },
]