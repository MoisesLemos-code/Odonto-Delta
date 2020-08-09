<template>
  <div class="loading">
    <v-overlay :value="isLoading">
      <v-card color="white" dark width="300">
        <v-card-text class="black--text">
          {{ loadingMessage }}
          <v-progress-linear indeterminate color="primary" class="mb-0"></v-progress-linear>
        </v-card-text>
      </v-card>
    </v-overlay>
  </div>
</template>

<script>
    export default {
        name: 'Loading',
        computed: {
            isLoading() {
                return this.$store.state.comum.isLoading
            },
            loadingMessage() {
                return this.$store.state.comum.loadingMessage
            }
        }
    }
</script>

<style lang="stylus">
.loading
  z-index 100
</style>