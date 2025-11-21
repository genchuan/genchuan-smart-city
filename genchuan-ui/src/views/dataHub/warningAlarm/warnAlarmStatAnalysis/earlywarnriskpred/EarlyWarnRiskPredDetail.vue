<template>
  <el-drawer
    v-model="drawerVisible"
    :size="isFullscreen ? '100%' : '75%'"
    class="detail-drawer"
    @close="handleClose"
  >
    <!-- 头部：按要求不修改样式 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">预警告警风险趋势预测详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <!-- 详情内容：浅灰背景+白色卡片，grid两列布局 -->
    <div class="detail-content">
      <div class="grid-layout">
        <!-- 基本信息卡片：马克龙浅蓝 -->
        <el-card class="info-card basic-card">
          <template #header>
            <div class="card-header basic-header">
              <span class="header-title">基本信息</span>
            </div>
          </template>
          <div class="card-body">
            <div class="info-item">
              <span class="label">主键：</span>
              <span class="value">{{ detailData?.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">预测ID：</span>
              <span class="value key-field">{{ detailData?.trendForecastId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">预测对象：</span>
              <span class="value key-field">{{ detailData?.forecastObject || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">预测对象值：</span>
              <span class="value">{{ detailData?.forecastObjectValue || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">预测时间：</span>
              <span class="value">{{ formatDate(detailData?.forecastTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 预测配置卡片：马克龙浅紫 -->
        <el-card class="info-card config-card">
          <template #header>
            <div class="card-header config-header">
              <span class="header-title">预测配置</span>
            </div>
          </template>
          <div class="card-body">
            <div class="info-item">
              <span class="label">历史数据周期：</span>
              <span class="value">{{ detailData?.historyDataCycle || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">历史数据范围：</span>
              <span class="value">{{ detailData?.historyDataRange || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">预测周期：</span>
              <span class="value">{{ detailData?.forecastCycle || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">预测时间范围：</span>
              <span class="value">{{ detailData?.forecastTimeRange || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 预测参数卡片：马克龙浅橙 -->
        <el-card class="info-card param-card">
          <template #header>
            <div class="card-header param-header">
              <span class="header-title">预测参数</span>
            </div>
          </template>
          <div class="card-body">
            <div class="info-item">
              <span class="label">预测指标：</span>
              <span class="value">{{ detailData?.forecastIndicators || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">预测模型：</span>
              <span class="value key-field">{{ detailData?.forecastModel || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">预测准确率：</span>
              <span
                class="value"
                :class="detailData?.forecastAccuracy > 85 ? 'status-success' : 'status-warning'"
              >
                {{
                  detailData?.forecastAccuracy ? `${detailData.forecastAccuracy.toFixed(2)}%` : '0%'
                }}
              </span>
            </div>
          </div>
        </el-card>

        <!-- 预测人信息卡片：马克龙浅绿 -->
        <el-card class="info-card creator-card">
          <template #header>
            <div class="card-header creator-header">
              <span class="header-title">预测人信息</span>
            </div>
          </template>
          <div class="card-body">
            <div class="info-item">
              <span class="label">预测人ID：</span>
              <span class="value key-field">{{ detailData?.forecastUserId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">预测人姓名：</span>
              <span class="value">{{ detailData?.forecastUserName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息卡片：马克龙浅灰，跨两列 -->
        <el-card class="info-card ext-card" :span="2">
          <template #header>
            <div class="card-header ext-header">
              <span class="header-title">扩展信息</span>
            </div>
          </template>
          <div class="card-body grid-ext">
            <div class="info-item">
              <span class="label">分类扩展字段1：</span>
              <span class="value">{{ detailData?.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类扩展字段2：</span>
              <span class="value">{{ detailData?.extCat2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段1：</span>
              <span class="value">{{ detailData?.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段2：</span>
              <span class="value">{{ detailData?.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { EarlyWarnRiskPredVO } from '@/api/dataHub/warningAlarm/warnAlarmStatAnalysis/earlywarnriskpred'

defineOptions({ name: 'EarlyWarnRiskPredDetail' })

// 接收父组件参数：规避prop变异错误，不直接修改props
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  detailData: {
    type: Object as () => EarlyWarnRiskPredVO | null,
    default: null
  }
})

// 事件发射
const emit = defineEmits(['close', 'update:visible'])

// 本地状态：控制抽屉显示，避免直接修改props
const drawerVisible = ref(props.visible)
const isFullscreen = ref(false)

// 监听props.visible变化，同步本地状态
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  },
  { immediate: true }
)

// 监听本地drawerVisible变化，通知父组件
watch(
  () => drawerVisible.value,
  (newVal) => {
    emit('update:visible', newVal)
    if (!newVal) {
      emit('close')
      isFullscreen.value = false // 关闭时退出全屏
    }
  }
)

// 关闭抽屉
const handleClose = () => {
  drawerVisible.value = false
}

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}
</script>

<style scoped>
/* 抽屉整体样式 */
.detail-drawer {
  --el-drawer-bg-color: #f5f7fa; /* 浅灰背景 */
}

/* 详情内容容器 */
.detail-content {
  padding: 20px;
  height: calc(100% - 60px);
  overflow-y: auto;
}

/* grid布局：两列，间距20px */
.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

/* 跨两列样式 */
.grid-layout > .info-card[span='2'] {
  grid-column: span 2;
}

/* 卡片样式：白色背景，hover浮起效果 */
.info-card {
  border: none;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
  overflow: hidden;
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

/* 卡片头部：马克龙色系背景 */
.card-header {
  padding: 12px 16px;
  border-bottom: 1px solid #f0f2f5;
}

.header-title {
  font-weight: 500;
  font-size: 14px;
}

/* 不同卡片头部配色 */
.basic-header {
  background-color: #e6f7ff;
}

.basic-header .header-title {
  color: #1890ff;
}

.config-header {
  background-color: #f0f2ff;
}

.config-header .header-title {
  color: #722ed1;
}

.param-header {
  background-color: #fff7e6;
}

.param-header .header-title {
  color: #fa8c16;
}

.creator-header {
  background-color: #f6ffed;
}

.creator-header .header-title {
  color: #52c41a;
}

.ext-header {
  background-color: #f5f5f5;
}

.ext-header .header-title {
  color: #666;
}

/* 卡片内容区域 */
.card-body {
  padding: 16px;
}

/* 信息项布局 */
.info-item {
  display: flex;
  align-items: flex-start;
  margin-bottom: 12px;
  line-height: 1.6;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  width: 110px;
  color: #6b7280;
  flex-shrink: 0;
  font-size: 13px;
  padding-top: 2px;
}

.value {
  color: #1f2937;
  font-size: 13px;
  word-break: break-all;
}

/* 重点字段：蓝色加粗 */
.key-field {
  color: #2563eb;
  font-weight: 500;
}

/* 状态字段：绿/橙标识 */
.status-success {
  color: #059669;
  font-weight: 500;
}

.status-warning {
  color: #fa8c16;
  font-weight: 500;
}

/* 扩展信息grid布局 */
.grid-ext {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
}

/* 响应式适配：小屏幕单列 */
@media (max-width: 1200px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }

  .grid-ext {
    grid-template-columns: 1fr;
  }
}
</style>
