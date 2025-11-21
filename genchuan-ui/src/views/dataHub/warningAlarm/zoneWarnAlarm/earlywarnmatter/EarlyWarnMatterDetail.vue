<template>
  <el-drawer v-model="visible" :size="isFullscreen ? '100%' : '75%'" class="detail-drawer">
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">按管理事项告警预警详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="drawer-content">
      <!-- 内容区 -->
      <div class="content-wrapper">
        <div class="grid-layout">
          <!-- 基本信息 -->
          <el-card class="grid-item card-hover">
            <template #header>
              <div class="card-header bg-blue-50">
                <span class="text-blue-600 font-medium">基本信息</span>
              </div>
            </template>
            <div class="info-item">
              <span class="label">统计ID：</span>
              <span class="value font-medium text-blue-600">{{
                data.warnMngMatterStatId || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计周期：</span>
              <span class="value">{{ data.statCycle || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计时间：</span>
              <span class="value">{{ formatDate(data.statTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计人：</span>
              <span class="value">{{ data.statUserId || '-' }}</span>
            </div>
          </el-card>

          <!-- 事项分类信息 -->
          <el-card class="grid-item card-hover">
            <template #header>
              <div class="card-header bg-purple-50">
                <span class="text-purple-600 font-medium">事项分类信息</span>
              </div>
            </template>
            <div class="info-item">
              <span class="label">事项大类ID：</span>
              <span class="value font-medium text-blue-600">{{ data.matterMajorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">事项大类名称：</span>
              <span class="value">{{ data.matterMajorName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">事项小类ID：</span>
              <span class="value font-medium text-blue-600">{{ data.matterMinorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">事项小类名称：</span>
              <span class="value">{{ data.matterMinorName || '-' }}</span>
            </div>
          </el-card>

          <!-- 处置情况 -->
          <el-card class="grid-item card-hover">
            <template #header>
              <div class="card-header bg-green-50">
                <span class="text-green-600 font-medium">处置情况</span>
              </div>
            </template>
            <div class="info-item">
              <span class="label">预警总数：</span>
              <span class="value font-medium">{{ data.totalWarnCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">已办结数：</span>
              <span class="value text-green-600">{{ data.completedCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">超时未处置数：</span>
              <span class="value text-red-600">{{ data.overtimeCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">处置率：</span>
              <span
                class="value"
                :class="data.handleRate > 80 ? 'text-green-600' : 'text-orange-600'"
              >
                {{ data.handleRate || 0 }}%
              </span>
            </div>
          </el-card>

          <!-- 部门信息 -->
          <el-card class="grid-item card-hover">
            <template #header>
              <div class="card-header bg-amber-50">
                <span class="text-amber-600 font-medium">部门信息</span>
              </div>
            </template>
            <div class="info-item">
              <span class="label">主要处置部门代码：</span>
              <span class="value font-medium text-blue-600">{{ data.mainDeptCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">主要处置部门名称：</span>
              <span class="value">{{ data.mainDeptName || '-' }}</span>
            </div>
          </el-card>

          <!-- 扩展信息 -->
          <el-card class="grid-item card-hover" :span="2">
            <template #header>
              <div class="card-header bg-gray-50">
                <span class="text-gray-600 font-medium">扩展信息</span>
              </div>
            </template>
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
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { EarlyWarnMatterVO } from '@/api/dataHub/warningAlarm/zoneWarnAlarm/earlywarnmatter'

defineOptions({ name: 'EarlyWarnMatterDetail' })

const visible = defineModel<boolean>({ default: false })
watch(visible, (newVal) => {
  console.log('子组件visible变化：', newVal) // 新增打印
})
const isFullscreen = ref(false)
const data = ref<Partial<EarlyWarnMatterVO>>({})

// 设置详情数据
const setData = (row: EarlyWarnMatterVO) => {
  data.value = { ...row }
}

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}

defineExpose({ setData })
</script>

<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f5f7fa;
}

.drawer-content {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.header {
  padding: 16px 24px;
  border-bottom: 1px solid #eee;
  background-color: #fff;
}

.content-wrapper {
  flex: 1;
  padding: 24px;
  overflow-y: auto;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.grid-item {
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

.grid-item[span='2'] {
  grid-column: span 2;
}

.card-header {
  padding: 12px 16px;
  border-bottom: 1px solid #f0f0f0;
}

.card-hover {
  transition: all 0.3s ease;
}

.card-hover:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.info-item {
  display: flex;
  padding: 10px 16px;
  border-bottom: 1px dashed #f0f0f0;
  align-items: center;
}

.info-item:last-child {
  border-bottom: none;
}

.label {
  width: 140px;
  color: #666;
  flex-shrink: 0;
}

.value {
  flex: 1;
  color: #333;
  word-break: break-all;
}

::v-deep .el-card__body {
  padding: 0;
}
</style>
