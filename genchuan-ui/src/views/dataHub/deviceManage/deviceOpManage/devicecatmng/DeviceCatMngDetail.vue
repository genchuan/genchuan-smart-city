<template>
  <el-drawer v-model="innerVisible" :size="isFullscreen ? '100%' : '75%'" @close="handleClose">
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">设备分类详情</span>
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
          <template #header>
            <div class="card-header">
              <span class="card-title">基础信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value">{{ data?.id ?? '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分类管理ID：</span>
            <span class="value primary">{{ data?.catMngId ?? '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备ID：</span>
            <span class="value primary">{{ data?.deviceId ?? '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备名称：</span>
            <span class="value">{{ data?.deviceName ?? '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备编码：</span>
            <span class="value">{{ data?.deviceCode ?? '-' }}</span>
          </div>
        </el-card>

        <!-- 分类信息卡片 -->
        <el-card class="info-card category-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">设备分类信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">设备大类：</span>
            <span class="value">{{ data?.deviceMajorName ?? '-' }}</span>
            <span class="value-id">({{ data?.deviceMajorId ?? '-' }})</span>
          </div>
          <div class="info-item">
            <span class="label">设备中类：</span>
            <span class="value">{{ data?.deviceMidName ?? '-' }}</span>
            <span class="value-id">({{ data?.deviceMidId ?? '-' }})</span>
          </div>
          <div class="info-item">
            <span class="label">设备小类：</span>
            <span class="value">{{ data?.deviceMinorName ?? '-' }}</span>
            <span class="value-id">({{ data?.deviceMinorId ?? '-' }})</span>
          </div>
        </el-card>

        <!-- 分类操作信息卡片 -->
        <el-card class="info-card operation-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">分类操作信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">分类时间：</span>
            <span class="value">{{ formatDate(data?.catTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">分类人：</span>
            <span class="value">{{ data?.catUser ?? '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分类状态：</span>
            <span class="value">
              <el-tag :type="data?.catStatus === '1' ? 'success' : 'warning'" size="small">
                {{ data?.catStatus === '1' ? '已分类' : '未分类' }}
              </el-tag>
            </span>
          </div>
        </el-card>

        <!-- 调整信息卡片 -->
        <el-card class="info-card adjust-info-card">
          <template #header>
            <div class="card-header">
              <span class="card-title">调整信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">调整时间：</span>
            <span class="value">{{ formatDate(data?.adjustTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">调整人：</span>
            <span class="value">{{ data?.adjustUser ?? '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">调整原因：</span>
            <span class="value">{{ data?.adjustReason ?? '-' }}</span>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card expand-info-card" style="grid-column: span 2">
          <template #header>
            <div class="card-header">
              <span class="card-title">扩展信息</span>
            </div>
          </template>
          <div class="grid-expand">
            <div class="info-item">
              <span class="label">分类扩展字段1：</span>
              <span class="value">{{ data?.extCat1 ?? '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类扩展字段2：</span>
              <span class="value">{{ data?.extCat2 ?? '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段1：</span>
              <span class="value">{{ data?.extCommon1 ?? '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段2：</span>
              <span class="value">{{ data?.extCommon2 ?? '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DeviceCatMngVO } from '@/api/dataHub/deviceManage/deviceOpManage/devicecatmng'

const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  data: {
    type: Object as () => DeviceCatMngVO | null,
    default: null
  }
})

const emit = defineEmits(['close'])

// 解决直接修改props的问题：使用内部状态
const innerVisible = ref(props.visible)
const isFullscreen = ref(false)

// 监听props变化同步到内部状态
watch(
  () => props.visible,
  (newVal) => {
    innerVisible.value = newVal
  }
)

const handleClose = () => {
  emit('close')
  isFullscreen.value = false
}

// 时间格式化
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}
</script>

<style scoped>
.detail-container {
  padding: 20px;
  height: calc(100% - 60px);
  overflow-y: auto;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.grid-expand {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 10px;
}

.info-card {
  border-radius: 8px;
  border: none;
  transition: all 0.3s ease;
  overflow: hidden;
}

.info-card:hover {
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.08);
  transform: translateY(-2px);
}

.card-header {
  padding: 12px 15px;
  background-color: #f0f7ff;
  border-bottom: 1px solid #e6f7ff;
}

.card-title {
  font-weight: 500;
  color: #1890ff;
  font-size: 14px;
}

.base-info-card .card-header {
  background-color: #fff0f0;
  border-bottom: 1px solid #fff1f0;
}

.base-info-card .card-title {
  color: #f5222d;
}

.category-info-card .card-header {
  background-color: #f6ffed;
  border-bottom: 1px solid #f0fff4;
}

.category-info-card .card-title {
  color: #52c41a;
}

.operation-info-card .card-header {
  background-color: #fff7e6;
  border-bottom: 1px solid #fffbe6;
}

.operation-info-card .card-title {
  color: #faad14;
}

.adjust-info-card .card-header {
  background-color: #e6f7ff;
  border-bottom: 1px solid #e6f7ff;
}

.adjust-info-card .card-title {
  color: #1890ff;
}

.expand-info-card .card-header {
  background-color: #f0f2f5;
  border-bottom: 1px solid #f0f2f5;
}

.expand-info-card .card-title {
  color: #4e5969;
}

/* 替换 ::v-deep 写法 */
:deep(.el-card__body) {
  padding: 15px;
}

.info-item {
  margin-bottom: 12px;
  line-height: 1.8;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  display: inline-block;
  width: 100px;
  color: #6b7280;
  font-size: 13px;
}

.value {
  color: #1f2937;
  font-size: 13px;
}

.value-id {
  color: #9ca3af;
  font-size: 12px;
}

.primary {
  color: #165dff;
  font-weight: 500;
}
</style>
