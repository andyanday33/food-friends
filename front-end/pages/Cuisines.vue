<template>
  
<div>
    <Navbar />
    <main class="flex">
        <Sidebar class = "flex-none" />
        <div class="flex-1 p-4 grow shadow justify-start">
            <h1 class="text-5xl text-center">Cuisines</h1>
            <div class="grid grid-cols-3 gap-8 p-8 text-center">
                <div class="container shadow hover:shadow-xl border-2 rounded-3xl border-gray-100 flex h-64">
                    <h2 class="m-auto text-3xl">French</h2>
                </div>
                <div class="container shadow hover:shadow-xl border-2 rounded-3xl border-gray-100 flex h-64">
                    <h2 class="m-auto text-3xl">Chinese</h2>
                </div>
                <div class="container shadow hover:shadow-xl border-2 rounded-3xl border-gray-100 flex h-64">
                    <h2 class="m-auto text-3xl">Greek</h2>
                </div>
                <div class="container shadow hover:shadow-xl border-2 rounded-3xl border-gray-100 flex h-64">
                    <h2 class="m-auto text-3xl">Italian</h2>
                </div>
                <div class="container shadow hover:shadow-xl border-2 rounded-3xl border-gray-100 flex h-64">
                    <h2 class="m-auto text-3xl">Spanish</h2>
                </div>
                <div class="container shadow hover:shadow-xl border-2 rounded-3xl border-gray-100 flex h-64">
                    <h2 class="m-auto text-3xl">Mediterranean</h2>
                </div>
            </div>
        </div>
        
    </main>
</div>
</template>

<script>
import Navbar from '../components/Navbar.vue';
import Sidebar from '../components/Sidebar.vue';

export default {
    components: { Navbar, Sidebar },
    name: "CuisinesPage",
    middleware: 'auth'
}
</script>

<style>

</style>