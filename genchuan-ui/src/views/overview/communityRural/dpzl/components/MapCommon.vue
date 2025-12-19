<!-- MapCommon.vue -->
<template>
  <div class="map-common-container">
    <div :id="idName" class="map-content" :style="{ width: '100%', height: '100%' }"></div>

    <!-- 地图控件 -->
    <div class="map-controls">
      <button @click="zoomIn" class="control-btn">+</button>
      <button @click="zoomOut" class="control-btn">-</button>
      <button @click="resetMap" class="control-btn">⟲</button>
      <button @click="toggleSelectionMode" class="control-btn" :class="{ active: selectionMode }">
        <el-icon><Select /></el-icon>
      </button>
    </div>

    <!-- 图例说明 - 集成资源分布 -->
    <div class="map-legend">
      <div class="legend-title">图例说明</div>
      <div class="legend-section">
        <div class="legend-subtitle">资源点位</div>
        <div class="legend-item">
          <div class="legend-icon resource land"></div>
          <span>土地资源</span>
        </div>
        <div class="legend-item">
          <div class="legend-icon resource water"></div>
          <span>水利资源</span>
        </div>
        <div class="legend-item">
          <div class="legend-icon resource culture"></div>
          <span>文旅资源</span>
        </div>
      </div>
      <div class="legend-section">
        <div class="legend-subtitle">事件点位</div>
        <div class="legend-item">
          <div class="legend-icon event-level1 pulse-animation-fast"></div>
          <span>一级事件</span>
        </div>
        <div class="legend-item">
          <div class="legend-icon event-level2 pulse-animation-medium"></div>
          <span>二级事件</span>
        </div>
        <div class="legend-item">
          <div class="legend-icon event-level3 pulse-animation-slow"></div>
          <span>三级事件</span>
        </div>
      </div>
      <div class="legend-section">
        <div class="legend-subtitle">边界图层</div>
        <div class="legend-item">
          <div class="legend-icon grid"></div>
          <span>网格边界</span>
        </div>
        <div class="legend-item">
          <div class="legend-icon area"></div>
          <span>行政边界</span>
        </div>
      </div>
    </div>

    <!-- 选择框 -->
    <div v-if="selectionMode && selectionStart" class="selection-rectangle"
         :style="selectionStyle"></div>
  </div>
</template>

<script setup>
import { onMounted, defineProps, onUnmounted, watch, ref, computed } from 'vue';
import { ElMessage } from 'element-plus';
import { Select } from '@element-plus/icons-vue';

const props = defineProps({
  idName: {
    type: String,
    default: 'ruralMap'
  },
  areaData: {
    type: Array,
    default: () => []
  },
  gridData: {
    type: Array,
    default: () => []
  },
  resourceData: {
    type: Array,
    default: () => []
  },
  eventData: {
    type: Array,
    default: () => []
  },
  filters: {
    type: Object,
    default: () => ({})
  },
  highlightedResource: {
    type: Object,
    default: null
  }
});

const emit = defineEmits(['drillDown', 'pointClick', 'mapLoaded', 'areaSelect', 'selectionChange', 'resourceHighlight']);

// 地图实例和状态
const mapInstance = ref(null);
const currentZoom = ref(10);
const selectedArea = ref(null);
const selectionMode = ref(false);
const selectionStart = ref(null);
const selectionEnd = ref(null);

// 腾讯地图实例
let qqMap = null;
let markers = [];
let polygons = [];
let selectionRectangle = null;

// 选择框样式计算属性
const selectionStyle = computed(() => {
  if (!selectionStart.value || !selectionEnd.value) return {};

  const start = selectionStart.value;
  const end = selectionEnd.value;

  const left = Math.min(start.x, end.x);
  const top = Math.min(start.y, end.y);
  const width = Math.abs(end.x - start.x);
  const height = Math.abs(end.y - start.y);

  return {
    left: left + 'px',
    top: top + 'px',
    width: width + 'px',
    height: height + 'px'
  };
});

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

// 地图回调函数
const mapCallback = () => {
  try {
    // 初始化地图
    const center = new window.TMap.LatLng(26.855227, 117.650114);
    mapInstance.value = new window.TMap.Map(document.getElementById(props.idName), {
      center: center,
      zoom: currentZoom.value,
      viewMode: '2D'
    });

    // 创建标记物管理器
    const markerLayer = new window.TMap.MultiMarker({
      map: mapInstance.value,
      styles: {
        // 资源点位样式
        land: new window.TMap.MarkerStyle({
          width: 20,
          height: 20,
          anchor: { x: 10, y: 10 },
          src: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPGNpcmNsZSBjeD0iMTAiIGN5PSIxMCIgcj0iOCIgZmlsbD0iIzIyQzU1RSIgc3Ryb2tlPSJ3aGl0ZSIgc3Ryb2tlLXdpZHRoPSIyIi8+Cjwvc3ZnPg=='
        }),
        water: new window.TMap.MarkerStyle({
          width: 20,
          height: 20,
          anchor: { x: 10, y: 10 },
          src: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPGNpcmNsZSBjeD0iMTAiIGN5PSIxMCIgcj0iOCIgZmlsbD0iIzNCODJGNiIgc3Ryb2tlPSJ3aGl0ZSIgc3Ryb2tlLXdpZHRoPSIyIi8+Cjwvc3ZnPg=='
        }),
        culture: new window.TMap.MarkerStyle({
          width: 20,
          height: 20,
          anchor: { x: 10, y: 10 },
          src: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPGNpcmNsZSBjeD0iMTAiIGN5PSIxMCIgcj0iOCIgZmlsbD0iI0Y1OUUwQiIgc3Ryb2tlPSJ3aGl0ZSIgc3Ryb2tlLXdpZHRoPSIyIi8+Cjwvc3ZnPg=='
        }),
        // 事件点位样式
        event1: new window.TMap.MarkerStyle({
          width: 16,
          height: 16,
          anchor: { x: 8, y: 8 },
          src: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTYiIGhlaWdodD0iMTYiIHZpZXdCb3g9IjAgMCAxNiAxNiIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPGNpcmNsZSBjeD0iOCIgY3k9IjgiIHI9IjciIGZpbGw9IiNFRjQ0NDQiIHN0cm9rZT0id2hpdGUiIHN0cm9rZS13aWR0aD0iMiIvPgo8L3N2Zz4='
        }),
        event2: new window.TMap.MarkerStyle({
          width: 16,
          height: 16,
          anchor: { x: 8, y: 8 },
          src: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTYiIGhlaWdodD0iMTYiIHZpZXdCb3g9IjAgMCAxNiAxNiIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPGNpcmNsZSBjeD0iOCIgY3k9IjgiIHI9IjciIGZpbGw9IiNGNTlFMEIiIHN0cm9rZT0id2hpdGUiIHN0cm9rZS13aWR0aD0iMiIvPgo8L3N2Zz4='
        }),
        event3: new window.TMap.MarkerStyle({
          width: 16,
          height: 16,
          anchor: { x: 8, y: 8 },
          src: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTYiIGhlaWdodD0iMTYiIHZpZXdCb3g9IjAgMCAxNiAxNiIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPGNpcmNsZSBjeD0iOCIgY3k9IjgiIHI9IjciIGZpbGw9IiMzQjgyRjYiIHN0cm9rZT0id2hpdGUiIHN0cm9rZS13aWR0aD0iMiIvPgo8L3N2Zz4='
        }),
        // 高亮资源样式
        highlighted: new window.TMap.MarkerStyle({
          width: 24,
          height: 24,
          anchor: { x: 12, y: 12 },
          src: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjQiIGhlaWdodD0iMjQiIHZpZXdCb3g9IjAgMCAyNCAyNCIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPGNpcmNsZSBjeD0iMTIiIGN5PSIxMiIgcj0iMTAiIGZpbGw9IiNGRjBERjAiIHN0cm9rZT0iI0ZGRiIgc3Ryb2tlLXdpZHRoPSIyIi8+CjxjaXJjbGUgY3g9IjEyIiBjeT0iMTIiIHI9IjQiIGZpbGw9IiNGRkYiLz4KPC9zdmc+'
        })
      }
    });

    // 创建多边形管理器
    const polygonLayer = new window.TMap.MultiPolygon({
      map: mapInstance.value,
      styles: {
        area: new window.TMap.PolygonStyle({
          color: 'rgba(30, 64, 175, 0.1)',
          borderColor: '#1E40AF',
          borderWidth: 2
        }),
        grid: new window.TMap.PolygonStyle({
          color: 'rgba(96, 165, 250, 0.05)',
          borderColor: '#60A5FA',
          borderWidth: 1,
          borderDashArray: [5, 5]
        }),
        selected: new window.TMap.PolygonStyle({
          color: 'rgba(79, 70, 229, 0.2)',
          borderColor: '#4f46e5',
          borderWidth: 3
        })
      }
    });

    // 监听地图事件
    mapInstance.value.on('dblclick', (evt) => {
      handleMapDblClick(evt);
    });

    mapInstance.value.on('click', (evt) => {
      handleMapClick(evt);
    });

    mapInstance.value.on('mousedown', (evt) => {
      handleMapMouseDown(evt);
    });

    mapInstance.value.on('mousemove', (evt) => {
      handleMapMouseMove(evt);
    });

    mapInstance.value.on('mouseup', (evt) => {
      handleMapMouseUp(evt);
    });

    mapInstance.value.on('zoom_changed', () => {
      currentZoom.value = mapInstance.value.getZoom();
      emit('zoomchange', { zoom: currentZoom.value });
    });

    // 触发地图加载完成事件
    emit('mapLoaded');

    // 创建图层
    createLayers(markerLayer, polygonLayer);

  } catch (error) {
    console.error('腾讯地图初始化失败:', error);
    ElMessage.error('地图加载失败');
  }
};

// 创建地图图层
const createLayers = (markerLayer, polygonLayer) => {
  if (!mapInstance.value) return;

  // 清除现有图层
  markers = [];
  polygons = [];

  // 创建行政区划图层
  if (props.areaData.length > 0) {
    props.areaData.forEach(area => {
      const polygon = {
        id: area.id,
        styleId: 'area',
        paths: area.coordinates.map(coord => new window.TMap.LatLng(coord.lat, coord.lng))
      };
      polygons.push(polygon);
    });
  }

  // 创建网格图层
  if (props.gridData.length > 0) {
    props.gridData.forEach(grid => {
      const polygon = {
        id: grid.id,
        styleId: 'grid',
        paths: grid.coordinates.map(coord => new window.TMap.LatLng(coord.lat, coord.lng))
      };
      polygons.push(polygon);
    });
  }

  // 创建资源点位图层
  if (props.resourceData.length > 0 && props.filters.dataTypes?.includes('resource')) {
    props.resourceData.forEach(resource => {
      const isHighlighted = props.highlightedResource && props.highlightedResource.id === resource.id;
      const styleId = isHighlighted ? 'highlighted' : resource.type;

      const marker = {
        id: resource.id,
        styleId: styleId,
        position: new window.TMap.LatLng(resource.x, resource.y),
        properties: {
          type: 'resource',
          data: resource
        }
      };
      markers.push(marker);
    });
  }

  // 创建事件点位图层
  if (props.eventData.length > 0 && props.filters.dataTypes?.includes('event')) {
    props.eventData.forEach(event => {
      const styleId = `event${event.level}`;
      const marker = {
        id: event.id,
        styleId: styleId,
        position: new window.TMap.LatLng(event.x, event.y),
        properties: {
          type: 'event',
          data: event
        }
      };
      markers.push(marker);
    });
  }

  // 更新图层
  if (markerLayer) {
    markerLayer.setGeometries(markers);

    // 绑定点击事件
    markerLayer.on('click', (evt) => {
      const geometry = evt.geometry;
      if (geometry && geometry.properties) {
        emit('pointClick', geometry.properties.data);
      }
    });
  }

  if (polygonLayer) {
    polygonLayer.setGeometries(polygons);

    // 绑定双击事件（下钻）
    polygonLayer.on('dblclick', (evt) => {
      const geometry = evt.geometry;
      if (geometry) {
        const area = props.areaData.find(a => a.id === geometry.id);
        if (area) {
          handleMapDrillDown(area);
        }
      }
    });
  }
};

// 鼠标事件处理
const handleMapMouseDown = (evt) => {
  if (!selectionMode.value) return;

  const rect = document.getElementById(props.idName).getBoundingClientRect();
  selectionStart.value = {
    x: evt.originalEvent.clientX - rect.left,
    y: evt.originalEvent.clientY - rect.top
  };
  selectionEnd.value = selectionStart.value;
};

const handleMapMouseMove = (evt) => {
  if (!selectionMode.value || !selectionStart.value) return;

  const rect = document.getElementById(props.idName).getBoundingClientRect();
  selectionEnd.value = {
    x: evt.originalEvent.clientX - rect.left,
    y: evt.originalEvent.clientY - rect.top
  };
};

const handleMapMouseUp = (evt) => {
  if (!selectionMode.value || !selectionStart.value) return;

  const rect = document.getElementById(props.idName).getBoundingClientRect();
  selectionEnd.value = {
    x: evt.originalEvent.clientX - rect.left,
    y: evt.originalEvent.clientY - rect.top
  };

  // 计算选择区域的地理坐标
  const startPixel = selectionStart.value;
  const endPixel = selectionEnd.value;

  const startLatLng = mapInstance.value.unprojectFromContainer(new window.TMap.Point(startPixel.x, startPixel.y));
  const endLatLng = mapInstance.value.unprojectFromContainer(new window.TMap.Point(endPixel.x, endPixel.y));

  const bounds = {
    minLng: Math.min(startLatLng.lng, endLatLng.lng),
    maxLng: Math.max(startLatLng.lng, endLatLng.lng),
    minLat: Math.min(startLatLng.lat, endLatLng.lat),
    maxLat: Math.max(startLatLng.lat, endLatLng.lat)
  };

  // 筛选在区域内的点位
  const selectedPoints = markers.filter(marker => {
    const pos = marker.position;
    return pos.lng >= bounds.minLng && pos.lng <= bounds.maxLng &&
      pos.lat >= bounds.minLat && pos.lat <= bounds.maxLat;
  });

  emit('selectionChange', {
    bounds: bounds,
    points: selectedPoints.map(p => p.properties.data)
  });

  // 重置选择
  selectionStart.value = null;
  selectionEnd.value = null;
};

// 处理地图双击事件（下钻）
const handleMapDblClick = (evt) => {
  const drillData = {
    lat: evt.latLng.lat,
    lng: evt.latLng.lng,
    currentZoom: currentZoom.value,
    areaId: 'sample-area',
    areaName: '示例区域',
    level: 'province'
  };

  console.log('地图下钻:', drillData);
  emit('drillDown', drillData);
  ElMessage.info(`正在下钻到 ${drillData.areaName}`);
};

// 处理地图单击事件
const handleMapClick = (evt) => {
  if (selectionMode.value) return;

  const pointTypes = ['resource', 'event', 'facility'];
  const pointType = pointTypes[Math.floor(Math.random() * pointTypes.length)];

  const pointData = {
    id: `point-${Date.now()}`,
    name: `${pointType === 'resource' ? '资源' : pointType === 'event' ? '事件' : '设施'}点位`,
    type: pointType,
    status: pointType === 'event' ? 'active' : 'normal',
    level: pointType === 'event' ? Math.floor(Math.random() * 3) + 1 : undefined,
    x: evt.latLng.lat,
    y: evt.latLng.lng,
    areaIds: ['fujian'],
    createTime: new Date().toISOString(),
    extraInfo: {
      '详细信息': '示例点位的额外信息',
      '状态说明': pointType === 'event' ? '待处理' : '正常运行',
      '所属网格': 'G001'
    }
  };

  console.log('点位点击:', pointData);
  emit('pointClick', pointData);
};

// 地图下钻处理
const handleMapDrillDown = (area) => {
  console.log('区域下钻:', area);
  emit('drillDown', area);
  ElMessage.info(`正在下钻到 ${area.name}`);
};

// 地图缩放控制
const zoomIn = () => {
  if (mapInstance.value) {
    mapInstance.value.zoomTo(currentZoom.value + 1);
  }
};

const zoomOut = () => {
  if (mapInstance.value) {
    mapInstance.value.zoomTo(currentZoom.value - 1);
  }
};

// 重置地图
const resetMap = () => {
  if (mapInstance.value) {
    mapInstance.value.setCenter(new window.TMap.LatLng(26.855227, 117.650114));
    mapInstance.value.setZoom(10);
    selectedArea.value = null;
    selectionMode.value = false;
  }
};

// 切换选择模式
const toggleSelectionMode = () => {
  selectionMode.value = !selectionMode.value;
  if (!selectionMode.value) {
    selectionStart.value = null;
    selectionEnd.value = null;
  }
};

// 高亮资源点位
const highlightResource = (resource) => {
  emit('resourceHighlight', resource);
};

// 监听数据变化，更新图层
watch([() => props.areaData, () => props.gridData, () => props.resourceData, () => props.eventData, () => props.filters, () => props.highlightedResource],
  () => {
    if (mapInstance.value) {
      // 重新创建图层
      setTimeout(() => {
        const markerLayer = new window.TMap.MultiMarker({
          map: mapInstance.value,
          styles: {
            land: new window.TMap.MarkerStyle({
              width: 20,
              height: 20,
              anchor: { x: 10, y: 10 },
              src: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPGNpcmNsZSBjeD0iMTAiIGN5PSIxMCIgcj0iOCIgZmlsbD0iIzIyQzU1RSIgc3Ryb2tlPSJ3aGl0ZSIgc3Ryb2tlLXdpZHRoPSIyIi8+Cjwvc3ZnPg=='
            }),
            water: new window.TMap.MarkerStyle({
              width: 20,
              height: 20,
              anchor: { x: 10, y: 10 },
              src: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPGNpcmNsZSBjeD0iMTAiIGN5PSIxMCIgcj0iOCIgZmlsbD0iIzNCODJGNiIgc3Ryb2tlPSJ3aGl0ZSIgc3Ryb2tlLXdpZHRoPSIyIi8+Cjwvc3ZnPg=='
            }),
            culture: new window.TMap.MarkerStyle({
              width: 20,
              height: 20,
              anchor: { x: 10, y: 10 },
              src: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPGNpcmNsZSBjeD0iMTAiIGN5PSIxMCIgcj0iOCIgZmlsbD0iI0Y1OUUwQiIgc3Ryb2tlPSJ3aGl0ZSIgc3Ryb2tlLXdpZHRoPSIyIi8+Cjwvc3ZnPg=='
            }),
            event1: new window.TMap.MarkerStyle({
              width: 16,
              height: 16,
              anchor: { x: 8, y: 8 },
              src: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTYiIGhlaWdodD0iMTYiIHZpZXdCb3g9IjAgMCAxNiAxNiIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPGNpcmNsZSBjeD0iOCIgY3k9IjgiIHI9IjciIGZpbGw9IiNFRjQ0NDQiIHN0cm9rZT0id2hpdGUiIHN0cm9rZS13aWR0aD0iMiIvPgo8L3N2Zz4='
            }),
            event2: new window.TMap.MarkerStyle({
              width: 16,
              height: 16,
              anchor: { x: 8, y: 8 },
              src: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTYiIGhlaWdodD0iMTYiIHZpZXdCb3g9IjAgMCAxNiAxNiIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPGNpcmNsZSBjeD0iOCIgY3k9IjgiIHI9IjciIGZpbGw9IiNGNTlFMEIiIHN0cm9rZT0id2hpdGUiIHN0cm9rZS13aWR0aD0iMiIvPgo8L3N2Zz4='
            }),
            event3: new window.TMap.MarkerStyle({
              width: 16,
              height: 16,
              anchor: { x: 8, y: 8 },
              src: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTYiIGhlaWdodD0iMTYiIHZpZXdCb3g9IjAgMCAxNiAxNiIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPGNpcmNsZSBjeD0iOCIgY3k9IjgiIHI9IjciIGZpbGw9IiMzQjgyRjYiIHN0cm9rZT0id2hpdGUiIHN0cm9rZS13aWR0aD0iMiIvPgo8L3N2Zz4='
            }),
            highlighted: new window.TMap.MarkerStyle({
              width: 24,
              height: 24,
              anchor: { x: 12, y: 12 },
              src: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjQiIGhlaWdodD0iMjQiIHZpZXdCb3g9IjAgMCAyNCAyNCIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPGNpcmNsZSBjeD0iMTIiIGN5PSIxMiIgcj0iMTAiIGZpbGw9IiNGRjBERjAiIHN0cm9rZT0iI0ZGRiIgc3Ryb2tlLXdpZHRoPSIyIi8+CjxjaXJjbGUgY3g9IjEyIiBjeT0iMTIiIHI9IjQiIGZpbGw9IiNGRkYiLz4KPC9zdmc+'
            })
          }
        });

        const polygonLayer = new window.TMap.MultiPolygon({
          map: mapInstance.value,
          styles: {
            area: new window.TMap.PolygonStyle({
              color: 'rgba(30, 64, 175, 0.1)',
              borderColor: '#1E40AF',
              borderWidth: 2
            }),
            grid: new window.TMap.PolygonStyle({
              color: 'rgba(96, 165, 250, 0.05)',
              borderColor: '#60A5FA',
              borderWidth: 1,
              borderDashArray: [5, 5]
            })
          }
        });

        createLayers(markerLayer, polygonLayer);
      }, 100);
    }
  },
  { deep: true }
);

// 初始化地图
onMounted(() => {
  initMap();
});

// 销毁地图
onUnmounted(() => {
  if (mapInstance.value) {
    mapInstance.value.destroy();
  }
  markers = [];
  polygons = [];
});

// 暴露方法给父组件
defineExpose({
  zoomIn,
  zoomOut,
  resetMap,
  highlightResource,
  toggleSelectionMode
});
</script>

<style scoped>
.map-common-container {
  width: 100%;
  height: 100%;
  position: relative;
}

.map-content {
  width: 100%;
  height: 100%;
  border-radius: 6px;
  overflow: hidden;
}

.map-controls {
  position: absolute;
  top: 20px;
  right: 20px;
  z-index: 100;
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.control-btn {
  width: 36px;
  height: 36px;
  border: none;
  background-color: rgba(30, 41, 59, 0.9);
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
  border-radius: 4px;
  cursor: pointer;
  font-size: 18px;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.2s;
  color: #e2e8f0;
  border: 1px solid rgba(0, 204, 255, 0.3);
}

.control-btn:hover {
  background-color: rgba(79, 70, 229, 0.3);
  transform: scale(1.05);
}

.control-btn:active {
  transform: scale(0.95);
}

.control-btn.active {
  background-color: rgba(79, 70, 229, 0.5);
  border-color: #4f46e5;
}

.map-legend {
  position: absolute;
  bottom: 20px;
  left: 20px;
  background: rgba(30, 41, 59, 0.95);
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.3);
  z-index: 100;
  backdrop-filter: blur(10px);
  border: 1px solid rgba(0, 204, 255, 0.3);
  min-width: 180px;
  max-width: 200px;
}

.legend-title {
  font-weight: 600;
  margin-bottom: 12px;
  font-size: 14px;
  color: #4f46e5;
  border-bottom: 1px solid rgba(0, 204, 255, 0.3);
  padding-bottom: 8px;
}

.legend-section {
  margin-bottom: 10px;

  &:last-child {
    margin-bottom: 0;
  }
}

.legend-subtitle {
  font-size: 12px;
  color: #94a3b8;
  margin-bottom: 6px;
  font-weight: 500;
}

.legend-item {
  display: flex;
  align-items: center;
  margin-bottom: 6px;
  font-size: 12px;
  color: #e2e8f0;
}

.legend-icon {
  width: 14px;
  height: 14px;
  margin-right: 8px;
  border-radius: 50%;
  display: inline-block;
  border: 2px solid rgba(30, 41, 59, 0.8);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
}

.legend-icon.resource.land {
  background-color: #22C55E;
}

.legend-icon.resource.water {
  background-color: #3B82F6;
}

.legend-icon.resource.culture {
  background-color: #F59E0B;
}

.legend-icon.event-level1 {
  background-color: #EF4444;
}

.legend-icon.event-level2 {
  background-color: #F59E0B;
}

.legend-icon.event-level3 {
  background-color: #3B82F6;
}

.legend-icon.grid {
  background-color: #6B7280;
  border-radius: 0;
  width: 10px;
  height: 10px;
}

.legend-icon.area {
  background-color: #1E40AF;
  border-radius: 2px;
  width: 10px;
  height: 10px;
}

/* 地图加载动画 */
.map-content {
  animation: fadeIn 0.8s ease-in-out;
}

@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}

/* 选择框样式 */
.selection-rectangle {
  position: absolute;
  border: 2px solid #4f46e5;
  background-color: rgba(79, 70, 229, 0.1);
  z-index: 99;
  pointer-events: none;
}

/* 点位脉冲动画 */
.pulse-fast {
  animation: pulse 0.5s infinite;
}

.pulse-medium {
  animation: pulse 0.67s infinite;
}

.pulse-slow {
  animation: pulse 1s infinite;
}

@keyframes pulse {
  0% {
    transform: scale(1);
    opacity: 1;
  }
  50% {
    transform: scale(1.2);
    opacity: 0.7;
  }
  100% {
    transform: scale(1);
    opacity: 1;
  }
}
</style>
