// 引入unocss css
import '@/plugins/unocss'

// 导入全局的svg图标
import '@/plugins/svgIcon'

// 初始化多语言
import {setupI18n} from '@/plugins/vueI18n'

// 引入状态管理
import {setupStore} from '@/store'

// 全局组件
import {setupGlobCom} from '@/components'

// 引入 element-plus
import {setupElementPlus} from '@/plugins/elementPlus'

// 引入 form-create
import {setupFormCreate} from '@/plugins/formCreate'

// 引入全局样式
import '@/styles/index.scss'
import '@/assets/thingsBoard/style.scss'

// 引入动画
import '@/plugins/animate.css'

// 路由
import router, {setupRouter} from '@/router'

// 指令
import {setupAuth, setupMountedFocus} from '@/directives'

import {createApp} from 'vue'

import App from './App.vue'

import 'uno.css'

import './permission'

import '@/plugins/tongji' // 百度统计
import Logger from '@/utils/Logger'

import VueDOMPurifyHTML from 'vue-dompurify-html' // 解决v-html 的安全隐患
import DataVVue3 from '@kjgl77/datav-vue3'





// 清理缓存和菜单 开始
import {CACHE_KEY, useCache} from '@/hooks/web/useCache'
const {wsCache} = useCache();
import packageJson from '../package.json';
const VUE_APP_VERSION = packageJson.version;
console.log(VUE_APP_VERSION)
const vers = window.localStorage.getItem("appVersion");

if (VUE_APP_VERSION !== vers) {
  localStorage.clear();
  window.localStorage.setItem("appVersion", VUE_APP_VERSION);
  wsCache.delete(CACHE_KEY.USER)
  wsCache.delete(CACHE_KEY.ROLE_ROUTERS)
  window.location.reload();
}
// 清理缓存和菜单 结束


// 创建实例
const setupAll = async () => {
  const app = createApp(App)

  await setupI18n(app)

  setupStore(app)

  setupGlobCom(app)

  setupElementPlus(app)

  setupFormCreate(app)

  setupRouter(app)

  // directives 指令
  setupAuth(app)
  setupMountedFocus(app)

  await router.isReady()
  app.use(DataVVue3)
  app.use(VueDOMPurifyHTML)

  app.mount('#app')
}

setupAll()

Logger.prettyPrimary(`欢迎使用`, import.meta.env.VITE_APP_TITLE)
