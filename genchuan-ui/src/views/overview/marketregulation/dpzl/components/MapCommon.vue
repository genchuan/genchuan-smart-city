<template>
  <div class="map-container">
    <div
      :id="idName"
      :style="{
        width: '100%',
        height: '100%',
        marginLeft: 'auto',
        marginRight: 'auto'
      }"
      class="map-common-css"
    ></div>

    <!-- 图例：监管对象、案件、预警事件 -->
    <div class="legend">
      <div class="legend-items">
        <div class="legend-item" v-if="shouldShowLegendItem('station')">
          <img :src="getLegendIcon('station')" class="legend-icon" alt="监管对象" />
          <span>监管对象</span>
        </div>
        <div class="legend-item" v-if="shouldShowLegendItem('vehicle')">
          <img :src="getLegendIcon('vehicle')" class="legend-icon" alt="案件" />
          <span>案件</span>
        </div>
        <div class="legend-item" v-if="shouldShowLegendItem('staff')">
          <img :src="getLegendIcon('staff')" class="legend-icon" alt="预警事件" />
          <span>预警事件</span>
        </div>
      </div>
    </div>

    <!-- 信息弹窗 -->
    <div v-if="showInfoWindow && selectedMarker" class="map-info-window-risk" :style="infoWindowStyle">
      <div class="info-window-content">
        <div class="info-header">
          <h4>详细信息</h4>
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
            <span class="info-label">所属队伍:</span>
            <span class="info-value">{{ selectedMarker.properties.team }}</span>
          </div>
          <div v-if="selectedMarker.properties.type" class="info-row">
            <span class="info-label">具体类型:</span>
            <span class="info-value">{{ selectedMarker.properties.type }}</span>
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
              <div class="grid-item" v-if="selectedMarker.properties.originalData.riskLevel">
                <div class="grid-label">风险等级</div>
                <div class="grid-value">{{ getRiskLevelText(selectedMarker.properties.originalData.riskLevel) }}</div>
              </div>
              <div class="grid-item" v-if="selectedMarker.properties.originalData.address">
                <div class="grid-label">地址</div>
                <div class="grid-value">{{ selectedMarker.properties.originalData.address }}</div>
              </div>
              <div class="grid-item" v-if="selectedMarker.properties.originalData.contact">
                <div class="grid-label">联系人</div>
                <div class="grid-value">{{ selectedMarker.properties.originalData.contact }}</div>
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
              @click="handleMarkerAction('detail')"
            >
              <el-icon><View /></el-icon>
              查看详情
            </el-button>
            <el-button
              size="small"
              type="warning"
              @click="handleMarkerAction('dispatch')"
            >
              <el-icon><Position /></el-icon>
              调度处理
            </el-button>
            <el-button
              size="small"
              type="success"
              @click="handleMarkerAction('navigate')"
            >
              <el-icon><Location /></el-icon>
              导航至此
            </el-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, watch, ref, nextTick, defineProps, defineEmits } from 'vue';
import {
  Close,
  Phone,
  Position,
  Location,
  View
} from "@element-plus/icons-vue";
import { ElMessage, ElButton } from 'element-plus';

const props = defineProps({
  idName: {
    type: String,
    default: 'market-regulation-map',
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

// 检查是否显示图例项
const shouldShowLegendItem = (dataType) => {
  if (!props.geometriesArray || props.geometriesArray.length === 0) return false;

  // 检查是否有对应类型的标记点
  return props.geometriesArray.some(item => item.dataType === dataType);
};

// 获取图例图标
const getLegendIcon = (dataType) => {
  const iconMap = {
    'staff': getStaffIcon('online'),
    'vehicle': getVehicleIcon('moving'),
    'station': getStationIcon('normal')
  };
  return iconMap[dataType] || iconMap.staff;
};

// 执法人员图标
const getStaffIcon = (status) => {
  const colorMap = {
    'online': '#22c55e', // 绿色
    'busy': '#f59e0b',   // 黄色
    'offline': '#ef4444' // 红色
  };
  const color = colorMap[status] || colorMap.online;

  const svg = `<svg width="20" height="30" viewBox="0 0 1119 1024" xmlns="http://www.w3.org/2000/svg">
    <path d="M556.016934 370.913185c-224.48702 0-406.370664 44.241967-406.370664 98.86168s181.883644 99.407878 406.370664 99.407878 406.370664-44.241967 406.370664-99.407878c0-54.619713-181.883644-98.86168-406.370664-98.86168z" fill="${color}" p-id="896"></path>
    <path d="M1081.458572 133.863631L732.984804 0.591531a36.595208 36.595208 0 0 0-13.108731 0H386.149627a36.595208 36.595208 0 0 0-13.108731 0L24.567127 133.863631a36.595208 36.595208 0 0 0-18.570702 54.619713l109.239426 186.253221a36.595208 36.595208 0 0 0 40.964785 16.385914l19.663096-4.369577a1032.858771 1032.858771 0 0 1 374.691231-54.619713c172.052096 0 320.071518 24.578871 385.615173 58.98929h11.47014a36.595208 36.595208 0 0 0 37.687602-17.478308l112.516608-187.891813a36.595208 36.595208 0 0 0-16.385914-51.888727zM623.745378 273.690096l-71.005627-37.141405L482.280322 273.690096l13.654928-78.652387L437.492157 136.594617l79.198584-11.47014 36.04901-69.913233 35.502814 72.098021 79.198583 11.47014-57.350698 54.619713zM168.763169 551.158238H164.393592v68.820838a389.438553 389.438553 0 1 0 778.33091 0v-68.274641c-58.98929 37.687602-209.1935 64.451261-386.161371 64.451261s-329.356869-27.309856-387.799962-64.997458z" fill="${color}" p-id="897"></path>
  </svg>`;

  return `data:image/svg+xml;base64,${btoa(unescape(encodeURIComponent(svg)))}`;
};

// 执法车辆图标
const getVehicleIcon = (status) => {
  const colorMap = {
    'moving': '#3b82f6', // 蓝色
    'parked': '#22c55e'  // 绿色
  };
  const color = colorMap[status] || colorMap.moving;

  const svg = `<svg width="25" height="25" viewBox="0 0 1082 1024" xmlns="http://www.w3.org/2000/svg">
    <path fill="${color}" d="M856.608782 665.768977v14.194059a421.766337 421.766337 0 0 1-4.731354 67.590759l35.147195 20.277228V665.093069z"/>
    <path fill="${color}" d="M791.721653 442.719472a290.640264 290.640264 0 1 0 290.640264 290.640264 291.316172 291.316172 0 0 0-290.640264-290.640264z m155.458746 465.70033c-35.823102 0-55.424422-25.684488-58.803961-67.590759v-43.933993l-25.684488 36.499009a83.812541 83.812541 0 0 0-14.19406-9.462706l-12.166336-12.842244a352.823762 352.823762 0 0 1-67.590759 106.117492l-52.720792-44.609901a217.642244 217.642244 0 0 0 67.590759-98.006601c-14.869967-10.814521-27.036304-18.925413-37.850825-25.684488v11.490429a45.961716 45.961716 0 0 1-15.545875 0h-11.490429v93.951155c0 31.091749-13.518152 47.989439-41.230363 48.665346a333.89835 333.89835 0 0 1-45.961716 0c0-20.277228-6.759076-39.20264-10.814522-57.452145h25.008581c10.814521 0 15.545875-4.731353 14.19406-16.89769v-58.80396c-7.434983 0-17.573597 0-31.09175 6.083168h-8.110891l-4.731353-67.590759 20.277228-3.379538a120.311551 120.311551 0 0 0 23.656766 0v-48.665346h-39.202641v-57.452146h39.202641v-63.535313h58.80396v63.535313h30.415842v57.452146h-30.415842v48.665346l27.036304-4.731353v38.526733l27.036303-36.49901a36.49901 36.49901 0 0 1 15.545875 9.462706h6.759076v-36.49901a19.60132 19.60132 0 0 1 0-6.083168v-3.379538h-37.850825V608.316832h37.850825V540.726073h60.831683v67.590759H947.180399c-3.379538 91.923432-3.379538 168.30099 0 229.808581 0 8.110891 0 12.166337 4.731353 12.166336s0-5.407261 4.731353-14.194059 0-28.388119 0-50.69307v-22.30495a228.456766 228.456766 0 0 0 23.656766 8.110891 111.524752 111.524752 0 0 1 20.953135 8.110891c0 39.20264-6.083168 67.590759-8.110891 80.433003-2.70363 31.091749-19.60132 48.665347-45.961716 48.665347z"/>
    <path fill="${color}" d="M417.944755 734.711551A372.425083 372.425083 0 0 1 474.045085 557.623762H332.780399a54.072607 54.072607 0 0 1 0-107.469307h246.70627a373.776898 373.776898 0 0 1 501.523433 67.59076v-29.064027A235.891749 235.891749 0 0 0 974.216702 277.122112c-19.60132-160.190099-101.386139-277.122112-202.772277-277.122112H308.447725c-100.710231 0-181.819142 116.932013-202.772277 277.79802A235.215842 235.215842 0 0 0 0.909772 494.764356a259.548515 259.548515 0 0 0 60.155775 176.411882v155.458745h182.49505v-91.247524zM308.447725 60.831683h464.348515c64.211221 0 120.987459 83.136634 142.616502 196.013201H213.144755a253.465347 253.465347 0 0 0-46.637624 4.731354C187.460267 143.968317 244.236504 60.831683 308.447725 60.831683zM188.136174 572.493729A75.70165 75.70165 0 0 1 115.814062 494.088449a74.349835 74.349835 0 0 1 71.646205-78.405281 75.70165 75.70165 0 0 1 72.322112 78.405281 75.025743 75.025743 0 0 1-71.646205 78.40528z"/>
  </svg>`;

  return `data:image/svg+xml;base64,${btoa(unescape(encodeURIComponent(svg)))}`;
};

// 执法站点图标
const getStationIcon = (status) => {
  const colorMap = {
    'normal': '#22c55e', // 绿色
    'offline': '#ef4444' // 红色
  };
  const color = colorMap[status] || colorMap.normal;

  const svg = `<svg width="30" height="30" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg">
    <path fill="${color}" d="M113.357934 110.523985h544.118081c19.837638 0 35.896679 16.059041 35.896679 35.896679v723.601476c0 19.837638-16.059041 34.95203-35.896679 34.95203H113.357934c-19.837638 0-35.896679-15.114391-35.896679-34.95203V146.420664c0-19.837638 16.059041-35.896679 35.896679-35.896679z m509.166051 70.848709H148.309963v652.752767h474.214022V181.372694z"/>
    <path fill="${color}" d="M657.476015 346.686347h251.276753c19.837638 0 34.95203 16.059041 34.952029 35.896679v487.439114c0 19.837638-15.114391 34.95203-34.952029 34.95203H657.476015c-18.892989 0-34.95203-15.114391-34.95203-34.95203V382.583026c0-19.837638 16.059041-35.896679 34.95203-35.896679z m215.380074 70.848708H693.372694v416.590406h179.483395V417.535055z"/>
    <path fill="${color}" d="M754.774908 600.797048h56.282214v56.282214H754.774908zM754.774908 715.099631h56.282214v56.282214H754.774908z"/>
    <path fill="${color}" d="M754.774908 484.605166h56.282214v56.282214H754.774908zM219.158672 312.678967h55.734317v-56.678967h-55.734317v56.678967z m112.413284 531.837638h115.247232V715.099631H331.571956v129.416974z m0-187.98524h114.302583v-56.678966H331.571956v56.678966z m0-114.302583h114.302583v-56.678967H331.571956v56.678967z m0-115.247232h114.302583v-55.734318H331.571956v55.734318z m0-114.302583h114.302583v-56.678967H331.571956v56.678967z m171.926199 458.154981h56.678967v-55.734317h-56.678967v55.734317z m0-114.302583h56.678967v-56.678966h-56.678967v56.678966z m0-114.302583h56.678967v-56.678967h-56.678967v56.678967z m0-115.247232h56.678967v-55.734318h-56.678967v55.734318z m0-114.302583h56.678967v-56.678967h-56.678967v56.678967zM219.158672 770.833948h55.734317v-55.734317h-55.734317v55.734317z m0-114.302583h55.734317v-56.678966h-55.734317v56.678966z m0-114.302583h55.734317v-56.678967h-55.734317v56.678967z m0-115.247232h55.734317v-55.734318h-55.734317v55.734318z"/>
  </svg>`;

  return `data:image/svg+xml;base64,${btoa(unescape(encodeURIComponent(svg)))}`;
};

// 获取数据类型名称
const getDataTypeName = (dataType) => {
  const nameMap = {
    'staff': '预警事件',
    'vehicle': '案件',
    'station': '监管对象'
  };
  return nameMap[dataType] || dataType;
};

// 获取数据类型样式类
const getDataTypeClass = (dataType) => {
  const classMap = {
    'staff': 'type-staff',
    'vehicle': 'type-vehicle',
    'station': 'type-station'
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

// 获取风险等级文本
const getRiskLevelText = (level) => {
  const levelMap = {
    'high': '高风险',
    'medium': '中风险',
    'low': '低风险'
  };
  return levelMap[level] || level;
};

// 检查是否有额外信息
const hasExtraInfo = (originalData) => {
  return originalData.riskLevel || originalData.address || originalData.contact || originalData.lastUpdate;
};

// 格式化时间
const formatTime = (timestamp) => {
  if (!timestamp) return '';
  const date = new Date(timestamp);
  return `${date.getFullYear()}-${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')}`;
};

// 处理标记点操作
const handleMarkerAction = (action) => {
  if (!selectedMarker.value) return;

  const actions = {
    'dispatch': `正在调度处理 ${selectedMarker.value.properties.title}...`,
    'navigate': `正在导航至 ${selectedMarker.value.properties.title}...`,
    'detail': `查看 ${selectedMarker.value.properties.title} 的详细信息...`
  };

  ElMessage.success(actions[action] || '操作执行成功');

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

// 暴露方法给父组件
defineExpose({
  updateMarkers,
  fitBounds: (bounds) => {
    if (map && bounds) {
      map.fitBounds(bounds, { padding: 50 });
    }
  }
});

// 初始化地图
const initMap = () => {
  const callbackName = `initMap_${props.idName}`;

  // 检查是否已加载腾讯地图API
  if (window.TMap) {
    mapCallback();
    return;
  }

  const script = document.createElement('script');
  script.src = `https://map.qq.com/api/gljs?v=1.exp&key=QTQBZ-F3RWW-JJJRV-YNPA5-ZIKDK-3SBNO&callback=${callbackName}`;
  script.async = true;
  script.onerror = () => {
    console.error('腾讯地图加载失败');
    ElMessage.error('地图加载失败，请检查网络连接');
    delete window[callbackName];
  };

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

  // 确保容器有正确的高度
  if (mapContainer.offsetHeight === 0) {
    console.warn('地图容器高度为0，重新计算尺寸');
    mapContainer.style.height = '500px'; // 临时设置固定高度
  }

  try {
    // 创建地图实例
    map = new TMap.Map(mapContainer, {
      center: new TMap.LatLng(26.793227, 117.810114),
      zoom: 11,
      mapStyleId: 'style1',
      pitch: 0,
      rotation: 0,
      scaleControl: true,
      zoomControl: true,
      baseMap: {
        type: 'vector',
        features: ['base', 'building']
      }
    });

    // 监听地图加载完成事件
    map.on('idle', () => {
      console.log('地图加载完成');
      updateMarkers();
    });

    // 监听地图大小变化
    map.on('resize', () => {
      updateMarkers();
    });

    // 添加点击事件监听
    map.on('click', (evt) => {
      // 清除之前的选中状态
      clearSelectedMarkers();

      // 如果有标记层，则处理标记点点击
      if (markerLayer && evt.geometry) {
        const properties = evt.geometry.properties;
        emit('marker-click', properties);

        // 显示信息窗口
        showMarkerInfo(
          evt.geometry,
          evt.latLng,
          evt.pixel
        );
      } else {
        // 点击空白处关闭信息窗口
        closeInfoWindow();
      }
    });

  } catch (error) {
    console.error('地图初始化失败:', error);
    ElMessage.error('地图初始化失败: ' + error.message);
  }
};

// 清除选中标记的状态
const clearSelectedMarkers = () => {
  // 可以在这里添加清除选中状态的逻辑
};

// 显示标记点信息窗口
const showMarkerInfo = (geometry, latLng, pixel) => {
  closeInfoWindow();

  nextTick(() => {
    selectedMarker.value = {
      geometry,
      properties: geometry.properties,
      position: {
        lat: latLng.getLat(),
        lng: latLng.getLng()
      }
    };

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

// 关闭信息窗口
const closeInfoWindow = () => {
  showInfoWindow.value = false;
};

// 更新标记点
const updateMarkers = () => {
  if (!map) {
    console.warn('地图实例未初始化，无法更新标记');
    return;
  }

  const geometriesData = [];
  if (Array.isArray(props.geometriesArray) && props.geometriesArray.length > 0) {
    console.log('更新地图标记点，数量:', props.geometriesArray.length);

    props.geometriesArray.forEach((item, index) => {
      if (item && typeof item.x === 'number' && typeof item.y === 'number') {
        const styleId = `marker-${item.dataType}-${item.status || 'normal'}`;

        geometriesData.push({
          id: `marker-${props.idName}-${Date.now()}-${index}`,
          styleId,
          position: new TMap.LatLng(item.x, item.y),
          properties: {
            title: item.name || `${item.dataType}${index + 1}`,
            dataType: item.dataType,
            status: item.status,
            team: item.team,
            type: item.type,
            originalData: item
          }
        });
      }
    });
  } else {
    console.log('没有标记点数据可显示');
  }

  // 清除旧标记层
  if (markerLayer) {
    markerLayer.setMap(null);
    markerLayer = null;
  }

  // 添加新标记层
  if (geometriesData.length > 0) {
    const markerStyles = {
      // 预警事件图标
      'marker-staff-online': new TMap.MarkerStyle({
        width: 25,
        height: 35,
        anchor: { x: 12.5, y: 17.5 },
        src: getStaffIcon('online')
      }),
      'marker-staff-busy': new TMap.MarkerStyle({
        width: 25,
        height: 35,
        anchor: { x: 12.5, y: 17.5 },
        src: getStaffIcon('busy')
      }),
      'marker-staff-offline': new TMap.MarkerStyle({
        width: 25,
        height: 35,
        anchor: { x: 12.5, y: 17.5 },
        src: getStaffIcon('offline')
      }),

      // 案件图标
      'marker-vehicle-moving': new TMap.MarkerStyle({
        width: 30,
        height: 30,
        anchor: { x: 15, y: 15 },
        src: getVehicleIcon('moving')
      }),
      'marker-vehicle-parked': new TMap.MarkerStyle({
        width: 30,
        height: 30,
        anchor: { x: 15, y: 15 },
        src: getVehicleIcon('parked')
      }),

      // 监管对象图标
      'marker-station-normal': new TMap.MarkerStyle({
        width: 35,
        height: 35,
        anchor: { x: 17.5, y: 17.5 },
        src: getStationIcon('normal')
      }),
      'marker-station-offline': new TMap.MarkerStyle({
        width: 35,
        height: 35,
        anchor: { x: 17.5, y: 17.5 },
        src: getStationIcon('offline')
      })
    };

    try {
      markerLayer = new TMap.MultiMarker({
        map: map,
        styles: markerStyles,
        geometries: geometriesData
      });

      // 添加点击事件
      markerLayer.on('click', (evt) => {
        if (evt.geometry) {
          const properties = evt.geometry.properties;
          emit('marker-click', properties);

          // 显示信息窗口
          showMarkerInfo(
            evt.geometry,
            evt.latLng,
            evt.pixel
          );
        }
      });

      console.log('标记点添加成功，数量:', geometriesData.length);

      // 自动调整视野以显示所有标记点
      if (geometriesData.length > 0) {
        const bounds = new TMap.LatLngBounds();
        geometriesData.forEach(item => {
          bounds.extend(item.position);
        });

        // 延迟调整视野，确保地图已渲染
        setTimeout(() => {
          if (map) {
            map.fitBounds(bounds, { padding: 100 });
          }
        }, 500);
      }

    } catch (error) {
      console.error('创建标记层失败:', error);
    }
  }
};

// 监听数据变化
watch(() => props.geometriesArray, (newVal) => {
  console.log('地图数据变化:', newVal?.length);
  if (map) {
    // 延迟更新，确保地图已准备好
    setTimeout(updateMarkers, 100);
  }
}, { deep: true });

onMounted(() => {
  console.log('MapCommon 组件挂载，开始初始化地图');
  initMap();

  // 确保容器有正确的高度
  nextTick(() => {
    const mapContainer = document.getElementById(props.idName);
    if (mapContainer && mapContainer.offsetHeight < 100) {
      console.warn('地图容器高度异常:', mapContainer.offsetHeight);
      mapContainer.style.height = 'calc(100% - 20px)';
    }
  });
});
</script>

<style scoped>
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
  background: rgba(15, 23, 42, 0.9);
  border: 1px solid rgba(59, 130, 246, 0.2);
  border-radius: 6px;
  padding: 10px 15px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.3);
  z-index: 999;
  display: flex;
  flex-direction: column;
  gap: 8px;
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
  color: #E2E8F0;
}

.legend-icon {
  width: 16px;
  height: 16px;
  margin-right: 6px;
}

.map-info-window-risk {
  position: absolute;
  width: 280px;
  background: rgba(15, 23, 42, 0.95);
  border: 1px solid rgba(59, 130, 246, 0.3);
  border-radius: 6px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.3);
  z-index: 1000;
  font-family: 'Microsoft YaHei', Arial, sans-serif;
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
  border-bottom: 1px solid rgba(59, 130, 246, 0.2);
}

.info-header h4 {
  margin: 0;
  color: #3B82F6;
  font-size: 14px;
  font-weight: bold;
}

.close-btn {
  cursor: pointer;
  color: #94A3B8;
  font-size: 14px;
  transition: color 0.2s;
}

.close-btn:hover {
  color: #E2E8F0;
}

.info-body {
  font-size: 14px;
  color: #E2E8F0;
}

.info-row {
  display: flex;
  margin-bottom: 8px;
  line-height: 1.4;
  align-items: center;
}

.info-label {
  color: #94A3B8;
  width: 70px;
  flex-shrink: 0;
  text-align: right;
  margin-right: 8px;
  font-weight: 500;
}

.info-value {
  color: #E2E8F0;
  flex: 1;
}

.coordinate {
  font-family: 'Courier New', monospace;
  font-size: 12px;
  color: #94A3B8;
  background: rgba(15, 23, 42, 0.5);
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

.type-staff {
  background: rgba(59, 130, 246, 0.1);
  color: #3b82f6;
  border: 1px solid rgba(59, 130, 246, 0.3);
}

.type-vehicle {
  background: rgba(239, 68, 68, 0.1);
  color: #ef4444;
  border: 1px solid rgba(239, 68, 68, 0.3);
}

.type-station {
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
  background: rgba(15, 23, 42, 0.5);
  border-radius: 4px;
  border: 1px solid rgba(59, 130, 246, 0.2);
}

.extra-info-title {
  color: #3B82F6;
  font-size: 13px;
  font-weight: 600;
  margin-bottom: 6px;
  padding-bottom: 4px;
  border-bottom: 1px solid rgba(59, 130, 246, 0.2);
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
  color: #94A3B8;
  margin-bottom: 2px;
}

.grid-value {
  font-size: 13px;
  color: #E2E8F0;
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
  background-color: rgba(15, 23, 42, 0.5);
  border-color: rgba(59, 130, 246, 0.3);
  color: #E2E8F0;
}

:deep(.el-button:hover) {
  background-color: rgba(59, 130, 246, 0.2);
  border-color: #3B82F6;
  color: #3B82F6;
}

:deep(.el-button .el-icon) {
  font-size: 12px;
  margin-right: 4px;
}
</style>
