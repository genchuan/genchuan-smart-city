<!-- 关联资产抽屉组件 -->
<template>
  <el-drawer
    v-model="visible"
    size="75%"
    title="资产关联资产详情"
    :destroy-on-close="true"
  >
    <template #header>
      <div class="drawer-header">
        <span class="text-lg font-bold">资产关联资产详情</span>
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

        <el-descriptions-item label="关联ID">
          {{ detail?.assetRelAssetId ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="从资产ID">
          {{ detail?.slaveAssetId ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="从资产名称">
          {{ detail?.slaveAssetName ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="主资产ID">
          {{ detail?.masterAssetId ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="主资产名称">
          {{ detail?.masterAssetName ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="关联类型">
          {{ getRelTypeLabel(detail?.relType) ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="关联时间">
          {{ formatDateDisplay(detail?.relTime) }}
        </el-descriptions-item>

        <el-descriptions-item label="操作人">
          {{ detail?.operUser ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="关联说明">
          {{ detail?.relDesc ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="更新人">
          {{ detail?.updateUser ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="更新时间">
          {{ formatDateDisplay(detail?.updatedTime) }}
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
import { AssetAssetApi, AssetAssetVO } from '@/api/dataHub/assetManagement/assetOperationManagement/assetAsset'
import { formatDate } from '@/utils/formatTime'
import { ElMessage } from 'element-plus'

/** 组件参数与状态 */
const props = defineProps<{
  modelValue: boolean
  id?: number
  assetAsset?: AssetAssetVO
}>()

/** 关联类型选项映射 */
const relTypeOptions = [
  { label: '类型A', value: '类型A' },
  { label: '类型B', value: '类型B' }
]

/** 获取关联类型标签 */
const getRelTypeLabel = (relType?: string) => {
  if (!relType) return '-'
  const option = relTypeOptions.find(item => item.value === relType)
  return option ? option.label : relType
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

const detail = ref<AssetAssetVO | null>(props.assetAsset ?? null)
const loading = ref(false)

/** 刷新详情数据 */
const handleRefresh = async () => {
  if (!props.id) return
  try {
    loading.value = true
    const res = await AssetAssetApi.getAssetAsset(props.id)
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
    if (val && props.id && !props.assetAsset) {
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