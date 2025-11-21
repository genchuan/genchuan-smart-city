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
        <span class="text-lg font-bold text-gray-800">设备客户端属性详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基础信息 -->
      <el-card class="info-card base-info-card">
        <div class="card-header">基础信息</div>
        <div class="info-grid">
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value font-semibold text-blue-500">{{ detailData.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备ID：</span>
            <span class="value">{{ detailData.deviceId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备名称：</span>
            <span class="value">{{ detailData.deviceName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备编码：</span>
            <span class="value">{{ detailData.deviceCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">属性配置ID：</span>
            <span class="value">{{ detailData.clientAttrCfgId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">配置人：</span>
            <span class="value">{{ detailData.cfgUser || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 属性信息 -->
      <el-card class="info-card attr-info-card">
        <div class="card-header">属性信息</div>
        <div class="info-grid">
          <div class="info-item">
            <span class="label">属性名称：</span>
            <span class="value">{{ detailData.attrName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">属性代码：</span>
            <span class="value">{{ detailData.attrCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">属性值：</span>
            <span class="value">{{ detailData.attrValue || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">数据类型：</span>
            <span class="value">{{ detailData.dataType || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">是否必选：</span>
            <span
              :class="detailData.isRequired !== '1' ? 'value text-green-500' : 'value text-red-500'"
            >
              {{ detailData.isRequired === '1' ? '是' : '否' }}
            </span>
          </div>
          <div class="info-item">
            <span class="label">属性说明：</span>
            <span class="value">{{ detailData.attrDesc || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 时间信息 -->
      <el-card class="info-card time-info-card">
        <div class="card-header">时间信息</div>
        <div class="info-grid">
          <div class="info-item">
            <span class="label">配置时间：</span>
            <span class="value">{{ formatDate(detailData.cfgTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">更新时间：</span>
            <span class="value">{{ formatDate(detailData.updateTimeSys) }}</span>
          </div>
        </div>
      </el-card>

      <!-- 扩展信息 -->
      <el-card class="info-card ext-info-card">
        <div class="card-header">扩展信息</div>
        <div class="info-grid">
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
  </el-drawer>
</template>

<script setup lang="ts">
import { DeviceClientAttrVO } from '@/api/dataHub/deviceManage/deviceDataManage/deviceclientattr'

defineOptions({ name: 'DeviceClientAttrDetail' })

const visible = ref(false)
const title = ref('设备属性详情')
const isFullscreen = ref(false)
const detailData = ref<DeviceClientAttrVO>({} as DeviceClientAttrVO)

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 打开抽屉
const open = (data: DeviceClientAttrVO) => {
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
  background-color: #f5f7fa;
}

.detail-container {
  padding: 20px;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.info-card {
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
  overflow: hidden;
}

.info-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 20px;
  font-weight: 500;
  font-size: 15px;
  color: #333;
}

.base-info-card .card-header {
  background-color: #e0f2fe;
  color: #0284c7;
}

.attr-info-card .card-header {
  background-color: #dbeafe;
  color: #1e40af;
}

.time-info-card .card-header {
  background-color: #fef3c7;
  color: #d97706;
}

.ext-info-card .card-header {
  background-color: #def7ec;
  color: #059669;
}

.info-grid {
  padding: 16px 20px;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
}

.info-item {
  display: flex;
  line-height: 1.8;
}

.label {
  color: #64748b;
  min-width: 110px;
}

.value {
  color: #334155;
  word-break: break-all;
}

.text-blue-500 {
  color: #3b82f6;
}

.text-green-500 {
  color: #10b981;
  font-weight: 500;
}

.text-red-500 {
  color: #ef4444;
  font-weight: 500;
}

::v-deep .el-drawer__body {
  padding: 0;
  overflow-y: auto;
}
</style>
