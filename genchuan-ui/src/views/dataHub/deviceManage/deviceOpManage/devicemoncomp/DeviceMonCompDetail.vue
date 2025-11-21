<template>
  <el-drawer
    :title="''"
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">设备关联监测部件详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基础信息卡片 -->
      <div class="grid-layout">
        <el-card class="info-card base-info-card">
          <template #header>
            <!-- 修复：使用template #header替代slot="header" -->
            <div class="card-header">
              <span class="card-title">基础信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="info-label">主键ID：</span>
            <span class="info-value">{{ data.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">关联ID：</span>
            <span class="info-value font-bold text-blue-500">{{
              data.deviceRelMonCompId || '-'
            }}</span>
          </div>
        </el-card>

        <!-- 设备信息卡片 -->
        <el-card class="info-card device-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">设备信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="info-label">设备ID：</span>
            <span class="info-value font-bold text-blue-500">{{ data.deviceId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">设备名称：</span>
            <span class="info-value">{{ data.deviceName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">设备编码：</span>
            <span class="info-value">{{ data.deviceCode || '-' }}</span>
          </div>
        </el-card>

        <!-- 监测部件信息卡片 -->
        <el-card class="info-card component-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">监测部件信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="info-label">监测部件ID：</span>
            <span class="info-value font-bold text-blue-500">{{ data.monCompId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">监测部件名称：</span>
            <span class="info-value">{{ data.monCompName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">监测部件编码：</span>
            <span class="info-value">{{ data.monCompCode || '-' }}</span>
          </div>
        </el-card>

        <!-- 关联信息卡片 -->
        <el-card class="info-card relation-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">关联信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="info-label">关联时间：</span>
            <span class="info-value">{{ formatDate(data.relTime) }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">关联人：</span>
            <span class="info-value">{{ data.relUser || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">关联状态：</span>
            <span :class="data.relStatus === '1' ? 'status-active' : 'status-inactive'">
              {{ data.relStatus === '1' ? '已关联' : '未关联' }}
            </span>
          </div>
        </el-card>

        <!-- 调整信息卡片 -->
        <el-card class="info-card adjust-info-card" :class="{ 'span-2': true }">
          <!-- 修复：用class替代非标准属性span -->
          <template #header>
            <div class="card-header">
              <span class="card-title">调整信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="info-label">调整时间：</span>
            <span class="info-value">{{ formatDate(data.adjustTime) }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">调整人：</span>
            <span class="info-value">{{ data.adjustUser || '-' }}</span>
          </div>
          <div class="info-item full-width">
            <span class="info-label">调整原因：</span>
            <span class="info-value">{{ data.adjustReason || '-' }}</span>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card extend-info-card" :class="{ 'span-2': true }">
          <template #header>
            <div class="card-header">
              <span class="card-title">扩展信息</span>
            </div>
          </template>
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
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DeviceMonCompVO } from '@/api/dataHub/deviceManage/deviceOpManage/devicemoncomp'

defineOptions({ name: 'DeviceMonCompDetail' })

// 修复：明确props类型定义
const props = defineProps<{
  visible: boolean
}>()
const emit = defineEmits<{
  (e: 'update:visible', value: boolean): void
  (e: 'close'): void
}>()

// 修复：通过计算属性处理visible避免直接修改props
const visible = computed({
  get() {
    return props.visible
  },
  set(value) {
    emit('update:visible', value)
  }
})

const data = ref<Partial<DeviceMonCompVO>>({})
const isFullscreen = ref(false)

// 设置详情数据
const setData = (row: DeviceMonCompVO) => {
  data.value = { ...row }
}

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 关闭抽屉
const handleClose = () => {
  emit('close')
  isFullscreen.value = false
}

defineExpose({ setData })
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
}

.detail-container {
  padding: 20px;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

/* 修复：添加span-2类实现跨列效果 */
.span-2 {
  grid-column: span 2;
}

.info-card {
  transition: all 0.3s ease;
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  background-color: #f0f2f5;
  padding: 12px 16px;
  border-radius: 8px 8px 0 0;
}

.card-title {
  font-weight: 600;
  font-size: 14px;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
}

.base-info-card .card-title {
  color: #1890ff;
}

.device-info-card .card-header {
  background-color: #fff7e6;
}

.device-info-card .card-title {
  color: #fa8c16;
}

.component-info-card .card-header {
  background-color: #f6ffed;
}

.component-info-card .card-title {
  color: #52c41a;
}

.relation-info-card .card-header {
  background-color: #fff0f6;
}

.relation-info-card .card-title {
  color: #eb2f96;
}

.adjust-info-card .card-header {
  background-color: #f0f5ff;
}

.adjust-info-card .card-title {
  color: #40a9ff;
}

.extend-info-card .card-header {
  background-color: #fff5f5;
}

.extend-info-card .card-title {
  color: #ff4d4f;
}

.info-item {
  display: flex;
  padding: 10px 16px;
  border-bottom: 1px dashed #f0f0f0;
  align-items: flex-start;
}

.info-item:last-child {
  border-bottom: none;
}

.info-item.full-width {
  flex-direction: column;
}

.info-item.full-width .info-label {
  margin-bottom: 5px;
}

.info-label {
  font-weight: 500;
  color: #666;
  width: 120px;
  flex-shrink: 0;
}

.info-value {
  color: #333;
  flex-grow: 1;
  word-break: break-all;
}

.status-active {
  color: #52c41a;
  background-color: rgba(82, 196, 26, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.status-inactive {
  color: #ff4d4f;
  background-color: rgba(255, 77, 79, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

/* 修复：使用::v-deep替代/deep/，并移除未使用的选择器 */
::v-deep .el-drawer__body {
  padding: 0;
  overflow-y: auto;
}
</style>
