<template>
  <div class="magazine-container">
    <h1 class="magazine-logo">Magazine</h1>
    <div class="content">
      <div class="btn-box">
        <button type="button" id="writeform-btn" class="btn btn-primary" @click="writeMagazine">
          <i class="bi bi-pencil-square"></i> 글쓰기
        </button>
      </div>
      <div class="search-console">
        <div class="row mb-4">
          <div class="col-md-4">
            <div class="form-group">
              <label for="dateFilter" class="text-white mb-2">주제</label>
              <select class="form-control" id="regionFilter" v-model="filters.cityNo">
                <option value="">전체</option>
                <option value="1">서울</option>
                <option value="2">경기</option>
                <option value="3">인천</option>
                <option value="4">강원</option>
                <option value="5">충청</option>
                <option value="6">전라</option>
                <option value="7">경상</option>
                <option value="8">제주</option>
              </select>
            </div>
          </div>
          <div class="col-md-4">
            <div class="form-group">
              <label for="regionFilter" class="text-white mb-2">검색어</label>
              <input type="text" class="form-control" id="dateFilter" v-model="filters.date">
            </div>
          </div>
          <div class="col-md-4">
            <div class="form-group">
              <label for="distanceFilter" class="text-white mb-2">정렬</label>
              <select class="form-control" id="distanceFilter" v-model="filters.distance">
                <option value="">전체</option>
                <option value="5K">5KM</option>
                <option value="10K">10KM</option>
                <option value="Half">하프코스</option>
                <option value="Full">풀코스</option>
              </select>
            </div>
          </div>
        </div>
        <!-- Search Button -->
        <div class="row mb-4">
          <div class="col-12">
            <button class="btn btn-primary w-100" @click="applyFilters">검색</button>
          </div>
        </div>
        <!-- 네비게이션 탭 -->
        <div class="magazine-list-box">
          <div class="magazine-card" v-for="magazine in store.magazineList" :key="magazine.magazineNo"
            @click="detailMagazine(magazine.magazineNo)">
            <div class="card text-bg-dark">
              <img src="../../assets/magazine/list/img2.png" class="card-img" alt="" />
              <div class="card-img-overlay">
                <h5 class="card-title">{{ magazine.magazineTitle }}</h5>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script setup>
import { useMagazineStore } from "@/stores/magazine";
import { computed, onMounted, ref } from "vue";
import { useRouter } from "vue-router";

const store = useMagazineStore();
const router = useRouter();

onMounted(() => {
  store.getMagazineList();
});

const detailMagazine = function (magazineNo) {
  console.log(magazineNo + "번 매거진 클릭");
  router.push({ name: 'magazineDetail', params: {magazineNo}});
};

const writeMagazine = function () {
  console.log("글쓰기 버튼 클릭!")
  router.push({ name: 'magazineWrite' })
}

// 검색 관련 함수들 -S
const filters = ref({
  date: '',
  cityNo: '',
  distance: ''
})

// 날짜 포맷팅 함수
const formatDate = (dateString) => {
  if (!dateString) return ''
  const date = new Date(dateString)
  return date.toLocaleDateString('ko-KR', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit'
  })
}

// 필터링된 레이스 목록
const filteredRaces = computed(() => {
  let results = store.raceList

  if (filters.value.date) {
    results = results.filter(race =>
      race.raceDate.includes(filters.value.date)
    )
  }

  if (filters.value.cityNo) {
    results = results.filter(race =>
      race.cityNo === parseInt(filters.value.cityNo)
    )
  }

  if (filters.value.distance) {
    results = results.filter(race =>
      race.raceDistance === filters.value.distance
    )
  }

  return results
})

// 필터 적용
const applyFilters = async () => {
  try {
    if (filters.value.cityNo) {
      await store.getRacesByCity(parseInt(filters.value.cityNo))
    } else {
      await store.getRaceList()
    }
  } catch (error) {
    console.error('Error applying filters:', error)
  }
}
// 검색 관련 함수들 -F
</script>

<style scoped>
* {
  box-sizing: border-box;
}

.magazine-container {
  width: 100%;
}

.magazine-logo {
  text-align: center;
  margin: 15px 0;
  color: #fff;
  font-weight: bold;
}

.content {
  background-color: #181818;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  justify-content: center;
}

/* 검색 콘솔 디자인 */
.form-control {
  background-color: rgba(255, 255, 255, 0.9);
}

.form-control:focus {
  background-color: #fff;
}

.search-console {
  width: 100%;
  padding: 20px;
  background-color: rgba(0, 0, 0, 0.7);
  border-radius: 10px;
}

label {
  font-weight: bold;
}

/* 검색 콘솔 디자인 */

.magazine-list-box {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  justify-content: center;
}

.magazine-card {
  /* background-color: yellow; */
  cursor: pointer;
  width: 380px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.magazine-card:hover {
  transform: scale(1.05);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}

.card-img {
  width: 100%;
}

.card-title {
  font-weight: bold;
  position: absolute;
  bottom: 0;
  margin-bottom: 20px;
}

.btn-box {
  width: 100%;
  display: flex;
  justify-content: flex-end;
}

#writeform-btn {
  /* align-self: flex-end; */
  /* 버튼을 오른쪽 끝에 배치 */
  margin-top: 0;
  /* 버튼과 다른 요소들 간 간격 조정 */
}

.btn-primary {
  background-color: #ff5722;
  border-color: #ff5722;
  margin: 3px;
}

.btn-primary:hover {
  background-color: #e64a19;
  border-color: #e64a19;
}
</style>
