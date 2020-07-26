<template>
    <v-btn
            id="botaoSalvar"
            color="primary"
            depressed
            @click="$emit('salvar')">
        Salvar
    </v-btn>
</template>

<script>
    export default {
        name: 'BotaoSalvar'
    }
</script>