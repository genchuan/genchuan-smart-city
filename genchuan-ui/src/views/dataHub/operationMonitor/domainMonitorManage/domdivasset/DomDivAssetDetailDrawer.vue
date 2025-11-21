<template>
  <el-drawer
    v-model="drawerVisible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">资产分域详情</span>
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
          <div class="card-header">
            <span class="card-title">基本信息</span>
          </div>
          <div class="card-content">
            <div class="info-item">
              <span class="info-label">主键ID：</span>
              <span class="info-value">{{ data.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">分域名称：</span>
              <span class="info-value key-field">{{ data.domName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">分域编码：</span>
              <span class="info-value key-field">{{ data.domCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">分域状态：</span>
              <span class="info-value">
                <el-tag :type="data.domStatus ? 'success' : 'danger'">
                  {{ data.domStatus ? '启用' : '停用' }}
                </el-tag>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 资产分类信息卡片 -->
        <el-card class="info-card category-info-card">
          <div class="card-header">
            <span class="card-title">资产分类信息</span>
          </div>
          <div class="card-content">
            <div class="info-item">
              <span class="info-label">资产大类ID：</span>
              <span class="info-value">{{ data.assetMajorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">资产大类名称：</span>
              <span class="info-value key-field">{{ data.assetMajorName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">资产小类ID：</span>
              <span class="info-value">{{ data.assetMinorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">资产小类名称：</span>
              <span class="info-value key-field">{{ data.assetMinorName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 关联数量信息卡片 -->
        <el-card class="info-card relation-info-card">
          <div class="card-header">
            <span class="card-title">关联数量信息</span>
          </div>
          <div class="card-content">
            <div class="info-item">
              <span class="info-label">关联资产数量：</span>
              <span class="info-value">{{ data.relAssetCount || 0 }} 个</span>
            </div>
            <div class="info-item">
              <span class="info-label">关联点位数量：</span>
              <span class="info-value">{{ data.relPtCount || 0 }} 个</span>
            </div>
            <div class="info-item">
              <span class="info-label">关联设备数量：</span>
              <span class="info-value">{{ data.relDeviceCount || 0 }} 个</span>
            </div>
          </div>
        </el-card>

        <!-- 部门信息卡片 -->
        <el-card class="info-card dept-info-card">
          <div class="card-header">
            <span class="card-title">运维部门信息</span>
          </div>
          <div class="card-content">
            <div class="info-item">
              <span class="info-label">部门代码：</span>
              <span class="info-value">{{ data.deptCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">部门名称：</span>
              <span class="info-value key-field">{{ data.deptName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 时间信息卡片 -->
        <el-card class="info-card time-info-card">
          <div class="card-header">
            <span class="card-title">时间信息</span>
          </div>
          <div class="card-content">
            <div class="info-item">
              <span class="info-label">创建时间(业务)：</span>
              <span class="info-value">{{ formatDate(data.createTimeBiz) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">更新时间(业务)：</span>
              <span class="info-value">{{ formatDate(data.updateTimeBiz) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">系统创建时间：</span>
              <span class="info-value">{{ formatDate(data.createTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card ext-info-card">
          <div class="card-header">
            <span class="card-title">扩展信息</span>
          </div>
          <div class="card-content">
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
import { DomDivAssetVO } from '@/api/dataHub/operationMonitor/domainMonitorManage/domdivasset'

const props = defineProps({
  visible: {
    type: Boolean,
    default: false
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

const data = ref<Partial<DomDivAssetVO>>({})
const isFullscreen = ref(false)

// 设置详情数据
const setData = (row: DomDivAssetVO) => {
  data.value = { ...row }
}

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}

// 关闭抽屉
const handleClose = () => {
  emit('close')
  isFullscreen.value = false
}

defineExpose({ setData })
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
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
  border-radius: 8px;
  overflow: hidden;
  transition: all 0.3s ease;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 16px;
  border-bottom: 1px solid #f0f0f0;
}

.basic-info-card .card-header {
  background-color: #e6f7ff;
}

.category-info-card .card-header {
  background-color: #fff2e8;
}

.relation-info-card .card-header {
  background-color: #fff7e6;
}

.dept-info-card .card-header {
  background-color: #f6ffed;
}

.time-info-card .card-header {
  background-color: #f0f7ff;
}

.ext-info-card .card-header {
  background-color: #fff0f6;
}

.card-title {
  font-weight: 500;
  font-size: 14px;
}

.basic-info-card .card-title {
  color: #1890ff;
}

.category-info-card .card-title {
  color: #fa8c16;
}

.relation-info-card .card-title {
  color: #faad14;
}

.dept-info-card .card-title {
  color: #52c41a;
}

.time-info-card .card-title {
  color: #40a9ff;
}

.ext-info-card .card-title {
  color: #ff4d4f;
}

.card-content {
  padding: 16px;
}

.info-item {
  display: flex;
  padding: 8px 0;
  border-bottom: 1px dashed #f0f0f0;
  font-size: 14px;
}

.info-item:last-child {
  border-bottom: none;
}

.info-label {
  flex: 0 0 120px;
  color: #666;
  font-weight: 400;
}

.info-value {
  flex: 1;
  color: #333;
  word-break: break-all;
}

.key-field {
  color: #1890ff;
  font-weight: 500;
}

::v-deep .el-drawer__body {
  padding: 0;
  overflow-y: auto;
}

@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }
}
</style>
