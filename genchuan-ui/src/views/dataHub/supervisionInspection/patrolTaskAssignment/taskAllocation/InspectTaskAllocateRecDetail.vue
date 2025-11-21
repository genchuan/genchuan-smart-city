<template>
  <el-drawer v-model="drawerVisible" :size="isFullscreen ? '100%' : '75%'" class="detail-drawer">
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">任务分配详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="drawer-container">
      <!-- 内容区域 -->
      <div class="detail-content">
        <el-row :gutter="20">
          <!-- 基本信息卡片 -->
          <el-col :span="12">
            <el-card class="info-card base-info-card">
              <template #header>
                <div class="card-header">
                  <span class="card-title">基本信息</span>
                </div>
              </template>
              <div class="info-item">
                <span class="info-label">主键：</span>
                <span class="info-value">{{ data.id || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">分配ID：</span>
                <span class="info-value font-bold text-blue-500">{{ data.allocateId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">任务ID：</span>
                <span class="info-value font-bold text-blue-500">{{ data.taskId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">任务编码：</span>
                <span class="info-value">{{ data.taskCode || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">任务类型：</span>
                <span class="info-value">{{ data.taskType || '-' }}</span>
              </div>
            </el-card>
          </el-col>

          <!-- 人员信息卡片 -->
          <el-col :span="12">
            <el-card class="info-card person-info-card">
              <template #header>
                <div class="card-header">
                  <span class="card-title">人员信息</span>
                </div>
              </template>
              <div class="info-item">
                <span class="info-label">原负责人ID：</span>
                <span class="info-value">{{ data.oldPersonId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">原负责人姓名：</span>
                <span class="info-value">{{ data.oldPersonName || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">新负责人ID：</span>
                <span class="info-value font-bold text-blue-500">{{
                  data.newPersonId || '-'
                }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">新负责人姓名：</span>
                <span class="info-value font-bold text-blue-500">{{
                  data.newPersonName || '-'
                }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">分配人：</span>
                <span class="info-value">{{ data.allocateUser || '-' }}</span>
              </div>
            </el-card>
          </el-col>

          <!-- 时间信息卡片 -->
          <el-col :span="12">
            <el-card class="info-card time-info-card">
              <template #header>
                <div class="card-header">
                  <span class="card-title">时间信息</span>
                </div>
              </template>
              <div class="info-item">
                <span class="info-label">分配时间：</span>
                <span class="info-value">{{ formatDate(data.allocateTime) }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">创建时间：</span>
                <span class="info-value">{{ formatDate(data.createTime) }}</span>
              </div>
            </el-card>
          </el-col>

          <!-- 状态信息卡片 -->
          <el-col :span="12">
            <el-card class="info-card status-info-card">
              <template #header>
                <div class="card-header">
                  <span class="card-title">状态信息</span>
                </div>
              </template>
              <div class="info-item">
                <span class="info-label">任务状态变更：</span>
                <span class="info-value status-tag" v-if="data.taskStatusChange">
                  <span
                    class="status-before"
                    :class="{
                      'status-new': data.taskStatusChange.startsWith('新建'),
                      'status-processing': data.taskStatusChange.startsWith('进行中')
                    }"
                    >{{ data.taskStatusChange.split('->')[0] }}</span
                  >
                  <span class="status-arrow">→</span>
                  <span
                    class="status-after"
                    :class="{
                      'status-reassigned': data.taskStatusChange.endsWith('重新分配'),
                      'status-processing': data.taskStatusChange.endsWith('进行中')
                    }"
                    >{{ data.taskStatusChange.split('->')[1] }}</span
                  >
                </span>
                <span class="info-value status-tag status-empty" v-else>-</span>
              </div>
            </el-card>
          </el-col>

          <!-- 分配原因卡片 -->
          <el-col :span="24">
            <el-card class="info-card reason-info-card">
              <template #header>
                <div class="card-header">
                  <span class="card-title">分配原因</span>
                </div>
              </template>
              <div class="info-item multi-line">
                <span class="info-value">{{ data.allocateReason || '-' }}</span>
              </div>
            </el-card>
          </el-col>

          <!-- 扩展信息卡片 -->
          <el-col :span="12">
            <el-card class="info-card ext-info-card">
              <template #header>
                <div class="card-header">
                  <span class="card-title">分类扩展信息</span>
                </div>
              </template>
              <div class="info-item">
                <span class="info-label">分类扩展字段1：</span>
                <span class="info-value">{{ data.extCat1 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">分类扩展字段2：</span>
                <span class="info-value">{{ data.extCat2 || '-' }}</span>
              </div>
            </el-card>
          </el-col>

          <el-col :span="12">
            <el-card class="info-card ext-info-card">
              <template #header>
                <div class="card-header">
                  <span class="card-title">通用扩展信息</span>
                </div>
              </template>
              <div class="info-item">
                <span class="info-label">通用扩展字段1：</span>
                <span class="info-value">{{ data.extCommon1 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">通用扩展字段2：</span>
                <span class="info-value">{{ data.extCommon2 || '-' }}</span>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { InspectTaskAllocateRecVO } from '@/api/dataHub/supervisionInspection/patrolTaskAssignment/taskAllocation'

defineOptions({ name: 'InspectTaskAllocateRecDetail' })

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

// 接收父组件参数
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  }
})

// 向父组件发送事件
const emit = defineEmits(['close'])
// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)

// 添加对 drawerVisible 的监听 以解决drawerVisible值的同步问题
watch(
  () => drawerVisible.value,
  (newVal) => {
    if (!newVal) {
      emit('close') // 当抽屉关闭时，通知父组件重置 detailVisible
    }
  }
)
// 抽屉状态
const isFullscreen = ref(false)
const data = ref<InspectTaskAllocateRecVO>({
  id: 0,
  allocateId: '',
  taskId: '',
  taskCode: '',
  oldPersonId: '',
  oldPersonName: '',
  newPersonId: '',
  newPersonName: '',
  allocateReason: '',
  allocateTime: new Date(),
  allocateUser: '',
  taskStatusChange: '',
  taskType: '',
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

// 设置详情数据
const setData = (row: InspectTaskAllocateRecVO) => {
  data.value = { ...row }
}

defineExpose({ setData })
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
}

.drawer-container {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.detail-content {
  flex: 1;
  overflow-y: auto;
  padding: 20px;
}

.info-card {
  margin-bottom: 20px;
  transition: all 0.3s ease;
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  background-color: #f0f2f5;
  padding: 12px 15px;
  border-radius: 8px 8px 0 0;
}

.card-title {
  font-weight: 500;
  font-size: 14px;
}

/*不同卡片使用不同的马克龙色标题背景*/
.base-info-card .card-header {
  background-color: #e6f7ff;
}

.person-info-card .card-header {
  background-color: #fff2e8;
}

.time-info-card .card-header {
  background-color: #f6ffed;
}

.status-info-card .card-header {
  background-color: #fff0f0;
}

.reason-info-card .card-header {
  background-color: #f9f0ff;
}

.ext-info-card .card-header {
  background-color: #e8f3ff;
}

.info-item {
  display: flex;
  padding: 12px 15px;
  border-bottom: 1px dashed #f0f0f0;
  align-items: flex-start;
}

.info-item:last-child {
  border-bottom: none;
}

.info-label {
  flex: 0 0 120px;
  color: #606266;
  font-weight: 500;
}

.info-value {
  flex: 1;
  color: #303133;
  word-break: break-all;
}

.multi-line .info-value {
  line-height: 1.6;
  white-space: pre-line;
}

.status-tag {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  padding: 4px 10px;
  border-radius: 4px;
  font-size: 13px;
  background-color: #f5f7fa;
}

.status-before,
.status-after {
  padding: 2px 8px;
  border-radius: 3px;
  font-weight: 500;
}

.status-arrow {
  color: #909399;
  font-weight: bold;
}

/* 状态样式 */
.status-new {
  background-color: #ecf5ff;
  color: #4096ff;
}

.status-processing {
  background-color: #f0fff4;
  color: #00b42a;
}

.status-reassigned {
  background-color: #fff7e6;
  color: #ff7d00;
}

.status-empty {
  color: #909399;
  background-color: #f5f7fa;
}

::v-deep .el-drawer__body {
  padding: 0;
  overflow: hidden;
}
</style>
