<template>
  
<div class="bg-gray-800 h-screen text-gray-50">
    <Navbar />
    <main class="flex">
        <Sidebar class = "flex-none" />
        <div class="flex-1 p-4 grow border rounded-2xl mx-6 mt-4 justify-start overflow-auto">
            <h1 class="text-5xl text-center">Cuisines</h1>
            <div class="grid grid-cols-3 gap-8 p-8 text-center max-h-128">
                <NuxtLink to="/Recipes/French" class="container shadow hover:shadow-xl border-2 rounded-3xl border-gray-100 flex h-64 hover:bg-gray-700">
                    <h2 class="m-auto text-3xl">French</h2>
                </NuxtLink>
                <NuxtLink to="/Recipes/Chinese" class="container shadow hover:shadow-xl border-2 rounded-3xl border-gray-100 flex h-64 hover:bg-gray-700">
                    <h2 class="m-auto text-3xl">Chinese</h2>
                </NuxtLink>
                <NuxtLink to="/Recipes/Greek" class="container shadow hover:shadow-xl border-2 rounded-3xl border-gray-100 flex h-64 hover:bg-gray-700">
                    <h2 class="m-auto text-3xl">Greek</h2>
                </NuxtLink>
                <NuxtLink to="/Recipes/Italian" class="container shadow hover:shadow-xl border-2 rounded-3xl border-gray-100 flex h-64 hover:bg-gray-700">
                    <h2 class="m-auto text-3xl">Italian</h2>
                </NuxtLink>
                <NuxtLink to="/Recipes/Spanish" class="container shadow hover:shadow-xl border-2 rounded-3xl border-gray-100 flex h-64 hover:bg-gray-700">
                    <h2 class="m-auto text-3xl">Spanish</h2>
                </NuxtLink>
                <NuxtLink to="/Recipes/Mediterranean" class="container shadow hover:shadow-xl border-2 rounded-3xl border-gray-100 flex h-64 hover:bg-gray-700">
                    <h2 class="m-auto text-3xl">Mediterranean</h2>
                </NuxtLink>
            </div>
        </div>
        
    </main>
</div>
</template>

<script>
import Navbar from '../components/Navbar.vue';
import Sidebar from '../components/Sidebar.vue';
const axios = require('axios').default;

export default {
    components: { Navbar, Sidebar },
    name: "CuisinesPage",
    middleware: 'auth',
    
    beforeMount() {
        //Get all the ingredients from the backend and store them in Vuex store
        axios.get("http://localhost:8080/getAllCuisines")
            .then((res) => {
                console.log(res);
                this.$store.dispatch("setCuisines", res.data.data);
                console.log(this.$store.state.cuisineData);
            })
            .catch((err) => {
                console.error(err);
            });

        var options = {
            method: 'POST',
            url: 'https://dev-tys5d4fu.us.auth0.com/oauth/token',
            headers: {'content-type': 'application/x-www-form-urlencoded'},
            data: {
                grant_type: 'client_credentials',
                client_id: process.env.CLIENT_ID,
                client_secret: process.env.CLIENT_SECRET,
                audience: "http:/localhost:3000"
            }
            };

        axios.request(options).then(function (response) {
                console.log(response.data);
            }).catch(function (error) {
                console.error(error);
                console.log("aaaaa");
            });
    
    }
}
</script>

<style>

</style>