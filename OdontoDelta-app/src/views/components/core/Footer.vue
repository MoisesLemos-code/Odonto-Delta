<template>
    <v-footer dark class="core-footer">
        <v-spacer/>
        <div class="footer-items">
       <span>
         <router-link to="ajuda" class="font-weight-light copyright" style="color: #CACACA">Precisa de ajuda?</router-link>
      </span>
        </div>
        <span class="font-weight-light copyright">
            &copy;{{ (new Date()).getFullYear() }}
      <v-icon
              color="tertiary"
              size="20">mdi-pizza</v-icon>
    </span>
    </v-footer>
</template>

<script>
    export default {
        name: 'core-footer'
    }
</script>

<style lang="stylus" scoped>
    .core-footer
        z-index 0
        border none

    .footer-items
        margin-right 20px
</style>