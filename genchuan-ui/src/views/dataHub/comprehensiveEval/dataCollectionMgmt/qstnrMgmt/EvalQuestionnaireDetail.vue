<template>
  <el-drawer
    v-model="visible"
    :title="''"
    :size="isFullscreen ? '100%' : '75%'"
    direction="rtl"
    :append-to-body="true"
    class="detail-drawer"
    :before-close="handleClose"
  >
    <!-- 抽屉头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">问卷调查详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <!-- 抽屉内容 -->
    <div class="p-4 bg-gray-50 min-h-[calc(100vh-120px)]">
      <div class="grid grid-cols-2 gap-4 mb-4">
        <!-- 基础信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow">
          <template #header>
            <div class="card-header bg-blue-50 text-blue-700">基础信息</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <span class="label">主键ID：</span>
              <span class="value font-bold text-blue-600">{{ data.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">问卷ID：</span>
              <span class="value font-bold text-blue-600">{{ data.questionnaireId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">问卷名称：</span>
              <span class="value">{{ data.questionnaireName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">问卷编码：</span>
              <span class="value font-bold text-blue-600">{{ data.questionnaireCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建人(业务)：</span>
              <span class="value">{{ data.createUserBiz || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建时间(业务)：</span>
              <span class="value">{{ formatDate(data.createTimeBiz) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 关联任务信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow">
          <template #header>
            <div class="card-header bg-pink-50 text-pink-700">关联任务信息</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <span class="label">关联任务ID：</span>
              <span class="value font-bold text-pink-600">{{ data.evalTaskId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联任务名称：</span>
              <span class="value">{{ data.evalTaskName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联指标项ID：</span>
              <span class="value font-bold text-pink-600">{{ data.idxItemId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联指标项名称：</span>
              <span class="value">{{ data.idxItemName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 问卷配置信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow">
          <template #header>
            <div class="card-header bg-green-50 text-green-700">问卷配置信息</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <span class="label">调查对象范围：</span>
              <span class="value">{{ data.targetScope || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">发放方式：</span>
              <span class="value">{{ data.releaseMethod || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">问卷链接：</span>
              <span
                class="value text-blue-600 hover:underline cursor-pointer"
                @click="copyLink(data.questionnaireUrl)"
              >
                {{ data.questionnaireUrl || '-' }}
              </span>
            </div>
            <div class="info-item">
              <span class="label">问卷二维码：</span>
              <span class="value">{{ data.questionnaireQrcode || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 时间与状态信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow">
          <template #header>
            <div class="card-header bg-yellow-50 text-yellow-700">时间与状态信息</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <span class="label">开始时间：</span>
              <span class="value">{{ formatDate(data.startTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">结束时间：</span>
              <span class="value">{{ formatDate(data.endTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">问卷状态：</span>
              <el-tag :type="data.questionnaireStatus === 'COMPLETED' ? 'success' : 'warning'">
                {{ data.questionnaireStatus === 'COMPLETED' ? '已完成' : '进行中' }}
              </el-tag>
            </div>
            <div class="info-item">
              <span class="label">填写人数：</span>
              <span class="value font-bold text-yellow-600">{{ data.fillCount || 0 }} 人</span>
            </div>
            <div class="info-item">
              <span class="label">系统创建时间：</span>
              <span class="value">{{ formatDate(data.createTime) }}</span>
            </div>
          </div>
        </el-card>
      </div>
      <div class="grid gap-4">
        <el-card class="card-beautify" :shadow="hoverShadow">
          <template #header>
            <div class="card-header bg-purple-50 text-purple-700">问卷内容</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <div class="label">内容详情：</div>
              <div class="value prose max-w-none p-4 bg-white rounded-lg border border-gray-100">
                <div
                  v-html="
                    data.questionnaireContent || '<span class=text-gray-400>无问卷内容</span>'
                  "
                ></div>
              </div>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>
<script setup lang="ts">
import { PropType, ref, watch } from 'vue'
import { EvalQuestionnaireVO } from '@/api/dataHub/comprehensiveEval/dataCollectionMgmt/qstnrMgmt'

const message = useMessage() // 消息弹窗

/** 时间格式化函数 */
const formatDate = (val: any) => (val ? new Date(val).toLocaleString() : '-')

/** Props定义 */
const props = defineProps({
  modelValue: {
    type: Boolean,
    required: true
  },
  data: {
    type: Object as PropType<EvalQuestionnaireVO>,
    required: true,
    default: () => ({})
  }
})

/** Emits定义 */
const emit = defineEmits(['update:modelValue', 'close'])

/** 内部状态（规避props直接修改错误） */
const visible = ref(props.modelValue)
const isFullscreen = ref(false)
const hoverShadow = ref('hover') // 卡片hover阴影

/** 监听props变化同步内部状态 */
watch(
  () => props.modelValue,
  (newVal) => {
    visible.value = newVal
  },
  { immediate: true }
)

/** 同步内部状态到props */
watch(
  () => visible.value,
  (newVal) => {
    emit('update:modelValue', newVal)
  }
)

/** 关闭抽屉回调 */
const handleClose = () => {
  visible.value = false
  emit('close')
  isFullscreen.value = false // 退出全屏
}

/** 复制问卷链接 */
const copyLink = (link: string) => {
  if (!link) {
    message.warning('暂无有效链接可复制')
    return
  }
  navigator.clipboard
    .writeText(link)
    .then(() => {
      message.success('链接复制成功')
    })
    .catch(() => {
      message.error('链接复制失败，请手动复制')
    })
}

// 暴露必要方法（保持组件一致性）
defineExpose({ visible })
</script>
<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f8f9fa;
}

.card-beautify {
  border-radius: 8px;
  border: none;
  transition: all 0.3s ease;
}

.card-beautify:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08) !important;
}

.card-header {
  padding: 8px 16px;
  border-radius: 4px 4px 0 0;
  font-weight: 600;
}

.card-content {
  padding: 16px;
}

.info-item {
  display: flex;
  flex-direction: column;
  margin-bottom: 16px;
  font-size: 14px;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  color: #666;
  font-weight: 500;
  margin-bottom: 4px;
}

.value {
  color: #333;
  word-break: break-word;
}

/* 马克龙配色优化 */
.bg-blue-50 {
  background-color: #e6f7ff;
}

.text-blue-700 {
  color: #1890ff;
}

.text-blue-600 {
  color: #096dd9;
}

.bg-pink-50 {
  background-color: #fff0f6;
}

.text-pink-700 {
  color: #ff4d94;
}

.text-pink-600 {
  color: #e53e3e;
}

.bg-green-50 {
  background-color: #f0fff4;
}

.text-green-700 {
  color: #52c41a;
}

.text-green-600 {
  color: #389e0d;
}

.bg-yellow-50 {
  background-color: #fffbe6;
}

.text-yellow-700 {
  color: #faad14;
}

.text-yellow-600 {
  color: #fa8c16;
}

.bg-purple-50 {
  background-color: #f9f0ff;
}

.text-purple-700 {
  color: #722ed1;
}

.el-drawer__body {
  padding: 0 !important;
  background-color: #f8fafc;
}

.el-drawer__header {
  padding: 16px 20px !important;
  border-bottom: 1px solid #e2e8f0;
}

/* 响应式适配 */
@media (max-width: 1200px) {
  .grid {
    grid-template-columns: 1fr !important;
  }
}

/* 问卷内容样式优化 */
.prose {
  line-height: 1.8;
}

.prose p {
  margin-bottom: 8px;
}

.prose strong {
  color: #2d3748;
}
</style>
