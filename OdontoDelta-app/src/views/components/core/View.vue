<template>
    <v-main>
        <div class="core-view">
            <v-fade-transition mode="out-in">
                <router-view />
            </v-fade-transition>
        </div>
        <core-footer />
    </v-main>
</template>
<script>
    export default {
        name: 'core-view',
    }
</script>
<style lang="stylus" scoped>
    .core-view
        padding-bottom 100px
        background-color #F5F5F5
</style>