<template>
  <el-drawer v-model="visible" :size="isFullscreen ? '100%' : '75%'" class="detail-drawer">
    <!-- 抽屉头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">设备分域监测报表详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="detail-container">
      <!-- 详情内容 -->
      <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
        <!-- 基本信息卡片 -->
        <el-card class="custom-card">
          <div class="card-header bg-blue-50">
            <span class="text-blue-600 font-medium">基本信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">主键：</span>
              <span class="value">{{ detailData.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">报表ID：</span>
              <span class="value font-medium text-blue-600">{{ detailData.rptId || '-' }}</span>
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

        <!-- 区域信息卡片 -->
        <el-card class="custom-card">
          <div class="card-header bg-purple-50">
            <span class="text-purple-600 font-medium">区域信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">行政区划代码：</span>
              <span class="value">{{ detailData.adminCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">行政区划名称：</span>
              <span class="value font-medium text-purple-600">{{
                detailData.adminName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">分域ID：</span>
              <span class="value">{{ detailData.domId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分域名称：</span>
              <span class="value font-medium text-purple-600">{{ detailData.domName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 设备信息卡片 -->
        <el-card class="custom-card">
          <div class="card-header bg-green-50">
            <span class="text-green-600 font-medium">设备信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">设备大类ID：</span>
              <span class="value">{{ detailData.deviceMajorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">设备大类名称：</span>
              <span class="value font-medium text-green-600">{{
                detailData.deviceMajorName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">设备小类ID：</span>
              <span class="value">{{ detailData.deviceMinorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">设备小类名称：</span>
              <span class="value font-medium text-green-600">{{
                detailData.deviceMinorName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">设备总数：</span>
              <span class="value">{{ detailData.totalDeviceCount || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 设备状态卡片 -->
        <el-card class="custom-card">
          <div class="card-header bg-orange-50">
            <span class="text-orange-600 font-medium">设备状态</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">设备在线率：</span>
              <span
                :class="detailData.deviceOnlineRate >= 90 ? 'text-green-600' : 'text-orange-600'"
              >
                {{ detailData.deviceOnlineRate ? detailData.deviceOnlineRate + '%' : '-' }}
              </span>
            </div>
            <div class="info-item">
              <span class="label">数据上传总频次：</span>
              <span class="value">{{ detailData.totalUploadFreq || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">数据上传达标率：</span>
              <span
                :class="detailData.uploadQualifiedRate >= 90 ? 'text-green-600' : 'text-orange-600'"
              >
                {{ detailData.uploadQualifiedRate ? detailData.uploadQualifiedRate + '%' : '-' }}
              </span>
            </div>
            <div class="info-item">
              <span class="label">设备异常次数：</span>
              <span
                :class="
                  detailData.deviceAbnCount && detailData.deviceAbnCount > 0
                    ? 'text-red-500'
                    : 'text-green-600'
                "
              >
                {{ detailData.deviceAbnCount || 0 }}
              </span>
            </div>
          </div>
        </el-card>

        <!-- 固件信息卡片 -->
        <el-card class="custom-card">
          <div class="card-header bg-teal-50">
            <span class="text-teal-600 font-medium">固件信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">固件更新设备数：</span>
              <span class="value">{{ detailData.firmwareUpdatedCount || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">固件更新率：</span>
              <span
                :class="detailData.firmwareUpdateRate >= 90 ? 'text-green-600' : 'text-orange-600'"
              >
                {{ detailData.firmwareUpdateRate ? detailData.firmwareUpdateRate + '%' : '-' }}
              </span>
            </div>
          </div>
        </el-card>

        <!-- 其他信息卡片 -->
        <el-card class="custom-card">
          <div class="card-header bg-gray-50">
            <span class="text-gray-600 font-medium">其他信息</span>
          </div>
          <div class="card-body">
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
import { DomDeviceMonRptVO } from '@/api/dataHub/operationMonitor/divMonitorReport/domdevicemonrpt'

const visible = ref(false)
const isFullscreen = ref(false)
const detailData = ref<DomDeviceMonRptVO>({} as DomDeviceMonRptVO)

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 打开抽屉
const open = (data: DomDeviceMonRptVO) => {
  detailData.value = { ...data }
  visible.value = true
  isFullscreen.value = false
}

defineExpose({ open })
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
}

.detail-container {
  padding: 20px;
  height: 100%;
  overflow-y: auto;
}

.custom-card {
  transition: all 0.3s ease;
  border-radius: 8px;
  overflow: hidden;
}

.custom-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 16px;
  font-size: 14px;
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
  font-weight: 500;
}

.value {
  flex: 1;
  color: #333;
  word-break: break-all;
}

/* 马克龙色系 */
.bg-blue-50 {
  background-color: #e6f4ff;
}

.text-blue-600 {
  color: #1677ff;
}

.bg-purple-50 {
  background-color: #f9f0ff;
}

.text-purple-600 {
  color: #722ed1;
}

.bg-green-50 {
  background-color: #f0fff4;
}

.text-green-600 {
  color: #00b42a;
}

.bg-orange-50 {
  background-color: #fff7e6;
}

.text-orange-600 {
  color: #ff7d00;
}

.bg-teal-50 {
  background-color: #e6fffa;
}

.text-teal-600 {
  color: #00b8d9;
}

.bg-gray-50 {
  background-color: #f2f3f5;
}

.text-gray-600 {
  color: #86909c;
}

.text-red-500 {
  color: #f53f3f;
}
</style>
