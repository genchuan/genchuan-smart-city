<template>
  <div class="map-container">
    <!-- 地图容器 -->
    <div :id="idName" class="map-common-css"></div>

    <!-- 图例：四种状态（景区正常/异常 + 场馆正常/异常） -->
    <div class="legend">
      <div class="legend-items">
        <!-- 景区正常 -->
        <div class="legend-item">
          <img :src="markerScenicGreen" class="legend-icon" alt="景区-正常" />
          <span>景区（正常）</span>
        </div>
        <!-- 景区异常 -->
        <div class="legend-item">
          <img :src="markerScenicRed" class="legend-icon" alt="景区-异常" />
          <span>景区（异常）</span>
        </div>
        <!-- 场馆正常 -->
        <div class="legend-item">
          <img :src="markerVenueGreen" class="legend-icon" alt="场馆-正常" />
          <span>场馆（正常）</span>
        </div>
        <!-- 场馆异常 -->
        <div class="legend-item">
          <img :src="markerVenueRed" class="legend-icon" alt="场馆-异常" />
          <span>场馆（异常）</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, defineProps, ref, onUnmounted, watch } from 'vue';
import markerScenicGreen from '@/assets/chart/images/mountain_normal.png';
import markerScenicRed from '@/assets/chart/images/mountain_abnormal.png';
import markerVenueGreen from '@/assets/chart/images/building_normal.png';
import markerVenueRed from '@/assets/chart/images/building_abnormal.png';
import markerGray from '@/assets/chart/images/marker-gray.png'; // 备用-灰色

const props = defineProps({
  idName: {
    type: String,
    default: 'chinaEcharts',
  },
  geometriesArray: { // 接收文旅资源数据（必须包含 comp_cat_name 和 run_status 字段）
    type: Array,
    default: () => []
  }
});

const mapInstance = ref(null);
const infoWindow = ref(null);
const markerLayer = ref(null); // 资源标记层（四种状态都在这里）

// 初始化地图
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

// 信息窗内容（按“：”对齐优化，保留原有状态颜色）
const getTooltipContent = (properties) => {
  // 对齐核心样式：标签固定宽度+右对齐，值占满剩余空间
  const labelStyle = 'width: 80px; text-align: right; font-weight: bold; margin-right: 6px; flex-shrink: 0;';
  const valueStyle = 'flex: 1; text-align: left;';
  const rowStyle = 'display: flex; align-items: center; margin: 6px 0;';

  // 异常位置（可选显示）
  const incidentHtml = properties.incident_x && properties.incident_y
    ? `<div style="${rowStyle}">
        <span style="${labelStyle}">异常位置：</span>
        <span style="${valueStyle}">(${properties.incident_x.toFixed(6)}, ${properties.incident_y.toFixed(6)})</span>
      </div>`
    : '';

  return `
    <div style="padding: 10px; font-size: 14px; color: #333; background: white; border: 1px solid #ccc; min-width: 280px; border-radius: 4px;">
      <div style="margin-bottom: 8px; font-weight: bold; color: #1E90FF; border-bottom: 1px solid #eee; padding-bottom: 4px; text-align: center;">文旅资源信息</div>

      <div style="${rowStyle}">
        <span style="${labelStyle}">资源类型：</span>
        <span style="${valueStyle}">${properties.comp_cat_name || '未知'}</span>
      </div>

      <div style="${rowStyle}">
        <span style="${labelStyle}">实时客流：</span>
        <span style="${valueStyle}">${properties.total_rpt_count || 0} 人</span>
      </div>

      <div style="${rowStyle}">
        <span style="${labelStyle}">设施状态：</span>
        <span style="${valueStyle}; color: ${properties.run_status === '正常' ? '#28a745' : '#dc3545'}; font-weight: 500;">
          ${properties.run_status || '未知'}
        </span>
      </div>

      <div style="${rowStyle}">
        <span style="${labelStyle}">资源坐标：</span>
        <span style="${valueStyle}">(${properties.coord_x.toFixed(6)}, ${properties.coord_y.toFixed(6)})</span>
      </div>

      ${incidentHtml}
    </div>
  `;
};

// 核心修复：定义四种状态的标记样式（景区绿/红 + 场馆绿/红）
const getMarkerStyles = () => {
  return {
    // 景区正常-绿色
    'scenic-normal': new TMap.MarkerStyle({
      width: 36,
      height: 36,
      anchor: { x: 18, y: 36 }, // 锚点在图标底部中心
      src: markerScenicGreen
    }),
    // 景区异常-红色
    'scenic-abnormal': new TMap.MarkerStyle({
      width: 36,
      height: 36,
      anchor: { x: 18, y: 36 },
      src: markerScenicRed
    }),
    // 场馆正常-绿色
    'venue-normal': new TMap.MarkerStyle({
      width: 36,
      height: 36,
      anchor: { x: 18, y: 36 },
      src: markerVenueGreen
    }),
    // 场馆异常-红色
    'venue-abnormal': new TMap.MarkerStyle({
      width: 36,
      height: 36,
      anchor: { x: 18, y: 36 },
      src: markerVenueRed
    }),
    // 备用样式（未知类型/状态）
    'default': new TMap.MarkerStyle({
      width: 36,
      height: 36,
      anchor: { x: 18, y: 36 },
      src: markerGray
    })
  };
};

// 销毁图层（避免重复渲染）
const destroyLayers = () => {
  if (markerLayer.value) {
    markerLayer.value.off('click');
    markerLayer.value.destroy();
    markerLayer.value = null;
  }
};

// 核心修复：根据数据生成四种状态的标记点
const updateLayers = (data) => {
  if (!mapInstance.value) return;

  // 先销毁旧图层
  destroyLayers();

  const geometriesData = [];
  data.forEach((item, index) => {
    // 校验坐标合法性
    if (!item || typeof item.coord_x !== 'number' || typeof item.coord_y !== 'number') return;

    // 核心逻辑：根据「资源类型 + 状态」匹配样式ID
    let styleId = 'default';
    if (item.comp_cat_name === '景区') {
      styleId = item.run_status === '正常' ? 'scenic-normal' : 'scenic-abnormal';
    } else if (item.comp_cat_name === '场馆') {
      styleId = item.run_status === '正常' ? 'venue-normal' : 'venue-abnormal';
    }

    // 收集标记点数据
    geometriesData.push({
      id: `resource-${index}`,
      styleId,
      position: new TMap.LatLng(item.coord_x, item.coord_y),
      properties: { ...item }
    });
  });

  // 渲染标记层
  if (geometriesData.length > 0) {
    markerLayer.value = new TMap.MultiMarker({
      map: mapInstance.value,
      styles: getMarkerStyles(),
      geometries: geometriesData
    });

    // 点击标记点显示信息窗
    markerLayer.value.on('click', (e) => {
      const { properties, position } = e.geometry;
      if (properties && position && infoWindow.value) {
        infoWindow.value.setContent(getTooltipContent(properties));
        infoWindow.value.setPosition(position);
        infoWindow.value.open();
      }
    });
  }
};

// 地图回调初始化
const mapCallback = () => {
  const mapContainer = document.getElementById(props.idName);
  if (!mapContainer) return;

  // 初始化地图
  const map = new TMap.Map(mapContainer, {
    center: new TMap.LatLng(26.793227, 117.810114), // 默认中心点（可根据需求调整）
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
