<template>
  
<div class="bg-gray-800 h-screen text-gray-50">
    <Navbar />
    <main class="flex">
        <Sidebar class = "flex-none" />
        <div class="flex-1 p-4 grow border rounded-2xl mx-6 mt-4 justify-start overflow-auto">
            <h1 class="text-5xl text-center">Recipes for {{ this.$route.params.type }} Cuisine</h1>
            <div class="grid grid-cols-3 gap-8 p-8 text-center max-h-128">
                <h1 v-if="recipes.length == 0" class="col-span-3 text-5xl text-green-500"> Loading... </h1>
                <div v-for="(x,recipe) in recipes" :key="recipe" class="container shadow hover:shadow-xl border-2 rounded-3xl border-gray-100 flex h-64">
                    <h2 class="m-auto text-3xl">{{ recipes[recipe].recipeName }}</h2>
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
    data() {
        return {
            recipes: []
        }
    },

    async beforeMount() {

        if(!this.$store.state.cuisineData) {
            //Get all the ingredients from the backend and store them in Vuex store
            await axios.get(`${ process.env.baseUrl }:${ process.env.apiPort }/getAllCuisines`)
                .then((res) => {
                    console.log(res);
                    this.$store.dispatch("setCuisines", res.data.data);
                    console.log(this.$store.state.cuisineData[0].name);
                })
                .catch((err) => {
                    console.error(err);
                });
        }

        let cuisine = await this.$store.state.cuisineData.filter((cuisine) => {
            return cuisine.name == this.$route.params.type
        });

        console.log(cuisine[0].id);
        await axios.get(`${ process.env.baseUrl }:${ process.env.apiPort }/getRecipesByCuisineId`, { params : { cuisineId : cuisine[0].id }})
            .then((res) => {
                console.log(res);
                this.recipes = res.data.data;
            })
            .catch((err) => {
                console.error(err);
            })
    
    }
}
</script>
