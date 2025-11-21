<template>
  <el-drawer
    v-model="visible"
    title="单元网格边界"
    size="75%"
    :destroy-on-close="true"
  >
    <!-- 基本信息展示 -->
    <div class="grid-info p-4 mb-4 bg-gray-50 rounded shadow-sm">
      <el-row :gutter="20">
        <el-col :span="8">
          <div class="info-item">
            <span class="label">网格名称：</span>{{ currentGrid?.unitGridName || '-' }}
          </div>
        </el-col>
        <el-col :span="8">
          <div class="info-item">
            <span class="label">社区ID：</span>{{ currentGrid?.commId || '-' }}
          </div>
        </el-col>
        <el-col :span="8">
          <div class="info-item">
            <span class="label">面积(m²)：</span>{{ currentGrid?.area || '-' }}
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="mt-2">
        <el-col :span="8">
          <div class="info-item">
            <span class="label">比例尺：</span>{{ currentGrid?.scale || '-' }}
          </div>
        </el-col>
        <el-col :span="8">
          <div class="info-item">
            <span class="label">备注：</span>{{ currentGrid?.remark || '-' }}
          </div>
        </el-col>
        <el-col :span="8">
          <div class="info-item">
            <span class="label">划分时间：</span>{{ formatDate(currentGrid?.divTime) }}
          </div>
        </el-col>
      </el-row>
    </div>

    <!-- 地图容器 -->
    <div id="tmap-container" class="w-full h-[70vh] rounded"></div>

    <!-- 底部操作按钮 -->
    <div class="mt-3 text-right">
      <el-button @click="visible = false">关闭</el-button>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { loadTMap } from '@/utils/useTMap'

// Drawer 可见状态
const visible = ref(false)
// 当前选中网格
const currentGrid = ref<any>(null)

// 打开 Drawer
const open = (row: any) => {
  currentGrid.value = row
  visible.value = true
  setTimeout(initMap, 300)
}

// 初始化地图
const initMap = async () => {
  try {
    const TMap = await loadTMap()

    if (!currentGrid.value?.boundaryCoords) {
      console.warn('当前网格未配置边界坐标')
      return
    }

    const coords = currentGrid.value.boundaryCoords
      .split(';')
      .map((p: string) => {
        const [lng, lat] = p.split(',')
        return new TMap.LatLng(parseFloat(lat), parseFloat(lng))
      })

    if (!coords.length) return

    const map = new TMap.Map('tmap-container', {
      center: coords[0],
      zoom: 17,
    })

    new TMap.MultiPolygon({
      id: 'grid-polygon',
      map,
      styles: {
        style_blue: new TMap.PolygonStyle({
          color: 'rgba(0,150,255,0.4)',
          showBorder: true,
          borderColor: '#0066cc',
          borderWidth: 2,
        }),
      },
      geometries: [{ styleId: 'style_blue', paths: coords }],
    })
  } catch (e) {
    console.error('地图加载失败:', e)
  }
}

// 日期格式化
const formatDate = (val: string | Date | undefined) => {
  if (!val) return '-'
  const d = new Date(val)
  return `${d.getFullYear()}-${(d.getMonth() + 1)
    .toString()
    .padStart(2, '0')}-${d.getDate().toString().padStart(2, '0')} ${d
    .getHours()
    .toString()
    .padStart(2, '0')}:${d.getMinutes().toString().padStart(2, '0')}`
}

defineExpose({ open })
</script>

<style scoped>
#tmap-container {
  width: 100%;
  height: 70vh;
}
.grid-info .info-item {
  font-size: 14px;
  line-height: 1.6;
}
.grid-info .label {
  font-weight: 600;
  color: #333;
  margin-right: 4px;
}
.mt-2 {
  margin-top: 8px;
}
.mt-3 {
  margin-top: 12px;
}
</style>
