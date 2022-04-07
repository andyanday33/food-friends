<template>
  <div class="bg-gray-800 text-gray-50 h-screen justify-center">
    <h1 class="text-5xl text-center">Login to Food Friends</h1>
    <form class="grid grid-cols-3 p-4 grow justify-start border rounded-2xl mx-6 mt-4" @submit.prevent="userLogin">
      <div class="col-span-3 justify-self-center grid grid-cols-2 justify-items-center">
        <label class="py-5 px-4">Email:</label>
        <input class="text-black mt-4 border-2 border-gray-300 bg-gray-50 h-10 pl-2 pr-8 rounded-lg focus:outline-none" 
        type="email" v-model="login.username" />
      </div>
      <div class="col-span-3 justify-self-center grid grid-cols-2 justify-items-center">
        <label class="py-2 px-4">Password:</label>
        <input class="text-black border-2 border-gray-300 bg-gray-50 h-10 pl-2 pr-8 rounded-lg focus:outline-none" 
        type="password" v-model="login.password" />
      </div>
      <div v-if="login.incorrect" class="col-span-3 justify-self-center grid grid-cols-2 shadow">
        <p class="col-span-2 rounded bg-red-900 py-2 px-8 mt-4">Incorrect username/password</p>
      </div>
      <div class="col-span-3 justify-self-center grid grid-cols-2 shadow">
        <button class="col-span-2 rounded bg-green-900 py-2 px-8 mt-4 hover:bg-gray-900" type="submit">Submit</button>
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
        password: '',
        incorrect: false,
      }
    }
  },
  methods: {
    async userLogin() {
      try {
        this.login.incorrect = false;
        // let response = await this.$auth.loginWith('local', { data: this.login });
        const res = await this.$axios.$post(`${ process.env.baseUrl }:${ process.env.apiPort }/user/login?email=${this.login.username}&password=${this.login.password}`);
        console.log(res);
        //Admin checking
        const admins = await this.$axios.$get(`${ process.env.baseUrl }:${ process.env.apiPort }/admin/getAllAdmins`);
        const adminAcc = admins.data.filter(data => data.email == res.email);
        if(adminAcc.length > 0){
            console.log("wow");
            await this.$store.dispatch("setIsAdmin", true);
        }
        console.log(this.$store.state.isAdmin);
        console.log(admins);
        console.log("-------------------");

        this.$auth.setUser(res.data);
        this.$auth.setUserToken(res.data.userId); //This is not encrypted, yet to implement jwt
      
        console.log("----------");
        console.log(this.$auth.user);
        console.log(this.$auth.loggedIn);
      } catch (err) {
        console.log(err);
        this.login.incorrect = true;
        console.log(this.login.incorrect);
      }
    }
  }
}
</script>