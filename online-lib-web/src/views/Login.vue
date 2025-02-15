<template>
  <div class="login">
    <h2>登入</h2>
    <form @submit.prevent="login">
      <div>
        <label for="phoneNumber">手機號碼：</label>
        <input
            type="text"
            id="phoneNumber"
            v-model="phoneNumber"
            required
            placeholder="請輸入手機號碼"
        />
      </div>
      <div>
        <label for="password">密碼：</label>
        <input
            type="password"
            id="password"
            v-model="password"
            required
            placeholder="請輸入密碼"
        />
      </div>
      <button type="submit">登入</button>
    </form>
    <p v-if="message">{{ message }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'UserLogin',
  data() {
    return {
      phoneNumber: '',
      password: '',
      message: ''
    };
  },
  methods: {
    async login() {
      try {
        const response = await axios.post('http://localhost:8080/api/users/login', {
          phoneNumber: this.phoneNumber,
          password: this.password
        },{
              headers: {
                "Content-Type": "application/json"
              }

        });
        localStorage.setItem('token', response.data.token);
        this.$router.push('/welcome'); // 跳轉到書籍頁面
      } catch (error) {
        this.message = error.response?.data || '登入失敗';
      }
    }
  }
};
</script>