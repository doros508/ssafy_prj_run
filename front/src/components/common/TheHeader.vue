<template>
    <div>
        <nav>
            <RouterLink :to="{ name: 'main' }">Main</RouterLink> |
            <!-- <RouterLink :to="{ name: 'magazine' }">Magazine</RouterLink> |  -->
            <RouterLink :to="{ name: 'board' }">Community</RouterLink> |
            <!-- <RouterLink :to="{ name: 'boardCreate' }">Crew</RouterLink> |  -->
            <!-- <RouterLink :to="{ name: 'login' }">Race</RouterLink> -->
        </nav>
    </div>
</template>

<script setup>
import { RouterLink } from 'vue-router';


</script>

<style lang="scss" scoped>

</style>