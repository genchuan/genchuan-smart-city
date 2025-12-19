<template>
  <div class="h-full flex flex-col">
    <!-- 搜索工作栏 - 保持原有样式和交互 -->
    <el-form
      class="-mb-15px mb-4 p-3 bg-white rounded-lg shadow-sm"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="80px"
    >
      <el-form-item label="分类名称" prop="evtCatName">
        <el-input
          v-model="queryParams.evtCatName"
          placeholder="请输入分类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px rounded-md"
        />
      </el-form-item>
      <el-form-item label="分类代码" prop="evtCatCode">
        <el-input
          v-model="queryParams.evtCatCode"
          placeholder="请输入分类代码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px rounded-md"
        />
      </el-form-item>
      <el-form-item label="启用状态" prop="enableStatus">
        <el-select
          v-model="queryParams.enableStatus"
          placeholder="请选择启用状态"
          clearable
          class="!w-200px rounded-md"
        >
          <el-option label="启用" value="1" />
          <el-option label="禁用" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker
          v-model="queryParams.createTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-300px rounded-md"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleQuery" class="mr-2">
          <Icon icon="ep:search" class="mr-5px" />
          搜索
        </el-button>
        <el-button @click="resetQuery" class="mr-2">
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

    <!-- 主体内容区：树形结构居左，表格居右 -->
    <div class="flex-1 flex gap-4 min-w-0 overflow-hidden">
      <!-- 左侧树形结构 -->
      <el-card shadow="hover" class="rounded-lg w-64 flex-shrink-0 h-full flex flex-col">
        <div class="mb-3">
          <el-input
            v-model="treeSearchKey"
            placeholder="请输入分类名称搜索"
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
          :data="treeData"
          :props="treeProps"
          :filter-node-method="filterNode"
          ref="treeRef"
          node-key="monEvtCatId"
          default-expand-all
          @node-click="handleTreeNodeClick"
          class="flex-1 overflow-y-auto"
        />
      </el-card>

      <!-- 右侧列表 -->
      <el-card shadow="hover" class="rounded-lg flex-1 min-w-0 h-full flex flex-col">
        <el-table
          v-loading="loading"
          :data="list"
          :stripe="true"
          :show-overflow-tooltip="true"
          class="table-beautify flex-1"
          border
        >
          <el-table-column label="主键ID" align="center" prop="id" width="80" />
          <el-table-column label="分类名称" align="center" prop="evtCatName" width="200" />
          <el-table-column label="分类代码" align="center" prop="evtCatCode" width="150" />
          <el-table-column label="分类层级" align="center" prop="evtCatLevel" width="120" />
          <el-table-column label="启用状态" align="center" prop="enableStatus" width="120">
            <template #default="scope">
              <el-tag :type="['1'].includes(scope.row.enableStatus) ? 'success' : 'danger'">
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
          <el-table-column label="创建人" align="center" prop="createUser" width="120" />
          <el-table-column label="操作" align="center" min-width="180px">
            <template #default="scope">
              <el-button link type="primary" @click="openDetail(scope.row)">
                <Icon icon="ep:detail" class="mr-2" />
                详情
              </el-button>
              <el-button link type="primary" @click="openForm('update', scope.row.id)">
                <Icon icon="ep:edit" class="mr-2" />
                编辑
              </el-button>
              <el-button link type="danger" @click="handleDelete(scope.row.id)">
                <Icon icon="ep:delete" class="mr-2" />
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>

        <!-- 分页 -->
        <div class="mt-4">
          <Pagination
            :total="total"
            v-model:page="queryParams.pageNo"
            v-model:limit="queryParams.pageSize"
            @pagination="getList"
          />
        </div>
      </el-card>
    </div>

    <!-- 表单弹窗：添加/修改 -->
    <MonEvtCatForm ref="formRef" @success="handleFormSuccess" />
    <!-- 详情抽屉 -->
    <MonEvtCatDetailDrawer ref="detailRef" />
  </div>
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  MonEvtCatApi,
  MonEvtCatVO
} from '@/api/dataHub/monitorCompEventMgr/monitorEvtConfigMgr/monevtcat'
import MonEvtCatForm from './MonEvtCatForm.vue'
import MonEvtCatDetailDrawer from './MonEvtCatDetailDrawer.vue'

/** 监测事件分类配置 列表 */
defineOptions({ name: 'MonEvtCat' })
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化
const loading = ref(true) // 列表的加载中
const list = ref<MonEvtCatVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  evtCatName: undefined,
  evtCatCode: undefined,
  enableStatus: undefined,
  createTime: [],
  monEvtCatId: undefined // 新增：用于树形节点筛选
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 树形结构相关 */
const treeRef = ref()
const treeData = ref<any[]>([])
const treeLoading = ref(false)
const treeSearchKey = ref('')
const treeProps = {
  children: 'children',
  label: 'evtCatName'
}
const allTreeData = ref<MonEvtCatVO[]>([]) // 保存所有树形数据用于搜索过滤

/** 组件Ref */
const formRef = ref()
const detailRef = ref()

/** 获取树形结构数据 */
const getTreeData = async () => {
  treeLoading.value = true
  try {
    // 获取所有分类数据用于构建树形结构
    const params = {
      pageNo: 1,
      pageSize: 100 // 假设1000条足够，实际可根据需求调整
    }
    const data = await MonEvtCatApi.getMonEvtCatPage(params)
    allTreeData.value = data.list
    treeData.value = buildTree(data.list)
  } catch (error) {
    console.error('获取树形数据失败', error)
  } finally {
    treeLoading.value = false
  }
}

/** 构建树形结构 */
const buildTree = (list: MonEvtCatVO[]): any[] => {
  const map = new Map()
  const tree: any[] = []

  // 先将所有节点存入map
  list.forEach((item) => {
    map.set(item.monEvtCatId, { ...item, children: [] })
  })

  // 构建树形结构
  list.forEach((item) => {
    const node = map.get(item.monEvtCatId)
    if (item.parentEvtId && map.has(item.parentEvtId)) {
      // 有父节点，添加到父节点的children中
      map.get(item.parentEvtId).children.push(node)
    } else {
      // 没有父节点，作为根节点
      tree.push(node)
    }
  })

  return tree
}

/** 树形节点搜索过滤 */
const filterNode = (value: string, data: any) => {
  if (!value) return true
  return data.evtCatName?.includes(value)
}

/** 处理树形搜索 */
const handleTreeSearch = (value: string) => {
  treeRef.value.filter(value)
}

/** 处理树形节点点击 */
const handleTreeNodeClick = (data: any) => {
  // 选中节点后，设置查询参数并刷新表格
  queryParams.monEvtCatId = data.monEvtCatId
  queryParams.pageNo = 1
  getList()
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await MonEvtCatApi.getMonEvtCatPage(queryParams)
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
  queryFormRef.value.resetFields()
  queryParams.monEvtCatId = undefined // 重置树形选择
  treeRef.value?.setCurrentKey(null) // 清除树形选中状态
  handleQuery()
}

/** 表单提交成功后处理 */
const handleFormSuccess = () => {
  // 重新获取树形数据和列表数据
  getTreeData()
  getList()
}

/** 添加/修改操作 */
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 详情操作 */
const openDetail = (row: MonEvtCatVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await MonEvtCatApi.deleteMonEvtCat(id)
    message.success(t('common.delSuccess'))
    // 刷新列表和树形
    getList()
    getTreeData()
  } catch {}
}

/** 导出按钮操作 */
const handleExport = async () => {
  try {
    // 导出的二次确认
    await message.exportConfirm()
    // 发起导出
    exportLoading.value = true
    const data = await MonEvtCatApi.exportMonEvtCat(queryParams)
    download.excel(data, '监测事件分类配置.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 初始化 **/
onMounted(() => {
  getTreeData()
  getList()
})
</script>

<style scoped>
.table-beautify {
  --el-table-row-hover-bg-color: #f0f9ff;
  --el-table-header-text-color: #333;
  --el-table-header-bg-color: #f8f9fa;
  --el-table-border-color: #e4e7ed;
}

.el-card {
  border: none;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
}

.el-form-item {
  margin-bottom: 15px;
}

/* 移除原有的tree-height样式，改用flex布局实现高度自适应 */
:deep(.el-tree) {
  height: 100%;
}

/* 确保整体布局占满高度 */
div[class='h-full flex flex-col'] {
  height: 100vh;
  box-sizing: border-box;
  padding: 16px;
}

/* 主体内容区占满剩余高度 */
div[class='flex-1 flex gap-4 min-w-0 overflow-hidden'] {
  height: calc(100% - 100px); /* 减去搜索栏高度 */
}
</style>
