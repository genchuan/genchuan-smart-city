<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    title="管理网格编码详情"
    :destroy-on-close="true"
    :with-header="true"
  >
    <!-- 自定义 header -->
    <template #header>
      <div class="flex items-center justify-between w-full">
        <span class="text-lg font-bold">管理网格编码详情</span>
        <div class="flex items-center space-x-2">
          <el-button link type="primary" @click="toggleFullscreen">
            {{ isFullscreen ? '退出全屏' : '全屏' }}
          </el-button>
        </div>
      </div>
    </template>

    <!-- 详情内容 -->
    <div class="p-4 bg-gray-50 rounded shadow-sm">
      <!-- 基础信息 -->
      <el-descriptions :column="2" border>
        <el-descriptions-item label="编码ID">{{ detail?.mgCodeId }}</el-descriptions-item>
        <el-descriptions-item label="管理网格标识码">{{ detail?.mgGridCode }}</el-descriptions-item>
        <el-descriptions-item label="所属管理网格ID">{{ detail?.mngGridId }}</el-descriptions-item>
        <el-descriptions-item label="行政区划完整代码">{{ detail?.areaFullCode }}</el-descriptions-item>
        <el-descriptions-item label="顺序码">{{ detail?.seqCode }}</el-descriptions-item>
        <el-descriptions-item label="编码状态">
          <el-tag :type="detail?.codeStatus === '有效' ? 'success' : 'danger'">
            {{ detail?.codeStatus }}
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="编码生成时间">{{ formatDate(detail?.generateTime) }}</el-descriptions-item>
        <el-descriptions-item label="备注">{{ detail?.remark || '-' }}</el-descriptions-item>
      </el-descriptions>

      <!-- 编码构成 -->
      <el-card shadow="hover" class="mt-6">
        <template #header>
          <div class="font-bold text-base text-gray-700">📘 编码构成</div>
        </template>

        <div v-if="detail?.mgGridCode" class="flex justify-around text-center text-sm">
          <div class="flex flex-col items-center">
            <div class="text-gray-500 mb-1">县级行政代码</div>
            <div class="text-blue-600 font-semibold">{{ formatCodePart(detail.mgGridCode, 0, 6) }}</div>
          </div>
          <div class="flex flex-col items-center">
            <div class="text-gray-500 mb-1">街道代码</div>
            <div class="text-green-600 font-semibold">{{ formatCodePart(detail.mgGridCode, 6, 9) }}</div>
          </div>
          <div class="flex flex-col items-center">
            <div class="text-gray-500 mb-1">顺序码</div>
            <div class="text-orange-600 font-semibold">{{ formatCodePart(detail.mgGridCode, 9, 12) }}</div>
          </div>
        </div>

        <div v-else class="text-center text-gray-400 py-3">暂无编码信息</div>
      </el-card>

      <!-- 所含单元网格列表 -->
      <el-card shadow="hover" class="mt-6">
        <template #header>
          <div class="font-bold text-base text-gray-700">📋 所含单元网格 ID 列表</div>
        </template>

        <el-table :data="unitGridList" border size="small">
          <el-table-column type="index" width="60" label="#" align="center" />
          <el-table-column prop="unitGridId" label="单元网格ID" align="center" />
        </el-table>
        <div v-if="unitGridList.length === 0" class="text-gray-500 text-sm mt-2 text-center">
          暂无单元网格数据（仅模拟展示）
        </div>
      </el-card>
    </div>

    <!-- 底部按钮 -->
    <div class="mt-3 text-right">
      <el-button @click="visible = false">关闭</el-button>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import type { MngGridCodeVO } from '@/api/dataHub/gridManagement/gridCode/mnggridcode'

const visible = ref(false)
const isFullscreen = ref(false)
const detail = ref<MngGridCodeVO | null>(null)
const unitGridList = ref<any[]>([])

/** 打开详情抽屉 */
const open = (row: MngGridCodeVO) => {
  if (!row) return
  detail.value = row
  // 模拟单元网格列表（仅示例）
  unitGridList.value = Array.from({ length: 5 }, (_, i) => ({
    unitGridId: `${row.mngGridId}-UNIT-${String(i + 1).padStart(3, '0')}`,
  }))
  visible.value = true
}

/** 截取编码段 */
const formatCodePart = (code: string, start: number, end: number) => {
  return code?.slice(start, end) || '-'
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

defineExpose({ open })
</script>

<style scoped>
.mt-3 {
  margin-top: 12px;
}
.mt-6 {
  margin-top: 24px;
}
.text-blue-600 {
  color: #2563eb;
}
.text-green-600 {
  color: #16a34a;
}
.text-orange-600 {
  color: #ea580c;
}
.text-gray-500 {
  color: #6b7280;
}
</style>
