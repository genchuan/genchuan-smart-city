<template>
  <el-drawer v-model="drawerVisible" :size="isFullscreen ? '100%' : '75%'" class="detail-drawer">
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2 mb-6">
        <span class="text-lg font-bold text-gray-800">预警告警时间维度统计详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="detail-container">
      <!-- 详情内容 -->
      <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
        <!-- 基本信息卡片 -->
        <el-card class="info-card base-info-card">
          <div class="card-header">基本信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">主键：</span>
              <span class="value">{{ detailData?.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计ID：</span>
              <span class="value primary">{{ detailData?.timeStatId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计维度：</span>
              <span class="value">{{ detailData?.timeDimension || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计人：</span>
              <span class="value">{{ detailData?.statUserId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计时间：</span>
              <span class="value">{{ formatDate(detailData?.statTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 周期信息卡片 -->
        <el-card class="info-card cycle-info-card">
          <div class="card-header">周期信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">统计周期值：</span>
              <span class="value">{{ detailData?.statCycleValue || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计周期名称：</span>
              <span class="value primary">{{ detailData?.statCycleName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 预警信息卡片 -->
        <el-card class="info-card warn-info-card">
          <div class="card-header">预警信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">预警总数：</span>
              <span class="value status-warning">{{ detailData?.totalWarnCount || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">主要预警类型：</span>
              <span class="value">{{ detailData?.mainWarnType || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">主要预警类型数量：</span>
              <span class="value">{{ detailData?.mainTypeCount || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 处置信息卡片 -->
        <el-card class="info-card handle-info-card">
          <div class="card-header">处置信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">已办结数：</span>
              <span class="value status-success">{{ detailData?.completedCount || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">处置率：</span>
              <span class="value primary">{{
                detailData?.handleRate ? detailData.handleRate * 100 + '%' : '-'
              }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card ext-info-card md:col-span-2">
          <div class="card-header">扩展信息</div>
          <div class="card-content grid grid-cols-1 md:grid-cols-2 gap-4">
            <div class="info-item">
              <span class="label">分类扩展字段1：</span>
              <span class="value">{{ detailData?.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类扩展字段2：</span>
              <span class="value">{{ detailData?.extCat2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段1：</span>
              <span class="value">{{ detailData?.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段2：</span>
              <span class="value">{{ detailData?.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { EarlyWarnTimeVO } from '@/api/dataHub/warningAlarm/warnAlarmStatAnalysis/earlywarntime'

defineOptions({ name: 'EarlyWarnTimeDetail' })

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  const date = new Date(val)
  return date.toLocaleString()
}

// Props
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  detailData: {
    type: Object as () => EarlyWarnTimeVO | null,
    default: null
  }
})

// Emits
const emit = defineEmits(['close'])
// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)
// 添加对 drawerVisible 的监听 以解决drawerVisible值的同步问题
watch(
  () => drawerVisible.value,
  (newVal) => {
    if (!newVal) {
      emit('close') // 当抽屉关闭时，通知父组件重置 detailVisible
    }
  }
)
// 全屏状态
const isFullscreen = ref(false)

// 监听visible变化，重置全屏状态
watch(
  () => props.visible,
  (newVal) => {
    if (!newVal) {
      isFullscreen.value = false
    }
  }
)
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

.info-card {
  transition: all 0.3s ease;
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 16px;
  font-size: 14px;
  font-weight: 500;
  border-bottom: 1px solid #f0f2f5;
  margin-bottom: 16px;
}

.card-content {
  padding: 0 16px 16px;
}

.info-item {
  display: flex;
  margin-bottom: 12px;
  line-height: 1.8;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  width: 130px;
  color: #606266;
  flex-shrink: 0;
}

.value {
  color: #303133;
  flex: 1;
  word-break: break-all;
}

/* 颜色样式 */
.primary {
  color: #409eff;
  font-weight: 500;
}

.status-success {
  color: #52c41a;
  font-weight: 500;
}

.status-warning {
  color: #faad14;
  font-weight: 500;
}

/* 卡片标题背景色 - 马克龙色系 */
.base-info-card .card-header {
  background-color: #e6f7ff;
  color: #1890ff;
}

.cycle-info-card .card-header {
  background-color: #fff7e6;
  color: #fa8c16;
}

.warn-info-card .card-header {
  background-color: #fff1f0;
  color: #f5222d;
}

.handle-info-card .card-header {
  background-color: #f6ffed;
  color: #52c41a;
}

.ext-info-card .card-header {
  background-color: #f0f2ff;
  color: #722ed1;
}
</style>
