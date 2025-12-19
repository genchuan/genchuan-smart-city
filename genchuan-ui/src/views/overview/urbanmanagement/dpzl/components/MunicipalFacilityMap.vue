<template>
  <div class="map-container">
    <!-- 地图容器 -->
    <div :id="idName" class="map-common-css"></div>

<!--    <div class="legend">-->
<!--      <div class="legend-items">-->
<!--        <div class="legend-item">-->
<!--          <img :src="markerEmergency" class="legend-icon" alt="紧急故障" />-->
<!--          <span>紧急故障</span>-->
<!--        </div>-->
<!--        <div class="legend-item">-->
<!--          <img :src="markerNormal" class="legend-icon" alt="一般故障" />-->
<!--          <span>一般故障</span>-->
<!--        </div>-->
<!--        <div class="legend-item">-->
<!--          <img :src="markerHandled" class="legend-icon" alt="已处置" />-->
<!--          <span>已处置（完好）</span>-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->
  </div>
</template>

<script setup>
import { onMounted, defineProps, ref, onUnmounted, watch, defineExpose } from 'vue';

import markerEmergency from '@/assets/chart/images/risk-high.png'; // 紧急故障红标
import markerNormal from '@/assets/chart/images/risk-mid.png';       // 一般故障橙标
import markerHandled from '@/assets/chart/images/normal.png';     // 已处置绿标
import markerGray from '@/assets/chart/images/marker-gray.png';    // 默认灰标

import markerManholeEmergency from '@/assets/chart/images/manhole-emergency.png'; // 井盖-紧急故障
import markerManholeNormal from '@/assets/chart/images/manhole-normal.png';       // 井盖-一般故障
import markerManholeHandled from '@/assets/chart/images/manhole-handled.png';     // 井盖-已处置

import markerStreetlightEmergency from '@/assets/chart/images/streetlight-emergency.png'; // 路灯-紧急故障
import markerStreetlightNormal from '@/assets/chart/images/streetlight-normal.png';       // 路灯-一般故障
import markerStreetlightHandled from '@/assets/chart/images/streetlight-handled.png';     // 路灯-已处置

import markerPipeNetworkEmergency from '@/assets/chart/images/pipeNetwork-emergency.png'; // 管网-紧急故障
import markerPipeNetworkNormal from '@/assets/chart/images/pipeNetwork-normal.png';       // 管网-一般故障
import markerPipeNetworkHandled from '@/assets/chart/images/pipeNetwork-handled.png';     // 管网-已处置

import markerFireHydrantEmergency from '@/assets/chart/images/fireHydrant-emergency.png'; // 消防栓-紧急故障
import markerFireHydrantNormal from '@/assets/chart/images/fireHydrant-normal.png';       // 消防栓-一般故障
import markerFireHydrantHandled from '@/assets/chart/images/fireHydrant-handled.png';     // 消防栓-已处置

import markerTrashCanEmergency from '@/assets/chart/images/trashCan-emergency.png'; // 垃圾桶-紧急故障
import markerTrashCanNormal from '@/assets/chart/images/trashCan-normal.png';       // 垃圾桶-一般故障
import markerTrashCanHandled from '@/assets/chart/images/trashCan-handled.png';     // 垃圾桶-已处置

const facilityIconMap = {
  '井盖': {
    emergency: markerManholeEmergency,
    normal: markerManholeNormal,
    handled: markerManholeHandled
  },
  '路灯': {
    emergency: markerStreetlightEmergency,
    normal: markerStreetlightNormal,
    handled: markerStreetlightHandled
  },
  '管网': {
    emergency: markerPipeNetworkEmergency,
    normal: markerPipeNetworkNormal,
    handled: markerPipeNetworkHandled
  },
  '消防栓': {
    emergency: markerFireHydrantEmergency,
    normal: markerFireHydrantNormal,
    handled: markerFireHydrantHandled
  },
  '垃圾桶': {
    emergency: markerTrashCanEmergency,
    normal: markerTrashCanNormal,
    handled: markerTrashCanHandled
  }
};

const props = defineProps({
  idName: {
    type: String,
    default: 'municipalMap',
  },
  geometriesArray: {
    type: Array,
    default: () => []
  }
});

const mapInstance = ref(null);
const infoWindow = ref(null);
const markerLayer = ref(null);
const mapInitialized = ref(false);
const mapKey = ref(0); // 用于触发地图刷新

// 处理标记点击事件（联动列表定位）
const handleMarkerClick = (e) => {
  const {properties} = e.geometry;
  if (properties && infoWindow.value) {
    infoWindow.value.setContent(getTooltipContent(properties));
    infoWindow.value.setPosition(e.geometry.position);
    infoWindow.value.open();
    // 触发父组件列表定位（可通过emit实现）
    // emit('markerClick', properties);
  }
};

// 处理信息窗关闭
const handleInfoWindowClose = () => {
  if (infoWindow.value) {
    infoWindow.value.close();
  }
};

// 初始化地图
const initMap = () => {
  const callbackName = `initMap_${props.idName}_${mapKey.value}`;
  const script = document.createElement('script');
  script.src = `https://map.qq.com/api/gljs?v=1.exp&key=QTQBZ-F3RWW-JJJRV-YNPA5-ZIKDK-3SBNO&callback=${callbackName}`;
  script.async = true;

  window[callbackName] = () => {
    mapCallback();
    delete window[callbackName];
  };

  document.head.appendChild(script);
};

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
        // 获取设施类型和故障状态
        const facilityType = item.comp_type || '未知'; // 设施类型（如井盖、路灯、消防栓等）
        const faultStatus = item.comp_status || '未知'; // 故障状态（紧急故障、一般故障、完好）

        // 生成样式ID：优先按「设施类型+故障状态」，无专属图标则降级为基础故障图标
        let styleId = 'default';
        if (facilityIconMap[facilityType]) {
          // 有专属设施图标：样式ID = 设施类型-故障状态（如 消防栓-emergency）
          if (faultStatus === '紧急故障') {
            styleId = `${facilityType}-emergency`;
          } else if (faultStatus === '一般故障') {
            styleId = `${facilityType}-normal`;
          } else if (faultStatus === '完好') {
            styleId = `${facilityType}-handled`;
          }
        } else {
          // 无专属设施图标：使用基础故障状态图标
          if (faultStatus === '紧急故障') styleId = 'emergency';
          else if (faultStatus === '一般故障') styleId = 'normal';
          else if (faultStatus === '完好') styleId = 'handled';
        }

        geometriesData.push({
          id: `marker-${item.mng_comp_id || index}`,
          styleId: styleId, // 动态样式ID
          position: new TMap.LatLng(item.coord_x, item.coord_y),
          properties: {...item}
        });
      }
    });
  }

  if (geometriesData.length > 0) {
    markerLayer.value = new TMap.MultiMarker({
      map: map,
      styles: getMarkerStyles(), // 动态生成所有样式
      geometries: geometriesData
    });

    markerLayer.value.on('click', handleMarkerClick);
  }
};

// 信息窗内容（保留原有逻辑，可按需调整）
const getTooltipContent = (properties) => {
  const labelStyle = 'width: 80px; text-align: right; font-weight: bold; margin-right: 6px; flex-shrink: 0;';
  const valueStyle = 'flex: 1; text-align: left; word-break: break-all;';
  const rowStyle = 'display: flex; align-items: center; margin: 6px 0;';

  return `
    <div style="padding: 10px 12px; font-size: 14px; color: #333; background: white; border: 1px solid #ccc; min-width: 260px; border-radius: 4px;">
      <div style="margin-bottom: 8px; font-weight: bold; color: #ff4949; border-bottom: 1px solid #eee; padding-bottom: 4px; text-align: center;">市政设施故障信息</div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">设施ID：</span>
        <span style="${valueStyle}">${properties.mng_comp_id || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">名称：</span>
        <span style="${valueStyle}">${properties.comp_name || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">类型：</span>
        <span style="${valueStyle}">${properties.comp_type || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">故障等级：</span>
        <span style="${valueStyle}; color: ${properties.comp_status === '紧急故障' ? 'red' : properties.comp_status === '一般故障' ? 'orange' : 'green'}; font-weight: 500;">
          ${properties.comp_status || '未知'}
        </span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">上报时间：</span>
        <span style="${valueStyle}${properties.is_timeout ? '; color: red;' : ''}">${properties.report_time || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">处置状态：</span>
        <span style="${valueStyle}">${properties.handle_status || '未知'}</span>
      </div>
    </div>
  `;
};

const getMarkerStyles = () => {
  // 基础样式（无专属设施图标时使用）
  const baseStyles = {
    'emergency': new TMap.MarkerStyle({
      width: 34,
      height: 34,
      anchor: {x: 15, y: 30},
      src: markerEmergency
    }),
    'normal': new TMap.MarkerStyle({
      width: 34,
      height: 34,
      anchor: {x: 15, y: 30},
      src: markerNormal
    }),
    'handled': new TMap.MarkerStyle({
      width: 34,
      height: 34,
      anchor: {x: 15, y: 30},
      src: markerHandled
    }),
    'default': new TMap.MarkerStyle({
      width: 34,
      height: 34,
      anchor: {x: 15, y: 30},
      src: markerGray
    })
  };

  // 动态添加各设施类型的专属样式（自动识别消防栓/垃圾桶/井盖/路灯）
  Object.keys(facilityIconMap).forEach(facilityType => {
    const iconSet = facilityIconMap[facilityType];
    // 紧急故障样式（如 消防栓-emergency、垃圾桶-emergency）
    baseStyles[`${facilityType}-emergency`] = new TMap.MarkerStyle({
      width: 34,
      height: 34,
      anchor: {x: 15, y: 30},
      src: iconSet.emergency
    });
    // 一般故障样式（如 消防栓-normal、垃圾桶-normal）
    baseStyles[`${facilityType}-normal`] = new TMap.MarkerStyle({
      width: 34,
      height: 34,
      anchor: {x: 15, y: 30},
      src: iconSet.normal
    });
    // 已处置样式（如 消防栓-handled、垃圾桶-handled）
    baseStyles[`${facilityType}-handled`] = new TMap.MarkerStyle({
      width: 34,
      height: 34,
      anchor: {x: 15, y: 30},
      src: iconSet.handled
    });
  });

  return baseStyles;
};

// 地图回调（保留原有逻辑）
const mapCallback = () => {
  const mapContainer = document.getElementById(props.idName);
  if (!mapContainer) {
    console.error(`地图容器不存在：${props.idName}`);
    return;
  }

  const map = new TMap.Map(mapContainer, {
    center: new TMap.LatLng(26.0753, 119.3062), // 福州坐标（可根据实际需求修改）
    zoom: 15,
    mapStyleId: 'style1'
  });
  mapInstance.value = map;
  mapInitialized.value = true;

  // 初始化信息窗
  infoWindow.value = new TMap.InfoWindow({
    map: map,
    position: new TMap.LatLng(0, 0),
    content: '',
    offset: {x: 0, y: -50},
    visible: false
  });

  infoWindow.value.on('close', handleInfoWindowClose);

  // 创建标记
  if (props.geometriesArray.length > 0) {
    createMarkers(map);
  }
};

// 刷新地图方法（供父组件调用）
const refreshMap = () => {
  mapKey.value++;
  if (mapInitialized.value) {
    createMarkers(mapInstance.value);
  } else {
    initMap();
  }
};

// 监听数据变化更新标记
watch(
  () => props.geometriesArray,
  (newVal) => {
    if (mapInitialized.value && Array.isArray(newVal) && newVal.length > 0) {
      createMarkers(mapInstance.value);
    }
  },
  {deep: true}
);

// 组件挂载
onMounted(() => {
  initMap();
});

// 组件卸载
onUnmounted(() => {
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
  mapInitialized.value = false;
});

// 暴露刷新方法给父组件
defineExpose({
  refreshMap
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
  justify-content: center;
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
