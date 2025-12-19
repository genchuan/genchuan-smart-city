<template>
  <div class="map-container">
    <!-- 地图容器 -->
    <div :id="idName" class="map-common-css"></div>

    <!-- 图例 -->
    <div class="legend">
      <div class="legend-items">
        <div class="legend-item">
          <img :src="markerGreen" class="legend-icon" alt="正常" />
          <span>正常</span>
        </div>
        <div class="legend-item">
          <img :src="markerYellow" class="legend-icon" alt="维护" />
          <span>维护</span>
        </div>
        <div class="legend-item">
          <img :src="markerRed" class="legend-icon" alt="异常" />
          <span>异常</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, defineProps, ref, onUnmounted, watch} from 'vue';
import markerGreen from '@/assets/chart/images/normal.png';
import markerYellow from '@/assets/chart/images/maintain.png';
import markerRed from '@/assets/chart/images/abnormal.png';
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
const mapInitialized = ref(false);

const handleMarkerClick = (e) => {
  const {properties, position} = e.geometry;
  if (properties && position && infoWindow.value) {
    infoWindow.value.setContent(getTooltipContent(properties));
    infoWindow.value.setPosition(position);
    infoWindow.value.open();
  }
};

const handleInfoWindowClose = () => {
  if (infoWindow.value) {
    infoWindow.value.close();
  }
};

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
        geometriesData.push({
          id: `marker-${index}`,
          styleId: item.run_status ? `status-${item.run_status}` : 'default',
          position: new TMap.LatLng(item.coord_x, item.coord_y),
          properties: {
            equip_id: item.equip_id,
            equip_name: item.equip_name,
            coord_x: item.coord_x,
            coord_y: item.coord_y,
            run_status: item.run_status
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
    <div style="padding: 10px; font-size: 14px; color: #333; background: white; border: 1px solid #ccc; min-width: 260px; border-radius: 4px;">
      <div style="margin-bottom: 8px; font-weight: bold; color: #1E90FF; border-bottom: 1px solid #eee; padding-bottom: 4px; text-align: center;">交通设备信息</div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">设备ID：</span>
        <span style="${valueStyle}">${properties.equip_id || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">名称：</span>
        <span style="${valueStyle}">${properties.equip_name || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">坐标：</span>
        <span style="${valueStyle}">(${properties.coord_x.toFixed(6)}, ${properties.coord_y.toFixed(6)})</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">状态：</span>
        <span style="${valueStyle}; color: ${
    properties.run_status === '正常' ? 'green' :
      properties.run_status === '维护' ? '#FFD700' : 'red'
  }; font-weight: 500;">
          ${properties.run_status || '未知'}
        </span>
      </div>
    </div>
  `;
};

// 标记点样式
const getMarkerStyles = () => {
  return {
    'status-正常': new TMap.MarkerStyle({
      width: 34,
      height: 34,
      anchor: {x: 15, y: 30},
      src: markerGreen
    }),
    'status-异常': new TMap.MarkerStyle({
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

const mapCallback = () => {
  const mapContainer = document.getElementById(props.idName);
  if (!mapContainer) return;

  const map = new TMap.Map(mapContainer, {
    center: new TMap.LatLng(26.793227, 117.810114),
    zoom: 10,
    mapStyleId: 'style1'
  });
  mapInstance.value = map;
  mapInitialized.value = true; // 标记地图已初始化完成

  infoWindow.value = new TMap.InfoWindow({
    map: map,
    position: new TMap.LatLng(0, 0),
    content: '',
    offset: {x: 0, y: -40},
    visible: false
  });

  infoWindow.value.on('close', handleInfoWindowClose);

  // 初始化时创建标记
  if (props.geometriesArray.length > 0) {
    createMarkers(map);
  }
};

// 监听数据变化，重新渲染标记
watch(
  () => props.geometriesArray,
  (newVal) => {
    if (mapInitialized.value && Array.isArray(newVal) && newVal.length > 0) {
      createMarkers(mapInstance.value);
    }
  },
  {deep: true} // 深度监听数组内部变化
);

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
  mapInitialized.value = false; // 重置初始化状态
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
