<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">评价网格统计详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="grid-container">
      <!-- 基本信息 -->
      <el-card class="info-card" hoverable>
        <div class="card-header bg-blue-50">
          <span class="text-blue-600 font-medium">基本信息</span>
        </div>
        <div class="card-body">
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value font-semibold text-blue-600">{{ data.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计ID：</span>
            <span class="value">{{ data.evalStatId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计周期：</span>
            <span class="value font-semibold">{{ data.statCycle || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计周期名称：</span>
            <span class="value">{{ data.statCycleName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计时间：</span>
            <span class="value">{{ formatDate(data.statTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计人：</span>
            <span class="value">{{ data.statUserId || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 区域信息 -->
      <el-card class="info-card" hoverable>
        <div class="card-header bg-green-50">
          <span class="text-green-600 font-medium">区域信息</span>
        </div>
        <div class="card-body">
          <div class="info-item">
            <span class="label">行政区划代码：</span>
            <span class="value">{{ data.regionCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">行政区划名称：</span>
            <span class="value font-semibold">{{ data.regionName || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 网格类型信息 -->
      <el-card class="info-card" hoverable>
        <div class="card-header bg-purple-50">
          <span class="text-purple-600 font-medium">网格类型信息</span>
        </div>
        <div class="card-body">
          <div class="info-item">
            <span class="label">评价网格类型：</span>
            <span class="value" :class="getTypeClass">{{ data.gridType || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">类型占比：</span>
            <span class="value font-semibold">{{ data.typeRatio || '-' }}%</span>
          </div>
          <div class="info-item">
            <span class="label">关联评价指标ID：</span>
            <span class="value">{{ data.idxId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联评价指标名称：</span>
            <span class="value">{{ data.idxName || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 网格数量信息 -->
      <el-card class="info-card" hoverable>
        <div class="card-header bg-orange-50">
          <span class="text-orange-600 font-medium">网格数量信息</span>
        </div>
        <div class="card-body">
          <div class="info-item">
            <span class="label">网格总数：</span>
            <span class="value font-semibold">{{ data.totalCount || '-' }} 个</span>
          </div>
          <div class="info-item">
            <span class="label">新增网格数：</span>
            <span class="value">{{ data.newCount || '-' }} 个</span>
          </div>
          <div class="info-item">
            <span class="label">总所含管理网格数：</span>
            <span class="value">{{ data.totalMgCount || '-' }} 个</span>
          </div>
          <div class="info-item">
            <span class="label">平均所含管理数：</span>
            <span class="value">{{ data.avgMgCount || '-' }} 个</span>
          </div>
        </div>
      </el-card>

      <!-- 面积信息 -->
      <el-card class="info-card" hoverable>
        <div class="card-header bg-teal-50">
          <span class="text-teal-600 font-medium">面积信息</span>
        </div>
        <div class="card-body">
          <div class="info-item">
            <span class="label">总面积：</span>
            <span class="value font-semibold text-blue-600">{{ data.totalArea || '-' }} m²</span>
          </div>
        </div>
      </el-card>

      <!-- 其他信息 -->
      <el-card class="info-card" hoverable>
        <div class="card-header bg-gray-50">
          <span class="text-gray-600 font-medium">其他信息</span>
        </div>
        <div class="card-body">
          <div class="info-item">
            <span class="label">报表备注：</span>
            <span class="value">{{ data.rptRemark || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分类扩展字段1：</span>
            <span class="value">{{ data.extCat1 || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分类扩展字段2：</span>
            <span class="value">{{ data.extCat2 || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">通用扩展字段1：</span>
            <span class="value">{{ data.extCommon1 || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">通用扩展字段2：</span>
            <span class="value">{{ data.extCommon2 || '-' }}</span>
          </div>
        </div>
      </el-card>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { EvalGridRptVO } from '@/api/dataHub/gridManagement/gridStatReport/evalgridrpt'

const visible = ref(false)
const data = ref<EvalGridRptVO>({} as EvalGridRptVO) // 类型断言初始化
const isFullscreen = ref(false)

// 格式化日期 - 明确参数类型
const formatDate = (val: string | number | Date | undefined) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 根据网格类型获取样式类 - 确保安全访问
const getTypeClass = computed(() => {
  const type = data.value?.gridType
  switch (type) {
    case 'A':
      return 'type-a'
    case 'B':
      return 'type-b'
    case 'C':
      return 'type-c'
    default:
      return ''
  }
})

// 打开抽屉 - 明确参数类型
const open = (row: EvalGridRptVO) => {
  data.value = { ...row }
  visible.value = true
  isFullscreen.value = false
}

// 移除未使用的close函数定义

// 暴露open方法
defineExpose({ open })

// 抽屉关闭事件
const emit = defineEmits(['close'])
watch(visible, (newVal) => {
  if (!newVal) {
    emit('close')
  }
})
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
}

.grid-container {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
  padding: 0 20px 20px;
}

.info-card {
  border: none;
  transition: all 0.3s ease;
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 15px;
  margin: -1px -1px 15px;
  border-radius: 4px 4px 0 0;
}

.card-body {
  padding: 0 15px 15px;
}

.info-item {
  display: flex;
  margin-bottom: 12px;
  line-height: 1.8;
}

.label {
  width: 120px;
  color: #666;
  flex-shrink: 0;
}

.value {
  flex-grow: 1;
  color: #333;
  word-break: break-all;
}

/* 类型样式 - 确保被使用 */
.type-a {
  color: #4299e1;
  background-color: #ebf8ff;
  padding: 2px 6px;
  border-radius: 4px;
  font-weight: 500;
}

.type-b {
  color: #48bb78;
  background-color: #f0fff4;
  padding: 2px 6px;
  border-radius: 4px;
  font-weight: 500;
}

.type-c {
  color: #ed8936;
  background-color: #fffaf0;
  padding: 2px 6px;
  border-radius: 4px;
  font-weight: 500;
}

/* 响应式调整 */
@media (max-width: 1200px) {
  .grid-container {
    grid-template-columns: 1fr;
  }
}
</style>
