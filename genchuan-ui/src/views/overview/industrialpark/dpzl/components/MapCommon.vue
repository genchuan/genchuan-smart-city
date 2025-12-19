<template>
  <div class="map-container">
    <div :id="idName" class="map-common-css"></div>

    <div class="legend">
      <div class="legend-items">
        <div class="legend-item">
          <div class="legend-color industrial-zone"></div>
          <span>工业区</span>
        </div>
        <div class="legend-item">
          <div class="legend-color commercial-zone"></div>
          <span>商业区</span>
        </div>
        <div class="legend-item">
          <div class="legend-color residential-zone"></div>
          <span>生活区</span>
        </div>

        <div class="legend-item">
          <img :src="markerFactory" class="legend-icon" alt="生产楼" />
          <span>生产楼</span>
        </div>
        <div class="legend-item">
          <img :src="markerOffice" class="legend-icon" alt="办公楼" />
          <span>办公楼</span>
        </div>
        <div class="legend-item">
          <img :src="markerDormitory" class="legend-icon" alt="宿舍楼" />
          <span>宿舍楼</span>
        </div>
        <div class="legend-item">
          <img :src="markerParking" class="legend-icon" alt="停车场" />
          <span>停车场</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, defineProps, ref, onUnmounted, defineExpose, watch } from 'vue';
import markerFactory from '@/assets/chart/images/factory.png';
import markerOffice from '@/assets/chart/images/office.png';
import markerDormitory from '@/assets/chart/images/dormitory.png';
import markerParking from '@/assets/chart/images/parking.png';
import markerDefault from '@/assets/chart/images/marker-gray.png';

const props = defineProps({
  idName: {
    type: String,
    default: 'parkMap',
  },
  geometriesArray: {
    type: Array,
    default: () => []
  }
});

// 图层实例
const layers = ref({
  industrial: null,
  commercial: null,
  residential: null,
  building: null,
  road: null,
  parking: null
});

// 图层显示状态
const layerVisible = ref({
  industrial: true,
  commercial: true,
  residential: true,
  building: true,
  road: true,
  parking: true
});

const mapInstance = ref(null);
const infoWindow = ref(null);

// 信息窗关闭回调
const handleInfoWindowClose = () => {
  if (infoWindow.value) {
    infoWindow.value.close();
  }
};

// 区域图层点击回调（通过闭包传递zoneType）
const createZoneClickHandler = (zoneType) => {
  return (e) => handleZoneClick(e, zoneType);
};

// 建筑点击回调
const handleBuildingClickWrapper = (e) => {
  handleBuildingClick(e);
};

// 道路点击回调
const handleRoadClickWrapper = (e) => {
  handleRoadClick(e);
};

// 停车场点击回调
const handleParkingClickWrapper = (e) => {
  handleParkingClick(e);
};

// 存储区域图层的点击处理器（用于off时匹配引用）
const zoneClickHandlers = ref({
  industrial: null,
  commercial: null,
  residential: null
});

// 重置图层（销毁旧图层）
const resetLayers = () => {
  // 销毁区域图层（需匹配zoneClickHandlers的引用）
  Object.keys(zoneClickHandlers.value).forEach(zoneType => {
    const layer = layers.value[zoneType];
    const handler = zoneClickHandlers.value[zoneType];
    if (layer && handler) {
      try {
        layer.off('click', handler);
      } catch (error) {
        console.warn(`销毁${zoneType}区域图层事件失败：`, error);
      }
    }
  });

  // 销毁其他图层
  const otherLayers = ['building', 'road', 'parking'];
  otherLayers.forEach(layerType => {
    const layer = layers.value[layerType];
    let handler = null;
    if (layerType === 'building') handler = handleBuildingClickWrapper;
    if (layerType === 'road') handler = handleRoadClickWrapper;
    if (layerType === 'parking') handler = handleParkingClickWrapper;

    if (layer && handler) {
      try {
        layer.off('click', handler);
      } catch (error) {
        console.warn(`销毁${layerType}图层事件失败：`, error);
      }
    }
  });

  // 销毁所有图层实例
  Object.values(layers.value).forEach(layer => {
    if (layer) {
      try {
        layer.destroy();
      } catch (error) {
        console.warn('销毁图层实例失败：', error);
      }
    }
  });

  // 重置状态
  layers.value = {
    industrial: null,
    commercial: null,
    residential: null,
    building: null,
    road: null,
    parking: null
  };
  zoneClickHandlers.value = {
    industrial: null,
    commercial: null,
    residential: null
  };
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

// 地图初始化回调
const mapCallback = () => {
  const mapContainer = document.getElementById(props.idName);
  if (!mapContainer) return;

  // 初始化地图
  const map = new TMap.Map(mapContainer, {
    center: new TMap.LatLng(26.793227, 117.810114),
    zoom: 12,
    mapStyleId: 'style1'
  });
  mapInstance.value = map;

  // 初始化信息窗
  infoWindow.value = new TMap.InfoWindow({
    map: map,
    position: new TMap.LatLng(0, 0),
    content: '',
    offset: { x: 0, y: -30 },
    visible: false
  });

  // 绑定信息窗关闭事件
  infoWindow.value.on('close', handleInfoWindowClose);

  // 初始创建图层
  if (props.geometriesArray.length > 0) {
    createZoneLayers(map);
    createBuildingLayer(map);
    createRoadLayer(map);
    createParkingLayer(map);
    // 绑定图层点击事件
    bindLayerClickEvents();
  }
};

// 绑定所有图层点击事件
const bindLayerClickEvents = () => {
  // 绑定区域图层点击事件
  Object.keys(zoneClickHandlers.value).forEach(zoneType => {
    const layer = layers.value[zoneType];
    if (layer && !zoneClickHandlers.value[zoneType]) {
      // 创建带zoneType参数的处理器（闭包保存参数）
      const handler = createZoneClickHandler(zoneType);
      zoneClickHandlers.value[zoneType] = handler;
      layer.on('click', handler);
    }
  });

  // 绑定其他图层点击事件
  if (layers.value.building) {
    layers.value.building.on('click', handleBuildingClickWrapper);
  }
  if (layers.value.road) {
    layers.value.road.on('click', handleRoadClickWrapper);
  }
  if (layers.value.parking) {
    layers.value.parking.on('click', handleParkingClickWrapper);
  }
};

// 创建区域图层
const createZoneLayers = (map) => {
  const buildingGroups = {
    industrial: props.geometriesArray.filter(item => item.building_type === '生产楼'),
    commercial: props.geometriesArray.filter(item => item.building_type === '办公楼'),
    residential: props.geometriesArray.filter(item => item.building_type === '宿舍楼')
  };

  const zoneStyles = {
    industrial: new TMap.PolygonStyle({
      color: 'rgba(136, 136, 136, 0.3)',
      showBorder: true,
      borderColor: 'rgba(100, 100, 100, 0.8)',
      borderWidth: 2
    }),
    commercial: new TMap.PolygonStyle({
      color: 'rgba(30, 144, 255, 0.3)',
      showBorder: true,
      borderColor: 'rgba(30, 144, 255, 0.8)',
      borderWidth: 2
    }),
    residential: new TMap.PolygonStyle({
      color: 'rgba(255, 127, 80, 0.3)',
      showBorder: true,
      borderColor: 'rgba(255, 127, 80, 0.8)',
      borderWidth: 2
    })
  };

  const zoneInfo = {
    industrial: {
      name: '工业区',
      area: buildingGroups.industrial[0]?.zone_area || '未知'
    },
    commercial: {
      name: '商业区',
      area: buildingGroups.commercial[0]?.zone_area || '未知'
    },
    residential: {
      name: '生活区',
      area: buildingGroups.residential[0]?.zone_area || '未知'
    }
  };

  Object.keys(buildingGroups).forEach(zoneType => {
    const buildings = buildingGroups[zoneType];
    if (buildings.length === 0) return;

    const coords = buildings.map(b => ({ x: b.coord_x, y: b.coord_y }));
    const minX = Math.min(...coords.map(c => c.x)) - 0.01;
    const maxX = Math.max(...coords.map(c => c.x)) + 0.01;
    const minY = Math.min(...coords.map(c => c.y)) - 0.01;
    const maxY = Math.max(...coords.map(c => c.y)) + 0.01;

    const path = [
      new TMap.LatLng(minX, minY),
      new TMap.LatLng(minX, maxY),
      new TMap.LatLng(maxX, maxY),
      new TMap.LatLng(maxX, minY)
    ];

    layers.value[zoneType] = new TMap.MultiPolygon({
      id: `${zoneType}-zone-layer`,
      map: map,
      styles: { [zoneType]: zoneStyles[zoneType] },
      geometries: [{
        id: `${zoneType}-zone`,
        styleId: zoneType,
        paths: path,
        properties: {
          name: zoneInfo[zoneType].name,
          area: zoneInfo[zoneType].area,
          buildingCount: buildings.length
        }
      }]
    });
  });
};

const createBuildingLayer = (map) => {
  const buildingData = [];
  props.geometriesArray.forEach((item, index) => {
    if (!item.type && typeof item.coord_x === 'number' && typeof item.coord_y === 'number') {
      let styleId = 'default';
      if (item.building_type === '生产楼') styleId = 'factory';
      else if (item.building_type === '办公楼') styleId = 'office';
      else if (item.building_type === '宿舍楼') styleId = 'dormitory';

      const zoneType = item.building_type === '生产楼' ? 'industrial' :
        item.building_type === '办公楼' ? 'commercial' : 'residential';
      const zoneName = zoneType === 'industrial' ? '工业区' :
        zoneType === 'commercial' ? '商业区' : '生活区';

      buildingData.push({
        id: `building-${index}`,
        styleId,
        position: new TMap.LatLng(item.coord_x, item.coord_y),
        properties: {
          ...item,
          zoneType,
          zoneName,
          zone_area: item.zone_area
        }
      });
    }
  });

  if (buildingData.length > 0) {
    layers.value.building = new TMap.MultiMarker({
      map: map,
      styles: getBuildingStyles(),
      geometries: buildingData
    });
  }
};

const getBuildingStyles = () => {
  return {
    'factory': new TMap.MarkerStyle({
      width: 40,
      height: 40,
      anchor: {x: 20, y: 40},
      src: markerFactory
    }),
    'office': new TMap.MarkerStyle({
      width: 40,
      height: 40,
      anchor: {x: 20, y: 40},
      src: markerOffice
    }),
    'dormitory': new TMap.MarkerStyle({
      width: 40,
      height: 40,
      anchor: {x: 20, y: 40},
      src: markerDormitory
    }),
    'default': new TMap.MarkerStyle({
      width: 40,
      height: 40,
      anchor: {x: 20, y: 40},
      src: markerDefault
    })
  };
};

const createRoadLayer = (map) => {
  const roadData = props.geometriesArray
    .filter(item => item.type === 'road')
    .map((item, index) => ({
      id: `road-${index}`,
      styleId: item.zone_type,
      path: item.path.map(p => new TMap.LatLng(p.x, p.y)),
      properties: item
    }));

  if (roadData.length > 0) {
    layers.value.road = new TMap.MultiPolyline({
      id: 'road-layer',
      map: map,
      styles: {
        industrial: new TMap.PolylineStyle({ color: '#888', width: 6 }),
        commercial: new TMap.PolylineStyle({ color: '#1E90FF', width: 6 }),
        residential: new TMap.PolylineStyle({ color: '#FF7F50', width: 6 })
      },
      geometries: roadData
    });
  }
};

const createParkingLayer = (map) => {
  const parkingData = props.geometriesArray
    .filter(item => item.type === 'parking')
    .map((item, index) => ({
      id: `parking-${index}`,
      styleId: 'parking',
      position: new TMap.LatLng(item.position.x, item.position.y),
      properties: item
    }));

  if (parkingData.length > 0) {
    layers.value.parking = new TMap.MultiMarker({
      id: 'parking-layer',
      map: map,
      styles: {
        parking: new TMap.MarkerStyle({
          width: 36,
          height: 36,
          anchor: {x: 18, y: 36},
          src: markerParking
        })
      },
      geometries: parkingData
    });
  }
};

// 区域点击事件
const handleZoneClick = (e, zoneType) => {
  if (e.geometry) {
    const props = e.geometry.properties;
    const content = `
      <div style="padding: 8px 12px; font-size: 14px; color: #333; background: white; border: 1px solid #ccc; width: 280px;">
        <h3 style="margin: 0 0 12px 0; font-size: 16px; color: #1E90FF; text-align: center; border-bottom: 1px solid #eee; padding-bottom: 8px;">${props.name}</h3>
        <div style="display: flex; align-items: center; margin: 6px 0;">
          <span style="width: 80px; text-align: right; font-weight: bold; margin-right: 6px;">区域类型：</span>
          <span style="flex: 1; text-align: left;">${props.name}</span>
        </div>
        <div style="display: flex; align-items: center; margin: 6px 0;">
          <span style="width: 80px; text-align: right; font-weight: bold; margin-right: 6px;">区域面积：</span>
          <span style="flex: 1; text-align: left;">${props.area}</span>
        </div>
        <div style="display: flex; align-items: center; margin: 6px 0;">
          <span style="width: 80px; text-align: right; font-weight: bold; margin-right: 6px;">建筑数量：</span>
          <span style="flex: 1; text-align: left;">${props.buildingCount} 栋</span>
        </div>
      </div>
    `;
    infoWindow.value.setContent(content);
    infoWindow.value.setPosition(e.latLng);
    infoWindow.value.open();
  }
};

// 建筑点击事件
const getTooltipContent = (properties) => {
  const formatPoints = (points) => {
    return points?.length
      ? points.map(p => `(${p.x.toFixed(6)}, ${p.y.toFixed(6)})`).join('、')
      : '无';
  };

  // 对齐样式：提取为公共样式，避免重复
  const labelStyle = 'width: 100px; text-align: right; font-weight: bold; margin-right: 6px; flex-shrink: 0;';
  const valueStyle = 'flex: 1; text-align: left; word-break: break-all;';
  const rowStyle = 'display: flex; align-items: flex-start; margin: 8px 0;';

  return `
    <div style="padding: 12px; font-size: 14px; color: #333; background: white; border: 1px solid #ccc; min-width: 320px; max-height: 400px; overflow-y: auto;">
      <div style="margin-bottom: 12px; font-weight: bold; color: #1E90FF; text-align: center; border-bottom: 1px solid #eee; padding-bottom: 8px;">${properties.building_name} 信息</div>

      <div style="${rowStyle}">
        <span style="${labelStyle}">所属区域：</span>
        <span style="${valueStyle}">${properties.zoneName}（面积：${properties.zone_area}）</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">楼栋ID：</span>
        <span style="${valueStyle}">${properties.building_id || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">建筑类型：</span>
        <span style="${valueStyle}">${properties.building_type || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">层数：</span>
        <span style="${valueStyle}">${properties.floors || '未知'} 层</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">建筑坐标：</span>
        <span style="${valueStyle}">(${properties.coord_x.toFixed(6)}, ${properties.coord_y.toFixed(6)})</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">绿地面积：</span>
        <span style="${valueStyle}">${properties.green_area || 0} 平方米</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">广场位置：</span>
        <span style="${valueStyle}">${properties.plaza_position || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">充电桩点位：</span>
        <span style="${valueStyle}">${formatPoints(properties.charging_piles)}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">消防栓位置：</span>
        <span style="${valueStyle}">${formatPoints(properties.fire_hydrants)}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">应急通道走向：</span>
        <span style="${valueStyle}">${properties.emergency_passage || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">避难所容量：</span>
        <span style="${valueStyle}">${properties.shelter_capacity || 0} 人</span>
      </div>
    </div>
  `;
};

const handleBuildingClick = (e) => {
  const { properties, position } = e.geometry;
  if (properties && position && infoWindow.value) {
    infoWindow.value.setContent(getTooltipContent(properties));
    infoWindow.value.setPosition(position);
    infoWindow.value.open();
  }
};

// 道路点击事件
const handleRoadClick = (e) => {
  if (e.geometry) {
    const props = e.geometry.properties;
    const zoneName = props.zone_type === 'industrial' ? '工业区' :
      props.zone_type === 'commercial' ? '商业区' : '生活区';

    const labelStyle = 'width: 80px; text-align: right; font-weight: bold; margin-right: 6px;';
    const valueStyle = 'flex: 1; text-align: left;';
    const rowStyle = 'display: flex; align-items: center; margin: 6px 0;';

    const content = `
      <div style="padding: 8px 12px; font-size: 14px; color: #333; background: white; border: 1px solid #ccc; width: 280px;">
        <h3 style="margin: 0 0 12px 0; font-size: 16px; color: #1E90FF; text-align: center; border-bottom: 1px solid #eee; padding-bottom: 8px;">${props.road_name}</h3>
        <div style="${rowStyle}">
          <span style="${labelStyle}">所属区域：</span>
          <span style="${valueStyle}">${zoneName}</span>
        </div>
        <div style="${rowStyle}">
          <span style="${labelStyle}">道路长度：</span>
          <span style="${valueStyle}">${props.length}</span>
        </div>
      </div>
    `;
    infoWindow.value.setContent(content);
    infoWindow.value.setPosition(e.latLng);
    infoWindow.value.open();
  }
};

// 停车场点击事件
const handleParkingClick = (e) => {
  if (e.geometry) {
    const props = e.geometry.properties;
    const zoneName = props.zone_type === 'industrial' ? '工业区' :
      props.zone_type === 'commercial' ? '商业区' : '生活区';

    const labelStyle = 'width: 80px; text-align: right; font-weight: bold; margin-right: 6px;';
    const valueStyle = 'flex: 1; text-align: left;';
    const rowStyle = 'display: flex; align-items: center; margin: 6px 0;';

    const content = `
      <div style="padding: 8px 12px; font-size: 14px; color: #333; background: white; border: 1px solid #ccc; width: 280px;">
        <h3 style="margin: 0 0 12px 0; font-size: 16px; color: #1E90FF; text-align: center; border-bottom: 1px solid #eee; padding-bottom: 8px;">${props.parking_name}</h3>
        <div style="${rowStyle}">
          <span style="${labelStyle}">所属区域：</span>
          <span style="${valueStyle}">${zoneName}</span>
        </div>
        <div style="${rowStyle}">
          <span style="${labelStyle}">位置坐标：</span>
          <span style="${valueStyle}">(${props.position.x.toFixed(6)}, ${props.position.y.toFixed(6)})</span>
        </div>
        <div style="${rowStyle}">
          <span style="${labelStyle}">车位数量：</span>
          <span style="${valueStyle}">${props.capacity} 个</span>
        </div>
      </div>
    `;
    infoWindow.value.setContent(content);
    infoWindow.value.setPosition(e.latLng);
    infoWindow.value.open();
  }
};

// 图层显示/隐藏切换
const toggleLayer = (type) => {
  layerVisible.value[type] = !layerVisible.value[type];
  if (layers.value[type]) {
    layers.value[type].setMap(layerVisible.value[type] ? mapInstance.value : null);
    infoWindow.value.close();
  }
};

// 暴露图层控制方法
defineExpose({ toggleLayer });

// 监听geometriesArray变化，重新渲染图层
watch(
  () => props.geometriesArray,
  (newVal) => {
    if (newVal && newVal.length > 0 && mapInstance.value) {
      resetLayers(); // 先销毁旧图层
      createZoneLayers(mapInstance.value);
      createBuildingLayer(mapInstance.value);
      createRoadLayer(mapInstance.value);
      createParkingLayer(mapInstance.value);
      bindLayerClickEvents(); // 重新绑定点击事件
    }
  },
  { deep: true }
);

onMounted(() => {
  initMap();
});

onUnmounted(() => {
  // 先移除所有事件，再销毁实例
  resetLayers();

  // 销毁信息窗
  if (infoWindow.value) {
    try {
      infoWindow.value.off('close', handleInfoWindowClose);
      infoWindow.value.destroy();
    } catch (error) {
      console.warn('卸载时销毁信息窗失败：', error);
    }
  }

  // 销毁地图
  if (mapInstance.value) {
    try {
      mapInstance.value.destroy();
    } catch (error) {
      console.warn('卸载时销毁地图失败：', error);
    }
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
  height: 90%;
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
  bottom: 3vh;
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
  width: 1vw;
  height: 2vh;
  margin-right: 0.5vw;
  border-radius: 2px;
  border: 1px solid rgba(0,0,0,0.1);
}

.industrial-zone { background: rgba(136, 136, 136, 0.6); }
.commercial-zone { background: rgba(30, 144, 255, 0.6); }
.residential-zone { background: rgba(255, 127, 80, 0.6); }

.legend-icon {
  width: 1.2vw;
  height: 2vh;
  margin-right: 0.2vw;
}
</style>
