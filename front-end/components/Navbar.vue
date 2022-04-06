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
                <a href="#" v-if="$auth.loggedIn" @click="printUserData"
                   class=" block mt-4 lg:inline-block lg:mt-0 hover:text-white px-4 py-2 rounded hover:bg-green-900 mr-2">
                    My Recipes
                </a>
                <a href="#" v-if="$auth.loggedIn"
                   class="block mt-4 lg:inline-block lg:mt-0 hover:text-white px-4 py-2 rounded hover:bg-green-900 mr-2">
                    Saved Recipes
                </a>
            </div>
            <div class="relative mx-auto text-gray-600 lg:block hidden">
                <input
                    class="border-2 border-gray-300 bg-white h-10 pl-2 pr-8 rounded-lg text-sm focus:outline-none"
                    type="search" name="search" placeholder="Search">
                <button type="submit" class="absolute right-0 top-0 mt-3 mr-2">
                    <svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"
                        width="20" height="20"
                        viewBox="0 0 30 30"
                        style=" fill:#000000;">
                        <path d="M 13 3 C 7.4889971 3 3 7.4889971 3 13 C 3 18.511003 7.4889971 23 13 23 C 15.396508 23 17.597385 22.148986 19.322266 20.736328 L 25.292969 26.707031 A 1.0001 1.0001 0 1 0 26.707031 25.292969 L 20.736328 19.322266 C 22.148986 17.597385 23 15.396508 23 13 C 23 7.4889971 18.511003 3 13 3 z M 13 5 C 17.430123 5 21 8.5698774 21 13 C 21 17.430123 17.430123 21 13 21 C 8.5698774 21 5 17.430123 5 13 C 5 8.5698774 8.5698774 5 13 5 z"></path>
                    </svg>
                </button>
            </div>
            <div class="flex ">
                <NuxtLink to="/login" v-if="!$auth.loggedIn"
                   class="block text-md px-4 py-2 rounded text-green-600 ml-2 font-bold hover:text-white mt-4 hover:bg-green-900 lg:mt-0"
                   @click="userSignIn"
                   >
                   Login
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
            console.log(data);
        },
        printUserData(){
            console.log("aaaaaa");
            console.log(this.$auth.user);
        }
    }
}
</script>

<style>

</style>