import alert from './alert'
import constant from './constant'
import date from './date'
import loading from './loading'
import telefone from './telefone'

export default {
    install(Vue) {
        Vue.mixin(alert)
        Vue.mixin(constant)
        Vue.mixin(date)
        Vue.mixin(loading)
        Vue.mixin(telefone)
    }
}