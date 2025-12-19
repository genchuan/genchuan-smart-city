<template>
  <div class="map-container">
    <div :id="idName" class="map-common-css"></div>

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
import markerLarge from '@/assets/chart/images/big-business.png';
import markerMedium from '@/assets/chart/images/mid-business.png';
import markerSmall from '@/assets/chart/images/small-business.png';
import markerDefault from '@/assets/chart/images/marker-gray.png';

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

// 标记点点击回调
const handleMarkerClick = (e) => {
  const {properties, position} = e.geometry;
  if (properties && position && infoWindow.value) {
    infoWindow.value.setContent(getTooltipContent(properties));
    infoWindow.value.setPosition(position);
    infoWindow.value.open();
  }
};

// 信息窗关闭回调
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
  const labelStyle = 'width: 100px; text-align: right; font-weight: bold; margin-right: 6px; flex-shrink: 0;';
  const valueStyle = 'flex: 1; text-align: left; word-break: break-all;';
  const rowStyle = 'display: flex; align-items: center; margin: 6px 0;';

  return `
    <div style="padding: 10px 12px; font-size: 14px; color: #333; background: white; border: 1px solid #ccc; min-width: 300px; border-radius: 4px;">
      <div style="margin-bottom: 8px; font-weight: bold; color: #1E90FF; border-bottom: 1px solid #eee; padding-bottom: 4px; text-align: center;">企业信息</div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">企业ID：</span>
        <span style="${valueStyle}">${properties.ent_id || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">企业名称：</span>
        <span style="${valueStyle}">${properties.ent_name || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">企业规模：</span>
        <span style="${valueStyle}; font-weight: 500;">${properties.scale || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">所属行业：</span>
        <span style="${valueStyle}">${properties.industry || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">成立时间：</span>
        <span style="${valueStyle}">${properties.establish_time || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">详细地址：</span>
        <span style="${valueStyle}">${properties.address || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">坐标：</span>
        <span style="${valueStyle}">(${properties.coord_x.toFixed(6)}, ${properties.coord_y.toFixed(6)})</span>
      </div>
    </div>
  `;
};

// 标记点样式
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

// 销毁现有标记层
const destroyMarkerLayer = () => {
  if (markerLayer.value) {
    try {
      // 传入命名回调函数
      markerLayer.value.off('click', handleMarkerClick);
      markerLayer.value.destroy();
    } catch (error) {
      console.warn('销毁企业标记层失败：', error);
    }
    markerLayer.value = null;
  }
};

// 根据数据创建标记层
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

  // 绑定命名回调函数
  markerLayer.value.on('click', handleMarkerClick);
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

  // 绑定命名回调函数
  infoWindow.value.on('close', handleInfoWindowClose);

  // 初始化时创建标记层
  if (props.geometriesArray.length) {
    createMarkerLayer(props.geometriesArray);
  }
};

// 监听数据变化，更新标记层
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
    try {
      markerLayer.value.off('click', handleMarkerClick);
      markerLayer.value.destroy();
    } catch (error) {
      console.warn('卸载时销毁企业标记层失败：', error);
    }
  }
  if (infoWindow.value) {
    try {
      infoWindow.value.off('close', handleInfoWindowClose);
      infoWindow.value.destroy();
    } catch (error) {
      console.warn('卸载时销毁企业信息窗失败：', error);
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
