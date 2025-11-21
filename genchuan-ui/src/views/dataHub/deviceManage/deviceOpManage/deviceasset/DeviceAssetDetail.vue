<template>
  <el-drawer
    :title="''"
    v-model="visibleComputed"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">设备关联资产详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基础信息 -->
      <div class="grid-layout">
        <el-card class="info-card base-info-card">
          <div class="card-header">基础信息</div>
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value">{{ data.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联ID：</span>
            <span class="value">{{ data.deviceRelAssetId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备ID：</span>
            <span class="value font-semibold text-blue-500">{{ data.deviceId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备名称：</span>
            <span class="value font-semibold text-blue-500">{{ data.deviceName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备编码：</span>
            <span class="value font-semibold text-blue-500">{{ data.deviceCode || '-' }}</span>
          </div>
        </el-card>

        <el-card class="info-card asset-info-card">
          <div class="card-header">资产信息</div>
          <div class="info-item">
            <span class="label">资产ID：</span>
            <span class="value font-semibold text-purple-500">{{ data.assetId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">资产名称：</span>
            <span class="value font-semibold text-purple-500">{{ data.assetName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">资产编码：</span>
            <span class="value font-semibold text-purple-500">{{ data.assetCode || '-' }}</span>
          </div>
        </el-card>

        <el-card class="info-card relation-info-card">
          <div class="card-header">关联信息</div>
          <div class="info-item">
            <span class="label">关联时间：</span>
            <span class="value">{{ formatDate(data.relTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联人：</span>
            <span class="value">{{ data.relUser || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联状态：</span>
            <span class="value">
              <span :class="data.relStatus === '1' ? 'status-active' : 'status-inactive'">
                {{ data.relStatus === '1' ? '已关联' : data.relStatus === '0' ? '未关联' : '-' }}
              </span>
            </span>
          </div>
        </el-card>

        <el-card class="info-card adjust-info-card">
          <div class="card-header">调整信息</div>
          <div class="info-item">
            <span class="label">调整时间：</span>
            <span class="value">{{ formatDate(data.adjustTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">调整人：</span>
            <span class="value">{{ data.adjustUser || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">调整原因：</span>
            <span class="value">{{ data.adjustReason || '-' }}</span>
          </div>
        </el-card>

        <el-card class="info-card ext-info-card span-2">
          <div class="card-header">扩展信息</div>
          <div class="grid-ext-info">
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
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DeviceAssetVO } from '@/api/dataHub/deviceManage/deviceOpManage/deviceasset'

const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['update:visible', 'close'])

const visibleComputed = computed({
  get() {
    return props.visible
  },
  set(value) {
    emit('update:visible', value)
  }
})

const data = ref<DeviceAssetVO>({
  id: 0,
  deviceRelAssetId: '',
  deviceId: '',
  deviceName: '',
  deviceCode: '',
  assetId: '',
  assetName: '',
  assetCode: '',
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

const isFullscreen = ref(false)

// 设置详情数据
const setData = (row: DeviceAssetVO) => {
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

.grid-ext-info {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 10px;
}

.span-2 {
  grid-column: span 2;
}

.info-card {
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
  overflow: hidden;
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 16px;
  font-weight: 500;
  color: #4b5563;
  border-bottom: 1px solid #f0f0f0;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
}

.asset-info-card .card-header {
  background-color: #f6f3ff;
}

.relation-info-card .card-header {
  background-color: #fff7e6;
}

.adjust-info-card .card-header {
  background-color: #f0fff4;
}

.ext-info-card .card-header {
  background-color: #fff0f6;
}

.info-item {
  display: flex;
  margin-bottom: 12px;
  line-height: 1.6;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  flex: 0 0 120px;
  color: #6b7280;
  font-size: 14px;
}

.value {
  flex: 1;
  color: #374151;
  font-size: 14px;
  word-break: break-all;
}

.status-active {
  color: #10b981;
  font-weight: 500;
}

.status-inactive {
  color: #ef4444;
  font-weight: 500;
}
</style>
