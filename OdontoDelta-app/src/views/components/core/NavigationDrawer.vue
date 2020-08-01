<template>
  <v-navigation-drawer
      v-model="drawer"
      :mini-variant.sync="asideClosed"
      mobile-breakpoint="720"
      mini-variant-width="60"
      class="aside-drawer"
      app
      dark
      floating>
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
      <div :class="{ 'arrow-opened': !asideClosed, 'arrow-closed': asideClosed }" class="no-mobile" @click="toogle()">
        <v-btn icon text>
          <v-icon color="rgba(255, 255, 255, .3)">
            {{ asideClosed ? 'mdi-arrow-right-bold-box-outline' : 'mdi-arrow-left-bold-box-outline' }}
          </v-icon>
        </v-btn>
        <a v-if="!asideClosed" class="text-hide-menu">Recolher menu</a>
      </div>
  </v-navigation-drawer>
</template>

<script>
import {mutationTypes} from '@/core/constants'

export default {
  name: 'core-navigation-drawer',
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
  computed: {
    asideClosed: {
      get() {
        return this.$store.state.asideClosed
      },
      set() {}
    },
    drawer: {
      get() {
        return this.$store.state.asideHide
      },
      set(hide) {
        this.$store.commit(mutationTypes.COMUM.SET_ASIDE_HIDE, hide)
      }
    }
  },
  methods: {
    toogle() {
      this.$store.commit(mutationTypes.COMUM.TOOGLE_ASIDE)
    },
    change(closed) {
      this.$store.commit(mutationTypes.COMUM.SET_ASIDE, closed)
    }
  },
}
</script>
<style lang='stylus' scoped>
.avatar-container
  padding-left 7px

.aside-drawer::-webkit-scrollbar
  width 6px

.aside-drawer::-webkit-scrollbar-thumb
  background rgba(255, 255, 255, .5)

.aside-drawer
  padding 0
  max-height unset !important
  .text-hide-menu
    color rgba(255, 255, 255, .3)
    font-size 13px
  &__menu
    height 100%
    padding-bottom 40px
    padding-top 120px
  .arrow-closed
    display flex
    align-items center
    .v-btn--icon
      border-radius unset

  .arrow-opened, .arrow-closed
    position absolute
    bottom 0
    width 100%
    border-top 1px solid rgba(255, 255, 255, .1)
    height 36px !important
    display -webkit-box
    display -ms-flexbox
    display flex
    -webkit-box-align center
    -ms-flex-align center
    align-items center
    justify-content center

  .v-btn
    margin 0 10px 0 0 !important
    color rgba(255, 255, 255, .8) !important

  .v-btn, .v-icon
    font-size 16px
    height 16px
    width 16px
</style>
