<template>
  <el-drawer v-model="drawerVisible" :size="isFullscreen ? '100%' : '75%'" class="detail-drawer">
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">设备分类规则详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="drawer-content">
      <!-- 主要信息卡片 -->
      <el-card class="main-card mb-6">
        <div class="card-header bg-blue-50 px-4 py-3 rounded-t-lg">
          <span class="text-blue-600 font-medium">基本信息</span>
        </div>
        <div class="card-body grid grid-cols-2 gap-6 p-6">
          <div class="info-item">
            <span class="info-label">分类规则ID：</span>
            <span class="info-value font-medium text-blue-600">{{ data.catRuleId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">主键ID：</span>
            <span class="info-value">{{ data.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">父类规则ID：</span>
            <span class="info-value">{{ data.parentCatRuleId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">分类层级：</span>
            <span class="info-value">{{ data.catLevel || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">分类代码：</span>
            <span class="info-value font-medium text-blue-600">{{ data.catCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">分类名称：</span>
            <span class="info-value font-medium text-blue-600">{{ data.catName || '-' }}</span>
          </div>
          <div class="info-item col-span-2">
            <span class="info-label">分类说明：</span>
            <span class="info-value block mt-1">{{ data.catDesc || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 状态信息卡片 -->
      <el-card class="status-card mb-6">
        <div class="card-header bg-green-50 px-4 py-3 rounded-t-lg">
          <span class="text-green-600 font-medium">状态信息</span>
        </div>
        <div class="card-body grid grid-cols-2 gap-6 p-6">
          <div class="info-item">
            <span class="info-label">启用状态：</span>
            <span
              class="info-value"
              :class="data.enableStatus === '启用' ? 'text-green-600' : 'text-red-600'"
            >
              {{ data.enableStatus || '-' }}
            </span>
          </div>
          <div class="info-item">
            <span class="info-label">创建时间：</span>
            <span class="info-value">{{ formatDate(data.createTimeSys) }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">更新时间：</span>
            <span class="info-value">{{ formatDate(data.updateTimeSys) }}</span>
          </div>
        </div>
      </el-card>

      <!-- 扩展信息卡片 -->
      <el-card class="ext-card">
        <div class="card-header bg-purple-50 px-4 py-3 rounded-t-lg">
          <span class="text-purple-600 font-medium">扩展信息</span>
        </div>
        <div class="card-body grid grid-cols-2 gap-6 p-6">
          <div class="info-item">
            <span class="info-label">分类扩展字段1：</span>
            <span class="info-value">{{ data.extCat1 || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">分类扩展字段2：</span>
            <span class="info-value">{{ data.extCat2 || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">通用扩展字段1：</span>
            <span class="info-value">{{ data.extCommon1 || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">通用扩展字段2：</span>
            <span class="info-value">{{ data.extCommon2 || '-' }}</span>
          </div>
        </div>
      </el-card>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DeviceCatRuleVO } from '@/api/dataHub/deviceManage/deviceRule/devicecatrule'

// 定义props并使用
const props = defineProps<{
  visible: boolean
}>()

// 定义emit
const emit = defineEmits<{
  (e: 'update:visible', value: boolean): void
}>()

// 使用本地变量控制抽屉显示，避免直接修改props
const drawerVisible = computed({
  get: () => props.visible,
  set: (val) => emit('update:visible', val)
})

const data = ref<Partial<DeviceCatRuleVO>>({})
const isFullscreen = ref(false)

// 设置详情数据
const setData = (row: DeviceCatRuleVO) => {
  data.value = { ...row }
}

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}

defineExpose({ setData })
</script>

<style scoped>
.detail-drawer {
  /* 替换v-deep为::v-deep的替代方案 */

  :deep(.el-drawer__body) {
    padding: 0;
    background-color: #f3f4f6;
    overflow-y: auto;
  }
}

.drawer-content {
  padding: 20px;
}

.drawer-header {
  margin-bottom: 20px;
}

/* 保留并使用el-card相关样式 */
.el-card {
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
}

.el-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  margin: -1px -1px 0;
  border-bottom: 1px solid #e5e7eb;
}

/* 保留并使用背景色相关样式 */
.bg-blue-50 {
  background-color: #eff6ff;
}

.bg-green-50 {
  background-color: #ecfdf5;
}

.bg-purple-50 {
  background-color: #f5f3ff;
}

.card-body {
  margin-top: 10px;
}

.info-item {
  display: flex;
  margin-bottom: 8px;
}

.info-label {
  color: #6b7280;
  width: 120px;
  flex-shrink: 0;
}

.info-value {
  color: #1f2937;
  flex: 1;
}

.grid {
  display: grid;
}

.grid-cols-2 {
  grid-template-columns: repeat(2, 1fr);
}

.gap-6 {
  gap: 1.5rem;
}

.col-span-2 {
  grid-column: span 2 / span 2;
}

.px-4 {
  padding-left: 1rem;
  padding-right: 1rem;
}

.py-3 {
  padding-top: 0.75rem;
  padding-bottom: 0.75rem;
}

.rounded-t-lg {
  border-top-left-radius: 0.5rem;
  border-top-right-radius: 0.5rem;
}

.p-6 {
  padding: 1.5rem;
}

.mb-6 {
  margin-bottom: 1.5rem;
}

.flex {
  display: flex;
}

.justify-between {
  justify-content: space-between;
}

.items-center {
  align-items: center;
}

.text-lg {
  font-size: 1.125rem;
}

.font-bold {
  font-weight: 700;
}

.text-gray-800 {
  color: #1f2937;
}

.text-blue-600 {
  color: #2563eb;
}

.text-green-600 {
  color: #059669;
}

.text-purple-600 {
  color: #7c3aed;
}

.text-red-600 {
  color: #dc2626;
}

.font-medium {
  font-weight: 500;
}
</style>
