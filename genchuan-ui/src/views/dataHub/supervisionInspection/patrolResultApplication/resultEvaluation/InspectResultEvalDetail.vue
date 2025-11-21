<template>
  <el-drawer
    v-model="isVisible"
    :size="isFullscreen ? '100%' : '75%'"
    direction="rtl"
    :append-to-body="true"
    :modal="true"
    class="detail-drawer"
  >
    <!-- 抽屉头部：保持原有样式不修改 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">巡查巡检结果评估详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <!-- 抽屉内容 -->
    <div class="detail-container bg-gray-50 p-4 rounded-lg min-h-[calc(100vh-120px)]">
      <div class="grid grid-cols-2 gap-4">
        <!-- 基础信息卡片：马克龙蓝色系 -->
        <el-card class="card-hover" shadow="hover">
          <div class="card-header bg-blue-50 text-blue-700 px-3 py-2 rounded-t-md font-medium"
            >基础信息
          </div>
          <div class="p-4">
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">评估编码：</span>
              <span class="info-value font-medium text-blue-600">{{ data?.evalCode || '-' }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">评估ID：</span>
              <span class="info-value text-gray-800">{{ data?.evalId || '-' }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">评估周期：</span>
              <span class="info-value text-gray-800"
                >{{ data?.evalCycleName || '-' }}（{{ data?.evalCycle || '' }}）</span
              >
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">所属区域：</span>
              <span class="info-value text-gray-800"
                >{{ data?.areaName || '-' }}（{{ data?.areaCode || '' }}）</span
              >
            </div>
          </div>
        </el-card>

        <!-- 评估人信息卡片：马克龙粉色系 -->
        <el-card class="card-hover" shadow="hover">
          <div class="card-header bg-pink-50 text-pink-700 px-3 py-2 rounded-t-md font-medium"
            >评估人信息
          </div>
          <div class="p-4">
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">评估人：</span>
              <span class="info-value text-gray-800">{{ data?.evalUser || '-' }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">评估时间：</span>
              <span class="info-value text-gray-800">{{ formatDate(data?.evalTime) }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">创建人：</span>
              <span class="info-value text-gray-800">{{ data?.createUser || '-' }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">创建时间：</span>
              <span class="info-value text-gray-800">{{ formatDate(data?.bizCreateTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 评估数据卡片：马克龙绿色系 -->
        <el-card class="card-hover" shadow="hover">
          <div class="card-header bg-green-50 text-green-700 px-3 py-2 rounded-t-md font-medium"
            >评估核心数据
          </div>
          <div class="p-4">
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">日常巡查合格率：</span>
              <span class="info-value font-medium text-blue-600">{{
                (data?.dailyQualifiedRate || 0) + '%'
              }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">专项巡查整改率：</span>
              <span class="info-value font-medium text-blue-600">{{
                (data?.specRectifyRate || 0) + '%'
              }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-40 text-gray-600">一级问题处置及时率：</span>
              <span class="info-value font-medium text-blue-600">{{
                (data?.level1TimelyRate || 0) + '%'
              }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">问题集中区域：</span>
              <span class="info-value text-gray-800">{{
                data?.problemConcentratedArea || '-'
              }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">主要问题类型：</span>
              <span class="info-value text-gray-800">{{ data?.mainProblemType || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 评估附件卡片：马克龙黄色系 -->
        <el-card class="card-hover" shadow="hover">
          <div class="card-header bg-yellow-50 text-yellow-700 px-3 py-2 rounded-t-md font-medium"
            >评估附件信息
          </div>
          <div class="p-4">
            <div class="info-item flex flex-col mb-3">
              <span class="info-label text-gray-600 mb-1">评估报告URL：</span>
              <a
                v-if="data?.evalRptUrl"
                :href="data.evalRptUrl"
                target="_blank"
                class="info-value text-blue-600 hover:underline"
              >
                {{ data.evalRptUrl }}
              </a>
              <span v-else class="info-value text-gray-800">无</span>
            </div>
            <div class="info-item flex flex-col mb-3">
              <span class="info-label text-gray-600 mb-1">扩展信息：</span>
              <div class="info-value text-gray-800 grid grid-cols-2 gap-2 mt-1">
                <div>分类扩展字段1：{{ data?.extCat1 || '-' }}</div>
                <div>分类扩展字段2：{{ data?.extCat2 || '-' }}</div>
                <div>通用扩展字段1：{{ data?.extCommon1 || '-' }}</div>
                <div>通用扩展字段2：{{ data?.extCommon2 || '-' }}</div>
              </div>
            </div>
          </div>
        </el-card>

        <!-- 评估意见卡片：马克龙紫色系，占两列 -->
        <el-card class="card-hover" shadow="hover" style="grid-column: span 2">
          <div class="card-header bg-purple-50 text-purple-700 px-3 py-2 rounded-t-md font-medium"
            >评估意见
          </div>
          <div class="p-4">
            <div class="info-item flex flex-col">
              <span class="info-label text-gray-600 mb-2 block font-medium">意见详情：</span>
              <div
                class="info-value text-gray-800 whitespace-pre-line p-4 bg-white rounded border border-gray-100 min-h-[150px]"
              >
                {{ data?.evalOpinion || '无评估意见' }}
              </div>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>
<script setup lang="ts">
import { InspectResultEvalVO } from '@/api/dataHub/supervisionInspection/patrolResultApplication/resultEvaluation'

// 接收父组件参数，规避直接修改props的错误
const props = defineProps<{
  modelValue: boolean
  data?: InspectResultEvalVO | null
}>()
const emit = defineEmits(['update:modelValue'])

// 内部维护抽屉显示状态
const isVisible = computed({
  get: () => props.modelValue,
  set: (val) => emit('update:modelValue', val)
})

const isFullscreen = ref(false) // 全屏状态控制

// 时间格式化函数（按要求实现）
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleDateString() : '-'
}
</script>
<style scoped>
/* 抽屉主体样式 */
.detail-drawer ::v-deep(.el-drawer__body) {
  padding: 0;
}

/* 内容容器样式 */
.detail-container {
  height: 100%;
  overflow-y: auto;
  scrollbar-width: thin;
  scrollbar-color: #e0e0e0 #f5f7fa;
}

.detail-container::-webkit-scrollbar {
  width: 6px;
}

.detail-container::-webkit-scrollbar-thumb {
  border-radius: 3px;
}

/* 网格布局基础样式 */
.grid {
  display: grid;
}

.grid-cols-2 {
  grid-template-columns: repeat(2, 1fr);
}

.gap-4 {
  gap: 16px;
}

/* 卡片 hover 效果 */
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

/* 卡片头部样式 */
.card-header {
  border-bottom: 1px solid #f0f2f5;
  font-size: 14px;
}

/* 信息项样式 */
.info-item {
  align-items: flex-start;
}

.info-label {
  font-size: 14px;
  font-weight: 500;
}

.info-value {
  font-size: 14px;
  flex: 1;
  word-break: break-all;
}

/* 文本换行样式 */
.whitespace-pre-line {
  white-space: pre-line;
}

/* 卡片内容区域重置 */
::v-deep(.el-card__body) {
  padding: 0;
}
</style>
