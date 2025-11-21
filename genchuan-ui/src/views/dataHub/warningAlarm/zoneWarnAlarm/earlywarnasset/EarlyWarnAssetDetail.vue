<template>
  <el-drawer
    v-model="drawerVisible"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">按资产告警预警详情</span>
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
          <div class="info-item">
            <span class="label">主键：</span>
            <span class="value">{{ data?.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计ID：</span>
            <span class="value key-field">{{ data?.warnAssetStatId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计周期：</span>
            <span class="value">{{ data?.statCycle || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计时间：</span>
            <span class="value">{{ formatDate(data?.statTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计人：</span>
            <span class="value">{{ data?.statUserId || '-' }}</span>
          </div>
        </el-card>

        <!-- 资产分类信息卡片 -->
        <el-card class="info-card asset-info-card">
          <div class="card-header">资产分类信息</div>
          <div class="info-item">
            <span class="label">资产大类ID：</span>
            <span class="value">{{ data?.assetMajorId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">资产大类名称：</span>
            <span class="value key-field">{{ data?.assetMajorName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">资产小类ID：</span>
            <span class="value">{{ data?.assetMinorId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">资产小类名称：</span>
            <span class="value key-field">{{ data?.assetMinorName || '-' }}</span>
          </div>
        </el-card>

        <!-- 预警统计信息卡片 -->
        <el-card class="info-card warn-info-card">
          <div class="card-header">预警统计信息</div>
          <div class="info-item">
            <span class="label">预警总数：</span>
            <span class="value status-highlight">{{ data?.totalWarnCount || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">主要故障类型：</span>
            <span class="value">{{ data?.mainFaultType || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">涉及资产数：</span>
            <span class="value">{{ data?.assetCount || '-' }}</span>
          </div>
        </el-card>

        <!-- 维修信息卡片 -->
        <el-card class="info-card repair-info-card">
          <div class="card-header">维修信息</div>
          <div class="info-item">
            <span class="label">维修总成本：</span>
            <span class="value">{{ data?.mntCost || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">已修复资产数：</span>
            <span class="value status-success">{{ data?.repairedAssetCount || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">修复率：</span>
            <span class="value key-field">{{ data?.repairRate || '-' }}%</span>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card ext-info-card" :span="2">
          <div class="card-header">扩展信息</div>
          <div class="grid-2cols">
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
import { EarlyWarnAssetVO } from '@/api/dataHub/warningAlarm/zoneWarnAlarm/earlywarnasset'

const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  data: {
    type: Object as () => EarlyWarnAssetVO | null,
    default: null
  }
})

const emit = defineEmits(['close'])
// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)

const isFullscreen = ref(false)

const handleClose = () => {
  emit('close')
  isFullscreen.value = false
}

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleDateString() : '-'
}
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
  --el-drawer-padding-primary: 20px;
}

.detail-container {
  padding: 10px 0;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.info-card {
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
  transition: all 0.3s ease;
  overflow: hidden;
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.12);
}

.card-header {
  padding: 12px 15px;
  font-weight: 500;
  color: #4e5969;
  font-size: 14px;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
}

.asset-info-card .card-header {
  background-color: #fff2e8;
}

.warn-info-card .card-header {
  background-color: #fff7e6;
}

.repair-info-card .card-header {
  background-color: #f6ffed;
}

.ext-info-card .card-header {
  background-color: #f0f2f5;
}

.ext-info-card {
  grid-column: span 2;
}

::v-deep .el-card__body {
  padding: 15px;
}

.info-item {
  display: flex;
  padding: 8px 0;
  border-bottom: 1px dashed #f0f0f0;
  align-items: center;
}

.info-item:last-child {
  border-bottom: none;
}

.label {
  color: #6b7280;
  min-width: 100px;
  font-size: 13px;
}

.value {
  color: #374151;
  flex: 1;
  font-size: 13px;
}

.key-field {
  color: #165dff;
  font-weight: 500;
}

.status-highlight {
  color: #fa541c;
  font-weight: 500;
}

.status-success {
  color: #52c41a;
  font-weight: 500;
}

.grid-2cols {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 10px;
}

@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }

  .ext-info-card {
    grid-column: span 1;
  }

  .grid-2cols {
    grid-template-columns: 1fr;
  }
}
</style>
