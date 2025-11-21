<template>
  <el-drawer
    v-model="visible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    class="detail-drawer"
  >
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">管理部件分域详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="detail-container">
      <!-- 内容区 -->
      <div class="detail-content">
        <div class="grid-layout">
          <!-- 基本信息卡片 -->
          <el-card class="info-card" hoverable>
            <div class="card-header macaron-blue">
              <span class="card-title">基本信息</span>
            </div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">主键ID：</span>
                <span class="value">{{ formData.id || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">分域名称：</span>
                <span class="value key-value">{{ formData.domName || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">分域编码：</span>
                <span class="value key-value">{{ formData.domCode || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">分域ID：</span>
                <span class="value">{{ formData.domId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">分域状态：</span>
                <span class="value">
                  <el-tag :type="formData.domStatus ? 'success' : 'danger'">
                    {{ formData.domStatus ? '启用' : '禁用' }}
                  </el-tag>
                </span>
              </div>
            </div>
          </el-card>

          <!-- 部件信息卡片 -->
          <el-card class="info-card" hoverable>
            <div class="card-header macaron-purple">
              <span class="card-title">部件信息</span>
            </div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">管理部件大类ID：</span>
                <span class="value">{{ formData.compMajorId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">管理部件大类名称：</span>
                <span class="value key-value">{{ formData.compMajorName || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">管理部件小类ID：</span>
                <span class="value">{{ formData.compMinorId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">管理部件小类名称：</span>
                <span class="value key-value">{{ formData.compMinorName || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 关联信息卡片 -->
          <el-card class="info-card" hoverable>
            <div class="card-header macaron-green">
              <span class="card-title">关联信息</span>
            </div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">关联点位数量：</span>
                <span class="value key-value">{{ formData.relPtCount || 0 }} 个</span>
              </div>
              <div class="info-item">
                <span class="label">关联设备数量：</span>
                <span class="value key-value">{{ formData.relDeviceCount || 0 }} 个</span>
              </div>
            </div>
          </el-card>

          <!-- 部门信息卡片 -->
          <el-card class="info-card" hoverable>
            <div class="card-header macaron-pink">
              <span class="card-title">主管部门信息</span>
            </div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">主管部门代码：</span>
                <span class="value">{{ formData.deptCode || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">主管部门名称：</span>
                <span class="value key-value">{{ formData.deptName || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 时间信息卡片 -->
          <el-card class="info-card" hoverable>
            <div class="card-header macaron-yellow">
              <span class="card-title">时间信息</span>
            </div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">创建时间：</span>
                <span class="value">{{ formatDate(formData.createTimeSys) }}</span>
              </div>
              <div class="info-item">
                <span class="label">更新时间：</span>
                <span class="value">{{ formatDate(formData.updateTimeSys) }}</span>
              </div>
            </div>
          </el-card>

          <!-- 扩展信息卡片 -->
          <el-card class="info-card" hoverable>
            <div class="card-header macaron-cyan">
              <span class="card-title">扩展信息</span>
            </div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">分类扩展字段1：</span>
                <span class="value">{{ formData.extCat1 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">分类扩展字段2：</span>
                <span class="value">{{ formData.extCat2 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">通用扩展字段1：</span>
                <span class="value">{{ formData.extCommon1 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">通用扩展字段2：</span>
                <span class="value">{{ formData.extCommon2 || '-' }}</span>
              </div>
            </div>
          </el-card>
        </div>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DomDivMngCompVO } from '@/api/dataHub/operationMonitor/domainMonitorManage/domdivmngcomp'

defineOptions({ name: 'DomDivMngCompDetail' })

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}

// 抽屉状态
const visible = ref(false)
const title = ref('管理部件分域详情')
const isFullscreen = ref(false)
const formData = ref<Partial<DomDivMngCompVO>>({})

// 打开抽屉
const open = (data: DomDivMngCompVO) => {
  visible.value = true
  formData.value = { ...data }
  isFullscreen.value = false
}

// 关闭抽屉
const close = () => {
  visible.value = false
}

// 暴露方法
defineExpose({
  open,
  close
})

// 定义关闭事件
const emit = defineEmits(['close'])
watch(visible, (newVal) => {
  if (!newVal) {
    emit('close')
  }
})
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
}

.detail-container {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.detail-header {
  padding: 16px 20px;
  border-bottom: 1px solid #e5e7eb;
  background-color: #fff;
}

.detail-content {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.info-card {
  border-radius: 8px;
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
  border-radius: 8px 8px 0 0;
  margin: -1px -1px 0;
}

.card-title {
  color: #333;
  font-weight: 600;
  font-size: 14px;
}

.card-body {
  padding: 16px;
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
  width: 120px;
  color: #666;
  flex-shrink: 0;
}

.value {
  flex: 1;
  color: #333;
  word-break: break-all;
}

.key-value {
  color: #165dff;
  font-weight: 500;
}

/* 马克龙色系 */
.macaron-blue {
  background-color: #e6f4ff;
}

.macaron-purple {
  background-color: #f9f0ff;
}

.macaron-green {
  background-color: #e6fffa;
}

.macaron-pink {
  background-color: #ffe6f4;
}

.macaron-yellow {
  background-color: #fffbe6;
}

.macaron-cyan {
  background-color: #e6ffff;
}

/* 响应式调整 */
@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }
}
</style>
