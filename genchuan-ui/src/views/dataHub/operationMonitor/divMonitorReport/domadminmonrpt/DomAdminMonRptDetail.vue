<template>
  <el-drawer
    v-model="visible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">行政区划分域报表详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基础信息 -->
      <el-card class="info-card base-info-card">
        <div class="card-header">
          <span class="card-title">基础信息</span>
        </div>
        <div class="card-content grid grid-cols-2 gap-6">
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value font-medium text-blue-600">{{ detailData.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">报表ID：</span>
            <span class="value">{{ detailData.rptId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计周期：</span>
            <span class="value">{{ detailData.statCycle || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计周期名称：</span>
            <span class="value">{{ detailData.statCycleName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">报表生成时间：</span>
            <span class="value">{{ formatDate(detailData.rptCreateTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">报表生成人：</span>
            <span class="value">{{ detailData.rptCreateUser || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 行政区划信息 -->
      <el-card class="info-card admin-info-card">
        <div class="card-header">
          <span class="card-title">行政区划信息</span>
        </div>
        <div class="card-content grid grid-cols-2 gap-6">
          <div class="info-item">
            <span class="label">行政区划代码：</span>
            <span class="value font-medium text-blue-600">{{ detailData.adminCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">行政区划名称：</span>
            <span class="value">{{ detailData.adminName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">行政区划层级：</span>
            <span class="value">{{ detailData.adminLevel || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 分域信息 -->
      <el-card class="info-card dom-info-card">
        <div class="card-header">
          <span class="card-title">分域信息</span>
        </div>
        <div class="card-content grid grid-cols-2 gap-6">
          <div class="info-item">
            <span class="label">分域ID：</span>
            <span class="value font-medium text-blue-600">{{ detailData.domId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分域名称：</span>
            <span class="value">{{ detailData.domName || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 监测数据 -->
      <el-card class="info-card monitor-info-card">
        <div class="card-header">
          <span class="card-title">监测数据</span>
        </div>
        <div class="card-content grid grid-cols-2 gap-6">
          <div class="info-item">
            <span class="label">监测点位数量：</span>
            <span class="value">{{ detailData.monPtCount || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备总数：</span>
            <span class="value">{{ detailData.deviceTotalCount || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备在线率：</span>
            <span
              class="value"
              :class="detailData.deviceOnlineRate > 90 ? 'text-green-500' : 'text-orange-500'"
            >
              {{ detailData.deviceOnlineRate ? detailData.deviceOnlineRate + '%' : '-' }}
            </span>
          </div>
          <div class="info-item">
            <span class="label">事件总数：</span>
            <span class="value">{{ detailData.evtTotalCount || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">事件办结率：</span>
            <span
              class="value"
              :class="detailData.evtCompleteRate > 90 ? 'text-green-500' : 'text-orange-500'"
            >
              {{ detailData.evtCompleteRate ? detailData.evtCompleteRate + '%' : '-' }}
            </span>
          </div>
        </div>
      </el-card>

      <!-- 其他信息 -->
      <el-card class="info-card other-info-card">
        <div class="card-header">
          <span class="card-title">其他信息</span>
        </div>
        <div class="card-content grid grid-cols-2 gap-6">
          <div class="info-item full-width">
            <span class="label">报表备注：</span>
            <span class="value">{{ detailData.rptRemark || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分类扩展字段1：</span>
            <span class="value">{{ detailData.extCat1 || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分类扩展字段2：</span>
            <span class="value">{{ detailData.extCat2 || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">通用扩展字段1：</span>
            <span class="value">{{ detailData.extCommon1 || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">通用扩展字段2：</span>
            <span class="value">{{ detailData.extCommon2 || '-' }}</span>
          </div>
        </div>
      </el-card>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DomAdminMonRptVO } from '@/api/dataHub/operationMonitor/divMonitorReport/domadminmonrpt'

defineOptions({ name: 'DomAdminMonRptDetail' })

const visible = ref(false)
const title = ref('报表详情')
const isFullscreen = ref(false)
const detailData = ref<DomAdminMonRptVO>({} as DomAdminMonRptVO)

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 打开抽屉
const open = (data: DomAdminMonRptVO) => {
  detailData.value = { ...data }
  visible.value = true
  isFullscreen.value = false
}

// 关闭抽屉
const handleClose = () => {
  visible.value = false
}

defineExpose({ open })
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
}

.detail-container {
  padding: 15px;
  display: flex;
  flex-direction: column;
  gap: 15px;
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
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 15px;
  border-bottom: 1px solid #f0f0f0;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
}

.admin-info-card .card-header {
  background-color: #fff2e8;
}

.dom-info-card .card-header {
  background-color: #f6ffed;
}

.monitor-info-card .card-header {
  background-color: #fff0f6;
}

.other-info-card .card-header {
  background-color: #f0f2ff;
}

.card-title {
  font-weight: 500;
  font-size: 14px;
}

.base-info-card .card-title {
  color: #1890ff;
}

.admin-info-card .card-title {
  color: #fa8c16;
}

.dom-info-card .card-title {
  color: #52c41a;
}

.monitor-info-card .card-title {
  color: #eb2f96;
}

.other-info-card .card-title {
  color: #722ed1;
}

.card-content {
  padding: 15px;
}

.info-item {
  display: flex;
  padding: 6px 0;
  word-break: break-all;
}

.full-width {
  grid-column: span 2;
}

.label {
  color: #666;
  min-width: 120px;
  flex-shrink: 0;
}

.value {
  color: #333;
  flex-grow: 1;
}

.text-green-500 {
  color: #52c41a !important;
  font-weight: 500;
}

.text-orange-500 {
  color: #fa8c16 !important;
  font-weight: 500;
}

::v-deep .el-drawer__body {
  padding: 0;
  overflow-y: auto;
}
</style>
