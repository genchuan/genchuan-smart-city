<template>
  <el-drawer v-model="drawerVisible" :size="isFullscreen ? '100%' : '75%'" class="detail-drawer">
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">网格分域监测报表详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="drawer-container">
      <!-- 内容区域 -->
      <div class="drawer-content">
        <!-- 基础信息卡片 -->
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
          <!-- 基础信息 -->
          <el-card class="info-card base-info-card">
            <div class="card-header">基础信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="info-label">主键ID：</span>
                <span class="info-value font-medium text-blue-500">{{ data.id || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">报表ID：</span>
                <span class="info-value">{{ data.rptId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">统计周期：</span>
                <span class="info-value">{{ data.statCycle || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">统计周期名称：</span>
                <span class="info-value">{{ data.statCycleName || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">报表生成时间：</span>
                <span class="info-value">{{ formatDate(data.rptCreateTime) }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">报表生成人：</span>
                <span class="info-value">{{ data.rptCreateUser || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 区域信息 -->
          <el-card class="info-card area-info-card">
            <div class="card-header">区域信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="info-label">行政区划代码：</span>
                <span class="info-value">{{ data.adminCode || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">行政区划名称：</span>
                <span class="info-value font-medium text-blue-500">{{
                  data.adminName || '-'
                }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">网格类型：</span>
                <span class="info-value">{{ data.gridType || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">分域ID：</span>
                <span class="info-value">{{ data.domId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">分域名称：</span>
                <span class="info-value">{{ data.domName || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">网格编码：</span>
                <span class="info-value">{{ data.gridCode || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 人员信息 -->
          <el-card class="info-card staff-info-card">
            <div class="card-header">人员信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="info-label">网格员姓名：</span>
                <span class="info-value font-medium text-blue-500">{{
                  data.gridUserName || '-'
                }}</span>
              </div>
            </div>
          </el-card>

          <!-- 监测数据 -->
          <el-card class="info-card monitor-info-card">
            <div class="card-header">监测数据</div>
            <div class="card-body">
              <div class="info-item">
                <span class="info-label">监测点位数量：</span>
                <span class="info-value">{{ data.monPtCount || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">设备在线率：</span>
                <span class="info-value">
                  {{ data.deviceOnlineRate || '-' }}%
                  <span :class="getOnlineRateClass">{{ getOnlineRateStatus }}</span>
                </span>
              </div>
              <div class="info-item">
                <span class="info-label">事件总数：</span>
                <span class="info-value">{{ data.evtTotalCount || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">事件办结率：</span>
                <span class="info-value">
                  {{ data.evtCompleteRate || '-' }}%
                  <span :class="getCompleteRateClass">{{ getCompleteRateStatus }}</span>
                </span>
              </div>
            </div>
          </el-card>

          <!-- 备注信息 -->
          <el-card class="info-card remark-info-card md:col-span-2">
            <div class="card-header">备注信息</div>
            <div class="card-body">
              <div class="info-item full-width">
                <span class="info-label">报表备注：</span>
                <span class="info-value">{{ data.rptRemark || '无' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 扩展信息 -->
          <el-card class="info-card ext-info-card md:col-span-2">
            <div class="card-header">扩展信息</div>
            <div class="card-body grid grid-cols-1 md:grid-cols-2 gap-4">
              <div class="info-item">
                <span class="info-label">分类扩展字段1：</span>
                <span class="info-value">{{ data.extCat1 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">分类扩展字段2：</span>
                <span class="info-value">{{ data.extCat2 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">通用扩展字段1：</span>
                <span class="info-value">{{ data.extCommon1 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="info-label">通用扩展字段2：</span>
                <span class="info-value">{{ data.extCommon2 || '-' }}</span>
              </div>
            </div>
          </el-card>
        </div>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DomGridMonRptVO } from '@/api/dataHub/operationMonitor/divMonitorReport/domgridmonrpt'

const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['close'])
// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)
// 添加对 drawerVisible 的监听 以解决drawerVisible值的同步问题
watch(
  () => drawerVisible.value,
  (newVal) => {
    if (!newVal) {
      emit('close') // 当抽屉关闭时，通知父组件重置 detailVisible
    }
  }
)
// 抽屉数据
const data = ref<Partial<DomGridMonRptVO>>({})

// 设置抽屉数据
const setData = (row: DomGridMonRptVO) => {
  data.value = { ...row }
}

// 全屏状态
const isFullscreen = ref(false)

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 设备在线率状态
const getOnlineRateStatus = computed(() => {
  const rate = data.value.deviceOnlineRate || 0
  return rate >= 90 ? '优秀' : rate >= 70 ? '良好' : '较差'
})

const getOnlineRateClass = computed(() => {
  const rate = data.value.deviceOnlineRate || 0
  return rate >= 90 ? 'status-good' : rate >= 70 ? 'status-normal' : 'status-poor'
})

// 事件办结率状态
const getCompleteRateStatus = computed(() => {
  const rate = data.value.evtCompleteRate || 0
  return rate >= 90 ? '优秀' : rate >= 70 ? '良好' : '较差'
})

const getCompleteRateClass = computed(() => {
  const rate = data.value.evtCompleteRate || 0
  return rate >= 90 ? 'status-good' : rate >= 70 ? 'status-normal' : 'status-poor'
})

// 暴露方法
defineExpose({ setData })
</script>

<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f5f7fa;
}

.drawer-container {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.drawer-header {
  padding: 16px 20px;
  border-bottom: 1px solid #e5e7eb;
  background-color: #fff;
}

.drawer-content {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}

.info-card {
  border-radius: 8px;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 16px;
  font-weight: 500;
  border-bottom: 1px solid #f2f3f5;
  margin-bottom: 16px;
  border-radius: 8px 8px 0 0;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
  color: #1890ff;
}

.area-info-card .card-header {
  background-color: #fff2e8;
  color: #fa8c16;
}

.staff-info-card .card-header {
  background-color: #f6ffed;
  color: #52c41a;
}

.monitor-info-card .card-header {
  background-color: #fff0f6;
  color: #eb2f96;
}

.remark-info-card .card-header {
  background-color: #f0f2ff;
  color: #722ed1;
}

.ext-info-card .card-header {
  background-color: #e8f3ff;
  color: #0050b3;
}

.card-body {
  padding: 0 16px 16px;
}

.info-item {
  display: flex;
  margin-bottom: 12px;
  line-height: 1.8;
}

.info-item.full-width {
  flex-direction: column;
}

.info-item.full-width .info-value {
  margin-top: 8px;
  padding: 10px;
  background-color: #f9fafb;
  border-radius: 6px;
  width: 100%;
}

.info-label {
  color: #6b7280;
  width: 120px;
  flex-shrink: 0;
}

.info-value {
  color: #1f2937;
  flex: 1;
  word-break: break-all;
}

.status-good {
  display: inline-block;
  margin-left: 8px;
  padding: 2px 6px;
  font-size: 12px;
  border-radius: 4px;
  background-color: #f0fff4;
  color: #00b42a;
}

.status-normal {
  display: inline-block;
  margin-left: 8px;
  padding: 2px 6px;
  font-size: 12px;
  border-radius: 4px;
  background-color: #fff7e6;
  color: #ff7d00;
}

.status-poor {
  display: inline-block;
  margin-left: 8px;
  padding: 2px 6px;
  font-size: 12px;
  border-radius: 4px;
  background-color: #fff2f3;
  color: #f53f3f;
}
</style>
