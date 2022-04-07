<template>
  
<div class="bg-gray-800 h-screen text-gray-50">
    <Navbar />
    <main class="flex">
        <Sidebar class = "flex-none" />
        <div class="flex-1 p-4 grow border rounded-2xl mx-6 mt-4 justify-start overflow-auto">
            <h1 class="text-5xl text-center">Cuisines</h1>
            <div class="grid grid-cols-3 gap-8 p-8 text-center max-h-128">
                <!-- <NuxtLink to="/Recipes/French" class="container shadow hover:shadow-xl border-2 rounded-3xl border-gray-100 flex h-64 hover:bg-gray-700">
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
                </NuxtLink> -->
                <h1 v-if="!$store.state.cuisineData" class="col-span-3 text-5xl text-green-500"> Loading... </h1>
                <NuxtLink v-for="(x,cuisine) in $store.state.cuisineData" :key="cuisine" :to="`/Recipes/` + $store.state.cuisineData[cuisine].name"
                 class="container shadow hover:shadow-xl border-2 rounded-3xl border-gray-100 flex h-64 hover:bg-gray-700">
                    <h2 class="m-auto text-3xl">{{ $store.state.cuisineData[cuisine].name }}</h2>
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
    
    async beforeMount() {
        //Get all the ingredients from the backend and store them in Vuex store
        await axios.get(`${ process.env.baseUrl }:${ process.env.apiPort }/getAllCuisines`)
            .then((res) => {
                console.log(res);
                this.$store.dispatch("setCuisines", res.data.data);
                console.log(this.$store.state.cuisineData[0].name);
            })
            .catch((err) => {
                console.log(err);
            });

    }
}
</script>

<style>

</style>