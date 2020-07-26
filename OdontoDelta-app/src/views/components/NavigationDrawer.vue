<template>
  <v-container>
    <v-navigation-drawer app clipped dark permanent expand-on-hover>
      <v-list dense>
        <v-list-item link @click="exibirCadastroProduto">
          <v-list-item-action>
            <v-icon>restaurant</v-icon>
          </v-list-item-action>
          <v-list-item-content style="text-align: start">
            <v-list-item-title>Cadastro</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link @click="exibirVisualizacaoProdutos">
          <v-list-item-action>
            <v-icon>local_dining</v-icon>
          </v-list-item-action>
          <v-list-item-content style="text-align: start">
            <v-list-item-title>Visualizar Produtos</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link @click="exibirTodosProdutos">
          <v-list-item-action>
            <v-icon>view_list</v-icon>
          </v-list-item-action>
          <v-list-item-content style="text-align: start">
            <v-list-item-title>Cadastros de Produtos</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </v-container>
</template>

<script>
export default {
  name: "NavigationDrawer",
  methods: {
    exibirCadastroProduto() {
      this.$emit("cadastro");
    },
    exibirTodosProdutos() {
      this.$emit("listagem");
    },
    exibirVisualizacaoProdutos() {
      this.$emit("visualizacao");
    },
  },
};
</script>