<template>
    <v-footer absolute dark id="core-footer">
        <div class="footer-items" style="margin-left: 0px">
       <span>
         <router-link to="ajuda" class="font-weight-light copyright" style="color: #CACACA">Precisa de ajuda?</router-link>
      </span>
        </div>
        <v-spacer/>
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
        margin-top 3%
        z-index 0
        position fixed
        bottom 0
        border none
</style>