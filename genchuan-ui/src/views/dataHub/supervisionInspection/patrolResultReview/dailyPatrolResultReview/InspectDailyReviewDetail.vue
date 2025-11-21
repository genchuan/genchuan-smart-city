<template>
  <el-drawer
    v-model="isVisible"
    :title="''"
    :size="isFullscreen ? '100%' : '75%'"
    direction="rtl"
    :append-to-body="true"
    :modal="true"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">巡查巡检日常结果复核详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="detail-container bg-gray-50 p-4 rounded-lg min-h-[calc(100vh-120px)]">
      <!-- 前四个卡片保持两列网格布局 -->
      <div class="grid grid-cols-2 gap-4 mb-4">
        <!-- 基础信息卡片 -->
        <el-card class="card-hover" shadow="hover">
          <div class="card-header bg-blue-50 text-blue-700 px-3 py-2 rounded-t-md font-medium">
            基础信息
          </div>
          <div class="p-4">
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">复核编码：</span>
              <span class="info-value font-medium text-blue-600">{{
                data?.dailyReviewCode || '-'
              }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">复核ID：</span>
              <span class="info-value text-gray-800">{{ data?.dailyReviewId || '-' }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">关联记录编码：</span>
              <span class="info-value font-medium text-blue-600">{{
                data?.dailyExecCode || '-'
              }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">关联记录ID：</span>
              <span class="info-value text-gray-800">{{ data?.dailyExecId || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 巡查信息卡片 -->
        <el-card class="card-hover" shadow="hover">
          <div class="card-header bg-pink-50 text-pink-700 px-3 py-2 rounded-t-md font-medium">
            巡查信息
          </div>
          <div class="p-4">
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">巡查人员：</span>
              <span class="info-value font-medium text-gray-800">{{
                data?.execUserName || '-'
              }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">巡查人员ID：</span>
              <span class="info-value text-gray-800">{{ data?.execUserId || '-' }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">巡查区域：</span>
              <span class="info-value text-gray-800">{{ data?.execAreaName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 复核结果卡片 -->
        <el-card class="card-hover" shadow="hover">
          <div class="card-header bg-green-50 text-green-700 px-3 py-2 rounded-t-md font-medium">
            复核结果
          </div>
          <div class="p-4">
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">复核结果：</span>
              <span
                class="info-value font-medium"
                :class="data?.reviewResult === '合格' ? 'text-green-600' : 'text-red-600'"
              >
                {{ data?.reviewResult || '-' }}
              </span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">复核人：</span>
              <span class="info-value text-gray-800">{{ data?.reviewUser || '-' }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">复核时间：</span>
              <span class="info-value text-gray-800">{{ formatDate(data?.reviewTime) }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">复核依据：</span>
              <span class="info-value text-gray-800">{{ data?.reviewBasis || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 整改要求卡片 -->
        <el-card class="card-hover" shadow="hover">
          <div class="card-header bg-yellow-50 text-yellow-700 px-3 py-2 rounded-t-md font-medium">
            整改要求
          </div>
          <div class="p-4">
            <div class="info-item flex flex-col mb-3">
              <span class="info-label text-gray-600 mb-1">整改要求内容：</span>
              <span
                class="info-value text-gray-800 whitespace-pre-line p-2 bg-white rounded border border-gray-100"
              >
                {{ data?.rectifyRequirement || '无' }}
              </span>
            </div>
          </div>
        </el-card>
      </div>

      <!-- 复核内容与意见卡片单独占一行 -->
      <div class="grid gap-4">
        <el-card class="card-hover" shadow="hover">
          <div class="card-header bg-purple-50 text-purple-700 px-3 py-2 rounded-t-md font-medium">
            复核内容与意见
          </div>
          <div class="p-4 grid grid-cols-2 gap-4">
            <div>
              <span class="info-label text-gray-600 mb-1 block">复核内容：</span>
              <div
                class="info-value text-gray-800 whitespace-pre-line p-3 bg-white rounded border border-gray-100 min-h-[120px]"
              >
                {{ data?.reviewContent || '无' }}
              </div>
            </div>
            <div>
              <span class="info-label text-gray-600 mb-1 block">复核意见：</span>
              <div
                class="info-value text-gray-800 whitespace-pre-line p-3 bg-white rounded border border-gray-100 min-h-[120px]"
              >
                {{ data?.reviewOpinion || '无' }}
              </div>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>
<script setup lang="ts">
import { InspectDailyReviewVO } from '@/api/dataHub/supervisionInspection/patrolResultReview/dailyPatrolResultReview'

const props = defineProps<{
  modelValue: boolean
  data?: InspectDailyReviewVO | null
}>()

const emit = defineEmits(['update:modelValue'])

// 规避直接修改props的visible，使用计算属性
const isVisible = computed({
  get: () => props.modelValue,
  set: (val) => emit('update:modelValue', val)
})

const isFullscreen = ref(false) // 全屏状态

// 时间格式化函数
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleDateString() : '-'
}
</script>
<style scoped>
.detail-drawer ::v-deep(.el-drawer__body) {
  padding: 0;
}

.detail-container {
  height: 100%;
  overflow-y: auto;
}

.grid {
  display: grid;
}

.grid-cols-2 {
  grid-template-columns: repeat(2, 1fr);
}

.gap-4 {
  gap: 16px;
}

.card-hover {
  transition: all 0.3s ease;
  border-radius: 8px;
  overflow: hidden;
  border: 1px solid #e8f4f8;
}

.card-hover:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.05);
}

.card-header {
  border-bottom: 1px solid #f0f2f5;
}

.info-item {
  align-items: flex-start;
}

.info-label {
  font-size: 14px;
}

.info-value {
  font-size: 14px;
  flex: 1;
}

.whitespace-pre-line {
  white-space: pre-line;
}

::v-deep(.el-card__body) {
  padding: 0;
}
</style>
