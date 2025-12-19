<template>
  <div ref="mapContainer" class="w-full h-full rounded overflow-hidden border"></div>
</template>

<script setup lang="ts">
import { onMounted, watch, ref, nextTick, onBeforeUnmount } from 'vue'
import { loadTMap } from '@/utils/useTMap'

const props = defineProps<{
  styleData: {
    scale: string
    fontHeight: number
    fontType: string
    color: string
    colorCMYK: string
  }
}>()

let map: any = null
let polygonLayer: any = null
let textLayer: any = null
const mapContainer = ref<HTMLDivElement | null>(null)

// mm转px（1mm≈3.78px）
const mm2px = (mm: number) => mm * 3.78

// 比例尺对应地图zoom
const scale2Zoom = {
  '1:500': 18,
  '1:1000': 17,
  '1:2000': 16
}

onMounted(async () => {
  const TMap = await loadTMap()
  await nextTick()

  // 初始化地图
  const zoom = props.styleData.scale ? scale2Zoom[props.styleData.scale] : 17
  map = new TMap.Map(mapContainer.value, {
    center: new TMap.LatLng(39.984104, 116.307503),
    zoom: zoom
  })

  // 绘制演示多边形
  polygonLayer = new TMap.MultiPolygon({
    id: 'style-preview',
    map,
    styles: {
      preview: new TMap.PolygonStyle({
        color: 'rgba(255,255,255,0.1)', // 透明填充
        borderColor: '#0066cc',
        borderWidth: 2
      })
    },
    geometries: [
      {
        id: 'demo',
        styleId: 'preview',
        paths: [
          new TMap.LatLng(39.984104, 116.307503),
          new TMap.LatLng(39.984104, 116.317503),
          new TMap.LatLng(39.974104, 116.317503),
          new TMap.LatLng(39.974104, 116.307503)
        ]
      }
    ]
  })

  // 添加注记文字
  initTextLayer(TMap)
})

// 初始化文字注记图层
const initTextLayer = (TMap: any) => {
  const fontHeightPx = props.styleData.fontHeight ? mm2px(props.styleData.fontHeight) : 18.9 // 默认5mm转px
  const center = new TMap.LatLng(39.979104, 116.312503) // 多边形中心

  textLayer = new TMap.MultiLabel({
    id: 'text-preview',
    map,
    styles: {
      textStyle: new TMap.LabelStyle({
        fontSize: fontHeightPx,
        fontFamily: 'SimHei, Microsoft JhengHei, sans-serif', // 近似正等线体
        color: props.styleData.color || '#ff00ff', // 品红色
        offset: new TMap.Point(0, 0)
      })
    },
    geometries: [
      {
        id: 'text-demo',
        styleId: 'textStyle',
        position: center,
        content: `演示注记\n${props.styleData.colorCMYK}\n字高：${props.styleData.fontHeight || 5}mm`
      }
    ]
  })
}

// 监听样式数据变化
watch(
  () => props.styleData,
  (val) => {
    if (!map || !textLayer) return

    // 更新地图zoom
    const zoom = val.scale ? scale2Zoom[val.scale] : 17
    map.setZoom(zoom)

    // 更新文字样式
    const fontHeightPx = val.fontHeight ? mm2px(val.fontHeight) : 18.9
    textLayer.setStyles({
      textStyle: new (loadTMap().TextStyle)({
        fontSize: fontHeightPx,
        fontFamily: 'SimHei, Microsoft JhengHei, sans-serif',
        color: val.color || '#ff00ff',
        offset: new (loadTMap().Point)(0, 0)
      })
    })

    // 更新文字内容
    textLayer.setGeometries([
      {
        id: 'text-demo',
        styleId: 'textStyle',
        position: new (loadTMap().LatLng)(39.979104, 116.312503),
        content: `演示注记\n${val.colorCMYK}\n字高：${val.fontHeight || 5}mm`
      }
    ])
  },
  { deep: true }
)

onBeforeUnmount(() => {
  if (map) map.destroy()
})
</script>

<style scoped>
div {
  width: 100%;
  height: 70vh;
}
</style>
