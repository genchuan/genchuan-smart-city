<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : drawerSize"
    :with-header="true"
    :destroy-on-close="true"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">网格边界样式详情</span>
        <div class="flex items-center gap-2">
          <!-- 全屏按钮 -->
          <el-button type="primary" link @click="isFullscreen = !isFullscreen">
            <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
            {{ isFullscreen ? '退出全屏' : '全屏' }}
          </el-button>
        </div>
      </div>
    </template>

    <div class="drawer-body p-6" :style="{ background: '#f5f7fa', height: '100%' }">
      <div class="grid grid-cols-2 gap-4">
        <!-- 基础信息 -->
        <el-card shadow="never">
          <div class="card-title bg-blue-50 p-2 rounded-t font-semibold">基础信息</div>
          <div class="p-3 text-sm space-y-1">
            <div>样式ID：{{ data.styleId }}</div>
            <div>比例尺：{{ data.scale }}</div>
            <div>网格类型：{{ data.gridType }}</div>
            <div>线宽：{{ data.lineWidth }} mm</div>
          </div>
        </el-card>

        <!-- 颜色信息 -->
        <el-card shadow="never">
          <div class="card-title bg-green-50 p-2 rounded-t font-semibold">颜色信息</div>
          <div class="p-3 text-sm space-y-2">
            <div>CMYK：C{{ data.colorC }}% M{{ data.colorM }}% Y{{ data.colorY }}% K{{ data.colorK }}%</div>
            <div class="flex items-center gap-2">
              <div :style="colorBoxStyle" class="rounded border border-gray-300"></div>
              <span>RGB: {{ colorBoxStyle.background }}</span>
            </div>
          </div>
        </el-card>

        <!-- 地图预览 -->
        <el-card shadow="never" class="col-span-2">
          <div class="card-title bg-yellow-50 p-2 rounded-t font-semibold">地图样式预览</div>
          <div class="p-2 h-[400px]">
            <div ref="mapContainer" class="w-full h-full rounded"></div>
          </div>
        </el-card>

        <!-- 系统信息 -->
        <el-card shadow="never" class="col-span-2">
          <div class="card-title bg-gray-50 p-2 rounded-t font-semibold">系统信息</div>
          <div class="p-3 text-sm space-y-1">
            <div>创建人：{{ data.createUserId || '—' }}</div>
            <div>创建时间：{{ formatDate(data.createTime) }}</div>
            <el-button type="warning" class="mt-2" @click="restartGis">重启 GIS 服务</el-button>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref, reactive, watch, nextTick, computed, onBeforeUnmount } from 'vue'
import { loadTMap } from '@/utils/useTMap'

const visible = ref(false)
const isFullscreen = ref(false)
const drawerSize = '75%'
const data = reactive<any>({})
let map: any = null
let polygonLayer: any = null

const emit = defineEmits(['restart-gis'])

/** 打开抽屉并渲染地图 */
const open = async (row: any) => {
  Object.assign(data, row)
  visible.value = true
  await nextTick()
  initMap()
}
defineExpose({ open })

/** 全屏切换 */
// ✅ 全屏切换自动调整地图
watch(isFullscreen, async () => {
  await nextTick()
  if (map) map.resize()
})

/** 时间格式化 */
const formatDate = (v: any) => {
  if (!v) return ''
  try {
    return new Date(v).toLocaleString()
  } catch {
    return ''
  }
}

/** 计算 CMYK 转 RGB */
const colorBoxStyle = computed(() => {
  const c = (data.colorC || 0) / 100
  const m = (data.colorM || 0) / 100
  const y = (data.colorY || 0) / 100
  const k = (data.colorK || 0) / 100
  const r = Math.round(255 * (1 - c) * (1 - k))
  const g = Math.round(255 * (1 - m) * (1 - k))
  const b = Math.round(255 * (1 - y) * (1 - k))
  return { background: `rgb(${r},${g},${b})`, width: '60px', height: '25px' }
})

/** 地图初始化与样式应用 */
const mapContainer = ref<HTMLDivElement | null>(null)
const initMap = async () => {
  try {
    const TMap = await loadTMap()
    await nextTick()

    // 若已有地图则销毁重建
    if (map) {
      map.destroy()
      map = null
    }

    // 示例边界（若无真实数据）
    const coords = data.boundaryCoords
      ? data.boundaryCoords.split(';').map((p: string) => {
        const [lng, lat] = p.split(',')
        return new TMap.LatLng(parseFloat(lat), parseFloat(lng))
      })
      : [
        new TMap.LatLng(39.909, 116.397),
        new TMap.LatLng(39.909, 116.407),
        new TMap.LatLng(39.919, 116.407),
        new TMap.LatLng(39.919, 116.397)
      ]

    map = new TMap.Map(mapContainer.value, {
      center: coords[0],
      zoom: 17
    })

    applyPolygonStyle(TMap, coords)
  } catch (err) {
    console.error('地图初始化失败:', err)
  }
}

/** 动态应用样式到地图 */
const applyPolygonStyle = (TMap: any, coords: any[]) => {
  if (!map) return

  const rgb = colorBoxStyle.value.background
  const borderWidth = Number(data.lineWidth) || 2

  const styles = {
    dynamic: new TMap.PolygonStyle({
      color: 'rgba(0,0,0,0)',
      showBorder: true,
      borderColor: rgb,
      borderWidth
    })
  }

  // 清除旧多边形层
  if (polygonLayer) {
    polygonLayer.setMap(null)
    polygonLayer = null
  }

  polygonLayer = new TMap.MultiPolygon({
    id: 'grid-style-preview',
    map,
    styles,
    geometries: [
      {
        id: 'poly-demo',
        styleId: 'dynamic',
        paths: coords
      }
    ]
  })
}

/** 当线宽或颜色变化时实时更新地图样式 */
watch(
  () => [data.lineWidth, data.colorC, data.colorM, data.colorY, data.colorK],
  async () => {
    if (!map) return
    const TMap = await loadTMap()
    const currentPolygons = polygonLayer?.getGeometries?.()
    if (currentPolygons && currentPolygons.length) {
      const coords = currentPolygons[0].paths
      applyPolygonStyle(TMap, coords)
    }
  },
  { deep: true }
)

/** 重启 GIS */
const restartGis = () => {
  emit('restart-gis', data)
  ElMessage.success('已发送 GIS 重启请求')
}

onBeforeUnmount(() => {
  if (map) {
    map.destroy()
    map = null
  }
})
</script>

<style scoped>
.drawer-body {
  overflow-y: auto;
}
.card-title {
  font-weight: bold;
  color: #333;
}
</style>
