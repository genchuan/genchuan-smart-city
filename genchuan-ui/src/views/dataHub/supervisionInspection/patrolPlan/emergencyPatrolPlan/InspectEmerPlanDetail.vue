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
        <span class="text-lg font-bold text-gray-800">应急巡查计划详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基本信息 -->
      <el-row :gutter="20" class="mb-4">
        <el-col :span="12">
          <el-card class="info-card">
            <template #header>
              <div class="card-header basic-header">
                <span class="card-title">基本信息</span>
              </div>
            </template>
            <div class="info-item">
              <span class="label">计划ID：</span>
              <span class="value">{{ detailData.emerPlanId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">计划名称：</span>
              <span class="value font-bold text-blue-600">{{
                detailData.emerPlanName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">计划编码：</span>
              <span class="value">{{ detailData.emerPlanCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">应急主题：</span>
              <span class="value">{{ detailData.emerTopic || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">计划状态：</span>
              <span class="value">
                <el-tag :type="detailData.planStatus ? 'success' : 'danger'">
                  {{ detailData.planStatus ? '启用' : '禁用' }}
                </el-tag>
              </span>
            </div>
          </el-card>
        </el-col>

        <el-col :span="12">
          <el-card class="info-card">
            <template #header>
              <div class="card-header time-header">
                <span class="card-title">时间信息</span>
              </div>
            </template>
            <div class="info-item">
              <span class="label">执行时间：</span>
              <span class="value">{{ formatDate(detailData.executeTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">执行截止时间：</span>
              <span class="value">{{ formatDate(detailData.executeDeadline) }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建时间：</span>
              <span class="value">{{ formatDate(detailData.bizCreateTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">更新时间：</span>
              <span class="value">{{ formatDate(detailData.bizUpdateTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">系统创建时间：</span>
              <span class="value">{{ formatDate(detailData.createTime) }}</span>
            </div>
          </el-card>
        </el-col>
      </el-row>

      <!-- 关联信息 -->
      <el-row :gutter="20" class="mb-4">
        <el-col :span="12">
          <el-card class="info-card">
            <template #header>
              <div class="card-header relation-header">
                <span class="card-title">关联事件信息</span>
              </div>
            </template>
            <div class="info-item">
              <span class="label">关联应急事件ID：</span>
              <span class="value">{{ detailData.emerEvtId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联应急事件名称：</span>
              <span class="value">{{ detailData.emerEvtName || '-' }}</span>
            </div>
          </el-card>
        </el-col>

        <el-col :span="12">
          <el-card class="info-card">
            <template #header>
              <div class="card-header standard-header">
                <span class="card-title">关联标准信息</span>
              </div>
            </template>
            <div class="info-item">
              <span class="label">关联标准ID：</span>
              <span class="value">{{ detailData.stdId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联标准名称：</span>
              <span class="value">{{ detailData.stdName || '-' }}</span>
            </div>
          </el-card>
        </el-col>
      </el-row>

      <!-- 其他信息 -->
      <el-row :gutter="20" class="mb-4">
        <el-col :span="12">
          <el-card class="info-card">
            <template #header>
              <div class="card-header area-header">
                <span class="card-title">区域信息</span>
              </div>
            </template>
            <div class="info-item">
              <span class="label">所属区域代码：</span>
              <span class="value">{{ detailData.areaCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">所属区域名称：</span>
              <span class="value">{{ detailData.areaName || '-' }}</span>
            </div>
          </el-card>
        </el-col>

        <el-col :span="12">
          <el-card class="info-card">
            <template #header>
              <div class="card-header user-header">
                <span class="card-title">负责人信息</span>
              </div>
            </template>
            <div class="info-item">
              <span class="label">负责人员IDs：</span>
              <span class="value">{{ detailData.personIds || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">负责人员Names：</span>
              <span class="value">{{ detailData.personNames || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建人：</span>
              <span class="value">{{ detailData.createUser || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">更新人：</span>
              <span class="value">{{ detailData.updateUser || '-' }}</span>
            </div>
          </el-card>
        </el-col>
      </el-row>

      <!-- 扩展信息 -->
      <el-row :gutter="20">
        <el-col :span="24">
          <el-card class="info-card">
            <template #header>
              <div class="card-header ext-header">
                <span class="card-title">扩展信息</span>
              </div>
            </template>
            <div class="ext-info-grid">
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
        </el-col>
      </el-row>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { InspectEmerPlanVO } from '@/api/dataHub/supervisionInspection/patrolPlan/emergencyPatrolPlan'

defineOptions({ name: 'InspectEmerPlanDetail' })

const visible = ref(false)
const title = ref('应急巡查计划详情')
const isFullscreen = ref(false)
const detailData = ref<InspectEmerPlanVO>({} as InspectEmerPlanVO)

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}

// 打开抽屉
const open = (data: InspectEmerPlanVO) => {
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
  --el-drawer-bg-color: #f5f7fa;
}

.detail-container {
  padding: 20px;
}

.info-card {
  transition: all 0.3s ease;
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 16px;
  border-bottom: 1px solid #f0f0f0;
}

.card-title {
  font-size: 15px;
  font-weight: 500;
}

.basic-header {
  background-color: #e6f7ff;
}

.time-header {
  background-color: #fff2e8;
}

.relation-header {
  background-color: #f6ffed;
}

.standard-header {
  background-color: #fff0f6;
}

.area-header {
  background-color: #f0f5ff;
}

.user-header {
  background-color: #fffbf0;
}

.ext-header {
  background-color: #f9f0ff;
}

.info-item {
  display: flex;
  padding: 12px 16px;
  border-bottom: 1px dashed #f0f0f0;
  align-items: center;
}

.info-item:last-child {
  border-bottom: none;
}

.label {
  width: 120px;
  color: #666;
  flex-shrink: 0;
}

.value {
  color: #333;
  flex-grow: 1;
  word-break: break-all;
}

.text-blue-600 {
  color: #165dff;
}

.ext-info-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
}

::v-deep .el-tag {
  padding: 2px 8px;
}
</style>
