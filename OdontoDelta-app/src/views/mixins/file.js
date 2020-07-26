export default {
    methods: {
        criarLinkDownload(uri, thumbnail) {
            return `${this.$store.state.loki.file.api}?uri=${uri}&thumbnail=${thumbnail === true}`
        },
        criarLinkDownloadTemporario(uri, thumbnail) {
            return `${this.$store.state.loki.file.api}/temporario?uri=${uri}&thumbnail=${thumbnail === true}`
        },
        tratarErroEnvioArquivo() {
            this.mostrarNotificacaoErro(`O arquivo selecionado deve ter no máximo ${this.$store.state.loki.file.maxSize}.`)
        }
    }
}
