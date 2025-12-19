<template>
  <el-drawer
    v-model="visible"
    :size="isFullscreen ? '100%' : '75%'"
    direction="rtl"
    :append-to-body="true"
    class="detail-drawer"
  >
    <!-- 抽屉头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">行政区划详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <el-scrollbar height="calc(100vh - 150px)">
      <el-descriptions :column="2" border size="default" class="detail-desc">
        <el-descriptions-item label="主键ID">
          {{ detail?.id ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="上级ID">
          {{ detail?.parentId ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="完整代码">
          {{ detail?.fullCode ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="短代码">
          {{ detail?.shortCode ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="名称">
          {{ detail?.name ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="行政级别">
          {{ renderLevel(detail?.level) }}
        </el-descriptions-item>

        <el-descriptions-item label="类型">
          {{ detail?.areaType ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="生效时间">
          {{ formatDateDisplay(detail?.effectiveTime) }}
        </el-descriptions-item>

        <el-descriptions-item label="失效时间">
          {{ formatDateDisplay(detail?.invalidTime) }}
        </el-descriptions-item>

        <el-descriptions-item label="备注" :span="2">
          {{ detail?.remark || '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="生效状态" :span="2">
          <el-tag v-if="isEffective(detail)" type="success">生效</el-tag>
          <el-tag v-else type="info">已失效</el-tag>
        </el-descriptions-item>
      </el-descriptions>

      <!-- 预留：关联网格、下级区域、历史版本等扩展区 -->
      <!-- <el-divider content-position="left">关联网格信息</el-divider> -->
    </el-scrollbar>

    <template #footer>
      <div class="flex justify-end">
        <el-button @click="visible = false">关闭</el-button>
      </div>
    </template>
  </el-drawer>
</template>

<script lang="ts" setup>
import { AreaApi, AreaVO } from '@/api/dataHub/gridManagement/adminDivConfig'
import { formatDate } from '@/utils/formatTime'
import { ElMessage } from 'element-plus'

const isFullscreen = ref(false) // 全屏状态
/** 组件参数与状态 */
const props = defineProps<{
  modelValue: boolean
  id?: number
  area?: AreaVO
}>()

/** 通用日期格式化显示 */
const formatDateDisplay = (val?: string | Date) => {
  if (!val) return '-'
  return formatDate(new Date(val), 'YYYY-MM-DD HH:mm:ss')
}

const emits = defineEmits(['update:modelValue'])

const visible = computed({
  get: () => props.modelValue,
  set: (val) => emits('update:modelValue', val)
})

const detail = ref<AreaVO | null>(props.area ?? null)
const loading = ref(false)

/** 渲染层级文本 */
const renderLevel = (lvl?: number) => {
  const map: Record<number, string> = {
    1: '省级 (1)',
    2: '市级 (2)',
    3: '县级 (3)',
    4: '乡镇 (4)',
    5: '社区 (5)'
  }
  return map[lvl ?? 0] ?? '-'
}

/** 判断是否生效 */
const isEffective = (row?: AreaVO | null) => {
  if (!row) return false
  const now = Date.now()
  const eff = row.effectiveTime ? new Date(row.effectiveTime).getTime() : null
  const inv = row.invalidTime ? new Date(row.invalidTime).getTime() : null
  if (eff && now < eff) return false
  if (!inv) return true
  return now <= inv
}

/** 刷新详情数据 */
const handleRefresh = async () => {
  if (!props.id) return
  try {
    loading.value = true
    const res = await AreaApi.getArea(props.id)
    detail.value = res
  } catch (e) {
    ElMessage.error('加载详情失败')
  } finally {
    loading.value = false
  }
}

/** 当抽屉打开时自动加载详情 */
watch(
  () => visible.value,
  (val) => {
    if (val && props.id && !props.area) {
      handleRefresh()
    }
  },
  { immediate: true }
)
</script>

<style scoped>
.drawer-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.detail-desc {
  padding: 10px 20px;
  font-size: 14px;
}
</style>
