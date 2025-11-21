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
        <span class="text-lg font-bold text-gray-800">管理事项分域详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基础信息 -->
      <div class="grid-layout">
        <el-card class="info-card" hoverable>
          <template #header>
            <div class="card-header bg-blue-50">
              <span class="text-blue-600 font-medium">基础信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value font-semibold text-blue-600">{{ detailData.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分域名称：</span>
            <span class="value">{{ detailData.domName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分域编码：</span>
            <span class="value">{{ detailData.domCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分域ID：</span>
            <span class="value">{{ detailData.domId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分域状态：</span>
            <span class="value">
              <el-tag :type="detailData.domStatus ? 'success' : 'danger'">
                {{ detailData.domStatus ? '启用' : '停用' }}
              </el-tag>
            </span>
          </div>
        </el-card>

        <!-- 管理事项信息 -->
        <el-card class="info-card" hoverable>
          <template #header>
            <div class="card-header bg-purple-50">
              <span class="text-purple-600 font-medium">管理事项信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">管理事项大类ID：</span>
            <span class="value">{{ detailData.matterMajorId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">管理事项大类名称：</span>
            <span class="value">{{ detailData.matterMajorName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">管理事项小类ID：</span>
            <span class="value">{{ detailData.matterMinorId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">管理事项小类名称：</span>
            <span class="value">{{ detailData.matterMinorName || '-' }}</span>
          </div>
        </el-card>

        <!-- 关联信息 -->
        <el-card class="info-card" hoverable>
          <template #header>
            <div class="card-header bg-green-50">
              <span class="text-green-600 font-medium">关联信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">关联点位数量(个)：</span>
            <span class="value">{{ detailData.relPtCount || 0 }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联事件数量(件)：</span>
            <span class="value">{{ detailData.relEvtCount || 0 }}</span>
          </div>
        </el-card>

        <!-- 处置部门信息 -->
        <el-card class="info-card" hoverable>
          <template #header>
            <div class="card-header bg-orange-50">
              <span class="text-orange-600 font-medium">处置部门信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">处置部门代码：</span>
            <span class="value">{{ detailData.deptCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">处置部门名称：</span>
            <span class="value">{{ detailData.deptName || '-' }}</span>
          </div>
        </el-card>

        <!-- 时间信息 -->
        <el-card class="info-card" hoverable>
          <template #header>
            <div class="card-header bg-teal-50">
              <span class="text-teal-600 font-medium">时间信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">创建时间(业务)：</span>
            <span class="value">{{ formatDate(detailData.createTimeBiz) }}</span>
          </div>
          <div class="info-item">
            <span class="label">更新时间(业务)：</span>
            <span class="value">{{ formatDate(detailData.updateTimeBiz) }}</span>
          </div>
          <div class="info-item">
            <span class="label">系统创建时间：</span>
            <span class="value">{{ formatDate(detailData.createTime) }}</span>
          </div>
        </el-card>

        <!-- 扩展信息 -->
        <el-card class="info-card" hoverable>
          <template #header>
            <div class="card-header bg-gray-50">
              <span class="text-gray-600 font-medium">扩展信息</span>
            </div>
          </template>
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
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DomDivMngMatterVO } from '@/api/dataHub/operationMonitor/domainMonitorManage/domdivmngmatter'

defineOptions({ name: 'DomDivMngMatterDetail' })

const visible = ref(false)
const title = ref('管理事项分域详情')
const isFullscreen = ref(false)
const detailData = ref<Partial<DomDivMngMatterVO>>({})
const emit = defineEmits(['close'])

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 打开抽屉
const open = (row: DomDivMngMatterVO) => {
  detailData.value = { ...row }
  visible.value = true
  isFullscreen.value = false
}

// 关闭抽屉
const handleClose = () => {
  visible.value = false
  emit('close')
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
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 15px;
  border-radius: 8px 8px 0 0;
}

.info-item {
  display: flex;
  padding: 10px 15px;
  border-bottom: 1px dashed #f0f0f0;
  align-items: center;
}

.info-item:last-child {
  border-bottom: none;
}

.label {
  width: 130px;
  color: #666;
  font-weight: 500;
  flex-shrink: 0;
}

.value {
  color: #333;
  flex-grow: 1;
}

/* 马克龙色系 */
.bg-blue-50 {
  background-color: #e6f4ff;
}

.text-blue-600 {
  color: #165dff;
}

.bg-purple-50 {
  background-color: #f9f0ff;
}

.text-purple-600 {
  color: #722ed1;
}

.bg-green-50 {
  background-color: #f0fff4;
}

.text-green-600 {
  color: #00b42a;
}

.bg-orange-50 {
  background-color: #fff7e6;
}

.text-orange-600 {
  color: #ff7d00;
}

.bg-teal-50 {
  background-color: #e6fffa;
}

.text-teal-600 {
  color: #00b8d9;
}

.bg-gray-50 {
  background-color: #f2f3f5;
}

.text-gray-600 {
  color: #4e5969;
}

@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }
}
</style>
