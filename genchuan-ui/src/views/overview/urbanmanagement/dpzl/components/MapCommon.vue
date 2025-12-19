<template>
  <div class="map-container">
    <!-- 地图容器 -->
    <div :id="idName" class="map-common-css"></div>

    <!-- 绕点环绕控制面板 -->
    <div class="orbit-control-panel">
      <button @click="toggleOrbitAnimation" class="control-btn">
        {{ orbitStatus.playing ? '暂停环绕' : '开始环绕' }}
      </button>
    </div>

    <div class="legend">
      <div class="legend-items">
        <div class="legend-item">
          <img :src="markerGreen" class="legend-icon" alt="完好" />
          <span>完好</span>
        </div>
        <div class="legend-item">
          <img :src="markerYellow" class="legend-icon" alt="维护" />
          <span>维护</span>
        </div>
        <div class="legend-item">
          <img :src="markerRed" class="legend-icon" alt="破损" />
          <span>破损</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, defineProps, ref, onUnmounted, watch} from 'vue';
import markerGreen from '@/assets/chart/images/good.png';
import markerYellow from '@/assets/chart/images/maintain.png';
import markerRed from '@/assets/chart/images/bad.png';
import markerGray from '@/assets/chart/images/marker-gray.png';

const props = defineProps({
  idName: {
    type: String,
    default: 'chinaEcharts',
  },
  geometriesArray: {
    type: Array,
    default: () => []
  },
  orbitConfig: {
    type: Object,
    default: () => ({
      center: {lat: 26.793227, lng: 117.810114},
      rotateSpeed: 0.2,
      pitch: 40,
      zoom: 12,
      loop: true
    })
  }
});

// 核心数据
const mapInstance = ref(null);
const infoWindow = ref(null);
const markerLayer = ref(null);
const mapInitialized = ref(false);

// 绕点动画状态：初始播放中，按钮默认显示「暂停环绕」
const orbitStatus = ref({
  playing: true,
  currentRotation: 0,
  animationFrameId: null,
  isInited: false
});

// 标记点点击事件
const handleMarkerClick = (e) => {
  const {properties, position} = e.geometry;
  if (properties && position && infoWindow.value) {
    infoWindow.value.setContent(getTooltipContent(properties));
    infoWindow.value.setPosition(position);
    infoWindow.value.open();
  }
};

// 信息窗关闭事件
const handleInfoWindowClose = () => {
  if (infoWindow.value) {
    infoWindow.value.close();
  }
};

// 初始化地图加载脚本
const initMap = () => {
  const callbackName = `initMap_${props.idName}`;
  const script = document.createElement('script');
  script.src = `https://map.qq.com/api/gljs?v=1.exp&key=QTQBZ-F3RWW-JJJRV-YNPA5-ZIKDK-3SBNO&callback=${callbackName}`;
  script.async = true;

  window[callbackName] = () => {
    mapCallback();
    delete window[callbackName];
  };

  document.head.appendChild(script);
};

// 创建标记点
const createMarkers = (map) => {
  if (markerLayer.value) {
    try {
      markerLayer.value.off('click', handleMarkerClick);
      markerLayer.value.destroy();
    } catch (error) {
      console.warn('销毁标记层失败：', error);
    }
    markerLayer.value = null;
  }

  const geometriesData = [];
  if (Array.isArray(props.geometriesArray)) {
    props.geometriesArray.forEach((item, index) => {
      if (item && typeof item.coord_x === 'number' && typeof item.coord_y === 'number') {
        const styleId = item.comp_status ? `status-${item.comp_status}` : 'default';
        geometriesData.push({
          id: `marker-${index}`,
          styleId,
          position: new TMap.LatLng(item.coord_x, item.coord_y),
          properties: {
            mng_comp_id: item.mng_comp_id,
            comp_name: item.comp_name,
            coord_x: item.coord_x,
            coord_y: item.coord_y,
            comp_status: item.comp_status
          }
        });
      }
    });
  }

  if (geometriesData.length > 0) {
    markerLayer.value = new TMap.MultiMarker({
      map: map,
      styles: getMarkerStyles(),
      geometries: geometriesData
    });

    markerLayer.value.on('click', handleMarkerClick);
  }
};

// 信息窗内容
const getTooltipContent = (properties) => {
  const labelStyle = 'width: 80px; text-align: right; font-weight: bold; margin-right: 6px; flex-shrink: 0;';
  const valueStyle = 'flex: 1; text-align: left; word-break: break-all;';
  const rowStyle = 'display: flex; align-items: center; margin: 6px 0;';

  return `
    <div style="padding: 10px 12px; font-size: 14px; color: #333; background: white; border: 1px solid #ccc; min-width: 260px; border-radius: 4px;">
      <div style="margin-bottom: 8px; font-weight: bold; color: #1E90FF; border-bottom: 1px solid #eee; padding-bottom: 4px; text-align: center;">管理部件信息</div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">部件ID：</span>
        <span style="${valueStyle}">${properties.mng_comp_id || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">名称：</span>
        <span style="${valueStyle}">${properties.comp_name || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">位置坐标：</span>
        <span style="${valueStyle}">(${properties.coord_x.toFixed(6)}, ${properties.coord_y.toFixed(6)})</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">状态：</span>
        <span style="${valueStyle}; color: ${
    properties.comp_status === '完好' ? 'green' :
      properties.comp_status === '维护' ? '#FFD700' : 'red'
  }; font-weight: 500;">
          ${properties.comp_status || '未知'}
        </span>
      </div>
    </div>
  `;
};

// 标记点样式
const getMarkerStyles = () => {
  return {
    'status-完好': new TMap.MarkerStyle({
      width: 34,
      height: 34,
      anchor: {x: 15, y: 30},
      src: markerGreen
    }),
    'status-破损': new TMap.MarkerStyle({
      width: 34,
      height: 34,
      anchor: {x: 15, y: 30},
      src: markerRed
    }),
    'status-维护': new TMap.MarkerStyle({
      width: 34,
      height: 34,
      anchor: {x: 15, y: 30},
      src: markerYellow
    }),
    'default': new TMap.MarkerStyle({
      width: 34,
      height: 34,
      anchor: {x: 15, y: 30},
      src: markerGray
    })
  };
};

// 手动绕点旋转动画逻辑
const orbitLoop = () => {
  if (!mapInstance.value || !orbitStatus.value.playing) return;

  const {center, rotateSpeed, loop} = props.orbitConfig;
  const orbitCenter = new TMap.LatLng(center.lat, center.lng);

  let newRotation = orbitStatus.value.currentRotation + rotateSpeed;
  if (newRotation >= 360) {
    if (!loop) {
      stopOrbitAnimation();
      return;
    }
    newRotation = 0;
  }

  mapInstance.value.setCenter(orbitCenter);
  mapInstance.value.setRotation(newRotation);

  orbitStatus.value.currentRotation = newRotation;
  orbitStatus.value.animationFrameId = requestAnimationFrame(orbitLoop);
};

// 启动绕点动画
const startOrbitAnimation = () => {
  if (orbitStatus.value.isInited || !mapInstance.value) return;

  const {center, pitch, zoom} = props.orbitConfig;
  const orbitCenter = new TMap.LatLng(center.lat, center.lng);

  mapInstance.value.setZoom(zoom);
  mapInstance.value.setPitch(pitch);
  mapInstance.value.setCenter(orbitCenter);
  mapInstance.value.setRotation(orbitStatus.value.currentRotation || 0);

  orbitStatus.value.isInited = true;
  orbitLoop();

  console.log('绕点动画已启动，中心点：', center, '初始角度：', orbitStatus.value.currentRotation);
};

// 停止绕点动画
const stopOrbitAnimation = () => {
  orbitStatus.value.playing = false;
  orbitStatus.value.isInited = false;
  if (orbitStatus.value.animationFrameId) {
    cancelAnimationFrame(orbitStatus.value.animationFrameId);
    orbitStatus.value.animationFrameId = null;
  }
  console.log('绕点动画已停止，当前角度：', orbitStatus.value.currentRotation);
};

// 切换播放/暂停
const toggleOrbitAnimation = () => {
  if (orbitStatus.value.playing) {
    stopOrbitAnimation();
  } else {
    orbitStatus.value.playing = true;
    startOrbitAnimation();
  }
};

// 地图初始化回调
const mapCallback = () => {
  const mapContainer = document.getElementById(props.idName);
  if (!mapContainer) {
    console.error(`地图容器不存在：${props.idName}`);
    return;
  }

  const map = new TMap.Map(mapContainer, {
    center: new TMap.LatLng(26.793227, 117.810114),
    zoom: 10,
    mapStyleId: 'style1',
    enablePitch: true,
    enableRotate: true,
    pitch: 0,
    rotation: 0
  });
  mapInstance.value = map;
  mapInitialized.value = true;

  infoWindow.value = new TMap.InfoWindow({
    map: map,
    position: new TMap.LatLng(0, 0),
    content: '',
    offset: {x: 0, y: -50},
    visible: false
  });

  infoWindow.value.on('close', handleInfoWindowClose);

  if (props.geometriesArray.length > 0) {
    createMarkers(map);
  }

  startOrbitAnimation();
  console.log('地图初始化完成，3D旋转已开启：', map.getOptions().enableRotate);
};

// 监听标记点数据变化
watch(
  () => props.geometriesArray,
  (newVal) => {
    if (mapInitialized.value && Array.isArray(newVal) && newVal.length > 0) {
      createMarkers(mapInstance.value);
    }
  },
  {deep: true}
);

// 监听绕点配置变化
watch(
  () => props.orbitConfig,
  () => {
    if (orbitStatus.value.playing) {
      stopOrbitAnimation();
      orbitStatus.value.isInited = false;
      startOrbitAnimation();
    }
  },
  {deep: true}
);

// 挂载初始化
onMounted(() => {
  initMap();
});

// 卸载清理
onUnmounted(() => {
  stopOrbitAnimation();

  if (markerLayer.value) {
    try {
      markerLayer.value.off('click', handleMarkerClick);
      markerLayer.value.destroy();
    } catch (error) {
      console.warn('卸载时销毁标记层失败：', error);
    }
  }

  if (infoWindow.value) {
    try {
      infoWindow.value.off('close', handleInfoWindowClose);
      infoWindow.value.destroy();
    } catch (error) {
      console.warn('卸载时销毁信息窗失败：', error);
    }
  }

  if (mapInstance.value) {
    mapInstance.value.destroy();
  }

  orbitStatus.value = {
    playing: true,
    currentRotation: 0,
    animationFrameId: null,
    isInited: false
  };
  mapInitialized.value = false;
});
</script>

<style scoped>
.map-container {
  position: relative;
  width: 100%;
  height: 100%;
}

.map-common-css {
  width: 100%;
  height: 98%;
  margin: 0 auto;
  border-radius: 8px;
  overflow: hidden;
}

.orbit-control-panel {
  position: absolute;
  top: 5px;
  left: 5px;
  z-index: 1000;
}

.control-btn {
  border: none;
  background: #409eff;
  color: white;
  padding: 6px 12px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  margin-bottom: 8px;
}

.control-btn:hover {
  background: #66b1ff;
}

.legend {
  position: absolute;
  background: rgba(0, 0, 0, 0.5);
  box-sizing: border-box;
  flex-direction: column;
  width: 100%;
  height: auto;
  bottom: 1vh;
  left: 0;
  display: flex;
  flex-wrap: wrap;
  padding: 0.5vw;
  align-items: flex-end;
}

.legend-items {
  display: flex;
  flex-direction: row;
  gap: 1vw;
  align-items: center;
}

.legend-item {
  display: flex;
  align-items: center;
  font-size: 0.8vw;
  color: #fff;
}

.legend-icon {
  width: 1.2vw;
  height: 2vh;
  margin-right: 0.2vw;
}
</style>
