<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    title="评价网格编码详情"
    :destroy-on-close="true"
    :with-header="true"
  >
    <!-- Header -->
    <template #header>
      <div class="flex items-center justify-between w-full">
        <span class="text-lg font-bold">评价网格编码详情</span>
        <el-button link type="primary" @click="toggleFullscreen">
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="p-4 bg-gray-50 rounded-lg space-y-5">

      <!-- 编码构成展示 -->
      <el-card shadow="hover">
        <template #header>
          <div class="font-bold text-base text-gray-700">📘 编码构成</div>
        </template>

        <div v-if="detail?.evalGridCode" class="flex justify-around text-center text-sm">
          <div class="flex flex-col items-center">
            <div class="text-gray-500 mb-1">县级行政代码</div>
            <div class="text-blue-600 font-semibold">{{ parts.county }}</div>
          </div>
          <div class="text-gray-400 text-lg">|</div>
          <div class="flex flex-col items-center">
            <div class="text-gray-500 mb-1">街道代码</div>
            <div class="text-green-600 font-semibold">{{ parts.street }}</div>
          </div>
          <div class="text-gray-400 text-lg">|</div>
          <div class="flex flex-col items-center">
            <div class="text-gray-500 mb-1">顺序码</div>
            <div class="text-orange-600 font-semibold">{{ parts.seq }}</div>
          </div>
          <div class="text-gray-400 text-lg">|</div>
          <div class="flex flex-col items-center">
            <div class="text-gray-500 mb-1">类型码</div>
            <el-tag
              :type="parts.type === 'A' ? 'danger' : parts.type === 'B' ? 'primary' : 'success'"
              effect="plain"
            >
              {{ parts.type }}
            </el-tag>
          </div>
        </div>

        <div v-else class="text-center text-gray-400 py-3">暂无编码信息</div>
      </el-card>

      <!-- 基本信息 -->
      <el-descriptions :column="2" border>
        <el-descriptions-item label="编码ID">{{ detail?.evalCodeId }}</el-descriptions-item>
        <el-descriptions-item label="评价网格标识码">{{ detail?.evalGridCode }}</el-descriptions-item>
        <el-descriptions-item label="所属评价网格ID">{{ detail?.evalGridId }}</el-descriptions-item>
        <el-descriptions-item label="行政区划完整代码">{{ detail?.areaFullCode }}</el-descriptions-item>
        <el-descriptions-item label="顺序码">{{ detail?.seqCode }}</el-descriptions-item>
        <el-descriptions-item label="类型码(A/B/C)">
          <el-tag
            :type="detail?.typeCode === 'A' ? 'danger' : detail?.typeCode === 'B' ? 'primary' : 'success'"
            effect="plain"
          >
            {{ detail?.typeCode }}
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="编码状态">
          <el-tag :type="detail?.codeStatus === '有效' ? 'success' : 'danger'">
            {{ detail?.codeStatus }}
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="编码生成时间">{{ formatDate(detail?.generateTime) }}</el-descriptions-item>
        <el-descriptions-item label="备注">{{ detail?.remark || '-' }}</el-descriptions-item>
      </el-descriptions>

      <!-- 所含管理网格 -->
      <el-card shadow="hover">
        <template #header>
          <div class="font-bold text-base text-gray-700">📋 所含管理网格</div>
        </template>
        <el-table :data="mockMngList" border stripe>
          <el-table-column label="管理网格名称" prop="name" />
          <el-table-column label="网格编号" prop="code" />
          <el-table-column label="状态" prop="status">
            <template #default="{ row }">
              <el-tag :type="row.status === '有效' ? 'success' : 'danger'">{{ row.status }}</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </el-card>

      <!-- ✅ 编码变更记录（保留你原始样式） -->
      <el-card shadow="hover">
        <template #header>
          <div class="font-bold text-base text-gray-700">📜 编码状态变更记录</div>
        </template>

        <el-timeline>
          <el-timeline-item
            v-for="(item, index) in changeLogs"
            :key="index"
            :timestamp="item.time"
            :color="item.status === '有效' ? '#67C23A' : item.status === '作废' ? '#909399' : '#E6A23C'"
          >
            <div class="flex flex-col">
              <div>
                <el-tag :type="item.status === '有效' ? 'success' : item.status === '作废' ? 'info' : 'warning'">
                  {{ item.status }}
                </el-tag>
              </div>
              <div class="mt-1 text-gray-600">操作人：{{ item.operator }}</div>
              <div class="text-gray-500 text-sm">{{ item.remark }}</div>
            </div>
          </el-timeline-item>
        </el-timeline>
      </el-card>
    </div>

    <div class="mt-4 text-right">
      <el-button @click="visible = false">关闭</el-button>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import type { EvalGridCodeVO } from '@/api/dataHub/gridManagement/gridCode/evalgridcode'

const visible = ref(false)
const isFullscreen = ref(false)
const detail = ref<EvalGridCodeVO | null>(null)

/** 编码分段 */
const parts = computed(() => {
  const code = detail.value?.evalGridCode || ''
  return {
    county: code.slice(0, 6) || '-',
    street: code.slice(6, 9) || '-',
    seq: code.slice(9, 11) || '-',
    type: code.slice(11) || '-'
  }
})

/** 模拟数据 */
const mockMngList = ref([
  { name: '东街管理网格001', code: 'MNG001', status: '有效' },
  { name: '东街管理网格002', code: 'MNG002', status: '有效' },
  { name: '东街管理网格003', code: 'MNG003', status: '作废' }
])

const changeLogs = ref([
  { status: '草稿', time: '2024-03-10 09:30:12', operator: '系统自动生成', remark: '初次生成编码' },
  { status: '有效', time: '2024-03-11 14:22:09', operator: '张三', remark: '审核通过，启用编码' },
  { status: '作废', time: '2024-05-08 10:45:33', operator: '李四', remark: '该编码已被合并至上级编码' }
])

/** 打开详情 */
const open = (row: EvalGridCodeVO) => {
  detail.value = row
  visible.value = true
}

/** 全屏切换 */
const toggleFullscreen = () => {
  isFullscreen.value = !isFullscreen.value
}

/** 格式化时间 */
const formatDate = (val?: string | Date) => {
  if (!val) return '-'
  const d = new Date(val)
  return d.toLocaleString()
}

defineExpose({ open })
</script>

<style scoped>
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
.mt-4 {
  margin-top: 16px;
}
</style>
