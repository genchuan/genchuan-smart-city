<template>
  <el-drawer
    v-model="visible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    :fullscreen="isFullscreen"
    :before-close="handleClose"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">管理网格统计详情</span>
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
          <div class="card-content">
            <div class="info-item">
              <span class="label">统计ID：</span>
              <span class="value">{{ detailData.mgStatId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计周期：</span>
              <span class="value key-field">{{ detailData.statCycle || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计周期名称：</span>
              <span class="value key-field">{{ detailData.statCycleName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计时间：</span>
              <span class="value">{{ formatDate(detailData.statTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计人：</span>
              <span class="value">{{ detailData.statUserId || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 行政区划信息卡片 -->
        <el-card class="info-card region-info-card">
          <div class="card-header">行政区划信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">行政区划代码：</span>
              <span class="value key-field">{{ detailData.regionCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">行政区划名称：</span>
              <span class="value">{{ detailData.regionName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 网格员信息卡片 -->
        <el-card class="info-card grid-user-card">
          <div class="card-header">网格员信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">网格员ID：</span>
              <span class="value">{{ detailData.gridUserId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">网格员姓名：</span>
              <span class="value">{{ detailData.gridUserName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">网格员覆盖数：</span>
              <span class="value status-green">{{ detailData.gridUserCoverCount || '-' }} 个</span>
            </div>
          </div>
        </el-card>

        <!-- 网格统计信息卡片 -->
        <el-card class="info-card grid-stat-card">
          <div class="card-header">网格统计信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">网格总数：</span>
              <span class="value">{{ detailData.totalCount || '-' }} 个</span>
            </div>
            <div class="info-item">
              <span class="label">新增网格数：</span>
              <span class="value status-green">{{ detailData.newCount || '-' }} 个</span>
            </div>
            <div class="info-item">
              <span class="label">总面积：</span>
              <span class="value">{{ detailData.totalArea || '-' }} m²</span>
            </div>
          </div>
        </el-card>

        <!-- 单元信息卡片 -->
        <el-card class="info-card unit-info-card">
          <div class="card-header">单元信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">所含单元数范围：</span>
              <span class="value">{{ detailData.unitCountRange || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">总所含单元网格数：</span>
              <span class="value">{{ detailData.totalUnitCount || '-' }} 个</span>
            </div>
            <div class="info-item">
              <span class="label">平均所含单元数：</span>
              <span class="value">{{ detailData.avgUnitCount || '-' }} 个</span>
            </div>
          </div>
        </el-card>

        <!-- 其他信息卡片 -->
        <el-card class="info-card other-info-card">
          <div class="card-header">其他信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">报表备注：</span>
              <span class="value">{{ detailData.rptRemark || '-' }}</span>
            </div>
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
            <div class="info-item">
              <span class="label">统计时间：</span>
              <span class="value">{{ formatDate(detailData.statTime) }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { MngGridRptVO } from '@/api/dataHub/gridManagement/gridStatReport/mnggridrpt'

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleString() : '-')

// 抽屉可见性
const visible = ref(false)
// 标题
const title = ref('管理网格统计详情')
// 是否全屏
const isFullscreen = ref(false)
// 详情数据
const detailData = ref<MngGridRptVO>({} as MngGridRptVO)

// 打开抽屉
const open = (data: MngGridRptVO) => {
  detailData.value = { ...data }
  visible.value = true
  isFullscreen.value = false
}

// 关闭抽屉
const handleClose = () => {
  visible.value = false
}

// 暴露方法
defineExpose({ open })
</script>

<style scoped>
.detail-container {
  padding: 16px;
  background-color: #f9fafb;
  min-height: calc(100vh - 120px);
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
}

.info-card {
  transition: all 0.3s ease;
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 16px;
  font-weight: 600;
  color: #666;
  border-radius: 8px 8px 0 0;
  margin: -1px -1px 16px;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
  border-left: 4px solid #1890ff;
}

.region-info-card .card-header {
  background-color: #fff7e6;
  border-left: 4px solid #faad14;
}

.grid-user-card .card-header {
  background-color: #e6fffb;
  border-left: 4px solid #00b42a;
}

.grid-stat-card .card-header {
  background-color: #f6ffed;
  border-left: 4px solid #52c41a;
}

.unit-info-card .card-header {
  background-color: #fff0f0;
  border-left: 4px solid #f5222d;
}

.other-info-card .card-header {
  background-color: #f0f2ff;
  border-left: 4px solid #722ed1;
}

.card-content {
  padding: 0 16px 16px;
}

.info-item {
  margin-bottom: 12px;
  line-height: 1.6;
}

.label {
  display: inline-block;
  width: 120px;
  color: #666;
}

.value {
  color: #333;
}

.key-field {
  color: #1890ff;
  font-weight: 600;
}

.status-green {
  color: #00b42a;
  font-weight: 600;
}

.status-red {
  color: #f5222d;
  font-weight: 600;
}

/* 响应式调整 */
@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }
}
</style>
