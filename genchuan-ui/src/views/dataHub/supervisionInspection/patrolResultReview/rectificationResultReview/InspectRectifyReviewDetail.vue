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
        <span class="text-lg font-bold text-gray-800">巡查巡检整改结果复核详情</span>
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
              <span class="info-label w-36 text-gray-600">复核编码：</span>
              <span class="info-value font-medium text-blue-600">{{
                data?.rectifyReviewCode || '-'
              }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">复核ID：</span>
              <span class="info-value text-gray-800">{{ data?.rectifyReviewId || '-' }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">关联验收编码：</span>
              <span class="info-value font-medium text-blue-600">{{
                data?.acceptCode || '-'
              }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">关联验收ID：</span>
              <span class="info-value text-gray-800">{{ data?.acceptId || '-' }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">整改任务ID：</span>
              <span class="info-value text-gray-800">{{ data?.rectifyTaskId || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 问题与责任信息卡片：马克龙粉色系 -->
        <el-card class="card-hover" shadow="hover">
          <div class="card-header bg-pink-50 text-pink-700 px-3 py-2 rounded-t-md font-medium"
            >问题与责任信息
          </div>
          <div class="p-4">
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">关联问题名称：</span>
              <span class="info-value font-medium text-gray-800">{{
                data?.problemName || '-'
              }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">责任部门：</span>
              <span class="info-value text-gray-800">{{ data?.deptName || '-' }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">整改负责人：</span>
              <span class="info-value text-gray-800">{{ data?.leaderUserName || '-' }}</span>
            </div>
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">创建人：</span>
              <span class="info-value text-gray-800">{{ data?.createUser || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 复核结果卡片：马克龙绿色系 -->
        <el-card class="card-hover" shadow="hover">
          <div class="card-header bg-green-50 text-green-700 px-3 py-2 rounded-t-md font-medium"
            >复核结果信息
          </div>
          <div class="p-4">
            <div class="info-item flex mb-3">
              <span class="info-label w-36 text-gray-600">复核结果：</span>
              <el-tag
                :type="
                  data?.reviewResult === '合格'
                    ? 'success'
                    : data?.reviewResult === '待改进'
                      ? 'danger'
                      : 'warning'
                "
              >
                {{ data?.reviewResult || '-' }}
              </el-tag>
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
              <span class="info-label w-36 text-gray-600">复核方式：</span>
              <span class="info-value text-gray-800">{{ data?.reviewMethod || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 复核附件卡片：马克龙黄色系 -->
        <el-card class="card-hover" shadow="hover">
          <div class="card-header bg-yellow-50 text-yellow-700 px-3 py-2 rounded-t-md font-medium"
            >复核附件信息
          </div>
          <div class="p-4">
            <div class="info-item flex flex-col mb-3">
              <span class="info-label text-gray-600 mb-1">复核照片URLs：</span>
              <div
                class="info-value text-gray-800 whitespace-pre-line p-2 bg-white rounded border border-gray-100"
              >
                {{ data?.reviewPhotoUrls ? data.reviewPhotoUrls.split(',').join('\n') : '无' }}
              </div>
            </div>
          </div>
        </el-card>

        <!-- 复核详情卡片：马克龙紫色系，占两列 -->
        <el-card class="card-hover" shadow="hover" style="grid-column: span 2">
          <div class="card-header bg-purple-50 text-purple-700 px-3 py-2 rounded-t-md font-medium"
            >复核详情
          </div>
          <div class="p-4 grid grid-cols-2 gap-6">
            <div>
              <span class="info-label text-gray-600 mb-1 block font-medium">复核意见：</span>
              <div
                class="info-value text-gray-800 whitespace-pre-line p-3 bg-white rounded border border-gray-100 min-h-[120px]"
              >
                {{ data?.reviewOpinion || '无' }}
              </div>
            </div>
            <div>
              <span class="info-label text-gray-600 mb-1 block font-medium">扩展信息：</span>
              <div
                class="info-value text-gray-800 p-3 bg-white rounded border border-gray-100 min-h-[120px]"
              >
                <div class="flex flex-col gap-2">
                  <div>分类扩展字段1：{{ data?.extCat1 || '-' }}</div>
                  <div>分类扩展字段2：{{ data?.extCat2 || '-' }}</div>
                  <div>通用扩展字段1：{{ data?.extCommon1 || '-' }}</div>
                  <div>通用扩展字段2：{{ data?.extCommon2 || '-' }}</div>
                </div>
              </div>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>
<script setup lang="ts">
import { InspectRectifyReviewVO } from '@/api/dataHub/supervisionInspection/patrolResultReview/rectificationResultReview'

// 接收父组件参数，规避直接修改props的错误
const props = defineProps<{
  modelValue: boolean
  data?: InspectRectifyReviewVO | null
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
