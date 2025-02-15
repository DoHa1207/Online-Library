<template>
  <div class="welcome">
    <h2>歡迎登錄！</h2>
    <p>您已成功登錄系統。</p>
    <button @click="logout">登出</button>
  </div>
</template>

<script>
export default {
  name: 'userWelcome',
  methods: {
    logout() {
      localStorage.removeItem('token'); // 移除 JWT Token
      this.$router.push('/login'); // 跳轉到登錄頁面
    }
  }
};
</script>

<style scoped>
.welcome {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
}

button {
  padding: 10px 15px;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #369f6e;
}
</style>