<template>
  <el-drawer
    v-model="visible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="device-detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">设备关联详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基础信息卡片 -->
      <div class="grid-layout">
        <!-- 基础关联信息 -->
        <el-card class="info-card base-info-card">
          <template #header>
            <div class="card-header bg-blue-50 text-blue-600">
              <span class="font-medium">基础关联信息</span>
            </div>
          </template>
          <div class="info-grid">
            <div class="info-item">
              <span class="label">主键ID：</span>
              <span class="value">{{ detailData.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联ID：</span>
              <span class="value font-semibold text-blue-600">{{
                detailData.deviceRelDeviceId || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联类型：</span>
              <span class="value">{{ detailData.relType || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联时间：</span>
              <span class="value">{{ formatDate(detailData.relTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联人：</span>
              <span class="value">{{ detailData.relUser || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联状态：</span>
              <span class="value">
                <span
                  class="status-tag"
                  :class="detailData.relStatus === '1' ? 'status-active' : 'status-inactive'"
                >
                  {{ detailData.relStatus === '1' ? '已关联' : '未关联' }}
                </span>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 主设备信息 -->
        <el-card class="info-card main-device-card">
          <template #header>
            <div class="card-header bg-green-50 text-green-600">
              <span class="font-medium">主设备信息</span>
            </div>
          </template>
          <div class="info-grid">
            <div class="info-item">
              <span class="label">主设备ID：</span>
              <span class="value font-semibold text-blue-600">{{
                detailData.mainDeviceId || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">主设备名称：</span>
              <span class="value">{{ detailData.mainDeviceName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">主设备编码：</span>
              <span class="value">{{ detailData.mainDeviceCode || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 从设备信息 -->
        <el-card class="info-card slave-device-card">
          <template #header>
            <div class="card-header bg-purple-50 text-purple-600">
              <span class="font-medium">从设备信息</span>
            </div>
          </template>
          <div class="info-grid">
            <div class="info-item">
              <span class="label">从设备ID：</span>
              <span class="value font-semibold text-blue-600">{{
                detailData.slaveDeviceId || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">从设备名称：</span>
              <span class="value">{{ detailData.slaveDeviceName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">从设备编码：</span>
              <span class="value">{{ detailData.slaveDeviceCode || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 调整信息 -->
        <el-card class="info-card adjust-info-card">
          <template #header>
            <div class="card-header bg-yellow-50 text-yellow-600">
              <span class="font-medium">调整信息</span>
            </div>
          </template>
          <div class="info-grid">
            <div class="info-item">
              <span class="label">调整时间：</span>
              <span class="value">{{ formatDate(detailData.adjustTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">调整人：</span>
              <span class="value">{{ detailData.adjustUser || '-' }}</span>
            </div>
            <div class="info-item full-width">
              <span class="label">调整原因：</span>
              <span class="value">{{ detailData.adjustReason || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息 -->
        <el-card class="info-card ext-info-card span-2">
          <template #header>
            <div class="card-header bg-pink-50 text-pink-600">
              <span class="font-medium">扩展信息</span>
            </div>
          </template>
          <div class="info-grid ext-grid">
            <div class="info-item">
              <span class="label">分类扩展字段1：</span>
              <span class="value">{{ detailData.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类扩展字段2：</span>
              <span class="value">{{ detailData.extCat2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段1：</span>
              <span class="value">{{ detailData.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段2：</span>
              <span class="value">{{ detailData.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DeviceDeviceVO } from '@/api/dataHub/deviceManage/deviceOpManage/devicedevice'

defineOptions({ name: 'DeviceDeviceDetailDrawer' })
const visible = ref(false)
const title = ref('设备关联设备详情')
const isFullscreen = ref(false)

const detailData = ref<DeviceDeviceVO>({
  id: 0, // 主键ID
  deviceRelDeviceId: '', // 关联ID
  mainDeviceId: '', // 主设备ID
  mainDeviceName: '', // 主设备名称
  mainDeviceCode: '', // 主设备编码
  slaveDeviceId: '', // 从设备ID
  slaveDeviceName: '', // 从设备名称
  slaveDeviceCode: '', // 从设备编码
  relType: '', // 关联类型
  relTime: new Date(), // 关联时间
  relUser: '', // 关联人
  relStatus: '', // 关联状态
  adjustTime: new Date(), // 调整时间
  adjustUser: '', // 调整人
  adjustReason: '', // 调整原因
  extCat1: '', // 分类扩展字段1
  extCat2: '', // 分类扩展字段2
  extCommon1: '', // 通用扩展字段1
  extCommon2: '' // 通用扩展字段2
})

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 打开抽屉
const open = (data: DeviceDeviceVO) => {
  detailData.value = { ...data }
  visible.value = true
  isFullscreen.value = false
}

// 关闭抽屉
const handleClose = () => {
  visible.value = false
}

defineExpose({ open })
</script>

<style scoped>
.device-detail-drawer {
  background-color: #f5f7fa; /* 浅灰背景 */
}

.detail-container {
  padding: 20px;
  height: calc(100% - 60px);
  overflow-y: auto;
}

/* Grid布局 */
.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

/* 跨列样式 */
.span-2 {
  grid-column: span 2;
}

/* 卡片样式 */
.info-card {
  border: none;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
  overflow: hidden;
}

/* 卡片hover效果 */
.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
}

/* 卡片头部样式 */
.card-header {
  padding: 12px 16px;
  font-size: 14px;
}

/* 卡片内容网格 */
.info-grid {
  padding: 16px;
  display: grid;
  grid-template-columns: 1fr;
  gap: 12px;
}

/* 扩展信息特殊网格 */
.ext-grid {
  grid-template-columns: repeat(2, 1fr);
}

/* 信息项样式 */
.info-item {
  display: flex;
  align-items: center;
  line-height: 1.6;
}

/* 全屏宽度信息项 */
.full-width {
  grid-column: span 2;
}

/* 标签样式 */
.label {
  flex: 0 0 110px;
  color: #6b7280; /* 标签文字颜色 */
  font-size: 14px;
}

/* 值样式 */
.value {
  flex: 1;
  color: #374151; /* 主文字深灰 */
  font-size: 14px;
  word-break: break-all;
}

/* 状态标签样式 */
.status-tag {
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 500;
}

/* 已关联状态（绿色） */
.status-active {
  background-color: rgba(16, 185, 129, 0.1);
  color: #10b981;
}

/* 未关联状态（红色） */
.status-inactive {
  background-color: rgba(239, 68, 68, 0.1);
  color: #ef4444;
}

/* 适配响应式 */
@media (max-width: 768px) {
  .grid-layout,
  .ext-grid {
    grid-template-columns: 1fr;
  }

  .span-2,
  .full-width {
    grid-column: span 1;
  }
}

/* 修复Element-Plus抽屉样式冲突 */
::v-deep .el-drawer__body {
  padding: 0;
}

::v-deep .el-drawer__header {
  border-bottom: 1px solid #e5e7eb;
  margin-bottom: 0;
}
</style>
