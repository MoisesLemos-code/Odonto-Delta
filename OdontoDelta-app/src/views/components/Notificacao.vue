<template>
    <div>
        <v-snackbar
                :color="notificacao.cor"
                :right="right"
                :top="top"
                dark
                v-model="notificacao.mostrar">
            <v-icon class="mr-3" color="white">mdi-bell-plus</v-icon>
            <div class="body">
                <h4 class="title">{{notificacao.titulo}}</h4>
                <p class="msg">{{notificacao.mensagem}}</p>
            </div>
            <v-icon @click="notificacao.mostrar = false" size="16">mdi-close-circle</v-icon>
        </v-snackbar>
    </div>
</template>

<script>
    import { mapState } from 'vuex'

    export default {
        name: 'notificacao',
        data: () => ({
            top: true,
            right: true
        }),
        computed: {
            ...mapState(['notificacao'])
        }
    }
</script>

<style lang='stylus' scoped>
    .body
        display inline-block
        margin 0px
</style>