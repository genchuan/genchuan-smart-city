<template>
  <div class="h-full w-full relative">
    <div ref="mapContainer" class="h-full w-full"></div>
    <!-- 导出JSON按钮 -->
    <div class="absolute left-4 top-4 p-2 bg-white rounded shadow" style="z-index: 1000">
      <el-button @click="downloadPreviewAsJson" class="cursor-pointer"> 导出预览为JSON</el-button>
    </div>
  </div>
</template>
<script setup lang="ts">
import { ref, watch, onMounted, onBeforeUnmount } from 'vue'
import { loadTMap } from '@/utils/useTMap'
import download from '@/utils/download'
import { GridTopicLayerVO } from '@/api/dataHub/gridManagement/gridDiagramManage/gridtopiclayer'

const props = defineProps<{
  layers: GridTopicLayerVO[]
  // drawerVisible: boolean  // 新增抽屉可见状态prop
}>()
const mapContainer = ref<HTMLDivElement | null>(null)
const mapInstance = ref<any>(null)
const drawnLayers: any[] = [] // 保留图层管理数组
const infoWindows = ref<Record<string, any>>({}) // 存储信息窗口实例，按图层ID映射

// 构造经纬度demo数组
const getDemoCoordinates = (index: number) => {
  const baseLat = 39.9042
  const baseLng = 116.4074
  const offset = index * 0.05
  const size = 0.15 - index * 0.02
  return [
    { lat: baseLat + offset, lng: baseLng + offset },
    { lat: baseLat + offset + size, lng: baseLng + offset },
    { lat: baseLat + offset + size, lng: baseLng + offset + size },
    { lat: baseLat + offset, lng: baseLng + offset + size }
  ]
}

const safeNumber = (v: any, fallback = 0) => (typeof v === 'number' ? v : parseInt(v) || fallback)

/** 初始化地图 */
const initMap = async () => {
  const TMap = await loadTMap()
  if (!mapContainer.value) return
  try {
    mapInstance.value = new TMap.Map(mapContainer.value, {
      center: new TMap.LatLng(39.9042, 116.4074),
      zoom: 12
    })
  } catch (err) {
    console.error('地图初始化失败（TMap）', err)
  }
  redraw()
}

/** 清理已有绘制（含信息窗口） */
const clearDrawn = () => {
  try {
    // 清理多边形/标记图层
    for (const l of drawnLayers) {
      if (l && l.setMap) l.setMap(null)
    }
    drawnLayers.length = 0

    // 清理信息窗口
    Object.values(infoWindows.value).forEach((win) => {
      if (win && win.close) win.close()
    })
    infoWindows.value = {}
  } catch (e) {
    console.warn('清理绘制出错', e)
  }
}

/** 16进制颜色转RGBA格式 */
const hexToRgba = (hex: string, opacity: number = 0.5) => {
  const fullHex =
    hex.length === 4
      ? hex.replace(/([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])/g, '$1$1$2$2$3$3')
      : hex.slice(1)
  const r = parseInt(fullHex.slice(0, 2), 16)
  const g = parseInt(fullHex.slice(2, 4), 16)
  const b = parseInt(fullHex.slice(4, 6), 16)
  return `rgba(${r}, ${g}, ${b}, ${opacity})`
}

/** 构建信息窗口内容 */
const buildInfoWindowContent = (layer: GridTopicLayerVO) => {
  return `
    <div style="padding: 10px; min-width: 200px;">
      <div style="font-weight: bold; margin-bottom: 8px; color: #333;">${layer.layerName || '未命名图层'}</div>
      <div style="font-size: 12px; line-height: 1.6; color: #666;">
        <div>图层ID：${layer.layerId || '-'}</div>
        <div>网格类型：${layer.gridType || '-'}</div>
        <div>比例尺：${layer.scale || '-'}</div>
        <div>显示顺序：${layer.layerWo || 0}</div>
        <div>创建人：${layer.createUserId || '-'}</div>
        <div>显示状态：${layer.displayStatus === '1' ? '显示' : '隐藏'}</div>
      </div>
    </div>
  `
}

/** 绘制图层（含标记+点击显示信息窗口） */
const drawLayer = (layer: GridTopicLayerVO, index: number) => {
  if (!mapInstance.value) return
  if (layer.displayStatus !== '1') return // 只绘制显示状态的图层

  const TMap = (window as any).TMap
  const coordinates = getDemoCoordinates(index)
  const path = coordinates.map((coord) => new TMap.LatLng(coord.lat, coord.lng))
  const color = pickColorByIndex(index)
  const fillColor = hexToRgba(color, 0.5)
  const borderColor = hexToRgba(color, 1)
  const layerId = layer.id || `layer_${index}` // 图层唯一标识

  try {
    // 1. 绘制多边形图层
    const polygon = new TMap.MultiPolygon({
      id: `polygon-${layerId}`,
      map: mapInstance.value,
      styles: {
        default: new TMap.PolygonStyle({
          fillColor: fillColor,
          fillOpacity: 0.5,
          showBorder: true,
          borderColor: borderColor,
          borderWidth: 2,
          borderDashArray: []
        })
      },
      geometries: [
        {
          id: `poly-${layerId}`,
          styleId: 'default',
          paths: path,
          properties: {
            title: layer.layerName || `图层-${layerId}`
          }
        }
      ]
    })
    drawnLayers.push(polygon)

    // 2. 计算多边形中心点（用于标记位置）
    const centerLat = coordinates.reduce((sum, coord) => sum + coord.lat, 0) / coordinates.length
    const centerLng = coordinates.reduce((sum, coord) => sum + coord.lng, 0) / coordinates.length
    const markerPosition = new TMap.LatLng(centerLat, centerLng)

    // 3. 创建MultiMarker标记（对齐官方示例）
    const marker = new TMap.MultiMarker({
      map: mapInstance.value,
      styles: {
        markerStyle: new TMap.MarkerStyle({
          width: 30, // 标记宽度
          height: 30, // 标记高度
          anchor: { x: 15, y: 30 }, // 锚点（标记底部中点）
          // 自定义标记内容（支持HTML）
          content: `<div style="
            width: 60px;
            height: 24px;
            line-height: 24px;
            text-align: center;
            background: ${color};
            color: white;
            border-radius: 4px;
            font-size: 12px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.2);
          ">${layer.layerName || `图层-${layerId}`}</div>`
        })
      },
      // 标记数据（对齐官方示例）
      geometries: [
        {
          id: `marker-${layerId}`, // 唯一标识
          styleId: 'markerStyle', // 关联样式
          position: markerPosition // 标记位置
        }
      ]
    })
    drawnLayers.push(marker)

    // 4. 绑定标记点击事件，显示信息窗口（对齐腾讯地图信息窗口示例）
    marker.on('click', (evt: any) => {
      // 关闭其他信息窗口
      Object.values(infoWindows.value).forEach((win) => win.close())

      // 创建/显示当前图层的信息窗口
      if (!infoWindows.value[layerId]) {
        infoWindows.value[layerId] = new TMap.InfoWindow({
          map: mapInstance.value,
          position: markerPosition, // 信息窗口位置（与标记同点）
          content: buildInfoWindowContent(layer), // 图层基本信息
          offset: { x: 0, y: -30 } // 偏移，避免遮挡标记
        })
      } else {
        infoWindows.value[layerId].open() // 已存在则直接打开
      }
    })
  } catch (err) {
    console.warn('绘制图层出错', err)
  }
}

// 根据索引选择颜色
const pickColorByIndex = (i: number) => {
  const palette = [
    '#e53e3e',
    '#3182ce',
    '#38a169',
    '#dd6b20',
    '#805ad5',
    '#d53f8c',
    '#ed64a6',
    '#718096'
  ]
  return palette[i % palette.length]
}

/** 重新绘制 */
const redraw = () => {
  clearDrawn()
  // 按顺序排序图层，确保新增图层按正确顺序显示
  const layers = (props.layers ?? [])
    .slice()
    .sort((a, b) => safeNumber(a.layerWo, 0) - safeNumber(b.layerWo, 0))
  layers.forEach((layer, index) => {
    drawLayer(layer, index)
  })
}

/** 导出JSON */
const downloadPreviewAsJson = () => {
  const blob = new Blob([JSON.stringify(props.layers, null, 2)], { type: 'application/json' })
  download.json(blob, `preview_layers_${Date.now()}.json`)
}

// 监听图层变化，确保新增/修改后能重绘
watch(
  () => props.layers,
  () => {
    if (mapInstance.value) {
      redraw()
    }
  },
  { deep: true, immediate: true }
)

onMounted(() => {
  initMap()
})

onBeforeUnmount(() => {
  clearDrawn()
})
</script>
<style scoped>
.grid-preview {
  height: 100%;
}

div:deep(#mapContainer) {
  height: 100%;
  width: 100%;
}
</style>
