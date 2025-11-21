<template>
  <el-drawer
    v-model="drawerVisible"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="custom-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">关键指标汇总报表详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="drawer-content" v-loading="loading">
      <div class="grid-layout">
        <!-- 基本信息卡片 -->
        <el-card class="info-card" hover-shadow>
          <div class="card-header bg-blue-50">
            <span class="text-blue-600 font-medium">基本信息</span>
          </div>
          <div class="card-body">
            <div class="info-item"
              ><span class="label">主键：</span
              ><span class="value">{{ detailData.id || '-' }}</span>
            </div>
            <div class="info-item"
              ><span class="label">报表ID：</span
              ><span class="value font-semibold text-blue-600">{{
                detailData.rptId || '-'
              }}</span></div
            >
            <div class="info-item"
              ><span class="label">统计周期：</span
              ><span class="value">{{ detailData.statCycle || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">统计周期名称：</span
              ><span class="value">{{ detailData.statCycleName || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">报表生成时间：</span
              ><span class="value">{{ formatDate(detailData.rptCreateTime) }}</span></div
            >
            <div class="info-item"
              ><span class="label">报表生成人：</span
              ><span class="value">{{ detailData.rptCreateUser || '-' }}</span></div
            >
          </div>
        </el-card>

        <!-- 行政区划信息卡片 -->
        <el-card class="info-card" hover-shadow>
          <div class="card-header bg-green-50">
            <span class="text-green-600 font-medium">行政区划信息</span>
          </div>
          <div class="card-body">
            <div class="info-item"
              ><span class="label">行政区划代码：</span
              ><span class="value">{{ detailData.adminCode || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">行政区划名称：</span
              ><span class="value font-semibold text-blue-600">{{
                detailData.adminName || '-'
              }}</span></div
            >
          </div>
        </el-card>

        <!-- 指标信息卡片 -->
        <el-card class="info-card" hover-shadow>
          <div class="card-header bg-purple-50">
            <span class="text-purple-600 font-medium">指标信息</span>
          </div>
          <div class="card-body">
            <div class="info-item"
              ><span class="label">指标ID：</span
              ><span class="value">{{ detailData.idxId || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">指标名称：</span
              ><span class="value font-semibold text-blue-600">{{
                detailData.idxName || '-'
              }}</span></div
            >
            <div class="info-item"
              ><span class="label">指标单位：</span
              ><span class="value">{{ detailData.idxUnit || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">指标重要等级：</span>
              <span class="value">
                <span :class="getLevelClass(detailData.idxLevel)">{{
                  detailData.idxLevel || '-'
                }}</span>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 指标数值卡片 -->
        <el-card class="info-card" hover-shadow>
          <div class="card-header bg-yellow-50">
            <span class="text-yellow-600 font-medium">指标数值</span>
          </div>
          <div class="card-body">
            <div class="info-item"
              ><span class="label">指标平均值：</span
              ><span class="value">{{ detailData.idxAvg || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">指标最大值：</span
              ><span class="value">{{ detailData.idxMax || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">超标次数：</span>
              <span class="value">
                <span
                  :class="
                    detailData.exceedCount && detailData.exceedCount > 0
                      ? 'text-red-500 font-medium'
                      : ''
                  "
                >
                  {{ detailData.exceedCount || '-' }}
                </span>
              </span>
            </div>
            <div class="info-item"
              ><span class="label">环比改善率：</span>
              <span class="value">
                <span
                  :class="
                    detailData.momImproveRate && detailData.momImproveRate > 0
                      ? 'text-green-500 font-medium'
                      : detailData.momImproveRate && detailData.momImproveRate < 0
                        ? 'text-red-500 font-medium'
                        : ''
                  "
                >
                  {{
                    detailData.momImproveRate !== undefined ? detailData.momImproveRate + '%' : '-'
                  }}
                </span>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 其他信息卡片 -->
        <el-card class="info-card" hover-shadow colspan="2">
          <div class="card-header bg-gray-50">
            <span class="text-gray-600 font-medium">其他信息</span>
          </div>
          <div class="card-body other-info">
            <div class="info-item"
              ><span class="label">报表备注：</span
              ><span class="value">{{ detailData.rptRemark || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">分类扩展字段1：</span
              ><span class="value">{{ detailData.extCat1 || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">分类扩展字段2：</span
              ><span class="value">{{ detailData.extCat2 || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">通用扩展字段1：</span
              ><span class="value">{{ detailData.extCommon1 || '-' }}</span></div
            >
            <div class="info-item"
              ><span class="label">通用扩展字段2：</span
              ><span class="value">{{ detailData.extCommon2 || '-' }}</span></div
            >
          </div>
        </el-card>
      </div>
    </div>

    <template #footer>
      <el-button @click="drawerVisible = false">关闭</el-button>
    </template>
  </el-drawer>
</template>

<script setup lang="ts">
import {
  KeyIdxSummaryRptApi,
  KeyIdxSummaryRptVO
} from '@/api/dataHub/operationMonitor/compStatAnalysisReport/keyidxsummaryrpt'
import { ref } from 'vue'

defineOptions({ name: 'KeyIdxSummaryRptDrawer' })

// 抽屉状态控制
const drawerVisible = ref(false)
const isFullscreen = ref(false)
const loading = ref(false)
const detailData = ref<KeyIdxSummaryRptVO>({} as KeyIdxSummaryRptVO)

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

// 根据指标等级获取样式
const getLevelClass = (level: string) => {
  switch (level) {
    case '高':
      return 'text-red-500 font-medium'
    case '中':
      return 'text-orange-500 font-medium'
    case '低':
      return 'text-green-500 font-medium'
    default:
      return ''
  }
}

// 打开抽屉并加载数据
const open = async (id: number) => {
  drawerVisible.value = true
  loading.value = true
  try {
    const data = await KeyIdxSummaryRptApi.getKeyIdxSummaryRpt(id)
    detailData.value = data
  } finally {
    loading.value = false
  }
}

// 关闭抽屉时重置数据
const handleClose = () => {
  detailData.value = {} as KeyIdxSummaryRptVO
  drawerVisible.value = false
  isFullscreen.value = false
}

// 暴露方法给父组件
defineExpose({ open })
</script>

<style scoped>
.custom-drawer {
  --el-drawer-bg-color: #f9fafb;
}

.drawer-content {
  padding: 20px;
  height: calc(100% - 50px);
  overflow-y: auto;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
  width: 100%;
}

.info-card {
  border-radius: 8px;
  transition: all 0.3s ease;
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 16px;
  border-radius: 8px 8px 0 0;
  margin: -1px -1px 0;
}

.card-body {
  padding: 16px;
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.other-info {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 12px;
}

.info-item {
  display: flex;
  line-height: 1.6;
}

.label {
  color: #666;
  min-width: 120px;
  flex-shrink: 0;
}

.value {
  color: #333;
  word-break: break-all;
}

/* 马克龙色系背景 */
.bg-blue-50 {
  background-color: #e6f4ff;
}

.text-blue-600 {
  color: #165dff;
}

.bg-green-50 {
  background-color: #f0fdf4;
}

.text-green-600 {
  color: #22c55e;
}

.bg-purple-50 {
  background-color: #f3e8ff;
}

.text-purple-600 {
  color: #9333ea;
}

.bg-yellow-50 {
  background-color: #fffbeb;
}

.text-yellow-600 {
  color: #d97706;
}

.bg-gray-50 {
  background-color: #f3f4f6;
}

.text-gray-600 {
  color: #6b7280;
}

/* 状态颜色 */
.text-red-500 {
  color: #ef4444;
}

.text-orange-500 {
  color: #f97316;
}

.font-medium {
  font-weight: 500;
}

.font-semibold {
  font-weight: 600;
}
</style>
