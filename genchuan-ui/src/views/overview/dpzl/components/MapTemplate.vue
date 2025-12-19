<template>
  <!-- 外层容器：强制约束所有子元素，防止溢出 -->
  <div class="map-container" style="width: 100%; height: 100%; position: relative;">
    <!-- 地图核心容器 -->
    <div :id="idName" style="width: 100%; height: 100%; overflow: hidden;"></div>

    <!-- 绕点环绕控制按钮（固定左上角，参考示例样式） -->
    <div class="orbit-control-panel">
      <button @click="toggleOrbitAnimation" class="control-btn">
        {{ orbitStatus.playing ? '暂停环绕' : '开始环绕' }}
      </button>
    </div>
  </div>
</template>

<script setup>
import {ref, onMounted, onUnmounted, watch, nextTick} from 'vue';
import {ElMessage} from 'element-plus';
import {getSceneMapData, MAP_DEFAULT_CONFIG} from '@/api/overview/dpzl/map.js';

// 接收父组件传参
const props = defineProps({
  idName: {type: String, default: 'map-container'},
  sceneKey: {type: String, required: true},
  previewMode: {type: Boolean, default: false},
  previewConfig: {
    type: Object,
    default: () => ({
      orbitAnimation: {
        ...MAP_DEFAULT_CONFIG.orbitAnimation,
        enable: true,
        loop: true,
        center: null
      },
      defaultIconUrl: MAP_DEFAULT_CONFIG.defaultIconUrl,
      defaultIconSize: MAP_DEFAULT_CONFIG.defaultIconSize,
      selectedFieldsConfig: MAP_DEFAULT_CONFIG.selectedFieldsConfig,
      statusIconConfigList: MAP_DEFAULT_CONFIG.statusIconConfigList,
      legendConfig: MAP_DEFAULT_CONFIG.legendConfig,
      mapCenter: MAP_DEFAULT_CONFIG.mapCenter
    })
  }
});

// 核心数据
const mapInstance = ref(null);
const markerLayer = ref(null);
const pointData = ref([]);
const legendDom = ref(null);
const infoWindowRef = ref(null);
const MAP_KEY = 'QTQBZ-F3RWW-JJJRV-YNPA5-ZIKDK-3SBNO';

// 手动动画状态：强制初始为播放中（核心修改1）
const orbitStatus = ref({
  playing: true, // 初始就是播放状态 → 按钮初始文字「暂停环绕」
  currentRotation: 0,
  animationFrameId: null,
  isInited: false // 标记动画是否已初始化，避免重复启动
});

// 防抖函数
const debounce = (fn, delay = 300) => {
  let timer = null;
  return (...args) => {
    if (timer) clearTimeout(timer);
    timer = setTimeout(() => fn(...args), delay);
  };
};

// 1. 加载腾讯地图SDK
const loadTMap = () => {
  return new Promise((resolve, reject) => {
    if (window.TMap) return resolve(window.TMap);
    const script = document.createElement('script');
    script.src = `https://map.qq.com/api/gljs?v=1.exp&key=${MAP_KEY}`;
    script.async = true;
    script.onload = () => resolve(window.TMap);
    script.onerror = () => reject(new Error('腾讯地图SDK加载失败'));
    document.head.appendChild(script);
  });
};

// 2. 获取点位数据
const getPointData = async () => {
  try {
    const res = await getSceneMapData(props.sceneKey);
    pointData.value = res.list || [];
    if (!props.previewMode) {
      ElMessage.success(`加载到 ${pointData.value.length} 个点位`);
    }
  } catch (e) {
    // ElMessage.error(`加载点位数据失败：${e.message}`);
    console.error('点位数据错误：', e);
  }
};

// 3. 移除旧图例
const removeOldLegend = () => {
  if (legendDom.value) {
    legendDom.value.remove();
    legendDom.value = null;
  }
};

// 4. 销毁信息窗
const destroyInfoWindow = () => {
  if (infoWindowRef.value) {
    infoWindowRef.value.close();
    infoWindowRef.value.destroy();
    infoWindowRef.value = null;
  }
};

// 5. 构建信息窗内容
const buildInfoWindowContent = (item) => {
  const {selectedFieldsConfig = []} = props.previewConfig;
  if (!selectedFieldsConfig.length) {
    return `<div style="padding: 10px; font-size: 12px;">暂无配置字段</div>`;
  }

  let contentHtml = `<div style="padding: 8px 0; min-width: 200px;">`;
  selectedFieldsConfig.forEach(field => {
    const fieldKey = field.key;
    const fieldLabel = field.label;
    let fieldValue = item[fieldKey] || '-';

    if (field.renderType === 'status' && field.statusMap) {
      fieldValue = field.statusMap[fieldValue] || fieldValue;
    }

    if (field.renderType === 'coord' && typeof fieldValue === 'object') {
      fieldValue = `${fieldValue.lat?.toFixed(6) || '-'}, ${fieldValue.lng?.toFixed(6) || '-'}`;
    }

    contentHtml += `
      <div style="display: flex; justify-content: space-between; padding: 4px 8px; font-size: 12px;">
        <span style="color: #666; font-weight: 500;">${fieldLabel}：</span>
        <span style="color: #333;">${fieldValue}</span>
      </div>
    `;
  });
  contentHtml += `</div>`;

  return contentHtml;
};

// 核心：手动绕点动画循环（防溢出优化）
const orbitLoop = () => {
  if (!mapInstance.value || !orbitStatus.value.playing) return;

  const {orbitAnimation} = props.previewConfig;
  const {center, rotateSpeed, loop} = orbitAnimation;
  if (!center || !center.lat || !center.lng) return;

  const TMap = window.TMap;
  const orbitCenter = new TMap.LatLng(center.lat, center.lng);

  // 1. 计算新旋转角度（限制在0-360°）
  let newRotation = orbitStatus.value.currentRotation + rotateSpeed;
  if (newRotation >= 360) {
    if (!loop) {
      stopOrbitAnimation();
      return;
    }
    newRotation = 0;
  }

  // 2. 关键：强制锁定中心点+约束视图范围，防止地图偏移出容器
  mapInstance.value.setCenter(orbitCenter); // 固定中心点
  mapInstance.value.setRotation(newRotation); // 仅更新旋转角
  // 额外防溢出：强制刷新视图尺寸
  mapInstance.value.resize();

  // 3. 更新状态+继续下一帧
  orbitStatus.value.currentRotation = newRotation;
  orbitStatus.value.animationFrameId = requestAnimationFrame(orbitLoop);
};

// 启动绕点动画（核心修改2：无延迟启动，且仅初始化一次）
const startOrbitAnimation = () => {
  // 避免重复启动
  if (orbitStatus.value.isInited || !mapInstance.value) return;

  const {orbitAnimation} = props.previewConfig;
  // 强制启用动画
  orbitAnimation.enable = true;

  const TMap = window.TMap;
  // 确定环绕中心点
  let center = orbitAnimation.center;
  if (!center || !center.lat || !center.lng) {
    // 兜底：用点位第一个坐标/地图默认中心
    center = pointData.value.length > 0
      ? {lat: pointData.value[0].lat, lng: pointData.value[0].lng}
      : {lat: 26.855237, lng: 118.000000};
    orbitAnimation.center = center; // 回填到配置中
  }

  const orbitCenter = new TMap.LatLng(center.lat, center.lng);
  // 初始化地图状态（强制约束在容器内）
  mapInstance.value.setZoom(orbitAnimation.zoom || 18);
  mapInstance.value.setPitch(orbitAnimation.pitch || 40);
  mapInstance.value.setCenter(orbitCenter);
  mapInstance.value.setRotation(orbitStatus.value.currentRotation || 0);
  mapInstance.value.resize(); // 强制适配容器尺寸

  // 标记已初始化，避免重复启动
  orbitStatus.value.isInited = true;
  // 启动循环（playing已为true，无需再次赋值）
  orbitLoop();
  console.log('手动绕点动画已启动', {center, rotateSpeed: orbitAnimation.rotateSpeed});
};

// 停止绕点动画
const stopOrbitAnimation = () => {
  orbitStatus.value.playing = false;
  orbitStatus.value.isInited = false; // 重置初始化标记
  if (orbitStatus.value.animationFrameId) {
    cancelAnimationFrame(orbitStatus.value.animationFrameId);
    orbitStatus.value.animationFrameId = null;
  }
  console.log('手动绕点动画已停止');
};

// 切换播放/暂停（核心按钮点击事件）
const toggleOrbitAnimation = () => {
  if (orbitStatus.value.playing) {
    stopOrbitAnimation();
  } else {
    orbitStatus.value.playing = true; // 先改状态，再启动
    startOrbitAnimation();
  }
};

// 8. 渲染标注点（仅修改此函数，适配多状态字段图标匹配）
const renderMarkers = (TMap) => {
  if (!mapInstance.value || pointData.value.length === 0) return;

  if (markerLayer.value) {
    markerLayer.value.destroy();
    markerLayer.value = null;
  }

  removeOldLegend();
  destroyInfoWindow();

  // 构建标注点样式
  const defaultIconUrl = props.previewConfig.defaultIconUrl || 'https://img.icons8.com/fluency/64/ff0000/marker.png';
  const iconSize = props.previewConfig.defaultIconSize || {width: 40, height: 40};

  const markerStyles = {
    default: new TMap.MarkerStyle({
      width: iconSize.width,
      height: iconSize.height,
      anchor: {x: iconSize.width / 2, y: iconSize.height / 2},
      src: defaultIconUrl
    })
  };

  // 补充状态样式（修改：区分不同字段的同值状态）
  if (props.previewConfig.statusIconConfigList && props.previewConfig.statusIconConfigList.length) {
    props.previewConfig.statusIconConfigList.forEach(item => {
      if (item.icon && item.rawValue && item.fieldKey) {
        // 样式ID改为「字段名_值」，避免不同字段同值冲突
        markerStyles[`${item.fieldKey}_${item.rawValue}`] = new TMap.MarkerStyle({
          width: iconSize.width,
          height: iconSize.height,
          anchor: {x: iconSize.width / 2, y: iconSize.height / 2},
          src: item.icon
        });
      }
    });
  }

  // 构建点位数据（核心修改：遍历所有status字段，匹配有值的字段）
  const geometries = pointData.value.map(item => {
    let styleId = 'default';
    const {statusIconConfigList, selectedFieldsConfig} = props.previewConfig;

    if (statusIconConfigList && statusIconConfigList.length && selectedFieldsConfig.length) {
      // 第一步：获取所有status类型的字段（如streetlight_status、manhole_status等）
      const statusFieldKeys = selectedFieldsConfig
        .filter(f => f.renderType === 'status')
        .map(f => f.key);

      // 第二步：遍历所有status字段，找到当前点位有值的那个
      for (const fieldKey of statusFieldKeys) {
        const fieldValue = item[fieldKey];
        if (fieldValue) {
          // 第三步：匹配该字段+值对应的图标规则
          const statusItem = statusIconConfigList.find(
            s => s.fieldKey === fieldKey && s.rawValue === fieldValue
          );
          if (statusItem) {
            styleId = `${statusItem.fieldKey}_${statusItem.rawValue}`;
            break; // 找到第一个有值的状态字段即停止
          }
        }
      }
    }

    return {
      id: item.uniqueId,
      styleId: styleId,
      position: new TMap.LatLng(item.lat, item.lng),
      properties: item
    };
  });

  // 创建图层
  markerLayer.value = new TMap.MultiMarker({
    id: 'marker-layer',
    map: mapInstance.value,
    styles: markerStyles,
    geometries: geometries
  });

  // 绑定点击事件
  markerLayer.value.on('click', (evt) => {
    const item = evt.geometry.properties;
    const position = evt.geometry.position;

    destroyInfoWindow();

    infoWindowRef.value = new TMap.InfoWindow({
      map: mapInstance.value,
      position: position,
      content: buildInfoWindowContent(item),
      offset: {x: 0, y: -30},
      autoClose: true
    });

    infoWindowRef.value.open();
  });

  // 添加图例
  if (props.previewConfig.legendConfig?.show) {
    addLegend(TMap, props.previewConfig.legendConfig);
  }

  // 地图居中
  setMapCenter(TMap);

  // 核心修改4：渲染完成后立即启动动画（无previewMode判断）
  nextTick(() => startOrbitAnimation());
};

// 9. 添加图例
const addLegend = (TMap, legendConfig) => {
  if (!mapInstance.value || !legendConfig.items || legendConfig.items.length === 0) return;

  legendDom.value = document.createElement('div');
  legendDom.value.style.position = 'absolute';
  legendDom.value.style.background = 'rgba(255,255,255,0.9)';
  legendDom.value.style.padding = '10px';
  legendDom.value.style.borderRadius = '4px';
  legendDom.value.style.boxShadow = '0 2px 12px 0 rgba(0,0,0,0.1)';
  legendDom.value.style.zIndex = '100';
  legendDom.value.style.fontSize = '12px';

  switch (legendConfig.position) {
    case 'bottomLeft':
      legendDom.value.style.bottom = '20px';
      legendDom.value.style.left = '20px';
      break;
    case 'bottomRight':
      legendDom.value.style.bottom = '20px';
      legendDom.value.style.right = '20px';
      break;
    case 'topLeft':
      legendDom.value.style.top = '60px'; // 避开控制按钮
      legendDom.value.style.left = '20px';
      break;
    case 'topRight':
      legendDom.value.style.top = '20px';
      legendDom.value.style.right = '20px';
      break;
  }

  let legendHtml = '<div style="font-size: 14px; font-weight: 500; margin-bottom: 8px; color: #333;">图例</div>';
  legendConfig.items.forEach(item => {
    if (item.text && item.icon) {
      legendHtml += `
        <div style="display: flex; align-items: center; margin-bottom: 4px; color: #666;">
          <img src="${item.icon}" style="width: 16px; height: 16px; margin-right: 8px;" />
          <span>${item.text}</span>
        </div>
      `;
    }
  });
  legendDom.value.innerHTML = legendHtml;

  const mapDom = document.getElementById(props.idName);
  mapDom.appendChild(legendDom.value);
};

// 10. 设置地图中心
const setMapCenter = (TMap) => {
  let centerLat = props.previewConfig.mapCenter?.lat || 26.855237;
  let centerLng = props.previewConfig.mapCenter?.lng || 118.000000;

  if (!props.previewConfig.mapCenter && pointData.value.length > 0) {
    centerLat = pointData.value[0].lat;
    centerLng = pointData.value[0].lng;
  }

  mapInstance.value.setCenter(new TMap.LatLng(centerLat, centerLng));
  mapInstance.value.setZoom(props.previewConfig.orbitAnimation?.zoom || 18);
};

// 11. 销毁地图实例
const destroyMap = () => {
  stopOrbitAnimation(); // 停止手动动画
  destroyInfoWindow();
  removeOldLegend();
  if (markerLayer.value) {
    markerLayer.value.destroy();
    markerLayer.value = null;
  }
  if (mapInstance.value) {
    mapInstance.value.destroy();
    mapInstance.value = null;
  }
  // 重置状态
  orbitStatus.value = {
    playing: true,
    currentRotation: 0,
    animationFrameId: null,
    isInited: false
  };
};

// 12. 初始化地图
const initMap = debounce(async () => {
  destroyMap();

  try {
    await getPointData();
    const TMap = await loadTMap();

    const mapDom = document.getElementById(props.idName);
    if (!mapDom) throw new Error('地图容器不存在');
    mapDom.style.width = '100%';
    mapDom.style.height = '100%';
    mapDom.style.overflow = 'hidden';

    // 创建地图实例（启用3D+强制约束）
    mapInstance.value = new TMap.Map(mapDom, {
      center: new TMap.LatLng(26.855237, 118.000000),
      zoom: 16,
      pitch: 0,
      rotation: 0,
      mapStyleId: 'style1',
      enablePitch: true,
      enableRotate: true,
      minZoom: 3,
      maxZoom: 20,
      minPitch: 0,
      maxPitch: 80
    });

    renderMarkers(TMap);
  } catch (e) {
    console.error('地图初始化错误：', e);
  }
}, 300);

// 监听props变化
watch([() => props.sceneKey, () => props.previewConfig], () => {
  if (props.sceneKey) initMap();
}, {immediate: true, deep: true});

// 生命周期
onMounted(() => {
  if (props.sceneKey) {
    initMap();
  }
});

onUnmounted(() => {
  destroyMap(); // 内部已包含停止动画
});

// 暴露方法给父组件调用
defineExpose({
  startOrbitAnimation,
  stopOrbitAnimation,
  toggleOrbitAnimation,
  orbitStatus
});
</script>

<style scoped>
/* 隐藏腾讯地图默认控件 */
:deep(.tmap-control) {
  display: none !important;
}

:deep(#tmap-logo) {
  display: none !important;
}

/* 信息窗样式优化 */
:deep(.tmap-info-window) {
  border-radius: 8px !important;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.15) !important;
  border: none !important;
}

:deep(.tmap-info-window-close) {
  top: 8px !important;
  right: 8px !important;
  width: 16px !important;
  height: 16px !important;
  font-size: 14px !important;
  color: #999 !important;
}

:deep(.tmap-info-window-close:hover) {
  color: #666 !important;
  background: #f5f5f5 !important;
  border-radius: 50% !important;
}

/* 绕点控制按钮样式（参考示例） */
.orbit-control-panel {
  position: absolute;
  top: 10px;
  left: 10px;
  z-index: 1000; /* 确保在最上层 */
}

.control-btn {
  border: none;
  background: #409eff;
  color: white;
  padding: 6px 12px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  margin-bottom: 8px;
  transition: background 0.2s;
}

.control-btn:hover {
  background: #66b1ff;
}

/* 强制地图容器不溢出 */
:deep(#map-container) {
  width: 100% !important;
  height: 100% !important;
  overflow: hidden !important;
  position: relative !important;
}
</style>
