<template>
  <div class="crew-list">
    <table class="table table-dark table-striped table-hover">
      <thead>
        <tr>
          <th>크루명</th>
          <th>지역</th>
          <th>인원</th>
          <th>모임요일</th>
          <th>상세위치</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="crew in crews"
          :key="crew.crewNo"
          @click="goToCrewUrl(crew.crewUrl)"
        >
          <td>{{ crew.crewName }}</td>
          <td>{{ crew.cityName }}</td>
          <td>{{ crew.crewSize }}명</td>
          <td>{{ crew.crewDay }}</td>
          <td>{{ crew.crewLocation }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
const props = defineProps({
  crews: {
    type: Array,
    required: true,
  },
});

const goToCrewUrl = (url) => {
  if (url) window.open(url, "_blank");
};
</script>

<style scoped>
.table {
  text-align: center;
  border-radius: 15px;
  overflow: hidden;
}

tr {
  cursor: pointer;
}

tr:hover {
  background-color: rgba(255, 87, 34, 0.1);
}
</style>
