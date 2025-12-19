<template>
  <!-- 外层滚动容器：固定布局，支持横向滚动查看完整内容 -->
  <div class="page-container">
    <div class="head-top">
      <!-- 退回按钮 -->
      <button class="back-button" @click="handleBack">
        <el-icon color="#00ccff" :size="`${1.2}vw`">
          <ArrowLeft />
        </el-icon>
      </button>

      <ul class="left-but nav-lise">
        <li v-for="(item, key) in leftNavList" :key="key" @click="routerClick(item)">
          <dv-border-box-8><span>{{ item.name }}</span></dv-border-box-8>
        </li>
      </ul>
      <span class="head-name">营商服务-{{ name }}</span>
      <ul class="right-but nav-lise">
        <li v-for="(item, key) in rightNavList" :key="key" @click="routerClick(item)">
          <dv-border-box-8 :reverse="true"><span>{{ item.name }}</span></dv-border-box-8>
        </li>
      </ul>
      <!-- 全屏按钮 -->
      <button class="fullScreenBut" @click="clickFullscreen">
        <el-icon color="#00ccff" :size="`${1.2}vw`">
          <FullScreen/>
        </el-icon>
      </button>
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
  {name: '分域场景专题', path: '', tag: '1402'},
  {name: '核心业务指标', path: '', tag: '1403'}
]);
const rightNavList = ref([
  {name: '事件预警追踪', path: '', tag: '1404'},
  {name: '应急指挥视图', path: '', tag: '1405'},
  {name: '跨域场景协同', path: '', tag: '1406'}
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
  overflow-y: hidden;
  background: url("@/assets/chart/images/bg.jpg") no-repeat;
  background-size: 100% 100%;
  min-height: 9vh;
}

.head-top {
  width: 100%;
  height: 10vh;
  position: relative;
  background: url("@/assets/chart/images/head_bg.png") no-repeat;
  background-size: 100% 100%;
  color: #00ccff;
  font-size: 2.1vw;
  font-weight: bold;

  .head-name {
    display: inline-block;
    line-height: 9vh;
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
    white-space: nowrap;
  }

  .left-but {
    position: absolute;
    left: 4vw;
    top: 1vh;
    margin: 0;
    padding: 0;
    list-style: none;
  }

  .right-but {
    position: absolute;
    right: 4vw;
    top: 1vh;
    margin: 0;
    padding: 0;
    list-style: none;
  }

  .nav-lise {
    display: flex;
    flex-wrap: nowrap; // 强制不折行，保持布局固定
    align-items: center;
    flex-direction: row;
    font-size: 1.1vw;

    li {
      margin: 0 4px; // 统一间距，替代nth-of-type
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
    top: 0.5vw;
    right: 1vw;
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

  .back-button {
    position: absolute;
    top: 0.5vw;
    left: 1vw;
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
</style>
