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
        <span class="text-lg font-bold text-gray-800">设备详情信息</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <el-row :gutter="20">
        <!-- 基本信息 -->
        <el-col :span="12">
          <el-card class="info-card basic-info-card">
            <div class="card-header">基本信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="info-label">设备ID：</span>
                <span class="info-value">{{ data.deviceId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">设备编码：</span>
                <span class="info-value font-bold text-blue-500">{{ data.deviceCode || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">设备名称：</span>
                <span class="info-value font-bold text-blue-500">{{ data.deviceName || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">设备类型：</span>
                <span class="info-value">{{ data.deviceType || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">设备状态：</span>
                <span
                  class="info-value"
                  :class="data.deviceStatus === '正常' ? 'status-normal' : 'status-abnormal'"
                >
                  {{ data.deviceStatus }}
                </span>
              </div>
            </div>
          </el-card>
        </el-col>

        <!-- 位置信息 -->
        <el-col :span="12">
          <el-card class="info-card location-info-card">
            <div class="card-header">位置信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="info-label">所属区域代码：</span>
                <span class="info-value">{{ data.areaCode || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">所属区域名称：</span>
                <span class="info-value">{{ data.areaName || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">所属网格ID：</span>
                <span class="info-value">{{ data.gridId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">所属网格名称：</span>
                <span class="info-value">{{ data.gridName || '-' }}</span>
              </div>
            </div>
          </el-card>
        </el-col>

        <!-- 人员信息 -->
        <el-col :span="12">
          <el-card class="info-card person-info-card">
            <div class="card-header">关联人员</div>
            <div class="card-body">
              <div class="info-item">
                <span class="info-label">关联人员ID：</span>
                <span class="info-value">{{ data.personId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">关联人员姓名：</span>
                <span class="info-value">{{ data.personName || '-' }}</span>
              </div>
            </div>
          </el-card>
        </el-col>

        <!-- 时间信息 -->
        <el-col :span="12">
          <el-card class="info-card time-info-card">
            <div class="card-header">时间信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="info-label">安装时间：</span>
                <span class="info-value">{{ formatDate(data.installTime) }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">保修截止时间：</span>
                <span class="info-value">{{ formatDate(data.warrantyEndTime) }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">创建时间：</span>
                <span class="info-value">{{ formatDate(data.bizCreateTime) }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">更新时间：</span>
                <span class="info-value">{{ formatDate(data.bizUpdateTime) }}</span>
              </div>
            </div>
          </el-card>
        </el-col>

        <!-- 扩展信息 -->
        <el-col :span="24">
          <el-card class="info-card ext-info-card">
            <div class="card-header">扩展信息</div>
            <div class="card-body">
              <el-row :gutter="20">
                <el-col :span="12">
                  <div class="info-item">
                    <span class="info-label">分类扩展字段1：</span>
                    <span class="info-value">{{ data.extCat1 || '-' }}</span>
                  </div>
                </el-col>
                <el-col :span="12">
                  <div class="info-item">
                    <span class="info-label">分类扩展字段2：</span>
                    <span class="info-value">{{ data.extCat2 || '-' }}</span>
                  </div>
                </el-col>
                <el-col :span="12">
                  <div class="info-item">
                    <span class="info-label">通用扩展字段1：</span>
                    <span class="info-value">{{ data.extCommon1 || '-' }}</span>
                  </div>
                </el-col>
                <el-col :span="12">
                  <div class="info-item">
                    <span class="info-label">通用扩展字段2：</span>
                    <span class="info-value">{{ data.extCommon2 || '-' }}</span>
                  </div>
                </el-col>
                <el-col :span="12">
                  <div class="info-item">
                    <span class="info-label">创建人：</span>
                    <span class="info-value">{{ data.createUser || '-' }}</span>
                  </div>
                </el-col>
                <el-col :span="12">
                  <div class="info-item">
                    <span class="info-label">更新人：</span>
                    <span class="info-value">{{ data.updateUser || '-' }}</span>
                  </div>
                </el-col>
              </el-row>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { InspectDeviceInfoVO } from '@/api/dataHub/supervisionInspection/patrolResource/deviceManage'

defineOptions({ name: 'InspectDeviceInfoDetail' })

const visible = ref(false)
const title = ref('设备详情')
const isFullscreen = ref(false)
const data = ref<InspectDeviceInfoVO>({} as InspectDeviceInfoVO)

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}

// 打开抽屉
const open = (row: InspectDeviceInfoVO) => {
  data.value = { ...row }
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
  --el-drawer-bg-color: #f5f7fa;
}

.detail-container {
  padding: 20px;
}

.info-card {
  margin-bottom: 20px;
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
  padding: 12px 16px;
  font-weight: 500;
  color: #4e5969;
  font-size: 14px;
}

.basic-info-card .card-header {
  background-color: #e6f7ff;
  border-left: 4px solid #1890ff;
}

.location-info-card .card-header {
  background-color: #fff7e6;
  border-left: 4px solid #faad14;
}

.person-info-card .card-header {
  background-color: #f6ffed;
  border-left: 4px solid #52c41a;
}

.time-info-card .card-header {
  background-color: #fff0f0;
  border-left: 4px solid #f5222d;
}

.ext-info-card .card-header {
  background-color: #f0f2ff;
  border-left: 4px solid #722ed1;
}

.card-body {
  padding: 16px;
}

.info-item {
  margin-bottom: 12px;
  line-height: 1.8;
}

.info-item:last-child {
  margin-bottom: 0;
}

.info-label {
  display: inline-block;
  width: 120px;
  color: #6b7280;
  font-size: 13px;
}

.info-value {
  color: #1f2937;
  font-size: 13px;
}

.status-normal {
  color: #52c41a;
  font-weight: 500;
}

.status-abnormal {
  color: #f5222d;
  font-weight: 500;
}

.text-blue-500 {
  color: #3b82f6;
}
</style>
