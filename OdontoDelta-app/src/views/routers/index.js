import Router from 'vue-router'
import goTo from 'vuetify/es5/services/goto'
import comum from './routes/comum'
import inicio from './routes/Inicio'

const index = new Router({
    routes: [
        {
            path: '/',
            name: 'Inicial',
            redirect: () => {
                return { name: 'Inicio' }
            },
        },
        ...comum,
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

export default index