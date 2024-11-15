<template>
  <div class="div3">
    <div class="race">Race</div>
    <div class="div4"></div>
    <div class="div5">+</div>
    <div class="div6">최신 대회 정보</div>
    <div class="_1" v-for="(event, index) in events" :key="index">
      <div class="_12">{{ event.date }}</div>
      <div class="_13">{{ event.name }}</div>
    </div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      events: [
        { date: '대회 일자 1', name: '대회 이름 1' },
        { date: '대회 일자 2', name: '대회 이름 2' },
        { date: '대회 일자 3', name: '대회 이름 3' },
        { date: '대회 일자 4', name: '대회 이름 4' },
      ]
    }
  }
}
</script>


<style scoped>
.race {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-Black", sans-serif;
  font-size: 55px;
  font-weight: 900;
}
</style>