<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="100px"
    >
      <el-form-item label="申请时间" prop="applyTime">
        <el-date-picker
          v-model="queryParams.applyTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="审核人ID" prop="auditUserId">
        <el-input
          v-model="queryParams.auditUserId"
          placeholder="请输入审核人ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
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

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <!--      <el-table-column label="主键ID" align="center" prop="id" />-->
      <el-table-column label="ID" align="center" prop="extGridId" />
      <el-table-column label="名称" align="center" prop="extGridName" />
      <el-table-column label="类型" align="center" prop="extType" />
      <el-table-column label="所含基础网格" align="center" prop="includedBasicIds" />
      <el-table-column label="基础网格类型" align="center" prop="basicGridType" />
      <el-table-column label="面积(m²)" align="center" prop="area" />
      <el-table-column label="边界坐标" align="center" prop="boundaryCoords" />
      <el-table-column label="申请原因" align="center" prop="applyReason" />
      <el-table-column label="审核状态" align="center" prop="auditStatus" />
      <el-table-column label="申请人ID" align="center" prop="applyUserId" />
      <el-table-column
        label="申请时间"
        align="center"
        prop="applyTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="审核人ID" align="center" prop="auditUserId" />
      <el-table-column
        label="审核时间"
        align="center"
        prop="auditTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="审核意见" align="center" prop="auditOpinion" />
      <!--      <el-table-column label="分类扩展字段1" align="center" prop="extCat1" />-->
      <!--      <el-table-column label="分类扩展字段2" align="center" prop="extCat2" />-->
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="180px" fixed="right">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)"> 详情 </el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)">
            编辑
          </el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)"> 删除 </el-button>
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

  <!-- 表单弹窗：添加/修改 -->
  <ExtGridDivForm ref="formRef" @success="getList" />
  <ExtGridDivDetail ref="detailDrawerRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { ExtGridDivApi, ExtGridDivVO } from '@/api/dataHub/gridManagement/divideConfig/extgriddiv'
import ExtGridDivForm from './ExtGridDivForm.vue'
import ExtGridDivDetail from './ExtGridDivDetail.vue'

/** 扩展网格划分 列表 */
defineOptions({ name: 'ExtGridDiv' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<ExtGridDivVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  extGridId: undefined,
  extGridName: undefined,
  extType: undefined,
  includedBasicIds: undefined,
  basicGridType: undefined,
  area: undefined,
  boundaryCoords: undefined,
  applyReason: undefined,
  auditStatus: undefined,
  applyUserId: undefined,
  applyTime: [],
  auditUserId: undefined,
  auditTime: [],
  auditOpinion: undefined,
  extCat1: undefined,
  extCat2: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await ExtGridDivApi.getExtGridDivPage(queryParams)
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
  handleQuery()
}

/** 添加/修改操作 */
const formRef = ref()
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 详情抽屉 */
const detailDrawerRef = ref<typeof ExtGridDivDetail>()

const openDetail = (row: ExtGridDivVO) => {
  detailDrawerRef.value?.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await ExtGridDivApi.deleteExtGridDiv(id)
    message.success(t('common.delSuccess'))
    // 刷新列表
    await getList()
  } catch {}
}

/** 导出按钮操作 */
const handleExport = async () => {
  try {
    // 导出的二次确认
    await message.exportConfirm()
    // 发起导出
    exportLoading.value = true
    const data = await ExtGridDivApi.exportExtGridDiv(queryParams)
    download.excel(data, '扩展网格划分.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 初始化 **/
onMounted(() => {
  getList()
})
</script>
