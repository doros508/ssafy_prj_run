 <template>
    <div class="login">
      <!-- 배경 이미지 -->
      <div class="login-picture"></div>
      
      <!-- 로그인 영역 -->
      <div class="sign-in">
        <div class="div"></div>
        <div class="social-login">
          <img class="naver" src="../../assets/user/login/naver.png" />
          <img class="kakao" src="../../assets/user/login/kakao.png" />
          <img class="google" src="../../assets/user/login/google.png" />
        </div>
        
        <!-- 회원가입 버튼 -->
        <div class="sign-up">
          <div class="sign-up-box"></div>
          <div class="sign-up-text">회원가입</div>
        </div>
        
        <!-- 로그인 버튼 -->
        <div class="login2">
          <div class="login-box"></div>
          <div class="login-text">로그인</div>
        </div>
        
        <!-- 비밀번호 찾기 -->
        <div class="div2">비밀번호를 잊어버리셨나요?</div>
        
        <!-- 비밀번호 입력 필드 -->
        <div class="password">
          <div class="password-box"></div>
          <div class="password-text">비밀번호</div>
        </div>
        
        <!-- 아이디 입력 필드 -->
        <div class="id">
          <div class="id-box"></div>
          <div class="id-text">아이디</div>  
        </div>
        
        <!-- 로그인 텍스트 -->
        <div class="sign-in-logo">Sign in</div>
      </div>
      
      <!-- MRT 로고 -->
      <div class="mrt-logo">MRT</div>
    </div>
  </template>
  
  <script setup>
    import { defineComponent } from 'vue';
  
  </script>
  
  <style scoped>
  .login,
  .login * {
    box-sizing: border-box;
  }
  
  .login {
    height: 100vh; /* 화면 크기에 맞게 설정 */
    position: relative;
  }
  
  .login-picture {
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    background-image: url('@/assets/login-picture0.png'); /* 배경 이미지 경로 */
    background-size: cover;
    background-position: center;
  }
  
  .sign-in {
    width: 649px;
    height: 694px;
    position: static;
  }
  
  .div {
    background: rgba(0, 0, 0, 0.8);
    border-radius: 12px;
    opacity: 0.8;
    width: 33.8%;
    height: 42.21%;
    position: absolute;
    right: 31.82%;
    left: 34.38%;
    bottom: 27.13%;
    top: 30.66%;
  }
  
  .social-login {
    width: 303px;
    height: 70px;
    position: static;
  }
  
  .naver,
  .kakao,
  .google {
    width: 3.65%;
    height: 4.26%;
    position: absolute;
    object-fit: cover;
  }
  
  .naver {
    right: 46.77%;
    left: 49.58%;
    bottom: 32.48%;
    top: 63.26%;
  }
  
  .kakao {
    right: 52.97%;
    left: 43.39%;
    bottom: 32.48%;
    top: 63.26%;
  }
  
  .google {
    right: 40.83%;
    left: 55.52%;
    bottom: 32.48%;
    top: 63.26%;
  }
  
  .sign-up,
  .login2 {
    width: 237px;
    height: 56px;
    position: static;
  }
  
  .sign-up-box,
  .login-box {
    background: #ffffff;
    border-radius: 28px;
    width: 12.34%;
    height: 3.41%; 
    position: absolute;
    bottom: 39.6%;
    top: 57%;
  }
  
  .sign-up-text,
  .login-text {
    color: #000000;
    text-align: center;
    font-family: "Inter-Bold", sans-serif;
    font-size: 20px;
    font-weight: 700;
    position: absolute;
    bottom: 40.57%;
    top: 58.03%;
    height: 1.4%;
  }
  
  .div2 {
    color: #ffffff;
    text-align: left;
    font-family: "Inter-Italic", sans-serif;
    font-size: 18px;
    font-weight: 400;
    font-style: italic;
    position: absolute;
    right: 35.83%;
    left: 53.07%;
    width: 11.09%;
    bottom: 48.44%;
    top: 49.98%;
    height: 1.58%;
  }
  
  .password,
  .id {
    width: 501.62px;
    height: 41.02px;
    position: static;
  }
  
  .password-box,
  .id-box {
    background: #ffffff;
    border-radius: 20px;
    width: 26.13%;
    height: 2.5%;
    position: absolute;
  }
  
  .password-text,
  .id-text {
    color: #000000;
    text-align: left;
    font-family: "Inter-Italic", sans-serif;
    font-size: 20px;
    font-weight: 400;
    font-style: italic;
    position: absolute;
    bottom: 51.78%;
    top: 46.88%;
    height: 1.34%;
  }
  
  .sign-in-logo {
    color: #ffffff;
    text-align: left;
    font-family: "Inter-BlackItalic", sans-serif;
    font-size: 48px;
    font-weight: 900;
    font-style: italic;
    position: absolute;
    right: 43.85%;
    left: 46.72%;
    width: 9.43%;
    bottom: 59.85%;
    top: 35.46%;
    height: 4.68%;
  }
  
  .mrt-logo {
    color: #ffffff;
    text-align: left;
    font-family: "Notable-Regular", sans-serif;
    font-size: 61px;
    font-weight: 400;
    position: absolute;
    right: 88.54%;
    left: 2.4%;
    width: 9.06%;
    bottom: 93.07%;
    top: 2.43%;
    height: 4.5%;
  }
  </style>
  