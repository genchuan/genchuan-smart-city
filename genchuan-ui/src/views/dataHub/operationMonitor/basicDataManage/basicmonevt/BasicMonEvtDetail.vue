<template>
  <el-drawer
    v-model="drawerVisible"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">基础监测事件详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基本信息 -->
      <el-card class="info-card" hoverable>
        <div class="card-header bg-blue-50">
          <span class="text-blue-600 font-medium">基本信息</span>
        </div>
        <div class="grid grid-cols-2 gap-4 mt-4">
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value font-medium text-blue-600">{{ data.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">事件ID：</span>
            <span class="value">{{ data.evtId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">事件编码：</span>
            <span class="value font-medium text-blue-600">{{ data.evtCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">事件类型：</span>
            <span class="value">{{ data.evtType || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">事发时间：</span>
            <span class="value">{{ formatDate(data.evtTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">创建时间：</span>
            <span class="value">{{ formatDate(data.createTimeSys) }}</span>
          </div>
        </div>
      </el-card>

      <!-- 关联信息 -->
      <el-card class="info-card mt-4" hoverable>
        <div class="card-header bg-purple-50">
          <span class="text-purple-600 font-medium">关联信息</span>
        </div>
        <div class="grid grid-cols-2 gap-4 mt-4">
          <div class="info-item">
            <span class="label">关联点位ID：</span>
            <span class="value">{{ data.relPtId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联点位名称：</span>
            <span class="value font-medium text-blue-600">{{ data.relPtName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联指标ID：</span>
            <span class="value">{{ data.relIdxId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联指标名称：</span>
            <span class="value">{{ data.relIdxName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联设备编码：</span>
            <span class="value">{{ data.relDeviceCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联设备名称：</span>
            <span class="value font-medium text-blue-600">{{ data.relDeviceName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">事件数值：</span>
            <span class="value">{{ data.evtValue || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 处置信息 -->
      <el-card class="info-card mt-4" hoverable>
        <div class="card-header bg-green-50">
          <span class="text-green-600 font-medium">处置信息</span>
        </div>
        <div class="grid grid-cols-2 gap-4 mt-4">
          <div class="info-item">
            <span class="label">处置状态：</span>
            <span
              class="value"
              :class="data.dealStatus === '已处置' ? 'text-green-600' : 'text-red-600'"
            >
              {{ data.dealStatus }}
            </span>
          </div>
          <div class="info-item">
            <span class="label">派单部门代码：</span>
            <span class="value">{{ data.deptCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">派单部门名称：</span>
            <span class="value">{{ data.deptName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">处置人ID：</span>
            <span class="value">{{ data.dealUserId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">处置人姓名：</span>
            <span class="value">{{ data.dealUserName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">处置时间：</span>
            <span class="value">{{ formatDate(data.dealTime) }}</span>
          </div>
          <div class="info-item col-span-2">
            <span class="label block">处置结果：</span>
            <span class="value block mt-1">{{ data.dealResult || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 扩展信息 -->
      <el-card class="info-card mt-4" hoverable>
        <div class="card-header bg-yellow-50">
          <span class="text-yellow-600 font-medium">扩展信息</span>
        </div>
        <div class="grid grid-cols-2 gap-4 mt-4">
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
import { BasicMonEvtVO } from '@/api/dataHub/operationMonitor/basicDataManage/basicmonevt'

const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['close'])
// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)
const data = ref<Partial<BasicMonEvtVO>>({})
const isFullscreen = ref(false)

// 设置详情数据
const setData = (row: BasicMonEvtVO) => {
  data.value = { ...row }
}

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

defineExpose({ setData })
</script>

<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f5f7fa;
}

.detail-container {
  padding: 15px;
}

.info-card {
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 10px 15px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0;
}

.info-item {
  padding: 5px 0;
}

.label {
  color: #6b7280;
  display: inline-block;
  width: 120px;
}

.value {
  color: #1f2937;
}

::v-deep .el-card__body {
  padding: 15px;
}
</style>
