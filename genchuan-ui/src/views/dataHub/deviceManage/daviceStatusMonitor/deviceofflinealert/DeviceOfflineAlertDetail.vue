<template>
  <el-drawer
    v-model="drawerVisible"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">设备离线预警详情</span>
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
        <div class="grid-layout">
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value">{{ data.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">预警ID：</span>
            <span class="value primary">{{ data.offlineAlertId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备ID：</span>
            <span class="value">{{ data.deviceId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备名称：</span>
            <span class="value primary">{{ data.deviceName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备编码：</span>
            <span class="value">{{ data.deviceCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">所属行政区划：</span>
            <span class="value">{{ data.regionName || '-' }}({{ data.regionCode || '' }})</span>
          </div>
        </div>
      </el-card>

      <!-- 离线信息 -->
      <el-card class="info-card offline-info-card">
        <div class="card-header">离线信息</div>
        <div class="grid-layout">
          <div class="info-item">
            <span class="label">离线开始时间：</span>
            <span class="value">{{ formatDate(data.offlineStartTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">离线时长：</span>
            <span class="value primary">{{ data.offlineEndure || '-' }} 分钟</span>
          </div>
        </div>
      </el-card>

      <!-- 预警信息 -->
      <el-card class="info-card alert-info-card">
        <div class="card-header">预警信息</div>
        <div class="grid-layout">
          <div class="info-item">
            <span class="label">预警级别：</span>
            <span class="value">{{ data.alertLevel || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">预警接收人：</span>
            <span class="value">{{ data.alertUserName || '-' }}({{ data.alertUserId || '' }})</span>
          </div>
          <div class="info-item">
            <span class="label">预警推送时间：</span>
            <span class="value">{{ formatDate(data.alertPushTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">预警状态：</span>
            <span class="value status">
              <el-tag
                :type="data.alertStatus === '1' ? 'success' : 'danger'"
                v-if="data.alertStatus !== undefined"
              >
                {{ data.alertStatus === '1' ? '已处理' : '未处理' }}
              </el-tag>
              <span v-else>-</span>
            </span>
          </div>
        </div>
      </el-card>

      <!-- 处理信息 -->
      <el-card class="info-card handle-info-card">
        <div class="card-header">处理信息</div>
        <div class="grid-layout">
          <div class="info-item">
            <span class="label">处理人：</span>
            <span class="value"
              >{{ data.handleUserName || '-' }}({{ data.handleUserId || '' }})</span
            >
          </div>
          <div class="info-item">
            <span class="label">处理时间：</span>
            <span class="value">{{ formatDate(data.handleTime) }}</span>
          </div>
          <div class="info-item full-width">
            <span class="label">处理结果：</span>
            <span class="value">{{ data.handleResult || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 扩展信息 -->
      <el-card class="info-card ext-info-card">
        <div class="card-header">扩展信息</div>
        <div class="grid-layout">
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
import { DeviceOfflineAlertVO } from '@/api/dataHub/deviceManage/daviceStatusMonitor/deviceofflinealert'

defineOptions({ name: 'DeviceOfflineAlertDetail' })

const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['close'])
// 本地状态管理抽屉显示（解决直接修改props问题）
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)
const data = ref<Partial<DeviceOfflineAlertVO>>({})
const isFullscreen = ref(false)

// 设置详情数据
const setData = (row: DeviceOfflineAlertVO) => {
  data.value = { ...row }
  isFullscreen.value = false
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
  --el-drawer-bg-color: #f5f7fa;
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
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 20px;
  font-weight: 500;
  color: #4b5563;
  font-size: 14px;
}

.base-info-card .card-header {
  background-color: #e6f7f0;
}

.offline-info-card .card-header {
  background-color: #fff1f0;
}

.alert-info-card .card-header {
  background-color: #fff7e6;
}

.handle-info-card .card-header {
  background-color: #e8f4fd;
}

.ext-info-card .card-header {
  background-color: #f6f3f9;
}

.grid-layout {
  padding: 15px 20px;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 15px;
}

.full-width {
  grid-column: 1 / -1;
}

.info-item {
  display: flex;
  padding: 5px 0;
  border-bottom: 1px dashed #f0f0f0;
  align-items: flex-start;
}

.info-item:last-child {
  border-bottom: none;
}

.label {
  color: #6b7280;
  min-width: 120px;
  flex-shrink: 0;
}

.value {
  color: #1f2937;
  flex-grow: 1;
  word-break: break-all;
}

.value.primary {
  color: #3b82f6;
  font-weight: 500;
}

.value.status {
  display: flex;
  align-items: center;
}

::v-deep .el-tag {
  margin-top: 2px;
}
</style>
