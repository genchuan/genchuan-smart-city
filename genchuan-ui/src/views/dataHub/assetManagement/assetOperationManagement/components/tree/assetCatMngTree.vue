<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="70px"
    >
      <el-form-item prop="assetCatName">
        <el-input
          v-model="queryParams.assetCatName"
          placeholder="请输入资产分类名称，按回车搜索"
          clearable
          @keyup.enter="handleQuery"
          @clear="resetQuery"
          class="!w-240px"
        />
      </el-form-item>
    </el-form>

    <!-- 树形展示 -->
    <el-tree
      :data="treeData"
      :props="treeProps"
      node-key="assetCatId"
      :default-expand-all="true"
      :filter-node-method="filterNode"
      empty-text="暂无数据"
    >
      <template #default="{ node, data }">
        <span>{{ node.label }}</span>
      </template>
    </el-tree>
  </ContentWrap>
</template>

<script setup lang="ts">
import { handleTree } from '@/utils/tree'
import { AssetCatMngApi } from '@/api/dataHub/assetManagement/assetOperationManagement/assetCatMng'

/** 分类 列表 */
defineOptions({ name: 'Category' })

const loading = ref(true)
const treeData = ref<any[]>([])
const treeRef = ref()

const queryParams = reactive({
  assetCatName: undefined,
})

const queryFormRef = ref()

// 树形配置
const treeProps = {
  label: 'assetCatName',
  children: 'children'
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await AssetCatMngApi.getAssetCatMngList()
    treeData.value = handleTree(data, 'assetCatId', 'parentCatId')
  } finally {
    loading.value = false
  }
}

/** 搜索按钮操作 */
const handleQuery = () => {
  if (treeRef.value) {
    treeRef.value.filter(queryParams.assetCatName)
  }
}

/** 重置按钮操作 */
const resetQuery = () => {
  queryParams.assetCatName = undefined
  if (treeRef.value) {
    treeRef.value.filter('')
  }
}

/** 树形节点过滤方法 */
const filterNode = (value: string, data: any) => {
  if (!value) return true
  return data.assetCatName?.includes(value)
}

/** 初始化 **/
onMounted(() => {
  getList()
})
</script>