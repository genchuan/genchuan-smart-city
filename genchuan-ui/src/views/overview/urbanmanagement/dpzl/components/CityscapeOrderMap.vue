<template>
  <div class="map-container">
    <!-- 地图容器 -->
    <div :id="idName" class="map-common-css"></div>

    <!-- 图例 -->
    <div class="legend">
      <div class="legend-items">
        <div class="legend-item">
          <img :src="markerEmergency" class="legend-icon" alt="超时/未处置" />
          <span>超时/未处置</span>
        </div>
        <div class="legend-item">
          <img :src="markerNormal" class="legend-icon" alt="处置中" />
          <span>处置中</span>
        </div>
        <div class="legend-item">
          <img :src="markerHandled" class="legend-icon" alt="已办结" />
          <span>已办结</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, defineProps, ref, onUnmounted, watch, defineExpose } from 'vue';

// 图标导入
import markerEmergency from '@/assets/chart/images/risk-high.png';
import markerNormal from '@/assets/chart/images/risk-mid.png';
import markerHandled from '@/assets/chart/images/normal.png';
import markerGray from '@/assets/chart/images/marker-gray.png';

// Props 定义
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

// 响应式变量
const mapInstance = ref(null);
const infoWindow = ref(null);
const markerLayer = ref(null);
const mapInitialized = ref(false);
const mapKey = ref(0);

// 标记点击事件
const emit = defineEmits(['markerClick']);
const handleMarkerClick = (e) => {
  const {properties} = e.geometry;
  if (properties && infoWindow.value) {
    infoWindow.value.setContent(getTooltipContent(properties));
    infoWindow.value.setPosition(e.geometry.position);
    infoWindow.value.open();
    emit('markerClick', properties);
  }
};

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

// 创建地图标记
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
      // 坐标字段匹配：incident_y=经度，incident_x=纬度
      const lng = item.incident_y;
      const lat = item.incident_x;

      if (item && typeof lng === 'number' && typeof lat === 'number') {
        let styleId = 'default';
        const matterStatus = item.matter_status || '未知';

        if (matterStatus === '未处置') {
          styleId = 'emergency';
        } else if (matterStatus === '处置中') {
          styleId = 'normal';
        } else if (matterStatus === '已办结') {
          styleId = 'handled';
        }

        geometriesData.push({
          id: `marker-${item.mng_matter_id || index}`,
          styleId: styleId,
          position: new TMap.LatLng(lat, lng),
          properties: {...item}
        });
      } else {
        console.warn(`第${index}条数据坐标无效`, item);
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
    console.log('地图标记生成成功，数量：', geometriesData.length);
  } else {
    console.warn('无有效标记数据');
  }
};

// 信息窗内容
const getTooltipContent = (properties) => {
  const labelStyle = 'width: 80px; text-align: right; font-weight: bold; margin-right: 6px; flex-shrink: 0;';
  const valueStyle = 'flex: 1; text-align: left; word-break: break-all;';
  const rowStyle = 'display: flex; align-items: center; margin: 6px 0;';

  const statusColor = properties.matter_status === '未处置'
    ? 'red'
    : properties.matter_status === '处置中'
      ? 'orange'
      : 'green';

  return `
    <div style="padding: 10px 12px; font-size: 14px; color: #333; background: white; border: 1px solid #ccc; min-width: 260px; border-radius: 4px;">
      <div style="margin-bottom: 8px; font-weight: bold; color: #ff4949; border-bottom: 1px solid #eee; padding-bottom: 4px; text-align: center;">市容秩序事件信息</div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">事件ID：</span>
        <span style="${valueStyle}">${properties.mng_matter_id || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">事件类型：</span>
        <span style="${valueStyle}">${properties.matter_type || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">处置状态：</span>
        <span style="${valueStyle}; color: ${statusColor}; font-weight: 500;">
          ${properties.matter_status || '未知'}
        </span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">上报时间：</span>
        <span style="${valueStyle}${properties.is_timeout ? '; color: red;' : ''}">${properties.report_time || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">发生位置：</span>
        <span style="${valueStyle}">${properties.incident_location || '未知'}</span>
      </div>
    </div>
  `;
};

// 标记样式定义
const getMarkerStyles = () => {
  return {
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
};

// 地图初始化回调
const mapCallback = () => {
  const mapContainer = document.getElementById(props.idName);
  if (!mapContainer) {
    console.error(`地图容器不存在：${props.idName}`);
    return;
  }

  const map = new TMap.Map(mapContainer, {
    center: new TMap.LatLng(26.0753, 119.3062),
    zoom: 15,
    mapStyleId: 'style1'
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
};

// 刷新地图
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
    console.log('地图数据更新，长度：', newVal.length);
    if (mapInitialized.value && Array.isArray(newVal) && newVal.length > 0) {
      createMarkers(mapInstance.value);
    }
  },
  {deep: true}
);

// 生命周期
onMounted(() => {
  initMap();
});

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

// 暴露方法给父组件
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

/* 图例样式 */
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
  object-fit: contain;
}
</style>
