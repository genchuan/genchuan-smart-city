<template>
  <div ref="mapContainer" class="w-full h-full rounded" ></div>
</template>

<script setup lang="ts">
import {  onBeforeUnmount, ref, watch, nextTick } from 'vue'
import { loadTMap } from '@/utils/useTMap'

const props = defineProps<{
  gridList: any[] // 含 boundaryCoords 的列表
  zoom?: number
}>()

let map: any = null
const mapContainer = ref<HTMLDivElement | null>(null)

// 监听数据变化，自动重绘
watch(
  () => props.gridList,
  (newVal) => {
    if (newVal && newVal.length) {
      initMap(newVal)
    }
  },
  { deep: true, immediate: true }
)

const initMap = async (gridList: any[]) => {
  try {
    const TMap = await loadTMap()
    await nextTick()

    const allCoords: any[] = []

    gridList.forEach((grid: any) => {
      if (Array.isArray(grid.boundaryCoords)) {
        grid.boundaryCoords.forEach((item: any) => {
          if (item.boundaryCoords) {
            const coords = item.boundaryCoords
              .split(';')
              .map((p: string) => {
                const [lng, lat] = p.split(',')
                return new TMap.LatLng(parseFloat(lat), parseFloat(lng))
              })
              .filter((p: any) => !isNaN(p.lat) && !isNaN(p.lng))
            if (coords.length) allCoords.push(coords)
          }
        })
      }
    })

    if (!allCoords.length) {
      console.warn('MngGridMap: 无可绘制边界坐标')
      return
    }

    // 清理旧地图
    if (map) {
      map.destroy()
      map = null
    }

    // 初始化地图
    map = new TMap.Map(mapContainer.value, {
      center: allCoords[0][0],
      zoom: props.zoom || 16
    })

    const geometries: any[] = allCoords.map((coords, index) => ({
      id: `grid-${index}`,
      styleId: 'style_blue',
      paths: coords
    }))

    new TMap.MultiPolygon({
      id: 'mng-grid-polygons',
      map,
      styles: {
        style_blue: new TMap.PolygonStyle({
          color: 'rgba(0,150,255,0.4)',
          showBorder: true,
          borderColor: '#0066cc',
          borderWidth: 2
        })
      },
      geometries
    })
  } catch (err) {
    console.error('MngGridMap 初始化失败:', err)
  }
}

// 暴露方法供父组件调用（如 resize）
const resize = () => {
  if (map) map.resize()
}
defineExpose({ resize })

onBeforeUnmount(() => {
  if (map) {
    map.destroy()
    map = null
  }
})
</script>

<style scoped>
div {
  width: 100%;
  height: 70vh;
  border-radius: 8px;
  overflow: hidden;
}
</style>
