<template>
  <el-drawer v-model="drawerVisible" :size="isFullscreen ? '100%' : '75%'" @close="handleClose">
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">监测事件预警告警统计详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="detail-container">
      <!-- 内容区 -->
      <div class="detail-content">
        <div class="grid-layout">
          <!-- 基本信息卡片 -->
          <el-card class="info-card base-info-card">
            <div class="card-header">基本信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="info-label">主键：</span>
                <span class="info-value">{{ data.id || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">统计ID：</span>
                <span class="info-value text-blue-600 font-semibold">{{
                  data.warnMonEvtStatId || '-'
                }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">统计周期：</span>
                <span class="info-value">{{ data.statCycle || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">统计人：</span>
                <span class="info-value">{{ data.statUserId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">统计时间：</span>
                <span class="info-value">{{ formatDate(data.statTime) }}</span>
              </div>
            </div>
          </el-card>

          <!-- 事件分类信息卡片 -->
          <el-card class="info-card event-info-card">
            <div class="card-header">事件分类信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="info-label">事件大类ID：</span>
                <span class="info-value">{{ data.evtMajorId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">事件大类名称：</span>
                <span class="info-value text-blue-600 font-semibold">{{
                  data.evtMajorName || '-'
                }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">事件小类ID：</span>
                <span class="info-value">{{ data.evtMinorId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">事件小类名称：</span>
                <span class="info-value text-blue-600 font-semibold">{{
                  data.evtMinorName || '-'
                }}</span>
              </div>
            </div>
          </el-card>

          <!-- 预警统计信息卡片 -->
          <el-card class="info-card stats-info-card">
            <div class="card-header">预警统计信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="info-label">预警总数：</span>
                <span class="info-value text-purple-600 font-semibold">{{
                  data.totalWarnCount || 0
                }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">已办结数：</span>
                <span class="info-value text-green-600 font-semibold">{{
                  data.completedCount || 0
                }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">涉及区域数：</span>
                <span class="info-value">{{ data.regionCount || 0 }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">平均响应时间：</span>
                <span class="info-value">{{ formatDate(data.avgRespTime) }}</span>
              </div>
            </div>
          </el-card>

          <!-- 处置情况卡片 -->
          <el-card class="info-card handle-info-card">
            <div class="card-header">处置情况</div>
            <div class="card-body">
              <div class="info-item">
                <span class="info-label">处置率：</span>
                <span class="info-value">
                  <span :class="getHandleRateClass()">{{ data.handleRate || 0 }}%</span>
                </span>
              </div>
              <div class="info-item">
                <span class="info-label">处置效率评估：</span>
                <span class="info-value">
                  <el-tag :type="getHandleRateTagType()">{{ getHandleRateDesc() }}</el-tag>
                </span>
              </div>
            </div>
          </el-card>

          <!-- 扩展信息卡片 -->
          <el-card class="info-card ext-info-card" :span="2">
            <div class="card-header">扩展信息</div>
            <div class="card-body ext-info-grid">
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
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { EarlyWarnMonEvtVO } from '@/api/dataHub/warningAlarm/zoneWarnAlarm/earlywarnmonevt'

const props = defineProps<{
  visible: boolean
}>()

const emit = defineEmits<{
  (e: 'close'): void
}>()

// 抽屉数据
const data = ref<Partial<EarlyWarnMonEvtVO>>({})
// 全屏状态
const isFullscreen = ref(false)

// 设置详情数据
const setData = (row: EarlyWarnMonEvtVO) => {
  data.value = { ...row }
  isFullscreen.value = false
}

// 关闭抽屉
const handleClose = () => {
  emit('close')
}
// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)
// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleString() : '-')

// 获取处置率样式
const getHandleRateClass = () => {
  const rate = data.value.handleRate || 0
  if (rate >= 90) return 'text-green-600 font-semibold'
  if (rate >= 60) return 'text-blue-600 font-semibold'
  return 'text-red-600 font-semibold'
}

// 获取处置率标签类型
const getHandleRateTagType = () => {
  const rate = data.value.handleRate || 0
  if (rate >= 90) return 'success'
  if (rate >= 60) return 'info'
  return 'danger'
}

// 获取处置率描述
const getHandleRateDesc = () => {
  const rate = data.value.handleRate || 0
  if (rate >= 90) return '优秀'
  if (rate >= 60) return '良好'
  return '待提升'
}

defineExpose({ setData })
</script>

<style scoped>
.detail-container {
  height: 100%;
  padding: 16px;
  overflow-y: auto;
}

.detail-header {
  margin-bottom: 20px;
}

.detail-content {
  padding-bottom: 20px;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.info-card {
  transition: all 0.3s ease;
  border-radius: 8px;
  overflow: hidden;
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
  color: #4e5969;
  border-bottom: 1px solid #f0f2f5;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
}

.event-info-card .card-header {
  background-color: #fff0f0;
}

.stats-info-card .card-header {
  background-color: #fff7e6;
}

.handle-info-card .card-header {
  background-color: #f6ffed;
}

.ext-info-card .card-header {
  background-color: #f0f5ff;
}

.card-body {
  padding: 16px;
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.ext-info-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 12px;
}

.info-item {
  display: flex;
  line-height: 1.6;
}

.info-label {
  flex: 0 0 120px;
  color: #606266;
  font-size: 14px;
}

.info-value {
  flex: 1;
  color: #303133;
  font-size: 14px;
  word-break: break-all;
}

.text-blue-600 {
  color: #4096ff;
}

.text-green-600 {
  color: #52c41a;
}

.text-red-600 {
  color: #f5222d;
}

.text-purple-600 {
  color: #722ed1;
}

.font-semibold {
  font-weight: 500;
}
</style>
