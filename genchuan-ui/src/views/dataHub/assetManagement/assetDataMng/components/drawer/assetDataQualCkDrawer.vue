<!-- 资产数据质量检测抽屉组件 -->
<template>
  <el-drawer
    v-model="visible"
    size="75%"
    title="资产数据质量检测详情"
    :destroy-on-close="true"
  >
    <template #header>
      <div class="drawer-header">
        <span class="text-lg font-bold">资产数据质量检测详情</span>
        <el-button link type="primary" @click="handleRefresh">
          <Icon icon="ep:refresh" class="mr-5px" /> 刷新
        </el-button>
      </div>
    </template>

    <el-scrollbar height="calc(100vh - 150px)">
      <el-descriptions
        :column="2"
        border
        size="default"
        class="detail-desc"
      >
        <el-descriptions-item label="主键ID">
          {{ detail?.id ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="质量检查ID">
          {{ detail?.assetDataQualId ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="关联资产ID">
          {{ detail?.relAssetId ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="关联资产名称">
          {{ detail?.relAssetName ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="检查数据类型">
          {{ getCkDataTypeLabel(detail?.ckDataType) ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="检查项名称">
          {{ detail?.ckItemName ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="检查项代码">
          {{ detail?.ckItemCode ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="检查结果">
          {{ getCkResultLabel(detail?.ckResult) ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="错误描述">
          {{ detail?.errorDesc ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="检查时间">
          {{ formatDateDisplay(detail?.ckTime) }}
        </el-descriptions-item>

        <el-descriptions-item label="检查方式">
          {{ getCkMethodLabel(detail?.ckMethod) ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="处理状态">
          {{ getHandleStatusLabel(detail?.handleStatus) ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="处理人">
          {{ detail?.handleUser ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="处理时间">
          {{ formatDateDisplay(detail?.handleTime) }}
        </el-descriptions-item>
      </el-descriptions>

      <!-- 扩展字段区域 -->
      <el-divider content-position="left">扩展字段</el-divider>
      
      <el-descriptions
        :column="2"
        border
        size="default"
        class="detail-desc"
      >
        <el-descriptions-item label="分类扩展字段1">
          {{ detail?.extCat1 || '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="分类扩展字段2">
          {{ detail?.extCat2 || '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="通用扩展字段1">
          {{ detail?.extCommon1 || '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="通用扩展字段2">
          {{ detail?.extCommon2 || '-' }}
        </el-descriptions-item>
      </el-descriptions>
    </el-scrollbar>

    <template #footer>
      <div class="flex justify-end">
        <el-button @click="visible = false">关闭</el-button>
      </div>
    </template>
  </el-drawer>
</template>

<script lang="ts" setup>
import { AssetDataQualCkApi, AssetDataQualCkVO } from '@/api/dataHub/assetManagement/assetDataMng/assetDataQualCk'
import { formatDate } from '@/utils/formatTime'
import { ElMessage } from 'element-plus'

/** 组件参数与状态 */
const props = defineProps<{
  modelValue: boolean
  id?: number
  assetDataQualCk?: AssetDataQualCkVO
}>()

/** 检查数据类型选项映射 */
const ckDataTypeOptions = [
  { label: '空间数据', value: 'spatial' },
  { label: '服务端属性', value: 'server_attr' },
  { label: '客户端属性', value: 'client_attr' }
]

/** 检查结果选项映射 */
const ckResultOptions = [
  { label: '合格', value: 'qualified' },
  { label: '不合格', value: 'unqualified' }
]

/** 检查方式选项映射 */
const ckMethodOptions = [
  { label: '自动定期检查', value: 'auto' },
  { label: '手动触发检查', value: 'manual' }
]

/** 处理状态选项映射 */
const handleStatusOptions = [
  { label: '待处理', value: 'pend' },
  { label: '已处理', value: 'handled' }
]

/** 获取检查数据类型标签 */
const getCkDataTypeLabel = (ckDataType?: string) => {
  if (!ckDataType) return '-'
  const option = ckDataTypeOptions.find(item => item.value === ckDataType)
  return option ? option.label : ckDataType
}

/** 获取检查结果标签 */
const getCkResultLabel = (ckResult?: string) => {
  if (!ckResult) return '-'
  const option = ckResultOptions.find(item => item.value === ckResult)
  return option ? option.label : ckResult
}

/** 获取检查方式标签 */
const getCkMethodLabel = (ckMethod?: string) => {
  if (!ckMethod) return '-'
  const option = ckMethodOptions.find(item => item.value === ckMethod)
  return option ? option.label : ckMethod
}

/** 获取处理状态标签 */
const getHandleStatusLabel = (handleStatus?: string) => {
  if (!handleStatus) return '-'
  const option = handleStatusOptions.find(item => item.value === handleStatus)
  return option ? option.label : handleStatus
}

/** 通用日期格式化显示 */
const formatDateDisplay = (val?: string | Date | number) => {
  if (!val) return '-'
  return formatDate(new Date(val), 'YYYY-MM-DD HH:mm:ss')
}

const emits = defineEmits(['update:modelValue'])

const visible = computed({
  get: () => props.modelValue,
  set: (val) => emits('update:modelValue', val)
})

const detail = ref<AssetDataQualCkVO | null>(props.assetDataQualCk ?? null)
const loading = ref(false)

/** 刷新详情数据 */
const handleRefresh = async () => {
  if (!props.id) return
  try {
    loading.value = true
    const res = await AssetDataQualCkApi.getAssetDataQualCk(props.id)
    detail.value = res
    ElMessage.success('刷新成功')
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
    if (val && props.id && !props.assetDataQualCk) {
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