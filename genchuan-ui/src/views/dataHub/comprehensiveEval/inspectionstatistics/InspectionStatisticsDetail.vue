<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    class="statistics-detail-drawer"
  >
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">环卫考核统计详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="detail-container">
      <!-- 内容区域 -->
      <div class="detail-content">
        <div class="grid-layout">
          <!-- 基本信息卡片 -->
          <el-card class="info-card basic-info-card">
            <div class="card-header">基本信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">主键ID：</span>
                <span class="value">{{ data.id || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">考核日期：</span>
                <span class="value">{{ formatDate(data.inspectionDate) }}</span>
              </div>
              <div class="info-item">
                <span class="label">场所类型：</span>
                <span class="value">{{ data.areaType || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">区域/地点名称：</span>
                <span class="value important">{{ data.areaName || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 评分信息卡片 -->
          <el-card class="info-card score-info-card">
            <div class="card-header">评分信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">该场所总得分：</span>
                <span class="value">{{ data.totalScore || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">该类型满分：</span>
                <span class="value">{{ data.maxScore || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">权重：</span>
                <span class="value">{{ data.weight || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">最终得分：</span>
                <span class="value important">{{ data.scoreWeighted || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 其他信息卡片 -->
          <el-card class="info-card other-info-card grid-col-span-2">
            <div class="card-header">其他信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">样本数：</span>
                <span class="value">{{ data.sampleCount || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">考核状态：</span>
                <span class="value status">
                  <el-tag
                    :type="data.inspectionStatus === '已完成' ? 'success' : 'warning'"
                    effect="light"
                  >
                    {{ data.inspectionStatus || '-' }}
                  </el-tag>
                </span>
              </div>
              <div class="info-item">
                <span class="label">创建时间：</span>
                <span class="value">{{ formatDate(data.createTime) }}</span>
              </div>
              <div class="info-item">
                <span class="label">更新时间：</span>
                <span class="value">{{ formatDate(data.updateTime) }}</span>
              </div>
            </div>
          </el-card>
        </div>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { InspectionStatisticsVO } from '@/api/dataHub/comprehensiveEval/inspectionstatistics'

defineOptions({ name: 'InspectionStatisticsDetail' })

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

// 抽屉显示状态
const visible = defineModel<boolean>({ default: false })

// 全屏状态
const isFullscreen = ref(false)

// 详情数据
const data = ref<InspectionStatisticsVO>({
  id: 0,
  inspectionDate: '',
  areaType: '',
  areaName: '',
  totalScore: 0,
  maxScore: 0,
  weight: 0,
  scoreWeighted: 0,
  sampleCount: 0,
  inspectionStatus: ''
})

// 设置详情数据
const setData = (row: InspectionStatisticsVO) => {
  data.value = { ...row }
}

defineExpose({ setData })
</script>

<style scoped>
.statistics-detail-drawer {
  background-color: #f5f7fa;
}

.detail-container {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.detail-content {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

/* 新增：让其他信息卡片跨两列显示 */
.grid-col-span-2 {
  grid-column: span 2;
}

.info-card {
  transition: all 0.3s ease;
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 16px;
  font-size: 15px;
  font-weight: 500;
  border-bottom: 1px solid #f0f2f5;
  margin-bottom: 10px;
}

.basic-info-card .card-header {
  background-color: #e6f7ff;
  color: #1890ff;
}

.score-info-card .card-header {
  background-color: #fff7e6;
  color: #fa8c16;
}

.other-info-card .card-header {
  background-color: #f6ffed;
  color: #52c41a;
}

.card-body {
  padding: 16px;
}

.info-item {
  margin-bottom: 14px;
  line-height: 1.8;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  display: inline-block;
  width: 110px;
  color: #666;
}

.value {
  color: #333;
}

.important {
  color: #1890ff;
  font-weight: 500;
}

.status {
  display: flex;
  align-items: center;
}

/* 响应式调整 */
@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }

  /* 响应式下重置跨列样式，避免显示问题 */
  .grid-col-span-2 {
    grid-column: span 1;
  }
}
</style>
