<template>
  <div ref="mapContainer" class="w-full h-full rounded overflow-hidden border"></div>
</template>

<script setup lang="ts">
import { onMounted, watch, ref, nextTick, onBeforeUnmount } from 'vue'
import { loadTMap } from '@/utils/useTMap'

const props = defineProps<{ styleData: any }>()

let map: any = null
let polygonLayer: any = null
const mapContainer = ref<HTMLDivElement | null>(null)

onMounted(async () => {
  const TMap = await loadTMap()
  await nextTick()
  map = new TMap.Map(mapContainer.value, {
    center: new TMap.LatLng(39.984104, 116.307503),
    zoom: 17
  })

  polygonLayer = new TMap.MultiPolygon({
    id: 'style-preview',
    map,
    styles: {
      preview: new TMap.PolygonStyle({
        color: props.styleData.color,
        borderColor: props.styleData.color,
        borderWidth: props.styleData.lineWidth
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
})

watch(
  () => props.styleData,
  (val) => {
    if (!polygonLayer) return
    polygonLayer.setStyles({
      preview: new TMap.PolygonStyle({
        color: val.color,
        borderColor: val.color,
        borderWidth: val.lineWidth
      })
    })
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
