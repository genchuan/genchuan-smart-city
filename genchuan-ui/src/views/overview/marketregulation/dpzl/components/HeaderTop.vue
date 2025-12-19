<template>
  <div class="page-container">
    <div class="head-top">
      <button class="back-button" @click="handleBack">
        <el-icon color="#00ccff" size="32">
          <ArrowLeft />
        </el-icon>
      </button>

      <!-- 左侧导航 -->
      <ul class="left-but nav-lise">
        <li
          v-for="(item, key) in navConfig.slice(0, 3)"
          :key="key"
          @click="routerClick(item)"
          :class="{ active: userStore.headerTopActive === item.tag }"
        >
          <dv-border-box-8 v-if="isDataVReady">
            <span>{{ item.name }}</span>
          </dv-border-box-8>
          <div v-else class="nav-placeholder">
            <span>{{ item.name }}</span>
          </div>
        </li>
      </ul>

      <span class="head-name">市场监管-{{ name }}</span>

      <!-- 右侧导航 -->
      <ul class="right-but nav-lise">
        <li
          v-for="(item, key) in navConfig.slice(3)"
          :key="key"
          @click="routerClick(item)"
          :class="{ active: userStore.headerTopActive === item.tag }"
        >
          <dv-border-box-8 v-if="isDataVReady" :reverse="true">
            <span>{{ item.name }}</span>
          </dv-border-box-8>
          <div v-else class="nav-placeholder">
            <span>{{ item.name }}</span>
          </div>
        </li>
      </ul>

      <el-icon color="#00ccff" size="32" class="fullScreenBut" @click="clickFullscreen">
        <FullScreen/>
      </el-icon>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import screenFull from 'screenfull';
import { useUserStore } from '@/store/modules/user';
import { useRouter, useRoute } from 'vue-router';
import { FullScreen, ArrowLeft } from "@element-plus/icons-vue";

const userStore = useUserStore();
const router = useRouter();
const route = useRoute();

// 修复：简化导航配置
const navConfig = ref([
  { name: '全局态势总览', tag: 'home' },
  { name: '分域场景专题', tag: '1202' },
  { name: '核心业务指标', tag: '1203' },
  { name: '事件预警追踪', tag: '1204' },
  { name: '应急指挥视图', tag: '1205' },
  { name: '跨域场景协同', tag: '1206' }
]);

const name = ref('');
const isDataVReady = ref(false);

// 修复：简化的导航处理
const routerClick = (item) => {
  console.log('导航到:', item.name);

  // 更新状态
  userStore.headerTopActive = item.tag;
  name.value = item.name;

  // 更新 URL 但不触发路由跳转（避免组件重新加载）
  const newUrl = `${window.location.pathname}?active=${item.tag}`;
  window.history.replaceState(null, '', newUrl);
};

const handleBack = () => {
  router.push('/');
};

// 监听路由变化
watch(
  () => route.query.active,
  (newActive) => {
    if (newActive && newActive !== userStore.headerTopActive) {
      userStore.headerTopActive = newActive;
      updateActiveName();
    }
  },
  { immediate: true }
);

// 更新活动名称
const updateActiveName = () => {
  const activeItem = navConfig.value.find(item => item.tag === userStore.headerTopActive);
  if (activeItem) {
    name.value = activeItem.name;
  } else {
    name.value = navConfig.value[0].name;
    userStore.headerTopActive = navConfig.value[0].tag;
  }
};

onMounted(() => {
  // 延迟加载 DataV 组件
  setTimeout(() => {
    isDataVReady.value = true;
  }, 300);

  updateActiveName();
});

const clickFullscreen = () => {
  if (!screenFull.isFullscreen) {
    screenFull.request();
  } else {
    screenFull.exit();
  }
};
</script>

<style lang="scss" scoped>
.page-container {
  width: 100%;
  overflow-x: auto;
  overflow-y: hidden;
  background: url("@/assets/chart/images/bg.jpg") no-repeat;
  background-size: cover;
  min-height: 93px;
}

.head-top {
  width: 100%;
  height: 93px;
  position: relative;
  background: url("@/assets/chart/images/head_bg.png") no-repeat;
  background-size: 100% 100%;
  color: #00ccff;
  font-size: 41px;
  font-weight: bold;

  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;

  .head-name {
    flex: 1;
    text-align: center;
    line-height: 93px;
    white-space: nowrap;
  }

  .left-but, .right-but {
    position: static;
    margin: 0;
    padding: 0;
    list-style: none;
    display: flex;
    /* 继承父元素背景色 */
    background: inherit;
  }
  .back-button {
    top: 10px;
    left: 15px;
    cursor: pointer;
    transition: transform 0.2s;
    padding: 5px;
    background: none;
    border: none;
    align-items: center;
    z-index: 1000;
  }

  .left-but {
    margin-right: auto;

    /* 单独设置返回键样式 - 显示背景色 */
    .nav-lise li:first-child {
      /* 关键：使用导航栏背景图作为按钮背景 */
      background: url("@/assets/chart/images/head_bg.png") no-repeat;
      background-size: 100% 100%;
      /* 确保背景色与父容器一致 */
      background-color: transparent;

      div {
        /* 内部容器继承背景 */
        background: inherit;
        padding: 5px 10px;
        /* 确保文字颜色正确 */
        color: inherit;
      }

      span, i {
        /* 图标/文字不遮挡背景 */
        background: transparent;
        color: inherit;
      }
    }
  }

  .right-but {
    margin-left: auto;
  }

  .nav-lise {
    display: flex;
    flex-wrap: nowrap;
    align-items: center;
    flex-direction: row;
    font-size: 22px;
    gap: 8px;

    li {
      margin: 0;
      cursor: pointer;
      flex-shrink: 0;

      div {
        padding: 5px 10px;
        white-space: nowrap;
      }
    }
  }
}

/* 响应式调整 */
@media (max-width: 1600px) {
  .head-top {
    font-size: 36px;

    .nav-lise {
      font-size: 20px;
    }
  }
}

@media (max-width: 1400px) {
  .head-top {
    font-size: 32px;

    .nav-lise {
      font-size: 18px;
    }

    .nav-placeholder, .dv-border-box-8 {
      padding: 3px 8px;
    }
  }
}

@media (max-width: 1200px) {
  .head-top {
    font-size: 28px;

    .nav-lise span {
      font-size: 16px;
    }
  }
}

@media (max-width: 992px) {
  .head-top {
    height: auto;
    min-height: 93px;
    flex-wrap: wrap;
    padding: 10px 15px;

    .head-name {
      order: -1;
      width: 100%;
      line-height: normal;
      margin-bottom: 10px;
    }
  }
}
</style>
