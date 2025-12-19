<template>
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
</template>

<script setup>
import { onMounted, defineProps, defineEmits, watch } from 'vue';

// 接收父组件传递的参数
const props = defineProps({
  idName: {
    type: String,
    default: 'chinaEcharts',
  },
  // 标记点数据（包含x、y坐标，dataType类型，status状态）
  geometriesArray: {
    type: Array,
    default: () => [] // 默认空数组，避免undefined
  }
});

const emit = defineEmits(['loaded', 'click', 'dblclick', 'contextmenu']);

let map = null;
let markers = null;

// 初始化地图：动态加载腾讯地图API，使用唯一回调名
const initMap = () => {
  // 生成唯一回调函数名（基于idName，确保多个地图实例不冲突）
  const callbackName = `initMap_${props.idName}`;

  // 创建script标签加载地图API
  const script = document.createElement('script');
  script.src = `https://map.qq.com/api/gljs?v=1.exp&key=QTQBZ-F3RWW-JJJRV-YNPA5-ZIKDK-3SBNO&callback=${callbackName}`;
  script.async = true;

  // 定义唯一回调函数
  window[callbackName] = () => {
    mapCallback(); // 执行地图初始化逻辑
    delete window[callbackName]; // 执行后删除，避免全局污染
  };

  document.head.appendChild(script);
};

// 地图初始化回调：创建地图实例和标记点
const mapCallback = () => {
  // 检查地图容器是否存在
  const mapContainer = document.getElementById(props.idName);
  if (!mapContainer) {
    console.error(`地图容器不存在：${props.idName}`);
    return;
  }

  // 创建地图实例（基础底图）
  map = new TMap.Map(mapContainer, {
    center: new TMap.LatLng(26.793227, 117.810114), // 中心坐标
    zoom: 11, // 缩放级别
    mapStyleId: 'style1' // 使用默认样式
  });

  // 添加地图事件监听
  map.on('click', (evt) => {
    emit('click', {
      coordinates: [evt.latLng.lat, evt.latLng.lng],
      targetType: 'map'
    });
  });

  map.on('dblclick', (evt) => {
    emit('dblclick', {
      coordinates: [evt.latLng.lat, evt.latLng.lng],
      targetType: 'map'
    });
  });

  map.on('contextmenu', (evt) => {
    evt.preventDefault();
    emit('contextmenu', {
      coordinates: [evt.latLng.lat, evt.latLng.lng],
      targetType: 'map'
    });
  });

  // 初始化标记点
  updateMarkers();

  emit('loaded');
};

// 更新标记点
const updateMarkers = () => {
  if (!map) return;

  // 清除现有标记点
  if (markers) {
    markers.setMap(null);
  }

  // 处理标记点数据
  const geometriesData = [];
  if (Array.isArray(props.geometriesArray)) {
    props.geometriesArray.forEach((item, index) => {
      // 过滤无效数据（必须包含x和y坐标）
      if (item && typeof item.x === 'number' && typeof item.y === 'number') {
        // 根据数据类型和状态确定样式
        const styleId = getMarkerStyleId(item.dataType, item.status);

        geometriesData.push({
          id: `marker-${props.idName}-${index}`,
          styleId,
          position: new TMap.LatLng(item.x, item.y),
          properties: {
            title: item.name || `${item.dataType}${index}`,
            dataType: item.dataType,
            status: item.status,
            ...item
          }
        });
      }
    });
  }

  // 添加标记点图层（如果有数据）
  if (geometriesData.length > 0) {
    markers = new TMap.MultiMarker({
      map: map,
      styles: getMarkerStyles(),
      geometries: geometriesData
    });

    // 添加标记点点击事件
    markers.on('click', (evt) => {
      const geometry = evt.geometry;
      emit('click', {
        targetType: 'marker',
        targetId: geometry.id,
        coordinates: [geometry.position.lat, geometry.position.lng],
        attributes: geometry.properties
      });
    });
  }
};

// 获取标记点样式ID
const getMarkerStyleId = (dataType, status) => {
  const styleMap = {
    'grid': 'grid',
    '路灯': status === 'online' ? 'light-online' : 'light-offline',
    '监控': status === 'online' ? 'camera-online' : 'camera-offline',
    '消防设施': status === 'online' ? 'fire-online' : 'fire-offline',
    '垃圾点': status === 'online' ? 'garbage-online' : 'garbage-offline',
    'event': `event-${status || 'normal'}`,
    'poi': 'poi'
  };
  return styleMap[dataType] || 'default';
};

// 定义标记点样式
const getMarkerStyles = () => {
  return {
    'default': new TMap.MarkerStyle({
      width: 20,
      height: 30,
      anchor: { x: 10, y: 30 },
      src: 'https://mapapi.qq.com/web/lbs/javascriptGL/demo/img/center.png'
    }),

    'grid': new TMap.MarkerStyle({
      width: 25,
      height: 25,
      anchor: { x: 12.5, y: 12.5 },
      src: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjUiIGhlaWdodD0iMjUiIHZpZXdCb3g9IjAgMCAyNSAyNSIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPHJlY3Qgd2lkdGg9IjI1IiBoZWlnaHQ9IjI1IiByeD0iNCIgZmlsbD0iIzE4OTBGRiIvPgo8L3N2Zz4K'
    }),

    // 路灯在线 - 绿色
    'light-online': new TMap.MarkerStyle({
      width: 24,
      height: 24,
      anchor: { x: 12, y: 12 },
      src: 'data:image/svg+xml;base64,PHN2ZyB0PSIxNzYzNDQ2MjI4MDMxIiBjbGFzcz0iaWNvbiIgdmlld0JveD0iMCAwIDEwMjQgMTAyNCIgdmVyc2lvbj0iMS4xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMDAiIGhlaWdodD0iMjAwIj48cGF0aCBkPSJNNTEwLjYgMy40Yy0yNDcuNSAwLTQ0OC4xIDIwNC00NDguMSA0NTQuOSAwIDkzLjUgMjMgMTcxLjEgNzUuNSAyNTIuNiAxMDMuNyAxNzIuOCAzMDUuNSAzMDguNSAzNzMgMzA4LjUgNjcuNCAwIDI2OC44LTEzNi42IDM3My0zMDguNSA1MS42LTgxLjUgNzUuNS0xNTkuMiA3NS41LTI1Mi42LTAuOC0yNTEtMjAxLjQtNDU0LjktNDQ4LjktNDU0Ljl6IG0wIDgyMS44Yy0yMDYuMSAwLTM3My40LTE2Ny4zLTM3My40LTM3My40IDAtMjA2LjEgMTY3LjMtMzczLjQgMzczLjQtMzczLjRDNzE3LjcgNzguNSA4ODUgMjQ1LjcgODg1IDQ1MS45YzAgMjA2LjEtMTY3LjMgMzczLjMtMzczLjQgMzczLjN6IG0wIDAiIGZpbGw9IiM1MmM0MWEiPjwvcGF0aD48cGF0aCBkPSJNNTEyIDQ1MS45bS0zNzMuNCAwYTM3My40IDM3My40IDAgMSAwIDc0Ni44IDAgMzczLjQgMzczLjQgMCAxIDAtNzQ2LjggMFoiIGZpbGw9IiNGRkZGRkYiPjwvcGF0aD48cGF0aCBkPSJNNjkwIDI5OS4xdi0xOS4yYzAtMjYuOS0yMS44LTQ4LjYtNDguNi00OC42SDM4MS45Yy0yNi45IDAtNDguNiAyMS44LTQ4LjYgNDguNnYxOS4yYy0xOS40IDYuOC0zMi40IDI1LjEtMzIuNCA0NS43IDAgMjYuOSAyMS44IDQ4LjYgNDguNiA0OC42IDI2LjkgMCA0OC42LTIxLjggNDguNi00OC42IDAtMjAuNi0xMy0zOC45LTMyLjQtNDUuN3YtMTkuMmMwLTkgNy4zLTE2LjIgMTYuMi0xNi4yaDExMy41djI3NS42aC02LjhjLTkgMC0xNy41IDMuNi0yMy45IDEwLTYuMyA2LjQtOS45IDE1LTkuOCAyMy45bC03IDk1LjhoLTQ5LjhjLTguNyAwLjQtMTUuNSA3LjUtMTUuNSAxNi4yczYuOCAxNS44IDE1LjUgMTYuMmg0My4yYzkgMCAxNi4yLTcuMyAxNi4yLTE2LjIgMC05LTcuMy0xNi4yLTE2LjItMTYuMmgtNjZsLTcuMS05N2MwLTE3LjUtMTUtMzIuNy0zMy43LTMyLjdoLTYuOFYyNjMuN2gxMTMuNWM5IDAgMTYuMiA3LjMgMTYuMiAxNi4ydjE5LjJjLTE5LjQgNi44LTMyLjQgMjUuMS0zMi40IDQ1LjcgMCAyNi45IDIxLjggNDguNiA0OC42IDQ4LjYgMjYuOSAwIDQ4LjYtMjEuOCA0OC42LTQ4LjYgMC4yLTIwLjYtMTIuOC0zOC45LTMyLjItNDUuN3ogbS0zNDAuNSA2MS44Yy05IDAtMTYuMi03LjMtMTYuMi0xNi4yIDAtOSA3LjMtMTYuMiAxNi4yLTE2LjIgOSAwIDE2LjIgNy4zIDE2LjIgMTYuMiAwIDktNy4zIDE2LjItMTYuMiAxNi4yem0xODYuMyAyMTIuM2w3IDk1LjhoLTYyLjRsNi45LTk0LjZjMC0yIDAuNi0yLjYgMS4zLTIuNmg0NS45YzAuOC0wLjEgMS4zIDAuNSAxLjMgMS40em0xMzcuOS0yMTIuM2MtOSAwLTE2LjItNy4zLTE2LjItMTYuMiAwLTkgNy4zLTE2LjIgMTYuMi0xNi4yIDkgMCAxNi4yIDcuMyAxNi4yIDE2LjIgMC4xIDktNy4yIDE2LjItMTYuMiAxNi4yeiBtMCAwIiBmaWxsPSIjNTJjNDFhIj48L3BhdGg+PC9zdmc+'
    }),

    // 路灯离线 - 红色
    'light-offline': new TMap.MarkerStyle({
      width: 24,
      height: 24,
      anchor: { x: 12, y: 12 },
      src: 'data:image/svg+xml;base64,PHN2ZyB0PSIxNzYzNDQ2MjI4MDMxIiBjbGFzcz0iaWNvbiIgdmlld0JveD0iMCAwIDEwMjQgMTAyNCIgdmVyc2lvbj0iMS4xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMDAiIGhlaWdodD0iMjAwIj48cGF0aCBkPSJNNTEwLjYgMy40Yy0yNDcuNSAwLTQ0OC4xIDIwNC00NDguMSA0NTQuOSAwIDkzLjUgMjMgMTcxLjEgNzUuNSAyNTIuNiAxMDMuNyAxNzIuOCAzMDUuNSAzMDguNSAzNzMgMzA4LjUgNjcuNCAwIDI2OC44LTEzNi42IDM3My0zMDguNSA1MS42LTgxLjUgNzUuNS0xNTkuMiA3NS41LTI1Mi42LTAuOC0yNTEtMjAxLjQtNDU0LjktNDQ4LjktNDU0Ljl6IG0wIDgyMS44Yy0yMDYuMSAwLTM3My40LTE2Ny4zLTM3My40LTM3My40IDAtMjA2LjEgMTY3LjMtMzczLjQgMzczLjQtMzczLjRDNzE3LjcgNzguNSA4ODUgMjQ1LjcgODg1IDQ1MS45YzAgMjA2LjEtMTY3LjMgMzczLjMtMzczLjQgMzczLjN6IG0wIDAiIGZpbGw9IiNGRjRERkYiPjwvcGF0aD48cGF0aCBkPSJNNTEyIDQ1MS45bS0zNzMuNCAwYTM3My40IDM3My40IDAgMSAwIDc0Ni44IDAgMzczLjQgMzczLjQgMCAxIDAtNzQ2LjggMFoiIGZpbGw9IiNGRkZGRkYiPjwvcGF0aD48cGF0aCBkPSJNNjkwIDI5OS4xdi0xOS4yYzAtMjYuOS0yMS44LTQ4LjYtNDguNi00OC42SDM4MS45Yy0yNi45IDAtNDguNiAyMS44LTQ4LjYgNDguNnYxOS4yYy0xOS40IDYuOC0zMi40IDI1LjEtMzIuNCA0NS43IDAgMjYuOSAyMS44IDQ4LjYgNDguNiA0OC42IDI2LjkgMCA0OC42LTIxLjggNDguNi00OC42IDAtMjAuNi0xMy0zOC45LTMyLjQtNDUuN3YtMTkuMmMwLTkgNy4zLTE2LjIgMTYuMi0xNi4yaDExMy41djI3NS42aC02LjhjLTkgMC0xNy41IDMuNi0yMy45IDEwLTYuMyA2LjQtOS45IDE1LTkuOCAyMy45bC03IDk1LjhoLTQ5LjhjLTguNyAwLjQtMTUuNSA3LjUtMTUuNSAxNi4yczYuOCAxNS44IDE1LjUgMTYuMmg0My4yYzkgMCAxNi4yLTcuMyAxNi4yLTE2LjIgMC05LTcuMy0xNi4yLTE2LjItMTYuMmgtNjZsLTcuMS05N2MwLTE3LjUtMTUtMzIuNy0zMy43LTMyLjdoLTYuOFYyNjMuN2gxMTMuNWM5IDAgMTYuMiA3LjMgMTYuMiAxNi4ydjE5LjJjLTE5LjQgNi44LTMyLjQgMjUuMS0zMi40IDQ1LjcgMCAyNi45IDIxLjggNDguNiA0OC42IDQ4LjYgMjYuOSAwIDQ4LjYtMjEuOCA0OC42LTQ4LjYgMC4yLTIwLjYtMTIuOC0zOC45LTMyLjItNDUuN3ogbS0zNDAuNSA2MS44Yy05IDAtMTYuMi03LjMtMTYuMi0xNi4yIDAtOSA3LjMtMTYuMiAxNi4yLTE2LjIgOSAwIDE2LjIgNy4zIDE2LjIgMTYuMiAwIDktNy4zIDE2LjItMTYuMiAxNi4yem0xODYuMyAyMTIuM2w3IDk1LjhoLTYyLjRsNi45LTk0LjZjMC0yIDAuNi0yLjYgMS4zLTIuNmg0NS45YzAuOC0wLjEgMS4zIDAuNSAxLjMgMS40em0xMzcuOS0yMTIuM2MtOSAwLTE2LjItNy4zLTE2LjItMTYuMiAwLTkgNy4zLTE2LjIgMTYuMi0xNi4yIDkgMCAxNi4yIDcuMyAxNi4yIDE2LjIgMC4xIDktNy4yIDE2LjItMTYuMiAxNi4yeiBtMCAwIiBmaWxsPSIjRkY0REZGIj48L3BhdGg+PC9zdmc+'
    }),

    // 监控在线 - 蓝色
    'camera-online': new TMap.MarkerStyle({
      width: 24,
      height: 24,
      anchor: { x: 12, y: 12 },
      src: 'data:image/svg+xml;base64,PHN2ZyB0PSIxNzYzNDQ2NDUyNjI2IiBjbGFzcz0iaWNvbiIgdmlld0JveD0iMCAwIDEwMjQgMTAyNCIgdmVyc2lvbj0iMS4xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMDAiIGhlaWdodD0iMjAwIj48cGF0aCBkPSJNNTEyIDMyMC4yYy04MC4zIDAtMTQ1LjcgNjkuNS0xNDUuNyAxNTQuOCAwIDQxLjQgMTUuMiA4MC4zIDQyLjcgMTA5LjUgMjcuNSAyOS4yIDY0LjEgNDUuNCAxMDMgNDUuNCAzOC44IDAgNzUuNC0xNi4xIDEwMi45LTQ1LjQgMjcuNS0yOS4zIDQyLjctNjguMiA0Mi43LTEwOS41IDAuMS04NS4zLTY1LjMtMTU0LjgtMTQ1LjYtMTU0Ljh6IG0tMC41IDIyMy43Yy0xOC4zIDAtMzUuNS03LjYtNDguNC0yMS41LTEyLjktMTMuOS0yMC4xLTMyLjMtMjAuMS01MiAwLTQwLjUgMzAuNy03My41IDY4LjUtNzMuNXM2OC41IDMzIDY4LjUgNzMuNS0zMC43IDczLjUtNjguNSA3My41eiIgZmlsbD0iIzE4OTBGRiI+PC9wYXRoPjxwYXRoIGQ9Ik04ODEgNDIxLjJ2LTUuNGMtMC44LTk1LTQwLjMtMTg0LTExMS4yLTI1MC43QzY5OS41IDk4LjkgNjA2LjYgNjIuNSA1MDkgNjMuOWMtMjAzLjQgMS42LTM2Ny42IDE2MS45LTM2NiAzNTcuNHY1LjljMS40IDE1MC4xIDEwMC45IDI4MS45IDI0OC41IDMzMC4xbC00OC43IDEyNS4xaC05Ny4yYy0xOC41IDAtMzYuMSAxMS41LTQwLjUgMjkuNC02LjMgMjUuNSAxMy44IDQ4LjMgMzkuMyA0OC4zaDUzNC4yYzE4LjUgMCAzNi4xLTExLjUgNDAuNS0yOS41IDYuMi0yNS41LTEzLjgtNDguMi0zOS4zLTQ4LjJoLTk4LjVsLTQ4LjctMTI1YzUzLjctMTcuNSAxMDMuMS00Ny4zIDE0My4zLTg2LjYgNjguNy02NyAxMDYtMTU1LjcgMTA1LjEtMjQ5LjZNNjEwLjQgODQ5LjhsNDQuNi0xMTguOGMxNC4zIDEuNSAyNy4zIDIuMyAzOS42IDIuM2g5LjNjMTEuNi0wLjEgMjMuOC0wLjggMzcuMi0yLjJsNDQuNiAxMTguN0g0Mjl6IG0yMzcuMy0yMzMuNmMtNDUuMiAyNi42LTk4LjMgMzkuMy0xNTEuNyAzOS40SDUwOWMtMTU3LjYgMC0yODYuOS0xMTktMjg4LjMtMjY1LjJ2LTUuOEMyMTkuNCAyNjggMzQ5IDE0NS42IDUwOS43IDE0NC40aDIuM2MxNTkuMyAwIDI5MCAxMjAuMyAyOTEuMyAyNjguMnY1LjNjMC45IDkyLjgtNDguNyAxNzguOC0xMzcgMjMwLjl6IiBmaWxsPSIjMTg5MEZGIj48L3BhdGg+PHBhdGggZD0iTTUxMiAyODAuNGMyMS40IDAgMzguOC0xNy40IDM4LjgtMzguOHMtMTcuNC0zOC44LTM5LTM4LjhjLTYuNiAwLjItMTMuMyAyLTE5LjMgNS4zLTExLjggNi45LTE5LjMgMTkuOC0xOS40IDMzLjUgMC4xIDIxLjMgMTcuNSAzOC44IDM4LjkgMzguOHoiIGZpbGw9IiMxODkwRkYiPjwvcGF0aD48L3N2Zz4='
    }),

    // 监控离线 - 红色
    'camera-offline': new TMap.MarkerStyle({
      width: 24,
      height: 24,
      anchor: { x: 12, y: 12 },
      src: 'data:image/svg+xml;base64,PHN2ZyB0PSIxNzYzNDQ2NDUyNjI2IiBjbGFzcz0iaWNvbiIgdmlld0JveD0iMCAwIDEwMjQgMTAyNCIgdmVyc2lvbj0iMS4xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMDAiIGhlaWdodD0iMjAwIj48cGF0aCBkPSJNNTEyIDMyMC4yYy04MC4zIDAtMTQ1LjcgNjkuNS0xNDUuNyAxNTQuOCAwIDQxLjQgMTUuMiA4MC4zIDQyLjcgMTA5LjUgMjcuNSAyOS4yIDY0LjEgNDUuNCAxMDMgNDUuNCAzOC44IDAgNzUuNC0xNi4xIDEwMi45LTQ1LjQgMjcuNS0yOS4zIDQyLjctNjguMiA0Mi43LTEwOS41IDAuMS04NS4zLTY1LjMtMTU0LjgtMTQ1LjYtMTU0Ljh6IG0tMC41IDIyMy43Yy0xOC4zIDAtMzUuNS03LjYtNDguNC0yMS41LTEyLjktMTMuOS0yMC4xLTMyLjMtMjAuMS01MiAwLTQwLjUgMzAuNy03My41IDY4LjUtNzMuNXM2OC41IDMzIDY4LjUgNzMuNS0zMC43IDczLjUtNjguNSA3My41eiIgZmlsbD0iI0ZGNEZGRiI+PC9wYXRoPjxwYXRoIGQ9Ik04ODEgNDIxLjJ2LTUuNGMtMC44LTk1LTQwLjMtMTg0LTExMS4yLTI1MC43QzY5OS41IDk4LjkgNjA2LjYgNjIuNSA1MDkgNjMuOWMtMjAzLjQgMS42LTM2Ny42IDE2MS45LTM2NiAzNTcuNHY1LjljMS40IDE1MC4xIDEwMC45IDI4MS45IDI0OC41IDMzMC4xbC00OC43IDEyNS4xaC05Ny4yYy0xOC41IDAtMzYuMSAxMS41LTQwLjUgMjkuNC02LjMgMjUuNSAxMy44IDQ4LjMgMzkuMyA0OC4zaDUzNC4yYzE4LjUgMCAzNi4xLTExLjUgNDAuNS0yOS41IDYuMi0yNS41LTEzLjgtNDguMi0zOS4zLTQ4LjJoLTk4LjVsLTQ4LjctMTI1YzUzLjctMTcuNSAxMDMuMS00Ny4zIDE0My4zLTg2LjYgNjguNy02NyAxMDYtMTU1LjcgMTA1LjEtMjQ5LjZNNjEwLjQgODQ5LjhsNDQuNi0xMTguOGMxNC4zIDEuNSAyNy4zIDIuMyAzOS42IDIuM2g5LjNjMTEuNi0wLjEgMjMuOC0wLjggMzcuMi0yLjJsNDQuNiAxMTguN0g0Mjl6IG0yMzcuMy0yMzMuNmMtNDUuMiAyNi42LTk4LjMgMzkuMy0xNTEuNyAzOS40SDUwOWMtMTU3LjYgMC0yODYuOS0xMTktMjg4LjMtMjY1LjJ2LTUuOEMyMTkuNCAyNjggMzQ5IDE0NS42IDUwOS43IDE0NC40aDIuM2MxNTkuMyAwIDI5MCAxMjAuMyAyOTEuMyAyNjguMnY1LjNjMC45IDkyLjgtNDguNyAxNzguOC0xMzcgMjMwLjl6IiBmaWxsPSIjRkY0RkZGIj48L3BhdGg+PHBhdGggZD0iTTUxMiAyODAuNGMyMS40IDAgMzguOC0xNy40IDM4LjgtMzguOHMtMTcuNC0zOC44LTM5LTM4LjhjLTYuNiAwLjItMTMuMyAyLTE5LjMgNS4zLTExLjggNi45LTE5LjMgMTkuOC0xOS40IDMzLjUgMC4xIDIxLjMgMTcuNSAzOC44IDM4LjkgMzguOHoiIGZpbGw9IiNGRjRGRkYiPjwvcGF0aD48L3N2Zz4='
    }),

    // 消防设施在线 - 橙色
    'fire-online': new TMap.MarkerStyle({
      width: 24,
      height: 24,
      anchor: { x: 12, y: 12 },
      src: 'data:image/svg+xml;base64,PHN2ZyB0PSIxNzYzNDQ2NjIzODM5IiBjbGFzcz0iaWNvbiIgdmlld0JveD0iMCAwIDEwMjQgMTAyNCIgdmVyc2lvbj0iMS4xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMDAiIGhlaWdodD0iMjAwIj48cGF0aCBkPSJNNTAxLjA1IDBjMS4wNyAwIDIuMDUgMC4xOSAzLjA3IDAuMzNoNDMuMjlDNTA4LjUzIDAuMTkgNTA5LjQ2IDAgNTEwLjUzIDBoMTAuNjFhMjcgMjcgMCAwIDEgMjYuNjIgMjcuNDJsLTAuMDUgMC42NWM3Ny43MyAxNi42NiAxNDEuMDMgNzMuNTQgMTY4LjgyIDE0OC40OGg0NC4xMmMyNS4xMyAwIDQ1LjQzIDE4LjY2IDQ1LjQzIDQxLjc1IDAgMjMuMDQtMjAuMyA0MS43LTQ1LjQzIDQxLjdINTQwLjZ2MTY5LjgxYzE2LjAxLTMuMjYgMzIuNTggMS4wMiA0NS4xNSAxMS42OCAxMi42MSAxMC42NiAxOS45MiAyNi41NCAxOS45MiA0My4yOXYxMjUuMjVjMCAxNi43NS03LjMxIDMyLjY3LTE5Ljg3IDQzLjMzLTEyLjYxIDEwLjY2LTI5LjE4IDE0Ljk0LTQ1LjE1IDExLjY4djI0MS4xSDgxNy44VjEwMjRIMTkzLjgydi0xMDcuODVoNzQuMjl2LTI0MS4xYy0xNS45NyAzLjI2LTMyLjU4LTEuMDItNDUuMTUtMTEuNjRhNTYuNzQgNTYuNzQgMCAwIDEtMTkuOTItNDMuMzh2LTEyNS4yMWMwLTE2Ljc1IDcuMzEtMzIuNjcgMTkuOTItNDMuMzMgMTIuNTctMTAuNjYgMjkuMTgtMTQuODkgNDUuMTUtMTEuNjhWMjYwSDIzMS42MWMtMjUuMDUgMC00NS40My0xOC42Ni00NS40My00MS43NSAwLTIzLjA5IDIwLjM0LTQxLjc1IDQ1LjQzLTQxLjc1aDUzLjUzYzMxLjgyLTc0Ljk0IDk1LjE0LTEzMS44NyAxNzIuODUtMTQ4LjVsLTAuMDUtMC42NWEyNyAyNyAwIDAgMSAyNi42Mi0yNy40MnpNNTEyIDQ0NS4wN2MtODQuNzYgMC0xNTMuNDEgNzAuNjYtMTUzLjQxIDE1Ny43OSAwIDg3LjIzIDY4LjY1IDE1Ny44OCAxNTMuMzcgMTU3Ljg4IDg0LjcxIDAgMTUzLjQxLTcwLjY1IDE1My40MS0xNTcuODRTNTk2LjY3IDQ0NS4wNyA1MTEuOTUgNDQ1LjA3em0tNDkuNTcgNjkuMzVhOTYuNDkgOTYuNDkgMCAwIDEgOTkuMSAwYzI3LjE3IDE1LjkgNDkuMzQgNjguNDQgNDkuMzQgODguNDQgMCAyMC0yMi4xNyA3Mi41NC00OS4zNCA4OC40M2E5Ni40OSA5Ni40OSAwIDAgMS05OS4xNCAwYy0yNy4xNy0xNS44OS00OS4zNC02OC40My00OS4zNC04OC40MyAwLTIyLjM2IDIyLjE3LTcyLjU0IDQ5LjM0LTg4LjQ0eiIgZmlsbD0iI0ZBQThGNSI+PC9wYXRoPjwvc3ZnPg=='
    }),

    // 消防设施离线 - 红色
    'fire-offline': new TMap.MarkerStyle({
      width: 24,
      height: 24,
      anchor: { x: 12, y: 12 },
      src: 'data:image/svg+xml;base64,PHN2ZyB0PSIxNzYzNDQ2NjIzODM5IiBjbGFzcz0iaWNvbiIgdmlld0JveD0iMCAwIDEwMjQgMTAyNCIgdmVyc2lvbj0iMS4xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMDAiIGhlaWdodD0iMjAwIj48cGF0aCBkPSJNNTAxLjA1IDBjMS4wNyAwIDIuMDUgMC4xOSAzLjA3IDAuMzNoNDMuMjlDNTA4LjUzIDAuMTkgNTA5LjQ2IDAgNTEwLjUzIDBoMTAuNjFhMjcgMjcgMCAwIDEgMjYuNjIgMjcuNDJsLTAuMDUgMC42NWM3Ny43MyAxNi42NiAxNDEuMDMgNzMuNTQgMTY4LjgyIDE0OC40OGg0NC4xMmMyNS4xMyAwIDQ1LjQzIDE4LjY2IDQ1LjQzIDQxLjc1IDAgMjMuMDQtMjAuMyA0MS43LTQ1LjQzIDQxLjdINTQwLjZ2MTY5LjgxYzE2LjAxLTMuMjYgMzIuNTggMS4wMiA0NS4xNSAxMS42OCAxMi42MSAxMC42NiAxOS45MiAyNi41NCAxOS45MiA0My4yOXYxMjUuMjVjMCAxNi43NS03LjMxIDMyLjY3LTE5Ljg3IDQzLjMzLTEyLjYxIDEwLjY2LTI5LjE4IDE0Ljk0LTQ1LjE1IDExLjY4djI0MS4xSDgxNy44VjEwMjRIMTkzLjgydi0xMDcuODVoNzQuMjl2LTI0MS4xYy0xNS45NyAzLjI2LTMyLjU4LTEuMDItNDUuMTUtMTEuNjRhNTYuNzQgNTYuNzQgMCAwIDEtMTkuOTItNDMuMzh2LTEyNS4yMWMwLTE2Ljc1IDcuMzEtMzIuNjcgMTkuOTItNDMuMzMgMTIuNTctMTAuNjYgMjkuMTgtMTQuODkgNDUuMTUtMTEuNjhWMjYwSDIzMS42MWMtMjUuMDUgMC00NS40My0xOC42Ni00NS40My00MS43NSAwLTIzLjA5IDIwLjM0LTQxLjc1IDQ1LjQzLTQxLjc1aDUzLjUzYzMxLjgyLTc0Ljk0IDk1LjE0LTEzMS44NyAxNzIuODUtMTQ4LjVsLTAuMDUtMC42NWEyNyAyNyAwIDAgMSAyNi42Mi0yNy40MnpNNTEyIDQ0NS4wN2MtODQuNzYgMC0xNTMuNDEgNzAuNjYtMTUzLjQxIDE1Ny43OSAwIDg3LjIzIDY4LjY1IDE1Ny44OCAxNTMuMzcgMTU3Ljg4IDg0LjcxIDAgMTUzLjQxLTcwLjY1IDE1My40MS0xNTcuODRTNTk2LjY3IDQ0NS4wNyA1MTEuOTUgNDQ1LjA3em0tNDkuNTcgNjkuMzVhOTYuNDkgOTYuNDkgMCAwIDEgOTkuMSAwYzI3LjE3IDE1LjkgNDkuMzQgNjguNDQgNDkuMzQgODguNDQgMCAyMC0yMi4xNyA3Mi41NC00OS4zNCA4OC40M2E5Ni40OSA5Ni40OSAwIDAgMS05OS4xNCAwYy0yNy4xNy0xNS44OS00OS4zNC02OC40My00OS4zNC04OC40MyAwLTIyLjM2IDIyLjE3LTcyLjU0IDQ5LjM0LTg4LjQ0eiIgZmlsbD0iI0ZGNEZGRiI+PC9wYXRoPjwvc3ZnPg=='
    }),

    // 垃圾点在线 - 黄色
    'garbage-online': new TMap.MarkerStyle({
      width: 24,
      height: 24,
      anchor: { x: 12, y: 12 },
      src: 'data:image/svg+xml;base64,PHN2ZyB0PSIxNzYzNDQ2NjgxMzI3IiBjbGFzcz0iaWNvbiIgdmlld0JveD0iMCAwIDEyMTUgMTAyNCIgdmVyc2lvbj0iMS4xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMDAiIGhlaWdodD0iMjAwIj48cGF0aCBkPSJNOTYyLjc2IDQzNC45M2EyNTIuMjQgMjUyLjI0IDAgMCAwLTI1Mi4yNCAyNTIuMjRjMCAyMTAuMiAyNTIuMjQgMzM2LjgzIDI1Mi4yNCAzMzYuODNzMjUyLjI0LTEyNi4xMiAyNTIuMjQtMzM2LjgzYTI1Mi4yNCAyNTIuMjQgMCAwIDAtMjUyLjI0LTI1Mi4yNHogbTAgMzM2LjgzYTg0LjA4IDg0LjA4IDAgMSAxIDg0LjA4LTg0LjA4IDg0LjA4IDg0LjA4IDAgMCAxLTg0LjA4IDg0LjA4eiIgZmlsbD0iI0ZBQThGNSI+PC9wYXRoPjxwYXRoIGQ9Ik02ODQuNTcgNjg3LjE2YTI3OC4xOSAyNzguMTkgMCAwIDEgMjA1LjAxLTI2Ny4yOVYzMjMuMzRINzUuOTd2NjQ2LjY4aDc1Ni4xOWEzODEuOTkgMzgxLjk5IDAgMCAxLTE1Mi41OS0yODIuODZ6IG0tMjgwLjI2IDEyMC45M0gzMjMuMzRWNDg1LjI3aDgwLjk3em0yNDIuMzggMEg1NjUuNzJWNDg1LjI3aDgwLjk3ek05NzAuMDIgODAuOTZINjQ2LjY4VjBIMzIzLjM0djgwLjk2SDB2MTYxLjQxaDk3MC4wMlY4MC45NnoiIGZpbGw9IiNGQUE4RjUiPjwvcGF0aD48L3N2Zz4='
    }),

    // 垃圾点离线 - 红色
    'garbage-offline': new TMap.MarkerStyle({
      width: 24,
      height: 24,
      anchor: { x: 12, y: 12 },
      src: 'data:image/svg+xml;base64,PHN2ZyB0PSIxNzYzNDQ2NjgxMzI3IiBjbGFzcz0iaWNvbiIgdmlld0JveD0iMCAwIDEyMTUgMTAyNCIgdmVyc2lvbj0iMS4xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMDAiIGhlaWdodD0iMjAwIj48cGF0aCBkPSJNOTYyLjc2IDQzNC45M2EyNTIuMjQgMjUyLjI0IDAgMCAwLTI1Mi4yNCAyNTIuMjRjMCAyMTAuMiAyNTIuMjQgMzM2LjgzIDI1Mi4yNCAzMzYuODNzMjUyLjI0LTEyNi4xMiAyNTIuMjQtMzM2LjgzYTI1Mi4yNCAyNTIuMjQgMCAwIDAtMjUyLjI0LTI1Mi4yNHogbTAgMzM2LjgzYTg0LjA4IDg0LjA4IDAgMSAxIDg0LjA4LTg0LjA4IDg0LjA4IDg0LjA4IDAgMCAxLTg0LjA4IDg0LjA4eiIgZmlsbD0iI0ZGNEZGRiI+PC9wYXRoPjxwYXRoIGQ9Ik02ODQuNTcgNjg3LjE2YTI3OC4xOSAyNzguMTkgMCAwIDEgMjA1LjAxLTI2Ny4yOVYzMjMuMzRINzUuOTd2NjQ2LjY4aDc1Ni4xOWEzODEuOTkgMzgxLjk5IDAgMCAxLTE1Mi41OS0yODIuODZ6IG0tMjgwLjI2IDEyMC45M0gzMjMuMzRWNDg1LjI3aDgwLjk3em0yNDIuMzggMEg1NjUuNzJWNDg1LjI3aDgwLjk3ek05NzAuMDIgODAuOTZINjQ2LjY4VjBIMzIzLjM0djgwLjk2SDB2MTYxLjQxaDk3MC4wMlY4MC45NnoiIGZpbGw9IiNGRjRGRkYiPjwvcGF0aD48L3N2Zz4='
    }),

    // 事件 - 高级（红色）
    'event-high': new TMap.MarkerStyle({
      width: 24,
      height: 24,
      anchor: { x: 12, y: 12 },
      src: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjQiIGhlaWdodD0iMjQiIHZpZXdCb3g9IjAgMCAyNCAyNCIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPGNpcmNsZSBjeD0iMTIiIGN5PSIxMiIgcj0iMTAiIGZpbGw9IiNGRjRERkYiLz4KPHBhdGggZD0iTTEyIDdWMTNNMTIgMTZIMTIuMDEiIHN0cm9rZT0id2hpdGUiIHN0cm9rZS13aWR0aD0iMiIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2UtbGluZWpvaW49InJvdW5kIi8+Cjwvc3ZnPgo='
    }),

    // 事件 - 中级（橙色）
    'event-medium': new TMap.MarkerStyle({
      width: 22,
      height: 22,
      anchor: { x: 11, y: 11 },
      src: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjIiIGhlaWdodD0iMjIiIHZpZXdCb3g9IjAgMCAyMiAyMiIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPGNpcmNsZSBjeD0iMTEiIGN5PSIxMSIgcj0iOSIgZmlsbD0iI0ZBQThGRiIvPgo8cGF0aCBkPSJNMTEgN1YxMk0xMSAxNUgxMS4wMSIgc3Ryb2tlPSJ3aGl0ZSIgc3Ryb2tlLXdpZHRoPSIyIiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiLz4KPC9zdmc+Cg=='
    }),

    // 事件 - 低级（黄色）
    'event-low': new TMap.MarkerStyle({
      width: 20,
      height: 20,
      anchor: { x: 10, y: 10 },
      src: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAiIGhlaWdodD0iMjAiIHZpZXdCb3g9IjAgMCAyMCAyMCIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPGNpcmNsZSBjeD0iMTAiIGN5PSIxMCIgcj0iOCIgZmlsbD0iI0ZBQThGNSIvPgo8cGF0aCBkPSJNMTAgN1YxMU0xMCAxNEgxMC4wMSIgc3Ryb2tlPSJ3aGl0ZSIgc3Ryb2tlLXdpZHRoPSIyIiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiLz4KPC9zdmc+Cg=='
    }),

    // 重点区域
    'poi': new TMap.MarkerStyle({
      width: 22,
      height: 22,
      anchor: { x: 11, y: 11 },
      src: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjIiIGhlaWdodD0iMjIiIHZpZXdCb3g9IjAgMCAyMiAyMiIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPHJlY3Qgd2lkdGg9IjIyIiBoZWlnaHQ9IjIyIiByeD0iNCIgZmlsbD0iI0ZBQThGNSIvPgo8dGV4dCB4PSI1MCIgeT0iNTAiIGZvbnQtc2l6ZT0iMTIiIGZpbGw9IndoaXRlIiB0ZXh0LWFuY2hvcj0ibWlkZGxlIiBkeT0iLjNlbSIgdHJhbnNmb3JtPSJzY2FsZSgwLjQpIj7lhbPogYw8L3RleHQ+Cjwvc3ZnPgo='
    })
  };
};

// 地图操作方法
const zoomIn = () => {
  if (map) {
    map.zoomTo(map.getZoom() + 1);
  }
};

const zoomOut = () => {
  if (map) {
    map.zoomTo(map.getZoom() - 1);
  }
};

const reset = () => {
  if (map) {
    map.setCenter(new TMap.LatLng(26.793227, 117.810114));
    map.setZoom(11);
  }
};

// 监听geometriesArray变化，更新标记点
watch(() => props.geometriesArray, () => {
  updateMarkers();
}, { deep: true });

// 组件挂载后初始化地图
onMounted(() => {
  initMap();
});

// 暴露方法给父组件
defineExpose({
  zoomIn,
  zoomOut,
  reset
});
</script>

<style lang="scss" scoped>
.map-common-css {
  border-radius: 8px;
  overflow: hidden;
  /* 确保地图可以交互 */
}
</style>
