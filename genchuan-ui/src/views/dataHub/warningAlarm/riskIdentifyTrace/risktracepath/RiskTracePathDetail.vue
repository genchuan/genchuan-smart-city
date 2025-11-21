<template>
  <el-drawer v-model="drawerVisible" :size="isFullscreen ? '100%' : '75%'" @close="handleClose">
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">风险溯源路径详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="detail-container">
      <!-- 内容区域 -->
      <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
        <!-- 基本信息 -->
        <el-card class="card-hover">
          <div class="card-header bg-blue-50 px-4 py-2 mb-4">
            <span class="text-blue-600 font-medium">基本信息</span>
          </div>
          <div class="info-item">
            <span class="label">路径ID：</span>
            <span class="value font-medium text-blue-600">{{ data?.tracePathId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">预警ID：</span>
            <span class="value font-medium text-blue-600">{{ data?.earlyWarnId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">预警名称：</span>
            <span class="value">{{ data?.earlyWarnName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">溯源时间：</span>
            <span class="value">{{ formatDate(data?.traceTime) }}</span>
          </div>
        </el-card>

        <!-- 分析人信息 -->
        <el-card class="card-hover">
          <div class="card-header bg-purple-50 px-4 py-2 mb-4">
            <span class="text-purple-600 font-medium">分析人信息</span>
          </div>
          <div class="info-item">
            <span class="label">分析人ID：</span>
            <span class="value font-medium text-blue-600">{{ data?.analystUserId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分析人姓名：</span>
            <span class="value">{{ data?.analystUserName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">是否自动分析：</span>
            <span
              class="value"
              :class="data?.isAutoAnalysis === '1' ? 'text-green-500' : 'text-red-500'"
            >
              {{ data?.isAutoAnalysis === '1' ? '是' : '否' }}
            </span>
          </div>
        </el-card>

        <!-- 路径节点1 -->
        <el-card class="card-hover">
          <div class="card-header bg-green-50 px-4 py-2 mb-4">
            <span class="text-green-600 font-medium">路径节点1（直接现象）</span>
          </div>
          <div class="info-content">
            {{ data?.node1Content || '-' }}
          </div>
        </el-card>

        <!-- 路径节点2 -->
        <el-card class="card-hover">
          <div class="card-header bg-yellow-50 px-4 py-2 mb-4">
            <span class="text-yellow-600 font-medium">路径节点2（直接原因）</span>
          </div>
          <div class="info-content">
            {{ data?.node2Content || '-' }}
          </div>
        </el-card>

        <!-- 路径节点3 -->
        <el-card class="card-hover md:col-span-2">
          <div class="card-header bg-orange-50 px-4 py-2 mb-4">
            <span class="text-orange-600 font-medium">路径节点3（根本原因）</span>
          </div>
          <div class="info-content">
            {{ data?.node3Content || '-' }}
          </div>
        </el-card>

        <!-- 溯源结论 -->
        <el-card class="card-hover md:col-span-2">
          <div class="card-header bg-teal-50 px-4 py-2 mb-4">
            <span class="text-teal-600 font-medium">溯源结论</span>
          </div>
          <div class="info-content">
            {{ data?.traceConclusion || '-' }}
          </div>
        </el-card>

        <!-- 扩展字段 -->
        <el-card class="card-hover md:col-span-2">
          <div class="card-header bg-gray-50 px-4 py-2 mb-4">
            <span class="text-gray-600 font-medium">扩展字段</span>
          </div>
          <div class="grid grid-cols-1 sm:grid-cols-2 gap-4">
            <div class="info-item">
              <span class="label">分类扩展字段1：</span>
              <span class="value">{{ data?.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类扩展字段2：</span>
              <span class="value">{{ data?.extCat2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段1：</span>
              <span class="value">{{ data?.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段2：</span>
              <span class="value">{{ data?.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { RiskTracePathVO } from '@/api/dataHub/warningAlarm/riskIdentifyTrace/risktracepath'

const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  data: {
    type: Object as () => RiskTracePathVO | null,
    default: null
  }
})

const emit = defineEmits(['close'])
// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)
const isFullscreen = ref(false)

const handleClose = () => {
  emit('close')
  // 关闭时重置全屏状态
  isFullscreen.value = false
}

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}
</script>

<style scoped>
.detail-container {
  padding: 20px;
  height: 100%;
  box-sizing: border-box;
  overflow-y: auto;
}

.card-hover {
  transition: all 0.3s ease;
}

.card-hover:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
}

.card-header {
  border-radius: 4px 4px 0 0;
  font-size: 14px;
}

.info-item {
  margin-bottom: 16px;
  line-height: 1.6;
}

.label {
  display: inline-block;
  width: 100px;
  color: #666;
}

.value {
  color: #333;
}

.info-content {
  color: #333;
  line-height: 1.8;
  padding: 4px 0;
  white-space: pre-wrap;
}

::v-deep .el-card__body {
  padding: 15px 20px;
}
</style>
