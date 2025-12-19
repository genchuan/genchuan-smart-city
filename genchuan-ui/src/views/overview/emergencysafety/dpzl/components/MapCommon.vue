<template>
  <div class="map-container">
    <div :id="idName" class="map-common-css"></div>

    <div class="legend">
      <div class="legend-items">
        <div class="legend-item">
          <img :src="markerRed" class="legend-icon" alt="高风险" />
          <span>高风险</span>
        </div>
        <div class="legend-item">
          <img :src="markerBlue" class="legend-icon" alt="中风险" />
          <span>中风险</span>
        </div>
        <div class="legend-item">
          <img :src="markerGray" class="legend-icon" alt="低风险" />
          <span>低风险</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, defineProps, ref, onUnmounted, watch} from 'vue';
import {dateFormatter} from '@/utils/formatTime'
import markerGreen from '@/assets/chart/images/marker-green.png';
import markerRed from '@/assets/chart/images/risk-high.png';
import markerGray from '@/assets/chart/images/risk-low.png';
import markerBlue from '@/assets/chart/images/risk-mid.png';

const props = defineProps({
  idName: {
    type: String,
    default: 'chinaEcharts',
  },
  geometriesArray: { // 接收风险分布数据
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

// 信息窗内容
const getTooltipContent = (properties) => {
  const labelStyle = 'width: 90px; text-align: right; font-weight: bold; margin-right: 6px; flex-shrink: 0;';
  const valueStyle = 'flex: 1; text-align: left; word-break: break-all;';
  const rowStyle = 'display: flex; align-items: center; margin: 6px 0;';

  return `
    <div style="padding: 10px; font-size: 14px; color: #333; background: white; border: 1px solid #ccc; min-width: 300px; border-radius: 4px;">
      <div style="margin-bottom: 8px; font-weight: bold; color: #1E90FF; border-bottom: 1px solid #eee; padding-bottom: 4px; text-align: center;">风险隐患信息</div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">隐患ID：</span>
        <span style="${valueStyle}">${properties.hazardId || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">风险等级：</span>
        <span style="${valueStyle}; color: ${
    properties.riskLevel === '高' ? 'red' :
      properties.riskLevel === '中' ? '#FFD700' : 'blue'
  }; font-weight: 500;">
          ${properties.riskLevel || '未知'}
        </span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">风险类型：</span>
        <span style="${valueStyle}">${properties.hazardType || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">所在网格：</span>
        <span style="${valueStyle}">${properties.gridName || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">所在区域：</span>
        <span style="${valueStyle}">${properties.regionName || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">发现时间：</span>
        <span style="${valueStyle}">${dateFormatter(null, null, properties.discoverTime) || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">坐标：</span>
        <span style="${valueStyle}">(${properties.coordX.toFixed(6)}, ${properties.coordY.toFixed(6)})</span>
      </div>
    </div>
  `;
};

// 标记点样式
const getMarkerStyles = () => {
  return {
    'level-高': new TMap.MarkerStyle({
      width: 34,
      height: 34,
      anchor: {x: 15, y: 30},
      src: markerRed
    }),
    'level-中': new TMap.MarkerStyle({
      width: 34,
      height: 34,
      anchor: {x: 15, y: 30},
      src: markerBlue
    }),
    'level-低': new TMap.MarkerStyle({
      width: 34,
      height: 34,
      anchor: {x: 15, y: 30},
      src: markerGray
    }),
    'default': new TMap.MarkerStyle({
      width: 34,
      height: 34,
      anchor: {x: 15, y: 30},
      src: markerGreen
    })
  };
};

// 创建/更新标注层：基于风险等级（riskLevel）绑定样式
const createMarkerLayer = (data) => {
  // 先销毁旧标注层（避免重复）
  if (markerLayer.value) {
    try {
      // 传入与on绑定的命名函数
      markerLayer.value.off('click', handleMarkerClick);
      markerLayer.value.destroy();
    } catch (error) {
      console.warn('销毁风险标记层失败：', error);
    }
    markerLayer.value = null;
  }

  if (!mapInstance.value || !data.length) return;

  // 处理风险数据（基于风险等级匹配样式）
  const geometriesData = [];
  data.forEach((item, index) => {
    if (item && typeof item.coordX === 'number' && typeof item.coordY === 'number') {
      // 根据风险等级（riskLevel）设置样式ID
      const styleId = item.riskLevel ? `level-${item.riskLevel}` : 'default';
      geometriesData.push({
        id: `risk-${index}`,
        styleId: styleId,
        position: new TMap.LatLng(item.coordX, item.coordY),
        properties: {
          hazardId: item.hazardId,
          riskLevel: item.riskLevel,
          hazardType: item.hazardType,
          gridName: item.gridName,
          regionName: item.regionName,
          discoverTime: item.discoverTime,
          coordX: item.coordX,
          coordY: item.coordY
        }
      });
    }
  });

  if (geometriesData.length > 0) {
    markerLayer.value = new TMap.MultiMarker({
      map: mapInstance.value,
      styles: getMarkerStyles(),
      geometries: geometriesData
    });

    markerLayer.value.on('click', handleMarkerClick);
  }
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

  infoWindow.value = new TMap.InfoWindow({
    map: map,
    position: new TMap.LatLng(0, 0),
    content: '',
    offset: {x: 0, y: -40},
    visible: false
  });

  infoWindow.value.on('close', handleInfoWindowClose);

  createMarkerLayer(props.geometriesArray);
};

// 监听数据变化，更新标注层
watch(
  () => props.geometriesArray,
  (newData) => {
    if (mapInstance.value) { // 确保地图已初始化
      createMarkerLayer(newData);
    }
  },
  {deep: true} // 深度监听数组内容变化
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
      console.warn('卸载时销毁风险标记层失败：', error);
    }
  }
  if (infoWindow.value) {
    try {
      infoWindow.value.off('close', handleInfoWindowClose);
      infoWindow.value.destroy();
    } catch (error) {
      console.warn('卸载时销毁风险信息窗失败：', error);
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
  height: 92%;
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
  bottom: 4vh;
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
