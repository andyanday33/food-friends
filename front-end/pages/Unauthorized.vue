<template>
<div class="bg-gray-800 text-white h-screen">
<Navbar />
<main class="flex">
<Sidebar class = "flex-none" />
<div class="flex-1 p-4 grow border rounded-2xl mx-6 mt-4 overflow-auto">
        <h2 class="text-5xl text-center">Unauthorized</h2>
        <p class="text-center p-4">
            Please <NuxtLink to="/login" class="lg:inline-block lg:mt-0 py-2 rounded hover:bg-green-900">Login or Signup</NuxtLink>  to see this page
        </p>
</div>

</main>
</div>
</template>

<script>
import Navbar from '../components/Navbar.vue';
import Sidebar from '../components/Sidebar.vue';

export default {
    components: { Navbar, Sidebar }
}
</script>

<style>

</style>