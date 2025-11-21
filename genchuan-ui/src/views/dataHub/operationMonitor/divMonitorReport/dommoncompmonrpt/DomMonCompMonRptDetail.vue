<template>
  <el-drawer v-model="visible" :size="isFullscreen ? '100%' : '75%'" class="detail-drawer">
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">监测部件分域报表详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="grid grid-cols-1 md:grid-cols-2 gap-6 p-6">
      <!-- 基本信息卡片 -->
      <el-card class="card-hover">
        <div class="card-header bg-blue-50 px-4 py-2 mb-4">
          <span class="text-blue-600 font-medium">基本信息</span>
        </div>
        <div class="grid grid-cols-1 gap-3">
          <div class="flex">
            <span class="w-1/3 text-gray-500">主键：</span>
            <span class="text-gray-800 font-medium text-blue-600">{{ detailData.id || '-' }}</span>
          </div>
          <div class="flex">
            <span class="w-1/3 text-gray-500">报表ID：</span>
            <span class="text-gray-800">{{ detailData.rptId || '-' }}</span>
          </div>
          <div class="flex">
            <span class="w-1/3 text-gray-500">统计周期：</span>
            <span class="text-gray-800">{{ detailData.statCycle || '-' }}</span>
          </div>
          <div class="flex">
            <span class="w-1/3 text-gray-500">统计周期名称：</span>
            <span class="text-gray-800">{{ detailData.statCycleName || '-' }}</span>
          </div>
          <div class="flex">
            <span class="w-1/3 text-gray-500">报表生成时间：</span>
            <span class="text-gray-800">{{ formatDate(detailData.rptCreateTime) }}</span>
          </div>
          <div class="flex">
            <span class="w-1/3 text-gray-500">报表生成人：</span>
            <span class="text-gray-800">{{ detailData.rptCreateUser || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 行政区划信息卡片 -->
      <el-card class="card-hover">
        <div class="card-header bg-green-50 px-4 py-2 mb-4">
          <span class="text-green-600 font-medium">行政区划信息</span>
        </div>
        <div class="grid grid-cols-1 gap-3">
          <div class="flex">
            <span class="w-1/3 text-gray-500">行政区划代码：</span>
            <span class="text-gray-800">{{ detailData.adminCode || '-' }}</span>
          </div>
          <div class="flex">
            <span class="w-1/3 text-gray-500">行政区划名称：</span>
            <span class="text-gray-800">{{ detailData.adminName || '-' }}</span>
          </div>
          <div class="flex">
            <span class="w-1/3 text-gray-500">分域ID：</span>
            <span class="text-gray-800">{{ detailData.domId || '-' }}</span>
          </div>
          <div class="flex">
            <span class="w-1/3 text-gray-500">分域名称：</span>
            <span class="text-gray-800">{{ detailData.domName || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 监测部件信息卡片 -->
      <el-card class="card-hover">
        <div class="card-header bg-purple-50 px-4 py-2 mb-4">
          <span class="text-purple-600 font-medium">监测部件信息</span>
        </div>
        <div class="grid grid-cols-1 gap-3">
          <div class="flex">
            <span class="w-1/3 text-gray-500">监测部件大类ID：</span>
            <span class="text-gray-800">{{ detailData.monCompMajorId || '-' }}</span>
          </div>
          <div class="flex">
            <span class="w-1/3 text-gray-500">监测部件大类名称：</span>
            <span class="text-gray-800">{{ detailData.monCompMajorName || '-' }}</span>
          </div>
          <div class="flex">
            <span class="w-1/3 text-gray-500">监测部件小类ID：</span>
            <span class="text-gray-800">{{ detailData.monCompMinorId || '-' }}</span>
          </div>
          <div class="flex">
            <span class="w-1/3 text-gray-500">监测部件小类名称：</span>
            <span class="text-gray-800">{{ detailData.monCompMinorName || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 设备状态信息卡片 -->
      <el-card class="card-hover">
        <div class="card-header bg-yellow-50 px-4 py-2 mb-4">
          <span class="text-yellow-600 font-medium">设备状态信息</span>
        </div>
        <div class="grid grid-cols-1 gap-3">
          <div class="flex">
            <span class="w-1/3 text-gray-500">设备总数：</span>
            <span class="text-gray-800">{{ detailData.totalDeviceCount || '-' }}</span>
          </div>
          <div class="flex">
            <span class="w-1/3 text-gray-500">设备在线率：</span>
            <span :class="detailData.deviceOnlineRate >= 90 ? 'text-green-600' : 'text-red-600'">
              {{ detailData.deviceOnlineRate ? detailData.deviceOnlineRate + '%' : '-' }}
            </span>
          </div>
          <div class="flex">
            <span class="w-1/3 text-gray-500">数据上传总数：</span>
            <span class="text-gray-800">{{ detailData.totalUploadCount || '-' }}</span>
          </div>
          <div class="flex">
            <span class="w-1/3 text-gray-500">数据上传达标率：</span>
            <span :class="detailData.uploadQualifiedRate >= 90 ? 'text-green-600' : 'text-red-600'">
              {{ detailData.uploadQualifiedRate ? detailData.uploadQualifiedRate + '%' : '-' }}
            </span>
          </div>
        </div>
      </el-card>

      <!-- 事件与维护信息卡片 -->
      <el-card class="card-hover md:col-span-2">
        <div class="card-header bg-pink-50 px-4 py-2 mb-4">
          <span class="text-pink-600 font-medium">事件与维护信息</span>
        </div>
        <div class="grid grid-cols-1 md:grid-cols-2 gap-3">
          <div class="flex">
            <span class="w-1/3 text-gray-500">故障事件数：</span>
            <span
              :class="
                detailData.faultEvtCount && detailData.faultEvtCount > 0
                  ? 'text-red-600'
                  : 'text-gray-800'
              "
            >
              {{ detailData.faultEvtCount || 0 }}
            </span>
          </div>
          <div class="flex">
            <span class="w-1/3 text-gray-500">校准次数：</span>
            <span class="text-gray-800">{{ detailData.calibrateCount || 0 }}</span>
          </div>
          <div class="flex md:col-span-2">
            <span class="w-1/6 text-gray-500">报表备注：</span>
            <span class="text-gray-800">{{ detailData.rptRemark || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 扩展信息卡片 -->
      <el-card class="card-hover md:col-span-2">
        <div class="card-header bg-indigo-50 px-4 py-2 mb-4">
          <span class="text-indigo-600 font-medium">扩展信息</span>
        </div>
        <div class="grid grid-cols-1 md:grid-cols-4 gap-3">
          <div class="flex">
            <span class="w-1/2 text-gray-500">分类扩展字段1：</span>
            <span class="text-gray-800">{{ detailData.extCat1 || '-' }}</span>
          </div>
          <div class="flex">
            <span class="w-1/2 text-gray-500">分类扩展字段2：</span>
            <span class="text-gray-800">{{ detailData.extCat2 || '-' }}</span>
          </div>
          <div class="flex">
            <span class="w-1/2 text-gray-500">通用扩展字段1：</span>
            <span class="text-gray-800">{{ detailData.extCommon1 || '-' }}</span>
          </div>
          <div class="flex">
            <span class="w-1/2 text-gray-500">通用扩展字段2：</span>
            <span class="text-gray-800">{{ detailData.extCommon2 || '-' }}</span>
          </div>
        </div>
      </el-card>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { DomMonCompMonRptVO } from '@/api/dataHub/operationMonitor/divMonitorReport/dommoncompmonrpt'

// 时间格式化
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

const visible = ref(false)
const isFullscreen = ref(false)
const detailData = ref<DomMonCompMonRptVO>({
  id: 0,
  rptId: '',
  statCycle: '',
  statCycleName: '',
  adminCode: '',
  adminName: '',
  monCompMajorId: '',
  monCompMajorName: '',
  monCompMinorId: '',
  monCompMinorName: '',
  domId: '',
  domName: '',
  totalDeviceCount: 0,
  deviceOnlineRate: 0,
  totalUploadCount: 0,
  uploadQualifiedRate: 0,
  faultEvtCount: 0,
  calibrateCount: 0,
  rptCreateTime: new Date(),
  rptCreateUser: 0,
  rptRemark: '',
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

// 打开详情抽屉
const open = (data: DomMonCompMonRptVO) => {
  visible.value = true
  detailData.value = { ...data }
  isFullscreen.value = false
}

defineExpose({ open })
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
}

.card-hover {
  transition: all 0.3s ease;
}

.card-hover:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
}

.card-header {
  border-radius: 4px 4px 0 0;
  font-size: 14px;
}
</style>
