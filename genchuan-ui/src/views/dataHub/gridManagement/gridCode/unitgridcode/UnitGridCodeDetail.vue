<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    title="单元网格编码详情"
    :destroy-on-close="true"
    :with-header="true"
  >
    <!-- 自定义 header -->
    <template #header>
      <div class="flex items-center justify-between w-full">
        <span class="text-lg font-bold">单元网格编码详情</span>
        <div class="flex items-center space-x-2">
          <el-button link type="primary" @click="toggleFullscreen">
            {{ isFullscreen ? '退出全屏' : '全屏' }}
          </el-button>
        </div>
      </div>
    </template>

    <div class="p-4 space-y-5">

      <!-- ① 基础信息 -->
      <el-card shadow="hover" class="bg-gray-50">
        <template #header>
          <div class="font-bold text-base text-gray-700">基础信息</div>
        </template>
        <el-descriptions :column="2" border>
          <el-descriptions-item label="编码ID">{{ detail?.unitCodeId }}</el-descriptions-item>
          <el-descriptions-item label="单元网格标识码">
            <span :class="detail?.codeStatus === '作废' ? 'text-red-500 font-semibold' : ''">
              {{ detail?.unitGridCode }}
            </span>
          </el-descriptions-item>
          <el-descriptions-item label="所属单元网格ID">{{ detail?.unitGridId }}</el-descriptions-item>
          <el-descriptions-item label="行政区划完整代码">{{ detail?.areaFullCode }}</el-descriptions-item>
          <el-descriptions-item label="顺序码">{{ detail?.seqCode }}</el-descriptions-item>
          <el-descriptions-item label="编码状态">
            <el-tag :type="detail?.codeStatus === '作废' ? 'danger' : 'success'">
              {{ detail?.codeStatus }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="备注">
            {{ detail?.remark || '—' }}
          </el-descriptions-item>
          <el-descriptions-item label="生成时间">{{ formatDate(detail?.generateTime) }}</el-descriptions-item>
        </el-descriptions>
      </el-card>

      <!-- ② 编码分段解析 -->
      <el-card shadow="hover">
        <template #header>
          <div class="font-bold text-base text-gray-700">编码分段构成</div>
        </template>

        <div v-if="segments" class="flex justify-around text-center text-sm">
          <div class="flex flex-col items-center">
            <div class="text-gray-500 mb-1">县级代码</div>
            <div class="text-blue-600 font-semibold">{{ segments.county }}</div>
          </div>
          <div class="flex flex-col items-center">
            <div class="text-gray-500 mb-1">街道代码</div>
            <div class="text-green-600 font-semibold">{{ segments.street }}</div>
          </div>
          <div class="flex flex-col items-center">
            <div class="text-gray-500 mb-1">社区代码</div>
            <div class="text-purple-600 font-semibold">{{ segments.comm }}</div>
          </div>
          <div class="flex flex-col items-center">
            <div class="text-gray-500 mb-1">顺序码</div>
            <div class="text-orange-600 font-semibold">{{ segments.seq }}</div>
          </div>
        </div>

        <div v-else class="text-center text-gray-400 py-3">暂无编码信息</div>
      </el-card>

      <!-- ③ 编码生成日志 -->
      <el-card shadow="hover">
        <template #header>
          <div class="font-bold text-base text-gray-700">编码生成日志</div>
        </template>

        <el-timeline>
          <el-timeline-item
            v-for="(log, index) in generateLogs"
            :key="index"
            :timestamp="log.time"
            :color="log.type === '作废' ? 'red' : 'green'"
          >
            <div class="font-medium">{{ log.action }}</div>
            <div class="text-gray-500 text-sm">{{ log.detail }}</div>
          </el-timeline-item>
        </el-timeline>
      </el-card>

      <!-- 底部按钮 -->
      <div class="text-right">
        <el-button @click="visible = false">关闭</el-button>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import type { UnitGridCodeVO } from '@/api/dataHub/gridManagement/gridCode/unitgridcode'

const visible = ref(false)
const isFullscreen = ref(false)
const detail = ref<UnitGridCodeVO | null>(null)

/** 打开详情抽屉 */
const open = (row: UnitGridCodeVO) => {
  if (!row) return
  detail.value = row
  visible.value = true
}

/** 切换全屏显示 */
const toggleFullscreen = () => {
  isFullscreen.value = !isFullscreen.value
}

/** 日期格式化 */
const formatDate = (val?: string | Date) => {
  if (!val) return '-'
  const d = new Date(val)
  return d.toLocaleString()
}

/** 分段解析：自动拆解15位编码 */
const segments = computed(() => {
  const code = detail.value?.unitGridCode
  if (!code || code.length !== 15) return null
  return {
    county: code.substring(0, 6),
    street: code.substring(6, 9),
    comm: code.substring(9, 12),
    seq: code.substring(12, 15)
  }
})

/** 模拟生成日志（基于状态与生成时间） */
const generateLogs = computed(() => {
  if (!detail.value) return []
  const logs = [
    {
      time: formatDate(detail.value.generateTime),
      action: '生成唯一编码',
      detail: `系统自动生成编码 ${detail.value.unitGridCode}，状态：${detail.value.codeStatus}`,
      type: '生成'
    }
  ]
  if (detail.value.codeStatus === '作废') {
    logs.push({
      time: formatDate(new Date()),
      action: '编码作废',
      detail: detail.value.remark || '该编码因网格调整已作废',
      type: '作废'
    })
  }
  return logs
})

defineExpose({ open })
</script>

<style scoped>
.text-blue-600 { color: #2563eb; }
.text-green-600 { color: #16a34a; }
.text-purple-600 { color: #7e22ce; }
.text-orange-600 { color: #ea580c; }
</style>
