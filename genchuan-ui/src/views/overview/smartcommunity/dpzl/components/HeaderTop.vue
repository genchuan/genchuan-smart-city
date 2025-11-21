<template>
  <!-- 外层滚动容器：固定布局，支持横向滚动 -->
  <div class="page-container">
    <div class="head-top">
      <!-- 退回按钮 -->
      <button class="back-button" @click="handleBack">
        <el-icon color="#00ccff" size="32">
          <ArrowLeft />
        </el-icon>
      </button>

      <ul class="left-but nav-lise">
        <li v-for="(item, key) in leftNavList" :key="key" @click="routerClick(item)">
          <dv-border-box-8><span>{{ item.name }}</span></dv-border-box-8>
        </li>
      </ul>
      <span class="head-name">智慧社区-{{ name }}</span>
      <ul class="right-but nav-lise">
        <li v-for="(item, key) in rightNavList" :key="key" @click="routerClick(item)">
          <dv-border-box-8 :reverse="true"><span>{{ item.name }}</span></dv-border-box-8>
        </li>
      </ul>
      <!-- 全屏按钮 -->
      <el-icon color="#00ccff" size="32" class="fullScreenBut" @click="clickFullscreen">
        <FullScreen/>
      </el-icon>
    </div>
  </div>
</template>

<script setup>
import {ref, onMounted} from 'vue';
import screenFull from 'screenfull';
import {useUserStore} from '@/store/modules/user';
import {useRouter} from 'vue-router';
import {FullScreen, ArrowLeft} from "@element-plus/icons-vue";

const userStore = useUserStore();
const router = useRouter();

// 退回功能实现
const handleBack = () => {
  router.push('/');
};

const leftNavList = ref([
  {name: '全局态势总览', path: '', tag: 'home'},
  {name: '分域场景专题', path: '', tag: '1002'},
  {name: '核心业务指标', path: '', tag: '1003'}
]);
const rightNavList = ref([
  {name: '事件预警追踪', path: '', tag: '1004'},
  {name: '应急指挥视图', path: '', tag: '1005'},
  {name: '跨域场景协同', path: '', tag: '1006'}
]);
const name = ref('');

const routerClick = (item) => {
  if (item.path) {
    router.push(item.path);
  } else {
    userStore.headerTopActive = item.tag;
  }
  name.value = item.name;
};

onMounted(() => {
  let navList = [...rightNavList.value, ...leftNavList.value];
  for (let i = 0; i < navList.length; i++) {
    if (navList[i].tag === userStore.headerTopActive) {
      name.value = navList[i].name;
      return;
    }
  }
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
