<template>
  <el-drawer
    v-model="visible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">预警告警解除详情</span>
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
          <div class="card-content">
            <div class="info-item">
              <span class="label">主键：</span>
              <span class="value">{{ detailData.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">解除ID：</span>
              <span class="value key-field">{{ detailData.releaseId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">预警ID：</span>
              <span class="value key-field">{{ detailData.earlyWarnId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">预警名称：</span>
              <span class="value">{{ detailData.earlyWarnName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 申请人信息卡片 -->
        <el-card class="info-card applicant-info-card">
          <div class="card-header">申请人信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">申请人ID：</span>
              <span class="value key-field">{{ detailData.applyUserId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">申请人姓名：</span>
              <span class="value">{{ detailData.applyUserName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">申请时间：</span>
              <span class="value">{{ formatDate(detailData.applyTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">解除原因：</span>
              <span class="value">{{ detailData.releaseReason || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 审核信息卡片 -->
        <el-card class="info-card audit-info-card">
          <div class="card-header">审核信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">审核人ID：</span>
              <span class="value key-field">{{ detailData.auditUserId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">审核人姓名：</span>
              <span class="value">{{ detailData.auditUserName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">审核时间：</span>
              <span class="value">{{ formatDate(detailData.auditTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">解除状态：</span>
              <span class="value status-field" :class="getStatusClass(detailData.releaseStatus)">{{
                detailData.releaseStatus || '-'
              }}</span>
            </div>
          </div>
        </el-card>

        <!-- 其他信息卡片 -->
        <el-card class="info-card other-info-card">
          <div class="card-header">其他信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">驳回原因：</span>
              <span class="value">{{ detailData.rejectReason || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">是否复发：</span>
              <span class="value">{{ detailData.isRecurrence || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建时间：</span>
              <span class="value">{{ formatDate(detailData.createTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card extend-info-card" :span="2">
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
import { ref, reactive } from 'vue'
import { EarlyWarnReleaseVO } from '@/api/dataHub/warningAlarm/warnAlarmRespDisposal/earlywarnrelease'

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleString() : '-')

// 组件参数
const visible = ref(false)
const title = ref('预警告警解除详情')
const isFullscreen = ref(false)
const detailData = reactive<EarlyWarnReleaseVO>({
  id: 0,
  releaseId: '',
  earlyWarnId: '',
  earlyWarnName: '',
  applyUserId: '',
  applyUserName: '',
  auditUserId: '',
  auditUserName: '',
  releaseStatus: '',
  releaseReason: '',
  applyTime: new Date(),
  auditTime: new Date(),
  rejectReason: '',
  isRecurrence: '',
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

// 打开抽屉
const open = (data: EarlyWarnReleaseVO) => {
  Object.assign(detailData, data)
  visible.value = true
  isFullscreen.value = false
}

// 获取状态样式
const getStatusClass = (status: string) => {
  // 这里根据实际状态值进行调整
  if (status === 'APPROVED') return 'status-success'
  if (status === 'PENDING') return 'status-danger'
  return ''
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
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.info-card[span='2'] {
  grid-column: span 2;
}

.card-header {
  padding: 12px 16px;
  background-color: #f0f2f5;
  border-radius: 8px 8px 0 0;
  font-weight: 500;
  color: #4e5969;
}

.base-info-card .card-header {
  background-color: #e6f7f0;
}

.applicant-info-card .card-header {
  background-color: #fff2e8;
}

.audit-info-card .card-header {
  background-color: #e8f3ff;
}

.other-info-card .card-header {
  background-color: #fff7e8;
}

.extend-info-card .card-header {
  background-color: #f6f3ff;
}

.card-content {
  padding: 16px;
}

.info-item {
  margin-bottom: 12px;
  line-height: 1.6;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  display: inline-block;
  width: 100px;
  color: #606266;
}

.value {
  color: #303133;
}

.key-field {
  color: #1890ff;
  font-weight: 500;
}

.status-field {
  padding: 2px 6px;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 500;
}

.status-success {
  background-color: #f0f9eb;
  color: #52c41a;
}

.status-danger {
  background-color: #fff1f0;
  color: #f5222d;
}

@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }

  .info-card[span='2'] {
    grid-column: span 1;
  }
}
</style>
