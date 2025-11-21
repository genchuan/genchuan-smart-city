<template>
  <el-drawer
    v-model="drawerVisible"
    :size="isFullscreen ? '100%' : '75%'"
    class="detail-drawer"
    @close="handleClose"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">管理部件分域监测报表详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="drawer-content">
      <!-- 内容区域 -->
      <div class="grid-container">
        <!-- 基本信息卡片 -->
        <el-card class="grid-item card-base">
          <template #header>
            <div class="card-header">基本信息</div>
          </template>
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value">{{ data.id || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">报表ID：</span>
            <span class="value primary">{{ data.rptId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计周期：</span>
            <span class="value">{{ data.statCycle || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计周期名称：</span>
            <span class="value">{{ data.statCycleName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">报表生成时间：</span>
            <span class="value">{{ formatDate(data.rptCreateTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">报表生成人：</span>
            <span class="value">{{ data.rptCreateUser || '-' }}</span>
          </div>
        </el-card>

        <!-- 行政区划信息卡片 -->
        <el-card class="grid-item card-region">
          <template #header>
            <div class="card-header">行政区划信息</div>
          </template>
          <div class="info-item">
            <span class="label">行政区划代码：</span>
            <span class="value">{{ data.adminCode || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">行政区划名称：</span>
            <span class="value primary">{{ data.adminName || '-' }}</span>
          </div>
        </el-card>

        <!-- 管理部件信息卡片 -->
        <el-card class="grid-item card-component">
          <template #header>
            <div class="card-header">管理部件信息</div>
          </template>
          <div class="info-item">
            <span class="label">管理部件大类ID：</span>
            <span class="value">{{ data.mngCompMajorId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">管理部件大类名称：</span>
            <span class="value primary">{{ data.mngCompMajorName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">管理部件小类ID：</span>
            <span class="value">{{ data.mngCompMinorId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">管理部件小类名称：</span>
            <span class="value primary">{{ data.mngCompMinorName || '-' }}</span>
          </div>
        </el-card>

        <!-- 分域信息卡片 -->
        <el-card class="grid-item card-domain">
          <template #header>
            <div class="card-header">分域信息</div>
          </template>
          <div class="info-item">
            <span class="label">分域ID：</span>
            <span class="value">{{ data.domId || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">分域名称：</span>
            <span class="value primary">{{ data.domName || '-' }}</span>
          </div>
        </el-card>

        <!-- 设备统计信息卡片 -->
        <el-card class="grid-item card-device">
          <template #header>
            <div class="card-header">设备统计信息</div>
          </template>
          <div class="info-item">
            <span class="label">关联点位数量：</span>
            <span class="value">{{ data.relPtCount || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">关联设备数量：</span>
            <span class="value">{{ data.relDeviceCount || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备在线率：</span>
            <span
              class="value"
              :class="data.deviceOnlineRate >= 90 ? 'status-good' : 'status-warning'"
            >
              {{ data.deviceOnlineRate ? data.deviceOnlineRate + '%' : '-' }}
            </span>
          </div>
        </el-card>

        <!-- 故障信息卡片 -->
        <el-card class="grid-item card-fault">
          <template #header>
            <div class="card-header">故障信息</div>
          </template>
          <div class="info-item">
            <span class="label">部件故障事件数：</span>
            <span
              class="value"
              :class="data.compFaultEvtCount && data.compFaultEvtCount > 0 ? 'status-warning' : ''"
            >
              {{ data.compFaultEvtCount || '-' }}
            </span>
          </div>
          <div class="info-item">
            <span class="label">故障修复率：</span>
            <span
              class="value"
              :class="data.faultRepairRate >= 90 ? 'status-good' : 'status-warning'"
            >
              {{ data.faultRepairRate ? data.faultRepairRate + '%' : '-' }}
            </span>
          </div>
          <div class="info-item">
            <span class="label">报表备注：</span>
            <span class="value">{{ data.rptRemark || '-' }}</span>
          </div>
        </el-card>

        <!-- 扩展信息卡片（占两列） -->
        <el-card class="grid-item card-extension" :span="2">
          <template #header>
            <div class="card-header">扩展信息</div>
          </template>
          <div class="extension-grid">
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
import { DomMngCompMonRptVO } from '@/api/dataHub/operationMonitor/divMonitorReport/dommngcompmonrpt'

defineOptions({ name: 'DomMngCompMonRptDetail' })

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleString() : '-')

// 接收父组件参数
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  }
})

// 向父组件发送事件
const emit = defineEmits(['close'])
// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)
// 内部状态
const data = ref<DomMngCompMonRptVO>({} as DomMngCompMonRptVO)
const isFullscreen = ref(false)

// 设置详情数据
const setData = (row: DomMngCompMonRptVO) => {
  data.value = { ...row }
}

// 关闭抽屉
const handleClose = () => {
  emit('close')
  isFullscreen.value = false
}

// 暴露方法
defineExpose({ setData })
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
  padding: 0;
}

.drawer-content {
  height: 100%;
  display: flex;
  flex-direction: column;
  padding: 20px;
  overflow: auto;
}

.drawer-header {
  margin-bottom: 20px;
  padding-bottom: 10px;
  border-bottom: 1px solid #e5e7eb;
}

.grid-container {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
  flex: 1;
}

.grid-item {
  transition: all 0.3s ease;
  border-radius: 8px;
  overflow: hidden;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.grid-item:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

.grid-item.card-extension {
  grid-column: span 2;
}

.card-header {
  background-color: #f0f7ff;
  color: #165dff;
  padding: 10px 15px;
  font-weight: 500;
}

.card-base .card-header {
  background-color: #f6ffed;
  color: #52c41a;
}

.card-region .card-header {
  background-color: #fff2e8;
  color: #fa8c16;
}

.card-component .card-header {
  background-color: #fff0f6;
  color: #eb2f96;
}

.card-domain .card-header {
  background-color: #e6f7ff;
  color: #1890ff;
}

.card-device .card-header {
  background-color: #f0f2ff;
  color: #722ed1;
}

.card-fault .card-header {
  background-color: #fffbe6;
  color: #faad14;
}

.card-extension .card-header {
  background-color: #f9f0ff;
  color: #9254de;
}

::v-deep .el-card__body {
  padding: 15px;
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
  flex: 0 0 130px;
  color: #666;
  font-size: 14px;
}

.value {
  flex: 1;
  color: #333;
  word-break: break-all;
}

.value.primary {
  color: #165dff;
  font-weight: 500;
}

.value.status-good {
  color: #52c41a;
  font-weight: 500;
}

.value.status-warning {
  color: #fa8c16;
  font-weight: 500;
}

.extension-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 10px;
}

@media screen and (max-width: 1200px) {
  .grid-container {
    grid-template-columns: 1fr;
  }

  .grid-item.card-extension {
    grid-column: span 1;
  }

  .extension-grid {
    grid-template-columns: 1fr;
  }
}
</style>
