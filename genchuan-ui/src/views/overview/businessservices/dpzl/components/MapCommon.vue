<template>
  <div class="map-container">
    <!-- 地图容器 -->
    <div :id="idName" class="map-common-css"></div>

    <!-- 图例：区分企业规模（大型/中型/小型） -->
    <div class="legend">
      <div class="legend-items">
        <div class="legend-item">
          <img :src="markerLarge" class="legend-icon" alt="大型企业" />
          <span>大型企业</span>
        </div>
        <div class="legend-item">
          <img :src="markerMedium" class="legend-icon" alt="中型企业" />
          <span>中型企业</span>
        </div>
        <div class="legend-item">
          <img :src="markerSmall" class="legend-icon" alt="小型企业" />
          <span>小型企业</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, defineProps, ref, onUnmounted, watch } from 'vue';
// 导入企业图标：按规模区分（大型/中型/小型）
import markerLarge from '@/assets/chart/images/marker-red.png'; // 大型企业图标
import markerMedium from '@/assets/chart/images/marker-blue.png'; // 中型企业图标
import markerSmall from '@/assets/chart/images/marker-green.png'; // 小型企业图标
import markerDefault from '@/assets/chart/images/marker-gray.png'; // 默认图标

const props = defineProps({
  idName: {
    type: String,
    default: 'enterpriseMap',
  },
  geometriesArray: { // 接收企业分布数据
    type: Array,
    default: () => []
  }
});

const mapInstance = ref(null);
const infoWindow = ref(null);
const markerLayer = ref(null);

const initMap = () => {
  const callbackName = `initMap_${props.idName}`;
  const script = document.createElement('script');
  script.src = `https://map.qq.com/api/gljs?v=1.exp&key=OHCBZ-7BPC3-J7E3H-OA62K-Y3ZFZ-JQBPD&callback=${callbackName}`;
  script.async = true;

  window[callbackName] = () => {
    mapCallback();
    delete window[callbackName];
  };

  document.head.appendChild(script);
};

// 信息窗内容：企业详细信息（ID/名称/规模/行业等）
const getTooltipContent = (properties) => {
  return `
    <div style="padding: 10px; font-size: 14px; color: #333; background: white; border: 1px solid #ccc; min-width: 280px;">
      <div style="margin-bottom: 6px; font-weight: bold; color: #1E90FF;">企业信息</div>
      <div><strong>企业ID：</strong>${properties.ent_id || '未知'}</div> <!-- 企业ID -->
      <div><strong>企业名称：</strong>${properties.ent_name || '未知'}</div> <!-- 企业名称 -->
      <div><strong>企业规模：</strong>${properties.scale || '未知'}</div> <!-- 规模（大/中/小） -->
      <div><strong>所属行业：</strong>${properties.industry || '未知'}</div> <!-- 行业类型 -->
      <div><strong>成立时间：</strong>${properties.establish_time || '未知'}</div> <!-- 成立时间 -->
      <div><strong>详细地址：</strong>${properties.address || '未知'}</div> <!-- 地址 -->
      <div><strong>坐标：</strong>(${properties.coord_x.toFixed(6)}, ${properties.coord_y.toFixed(6)})</div> <!-- 坐标 -->
    </div>
  `;
};

// 标记点样式：按企业规模区分（大型尺寸最大，中型次之，小型最小）
const getMarkerStyles = () => {
  return {
    'scale-大型': new TMap.MarkerStyle({
      width: 40, // 大型企业图标尺寸最大
      height: 40,
      anchor: {x: 20, y: 40}, // 锚点适配尺寸
      src: markerLarge
    }),
    'scale-中型': new TMap.MarkerStyle({
      width: 34, // 中型企业图标尺寸中等
      height: 34,
      anchor: {x: 17, y: 34},
      src: markerMedium
    }),
    'scale-小型': new TMap.MarkerStyle({
      width: 28, // 小型企业图标尺寸最小
      height: 28,
      anchor: {x: 14, y: 28},
      src: markerSmall
    }),
    'default': new TMap.MarkerStyle({ // 默认样式
      width: 34,
      height: 34,
      anchor: {x: 17, y: 34},
      src: markerDefault
    })
  };
};

// 新增：销毁现有标记层
const destroyMarkerLayer = () => {
  if (markerLayer.value) {
    markerLayer.value.off('click');
    markerLayer.value.destroy();
    markerLayer.value = null;
  }
};

// 新增：根据数据创建标记层
const createMarkerLayer = (data) => {
  if (!mapInstance.value || !data.length) return;

  const geometriesData = [];
  data.forEach((item, index) => {
    if (item && typeof item.coord_x === 'number' && typeof item.coord_y === 'number') {
      const styleId = item.scale ? `scale-${item.scale}` : 'default';
      geometriesData.push({
        id: `enterprise-${index}`,
        styleId,
        position: new TMap.LatLng(item.coord_x, item.coord_y),
        properties: { ...item }
      });
    }
  });

  markerLayer.value = new TMap.MultiMarker({
    map: mapInstance.value,
    styles: getMarkerStyles(),
    geometries: geometriesData
  });

  markerLayer.value.on('click', (e) => {
    const {properties, position} = e.geometry;
    if (properties && position && infoWindow.value) {
      infoWindow.value.setContent(getTooltipContent(properties));
      infoWindow.value.setPosition(position);
      infoWindow.value.open();
    }
  });
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
    offset: {x: 0, y: -90},
    visible: false
  });

  infoWindow.value.on('close', () => infoWindow.value.close());

  // 初始化时创建标记层
  if (props.geometriesArray.length) {
    createMarkerLayer(props.geometriesArray);
  }
};

// 新增：监听数据变化，更新标记层
watch(
  () => props.geometriesArray,
  (newData) => {
    if (mapInstance.value) {
      destroyMarkerLayer();
      createMarkerLayer(newData);
    }
  },
  { deep: true }
);

onMounted(() => {
  initMap();
});

onUnmounted(() => {
  if (markerLayer.value) {
    markerLayer.value.off('click');
    markerLayer.value.destroy();
  }
  if (infoWindow.value) {
    infoWindow.value.off('close');
    infoWindow.value.destroy();
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
  bottom: 30px;
  left: 0;
  display: flex;
  flex-wrap: wrap;
  gap: 15px;
  padding: 1%;
  align-items: flex-end;
}

.legend-items {
  display: flex;
  flex-direction: row;
  gap: 15px;
  align-items: center;
}

.legend-item {
  display: flex;
  align-items: center;
  font-size: 13px;
  color: #666;
}

.legend-icon {
  /* 图例图标尺寸统一，避免视觉混乱 */
  width: 20px;
  height: 20px;
  margin-right: 6px;
}
</style>
