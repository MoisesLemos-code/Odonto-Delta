import Vue from 'vue'
import '@babel/polyfill'
import '@/plugins'
import mixins from '@/views/mixins'

class Register {

    registerAll() {
        Vue.use(mixins)
    }

}

export default new Register()
