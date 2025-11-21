<template>
  <div class="app-container">
    <!-- 🔍 查询条件 -->
    <el-form :inline="true" :model="queryParams" class="query-form">
      <el-form-item label="规则名称">
        <el-input
          v-model="queryParams.ruleName"
          placeholder="请输入规则名称"
          clearable
          style="width: 200px"
        />
      </el-form-item>
      <el-form-item label="启用状态">
        <el-select
          v-model="queryParams.enableStatus"
          placeholder="请选择状态"
          clearable
          style="width: 160px"
        >
          <el-option label="启用" value="1" />
          <el-option label="禁用" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary"  @click="handleQuery" class="mr-5px"><Icon icon="ep:search" />搜索</el-button>
        <el-button  @click="resetQuery" class="mr-5px"><Icon icon="ep:refresh" />重置</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary"  plain  @click="openAdd" >
          <Icon icon="ep:plus" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading">
          <Icon icon="ep:download" />导出
        </el-button>
      </el-form-item>
    </el-form>

    <!-- 📋 数据表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      border
      highlight-current-row
      @sort-change="handleSort"
    >
      <el-table-column label="规则ID" prop="idRuleId" width="180" align="center" />
      <el-table-column label="规则名称" prop="ruleName" min-width="180" align="center" />
      <el-table-column label="各段位数" min-width="240" align="center">
        <template #default="{ row }">
          行政区划:{{ row.adminCodeLength }} /
          大类:{{ row.majorCodeLength }} /
          中类:{{ row.middleCodeLength }} /
          小类:{{ row.minorCodeLength }} /
          顺序码:{{ row.seqCodeLength }}
        </template>
      </el-table-column>
      <el-table-column
        label="顺序码生成规则"
        prop="seqGenRule"
        min-width="220"
        align="center"
        show-overflow-tooltip
      />
      <el-table-column label="启用状态" prop="enableStatus" width="120" align="center">
        <template #default="{ row }">
          <el-switch
            v-model="row.enableStatus"
            active-value="1"
            inactive-value="0"
            @change="toggleEnable(row)"
          />
        </template>
      </el-table-column>
      <el-table-column label="创建人" prop="createUser" width="100" align="center" />
      <el-table-column
        label="创建时间"
        prop="createTime"
        sortable="custom"
        width="180"
        align="center"
      />
      <el-table-column label="操作" fixed="right" width="140" align="center">
        <template #default="{ row }">
          <el-button type="primary" link @click="openEdit(row)">修改</el-button>
          <el-button type="danger" link @click="handleDelete(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 📄 分页 -->
    <el-pagination
      background
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      v-model:page-size="pageSize"
      v-model:current-page="currentPage"
      :page-sizes="[5, 10, 20]"
      @current-change="loadData"
      @size-change="loadData"
      class="pagination"
    />

    <!-- ✨ 弹窗表单 -->
    <CodeRuleForm
      v-if="dialogVisible"
      :visible="dialogVisible"
      :is-edit="isEdit"
      :form-data="formData"
      @close="dialogVisible = false"
      @success="onFormSuccess"
    />
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
// import * as XLSX from 'xlsx'
import CodeRuleForm from './CodeRuleForm.vue'
import download from '@/utils/download'
const message = useMessage() // 消息弹窗
// mock 数据
const mockData = [
  {
    idRuleId: 'UUID-001',
    ruleName: '监测部件18位标识码规则-默认',
    adminCodeLength: 6,
    majorCodeLength: 2,
    middleCodeLength: 2,
    minorCodeLength: 2,
    seqCodeLength: 6,
    seqGenRule: '同一行政区划+小类下按录入顺序递增',
    enableStatus: '1',
    createUser: 'admin',
    createTime: '2025-10-10 12:00:00',
    linkedInstances: false, // 是否有关联部件实例
  },
  {
    idRuleId: 'UUID-002',
    ruleName: '备用规则',
    adminCodeLength: 6,
    majorCodeLength: 2,
    middleCodeLength: 2,
    minorCodeLength: 2,
    seqCodeLength: 6,
    seqGenRule: '同一行政区划+小类下按录入顺序递增',
    enableStatus: '0',
    createUser: 'user01',
    createTime: '2025-10-09 09:30:00',
    linkedInstances: false,
  },
]

const loading = ref(false)
const tableData = ref([])
const total = ref(0)
const currentPage = ref(1)
const pageSize = ref(10)
const sortOrder = ref('desc') // 默认降序

const queryParams = reactive({
  ruleName: '',
  enableStatus: '',
})

const dialogVisible = ref(false)
const isEdit = ref(false)
const formData = ref({})

// =============== 数据加载 ===============
const loadData = () => {
  loading.value = true
  setTimeout(() => {
    let list = [...mockData]
    // 过滤
    if (queryParams.ruleName)
      list = list.filter((r) => r.ruleName.includes(queryParams.ruleName))
    if (queryParams.enableStatus)
      list = list.filter((r) => r.enableStatus === queryParams.enableStatus)
    // 排序（按创建时间降序）
    list.sort(
      (a, b) =>
        (sortOrder.value === 'desc' ? -1 : 1) *
        (new Date(a.createTime) - new Date(b.createTime))
    )
    total.value = list.length
    const start = (currentPage.value - 1) * pageSize.value
    tableData.value = list.slice(start, start + pageSize.value)
    loading.value = false
  }, 200)
}
onMounted(loadData)

// =============== 查询、重置 ===============
const handleQuery = () => {
  currentPage.value = 1
  loadData()
}
const resetQuery = () => {
  queryParams.ruleName = ''
  queryParams.enableStatus = ''
  handleQuery()
}

// =============== 排序 ===============
const handleSort = ({ prop, order }) => {
  if (prop === 'createTime') {
    sortOrder.value = order === 'ascending' ? 'asc' : 'desc'
    loadData()
  }
}

// =============== 新增、修改 ===============
const openAdd = () => {
  isEdit.value = false
  formData.value = {}
  dialogVisible.value = true
}
const openEdit = (row) => {
  isEdit.value = true
  formData.value = { ...row }
  dialogVisible.value = true
}

// =============== 删除 ===============
const handleDelete = (row) => {
  if (row.enableStatus === '1') {
    ElMessage.warning('启用状态下的规则不能删除！')
    return
  }
  if (row.linkedInstances) {
    ElMessage.warning('该规则有关联部件实例，无法删除！')
    return
  }
  ElMessageBox.confirm('确定要删除该规则吗？', '提示', { type: 'warning' })
    .then(() => {
      const idx = mockData.findIndex((r) => r.idRuleId === row.idRuleId)
      if (idx > -1) mockData.splice(idx, 1)
      loadData()
      ElMessage.success('删除成功')
    })
    .catch(() => {})
}

// =============== 启用切换 ===============
const toggleEnable = (row) => {
  if (row.enableStatus === '1') {
    ElMessageBox.confirm('启用当前规则将禁用其他规则，是否继续？', '提示', {
      type: 'warning',
    }).then(() => {
      mockData.forEach((r) => (r.enableStatus = r.idRuleId === row.idRuleId ? '1' : '0'))
      ElMessage.success('启用成功')
      loadData()
    })
  } else {
    ElMessage.success('已禁用')
  }
}

// =============== 导出 Excel ===============
// const handleExport = () => {
// const exportData = mockData.map((r) => ({
//   规则ID: r.idRuleId,
//   规则名称: r.ruleName,
//   各段位数: `6+2+2+2+6`,
//   顺序码生成规则: r.seqGenRule,
//   启用状态: r.enableStatus === '1' ? '启用' : '禁用',
//   创建人: r.createUser,
//   创建时间: r.createTime,
// }))
// const ws = XLSX.utils.json_to_sheet(exportData)
// const wb = XLSX.utils.book_new()
// XLSX.utils.book_append_sheet(wb, ws, '标识码规则')
// XLSX.writeFile(wb, '监测部件标识码规则.xlsx')
// ElMessage.success('导出成功')
// }
/** 导出按钮操作 */
const exportLoading = ref(false)
const handleExport = async () => {
  try {
    // 导出的二次确认
    await message.exportConfirm()
    // 发起导出
    exportLoading.value = true
    const data = await UserApi.exportUser(queryParams)
    download.excel(data, '监测部件标识码规则.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

const onFormSuccess = () => {
  dialogVisible.value = false
  loadData()
}
</script>

<style scoped>
.app-container {
  padding: 16px;
}
.query-form {
  margin-bottom: 10px;
}
.pagination {
  margin-top: 10px;
  text-align: right;
}
</style>
