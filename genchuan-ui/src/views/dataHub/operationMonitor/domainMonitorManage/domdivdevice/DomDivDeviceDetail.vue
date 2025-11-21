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
        <span class="text-lg font-bold text-gray-800">{{ title }}</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基础信息卡片 -->
      <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
        <!-- 基本信息卡片 -->
        <el-card class="card-hover">
          <div class="card-header bg-blue-50">
            <span class="text-blue-600 font-medium">基本信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">分域ID：</span>
              <span class="info-value">{{ detailData.domId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">分域名称：</span>
              <span class="info-value font-semibold text-blue-600">{{
                detailData.domName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">分域编码：</span>
              <span class="info-value">{{ detailData.domCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">分域状态：</span>
              <span class="info-value">
                <el-tag :type="detailData.domStatus ? 'success' : 'danger'">
                  {{ detailData.domStatus ? '启用' : '禁用' }}
                </el-tag>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 设备分类信息卡片 -->
        <el-card class="card-hover">
          <div class="card-header bg-purple-50">
            <span class="text-purple-600 font-medium">设备分类信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">设备大类ID：</span>
              <span class="info-value">{{ detailData.deviceMajorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">设备大类名称：</span>
              <span class="info-value font-semibold text-purple-600">{{
                detailData.deviceMajorName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">设备小类ID：</span>
              <span class="info-value">{{ detailData.deviceMinorId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">设备小类名称：</span>
              <span class="info-value font-semibold text-purple-600">{{
                detailData.deviceMinorName || '-'
              }}</span>
            </div>
          </div>
        </el-card>

        <!-- 设备统计信息卡片 -->
        <el-card class="card-hover">
          <div class="card-header bg-green-50">
            <span class="text-green-600 font-medium">设备统计信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">关联设备数量：</span>
              <span class="info-value">{{ detailData.relDeviceCount || 0 }} 个</span>
            </div>
            <div class="info-item">
              <span class="info-label">设备在线率：</span>
              <span
                class="info-value"
                :class="detailData.deviceOnlineRate < 50 ? 'text-red-500' : 'text-green-600'"
              >
                {{ detailData.deviceOnlineRate || 0 }}%
              </span>
            </div>
            <div class="info-item">
              <span class="info-label">关联点位数量：</span>
              <span class="info-value">{{ detailData.relPtCount || 0 }} 个</span>
            </div>
          </div>
        </el-card>

        <!-- 管理部门信息卡片 -->
        <el-card class="card-hover">
          <div class="card-header bg-orange-50">
            <span class="text-orange-600 font-medium">管理部门信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">部门代码：</span>
              <span class="info-value">{{ detailData.deptCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">部门名称：</span>
              <span class="info-value font-semibold text-orange-600">{{
                detailData.deptName || '-'
              }}</span>
            </div>
          </div>
        </el-card>

        <!-- 时间信息卡片 -->
        <el-card class="card-hover md:col-span-2">
          <div class="card-header bg-teal-50">
            <span class="text-teal-600 font-medium">时间信息</span>
          </div>
          <div class="card-body grid grid-cols-1 md:grid-cols-2 gap-4">
            <div class="info-item">
              <span class="info-label">创建时间(业务)：</span>
              <span class="info-value">{{ formatDate(detailData.createTimeBiz) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">更新时间(业务)：</span>
              <span class="info-value">{{ formatDate(detailData.updateTimeBiz) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">系统创建时间：</span>
              <span class="info-value">{{ formatDate(detailData.createTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展字段卡片 -->
        <el-card class="card-hover md:col-span-2">
          <div class="card-header bg-gray-50">
            <span class="text-gray-600 font-medium">扩展字段信息</span>
          </div>
          <div class="card-body grid grid-cols-1 md:grid-cols-2 gap-4">
            <div class="info-item">
              <span class="info-label">分类扩展字段1：</span>
              <span class="info-value">{{ detailData.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">分类扩展字段2：</span>
              <span class="info-value">{{ detailData.extCat2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">通用扩展字段1：</span>
              <span class="info-value">{{ detailData.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">通用扩展字段2：</span>
              <span class="info-value">{{ detailData.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DomDivDeviceVO } from '@/api/dataHub/operationMonitor/domainMonitorManage/domdivdevice'

defineOptions({ name: 'DomDivDeviceDetail' })

const visible = ref(false)
const title = ref('设备分域详情')
const isFullscreen = ref(false)
const detailData = ref<Partial<DomDivDeviceVO>>({})

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 打开抽屉
const open = (data: DomDivDeviceVO) => {
  detailData.value = { ...data }
  title.value = `设备分域详情 - ${data.domName || ''}`
  visible.value = true
  isFullscreen.value = false
}

// 关闭抽屉
const handleClose = () => {
  visible.value = false
}

// 提供外部调用方法
defineExpose({
  open
})

// 关闭事件
const emit = defineEmits(['close'])
watch(visible, (newVal) => {
  if (!newVal) {
    emit('close')
  }
})
</script>

<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f5f7fa;
}

.detail-container {
  padding: 20px;
}

.card-header {
  padding: 10px 15px;
  margin: -20px -20px 15px;
  border-radius: 4px 4px 0 0;
}

.card-body {
  padding-top: 5px;
}

.info-item {
  display: flex;
  padding: 8px 0;
  border-bottom: 1px dashed #f0f0f0;

  &:last-child {
    border-bottom: none;
  }
}

.info-label {
  flex: 0 0 120px;
  color: #666;
  font-size: 14px;
}

.info-value {
  flex: 1;
  color: #333;
  font-size: 14px;
  word-break: break-all;
}

.card-hover {
  transition: all 0.3s ease;
}

.card-hover:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
}
</style>
