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
        <span class="text-lg font-bold text-gray-800">按应用场景预警告警详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <div class="grid-layout">
        <!-- 基本信息卡片 -->
        <el-card class="info-card basic-card">
          <div class="card-header">基本信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">统计ID：</span>
              <span class="value">{{ data.warnAppSceneStatId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计周期：</span>
              <span class="value font-semibold text-blue">{{ data.statCycle || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计时间：</span>
              <span class="value">{{ formatDate(data.statTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计人：</span>
              <span class="value">{{ data.statUserId || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 场景信息卡片 -->
        <el-card class="info-card scene-card">
          <div class="card-header">场景信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">场景大类ID：</span>
              <span class="value">{{ data.sceneMajorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">场景大类名称：</span>
              <span class="value font-semibold text-blue">{{ data.sceneMajorName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">场景小类ID：</span>
              <span class="value">{{ data.sceneMinorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">场景小类名称：</span>
              <span class="value font-semibold text-blue">{{ data.sceneMinorName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 预警统计卡片 -->
        <el-card class="info-card warn-card">
          <div class="card-header">预警统计</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">预警总数：</span>
              <span class="value font-semibold">{{ data.totalWarnCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">主要预警类型：</span>
              <span class="value">{{ data.mainWarnType || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">已办结数：</span>
              <span class="value">{{ data.completedCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">处置成功率：</span>
              <span class="value" :class="data.successRate >= 90 ? 'success' : 'warning'">
                {{ data.successRate || 0 }}%
              </span>
            </div>
          </div>
        </el-card>

        <!-- 其他信息卡片 -->
        <el-card class="info-card other-card">
          <div class="card-header">其他信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">涉及网格数：</span>
              <span class="value">{{ data.gridCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类扩展字段1：</span>
              <span class="value">{{ data.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类扩展字段2：</span>
              <span class="value">{{ data.extCat2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段1：</span>
              <span class="value">{{ data.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段2：</span>
              <span class="value">{{ data.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { EarlyWarnAppSceneVO } from '@/api/dataHub/warningAlarm/zoneWarnAlarm/earlywarnappscene'

defineOptions({ name: 'EarlyWarnAppSceneDetail' })

const visible = ref(false)
const title = ref('预警告警详情')
const isFullscreen = ref(false)
const data = ref<EarlyWarnAppSceneVO>({} as EarlyWarnAppSceneVO)

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleString() : '-')

// 打开抽屉
const open = (row: EarlyWarnAppSceneVO) => {
  data.value = { ...row }
  visible.value = true
  isFullscreen.value = false
}

// 关闭抽屉
const handleClose = () => {
  visible.value = false
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
  border-radius: 8px;
  transition: all 0.3s ease;
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
  font-size: 14px;
  font-weight: 500;
  color: #4e5969;
}

.basic-card .card-header {
  background-color: #e6f7ff;
}

.scene-card .card-header {
  background-color: #fff2e8;
}

.warn-card .card-header {
  background-color: #fff7e6;
}

.other-card .card-header {
  background-color: #f6ffed;
}

.card-content {
  padding: 16px;
}

.info-item {
  display: flex;
  padding: 8px 0;
  border-bottom: 1px dashed #f0f0f0;
}

.info-item:last-child {
  border-bottom: none;
}

.label {
  width: 120px;
  color: #6b7280;
  flex-shrink: 0;
}

.value {
  flex: 1;
  color: #374151;
}

.text-blue {
  color: #3b82f6;
}

.success {
  color: #10b981;
  font-weight: 500;
}

.warning {
  color: #f59e0b;
  font-weight: 500;
}

@media screen and (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }
}
</style>
