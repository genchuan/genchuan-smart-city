<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">日常巡查记录详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基本信息卡片 -->
      <div class="grid-layout">
        <!-- 基本信息 -->
        <el-card class="info-card base-info-card">
          <div class="card-header">基本信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">主键ID：</span>
              <span class="value">{{ formData.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">记录ID：</span>
              <span class="value primary">{{ formData.dailyExecId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">执行记录编码：</span>
              <span class="value primary">{{ formData.dailyExecCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建时间：</span>
              <span class="value">{{ formatDate(formData.createTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 任务信息 -->
        <el-card class="info-card task-info-card">
          <div class="card-header">任务信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">关联任务ID：</span>
              <span class="value">{{ formData.taskId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联任务编码：</span>
              <span class="value primary">{{ formData.taskCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联标准ID：</span>
              <span class="value">{{ formData.stdId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联设备编码：</span>
              <span class="value">{{ formData.deviceCode || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 执行人信息 -->
        <el-card class="info-card person-info-card">
          <div class="card-header">执行人信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">执行人员ID：</span>
              <span class="value">{{ formData.personId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">执行人员姓名：</span>
              <span class="value primary">{{ formData.personName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 执行时间信息 -->
        <el-card class="info-card time-info-card">
          <div class="card-header">执行时间信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">执行开始时间：</span>
              <span class="value">{{ formatDate(formData.execStartTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">执行完成时间：</span>
              <span class="value">{{ formatDate(formData.execCompleteTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">执行时长：</span>
              <span class="value">{{ calculateDuration() || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 执行结果信息 -->
        <el-card class="info-card result-info-card">
          <div class="card-header">执行结果信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">检查项执行情况：</span>
              <span class="value status">
                <span :class="getExecStatusClass()">{{ formData.ckItemExec || '-' }}</span>
              </span>
            </div>
            <div class="info-item">
              <span class="label">执行评分：</span>
              <span class="value score">{{ formData.execScore || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">执行备注：</span>
              <span class="value multi-line">{{ formData.execRemark || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 位置与扩展信息 -->
        <el-card class="info-card location-info-card">
          <div class="card-header">位置与扩展信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">执行地点坐标X：</span>
              <span class="value">{{ formData.execLocX || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">执行地点坐标Y：</span>
              <span class="value">{{ formData.execLocY || '-' }}</span>
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
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { InspectDailyExecRecVO } from '@/api/dataHub/supervisionInspection/patrolExecutionRecord/dailyPatrolExecutionRecord'

defineOptions({ name: 'InspectDailyExecRecDetail' })

const visible = ref(false)
const isFullscreen = ref(false)
const formData = ref<InspectDailyExecRecVO>({
  id: 0,
  dailyExecId: '',
  dailyExecCode: '',
  taskId: '',
  taskCode: '',
  personId: '',
  personName: '',
  stdId: '',
  execStartTime: new Date(),
  execCompleteTime: new Date(),
  ckItemExec: '',
  execScore: 0,
  execRemark: '',
  deviceCode: '',
  execLocX: 0,
  execLocY: 0,
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  const date = new Date(val)
  return date.toLocaleString()
}

// 计算执行时长
const calculateDuration = () => {
  if (!formData.value.execStartTime || !formData.value.execCompleteTime) return ''
  const start = new Date(formData.value.execStartTime).getTime()
  const end = new Date(formData.value.execCompleteTime).getTime()
  if (end <= start) return '0分钟'

  const minutes = Math.floor((end - start) / (1000 * 60))
  if (minutes < 60) {
    return `${minutes}分钟`
  } else {
    const hours = Math.floor(minutes / 60)
    const mins = minutes % 60
    return `${hours}小时${mins}分钟`
  }
}

// 获取执行状态样式
const getExecStatusClass = () => {
  const status = formData.value.ckItemExec || ''
  if (status.includes('完成') || status.includes('成功')) {
    return 'success-status'
  } else if (status.includes('失败') || status.includes('异常') || status.includes('未完成')) {
    return 'error-status'
  }
  return ''
}

// 打开抽屉
const open = (data: InspectDailyExecRecVO) => {
  formData.value = { ...data }
  visible.value = true
  isFullscreen.value = false
}

// 关闭抽屉
const handleClose = () => {
  visible.value = false
  isFullscreen.value = false
}

// 对外暴露方法
defineExpose({
  open
})

// 关闭事件
const emit = defineEmits(['close'])
watch(visible, (newVal) => {
  if (!newVal) {
    emit('close')
  }
})
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
  border-radius: 8px;
  border: none;
  transition: all 0.3s ease;
  overflow: hidden;
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 15px;
  font-weight: 500;
  color: #4e5969;
  font-size: 14px;
}

.card-content {
  padding: 15px;
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
  color: #606266;
  font-weight: 500;
}

.value {
  flex: 1;
  color: #303133;
  word-break: break-word;
}

.value.primary {
  color: #409eff;
  font-weight: 500;
}

.value.multi-line {
  line-height: 1.8;
  white-space: pre-wrap;
}

.value.score {
  color: #e6a23c;
  font-weight: 500;
}

.success-status {
  color: #67c23a;
  font-weight: 500;
}

.error-status {
  color: #f56c6c;
  font-weight: 500;
}

/* 不同卡片头部不同颜色 */
.base-info-card .card-header {
  background-color: #e6f7ff;
}

.task-info-card .card-header {
  background-color: #fff7e6;
}

.person-info-card .card-header {
  background-color: #f0f9eb;
}

.time-info-card .card-header {
  background-color: #fff0f0;
}

.result-info-card .card-header {
  background-color: #f6f3f9;
}

.location-info-card .card-header {
  background-color: #e8f4f8;
}

/* 响应式调整 */
@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }
}
</style>
