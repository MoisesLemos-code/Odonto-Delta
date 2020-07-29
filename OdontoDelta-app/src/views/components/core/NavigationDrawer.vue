<template>
    <div>
        <v-navigation-drawer
                :mini-variant='drawer'
                :right='true'
                dark
                v-model='drawerVisible'
        >
            <v-list dense class='py-0'>
                <v-list-item two-line class="avatar-container">
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
                        active-class='tertiary'
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
            <template v-slot:append>
                <v-btn @click.stop="gerenciarGavetaLateral" block>
                    <v-icon v-if="drawer">mdi-arrow-right-bold-box-outline</v-icon>
                    <div v-else>
                        <v-icon>mdi-arrow-left-bold-box-outline</v-icon>
                        <label>Fechar modal</label>
                    </div>
                </v-btn>
            </template>
        </v-navigation-drawer>
    </div>
</template>

<script>
    import {mutationTypes} from '@/core/constants'

    export default {
        name: 'core-navigation-drawer',
        data: () => ({
            logo: '',
            color: '',
            responsive: false,
            links: [
                {
                    to: '/inicio',
                    icon: 'mdi-home',
                    text: 'Início'
                },
                {
                    to: '/orcamento',
                    icon: 'mdi-account-cash',
                    text: 'Orçamento'
                },
                {
                    to: '/perfil',
                    icon: 'mdi-account',
                    text: 'Cadastros'
                },
                {
                    to: '/usuarios',
                    icon: 'mdi-account-settings',
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
        computed:{
            drawer: function () {
                return this.$store.state.menuLateral
            },
            drawerVisible: function () {
                return this.$store.state.menuLateralExibicao
            }
        },
        mounted() {
            this.onResponsiveInverted()
            window.addEventListener('resize', this.onResponsiveInverted)
        },
        beforeDestroy() {
            window.removeEventListener('resize', this.onResponsiveInverted)
        },
        methods: {
            gerenciarGavetaLateral() {
                if (this.$store.state.menuLateral) {
                    this.$store.commit(mutationTypes.COMUM.SET_EXPANDIR_MENU)
                } else {
                    this.$store.commit(mutationTypes.COMUM.SET_RETRAIR_MENU)
                }
            },
            onResponsiveInverted() {
                if (window.innerWidth < 1150) {
                    this.responsive = true
                } else {
                    this.responsive = false
                }
            }
        }
    }
</script>
<style lang='stylus' scoped>
    .avatar-container
        padding-left 7px
</style>
