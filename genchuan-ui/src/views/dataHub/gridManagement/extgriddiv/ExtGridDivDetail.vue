<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    title="扩展网格详情"
    :destroy-on-close="true"
    :with-header="true"
  >
    <!-- 自定义 header -->
    <template #header>
      <div class="flex items-center justify-between w-full">
        <span class="text-lg font-bold">扩展网格详情</span>
        <div class="flex items-center space-x-2">
          <el-button link type="primary" @click="toggleFullscreen">
            {{ isFullscreen ? '退出全屏' : '全屏' }}
          </el-button>
        </div>
      </div>
    </template>

    <!-- 详情信息 -->
    <div class="ext-grid-detail p-4 bg-gray-50 rounded shadow-sm mb-4">
      <el-descriptions :column="2" border>
        <el-descriptions-item label="扩展网格ID">{{ detail?.extGridId }}</el-descriptions-item>
        <el-descriptions-item label="扩展网格名称">{{ detail?.extGridName }}</el-descriptions-item>
        <el-descriptions-item label="扩展网格类型">{{ detail?.extType }}</el-descriptions-item>
        <el-descriptions-item label="所含基础网格IDs">{{ detail?.includedBasicIds }}</el-descriptions-item>
        <el-descriptions-item label="基础网格类型">{{ detail?.basicGridType }}</el-descriptions-item>
        <el-descriptions-item label="面积(m²)">{{ detail?.area }}</el-descriptions-item>
        <el-descriptions-item label="申请原因">{{ detail?.applyReason }}</el-descriptions-item>
        <el-descriptions-item label="审核状态">{{ detail?.auditStatus }}</el-descriptions-item>
        <el-descriptions-item label="申请人ID">{{ detail?.applyUserId }}</el-descriptions-item>
        <el-descriptions-item label="申请时间">{{ formatDate(detail?.applyTime) }}</el-descriptions-item>
        <el-descriptions-item label="审核人ID">{{ detail?.auditUserId }}</el-descriptions-item>
        <el-descriptions-item label="审核时间">{{ formatDate(detail?.auditTime) }}</el-descriptions-item>
        <el-descriptions-item label="审核意见">{{ detail?.auditOpinion }}</el-descriptions-item>
      </el-descriptions>
    </div>

    <!-- 地图容器 -->
    <div id="tmap-container" class="w-full h-[70vh] rounded"></div>

    <!-- 底部操作 -->
    <div class="mt-3 text-right">
      <el-button @click="visible = false">关闭</el-button>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref, nextTick } from 'vue'
import { loadTMap } from '@/utils/useTMap'
import type { ExtGridDivVO } from '@/api/dataHub/gridManagement/divideConfig/extgriddiv'

const visible = ref(false)
const isFullscreen = ref(false)
const detail = ref<ExtGridDivVO | null>(null)
let map: any = null

/** 打开抽屉并绘制地图 */
const open = async (row?: ExtGridDivVO) => {
  if (!row) return
  detail.value = row
  visible.value = true

  await nextTick()
  initMap()
}

/** 初始化地图并绘制多边形 */
const initMap = async () => {
  try {
    const TMap = await loadTMap()

    if (!detail.value?.boundaryCoords) {
      console.warn('未获取到 boundaryCoords')
      return
    }

    // 将字符串坐标转换为 TMap.LatLng 数组
    const coords = detail.value.boundaryCoords
      .split(';')
      .map((p: string) => {
        const [lng, lat] = p.split(',')
        return new TMap.LatLng(parseFloat(lat), parseFloat(lng))
      })
      .filter((p: any) => !isNaN(p.lat) && !isNaN(p.lng))

    if (!coords.length) {
      console.warn('boundaryCoords 坐标解析为空')
      return
    }

    // 初始化地图（以第一个点为中心）
    map = new TMap.Map('tmap-container', {
      center: coords[0],
      zoom: 17
    })

    // 创建并绘制多边形
    new TMap.MultiPolygon({
      id: 'ext-grid-polygon',
      map,
      styles: {
        style_blue: new TMap.PolygonStyle({
          color: 'rgba(0,150,255,0.4)',
          showBorder: true,
          borderColor: '#0066cc',
          borderWidth: 2
        })
      },
      geometries: [
        {
          id: 'polygon-1',
          styleId: 'style_blue',
          paths: coords
        }
      ]
    })
  } catch (err) {
    console.error('地图加载失败:', err)
  }
}

/** 切换全屏 */
const toggleFullscreen = () => {
  isFullscreen.value = !isFullscreen.value
  // 延迟刷新地图尺寸
  setTimeout(() => {
    if (map) map.resize()
  }, 300)
}

/** 日期格式化 */
const formatDate = (val?: string | Date) => {
  if (!val) return '-'
  const d = new Date(val)
  return d.toLocaleString()
}

defineExpose({ open })
</script>

<style scoped>
.ext-grid-detail {
  padding: 10px;
}

#tmap-container {
  width: 100%;
  height: 70vh;
  border-radius: 8px;
  overflow: hidden;
  background-color: #f2f2f2;
}

.mt-3 {
  margin-top: 12px;
}
</style>
