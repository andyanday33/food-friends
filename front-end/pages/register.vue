<template>
  <div class="bg-gray-800 text-gray-50 h-screen justify-center">
    <Navbar />
    <h1 class="mt-5 text-5xl text-center">Register to Food Friends!</h1>
    <form class="grid grid-cols-3 p-4 grow justify-start border rounded-2xl mx-6 mt-4" @submit.prevent="userLogin">
      <div class="col-span-3 justify-self-center grid grid-cols-2 justify-items-center">
        <label class="py-5 px-4">Email:</label>
        <input class="text-black mt-4 border-2 border-gray-300 bg-gray-50 h-10 pl-2 pr-8 rounded-lg focus:outline-none" 
        type="email" v-model="register.email" />
      </div>
      <div class="col-span-3 justify-self-center grid grid-cols-2 justify-items-center">
        <label class="py-5 px-4">Username:</label>
        <input class="text-black mt-4 border-2 border-gray-300 bg-gray-50 h-10 pl-2 pr-8 rounded-lg focus:outline-none" 
        type="text" v-model="register.username" />
      </div>
      <div class="col-span-3 justify-self-center grid grid-cols-2 justify-items-center">
        <label class="py-2 px-4 mt-4">Password:</label>
        <input class="text-black border-2 mt-4 border-gray-300 bg-gray-50 h-10 pl-2 pr-8 rounded-lg focus:outline-none" 
        type="password" v-model="register.password" />
      </div>
      <div v-if="register.incorrect" class="col-span-3 justify-self-center grid grid-cols-2 shadow">
        <p class="col-span-2 rounded bg-red-900 py-2 px-8 mt-4">Account already exists</p>
      </div>
      <div class="col-span-3 justify-self-center grid grid-cols-2 shadow">
        <button class="col-span-2 rounded bg-green-900 py-2 px-8 mt-4 hover:bg-gray-900" type="submit">Register</button>
      </div>
      <div class="col-span-3 justify-self-center grid grid-cols-2">
        <p class="col-span-2">Have an account? <NuxtLink to="/login" class="col-span-2 rounded mt-4 text-green-500 hover:text-gray-50" type="submit">login</NuxtLink> instead</p>
      </div>
    </form>
  </div>
</template>

<script>
import Navbar from '../components/Navbar.vue';
export default {
  components: { Navbar },
  data() {
    return {
      register: {
        email: '',
        username: '',
        password: '',
        incorrect: false,
      }
    }
  },
  methods: {
    async userLogin() {
      try {
        this.register.incorrect = false;
        // let response = await this.$auth.loginWith('local', { data: this.login });
        const res = await this.$axios.$post(`${ process.env.baseUrl }:${ process.env.apiPort }/user/register?email=${this.register.email}&password=${this.register.password}&userName=${this.register.username}`);
        console.log(res);
        if(res.code == 200) {
            this.$router.push('/');
        } else {
            this.register.incorrect = true;
        }
      } catch (err) {
        console.log(err);
        this.register.incorrect = true;
      }
    }
  }
}
</script>