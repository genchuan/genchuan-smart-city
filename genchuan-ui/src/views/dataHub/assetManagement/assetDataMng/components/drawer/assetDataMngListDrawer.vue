<!-- 资产数据管理详情抽屉 -->
<template>
  <el-drawer
    v-model="visible"
    size="75%"
    title="资产数据详情"
    :destroy-on-close="true"
  >
    <template #header>
      <div class="drawer-header">
        <span class="text-lg font-bold">资产数据详情</span>
        <el-button link type="primary" @click="handleRefresh">
          <Icon icon="ep:refresh" class="mr-5px" /> 刷新
        </el-button>
      </div>
    </template>

    <el-scrollbar height="calc(100vh - 150px)">
      <!-- 基本信息标签页 -->
      <el-tabs v-model="activeTab" type="border-card">
        <!-- 基本信息标签 -->
        <el-tab-pane label="基本信息" name="basic">
          <el-descriptions
            :column="2"
            border
            size="default"
            class="detail-desc"
          >
            <el-descriptions-item label="主键ID">
              {{ detail?.id ?? '-' }}
            </el-descriptions-item>

            <el-descriptions-item label="资产数据ID">
              {{ detail?.assetDataId ?? '-' }}
            </el-descriptions-item>

            <el-descriptions-item label="关联资产分类ID">
              {{ detail?.relAssetCatId ?? '-' }}
            </el-descriptions-item>

            <el-descriptions-item label="关联资产分类名称">
              {{ detail?.relAssetCatName ?? '-' }}
            </el-descriptions-item>

            <el-descriptions-item label="资产编码">
              {{ detail?.assetCode ?? '-' }}
            </el-descriptions-item>

            <el-descriptions-item label="资产名称">
              {{ detail?.assetName ?? '-' }}
            </el-descriptions-item>

            <el-descriptions-item label="资产状态">
              {{ renderAssetStatus(detail?.assetStatus) }}
            </el-descriptions-item>

            <el-descriptions-item label="安装时间">
              {{ formatDateDisplay(detail?.installTime) }}
            </el-descriptions-item>

            <el-descriptions-item label="使用年限">
              {{ detail?.serviceLife ? detail.serviceLife + '年' : '-' }}
            </el-descriptions-item>

            <el-descriptions-item label="资产负责人">
              {{ detail?.assetManager ?? '-' }}
            </el-descriptions-item>

            <el-descriptions-item label="资产负责人电话">
              {{ detail?.assetManagerTel ?? '-' }}
            </el-descriptions-item>

            <el-descriptions-item label="资产描述" :span="2">
              {{ detail?.assetDesc || '-' }}
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
              {{ detail?.extCat1 ?? '-' }}
            </el-descriptions-item>

            <el-descriptions-item label="分类扩展字段2">
              {{ detail?.extCat2 ?? '-' }}
            </el-descriptions-item>

            <el-descriptions-item label="通用扩展字段1">
              {{ detail?.extCommon1 ?? '-' }}
            </el-descriptions-item>

            <el-descriptions-item label="通用扩展字段2">
              {{ detail?.extCommon2 ?? '-' }}
            </el-descriptions-item>
          </el-descriptions>
        </el-tab-pane>

        <!-- 服务端属性标签 -->
        <el-tab-pane label="服务端属性" name="serverAttr">
          <div class="server-attr-container">
            <div class="table-header">
              <span class="text-md font-semibold">服务端属性列表</span>
              <div>
                <span class="text-sm text-gray-500 mr-3">
                  当前资产ID: {{ detail?.assetDataId || '-' }}
                </span>
                <el-button 
                  type="primary" 
                  size="small" 
                  @click="loadServerAttributes"
                  :loading="serverAttrLoading"
                >
                  <Icon icon="ep:refresh" class="mr-5px" /> 刷新属性
                </el-button>
              </div>
            </div>

            <el-table 
              :data="serverAttributes" 
              v-loading="serverAttrLoading"
              empty-text="暂无服务端属性数据"
              style="width: 100%; margin-top: 16px;"
            >
              <el-table-column prop="attrName" label="属性名称" min-width="120" />
              <el-table-column prop="attrCode" label="属性代码" min-width="120" />
              <el-table-column prop="attrDataType" label="数据类型" min-width="100">
                <template #default="{ row }">
                  {{ renderDataType(row.attrDataType) }}
                </template>
              </el-table-column>
              <el-table-column prop="attrValue" label="属性值" min-width="120" />
              <el-table-column prop="collectFreq" label="采集频率" min-width="100" />
              <el-table-column prop="lastCollectTime" label="最后采集时间" min-width="150">
                <template #default="{ row }">
                  {{ formatDateDisplay(row.lastCollectTime) }}
                </template>
              </el-table-column>
            </el-table>
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-scrollbar>

    <template #footer>
      <div class="flex justify-end">
        <el-button @click="visible = false">关闭</el-button>
      </div>
    </template>
  </el-drawer>
</template>

<script lang="ts" setup>
import { AssetDataMngApi, AssetDataMngVO } from '@/api/dataHub/assetManagement/assetDataMng/assetDataMngList'
import { AssetServerAttrCfgApi } from '@/api/dataHub/assetManagement/assetDataMng/assetServerAttrCfg'
import { formatDate } from '@/utils/formatTime'
import { ElMessage } from 'element-plus'

/** 组件参数与状态 */
const props = defineProps<{
  modelValue: boolean
  id?: number
  assetData?: AssetDataMngVO
}>()

/** 通用日期格式化显示 */
const formatDateDisplay = (val?: string | Date | number) => {
  if (!val) return '-'
  return formatDate(new Date(val), 'YYYY-MM-DD HH:mm:ss')
}

/** 渲染资产状态文本 */
const renderAssetStatus = (status?: string) => {
  const statusMap: Record<string, string> = {
    '1': '正常',
    '2': '故障', 
    '3': '待维护'
  }
  return status ? (statusMap[status] || status) : '-'
}

/** 渲染数据类型文本 */
const renderDataType = (dataType?: string) => {
  const dataTypeMap: Record<string, string> = {
    'string': '字符串',
    'decimal': '数值',
    'integer': '整数',
    'boolean': '布尔值',
    'date': '日期',
    'datetime': '日期时间'
  }
  return dataType ? (dataTypeMap[dataType] || dataType) : '-'
}

const emits = defineEmits(['update:modelValue'])

const visible = computed({
  get: () => props.modelValue,
  set: (val) => emits('update:modelValue', val)
})

// 标签页状态
const activeTab = ref('basic')

// 详情数据
const detail = ref<AssetDataMngVO | null>(props.assetData ?? null)
const loading = ref(false)

// 服务端属性数据
const serverAttributes = ref<any[]>([])
const serverAttrLoading = ref(false)

/** 加载服务端属性数据 */
const loadServerAttributes = async () => {
  if (!detail.value?.assetDataId) {
    ElMessage.warning('资产数据ID不存在，无法加载服务端属性')
    return
  }

  try {
    serverAttrLoading.value = true
    const params = {
      relAssetId: detail.value.assetDataId
    }
    
    console.log('请求服务端属性参数:', params)
    
    // 使用正确的API方法获取服务端属性列表
    const res = await AssetServerAttrCfgApi.getAssetClientAttrCfgList(params)
    
    console.log('服务端属性API响应:', res)
    
    // 处理响应数据
    if (res && res.data) {
      // 如果返回的是分页结构
      serverAttributes.value = Array.isArray(res.data) ? res.data : []
    } else if (Array.isArray(res)) {
      // 如果直接返回数组
      serverAttributes.value = res
    } else {
      serverAttributes.value = []
    }
    
    // 过滤数据，确保只显示当前资产的数据
    serverAttributes.value = serverAttributes.value.filter(item => 
      item.relAssetId === detail.value?.assetDataId
    )
    
    console.log('过滤后的服务端属性:', serverAttributes.value)
    
    if (serverAttributes.value.length === 0) {
      ElMessage.info(`资产 ${detail.value.assetDataId} 暂无服务端属性数据`)
    } else {
      ElMessage.success(`已加载 ${serverAttributes.value.length} 个服务端属性`)
    }
  } catch (e) {
    console.error('加载服务端属性失败:', e)
    ElMessage.error('加载服务端属性失败')
    serverAttributes.value = []
  } finally {
    serverAttrLoading.value = false
  }
}

/** 刷新详情数据 */
const handleRefresh = async () => {
  if (!props.id) return
  try {
    loading.value = true
    const res = await AssetDataMngApi.getAssetDataMng(props.id)
    detail.value = res
    
    // 如果当前在服务端属性标签页，则重新加载属性数据
    if (activeTab.value === 'serverAttr') {
      await loadServerAttributes()
    }
  } catch (e) {
    ElMessage.error('加载资产详情失败')
  } finally {
    loading.value = false
  }
}

/** 当抽屉打开时自动加载详情 */
watch(
  () => visible.value,
  (val) => {
    if (val && props.id && !props.assetData) {
      handleRefresh()
    } else if (val && props.assetData) {
      // 如果直接传递了assetData，直接使用
      detail.value = props.assetData
    }
  },
  { immediate: true }
)

/** 监听标签页切换，切换到服务端属性时加载数据 */
watch(
  () => activeTab.value,
  (val) => {
    if (val === 'serverAttr' && detail.value?.assetDataId) {
      // 每次切换到服务端属性标签页都重新加载数据
      loadServerAttributes()
    }
  }
)

/** 监听资产数据变化，清空服务端属性缓存 */
watch(
  () => detail.value?.assetDataId,
  (newAssetId, oldAssetId) => {
    if (newAssetId !== oldAssetId) {
      serverAttributes.value = []
    }
  }
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

.server-attr-container {
  padding: 16px;
}

.table-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
}
</style>