<template>
  <el-drawer
    v-model="visible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">风险源档案详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="drawer-content">
      <!-- 内容区 -->
      <div class="content-wrapper">
        <div class="grid-layout">
          <!-- 基本信息 -->
          <el-card class="info-card base-info-card">
            <div class="card-header">基本信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">风险源ID：</span>
                <span class="value">{{ formData.riskSourceId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">风险源编号：</span>
                <span class="value font-semibold text-blue-600">{{
                  formData.riskSourceNo || '-'
                }}</span>
              </div>
              <div class="info-item">
                <span class="label">风险源名称：</span>
                <span class="value">{{ formData.riskSourceName || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">风险源类型：</span>
                <span class="value">{{ formData.riskSourceType || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">风险等级：</span>
                <span class="value status-tag" :class="getRiskLevelClass">{{
                  formData.riskLevel || '-'
                }}</span>
              </div>
              <div class="info-item">
                <span class="label">建档时间：</span>
                <span class="value">{{ formatDate(formData.createTime) }}</span>
              </div>
            </div>
          </el-card>

          <!-- 位置信息 -->
          <el-card class="info-card location-info-card">
            <div class="card-header">位置信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">所在行政区划代码：</span>
                <span class="value">{{ formData.regionCode || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">所在行政区划名称：</span>
                <span class="value">{{ formData.regionName || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">所在位置描述：</span>
                <span class="value">{{ formData.locationDesc || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 关联信息 -->
          <el-card class="info-card relation-info-card">
            <div class="card-header">关联信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">关联资产ID：</span>
                <span class="value">{{ formData.assetId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">关联资产名称：</span>
                <span class="value">{{ formData.assetName || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">责任部门ID：</span>
                <span class="value">{{ formData.deptId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">责任部门名称：</span>
                <span class="value">{{ formData.deptName || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 预警信息 -->
          <el-card class="info-card warning-info-card">
            <div class="card-header">预警信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">历史预警次数：</span>
                <span class="value font-semibold text-purple-600">{{
                  formData.historyWarnCount || '0'
                }}</span>
              </div>
            </div>
          </el-card>

          <!-- 扩展信息 -->
          <el-card class="info-card extension-info-card" :span="2">
            <div class="card-header">扩展信息</div>
            <div class="card-body extension-body">
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
import { RiskSourceArchVO } from '@/api/dataHub/warningAlarm/riskIdentifyTrace/risksourcearch'

defineOptions({ name: 'RiskSourceArchDetail' })

const visible = ref(false)
const title = ref('风险源详情')
const isFullscreen = ref(false)
const formData = ref<RiskSourceArchVO>({
  id: 0,
  riskSourceId: '',
  riskSourceNo: '',
  riskSourceName: '',
  riskSourceType: '',
  riskLevel: '',
  regionCode: '',
  regionName: '',
  locationDesc: '',
  assetId: '',
  assetName: '',
  deptId: '',
  deptName: '',
  historyWarnCount: 0,
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleString() : '-')

// 根据风险等级获取样式类
const getRiskLevelClass = computed(() => {
  switch (formData.value.riskLevel) {
    case '高':
      return 'high-risk'
    case '中':
      return 'medium-risk'
    case '低':
      return 'low-risk'
    default:
      return ''
  }
})

// 打开抽屉
const open = (data: RiskSourceArchVO) => {
  formData.value = { ...data }
  visible.value = true
  isFullscreen.value = false
}

// 关闭抽屉
const close = () => {
  visible.value = false
  emit('close')
}

defineExpose({ open, close })

const emit = defineEmits(['close'])
</script>

<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f5f7fa;
}

.drawer-content {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.header {
  padding: 16px 24px;
  border-bottom: 1px solid #e5e7eb;
  background-color: #fff;
}

.content-wrapper {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.extension-info-card {
  grid-column: span 2;
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
  font-weight: 500;
  color: #4e5969;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
}

.location-info-card .card-header {
  background-color: #fff2e8;
}

.relation-info-card .card-header {
  background-color: #fff8e6;
}

.warning-info-card .card-header {
  background-color: #f6ffed;
}

.extension-info-card .card-header {
  background-color: #f0f5ff;
}

.card-body {
  padding: 16px;
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.extension-body {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 12px;
}

.info-item {
  display: flex;
  line-height: 1.6;
}

.label {
  flex: 0 0 140px;
  color: #6b7280;
  font-size: 14px;
}

.value {
  flex: 1;
  color: #1f2937;
  font-size: 14px;
  word-break: break-all;
}

.status-tag {
  padding: 2px 6px;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 500;
}

.high-risk {
  background-color: #fee2e2;
  color: #dc2626;
}

.medium-risk {
  background-color: #fef3c7;
  color: #d97706;
}

.low-risk {
  background-color: #dcfce7;
  color: #059669;
}

@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }

  .extension-info-card {
    grid-column: span 1;
  }

  .extension-body {
    grid-template-columns: 1fr;
  }
}
</style>
