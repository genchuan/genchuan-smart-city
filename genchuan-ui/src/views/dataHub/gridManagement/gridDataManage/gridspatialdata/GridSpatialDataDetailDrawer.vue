<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    :with-header="true"
    destroy-on-close
    class="grid-detail-drawer"
  >
    <!-- 自定义标题栏 -->
    <template #title>
      <div class="drawer-header flex justify-between items-center w-full px-2">
        <h3 class="text-lg font-bold text-gray-800">网格空间数据详情</h3>
        <el-button type="primary" link @click="toggleFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="p-4 grid grid-cols-2 gap-5">
      <!-- 基础信息 -->
      <el-card shadow="hover" class="info-card">
        <div class="section-header bg-blue-100 text-blue-700">基础信息</div>
        <div class="mt-3 space-y-2">
          <p><b>ID：</b><span class="text-gray-800">{{ data.id }}</span></p>
          <p><b>空间数据ID：</b><span>{{ data.spatialId || '—' }}</span></p>
          <p><b>关联网格ID：</b><span>{{ data.gridId || '—' }}</span></p>
          <p><b>网格类型：</b><span>{{ data.gridType || '—' }}</span></p>
        </div>
      </el-card>

      <!-- 数据格式与坐标系 -->
      <el-card shadow="hover" class="info-card">
        <div class="section-header bg-green-100 text-green-700">数据属性</div>
        <div class="mt-3 space-y-2">
          <p><b>数据格式：</b><span>{{ data.dataFormat || '—' }}</span></p>
          <p><b>坐标系：</b><span>{{ data.coordSystem || '—' }}</span></p>
          <p><b>拓扑关系：</b><span>{{ data.topoRelation || '—' }}</span></p>
          <p><b>备注：</b><span>{{ data.remark || '—' }}</span></p>
        </div>
      </el-card>

      <!-- 扩展信息 -->
      <el-card shadow="hover" class="col-span-2 info-card">
        <div class="section-header bg-purple-100 text-purple-700">扩展信息</div>
        <div class="grid grid-cols-2 gap-3 mt-3">
          <p><b>分类扩展1：</b><span>{{ data.extCat1 || '—' }}</span></p>
          <p><b>分类扩展2：</b><span>{{ data.extCat2 || '—' }}</span></p>
          <p><b>通用扩展1：</b><span>{{ data.extCommon1 || '—' }}</span></p>
          <p><b>通用扩展2：</b><span>{{ data.extCommon2 || '—' }}</span></p>
        </div>
      </el-card>

      <!-- 时间信息 -->
      <el-card shadow="hover" class="col-span-2 info-card">
        <div class="section-header bg-yellow-100 text-yellow-700">时间信息</div>
        <div class="grid grid-cols-2 gap-3 mt-3">
          <p><b>导入时间：</b><span>{{ formatDate(data.importTime) || '—' }}</span></p>
          <p><b>更新时间：</b><span>{{ formatDate(data.updateTime) || '—' }}</span></p>
        </div>
      </el-card>

      <!-- 地图展示 -->
      <el-card shadow="hover" class="col-span-2 info-card">
        <div class="section-header bg-sky-100 text-sky-700">边界预览 / 地图</div>
        <div id="map-container" class="mt-3"></div>
        <p class="text-sm text-gray-500 mt-3">
          说明：边界坐标已渲染为矢量图层，可缩放查看。
        </p>
      </el-card>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref, reactive, nextTick } from 'vue'

import type { GridSpatialDataVO } from '@/api/dataHub/gridManagement/gridDataManage/gridspatialdata'
import { loadTMap } from '@/utils/useTMap'

const visible = ref(false)
const isFullscreen = ref(false)
const data = reactive<GridSpatialDataVO>({
  id: undefined,
  spatialId: undefined,
  gridId: undefined,
  gridType: undefined,
  boundaryCoords: undefined,
  topoRelation: undefined,
  coordSystem: undefined,
  dataFormat: undefined,
  importTime: undefined,
  updateTime: undefined,
  remark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})

let map: any = null
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '—')
// 打开抽屉
const open = async (d: GridSpatialDataVO) => {
  Object.assign(data, d || {})
  visible.value = true
  await nextTick()
  renderMap()
}

// 切换全屏并刷新地图
const toggleFullscreen = () => {
  isFullscreen.value = !isFullscreen.value
  setTimeout(() => {
    if (map) map.resize()
  }, 300)
}

// 渲染地图与多边形
const renderMap = async () => {
  if (!data.boundaryCoords) return
  try {
    const TMap = await loadTMap()
    const coords = data.boundaryCoords
      .replace('POLYGON((', '')
      .replace('))', '')
      .split(',')
      .map(str => {
        const [lng, lat] = str.trim().split(' ').map(Number)
        return new TMap.LatLng(lat, lng)
      })
      .filter(p => !isNaN(p.lat) && !isNaN(p.lng))

    if (!coords.length) return
    map = new TMap.Map('map-container', { center: coords[0], zoom: 17 })

    new TMap.MultiPolygon({
      id: 'polygon',
      map,
      styles: {
        style_blue: new TMap.PolygonStyle({
          color: 'rgba(0,150,255,0.3)',
          showBorder: true,
          borderColor: '#0066cc',
          borderWidth: 2,
        }),
      },
      geometries: [{ id: 'poly1', styleId: 'style_blue', paths: coords }],
    })
  } catch (err) {
    console.error('地图加载失败:', err)
  }
}

defineExpose({ open })
</script>

<style scoped>
.grid-detail-drawer {
  background-color: #f9fafb;
}

.info-card {
  border-radius: 14px;
  transition: all 0.3s;
}

.info-card:hover {
  transform: translateY(-2px);
  box-shadow: var(--el-box-shadow-light);
}

.section-header {
  font-weight: 600;
  padding: 6px 10px;
  border-radius: 6px;
  display: inline-block;
}

#map-container {
  width: 100%;
  height: 320px;
  border: 1px solid #d0e3ff;
  border-radius: 8px;
  overflow: hidden;
  background-color: #eef6ff;
}

.text-gray-500 {
  color: var(--el-text-color-secondary);
}

.text-gray-800 {
  color: var(--el-text-color-primary);
}
</style>
