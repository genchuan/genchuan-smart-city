<template>
  <el-drawer
    v-model="isVisible"
    :size="isFullscreen ? '100%' : '75%'"
    direction="rtl"
    class="detail-drawer"
  >
    <!-- 头部区域（样式不修改） -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">巡查巡检整改档案详情</span>
        <el-button type="primary" link @click="handleToggleFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container bg-gray-50 p-6 rounded-lg h-full overflow-auto">
      <div class="grid grid-cols-2 gap-6">
        <!-- 基础信息卡片 -->
        <el-card class="card-hover">
          <div class="card-header bg-blue-50 px-4 py-3 rounded-t-lg mb-4">
            <span class="text-blue-700 font-semibold">基础信息</span>
          </div>
          <div class="card-content space-y-3 text-gray-700">
            <div class="flex items-center">
              <span class="w-36 text-gray-500">档案ID：</span>
              <span class="font-bold text-blue-600">{{ data?.rectifyArchId || '-' }}</span>
            </div>
            <div class="flex items-center">
              <span class="w-36 text-gray-500">档案编码：</span>
              <span class="font-bold text-blue-600">{{ data?.rectifyArchCode || '-' }}</span>
            </div>
            <div class="flex items-center">
              <span class="w-36 text-gray-500">档案名称：</span>
              <span class="font-medium">{{ data?.rectifyArchName || '-' }}</span>
            </div>
            <div class="flex items-center">
              <span class="w-36 text-gray-500">主键ID：</span>
              <span class="font-bold text-blue-600">{{ data?.id || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 区域信息卡片 -->
        <el-card class="card-hover">
          <div class="card-header bg-pink-50 px-4 py-3 rounded-t-lg mb-4">
            <span class="text-pink-700 font-semibold">区域信息</span>
          </div>
          <div class="card-content space-y-3 text-gray-700">
            <div class="flex items-center">
              <span class="w-36 text-gray-500">所属区域代码：</span>
              <span class="font-bold text-blue-600">{{ data?.areaCode || '-' }}</span>
            </div>
            <div class="flex items-center">
              <span class="w-36 text-gray-500">所属区域名称：</span>
              <span class="font-medium">{{ data?.areaName || '-' }}</span>
            </div>
            <div class="flex items-center">
              <span class="w-36 text-gray-500">归档周期：</span>
              <span>{{ data?.rectifyArchCycle || '-' }}</span>
            </div>
            <div class="flex items-center">
              <span class="w-36 text-gray-500">关联问题类型：</span>
              <span>{{ data?.problemTypeName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 整改信息卡片 -->
        <el-card class="card-hover">
          <div class="card-header bg-green-50 px-4 py-3 rounded-t-lg mb-4">
            <span class="text-green-700 font-semibold">整改信息</span>
          </div>
          <div class="card-content space-y-3 text-gray-700">
            <div class="flex items-center">
              <span class="w-36 text-gray-500">整改任务数：</span>
              <span class="font-bold text-blue-600">{{ data?.includeTaskCount || 0 }}</span>
            </div>
            <div class="flex items-center">
              <span class="w-36 text-gray-500">整改完成率：</span>
              <span
                :class="{
                  'text-green-600': Number(data?.rectifyCompleteRate) >= 90,
                  'text-orange-600':
                    Number(data?.rectifyCompleteRate) < 90 &&
                    data?.rectifyCompleteRate !== undefined
                }"
                >{{ data?.rectifyCompleteRate + '%' || '0%' }}</span
              >
            </div>
            <div class="flex items-center">
              <span class="w-36 text-gray-500">归档人：</span>
              <span>{{ data?.rectifyArchUser || '-' }}</span>
            </div>
            <div class="flex items-center">
              <span class="w-36 text-gray-500">归档时间：</span>
              <span>{{ formatDate(data?.rectifyArchTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 状态信息卡片 -->
        <el-card class="card-hover">
          <div class="card-header bg-yellow-50 px-4 py-3 rounded-t-lg mb-4">
            <span class="text-yellow-700 font-semibold">状态信息</span>
          </div>
          <div class="card-content space-y-3 text-gray-700">
            <div class="flex items-center">
              <span class="w-36 text-gray-500">档案状态：</span>
              <el-tag :type="data?.rectifyArchStatus === '已归档' ? 'success' : 'warning'">
                {{ data?.rectifyArchStatus || '-' }}
              </el-tag>
            </div>
            <div class="flex items-center">
              <span class="w-36 text-gray-500">销毁时间：</span>
              <span>{{ formatDate(data?.rectifyDestroyTime) }}</span>
            </div>
            <div class="flex items-center">
              <span class="w-36 text-gray-500">归档说明：</span>
              <span>{{ data?.rectifyArchDesc || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 附件信息卡片 -->
        <el-card class="card-hover">
          <div class="card-header bg-purple-50 px-4 py-3 rounded-t-lg mb-4">
            <span class="text-purple-700 font-semibold">附件信息</span>
          </div>
          <div class="card-content space-y-3 text-gray-700">
            <div class="flex flex-col">
              <span class="w-36 text-gray-500 mb-1">档案附件URLs：</span>
              <div class="flex flex-wrap gap-2">
                <span class="text-blue-500 hover:underline cursor-pointer break-all max-w-[200px]">
                  {{ data?.rectifyAttachUrls || '-' }}
                </span>
              </div>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息卡片（跨列） -->
        <el-card class="card-hover grid-cols-2">
          <div class="card-header bg-teal-50 px-4 py-3 rounded-t-lg mb-4">
            <span class="text-teal-700 font-semibold">扩展信息</span>
          </div>
          <div class="card-content space-y-3 text-gray-700 grid grid-cols-2 gap-4">
            <div class="flex items-center">
              <span class="w-36 text-gray-500">分类扩展字段1：</span>
              <span>{{ data?.extCat1 || '-' }}</span>
            </div>
            <div class="flex items-center">
              <span class="w-36 text-gray-500">分类扩展字段2：</span>
              <span>{{ data?.extCat2 || '-' }}</span>
            </div>
            <div class="flex items-center">
              <span class="w-36 text-gray-500">通用扩展字段1：</span>
              <span>{{ data?.extCommon1 || '-' }}</span>
            </div>
            <div class="flex items-center">
              <span class="w-36 text-gray-500">通用扩展字段2：</span>
              <span>{{ data?.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>
<script setup lang="ts">
import { InspectRectifyArchVO } from '@/api/dataHub/supervisionInspection/patrolArchiveManagement/rectificationArchive'

const props = defineProps<{
  modelValue: boolean
  data?: InspectRectifyArchVO | null
}>()

const emit = defineEmits<{
  'update:modelValue': [value: boolean]
  'toggle-fullscreen': []
}>()

// 规避 prop mutation 错误：通过计算属性代理 visible 状态
const isVisible = computed({
  get: () => props.modelValue,
  set: (val) => emit('update:modelValue', val)
})

const isFullscreen = ref(false)

/** 时间格式化 */
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleDateString() : '-'
}

/** 全屏切换 */
const handleToggleFullscreen = () => {
  isFullscreen.value = !isFullscreen.value
  emit('toggle-fullscreen')
}
</script>
<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f9fafb;
}

.detail-container {
  font-size: 14px;
}

.grid {
  display: grid;
}

.grid-cols-2 {
  grid-template-columns: repeat(2, 1fr);
}

.gap-6 {
  gap: 24px;
}

.gap-4 {
  gap: 16px;
}

.space-y-3 {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.card-hover {
  transition: all 0.3s ease;
  border: none !important;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1);
}

.card-hover:hover {
  transform: translateY(-3px);
  box-shadow:
    0 10px 15px -3px rgba(0, 0, 0, 0.1),
    0 4px 6px -2px rgba(0, 0, 0, 0.05);
}

.card-header {
  border-bottom: none !important;
  margin: -20px -20px 16px -20px !important;
  border-radius: 8px 8px 0 0 !important;
}

.card-content {
  padding: 0 4px;
}

.el-card__body {
  padding: 20px !important;
  border-radius: 8px !important;
}

.flex-col {
  flex-direction: column;
}

.flex-wrap {
  flex-wrap: wrap;
}

.mb-1 {
  margin-bottom: 4px;
}
</style>
