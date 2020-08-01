<template>
  <v-toolbar-title class="to-title">
    <span class="to-title__title primary--text">{{ title }}</span>
    <span class="to-title__subtitle" v-if="subtitle">» {{ subtitle }}</span>
  </v-toolbar-title>
</template>

<script>
export default {
  name: 'core-title',
  computed: {
    title() {
      return this.$store.state.page.title
    },
    subtitle() {
      return this.$store.state.page.subtitle
    }
  }
}
</script>

<style lang="stylus">
.to-title
  margin-left 0 !important
  &__title
    font-size 16px
    font-weight bold
    padding-right 5px
  &__subtitle
    color #7f7f7f
    font-size 14px
    font-weight normal
</style>