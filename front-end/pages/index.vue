<template>
<div class="bg-gray-800 text-gray-50 h-screen">

  <Navbar />

  <main class="flex mt-2">
    
    <Sidebar class = "flex-none" />
    <div class="flex-1 p-4 grow justify-start border rounded-2xl mx-6 mt-4">
            <h2 class="text-5xl text-center">Welcome to Food Friends!</h2>
            <p class="p-4">
                Lorem ipsum dolor sit amet consectetur adipisicing elit. At ipsam illum facilis ut? Necessitatibus nesciunt blanditiis natus nostrum, temporibus inventore quibusdam culpa ex nam, corrupti provident voluptatum! Dolore magni sunt porro ipsum nisi nihil distinctio ratione provident quos vel voluptatibus libero cum nemo doloremque deserunt incidunt tempora, maxime fugit illo dolores. Minus laboriosam commodi vero consectetur assumenda in asperiores id, mollitia odit, cupiditate impedit quia beatae qui incidunt dolores magnam nisi sapiente omnis? Labore animi, eligendi minus aliquid quasi nobis ipsum perspiciatis dolore dicta enim sint eius deserunt expedita nisi quas dolores quisquam qui autem! Sit ea aliquid quasi aspernatur?
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
