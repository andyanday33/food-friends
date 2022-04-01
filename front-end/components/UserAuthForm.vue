<template>
  <div class="flex">
    <form @submit.prevent="userLogin">
      <div class="p-2">
        <label>Username</label>
        <input class="shadow bg-gray-50 ml-3" type="text" v-model="login.username" />
      </div>
      <div class="p-2">
        <label>Password</label>
        <input class="shadow bg-gray-50 ml-3" type="password" v-model="login.password" />
      </div>
      <div class="py-2">
        <button type="submit" class="text-gray-50 bg-green-900 border-green-900 p-2 hover:bg-gray-100 hover:text-green-900 rounded">Submit</button>
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
        let response = await this.$auth.loginWith('local', { data: this.login })
        console.log(response)
      } catch (err) {
        console.log(err)
      }
    }
  }
}
</script>