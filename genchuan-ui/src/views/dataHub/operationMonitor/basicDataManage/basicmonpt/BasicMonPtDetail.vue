<template>
  <el-drawer
    v-model="visible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">基础监测点位详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基础信息卡片 -->
      <el-card class="info-card base-info-card">
        <div class="card-header">基础信息</div>
        <div class="card-content grid grid-cols-2 gap-6">
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value font-medium text-blue-600">{{ detailData.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">点位ID：</span>
            <span class="value">{{ detailData.ptId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">点位名称：</span>
            <span class="value font-medium text-blue-600">{{ detailData.ptName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">点位编码：</span>
            <span class="value">{{ detailData.ptCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">点位类型：</span>
            <span class="value">{{ detailData.ptType || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">点位状态：</span>
            <span class="value">
              <span :class="detailData.ptStatus ? 'text-green-500' : 'text-red-500'">
                {{ detailData.ptStatus ? '正常' : '异常' }}
              </span>
            </span>
          </div>
        </div>
      </el-card>

      <!-- 区域与设备信息卡片 -->
      <el-card class="info-card area-device-card">
        <div class="card-header">区域与设备信息</div>
        <div class="card-content grid grid-cols-2 gap-6">
          <div class="info-item">
            <span class="label">所属区域代码：</span>
            <span class="value">{{ detailData.areaCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">所属区域名称：</span>
            <span class="value">{{ detailData.areaName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联设备编码：</span>
            <span class="value">{{ detailData.deviceCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联设备名称：</span>
            <span class="value">{{ detailData.deviceName || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 坐标信息卡片 -->
      <el-card class="info-card coordinate-card">
        <div class="card-header">坐标信息</div>
        <div class="card-content grid grid-cols-2 gap-6">
          <div class="info-item">
            <span class="label">点位坐标X(度)：</span>
            <span class="value">{{ detailData.ptCoordX || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">点位坐标Y(度)：</span>
            <span class="value">{{ detailData.ptCoordY || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 负责人信息卡片 -->
      <el-card class="info-card manager-card">
        <div class="card-header">负责人信息</div>
        <div class="card-content grid grid-cols-2 gap-6">
          <div class="info-item">
            <span class="label">负责人ID：</span>
            <span class="value">{{ detailData.mngrId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">负责人姓名：</span>
            <span class="value">{{ detailData.mngrName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">负责人联系方式：</span>
            <span class="value">{{ detailData.mngrPhone || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 时间信息卡片 -->
      <el-card class="info-card time-card">
        <div class="card-header">时间信息</div>
        <div class="card-content grid grid-cols-2 gap-6">
          <div class="info-item">
            <span class="label">创建时间：</span>
            <span class="value">{{ formatDate(detailData.createTimeSys) }}</span>
          </div>
          <div class="info-item">
            <span class="label">更新时间：</span>
            <span class="value">{{ formatDate(detailData.updateTimeSys) }}</span>
          </div>
        </div>
      </el-card>

      <!-- 扩展信息卡片 -->
      <el-card class="info-card extend-card">
        <div class="card-header">扩展信息</div>
        <div class="card-content grid grid-cols-2 gap-6">
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
import { BasicMonPtVO } from '@/api/dataHub/operationMonitor/basicDataManage/basicmonpt'

defineOptions({ name: 'BasicMonPtDetail' })

const visible = ref(false)
const title = ref('点位详情')
const isFullscreen = ref(false)
const detailData = ref<BasicMonPtVO>({} as BasicMonPtVO)

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}

// 打开抽屉
const open = (data: BasicMonPtVO) => {
  detailData.value = { ...data }
  visible.value = true
  isFullscreen.value = false
}

defineExpose({ open })
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
  padding: 20px 0;
}

.drawer-header {
  padding: 0 20px 15px;
  border-bottom: 1px solid #e5e7eb;
  margin-bottom: 20px;
}

.detail-container {
  padding: 0 20px;
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
}

@media (max-width: 1200px) {
  .detail-container {
    grid-template-columns: 1fr;
  }
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
  padding: 12px 16px;
  font-weight: 500;
  color: #374151;
  font-size: 14px;
}

.card-content {
  padding: 16px;
}

.info-item {
  margin-bottom: 12px;
  font-size: 13px;
}

.label {
  color: #64748b;
  display: inline-block;
  width: 120px;
}

.value {
  color: #334155;
}

/* 不同卡片标题背景色（马克龙色系） */
.base-info-card .card-header {
  background-color: #e0f2fe;
}

.area-device-card .card-header {
  background-color: #e0f7fa;
}

.coordinate-card .card-header {
  background-color: #e8f5e9;
}

.manager-card .card-header {
  background-color: #fff3e0;
}

.time-card .card-header {
  background-color: #f3e5f5;
}

.extend-card .card-header {
  background-color: #ede7f6;
}
</style>
