<template>
  <el-drawer v-model="drawerVisible" :size="isFullscreen ? '100%' : '75%'" @close="handleClose">
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">按行政区划告警预警详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="detail-container" :class="{ fullscreen: isFullscreen }">
      <!-- 详情内容 -->
      <div class="detail-content">
        <!-- 基本信息 -->
        <el-card class="info-card basic-info-card">
          <template #header>
            <div class="card-header basic-header">
              <span class="header-title">基本信息</span>
            </div>
          </template>
          <div class="info-grid">
            <div class="info-item">
              <span class="label">主键ID：</span>
              <span class="value">{{ data?.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计ID：</span>
              <span class="value key-field">{{ data?.warnRegionStatId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计周期：</span>
              <span class="value key-field">{{ data?.statCycle || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计时间：</span>
              <span class="value">{{ formatDate(data?.statTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计人：</span>
              <span class="value">{{ data?.statUserId || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 行政区划信息 -->
        <el-card class="info-card region-info-card">
          <template #header>
            <div class="card-header region-header">
              <span class="header-title">行政区划信息</span>
            </div>
          </template>
          <div class="info-grid">
            <div class="info-item">
              <span class="label">行政区划代码：</span>
              <span class="value key-field">{{ data?.regionCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">行政区划名称：</span>
              <span class="value key-field">{{ data?.regionName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">行政区划级别：</span>
              <span class="value">{{ data?.regionLevel || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 预警数量信息 -->
        <el-card class="info-card warning-info-card">
          <template #header>
            <div class="card-header warning-header">
              <span class="header-title">预警数量信息</span>
            </div>
          </template>
          <div class="info-grid">
            <div class="info-item">
              <span class="label">预警总数：</span>
              <span class="value count-total">{{ data?.totalWarnCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">一般预警数：</span>
              <span class="value count-normal">{{ data?.normalLevelCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">较重预警数：</span>
              <span class="value count-heavy">{{ data?.heavyLevelCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">严重预警数：</span>
              <span class="value count-serious">{{ data?.seriousLevelCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">特别严重预警数：</span>
              <span class="value count-critical">{{ data?.criticalLevelCount || 0 }}</span>
            </div>
          </div>
        </el-card>

        <!-- 处置情况信息 -->
        <el-card class="info-card handle-info-card">
          <template #header>
            <div class="card-header handle-header">
              <span class="header-title">处置情况信息</span>
            </div>
          </template>
          <div class="info-grid">
            <div class="info-item">
              <span class="label">已办结数：</span>
              <span class="value status-completed">{{ data?.completedCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">待处置数：</span>
              <span class="value status-pending">{{ data?.pendCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">处置率：</span>
              <span class="value rate-value">{{ data?.handleRate || 0 }}%</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展字段信息 -->
        <el-card class="info-card ext-info-card">
          <template #header>
            <div class="card-header ext-header">
              <span class="header-title">扩展字段信息</span>
            </div>
          </template>
          <div class="info-grid">
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
import { EarlyWarnRegionVO } from '@/api/dataHub/warningAlarm/zoneWarnAlarm/adminDivDomainWarnAlarm'

defineOptions({ name: 'EarlyWarnRegionDetail' })

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

// 接收的参数
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  data: {
    type: Object as () => EarlyWarnRegionVO | null,
    default: null
  }
})

// 抛出的事件
const emit = defineEmits(['close'])
// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)
// 全屏状态
const isFullscreen = ref(false)

// 关闭抽屉
const handleClose = () => {
  emit('close')
  isFullscreen.value = false // 关闭时退出全屏
}
</script>

<style scoped>
.detail-container {
  background-color: #f5f7fa;
  height: 100%;
  padding: 15px;
  overflow-y: auto;
}

.detail-container.fullscreen {
  padding: 20px;
}

.detail-content {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.info-card {
  border: none;
  transition: all 0.3s ease;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 15px;
  border-bottom: 1px solid #f0f2f5;
}

.header-title {
  font-weight: 600;
  font-size: 14px;
}

.basic-header {
  background-color: #e6f7ff;
}

.basic-header .header-title {
  color: #1890ff;
}

.region-header {
  background-color: #fff0f0;
}

.region-header .header-title {
  color: #f5222d;
}

.warning-header {
  background-color: #fff7e6;
}

.warning-header .header-title {
  color: #fa8c16;
}

.handle-header {
  background-color: #f6ffed;
}

.handle-header .header-title {
  color: #52c41a;
}

.ext-header {
  background-color: #f0f5ff;
}

.ext-header .header-title {
  color: #40a9ff;
}

.info-grid {
  padding: 15px;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 15px;
}

.info-item {
  padding: 5px 0;
  font-size: 13px;
}

.label {
  color: #666;
  margin-right: 8px;
}

.value {
  color: #333;
}

.key-field {
  color: #1890ff;
  font-weight: 600;
}

.count-total {
  color: #1890ff;
  font-weight: 600;
}

.count-normal {
  color: #52c41a;
  font-weight: 600;
}

.count-heavy {
  color: #fa8c16;
  font-weight: 600;
}

.count-serious {
  color: #faad14;
  font-weight: 600;
}

.count-critical {
  color: #f5222d;
  font-weight: 600;
}

.status-completed {
  color: #52c41a;
  font-weight: 600;
}

.status-pending {
  color: #fa8c16;
  font-weight: 600;
}

.rate-value {
  color: #1890ff;
  font-weight: 600;
}

/* 响应式调整 */
@media (max-width: 1200px) {
  .detail-content {
    grid-template-columns: 1fr;
  }
}
</style>
