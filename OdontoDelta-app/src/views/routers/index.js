import Router from 'vue-router'
import goTo from 'vuetify/es5/services/goto'
import comum from './routes/comum'
import login from './routes/login'
import inicio from './routes/Inicio'
import {actionTypes} from '@/core/constants'


let router = new Router({
    routes: [
        {
            path: '/',
            name: 'Inicial',
            redirect: () => {
                return { name: 'Inicio' }
            },
        },
        ...comum,
        ...login,
        ...inicio
    ],
    scrollBehavior: (to, from, savedPosition) => {
        let scrollTo = 0

        if (to.hash) {
            scrollTo = to.hash
        } else if (savedPosition) {
            scrollTo = savedPosition.y
        }

        return goTo(scrollTo)
    },
})

router.beforeEach((to, from, next) => {
    const usuario = actionTypes.COMUM.BUSCAR_USUARIO_LOGADO
    if(to.matched.some(record => record.meta.requiresAuth)) {
        if (usuario.token == null) {
            next({ name: 'Login'})
        } else {
            if(to.matched.some(record => record.meta.is_admin)) {
                if(usuario.admin){
                    next()
                }
                else{
                    next({ name: 'Inicio'})
                }
            }else {
                next()
            }
        }
    } else if(to.matched.some(record => record.meta.guest)) {
        if(usuario.token == null){
            next()
        }
        else{
            next({ name: 'Inicio'})
        }
    }else {
        next()
    }
})


export default router