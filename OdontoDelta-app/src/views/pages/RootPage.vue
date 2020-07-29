<template>
    <v-app>
        <div class="menu">
            <core-navigation-drawer/>
            <div class="menu-content">
                <core-toolbar/>
                <core-view/>
                <core-footer/>
            </div>
        </div>
    </v-app>
</template>

<script>
    export default {
        name: 'RootPage'
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
</style>
