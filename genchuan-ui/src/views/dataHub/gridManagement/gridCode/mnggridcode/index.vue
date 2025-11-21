<template>
  <ContentWrap>
    <!-- 查询区 -->
    <el-form :model="queryParams" ref="queryFormRef" :inline="true" label-width="120px">
      <el-form-item label="管理网格标识码">
        <el-input
          v-model="queryParams.mgGridCode"
          placeholder="请输入网格编码"
          clearable
          class="!w-220px"
        />
      </el-form-item>

      <el-form-item label="编码状态">
        <el-select
          v-model="queryParams.codeStatus"
          placeholder="请选择状态"
          clearable
          class="!w-160px"
        >
          <el-option label="有效" value="有效" />
          <el-option label="作废" value="作废" />
        </el-select>
      </el-form-item>

      <el-form-item label="街道代码">
        <el-input
          v-model="queryParams.streetCode"
          placeholder="请输入街道代码"
          clearable
          class="!w-160px"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="handleQuery">
          <Icon icon="ep:search" /> 查询
        </el-button>
        <el-button @click="resetQuery">
          <Icon icon="ep:refresh" /> 重置
        </el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading">
          <Icon icon="ep:download" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 表格 -->
  <ContentWrap>
    <el-table
      v-loading="loading"
      :data="list"
      :default-sort="{ prop: 'generateTime', order: 'descending' }"
      border
      stripe
    >
      <el-table-column prop="mgCodeId" label="编码ID" align="center" width="160" />
      <el-table-column prop="mgGridCode" label="网格标识码" align="center" width="180" />
      <el-table-column prop="mngGridId" label="所属管理网格ID" align="center" />
      <el-table-column prop="areaFullCode" label="行政区划完整代码" align="center" />
      <el-table-column prop="seqCode" label="顺序码" align="center" width="100" />
      <el-table-column
        prop="generateTime"
        label="生成时间"
        align="center"
        :formatter="dateFormatter"
        sortable
        width="180"
      />
      <el-table-column label="状态" align="center" width="100">
        <template #default="scope">
          <el-tag :type="scope.row.codeStatus === '有效' ? 'success' : 'danger'">
            {{ scope.row.codeStatus }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="remark" label="备注" align="center" />
      <el-table-column label="操作" align="center" width="120">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)">详情</el-button>
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

  <!-- 详情抽屉 -->
  <MngGridCodeDetail ref="detailRef" />
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { MngGridCodeApi, MngGridCodeVO } from '@/api/dataHub/gridManagement/gridCode/mnggridcode'
import MngGridCodeDetail from './MngGridCodeDetail.vue'

/** 加载状态与数据 */
const loading = ref(true)
const list = ref<MngGridCodeVO[]>([])
const total = ref(0)
const exportLoading = ref(false)

/** 查询参数（✅ 空字符串防止类型报错） */
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  mgGridCode: '',
  codeStatus: '',
  streetCode: '',
})

const queryFormRef = ref()
const detailRef = ref()

/** 获取列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await MngGridCodeApi.getMngGridCodePage(queryParams)
    list.value = data.list
    total.value = data.total
  } finally {
    loading.value = false
  }
}

/** 搜索 */
const handleQuery = () => {
  queryParams.pageNo = 1
  getList()
}

/** 重置（✅ 清空字符串并刷新） */
const resetQuery = () => {
  queryParams.mgGridCode = ''
  queryParams.codeStatus = ''
  queryParams.streetCode = ''
  queryParams.pageNo = 1
  queryFormRef.value?.resetFields()
  getList()
}

/** 导出 */
const handleExport = async () => {
  exportLoading.value = true
  try {
    const data = await MngGridCodeApi.exportMngGridCode(queryParams)
    download.excel(data, '管理网格编码.xls')
  } finally {
    exportLoading.value = false
  }
}

/** 打开详情 */
const openDetail = (row: MngGridCodeVO) => {
  detailRef.value.open(row)
}

/** 初始化 */
onMounted(() => {
  getList()
})
</script>
