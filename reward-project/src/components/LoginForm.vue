<template>
<main class="login-con">
  <div class="login-box">
<form class="login-form">
      <h1>로그인</h1>
  <div>
    로그인 가능 계정 목록
    <li>id : member1 , pw : member1</li>
    <li>id : member2 , pw : member2</li>
    <li>id : member3 , pw : member3</li>
    <li>id : member4 , pw : member4</li>
    <li>id : member5 , pw : member5</li>
    <li>id : member6 , pw : member6</li>
    <li>id : member7 , pw : member7</li>
    <li>id : member8 , pw : member8</li>
    <li>id : member9 , pw : member9</li>
    <li>id : member10 , pw : member10</li>
  </div>
      <div class="input-section">
        <label for="inputId">아이디</label>
        <input v-model="id" type="text" id="inputId" placeholder="아이디를 입력하세요.">
      </div>
      <div class="input-section">
        <label for="inputPassword">비밀번호</label>
      <input v-model="password" type="password" id="inputPassword" placeholder="비밀번호를 입력하세요.">
      </div>
      <button type="button" class="login-btn" @click="onClickLogin">로그인</button>
</form>
  </div>
</main>

</template>

<script>
import router from '../router';

export default {
  name: 'LoginForm',
  props: {
  },
  data(){
    return {
      userId: null,
      password: null
    }
  },
  methods: {
    onClickLogin(){
      this.$http({
        url: 'http://localhost:8080/auth/login',
        method: 'POST',
        data: {
          loginId: this.id,
          password: this.password
        }
      }).then((result) => {
        console.log(result.data)
        localStorage.setItem('member-data', JSON.stringify(result.data.data));
        router.push({path: '/board'})
      })
      .catch((error) => {
        alert(error.response.data.error.message)
      });
    }
  }
}
</script>
<style scoped>
.login-con {
  width: 100%;
  height: 100%;
  position: relative;
  margin-top: 400px;
}

.login-box {
  display: flex;
  justify-content: center;
  align-items: center;
}

h1{
  font-size: 20px;
  text-align: center;
}

.login-form {
  width: 500px;
  padding: 50px;
  background: #f8f9fa;
  border-radius: 10px;
  border: 2px solid #f8f9fa;
}

.input-section {
  width : 300px;
  margin: 10px 100px;
  font-size: 1rem;
}
.input-section > input {
    display: block;
    width: 100%;
    height: 24px;
    padding: 5px;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #ced4da;
    border-radius: 10px;
}

label {
  display: block;
  text-align: left;
}

.login-btn {
  margin: 0 100px;
  width: 310px;
  display: inline-block;
    color: #212529;
    text-align: center;
    vertical-align: middle;
    background-color: #007bff;
    border: 1px solid transparent;
    padding: 0.375rem 0.75rem;
    font-size: 1rem;
    line-height: 1.5;
    border-radius: 10px;
    color: #fff;
    cursor: pointer;
}
</style>
