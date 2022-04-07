<template>
<div class="bg-gray-800 text-white h-screen">
<Navbar />
<main class="flex">
<Sidebar class = "flex-none" />
<div class="flex-1 p-4 grow border rounded-2xl mx-6 mt-4 overflow-auto">
        <h2 class="text-5xl text-center">Under Construction</h2>
        <p class="text-center p-4">
            This page is currently under construction, come back later to see amazing new features that we will implement!
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