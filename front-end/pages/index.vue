<template>
<div class="bg-gray-800 text-gray-50 h-screen">

  <Navbar />

  <main class="flex mt-2">
    
    <Sidebar class = "flex-none" />
    <div class="flex-1 p-4 grow justify-start border rounded-2xl mx-6 mt-4">
            <h2 class="text-5xl text-center">Welcome to Food Friends!</h2>
            <p class="p-4">
                Share, find, get inspired to try new things in the kitchen!
            </p>
    </div>
    
  </main>

</div>
  

</template>

<script>

import Navbar from '../components/Navbar.vue';
import Sidebar from '../components/Sidebar.vue';



export default {
  components: { Sidebar, Navbar },
  name: 'IndexPage'
}
</script>
