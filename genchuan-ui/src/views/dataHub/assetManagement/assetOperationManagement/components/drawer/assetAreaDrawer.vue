<!-- 关联行政区划抽屉 -->
<template>
  <el-drawer
    v-model="visible"
    size="75%"
    title="资产关联行政区划详情"
    :destroy-on-close="true"
  >
    <template #header>
      <div class="drawer-header">
        <span class="text-lg font-bold">资产关联行政区划详情</span>
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
          {{ detail?.assetRelRegionId ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="关联资产ID">
          {{ detail?.relAssetId ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="关联资产名称">
          {{ detail?.relAssetName ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="行政区划代码">
          {{ detail?.regionCode ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="行政区划名称">
          {{ detail?.regionName ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="行政区划级别">
          {{ getRegionLevelLabel(detail?.regionLevel) ?? '-' }}
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
import { AssetAreaApi, AssetAreaVO } from '@/api/dataHub/assetManagement/assetOperationManagement/assetArea'
import { formatDate } from '@/utils/formatTime'
import { ElMessage } from 'element-plus'

/** 组件参数与状态 */
const props = defineProps<{
  modelValue: boolean
  id?: number
  assetArea?: AssetAreaVO
}>()

/** 行政区划级别选项映射 */
const regionLevelOptions = [
  { label: '省级', value: '省级' },
  { label: '市级', value: '市级' },
  { label: '县级', value: '县级' },
]

/** 获取行政区划级别标签 */
const getRegionLevelLabel = (regionLevel?: string) => {
  if (!regionLevel) return '-'
  const option = regionLevelOptions.find(item => item.value === regionLevel)
  return option ? option.label : regionLevel
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

const detail = ref<AssetAreaVO | null>(props.assetArea ?? null)
const loading = ref(false)

/** 刷新详情数据 */
const handleRefresh = async () => {
  if (!props.id) return
  try {
    loading.value = true
    const res = await AssetAreaApi.getAssetArea(props.id)
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
    if (val && props.id && !props.assetArea) {
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