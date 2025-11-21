<template>
  <el-drawer
    v-model="visible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">综合对比分析报表详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container" v-loading="loading">
      <div class="grid-layout">
        <!-- 基本信息卡片 -->
        <el-card class="info-card base-info-card">
          <div class="card-header">基本信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="info-label">主键：</span>
              <span class="info-value">{{ detailData.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">报表ID：</span>
              <span class="info-value font-semibold text-blue-600">{{
                detailData.rptId || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">对比维度类型：</span>
              <span class="info-value">{{ detailData.compareDimType || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">统计周期：</span>
              <span class="info-value">{{ detailData.statCycle || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">统计周期名称：</span>
              <span class="info-value">{{ detailData.statCycleName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">统计指标：</span>
              <span class="info-value font-semibold text-blue-600">{{
                detailData.statIdx || '-'
              }}</span>
            </div>
          </div>
        </el-card>

        <!-- 报表信息卡片 -->
        <el-card class="info-card report-info-card">
          <div class="card-header">报表信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="info-label">报表生成时间：</span>
              <span class="info-value">{{ formatDate(detailData.rptCreateTime) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">报表生成人：</span>
              <span class="info-value">{{ detailData.rptCreateUser || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">报表备注：</span>
              <span class="info-value">{{ detailData.rptRemark || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">分类扩展字段1：</span>
              <span class="info-value">{{ detailData.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">分类扩展字段2：</span>
              <span class="info-value">{{ detailData.extCat2 || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 对比对象1信息卡片 -->
        <el-card class="info-card object1-info-card">
          <div class="card-header">对比对象1信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="info-label">对象ID：</span>
              <span class="info-value">{{ detailData.compareObj1Id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">对象名称：</span>
              <span class="info-value font-semibold text-purple-500">{{
                detailData.compareObj1Name || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">对象数值：</span>
              <span class="info-value">{{ detailData.compareObj1Value || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 对比对象2信息卡片 -->
        <el-card class="info-card object2-info-card">
          <div class="card-header">对比对象2信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="info-label">对象ID：</span>
              <span class="info-value">{{ detailData.compareObj2Id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">对象名称：</span>
              <span class="info-value font-semibold text-pink-500">{{
                detailData.compareObj2Name || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">对象数值：</span>
              <span class="info-value">{{ detailData.compareObj2Value || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 统计分析结果卡片 -->
        <el-card class="info-card analysis-info-card" :span="2">
          <div class="card-header">统计分析结果</div>
          <div class="card-content analysis-content">
            <div class="analysis-item">
              <span class="info-label">平均值：</span>
              <span class="info-value">{{ detailData.avgValue || '-' }}</span>
            </div>
            <div class="analysis-item">
              <span class="info-label">最大值：</span>
              <span class="info-value font-semibold text-red-500">{{
                detailData.maxValue || '-'
              }}</span>
              <span class="info-sub">（{{ detailData.maxObjName || '-' }}）</span>
            </div>
            <div class="analysis-item">
              <span class="info-label">最小值：</span>
              <span class="info-value font-semibold text-green-500">{{
                detailData.minValue || '-'
              }}</span>
              <span class="info-sub">（{{ detailData.minObjName || '-' }}）</span>
            </div>
            <div class="analysis-item">
              <span class="info-label">最大差异值：</span>
              <span class="info-value">{{ detailData.maxDiffValue || '-' }}</span>
            </div>
            <div class="analysis-item">
              <span class="info-label">最大差异率：</span>
              <span class="info-value">{{
                detailData.maxDiffRate ? detailData.maxDiffRate + '%' : '-'
              }}</span>
            </div>
            <div class="analysis-item">
              <span class="info-label">通用扩展字段1：</span>
              <span class="info-value">{{ detailData.extCommon1 || '-' }}</span>
            </div>
            <div class="analysis-item">
              <span class="info-label">通用扩展字段2：</span>
              <span class="info-value">{{ detailData.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import {
  CompareAnalysisRptApi,
  CompareAnalysisRptVO
} from '@/api/dataHub/operationMonitor/compStatAnalysisReport/compareanalysisrpt'
import { ref } from 'vue'

defineOptions({ name: 'CompareAnalysisRptDetail' })

const message = useMessage()

// 抽屉显示状态
const visible = ref(false)
// 标题
const title = ref('综合对比分析报表详情')
// 加载状态
const loading = ref(false)
// 详情数据
const detailData = ref<Partial<CompareAnalysisRptVO>>({})
// 全屏状态
const isFullscreen = ref(false)

// 打开抽屉
const open = async (id: number) => {
  visible.value = true
  loading.value = true
  try {
    const data = await CompareAnalysisRptApi.getCompareAnalysisRpt(id)
    detailData.value = { ...data }
  } catch (error) {
    message.error('获取详情失败')
    visible.value = false
  } finally {
    loading.value = false
  }
}

// 关闭抽屉
const handleClose = () => {
  visible.value = false
  isFullscreen.value = false
  // 触发父组件的close事件
  emit('close')
}

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleString() : '-')

// 暴露方法
defineExpose({ open })

// 定义事件
const emit = defineEmits(['close'])
</script>

<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f5f7fa;
}

.detail-container {
  padding: 20px;
  height: calc(100% - 55px);
  overflow-y: auto;
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
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.12);
}

.card-header {
  padding: 12px 16px;
  font-weight: 500;
  color: #333;
  border-bottom: 1px solid #f0f0f0;
}

.base-info-card .card-header {
  background-color: #e6f4ff;
}

.report-info-card .card-header {
  background-color: #fff7e6;
}

.object1-info-card .card-header {
  background-color: #f6e6ff;
}

.object2-info-card .card-header {
  background-color: #ffe6ea;
}

.analysis-info-card .card-header {
  background-color: #e6fffa;
}

.card-content {
  padding: 16px;
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.analysis-content {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 12px;
}

.analysis-item {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
}

.analysis-info-card {
  grid-column: span 2;
}

.info-item {
  display: flex;
  align-items: center;
}

.info-label {
  color: #666;
  width: 120px;
  flex-shrink: 0;
}

.info-value {
  flex-grow: 1;
}

.info-sub {
  color: #999;
  margin-left: 8px;
}

@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }

  .analysis-info-card {
    grid-column: span 1;
  }

  .analysis-content {
    grid-template-columns: 1fr;
  }
}
</style>
