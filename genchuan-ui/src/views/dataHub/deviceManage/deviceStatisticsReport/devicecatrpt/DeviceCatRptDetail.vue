<template>
  <el-drawer
    v-model="drawerVisible"
    :size="isFullscreen ? '100%' : '75%'"
    class="detail-drawer"
    @close="handleClose"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">设备分类统计详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <!-- 基础信息卡片 -->
      <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
        <!-- 统计基本信息 -->
        <el-card class="info-card" hoverable>
          <template #header>
            <div class="card-header bg-blue-50">
              <span class="text-blue-600 font-medium">统计基本信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">主键ID：</span>
            <span class="value">{{ data.id ?? '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计ID：</span>
            <span class="value font-semibold text-blue-600">{{ data.catStatId ?? '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计周期：</span>
            <span class="value">{{ data.statCycle ?? '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计周期名称：</span>
            <span class="value">{{ data.statCycleName ?? '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计时间：</span>
            <span class="value">{{ formatDate(data.statTime) }}</span>
          </div>
          <div class="info-item">
            <span class="label">统计人：</span>
            <span class="value">{{ data.statUser ?? '-' }}</span>
          </div>
        </el-card>

        <!-- 行政区划信息 -->
        <el-card class="info-card" hoverable>
          <template #header>
            <div class="card-header bg-purple-50">
              <span class="text-purple-600 font-medium">行政区划信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">行政区划代码：</span>
            <span class="value">{{ data.regionCode ?? '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">行政区划名称：</span>
            <span class="value font-semibold text-purple-600">{{ data.regionName ?? '-' }}</span>
          </div>
        </el-card>

        <!-- 设备分类信息 -->
        <el-card class="info-card" hoverable>
          <template #header>
            <div class="card-header bg-green-50">
              <span class="text-green-600 font-medium">设备分类信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">设备大类ID：</span>
            <span class="value">{{ data.deviceMajorId ?? '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备大类名称：</span>
            <span class="value font-semibold text-green-600">{{
              data.deviceMajorName ?? '-'
            }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备中类ID：</span>
            <span class="value">{{ data.deviceMidId ?? '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备中类名称：</span>
            <span class="value">{{ data.deviceMidName ?? '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备小类ID：</span>
            <span class="value">{{ data.deviceMinorId ?? '-' }}</span>
          </div>
          <div class="info-item">
            <span class="label">设备小类名称：</span>
            <span class="value">{{ data.deviceMinorName ?? '-' }}</span>
          </div>
        </el-card>

        <!-- 设备状态统计 -->
        <el-card class="info-card" hoverable>
          <template #header>
            <div class="card-header bg-orange-50">
              <span class="text-orange-600 font-medium">设备状态统计</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">设备总数(台)：</span>
            <span class="value font-semibold">{{ data.totalDeviceCount ?? 0 }}</span>
          </div>
          <div class="info-item">
            <span class="label">在线设备数(台)：</span>
            <span class="value text-green-500 font-semibold">{{
              data.onlineDeviceCount ?? 0
            }}</span>
          </div>
          <div class="info-item">
            <span class="label">离线设备数(台)：</span>
            <span class="value text-orange-500">{{ data.offlineDeviceCount ?? 0 }}</span>
          </div>
          <div class="info-item">
            <span class="label">故障设备数(台)：</span>
            <span class="value text-red-500 font-semibold">{{ data.faultDeviceCount ?? 0 }}</span>
          </div>
          <div class="info-item">
            <span class="label">新增设备数(台)：</span>
            <span class="value">{{ data.newDeviceCount ?? 0 }}</span>
          </div>
          <div class="info-item">
            <span class="label">报废设备数(台)：</span>
            <span class="value">{{ data.scrappedDeviceCount ?? 0 }}</span>
          </div>
        </el-card>

        <!-- 其他信息 -->
        <el-card class="info-card" hoverable md:col-span-2>
          <template #header>
            <div class="card-header bg-gray-50">
              <span class="text-gray-600 font-medium">其他信息</span>
            </div>
          </template>
          <div class="info-item">
            <span class="label">报表备注：</span>
            <span class="value multi-line">{{ data.rptRemark ?? '-' }}</span>
          </div>
        </el-card>

        <!-- 扩展字段信息 -->
        <el-card class="info-card" hoverable md:col-span-2>
          <template #header>
            <div class="card-header bg-gray-50">
              <span class="text-gray-600 font-medium">扩展字段信息</span>
            </div>
          </template>
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div class="info-item">
              <span class="label">分类扩展字段1：</span>
              <span class="value">{{ data.extCat1 ?? '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类扩展字段2：</span>
              <span class="value">{{ data.extCat2 ?? '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段1：</span>
              <span class="value">{{ data.extCommon1 ?? '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段2：</span>
              <span class="value">{{ data.extCommon2 ?? '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DeviceCatRptVO } from '@/api/dataHub/deviceManage/deviceStatisticsReport/devicecatrpt'

defineOptions({ name: 'DeviceCatRptDetail' })

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
// 处理抽屉关闭
const handleClose = () => {
  emit('close')
}

// 抽屉状态
const isFullscreen = ref(false)
const data = ref<DeviceCatRptVO>({
  id: 0,
  catStatId: '',
  statCycle: '',
  statCycleName: '',
  regionCode: '',
  regionName: '',
  deviceMajorId: '',
  deviceMajorName: '',
  deviceMidId: '',
  deviceMidName: '',
  deviceMinorId: '',
  deviceMinorName: '',
  totalDeviceCount: 0,
  onlineDeviceCount: 0,
  offlineDeviceCount: 0,
  faultDeviceCount: 0,
  newDeviceCount: 0,
  scrappedDeviceCount: 0,
  statTime: new Date(),
  statUser: '',
  rptRemark: '',
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

// 设置详情数据
const setData = (row: DeviceCatRptVO) => {
  data.value = { ...row }
}

// 暴露方法给父组件
defineExpose({ setData })

// 监听抽屉关闭
watch(
  () => props.visible,
  (newVal) => {
    if (!newVal) {
      isFullscreen.value = false
    }
  }
)
</script>

<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f5f7fa;
}

.drawer-header {
  padding: 16px 24px;
  border-bottom: 1px solid #e5e7eb;
  background-color: #fff;
  margin: -20px -20px 20px;
}

.detail-container {
  padding: 0 20px 20px;
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

.info-item {
  display: flex;
  padding: 8px 0;
  border-bottom: 1px dashed #f0f0f0;
  align-items: flex-start;
}

.info-item:last-child {
  border-bottom: none;
}

.label {
  flex: 0 0 120px;
  color: #666;
  font-size: 14px;
  padding-right: 10px;
  text-align: right;
}

.value {
  flex: 1;
  color: #333;
  font-size: 14px;
  word-break: break-all;
}

.multi-line {
  line-height: 1.6;
  white-space: pre-wrap;
}

:deep(.el-card__body) {
  padding: 16px;
}
</style>
