<template>
    <v-main class="main-container">
        <div class="core-view">
            <v-fade-transition mode="out-in">
                <router-view />
            </v-fade-transition>
        </div>
    </v-main>
</template>
<script>
    export default {
        name: 'core-view',
    }
</script>
<style lang="stylus" scoped>
    .main-container
        padding 20px !important
        background-color #F5F5F5

    .core-view
        height 100%
        -webkit-box-shadow: 0 2px 4px -1px rgba(0, 0, 0, 0.2) !important
        box-shadow: 0 2px 4px -1px rgba(0, 0, 0, 0.2) !important
        background-color: #fff !important
</style>