<template>
  <el-drawer
    v-model="visibleComputed"
    :title="''"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="asset-detail-drawer"
  >
    <!-- 抽屉头部（含全屏切换） -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">资产信息详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <!-- 抽屉内容 -->
    <div class="detail-container">
      <!-- 基础信息区域（Grid两列布局） -->
      <div class="grid-layout base-info-section">
        <!-- 基础资产信息卡片 -->
        <el-card class="info-card base-asset-card">
          <template #header>
            <div class="card-header bg-blue-50 text-blue-600">
              <span class="font-medium">基础资产信息</span>
            </div>
          </template>
          <div class="info-grid">
            <div class="info-item">
              <span class="label">主键ID：</span>
              <span class="value">{{ detailData.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">资产ID：</span>
              <span class="value font-semibold text-blue-600">{{ detailData.assetId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">资产名称：</span>
              <span class="value">{{ detailData.assetName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">资产类型：</span>
              <span class="value">{{ detailData.assetType || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">资产标签：</span>
              <span class="value">{{ detailData.assetLabel || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">外部ID：</span>
              <span class="value">{{ detailData.externalId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">版本号：</span>
              <span class="value">{{ detailData.version || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 租户客户信息卡片 -->
        <el-card class="info-card tenant-customer-card">
          <template #header>
            <div class="card-header bg-green-50 text-green-600">
              <span class="font-medium">租户客户信息</span>
            </div>
          </template>
          <div class="info-grid">
            <div class="info-item">
              <span class="label">系统租户ID：</span>
              <span class="value font-semibold text-blue-600">{{ detailData.tenantIdSys || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">客户ID：</span>
              <span class="value">{{ detailData.customerId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">客户标题：</span>
              <span class="value">{{ detailData.customerTitle || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">客户是否公开：</span>
              <span class="value">
                <el-tag :type="detailData.customerIsPublic ? 'success' : 'danger'">
                  {{ detailData.customerIsPublic ? '是' : '否' }}
                </el-tag>
              </span>
            </div>
            <div class="info-item">
              <span class="label">创建时间戳：</span>
              <span class="value">{{ formatDate(detailData.createdTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建时间：</span>
              <span class="value">{{ formatDate(detailData.createTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 资产档案信息卡片（跨列） -->
        <el-card class="info-card asset-profile-card span-2">
          <template #header>
            <div class="card-header bg-purple-50 text-purple-600">
              <span class="font-medium">资产档案信息</span>
            </div>
          </template>
          <div class="info-grid profile-grid">
            <div class="info-item">
              <span class="label">资产档案ID：</span>
              <span class="value font-semibold text-blue-600">{{ detailData.assetProfileId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">资产档案名称：</span>
              <span class="value">{{ detailData.assetProfileName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">附加信息：</span>
              <span class="value json-value">
                <pre>{{ formatJson(detailData.additionalInfo) }}</pre>
              </span>
            </div>
          </div>
        </el-card>
      </div>

      <!-- 标签页区域（属性列表、关联设备列表） -->
      <el-tabs v-model="activeTab" class="tabs-section mt-6">
        <el-tab-pane label="属性列表" name="attributes">
          <el-card class="tabs-card">
            <div class="json-container">
              <pre>{{ formatJson(detailData.attributes) }}</pre>
            </div>
          </el-card>
        </el-tab-pane>
        <el-tab-pane label="关联设备列表" name="contextDevices">
          <el-card class="tabs-card">
            <div class="json-container">
              <pre>{{ formatJson(detailData.contextDevices) }}</pre>
            </div>
          </el-card>
        </el-tab-pane>
      </el-tabs>
    </div>
  </el-drawer>
</template>
<script setup lang="ts">
import { AssetInfoVO } from '@/api/dataHub/assetManagement/assetinfo'
import { computed, ref } from 'vue'

defineOptions({ name: 'AssetInfoDetailDrawer' })

// 接收父组件参数
const props = defineProps<{
  visible: boolean
  detailData: AssetInfoVO
}>()

// 向父组件触发事件
const emit = defineEmits(['update:visible', 'close'])

// 处理visible双向绑定（避免直接修改props）
const visibleComputed = computed({
  get() {
    return props.visible
  },
  set(value) {
    emit('update:visible', value)
  }
})

// 状态管理
const isFullscreen = ref(false)
const activeTab = ref('attributes') // 默认激活“属性列表”标签

/** 格式化日期（支持时间戳和字符串） */
const formatDate = (val: any) => {
  if (!val) return '-'
  // 处理时间戳（数字或字符串转数字）
  const timestamp = typeof val === 'string' ? Number(val) : val
  return new Date(timestamp).toLocaleString()
}

/** 格式化JSON（美化显示） */
const formatJson = (val: any) => {
  if (!val) return '{}'
  try {
    // 若为字符串先解析，再格式化
    const jsonData = typeof val === 'string' ? JSON.parse(val) : val
    return JSON.stringify(jsonData, null, 2)
  } catch {
    return 'JSON格式错误'
  }
}

/** 关闭抽屉 */
const handleClose = () => {
  emit('close')
  isFullscreen.value = false // 重置全屏状态
  activeTab.value = 'attributes' // 重置标签页
}
</script>

<style scoped>
.asset-detail-drawer {
  background-color: #f5f7fa; /* 浅灰背景 */
}

.detail-container {
  padding: 20px;
  height: calc(100% - 60px);
  overflow-y: auto;
}

/* 基础信息Grid布局 */
.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

/* 跨列样式 */
.span-2 {
  grid-column: span 2;
}

/* 卡片样式 */
.info-card {
  border: none;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
  overflow: hidden;
}

/* 卡片Hover效果 */
.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
}

/* 卡片头部样式 */
.card-header {
  padding: 12px 16px;
  font-size: 14px;
}

/* 信息网格布局 */
.info-grid {
  padding: 16px;
  display: grid;
  grid-template-columns: 1fr;
  gap: 12px;
}

/* 档案信息特殊网格 */
.profile-grid {
  grid-template-columns: repeat(2, 1fr);
}

/* 信息项样式 */
.info-item {
  display: flex;
  align-items: flex-start;
  line-height: 1.6;
}

/* 标签样式 */
.label {
  flex: 0 0 120px;
  color: #6b7280; /* 标签文字颜色 */
  font-size: 14px;
}

/* 值样式 */
.value {
  flex: 1;
  color: #374151; /* 主文字深灰 */
  font-size: 14px;
  word-break: break-all;
}

/* JSON值样式 */
.json-value pre {
  margin: 0;
  padding: 8px;
  background-color: #f9fafb;
  border-radius: 4px;
  font-size: 12px;
  color: #374151;
  overflow-x: auto;
}

/* 标签页区域样式 */
.tabs-section {
  margin-top: 20px;
}

.tabs-card {
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  border-radius: 8px;
}

/* JSON容器样式 */
.json-container {
  padding: 16px;
}

.json-container pre {
  margin: 0;
  padding: 12px;
  background-color: #f9fafb;
  border-radius: 6px;
  font-size: 13px;
  color: #374151;
  overflow-x: auto;
}

/* 适配响应式 */
@media (max-width: 768px) {
  .grid-layout,
  .profile-grid {
    grid-template-columns: 1fr;
  }

  .span-2 {
    grid-column: span 1;
  }
}

/* 修复Element-Plus样式冲突 */
::v-deep .el-drawer__body {
  padding: 0;
}

::v-deep .el-drawer__header {
  border-bottom: 1px solid #e5e7eb;
  margin-bottom: 0;
}

::v-deep .el-tabs__content {
  padding: 16px 0 0;
}
</style>
