<template>
    <v-card flat tile>
    <v-toolbar dense class="core-toolbar">
        <div class="v-toolbar-title">
            <v-btn @click.stop="onClickBtn" icon v-if="responsive">
                <v-icon>mdi-menu</v-icon>
            </v-btn>
            <v-toolbar-title class="toolbar-title-text">{{ title }}</v-toolbar-title>
        </div>
        <v-spacer/>
        <v-toolbar-items class="toolbar-items">
                <router-link class="toolbar-items" to="/inicio" v-ripple>
                    <v-icon color="tertiary">mdi-home</v-icon>
                </router-link>
            <v-menu bottom content-class="dropdown-menu" left offset-y transition="slide-y-transition">
                <template v-slot:activator="{ on }">
                <router-link class="toolbar-items" v-on="on" to v-ripple>
                    <v-badge color="error" overlap>
                        <template slot="badge">{{ notifications.length}}</template>
                        <v-icon color="tertiary">mdi-bell</v-icon>
                    </v-badge>
                </router-link>
                <v-card>
                    <v-list dense>
                        <v-list-tile :key="notification" v-for="notification in notifications">
                            <v-list-tile-title v-text="notification" />
                        </v-list-tile>
                    </v-list>
                </v-card>
                </template>
            </v-menu>
                <router-link class="toolbar-items" to="/perfil" v-ripple>
                    <v-icon color="tertiary">mdi-account</v-icon>
                </router-link>
        </v-toolbar-items>
    </v-toolbar>
    </v-card>
</template>

<script>
    import {mapMutations, mapState} from 'vuex'

    export default {
        name: 'core-toolbar',
        data: () => ({
            notifications: [],
            title: null,
            responsive: false,
            responsiveInput: false
        }),
        watch: {
            $route(val) {
                this.title = val.name
            }
        },
        mounted() {
            this.onResponsiveInverted();
            window.addEventListener('resize', this.onResponsiveInverted)
        },
        beforeDestroy() {
            window.removeEventListener('resize', this.onResponsiveInverted)
        },
        methods: {
            onResponsiveInverted() {
                if (window.innerWidth < 1150) {
                    this.responsive = true
                    this.responsiveInput = false
                } else {
                    this.responsive = false
                    this.responsiveInput = true
                }
            },
            onClickBtn(){
                alert('modal')
            }
        }
    }
</script>

<style lang="stylus" scoped>
    .core-toolbar a
        text-decoration none

    .toolbar-title-text
        color '#7777'

    .v-toolbar-title, .toolbar-items
       display flex
       align-items center
</style>