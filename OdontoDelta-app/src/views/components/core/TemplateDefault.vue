<template>
  <v-app class="template-default">
     <loading />
      <core-navigation-drawer>
        <div class="menu">
          <core-menu />
        </div>
      </core-navigation-drawer>
    <v-app-bar app height="60" class="toolbar">
      <v-icon class="mobile_menu" @click="showAside()">mdi-menu</v-icon>
      <core-title/>
      <v-spacer/>
      <core-toolbar/>
    </v-app-bar>
    <v-main>
      <v-container fluid class="container">
        <core-view/>
      </v-container>
      <core-footer/>
    </v-main>
  </v-app>
</template>

<script>
    import {mutationTypes} from '@/core/constants'
    import Loading from '@/views/components/Loading'

    export default {
        name: 'core-template-default',
        components: {Loading},
        methods: {
            showAside() {
                this.$store.commit(mutationTypes.DRAWER.SET_ASIDE_HIDE, true)
            }
        }
    }
</script>


<style lang="stylus">
.template-default
  .aside-drawer
    .logo
      border-bottom 1px solid rgba(255, 255, 255, .1)
      height 120px
  .container
    background-color #eee
    height 100%
    overflow hidden
    padding 20px
  .toolbar
    box-shadow 0 2px 4px -1px rgba(0, 0, 0, .2)
    background-color white !important
  .mobile_menu
    display none
body
  background-color #eee
.application.theme--light
  background unset !important
.application--wrap
  min-height unset !important
html
  overflow-y auto
@media (max-width 720px)
  .template-default
    .mobile_menu
      margin-right 10px
      display unset !important
      top 1px
      position relative
      color var(--v-primary-base) !important
    .title__subtitle
      display none
    .no-mobile
      display none !important
</style>