<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    title="网格检查详情"
    :destroy-on-close="true"
  >
    <!-- Header -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold">网格检查详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="p-4 grid grid-cols-2 gap-5">
      <!-- 基本信息 -->
      <el-card shadow="hover">
        <div class="section-header bg-blue-100 text-blue-700">基本信息</div>
        <div class="mt-3 space-y-2">
          <p><b>网格编码：</b>{{ record?.gridCode || '-' }}</p>
          <p><b>网格类型：</b>{{ record?.gridType || '-' }}</p>
          <p><b>检查项：</b>{{ record?.ckItem || '-' }}</p>
          <p>
            <b>检查结果：</b>
            <el-tag :type="record?.ckResult === 'PASS' ? 'success' : 'danger'">
              {{ record?.ckResult === 'PASS' ? '合格' : '不合格' }}
            </el-tag>
          </p>
        </div>
      </el-card>

      <!-- 整改信息 -->
      <el-card shadow="hover">
        <div class="section-header bg-green-100 text-green-700">整改信息</div>
        <div class="mt-3 space-y-2">
          <p><b>整改状态：</b>{{ rectifyStatusMap[record?.rectifyStatus || '0'] }}</p>
          <p><b>整改人员：</b>{{ record?.rectifyUserId || '-' }}</p>
          <p><b>整改时间：</b>{{ formatDate(record?.rectifyTime) }}</p>
          <p><b>整改建议：</b>{{ record?.rectifySugg || '-' }}</p>
        </div>
      </el-card>

      <!-- 错误描述 -->
      <el-card shadow="hover" class="col-span-2">
        <div class="section-header bg-yellow-100 text-yellow-700">错误描述</div>
        <div class="mt-3 space-y-2">
          <p>{{ record?.errorDesc || '无错误' }}</p>
        </div>
      </el-card>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { computed, ref } from 'vue'

/** ✅ Props 定义，允许 record 为 null 或 undefined */
const props = defineProps<{
  modelValue: boolean
  record?: Record<string, any> | null
}>()

const emit = defineEmits(['update:modelValue'])

/** 抽屉显示控制 */
const visible = computed({
  get: () => props.modelValue,
  set: (v) => emit('update:modelValue', v)
})

/** 全屏切换 */
const isFullscreen = ref(false)

/** 整改状态映射 */
const rectifyStatusMap: Record<string, string> = {
  COMPLETED: '已整改',
  PENDING: '待处理',
  IN_PROGRESS: '整改中'
}
// 日期格式化
const formatDate = (val: string | Date | undefined) => {
  if (!val) return '-'
  const d = new Date(val)
  return `${d.getFullYear()}-${(d.getMonth() + 1)
    .toString()
    .padStart(2, '0')}-${d.getDate().toString().padStart(2, '0')} ${d
    .getHours()
    .toString()
    .padStart(2, '0')}:${d.getMinutes().toString().padStart(2, '0')}`
}
/** record 兜底（防止为 null 时访问属性报错） */
const record = computed(() => props.record || {})
</script>

<style scoped>
.section-header {
  font-weight: 600;
  padding: 5px 10px;
  border-radius: 6px;
  display: inline-block;
}
</style>
