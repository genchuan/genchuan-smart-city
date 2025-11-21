<!-- 资产分类管理抽屉 -->
<template>
  <el-drawer
    v-model="visible"
    size="75%"
    title="资产分类管理详情"
    :destroy-on-close="true"
  >
    <template #header>
      <div class="drawer-header">
        <span class="text-lg font-bold">资产分类管理详情</span>
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

        <el-descriptions-item label="资产分类ID">
          {{ detail?.assetCatId ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="关联分类规则ID">
          {{ detail?.relCatRuleId ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="资产分类编码">
          {{ detail?.assetCatCode ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="资产分类名称">
          {{ detail?.assetCatName ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="分类层级">
          {{ getCatLevelLabel(detail?.catLevel) ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="上级分类ID">
          {{ detail?.parentCatId ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="上级分类名称">
          {{ detail?.parentCatName ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="分类说明">
          {{ detail?.catDesc ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="启用状态">
          <el-tag v-if="getEnableStatusValue() === 1" type="success">启用</el-tag>
          <el-tag v-else-if="getEnableStatusValue() === 0" type="info">禁用</el-tag>
          <span v-else>-</span>
        </el-descriptions-item>

        <el-descriptions-item label="创建人">
          {{ detail?.createUser ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="创建时间">
          {{ formatDateDisplay(detail?.createdTime) }}
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
import { AssetCatMngApi, AssetCatMngVO } from '@/api/dataHub/assetManagement/assetOperationManagement/assetCatMng'
import { formatDate } from '@/utils/formatTime'
import { ElMessage } from 'element-plus'

/** 组件参数与状态 */
const props = defineProps<{
  modelValue: boolean
  id?: number
  assetCatMng?: AssetCatMngVO
}>()

/** 分类层级选项映射 */
const catLevelOptions = [
  { label: '大类', value: 1 },
  { label: '中类', value: 2 },
  { label: '小类', value: 3 },
]

/** 启用状态选项映射 */
const enableStatusOptions = [
  { label: '启用', value: 1 },
  { label: '禁用', value: 0 }
]

/** 获取分类层级标签 */
const getCatLevelLabel = (catLevel?: string | number) => {
  if (catLevel === undefined || catLevel === null) return '-'
  const option = catLevelOptions.find(item => String(item.value) === String(catLevel))
  return option ? option.label : catLevel
}

/** 获取启用状态的值（处理字符串和数字类型） */
const getEnableStatusValue = () => {
  if (detail.value?.enableStatus === undefined || detail.value?.enableStatus === null) {
    return undefined
  }
  // 如果是字符串，转换为数字
  if (typeof detail.value.enableStatus === 'string') {
    return Number(detail.value.enableStatus)
  }
  // 如果是数字，直接返回
  return detail.value.enableStatus
}

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

const detail = ref<AssetCatMngVO | null>(props.assetCatMng ?? null)
const loading = ref(false)

/** 刷新详情数据 */
const handleRefresh = async () => {
  if (!props.id) return
  try {
    loading.value = true
    const res = await AssetCatMngApi.getAssetCatMng(props.id)
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
    if (val && props.id && !props.assetCatMng) {
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