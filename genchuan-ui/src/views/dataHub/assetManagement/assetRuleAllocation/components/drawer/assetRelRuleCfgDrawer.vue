<!-- 资产关联规则配置抽屉 -->
<template>
  <el-drawer
    v-model="visible"
    size="75%"
    title="资产关联规则配置详情"
    :destroy-on-close="true"
  >
    <template #header>
      <div class="drawer-header">
        <span class="text-lg font-bold">资产关联规则配置详情</span>
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

        <el-descriptions-item label="关联规则ID">
          {{ detail?.assetRelRuleId ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="关联资产分类ID">
          {{ detail?.relAssetCatId ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="关联资产分类名称">
          {{ detail?.relAssetCatName ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="关联对象类型">
          {{ getRelObjectTypeLabel(detail?.relObjectType) ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="关联对象名称">
          {{ detail?.relObjectName ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="是否必选">
          <el-tag v-if="getIsRequiredValue() === 1" type="success">是</el-tag>
          <el-tag v-else-if="getIsRequiredValue() === 0" type="info">否</el-tag>
          <span v-else>-</span>
        </el-descriptions-item>

        <el-descriptions-item label="关联数量限制">
          {{ detail?.relQuantityLimit ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="关联对象来源表">
          {{ getSourceTableLabel(detail?.relObjectSourceTable) ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="关联说明">
          {{ detail?.relDesc ?? '-' }}
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
import { AssetRelRuleCfgApi, AssetRelRuleCfgVO } from '@/api/dataHub/assetManagement/assetRuleAllocation/assetRelRuleCfg'
import { formatDate } from '@/utils/formatTime'
import { ElMessage } from 'element-plus'

/** 组件参数与状态 */
const props = defineProps<{
  modelValue: boolean
  id?: number
  assetRelRuleCfg?: AssetRelRuleCfgVO
}>()

/** 关联对象类型选项映射 */
const relObjectTypeOptions = [
  { label: '行政区划', value: 'region' },
  { label: '网格', value: 'grid' },
  { label: '管理部件', value: 'mng_comp' },
  { label: '应用场景', value: 'app_scene' },
]

/** 关联对象来源表选项映射 */
const relObjectSourceTableOptions = [
  { label: '表1', value: '表1' },
  { label: '表2', value: '表2' }
]

/** 获取关联对象类型标签 */
const getRelObjectTypeLabel = (type?: string) => {
  if (!type) return '-'
  const option = relObjectTypeOptions.find(item => item.value === type)
  return option ? option.label : type
}

/** 获取来源表标签 */
const getSourceTableLabel = (table?: string) => {
  if (!table) return '-'
  const option = relObjectSourceTableOptions.find(item => item.value === table)
  return option ? option.label : table
}

/** 获取是否必选的值（处理字符串和数字类型） */
const getIsRequiredValue = () => {
  if (detail.value?.isRequired === undefined || detail.value?.isRequired === null) {
    return undefined
  }
  // 如果是字符串，转换为数字
  if (typeof detail.value.isRequired === 'string') {
    return Number(detail.value.isRequired)
  }
  // 如果是数字，直接返回
  return detail.value.isRequired
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

const detail = ref<AssetRelRuleCfgVO | null>(props.assetRelRuleCfg ?? null)
const loading = ref(false)

/** 刷新详情数据 */
const handleRefresh = async () => {
  if (!props.id) return
  try {
    loading.value = true
    const res = await AssetRelRuleCfgApi.getAssetRelRuleCfg(props.id)
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
    if (val && props.id && !props.assetRelRuleCfg) {
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