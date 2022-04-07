<template>
  
<div class="bg-gray-800 h-screen text-gray-50">
    <Navbar />
    <main class="flex">
        <Sidebar class = "flex-none" />
        <div class="flex-1 p-4 grow border rounded-2xl mx-6 mt-4 justify-start overflow-auto">
            <h2 class="text-5xl text-center">{{ this.$route.params.title }}</h2>
            <div class="grid grid-cols-3 gap-x-2 p-8 text-center max-h-128">
                <p v-if="loading" class="col-span-3 text-5xl text-green-500"> Loading... </p>
                <p v-if="author.id" class="text-center col-span-3">Author: {{ author.id }} </p>
                <p v-if="description" class="text-center col-span-3">Description: {{ description }} </p>
                <p v-if="instructions" class="text-center col-span-3">Instructions: {{ instructions }} </p>
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
            ingredients: [],
            author: {},
            cuisine: {},
            description: "",
            instructions: "",
            loading : true,
        }
    },

    async beforeMount() {

        if(!this.$store.state.cuisineData) {
            //Get all the ingredients from the backend and store them in Vuex store
            await axios.get(`${ process.env.baseUrl }:${ process.env.apiPort }/getAllCuisines`)
                .then((res) => {
                    console.log(res);
                    this.$store.dispatch("setCuisines", res.data.data);
                    //console.log(this.$store.state.cuisineData[0].name);
                    this.instructions = "xa";
                })
                .catch((err) => {
                    console.error(err);
                });
        }

        const recipeName = this.$route.params.title;

        await axios.get(`${ process.env.baseUrl }:${ process.env.apiPort }/getRecipeByTitle`, { params : { title : recipeName}})
            .then((res) => {
                console.log(res);
                const recipe =  res.data.data[0];
                if(recipe) {
                    this.author.id = recipe.authorId;
                    this.description = recipe.description;
                    this.instructions = recipe.instructions;
                    this.loading = false;
                }
                
                
                
                console.log(this.author.id);
            })
            .catch((err) => {
                console.error(err);
            })
    
    }
}
</script>
