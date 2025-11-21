<!-- 资产分类规则配置抽屉 -->
 <template>
  <el-drawer
    v-model="visible"
    size="75%"
    title="资产分类规则配置详情"
    :destroy-on-close="true"
  >
    <template #header>
      <div class="drawer-header">
        <span class="text-lg font-bold">资产分类规则配置详情</span>
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

        <el-descriptions-item label="分类规则ID">
          {{ detail?.assetCatRuleId ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="规则名称">
          {{ detail?.ruleName ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="大类编码位数">
          {{ detail?.majorCodeLength ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="中类编码位数">
          {{ detail?.midCodeLength ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="小类编码位数">
          {{ detail?.minorCodeLength ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="编码生成逻辑">
          {{ detail?.codeGenLogic ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="适用资产领域">
          {{ detail?.assetDom ?? '-' }}
        </el-descriptions-item>

        <el-descriptions-item label="启用状态">
          <el-tag v-if="detail?.enableStatus" type="success">启用</el-tag>
          <el-tag v-else type="info">禁用</el-tag>
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

      <!-- 预留：规则配置详情、关联分类等扩展区 -->
      <!-- <el-divider content-position="left">规则配置详情</el-divider> -->
    </el-scrollbar>

    <template #footer>
      <div class="flex justify-end">
        <el-button @click="visible = false">关闭</el-button>
      </div>
    </template>
  </el-drawer>
</template>

<script lang="ts" setup>
import { AssetCatRuleCfgApi, AssetCatRuleCfgVO } from '@/api/dataHub/assetManagement/assetRuleAllocation/assetCatRuleCfg'
import { formatDate } from '@/utils/formatTime'
import { ElMessage } from 'element-plus'

/** 组件参数与状态 */
const props = defineProps<{
  modelValue: boolean
  id?: number
  assetCatRuleCfg?: AssetCatRuleCfgVO
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

const detail = ref<AssetCatRuleCfgVO | null>(props.assetCatRuleCfg ?? null)
const loading = ref(false)

/** 刷新详情数据 */
const handleRefresh = async () => {
  if (!props.id) return
  try {
    loading.value = true
    const res = await AssetCatRuleCfgApi.getAssetCatRuleCfg(props.id)
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
    if (val && props.id && !props.assetCatRuleCfg) {
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