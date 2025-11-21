<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    destroy-on-close
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">关联风险识别详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container bg-gray-50 p-4 rounded-lg">
      <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
        <!-- 基础信息卡片 -->
        <el-card class="card-hover">
          <template #header>
            <div class="card-header bg-blue-50">
              <span class="text-blue-600 font-medium">基础信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">主键：</span>
            <span class="value">{{ detailData.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">识别ID：</span>
            <span class="value font-semibold text-blue-600">{{ detailData.identId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">触发预警ID：</span>
            <span class="value">{{ detailData.triggerWarnId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">触发预警名称：</span>
            <span class="value">{{ detailData.triggerWarnName || '-' }}</span>
          </div>
        </el-card>

        <!-- 风险信息卡片 -->
        <el-card class="card-hover">
          <template #header>
            <div class="card-header bg-purple-50">
              <span class="text-purple-600 font-medium">风险信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">关联风险名称：</span>
            <span class="value">{{ detailData.relatedRiskName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联风险类型：</span>
            <span class="value">{{ detailData.relatedRiskType || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">风险等级：</span>
            <span class="value">
              <span :class="getRiskLevelClass(detailData.riskLevel)">{{
                detailData.riskLevel || '-'
              }}</span>
            </span>
          </div>
          <div class="info-item">
            <span class="label">关联依据：</span>
            <span class="value">{{ detailData.relatedBasis || '-' }}</span>
          </div>
        </el-card>

        <!-- 识别信息卡片 -->
        <el-card class="card-hover">
          <template #header>
            <div class="card-header bg-green-50">
              <span class="text-green-600 font-medium">识别信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">识别时间：</span>
            <span class="value">{{ formatDate(detailData.identTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">识别方式：</span>
            <span class="value">{{ detailData.identMethod || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">识别用户ID：</span>
            <span class="value">{{ detailData.identifyUserId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">识别用户姓名：</span>
            <span class="value font-semibold text-blue-600">{{
              detailData.identifyUserName || '-'
            }}</span>
          </div>
        </el-card>

        <!-- 处置状态卡片 -->
        <el-card class="card-hover">
          <template #header>
            <div class="card-header bg-orange-50">
              <span class="text-orange-600 font-medium">处置与扩展信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">是否已处置：</span>
            <span class="value">
              <el-tag :type="detailData.isHandled === '1' ? 'success' : 'danger'" size="small">
                {{ detailData.isHandled === '1' ? '是' : '否' }}
              </el-tag>
            </span>
          </div>
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
import { ref, reactive } from 'vue'
import {
  RelatedRiskIdentVO,
  RelatedRiskIdentApi
} from '@/api/dataHub/warningAlarm/riskIdentifyTrace/relatedriskident'

const visible = ref(false)
const isFullscreen = ref(false)
const detailData = reactive<RelatedRiskIdentVO>({} as RelatedRiskIdentVO)

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

// 获取风险等级样式
const getRiskLevelClass = (level: string) => {
  switch (level) {
    case '高':
      return 'text-red-500 font-medium'
    case '中':
      return 'text-orange-500 font-medium'
    case '低':
      return 'text-green-500 font-medium'
    default:
      return ''
  }
}

// 打开抽屉并加载详情
const open = async (row: RelatedRiskIdentVO) => {
  visible.value = true
  isFullscreen.value = false
  try {
    const res = await RelatedRiskIdentApi.getRelatedRiskIdent(row.id)
    Object.assign(detailData, res)
  } catch (error) {
    console.error('获取详情失败', error)
  }
}

// 关闭抽屉
const handleClose = () => {
  visible.value = false
  // 清空数据
  Object.keys(detailData).forEach((key) => {
    detailData[key as keyof RelatedRiskIdentVO] = '' as any
  })
  emits('close')
}

const emits = defineEmits(['close'])

defineExpose({
  open
})
</script>

<style scoped>
.detail-container {
  min-height: calc(100vh - 120px);
}

.card-header {
  padding: 8px 16px;
  border-radius: 4px 4px 0 0;
}

.info-item {
  display: flex;
  padding: 12px 16px;
  border-bottom: 1px solid #f0f2f5;

  &:last-child {
    border-bottom: none;
  }
}

.label {
  width: 120px;
  color: #606266;
  font-weight: 500;
}

.value {
  flex: 1;
  color: #303133;
  word-break: break-all;
}

.card-hover {
  transition: all 0.3s ease;

  &:hover {
    transform: translateY(-3px);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
  }
}
</style>
