<template>
    <div>
        <notificacao/>
            <v-navigation-drawer
                    v-model='drawer'
                    :mini-variant='miniVariant'
                    :right='true'
                    dark
                    permanent
                    expand-on-hover
            >
                <v-list dense nav class='py-0'>
                    <v-list-item two-line :class="miniVariant && 'px-0'">
                        <v-list-item-avatar>
                            <v-icon size='50'>mdi-account-circle</v-icon>
                        </v-list-item-avatar>

                        <v-list-item-content>
                            <v-list-item-title>Application</v-list-item-title>
                            <v-list-item-subtitle>Subtext</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>

                    <v-divider></v-divider>

                    <v-list-item
                            :key='i'
                            :to='link.to'
                            active-class='info'
                            class='v-list-item'
                            v-for='(link, i) in links'
                            link
                    >
                        <v-list-item-icon>
                            <v-icon>{{ link.icon }}</v-icon>
                        </v-list-item-icon>

                        <v-list-item-content>
                            <v-list-item-title>{{ link.text }}</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-navigation-drawer>
    </div>
</template>

<script>
    import notificacao from '@/views/components/Notificacao'
    import {mapMutations, mapState} from 'vuex'
    import {actionTypes, mutationTypes} from '@/core/constants'

    export default {
        name: 'core-navigation-drawer',
        components: {notificacao},
        data: () => ({
            logo: '',
            drawer: true,
            miniVariant: false,
            color: '',
            responsive: false,
            links: [
                {
                    to: '/inicio',
                    icon: 'mdi-home',
                    text: 'Início'
                },
                {
                    to: '/vendas',
                    icon: 'mdi-account-cash',
                    text: 'Vendas'
                },
                {
                    to: '/perfil',
                    icon: 'mdi-account',
                    text: 'Perfil'
                },
                {
                    to: '/usuarios',
                    icon: 'mdi-worker',
                    text: 'Gerenciar Usuários'
                },
                {
                    to: '/produtos',
                    icon: 'mdi-food-fork-drink',
                    text: 'Gerenciar Produtos'
                },
                {
                    to: '/relatorios',
                    icon: 'mdi-clipboard-outline',
                    text: 'Relatórios'
                }
            ]
        }),
        computed: {
            //...mapState(['usuarioLogado']),
        },
        /*
        mounted() {
            this.buscarUsuarioLogado()
            this.onResponsiveInverted()
            window.addEventListener('resize', this.onResponsiveInverted)
        },
        beforeDestroy() {
            window.removeEventListener('resize', this.onResponsiveInverted)
        },
        methods: {
            ...mapMutations('app', ['setDrawer', 'toggleDrawer']),
            ...mapMutations([mutationTypes.SET_NOTIFICACAO]),
            abrirNotificacaoSucesso() {
                this.notificacao = {
                    cor: 'secondary',
                    mensagem: 'Operação realizada com sucesso !',
                    mostrar: true
                }
                this.setNotificacao(this.notificacao)
            },
            abrirNotificacaoErro() {
                this.notificacao = {
                    cor: 'error',
                    mensagem: 'Ops... algo deu errado, contate seu administrador',
                    mostrar: true
                }
                this.setNotificacao(this.notificacao)
            },
            onResponsiveInverted() {
                if (window.innerWidth < 1150) {
                    this.responsive = true
                } else {
                    this.responsive = false
                }
            },
            async efetuarLogout() {
                try {
                    await this.$store.dispatch(actionTypes.EFETUAR_LOGOUT)
                    this.buscarUsuarioLogado()
                    await this.$router.push({path: '/'})
                    this.abrirNotificacaoSucesso()
                } catch (e) {
                    this.abrirNotificacaoErro()
                }
            },
            async buscarUsuarioLogado() {
                await this.$store.dispatch(actionTypes.BUSCAR_USUARIO_LOGADO)
                this.tenhoPermissao()
            },
            tenhoPermissao() {
                if (!this.usuarioLogado.id) {
                    this.$router.push({path: '/'})
                } else {
                    this.$router.push({path: '/inicio'})
                }
                if (!this.usuarioLogado.admin) {
                    this.links.forEach(link => {
                        if (
                            link.text === 'Relatórios' ||
                            link.text === 'Gerenciar Usuários'
                        ) {
                            this.links.splice(this.links.indexOf(link), 1)
                        }
                    })
                }
            }
        } */
    }
</script>
<style lang='stylus' scoped>
    #app-drawer
        background-color red
    /*
     #app-drawer
         .v-list__tile
             border-radius 4px

             &--btn
                 margin-top auto
                 margin-bottom 17px

         .v-image__image--contain
             top 9px
             height 60%

         div.v-responsive.v-image > div.v-responsive__content
             overflow-y auto

         .img
             background url(@/images/bg-02.jpg)
             background-size cover
             -webkit-background-size cover
             -moz-background-size cover
             -o-background-size cover
             -ms-background-size cover
             position relative
             min-height 100vh
             overflow hidden
     */
</style>
