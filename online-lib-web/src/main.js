import { createApp } from 'vue'; // 從 'vue' 導入 createApp
import App from './App.vue'; // 導入根元件
import router from './router'; // 導入路由配置

// 創建 Vue 應用實例
const app = createApp(App);

// 使用路由
app.use(router);

// 掛載到 #app
app.mount('#app');