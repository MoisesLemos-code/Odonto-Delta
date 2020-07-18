<template>
  <div>
    <section class="main">
      <notificacao />
      <v-container fluid>
        <v-row class="v-row" align="end" justify="end">
          <v-btn id="btn-cadastrar" outlined to="/cadastrar">Cadastre-se</v-btn>
        </v-row>
        <v-row class="v-row" align="center" justify="center">
          <v-col cols="12" sm="8" md="4">
            <v-img src="../../images/logo-01.png" aspect-ratio="2.5" id="logo"></v-img>
            <v-card class="elevation-12">
              <v-card-text>
                <v-form @submit.prevent="entrar">
                  <v-text-field
                    label="Usuário"
                    name="login"
                    v-model="usuario.nome"
                    prepend-inner-icon="mdi-account"
                    type="text"
                    outlined
                    dense
                    required
                    color="#53999D"
                  ></v-text-field>
                  <v-text-field
                    v-model="usuario.senha"
                    prepend-inner-icon="mdi-lock"
                    :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                    :type="showPassword ? 'text' : 'password'"
                    name="password"
                    label="Senha"
                    color="#53999D"
                    outlined
                    dense
                    required
                    @click:append="showPassword = !showPassword"
                  ></v-text-field>
                  <v-btn id="btn-login" block outlined type="submit" :loading="loadingBtn">Entrar</v-btn>
                </v-form>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </section>
  </div>
</template>

<script>
import notificacao from "../Notifications";
import { mapMutations, mapState } from "vuex";
import { actionTypes, mutationTypes } from "../../commons/constants";

export default {
  name: "Entrar",
  components: { notificacao },
  data() {
    return {
      loadingBtn: false,
      showPassword: false,
      notificacao: {
        cor: "error",
        mensagem: "Usuário ou senha inválidos !",
        mostrar: true
      },
      usuario: {
        nome: "",
        senha: ""
      },
      usuarioAutenticado: {}
    };
  },
  computed: {
    // ...mapState(["usuarioLogado"])
  },
  methods: {
    // ...mapMutations([mutationTypes.SET_USUARIO_LOGADO]),
    ...mapMutations([mutationTypes.SET_NOTIFICACAO]),
    abrirNotificacaoSucesso() {
      this.notificacao = {
        cor: "secondary",
        mensagem: "Operação realizada com sucesso !",
        mostrar: true
      };
      this.setNotificacao(this.notificacao);
    },
    abrirNotificacaoErro() {
      this.notificacao = {
        cor: "error",
        mensagem: "Usuário ou senha inválidos!",
        mostrar: true
      };
      this.setNotificacao(this.notificacao);
    },
    async entrar() {
      try {
        this.loadingBtn = true;
        this.usuarioAutenticado = await this.$store.dispatch(
          actionTypes.EFETUAR_LOGIN,
          this.usuario
        );
        if (this.usuarioAutenticado) {
          this.setUsuarioLogado(this.usuarioAutenticado);
          await this.$router.push({ path: "/inicio" });
          this.abrirNotificacaoSucesso();
        } else {
          return this.abrirNotificacaoErro();
        }
      } catch (e) {
        this.loadingBtn = false;
        this.abrirNotificacaoErro();
      }
    }
  }
};
</script>

<style scoped>
#logo {
  animation: fade 500ms;
}
.v-card {
  margin-top: 30px;
  background-color: #ffffff;
  border-style: solid;
  border-width: 1px;
  border-color: #d8dde6;
  border-radius: 5px;
  padding: 30px;
  animation: fade 400ms;
  overflow: hidden;
}
#btn-cadastrar {
  margin-right: 10px;
  background: #fc6666;
  color: #fff;
  box-shadow: inset 0px 20px 4px rgba(252, 59, 59, 0.25);
  border-radius: 5px;
  border-style: none;
}
#btn-login {
  background: #fc6666;
  color: #fff;
  box-shadow: inset 0px 20px 4px rgba(252, 59, 59, 0.25);
  border-radius: 5px;
  animation: moveX 500ms;
  animation-delay: 390ms;
  animation-fill-mode: backwards;
}
.v-text-field:nth-child(1) {
  animation: moveX 500ms;
  animation-delay: 200ms;
  animation-fill-mode: backwards;
}
.v-text-field:nth-child(2) {
  animation: moveX 500ms;
  animation-delay: 300ms;
  animation-fill-mode: backwards;
}
@import "css/style.css";
@import "css/font-awesome.min.css";
</style>
