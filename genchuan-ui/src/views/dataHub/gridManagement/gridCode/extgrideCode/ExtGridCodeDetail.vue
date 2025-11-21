<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    title="扩展网格编码详情"
    :destroy-on-close="true"
  >
    <!-- Header 区 -->
    <template #header>
      <div class="flex justify-between items-center w-full">
        <span class="text-lg font-bold">扩展网格编码详情</span>
        <el-button type="primary" text @click="toggleFullscreen">
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <!-- 内容区 -->
    <div class="p-4">
      <el-descriptions :column="2" border>
        <el-descriptions-item label="编码ID">{{ detail.extCodeId }}</el-descriptions-item>
        <el-descriptions-item label="扩展网格标识码">{{ detail.extGridCode }}</el-descriptions-item>
        <el-descriptions-item label="所属扩展网格ID">{{ detail.extGridId }}</el-descriptions-item>
        <el-descriptions-item label="行政区划完整代码">{{ detail.areaFullCode }}</el-descriptions-item>
        <el-descriptions-item label="顺序码">{{ detail.seqCode }}</el-descriptions-item>
        <el-descriptions-item label="类型码">
          <el-tag :type="getTypeColor(detail.typeCode)">
            {{ getTypeLabel(detail.typeCode) }}
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="生成时间">{{ formatDate(detail.generateTime) }}</el-descriptions-item>
        <el-descriptions-item label="状态">
          <el-tag :type="detail.codeStatus === '有效' ? 'success' : 'danger'">
            {{ detail.codeStatus }}
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="备注" :span="2">{{ detail.remark || '—' }}</el-descriptions-item>
      </el-descriptions>

      <!-- 编码构成 -->
<!--      <div class="mt-6">-->
<!--        <h3 class="font-bold mb-2">编码构成</h3>-->
<!--        <div class="flex items-center space-x-3 text-center">-->
<!--          <div class="p-3 bg-gray-100 rounded-lg w-32">-->
<!--            <p class="font-mono text-lg">{{ formatCodePart(detail.extGridCode, 0, 6) }}</p>-->
<!--            <p class="text-xs text-gray-500">县级代码</p>-->
<!--          </div>-->
<!--          <div class="p-3 bg-gray-100 rounded-lg w-32">-->
<!--            <p class="font-mono text-lg">{{ formatCodePart(detail.extGridCode, 6, 9) }}</p>-->
<!--            <p class="text-xs text-gray-500">街道代码</p>-->
<!--          </div>-->
<!--          <div class="p-3 bg-gray-100 rounded-lg w-32">-->
<!--            <p class="font-mono text-lg">{{ formatCodePart(detail.extGridCode, 9, 12) }}</p>-->
<!--            <p class="text-xs text-gray-500">顺序码</p>-->
<!--          </div>-->
<!--          <div class="p-3 bg-gray-100 rounded-lg w-20">-->
<!--            <el-tag :type="getTypeColor(detail.typeCode)">-->
<!--              {{ detail.typeCode }}-->
<!--            </el-tag>-->
<!--            <p class="text-xs text-gray-500">类型码</p>-->
<!--          </div>-->
<!--        </div>-->
<!--      </div>-->

      <!-- 编码构成 -->
      <el-card shadow="hover" class="mt-6">
        <template #header>
          <div class="font-bold text-base text-gray-700">编码构成</div>
        </template>

        <div v-if="detail.extGridCode" class="flex justify-around text-center text-sm">
          <div class="flex flex-col items-center">
            <div class="text-gray-500 mb-1">县级代码</div>
            <div class="text-blue-600 font-semibold">{{ formatCodePart(detail.extGridCode, 0, 6) }}</div>
          </div>
          <div class="flex flex-col items-center">
            <div class="text-gray-500 mb-1">街道代码</div>
            <div class="text-green-600 font-semibold">{{ formatCodePart(detail.extGridCode, 6, 9) }}</div>
          </div>
          <div class="flex flex-col items-center">
            <div class="text-gray-500 mb-1">顺序码</div>
            <div class="text-orange-600 font-semibold">{{ formatCodePart(detail.extGridCode, 9, 12) }}</div>
          </div>
          <div class="flex flex-col items-center">
            <div class="text-gray-500 mb-1">类型码</div>
            <el-tag :type="getTypeColor(detail.typeCode)">
              {{ detail.typeCode }}
            </el-tag>
          </div>
        </div>

        <div v-else class="text-center text-gray-400 py-3">暂无编码信息</div>
      </el-card>


      <!-- 所含基础网格列表 -->
      <div class="mt-6">
        <h3 class="font-bold mb-2">所含基础网格</h3>
        <el-table :data="baseGrids" stripe style="width: 100%">
          <el-table-column prop="id" label="基础网格ID" align="center" width="180" />
          <el-table-column prop="name" label="网格名称" align="center" />
          <el-table-column prop="type" label="网格类型" align="center" />
          <el-table-column prop="areaCode" label="行政区划代码" align="center" />
        </el-table>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref } from 'vue'

// 接口类型定义
interface ExtGridCodeDetail {
  extCodeId: string
  extGridCode: string
  extGridId: string
  areaFullCode: string
  seqCode: string
  typeCode: string
  generateTime: string
  codeStatus: string
  remark?: string
}

// 基础网格信息类型
interface BaseGrid {
  id: string
  name: string
  type: string
  areaCode: string
}

const visible = ref(false)
const isFullscreen = ref(false)
const detail = ref<ExtGridCodeDetail>({
  extCodeId: '',
  extGridCode: '',
  extGridId: '',
  areaFullCode: '',
  seqCode: '',
  typeCode: '',
  generateTime: '',
  codeStatus: '',
  remark: ''
})
const baseGrids = ref<BaseGrid[]>([]) // ✅ 显式指定类型，避免 never 报错

// 打开详情抽屉
const open = (row: ExtGridCodeDetail) => {
  detail.value = { ...row }
  // 示例：模拟基础网格数据
  baseGrids.value = [
    { id: 'B001', name: '基础网格A', type: '居民区', areaCode: '330106023001' },
    { id: 'B002', name: '基础网格B', type: '工业区', areaCode: '330106023002' }
  ]
  visible.value = true
}

// 处理Tag类型错误
type TagType = 'success' | 'info' | 'warning' | 'primary' | 'danger'

/** 类型码 -> Tag 颜色映射（G=绿、Z=蓝、S=红、Q=灰(info)） */
const TYPE_TAG_MAP = {
  G: 'success',
  Z: 'primary',
  S: 'danger',
  Q: 'info'
} as const

type TypeCode = keyof typeof TYPE_TAG_MAP

/** 返回严格的 TagType，未知时用 'warning' 兜底，避免返回空字符串 */
const getTypeColor = (typeCode?: string): TagType => {
  return TYPE_TAG_MAP[(typeCode || '') as TypeCode] ?? 'warning'
}

// 工具函数
const toggleFullscreen = () => {
  isFullscreen.value = !isFullscreen.value
}

const formatDate = (time: string | Date | undefined) =>
  time ? new Date(time).toLocaleString() : '—'

// const getTypeColor = (typeCode: string | undefined) => {
//   switch (typeCode) {
//     case 'G': return 'success'
//     case 'Z': return 'primary'
//     case 'S': return 'danger'
//     case 'Q': return 'info'
//     default: return ''
//   }
// }

const getTypeLabel = (typeCode: string | undefined) => {
  switch (typeCode) {
    case 'G': return '工业园区'
    case 'Z': return '自贸区'
    case 'S': return '商圈'
    case 'Q': return '其他'
    default: return '未知'
  }
}

const formatCodePart = (code: string | undefined, start: number, end: number) => {
  if (!code) return ''
  return code.substring(start, end)
}

defineExpose({ open })
</script>
<style scoped>
.text-blue-600 { color: #2563eb; }
.text-green-600 { color: #16a34a; }
.text-orange-600 { color: #ea580c; }
.text-gray-500 { color: #6b7280; }
</style>
