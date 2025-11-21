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
        <span class="text-lg font-bold text-gray-800">应急巡查执行记录详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基本信息 -->
      <div class="grid-layout">
        <el-card class="info-card" hoverable>
          <div class="card-header bg-blue-50">
            <span class="text-blue-600 font-medium">基本信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">记录ID：</span>
              <span class="value font-semibold text-blue-600">{{ detail.emerExecId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">记录编码：</span>
              <span class="value">{{ detail.emerExecCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">主键ID：</span>
              <span class="value">{{ detail.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建人：</span>
              <span class="value">{{ detail.bizCreateUser || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建时间：</span>
              <span class="value">{{ formatDate(detail.bizCreateTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 关联信息 -->
        <el-card class="info-card" hoverable>
          <div class="card-header bg-purple-50">
            <span class="text-purple-600 font-medium">关联信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">关联应急计划ID：</span>
              <span class="value">{{ detail.emerPlanId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联应急计划名称：</span>
              <span class="value font-semibold text-blue-600">{{
                detail.emerPlanName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联应急事件ID：</span>
              <span class="value">{{ detail.emerEvtId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联应急事件名称：</span>
              <span class="value font-semibold text-blue-600">{{ detail.emerEvtName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联任务ID：</span>
              <span class="value">{{ detail.taskId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联任务编码：</span>
              <span class="value">{{ detail.taskCode || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 执行人员信息 -->
        <el-card class="info-card" hoverable>
          <div class="card-header bg-green-50">
            <span class="text-green-600 font-medium">执行人员信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">执行人员ID：</span>
              <span class="value">{{ detail.personId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">执行人员姓名：</span>
              <span class="value font-semibold text-blue-600">{{ detail.personName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">执行设备编码：</span>
              <span class="value">{{ detail.deviceCode || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 时间信息 -->
        <el-card class="info-card" hoverable>
          <div class="card-header bg-orange-50">
            <span class="text-orange-600 font-medium">时间信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">执行开始时间：</span>
              <span class="value">{{ formatDate(detail.execStartTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">执行完成时间：</span>
              <span class="value">{{ formatDate(detail.execCompleteTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">推送时间：</span>
              <span class="value">{{ formatDate(detail.pushTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 执行详情 -->
        <el-card class="info-card" hoverable :span="24">
          <div class="card-header bg-teal-50">
            <span class="text-teal-600 font-medium">执行详情</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">应急检查项明细：</span>
              <span class="value block">{{ detail.emerCkDetail || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">紧急级别：</span>
              <span class="value">
                <el-tag :type="getUrgencyLevelTagType(detail.urgencyLevel)">
                  {{ detail.urgencyLevel || '-' }}
                </el-tag>
              </span>
            </div>
            <div class="info-item">
              <span class="label">应急问题描述：</span>
              <span class="value block">{{ detail.emerProblemDesc || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">处置建议：</span>
              <span class="value block">{{ detail.disposalSugg || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 其他信息 -->
        <el-card class="info-card" hoverable>
          <div class="card-header bg-gray-50">
            <span class="text-gray-600 font-medium">其他信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">应急地点坐标X：</span>
              <span class="value">{{ detail.emerLocX || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">应急地点坐标Y：</span>
              <span class="value">{{ detail.emerLocY || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">是否已推送指挥中心：</span>
              <span class="value">
                <el-tag :type="detail.isPushedCmd ? 'success' : 'danger'">
                  {{ detail.isPushedCmd ? '是' : '否' }}
                </el-tag>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息 -->
        <el-card class="info-card" hoverable>
          <div class="card-header bg-indigo-50">
            <span class="text-indigo-600 font-medium">扩展信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">分类扩展字段1：</span>
              <span class="value">{{ detail.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类扩展字段2：</span>
              <span class="value">{{ detail.extCat2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段1：</span>
              <span class="value">{{ detail.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段2：</span>
              <span class="value">{{ detail.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { InspectEmerExecRecVO } from '@/api/dataHub/supervisionInspection/patrolExecutionRecord/emergencyPatrolExecutionRecord'

defineOptions({ name: 'InspectEmerExecRecDetail' })

const visible = ref(false)
const title = ref('应急巡查执行记录详情')
const isFullscreen = ref(false)
const detail = ref<InspectEmerExecRecVO>({} as InspectEmerExecRecVO)
const emit = defineEmits(['close'])

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 根据紧急级别获取标签类型
const getUrgencyLevelTagType = (level: string) => {
  switch (level) {
    case '一般':
      return 'info'
    case '较紧急':
      return 'warning'
    case '紧急':
      return 'error'
    case '非常紧急':
      return 'danger'
    default:
      return 'default'
  }
}

// 打开抽屉
const open = (row: InspectEmerExecRecVO) => {
  detail.value = { ...row }
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
  padding: 16px 0;
}

.detail-container {
  padding: 0 24px;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
}

.info-card {
  border-radius: 8px;
  transition: all 0.3s ease;
  overflow: hidden;
}

::v-deep .info-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 16px;
  margin: 0;
}

.card-body {
  padding: 16px;
}

.info-item {
  margin-bottom: 12px;
  line-height: 1.6;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  display: inline-block;
  width: 140px;
  color: #6b7280;
  font-size: 14px;
}

.value {
  color: #1f2937;
  font-size: 14px;
}

.value.block {
  display: block;
  margin-left: 120px;
}

/* 适配全屏模式 */
::v-deep .el-drawer__body {
  padding: 0;
  overflow-y: auto;
  height: calc(100% - 55px);
}

/* 颜色定义 - 马克龙色系 */
.bg-blue-50 {
  background-color: #e0f2fe;
}

.text-blue-600 {
  color: #0284c7;
}

.bg-purple-50 {
  background-color: #f3e8ff;
}

.text-purple-600 {
  color: #7c3aed;
}

.bg-green-50 {
  background-color: #dcfce7;
}

.text-green-600 {
  color: #16a34a;
}

.bg-orange-50 {
  background-color: #fff7ed;
}

.text-orange-600 {
  color: #ea580c;
}

.bg-teal-50 {
  background-color: #d5f5f6;
}

.text-teal-600 {
  color: #0d9488;
}

.bg-gray-50 {
  background-color: #f3f4f6;
}

.text-gray-600 {
  color: #4b5563;
}

.bg-indigo-50 {
  background-color: #e0e7ff;
}

.text-indigo-600 {
  color: #4338ca;
}

/* 响应式调整 */
@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }

  .label {
    width: 100px;
  }

  .value.block {
    margin-left: 100px;
  }
}
</style>
