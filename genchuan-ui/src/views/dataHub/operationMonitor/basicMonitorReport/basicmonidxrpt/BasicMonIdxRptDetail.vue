<template>
  <el-drawer
    v-model="visible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">基础监测指标报表详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="grid grid-cols-1 md:grid-cols-2 gap-6 p-6 bg-gray-50">
      <!-- 基础信息卡片 -->
      <el-card class="card-hover">
        <template #header>
          <div class="bg-blue-50 text-blue-600 font-medium py-2 px-4"> 基础信息</div>
        </template>
        <div class="grid grid-cols-1 gap-4 p-4">
          <div class="flex">
            <span class="text-gray-500 w-1/3">主键ID：</span>
            <span class="text-gray-800 font-semibold text-blue-600">{{ formData.id || '-' }}</span>
          </div>
          <div class="flex">
            <span class="text-gray-500 w-1/3">报表ID：</span>
            <span class="text-gray-800">{{ formData.rptId || '-' }}</span>
          </div>
          <div class="flex">
            <span class="text-gray-500 w-1/3">统计周期：</span>
            <span class="text-gray-800 font-semibold text-blue-600">{{
              formData.statCycle || '-'
            }}</span>
          </div>
          <div class="flex">
            <span class="text-gray-500 w-1/3">统计周期名称：</span>
            <span class="text-gray-800">{{ formData.statCycleName || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 行政区划信息卡片 -->
      <el-card class="card-hover">
        <template #header>
          <div class="bg-green-50 text-green-600 font-medium py-2 px-4"> 行政区划信息</div>
        </template>
        <div class="grid grid-cols-1 gap-4 p-4">
          <div class="flex">
            <span class="text-gray-500 w-1/3">行政区划代码：</span>
            <span class="text-gray-800 font-semibold text-blue-600">{{
              formData.adminCode || '-'
            }}</span>
          </div>
          <div class="flex">
            <span class="text-gray-500 w-1/3">行政区划名称：</span>
            <span class="text-gray-800">{{ formData.adminName || '-' }}</span>
          </div>
          <div class="flex">
            <span class="text-gray-500 w-1/3">点位类型：</span>
            <span class="text-gray-800">{{ formData.ptType || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 点位统计信息卡片 -->
      <el-card class="card-hover">
        <template #header>
          <div class="bg-purple-50 text-purple-600 font-medium py-2 px-4"> 点位统计信息</div>
        </template>
        <div class="grid grid-cols-1 gap-4 p-4">
          <div class="flex">
            <span class="text-gray-500 w-1/3">点位总数(个)：</span>
            <span class="text-gray-800 font-semibold text-blue-600">{{
              formData.totalPtCount || '-'
            }}</span>
          </div>
          <div class="flex">
            <span class="text-gray-500 w-1/3">启用点位数量(个)：</span>
            <span class="text-gray-800 text-green-600">{{ formData.enablePtCount || '-' }}</span>
          </div>
          <div class="flex">
            <span class="text-gray-500 w-1/3">停用点位数量(个)：</span>
            <span class="text-gray-800 text-red-600">{{ formData.disablePtCount || '-' }}</span>
          </div>
          <div class="flex">
            <span class="text-gray-500 w-1/3">新增点位数量(个)：</span>
            <span class="text-gray-800">{{ formData.newPtCount || '-' }}</span>
          </div>
          <div class="flex">
            <span class="text-gray-500 w-1/3">关联设备总数(个)：</span>
            <span class="text-gray-800">{{ formData.relDeviceCount || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 报表信息卡片 -->
      <el-card class="card-hover">
        <template #header>
          <div class="bg-yellow-50 text-yellow-600 font-medium py-2 px-4"> 报表信息</div>
        </template>
        <div class="grid grid-cols-1 gap-4 p-4">
          <div class="flex">
            <span class="text-gray-500 w-1/3">报表生成时间：</span>
            <span class="text-gray-800">{{ formatDate(formData.rptCreateTime) }}</span>
          </div>
          <div class="flex">
            <span class="text-gray-500 w-1/3">报表生成人：</span>
            <span class="text-gray-800">{{ formData.rptCreateUser || '-' }}</span>
          </div>
          <div class="flex flex-col">
            <span class="text-gray-500">报表备注：</span>
            <span class="text-gray-800 mt-1">{{ formData.rptRemark || '-' }}</span>
          </div>
        </div>
      </el-card>

      <!-- 扩展信息卡片 -->
      <el-card class="card-hover md:col-span-2">
        <template #header>
          <div class="bg-pink-50 text-pink-600 font-medium py-2 px-4"> 扩展信息</div>
        </template>
        <div class="grid grid-cols-1 md:grid-cols-2 gap-4 p-4">
          <div class="flex">
            <span class="text-gray-500 w-1/3">分类扩展字段1：</span>
            <span class="text-gray-800">{{ formData.extCat1 || '-' }}</span>
          </div>
          <div class="flex">
            <span class="text-gray-500 w-1/3">分类扩展字段2：</span>
            <span class="text-gray-800">{{ formData.extCat2 || '-' }}</span>
          </div>
          <div class="flex">
            <span class="text-gray-500 w-1/3">通用扩展字段1：</span>
            <span class="text-gray-800">{{ formData.extCommon1 || '-' }}</span>
          </div>
          <div class="flex">
            <span class="text-gray-500 w-1/3">通用扩展字段2：</span>
            <span class="text-gray-800">{{ formData.extCommon2 || '-' }}</span>
          </div>
        </div>
      </el-card>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { BasicMonIdxRptVO } from '@/api/dataHub/operationMonitor/basicMonitorReport/basicmonidxrpt'

const visible = ref(false)
const title = ref('基础监测指标报表详情')
const isFullscreen = ref(false)
const formData = ref<Partial<BasicMonIdxRptVO>>({})

// 格式化日期
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

// 打开抽屉并加载数据
const open = (data: BasicMonIdxRptVO) => {
  visible.value = true
  formData.value = { ...data }
}

defineExpose({ open })
</script>

<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f9fafb;
}

.card-hover {
  transition: all 0.3s ease;
  border-radius: 8px;
  overflow: hidden;
}

.card-hover:hover {
  transform: translateY(-4px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
}

/* 马克龙色系配置 */
::v-deep .bg-blue-50 {
  background-color: #e6f4ff;
}

::v-deep .text-blue-600 {
  color: #165dff;
}

::v-deep .bg-green-50 {
  background-color: #f0fff4;
}

::v-deep .text-green-600 {
  color: #00b42a;
}

::v-deep .bg-purple-50 {
  background-color: #f9f0ff;
}

::v-deep .text-purple-600 {
  color: #722ed1;
}

::v-deep .bg-yellow-50 {
  background-color: #fffbe6;
}

::v-deep .text-yellow-600 {
  color: #ff7d00;
}

::v-deep .bg-pink-50 {
  background-color: #fff0f3;
}

::v-deep .text-pink-600 {
  color: #f53f3f;
}
</style>
