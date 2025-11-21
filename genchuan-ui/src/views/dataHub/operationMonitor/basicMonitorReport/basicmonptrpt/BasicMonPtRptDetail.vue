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
        <span class="text-lg font-bold text-gray-800">基础监测点位报表详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基础信息 -->
      <el-card class="info-card" hover-shadow>
        <template #header>
          <div class="card-header bg-blue-50">
            <span class="text-blue-600 font-medium">基础信息</span>
          </div>
        </template>
        <div class="grid grid-cols-2 gap-6 mt-4">
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value font-medium">{{ formData.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">报表ID：</span>
            <span class="value font-medium text-blue-600">{{ formData.rptId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计周期：</span>
            <span class="value font-medium">{{ formData.statCycle || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计周期名称：</span>
            <span class="value font-medium">{{ formData.statCycleName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">行政区划代码：</span>
            <span class="value">{{ formData.adminCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">行政区划名称：</span>
            <span class="value font-medium">{{ formData.adminName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">点位类型：</span>
            <span class="value">{{ formData.ptType || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">报表生成时间：</span>
            <span class="value">{{ formatDate(formData.rptCreateTime) }}</span>
          </div>
        </div>
      </el-card>

      <!-- 点位统计信息 -->
      <el-card class="info-card" hover-shadow>
        <template #header>
          <div class="card-header bg-green-50">
            <span class="text-green-600 font-medium">点位统计信息</span>
          </div>
        </template>
        <div class="grid grid-cols-2 gap-6 mt-4">
          <div class="info-item">
            <span class="label">点位总数(个)：</span>
            <span class="value font-medium text-blue-600">{{ formData.totalPtCount || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">启用点位数量(个)：</span>
            <span class="value font-medium text-green-600">{{
              formData.enablePtCount || '-'
            }}</span>
          </div>
          <div class="info-item">
            <span class="label">停用点位数量(个)：</span>
            <span class="value font-medium text-red-600">{{ formData.disablePtCount || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">新增点位数量(个)：</span>
            <span class="value font-medium">{{ formData.newPtCount || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联设备总数(个)：</span>
            <span class="value font-medium">{{ formData.relDeviceCount || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 其他信息 -->
      <el-card class="info-card" hover-shadow>
        <template #header>
          <div class="card-header bg-purple-50">
            <span class="text-purple-600 font-medium">其他信息</span>
          </div>
        </template>
        <div class="grid grid-cols-2 gap-6 mt-4">
          <div class="info-item">
            <span class="label">报表生成人：</span>
            <span class="value">{{ formData.rptCreateUser || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分类扩展字段1：</span>
            <span class="value">{{ formData.extCat1 || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分类扩展字段2：</span>
            <span class="value">{{ formData.extCat2 || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">通用扩展字段1：</span>
            <span class="value">{{ formData.extCommon1 || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">通用扩展字段2：</span>
            <span class="value">{{ formData.extCommon2 || '-' }}</span>
          </div>
        </div>
        <div class="mt-4 info-item full-width">
          <span class="label">报表备注：</span>
          <span class="value block mt-2 pl-40">{{ formData.rptRemark || '-' }}</span>
        </div>
      </el-card>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { BasicMonPtRptVO } from '@/api/dataHub/operationMonitor/basicMonitorReport/basicmonptrpt'

defineOptions({ name: 'BasicMonPtRptDetail' })

const visible = ref(false)
const title = ref('基础监测点位报表详情')
const isFullscreen = ref(false)
const formData = ref<BasicMonPtRptVO>({} as BasicMonPtRptVO)

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleString() : '-')

// 打开抽屉
const open = (data: BasicMonPtRptVO) => {
  formData.value = { ...data }
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
  --el-drawer-bg-color: #f9fafb;
}

.detail-container {
  padding: 20px;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.info-card {
  border-radius: 8px;
  transition: all 0.3s ease;
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 8px 16px;
  border-radius: 4px 4px 0 0;
  margin: -1px -1px 0;
}

.info-item {
  margin-bottom: 12px;
  display: flex;
  align-items: center; /* 改为居中对齐 */
  min-height: 24px; /* 增加最小高度确保一致 */
}

.full-width {
  grid-column: span 2;
}

.label {
  color: #6b7280;
  width: 130px;
  flex-shrink: 0;
  padding: 4px 0; /* 改为上下内边距一致 */
}

.value {
  color: #1f2937;
  flex: 1;
  word-break: break-word;
  padding: 4px 0; /* 增加与label相同的内边距 */
}

/* 针对多行文本的情况单独处理 */
.full-width .value {
  padding-top: 4px; /* 保持顶部对齐 */
}

::v-deep .el-card__body {
  padding: 15px 20px;
}

/* 马克龙色系 */
.bg-blue-50 {
  background-color: #e0f2fe;
}

.text-blue-600 {
  color: #0284c7;
}

.bg-green-50 {
  background-color: #dcfce7;
}

.text-green-600 {
  color: #16a34a;
}

.bg-purple-50 {
  background-color: #f3e8ff;
}

.text-purple-600 {
  color: #7e22ce;
}

.text-red-600 {
  color: #dc2626;
}
</style>
