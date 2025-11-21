<!-- 资产空间数据抽屉组件 -->
<template>
  <el-drawer
    v-model="visible"
    size="75%"
    title="资产空间数据详情"
    :destroy-on-close="true"
  >
    <template #header>
      <div class="drawer-header">
        <span class="text-lg font-bold">资产空间数据详情</span>
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

        <el-descriptions-item label="空间数据ID">
          {{ detail?.assetSpatialId ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="关联资产ID">
          {{ detail?.relAssetId ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="关联资产名称">
          {{ detail?.relAssetName ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="坐标系类型">
          {{ getCoordSystemTypeLabel(detail?.coordSystemType) ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="坐标X">
          {{ detail?.coordX ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="坐标Y">
          {{ detail?.coordY ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="高程">
          {{ detail?.elevation ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="边界坐标">
          {{ detail?.boundaryCoords ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="空间数据来源">
          {{ getSpatialDataSourceLabel(detail?.spatialDataSource) ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="录入时间">
          {{ formatDateDisplay(detail?.inputTime) }}
        </el-descriptions-item>

        <el-descriptions-item label="操作人">
          {{ detail?.operUser ?? '-' }}
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
import { AssetSpatialDataApi, AssetSpatialDataVO } from '@/api/dataHub/assetManagement/assetDataMng/assetSpatialData'
import { formatDate } from '@/utils/formatTime'
import { ElMessage } from 'element-plus'

/** 组件参数与状态 */
const props = defineProps<{
  modelValue: boolean
  id?: number
  assetSpatialData?: AssetSpatialDataVO
}>()

/** 空间数据来源选项映射 */
const spatialDataSourceOptions = [
  { label: '地图拾取', value: 'map_pick' },
  { label: '手动导入', value: 'manual' },
  { label: '文件导入', value: 'import' }
]

/** 坐标系类型选项映射 */
const coordSystemTypeOptions = [
  { label: 'WGS84', value: 'WGS84' },
  { label: 'GCJ02', value: 'GCJ02' }
]

/** 获取空间数据来源标签 */
const getSpatialDataSourceLabel = (spatialDataSource?: string) => {
  if (!spatialDataSource) return '-'
  const option = spatialDataSourceOptions.find(item => item.value === spatialDataSource)
  return option ? option.label : spatialDataSource
}

/** 获取坐标系类型标签 */
const getCoordSystemTypeLabel = (coordSystemType?: string) => {
  if (!coordSystemType) return '-'
  const option = coordSystemTypeOptions.find(item => item.value === coordSystemType)
  return option ? option.label : coordSystemType
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

const detail = ref<AssetSpatialDataVO | null>(props.assetSpatialData ?? null)
const loading = ref(false)

/** 刷新详情数据 */
const handleRefresh = async () => {
  if (!props.id) return
  try {
    loading.value = true
    const res = await AssetSpatialDataApi.getAssetSpatialData(props.id)
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
    if (val && props.id && !props.assetSpatialData) {
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