<template>
  <div>
    <h1>圖書借閱系統</h1>
  </div>
  <div id="app">

    <nav>
      <router-link to="/register">註冊</router-link> |
      <router-link to="/login">登入</router-link>
    </nav>


    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

nav {
  margin-bottom: 20px;
}

nav a {
  margin: 0 10px;
  text-decoration: none;
  color: #42b983;
}

nav a:hover {
  text-decoration: underline;
}
</style>