<template>
    <div>
        <v-snackbar
                :color="notificacao.cor"
                :right="right"
                :top="top"
                :timeout="timeout"
                dark
                v-model="notificacao.mostrar">
            <div class="container">
              <div class="containerBody">
            <v-icon class="mr-3" color="white">mdi-bell-plus</v-icon>
            <div class="body">
                <span class="title">{{notificacao.titulo}}</span>
                <a class="msg">{{notificacao.mensagem}}</a>
            </div>
              </div>
            <v-icon @click="fecharNotificacao" size="16">mdi-close-circle</v-icon>
            </div>
        </v-snackbar>
    </div>
</template>

<script>
    import { mapState } from 'vuex'
    import { mutationTypes } from '@/core/constants'

    export default {
        name: 'notificacao',
        data: () => ({
            top: true,
            right: true,
            timeout: 6000
        }),
        computed: {
            ...mapState(['notificacao'])
        },
        methods:{
            fecharNotificacao(){
                this.$store.commit(mutationTypes.COMUM.SET_NOTIFICACAO, {
                    cor: '',
                    titulo: '',
                    mensagem: '',
                    mostrar: false
                })
            }
        }
    }
</script>

<style lang='stylus' scoped>
    .container
        display flex
        flex-direction row
        justify-content space-between
        padding 0px
        margin 0px
    .containerBody
        display flex
        flex-direction row
        padding 0px
        margin 0px
    .body
        display flex
        flex-direction column
        justify-content center
        margin 0px
    .msg
        color #FFF
</style>