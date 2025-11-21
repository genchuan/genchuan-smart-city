<template>
  <el-drawer
    v-model="isShow"
    :title="null"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleBeforeClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">区域统计详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <div class="grid-layout">
        <!-- 基础信息卡片 -->
        <el-card class="info-card base-info-card">
          <div class="card-header">基础信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">主键ID：</span>
              <span class="value">{{ detailData.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计ID：</span>
              <span class="value font-bold text-blue-600">{{ detailData.areaStatId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计周期：</span>
              <span class="value">{{ detailData.statCycle || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">周期名称：</span>
              <span class="value">{{ detailData.statCycleName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计人：</span>
              <span class="value">{{ detailData.statUser || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计时间：</span>
              <span class="value">{{ formatDate(detailData.statTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 区域信息卡片 -->
        <el-card class="info-card area-info-card">
          <div class="card-header">区域信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">行政区划代码：</span>
              <span class="value">{{ detailData.areaCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">行政区划名称：</span>
              <span class="value font-bold text-blue-600">{{ detailData.areaName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">上级区域名称：</span>
              <span class="value">{{ detailData.parentAreaName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 巡查统计卡片 -->
        <el-card class="info-card inspect-info-card">
          <div class="card-header">巡查统计</div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">日常巡查次数：</span>
              <span class="value">{{ detailData.dailyInspectCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">专项巡查次数：</span>
              <span class="value">{{ detailData.specInspectCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">应急巡查次数：</span>
              <span class="value">{{ detailData.emerInspectCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">总巡查次数：</span>
              <span class="value font-bold text-blue-600">
                {{
                  (detailData.dailyInspectCount || 0) +
                  (detailData.specInspectCount || 0) +
                  (detailData.emerInspectCount || 0)
                }}
              </span>
            </div>
          </div>
        </el-card>

        <!-- 问题统计卡片 -->
        <el-card class="info-card problem-info-card">
          <div class="card-header">问题统计</div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">上报问题总数：</span>
              <span class="value">{{ detailData.totalProblemCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">整改完成数：</span>
              <span class="value">{{ detailData.rectifyCompleteCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">整改率：</span>
              <el-tag :type="getRectifyRateTag(detailData.rectifyRate)">
                {{ detailData.rectifyRate ? detailData.rectifyRate + '%' : '0%' }}
              </el-tag>
            </div>
            <div class="info-item">
              <span class="label">问题集中类型：</span>
              <span class="value">{{ detailData.concentratedProblemType || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card ext-info-card" style="grid-column: span 2">
          <div class="card-header">扩展信息</div>
          <div class="card-body ext-grid">
            <div class="info-item">
              <span class="label">分类扩展字段1：</span>
              <span class="value">{{ detailData.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类扩展字段2：</span>
              <span class="value">{{ detailData.extCat2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段1：</span>
              <span class="value">{{ detailData.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段2：</span>
              <span class="value">{{ detailData.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref, defineProps, defineEmits, watch } from 'vue'
import { InspectAreaRptVO } from '@/api/dataHub/supervisionInspection/patrolStatisticalReport/areaStatisticalReport'

// 定义props，支持v-model双向绑定
const props = defineProps({
  modelValue: {
    type: Boolean,
    default: false
  },
  data: {
    type: Object as () => InspectAreaRptVO,
    default: () => ({})
  }
})

// 定义emits，包含v-model必需的update:modelValue事件
const emit = defineEmits(['update:modelValue', 'close'])

// 内部显示状态，与props.modelValue双向绑定
const isShow = ref(props.modelValue)
const isFullscreen = ref(false)
const detailData = ref<InspectAreaRptVO>({} as InspectAreaRptVO)

// 关键修复：同步v-model双向绑定
watch(
  isShow,
  (newVal) => {
    emit('update:modelValue', newVal)
    if (!newVal) {
      emit('close')
    }
  },
  { immediate: true }
)

// 监听props变化，同步内部状态
watch(
  () => props.modelValue,
  (newVal) => {
    isShow.value = newVal
  }
)

watch(
  () => props.data,
  (newVal) => {
    detailData.value = { ...newVal }
  },
  { deep: true }
)

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}

// 获取整改率Tag类
const getRectifyRateTag = (rate?: number) => {
  if (!rate) return 'info'
  return rate >= 90 ? 'success' : rate >= 60 ? 'warning' : 'danger'
}

// 关键修复：处理抽屉关闭前的回调（Element Plus要求必须调用done函数）
const handleBeforeClose = (done: () => void) => {
  isShow.value = false
  isFullscreen.value = false
  done() // 必须调用done函数完成关闭
}

// 提供外部打开方法
const open = (data: InspectAreaRptVO) => {
  detailData.value = { ...data }
  isShow.value = true
  isFullscreen.value = false
}

defineExpose({ open })
</script>

<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f5f7fa;
}

.detail-container {
  padding: 20px;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.info-card {
  transition: all 0.3s ease;
  border-radius: 8px;
  overflow: hidden;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 16px;
  font-weight: 500;
  color: #4e5969;
}

/* 马克龙色系标题背景 */
.base-info-card .card-header {
  background-color: #e6f7ff;
}

.area-info-card .card-header {
  background-color: #fff7e6;
}

.inspect-info-card .card-header {
  background-color: #f6ffed;
}

.problem-info-card .card-header {
  background-color: #fff0f6;
}

.ext-info-card .card-header {
  background-color: #f0f2ff;
}

.card-body {
  padding: 16px;
}

.info-item {
  display: flex;
  padding: 10px 0;
  border-bottom: 1px dashed #f0f0f0;
  align-items: center;
}

.info-item:last-child {
  border-bottom: none;
}

.label {
  width: 120px;
  color: #666;
  flex-shrink: 0;
}

.value {
  flex-grow: 1;
  word-break: break-all;
}

.ext-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 10px;
}

.status-label {
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 500;
}

.status-success {
  background-color: #e6f7ee;
  color: #00b42a;
}

.status-warning {
  background-color: #fffbe6;
  color: #faad14;
}

.status-danger {
  background-color: #fff2f0;
  color: #f53f3f;
}

::v-deep .el-drawer__header {
  border-bottom: 1px solid #f0f0f0;
  padding-bottom: 10px;
  margin-bottom: 10px;
}
</style>
