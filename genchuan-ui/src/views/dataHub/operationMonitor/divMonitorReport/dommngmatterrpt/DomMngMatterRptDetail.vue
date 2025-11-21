<template>
  <el-drawer v-model="drawerVisible" :size="isFullscreen ? '100%' : '75%'" class="detail-drawer">
    <!-- 头部区域 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">管理事项分域监测报表详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="drawer-content">
      <!-- 内容区域 -->
      <div class="grid-container">
        <!-- 基本信息卡片 -->
        <el-card class="info-card base-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">基本信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">主键：</span>
            <span class="value">{{ data.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">报表ID：</span>
            <span class="value primary">{{ data.rptId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计周期：</span>
            <span class="value">{{ data.statCycle || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计周期名称：</span>
            <span class="value">{{ data.statCycleName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">报表生成时间：</span>
            <span class="value">{{ formatDate(data.rptCreateTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">报表生成人：</span>
            <span class="value">{{ data.rptCreateUser || '-' }}</span>
          </div>
        </el-card>

        <!-- 行政区划信息卡片 -->
        <el-card class="info-card region-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">行政区划信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">行政区划代码：</span>
            <span class="value">{{ data.adminCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">行政区划名称：</span>
            <span class="value primary">{{ data.adminName || '-' }}</span>
          </div>
        </el-card>

        <!-- 管理事项信息卡片 -->
        <el-card class="info-card matter-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">管理事项信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">管理事项大类ID：</span>
            <span class="value">{{ data.mngMatterMajorId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">管理事项大类名称：</span>
            <span class="value primary">{{ data.mngMatterMajorName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">管理事项小类ID：</span>
            <span class="value">{{ data.mngMatterMinorId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">管理事项小类名称：</span>
            <span class="value primary">{{ data.mngMatterMinorName || '-' }}</span>
          </div>
        </el-card>

        <!-- 分域信息卡片 -->
        <el-card class="info-card domain-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">分域信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">分域ID：</span>
            <span class="value">{{ data.domId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分域名称：</span>
            <span class="value primary">{{ data.domName || '-' }}</span>
          </div>
        </el-card>

        <!-- 处置部门信息卡片 -->
        <el-card class="info-card dept-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">处置部门信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">处置部门代码：</span>
            <span class="value">{{ data.deptCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">处置部门名称：</span>
            <span class="value primary">{{ data.deptName || '-' }}</span>
          </div>
        </el-card>

        <!-- 事件统计信息卡片 -->
        <el-card class="info-card event-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">事件统计信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">事件总数：</span>
            <span class="value">{{ data.totalEvtCount || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">待处置事件数：</span>
            <span class="value status-pending">{{ data.pendEvtCount || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">处置中事件数：</span>
            <span class="value status-handling">{{ data.handlEvtCount || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">已办结事件数：</span>
            <span class="value status-completed">{{ data.completedEvtCount || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">事件办结率：</span>
            <span class="value primary">{{
              data.evtCompleteRate ? data.evtCompleteRate + '%' : '-'
            }}</span>
          </div>
          <div class="info-item">
            <span class="label">平均处置时长：</span>
            <span class="value">{{ data.avgHandleEndure || '-' }}</span>
          </div>
        </el-card>

        <!-- 其他信息卡片 -->
        <el-card class="info-card other-info-card" :span="2">
          <template #header>
            <div class="card-header">
              <span class="card-title">其他信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">报表备注：</span>
            <span class="value">{{ data.rptRemark || '-' }}</span>
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
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DomMngMatterRptVO } from '@/api/dataHub/operationMonitor/divMonitorReport/dommngmatterrpt'

defineOptions({ name: 'DomMngMatterRptDetail' })

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 接收父组件参数
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  }
})

// 暴露事件
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
// 内部状态
const data = ref<DomMngMatterRptVO>({} as DomMngMatterRptVO)
const isFullscreen = ref(false)

// 设置详情数据
const setData = (row: DomMngMatterRptVO) => {
  data.value = { ...row }
}

defineExpose({ setData })
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
}

.drawer-content {
  height: 100%;
  padding: 20px;
  overflow-y: auto;
}

.grid-container {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.info-card {
  transition: all 0.3s ease;
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.12);
}

.card-header {
  background-color: #f0f2f5;
  padding: 12px 15px;
  border-radius: 8px 8px 0 0;
}

.card-title {
  font-weight: 500;
  font-size: 14px;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
}

.region-info-card .card-header {
  background-color: #fff2e8;
}

.matter-info-card .card-header {
  background-color: #fff7e6;
}

.domain-info-card .card-header {
  background-color: #f6ffed;
}

.dept-info-card .card-header {
  background-color: #f0f7ff;
}

.event-info-card .card-header {
  background-color: #fff0f0;
}

.other-info-card {
  grid-column: span 2;
}

.other-info-card .card-header {
  background-color: #f9f0ff;
}

.info-item {
  display: flex;
  padding: 10px 15px;
  border-bottom: 1px dashed #f0f0f0;
}

.info-item:last-child {
  border-bottom: none;
}

.label {
  flex: 0 0 130px;
  color: #606266;
  font-weight: 500;
}

.value {
  flex: 1;
  color: #303133;
}

.value.primary {
  color: #165dff;
  font-weight: 500;
}

.value.status-pending {
  color: #faad14;
  font-weight: 500;
}

.value.status-handling {
  color: #1890ff;
  font-weight: 500;
}

.value.status-completed {
  color: #52c41a;
  font-weight: 500;
}

::v-deep .el-drawer__body {
  padding: 0;
  overflow: hidden;
}

::v-deep .el-card__body {
  padding: 0;
}
</style>
