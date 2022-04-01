<template>
  <div class="grid grid-rows-3 justify-items-stretch">
    <form @submit.prevent="userLogin">
      <div class="flex">
        <label class="flex-1">Username:</label>
        <input class="shadow bg-gray-50 ml-3" type="text" v-model="login.username" />
      </div>
      <div class="pt-6 flex">
        <label class="flex-1">Password:</label>
        <input class="shadow bg-gray-50 ml-3" type="password" v-model="login.password" />
      </div>
      <div class="flex pt-6">
        <button type="submit" class="text-gray-50 bg-green-900 border-green-900 p-2 hover:bg-gray-100 hover:text-green-900 rounded flex-1">Login</button>
      </div>
      <div class="flex">
        <p> Don't have an account? <NuxtLink to="/register" class="text-green-700">Register</NuxtLink> instead.</p>
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