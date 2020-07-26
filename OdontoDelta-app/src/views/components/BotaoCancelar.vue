<template>
    <v-btn
            id="botaoCancelar"
            outlined
            depressed
            class="botao-cancelar"
            @click="$emit('cancelar')">
        Cancelar
    </v-btn>
</template>

<script>
    export default {
        name: 'BotaoCancelar'
    }
</script>

<style lang="stylus" scoped>
    .botao-cancelar
        color #999 !important
</style>
