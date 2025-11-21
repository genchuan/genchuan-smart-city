<template>
  <el-drawer
    v-model="drawerVisible"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800"
          >监测点位汇总报表详情
          <!--          <span class="ml-2 text-smblue-500 text-base">#{{ formData.id }}</span>-->
        </span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基础信息卡片 -->
      <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
        <!-- 基础信息 -->
        <el-card class="info-card hover-lift">
          <div class="card-header bg-blue-50">
            <span class="text-blue-600 font-medium">基础信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">报表ID：</span>
              <span class="value font-medium text-blue-600">{{ formData.rptId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计周期：</span>
              <span class="value">{{ formData.statCycle || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">统计周期名称：</span>
              <span class="value">{{ formData.statCycleName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">报表生成时间：</span>
              <span class="value">{{ formatDate(formData.rptCreateTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">报表生成人：</span>
              <span class="value">{{ formData.rptCreateUser || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 行政区划信息 -->
        <el-card class="info-card hover-lift">
          <div class="card-header bg-purple-50">
            <span class="text-purple-600 font-medium">行政区划信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">行政区划代码：</span>
              <span class="value">{{ formData.adminCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">行政区划名称：</span>
              <span class="value font-medium text-purple-600">{{ formData.adminName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">点位覆盖率：</span>
              <span class="value">
                <span class="text-green-600 font-medium">{{ formData.ptCoverageRate || 0 }}</span
                >%
              </span>
            </div>
          </div>
        </el-card>

        <!-- 点位数量统计 -->
        <el-card class="info-card hover-lift">
          <div class="card-header bg-green-50">
            <span class="text-green-600 font-medium">点位数量统计</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">点位总数量：</span>
              <span class="value font-medium text-green-600">{{ formData.totalPtCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">空气质量监测点位数：</span>
              <span class="value">{{ formData.airPtCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">交通流量监测点位数：</span>
              <span class="value">{{ formData.trafPtCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">井盖状态监测点位数：</span>
              <span class="value">{{ formData.manholePtCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">其他类型点位数：</span>
              <span class="value">{{ formData.otherPtCount || 0 }}</span>
            </div>
          </div>
        </el-card>

        <!-- 点位变动信息 -->
        <el-card class="info-card hover-lift">
          <div class="card-header bg-orange-50">
            <span class="text-orange-600 font-medium">点位变动信息</span>
          </div>
          <div class="card-body">
            <div class="info-item">
              <span class="label">新增点位数量：</span>
              <span class="value text-green-600">{{ formData.newPtCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">停用点位数量：</span>
              <span class="value text-red-500">{{ formData.disablePtCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="label">报表备注：</span>
              <span class="value">{{ formData.rptRemark || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息 -->
        <el-card class="info-card hover-lift md:col-span-2">
          <div class="card-header bg-gray-50">
            <span class="text-gray-600 font-medium">扩展信息</span>
          </div>
          <div class="card-body grid grid-cols-1 md:grid-cols-2 gap-4">
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
  </el-drawer>
</template>

<script setup lang="ts">
import { MonPtSummaryRptVO } from '@/api/dataHub/operationMonitor/compStatAnalysisReport/monptsummaryrpt'

defineOptions({ name: 'MonPtSummaryRptDetail' })

const props = defineProps<{
  visible: boolean
}>()

const emit = defineEmits<{
  (e: 'close'): void
}>()
// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)
const formData = ref<MonPtSummaryRptVO>({
  id: 0,
  rptId: '',
  statCycle: '',
  statCycleName: '',
  adminCode: '',
  adminName: '',
  totalPtCount: 0,
  airPtCount: 0,
  trafPtCount: 0,
  manholePtCount: 0,
  otherPtCount: 0,
  newPtCount: 0,
  disablePtCount: 0,
  ptCoverageRate: 0,
  rptCreateTime: new Date(),
  rptCreateUser: 0,
  rptRemark: '',
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

const isFullscreen = ref(false)

// 设置详情数据
const setData = (data: MonPtSummaryRptVO) => {
  formData.value = { ...data }
  isFullscreen.value = false
}

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleString() : '-')

// 关闭抽屉
const handleClose = () => {
  emit('close')
}

defineExpose({ setData })
</script>

<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f5f7fa;
}

.detail-container {
  padding: 20px;
}

.info-card {
  border-radius: 8px;
  overflow: hidden;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.hover-lift {
  transition:
    transform 0.3s ease,
    box-shadow 0.3s ease;
}

.hover-lift:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 16px;
  border-bottom: 1px solid #f0f0f0;
}

.card-body {
  padding: 16px;
}

.info-item {
  display: flex;
  padding: 8px 0;
  border-bottom: 1px dashed #f0f0f0;

  &:last-child {
    border-bottom: none;
  }
}

.label {
  flex: 0 0 140px;
  color: #606266;
  font-size: 14px;
}

.value {
  flex: 1;
  color: #303133;
  font-size: 14px;
  word-break: break-all;
}
</style>
