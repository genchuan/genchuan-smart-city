<template>
  <div class="h-full w-full relative">
    <div ref="mapContainer" class="h-full w-full" ></div>
    <!-- 透明度控件和刷新按钮位置调整 -->
    <div class="absolute left-4 top-4 p-2 bg-white rounded shadow" style="width:220px">
      <div class="mb-2 text-sm font-medium">图层透明度</div>
      <el-slider v-model="opacity" :min="0" :max="1" :step="0.1" show-input />
      <div class="mt-3 text-xs text-gray-600">渲染顺序：下 → 上</div>
      <el-button  class="mt-3" @click="redraw">刷新渲染</el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, watch, onMounted, onBeforeUnmount } from 'vue'
import { loadTMap } from '@/utils/useTMap'

interface Layer {
  id?: number
  layerId?: string
  layerName?: string
  gridType?: string
  scale?: string
  boundaryStyleId?: string
  annotateStyleId?: string
  displayStatus?: string
  layerWo?: number
  extCommon1?: string
}

const props = defineProps<{ layers: Layer[] }>()
const mapContainer = ref<HTMLDivElement | null>(null)
const mapInstance = ref<any>(null)
const drawnLayers: any[] = []

const opacity = ref(0.6)

const safeNumber = (v: any, fallback = 0) => (typeof v === 'number' ? v : parseInt(v) || fallback)

/** 初始化地图 */
const initMap = async () => {
  const TMap = await loadTMap()
  if (!mapContainer.value) return
  try {
    mapInstance.value = new TMap.Map(mapContainer.value, {
      center: new TMap.LatLng(39.984104, 116.307503),
      zoom: 10,
    })
  } catch (err) {
    console.error('地图初始化失败（TMap）', err)
  }
  redraw()
}

/** 清理已有绘制 */
const clearDrawn = () => {
  try {
    for (const l of drawnLayers) {
      if (l && l.setMap) l.setMap(null)
    }
    drawnLayers.length = 0
  } catch (e) {
    console.warn('清理绘制出错', e)
  }
}

/** 绘制图层 */
const drawLayer = (layer: Layer, index: number) => {
  if (!mapInstance.value) return
  if (layer.displayStatus && layer.displayStatus !== '1') return

  const bounds = new (window as any).TMap.LatLngBounds(
    new (window as any).TMap.LatLng(39.9 + index * 0.02, 116.2 + index * 0.02),
    new (window as any).TMap.LatLng(40.0 + index * 0.02, 116.3 + index * 0.02)
  )
  const color = pickColorByIndex(index)

  try {
    const rectangle = new (window as any).TMap.Rectangle({
      bounds,
      strokeColor: color,
      strokeWeight: 2,
      fillColor: color,
      fillOpacity: opacity.value,
      map: mapInstance.value,
    })
    drawnLayers.push(rectangle)

    const center = bounds.getCenter()
    const marker = new (window as any).TMap.Marker({
      position: center,
      title: layer.layerName || `layer-${layer.layerId || layer.id}`,
      map: mapInstance.value,
    })
    drawnLayers.push(marker)
  } catch (err) {
    console.warn('绘制图层出错，尝试降级绘制', err)
    try {
      const polygon = new (window as any).TMap.Polygon({
        path: bounds.getPath(),
        strokeColor: color,
        strokeWeight: 2,
        fillColor: color,
        fillOpacity: opacity.value,
        map: mapInstance.value,
      })
      drawnLayers.push(polygon)
    } catch (polygonErr) {
      console.warn('Polygon 绘制失败', polygonErr)
    }
  }
}

const pickColorByIndex = (i: number) => {
  const palette = ['#e53e3e', '#3182ce', '#38a169', '#dd6b20', '#805ad5', '#d53f8c']
  return palette[i % palette.length]
}

/** 重新绘制（按顺序渲染） */
const redraw = () => {
  clearDrawn()
  const layers = (props.layers ?? []).slice().sort((a, b) => (safeNumber(a.layerWo) - safeNumber(b.layerWo)))
  for (let i = 0; i < layers.length; i++) {
    drawLayer(layers[i], i)
  }
}

/** 监听透明度变化实时更新 fillOpacity */
watch(opacity, (v) => {
  for (const d of drawnLayers) {
    try {
      if (d && d.setOptions) {
        d.setOptions({ fillOpacity: v })
      } else if (d && d.setOpacity) {
        d.setOpacity(v)
      }
    } catch {}
  }
})

onMounted(() => {
  initMap()
})

onBeforeUnmount(() => {
  clearDrawn()
})
</script>

<style scoped>
.grid-preview { height: 100%; }
</style>
