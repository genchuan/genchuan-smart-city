<template>
  <el-drawer
    v-model="localVisible"
    :size="isFullscreen ? '100%' : '75%'"
    :modal="false"
    :before-close="handleBeforeClose"
    :append-to-body="true"
    :custom-class="isFullscreen ? 'fullscreen-drawer' : ''"
    @open="handleDrawerOpen"
  >
    <template #title>
      <span>预警详情</span>
      <div class="absolute top-4 right-16 flex flex-col items-center gap-2">
        <el-button
          :icon="FullScreen"
          circle
          @click="handleToggleFullscreen"
          size="small"
          :title="isFullscreen ? '退出全屏' : '全屏展开'"
          class="custom-fullscreen-btn"
        />
      </div>
    </template>

    <!-- 详情加载中状态 -->
    <div v-if="loading" class="flex justify-center items-center h-60">
      <el-loading indicator-class="loading-indicator" />
    </div>

    <!-- 详情内容 - 接口返回数据加载完成后展示 -->
    <div v-else class="space-y-6 p-4">
      <!-- 1. 基础信息区域 -->
      <div class="border rounded-lg p-4 bg-white">
        <h3 class="text-lg font-semibold mb-4">基础信息</h3>
        <el-table :data="detailList" borderless :show-header="false" max-height="200">
          <el-table-column label="字段" width="120" align="center" prop="label" />
          <el-table-column label="值" align="center" prop="value" />
        </el-table>
      </div>

      <!-- 2. 关联异常数据区域（指标趋势图、设备状态截图） -->
      <div class="border rounded-lg p-4 bg-white">
        <h3 class="text-lg font-semibold mb-4">关联异常数据</h3>
        <div class="grid grid-cols-2 gap-4">
          <!-- 指标趋势图 -->
          <div v-if="detailData.indicatorTrendData" class="border rounded p-2 h-60">
            <h4 class="text-sm font-medium mb-2">指标趋势图</h4>
            <!-- ECharts图表容器 -->
            <div ref="trendChartRef" class="w-full h-[calc(100%-24px)]"></div>
          </div>
          <!-- 设备状态截图 -->
          <div v-if="detailData.deviceStatusImage" class="border rounded p-2 h-60 flex justify-center items-center flex-col">
            <h4 class="text-sm font-medium mb-2">设备状态截图</h4>
            <el-image
              :src="detailData.deviceStatusImage"
              alt="设备状态截图"
              class="max-w-full max-h-[calc(100%-24px)] object-contain"
              :preview-src-list="[detailData.deviceStatusImage]"
            />
          </div>
          <!-- 无数据提示 -->
          <div v-if="!detailData.indicatorTrendData && !detailData.deviceStatusImage" class="col-span-2 flex justify-center items-center h-60 text-gray-500">
            暂无关联异常数据
          </div>
        </div>
      </div>

      <!-- 3. 处置日志区域 -->
      <div class="border rounded-lg p-4 bg-white">
        <h3 class="text-lg font-semibold mb-4">处置日志</h3>
        <ProcessInstanceTaskList
          :key="detailData.processInstanceId"
          :loading="processInstanceLoading"
          :id="detailData.processInstanceId"
        />
        <div v-if="detailData.status===0" class="flex justify-center items-center h-20 text-gray-500">
          暂无处置日志
        </div>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref, reactive, onUnmounted, watch, defineEmits, computed } from 'vue'
import { dateFormatter } from '@/utils/formatTime'
import { ElMessage, ElTable, ElTableColumn, ElImage, ElLoading, ElButton } from 'element-plus'
import { FullScreen } from '@element-plus/icons-vue'  // 只导入Fullscreen图标

import * as echarts from 'echarts'
import type { WarningAlertListTableVO } from '@/api/dataHub/warningAlarm/list'
import ProcessInstanceTaskList from '@/views/bpm/processInstance/detail/ProcessInstanceTaskList.vue'

// 定义事件
const emit = defineEmits(['close', 'update:currentId', 'update:isFullscreen'])
//定义流程相关变量
const processInstanceLoading = ref(false) // 流程实例的加载中

// 扩展详情数据类型
interface WarningDetailVO extends WarningAlertListTableVO {
  indicatorTrendData?: {
    xAxis: string[]
    series: {
      name: string
      data: number[]
    }[]
  }
  deviceStatusImage?: string
  disposalLogs?: {
    operatorName: string
    operationType: string
    operationContent: string
    operationTime: Date
    remark?: string
  }[]
}

// 接收父组件传入的全屏状态
const props = defineProps({
  isFullscreen: {
    type: Boolean,
    default: false
  }
})

// 组件状态
const localVisible = ref(false)
const loading = ref(false)
const currentId = ref<number | null>(null)
const detailData = reactive<WarningDetailVO>({} as WarningDetailVO)
const detailList = ref<Array<{ label: string; value: string }>>([])
// 计算属性：响应props的全屏状态变化
const isFullscreen = computed({
  get: () => props.isFullscreen,
  set: (value) => {
    emit('update:isFullscreen', value)
  }
})

// ECharts实例引用
const trendChartRef = ref<HTMLDivElement | null>(null)
let trendChartInstance: echarts.ECharts | null = null

/** 打开详情抽屉 */
const open = (data: WarningDetailVO) => {
  currentId.value = data.id || null
  localVisible.value = true
  console.log(data.processInstanceId)
  //利用传入的processInstanceId获取流转记录
  processInstanceLoading.value = true;
  loadData(data)
  processInstanceLoading.value = false;
  emit('update:currentId', currentId.value)
}

/** 更新抽屉数据（不关闭抽屉） */
const updateData = (data: WarningDetailVO) => {
  if (data.id === currentId.value) return

  currentId.value = data.id || null
  emit('update:currentId', currentId.value)

  loading.value = true

  if (trendChartInstance) {
    trendChartInstance.dispose()
    trendChartInstance = null
  }

  loadData(data)
}

/** 加载数据并更新UI */
const loadData = (data: WarningDetailVO) => {
  try {
    Object.assign(detailData, data)
    formatDetailList()
    watch(
      () => detailData.indicatorTrendData,
      () => {
        if (detailData.indicatorTrendData) {
          initTrendChart()
        }
      },
      { immediate: true }
    )
  } catch (error) {
    ElMessage.error('详情数据处理失败')
    console.error('详情数据处理错误：', error)
  } finally {
    loading.value = false
  }
}

/** 检查抽屉是否可见 */
const isVisible = () => {
  return localVisible.value
}

/** 获取当前展示的详情ID */
const getCurrentId = () => {
  return currentId.value
}

/** 格式化基础信息列表 */
const formatDetailList = () => {
  detailList.value = [
    { label: '预警ID', value: detailData.id?.toString() || '-' },
    { label: '告警编号', value: detailData.alertCode || '-' },
    { label: '关联对象类型', value: detailData.relatedObjectType || '-' },
    { label: '关联对象名称', value: detailData.relatedObjectName || '-' },
    { label: '预警领域', value: detailData.warningField || '-' },
    { label: '预警类型', value: detailData.warningType || '-' },
    { label: '预警等级', value: mapWarningLevel(detailData.warningLevel) || '-' },
    { label: '预警状态', value: mapWarningStatus(detailData.warningStatus) || '-' },
    { label: '触发原因', value: detailData.triggerReason || '-' },
    { label: '派发部门', value: detailData.dispatchDepartment || '-' },
    { label: '责任人', value: detailData.responsiblePerson || '-' },
    { label: '责任人电话', value: detailData.responsiblePersonPhone || '-' },
    { label: '触发时间', value: detailData.triggerTime ? dateFormatter(detailData.triggerTime) : '-' },
    { label: '要求完成时间', value: detailData.requiredCompleteTime ? dateFormatter(detailData.requiredCompleteTime) : '-' },
    { label: '处置进展描述', value: detailData.disposalProgressDesc || '-' },
    { label: '审核人', value: detailData.reviewer || '-' },
    { label: '审核意见', value: detailData.reviewOpinion || '-' },
    { label: '审核时间', value: detailData.reviewTime ? dateFormatter(detailData.reviewTime) : '-' }
  ]
}

/** 映射预警等级为中文 */
const mapWarningLevel = (level?: string) => {
  const levelMap: Record<string, string> = {
    'emergency': '紧急',
    'important': '重要',
    'general': '一般'
  }
  return levelMap[level || ''] || level || '-'
}

/** 映射预警状态为中文 */
const mapWarningStatus = (status?: string) => {
  const statusMap: Record<string, string> = {
    0:'待派单',
    1:'已派单'
  }
  return statusMap[status || ''] || status || '-'
}

/** 初始化指标趋势图 */
const initTrendChart = () => {
  if (!trendChartRef.value || !detailData.indicatorTrendData) return

  trendChartInstance = echarts.init(trendChartRef.value)

  const option = {
    tooltip: {
      trigger: 'axis',
      axisPointer: { type: 'shadow' }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: detailData.indicatorTrendData.xAxis,
      axisLabel: { interval: 0, rotate: 30 }
    },
    yAxis: {
      type: 'value',
      min: 'dataMin - 10',
      max: 'dataMax + 10'
    },
    series: detailData.indicatorTrendData.series.map(series => ({
      ...series,
      type: 'line',
      smooth: true,
      markLine: {
        data: [{ type: 'average', name: '平均值' }]
      }
    }))
  }

  trendChartInstance.setOption(option)
  window.addEventListener('resize', handleChartResize)
}

/** 图表窗口大小自适应 */
const handleChartResize = () => {
  trendChartInstance?.resize()
}

/** 关闭抽屉前的处理 */
const handleBeforeClose = () => {
  // 退出全屏
  if (isFullscreen.value) {
    isFullscreen.value = false
  }

  if (trendChartInstance) {
    trendChartInstance.dispose()
    trendChartInstance = null
  }
  window.removeEventListener('resize', handleChartResize)

  Object.keys(detailData).forEach(key => {
    delete (detailData as any)[key]
  })
  detailList.value = []
  currentId.value = null
  localVisible.value = false

  emit('close')
  emit('update:currentId', null)
  return true
}

/** 操作类型标签样式映射 */
// const getOperationTypeTagType = (type?: string): 'success' | 'warning' | 'info' | 'primary' | 'danger' => {
//   const typeMap: Record<string, 'success' | 'warning' | 'info' | 'primary' | 'danger'> = {
//     '派单': 'primary',
//     '接单': 'success',
//     '提交审核': 'warning',
//     '审核通过': 'success',
//     '审核驳回': 'danger',
//     '补充进展': 'info',
//     '删除预警': 'danger'
//   }
//   return typeMap[type || ''] || 'info'
// }

/** 切换全屏状态 */
const handleToggleFullscreen = () => {
  isFullscreen.value = !isFullscreen.value
  // 图表大小自适应
  setTimeout(() => {
    handleChartResize()
  }, 100)
}

/** 抽屉打开时的回调 */
const handleDrawerOpen = () => {
  // 确保图表正确渲染
  if (detailData.indicatorTrendData && trendChartRef.value) {
    setTimeout(() => {
      initTrendChart()
    }, 100)
  }
}

/** 监听全屏变化 */
document.addEventListener('fullscreenchange', () => {
  // 同步浏览器全屏状态
  if (!document.fullscreenElement && isFullscreen.value) {
    isFullscreen.value = false
  }
})

/** 监听组件卸载，清理资源 */
onUnmounted(() => {
  if (trendChartInstance) {
    trendChartInstance.dispose()
    trendChartInstance = null
  }
  window.removeEventListener('resize', handleChartResize)
})

// 暴露方法给父组件调用
defineExpose({
  open,
  updateData,
  isVisible,
  getCurrentId
})
</script>

<style scoped>
/* 加载指示器样式 */
.loading-indicator {
  width: 40px;
  height: 40px;
}


/* 1. 让抽屉标题栏支持绝对定位（父容器相对定位） */
::v-deep .el-drawer__header {
  padding: 16px 20px;
  border-bottom: 1px solid #eee;
  display: flex;
  align-items: center;
  position: relative; /* 关键：为子元素的绝对定位提供参考 */
}

/* 2. 调整全屏按钮样式：匹配关闭按钮尺寸，优化 hover 效果 */
::v-deep .custom-fullscreen-btn {
  width: 24px; /* 与抽屉关闭按钮尺寸一致 */
  height: 24px;
  padding: 0; /* 清除默认内边距，避免按钮过大 */
  transition: all 0.3s ease;
}

/* 3. 优化按钮 hover 效果，与关闭按钮视觉风格统一 */


/* 4. 若抽屉关闭按钮位置特殊，可微调绝对定位参数（根据实际效果调整） */
/* 说明：right 控制水平距离，top 控制垂直距离，gap 控制与关闭按钮的间距 */
.absolute {
  position: absolute;
}
.top-4 {
  top: 20px; /* 与标题栏顶部对齐 */
}
.right-16 {
  right: 64px; /* 与右侧关闭按钮保持间距（可根据实际需求调整） */
}
.flex-col {
  flex-direction: column;
}
.items-center {
  align-items: center;
}
.gap-2 {
  gap: 10px; /* 与关闭按钮的垂直间距（可按需调整） */
}
.grid {
  display: grid;
}

.grid-cols-2 {
  grid-template-columns: repeat(2, minmax(0, 1fr));
}

.gap-4 {
  gap: 16px;
}

.border {
  border: 1px solid #e5e7eb;
}

.rounded {
  border-radius: 6px;
}

.rounded-lg {
  border-radius: 8px;
}

.p-2 {
  padding: 8px;
}

.p-4 {
  padding: 16px;
}

.bg-white {
  background-color: #ffffff;
}

.text-lg {
  font-size: 16px;
}

.font-semibold {
  font-weight: 600;
}

.mb-2 {
  margin-bottom: 8px;
}

.mb-4 {
  margin-bottom: 16px;
}

.h-60 {
  height: 240px;
}

.h-20 {
  height: 80px;
}

.flex {
  display: flex;
}

.justify-center {
  justify-content: center;
}

.items-center {
  align-items: center;
}

.text-gray-500 {
  color: #6b7280;
}

.object-contain {
  object-fit: contain;
}

.max-w-full {
  max-width: 100%;
}

.max-h-full {
  max-height: 100%;
}
</style>
