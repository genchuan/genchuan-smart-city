<template>
  <el-drawer
    v-model="visible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    direction="rtl"
    :before-close="handleClose"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">设备运行状态统计详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <div class="grid-layout">
        <!-- 基本信息卡片 -->
        <el-card class="info-card base-info-card">
          <div class="card-header">基本信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">主键ID：</span>
              <span class="value">{{ detailData.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计ID：</span>
              <span class="value">{{ detailData.operStatId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计周期：</span>
              <span class="value font-medium text-blue-600">{{ detailData.statCycle || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计周期名称：</span>
              <span class="value">{{ detailData.statCycleName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计时间：</span>
              <span class="value">{{ formatDate(detailData.statTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计人：</span>
              <span class="value">{{ detailData.statUser || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 区域与设备信息卡片 -->
        <el-card class="info-card region-device-card">
          <div class="card-header">区域与设备信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">行政区划代码：</span>
              <span class="value">{{ detailData.regionCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">行政区划名称：</span>
              <span class="value font-medium text-blue-600">{{
                detailData.regionName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">设备大类ID：</span>
              <span class="value">{{ detailData.deviceMajorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">设备大类名称：</span>
              <span class="value font-medium text-blue-600">{{
                detailData.deviceMajorName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">设备总数量(台)：</span>
              <span class="value">{{ detailData.totalDeviceCount || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 在线状态信息卡片 -->
        <el-card class="info-card online-status-card">
          <div class="card-header">在线状态信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">平均在线设备数(台)：</span>
              <span class="value">{{ detailData.avgOnlineCount || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">设备在线率(%)：</span>
              <span class="value">
                <span
                  :class="detailData.deviceOnlineRate >= 90 ? 'text-green-600' : 'text-orange-500'"
                >
                  {{ detailData.deviceOnlineRate || '-' }}
                </span>
              </span>
            </div>
            <div class="info-item">
              <span class="label">总离线时长(分钟)：</span>
              <span class="value">{{ detailData.totalOfflineEndure || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">平均离线时长(分钟)：</span>
              <span class="value">{{ detailData.avgOfflineEndure || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 预警信息卡片 -->
        <el-card class="info-card alert-info-card">
          <div class="card-header">预警信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">一般预警数(条)：</span>
              <span class="value">{{ detailData.generalAlertCount || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">重要预警数(条)：</span>
              <span class="value text-orange-500">{{ detailData.importantAlertCount || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">紧急预警数(条)：</span>
              <span class="value text-red-500">{{ detailData.urgentAlertCount || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">已处理预警数(条)：</span>
              <span class="value text-green-600">{{ detailData.handledAlertCount || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">预警处置率(%)：</span>
              <span class="value">
                <span
                  :class="detailData.alertHandleRate >= 90 ? 'text-green-600' : 'text-orange-500'"
                >
                  {{ detailData.alertHandleRate || '-' }}
                </span>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 其他信息卡片 -->
        <el-card class="info-card other-info-card" :span="2">
          <div class="card-header">其他信息</div>
          <div class="card-content">
            <div class="info-item">
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
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DeviceOperRptVO } from '@/api/dataHub/deviceManage/deviceStatisticsReport/deviceoperrpt'

defineOptions({ name: 'DeviceOperRptDetail' })

const visible = ref(false)
const title = ref('设备运行状态统计详情')
const isFullscreen = ref(false)
const detailData = ref<DeviceOperRptVO>({
  id: 0,
  operStatId: '',
  statCycle: '',
  statCycleName: '',
  regionCode: '',
  regionName: '',
  deviceMajorId: '',
  deviceMajorName: '',
  totalDeviceCount: 0,
  avgOnlineCount: 0,
  deviceOnlineRate: 0,
  totalOfflineEndure: 0,
  avgOfflineEndure: 0,
  generalAlertCount: 0,
  importantAlertCount: 0,
  urgentAlertCount: 0,
  handledAlertCount: 0,
  alertHandleRate: 0,
  statTime: new Date(),
  statUser: '',
  rptRemark: '',
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleString() : '-')

// 打开抽屉
const open = (data: DeviceOperRptVO) => {
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
.detail-container {
  padding: 20px;
  background-color: #f5f7fa;
  height: calc(100% - 55px);
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
  overflow: hidden;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

.info-card[span='2'] {
  grid-column: span 2;
}

.card-header {
  padding: 12px 16px;
  font-weight: 500;
  color: #4b5563;
  border-bottom: 1px solid #f0f2f5;
}

.base-info-card .card-header {
  background-color: #e6f4ff;
}

.region-device-card .card-header {
  background-color: #fff7e6;
}

.online-status-card .card-header {
  background-color: #e6ffe6;
}

.alert-info-card .card-header {
  background-color: #ffe6e6;
}

.other-info-card .card-header {
  background-color: #f5e6ff;
}

.card-content {
  padding: 16px;
}

.info-item {
  display: flex;
  margin-bottom: 12px;
  line-height: 1.5;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  flex: 0 0 120px;
  color: #6b7280;
  font-size: 14px;
}

.value {
  flex: 1;
  color: #1f2937;
  font-size: 14px;
  word-break: break-all;
}

::v-deep .el-drawer__body {
  padding: 0;
  overflow: hidden;
}
</style>
