<template>
<div>
<Navbar />
<main class="flex">
<Sidebar class = "flex-none" />
<div class="flex-1 p-4 grow shadow justify-start">
        <h2 class="text-5xl text-center">Unauthorized</h2>
        <p class="p-4">
            Please <a class="color-green-900 hover:text-gray-50 hover:bg-green-900" @click="$auth.loginWith('auth0')">Login</a> or Signup to see this page
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