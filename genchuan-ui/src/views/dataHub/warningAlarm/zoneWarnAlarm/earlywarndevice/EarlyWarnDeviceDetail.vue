<template>
  <el-drawer
    v-model="drawerVisible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">按设备预警告警详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <div class="grid-layout">
        <!-- 基础信息卡片 -->
        <el-card class="info-card base-info-card">
          <div class="card-header">基础信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">统计ID：</span>
              <span class="value">{{ data.warnDeviceStatId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计周期：</span>
              <span class="value font-semibold text-blue-600">{{ data.statCycle || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计时间：</span>
              <span class="value">{{ formatDate(data.statTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计人：</span>
              <span class="value">{{ data.statUserId || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 设备信息卡片 -->
        <el-card class="info-card device-info-card">
          <div class="card-header">设备信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">设备大类ID：</span>
              <span class="value">{{ data.deviceMajorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">设备大类名称：</span>
              <span class="value font-semibold text-blue-600">{{
                data.deviceMajorName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">设备型号ID：</span>
              <span class="value">{{ data.deviceModelId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">设备型号名称：</span>
              <span class="value font-semibold text-blue-600">{{
                data.deviceModelName || '-'
              }}</span>
            </div>
          </div>
        </el-card>

        <!-- 预警信息卡片 -->
        <el-card class="info-card warning-info-card">
          <div class="card-header">预警信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">预警总数：</span>
              <span class="value font-semibold text-orange-500">{{
                data.totalWarnCount || 0
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">主要故障类型：</span>
              <span class="value">{{ data.mainFaultType || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">涉及设备数：</span>
              <span class="value">{{ data.deviceCount || 0 }}</span>
            </div>
          </div>
        </el-card>

        <!-- 修复信息卡片 -->
        <el-card class="info-card repair-info-card">
          <div class="card-header">修复信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">在线修复数：</span>
              <span class="value">{{ data.onlineRepairCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">在线修复率：</span>
              <span class="value">
                <span :class="data.onlineRepairRate >= 80 ? 'text-green-500' : 'text-red-500'">
                  {{ data.onlineRepairRate ? data.onlineRepairRate + '%' : '-' }}
                </span>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card expand-info-card">
          <div class="card-header">扩展信息</div>
          <div class="card-content expand-content">
            <div class="info-row">
              <div class="info-item">
                <span class="label">分类扩展字段1：</span>
                <span class="value">{{ data.extCat1 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">分类扩展字段2：</span>
                <span class="value">{{ data.extCat2 || '-' }}</span>
              </div>
            </div>
            <div class="info-row">
              <div class="info-item">
                <span class="label">通用扩展字段1：</span>
                <span class="value">{{ data.extCommon1 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">通用扩展字段2：</span>
                <span class="value">{{ data.extCommon2 || '-' }}</span>
              </div>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref, defineProps, defineEmits, watch } from 'vue'
import { EarlyWarnDeviceVO } from '@/api/dataHub/warningAlarm/zoneWarnAlarm/earlywarndevice'

const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['update:visible'])

// 解决直接修改props的问题：使用内部状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
const data = ref<EarlyWarnDeviceVO>({
  id: 0,
  warnDeviceStatId: '',
  statCycle: '',
  deviceMajorId: '',
  deviceMajorName: '',
  deviceModelId: '',
  deviceModelName: '',
  totalWarnCount: 0,
  mainFaultType: '',
  deviceCount: 0,
  onlineRepairCount: 0,
  onlineRepairRate: 0,
  statUserId: '',
  statTime: new Date(),
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

const isFullscreen = ref(false)
const title = ref('预警告警详情')

// 同步外部visible状态到内部
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
    if (!newVal) {
      isFullscreen.value = false
    }
  }
)

// 同步内部状态到外部
watch(drawerVisible, (newVal) => {
  if (!newVal) {
    emit('update:visible', false)
  }
})

// 设置详情数据
const setData = (row: EarlyWarnDeviceVO) => {
  data.value = { ...row }
}

// 关闭抽屉
const handleClose = () => {
  drawerVisible.value = false
  isFullscreen.value = false
}

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}

defineExpose({ setData })
</script>

<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f5f7fa;
}

.detail-container {
  padding: 20px;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
  width: 100%;
}

.info-card {
  transition: all 0.3s ease;
  border-radius: 8px;
  border: none;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

/* 最后一个卡片占两列 */
.grid-layout > .info-card:last-child {
  grid-column: span 2;
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 16px;
  font-weight: 500;
  color: #4e5969;
  border-bottom: 1px solid #f0f2f5;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
}

.device-info-card .card-header {
  background-color: #fff7e6;
}

.warning-info-card .card-header {
  background-color: #fff2f0;
}

.repair-info-card .card-header {
  background-color: #f6ffed;
}

.expand-info-card .card-header {
  background-color: #f0f5ff;
}

.card-content {
  padding: 16px;
}

.info-item {
  margin-bottom: 14px;
  line-height: 1.6;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  display: inline-block;
  width: 110px;
  color: #6b7280;
}

.expand-content .info-row {
  display: flex;
  flex-wrap: wrap;
  gap: 14px 0;
}

.expand-content .info-item {
  flex: 1;
  min-width: 200px;
}

/* 移除v-deep，改用样式穿透的替代方案 */
.detail-drawer :deep(.el-drawer__body) {
  padding: 0 !important;
  overflow-y: auto;
}
</style>
