<template>
  <el-drawer
    v-model="drawerVisible"
    :size="isFullscreen ? '100%' : '75%'"
    @close="onClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">按管理部件告警预警详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基础信息卡片 -->
      <div class="grid-layout">
        <el-card class="info-card base-info-card">
          <div class="card-header">基础信息</div>
          <div class="info-item">
            <span class="info-label">主键：</span>
            <span class="info-value">{{ data.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">统计ID：</span>
            <span class="info-value primary">{{ data.warnMngCompStatId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">统计周期：</span>
            <span class="info-value">{{ data.statCycle || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">统计时间：</span>
            <span class="info-value">{{ formatDate(data.statTime) }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">统计人：</span>
            <span class="info-value">{{ data.statUserId || '-' }}</span>
          </div>
        </el-card>

        <!-- 部件信息卡片 -->
        <el-card class="info-card component-info-card">
          <div class="card-header">部件信息</div>
          <div class="info-item">
            <span class="info-label">部件大类ID：</span>
            <span class="info-value">{{ data.compMajorId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">部件大类名称：</span>
            <span class="info-value primary">{{ data.compMajorName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">部件小类ID：</span>
            <span class="info-value">{{ data.compMinorId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">部件小类名称：</span>
            <span class="info-value primary">{{ data.compMinorName || '-' }}</span>
          </div>
        </el-card>

        <!-- 预警信息卡片 -->
        <el-card class="info-card warning-info-card">
          <div class="card-header">预警信息</div>
          <div class="info-item">
            <span class="info-label">预警总数：</span>
            <span class="info-value warning">{{ data.totalWarnCount || 0 }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">主要故障类型：</span>
            <span class="info-value">{{ data.mainFaultType || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">已办结数：</span>
            <span class="info-value success">{{ data.completedCount || 0 }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">处置率：</span>
            <span class="info-value">{{ data.handleRate ? data.handleRate + '%' : '-' }}</span>
          </div>
        </el-card>

        <!-- 区域信息卡片 -->
        <el-card class="info-card region-info-card">
          <div class="card-header">区域信息</div>
          <div class="info-item">
            <span class="info-label">主要分布区域代码：</span>
            <span class="info-value">{{ data.mainRegionCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">主要分布区域名称：</span>
            <span class="info-value primary">{{ data.mainRegionName || '-' }}</span>
          </div>
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
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { EarlyWarnMngCompVO } from '@/api/dataHub/warningAlarm/zoneWarnAlarm/managePartWarnAlarm'

const props = defineProps<{
  visible: boolean
}>()

const emit = defineEmits<{
  (e: 'close'): void
}>()

const data = ref<EarlyWarnMngCompVO>({} as EarlyWarnMngCompVO)
const isFullscreen = ref(false)

// 设置详情数据
const setData = (row: EarlyWarnMngCompVO) => {
  data.value = { ...row }
}

// 关闭抽屉
const onClose = () => {
  emit('close')
  isFullscreen.value = false
}
// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)
// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleDateString() : '-'
}

defineExpose({ setData })
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
}

.detail-container {
  padding: 20px;
  height: calc(100% - 60px);
  overflow-y: auto;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.info-card {
  border-radius: 8px;
  overflow: hidden;
  transition: all 0.3s ease;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 15px;
  font-weight: 500;
  color: #4e5969;
  border-bottom: 1px solid #f0f2f5;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
}

.component-info-card .card-header {
  background-color: #fff2e8;
}

.warning-info-card .card-header {
  background-color: #fff7e6;
}

.region-info-card .card-header {
  background-color: #f6ffed;
}

::v-deep .el-card__body {
  padding: 15px;
}

.info-item {
  display: flex;
  padding: 8px 0;
  border-bottom: 1px dashed #f0f2f5;
}

.info-item:last-child {
  border-bottom: none;
}

.info-label {
  flex: 0 0 130px;
  color: #86909c;
  font-size: 14px;
}

.info-value {
  flex: 1;
  color: #1d2129;
  font-size: 14px;
}

.primary {
  color: #1890ff;
  font-weight: 500;
}

.success {
  color: #52c41a;
}

.warning {
  color: #faad14;
}

@media screen and (max-width: 1200px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }
}
</style>
