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
        <span class="text-lg font-bold text-gray-800">预警告警处置归档详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <div class="grid-layout">
        <!-- 基本信息卡片 -->
        <el-card class="info-card base-info-card">
          <div class="card-header">基本信息</div>
          <div class="info-item">
            <span class="label">主键：</span>
            <span class="value">{{ data.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">归档ID：</span>
            <span class="value key-field">{{ data.archId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">归档编号：</span>
            <span class="value key-field">{{ data.archNo || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">预警ID：</span>
            <span class="value">{{ data.earlyWarnId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">预警名称：</span>
            <span class="value">{{ data.earlyWarnName || '-' }}</span>
          </div>
        </el-card>

        <!-- 预警信息卡片 -->
        <el-card class="info-card warn-info-card">
          <div class="card-header">预警信息</div>
          <div class="info-item">
            <span class="label">预警类型：</span>
            <span class="value">{{ data.warnType || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">预警等级：</span>
            <span class="value">{{ data.warnLevel || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">所在区域：</span>
            <span class="value">{{ data.regionName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">处置人：</span>
            <span class="value">{{ data.handleUserName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">综合评估得分：</span>
            <span class="value">{{ data.comprehensiveScore || '-' }}</span>
          </div>
        </el-card>

        <!-- 状态信息卡片 -->
        <el-card class="info-card status-info-card">
          <div class="card-header">状态信息</div>
          <div class="info-item">
            <span class="label">解除状态：</span>
            <span
              class="value"
              :class="data.releaseStatus === 'RELEASED' ? 'status-success' : 'status-danger'"
            >
              {{
                data.releaseStatus === 'RELEASED'
                  ? '已解除'
                  : data.releaseStatus === 'PENDING'
                    ? '未解除'
                    : '-'
              }}
            </span>
          </div>
          <div class="info-item">
            <span class="label">归档时间：</span>
            <span class="value">{{ formatDate(data.archTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">创建时间：</span>
            <span class="value">{{ formatDate(data.createTime) }}</span>
          </div>
        </el-card>

        <!-- 归档人信息卡片 -->
        <el-card class="info-card user-info-card">
          <div class="card-header">归档人信息</div>
          <div class="info-item">
            <span class="label">归档人ID：</span>
            <span class="value">{{ data.archUserId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">归档人姓名：</span>
            <span class="value">{{ data.archUserName || '-' }}</span>
          </div>
        </el-card>

        <!-- 扩展字段卡片 -->
        <el-card class="info-card ext-info-card" :span="2">
          <div class="card-header">扩展字段</div>
          <div class="ext-fields-grid">
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
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { EarlyWarnArchVO } from '@/api/dataHub/warningAlarm/warnAlarmRespDisposal/earlywarnarch'

const visible = ref(false)
const title = ref('预警告警处置归档详情')
const isFullscreen = ref(false)
const data = ref<EarlyWarnArchVO>({} as EarlyWarnArchVO)

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}

// 打开抽屉
const open = (row: EarlyWarnArchVO) => {
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
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 15px;
  font-size: 15px;
  font-weight: 500;
  margin: -1px -1px 15px;
  border-radius: 8px 8px 0 0;
  color: #666;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
  border-left: 4px solid #1890ff;
}

.warn-info-card .card-header {
  background-color: #fff7e6;
  border-left: 4px solid #faad14;
}

.status-info-card .card-header {
  background-color: #f6ffed;
  border-left: 4px solid #52c41a;
}

.user-info-card .card-header {
  background-color: #fff0f6;
  border-left: 4px solid #eb2f96;
}

.ext-info-card .card-header {
  background-color: #f0f2f5;
  border-left: 4px solid #8c8c8c;
}

.ext-info-card {
  grid-column: span 2;
}

.info-item {
  display: flex;
  padding: 8px 15px;
  border-bottom: 1px dashed #f0f0f0;
  align-items: center;
}

.info-item:last-child {
  border-bottom: none;
}

.label {
  flex: 0 0 120px;
  color: #666;
  font-size: 14px;
}

.value {
  flex: 1;
  color: #333;
  font-size: 14px;
  word-break: break-all;
}

.key-field {
  color: #1890ff;
  font-weight: 500;
}

.status-success {
  color: #52c41a;
  font-weight: 500;
}

.status-danger {
  color: #ff4d4f;
  font-weight: 500;
}

.ext-fields-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 5px;
}

@media screen and (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }

  .ext-info-card {
    grid-column: span 1;
  }

  .ext-fields-grid {
    grid-template-columns: 1fr;
  }
}
</style>
