<template>
  <div>
    <form @submit.prevent="userLogin">
      <div>
        <label>Username</label>
        <input type="text" v-model="login.username" />
      </div>
      <div>
        <label>Password</label>
        <input type="password" v-model="login.password" />
      </div>
      <div>
        <button type="submit">Submit</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      login: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    async userLogin() {
      try {
        // let response = await this.$auth.loginWith('local', { data: this.login });
        const res = await this.$axios.$post(`${ process.env.baseUrl }:${ process.env.apiPort }/user/login?email=${this.login.username}&password=${this.login.password}`);
        console.log(res);
        this.$auth.setUser(res.data);
        this.$auth.setUserToken(res.data.userId); //This is not encrypted, yet to implement jwt
        console.log("----------");
        console.log(this.$auth.user);
        console.log(this.$auth.loggedIn);
      } catch (err) {
        console.log(err)
      }
    }
  }
}
</script>