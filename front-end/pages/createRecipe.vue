<template>
  
<div class="bg-gray-800 h-screen text-gray-50">
    <Navbar />
    <main class="flex">
        <Sidebar class = "flex-none" />
        <div class="flex-1 p-4 grow border rounded-2xl mx-6 mt-4 justify-start overflow-auto max-h-256">
            <h2 class="text-5xl text-center">Create New Recipe</h2>
            <form class="grid grid-cols-3 p-4 grow justify-start rounded-2xl mx-6 mt-4 overflow-auto" @submit.prevent="createRecipe">
                <div class="col-span-3 justify-self-center grid grid-cols-2 justify-items-center">
                    <label class="py-5 px-4">Recipe Name:</label>
                    <input class="text-black mt-4 border-2 border-gray-300 bg-gray-50 h-10 pl-2 pr-8 rounded-lg focus:outline-none" 
                    type="text" v-model="recipe.name" />
                </div>
                <div class="col-span-3 justify-self-center grid grid-cols-2 justify-items-center">
                    <label class="py-5 px-4">Description:</label>
                    <input class="text-black mt-4 border-2 border-gray-300 bg-gray-50 h-10 pl-2 pr-8 rounded-lg focus:outline-none" 
                    type="text" v-model="recipe.description" />
                </div>
                <div class="col-span-3 justify-self-center grid grid-cols-2 justify-items-center">
                    <label class="py-5 px-4">Cuisine Name:</label>
                    <input class="text-black mt-4 border-2 border-gray-300 bg-gray-50 h-10 pl-2 pr-8 rounded-lg focus:outline-none" 
                    type="text" v-model="recipe.cuisineName" />
                </div>
                <p class="col-span-3 justify-self-center grid grid-cols-2 mt-4 pl-20">Ingredient Names: (Separate by new line)</p>
                <div class="col-span-3 row-span-2 justify-self-center grid grid-cols-2 justify-items-center">
                    <textarea class="col-span-2 text-black mt-4 border-2 border-gray-300 bg-gray-50 h-10 pl-2 pr-8 rounded-lg focus:outline-none w-96 h-96" 
                    type="text" v-model="recipe.ingredientNames" />
                </div>
                <p class="col-span-3 justify-self-center grid grid-cols-2 mt-4 pl-20">Ingredient Quantities: (Separate by new line)</p>
                <div class="col-span-3 row-span-2 justify-self-center grid grid-cols-2 justify-items-center">
                    <textarea class="col-span-2 text-black mt-4 border-2 border-gray-300 bg-gray-50 h-10 pl-2 pr-8 rounded-lg focus:outline-none w-96 h-96" 
                    type="text" v-model="recipe.ingredientQuantities" />
                </div>
                <p class="col-span-3 justify-self-center grid grid-cols-2 mt-4 pl-20">Instructions:</p>
                <div class="col-span-3 row-span-2 justify-self-center grid grid-cols-2 justify-items-center">
                    <textarea class="col-span-2 text-black mt-4 border-2 border-gray-300 bg-gray-50 h-10 pl-2 pr-8 rounded-lg focus:outline-none w-96 h-96" 
                    type="text" v-model="recipe.instructions" />
                </div>
                <div class="col-span-3 justify-self-center grid grid-cols-2 shadow">
                    <button class="col-span-2 rounded bg-green-900 py-2 px-8 mt-4 hover:bg-gray-900" type="submit">Create</button>
                </div>
            </form>
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
    data() {
        return {
            recipe: {
                name: '',
                description: '',
                cuisineName: '',
                instructions: '',
                ingredientNames: '',
                ingredientQuantities: '',
            }
        }
    },
    methods: {
        async createRecipe() {
        //Get all the ingredients from the backend and store them in Vuex store

        const ingredients = this.recipe.ingredientNames.split("\n");
        console.log(ingredients);
        const quantitites = this.recipe.ingredientQuantities.split("\n");
        console.log(quantitites);
        const userId = this.$store.state.user.userId;
        console.log(userId);

        await axios.post(`${ process.env.baseUrl }:${ process.env.apiPort }/createNewRecipe`, null, {params : { 
            title: this.recipe.name,
            description: this.recipe.description,
            instructions : this.recipe.instructions,
            ingredientNames: this.recipe.ingredientNames,
            ingredientQuantities: this.recipe.ingredientQuantities,
            ownerId: userId,
            cuisineName: this.recipe.cuisineName
            }})
            .then((res) => {
                console.log(res);
            })
            .catch((err) => {
                console.log(err);
            });
    }
    

    }
}
</script>

<style>

</style>