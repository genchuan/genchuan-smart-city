<template>
  <el-drawer
    v-model="drawerVisible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">任务跟踪详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基本信息卡片 -->
      <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
        <!-- 任务基本信息 -->
        <el-card class="detail-card">
          <div class="card-header bg-blue-50">
            <span class="text-blue-600 font-medium">任务基本信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">主键ID：</span>
              <span class="value font-semibold text-blue-600">{{ detailData.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">任务ID：</span>
              <span class="value">{{ detailData.taskId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">任务编码：</span>
              <span class="value">{{ detailData.taskCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">任务名称：</span>
              <span class="value font-semibold text-blue-600">{{
                detailData.taskName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">任务类型：</span>
              <span class="value">{{ detailData.taskType || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">任务状态：</span>
              <span class="value">
                <span
                  :class="
                    detailData.taskStatus === '已完成'
                      ? 'text-green-500'
                      : detailData.taskStatus === '待开始'
                        ? 'text-[#4494efff]'
                        : 'text-[#ef8644ff]'
                  "
                >
                  {{ detailData.taskStatus }}
                </span>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 负责人信息 -->
        <el-card class="detail-card">
          <div class="card-header bg-purple-50">
            <span class="text-purple-600 font-medium">负责人信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">负责人员ID：</span>
              <span class="value">{{ detailData.personId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">负责人员姓名：</span>
              <span class="value font-semibold text-blue-600">{{
                detailData.personName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">所属区域代码：</span>
              <span class="value">{{ detailData.areaCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">所属区域名称：</span>
              <span class="value">{{ detailData.areaName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 时间信息 -->
        <el-card class="detail-card">
          <div class="card-header bg-green-50">
            <span class="text-green-600 font-medium">时间信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">开始时间：</span>
              <span class="value">{{ formatDate(detailData.startTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">完成时间：</span>
              <span class="value">{{ formatDate(detailData.completeTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">截止时间：</span>
              <span class="value">{{ formatDate(detailData.deadlineTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">最后催办时间：</span>
              <span class="value">{{ formatDate(detailData.lastRemindTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 任务进度信息 -->
        <el-card class="detail-card">
          <div class="card-header bg-yellow-50">
            <span class="text-yellow-600 font-medium">任务进度信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">检查项总数：</span>
              <span class="value">{{ detailData.totalCkItem || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">已完成检查项：</span>
              <span class="value font-semibold text-blue-600">{{
                detailData.completedCkItem || 0
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">完成率：</span>
              <span class="value">
                {{
                  detailData.totalCkItem
                    ? Math.round((detailData.completedCkItem / detailData.totalCkItem) * 100) + '%'
                    : '0%'
                }}
              </span>
            </div>
            <div class="info-item">
              <span class="label">上传照片数：</span>
              <span class="value">{{ detailData.uploadPhotoCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">是否超时：</span>
              <span class="value">
                <span :class="detailData.isOvertime === 1 ? 'text-red-500' : 'text-green-500'">
                  {{ detailData.isOvertime === 1 ? '是' : '否' }}
                </span>
              </span>
            </div>
            <div class="info-item">
              <span class="label">催办次数：</span>
              <span class="value">{{ detailData.remindCount || 0 }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { InspectTaskTrackVO } from '@/api/dataHub/supervisionInspection/patrolTaskAssignment/taskTracking'

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

// 定义props
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  detailData: {
    type: Object as () => InspectTaskTrackVO,
    default: () => ({})
  }
})

// 定义emits
const emit = defineEmits(['update:visible', 'close'])
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
// 全屏状态
const isFullscreen = ref(false)

// 关闭抽屉
const handleClose = () => {
  emit('update:visible', false)
  emit('close')
  isFullscreen.value = false
}
</script>

<style scoped>
.detail-container {
  padding: 16px;
  min-height: calc(100% - 55px);
}

.detail-card {
  transition: all 0.3s ease;
  border-radius: 8px;
  overflow: hidden;
}

.detail-card:hover {
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.08);
  transform: translateY(-2px);
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
  border-bottom: 1px solid #f0f0f0;
  font-size: 13px;
}

.info-item:last-child {
  border-bottom: none;
}

.label {
  flex: 0 0 120px;
  color: #606266;
}

.value {
  flex: 1;
  color: #303133;
}
</style>
