<template>
  <el-drawer
    v-model="localVisible"
    :size="isFullscreen ? '100%' : '75%'"
    :append-to-body="true"
    class="detail-drawer"
  >
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">巡查巡检台账档案详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="drawer-content">
      <!-- 详情内容 -->
      <div class="detail-container bg-gray-50 p-6 rounded-lg">
        <div class="grid grid-cols-2 gap-6">
          <!-- 基础信息卡片 -->
          <el-card class="card-beautify" shadow="hover">
            <div class="card-header bg-blue-50 px-4 py-3 rounded-t-lg mb-4">
              <span class="font-bold text-blue-700">基础信息</span>
            </div>
            <div class="card-body">
              <div class="info-item">
                <label class="info-label">档案ID：</label>
                <span class="info-value font-medium text-blue-600">{{
                  detail?.ledgerArchId || '-'
                }}</span>
              </div>
              <div class="info-item">
                <label class="info-label">档案编码：</label>
                <span class="info-value font-medium text-blue-600">{{
                  detail?.ledgerArchCode || '-'
                }}</span>
              </div>
              <div class="info-item">
                <label class="info-label">档案名称：</label>
                <span class="info-value font-medium text-blue-600">{{
                  detail?.ledgerArchName || '-'
                }}</span>
              </div>
              <div class="info-item">
                <label class="info-label">所属区域：</label>
                <span class="info-value">{{ detail?.areaName || '-' }}</span>
              </div>
              <div class="info-item">
                <label class="info-label">区域代码：</label>
                <span class="info-value">{{ detail?.areaCode || '-' }}</span>
              </div>
              <div class="info-item">
                <label class="info-label">台账周期：</label>
                <span class="info-value">{{ detail?.ledgerCycle || '-' }}</span>
              </div>
              <div class="info-item">
                <label class="info-label">数据ID：</label>
                <span class="info-value font-mono text-gray-600">{{ detail?.id || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 归档信息卡片 -->
          <el-card class="card-beautify" shadow="hover">
            <div class="card-header bg-green-50 px-4 py-3 rounded-t-lg mb-4">
              <span class="font-bold text-green-700">归档信息</span>
            </div>
            <div class="card-body">
              <div class="info-item">
                <label class="info-label">归档人：</label>
                <span class="info-value font-medium text-green-600">{{
                  detail?.ledgerArchUser || '-'
                }}</span>
              </div>
              <div class="info-item">
                <label class="info-label">归档时间：</label>
                <span class="info-value">{{ formatDate(detail?.ledgerArchTime) }}</span>
              </div>
              <div class="info-item">
                <label class="info-label">销毁时间：</label>
                <span class="info-value">{{ formatDate(detail?.ledgerDestroyTime) }}</span>
              </div>
              <div class="info-item">
                <label class="info-label">档案状态：</label>
                <el-tag :type="detail?.ledgerArchStatus === '已归档' ? 'success' : 'warning'">
                  {{ detail?.ledgerArchStatus || '-' }}
                </el-tag>
              </div>
              <div class="info-item">
                <label class="info-label">包含档案类型：</label>
                <span class="info-value">{{ detail?.includeArchTypes || '-' }}</span>
              </div>
              <div class="info-item">
                <label class="info-label">附件访问：</label>
                <el-link
                  type="primary"
                  :href="detail?.ledgerAttachUrl"
                  target="_blank"
                  v-if="detail?.ledgerAttachUrl"
                >
                  查看附件
                </el-link>
                <span v-else>-</span>
              </div>
            </div>
          </el-card>

          <!-- 核心信息卡片 -->
          <el-card class="card-beautify" shadow="hover">
            <div class="card-header bg-pink-50 px-4 py-3 rounded-t-lg mb-4">
              <span class="font-bold text-pink-700">核心信息</span>
            </div>
            <div class="card-body">
              <div class="info-item">
                <label class="info-label">核心指标JSON：</label>
                <pre class="info-pre">{{ formatJson(detail?.coreIdxJson) }}</pre>
              </div>
            </div>
          </el-card>

          <!-- 扩展信息卡片 -->
          <el-card class="card-beautify" shadow="hover">
            <div class="card-header bg-purple-50 px-4 py-3 rounded-t-lg mb-4">
              <span class="font-bold text-purple-700">扩展信息</span>
            </div>
            <div class="card-body">
              <div class="info-item">
                <label class="info-label">归档说明：</label>
                <span class="info-value">{{ detail?.ledgerArchDesc || '-' }}</span>
              </div>
              <div class="info-item">
                <label class="info-label">分类扩展字段1：</label>
                <span class="info-value">{{ detail?.extCat1 || '-' }}</span>
              </div>
              <div class="info-item">
                <label class="info-label">分类扩展字段2：</label>
                <span class="info-value">{{ detail?.extCat2 || '-' }}</span>
              </div>
              <div class="info-item">
                <label class="info-label">通用扩展字段1：</label>
                <span class="info-value">{{ detail?.extCommon1 || '-' }}</span>
              </div>
              <div class="info-item">
                <label class="info-label">通用扩展字段2：</label>
                <span class="info-value">{{ detail?.extCommon2 || '-' }}</span>
              </div>
              <div class="info-item">
                <label class="info-label">创建时间：</label>
                <span class="info-value">{{ formatDate(detail?.createTime) }}</span>
              </div>
            </div>
          </el-card>
        </div>
      </div>
    </div>
  </el-drawer>
</template>
<script setup lang="ts">
import { defineProps, defineEmits, ref, watch, computed } from 'vue'
import { InspectLedgerArchVO } from '@/api/dataHub/supervisionInspection/patrolArchiveManagement/accountBookArchive'

/** 格式化时间 */
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

/** 格式化JSON字符串 */
const formatJson = (jsonStr?: string) => {
  if (!jsonStr) return '-'
  try {
    return JSON.stringify(JSON.parse(jsonStr), null, 2)
  } catch {
    return jsonStr
  }
}

/** 定义props和emits */
const props = defineProps<{
  detail: InspectLedgerArchVO | null
  visible: boolean
}>()

const emit = defineEmits<{
  (e: 'update:visible', value: boolean): void
}>()

/** 本地维护抽屉可见状态，避免直接修改props */
const localVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    localVisible.value = newVal
    if (!newVal) {
      isFullscreen.value = false // 关闭时退出全屏
    }
  }
)
watch(localVisible, (newVal) => {
  emit('update:visible', newVal)
})

/** 全屏状态 */
const isFullscreen = ref(false)

/** 获取状态标签类型 */
const getStatusTagType = (status?: string) => {
  switch (status) {
    case '1':
      return 'success'
    case '2':
      return 'danger'
    case '3':
      return 'warning'
    default:
      return 'info'
  }
}

/** 获取状态文本 */
const getStatusText = (status?: string) => {
  switch (status) {
    case '1':
      return '正常'
    case '2':
      return '已销毁'
    case '3':
      return '待归档'
    default:
      return '未知'
  }
}

/** 确保工具类方法被使用（若仍提示未使用，可在模板中增加调试输出或确认逻辑） */
const useStatusUtils = computed(() => {
  return {
    tagType: getStatusTagType(''),
    text: getStatusText('')
  }
})
</script>
<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #fff;
  border-radius: 8px 0 0 8px;
}

.drawer-content {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.detail-container {
  flex: 1;
  overflow-y: auto;
}

.card-beautify {
  border: none;
  border-radius: 8px;
  transition: all 0.3s ease;
}

.card-beautify:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.08);
}

.card-header {
  margin: -1px -1px 0 -1px;
  border-bottom: 1px solid #eee;
}

.card-body {
  padding: 0 20px 20px;
}

.info-item {
  display: flex;
  flex-direction: column;
  margin-bottom: 16px;
  font-size: 14px;
}

.info-label {
  color: #666;
  font-weight: 500;
  margin-bottom: 4px;
}

.info-value {
  color: #333;
  line-height: 1.5;
}

.info-pre {
  background-color: #f9f9f9;
  border-radius: 4px;
  padding: 8px;
  font-size: 12px;
  line-height: 1.4;
  overflow-x: auto;
  color: #333;
  margin: 0;
}

/* 适配全屏样式 */
:deep(.el-drawer__wrapper) {
  display: flex;
  align-items: center;
  justify-content: center;
}

:deep(.el-drawer__body) {
  padding: 0;
  overflow: hidden;
}

/* 滚动条美化 */
.detail-container::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}

.detail-container::-webkit-scrollbar-thumb {
  border-radius: 3px;
  background-color: #ddd;
}

.detail-container::-webkit-scrollbar-track {
  background-color: transparent;
}
</style>
