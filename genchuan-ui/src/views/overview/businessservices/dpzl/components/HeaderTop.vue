<template>
  <!-- 外层滚动容器：固定布局，支持横向滚动查看完整内容 -->
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
      <span class="head-name">营商服务-{{ name }}</span>
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
// 外层滚动容器：固定宽度，确保布局不随窗口变化，超出可滚动
.page-container {
  width: 1920px; // 固定设计稿宽度（与设计稿保持一致）
  overflow-x: auto; // 横向超出时允许滚动（支持鼠标拖动/触屏滑动）
  overflow-y: hidden; // 禁止垂直滚动
  background: url("@/assets/chart/images/bg.jpg") no-repeat;
  background-size: cover; // 背景图完整覆盖容器，避免露出黑色区域
  min-height: 93px; // 与头部高度一致，确保背景连贯
}

// 隐藏滚动条但保留滚动功能（视觉优化）
::-webkit-scrollbar {
  height: 0;
}

// 头部样式：固定宽度，与外层容器匹配
.head-top {
  width: 1920px; // 与滚动容器宽度一致，确保背景完整覆盖
  height: 93px;
  position: relative;
  background: url("@/assets/chart/images/head_bg.png") no-repeat;
  background-size: 100% 100%; // 头部背景图完全覆盖自身，无拉伸/截断
  color: #00ccff;
  font-size: 41px;
  font-weight: bold;

  .head-name {
    display: inline-block;
    line-height: 93px;
    // 绝对居中定位，不受滚动影响
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
    white-space: nowrap; // 避免文字折行破坏布局
  }

  .left-but {
    position: absolute;
    left: 60px; // 固定左侧距离，不随窗口变化
    top: 6px;
    margin: 0;
    padding: 0;
    list-style: none; // 清除默认列表样式
  }

  .right-but {
    position: absolute;
    right: 60px; // 固定右侧距离，不随窗口变化
    top: 6px;
    margin: 0;
    padding: 0;
    list-style: none; // 清除默认列表样式
  }

  .nav-lise {
    display: flex;
    flex-wrap: nowrap; // 强制不折行，保持原始布局
    align-items: center;
    flex-direction: row;
    font-size: 22px;

    li {
      margin: 0 4px; // 统一间距，替代nth-of-type避免布局偏差
      cursor: pointer;

      div {
        padding: 5px;
        margin: 5px;
        white-space: nowrap; // 文字不折行，确保导航项完整显示
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
</style>
