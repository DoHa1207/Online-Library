<template>
  <div class="register">
    <h2>註冊</h2>
    <form @submit.prevent="register">
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
      <div>
        <label for="confirmPassword">確認密碼：</label>
        <input
            type="password"
            id="confirmPassword"
            v-model="confirmPassword"
            required
            placeholder="請再次輸入密碼"
        />
      </div>
      <div>
        <label for="userName">使用者名稱：</label>
        <input
            type="text"
            id="userName"
            v-model="userName"
            required
            placeholder="請輸入使用者名稱"
        />
      </div>
      <button type="submit">註冊</button>
    </form>
    <p v-if="message">{{ message }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'UserRegister',

  data() {
    return {
      phoneNumber: '',
      password: '',
      userName: '',
      message: ''
    };
  },
  methods: {
    async register() {
      if (this.password !== this.confirmPassword) {
        this.message = '密碼與確認密碼不一致';
        return;
      }
      try {
        const response = await axios.post('http://localhost:8080/api/users/register', {
          phoneNumber: this.phoneNumber,
          password: this.password,
          userName: this.userName
        });
        this.message = response.data;
      } catch (error) {
        if (error.response && error.response.status === 400) {
          this.message = '號碼已注冊'; // 顯示後端回傳的錯誤訊息
        } else {
          this.message = '註冊失敗';
        }
      }
    }
  }
};
</script>