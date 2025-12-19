<template>
  <!-- 外层滚动容器：固定布局，支持横向滚动 -->
  <div class="page-container">
    <div class="head-top">
      <!-- 退回按钮 -->
      <button class="back-button" @click="handleBack">
        <el-icon color="#00ccff" :size="`${1.2}vw`">
          <ArrowLeft />
        </el-icon>
      </button>

      <ul class="left-but nav-lise">
        <!-- 1. 应用场景导航位置替换为筛选栏（单独渲染，无导航文字） -->
        <li key="scene-filter">
          <!-- @click.stop 阻止筛选栏点击触发导航逻辑 -->
          <div class="scene-filter-bar" @click.stop>
            <el-cascader
              v-model="selectedScene"
              placeholder="选择应用场景"
              size="small"
              :options="sceneList"
              separator="/"
              collapse-tags
              @change="handleSceneChange"
              style="width: 10vw;"
              clearable
            />
          </div>
        </li>
        <!-- 2. 其他导航项正常渲染 -->
        <li v-for="(item, key) in filteredLeftNavList" :key="key" @click="routerClick(item)">
          <dv-border-box-8><span>{{ item.name }}</span></dv-border-box-8>
        </li>
      </ul>

      <!-- 标题：原始模板替换为子场景名称 -->
      <span class="head-name">{{ currentSceneName }}-{{ validTitle }}</span>

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
import {ref, onMounted, computed, nextTick} from 'vue';
import screenFull from 'screenfull';
import {useUserStore} from '@/store/modules/user';
import {useRouter} from 'vue-router';
import {FullScreen, ArrowLeft} from "@element-plus/icons-vue";
import {ElMessage} from 'element-plus';
import {getSceneList} from '@/api/overview/dpzl/map.js';

const userStore = useUserStore();
const router = useRouter();

// 退回功能实现（原始逻辑）
const handleBack = () => {
  router.push('/');
};

// 原始导航列表（保留所有项，用于过滤）
const leftNavList = ref([
  {name: '应用场景导航', path: '', tag: 'nav'},
  {name: '全局态势总览', path: '', tag: 'home'},
  {name: '核心业务指标', path: '', tag: '0103'}
]);
const rightNavList = ref([
  {name: '事件预警追踪', path: '', tag: '0104'},
  {name: '应急指挥视图', path: '', tag: '0105'},
  {name: '跨域场景协同', path: '', tag: '0106'}
]);

// 筛选栏核心数据（和地图页面完全一致）
const sceneList = ref([]);
const selectedScene = ref([]);

// 【解决ESLint】计算属性：过滤掉应用场景导航项
const filteredLeftNavList = computed(() => {
  return leftNavList.value.filter(item => item.tag !== 'nav');
});

// 计算属性：仅显示有效导航标题（排除应用场景导航）
const validTitle = computed(() => {
  const allNav = [...filteredLeftNavList.value, ...rightNavList.value];
  const activeItem = allNav.find(item => item.tag === userStore.headerTopActive);
  // 默认显示第一个有效导航（全局态势总览）
  return activeItem?.name || filteredLeftNavList.value[0]?.name || '';
});

// 【新增】计算属性：仅获取最后面的子场景名称（替换原始模板）
const currentSceneName = computed(() => {
  // 已选中场景：仅返回子场景名称
  if (selectedScene.value.length >= 2) {
    return getSceneName(selectedScene.value);
  }
  // 未选中场景：显示第一个子场景名称（默认）
  if (sceneList.value.length > 0 && sceneList.value[0].children?.length > 0) {
    return getSceneName([
      sceneList.value[0].value,
      sceneList.value[0].children[0].value
    ]);
  }
  // 兜底：显示“默认场景”
  return '默认场景';
});

// 【新增】辅助函数：仅获取最后面的子场景名称
const getSceneName = (cascaderValue) => {
  if (!cascaderValue || cascaderValue.length < 2) return '默认场景';
  const parent = sceneList.value.find(item => item.value === cascaderValue[0]);
  if (!parent || !parent.children) return '默认场景';
  const child = parent.children.find(item => item.value === cascaderValue[1]);
  // 仅返回子场景的label，去掉父分类
  return child?.label || '默认子场景';
};

// 导航点击逻辑（原始逻辑，仅修改激活状态）
const routerClick = (item) => {
  if (item.path) {
    router.push(item.path);
  } else {
    userStore.headerTopActive = item.tag;
  }
};

// 筛选栏场景切换：仅触发全局事件，不修改任何导航状态
const handleSceneChange = () => {
  window.dispatchEvent(
    new CustomEvent('scene-change', {detail: selectedScene.value})
  );
};

onMounted(async () => {
  // 加载场景列表（和地图页面原始逻辑一致）
  try {
    const res = await getSceneList();
    sceneList.value = res || [];

    // 【修复】添加nextTick确保DOM渲染后再设置默认值，保证筛选栏显示
    if (sceneList.value.length > 0 && sceneList.value[0].children?.length > 0) {
      await nextTick(); // 等待DOM渲染完成
      selectedScene.value = [
        sceneList.value[0].value,
        sceneList.value[0].children[0].value
      ];
      handleSceneChange(); // 初始化触发一次事件
    }
  } catch (error) {
    ElMessage.error(`加载场景列表失败：${error.message}`);
  }

  // 初始化激活第一个有效导航（全局态势总览），避免默认选中应用场景导航
  if (!userStore.headerTopActive || userStore.headerTopActive === 'nav') {
    userStore.headerTopActive = filteredLeftNavList.value[0].tag;
  }
});

// 全屏逻辑（原始逻辑）
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
    left: 3vw;
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

  // 筛选栏样式
  .scene-filter-bar {
    height: 40px;
    display: flex;
    align-items: center;
    gap: 0.5vw;
    width: 100%;
  }
}
</style>
