<template>
  <el-drawer v-model="drawerVisible" :size="isFullscreen ? '100%' : '75%'" class="detail-drawer">
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">基础监测事件报表详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="drawer-content">
      <!-- 内容区域 -->
      <div class="grid-container">
        <!-- 基础信息 -->
        <el-card class="grid-item card-primary">
          <div class="card-header">基础信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">主键ID：</span>
              <span class="info-value">{{ data.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">报表ID：</span>
              <span class="info-value font-semibold text-blue-500">{{ data.rptId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">统计周期：</span>
              <span class="info-value">{{ data.statCycle || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">统计周期名称：</span>
              <span class="info-value">{{ data.statCycleName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">报表生成时间：</span>
              <span class="info-value">{{ formatDate(data.rptCreateTime) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">报表生成人：</span>
              <span class="info-value">{{ data.rptCreateUser || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 行政区划信息 -->
        <el-card class="grid-item card-secondary">
          <div class="card-header">行政区划信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">行政区划代码：</span>
              <span class="info-value">{{ data.adminCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">行政区划名称：</span>
              <span class="info-value font-semibold text-blue-500">{{
                data.adminName || '-'
              }}</span>
            </div>
          </div>
        </el-card>

        <!-- 事件统计信息 -->
        <el-card class="grid-item card-tertiary">
          <div class="card-header">事件统计信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">事件类型：</span>
              <span class="info-value">{{ data.evtType || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">事件总数：</span>
              <span class="info-value font-semibold">{{ data.totalEvtCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">待处置事件数：</span>
              <span class="info-value text-orange-500">{{ data.pendEvtCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">处置中事件数：</span>
              <span class="info-value text-blue-500">{{ data.handlEvtCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">已办结事件数：</span>
              <span class="info-value text-green-500">{{ data.completedEvtCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">事件办结率：</span>
              <span class="info-value">{{
                data.evtCompleteRate ? data.evtCompleteRate + '%' : '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">平均处置时长：</span>
              <span class="info-value">{{
                data.avgDealEndure ? data.avgDealEndure + '分钟' : '-'
              }}</span>
            </div>
          </div>
        </el-card>

        <!-- 其他信息 -->
        <el-card class="grid-item card-quaternary">
          <div class="card-header">其他信息</div>
          <div class="card-body">
            <div class="info-item full-width">
              <span class="info-label">报表备注：</span>
              <span class="info-value">{{ data.rptRemark || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息 -->
        <el-card class="grid-item card-expand">
          <div class="card-header">扩展信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">分类扩展字段1：</span>
              <span class="info-value">{{ data.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">分类扩展字段2：</span>
              <span class="info-value">{{ data.extCat2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">通用扩展字段1：</span>
              <span class="info-value">{{ data.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">通用扩展字段2：</span>
              <span class="info-value">{{ data.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { BasicMonEvtRptVO } from '@/api/dataHub/operationMonitor/basicMonitorReport/basicmonevtrpt'

const props = defineProps<{
  visible: boolean
}>()

const emit = defineEmits<{
  (e: 'close'): void
}>()
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
const data = ref<BasicMonEvtRptVO>({} as BasicMonEvtRptVO)
const isFullscreen = ref(false)

// 设置详情数据
const setData = (row: BasicMonEvtRptVO) => {
  data.value = { ...row }
}

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

defineExpose({ setData })
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
}

.drawer-content {
  height: 100%;
  padding: 0 20px 20px;
  overflow-y: auto;
}

.grid-container {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
  margin-top: 20px;
}

.grid-item {
  transition: all 0.3s ease;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.grid-item:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 15px;
  font-weight: 500;
  color: #333;
  border-bottom: 1px solid #f0f0f0;
  margin-bottom: 15px;
}

.card-primary .card-header {
  background-color: #e6f7ff;
  border-left: 4px solid #1890ff;
}

.card-secondary .card-header {
  background-color: #fff7e6;
  border-left: 4px solid #faad14;
}

.card-tertiary .card-header {
  background-color: #e8f4ea;
  border-left: 4px solid #52c41a;
}

.card-quaternary .card-header {
  background-color: #f6f6ff;
  border-left: 4px solid #722ed1;
}

.card-expand .card-header {
  background-color: #fff0f0;
  border-left: 4px solid #f5222d;
}

.card-body {
  padding: 0 15px 15px;
}

.info-item {
  display: flex;
  margin-bottom: 12px;
  line-height: 1.6;
}

.info-item.full-width {
  flex-direction: column;
}

.info-item.full-width .info-label {
  margin-bottom: 5px;
}

.info-label {
  color: #666;
  min-width: 120px;
  flex-shrink: 0;
}

.info-value {
  color: #333;
  flex-grow: 1;
  word-break: break-all;
}

/* 响应式调整 */
@media (max-width: 768px) {
  .grid-container {
    grid-template-columns: 1fr;
  }
}
</style>
