<template>
        <!-- <nav>
            <RouterLink :to="{ name: 'main' }">Main</RouterLink> |
            <RouterLink :to="{ name: 'magazine' }">Magazine</RouterLink> | 
            <RouterLink :to="{ name: 'board' }">Community</RouterLink> |
            <RouterLink :to="{ name: 'boardCreate' }">Crew</RouterLink> | 
            <RouterLink :to="{ name: 'login' }">Race</RouterLink>
        </nav> -->
        <div class="nav-bar">
            <RouterLink class="mrt-logo" :to="{name: 'main'}">MRT</RouterLink> |
            <RouterLink class="magazine" :to="{name: 'magazine'}">Magazine</RouterLink> |
            <RouterLink class="community" :to="{name: 'board'}">Community</RouterLink> |
            <RouterLink class="crew" :to="{name: 'crew'}">Crew</RouterLink> |
            <RouterLink class="race" :to="{name: 'race'}">Race</RouterLink> |

            <div class="sign-up-in"></div>
            <div class="sign-up-in2">Sign up/in</div>
            <div class="div"></div>
        </div>
</template>

<script setup>
import { RouterLink } from 'vue-router';


</script>

<style scoped>

</style>