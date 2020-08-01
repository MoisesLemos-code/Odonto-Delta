<template>
        <v-toolbar-items class="toolbar-items">
            <v-menu bottom content-class="dropdown-menu" left offset-y transition="slide-y-transition">
                <template v-slot:activator="{ on }">
                <router-link class="toolbar-icon" v-on="on" to v-ripple>
                    <v-badge color="error" overlap>
                        <template slot="badge">{{ notifications.length}}</template>
                        <v-icon color="tertiary">mdi-bell</v-icon>
                    </v-badge>
                </router-link>
                <v-card>
                    <v-list dense>
                        <v-list-tile :key="notification" v-for="notification in notifications">
                            <v-list-tile-title v-text="notification" />
                        </v-list-tile>
                    </v-list>
                </v-card>
                </template>
            </v-menu>
        </v-toolbar-items>
</template>

<script>
    export default {
        name: 'core-toolbar',
        data: () => ({
            notifications: [],
        })
    }
</script>

<style lang="stylus" scoped>
    .toolbar-items
       display flex
       align-items center

    .toolbar-icon
      margin-left 10px
      text-decoration none
</style>