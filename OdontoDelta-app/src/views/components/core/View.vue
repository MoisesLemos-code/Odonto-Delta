<template>
        <div class="core-view">
            <v-fade-transition mode="out-in">
                <router-view />
            </v-fade-transition>
        </div>
</template>
<script>
    export default {
        name: 'core-view',
    }
</script>
<style lang="stylus" scoped>
    .core-view
        height 100%
        -webkit-box-shadow: 0 2px 4px -1px rgba(0, 0, 0, 0.2) !important
        box-shadow: 0 2px 4px -1px rgba(0, 0, 0, 0.2) !important
        background-color: #fff !important
</style>