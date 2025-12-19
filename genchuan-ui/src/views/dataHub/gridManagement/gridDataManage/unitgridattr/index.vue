<!-- index.vue -->
<template>
  <ContentWrap>
    <!-- 搜索条件区 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="100px"
    >
      <el-form-item label="单元网格编码" prop="unitGridCode">
        <el-input
          v-model="queryParams.unitGridCode"
          placeholder="请输入单元网格编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-220px"
        />
      </el-form-item>

      <el-form-item label="初始时间" prop="initTime">
        <el-date-picker
          v-model="queryParams.initTime"
          type="daterange"
          value-format="YYYY-MM-DD"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item label="终止时间" prop="endTime">
        <el-date-picker
          v-model="queryParams.endTime"
          type="daterange"
          value-format="YYYY-MM-DD"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item label="是否停用" prop="stopFlag">
        <el-select v-model="queryParams.stopFlag" placeholder="全部" clearable class="!w-180px">
          <el-option label="在用" value="0" />
          <el-option label="停用" value="1" />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="handleQuery">
          <Icon icon="ep:search" class="mr-5px" />
          查询
        </el-button>
        <el-button @click="resetQuery">
          <Icon icon="ep:refresh" class="mr-5px" />
          重置
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['datacenter:unit-grid-attr:export']"
        >
          <Icon icon="ep:download" class="mr-5px" />
          导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表区 -->
  <ContentWrap>
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      @row-dblclick="openDetail"
      highlight-current-row
    >
      <el-table-column label="单元网格编码" align="center" prop="unitGridCode" width="160" />
      <el-table-column label="面积(m²)" align="center" prop="area" width="120" />
      <el-table-column
        label="初始时间"
        align="center"
        prop="initTime"
        :formatter="dateFormatter"
        width="150"
      />
      <el-table-column
        label="终止时间"
        align="center"
        prop="endTime"
        :formatter="dateFormatter"
        width="150"
      />
      <el-table-column label="状态" align="center" width="100">
        <template #default="{ row }">
          <el-tag :type="row.endTime ? 'danger' : 'success'">
            {{ row.endTime ? '停用' : '在用' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" min-width="160" />
      <el-table-column label="操作" align="center" min-width="120">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)">详情</el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)">编辑</el-button>
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

  <!-- 表单弹窗 -->
  <UnitGridAttrForm ref="formRef" @success="getList" />

  <!-- ✅ 详情抽屉：关闭后自动刷新列表 -->
  <UnitGridAttrDetailDrawer ref="detailRef" @closed="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  UnitGridAttrApi,
  UnitGridAttrVO
} from '@/api/dataHub/gridManagement/gridDataManage/unitgridattr'
import UnitGridAttrForm from './UnitGridAttrForm.vue'
import UnitGridAttrDetailDrawer from './UnitGridAttrDetailDrawer.vue'

defineOptions({ name: 'UnitGridAttr' })
const message = useMessage()

const loading = ref(false)
const exportLoading = ref(false)
const total = ref(0)
const list = ref<UnitGridAttrVO[]>([])
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  unitGridCode: '',
  initTime: [],
  endTime: [],
  stopFlag: ''
})

const queryFormRef = ref()

const getList = async () => {
  loading.value = true
  try {
    const data = await UnitGridAttrApi.getUnitGridAttrPage({
      ...queryParams,
      orderBy: 'initTime desc'
    })
    list.value = data.list
    total.value = data.total
  } finally {
    loading.value = false
  }
}

const handleQuery = () => {
  queryParams.pageNo = 1
  getList()
}

const resetQuery = () => {
  queryFormRef.value?.resetFields()
  handleQuery()
}

const formRef = ref()
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

const detailRef = ref()
const openDetail = (row: UnitGridAttrVO) => {
  detailRef.value.open(row)
}

const handleExport = async () => {
  try {
    await message.exportConfirm()
    exportLoading.value = true
    const data = await UnitGridAttrApi.exportUnitGridAttr(queryParams)
    download.excel(data, '单元网格属性台账.xls')
  } finally {
    exportLoading.value = false
  }
}

onMounted(() => getList())
</script>
