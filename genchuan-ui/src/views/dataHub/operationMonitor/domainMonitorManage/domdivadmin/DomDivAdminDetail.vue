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
        <span class="text-lg font-bold text-gray-800">行政区划分域详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <div class="grid-layout">
        <!-- 基本信息卡片 -->
        <el-card class="info-card basic-info-card">
          <div class="card-header">基本信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">主键ID：</span>
              <span class="value">{{ detailData.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分域ID：</span>
              <span class="value primary">{{ detailData.domId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分域名称：</span>
              <span class="value primary">{{ detailData.domName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分域编码：</span>
              <span class="value">{{ detailData.domCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分域状态：</span>
              <span class="value">
                <el-tag :type="detailData.domStatus ? 'success' : 'danger'">
                  {{ detailData.domStatus ? '启用' : '禁用' }}
                </el-tag>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 行政区域信息卡片 -->
        <el-card class="info-card region-info-card">
          <div class="card-header">行政区域信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">行政区域级别：</span>
              <span class="value">{{ detailData.adminLevel || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">行政区域代码：</span>
              <span class="value">{{ detailData.adminCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">行政区域名称：</span>
              <span class="value primary">{{ detailData.adminName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 关联信息卡片 -->
        <el-card class="info-card relation-info-card">
          <div class="card-header">关联信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">关联点位数量：</span>
              <span class="value">{{ detailData.relPtCount || 0 }} 个</span>
            </div>
            <div class="info-item">
              <span class="label">关联设备数量：</span>
              <span class="value">{{ detailData.relDeviceCount || 0 }} 个</span>
            </div>
          </div>
        </el-card>

        <!-- 负责人信息卡片 -->
        <el-card class="info-card manager-info-card">
          <div class="card-header">负责人信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">负责人ID：</span>
              <span class="value">{{ detailData.mngrId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">负责人姓名：</span>
              <span class="value primary">{{ detailData.mngrName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 时间信息卡片 -->
        <el-card class="info-card time-info-card">
          <div class="card-header">时间信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">创建时间：</span>
              <span class="value">{{ formatDate(detailData.createTimeSys) }}</span>
            </div>
            <div class="info-item">
              <span class="label">更新时间：</span>
              <span class="value">{{ formatDate(detailData.updateTimeSys) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card extend-info-card">
          <div class="card-header">扩展信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">分类扩展字段1：</span>
              <span class="value">{{ detailData.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类扩展字段2：</span>
              <span class="value">{{ detailData.extCat2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段1：</span>
              <span class="value">{{ detailData.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段2：</span>
              <span class="value">{{ detailData.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DomDivAdminVO } from '@/api/dataHub/operationMonitor/domainMonitorManage/domdivadmin'

defineOptions({ name: 'DomDivAdminDetail' })

const visible = ref(false)
const title = ref('分域详情')
const isFullscreen = ref(false)
const detailData = ref<Partial<DomDivAdminVO>>({})

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}

// 打开抽屉
const open = (data: DomDivAdminVO) => {
  detailData.value = { ...data }
  visible.value = true
  isFullscreen.value = false
}

// 关闭抽屉
const handleClose = () => {
  visible.value = false
  isFullscreen.value = false
}

defineExpose({ open })
</script>

<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f5f7fa;
}

.detail-container {
  padding: 20px;
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
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.12);
}

.card-header {
  padding: 12px 16px;
  font-size: 15px;
  font-weight: 500;
  border-bottom: 1px solid #f0f2f5;
  margin-bottom: 10px;
}

.basic-info-card .card-header {
  background-color: #e6f7ff;
  color: #1890ff;
}

.region-info-card .card-header {
  background-color: #fff2e8;
  color: #fa8c16;
}

.relation-info-card .card-header {
  background-color: #f6ffed;
  color: #52c41a;
}

.manager-info-card .card-header {
  background-color: #fff0f6;
  color: #eb2f96;
}

.time-info-card .card-header {
  background-color: #f0f5ff;
  color: #40a9ff;
}

.extend-info-card .card-header {
  background-color: #f9f0ff;
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
  color: #666;
  font-weight: 500;
}

.value {
  flex: 1;
  color: #333;
  word-break: break-all;
}

.value.primary {
  color: #1890ff;
  font-weight: 500;
}

::v-deep .el-drawer__body {
  padding: 0;
  overflow-y: auto;
}

@media (max-width: 1200px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }
}
</style>
