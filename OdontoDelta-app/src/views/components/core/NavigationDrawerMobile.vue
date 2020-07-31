<template>
        <v-navigation-drawer
                v-model="drawerControl"
                dark
                absolute
                temporary>
            <v-list dense class='py-0'>
                <v-list-item two-line class="avatar-container">
                    <v-list-item-avatar>
                        <v-icon size='50'>mdi-account-circle</v-icon>
                    </v-list-item-avatar>

                    <v-list-item-content>
                        <v-list-item-title>Application Mobile</v-list-item-title>
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
        </v-navigation-drawer>
</template>

<script>
    import {mutationTypes} from '@/core/constants'

    export default {
        name: 'core-navigation-drawer-mobile',
        data: () => ({
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
            drawerControl: {
                get: function () {
                    return this.$store.state.menuLateral
                },
                set: function (data){
                    if(!data){
                        this.$store.commit(mutationTypes.COMUM.SET_RETRAIR_MENU_MOBILE)
                    }
                }
            }
        }
    }
</script>
<style lang='stylus' scoped>
    .avatar-container
        padding-left 7px
</style>
