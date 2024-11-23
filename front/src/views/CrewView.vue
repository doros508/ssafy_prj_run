<template>
  <div>
    <TheHeader />
    <hr />
    <div class="container">
      <CrewSearch @search="handleSearch" />
      <CrewMap :crews="filteredCrews" />
      <CrewList :crews="filteredCrews" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useCrewStore } from "@/stores/crew";
import TheHeader from "@/components/common/TheHeader.vue";
import CrewList from "@/components/crew/CrewList.vue";
import CrewMap from "@/components/crew/CrewMap.vue";
import CrewSearch from "@/components/crew/CrewSearch.vue";

const store = useCrewStore();
const filteredCrews = ref([]);

// 초기 데이터 로드
onMounted(async () => {
  try {
    const results = await store.searchCrews({});
    filteredCrews.value = results;
  } catch (error) {
    console.error("Error loading crews:", error);
  }
});

// 검색 결과 처리
const handleSearch = (results) => {
  filteredCrews.value = results;
};
</script>

<style scoped>
.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}
</style>
