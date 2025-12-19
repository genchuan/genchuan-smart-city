<template>
  <div class="map-container">
    <div
      :id="idName"
      :style="{
        width: '100%',
        height: '90%',
        marginLeft: 'auto',
        marginRight: 'auto'
      }"
      class="map-common-css"
    ></div>

    <!-- 图例：教师、校车、学校 -->
    <div class="legend">
      <div class="legend-items">
        <div class="legend-item">
          <img :src="getLegendIcon('teacher')" class="legend-icon" alt="教师" />
          <span>教师</span>
        </div>
        <div class="legend-item">
          <img :src="getLegendIcon('schoolbus')" class="legend-icon" alt="校车" />
          <span>校车</span>
        </div>
        <div class="legend-item">
          <img :src="getLegendIcon('school')" class="legend-icon" alt="学校" />
          <span>学校</span>
        </div>
      </div>
    </div>

    <!-- 信息弹窗  -->
    <div v-if="showInfoWindow && selectedMarker" class="map-info-window-risk" :style="infoWindowStyle">
      <div class="info-window-content">
        <div class="info-header">
          <h4>教育信息</h4>
          <el-icon class="close-btn" @click.stop="closeInfoWindow"><Close /></el-icon>
        </div>
        <div class="info-body">
          <div class="info-row">
            <span class="info-label">名称:</span>
            <span class="info-value">{{ selectedMarker.properties.title }}</span>
          </div>
          <div class="info-row">
            <span class="info-label">类型:</span>
            <span class="info-value type-badge" :class="getDataTypeClass(selectedMarker.properties.dataType)">
              {{ getDataTypeName(selectedMarker.properties.dataType) }}
            </span>
          </div>
          <div class="info-row">
            <span class="info-label">状态:</span>
            <span class="info-value status-badge" :class="getStatusClass(selectedMarker.properties.status)">
              {{ getStatusName(selectedMarker.properties.status) }}
            </span>
          </div>
          <div v-if="selectedMarker.properties.team" class="info-row">
            <span class="info-label">所属教研组:</span>
            <span class="info-value">{{ selectedMarker.properties.team }}</span>
          </div>
          <div v-if="selectedMarker.properties.school" class="info-row">
            <span class="info-label">所在学校:</span>
            <span class="info-value">{{ selectedMarker.properties.school }}</span>
          </div>
          <div v-if="selectedMarker.properties.type" class="info-row">
            <span class="info-label">学校类型:</span>
            <span class="info-value">{{ selectedMarker.properties.type }}</span>
          </div>
          <div v-if="selectedMarker.properties.route" class="info-row">
            <span class="info-label">校车路线:</span>
            <span class="info-value">{{ selectedMarker.properties.route }}</span>
          </div>
          <div class="info-row">
            <span class="info-label">位置:</span>
            <span class="info-value coordinate">
              {{ selectedMarker.position.lat.toFixed(6) }}, {{ selectedMarker.position.lng.toFixed(6) }}
            </span>
          </div>

          <!-- 详细信息区域 -->
          <div v-if="selectedMarker.properties.originalData && hasExtraInfo(selectedMarker.properties.originalData)" class="extra-info">
            <div class="extra-info-title">详细信息</div>
            <div class="info-grid">
              <div class="grid-item" v-if="selectedMarker.properties.originalData.battery">
                <div class="grid-label">电量</div>
                <div class="grid-value">{{ selectedMarker.properties.originalData.battery }}%</div>
              </div>
              <div class="grid-item" v-if="selectedMarker.properties.originalData.speed">
                <div class="grid-label">速度</div>
                <div class="grid-value">{{ selectedMarker.properties.originalData.speed }}km/h</div>
              </div>
              <div class="grid-item" v-if="selectedMarker.properties.originalData.direction">
                <div class="grid-label">方向</div>
                <div class="grid-value">{{ selectedMarker.properties.originalData.direction }}</div>
              </div>
              <div class="grid-item" v-if="selectedMarker.properties.originalData.lastUpdate">
                <div class="grid-label">最后更新</div>
                <div class="grid-value">{{ formatTime(selectedMarker.properties.originalData.lastUpdate) }}</div>
              </div>
            </div>
          </div>

          <!-- 操作按钮 -->
          <div class="action-buttons">
            <el-button
              size="small"
              type="primary"
              @click="handleMarkerAction('contact')"
              v-if="selectedMarker.properties.dataType === 'teacher'"
            >
              <el-icon><Phone /></el-icon>
              联系教师
            </el-button>
            <el-button
              size="small"
              type="warning"
              @click="handleMarkerAction('dispatch')"
              v-if="selectedMarker.properties.dataType === 'schoolbus'"
            >
              <el-icon><Position /></el-icon>
              调度校车
            </el-button>
            <el-button
              size="small"
              type="success"
              @click="handleMarkerAction('navigate')"
            >
              <el-icon><Location /></el-icon>
              导航至此
            </el-button>
            <el-button
              size="small"
              type="info"
              @click="handleMarkerAction('detail')"
            >
              <el-icon><View /></el-icon>
              查看详情
            </el-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, watch, ref, nextTick } from 'vue';
import {
  Close,
  Phone,
  Position,
  Location,
  View
} from "@element-plus/icons-vue";
import { ElButton } from 'element-plus';

const props = defineProps({
  idName: {
    type: String,
    default: 'chinaEcharts',
  },
  geometriesArray: {
    type: Array,
    default: () => []
  }
});

// 定义emit事件
const emit = defineEmits(['marker-click', 'marker-action']);

// 地图实例
let map = null;
let markerLayer = null;

// 信息窗口相关
const showInfoWindow = ref(false);
const selectedMarker = ref(null);
const infoWindowStyle = ref({
  left: '0px',
  top: '0px'
});

// 标记点信息存储
const markerInfoMap = ref(new Map());

// 获取图例图标
const getLegendIcon = (dataType) => {
  const iconMap = {
    'teacher': getTeacherIcon('online'),
    'schoolbus': getSchoolbusIcon('moving'),
    'school': getSchoolIcon('normal')
  };
  return iconMap[dataType] || iconMap.teacher;
};

// 教师图标 - 使用新图标
const getTeacherIcon = (status) => {
  const colorMap = {
    'online': '#22c55e', // 绿色
    'busy': '#f59e0b',   // 黄色
    'offline': '#ef4444' // 红色
  };
  const color = colorMap[status] || colorMap.online;

  const svg = `<svg width="20" height="30" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg">
    <path d="M433 119c128.682 0 233 104.318 233 233 0 83.088-43.49 156.017-108.949 197.267v-0.011c79.695 33.428 144.49 95.338 181.65 172.995L887.316 509.32c10.115-14.492 30.063-18.04 44.556-7.926 14.347 10.014 17.968 29.665 8.225 44.12l-0.3 0.436-170.556 244.365a32.089 32.089 0 0 1-4.944 5.579C769.995 821.013 773 847.155 773 874c0 17.673-14.327 32-32 32-17.496 0-31.713-14.042-31.996-31.47L709 874c0-159.058-128.942-288-288-288-157.467 0-285.418 126.376-287.961 283.237L133 874c0 17.673-14.327 32-32 32-17.673 0-32-14.327-32-32 0-151.898 96.214-281.324 231.023-330.66l-0.003 0.01C239.565 501.259 200 431.25 200 352c0-128.682 104.318-233 233-233z m0 64c-93.336 0-169 75.664-169 169s75.664 169 169 169 169-75.664 169-169-75.664-169-169-169z m489 98c17.673 0 32 14.327 32 32 0 17.496-14.042 31.713-31.47 31.996L922 345H802c-17.673 0-32-14.327-32-32 0-17.496 14.042-31.713 31.47-31.996L802 281h120z m0-141c17.673 0 32 14.327 32 32 0 17.496-14.042 31.713-31.47 31.996L922 204H718c-17.673 0-32-14.327-32-32 0-17.496 14.042-31.713 31.47-31.996L718 140h204z" fill="${color}" p-id="929"></path>
  </svg>`;

  return `data:image/svg+xml;base64,${btoa(unescape(encodeURIComponent(svg)))}`;
};

// 校车图标 - 使用新图标
const getSchoolbusIcon = (status) => {
  const colorMap = {
    'moving': '#3b82f6', // 蓝色
    'parked': '#22c55e'  // 绿色
  };
  const color = colorMap[status] || colorMap.moving;

  const svg = `<svg width="25" height="25" viewBox="0 0 1263 1024" xmlns="http://www.w3.org/2000/svg">
    <path d="M1262.276923 829.046154H1094.892308v-78.769231h88.615384v-196.923077a39.384615 39.384615 0 0 0-39.384615-39.384615h-39.384615v-393.846154a39.384615 39.384615 0 0 0-39.384616-39.384615H130.756923a49.624615 49.624615 0 0 0-50.018461 47.655384v621.883077H200.861538v78.769231h-196.923076V128.393846A126.424615 126.424615 0 0 1 39.384615 39.384615a128 128 0 0 1 91.372308-39.384615h935.384615a118.153846 118.153846 0 0 1 118.153847 118.153846v323.741539a118.153846 118.153846 0 0 1 78.76923 111.458461z" fill="${color}" p-id="1085"></path>
    <path d="M376.516923 750.276923h539.569231v78.769231H376.516923z" fill="${color}" p-id="1086"></path>
    <path d="M296.566154 907.815385A137.058462 137.058462 0 1 1 433.230769 770.756923a137.058462 137.058462 0 0 1-136.664615 137.058462z m0-194.953847A58.289231 58.289231 0 1 0 354.461538 770.756923a57.895385 57.895385 0 0 0-57.895384-57.895385zM1005.883077 907.815385A137.058462 137.058462 0 1 1 1142.153846 770.756923a137.058462 137.058462 0 0 1-136.270769 137.058462z m0-194.953847A58.289231 58.289231 0 1 0 1063.384615 770.756923a58.289231 58.289231 0 0 0-57.501538-57.895385z" fill="${color}" p-id="1087"></path>
    <path d="M869.218462 827.076923h-78.769231V236.307692h-78.769231v590.769231h-78.769231V236.307692a78.769231 78.769231 0 0 1 78.769231-78.76923h78.769231a78.769231 78.769231 0 0 1 78.769231 78.76923zM474.978462 472.615385h-78.769231a78.769231 78.769231 0 0 1-78.769231-78.769231V236.307692a78.769231 78.769231 0 0 1 78.769231-78.76923h78.769231a78.769231 78.769231 0 0 1 78.76923 78.76923v157.538462a78.769231 78.769231 0 0 1-78.76923 78.769231z m-78.769231-236.307693v157.538462h78.769231V236.307692zM159.507692 472.615385h-118.153846v-78.769231h118.153846V236.307692h-118.153846V157.538462h118.153846a78.769231 78.769231 0 0 1 78.769231 78.76923v157.538462a78.769231 78.769231 0 0 1-78.769231 78.769231zM1144.910769 513.969231h-118.153846a78.769231 78.769231 0 0 1-78.769231-80.738462V236.307692a78.769231 78.769231 0 0 1 78.769231-78.76923h118.153846v78.76923h-118.153846v196.923077l118.153846 1.969231zM0 945.230769h1260.307692v78.769231H0z" fill="${color}" p-id="1088"></path>
  </svg>`;

  return `data:image/svg+xml;base64,${btoa(unescape(encodeURIComponent(svg)))}`;
};

// 学校图标 - 使用新图标
const getSchoolIcon = (status) => {
  const colorMap = {
    'normal': '#22c55e', // 绿色
    'offline': '#ef4444' // 红色
  };
  const color = colorMap[status] || colorMap.normal;

  const svg = `<svg width="30" height="30" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg">
    <path d="M810.666667 661.333333v192H213.333333v-192h597.333334m40.533333-85.333333H172.8c-25.6 0-44.8 19.2-44.8 44.8V896c0 23.466667 19.2 42.666667 44.8 42.666667H853.333333c23.466667 0 44.8-19.2 44.8-44.8V620.8c-2.133333-25.6-21.333333-44.8-46.933333-44.8z" fill="${color}" p-id="1242"></path>
    <path d="M512 469.333333m-42.666667 0a42.666667 42.666667 0 1 0 85.333334 0 42.666667 42.666667 0 1 0-85.333334 0Z" fill="${color}" p-id="1243"></path>
    <path d="M512 320c-23.466667 0-42.666667-19.2-42.666667-42.666667V128c0-23.466667 19.2-42.666667 42.666667-42.666667s42.666667 19.2 42.666667 42.666667v149.333333c0 23.466667-19.2 42.666667-42.666667 42.666667z" fill="${color}" p-id="1244"></path>
    <path d="M640 170.666667h-128c-23.466667 0-42.666667-19.2-42.666667-42.666667s19.2-42.666667 42.666667-42.666667h128c23.466667 0 42.666667 19.2 42.666667 42.666667s-19.2 42.666667-42.666667 42.666667zM512 938.666667c-23.466667 0-42.666667-19.2-42.666667-42.666667v-128c0-23.466667 19.2-42.666667 42.666667-42.666667s42.666667 19.2 42.666667 42.666667v128c0 23.466667-19.2 42.666667-42.666667 42.666667zM512 322.133333l128 102.4V576H384v-151.466667l128-102.4m0-96c-10.666667 0-19.2 4.266667-27.733333 10.666667l-168.533334 134.4c-10.666667 8.533333-17.066667 21.333333-17.066666 34.133333v211.2c0 25.6 19.2 44.8 44.8 44.8h337.066666c25.6 0 44.8-19.2 44.8-44.8V405.333333c0-12.8-6.4-25.6-17.066666-34.133333l-168.533334-134.4c-8.533333-8.533333-17.066667-10.666667-27.733333-10.666667z" fill="${color}" p-id="1245"></path>
  </svg>`;

  return `data:image/svg+xml;base64,${btoa(unescape(encodeURIComponent(svg)))}`;
};

// 获取数据类型名称
const getDataTypeName = (dataType) => {
  const nameMap = {
    'teacher': '教师',
    'schoolbus': '校车',
    'school': '学校'
  };
  return nameMap[dataType] || dataType;
};

// 获取数据类型样式类
const getDataTypeClass = (dataType) => {
  const classMap = {
    'teacher': 'type-teacher',
    'schoolbus': 'type-schoolbus',
    'school': 'type-school'
  };
  return classMap[dataType] || '';
};

// 获取状态样式类
const getStatusClass = (status) => {
  const classMap = {
    'online': 'status-online',
    'busy': 'status-busy',
    'offline': 'status-offline',
    'moving': 'status-moving',
    'parked': 'status-parked',
    'normal': 'status-normal'
  };
  return classMap[status] || '';
};

// 获取状态名称
const getStatusName = (status) => {
  const nameMap = {
    'online': '在线',
    'busy': '忙碌',
    'offline': '离线',
    'moving': '移动中',
    'parked': '已停放',
    'normal': '正常'
  };
  return nameMap[status] || status;
};

// 检查是否有额外信息
const hasExtraInfo = (originalData) => {
  return originalData.battery || originalData.speed || originalData.direction || originalData.lastUpdate;
};

// 格式化时间
const formatTime = (timestamp) => {
  if (!timestamp) return '';
  const date = new Date(timestamp);
  return `${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')}`;
};

// 处理标记点操作
const handleMarkerAction = (action) => {
  if (!selectedMarker.value) return;

  const actions = {
    'contact': `正在联系教师 ${selectedMarker.value.properties.title}...`,
    'dispatch': `正在调度校车 ${selectedMarker.value.properties.title}...`,
    'navigate': `正在导航至 ${selectedMarker.value.properties.title}...`,
    'detail': `查看 ${selectedMarker.value.properties.title} 的详细信息...`
  };

  // 触发父组件事件
  emit('marker-action', {
    action,
    marker: selectedMarker.value
  });

  // 如果是详情操作，不关闭弹窗
  if (action !== 'detail') {
    closeInfoWindow();
  }
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

// 地图回调函数
const mapCallback = () => {
  const mapContainer = document.getElementById(props.idName);
  if (!mapContainer) {
    console.error(`地图容器不存在：${props.idName}`);
    return;
  }

  // 创建地图实例
  map = new TMap.Map(mapContainer, {
    center: new TMap.LatLng(26.793227, 117.810114),
    zoom: 11,
    mapStyleId: 'style1'
  });

  // 添加标记点
  updateMarkers();

  // 添加地图点击事件，点击空白处关闭信息窗口
  map.on('click', (evt) => {
    if (!evt.geometry) {
      closeInfoWindow();
    }
  });
};

// 更新标记点
const updateMarkers = () => {
  if (!map) return;

  const geometriesData = [];
  if (Array.isArray(props.geometriesArray)) {
    props.geometriesArray.forEach((item, index) => {
      if (item && typeof item.x === 'number' && typeof item.y === 'number') {
        const styleId = `marker-${item.dataType}-${item.status || 'normal'}`;

        geometriesData.push({
          id: `marker-${props.idName}-${index}`,
          styleId,
          position: new TMap.LatLng(item.x, item.y),
          properties: {
            title: item.name || `${item.dataType}${index}`,
            dataType: item.dataType,
            status: item.status,
            team: item.team,
            school: item.school,
            type: item.type,
            route: item.route,
            originalData: item
          }
        });

        // 存储标记点信息
        markerInfoMap.value.set(`marker-${props.idName}-${index}`, {
          geometry: {
            id: `marker-${props.idName}-${index}`,
            styleId,
            position: new TMap.LatLng(item.x, item.y),
            properties: {
              title: item.name || `${item.dataType}${index}`,
              dataType: item.dataType,
              status: item.status,
              team: item.team,
              school: item.school,
              type: item.type,
              route: item.route,
              originalData: item
            }
          },
          latLng: new TMap.LatLng(item.x, item.y)
        });
      }
    });
  }

  // 清除旧标记
  if (markerLayer) {
    markerLayer.setMap(null);
    markerLayer = null;
  }

  // 添加新标记
  if (geometriesData.length > 0) {
    const markerStyles = {
      // 教师状态图标
      'marker-teacher-online': new TMap.MarkerStyle({
        width: 20,
        height: 30,
        anchor: { x: 10, y: 15 },
        src: getTeacherIcon('online')
      }),
      'marker-teacher-busy': new TMap.MarkerStyle({
        width: 20,
        height: 30,
        anchor: { x: 10, y: 15 },
        src: getTeacherIcon('busy')
      }),
      'marker-teacher-offline': new TMap.MarkerStyle({
        width: 20,
        height: 30,
        anchor: { x: 10, y: 15 },
        src: getTeacherIcon('offline')
      }),

      // 校车状态图标
      'marker-schoolbus-moving': new TMap.MarkerStyle({
        width: 25,
        height: 25,
        anchor: { x: 12.5, y: 12.5 },
        src: getSchoolbusIcon('moving')
      }),
      'marker-schoolbus-parked': new TMap.MarkerStyle({
        width: 25,
        height: 25,
        anchor: { x: 12.5, y: 12.5 },
        src: getSchoolbusIcon('parked')
      }),

      // 学校状态图标
      'marker-school-normal': new TMap.MarkerStyle({
        width: 30,
        height: 30,
        anchor: { x: 15, y: 15 },
        src: getSchoolIcon('normal')
      }),
      'marker-school-offline': new TMap.MarkerStyle({
        width: 30,
        height: 30,
        anchor: { x: 15, y: 15 },
        src: getSchoolIcon('offline')
      })
    };

    markerLayer = new TMap.MultiMarker({
      map: map,
      styles: markerStyles,
      geometries: geometriesData
    });

    // 添加点击事件 - 修复重复点击问题
    markerLayer.on('click', (evt) => {
      if (evt.geometry) {
        const properties = evt.geometry.properties;
        emit('marker-click', properties);

        // 修复：确保每次点击都能正确显示信息窗口
        const markerInfo = markerInfoMap.value.get(evt.geometry.id);
        if (markerInfo) {
          showMarkerInfo(markerInfo.geometry, markerInfo.latLng, evt.pixel);
        } else {
          // 如果找不到存储的信息，使用当前事件数据
          showMarkerInfo(evt.geometry, evt.latLng, evt.pixel);
        }
      }
    });
  }
};

// 显示标记点信息窗口 - 修复重复显示问题
const showMarkerInfo = (geometry, latLng, pixel) => {
  // 修复：先关闭当前信息窗口，确保重新打开时状态正确
  closeInfoWindow();

  // 使用 nextTick 确保 DOM 更新后再显示新窗口
  nextTick(() => {
    selectedMarker.value = geometry;

    let targetPixel = pixel;
    if (!targetPixel && map) {
      const projection = map.getProjection();
      targetPixel = projection.projectToViewport(latLng);
    }

    if (targetPixel) {
      const mapRect = document.getElementById(props.idName).getBoundingClientRect();
      const infoWidth = 280;
      const infoHeight = 350;

      let left = targetPixel.x - infoWidth / 2;
      let top = targetPixel.y - infoHeight - 20;

      // 边界检查
      if (left < 10) left = 10;
      if (left + infoWidth > mapRect.width - 10) {
        left = mapRect.width - infoWidth - 10;
      }
      if (top < 10) {
        top = targetPixel.y + 30;
      }

      infoWindowStyle.value = {
        left: `${left}px`,
        top: `${top}px`
      };
    }

    showInfoWindow.value = true;
  });
};

// 关闭信息窗口 - 修复关闭逻辑
const closeInfoWindow = () => {
  showInfoWindow.value = false;
  // 不要清空 selectedMarker，以便在需要时可以重新打开
  // selectedMarker.value = null;
};

// 监听数据变化
watch(() => props.geometriesArray, () => {
  if (map) {
    updateMarkers();
  }
}, { deep: true });

onMounted(() => {
  initMap();
});
</script>

<style scoped>
/* 样式保持不变，主要修改类型和状态的颜色和样式 */
.map-container {
  position: relative;
  width: 100%;
  height: 100%;
}

.map-common-css {
  border-radius: 8px;
  overflow: hidden;
}

.legend {
  position: absolute;
  bottom: 20px;
  right: 20px;
  background: white;
  border: 1px solid #ddd;
  border-radius: 6px;
  padding: 10px 15px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
  z-index: 999;
  display: flex;
  flex-direction: column;
  gap: 8px;
  margin-bottom: 20px;
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
  width: 16px;
  height: 16px;
  margin-right: 6px;
}

.map-info-window-risk {
  position: absolute;
  width: 280px;
  background: white;
  border: 1px solid #ccc;
  border-radius: 6px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.15);
  z-index: 1000;
  font-family: 'Microsoft YaHei', Arial, sans-serif;
  /* 修复：确保信息窗口可以持续显示 */
  pointer-events: auto;
}

.info-window-content {
  padding: 12px;
}

.info-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
  padding-bottom: 8px;
  border-bottom: 1px solid #f0f0f0;
}

.info-header h4 {
  margin: 0;
  color: #1E90FF;
  font-size: 14px;
  font-weight: bold;
}

.close-btn {
  cursor: pointer;
  color: #999;
  font-size: 14px;
  transition: color 0.2s;
}

.close-btn:hover {
  color: #333;
}

.info-body {
  font-size: 14px;
  color: #333;
}

.info-row {
  display: flex;
  margin-bottom: 8px;
  line-height: 1.4;
  align-items: center;
}

.info-label {
  color: #666;
  width: 70px;
  flex-shrink: 0;
  text-align: right;
  margin-right: 8px;
  font-weight: 500;
}

.info-value {
  color: #333;
  flex: 1;
}

.coordinate {
  font-family: 'Courier New', monospace;
  font-size: 12px;
  color: #666;
  background: #f5f5f5;
  padding: 2px 6px;
  border-radius: 4px;
}

/* 类型和状态标签 */
.type-badge, .status-badge {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 500;
}

.type-teacher {
  background: rgba(59, 130, 246, 0.1);
  color: #3b82f6;
  border: 1px solid rgba(59, 130, 246, 0.3);
}

.type-schoolbus {
  background: rgba(239, 68, 68, 0.1);
  color: #ef4444;
  border: 1px solid rgba(239, 68, 68, 0.3);
}

.type-school {
  background: rgba(245, 158, 11, 0.1);
  color: #f59e0b;
  border: 1px solid rgba(245, 158, 11, 0.3);
}

.status-online {
  background: rgba(34, 197, 94, 0.1);
  color: #22c55e;
  border: 1px solid rgba(34, 197, 94, 0.3);
}

.status-busy {
  background: rgba(245, 158, 11, 0.1);
  color: #f59e0b;
  border: 1px solid rgba(245, 158, 11, 0.3);
}

.status-offline {
  background: rgba(239, 68, 68, 0.1);
  color: #ef4444;
  border: 1px solid rgba(239, 68, 68, 0.3);
}

.status-moving {
  background: rgba(59, 130, 246, 0.1);
  color: #3b82f6;
  border: 1px solid rgba(59, 130, 246, 0.3);
}

.status-parked, .status-normal {
  background: rgba(34, 197, 94, 0.1);
  color: #22c55e;
  border: 1px solid rgba(34, 197, 94, 0.3);
}

/* 额外信息区域 */
.extra-info {
  margin-top: 12px;
  padding: 10px;
  background: #f8f9fa;
  border-radius: 4px;
  border: 1px solid #e9ecef;
}

.extra-info-title {
  color: #1E90FF;
  font-size: 13px;
  font-weight: 600;
  margin-bottom: 6px;
  padding-bottom: 4px;
  border-bottom: 1px solid #dee2e6;
}

.info-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 6px;
}

.grid-item {
  display: flex;
  flex-direction: column;
}

.grid-label {
  font-size: 12px;
  color: #666;
  margin-bottom: 2px;
}

.grid-value {
  font-size: 13px;
  color: #333;
  font-weight: 500;
}

/* 操作按钮区域 */
.action-buttons {
  margin-top: 12px;
  display: flex;
  gap: 6px;
  flex-wrap: wrap;
}

:deep(.el-button) {
  flex: 1;
  min-width: 0;
  padding: 6px 10px;
  font-size: 12px;
  border-radius: 4px;
}

:deep(.el-button .el-icon) {
  font-size: 12px;
  margin-right: 4px;
}
</style>
