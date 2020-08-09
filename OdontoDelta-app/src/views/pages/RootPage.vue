<template>
  <v-app v-if="this.$route.name === 'Login'">
    <loading />
    <router-view />
  </v-app>
  <core-template-default v-else/>
</template>

<script>
    import Loading from '@/views/components/Loading'
    export default {
        name: 'RootPage',
        components: {Loading}
    }
</script>

<style lang="stylus">
    .menu
        width 100%
        height 100%
        display flex
        flex-direction row

    .menu-content
        width 100%
        height 100%
        display flex
        flex-direction column

    html, body, .application
        font-family 'Open Sans', sans-serif

    .erro-inicializacao
      width 100%
      padding 0
      text-align center
      color #555
      height 80vh
      display flex
      justify-content center
      align-items center

      i
        font-size 36px

      p
        font-size 18px
        margin 10px 0 0
        font-weight 600

    @keyframes fade
      from
        opacity 0
        transform scale(0.5)
      to
        opacity 1
        transform scale(1)

    @keyframes moveX
      from
        opacity 0
        transform translateX(-35%)
      to
        opacity 1
        transform translateX(0%)

    @keyframes moveXRight
      from
        opacity 0
        transform translateX(95%)
      to
        opacity 1
        transform translateX(0%)

    @keyframes moveY
      from
        opacity 0
        transform translateY(-35%)
      to
        opacity 1
        transform translateY(0%)

    @keyframes moveYBack
      from
        opacity 0
        transform translateY(95%)
      to
        opacity 1
        transform translateY(0%)
</style>
