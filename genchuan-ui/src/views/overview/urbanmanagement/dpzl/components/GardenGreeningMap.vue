<template>
  <div class="map-container">
    <!-- 地图容器 -->
    <div :id="idName" class="map-common-css"></div>

    <!-- 病害波纹动画容器 -->
    <div ref="diseaseWaveContainer" class="disease-wave-container"></div>

    <!-- 仅保留4个图标图例项，样式和参考代码一致 -->
    <div class="legend">
      <div class="legend-group">
        <div class="legend-items">
          <div class="legend-item">
            <img :src="markerTree" class="legend-icon" alt="树木" />
            <span>树木</span>
          </div>
          <div class="legend-item">
            <img :src="markerGreenSpace" class="legend-icon" alt="绿地" />
            <span>绿地</span>
          </div>
          <div class="legend-item">
            <img :src="markerFlowerBed" class="legend-icon" alt="花坛" />
            <span>花坛</span>
          </div>
          <div class="legend-item">
            <img :src="markerDisease" class="legend-icon" alt="病害点位" />
            <span>病害点位</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// 【脚本部分：和你提供的原代码一字不差，无任何修改】
import {onMounted, defineProps, ref, onUnmounted, watch, defineExpose} from 'vue';

// 绿化类型图标
import markerTree from '@/assets/chart/images/tree.png';         // 树木-绿色
import markerGreenSpace from '@/assets/chart/images/greenspace.png'; // 绿地-深绿
import markerFlowerBed from '@/assets/chart/images/flowerbed.png'; // 花坛-紫色
import markerDisease from '@/assets/chart/images/disease.png';   // 病害-红色感叹号

const gardenIconMap = {
  '树木': markerTree,
  '绿地': markerGreenSpace,
  '花坛': markerFlowerBed
};

const props = defineProps({
  idName: {
    type: String,
    default: 'gardenMap',
  },
  geometriesArray: {
    type: Array,
    default: () => []
  },
  // 新增病害点位（用于触发波纹动画）
  newDiseasePoints: {
    type: Array,
    default: () => []
  }
});

const mapInstance = ref(null);
const infoWindow = ref(null);
const markerLayer = ref(null);
const mapInitialized = ref(false);
const mapKey = ref(0);
const diseaseWaveContainer = ref(null);

// 处理标记点击事件
const handleMarkerClick = (e) => {
  const {properties} = e.geometry;
  if (properties && infoWindow.value) {
    infoWindow.value.setContent(getTooltipContent(properties));
    infoWindow.value.setPosition(e.geometry.position);
    infoWindow.value.open();
    // 触发父组件事件
    emit('markerClick', properties);
  }
};

// 处理信息窗关闭
const handleInfoWindowClose = () => {
  if (infoWindow.value) {
    infoWindow.value.close();
  }
};

// 病害波纹动画
const showDiseaseWave = (point) => {
  if (!diseaseWaveContainer.value || !point) return;

  // 转换坐标到地图容器像素
  const pixel = mapInstance.value.projectToContainer(new TMap.LatLng(point.coord_x, point.coord_y));
  const wave = document.createElement('div');
  wave.className = 'disease-wave';
  wave.style.left = `${pixel.x}px`;
  wave.style.top = `${pixel.y}px`;

  diseaseWaveContainer.value.appendChild(wave);

  // 3秒后移除动画
  setTimeout(() => {
    wave.remove();
  }, 3000);
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
      if (item && typeof item.coord_x === 'number' && typeof item.coord_y === 'number') {
        // 基础样式
        let styleId = item.minor_name || 'default';
        let src = gardenIconMap[item.minor_name] || markerTree;

        // 病害点位特殊处理
        if (item.disease_status !== '无病害' && item.disease_status !== '已处置') {
          styleId = 'disease';
          src = markerDisease;
        }

        const markerStyle = new TMap.MarkerStyle({
          width: item.disease_status !== '无病害' && item.disease_status !== '已处置' ? 39.1 : 34, // 病害图标放大15%
          height: item.disease_status !== '无病害' && item.disease_status !== '已处置' ? 39.1 : 34,
          anchor: {x: 15, y: 30},
          src: src
        });

        // 已养护标签
        const labels = [];
        if (item.maintain_status === '已养护') {
          labels.push({
            content: '✓ 已养护',
            position: {x: 0, y: -20},
            style: new TMap.LabelStyle({
              fontSize: 12,
              color: '#38b000',
              bgColor: 'rgba(255,255,255,0.8)',
              borderColor: '#38b000',
              borderWidth: 1,
              borderRadius: 4,
              padding: [2, 4]
            })
          });
        }

        geometriesData.push({
          id: `marker-${item.mng_comp_id || index}`,
          styleId: styleId,
          position: new TMap.LatLng(item.coord_x, item.coord_y),
          properties: {...item},
          labels: labels
        });
      }
    });
  }

  if (geometriesData.length > 0) {
    markerLayer.value = new TMap.MultiMarker({
      map: map,
      styles: {
        '树木': new TMap.MarkerStyle({
          width: 34,
          height: 34,
          anchor: {x: 15, y: 30},
          src: markerTree
        }),
        '绿地': new TMap.MarkerStyle({
          width: 34,
          height: 34,
          anchor: {x: 15, y: 30},
          src: markerGreenSpace
        }),
        '花坛': new TMap.MarkerStyle({
          width: 34,
          height: 34,
          anchor: {x: 15, y: 30},
          src: markerFlowerBed
        }),
        'disease': new TMap.MarkerStyle({
          width: 39.1,
          height: 39.1,
          anchor: {x: 15, y: 30},
          src: markerDisease
        }),
        'default': new TMap.MarkerStyle({
          width: 34,
          height: 34,
          anchor: {x: 15, y: 30},
          src: markerTree
        })
      },
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
    <div style="padding: 10px 12px; font-size: 14px; color: #333; background: white; border: 1px solid #ccc; min-width: 280px; border-radius: 4px;">
      <div style="margin-bottom: 8px; font-weight: bold; color: #2e7d32; border-bottom: 1px solid #eee; padding-bottom: 4px; text-align: center;">园林绿化信息</div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">绿化ID：</span>
        <span style="${valueStyle}">${properties.mng_comp_id || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">名称：</span>
        <span style="${valueStyle}">${properties.comp_name || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">类型：</span>
        <span style="${valueStyle}">${properties.minor_name || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">养护状态：</span>
        <span style="${valueStyle}; color: ${properties.maintain_status === '待养护' ? 'red' : properties.maintain_status === '养护中' ? 'orange' : 'green'}; font-weight: 500;">
          ${properties.maintain_status || '未知'}
        </span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">病害状态：</span>
        <span style="${valueStyle}; color: ${properties.disease_status === '未处置' ? 'red' : properties.disease_status === '处置中' ? 'orange' : properties.disease_status === '无病害' ? 'gray' : 'green'}; font-weight: 500;">
          ${properties.disease_status || '未知'}
        </span>
      </div>
      <div style="${rowStyle}" ${properties.disease_reason ? '' : 'style="display:none"'}>
        <span style="${labelStyle}">病害原因：</span>
        <span style="${valueStyle}">${properties.disease_reason || '无'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">责任人：</span>
        <span style="${valueStyle}">${properties.responsible_person || '未分配'}</span>
      </div>
    </div>
  `;
};

// 地图回调
const mapCallback = () => {
  const mapContainer = document.getElementById(props.idName);
  if (!mapContainer) {
    console.error(`地图容器不存在：${props.idName}`);
    return;
  }

  const map = new TMap.Map(mapContainer, {
    center: new TMap.LatLng(26.0753, 119.3062), // 福州坐标
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

  // 新增病害波纹动画
  if (props.newDiseasePoints.length > 0) {
    props.newDiseasePoints.forEach(point => {
      showDiseaseWave(point);
    });
  }
};

// 刷新地图
const refreshMap = () => {
  mapKey.value++;
  if (mapInitialized.value) {
    createMarkers(mapInstance.value);

    // 新增病害波纹动画
    if (props.newDiseasePoints.length > 0) {
      props.newDiseasePoints.forEach(point => {
        showDiseaseWave(point);
      });
    }
  } else {
    initMap();
  }
};

// 监听数据变化
watch(
  () => props.geometriesArray,
  (newVal) => {
    if (mapInitialized.value && Array.isArray(newVal) && newVal.length > 0) {
      createMarkers(mapInstance.value);
    }
  },
  {deep: true}
);

// 监听新增病害点位
watch(
  () => props.newDiseasePoints,
  (newVal) => {
    if (mapInitialized.value && Array.isArray(newVal) && newVal.length > 0) {
      newVal.forEach(point => {
        showDiseaseWave(point);
      });
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

// 暴露方法
defineExpose({
  refreshMap
});

const emit = defineEmits(['markerClick']);
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

/* 病害波纹动画 */
.disease-wave-container {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
  z-index: 10;
}

.disease-wave {
  position: absolute;
  width: 40px;
  height: 40px;
  margin-left: -20px;
  margin-top: -20px;
  border-radius: 50%;
  border: 2px solid #ff4d6d;
  animation: wave-anim 3s infinite;
}

@keyframes wave-anim {
  0% {
    transform: scale(0);
    opacity: 1;
  }
  100% {
    transform: scale(2);
    opacity: 0;
  }
}

/* 图例样式：和参考代码一致，仅保留图标相关样式（无颜色块） */
.legend {
  position: absolute;
  background: rgba(0, 0, 0, 0.5);
  box-sizing: border-box;
  width: 100%;
  height: auto;
  bottom: 0;
  left: 0;
  display: flex;
  justify-content: right;
  gap: 2vw;
  padding: 0.5vw;
  flex-wrap: wrap;
  z-index: 9;
}

.legend-group {
  display: flex;
  flex-direction: column;
  align-items: center;
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

/* 仅保留图标样式（和参考代码一致） */
.legend-icon {
  width: 1.2vw;
  height: 2vh;
  margin-right: 0.2vw;
  object-fit: contain;
}
</style>
