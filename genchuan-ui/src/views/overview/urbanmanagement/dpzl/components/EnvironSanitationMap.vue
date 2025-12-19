<template>
  <div class="map-container">
    <!-- 地图容器 -->
    <div :id="idName" class="map-common-css" style="width: 100%; height: 100%;"></div>

    <!-- 图例 -->
    <div class="legend">
      <!-- 清运点图例组 -->
      <div class="legend-group">
        <div class="legend-items">
          <div class="legend-item">
            <img :src="markerOverflow" class="legend-icon" alt="满溢" />
            <span>满溢</span>
          </div>
          <div class="legend-item">
            <img :src="markerWaitClear" class="legend-icon" alt="待清运" />
            <span>待清运</span>
          </div>
          <div class="legend-item">
            <img :src="markerCleared" class="legend-icon" alt="已清运" />
            <span>已清运</span>
          </div>
        </div>
      </div>
      <!-- 保洁区域图例组 -->
      <div class="legend-group">
        <div class="legend-items">
          <div class="legend-item">
            <div class="legend-color un-clean"></div>
            <span>未保洁</span>
          </div>
          <div class="legend-item">
            <div class="legend-color cleaning"></div>
            <span>保洁中</span>
          </div>
          <div class="legend-item">
            <div class="legend-color cleaned"></div>
            <span>已保洁</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, defineProps, ref, onUnmounted, watch, defineExpose } from 'vue';

// 图标导入（清运点标记）
import markerOverflow from '@/assets/chart/images/clear-overflow.png'; // 满溢
import markerWaitClear from '@/assets/chart/images/clear-wait.png'; // 待清运
import markerCleared from '@/assets/chart/images/clear-done.png'; // 已清运
import markerGray from '@/assets/chart/images/marker-gray.png'; // 默认

// Props 定义（对齐参考代码写法）
const props = defineProps({
  idName: {
    type: String,
    default: 'environMap',
  },
  clearPointList: { // 清运点列表
    type: Array,
    default: () => []
  },
  cleanAreaList: { // 保洁区域列表
    type: Array,
    default: () => []
  }
});

// 图层实例管理（对齐参考代码的layers对象）
const layers = ref({
  clearPoint: null,   // 清运点标记层
  cleanArea: null     // 保洁区域面图层
});

// 图层点击处理器存储（用于解绑事件）
const layerClickHandlers = ref({
  clearPoint: null,
  cleanArea: null
});

// 响应式变量
const mapInstance = ref(null);
const infoWindow = ref(null);
const mapInitialized = ref(false);

// 自定义事件（传递给父组件）
const emit = defineEmits(['clearPointClick', 'cleanAreaClick']);

// 信息窗关闭事件
const handleInfoWindowClose = () => {
  if (infoWindow.value) {
    infoWindow.value.close();
  }
};

// 清运点点击事件处理（闭包封装，对齐参考代码）
const createClearPointClickHandler = () => {
  return (e) => {
    const { properties } = e.geometry;
    if (properties && infoWindow.value) {
      infoWindow.value.setContent(getClearPointTooltip(properties));
      infoWindow.value.setPosition(e.geometry.position);
      infoWindow.value.open();
      emit('clearPointClick', properties);
    }
  };
};

// 保洁区域点击事件处理（闭包封装，对齐参考代码）
const createCleanAreaClickHandler = () => {
  return (e) => {
    const { properties } = e.geometry;
    if (properties && infoWindow.value) {
      infoWindow.value.setContent(getCleanAreaTooltip(properties));
      // 取面的中心坐标作为信息窗位置（对齐参考代码的坐标处理）
      const center = getPolygonCenter(properties.coords);
      infoWindow.value.setPosition(new TMap.LatLng(center.lat, center.lng));
      infoWindow.value.open();
      emit('cleanAreaClick', properties);
    }
  };
};

// 获取面的中心坐标（优化坐标计算逻辑）
const getPolygonCenter = (coords) => {
  if (!Array.isArray(coords) || coords.length === 0) {
    return { lat: 26.0753, lng: 119.3062 }; // 默认坐标（福州）
  }

  // 兼容参考代码的坐标格式：支持[[x,y],[x,y]] 或 [{x,y},{x,y}]
  let latSum = 0, lngSum = 0, count = 0;
  coords.forEach(coord => {
    let lng, lat;
    if (Array.isArray(coord)) {
      lng = coord[0];
      lat = coord[1];
    } else if (coord.x && coord.y) {
      lng = coord.x;
      lat = coord.y;
    }

    if (typeof lng === 'number' && typeof lat === 'number') {
      lngSum += lng;
      latSum += lat;
      count++;
    }
  });

  return {
    lat: count > 0 ? latSum / count : 26.0753,
    lng: count > 0 ? lngSum / count : 119.3062
  };
};

// 重置图层（销毁旧图层，对齐参考代码的resetLayers）
const resetLayers = () => {
  // 解绑图层点击事件
  Object.keys(layerClickHandlers.value).forEach(layerType => {
    const layer = layers.value[layerType];
    const handler = layerClickHandlers.value[layerType];
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
    clearPoint: null,
    cleanArea: null
  };
  layerClickHandlers.value = {
    clearPoint: null,
    cleanArea: null
  };
};

// 初始化地图（完全对齐参考代码的腾讯地图加载方式）
const initMap = () => {
  const callbackName = `initMap_${props.idName}`;
  const script = document.createElement('script');
  // 复用参考代码的腾讯地图key
  script.src = `https://map.qq.com/api/gljs?v=1.exp&key=QTQBZ-F3RWW-JJJRV-YNPA5-ZIKDK-3SBNO&callback=${callbackName}`;
  script.async = true;

  window[callbackName] = () => {
    mapCallback();
    delete window[callbackName];
  };

  document.head.appendChild(script);
};

// 地图初始化回调（核心逻辑，对齐参考代码）
const mapCallback = () => {
  const mapContainer = document.getElementById(props.idName);
  if (!mapContainer) {
    console.error(`地图容器不存在：${props.idName}`);
    return;
  }

  // 创建地图实例（对齐参考代码的初始化参数）
  const map = new TMap.Map(mapContainer, {
    center: new TMap.LatLng(26.0753, 119.3062),
    zoom: 15,
    mapStyleId: 'style1'
  });
  mapInstance.value = map;
  mapInitialized.value = true;

  // 初始化信息窗（对齐参考代码的配置）
  infoWindow.value = new TMap.InfoWindow({
    map: map,
    position: new TMap.LatLng(0, 0),
    content: '',
    offset: { x: 0, y: -30 }, // 对齐参考代码的偏移量
    visible: false
  });
  infoWindow.value.on('close', handleInfoWindowClose);

  // 初始创建图层（先重置，再创建，再绑定事件）
  if (props.clearPointList.length > 0 || props.cleanAreaList.length > 0) {
    resetLayers();
    createClearPointMarkers(map);
    createCleanAreaPolygons(map);
    bindLayerClickEvents();
  }
};

// 绑定图层点击事件（对齐参考代码的bindLayerClickEvents）
const bindLayerClickEvents = () => {
  // 绑定清运点点击事件
  if (layers.value.clearPoint && !layerClickHandlers.value.clearPoint) {
    const handler = createClearPointClickHandler();
    layerClickHandlers.value.clearPoint = handler;
    layers.value.clearPoint.on('click', handler);
  }

  // 绑定保洁区域点击事件
  if (layers.value.cleanArea && !layerClickHandlers.value.cleanArea) {
    const handler = createCleanAreaClickHandler();
    layerClickHandlers.value.cleanArea = handler;
    layers.value.cleanArea.on('click', handler);
  }
};

// 创建清运点标记（完全对齐参考代码的创建逻辑）
const createClearPointMarkers = (map) => {
  const markerData = [];
  if (Array.isArray(props.clearPointList)) {
    props.clearPointList.forEach((item, index) => {
      // 坐标字段：对齐参考代码的coord_x=纬度，coord_y=经度
      const lat = item.coord_x;
      const lng = item.coord_y;

      if (item && typeof lng === 'number' && typeof lat === 'number') {
        let styleId = 'default';
        const clearStatus = item.clear_status || '未知';

        // 匹配清运点状态样式
        if (clearStatus === '满溢') {
          styleId = 'overflow';
        } else if (clearStatus === '待清运') {
          styleId = 'waitClear';
        } else if (clearStatus === '已清运') {
          styleId = 'cleared';
        }

        markerData.push({
          id: `clearPoint-${item.clear_point_id || index}`,
          styleId: styleId,
          position: new TMap.LatLng(lat, lng),
          properties: { ...item }
        });
      } else {
        console.warn(`第${index}条清运点数据坐标无效`, item);
      }
    });
  }

  // 创建标记层（对齐参考代码的MultiMarker创建方式）
  if (markerData.length > 0) {
    layers.value.clearPoint = new TMap.MultiMarker({
      id: 'clear-point-layer', // 对齐参考代码的id配置
      map: map,
      styles: getClearPointStyles(),
      geometries: markerData
    });
    console.log('清运点标记生成成功，数量：', markerData.length);
  } else {
    console.warn('无有效清运点标记数据');
  }
};

// 创建保洁区域面（核心修正：适配接口返回的polygon_coords字段）
const createCleanAreaPolygons = (map) => {
  const polygonData = [];
  if (Array.isArray(props.cleanAreaList)) {
    props.cleanAreaList.forEach((item, index) => {
      // 核心修正：读取接口返回的polygon_coords字段（兼容旧的coords字段）
      let coords = item.polygon_coords || item.coords || [];
      if (!Array.isArray(coords) || coords.length === 0) {
        console.warn(`第${index}条保洁区域数据坐标无效`, item);
        return;
      }

      // 坐标转换：统一转为TMap.LatLng数组（对齐参考代码的path格式）
      const latLngs = coords.map(coord => {
        let lng, lat;
        if (Array.isArray(coord)) {
          lng = coord[0];
          lat = coord[1];
        } else if (coord.x && coord.y) {
          lng = coord.x;
          lat = coord.y;
        }
        return new TMap.LatLng(lat, lng); // 参考代码标准：LatLng(纬度, 经度)
      });

      // 过滤无效坐标（面最少需要3个有效点才能显示）
      const validLatLngs = latLngs.filter(latlng =>
        !isNaN(latlng.getLat()) && !isNaN(latlng.getLng())
      );
      if (validLatLngs.length < 3) { // 面最少需要3个点
        console.warn(`第${index}条保洁区域坐标点数不足`, item);
        return;
      }

      // 匹配保洁区域状态样式
      let styleId = 'default';
      const cleanStatus = item.clean_status || '未知';
      if (cleanStatus === '未保洁') {
        styleId = 'unClean';
      } else if (cleanStatus === '保洁中') {
        styleId = 'cleaning';
      } else if (cleanStatus === '已保洁') {
        styleId = 'cleaned';
      }

      polygonData.push({
        id: `cleanArea-${item.clean_area_id || index}`,
        styleId: styleId,
        paths: [validLatLngs], // 参考代码标准：paths是二维数组
        properties: { ...item, coords: item.polygon_coords || item.coords } // 保留原始坐标
      });
    });
  }

  // 创建面图层（对齐参考代码的MultiPolygon创建方式）
  if (polygonData.length > 0) {
    layers.value.cleanArea = new TMap.MultiPolygon({
      id: 'clean-area-layer', // 对齐参考代码的id配置
      map: map,
      styles: getCleanAreaStyles(), // 样式对象key对应styleId
      geometries: polygonData
    });
    console.log('保洁区域面生成成功，数量：', polygonData.length);
  } else {
    console.warn('无有效保洁区域面数据');
  }
};

// 清运点信息窗内容（对齐参考代码的HTML拼接风格）
const getClearPointTooltip = (properties) => {
  // 提取公共样式（对齐参考代码）
  const labelStyle = 'width: 100px; text-align: right; font-weight: bold; margin-right: 6px; flex-shrink: 0;';
  const valueStyle = 'flex: 1; text-align: left; word-break: break-all;';
  const rowStyle = 'display: flex; align-items: center; margin: 8px 0;';
  const statusColor = properties.clear_status === '满溢'
    ? 'red'
    : properties.clear_status === '待清运'
      ? 'orange'
      : 'green';

  return `
    <div style="padding: 12px; font-size: 14px; color: #333; background: white; border: 1px solid #ccc; min-width: 320px; border-radius: 4px;">
      <div style="margin-bottom: 12px; font-weight: bold; color: #00c6ff; text-align: center; border-bottom: 1px solid #eee; padding-bottom: 8px;">清运点信息</div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">清运点ID：</span>
        <span style="${valueStyle}">${properties.clear_point_id || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">名称：</span>
        <span style="${valueStyle}">${properties.point_name || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">清运状态：</span>
        <span style="${valueStyle}; color: ${statusColor}; font-weight: 500;">
          ${properties.clear_status || '未知'}
        </span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">剩余桶数：</span>
        <span style="${valueStyle}${properties.bucket_remaining > 5 ? '; color: red;' : ''}">${properties.bucket_remaining || 0}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">负责人：</span>
        <span style="${valueStyle}">${properties.responsible_person || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">计划清运：</span>
        <span style="${valueStyle}${properties.is_timeout ? '; color: red;' : ''}">${properties.plan_clear_time || '未知'}</span>
      </div>
    </div>
  `;
};

// 保洁区域信息窗内容（对齐参考代码的HTML拼接风格）
const getCleanAreaTooltip = (properties) => {
  // 提取公共样式（对齐参考代码）
  const labelStyle = 'width: 100px; text-align: right; font-weight: bold; margin-right: 6px; flex-shrink: 0;';
  const valueStyle = 'flex: 1; text-align: left; word-break: break-all;';
  const rowStyle = 'display: flex; align-items: center; margin: 8px 0;';
  const statusColor = properties.clean_status === '未保洁'
    ? 'red'
    : properties.clean_status === '保洁中'
      ? 'orange'
      : 'green';

  return `
    <div style="padding: 12px; font-size: 14px; color: #333; background: white; border: 1px solid #ccc; min-width: 320px; border-radius: 4px;">
      <div style="margin-bottom: 12px; font-weight: bold; color: #ec4899; text-align: center; border-bottom: 1px solid #eee; padding-bottom: 8px;">保洁区域信息</div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">区域ID：</span>
        <span style="${valueStyle}">${properties.clean_area_id || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">名称：</span>
        <span style="${valueStyle}">${properties.area_name || '未知'}</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">保洁状态：</span>
        <span style="${valueStyle}; color: ${statusColor}; font-weight: 500;">
          ${properties.clean_status || '未知'}
        </span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">覆盖率：</span>
        <span style="${valueStyle}">${properties.coverage_rate || 0}%</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">总面积：</span>
        <span style="${valueStyle}">${(properties.total_area / 10000).toFixed(2) || 0}万㎡</span>
      </div>
      <div style="${rowStyle}">
        <span style="${labelStyle}">保洁队：</span>
        <span style="${valueStyle}">${properties.responsible_team || '未知'}</span>
      </div>
    </div>
  `;
};

// 清运点标记样式（对齐参考代码的样式定义方式）
const getClearPointStyles = () => {
  return {
    'overflow': new TMap.MarkerStyle({ // 满溢
      width: 40, // 对齐参考代码的标记尺寸
      height: 40,
      anchor: { x: 20, y: 40 }, // 对齐参考代码的锚点
      src: markerOverflow
    }),
    'waitClear': new TMap.MarkerStyle({ // 待清运
      width: 40,
      height: 40,
      anchor: { x: 20, y: 40 },
      src: markerWaitClear
    }),
    'cleared': new TMap.MarkerStyle({ // 已清运
      width: 40,
      height: 40,
      anchor: { x: 20, y: 40 },
      src: markerCleared
    }),
    'default': new TMap.MarkerStyle({ // 默认
      width: 40,
      height: 40,
      anchor: { x: 20, y: 40 },
      src: markerGray
    })
  };
};

// 保洁区域面样式（核心修复：对齐参考代码的PolygonStyle配置）
const getCleanAreaStyles = () => {
  return {
    'unClean': new TMap.PolygonStyle({ // 未保洁
      color: 'rgba(255, 77, 109, 0.6)', // 红
      showBorder: true, // 对齐参考代码的边框配置
      borderColor: 'rgba(255, 77, 109, 1)',
      borderWidth: 2
    }),
    'cleaning': new TMap.PolygonStyle({ // 保洁中
      color: 'rgba(247, 147, 30, 0.6)', // 橙
      showBorder: true,
      borderColor: 'rgba(247, 147, 30, 1)',
      borderWidth: 2
    }),
    'cleaned': new TMap.PolygonStyle({ // 已保洁
      color: 'rgba(52, 211, 153, 0.6)', // 绿
      showBorder: true,
      borderColor: 'rgba(52, 211, 153, 1)',
      borderWidth: 2
    }),
    'default': new TMap.PolygonStyle({ // 默认
      color: 'rgba(150, 150, 150, 0.4)',
      showBorder: true,
      borderColor: 'rgba(150, 150, 150, 1)',
      borderWidth: 2
    })
  };
};

// 刷新地图（对齐参考代码的刷新逻辑）
const refreshMap = () => {
  if (mapInitialized.value && mapInstance.value) {
    resetLayers();
    createClearPointMarkers(mapInstance.value);
    createCleanAreaPolygons(mapInstance.value);
    bindLayerClickEvents();
  } else {
    initMap();
  }
};

// 监听数据变化更新图层（对齐参考代码的watch逻辑）
watch(
  () => [props.clearPointList, props.cleanAreaList],
  ([newClearPoints, newCleanAreas]) => {
    console.log('地图数据更新：清运点', newClearPoints.length, '保洁区域', newCleanAreas.length);
    if (mapInitialized.value && mapInstance.value) {
      resetLayers();
      createClearPointMarkers(mapInstance.value);
      createCleanAreaPolygons(mapInstance.value);
      bindLayerClickEvents();
    }
  },
  { deep: true }
);

// 生命周期（对齐参考代码的写法）
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
  mapInitialized.value = false;
});

// 暴露方法给父组件（对齐参考代码的defineExpose）
defineExpose({
  refreshMap
});
</script>

<style scoped>
/* 基础布局（完全对齐参考代码的样式结构） */
.map-container {
  position: relative;
  width: 100%;
  height: 100%;
}

.map-common-css {
  width: 100%;
  height: 90%; /* 对齐参考代码的高度比例 */
  margin: 0 auto;
  border-radius: 8px;
  overflow: hidden;
}

/* 图例样式（对齐参考代码的图例结构） */
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

/* 图例颜色块（对齐参考代码的样式） */
.legend-color {
  width: 1vw;
  height: 2vh;
  margin-right: 0.5vw;
  border-radius: 2px;
  border: 1px solid rgba(0,0,0,0.1);
}
.un-clean { background: rgba(255, 77, 109, 0.6); }
.cleaning { background: rgba(247, 147, 30, 0.6); }
.cleaned { background: rgba(52, 211, 153, 0.6); }

/* 图例图标（对齐参考代码的样式） */
.legend-icon {
  width: 1.2vw;
  height: 2vh;
  margin-right: 0.2vw;
  object-fit: contain;
}
</style>
