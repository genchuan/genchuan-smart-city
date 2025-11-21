<template>
  <el-drawer
    v-model="drawerVisible"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">趋势分析报表详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <el-row :gutter="20">
        <!-- 基本信息卡片 -->
        <el-col :span="12">
          <el-card class="info-card basic-info-card">
            <div class="card-header">
              <span class="card-title">基本信息</span>
            </div>
            <div class="card-content">
              <div class="info-item">
                <span class="info-label">主键：</span>
                <span class="info-value">{{ data.id || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">报表ID：</span>
                <span class="info-value">{{ data.rptId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">分析维度类型：</span>
                <span class="info-value">{{ data.trendDimType || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">分析维度ID：</span>
                <span class="info-value">{{ data.trendDimId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">分析维度名称：</span>
                <span class="info-value font-semibold text-blue-500">{{
                  data.trendDimName || '-'
                }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">时间粒度：</span>
                <span class="info-value">{{ data.timeGranularity || '-' }}</span>
              </div>
            </div>
          </el-card>
        </el-col>

        <!-- 时间信息卡片 -->
        <el-col :span="12">
          <el-card class="info-card time-info-card">
            <div class="card-header">
              <span class="card-title">时间信息</span>
            </div>
            <div class="card-content">
              <div class="info-item">
                <span class="info-label">起始时间：</span>
                <span class="info-value">{{ formatDate(data.startTime) }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">结束时间：</span>
                <span class="info-value">{{ formatDate(data.endTime) }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">报表生成时间：</span>
                <span class="info-value">{{ formatDate(data.rptCreateTime) }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">报表生成人：</span>
                <span class="info-value">{{ data.rptCreateUser || '-' }}</span>
              </div>
            </div>
          </el-card>
        </el-col>

        <!-- 周期数据卡片 -->
        <el-col :span="12">
          <el-card class="info-card cycle-info-card">
            <div class="card-header">
              <span class="card-title">周期数据</span>
            </div>
            <div class="card-content">
              <div class="info-item">
                <span class="info-label">周期1标识：</span>
                <span class="info-value">{{ data.cycle1Label || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">周期1数值：</span>
                <span class="info-value font-semibold text-blue-500">{{
                  data.cycle1Value || '-'
                }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">周期2标识：</span>
                <span class="info-value">{{ data.cycle2Label || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">周期2数值：</span>
                <span class="info-value font-semibold text-blue-500">{{
                  data.cycle2Value || '-'
                }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">整体变化率：</span>
                <span class="info-value">
                  <span :class="data.overallChangeRate > 0 ? 'text-green-500' : 'text-red-500'">
                    {{ data.overallChangeRate || '-' }}
                  </span>
                </span>
              </div>
            </div>
          </el-card>
        </el-col>

        <!-- 趋势信息卡片 -->
        <el-col :span="12">
          <el-card class="info-card trend-info-card">
            <div class="card-header">
              <span class="card-title">趋势信息</span>
            </div>
            <div class="card-content">
              <div class="info-item">
                <span class="info-label">趋势类型：</span>
                <span class="info-value">
                  <span :class="getTrendTypeClass(data.trendType)">
                    {{ data.trendType || '-' }}
                  </span>
                </span>
              </div>
              <div class="info-item">
                <span class="info-label">报表备注：</span>
                <span class="info-value">{{ data.rptRemark || '-' }}</span>
              </div>
            </div>
          </el-card>
        </el-col>

        <!-- 扩展信息卡片 -->
        <el-col :span="24">
          <el-card class="info-card extension-info-card">
            <div class="card-header">
              <span class="card-title">扩展信息</span>
            </div>
            <div class="card-content">
              <div class="info-grid">
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
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { TrendAnalysisRptVO } from '@/api/dataHub/operationMonitor/compStatAnalysisReport/trendanalysisrpt'

const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['close'])

const data = ref<TrendAnalysisRptVO>({
  id: 0,
  rptId: '',
  trendDimType: '',
  trendDimId: '',
  trendDimName: '',
  timeGranularity: '',
  startTime: new Date(),
  endTime: new Date(),
  cycle1Label: '',
  cycle1Value: 0,
  cycle2Label: '',
  cycle2Value: 0,
  overallChangeRate: 0,
  trendType: '',
  rptCreateTime: new Date(),
  rptCreateUser: 0,
  rptRemark: '',
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

const isFullscreen = ref(false)

// 设置详情数据
const setData = (row: TrendAnalysisRptVO) => {
  data.value = { ...row }
}

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}

// 根据趋势类型获取样式类
const getTrendTypeClass = (type: string) => {
  switch (type) {
    case '上升':
      return 'text-green-500 font-semibold'
    case '下降':
      return 'text-red-500 font-semibold'
    default:
      return ''
  }
}

// 关闭抽屉
const handleClose = () => {
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
defineExpose({ setData })
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
}

.detail-container {
  padding: 20px;
}

.el-row {
  margin-bottom: 20px;
}

.info-card {
  transition: all 0.3s ease;
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.12);
}

.card-header {
  padding: 12px 15px;
  border-bottom: 1px solid #f0f2f5;
  background-color: #f9fafb;
  border-radius: 8px 8px 0 0;
}

.card-title {
  font-weight: 500;
  font-size: 14px;
}

.basic-info-card .card-header {
  background-color: #e6f7ff;
}

.basic-info-card .card-title {
  color: #1890ff;
}

.time-info-card .card-header {
  background-color: #fff7e6;
}

.time-info-card .card-title {
  color: #fa8c16;
}

.cycle-info-card .card-header {
  background-color: #f6ffed;
}

.cycle-info-card .card-title {
  color: #52c41a;
}

.trend-info-card .card-header {
  background-color: #fff0f0;
}

.trend-info-card .card-title {
  color: #f5222d;
}

.extension-info-card .card-header {
  background-color: #f0f5ff;
}

.extension-info-card .card-title {
  color: #40a9ff;
}

.card-content {
  padding: 15px;
}

.info-item {
  display: flex;
  margin-bottom: 12px;
  line-height: 1.5;
}

.info-item:last-child {
  margin-bottom: 0;
}

.info-label {
  flex: 0 0 100px;
  color: #6b7280;
  font-size: 13px;
}

.info-value {
  flex: 1;
  color: #374151;
  word-break: break-all;
}

.info-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 12px;
}

::v-deep .el-drawer__body {
  padding: 0;
  overflow-y: auto;
}
</style>
