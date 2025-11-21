<template>
  <el-drawer
    v-model="drawerVisible"
    :title="null"
    :size="isFullscreen ? '100%' : '75%'"
    class="detail-drawer"
    @close="handleClose"
  >
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">设备数据质量检查详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="drawer-content">
      <!-- 详情内容 -->
      <div class="grid-layout">
        <!-- 基础信息卡片 -->
        <el-card class="info-card base-info-card">
          <div class="card-header">
            <span class="card-title">基础信息</span>
          </div>
          <div class="card-content">
            <div class="info-item">
              <span class="info-label">主键ID：</span>
              <span class="info-value">{{ data.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">质量检查ID：</span>
              <span class="info-value font-medium text-blue-500">{{ data.qualCkId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">设备ID：</span>
              <span class="info-value">{{ data.deviceId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">设备名称：</span>
              <span class="info-value font-medium text-blue-500">{{ data.deviceName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">设备编码：</span>
              <span class="info-value">{{ data.deviceCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">检查批次：</span>
              <span class="info-value">{{ data.ckBatch || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 检查信息卡片 -->
        <el-card class="info-card check-info-card">
          <div class="card-header">
            <span class="card-title">检查信息</span>
          </div>
          <div class="card-content">
            <div class="info-item">
              <span class="info-label">检查时间：</span>
              <span class="info-value">{{ formatDate(data.ckTime) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">检查项：</span>
              <span class="info-value">{{ data.ckItem || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">检查结果：</span>
              <span class="info-value">{{ data.ckResult || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">检查人：</span>
              <span class="info-value">{{ data.ckUser || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">错误描述：</span>
              <span class="info-value">{{ data.errorDesc || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">整改建议：</span>
              <span class="info-value">{{ data.rectifySugg || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 整改信息卡片 -->
        <el-card class="info-card rectify-info-card">
          <div class="card-header">
            <span class="card-title">整改信息</span>
          </div>
          <div class="card-content">
            <div class="info-item">
              <span class="info-label">整改状态：</span>
              <span class="info-value">
                <span v-if="data.rectifyStatus === '1'" class="status-success">已整改</span>
                <span v-else class="status-danger">未整改</span>
              </span>
            </div>
            <div class="info-item">
              <span class="info-label">整改时间：</span>
              <span class="info-value">{{ formatDate(data.rectifyTime) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">整改人：</span>
              <span class="info-value">{{ data.rectifyUser || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card ext-info-card">
          <div class="card-header">
            <span class="card-title">扩展信息</span>
          </div>
          <div class="card-content">
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
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DeviceDataQualCkVO } from '@/api/dataHub/deviceManage/deviceDataManage/devicedataqualck'

defineOptions({ name: 'DeviceDataQualCkDetail' })

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleString() : '-')

// Props
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  }
})

// Emits
const emit = defineEmits(['close'])
// 内部状态管理抽屉显示（解决直接修改props问题）
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)
// 状态
const data = ref<Partial<DeviceDataQualCkVO>>({})
const isFullscreen = ref(false)

// 设置详情数据
const setData = (row: DeviceDataQualCkVO) => {
  data.value = { ...row }
}

// 关闭抽屉
const handleClose = () => {
  emit('close')
  isFullscreen.value = false
}

// 暴露方法
defineExpose({ setData })
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
  padding: 20px;
}

.drawer-content {
  height: 100%;
  overflow-y: auto;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.info-card {
  border-radius: 8px;
  border: none;
  transition: all 0.3s ease;
  overflow: hidden;
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 15px;
  background-color: #f0f7ff;
  border-bottom: 1px solid #e6f7ff;
}

.base-info-card .card-header {
  background-color: #f0f7ff;
  border-bottom: 1px solid #e6f7ff;
}

.check-info-card .card-header {
  background-color: #fff0f3;
  border-bottom: 1px solid #ffe6eb;
}

.rectify-info-card .card-header {
  background-color: #f6ffed;
  border-bottom: 1px solid #eaffd0;
}

.ext-info-card .card-header {
  background-color: #fff7e6;
  border-bottom: 1px solid #fff1cc;
}

.card-title {
  font-weight: 600;
  color: #1890ff;
}

.base-info-card .card-title {
  color: #1890ff;
}

.check-info-card .card-title {
  color: #f5222d;
}

.rectify-info-card .card-title {
  color: #52c41a;
}

.ext-info-card .card-title {
  color: #faad14;
}

.card-content {
  padding: 15px;
}

.info-item {
  display: flex;
  padding: 8px 0;
  border-bottom: 1px dashed #f0f0f0;
}

.info-item:last-child {
  border-bottom: none;
}

.info-label {
  flex: 0 0 120px;
  color: #666;
  font-weight: 500;
}

.info-value {
  flex: 1;
  color: #333;
  word-break: break-all;
}

.status-success {
  color: #52c41a;
  font-weight: 500;
}

.status-danger {
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
