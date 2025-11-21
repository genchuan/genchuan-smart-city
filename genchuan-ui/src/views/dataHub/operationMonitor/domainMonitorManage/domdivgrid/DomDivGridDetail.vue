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
        <span class="text-lg font-bold text-gray-800">网格分域详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基础信息 -->
      <el-card class="info-card base-info-card">
        <div class="card-header">基础信息</div>
        <div class="info-grid">
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value">{{ data.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分域ID：</span>
            <span class="value">{{ data.domId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分域名称：</span>
            <span class="value primary">{{ data.domName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分域编码：</span>
            <span class="value primary">{{ data.domCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">网格类型：</span>
            <span class="value">{{ data.gridType || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分域状态：</span>
            <span class="value">
              <el-tag :type="data.domStatus ? 'success' : 'danger'">
                {{ data.domStatus ? '启用' : '禁用' }}
              </el-tag>
            </span>
          </div>
        </div>
      </el-card>

      <!-- 网格信息 -->
      <el-card class="info-card grid-info-card">
        <div class="card-header">网格信息</div>
        <div class="info-grid">
          <div class="info-item">
            <span class="label">网格ID：</span>
            <span class="value">{{ data.gridId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">网格编码：</span>
            <span class="value">{{ data.gridCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">网格名称：</span>
            <span class="value primary">{{ data.gridName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">所属行政区域代码：</span>
            <span class="value">{{ data.adminCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">所属行政区域名称：</span>
            <span class="value">{{ data.adminName || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 关联信息 -->
      <el-card class="info-card relation-info-card">
        <div class="card-header">关联信息</div>
        <div class="info-grid">
          <div class="info-item">
            <span class="label">关联点位数量(个)：</span>
            <span class="value">{{ data.relPtCount || 0 }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联设备数量(个)：</span>
            <span class="value">{{ data.relDeviceCount || 0 }}</span>
          </div>
          <div class="info-item">
            <span class="label">网格员ID：</span>
            <span class="value">{{ data.gridUserId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">网格员姓名：</span>
            <span class="value">{{ data.gridUserName || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 时间信息 -->
      <el-card class="info-card time-info-card">
        <div class="card-header">时间信息</div>
        <div class="info-grid">
          <div class="info-item">
            <span class="label">创建时间：</span>
            <span class="value">{{ formatDate(data.createTimeSys) }}</span>
          </div>
          <div class="info-item">
            <span class="label">更新时间：</span>
            <span class="value">{{ formatDate(data.updateTimeSys) }}</span>
          </div>
        </div>
      </el-card>

      <!-- 扩展信息 -->
      <el-card class="info-card extend-info-card">
        <div class="card-header">扩展信息</div>
        <div class="info-grid">
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
        </div>
      </el-card>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DomDivGridVO } from '@/api/dataHub/operationMonitor/domainMonitorManage/domdivgrid'

defineOptions({ name: 'DomDivGridDetail' })

const visible = ref(false)
const title = ref('网格分域详情')
const isFullscreen = ref(false)
const data = ref<DomDivGridVO>({} as DomDivGridVO)

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 打开抽屉
const open = (row: DomDivGridVO) => {
  data.value = { ...row }
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
  background-color: #f5f7fa;
}

.detail-container {
  padding: 15px;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.info-card {
  border-radius: 8px;
  overflow: hidden;
  transition: all 0.3s ease;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.12);
}

.card-header {
  padding: 12px 15px;
  font-weight: 500;
  color: #666;
  border-bottom: 1px solid #f0f0f0;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
  border-bottom-color: #b3e0ff;
}

.grid-info-card .card-header {
  background-color: #fff2e8;
  border-bottom-color: #ffd6bf;
}

.relation-info-card .card-header {
  background-color: #f6ffed;
  border-bottom-color: #d9f7be;
}

.time-info-card .card-header {
  background-color: #fff0f6;
  border-bottom-color: #ffccd8;
}

.extend-info-card .card-header {
  background-color: #f0f2ff;
  border-bottom-color: #c9d1ff;
}

.info-grid {
  padding: 15px;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 15px;
}

.info-item {
  display: flex;
  padding: 5px 0;
}

.label {
  color: #666;
  min-width: 120px;
  font-size: 14px;
}

.value {
  color: #333;
  flex: 1;
  font-size: 14px;
  word-break: break-all;
}

.value.primary {
  color: #165dff;
  font-weight: 500;
}

@media (max-width: 1200px) {
  .detail-container {
    grid-template-columns: 1fr;
  }
}
</style>
