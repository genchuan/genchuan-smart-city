<template>
  <div class="map-container">
    <!-- 图层控制面板 -->
    <div class="control-panel">
      <div class="control-group">
        <button
          @click="toggleLayer('adminDivision')"
          :class="{ active: layerVisible.adminDivision }"
        >
          行政区划-{{ layerVisible.adminDivision ? '隐藏' : '显示' }}
        </button>
      </div>
      <div class="control-group">
        <button
          @click="toggleLayer('ecoFunction')"
          :class="{ active: layerVisible.ecoFunction }"
        >
          生态功能区-{{ layerVisible.ecoFunction ? '隐藏' : '显示' }}
        </button>
      </div>
      <div class="control-group">
        <button
          @click="toggleLayer('ecoRedLine')"
          :class="{ active: layerVisible.ecoRedLine }"
        >
          生态红线-{{ layerVisible.ecoRedLine ? '隐藏' : '显示' }}
        </button>
      </div>
      <div class="control-group">
        <button
          @click="toggleLayer('pollutionSource')"
          :class="{ active: layerVisible.pollutionSource }"
        >
          污染源-{{ layerVisible.pollutionSource ? '隐藏' : '显示' }}
        </button>
      </div>
    </div>

    <!-- 地图容器 -->
    <div :id="idName" class="map-common-css"></div>

    <!-- 图例 -->
    <div class="legend">
      <div class="legend-items">
        <div class="legend-item">
          <div class="legend-color nature-reserve"></div>
          <span>自然保护区</span>
        </div>
        <div class="legend-item">
          <div class="legend-color wetland"></div>
          <span>湿地</span>
        </div>
        <div class="legend-item">
          <div class="legend-color woodland"></div>
          <span>林地</span>
        </div>
        <div class="legend-item">
          <div class="legend-color high-risk"></div>
          <span>高污染</span>
        </div>
        <div class="legend-item">
          <div class="legend-color medium-risk"></div>
          <span>中污染</span>
        </div>
        <div class="legend-item">
          <div class="legend-color low-risk"></div>
          <span>低污染</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, defineProps, ref, onUnmounted, defineExpose, watch } from 'vue';

const props = defineProps({
  idName: {
    type: String,
    default: 'ecoMap',
  },
  geometriesArray: {
    type: Array,
    default: () => []
  }
});

const layers = ref({
  ecoFunction: null,
  ecoRedLine: null,
  adminDivision: null,
  pollutionSource: null
});

const layerVisible = ref({
  ecoFunction: true,
  ecoRedLine: true,
  adminDivision: true,
  pollutionSource: true
});

const mapInstance = ref(null);
const infoWindow = ref(null);
const mapInitialized = ref(false);

const initMap = () => {
  const callbackName = `initMap_${props.idName}`;

  const existingScript = document.querySelector(`script[src*="map.qq.com/api/gljs"]`);
  if (existingScript) {
    if (window.TMap) {
      mapCallback();
    } else {
      const checkTMap = setInterval(() => {
        if (window.TMap) {
          clearInterval(checkTMap);
          mapCallback();
        }
      }, 100);
    }
    return;
  }

  const script = document.createElement('script');
  script.src = `https://map.qq.com/api/gljs?v=1.exp&key=QTQBZ-F3RWW-JJJRV-YNPA5-ZIKDK-3SBNO&callback=${callbackName}`;
  script.async = true;

  window[callbackName] = () => {
    mapCallback();
    delete window[callbackName];
  };

  document.head.appendChild(script);
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
  mapInitialized.value = true;

  infoWindow.value = new TMap.InfoWindow({
    map: map,
    position: new TMap.LatLng(0, 0),
    content: '',
    offset: { x: 0, y: -30 },
    visible: false
  });

  if (props.geometriesArray.length > 0) {
    createLayersFromData(map);
  }
};

const createLayersFromData = (map) => {
  destroyLayers();

  const adminDivisionGeometries = props.geometriesArray
    .filter(item => item.type === 'adminDivision')
    .map((item, index) => ({
      id: `admin-${index}`,
      styleId: 'adminDivision',
      paths: item.paths.map(p => new TMap.LatLng(p.coord_x, p.coord_y)),
      properties: item
    }));

  layers.value.adminDivision = new TMap.MultiPolygon({
    id: 'admin-division-layer',
    map: map,
    zIndex: 1,
    styles: {
      adminDivision: new TMap.PolygonStyle({
        color: 'rgba(93, 173, 226, 0.2)',
        showBorder: true,
        borderColor: 'rgba(52, 152, 219, 0.8)',
        borderWidth: 2
      })
    },
    geometries: adminDivisionGeometries
  });

  const ecoFunctionGeometries = props.geometriesArray
    .filter(item => item.type === 'ecoFunction')
    .map((item, index) => ({
      id: `eco-${item.subType}-${index}`,
      styleId: item.subType,
      paths: item.paths.map(p => new TMap.LatLng(p.coord_x, p.coord_y)),
      properties: item
    }));

  layers.value.ecoFunction = new TMap.MultiPolygon({
    id: 'eco-function-layer',
    map: map,
    zIndex: 2,
    styles: {
      natureReserve: new TMap.PolygonStyle({
        color: 'rgba(2, 79, 25, 0.6)',
        showBorder: true,
        borderColor: 'rgba(255, 255, 255, 0.8)',
        borderWidth: 2
      }),
      wetland: new TMap.PolygonStyle({
        color: 'rgba(135, 206, 235, 0.6)',
        showBorder: true,
        borderColor: 'rgba(255, 255, 255, 0.8)',
        borderWidth: 2
      }),
      woodland: new TMap.PolygonStyle({
        color: 'rgba(50, 205, 50, 0.6)',
        showBorder: true,
        borderColor: 'rgba(255, 255, 255, 0.8)',
        borderWidth: 2
      })
    },
    geometries: ecoFunctionGeometries
  });

  const ecoRedLineGeometries = props.geometriesArray
    .filter(item => item.type === 'ecoRedLine')
    .map((item, index) => ({
      id: `redline-${index}`,
      styleId: 'ecoRedLine',
      paths: item.paths.map(p => new TMap.LatLng(p.coord_x, p.coord_y)),
      properties: item
    }));

  layers.value.ecoRedLine = new TMap.MultiPolygon({
    id: 'eco-redline-layer',
    map: map,
    zIndex: 3,
    styles: {
      ecoRedLine: new TMap.PolygonStyle({
        color: 'rgba(255, 0, 0, 0.3)',
        showBorder: true,
        borderColor: 'rgba(255, 0, 0, 0.8)',
        borderWidth: 3,
        borderDashArray: [8, 4]
      })
    },
    geometries: ecoRedLineGeometries
  });

  const pollutionGeometries = props.geometriesArray
    .filter(item => item.type === 'pollutionSource')
    .map((item, index) => ({
      id: `pollution-${item.subType}-${index}`,
      styleId: item.subType,
      paths: item.paths.map(p => new TMap.LatLng(p.coord_x, p.coord_y)),
      properties: item
    }));

  layers.value.pollutionSource = new TMap.MultiPolygon({
    id: 'pollution-source-layer',
    map: map,
    zIndex: 4,
    styles: {
      low: new TMap.PolygonStyle({
        color: 'rgba(255, 160, 160, 0.6)',
        showBorder: true,
        borderColor: 'rgba(255, 99, 71, 0.8)',
        borderWidth: 2
      }),
      medium: new TMap.PolygonStyle({
        color: 'rgba(220, 20, 60, 0.6)',
        showBorder: true,
        borderColor: 'rgba(178, 34, 34, 0.9)',
        borderWidth: 2
      }),
      high: new TMap.PolygonStyle({
        color: 'rgba(139, 0, 0, 0.7)',
        showBorder: true,
        borderColor: 'rgba(101, 31, 255, 1)',
        borderWidth: 3,
        borderDashArray: [2, 1]
      })
    },
    geometries: pollutionGeometries
  });

  bindLayerEvents();
};

const bindLayerEvents = () => {
  // 生态功能区绑定 handleEcoClick
  if (layers.value.ecoFunction) {
    layers.value.ecoFunction.on('click', handleEcoClick);
  }
  // 污染源绑定 handlePollutionClick
  if (layers.value.pollutionSource) {
    layers.value.pollutionSource.on('click', handlePollutionClick);
  }
};

const destroyLayers = () => {
  const eventBindMap = {
    ecoFunction: ['click', handleEcoClick],
    pollutionSource: ['click', handlePollutionClick]
  };

  Object.entries(layers.value).forEach(([layerType, layer]) => {
    if (layer) {
      try {
        if (eventBindMap[layerType]) {
          const [eventName, callback] = eventBindMap[layerType];
          layer.off(eventName, callback);
        }
      } catch (error) {
        console.warn(`移除${layerType}图层事件失败：`, error);
      }
      layer.destroy();
    }
  });

  layers.value = {
    ecoFunction: null,
    ecoRedLine: null,
    adminDivision: null,
    pollutionSource: null
  };
};

const handleEcoClick = (evt) => {
  if (evt.geometry) {
    const props = evt.geometry.properties;
    const labelStyle = 'width: 100px; text-align: right; font-weight: bold; margin-right: 6px; flex-shrink: 0;';
    const valueStyle = 'flex: 1; text-align: left; word-break: break-all;';
    const rowStyle = 'display: flex; align-items: flex-start; margin: 6px 0;';

    const content = `
      <div style="padding: 10px 12px; font-size: 14px; color: #333; background: white; border: 1px solid #ccc; min-width: 280px; border-radius: 4px;">
        <h3 style="margin: 0 0 8px 0; font-size: 16px; color: #1E90FF; text-align: center; border-bottom: 1px solid #eee; padding-bottom: 4px;">${props.name}</h3>
        <div style="${rowStyle}"><span style="${labelStyle}">类型：</span><span style="${valueStyle}">${getEcoTypeName(props.subType)}</span></div>
        <div style="${rowStyle}"><span style="${labelStyle}">面积：</span><span style="${valueStyle}">${props.area}</span></div>
        <div style="${rowStyle}"><span style="${labelStyle}">保护级别：</span><span style="${valueStyle}">${props.level || '未知'}</span></div>
        <div style="${rowStyle}"><span style="${labelStyle}">主要物种：</span><span style="${valueStyle}">${props.species || '未知'}</span></div>
        <div style="${rowStyle}"><span style="${labelStyle}">建立时间：</span><span style="${valueStyle}">${props.establishYear || '未知'}</span></div>
      </div>
    `;
    infoWindow.value.setContent(content);
    infoWindow.value.setPosition(evt.latLng);
    infoWindow.value.open();
  }
};

const handlePollutionClick = (evt) => {
  if (evt.geometry) {
    const props = evt.geometry.properties;
    const labelStyle = 'width: 100px; text-align: right; font-weight: bold; margin-right: 6px; flex-shrink: 0;';
    const valueStyle = 'flex: 1; text-align: left; word-break: break-all;';
    const rowStyle = 'display: flex; align-items: flex-start; margin: 6px 0;';

    const content = `
      <div style="padding: 10px 12px; font-size: 14px; color: #333; background: white; border: 1px solid #ccc; min-width: 280px; border-radius: 4px;">
        <h3 style="margin: 0 0 8px 0; font-size: 16px; color: #1E90FF; text-align: center; border-bottom: 1px solid #eee; padding-bottom: 4px;">${props.name}</h3>
        <div style="${rowStyle}"><span style="${labelStyle}">污染级别：</span><span style="${valueStyle}">${getPollutionLevelName(props.subType)}</span></div>
        <div style="${rowStyle}"><span style="${labelStyle}">污染类型：</span><span style="${valueStyle}">${props.pollutionType || '未知'}</span></div>
        <div style="${rowStyle}"><span style="${labelStyle}">主要污染物：</span><span style="${valueStyle}">${props.pollutants || '未知'}</span></div>
        <div style="${rowStyle}"><span style="${labelStyle}">日均排放量：</span><span style="${valueStyle}">${props.emission || '未知'}</span></div>
        <div style="${rowStyle}"><span style="${labelStyle}">区域面积：</span><span style="${valueStyle}">${props.area || '未知'}</span></div>
      </div>
    `;
    infoWindow.value.setContent(content);
    infoWindow.value.setPosition(evt.latLng);
    infoWindow.value.open();
  }
};

// 类型转换
const getEcoTypeName = (subType) => {
  const map = {'natureReserve': '自然保护区', 'wetland': '湿地', 'woodland': '林地'};
  return map[subType] || subType;
};

const getPollutionLevelName = (subType) => {
  const map = {'low': '低污染', 'medium': '中污染', 'high': '高污染'};
  return map[subType] || subType;
};

// 图层显示/隐藏切换
const toggleLayer = (type) => {
  layerVisible.value[type] = !layerVisible.value[type];
  if (layers.value[type] && mapInstance.value) {
    layers.value[type].setMap(layerVisible.value[type] ? mapInstance.value : null);
    infoWindow.value.close();
  }
};

// 监听geometriesArray变化
watch(
  () => props.geometriesArray,
  (newVal) => {
    if (mapInitialized.value && newVal.length > 0) {
      createLayersFromData(mapInstance.value);
    }
  },
  {deep: true}
);

// 暴露方法
defineExpose({toggleLayer});

onMounted(() => {
  initMap();
});

onUnmounted(() => {
  // 销毁所有资源
  destroyLayers();
  if (infoWindow.value) infoWindow.value.destroy();
  if (mapInstance.value) mapInstance.value.destroy();
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

.control-panel {
  position: absolute;
  z-index: 1002;
  padding: 15px;
  border-radius: 10px;
  background: rgba(0, 0, 0, 0.5);
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  display: grid;
  grid-template-columns: 1fr;
  grid-gap: 15px;
}

.control-group {
  margin: 0;
}

.control-panel button {
  background: #fff;
  border: 1px solid #ddd;
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 12px;
  transition: all 0.2s;
  width: 100%;
  box-sizing: border-box;
}

.control-panel button:hover {
  border-color: #409eff;
  color: #409eff;
}

.control-panel button.active {
  background: #409eff;
  color: white;
  border-color: #409eff;
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

.legend-color {
  width: 16px;
  height: 16px;
  margin-right: 6px;
  border-radius: 3px;
  border: 1px solid rgba(0, 0, 0, 0.1);
}

.nature-reserve {
  background: rgba(2, 79, 25, 0.6);
}

.wetland {
  background: rgba(135, 206, 235, 0.6);
}

.woodland {
  background: rgba(50, 205, 50, 0.6);
}

.high-risk {
  background: rgba(139, 0, 0, 0.7);
}

.medium-risk {
  background: rgba(220, 20, 60, 0.6);
}

.low-risk {
  background: rgba(255, 160, 160, 0.6);
}
</style>
