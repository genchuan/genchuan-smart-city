<template>
  <el-drawer
    v-model="visible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">设备关联网格详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基础信息卡片 -->
      <div class="grid grid-cols-1 md:grid-cols-2 gap-4 mb-6">
        <!-- 设备信息 -->
        <el-card class="info-card device-card">
          <div class="card-header device-header">
            <span class="card-title">设备信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">设备ID：</span>
              <span class="info-value font-medium text-blue-500">{{
                detailData.deviceId || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">设备名称：</span>
              <span class="info-value">{{ detailData.deviceName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">设备编码：</span>
              <span class="info-value">{{ detailData.deviceCode || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 网格信息 -->
        <el-card class="info-card grid-card">
          <div class="card-header grid-header">
            <span class="card-title">网格信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">网格ID：</span>
              <span class="info-value font-medium text-blue-500">{{
                detailData.gridId || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">网格名称：</span>
              <span class="info-value">{{ detailData.gridName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">网格编码：</span>
              <span class="info-value">{{ detailData.gridCode || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 关联信息 -->
        <el-card class="info-card relation-card">
          <div class="card-header relation-header">
            <span class="card-title">关联信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">关联ID：</span>
              <span class="info-value font-medium text-blue-500">{{
                detailData.deviceRelGridId || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">关联时间：</span>
              <span class="info-value">{{ formatDate(detailData.relTime) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">关联人：</span>
              <span class="info-value">{{ detailData.relUser || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">关联状态：</span>
              <span class="info-value">
                <span :class="detailData.relStatus === '1' ? 'status-active' : 'status-inactive'">
                  {{ detailData.relStatus === '1' ? '已关联' : '未关联' }}
                </span>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 调整信息 -->
        <el-card class="info-card adjust-card">
          <div class="card-header adjust-header">
            <span class="card-title">调整信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">调整时间：</span>
              <span class="info-value">{{ formatDate(detailData.adjustTime) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">调整人：</span>
              <span class="info-value">{{ detailData.adjustUser || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">调整原因：</span>
              <span class="info-value">{{ detailData.adjustReason || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息 -->
        <el-card class="info-card extend-card md:col-span-2">
          <div class="card-header extend-header">
            <span class="card-title">扩展信息</span>
          </div>
          <div class="card-body grid grid-cols-1 md:grid-cols-2 gap-4">
            <div class="info-item">
              <span class="info-label">分类扩展字段1：</span>
              <span class="info-value">{{ detailData.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">分类扩展字段2：</span>
              <span class="info-value">{{ detailData.extCat2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">通用扩展字段1：</span>
              <span class="info-value">{{ detailData.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">通用扩展字段2：</span>
              <span class="info-value">{{ detailData.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DeviceGridVO } from '@/api/dataHub/deviceManage/deviceOpManage/devicegrid'

defineOptions({ name: 'DeviceGridDetail' })

const visible = ref(false)
const title = ref('设备关联网格详情')
const isFullscreen = ref(false)
const detailData = ref<DeviceGridVO>({
  id: 0,
  deviceRelGridId: '',
  deviceId: '',
  deviceName: '',
  deviceCode: '',
  gridId: '',
  gridName: '',
  gridCode: '',
  relTime: new Date(),
  relUser: '',
  relStatus: '',
  adjustTime: new Date(),
  adjustUser: '',
  adjustReason: '',
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 打开抽屉
const open = (data: DeviceGridVO) => {
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
.detail-drawer {
  --el-drawer-bg-color: #f5f7fa;
}

.detail-container {
  padding: 20px;
}

.info-card {
  transition: all 0.3s ease;
  border-radius: 8px;
  border: none;
  overflow: hidden;
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 15px;
  color: #6b7280;
  font-weight: 500;
}

.card-title {
  font-size: 15px;
}

.card-body {
  padding: 15px;
}

.info-item {
  margin-bottom: 12px;
  line-height: 1.8;
}

.info-item:last-child {
  margin-bottom: 0;
}

.info-label {
  display: inline-block;
  width: 100px;
  color: #6b7280;
}

.info-value {
  color: #374151;
}

/* 卡片头部颜色 - 马克龙色系 */
.device-header {
  background-color: #e0f2fe;
}

.grid-header {
  background-color: #e0e7ff;
}

.relation-header {
  background-color: #dbeafe;
}

.adjust-header {
  background-color: #fef3c7;
}

.extend-header {
  background-color: #dcfce7;
}

/* 状态样式 */
.status-active {
  color: #10b981;
  font-weight: 500;
}

.status-inactive {
  color: #ef4444;
  font-weight: 500;
}
</style>
