<script setup>
import { ref } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

const router = useRouter();

// ref로 입력 필드 관리
const userId = ref("");
const userNickname = ref("");
const userName = ref("");
const userEmail = ref("");
const userBirthday = ref("");
const userPhoneNumber = ref("");

// 주소 관련 ref
const userAddress = ref("");
const userZipCode = ref("");
const userDetailAddress = ref("")

// 기존 ref 유지 ddd
const selectedGender = ref("");
const profileImage = ref(null);
const password = ref("");
const confirmPassword = ref("");
const isUsernameAvailable = ref(true);
const isNicknameAvailable = ref(true);

//주소(우편번호) 검색 함수 
const execDaumPostcode = () => {
  new window.daum.Postcode({
    oncomplete: (data) => {
      // 도로명 주소와 지번 주소 처리
      let addr = '';

      // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져옴
      if (data.userSelectedType === 'R') {
        addr = data.roadAddress;
      } else {
        addr = data.jibunAddress;
      }

      // 데이터 바인딩
      userZipCode.value = data.zonecode;
      userAddress.value = addr;
      // 상세주소 초기화
      userDetailAddress.value = '';
    }
  }).open();
}

// 성별 선택 함수
function selectGender(gender) {
  selectedGender.value = gender;
}

// 프로필 사진 업로드 핸들러
function handleProfileImageUpload(event) {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = () => {
      profileImage.value = reader.result;
    };
    reader.readAsDataURL(file);
  }
}




// 아이디 중복 검사 함수
async function checkUsernameAvailability() {
  // v-model로 입력된 값 직접 사용
  if (!userId.value) {
    alert("아이디를 입력해주세요.");
    return;
  }

  try {
    const response = await axios.get("/api-user/check-userid", {
      params: {
        userId: userId.value,
      },
    });

    isUsernameAvailable.value = !response.data.isDuplicate;

    // 중복 여부에 따른 메시지
    if (isUsernameAvailable.value) {
      alert("사용 가능한 아이디입니다.");
    } else {
      alert("이미 존재하는 아이디입니다.");
    }
  } catch (error) {
    console.error("아이디 중복 검사 오류", error);

    // 에러 상세 로깅
    if (error.response) {
      console.error("Response error:", error.response.data);
      alert(
        error.response.data.message ||
          "아이디 중복 검사 중 오류가 발생했습니다."
      );
    } else if (error.request) {
      console.error("Request error:", error.request);
      alert("서버 응답이 없습니다.");
    } else {
      console.error("Error:", error.message);
      alert("네트워크 오류가 발생했습니다.");
    }

    isUsernameAvailable.value = false;
  }
}

// 닉네임 중복 검사 함수
async function checkNicknameAvailability() {
  // v-model로 입력된 값 직접 사용
  if (!userNickname.value) {
    alert("닉네임을 입력해주세요.");
    return;
  }

  try {
    const response = await axios.get("/api-user/check-nickname", {
      params: {
        userNickname: userNickname.value,
      },
    });

    isNicknameAvailable.value = !response.data.isDuplicate;

    // 중복 여부에 따른 메시지
    if (isNicknameAvailable.value) {
      alert("사용 가능한 닉네임입니다.");
    } else {
      alert("이미 존재하는 닉네임입니다.");
    }
  } catch (error) {
    console.error("닉네임 중복 검사 오류", error);

    // 에러 상세 로깅
    if (error.response) {
      console.error("Response error:", error.response.data);
      alert(
        error.response.data.message ||
          "닉네임 중복 검사 중 오류가 발생했습니다."
      );
    } else if (error.request) {
      console.error("Request error:", error.request);
      alert("서버 응답이 없습니다.");
    } else {
      console.error("Error:", error.message);
      alert("네트워크 오류가 발생했습니다.");
    }

    isNicknameAvailable.value = false;
  }
}

// 회원가입 함수
async function signup() {
  try {
    // 기본 유효성 검사
    if (!userId.value || !userNickname.value || !password.value) {
      alert("필수 정보를 모두 입력해주세요.");
      return;
    }

    if (!isUsernameAvailable.value || !isNicknameAvailable.value) {
      alert("아이디 또는 닉네임 중복을 확인해주세요.");
      return;
    }

    if (password.value !== confirmPassword.value) {
      alert("비밀번호가 일치하지 않습니다.");
      return;
    }

    // 사용자 데이터 객체 생성
    const userData = {
      userId: userId.value,
      userNickname: userNickname.value,
      userPassword: password.value,
      userName: userName.value,
      userEmail: userEmail.value,
      userBirthday: userBirthday.value,
      userGender: selectedGender.value,
      userAddress: userAddress.value,
      userPhoneNumber: userPhoneNumber.value,
      userZipCode: userZipCode.value,
      userDetailAddress:userDetailAddress.value,
    };

    const formData = new FormData();
    formData.append(
      "userData",
      new Blob([JSON.stringify(userData)], {
        type: "application/json",
      })
    );

    // 프로필 이미지가 있는 경우에만 파일 추가
    const fileInput = document.getElementById("profile-image");
    if (fileInput?.files?.length > 0) {
      formData.append("file", fileInput.files[0]);
    }

    // FormData의 내용 확인 (디버깅용)
    for (let pair of formData.entries()) {
      console.log(pair[0] + ": " + pair[1]);
    }

    const response = await axios.post("/api-user/regist", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });

    if (response?.data) {
      alert("회원가입이 완료되었습니다.");
      router.push("/user/login");
    }
  } catch (error) {
    console.error("회원가입 오류:", error);
    if (error.response) {
      alert(
        error.response.data.message || "회원가입 처리 중 오류가 발생했습니다."
      );
    } else {
      alert("서버와의 통신 중 오류가 발생했습니다.");
    }
  }
}
</script>

<template>
  <div class="signup-container">
    <img
      class="background-img"
      src="../../assets/user/sign_up/login_picture.png"
    />
    <div class="signup-form">
      <h1 class="logo">Sign up</h1>
      <div class="spacing"></div>

      <!-- 프로필 사진 업로드 -->
      <div class="form-group">
        <label for="profile-image" class="profile-image-label">
          <span v-if="!profileImage" class="upload-text">프로필 사진</span>
          <img
            v-if="profileImage"
            :src="profileImage"
            alt="Profile Image"
            class="profile-image-preview"
          />
        </label>
        <input
          type="file"
          id="profile-image"
          accept="image/*"
          class="profile-image-input"
          @change="handleProfileImageUpload"
        />
      </div>

      <!-- 아이디 입력 및 중복검사 -->
      <div class="form-group">
        <input type="text" placeholder="아이디" v-model="userId" />
        <button class="check-username-btn" @click="checkUsernameAvailability">
          아이디 중복 검사
        </button>
        <p v-if="!isUsernameAvailable" class="error-message">
          이미 존재하는 아이디입니다.
        </p>
      </div>

      <!-- 별명 입력 및 중복검사 -->
      <div class="form-group">
        <input type="text" placeholder="별명" v-model="userNickname" />
        <button class="check-nickname-btn" @click="checkNicknameAvailability">
          별명 중복 검사
        </button>
        <p v-if="!isNicknameAvailable" class="error-message">
          이미 존재하는 별명입니다.
        </p>
      </div>

      <!-- 비밀번호 입력 -->
      <div class="form-group">
        <input type="password" placeholder="비밀번호" v-model="password" />
      </div>

      <!-- 비밀번호 확인 입력 -->
      <div class="form-group">
        <input
          type="password"
          placeholder="비밀번호 확인"
          v-model="confirmPassword"
        />
        <p
          v-if="password && confirmPassword && password !== confirmPassword"
          class="error-message"
        >
          비밀번호가 일치하지 않습니다.
        </p>
      </div>

      <!-- 이메일 입력 -->
      <div class="form-group">
        <input type="text" placeholder="이메일" v-model="userEmail" />
        <button class="send-code-btn">인증코드 전송</button>
      </div>

      <!-- 이름 입력 -->
      <div class="form-group">
        <input type="text" placeholder="이름" v-model="userName" />
      </div>

      <!-- 생일 입력 -->
      <div class="form-group">
        <input type="date" v-model="userBirthday" />
      </div>

      <!-- 성별 선택 -->
      <div class="form-group gender">
        <button
          class="gender-btn"
          :class="{ active: selectedGender === '남자' }"
          @click="selectGender('남자')"
        >
          남자
        </button>
        <button
          class="gender-btn"
          :class="{ active: selectedGender === '여자' }"
          @click="selectGender('여자')"
        >
          여자
        </button>
      </div>

      <!-- 주소 검색 -->
      <div class="form-group">
        <input type="text" placeholder="주소" v-model="userAddress" />
        <button class="search-address-btn" @click="execDaumPostcode">
         주소 검색하기
        </button>
      </div>

       <!-- 상세주소 -->
        <div class="form-group">
        <input type="text" placeholder="상세주소" v-model="userDetailAddress" />
      </div>


      <!-- 우편번호 입력 -->
      <div class="form-group">
        <input
          type="text"
          placeholder="우편번호"
          v-model="userZipCode"
          pattern="\d*"
          inputmode="numeric"
          maxlength="5"
        />
      </div>

      <!-- 전화번호 입력 -->
      <div class="form-group">
        <input type="tel" placeholder="전화번호" v-model="userPhoneNumber" />
      </div>

      <!-- 인증번호 확인 버튼 -->
      <div class="form-group">
        <input type="text" placeholder="인증번호 입력" />
        <button class="check-code-btn" @click="checkVerificationCode">
          확인
        </button>
      </div>

      <!-- 회원가입 버튼 -->
      <button class="submit-btn" @click="signup">회원가입</button>
    </div>
  </div>
</template>

<style scoped>
.spacing {
  height: 20px; /* 간격을 설정 */
}

/* 기존 스타일 */

.signup-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: rgba(0, 0, 0, 0.5);
  position: relative;
}

.background-img {
  position: absolute;
  width: 100%;
  height: 100%;
  object-fit: cover;
  z-index: -1;
}

.signup-form {
  width: 400px;
  padding: 20px;
  background: rgba(0, 0, 0, 0.7);
  border-radius: 10px;
  color: #fff;
}

.logo {
  font-size: 32px;
  text-align: center;
  font-weight: bold;
}

.title {
  font-size: 24px;
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  display: flex;
  align-items: center;
  justify-content: space-between; /* 버튼과 입력란의 간격 유지 */
  margin-bottom: 15px;
  gap: 10px; /* 입력란과 버튼 사이 간격 */
}

input[type="text"],
input[type="password"],
input[type="date"],
input[type="tel"] {
  flex: 1; /* 입력란이 버튼보다 넓게 차지 */
  padding: 10px;
  border: none;
  border-radius: 5px;
  outline: none;
  font-size: 16px;
}

button {
  width: 40%; /* 버튼 가로폭을 줄임 */
  padding: 10px;
  background-color: #ff5722;
  color: #fff;
  border: none;
  border-radius: 5px;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #e64a19;
}

/* 회원가입 버튼 */
.submit-btn {
  width: 100%; /* 전체 폭 */
  font-size: 18px;
  font-weight: bold;
  padding: 15px; /* 버튼 크기 증가 */
  margin-top: 20px; /* 간격 추가 */
  background-color: #ff5722;
  text-align: center;
  display: block;
}

.submit-btn:hover {
  background-color: #e64a19;
}

/* 오류 메시지 */
.error-message {
  color: red;
  font-size: 14px;
  margin-top: 5px;
}

/* 성별 버튼 스타일 */
.gender-btn {
  width: 48%; /* 가로폭을 조정 */
  padding: 8px 0; /* 성별 버튼 크기 */
  font-size: 16px;
  font-weight: bold;
  border: 2px solid #ddd;
  border-radius: 10px;
  background-color: #fff; /* 기본 흰색 */
  color: #333;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
}

.gender-btn.active,
.gender-btn:hover {
  background-color: #ff5722; /* 선택 및 호버 시 */
  color: #fff;
  border-color: #ff5722;
}

.gender {
  display: flex;
  justify-content: space-between;
  gap: 10px; /* 버튼 간격 추가 */
}

/* 프로필 사진 스타일 */
.profile-image-label {
  display: block;
  width: 120px; /* 너비와 높이를 지정해서 원형을 만들기 */
  height: 120px;
  background-color: #ddd;
  border-radius: 50%; /* 원형으로 만들기 */
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
  cursor: pointer;
  position: relative;
  margin: 0 auto; /* 중앙 정렬 */
}

.upload-text {
  font-size: 16px;
  color: #555;
}

.profile-image-preview {
  width: 100%;
  height: 100%;
  object-fit: cover; /* 이미지가 원형 안에 잘 맞게 조정 */
}

.profile-image-input {
  display: none;
}
</style>
