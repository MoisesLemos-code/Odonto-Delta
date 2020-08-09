<template>
  <div>
    <section class="background-container">
      <notificacao/>
      <v-container fluid>
        <v-row class="v-row" align="center" justify="center">
          <v-col cols="12" sm="8" md="4">
            <v-img :src="require('@/images/logo-01.png')" aspect-ratio="2.5" id="logo"/>
            <v-card class="elevation-12">
              <v-form @submit.prevent="efetuarLogin">
              <v-card-text>
                <v-text-field
                    v-model="usuario.nome"
                    name="usuario"
                    label="Usuário"
                    prepend-inner-icon="mdi-account"
                    outlined
                    dense
                    :error-messages="errors.collect('usuario')"
                    v-validate="{ required: true}"
                ></v-text-field>
                <v-text-field
                    v-model="usuario.senha"
                    name="senha"
                    prepend-inner-icon="mdi-lock"
                    :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                    :type="showPassword ? 'text' : 'password'"
                    label="Senha"
                    outlined
                    dense
                    @click:append="showPassword = !showPassword"
                    :error-messages="errors.collect('senha')"
                    v-validate="{ required: true}"
                ></v-text-field>
                <v-btn id="btn-login" block outlined type="submit" :loading="loadingBtn">Entrar</v-btn>
              </v-card-text>
              </v-form>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </section>
  </div>
</template>

<script>
    import notificacao from '@/views/components/Notificacao'
    import {mapMutations} from 'vuex'
    import {actionTypes, mutationTypes} from '@/core/constants'

    export default {
        name: 'Login',
        components: {notificacao},
        $_veeValidate: {
            validator: 'new'
        },
        data() {
            return {
                loadingBtn: false,
                showPassword: false,
                usuario: {
                    nome: '',
                    senha: ''
                },
                usuarioAutenticado: {
                    nome: '',
                    token: ''
                }
            }
        },
        methods: {
            ...mapMutations([mutationTypes.COMUM.SET_USUARIO_LOGADO]),
            async efetuarLogin() {
                if (await this.validarDadosFormulario()) {
                    try {
                        this.loadingBtn = true
                        const { data } = await this.$store.dispatch(
                            actionTypes.COMUM.EFETUAR_LOGIN,
                            this.usuario
                        )
                        if (data) {
                            this.usuarioAutenticado = {
                                codigo: data.userCodigo,
                                nome: data.userNome,
                                nomeCompleto: data.userNomeCompleto,
                                token: data.token,
                                admin: data.isAdmin
                            }
                            this.setUsuarioLogado(this.usuarioAutenticado)
                            await this.$router.push({name: 'Inicio'})
                        }
                    } catch (err) {
                        this.loadingBtn = false
                        this.mostrarNotificacaoErro(err.response.data.message)
                    }
                }
            },
            async validarDadosFormulario() {
                return this.$validator._base.validateAll()
            }
        }
    }
</script>

<style lang="stylus" scoped>
.background-container
  background: url(../../../images/bg-01.jpg) no-repeat center
  background-size cover
  -webkit-background-size cover
  -moz-background-size cover
  -o-background-size cover
  -ms-background-size cover
  position relative
  min-height 100vh

#logo
  animation fade 500ms

.v-card
  margin-top 30px
  background-color #ffffff
  border-style solid
  border-width 1px
  border-color #d8dde6
  border-radius 5px
  padding 30px
  animation fade 400ms
  overflow hidden

#btn-login
  background #fc6666
  color #fff
  box-shadow inset 0px 20px 4px rgba(252, 59, 59, 0.25)
  border-radius 5px
  animation moveX 500ms
  animation-delay 390ms
  animation-fill-mode backwards

.v-text-field:nth-child(1)
  animation moveX 500ms
  animation-delay 200ms
  animation-fill-mode backwards

.v-text-field:nth-child(2)
  animation moveX 500ms
  animation-delay 300ms
  animation-fill-mode backwards
</style>