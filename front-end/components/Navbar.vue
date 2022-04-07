<template>
 <header> 
     <nav class="flex items-center justify-between flex-wrap py-4 lg:px-12 bg-gray-900">
        <div class="flex justify-between lg:w-auto w-full pl-6 pr-2 pb-5 lg:pb-0">
            <div class="flex items-center flex-shrink-0 text-gray-800 mr-16">
                <span class="font-semibold text-xl tracking-tight text-gray-50">Food Friends</span>
            </div>
        </div>
    
        <div class="menu w-full lg:block flex-grow lg:flex lg:items-center lg:w-auto lg:px-3 px-8">
            <div class="text-md font-bold text-green-600 lg:flex-grow">
                <NuxtLink to="/"
                   class="block mt-4 lg:inline-block lg:mt-0 hover:text-white px-4 py-2 rounded hover:bg-green-900 mr-2">
                    Home
                </NuxtLink>
                <NuxtLink to="/createRecipe" v-if="$auth.loggedIn"
                   class="block mt-4 lg:inline-block lg:mt-0 hover:text-white px-4 py-2 rounded hover:bg-green-900 mr-2">
                    Create new recipe
                </NuxtLink>
                <NuxtLink to="/underConstruction" href="#" v-if="$auth.loggedIn" 
                   class=" block mt-4 lg:inline-block lg:mt-0 hover:text-white px-4 py-2 rounded hover:bg-green-900 mr-2">
                    My Recipes
                </NuxtLink>
                <NuxtLink to="/underConstruction" href="#" v-if="$auth.loggedIn" 
                   class="block mt-4 lg:inline-block lg:mt-0 hover:text-white px-4 py-2 rounded hover:bg-green-900 mr-2">
                    Saved Recipes
                </NuxtLink>
            </div>
            <div class="flex ">
                <NuxtLink to="/login" v-if="!$auth.loggedIn"
                   class="block text-md px-4 py-2 rounded text-green-600 ml-2 font-bold hover:text-white mt-4 hover:bg-green-900 lg:mt-0"
                   >
                   Login
                </NuxtLink>
                <NuxtLink to="/register" v-if="!$auth.loggedIn"
                   class="block text-md px-4 py-2 rounded text-green-600 ml-2 font-bold hover:text-white mt-4 hover:bg-green-900 lg:mt-0"
                   >
                   Register
                </NuxtLink>
                <a href="#" v-if="$auth.loggedIn"
                   class=" block text-md px-4  ml-2 py-2 rounded text-green-600 font-bold hover:text-white mt-4 hover:bg-green-900 lg:mt-0"
                   @click="userLogOut"
                   >Log Out</a>
            </div>
        </div>
    
    </nav>
 </header>
</template>

<script>
export default {   
    methods: {
        async userLogOut() {
            let data = await this.$auth.logout();
            this.$store.dispatch("setIsAdmin", false); //We're always taking admin role from the current user
            this.$store.dispatch("setUser", {});
        },
        printUserData(){
            console.log("aaaaaa");
            console.log(this.$auth.user);
            console.log(this.$store.state.user);
        }
    }
}
</script>

<style>

</style>