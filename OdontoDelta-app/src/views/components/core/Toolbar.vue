<template>
        <v-toolbar-items class="toolbar-items">
            <v-menu bottom content-class="dropdown-menu" left offset-y transition="slide-y-transition">
                <template v-slot:activator="{ on }">
                <router-link class="toolbar-icon" v-on="on" to v-ripple>
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
          <v-btn text class="btn-sair desktop" @click="logout()">Sair</v-btn>
        </v-toolbar-items>
</template>

<script>
    import {mutationTypes} from "@/core/constants";

    export default {
        name: 'core-toolbar',
        data: () => ({
            notifications: [],
        }),
        methods:{
          logout() {
            this.$store.commit(mutationTypes.COMUM.EFETUAR_LOGOUT)
            this.$router.push({ name: 'Login' })
          }
        }
    }
</script>

<style lang="stylus" scoped>
.toolbar-items
  display flex
  align-items center

.toolbar-icon
  text-decoration none

.btn-sair
  margin-left 20px
  color #bbb
  letter-spacing 1px
  font-size 16px !important
  font-weight bold
  word-spacing 1px
  text-transform capitalize
@media (max-width 720px)
  .desktop
     display none
</style>