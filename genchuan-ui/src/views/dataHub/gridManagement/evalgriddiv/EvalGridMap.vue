<template>
  <div class="eval-grid-map">
    <div ref="mapContainer" class="map-container"></div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, watch, nextTick } from 'vue'
import { ElMessage } from 'element-plus'
import { loadTMap } from '@/utils/useTMap'
import { EvalGridDivApi } from '@/api/dataHub/gridManagement/divideConfig/evalgriddiv'

/** Props：父组件传入选中的 includedMgIds */
const props = defineProps({
  includedMgIds: {
    type: [String, Array],
    default: () => []
  }
})

/** 地图容器 & 实例 */
const mapContainer = ref<HTMLElement | null>(null)
let map: any = null
let multiPolygon: any = null
let TMap: any = null

/** ✅ 初始化基础地图 */
const initMapBase = async () => {
  try {
    TMap = await loadTMap()
    await nextTick()
    if (!mapContainer.value) return

    // 防止重复创建
    if (map) return

    map = new TMap.Map(mapContainer.value, {
      center: new TMap.LatLng(39.916527, 116.397128), // 默认北京
      zoom: 12
    })
  } catch (e) {
    console.error('地图初始化失败:', e)
    ElMessage.warning('地图加载失败')
  }
}

/** ✅ 绘制评价网格多边形 */
const drawPolygons = async (list: any[]) => {
  if (!map || !TMap) {
    await initMapBase()
  }

  // 清除旧图层
  if (multiPolygon) {
    multiPolygon.setMap(null)
    multiPolygon = null
  }

  const allCoords: any[] = []
  list.forEach((grid: any) => {
    if (Array.isArray(grid.boundaryCoords)) {
      grid.boundaryCoords.forEach((item: any) => {
        if (!item.boundaryCoords) return

        // 拆分坐标字符串为 LatLng 数组
        const coords = item.boundaryCoords
          .split(';')
          .map((p: string) => {
            const [lng, lat] = p.split(',')
            const latNum = parseFloat(lat)
            const lngNum = parseFloat(lng)
            if (isNaN(latNum) || isNaN(lngNum)) return null
            return new TMap.LatLng(latNum, lngNum)
          })
          .filter(Boolean)

        if (coords.length) {
          allCoords.push({
            name: grid.evalGridName || '未命名网格',
            coords
          })
        }
      })
    }
  })

  if (!allCoords.length) {
    console.warn('暂无可绘制的网格')
    return
  }

  // ✅ 修复点：MultiPolygon 需要二维数组 [ [LatLng[]] ]
  const geometries: any[] = allCoords.map((item, index) => ({
    id: `eval-grid-${index}`,
    styleId: 'style_eval',
    paths: [item.coords] // ✅ 外层必须包一层 []
  }))

  // 绘制多边形
  multiPolygon = new TMap.MultiPolygon({
    id: 'eval-grid-polygons',
    map,
    styles: {
      style_eval: new TMap.PolygonStyle({
        color: 'rgba(0,150,255,0.4)',
        showBorder: true,
        borderColor: '#2B85E4',
        borderWidth: 2
      }),
      style_hover: new TMap.PolygonStyle({
        color: 'rgba(255,204,51,0.5)',
        showBorder: true,
        borderColor: '#FF9900',
        borderWidth: 2
      })
    },
    geometries
  })

  // 信息窗
  const infoWindow = new TMap.InfoWindow({
    map,
    position: allCoords[0].coords[0],
    content: '',
    offset: { x: 0, y: -10 }
  })
  infoWindow.close()

  // 鼠标交互
  multiPolygon.on('mouseover', (evt: any) => {
    const { geometry } = evt
    const index = parseInt(geometry.id.replace('eval-grid-', ''))
    const gridName = allCoords[index].name
    infoWindow.setPosition(evt.latLng)
    infoWindow.setContent(`<div style="padding:4px 8px;">${gridName}</div>`)
    infoWindow.open()
    multiPolygon.updateGeometries([{ ...geometry, styleId: 'style_hover' }])
  })

  multiPolygon.on('mouseout', (evt: any) => {
    infoWindow.close()
    const { geometry } = evt
    multiPolygon.updateGeometries([{ ...geometry, styleId: 'style_eval' }])
  })

  // 自动适配视野范围
  const bounds = new TMap.LatLngBounds()
  allCoords.forEach(item => item.coords.forEach(p => bounds.extend(p)))
  if (!bounds.isEmpty()) {
    map.fitBounds(bounds)
  }
}

/** ✅ 拉取接口数据并绘制 */
const loadEvalGrids = async () => {
  let ids: string[] = []

  if (Array.isArray(props.includedMgIds)) {
    ids = props.includedMgIds.map(String)
  } else if (typeof props.includedMgIds === 'string' && props.includedMgIds.trim()) {
    ids = props.includedMgIds.split(',').map(s => s.trim())
  }

  if (!ids.length) {
    console.log('无 includedMgIds，展示空地图')
    await initMapBase()
    return
  }

  try {
    const res = await EvalGridDivApi.getListWithBoundary({
      pageNo: 1,
      pageSize: 100,
      includedMgIds: ids.join(',')
    })

    // const list = res?.data?.list || res?.list || []
    const list = res.list
    if (!list.length) {
      ElMessage.info('无可展示的评价网格数据')
      return
    }

    await drawPolygons(list)
  } catch (err) {
    console.error('加载评价网格失败:', err)
    ElMessage.warning('加载评价网格失败')
  }
}

/** 暴露方法供父组件调用 */
defineExpose({ loadEvalGrids })

/** 监听属性变化自动刷新地图 */
watch(
  () => props.includedMgIds,
  async () => {
    await loadEvalGrids()
  },
  { deep: true, immediate: true }
)

onMounted(() => {
  loadEvalGrids()
})
</script>

<style scoped>
.map-container {
  width: 100%;
  height: 500px;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 0 6px rgba(0, 0, 0, 0.15);
}
</style>
