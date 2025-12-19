<template>
  <div class="page-container">
    <div class="mainbox">
      <div class="left">
        <div class="left_top">核心指标看板</div>
        <div class="left_bottom">核心对象分布视图</div>
      </div>
      <div class="middle">
        <div class="middle_top" id="main-map-wrapper" style="position: relative; overflow: visible !important;">
          <MapTemplate
            v-if="sceneKey && mapConfig.orbitAnimation"
            idName="map-container"
            :sceneKey="sceneKey"
            :previewMode="true"
            :previewConfig="mapConfig"
          />
        </div>

        <div class="middle_bottom">全局态势趋势分析</div>
      </div>
      <div class="right">
        <div class="right_top">行业特色态势聚合</div>
        <div class="right_bottom">核心要素运行监测</div>
      </div>
    </div>
    <div class="footer-box">帮助文档、异常反馈入口</div>
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick, onUnmounted } from 'vue';
import { ElMessage } from 'element-plus';
import MapTemplate from '@/views/overview/dpzl/components/MapTemplate.vue';
import {
  getSceneList,
  getSceneKeyByCascader,
  MAP_DEFAULT_CONFIG
} from '@/api/overview/dpzl/map.js';
import { getConfig } from '@/utils/mapConfigStorage.js';

// ===================== 核心数据（保留原始逻辑） =====================
const sceneList = ref([]);
const selectedScene = ref([]);
const sceneKey = ref('');
const mapConfig = ref({ ...MAP_DEFAULT_CONFIG });

// ===================== 初始化（仅新增事件监听） =====================
onMounted(async () => {
  // 加载场景列表（保留原始逻辑）
  try {
    const res = await getSceneList();
    sceneList.value = res || [];
  } catch (error) {
    ElMessage.error(`加载场景列表失败：${error.message}`);
  }

  // 监听顶部导航筛选栏的场景切换事件
  window.addEventListener('scene-change', handleSceneChangeFromTop);

});

// ===================== 接收顶部筛选栏的场景值 =====================
const handleSceneChangeFromTop = (e) => {
  selectedScene.value = e.detail; // 接收顶部筛选栏选中的场景值
  handleSceneChange(); // 执行原有场景切换逻辑，更新当前模块数据
};

// ===================== 场景切换 =====================
const handleSceneChange = async () => {
  if (!selectedScene.value || selectedScene.value.length < 2) {
    sceneKey.value = '';
    mapConfig.value = { ...MAP_DEFAULT_CONFIG };
    ElMessage.warning('请选择完整的应用场景（需选到二级分类）');
    return;
  }

  sceneKey.value = getSceneKeyByCascader(selectedScene.value);
  try {
    await nextTick();
    const config = await getConfig(sceneKey.value, MAP_DEFAULT_CONFIG);
    // 核心1：简化配置合并，orbitAnimation自动复用全局默认值（getConfig已兜底）
    mapConfig.value = {
      ...MAP_DEFAULT_CONFIG,
      ...config,
      orbitAnimation: {
        ...MAP_DEFAULT_CONFIG.orbitAnimation, // 复用全局默认值
        ...config.orbitAnimation             // 覆盖自定义配置
      }
    };
    ElMessage.success(`已加载【${getSceneName(selectedScene.value)}】地图数据`);
  } catch (error) {
    mapConfig.value = { ...MAP_DEFAULT_CONFIG };
    ElMessage.info(`使用默认配置加载【${getSceneName(selectedScene.value)}】地图`);
  }
};

// ===================== 辅助函数 =====================
const getSceneName = (cascaderValue) => {
  if (!cascaderValue || cascaderValue.length < 2) return '未知场景';
  const parent = sceneList.value.find(item => item.value === cascaderValue[0]);
  if (!parent || !parent.children) return '未知场景';
  const child = parent.children.find(item => item.value === cascaderValue[1]);
  return child ? `${parent.label}/${child.label}` : `${parent.label}/未知子场景`;
};

// ===================== 新增：组件卸载移除事件监听（防止内存泄漏） =====================
onUnmounted(() => {
  window.removeEventListener('scene-change', handleSceneChangeFromTop);
});
</script>

<style lang="scss" scoped>
@import url('./common-styles.scss');

// 页面容器
.page-container {
  width: 100%;
  height: 100vh;
  overflow-x: auto;
  overflow-y: hidden;
  background: url("@/assets/chart/images/bg.jpg") no-repeat;
  background-size: 100% 100%;
  color: #fff;
  padding: 0 1vw;
  box-sizing: border-box;
}

.mainbox {
  display: flex;
  margin: 0 auto;
  height: 84vh;
  box-sizing: border-box;
  gap: 0.6vw;
  width: 100%;
}

.left {
  flex: 1;
  border: solid black 2px;
  display: flex;
  flex-direction: column;
  gap: 2%;
}

.left_top {
  height: 40%;
  border: solid black 2px;
}

.left_bottom {
  height: 58%;
  border: solid black 2px;
}

.middle {
  flex: 3;
  border: solid black 2px;
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
  height: 100%;
  min-height: 0;
}

.middle_top {
  flex: 1;
  margin-bottom: 1%;
  border: solid black 2px;
  overflow: visible !important;
  position: relative;
  height: 69%;
}

.middle_bottom {
  height: 30%;
  border: solid black 2px;
}

.right {
  flex: 1;
  border: solid black 2px;
  display: flex;
  flex-direction: column;
  gap: 2%;
}

.right_top {
  height: 40%;
  border: solid black 2px;
}

.right_bottom {
  height: 58%;
  border: solid black 2px;
}

.footer-box {
  height: 4vh;
  border: solid black 2px;
  box-sizing: border-box;
}

:deep(#main-map-wrapper #map-container) {
  width: 100%;
  height: 100%;
  min-height: 380px;
}
</style>
