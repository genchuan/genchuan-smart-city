<template>
  <ContentWrap>
    <!-- 搜索区域 -->
    <el-form
      :inline="true"
      :model="queryParams"
      ref="queryFormRef"
      label-width="100px"
      class="mb-3 flex-wrap"
    >
      <el-form-item label="网格员姓名">
        <el-input
          v-model="queryParams.gridUserName"
          placeholder="请输入网格员姓名"
          clearable
          class="!w-220px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="管理网格编码">
        <el-input
          v-model="queryParams.mgGridCode"
          placeholder="请输入网格编码"
          clearable
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="初始时间">
        <el-date-picker
          v-model="queryParams.initTime"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          value-format="YYYY-MM-DD"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="是否停用">
        <el-select v-model="queryParams.stopFlag" placeholder="全部" clearable class="!w-150px">
          <el-option label="启用" :value="false" />
          <el-option label="停用" :value="true" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleQuery">
          <Icon icon="ep:search" />
          搜索
        </el-button>
        <el-button @click="resetQuery">
          <Icon icon="ep:refresh" />
          重置
        </el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading">
          <Icon icon="ep:download" />
          导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <div class="mb-2 flex justify-between items-center">
      <el-button
        type="primary"
        plain
        :disabled="multipleSelection.length === 0"
        @click="openBatchAssignDialog"
      >
        <Icon icon="ep:user" />
        批量分配网格员
      </el-button>
    </div>

    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="50" />
      <el-table-column label="管理网格编码" prop="mgGridCode" />
      <el-table-column label="网格员" prop="gridUserName" />
      <el-table-column label="面积(m²)" prop="area" />
      <el-table-column label="初始时间" prop="initTime" :formatter="dateFormatter" width="160" />
      <el-table-column label="终止时间" prop="endTime" :formatter="dateFormatter" width="160" />
      <el-table-column label="是否停用" width="100">
        <template #default="{ row }">
          <el-tag :type="row.endTime ? 'danger' : 'success'">
            {{ row.endTime ? '停用' : '启用' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="备注" prop="remark" min-width="160" show-overflow-tooltip />
      <el-table-column label="操作" align="center" width="180">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)">详情</el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)">编辑</el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <Pagination
      :total="total"
      v-model:page="queryParams.pageNo"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />
  </ContentWrap>

  <!-- 弹窗与抽屉 -->
  <MngGridAttrForm ref="formRef" @success="getList" />
  <MngGridAttrDetailDrawer v-model="detailVisible" :record="currentRecord" />

  <!-- 批量分配网格员 -->
  <el-dialog v-model="batchAssignVisible" title="批量分配网格员" width="400px">
    <el-select v-model="selectedUser" placeholder="选择网格员" filterable clearable class="w-full">
      <el-option v-for="user in mockUsers" :key="user.id" :label="user.name" :value="user.id" />
    </el-select>
    <template #footer>
      <el-button @click="batchAssignVisible = false">取消</el-button>
      <el-button type="primary" @click="handleBatchAssign">确定</el-button>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import {
  MngGridAttrApi,
  MngGridAttrVO
} from '@/api/dataHub/gridManagement/gridDataManage/mnggridattr'
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import MngGridAttrForm from './MngGridAttrForm.vue'
import MngGridAttrDetailDrawer from './MngGridAttrDetailDrawer.vue'

defineOptions({ name: 'MngGridAttr' })

const message = useMessage()
const loading = ref(false)
const exportLoading = ref(false)
const list = ref<MngGridAttrVO[]>([])
const total = ref(0)
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  mgGridCode: '',
  gridUserName: '',
  stopFlag: '',
  initTime: []
})

const getList = async () => {
  loading.value = true
  try {
    const res = await MngGridAttrApi.getMngGridAttrPage(queryParams)
    list.value = res.list || []
    total.value = res.total || 0
  } finally {
    loading.value = false
  }
}

const handleQuery = () => {
  queryParams.pageNo = 1
  getList()
}

const resetQuery = () => {
  queryParams.gridUserName = ''
  queryParams.mgGridCode = ''
  queryParams.stopFlag = ''
  queryParams.initTime = []
  handleQuery()
}

/** 删除 */
const handleDelete = async (id: number) => {
  await message.delConfirm()
  await MngGridAttrApi.deleteMngGridAttr(id)
  message.success('删除成功')
  getList()
}

/** 导出 */
const handleExport = async () => {
  await message.exportConfirm()
  exportLoading.value = true
  const data = await MngGridAttrApi.exportMngGridAttr(queryParams)
  download.excel(data, '管理网格属性.xls')
  exportLoading.value = false
}

/** 表单操作 */
const formRef = ref()
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 详情抽屉 */
const detailVisible = ref(false)
const currentRecord = ref<MngGridAttrVO | null>(null)
const openDetail = (row: MngGridAttrVO) => {
  currentRecord.value = row
  detailVisible.value = true
}

/** 批量选择与分配 */
const multipleSelection = ref<MngGridAttrVO[]>([])
const handleSelectionChange = (rows: MngGridAttrVO[]) => {
  multipleSelection.value = rows
}

const batchAssignVisible = ref(false)
const selectedUser = ref('')
const mockUsers = [
  { id: 'U001', name: '张三' },
  { id: 'U002', name: '李四' },
  { id: 'U003', name: '王五' }
]
const openBatchAssignDialog = () => {
  selectedUser.value = ''
  batchAssignVisible.value = true
}
const handleBatchAssign = () => {
  message.success(`已将 ${selectedUser.value} 分配至 ${multipleSelection.value.length} 个网格`)
  batchAssignVisible.value = false
}

onMounted(getList)
</script>
