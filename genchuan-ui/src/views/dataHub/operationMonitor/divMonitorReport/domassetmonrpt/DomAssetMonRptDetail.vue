<template>
  <el-drawer v-model="visible" :size="isFullscreen ? '100%' : '75%'" class="detail-drawer">
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">应用场景分域监测报表详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 内容区域 -->
      <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
        <!-- 基本信息卡片 -->
        <el-card class="custom-card">
          <div class="card-header bg-blue-50">
            <span class="text-blue-600 font-medium">基本信息</span>
          </div>
          <div class="card-body">
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
              <span class="info-label">统计周期：</span>
              <span class="info-value">{{ detailData.statCycle || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">统计周期名称：</span>
              <span class="info-value">{{ detailData.statCycleName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">报表生成时间：</span>
              <span class="info-value">{{ formatDate(detailData.rptCreateTime) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">报表生成人：</span>
              <span class="info-value">{{ detailData.rptCreateUser || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 区域信息卡片 -->
        <el-card class="custom-card">
          <div class="card-header bg-purple-50">
            <span class="text-purple-600 font-medium">区域信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">行政区划代码：</span>
              <span class="info-value">{{ detailData.adminCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">行政区划名称：</span>
              <span class="info-value font-semibold text-blue-600">{{
                detailData.adminName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">分域ID：</span>
              <span class="info-value">{{ detailData.domId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">分域名称：</span>
              <span class="info-value">{{ detailData.domName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 资产分类卡片 -->
        <el-card class="custom-card">
          <div class="card-header bg-green-50">
            <span class="text-green-600 font-medium">资产分类信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">资产大类ID：</span>
              <span class="info-value">{{ detailData.assetMajorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">资产大类名称：</span>
              <span class="info-value">{{ detailData.assetMajorName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">资产小类ID：</span>
              <span class="info-value">{{ detailData.assetMinorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">资产小类名称：</span>
              <span class="info-value">{{ detailData.assetMinorName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 资产统计卡片 -->
        <el-card class="custom-card">
          <div class="card-header bg-orange-50">
            <span class="text-orange-600 font-medium">资产统计信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">资产总数：</span>
              <span class="info-value font-semibold text-blue-600">{{
                detailData.totalAssetCount || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">在用资产数：</span>
              <span class="info-value">{{ detailData.inUseAssetCount || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">资产在用率：</span>
              <span class="info-value">
                {{ detailData.assetInUseRate || '-' }}
                <span v-if="detailData.assetInUseRate !== undefined">%</span>
              </span>
            </div>
            <div class="info-item">
              <span class="info-label">平均资产寿命：</span>
              <span class="info-value">{{ detailData.avgAssetLife || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 维护信息卡片 -->
        <el-card class="custom-card">
          <div class="card-header bg-red-50">
            <span class="text-red-600 font-medium">维护与故障信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">维护总次数：</span>
              <span class="info-value">{{ detailData.totalMntCount || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">资产故障事件数：</span>
              <span
                class="info-value font-semibold"
                :class="detailData.assetFaultEvtCount > 0 ? 'text-red-500' : 'text-green-500'"
              >
                {{ detailData.assetFaultEvtCount || 0 }}
              </span>
            </div>
          </div>
        </el-card>

        <!-- 备注信息卡片 -->
        <el-card class="custom-card">
          <div class="card-header bg-gray-50">
            <span class="text-gray-600 font-medium">备注信息</span>
          </div>
          <div class="card-body">
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
            <div class="info-item">
              <span class="info-label">通用扩展字段1：</span>
              <span class="info-value">{{ detailData.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
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
import { DomAssetMonRptVO } from '@/api/dataHub/operationMonitor/divMonitorReport/domassetmonrpt'

defineOptions({ name: 'DomAssetMonRptDetail' })

const visible = ref(false)
const isFullscreen = ref(false)
const detailData = ref<DomAssetMonRptVO>({} as DomAssetMonRptVO)

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

// 打开抽屉
const open = (data: DomAssetMonRptVO) => {
  detailData.value = { ...data }
  visible.value = true
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
  height: 100%;
  overflow-y: auto;
}

.custom-card {
  transition: all 0.3s ease;
  border-radius: 8px;
  overflow: hidden;
  border: none;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

.custom-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 16px;
  margin: 0;
}

.card-body {
  padding: 16px;
}

.info-item {
  display: flex;
  padding: 8px 0;
  border-bottom: 1px dashed #f0f0f0;

  last-child {
    border-bottom: none;
  }
}

.info-label {
  flex: 0 0 120px;
  color: #6b7280;
  font-size: 14px;
}

.info-value {
  flex: 1;
  color: #1f2937;
  font-size: 14px;
  word-break: break-all;
}

/* 马克龙色系 */
.bg-blue-50 {
  background-color: #e0f2fe;
}

.text-blue-600 {
  color: #0284c7;
}

.bg-purple-50 {
  background-color: #f3e8ff;
}

.text-purple-600 {
  color: #7c3aed;
}

.bg-green-50 {
  background-color: #dcfce7;
}

.text-green-600 {
  color: #16a34a;
}

.bg-orange-50 {
  background-color: #fff7ed;
}

.text-orange-600 {
  color: #ea580c;
}

.bg-red-50 {
  background-color: #fee2e2;
}

.text-red-600 {
  color: #dc2626;
}

.bg-gray-50 {
  background-color: #f3f4f6;
}

.text-gray-600 {
  color: #4b5563;
}

.text-red-500 {
  color: #ef4444;
}

.text-green-500 {
  color: #22c55e;
}
</style>
