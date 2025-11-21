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
        <span class="text-lg font-bold text-gray-800">专项巡查执行记录详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <div class="grid-layout">
        <!-- 基本信息 -->
        <el-card class="info-card base-info-card">
          <div class="card-header">基本信息</div>
          <div class="info-content">
            <div class="info-item">
              <span class="label">记录ID：</span>
              <span class="value">{{ data.specExecId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">记录编码：</span>
              <span class="value font-semibold text-blue-500">{{ data.specExecCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">主键ID：</span>
              <span class="value">{{ data.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建人：</span>
              <span class="value">{{ data.bizCreateUser || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建时间：</span>
              <span class="value">{{ formatDate(data.bizCreateTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">更新人：</span>
              <span class="value">{{ data.bizUpdateUser || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">更新时间：</span>
              <span class="value">{{ formatDate(data.bizUpdateTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 计划与任务信息 -->
        <el-card class="info-card plan-info-card">
          <div class="card-header">计划与任务信息</div>
          <div class="info-content">
            <div class="info-item">
              <span class="label">关联专项计划ID：</span>
              <span class="value">{{ data.specPlanId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联专项计划名称：</span>
              <span class="value font-semibold text-blue-500">{{ data.specPlanName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联任务ID：</span>
              <span class="value">{{ data.taskId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联任务编码：</span>
              <span class="value">{{ data.taskCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联标准ID：</span>
              <span class="value">{{ data.stdId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联标准名称：</span>
              <span class="value">{{ data.stdName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 执行人员信息 -->
        <el-card class="info-card person-info-card">
          <div class="card-header">执行人员信息</div>
          <div class="info-content">
            <div class="info-item">
              <span class="label">执行人员ID：</span>
              <span class="value">{{ data.personId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">执行人员姓名：</span>
              <span class="value font-semibold text-blue-500">{{ data.personName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 执行时间信息 -->
        <el-card class="info-card time-info-card">
          <div class="card-header">执行时间信息</div>
          <div class="info-content">
            <div class="info-item">
              <span class="label">执行开始时间：</span>
              <span class="value">{{ formatDate(data.execStartTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">执行完成时间：</span>
              <span class="value">{{ formatDate(data.execCompleteTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 执行结果信息 - 单独占一行 -->
        <el-card class="info-card result-info-card" :span="2">
          <div class="card-header">执行结果信息</div>
          <div class="info-content">
            <div class="info-item">
              <span class="label">不合格项数量：</span>
              <span
                class="value"
                :class="data.unqualifiedCount > 0 ? 'text-red-500' : 'text-green-500'"
              >
                {{ data.unqualifiedCount || 0 }}
              </span>
            </div>
            <div class="info-item">
              <span class="label">执行评分：</span>
              <span class="value font-semibold">{{ data.execScore || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">执行设备编码：</span>
              <span class="value">{{ data.deviceCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">执行地点坐标X：</span>
              <span class="value">{{ data.execLocX || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">执行地点坐标Y：</span>
              <span class="value">{{ data.execLocY || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 详细信息 -->
        <el-card class="info-card detail-info-card" :span="2">
          <div class="card-header">详细信息</div>
          <div class="info-content">
            <div class="info-item full-width">
              <span class="label">检查项执行明细：</span>
              <span class="value block">{{ data.ckItemDetail || '-' }}</span>
            </div>
            <div class="info-item full-width">
              <span class="label">不合格项详情：</span>
              <span class="value block">{{ data.unqualifiedDetail || '-' }}</span>
            </div>
            <div class="info-item full-width">
              <span class="label">专项执行备注：</span>
              <span class="value block">{{ data.specExecRemark || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息 -->
        <el-card class="info-card ext-info-card" :span="2">
          <div class="card-header">扩展信息</div>
          <div class="info-content">
            <div class="info-item">
              <span class="label">分类扩展字段1：</span>
              <span class="value">{{ data.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类扩展字段2：</span>
              <span class="value">{{ data.extCat2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段1：</span>
              <span class="value">{{ data.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段2：</span>
              <span class="value">{{ data.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { InspectSpecExecRecVO } from '@/api/dataHub/supervisionInspection/patrolExecutionRecord/specialPatrolExecutionRecord'

defineOptions({ name: 'InspectSpecExecRecDetail' })

const visible = ref(false)
const title = ref('专项巡查执行记录详情')
const isFullscreen = ref(false)
const data = ref<InspectSpecExecRecVO>({} as InspectSpecExecRecVO)

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 打开抽屉
const open = (row: InspectSpecExecRecVO) => {
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

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.info-card {
  transition: all 0.3s ease;
  border-radius: 8px;
  border: none;
  overflow: hidden;
}

.info-card:hover {
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.08);
  transform: translateY(-2px);
}

.info-card[span='2'] {
  grid-column: span 2;
}

.card-header {
  padding: 12px 15px;
  font-weight: 600;
  color: #4e5969;
}

.base-info-card .card-header {
  background-color: #e6f4ff;
}

.plan-info-card .card-header {
  background-color: #fff0f0;
}

.person-info-card .card-header {
  background-color: #f0f9ff;
}

.time-info-card .card-header {
  background-color: #fff7e6;
}

.result-info-card .card-header {
  background-color: #f6ffed;
}

.detail-info-card .card-header {
  background-color: #f0f2ff;
}

.ext-info-card .card-header {
  background-color: #fff0f7;
}

.info-content {
  padding: 15px;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 15px;
}

.info-item {
  display: flex;
  flex-wrap: wrap;
  line-height: 1.8;
}

.info-item.full-width {
  grid-column: span 2;
}

.label {
  color: #606266;
  margin-right: 8px;
  white-space: nowrap;
}

.value {
  color: #303133;
  flex: 1;
  word-break: break-all;
}

::v-deep .el-card__body {
  padding: 0;
}
</style>
