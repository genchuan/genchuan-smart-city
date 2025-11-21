<template>
  <!-- 外层滚动容器：固定宽度，超出时可滚动 -->
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
      <span class="head-name">卫生健康-{{ name }}</span>
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
  {name: '分域场景专题', path: '', tag: '0602'},
  {name: '核心业务指标', path: '', tag: '0603'}
]);
const rightNavList = ref([
  {name: '事件预警追踪', path: '', tag: '0604'},
  {name: '应急指挥视图', path: '', tag: '0605'},
  {name: '跨域场景协同', path: '', tag: '0606'}
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
// 外层滚动容器：固定宽度，确保内容不折行，超出时可滚动
.page-container {
  width: 1920px; // 固定设计稿宽度（根据实际设计稿调整）
  overflow-x: auto; // 横向超出时可滚动
  overflow-y: hidden;
  background: url("@/assets/chart/images/bg.jpg") no-repeat;
  background-size: cover; // 背景图覆盖整个容器，避免黑色区域
  min-height: 100vh;
  -webkit-overflow-scrolling: touch; /* 让iOS设备支持平滑触摸滚动 */
  touch-action: pan-x; /* 明确允许横向触摸滑动（兼容安卓/ios） */
}

// 头部样式：固定宽度，与外层容器一致
.head-top {
  width: 1920px; // 与page-container宽度一致，确保背景完整
  height: 93px;
  position: relative;
  background: url("@/assets/chart/images/head_bg.png") no-repeat;
  background-size: 100% 100%; // 头部背景图完全覆盖
  color: #00ccff;
  font-size: 41px;
  font-weight: bold;

  .head-name {
    display: inline-block;
    line-height: 93px;
    // 居中定位，不使用text-align避免受父容器滚动影响
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
  }

  .left-but {
    position: absolute;
    left: 60px; // 固定左侧距离，不随窗口变化
    top: 6px;
  }

  .right-but {
    position: absolute;
    right: 60px; // 固定右侧距离，不随窗口变化
    top: 6px;
  }

  .nav-lise {
    display: flex;
    flex-wrap: nowrap; // 强制不折行，确保布局固定
    align-items: center;
    gap: 8px; // 用gap替代margin，避免计算偏差
    font-size: 22px;
    margin: 0;
    padding: 0;
    list-style: none;

    li {
      cursor: pointer;

      div {
        padding: 5px;
        margin: 5px;
        white-space: nowrap; // 文字不折行
      }
    }
  }

  .fullScreenBut {
    position: absolute;
    top: 10px;
    right: 15px;
    font-size: 30px;
    z-index: 99999;
    color: #00ccff;
    cursor: pointer;
  }

  .back-button {
    position: absolute;
    top: 10px;
    left: 15px;
    cursor: pointer;
    transition: transform 0.2s;
    padding: 5px;
    background: none;
    border: none;
    display: flex;
    align-items: center;
    z-index: 1000;

    &:hover {
      transform: scale(1.05);
      background: rgba(0, 30, 60, 0.8);
      border-radius: 4px;
    }
  }
}

// 隐藏滚动条但保留滚动功能（可选，根据设计需求）
::-webkit-scrollbar {
  height: 0;
}
</style>
