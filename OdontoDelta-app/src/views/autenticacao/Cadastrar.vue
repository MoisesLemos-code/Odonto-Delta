<template>
  <div>
    <section class="main">
      <notificacao />
      <v-container fluid>
        <v-row class="v-row" align="end" justify="end">
          <v-btn id="btn-login-cad" outlined to="/">Login</v-btn>
        </v-row>
        <v-row class="v-row" align="center" justify="center">
          <v-col cols="12" sm="8" md="4">
            <v-card class="elevation-12">
              <h2 id="h2-cadastro">Cadastre-se</h2>
              <v-card-text>
                <v-form ref="form" :lazy-validation="validacao.lazy" v-model="validacao.valid">
                  <v-text-field
                    label="Nome de usuário"
                    name="nome"
                    v-model="usuario.nome"
                    prepend-inner-icon="mdi-account"
                    type="text"
                    outlined
                    dense
                    :rules="validacao.campoRules"
                    required
                    color="#53999D"
                  ></v-text-field>
                  <v-text-field
                    label="Nome completo"
                    name="nomeCompleto"
                    v-model="usuario.nome_completo"
                    prepend-inner-icon="mdi-pencil"
                    type="text"
                    outlined
                    dense
                    :rules="validacao.campoRules"
                    required
                    color="#53999D"
                  ></v-text-field>
                  <v-text-field
                    label="Email"
                    name="email"
                    v-model="usuario.email"
                    prepend-inner-icon="mdi-email"
                    type="email"
                    outlined
                    dense
                    :rules="validacao.emailRules"
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
                    :rules="validacao.campoRules"
                    required
                    @click:append="showPassword = !showPassword"
                  ></v-text-field>
                  <v-btn
                    id="btn-cad"
                    block
                    outlined
                    @click="validate"
                    :loading="loadingBtn"
                  >Cadastrar</v-btn>
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
import notificacao from "@/components/Notificacao";
import { mapMutations } from "vuex";
import { actionTypes, mutationTypes } from "@/commons/constants";

export default {
  name: "Cadastrar",
  components: { notificacao },
  data() {
    return {
      loadingBtn: false,
      validacao: {
        valid: false,
        lazy: false,
        campoRules: [v => !!v || "Este campo é obrigatório!"],
        emailRules: [
          v => !!v || "O e-mail é obrigatório!",
          v => /.+@.+\..+/.test(v) || "E-mail inválido!"
        ]
      },
      showPassword: false,
      notificacao: {},
      usuario: {
        nome: "",
        nome_completo: "",
        senha: "",
        email: ""
      }
    };
  },
  methods: {
    ...mapMutations([mutationTypes.SET_NOTIFICACAO]),
    abrirNotificacaoSucesso() {
      this.notificacao = {
        cor: "success",
        mensagem: "Operação realizada com sucesso!",
        mostrar: true
      };
      this.setNotificacao(this.notificacao);
    },
    abrirNotificacaoErro(data) {
      this.notificacao = {
        cor: "error",
        titulo: data.msg,
        mensagem: data.errors[0].message,
        mostrar: true
      };
      this.setNotificacao(this.notificacao);
    },
    validate() {
      if (this.$refs.form.validate()) {
        this.inserirUsuario();
      }
    },
    async inserirUsuario() {
      try {
        this.loadingBtn = true;
        await this.$store.dispatch(actionTypes.INSERIR_USUARIO, this.usuario);

        await this.$router.push({ path: "/" });
        this.abrirNotificacaoSucesso();
      } catch (err) {
        this.loadingBtn = false;
        this.abrirNotificacaoErro(err.response.data);
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
#h2-cadastro {
  text-align: center;
}
#btn-login-cad {
  margin-right: 10px;
  background: #fc6666;
  color: #fff;
  box-shadow: inset 0px 20px 4px rgba(252, 59, 59, 0.25);
  border-radius: 5px;
  border-style: none;
}
#btn-cad {
  background: #fc6666;
  color: white;
  box-shadow: inset 0px 20px 4px rgba(252, 59, 59, 0.25);
  border-radius: 5px;
  animation: moveX 500ms;
  animation-delay: 590ms;
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
.v-text-field:nth-child(3) {
  animation: moveX 500ms;
  animation-delay: 400ms;
  animation-fill-mode: backwards;
}
.v-text-field:nth-child(4) {
  animation: moveX 500ms;
  animation-delay: 500ms;
  animation-fill-mode: backwards;
}
@import "css/style.css";
@import "css/font-awesome.min.css";
</style>
