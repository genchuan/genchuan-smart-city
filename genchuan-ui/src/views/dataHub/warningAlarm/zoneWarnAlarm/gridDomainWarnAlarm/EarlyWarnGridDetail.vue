<template>
  <el-drawer
    v-model="drawerVisible"
    :size="isFullscreen ? '100%' : '75%'"
    class="detail-drawer"
    @close="handleClose"
  >
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">按网格告警预警详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="drawer-container">
      <!-- 内容区域 -->
      <div class="content">
        <div class="grid-layout">
          <!-- 基本信息卡片 -->
          <el-card class="info-card base-info-card">
            <div class="card-header">基本信息</div>
            <div class="card-content">
              <div class="info-item">
                <span class="label">统计ID：</span>
                <span class="value font-medium text-blue-500">{{
                  detailData?.warnGridStatId || '-'
                }}</span>
              </div>
              <div class="info-item">
                <span class="label">统计周期：</span>
                <span class="value">{{ detailData?.statCycle || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">网格类型：</span>
                <span class="value">{{ detailData?.gridType || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">网格ID：</span>
                <span class="value">{{ detailData?.gridId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">网格编码：</span>
                <span class="value">{{ detailData?.gridCode || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">网格名称：</span>
                <span class="value font-medium text-blue-500">{{
                  detailData?.gridName || '-'
                }}</span>
              </div>
            </div>
          </el-card>

          <!-- 所属区域卡片 -->
          <el-card class="info-card area-info-card">
            <div class="card-header">所属区域</div>
            <div class="card-content">
              <div class="info-item">
                <span class="label">所属街道代码：</span>
                <span class="value">{{ detailData?.streetCode || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">所属街道名称：</span>
                <span class="value">{{ detailData?.streetName || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 预警统计卡片 -->
          <el-card class="info-card warn-stat-card">
            <div class="card-header">预警统计</div>
            <div class="card-content">
              <div class="info-item">
                <span class="label">预警总数：</span>
                <span class="value">{{ detailData?.totalWarnCount || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">已办结数：</span>
                <span class="value text-green-500">{{ detailData?.completedCount || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">待处置数：</span>
                <span class="value text-red-500 font-medium">{{
                  detailData?.pendCount || '-'
                }}</span>
              </div>
              <div class="info-item">
                <span class="label">主要预警类型：</span>
                <span class="value">{{ detailData?.mainWarnType || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">处置率：</span>
                <span
                  class="value"
                  :class="detailData?.handleRate >= 90 ? 'text-green-500' : 'text-orange-500'"
                >
                  {{ detailData?.handleRate ? detailData.handleRate + '%' : '-' }}
                </span>
              </div>
            </div>
          </el-card>

          <!-- 操作信息卡片 -->
          <el-card class="info-card operate-info-card">
            <div class="card-header">操作信息</div>
            <div class="card-content">
              <div class="info-item">
                <span class="label">统计人：</span>
                <span class="value">{{ detailData?.statUserId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">统计时间：</span>
                <span class="value">{{ formatDate(detailData?.statTime) }}</span>
              </div>
              <div class="info-item">
                <span class="label">创建时间：</span>
                <span class="value">{{ formatDate(detailData?.createTime) }}</span>
              </div>
            </div>
          </el-card>

          <!-- 扩展信息卡片 -->
          <el-card class="info-card ext-info-card" :span="2">
            <div class="card-header">扩展信息</div>
            <div class="card-content">
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
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { EarlyWarnGridVO } from '@/api/dataHub/warningAlarm/zoneWarnAlarm/gridDomainWarnAlarm'

defineOptions({ name: 'EarlyWarnGridDetail' })

// 时间格式化
const formatDate = (val: any) => (val ? new Date(val).toLocaleString() : '-')

// Props
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  detailData: {
    type: Object as () => EarlyWarnGridVO | null,
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
// 全屏状态
const isFullscreen = ref(false)

// 关闭抽屉
const handleClose = () => {
  emit('close')
  isFullscreen.value = false
}
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
}

.drawer-container {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.header {
  padding: 16px 20px;
  border-bottom: 1px solid #e5e7eb;
  background-color: #fff;
}

.content {
  flex: 1;
  padding: 20px;
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
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

.ext-info-card {
  grid-column: span 2;
}

.card-header {
  padding: 12px 16px;
  font-weight: 500;
  font-size: 14px;
  border-bottom: 1px solid #f0f2f5;
  margin-bottom: 10px;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
  color: #1890ff;
}

.area-info-card .card-header {
  background-color: #fff0f0;
  color: #f5222d;
}

.warn-stat-card .card-header {
  background-color: #fff7e6;
  color: #fa8c16;
}

.operate-info-card .card-header {
  background-color: #f6ffed;
  color: #52c41a;
}

.ext-info-card .card-header {
  background-color: #f0f2ff;
  color: #722ed1;
}

.card-content {
  padding: 16px;
}

.info-item {
  display: flex;
  margin-bottom: 12px;
  line-height: 1.6;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  flex: 0 0 120px;
  color: #6b7280;
  font-size: 13px;
}

.value {
  flex: 1;
  color: #1f2937;
  font-size: 13px;
  word-break: break-all;
}

/* 滚动条美化 */
::v-deep .content::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}

::v-deep .content::-webkit-scrollbar-thumb {
  border-radius: 3px;
  background-color: #c9cdd4;
}

::v-deep .content::-webkit-scrollbar-track {
  background-color: transparent;
}
</style>
