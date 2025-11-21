<template>
  <el-drawer
    v-model="drawerVisible"
    :size="isFullscreen ? '100%' : '75%'"
    class="detail-drawer"
    @close="handleClose"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">设备实时状态详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="drawer-container">
      <!-- 内容区 -->
      <div class="drawer-content">
        <div class="grid-layout">
          <!-- 基础信息卡片 -->
          <el-card class="info-card base-info-card">
            <div class="card-header">基础信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">主键ID：</span>
                <span class="value">{{ data?.id || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">状态ID：</span>
                <span class="value key-field">{{ data?.realTimeStatusId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">设备ID：</span>
                <span class="value key-field">{{ data?.deviceId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">设备名称：</span>
                <span class="value">{{ data?.deviceName || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">设备编码：</span>
                <span class="value">{{ data?.deviceCode || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">运行状态：</span>
                <span
                  class="value"
                  :class="data?.runStatus === 'online' ? 'status-normal' : 'status-abnormal'"
                >
                  {{ data?.runStatus || '-' }}
                </span>
              </div>
            </div>
          </el-card>

          <!-- 时间信息卡片 -->
          <el-card class="info-card time-info-card">
            <div class="card-header">时间信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">最近上报时间：</span>
                <span class="value">{{ formatDate(data?.lastRptTime) }}</span>
              </div>
              <div class="info-item">
                <span class="label">状态更新时间：</span>
                <span class="value">{{ formatDate(data?.statusUpdateTime) }}</span>
              </div>
              <div class="info-item">
                <span class="label">创建时间：</span>
                <span class="value">{{ formatDate(data?.createTime) }}</span>
              </div>
            </div>
          </el-card>

          <!-- 参数信息卡片 -->
          <el-card class="info-card param-info-card">
            <div class="card-header">参数信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">实时参数1：</span>
                <span class="value">{{ data?.realTimeParam1 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">实时参数2：</span>
                <span class="value">{{ data?.realTimeParam2 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">分类扩展字段1：</span>
                <span class="value">{{ data?.extCat1 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">分类扩展字段2：</span>
                <span class="value">{{ data?.extCat2 || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 扩展信息卡片 -->
          <el-card class="info-card extend-info-card">
            <div class="card-header">扩展信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">所属行政区划代码：</span>
                <span class="value">{{ data?.regionCode || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">所属行政区划名称：</span>
                <span class="value">{{ data?.regionName || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">所属网格ID：</span>
                <span class="value">{{ data?.gridId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">所属网格名称：</span>
                <span class="value">{{ data?.gridName || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">通用扩展字段1：</span>
                <span class="value">{{ data?.extCommon1 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">通用扩展字段2：</span>
                <span class="value">{{ data?.extCommon2 || '-' }}</span>
              </div>
            </div>
          </el-card>
        </div>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DeviceRealTimeStatusVO } from '@/api/dataHub/deviceManage/daviceStatusMonitor/devicerealtimestatus'

defineOptions({ name: 'DeviceRealTimeStatusDetail' })

// 定义props
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  data: {
    type: Object as () => DeviceRealTimeStatusVO | null,
    default: null
  }
})

// 定义emit
const emit = defineEmits(['close'])
// 内部状态管理抽屉显示（解决直接修改props问题）
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

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  const date = new Date(val)
  return date.toLocaleString()
}
</script>

<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f5f7fa;
}

.drawer-container {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.drawer-header {
  padding: 16px 20px;
  border-bottom: 1px solid #eee;
  background-color: #fff;
}

.drawer-content {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.info-card {
  transition: all 0.3s ease;
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 16px;
  font-weight: 500;
  border-bottom: 1px solid #f0f0f0;
  border-radius: 8px 8px 0 0;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
  color: #1890ff;
}

.time-info-card .card-header {
  background-color: #fff2e8;
  color: #fa8c16;
}

.param-info-card .card-header {
  background-color: #f6ffed;
  color: #52c41a;
}

.extend-info-card .card-header {
  background-color: #fff0f6;
  color: #eb2f96;
}

.card-body {
  padding: 16px;
}

.info-item {
  display: flex;
  padding: 8px 0;
  border-bottom: 1px dashed #f0f0f0;
}

.info-item:last-child {
  border-bottom: none;
}

.label {
  flex: 0 0 120px;
  color: #666;
  font-size: 14px;
}

.value {
  flex: 1;
  color: #333;
  font-size: 14px;
}

.key-field {
  color: #1890ff;
  font-weight: 500;
}

.status-normal {
  color: #52c41a;
  font-weight: 500;
}

.status-abnormal {
  color: #f5222d;
  font-weight: 500;
}

/* 响应式调整 */
@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }
}
</style>
