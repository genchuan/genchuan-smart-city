<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    title="管理网格详情"
    :destroy-on-close="true"
    :with-header="true"
  >
    <!-- 自定义 header -->
    <template #header>
      <div class="flex items-center justify-between w-full">
        <span class="text-lg font-bold">管理网格详情</span>
        <div class="flex items-center space-x-2">
          <el-button link type="primary" @click="toggleFullscreen">
            {{ isFullscreen ? '退出全屏' : '全屏' }}
          </el-button>
        </div>
      </div>
    </template>

    <!-- 详情信息 -->
    <div class="mng-grid-detail p-4 bg-gray-50 rounded shadow-sm mb-4">
      <el-descriptions :column="2" border>
        <el-descriptions-item label="管理网格名称">{{ detail?.mngGridName }}</el-descriptions-item>
        <el-descriptions-item label="所属乡镇">{{ detail?.townStreetId }}</el-descriptions-item>
        <el-descriptions-item label="面积(m²)">{{ detail?.area }}</el-descriptions-item>
        <el-descriptions-item label="划分时间">{{ formatDate(detail?.divTime) }}</el-descriptions-item>
        <el-descriptions-item label="更新时间">{{ formatDate(detail?.updateTime) }}</el-descriptions-item>
        <el-descriptions-item label="备注">{{ detail?.remark || '-' }}</el-descriptions-item>
      </el-descriptions>

      <div class="mt-4">
        <el-tag v-if="detail?.extCat1">分类扩展1：{{ detail.extCat1 }}</el-tag>
        <el-tag v-if="detail?.extCat2" class="ml-2">分类扩展2：{{ detail.extCat2 }}</el-tag>
      </div>
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
import { MngGridDivApi, MngGridDivVO } from '@/api/dataHub/gridManagement/divideConfig/mnggriddiv'
import { loadTMap } from '@/utils/useTMap'

const visible = ref(false)
const isFullscreen = ref(false)
const detail = ref<MngGridDivVO | null>(null)
let map: any = null

// 打开抽屉并加载详情 + 绘制地图
const open = async (id?: number | null) => {
  if (!id) return
  visible.value = true
  await loadDetailAndMap(id)
}

const loadDetailAndMap = async (id: number) => {
  const res = await MngGridDivApi.getMngGridDiv(id)
  detail.value = res

  // 获取分页数据（包含多个 boundaryCoords）
  const pageRes = await MngGridDivApi.getMngGridDivPage({
    pageNo: 1,
    pageSize: 100,
    mngGridId: res.mngGridId
  })

  const gridList = pageRes.list || []

  await nextTick()
  initMap(gridList)
}

// 初始化地图并绘制多边形
const initMap = async (gridList: any[]) => {
  try {
    const TMap = await loadTMap()
    const allCoords: any[] = []

    // 遍历所有管理网格项
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
      console.warn('无可绘制边界坐标')
      return
    }

    // 初始化地图中心为第一个多边形的第一个点
    map = new TMap.Map('tmap-container', {
      center: allCoords[0][0],
      zoom: 16
    })

    const geometries: any[] = []
    allCoords.forEach((coords, index) => {
      geometries.push({
        id: `grid-${index}`,
        styleId: 'style_blue',
        paths: coords
      })
    })

    // 绘制所有多边形
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
  } catch (e) {
    console.error('地图加载失败:', e)
  }
}

// 切换全屏
const toggleFullscreen = () => {
  isFullscreen.value = !isFullscreen.value
  setTimeout(() => {
    if (map) map.resize()
  }, 300)
}

// 日期格式化
const formatDate = (val?: string | Date) => {
  if (!val) return '-'
  const d = new Date(val)
  return d.toLocaleString()
}

defineExpose({ open })
</script>

<style scoped>
.mng-grid-detail {
  padding: 10px;
}

#tmap-container {
  width: 100%;
  height: 70vh;
  border-radius: 8px;
  overflow: hidden;
}

.mt-3 {
  margin-top: 12px;
}
</style>
