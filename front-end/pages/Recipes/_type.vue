<template>
  
<div>
    <Navbar />
    <main class="flex">
        <Sidebar class = "flex-none" />
        <div class="flex-1 p-4 grow shadow justify-start">
            <h1 class="text-5xl text-center">Recipes for {{ this.$route.params.type }} Cuisine</h1>
            <div class="grid grid-cols-3 gap-8 p-8 text-center">
                <div class="container shadow hover:shadow-xl border-2 rounded-3xl border-gray-100 flex h-64">
                    <h2 class="m-auto text-3xl">Recipe 1</h2>
                </div>
            </div>
        </div>
        
    </main>
</div>
</template>

<script>
import Navbar from '../../components/Navbar.vue';
import Sidebar from '../../components/Sidebar.vue';
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
            })
    
    }
}
</script>

<style>

</style>