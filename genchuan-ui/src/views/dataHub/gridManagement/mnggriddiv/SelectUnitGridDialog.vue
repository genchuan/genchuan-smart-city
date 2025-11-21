<template>
  <el-dialog
    v-model="visible"
    :fullscreen="isFullscreen"
    title="选择单元网格"
    width="90%"
  >
    <template #header>
      <div class="flex justify-between items-center">
        <span>选择单元网格</span>
        <div>
          <el-button @click="toggleFullscreen">
            {{ isFullscreen ? '退出全屏' : '全屏' }}
          </el-button>
          <el-button type="primary" @click="confirmSelect">完成选择</el-button>
        </div>
      </div>
    </template>

    <div id="tmap-container" class="w-full h-[80vh]"></div>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, nextTick } from 'vue'
import { ElMessage } from 'element-plus'
import { loadTMap } from '@/utils/useTMap'
import { UnitGridDivApi } from '@/api/dataHub/gridManagement/divideConfig/unitgriddiv'

const visible = ref(false)
const isFullscreen = ref(false)
const selectedIds = ref<string[]>([])
let map: any = null
let polygonLayer: any = null
let TMapObj: any = null

let currentCommId: string | null = null

/** 打开并加载指定社区(commId)的单元网格 */
const open = async (commId: string) => {
  if (!commId) {
    ElMessage.warning('未传入社区ID')
    return
  }
  currentCommId = commId
  visible.value = true
  await nextTick()
  await loadGridsByComm(commId)
}

/** 全屏切换 */
const toggleFullscreen = async () => {
  isFullscreen.value = !isFullscreen.value
  await nextTick()
  if (map) map.resize()
}

/** 按社区加载单元网格 */
const loadGridsByComm = async (commId: string) => {
  try {
    const res = await UnitGridDivApi.listByComm(commId)
    const list = res || []
    if (!list.length) {
      ElMessage.info('该社区暂无单元网格')
    }
    await initMap(list)
  } catch (e) {
    console.error('加载单元网格失败:', e)
    ElMessage.error('加载单元网格失败')
  }
}

/** 初始化地图 */
const initMap = async (list: any[]) => {
  TMapObj = await loadTMap()

  if (!map) {
    map = new TMapObj.Map('tmap-container', {
      zoom: 14,
      center: new TMapObj.LatLng(39.9, 116.4)
    })
  }

  if (polygonLayer) polygonLayer.setMap(null)

  const geometries: any[] = []
  const bounds = new TMapObj.LatLngBounds()

  list.forEach(item => {
    if (!item.boundaryCoords) return
    const paths = item.boundaryCoords.split(';').map(p => {
      const [lng, lat] = p.split(',')
      const point = new TMapObj.LatLng(parseFloat(lat), parseFloat(lng))
      bounds.extend(point)
      return point
    })
    geometries.push({
      id: item.unitGridId,
      styleId: selectedIds.value.includes(item.unitGridId)
        ? 'selected'
        : 'default',
      properties: item,
      paths
    })
  })

  polygonLayer = new TMapObj.MultiPolygon({
    map,
    styles: {
      default: new TMapObj.PolygonStyle({
        color: 'rgba(0,150,255,0.3)',
        borderColor: '#0066cc'
      }),
      selected: new TMapObj.PolygonStyle({
        color: 'rgba(255,200,0,0.6)',
        borderColor: '#ffaa00'
      })
    },
    geometries
  })

  polygonLayer.on('click', (e: any) => {
    const id = e.geometry.id
    if (selectedIds.value.includes(id))
      selectedIds.value = selectedIds.value.filter(x => x !== id)
    else selectedIds.value.push(id)

    polygonLayer.updateGeometries(
      polygonLayer.getGeometries().map((g: any) => ({
        ...g,
        styleId: selectedIds.value.includes(g.id) ? 'selected' : 'default'
      }))
    )
  })

  if (!bounds.isEmpty()) map.fitBounds(bounds, { padding: 50 })
}

/** 确认选择 */
const emit = defineEmits(['confirm'])
const confirmSelect = () => {
  if (!selectedIds.value.length) {
    ElMessage.warning('请至少选择一个单元网格')
    return
  }
  emit('confirm', selectedIds.value)
  visible.value = false
}

defineExpose({ open })
</script>
