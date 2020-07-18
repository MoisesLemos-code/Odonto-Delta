import Vue from 'vue'
import router from '@/globals/router'
import store from '@/commons/store'
import vuetify from '@/globals/vendors/vuetify'
import App from './App'
import { sync } from 'vuex-router-sync'
import '@/globals/http/axios'
import '@/globals/vendors'
import '@/commons/theme'

sync(store, router, vuetify)
class AppBuilder {

    async build() {
        this.instanciarComponenteRaiz()
    }

    instanciarComponenteRaiz() {
        new Vue({
            vuetify,
            router,
            store,
            render: h => h(App)
        }).$mount('#app')
    }
}

export default new AppBuilder()
