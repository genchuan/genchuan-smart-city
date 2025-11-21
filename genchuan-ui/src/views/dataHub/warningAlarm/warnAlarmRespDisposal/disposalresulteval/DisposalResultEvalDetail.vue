<template>
  <el-drawer v-model="visible" :size="isFullscreen ? '100%' : '75%'" class="detail-drawer">
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">处置效果评估详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="drawer-content">
      <div class="detail-container">
        <!-- 基础信息卡片 -->
        <div class="grid-layout">
          <el-card class="info-card base-info-card">
            <div class="card-header">基础信息</div>
            <div class="card-content">
              <div class="info-item">
                <span class="label">主键：</span>
                <span class="value">{{ detailData.id || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">评估ID：</span>
                <span class="value key-field">{{ detailData.evalId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">预警ID：</span>
                <span class="value key-field">{{ detailData.earlyWarnId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">派单ID：</span>
                <span class="value key-field">{{ detailData.dispatchId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">评估时间：</span>
                <span class="value">{{ formatDate(detailData.evalTime) }}</span>
              </div>
            </div>
          </el-card>

          <!-- 评估人信息卡片 -->
          <el-card class="info-card user-info-card">
            <div class="card-header">评估人信息</div>
            <div class="card-content">
              <div class="info-item">
                <span class="label">评估人ID：</span>
                <span class="value">{{ detailData.evalUserId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">评估人姓名：</span>
                <span class="value key-field">{{ detailData.evalUserName || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 评分信息卡片 -->
          <el-card class="info-card score-info-card">
            <div class="card-header">评分信息</div>
            <div class="card-content">
              <div class="info-item">
                <span class="label">问题解决度得分：</span>
                <span class="value">{{ detailData.problemSolveScore || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">响应及时性得分：</span>
                <span class="value">{{ detailData.timelinessScore || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">现场规范性得分：</span>
                <span class="value">{{ detailData.stdizationScore || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">综合得分：</span>
                <span class="value key-field">{{ detailData.comprehensiveScore || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">评估等级：</span>
                <span class="value">{{ detailData.evalLevel || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 处置信息卡片 -->
          <el-card class="info-card disposal-info-card">
            <div class="card-header">处置信息</div>
            <div class="card-content">
              <div class="info-item">
                <span class="label">是否二次处置：</span>
                <span
                  class="value status-field"
                  :class="detailData.isSecondHandle === '1' ? 'status-success' : 'status-danger'"
                >
                  {{ detailData.isSecondHandle === '1' ? '是' : '否' }}
                </span>
              </div>
              <div class="info-item">
                <span class="label">评估备注：</span>
                <span class="value">{{ detailData.evalRemark || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 扩展信息卡片 -->
          <el-card class="info-card ext-info-card" :span="2">
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
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { EarlyWarnHandleEvalVO } from '@/api/dataHub/warningAlarm/warnAlarmRespDisposal/disposalresulteval'

const visible = ref(false)
const isFullscreen = ref(false)
const detailData = ref<EarlyWarnHandleEvalVO>({} as EarlyWarnHandleEvalVO)
const emit = defineEmits(['close'])

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  const date = new Date(val)
  return date.toLocaleString()
}

// 打开抽屉
const open = (data: EarlyWarnHandleEvalVO) => {
  detailData.value = { ...data }
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
  background-color: #f5f7fa;
}

.drawer-content {
  height: 100%;
  padding: 20px;
  overflow-y: auto;
}

.detail-container {
  margin-top: 20px;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.ext-info-card {
  grid-column: span 2;
}

.info-card {
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
  overflow: hidden;
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 15px;
  font-weight: 600;
  color: #4e5969;
  font-size: 14px;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
}

.user-info-card .card-header {
  background-color: #fff2e8;
}

.score-info-card .card-header {
  background-color: #f6ffed;
}

.disposal-info-card .card-header {
  background-color: #fff0f6;
}

.ext-info-card .card-header {
  background-color: #f0f2ff;
}

.card-content {
  padding: 15px;
}

.info-item {
  display: flex;
  margin-bottom: 12px;
  line-height: 1.5;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  flex: 0 0 120px;
  color: #86909c;
  font-size: 13px;
}

.value {
  flex: 1;
  color: #1d2129;
  font-size: 13px;
  word-break: break-all;
}

.key-field {
  color: #1890ff;
  font-weight: 600;
}

.status-field {
  padding: 2px 6px;
  border-radius: 4px;
  font-size: 12px;
}

.status-success {
  color: #52c41a;
  background-color: #f6ffed;
}

.status-danger {
  color: #f5222d;
  background-color: #fff2f0;
}

@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }

  .ext-info-card {
    grid-column: span 1;
  }
}
</style>
