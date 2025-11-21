<template>
  <el-row :gutter="20">
    <!-- 左侧分类树 -->
    <el-col :span="5" :xs="24">
      <ContentWrap class="h-1/1">
        <ComponentCategoryTree @node-click="handleTreeNodeClick" />
      </ContentWrap>
    </el-col>

    <!-- 右侧表格区域 -->
    <el-col :span="19" :xs="24">
      <ContentWrap>
        <!-- 查询区域 -->
        <el-form :model="queryParams" ref="queryFormRef" :inline="true" label-width="90px">
          <el-form-item label="分类名称">
            <el-input
              v-model="queryParams.category_name"
              placeholder="请输入分类名称"
              clearable
              class="!w-240px"
              @keyup.enter="handleQuery"
            />
          </el-form-item>

          <el-form-item label="分类代码">
            <el-input
              v-model="queryParams.category_code"
              placeholder="请输入分类代码"
              clearable
              class="!w-240px"
              @keyup.enter="handleQuery"
            />
          </el-form-item>

          <el-form-item label="分类层级">
            <el-select v-model="queryParams.category_level" placeholder="请选择层级" clearable class="!w-160px">
              <el-option label="大类" :value="1" />
              <el-option label="中类" :value="2" />
              <el-option label="小类" :value="3" />
            </el-select>
          </el-form-item>

          <el-form-item label="启用状态">
            <el-select v-model="queryParams.enable_status" placeholder="请选择状态" clearable class="!w-160px">
              <el-option label="启用" :value="1" />
              <el-option label="禁用" :value="0" />
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button @click="handleQuery"><Icon icon="ep:search" /> 查询</el-button>
            <el-button @click="resetQuery"><Icon icon="ep:refresh" /> 重置</el-button>
            <el-button type="primary" plain @click="openForm('create')">
              <Icon icon="ep:plus" /> 新增
            </el-button>
            <el-button type="success" plain @click="handleExport">
              <Icon icon="ep:download" /> 导出
            </el-button>
          </el-form-item>
        </el-form>
      </ContentWrap>

      <!-- 表格展示 -->
      <ContentWrap>
        <el-table :data="list" v-loading="loading" border style="width: 100%">
          <el-table-column prop="category_id" label="分类ID" align="center" width="100" />
          <el-table-column prop="category_code" label="分类代码" align="center" width="100" />
          <el-table-column prop="category_name" label="分类名称" align="center" />
          <el-table-column prop="category_level" label="层级" align="center" width="80">
            <template #default="scope">
              <span v-if="scope.row.category_level === 1">大类</span>
              <span v-else-if="scope.row.category_level === 2">中类</span>
              <span v-else>小类</span>
            </template>
          </el-table-column>
          <el-table-column prop="parent_category_id" label="父类ID" align="center" width="100" />
          <el-table-column prop="category_desc" label="分类说明" align="center" />
          <el-table-column label="启用状态" align="center" width="100">
            <template #default="scope">
              <el-switch
                v-model="scope.row.enable_status"
                :active-value="1"
                :inactive-value="0"
                @change="handleStatusChange(scope.row)"
              />
            </template>
          </el-table-column>
          <el-table-column prop="create_time" label="创建时间" align="center" width="180" />
          <el-table-column label="操作" align="center" width="180">
            <template #default="scope">
              <el-button type="primary" link @click="openForm('update', scope.row)">修改</el-button>
              <el-button type="danger" link @click="handleDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <!-- 分页 -->
        <Pagination
          :total="total"
          v-model:page="queryParams.pageNo"
          v-model:limit="queryParams.pageSize"
          @pagination="getList"
        />
      </ContentWrap>
    </el-col>
  </el-row>

  <!-- 弹窗表单组件 -->
  <ComponentCategoryForm ref="formRef" @success="getList" />
</template>

<script lang="ts" setup>
import ComponentCategoryTree from './ComponentCategoryTree.vue'
import ComponentCategoryForm from './ComponentCategoryForm.vue'

const loading = ref(false)
const total = ref(0)
const list = ref<any[]>([])
const queryFormRef = ref()
const formRef = ref()

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  category_name: '',
  category_code: '',
  category_level: undefined,
  enable_status: undefined
})

/** 模拟静态数据（后续对接接口） */
const mockData = [
  {
    category_id: 1,
    parent_category_id: 0,
    category_level: 1,
    category_code: '01',
    category_name: '市政设施',
    category_desc: '城市公共基础设施',
    enable_status: 1,
    create_time: '2025-10-10 12:00:00'
  },
  {
    category_id: 2,
    parent_category_id: 1,
    category_level: 2,
    category_code: '01',
    category_name: '燃气',
    category_desc: '市政燃气系统',
    enable_status: 1,
    create_time: '2025-10-10 12:10:00'
  },
  {
    category_id: 3,
    parent_category_id: 2,
    category_level: 3,
    category_code: '001',
    category_name: '燃气管道',
    category_desc: '输送燃气的管道',
    enable_status: 1,
    create_time: '2025-10-10 12:20:00'
  }
]

/** 获取列表（模拟异步） */
const getList = async () => {
  loading.value = true
  setTimeout(() => {
    list.value = mockData
    total.value = mockData.length
    loading.value = false
  }, 500)
}

/** 查询 */
const handleQuery = () => {
  queryParams.pageNo = 1
  getList()
}

/** 重置 */
const resetQuery = () => {
  Object.assign(queryParams, {
    category_name: '',
    category_code: '',
    category_level: undefined,
    enable_status: undefined
  })
  handleQuery()
}

/** 左侧树点击事件 */
const handleTreeNodeClick = (node: any) => {
  queryParams.category_name = node.category_name
  getList()
}

/** 启用状态变更 */
const handleStatusChange = (row: any) => {
  if (row.enable_status === 0) {
    ElMessage.success(`已禁用分类 ${row.category_name}`)
  } else {
    ElMessage.success(`已启用分类 ${row.category_name}`)
  }
  // TODO: 后续同步子级状态 + 接口更新
}

/** 新增 / 修改表单 */
const openForm = (type: string, row?: any) => {
  formRef.value.open(type, row)
}

/** 删除分类 */
const handleDelete = (row: any) => {
  ElMessageBox.confirm(`确定删除分类 [${row.category_name}] 吗？删除后不可恢复。`, '警告', {
    type: 'warning'
  }).then(() => {
    // TODO: 检查关联关系后再删除
    ElMessage.success('删除成功')
    getList()
  })
}

/** 导出操作（模拟） */
const handleExport = () => {
  ElMessage.info('导出功能暂未对接接口')
}

onMounted(() => {
  getList()
})
</script>
