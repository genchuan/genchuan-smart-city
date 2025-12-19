<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="130px"
    >
      <el-form-item label="应用场景分类代码" prop="sceneCatCode">
        <el-input
          v-model="queryParams.sceneCatCode"
          placeholder="请输入应用场景分类代码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="应用场景分类名称" prop="sceneCatName">
        <el-input
          v-model="queryParams.sceneCatName"
          placeholder="请输入应用场景分类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="分类层级" prop="catLevel">
        <el-input
          v-model="queryParams.catLevel"
          placeholder="1-大类/2-中类/3-小类"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="启用状态" prop="enableStatus">
        <el-select
          v-model="queryParams.enableStatus"
          placeholder="请选择启用状态"
          clearable
          class="!w-240px"
        >
          <el-option label="启用" value="1" />
          <el-option label="禁用" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery">
          <Icon icon="ep:search" class="mr-5px" />
          搜索
        </el-button>
        <el-button @click="resetQuery">
          <Icon icon="ep:refresh" class="mr-5px" />
          重置
        </el-button>
        <el-button type="primary" plain @click="openForm('create')">
          <Icon icon="ep:plus" class="mr-5px" />
          新增
        </el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading">
          <Icon icon="ep:download" class="mr-5px" />
          导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 主体内容区：左侧树形 + 右侧表格 -->
  <ContentWrap class="mt-4">
    <el-row :gutter="20">
      <!-- 左侧树形结构 -->
      <el-col :span="5" :xs="24">
        <el-card shadow="hover" class="rounded-lg h-full">
          <div class="mb-3">
            <el-input
              v-model="treeSearchKey"
              placeholder="请输入应用场景名称搜索"
              clearable
              @input="handleTreeSearch"
              class="w-full"
            >
              <template #prefix>
                <Icon icon="ep:search" class="text-gray-400" />
              </template>
            </el-input>
          </div>
          <el-tree
            v-loading="treeLoading"
            ref="treeRef"
            :data="treeData"
            :props="treeProps"
            :filter-node-method="filterTreeNode"
            node-key="id"
            default-expand-level="1"
            @node-click="handleTreeNodeClick"
            class="tree-height"
          >
            <template #default="{ node, data }">
              <span>
                {{ data.sceneCatName }}
                <small class="ml-2 text-gray-400">({{ data.sceneCatCode }})</small>
              </span>
            </template>
          </el-tree>
        </el-card>
      </el-col>

      <!-- 右侧表格 -->
      <el-col :span="18" :xs="24">
        <el-table
          v-loading="loading"
          :data="list"
          :stripe="true"
          :show-overflow-tooltip="true"
          class="custom-table"
        >
<!--          <el-table-column label="主键ID" align="center" prop="id" width="80" />-->
          <el-table-column label="应用场景分类代码" align="center" prop="sceneCatCode" />
          <el-table-column label="应用场景分类名称" align="center" prop="sceneCatName" />
          <el-table-column label="分类层级" align="center" prop="catLevel">
            <template #default="scope">
              <span :class="levelClass(scope.row.catLevel)">{{
                scope.row.catLevel === '1'
                  ? '大类'
                  : scope.row.catLevel === '2'
                    ? '中类'
                    : scope.row.catLevel === '3'
                      ? '小类'
                      : '-'
              }}</span>
            </template>
          </el-table-column>
          <el-table-column label="父级分类名称" align="center" prop="parentCatName" />
<!--          <el-table-column label="是否扩展类" align="center" prop="isExt">-->
<!--            <template #default="scope">-->
<!--              <span :class="scope.row.isExt === '1' ? 'ext-tag' : 'standard-tag'">-->
<!--                {{ scope.row.isExt === '1' ? '扩展类' : '标准类' }}-->
<!--              </span>-->
<!--            </template>-->
<!--          </el-table-column>-->
          <el-table-column label="启用状态" align="center" prop="enableStatus">
            <template #default="scope">
              <el-tag :type="scope.row.enableStatus === '1' ? 'success' : 'danger'">
                {{ scope.row.enableStatus === '1' ? '启用' : '禁用' }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column
            label="创建时间"
            align="center"
            prop="createTime"
            :formatter="dateFormatter"
            width="180px"
          />
          <el-table-column label="操作" align="center" min-width="180px">
            <template #default="scope">
              <el-button link type="primary" @click="openDetail(scope.row)"> 查看</el-button>
              <el-button link type="primary" @click="openForm('update', scope.row.id)">
                编辑
              </el-button>
              <el-button link type="danger" @click="handleDelete(scope.row.id)"> 删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页 -->
        <Pagination
          :total="total"
          v-model:page="queryParams.pageNo"
          v-model:limit="queryParams.pageSize"
          @pagination="getList"
          class="mt-4"
        />
      </el-col>
    </el-row>
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <AppSceneCategoryForm ref="formRef" @success="getList" @refresh-tree="loadTreeData"/>
  <!-- 详情抽屉 -->
  <AppSceneCategoryDetail
    ref="detailRef"
    :visible="detailVisible"
    :data="detailData"
    @close="detailVisible = false"
  />
</template>

<script setup lang="ts">
import { ref, reactive, watch, onMounted } from 'vue'
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { AppSceneCategoryApi, AppSceneCategoryVO } from '@/api/dataHub/sceneCategory/appCateInfo'
import AppSceneCategoryForm from './AppSceneCategoryForm.vue'
import AppSceneCategoryDetail from './AppSceneCategoryDetail.vue'

/** 应用场景分类配置 列表 */
defineOptions({ name: 'AppSceneCategory' })
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

// 列表相关
const loading = ref(true)
const list = ref<AppSceneCategoryVO[]>([])
const total = ref(0)
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,

  sceneCatCode: undefined,
  sceneCatName: undefined,
  catLevel: undefined,
  parentCatId: undefined,
  parentCatName: undefined,
  sceneCatDesc: undefined,
  isExt: undefined,
  enableStatus: undefined,
  relCompCatId: undefined,
  relCompCatName: undefined,
  relEvtCatId: undefined,
  relEvtCatName: undefined,
  createUserBiz: undefined,
  createTimeBiz: undefined,
  updateUserBiz: undefined,
  updateTimeBiz: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: [],
  ancestorId: undefined // 新增：用于树形节点筛选（选中节点ID）
})
const queryFormRef = ref()
const exportLoading = ref(false)

// 详情抽屉相关
const detailRef = ref()
const detailVisible = ref(false)
const detailData = ref<AppSceneCategoryVO | null>(null)
const openDetail = (row: AppSceneCategoryVO) => {
  detailData.value = row
  detailVisible.value = true
}

// 树形组件相关
const treeRef = ref()
const treeData = ref<any[]>([])
const treeLoading = ref(false)
const treeSearchKey = ref('')
const treeProps = reactive({
  label: 'sceneCatName',
  children: 'children',
  value: 'id'
})

/** 加载树形数据 */
const loadTreeData = async () => {
  treeLoading.value = true
  try {
    const res = await AppSceneCategoryApi.getAppSceneCategoryTree()
    treeData.value = res || []
  } catch (error) {
    console.error('加载应用场景树形数据失败', error)
    message.error('加载树形数据失败，请刷新重试')
    treeData.value = []
  } finally {
    treeLoading.value = false
  }
}

/** 树形节点过滤（按sceneCatName模糊查询） */
const filterTreeNode = (value: string, data: any) => {
  if (!value) return true
  return data.sceneCatName?.toLowerCase().includes(value.toLowerCase())
}

/** 树形搜索输入事件 */
const handleTreeSearch = (value: string) => {
  treeRef.value?.filter(value)
}

/** 树形节点点击事件 */
const handleTreeNodeClick = (data: any) => {
  // 选中节点后，设置ancestorId为选中节点ID，刷新表格 无祖先id只能使用parentCatId
  queryParams.parentCatId = data.id
  queryParams.pageNo = 1 // 重置页码
  getList()
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await AppSceneCategoryApi.getAppSceneCategoryPage(queryParams)
    list.value = data.list
    total.value = data.total
  } finally {
    loading.value = false
  }
}

/** 搜索按钮操作 */
const handleQuery = () => {
  queryParams.pageNo = 1
  getList()
}

/** 重置按钮操作 */
const resetQuery = () => {
  queryFormRef.value?.resetFields()
  // 重置树形相关状态
  treeSearchKey.value = ''
  treeRef.value?.filter('') // 清空过滤
  treeRef.value?.setCurrentKey(null) // 清空选中状态
  // 重置查询参数
  Object.assign(queryParams, {
    pageNo: 1,
    parentCatId: undefined,
    sceneCatCode: undefined,
    sceneCatName: undefined,
    catLevel: undefined,
    enableStatus: undefined,
    createTime: []
  })
  handleQuery()
}

/** 添加/修改操作 */
const formRef = ref()
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    await message.delConfirm()
    await AppSceneCategoryApi.deleteAppSceneCategory(id)
    message.success(t('common.delSuccess'))
    await getList()
    await loadTreeData() // 删除后刷新树形数据
  } catch {}
}

/** 导出按钮操作 */
const handleExport = async () => {
  try {
    await message.exportConfirm()
    exportLoading.value = true
    const data = await AppSceneCategoryApi.exportAppSceneCategory(queryParams)
    download.excel(data, '应用场景分类配置.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 分类层级样式 */
const levelClass = (level: string) => {
  switch (level) {
    case '1':
      return 'level-1'
    case '2':
      return 'level-2'
    case '3':
      return 'level-3'
    default:
      return ''
  }
}

/** 初始化 */
onMounted(() => {
  loadTreeData()
  getList()
})

// 监听树形搜索关键词变化，实时过滤
watch(treeSearchKey, (newVal) => {
  handleTreeSearch(newVal)
})
</script>

<style scoped>
.custom-table {
  border-radius: 8px;
  overflow: hidden;
}

.level-1 {
  color: #4096ff;
  font-weight: 500;
}

.level-2 {
  color: #67c23a;
  font-weight: 500;
}

.level-3 {
  color: #faad14;
  font-weight: 500;
}

.ext-tag {
  color: #722ed1;
  background-color: #f9f0ff;
  padding: 2px 6px;
  border-radius: 4px;
  font-size: 12px;
}

.standard-tag {
  color: #1890ff;
  background-color: #e6f7ff;
  padding: 2px 6px;
  border-radius: 4px;
  font-size: 12px;
}

/* 树形组件样式 */
.tree-height {
  height: calc(100vh - 380px);
  overflow-y: auto;
}

.el-card {
  border: none;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
}

/* 树形节点hover样式优化 */
:deep(.el-tree-node__content:hover) {
  background-color: #f0f9ff;
}

/* 树形节点选中样式 */
:deep(.el-tree--highlight-current .el-tree-node.is-current > .el-tree-node__content) {
  background-color: #e6f7ff;
  color: #1890ff;
  font-weight: 500;
}
</style>
