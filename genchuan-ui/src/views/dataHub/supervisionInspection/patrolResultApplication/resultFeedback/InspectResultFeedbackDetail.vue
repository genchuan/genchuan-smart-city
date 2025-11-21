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
        <span class="text-lg font-bold text-gray-800">巡查巡检结果反馈详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <!-- 抽屉内容 -->
    <div class="detail-container bg-gray-50 p-4 rounded-lg min-h-[calc(100vh-120px)]">
      <div class="grid grid-cols-2 gap-4">
        <!-- 反馈基础信息卡片：马克龙蓝色系 -->
        <el-card class="card-hover" shadow="hover">
          <div class="card-header bg-blue-50 text-blue-700 px-3 py-2 rounded-t-md font-medium"
            >反馈基础信息
          </div>
          <div class="p-4">
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">反馈编码：</span>
              <span class="info-value font-medium text-blue-600">{{
                data?.feedbackCode || '-'
              }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">反馈ID：</span>
              <span class="info-value text-gray-800">{{ data?.feedbackId || '-' }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">关联公示：</span>
              <span class="info-value text-gray-800"
                >{{ data?.publicTitle || '-' }}（{{ data?.publicId || '' }}）</span
              >
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">反馈时间：</span>
              <span class="info-value text-gray-800">{{ formatDate(data?.feedbackTime) }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">反馈状态：</span>
              <el-tag :type="getFeedbackStatusTag(data?.feedbackStatus)">
                {{ data?.feedbackStatus || '-' }}
              </el-tag>
            </div>
          </div>
        </el-card>

        <!-- 反馈人信息卡片：马克龙粉色系 -->
        <el-card class="card-hover" shadow="hover">
          <div class="card-header bg-pink-50 text-pink-700 px-3 py-2 rounded-t-md font-medium"
            >反馈人信息
          </div>
          <div class="p-4">
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">反馈人姓名：</span>
              <span class="info-value text-gray-800">{{ data?.feedbackUserName || '-' }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">反馈人手机号：</span>
              <span class="info-value text-gray-800">{{ data?.feedbackUserPhone || '-' }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">关联问题ID：</span>
              <span class="info-value text-gray-800">{{ data?.problemId || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 核实信息卡片：马克龙绿色系 -->
        <el-card class="card-hover" shadow="hover">
          <div class="card-header bg-green-50 text-green-700 px-3 py-2 rounded-t-md font-medium"
            >核实信息
          </div>
          <div class="p-4">
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">核实人员：</span>
              <span class="info-value text-gray-800"
                >{{ data?.verifyUserName || '-' }}（{{ data?.verifyUserId || '' }}）</span
              >
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">核实结果：</span>
              <span
                class="info-value font-medium"
                :class="data?.verifyResult === '属实' ? 'text-green-600' : 'text-orange-600'"
              >
                {{ data?.verifyResult || '-' }}
              </span>
            </div>
            <div class="info-item flex flex-col mb-3">
              <span class="info-label text-gray-600 mb-1">核实意见：</span>
              <span
                class="info-value text-gray-800 whitespace-pre-line p-2 bg-white rounded border border-gray-100"
              >
                {{ data?.verifyOpinion || '无' }}
              </span>
            </div>
          </div>
        </el-card>

        <!-- 处理信息卡片：马克龙黄色系 -->
        <el-card class="card-hover" shadow="hover">
          <div class="card-header bg-yellow-50 text-yellow-700 px-3 py-2 rounded-t-md font-medium"
            >处理信息
          </div>
          <div class="p-4">
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">处理人：</span>
              <span class="info-value text-gray-800">{{ data?.handleUser || '-' }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">处理时间：</span>
              <span class="info-value text-gray-800">{{ formatDate(data?.handleTime) }}</span>
            </div>
            <div class="info-item flex flex-col mb-3">
              <span class="info-label text-gray-600 mb-1">处理结果：</span>
              <span
                class="info-value text-gray-800 whitespace-pre-line p-2 bg-white rounded border border-gray-100"
              >
                {{ data?.handleResult || '无' }}
              </span>
            </div>
          </div>
        </el-card>

        <!-- 反馈内容卡片：马克龙紫色系，占两列 -->
        <el-card class="card-hover" shadow="hover" style="grid-column: span 2">
          <div class="card-header bg-purple-50 text-purple-700 px-3 py-2 rounded-t-md font-medium"
            >反馈内容与附件
          </div>
          <div class="p-4 grid grid-cols-2 gap-6">
            <div>
              <span class="info-label text-gray-600 mb-2 block font-medium">反馈内容：</span>
              <div
                class="info-value text-gray-800 whitespace-pre-line p-4 bg-white rounded border border-gray-100 min-h-[150px]"
              >
                {{ data?.feedbackContent || '无反馈内容' }}
              </div>
            </div>
            <div>
              <span class="info-label text-gray-600 mb-2 block font-medium">反馈照片URL：</span>
              <div
                class="info-value text-gray-800 p-4 bg-white rounded border border-gray-100 min-h-[150px]"
              >
                <div v-if="data?.feedbackPhotoUrls" class="space-y-2">
                  <a
                    v-for="(url, index) in data.feedbackPhotoUrls.split(',')"
                    :key="index"
                    :href="url"
                    target="_blank"
                    class="block text-blue-600 hover:underline"
                  >
                    照片{{ index + 1 }}：{{ url }}
                  </a>
                </div>
                <div v-else class="text-gray-500">无反馈照片</div>
              </div>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息卡片：马克龙橙色系，占两列 -->
        <el-card class="card-hover" shadow="hover" style="grid-column: span 2">
          <div class="card-header bg-orange-50 text-orange-700 px-3 py-2 rounded-t-md font-medium"
            >扩展信息
          </div>
          <div class="p-4 grid grid-cols-4 gap-4">
            <div class="bg-white p-3 rounded border border-gray-100">
              <span class="text-gray-600 text-sm">分类扩展字段1：</span>
              <span class="text-gray-800 ml-2">{{ data?.extCat1 || '-' }}</span>
            </div>
            <div class="bg-white p-3 rounded border border-gray-100">
              <span class="text-gray-600 text-sm">分类扩展字段2：</span>
              <span class="text-gray-800 ml-2">{{ data?.extCat2 || '-' }}</span>
            </div>
            <div class="bg-white p-3 rounded border border-gray-100">
              <span class="text-gray-600 text-sm">通用扩展字段1：</span>
              <span class="text-gray-800 ml-2">{{ data?.extCommon1 || '-' }}</span>
            </div>
            <div class="bg-white p-3 rounded border border-gray-100">
              <span class="text-gray-600 text-sm">通用扩展字段2：</span>
              <span class="text-gray-800 ml-2">{{ data?.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>
<script setup lang="ts">
import { InspectResultFeedbackVO } from '@/api/dataHub/supervisionInspection/patrolResultApplication/resultFeedback'

// 接收父组件参数，规避直接修改props的错误
const props = defineProps<{
  modelValue: boolean
  data?: InspectResultFeedbackVO | null
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

// 反馈状态样式映射
const getFeedbackStatusTag = (status?: string) => {
  switch (status) {
    case '已处理':
      return 'success'
    case '处理中':
      return 'primary'
    case '待处理':
      return 'warning'
    default:
      return 'info'
  }
}
</script>
<style scoped>
/* 抽屉主体样式 */
.detail-drawer ::v-deep(.el-drawer__body) {
  padding: 0;
  background-color: #f5f7fa;
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
  background-color: #e0e0e0;
  border-radius: 3px;
}

/* 网格布局基础样式 */
.grid {
  display: grid;
}

.grid-cols-2 {
  grid-template-columns: repeat(2, 1fr);
}

.grid-cols-4 {
  grid-template-columns: repeat(4, 1fr);
}

.gap-4 {
  gap: 16px;
}

.gap-6 {
  gap: 24px;
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

/* 照片URL链接样式 */
.space-y-2 > a {
  transition: color 0.2s ease;
}

.space-y-2 > a:hover {
  color: #2563eb;
}
</style>
