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
        <span class="text-lg font-bold text-gray-800">基础监测指标详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基本信息卡片 -->
      <el-card class="detail-card base-info-card">
        <div class="card-header">基本信息</div>
        <div class="grid-layout">
          <div class="grid-item">
            <span class="label">指标ID：</span>
            <span class="value">{{ data.idxId || '-' }}</span>
          </div>
          <div class="grid-item">
            <span class="label">指标名称：</span>
            <span class="value primary">{{ data.idxName || '-' }}</span>
          </div>
          <div class="grid-item">
            <span class="label">指标编码：</span>
            <span class="value primary">{{ data.idxCode || '-' }}</span>
          </div>
          <div class="grid-item">
            <span class="label">指标单位：</span>
            <span class="value">{{ data.idxUnit || '-' }}</span>
          </div>
          <div class="grid-item">
            <span class="label">数据类型：</span>
            <span class="value">{{ data.dataType || '-' }}</span>
          </div>
          <div class="grid-item">
            <span class="label">数据长度：</span>
            <span class="value">{{ data.dataLength || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 关联信息卡片 -->
      <el-card class="detail-card relation-info-card">
        <div class="card-header">关联信息</div>
        <div class="grid-layout">
          <div class="grid-item">
            <span class="label">关联点位类型：</span>
            <span class="value">{{ data.relPtType || '-' }}</span>
          </div>
          <div class="grid-item">
            <span class="label">预警阈值上限：</span>
            <span class="value">{{ data.warnThresholdMax || '-' }}</span>
          </div>
          <div class="grid-item">
            <span class="label">预警阈值下限：</span>
            <span class="value">{{ data.warnThresholdMin || '-' }}</span>
          </div>
          <div class="grid-item">
            <span class="label">指标说明：</span>
            <span class="value">{{ data.idxDesc || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 状态信息卡片 -->
      <el-card class="detail-card status-info-card">
        <div class="card-header">状态信息</div>
        <div class="grid-layout">
          <div class="grid-item">
            <span class="label">指标状态：</span>
            <span
              class="value status"
              :class="data.idxStatus ? 'status-active' : 'status-inactive'"
            >
              {{ data.idxStatus ? '启用' : '禁用' }}
            </span>
          </div>
          <div class="grid-item">
            <span class="label">创建时间：</span>
            <span class="value">{{ formatDate(data.createTimeSys) }}</span>
          </div>
          <div class="grid-item">
            <span class="label">更新时间：</span>
            <span class="value">{{ formatDate(data.updateTimeSys) }}</span>
          </div>
        </div>
      </el-card>

      <!-- 扩展信息卡片 -->
      <el-card class="detail-card extend-info-card">
        <div class="card-header">扩展信息</div>
        <div class="grid-layout">
          <div class="grid-item">
            <span class="label">分类扩展字段1：</span>
            <span class="value">{{ data.extCat1 || '-' }}</span>
          </div>
          <div class="grid-item">
            <span class="label">分类扩展字段2：</span>
            <span class="value">{{ data.extCat2 || '-' }}</span>
          </div>
          <div class="grid-item">
            <span class="label">通用扩展字段1：</span>
            <span class="value">{{ data.extCommon1 || '-' }}</span>
          </div>
          <div class="grid-item">
            <span class="label">通用扩展字段2：</span>
            <span class="value">{{ data.extCommon2 || '-' }}</span>
          </div>
        </div>
      </el-card>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { BasicMonIdxVO } from '@/api/dataHub/operationMonitor/basicDataManage/basicmonidx'

const visible = ref(false)
const title = ref('指标详情')
const isFullscreen = ref(false)
const data = ref<BasicMonIdxVO>({
  id: 0,
  idxId: '',
  idxName: '',
  idxCode: '',
  idxUnit: '',
  dataType: '',
  dataLength: '',
  relPtType: '',
  warnThresholdMax: '',
  warnThresholdMin: '',
  idxDesc: '',
  idxStatus: false,
  createTimeSys: new Date(),
  updateTimeSys: new Date(),
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

// 打开抽屉
const open = (rowData: BasicMonIdxVO) => {
  data.value = { ...rowData }
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
  padding: 16px;
  border-radius: 8px;
}

.detail-card {
  margin-bottom: 16px;
  transition: all 0.3s ease;
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

.detail-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 16px;
  font-weight: bold;
  border-radius: 8px 8px 0 0;
  margin: -1px;
  margin-bottom: 16px;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
  color: #1890ff;
}

.relation-info-card .card-header {
  background-color: #fff7e6;
  color: #faad14;
}

.status-info-card .card-header {
  background-color: #f6ffed;
  color: #52c41a;
}

.extend-info-card .card-header {
  background-color: #f0f5ff;
  color: #40a9ff;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
  padding: 0 16px 16px;
}

.grid-item {
  padding: 8px 0;
  border-bottom: 1px dashed #f0f0f0;
}

.label {
  color: #666;
  display: inline-block;
  width: 120px;
}

.value {
  color: #333;
}

.value.primary {
  color: #1890ff;
  font-weight: bold;
}

.status {
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.status-active {
  background-color: #f6ffed;
  color: #52c41a;
  border: 1px solid #b7eb8f;
}

.status-inactive {
  background-color: #fff2f0;
  color: #f5222d;
  border: 1px solid #ffccc7;
}
</style>
