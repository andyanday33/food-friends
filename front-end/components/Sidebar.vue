<template>
  <aside class="py-2 w-64 mt-10 bg-green-900 border rounded-2xl border-gray-900 max-h-100" aria-label="Sidebar">
   <div class="overflow-y-auto rounded">
      <ul class="space-y-2 text-gray-50">
         <li>
            <NuxtLink to="/cuisines" class="flex items-center p-3 text-base font-normal rounded-lg border-green-500 border-b hover:bg-gray-900 hover:text-gray-50">
               
               <span class="ml-3">Cuisines</span>
            </NuxtLink>

            <NuxtLink to="/underConstruction" href="#" class="flex items-center p-3 text-base font-normal rounded-lg border-red-500 bg-red-900 border-b hover:bg-gray-900 hover:text-gray-50">
               
               <span class="ml-3">Below is Under Construction!!</span>
            </NuxtLink>
            <NuxtLink to="/underConstruction" class="flex items-center p-3 text-base font-normal rounded-lg border-green-500 border-b hover:bg-gray-900 hover:text-gray-50">
               
               <span class="ml-3">Breakfasts</span>
            </NuxtLink>
            <NuxtLink to="/underConstruction" class="flex items-center p-3 text-base font-normalrounded-lg border-green-500 border-b hover:bg-gray-900 hover:text-gray-50">
               
               <span class="ml-3">Lunches</span>
            </NuxtLink>
            <NuxtLink to="/underConstruction" class="flex items-center p-3 text-base font-normal rounded-lg border-green-500 border-b hover:bg-gray-900 hover:text-gray-50">
               
               <span class="ml-3">Dinners</span>
            </NuxtLink>
            <NuxtLink to="/underConstruction" class="flex items-center p-3 text-base font-normal rounded-lg border-green-500 border-b hover:bg-gray-900 hover:text-gray-50">
               
               <span class="ml-3">Desserts</span>
            </NuxtLink>
            <NuxtLink to="/underConstruction" class="flex items-center p-3 text-base font-normal rounded-lg border-green-500 border-b hover:bg-gray-900 hover:text-gray-50">
               
               <span class="ml-3">Drinks</span>
            </NuxtLink>
            <NuxtLink to="/underConstruction" class="flex items-center p-3 text-base font-normal rounded-lg border-green-500 border-b hover:bg-gray-900 hover:text-gray-50">
               
               <span class="ml-3">By ingredient</span>
            </NuxtLink>
         </li>
      </ul>
   </div>
</aside>
</template>

<script>
export default {

}
</script>
