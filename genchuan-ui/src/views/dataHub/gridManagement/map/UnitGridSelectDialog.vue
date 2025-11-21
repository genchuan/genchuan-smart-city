<template>
  <el-dialog
    v-model="visible"
    :fullscreen="isFullscreen"
    title="选择单元网格"
    width="90%"
    :destroy-on-close="true"
  >
    <!-- 顶部操作区 -->
    <template #header>
      <div class="flex items-center justify-between">
        <span class="text-lg font-bold">选择单元网格</span>
        <div>
          <el-button @click="toggleFullscreen">
            {{ isFullscreen ? '退出全屏' : '全屏' }}
          </el-button>
          <el-button type="primary" @click="handleConfirmSelect">
            完成选择
          </el-button>
        </div>
      </div>
    </template>

    <!-- 地图容器 -->
    <div id="tmap-container" class="w-full h-[80vh] rounded shadow-inner"></div>

    <!-- 底部提示 -->
    <div class="mt-3 text-gray-500 text-sm text-center">
      点击地图上的多边形以选择 / 取消选择
    </div>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { loadTMap } from '@/utils/useTMap'
import { UnitGridDivApi, UnitGridDivVO } from '@/api/dataHub/gridManagement/divideConfig/unitgriddiv'

/** 弹窗状态 */
const visible = ref(false)
const isFullscreen = ref(false)

/** 数据集合 */
const gridList = ref<UnitGridDivVO[]>([])
const selectedIds = ref<string[]>([])

/** 地图对象 */
let map: any = null
let polygonLayer: any = null
let TMapObj: any = null

/** 打开弹窗 */
const open = async () => {
  visible.value = true
  setTimeout(() => loadAllGrids(), 300)
}

/** 切换全屏 */
const toggleFullscreen = () => {
  isFullscreen.value = !isFullscreen.value
  setTimeout(() => initMap(), 200)
}

/** 一次性加载所有网格数据 */
const loadAllGrids = async () => {
  try {
    const data = await UnitGridDivApi.getUnitGridDivPage({
      pageNo: 1,
      pageSize: 100 // ✅ 加载所有数据
    })
    gridList.value = data.list || []
    await initMap()
  } catch (err) {
    console.error('加载单元网格失败：', err)
  }
}

/** 初始化地图并绘制所有多边形 */
const initMap = async () => {
  if (!visible.value) return

  TMapObj = await loadTMap()

  // 初始化地图
  if (!map) {
    map = new TMapObj.Map('tmap-container', {
      zoom: 14,
      center: new TMapObj.LatLng(39.9131, 116.3927)
    })
  }

  // 清理旧图层
  if (polygonLayer) {
    try {
      polygonLayer.setMap(null)
      polygonLayer = null
    } catch (e) {
      console.warn('旧图层清理失败：', e)
    }
  }

  const geometries: any[] = []
  const bounds = new TMapObj.LatLngBounds()

  // 绘制所有多边形
  gridList.value.forEach((item) => {
    if (!item.boundaryCoords) return
    const paths = item.boundaryCoords.split(';').map((p: string) => {
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
    id: 'gridLayer',
    map,
    styles: {
      default: new TMapObj.PolygonStyle({
        color: 'rgba(0,150,255,0.4)',
        showBorder: true,
        borderColor: '#0066cc',
        borderWidth: 2
      }),
      selected: new TMapObj.PolygonStyle({
        color: 'rgba(255,200,0,0.6)',
        showBorder: true,
        borderColor: '#ffaa00',
        borderWidth: 3
      })
    },
    geometries
  })

  /** 点击多边形切换选中状态 */
  polygonLayer.on('click', (e: any) => {
    const id = e.geometry.id
    if (selectedIds.value.includes(id)) {
      selectedIds.value = selectedIds.value.filter((x) => x !== id)
    } else {
      selectedIds.value.push(id)
    }

    const updated = polygonLayer.getGeometries().map((g: any) => ({
      ...g,
      styleId: selectedIds.value.includes(g.id) ? 'selected' : 'default'
    }))
    polygonLayer.updateGeometries(updated)
  })

  // ✅ 自动居中显示所有网格
  if (!bounds.isEmpty()) {
    map.fitBounds(bounds, { padding: 50 })
  }
}

/** 完成选择 */
const handleConfirmSelect = () => {
  if (selectedIds.value.length === 0) {
    ElMessage.warning('请至少选择一个单元网格')
    return
  }

  ElMessageBox.confirm(
    `已选择 ${selectedIds.value.length} 个单元网格，是否确认？`,
    '提示',
    { type: 'info' }
  )
    .then(() => {
      console.log('✅ 选中的 unitGridId 列表:', selectedIds.value)
      ElMessage.success('选择完成！')
    })
    .catch(() => {
      ElMessage.info('已取消选择')
    })
}

defineExpose({ open })
</script>

<style scoped>
#tmap-container {
  width: 100%;
  height: 80vh;
}
</style>
