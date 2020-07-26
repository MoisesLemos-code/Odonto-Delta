<template>
    <div class="align-right">
        <v-btn
                id="botaoSalvarAvancar"
                color="primary"
                depressed
                @click="$emit('salvarEAvancar')">
            Continuar
            <v-icon size="15" class="ml-1">keyboard_arrow_right</v-icon>
        </v-btn>
    </div>
</template>

<script>
    export default {
        name: 'BotaoSalvarAvancar'
    }
</script>

<style scoped lang="stylus">
    .action-primary
        padding 10px 15px
        border 1px solid #7aa329
        background-color #7aa329 !important
        color #fff !important
        margin-left 10px
        border-radius 2px

        i
            margin-right 0
</style>