<template>
  <div class="map-container">
    <div :id="idName" class="map-common-css"></div>

    <div class="legend">
      <div class="legend-items">
        <div class="legend-item">
          <img :src="markerScenicGreen" class="legend-icon" alt="景区-正常" />
          <span>景区（正常）</span>
        </div>
        <div class="legend-item">
          <img :src="markerScenicRed" class="legend-icon" alt="景区-异常" />
          <span>景区（异常）</span>
        </div>
        <div class="legend-item">
          <img :src="markerVenueGreen" class="legend-icon" alt="场馆-正常" />
          <span>场馆（正常）</span>
        </div>
        <div class="legend-item">
          <img :src="markerVenueRed" class="legend-icon" alt="场馆-异常" />
          <span>场馆（异常）</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, defineProps, ref, onUnmounted, watch} from 'vue';
import markerScenicGreen from '@/assets/chart/images/mountain_normal.png';
import markerScenicRed from '@/assets/chart/images/mountain_abnormal.png';
import markerVenueGreen from '@/assets/chart/images/building_normal.png';
import markerVenueRed from '@/assets/chart/images/building_abnormal.png';
import markerGray from '@/assets/chart/images/marker-gray.png';

const props = defineProps({
  idName: {
    type: String,
    default: 'chinaEcharts',
  },
  geometriesArray: {
    type: Array,
    default: () => []
  }
});

const mapInstance = ref(null);
const infoWindow = ref(null);
const markerLayer = ref(null);

const handleMarkerClick = (e) => {
  const {properties, position} = e.geometry;
  if (properties && position && infoWindow.value) {
    infoWindow.value.setContent(getTooltipContent(properties));
    infoWindow.value.setPosition(position);
    infoWindow.value.open();
  }
};

// 初始化地图
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

// 信息窗内容
const getTooltipContent = (properties) => {
  const labelStyle = 'width: 80px; text-align: right; font-weight: bold; margin-right: 6px; flex-shrink: 0;';
  const valueStyle = 'flex: 1; text-align: left;';
  const rowStyle = 'display: flex; align-items: center; margin: 6px 0;';

  // 异常位置（可选显示）
  const incidentHtml = properties.incidentX && properties.incidentY
    ? `<div style="${rowStyle}">
        <span style="${labelStyle}">异常位置：</span>
        <span style="${valueStyle}">(${properties.incidentX.toFixed(6)}, ${properties.incidentY.toFixed(6)})</span>
      </div>`
    : '';

  return `
    <div style="padding: 10px; font-size: 14px; color: #333; background: white; border: 1px solid #ccc; min-width: 280px; border-radius: 4px;">
      <div style="margin-bottom: 8px; font-weight: bold; color: #1E90FF; border-bottom: 1px solid #eee; padding-bottom: 4px; text-align: center;">文旅资源信息</div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">资源类型：</span>
        <span style="${valueStyle}">${properties.compCatName || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">实时客流：</span>
        <span style="${valueStyle}">${properties.totalRptCount || 0} 人</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">设施状态：</span>
        <span style="${valueStyle}; color: ${properties.runStatus === '正常' ? '#28a745' : '#dc3545'}; font-weight: 500;">
          ${properties.runStatus || '未知'}
        </span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">资源坐标：</span>
        <span style="${valueStyle}">(${properties.coordX.toFixed(6)}, ${properties.coordY.toFixed(6)})</span>
      </div>
      ${incidentHtml}
    </div>
  `;
};

const getMarkerStyles = () => {
  return {
    'scenic-normal': new TMap.MarkerStyle({
      width: 36,
      height: 36,
      anchor: {x: 18, y: 36},
      src: markerScenicGreen
    }),
    'scenic-abnormal': new TMap.MarkerStyle({
      width: 36,
      height: 36,
      anchor: {x: 18, y: 36},
      src: markerScenicRed
    }),
    'venue-normal': new TMap.MarkerStyle({
      width: 36,
      height: 36,
      anchor: {x: 18, y: 36},
      src: markerVenueGreen
    }),
    'venue-abnormal': new TMap.MarkerStyle({
      width: 36,
      height: 36,
      anchor: {x: 18, y: 36},
      src: markerVenueRed
    }),
    'default': new TMap.MarkerStyle({
      width: 36,
      height: 36,
      anchor: {x: 18, y: 36},
      src: markerGray
    })
  };
};

// 销毁图层（避免重复渲染）
const destroyLayers = () => {
  if (markerLayer.value) {
    try {
      markerLayer.value.off('click', handleMarkerClick);
      markerLayer.value.destroy();
    } catch (error) {
      console.warn('销毁文旅资源标记层失败：', error);
    }
    markerLayer.value = null;
  }
};

const updateLayers = (data) => {
  if (!mapInstance.value) return;

  // 先销毁旧图层
  destroyLayers();

  const geometriesData = [];
  data.forEach((item, index) => {
    // 校验坐标合法性
    if (!item || typeof item.coordX !== 'number' || typeof item.coordY !== 'number') return;

    // 核心逻辑：根据「资源类型 + 状态」匹配样式ID
    let styleId = 'default';
    if (item.compCatName === '景区') {
      styleId = item.runStatus === '正常' ? 'scenic-normal' : 'scenic-abnormal';
    } else if (item.compCatName === '场馆') {
      styleId = item.runStatus === '正常' ? 'venue-normal' : 'venue-abnormal';
    }

    // 收集标记点数据
    geometriesData.push({
      id: `resource-${index}`,
      styleId,
      position: new TMap.LatLng(item.coordX, item.coordY),
      properties: {...item}
    });
  });

  // 渲染标记层
  if (geometriesData.length > 0) {
    markerLayer.value = new TMap.MultiMarker({
      map: mapInstance.value,
      styles: getMarkerStyles(),
      geometries: geometriesData
    });

    // 绑定命名函数
    markerLayer.value.on('click', handleMarkerClick);
  }
};

// 地图回调初始化
const mapCallback = () => {
  const mapContainer = document.getElementById(props.idName);
  if (!mapContainer) return;

  // 初始化地图
  const map = new TMap.Map(mapContainer, {
    center: new TMap.LatLng(26.793227, 117.810114), // 默认中心点
    zoom: 10,
    mapStyleId: 'style1'
  });
  mapInstance.value = map;

  // 初始化信息窗
  infoWindow.value = new TMap.InfoWindow({
    map: map,
    position: new TMap.LatLng(0, 0),
    content: '',
    offset: {x: 0, y: -40}, // 信息窗在标记点上方
    visible: false
  });

  // 初始化渲染图层
  updateLayers(props.geometriesArray);
};

// 监听数据变化，自动更新标记点
watch(
  () => props.geometriesArray,
  (newData) => {
    if (mapInstance.value) {
      updateLayers(newData);
    }
  },
  {deep: true} // 深度监听数组内对象变化
);

// 生命周期钩子
onMounted(() => {
  initMap();
});

onUnmounted(() => {
  destroyLayers();
  if (infoWindow.value) {
    try {
      infoWindow.value.destroy();
    } catch (error) {
      console.warn('卸载时销毁文旅信息窗失败：', error);
    }
  }
  if (mapInstance.value) {
    mapInstance.value.destroy();
  }
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
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
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
