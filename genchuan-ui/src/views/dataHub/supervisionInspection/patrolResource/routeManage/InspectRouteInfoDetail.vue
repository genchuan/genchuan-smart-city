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
        <span class="text-lg font-bold text-gray-800">巡查路线详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基础信息 -->
      <el-card class="info-card base-info-card">
        <template #header>
          <div class="card-header">
            <span class="card-title">基础信息</span>
          </div>
        </template>
        <div class="info-grid">
          <div class="info-item">
            <span class="info-label">主键：</span>
            <span class="info-value">{{ data.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">路线ID：</span>
            <span class="info-value font-semibold text-blue-500">{{ data.routeId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">路线名称：</span>
            <span class="info-value font-semibold">{{ data.routeName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">路线编码：</span>
            <span class="info-value">{{ data.routeCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">所属行政区划代码：</span>
            <span class="info-value">{{ data.areaCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">所属行政区划名称：</span>
            <span class="info-value">{{ data.areaName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">路线状态：</span>
            <span class="info-value">
              <el-tag :type="data.routeStatus ? 'success' : 'danger'">
                {{ data.routeStatus ? '启用' : '禁用' }}
              </el-tag>
            </span>
          </div>
          <div class="info-item">
            <span class="info-label">创建时间：</span>
            <span class="info-value">{{ formatDate(data.createTime) }}</span>
          </div>
        </div>
      </el-card>

      <!-- 起点信息 -->
      <el-card class="info-card start-info-card">
        <template #header>
          <div class="card-header">
            <span class="card-title">起点信息</span>
          </div>
        </template>
        <div class="info-grid">
          <div class="info-item">
            <span class="info-label">起点名称：</span>
            <span class="info-value">{{ data.startPtName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">起点坐标X：</span>
            <span class="info-value">{{ data.startPtX || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">起点坐标Y：</span>
            <span class="info-value">{{ data.startPtY || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 终点信息 -->
      <el-card class="info-card end-info-card">
        <template #header>
          <div class="card-header">
            <span class="card-title">终点信息</span>
          </div>
        </template>
        <div class="info-grid">
          <div class="info-item">
            <span class="info-label">终点名称：</span>
            <span class="info-value">{{ data.endPtName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">终点坐标X：</span>
            <span class="info-value">{{ data.endPtX || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">终点坐标Y：</span>
            <span class="info-value">{{ data.endPtY || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 巡查信息 -->
      <el-card class="info-card patrol-info-card">
        <template #header>
          <div class="card-header">
            <span class="card-title">巡查信息</span>
          </div>
        </template>
        <div class="info-grid">
          <div class="info-item">
            <span class="info-label">途经网格IDs：</span>
            <span class="info-value">{{ data.passGridIds || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">途经网格名称：</span>
            <span class="info-value">{{ data.passGridNames || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">预计时长：</span>
            <span class="info-value">{{ data.estimateEndure || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">巡查频次：</span>
            <span class="info-value">{{ data.inspectFrequency || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">负责人员ID：</span>
            <span class="info-value">{{ data.personId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">负责人员姓名：</span>
            <span class="info-value">{{ data.personName || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 业务时间信息 -->
      <el-card class="info-card time-info-card">
        <template #header>
          <div class="card-header">
            <span class="card-title">业务时间信息</span>
          </div>
        </template>
        <div class="info-grid">
          <div class="info-item">
            <span class="info-label">创建人：</span>
            <span class="info-value">{{ data.createUser || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">创建时间（业务）：</span>
            <span class="info-value">{{ formatDate(data.bizCreateTime) }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">更新人：</span>
            <span class="info-value">{{ data.updateUser || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">更新时间（业务）：</span>
            <span class="info-value">{{ formatDate(data.bizUpdateTime) }}</span>
          </div>
        </div>
      </el-card>

      <!-- 扩展信息 -->
      <el-card class="info-card ext-info-card">
        <template #header>
          <div class="card-header">
            <span class="card-title">扩展信息</span>
          </div>
        </template>
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
      </el-card>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { InspectRouteInfoVO } from '@/api/dataHub/supervisionInspection/patrolResource/routeManage'

defineOptions({ name: 'InspectRouteInfoDetail' })

const visible = ref(false)
const title = ref('巡查路线详情')
const isFullscreen = ref(false)
const data = ref<Partial<InspectRouteInfoVO>>({})
const emit = defineEmits(['close'])

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

// 打开抽屉
const open = (row: InspectRouteInfoVO) => {
  data.value = { ...row }
  visible.value = true
  isFullscreen.value = false
}

// 关闭抽屉
const handleClose = () => {
  visible.value = false
  emit('close')
}

defineExpose({ open })
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
  padding: 0;
}

.detail-container {
  padding: 20px;
  overflow-y: auto;
  height: calc(100% - 55px);
}

.info-card {
  margin-bottom: 20px;
  border-radius: 8px;
  border: none;
  transition: all 0.3s ease;
}

.info-card:hover {
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.08);
  transform: translateY(-2px);
}

.card-header {
  background-color: #f0f2f5;
  padding: 12px 15px;
  border-radius: 8px 8px 0 0;
}

.card-title {
  font-weight: 500;
  font-size: 15px;
}

/* 不同卡片使用不同的马克龙色标题背景 */
.base-info-card .card-header {
  background-color: #e6f7f0;
}

.start-info-card .card-header {
  background-color: #fff2e8;
}

.end-info-card .card-header {
  background-color: #e8f3ff;
}

.patrol-info-card .card-header {
  background-color: #fff7e8;
}

.time-info-card .card-header {
  background-color: #f6f3ff;
}

.ext-info-card .card-header {
  background-color: #ffe8e8;
}

.info-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 15px;
  padding: 15px;
}

.info-item {
  display: flex;
  padding: 5px 0;
  border-bottom: 1px dashed #f0f0f0;
}

.info-label {
  color: #666;
  width: 140px;
  flex-shrink: 0;
}

.info-value {
  color: #333;
  flex-grow: 1;
  word-break: break-all;
}

::v-deep .el-tag {
  margin-left: 5px;
}
</style>
