<template>
  <ContentWrap>
    <!-- 搜索条件 -->
    <el-form :model="queryParams" ref="queryFormRef" :inline="true" label-width="120px">
      <el-form-item label="评价网格标识码">
        <el-input
          v-model="queryParams.evalGridCode"
          placeholder="请输入编码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>

      <el-form-item label="类型码">
        <el-select
          v-model="queryParams.typeCode"
          placeholder="请选择类型"
          clearable
          style="width: 200px"
        >
          <el-option label="A类" value="A" />
          <el-option label="B类" value="B" />
          <el-option label="C类" value="C" />
        </el-select>
      </el-form-item>

      <el-form-item label="编码状态">
        <el-select
          v-model="queryParams.codeStatus"
          placeholder="请选择状态"
          clearable
          style="width: 200px"
        >
          <el-option label="有效" value="有效" />
          <el-option label="作废" value="作废" />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="handleQuery">搜索</el-button>
        <el-button @click="resetQuery">重置</el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading">
          导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 表格 -->
  <ContentWrap>
    <el-table
      v-loading="loading"
      :data="list"
      border
      stripe
      highlight-current-row
      :default-sort="{ prop: 'generateTime', order: 'descending' }"
    >
      <el-table-column label="评价网格标识码" prop="evalGridCode" align="center" />

      <el-table-column label="类型码" prop="typeCode" align="center">
        <template #default="{ row }">
          <el-tag
            :type="row.typeCode === 'A' ? 'danger' : row.typeCode === 'B' ? 'primary' : 'success'"
          >
            {{ row.typeCode }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column
        label="生成时间"
        prop="generateTime"
        align="center"
        width="180"
        sortable
        :formatter="dateFormatter"
      />

      <!-- 状态字段颜色区分 -->
      <el-table-column label="状态" prop="codeStatus" align="center">
        <template #default="{ row }">
          <el-tag
            :type="row.codeStatus === '有效' ? 'success' : row.codeStatus === '作废' ? 'danger' : 'info'"
          >
            {{ row.codeStatus }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column label="备注" prop="remark" align="center" />

      <el-table-column label="操作" width="160" align="center">
        <template #default="{ row }">
          <el-button link type="primary" @click="openDetail(row)">详情</el-button>
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

  <EvalGridCodeDetail ref="detailRef" />
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { EvalGridCodeApi, EvalGridCodeVO } from '@/api/dataHub/gridManagement/gridCode/evalgridcode'
import EvalGridCodeDetail from './EvalGridCodeDetail.vue'

const list = ref<EvalGridCodeVO[]>([])
const total = ref(0)
const loading = ref(false)
const exportLoading = ref(false)

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  evalGridCode: undefined,
  typeCode: undefined,
  codeStatus: undefined
})

const getList = async () => {
  loading.value = true
  const data = await EvalGridCodeApi.getEvalGridCodePage({
    ...queryParams,
    //sortField: 'generateTime', // 后端排序（与表格默认排序一致）
    //sortOrder:''
  })
  list.value = data.list
  total.value = data.total
  loading.value = false
}

const handleQuery = () => {
  queryParams.pageNo = 1
  getList()
}

const resetQuery = () => {
  queryParams.evalGridCode = undefined
  queryParams.typeCode = undefined
  queryParams.codeStatus = undefined
  handleQuery()
}

const handleExport = async () => {
  exportLoading.value = true
  const data = await EvalGridCodeApi.exportEvalGridCode(queryParams)
  download.excel(data, '评价网格编码.xlsx')
  exportLoading.value = false
}

const detailRef = ref()
const openDetail = (row: EvalGridCodeVO) => {
  detailRef.value.open(row)
}

onMounted(() => {
  getList()
})
</script>
