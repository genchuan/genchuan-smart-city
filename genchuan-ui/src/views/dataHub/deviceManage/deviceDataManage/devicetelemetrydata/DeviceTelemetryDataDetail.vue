<template>
  <el-drawer v-model="drawerVisible" :size="isFullscreen ? '100%' : '75%'" @close="handleClose">
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">设备遥测数据详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="drawer-container" :class="{ fullscreen: isFullscreen }">
      <!-- 内容区域 -->
      <div class="drawer-content">
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
          <!-- 基础信息卡片 - 修复template无用属性 -->
          <el-card class="info-card base-info-card hover-lift">
            <template #header>
              <div class="card-header base-header">
                <span class="header-title">基础信息</span>
              </div>
            </template>
            <div class="info-item">
              <span class="info-label">主键ID：</span>
              <span class="info-value">{{ data?.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">遥测数据ID：</span>
              <span class="info-value font-semibold text-blue-500">{{
                data?.telemetryDataId || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">设备ID：</span>
              <span class="info-value">{{ data?.deviceId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">设备名称：</span>
              <span class="info-value">{{ data?.deviceName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">设备编码：</span>
              <span class="info-value">{{ data?.deviceCode || '-' }}</span>
            </div>
          </el-card>

          <!-- 遥测信息卡片 -->
          <el-card class="info-card telemetry-info-card hover-lift">
            <template #header>
              <div class="card-header telemetry-header">
                <span class="header-title">遥测信息</span>
              </div>
            </template>
            <div class="info-item">
              <span class="info-label">遥测项名称：</span>
              <span class="info-value">{{ data?.telemetryItemName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">遥测项代码：</span>
              <span class="info-value">{{ data?.telemetryItemCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">遥测值：</span>
              <span class="info-value font-semibold text-blue-500">{{
                data?.telemetryValue || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">数据类型：</span>
              <span class="info-value">{{ data?.dataType || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">采集时间：</span>
              <span class="info-value">{{ formatDate(data?.collectTime) }}</span>
            </div>
          </el-card>

          <!-- 存储信息卡片 -->
          <el-card class="info-card storage-info-card hover-lift">
            <template #header>
              <div class="card-header storage-header">
                <span class="header-title">存储信息</span>
              </div>
            </template>
            <div class="info-item">
              <span class="info-label">数据来源：</span>
              <span class="info-value">{{ data?.dataSource || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">存储周期：</span>
              <span class="info-value">{{ data?.storageCycle || '-' }} 天</span>
            </div>
            <div class="info-item">
              <span class="info-label">过期状态：</span>
              <span
                class="info-value"
                :class="data?.expireStatus === '1' ? 'status-red' : 'status-green'"
              >
                {{ data?.expireStatus === '1' ? '已过期' : '未过期' }}
              </span>
            </div>
            <div class="info-item">
              <span class="info-label">创建时间：</span>
              <span class="info-value">{{ formatDate(data?.createTimeSys) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">更新时间：</span>
              <span class="info-value">{{ formatDate(data?.updateTimeSys) }}</span>
            </div>
          </el-card>

          <!-- 扩展信息卡片 -->
          <el-card class="info-card extend-info-card hover-lift">
            <template #header>
              <div class="card-header extend-header">
                <span class="header-title">扩展信息</span>
              </div>
            </template>
            <div class="info-item">
              <span class="info-label">分类扩展字段1：</span>
              <span class="info-value">{{ data?.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">分类扩展字段2：</span>
              <span class="info-value">{{ data?.extCat2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">通用扩展字段1：</span>
              <span class="info-value">{{ data?.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">通用扩展字段2：</span>
              <span class="info-value">{{ data?.extCommon2 || '-' }}</span>
            </div>
          </el-card>
        </div>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DeviceTelemetryDataVO } from '@/api/dataHub/deviceManage/deviceDataManage/devicetelemetrydata'
import { watch } from 'vue'

defineOptions({ name: 'DeviceTelemetryDataDetail' })

// 时间格式化
const formatDate = (val: any) => {
  if (!val) return '-'
  const date = new Date(val)
  return date.toLocaleString()
}

// Props
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  data: {
    type: Object as () => DeviceTelemetryDataVO | null,
    default: null
  }
})

// Emits
const emit = defineEmits(['close'])

// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)

// 全屏状态
const isFullscreen = ref(false)

// 关闭抽屉
const handleClose = () => {
  emit('close')
  isFullscreen.value = false
}
</script>

<style scoped>
/* 样式部分保持不变 */
.drawer-container {
  background-color: #f5f7fa;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.drawer-header {
  padding: 16px 24px;
  border-bottom: 1px solid #e5e7eb;
  background-color: #fff;
}

.drawer-content {
  flex: 1;
  padding: 24px;
  overflow-y: auto;
}

.info-card {
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
}

.hover-lift:hover {
  transform: translateY(-4px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 16px;
  border-radius: 8px 8px 0 0;
}

.header-title {
  font-size: 16px;
  font-weight: 500;
}

.base-header {
  background-color: #e6f7ff;
}

.base-info-card .header-title {
  color: #1890ff;
}

.telemetry-header {
  background-color: #fff0f0;
}

.telemetry-info-card .header-title {
  color: #f5222d;
}

.storage-header {
  background-color: #f6ffed;
}

.storage-info-card .header-title {
  color: #52c41a;
}

.extend-header {
  background-color: #fff7e6;
}

.extend-info-card .header-title {
  color: #faad14;
}

.info-item {
  display: flex;
  padding: 10px 16px;
  border-bottom: 1px dashed #f0f0f0;
  align-items: center;
}

.info-item:last-child {
  border-bottom: none;
}

.info-label {
  width: 35%;
  color: #666;
  font-size: 14px;
}

.info-value {
  flex: 1;
  color: #333;
  word-break: break-all;
}

.fullscreen {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 1000;
}

.status-red {
  color: #ef4444;
  font-weight: 500;
}

.status-green {
  color: #10b981;
  font-weight: 500;
}

@media (max-width: 768px) {
  .info-item {
    flex-direction: column;
    align-items: flex-start;
  }

  .info-label {
    width: 100%;
    margin-bottom: 4px;
  }
}
</style>
