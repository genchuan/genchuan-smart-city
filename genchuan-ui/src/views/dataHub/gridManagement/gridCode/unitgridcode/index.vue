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
      <el-form-item label="编码ID" prop="unitCodeId">
        <el-input
          v-model="queryParams.unitCodeId"
          placeholder="请输入编码ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
<!--      <el-form-item label="单元网格标识码" prop="unitGridCode">-->
<!--        <el-input-->
<!--          v-model="queryParams.unitGridCode"-->
<!--          placeholder="请输入单元网格标识码"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--          class="!w-240px"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="所属单元网格ID" prop="unitGridId">-->
<!--        <el-input-->
<!--          v-model="queryParams.unitGridId"-->
<!--          placeholder="请输入所属单元网格ID"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--          class="!w-240px"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="行政区划代码" prop="areaFullCode">
        <el-input
          v-model="queryParams.areaFullCode"
          placeholder="请输入前6位县级代码可快速定位"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>



      <el-form-item label="编码状态" prop="codeStatus">
        <el-select
          v-model="queryParams.codeStatus"
          placeholder="请选择编码状态"
          clearable
          class="!w-240px"
          @change="handleQuery"
        >
          <el-option label="有效" value="有效" />
          <el-option label="作废" value="作废" />
        </el-select>
      </el-form-item>

<!--          <el-form-item label="顺序码" prop="seqCode">-->
<!--        <el-input-->
<!--          v-model="queryParams.seqCode"-->
<!--          placeholder="请输入顺序码"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--          class="!w-240px"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="编码生成时间" prop="generateTime">-->
<!--        <el-date-picker-->
<!--          v-model="queryParams.generateTime"-->
<!--          value-format="YYYY-MM-DD HH:mm:ss"-->
<!--          type="daterange"-->
<!--          start-placeholder="开始日期"-->
<!--          end-placeholder="结束日期"-->
<!--          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"-->
<!--          class="!w-220px"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="备注" prop="remark">-->
<!--        <el-input-->
<!--          v-model="queryParams.remark"-->
<!--          placeholder="请输入备注"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--          class="!w-240px"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="分类扩展字段1" prop="extCat1">-->
<!--        <el-input-->
<!--          v-model="queryParams.extCat1"-->
<!--          placeholder="请输入分类扩展字段1"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--          class="!w-240px"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="分类扩展字段2" prop="extCat2">-->
<!--        <el-input-->
<!--          v-model="queryParams.extCat2"-->
<!--          placeholder="请输入分类扩展字段2"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--          class="!w-240px"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="通用扩展字段1" prop="extCommon1">-->
<!--        <el-input-->
<!--          v-model="queryParams.extCommon1"-->
<!--          placeholder="请输入通用扩展字段1"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--          class="!w-240px"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="通用扩展字段2" prop="extCommon2">-->
<!--        <el-input-->
<!--          v-model="queryParams.extCommon2"-->
<!--          placeholder="请输入通用扩展字段2"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--          class="!w-240px"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="创建时间" prop="createTime">-->
<!--        <el-date-picker-->
<!--          v-model="queryParams.createTime"-->
<!--          value-format="YYYY-MM-DD HH:mm:ss"-->
<!--          type="daterange"-->
<!--          start-placeholder="开始日期"-->
<!--          end-placeholder="结束日期"-->
<!--          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"-->
<!--          class="!w-220px"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          @click="openForm('create')"-->
<!--        >-->
<!--          <Icon icon="ep:plus" class="mr-5px" /> 新增-->
<!--        </el-button>-->
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
<!--    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">-->
<!--      <el-table-column label="主键ID" align="center" prop="id" />-->
<!--      <el-table-column label="编码ID" align="center" prop="unitCodeId" />-->
<!--      <el-table-column label="单元网格标识码" align="center" prop="unitGridCode" />-->
<!--      <el-table-column label="所属单元网格ID" align="center" prop="unitGridId" />-->
<!--      <el-table-column label="行政区划完整代码" align="center" prop="areaFullCode" />-->
<!--      <el-table-column label="顺序码" align="center" prop="seqCode" />-->
<!--      <el-table-column-->
<!--        label="编码生成时间"-->
<!--        align="center"-->
<!--        prop="generateTime"-->
<!--        :formatter="dateFormatter"-->
<!--        width="180px"-->
<!--      />-->
<!--      <el-table-column label="编码状态" align="center" prop="codeStatus" />-->
<!--      <el-table-column label="备注" align="center" prop="remark" />-->
<!--      <el-table-column label="分类扩展字段1" align="center" prop="extCat1" />-->
<!--      <el-table-column label="分类扩展字段2" align="center" prop="extCat2" />-->
<!--      <el-table-column label="通用扩展字段1" align="center" prop="extCommon1" />-->
<!--      <el-table-column label="通用扩展字段2" align="center" prop="extCommon2" />-->
<!--      <el-table-column-->
<!--        label="创建时间"-->
<!--        align="center"-->
<!--        prop="createTime"-->
<!--        :formatter="dateFormatter"-->
<!--        width="180px"-->
<!--      />-->
<!--      <el-table-column label="操作" align="center" min-width="120px">-->
<!--        <template #default="scope">-->
<!--          <el-button-->
<!--            link-->
<!--            type="primary"-->
<!--            @click="openForm('update', scope.row.id)"-->
<!--          >-->
<!--            编辑-->
<!--          </el-button>-->
<!--          <el-button-->
<!--            link-->
<!--            type="primary"-->
<!--            @click="openDetail(scope.row)"-->
<!--          >-->
<!--            详情-->
<!--          </el-button>-->
<!--          <el-button-->
<!--            link-->
<!--            type="danger"-->
<!--            @click="handleDelete(scope.row.id)"-->
<!--          >-->
<!--            删除-->
<!--          </el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->

    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :highlight-current-row="true"
      :show-overflow-tooltip="true"
      @sort-change="handleSortChange"
    >
      <el-table-column label="编码ID" align="center" prop="unitCodeId" />

      <el-table-column
        label="单元网格标识码"
        align="center"
        prop="unitGridCode"
        min-width="200"
      >
        <template #default="{ row }">
          <el-tooltip
            effect="dark"
            :content="`县级: ${row.areaFullCode?.slice(0, 6)} 街道: ${row.areaFullCode?.slice(6, 9)} 社区: ${row.areaFullCode?.slice(9, 12)} 顺序码: ${row.seqCode}`"
            placement="top"
          >
            <span class="font-mono tracking-wide">{{ row.unitGridCode }}</span>
          </el-tooltip>
        </template>
      </el-table-column>

      <el-table-column label="所属单元网格ID" align="center" prop="unitGridId" />

      <el-table-column
        label="编码生成时间"
        align="center"
        prop="generateTime"
        sortable="custom"
        :formatter="dateFormatter"
        width="180px"
      />

<!--      <el-table-column label="编码状态" align="center" prop="codeStatus" width="120">-->
<!--        <template #default="{ row }">-->
<!--          <el-tooltip-->
<!--            v-if="row.codeStatus === '作废'"-->
<!--            effect="dark"-->
<!--            :content="row.remark || '作废原因未记录'"-->
<!--            placement="top"-->
<!--          >-->
<!--            <span class="text-red-500 font-bold cursor-pointer">{{ row.codeStatus }}</span>-->
<!--          </el-tooltip>-->
<!--          <span v-else class="text-green-600 font-medium">{{ row.codeStatus }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->

      <el-table-column label="状态" align="center">
        <template #default="{ row }">
          <el-tag  :type="row.codeStatus === '有效' ? 'success' : 'danger'">
            {{ row.codeStatus }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column label="备注" align="center" prop="remark" min-width="160" show-overflow-tooltip />

      <el-table-column label="操作" align="center" min-width="120">
        <template #default="{ row }">
          <el-button link type="primary" @click="openDetail(row)">详情</el-button>
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
<!--  <UnitGridCodeForm ref="formRef" @success="getList" />-->
  <!-- 详情抽屉 -->
  <UnitGridCodeDetail ref="detailRef" />

</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { UnitGridCodeApi, UnitGridCodeVO } from '@/api/dataHub/gridManagement/gridCode/unitgridcode'
// import UnitGridCodeForm from './UnitGridCodeForm.vue'
import UnitGridCodeDetail from './UnitGridCodeDetail.vue'

/** 单元网格编码 列表 */
defineOptions({ name: 'UnitGridCode' })

const message = useMessage() // 消息弹窗
//const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<UnitGridCodeVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  unitCodeId: undefined,
  unitGridCode: undefined,
  unitGridId: undefined,
  areaFullCode: undefined,
  seqCode: undefined,
  generateTime: [],
  codeStatus: undefined,
  remark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: [],
  sortField:'',
  sortOrder:''
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await UnitGridCodeApi.getUnitGridCodePage(queryParams)
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

/** 用户点击排序时触发 */
const handleSortChange = ({ prop, order }: any) => {
  queryParams.sortField = prop
  queryParams.sortOrder = order === 'ascending' ? 'asc' : order === 'descending' ? 'desc' : ''
  getList()
}



const detailRef = ref()

/** 打开详情抽屉 */
const openDetail = (row: UnitGridCodeVO) => {
  detailRef.value.open(row)
}


/** 添加/修改操作 */
// const formRef = ref()
// const openForm = (type: string, id?: number) => {
//   formRef.value.open(type, id)
// }

/** 删除按钮操作 */
// const handleDelete = async (id: number) => {
//   try {
//     // 删除的二次确认
//     await message.delConfirm()
//     // 发起删除
//     await UnitGridCodeApi.deleteUnitGridCode(id)
//     message.success(t('common.delSuccess'))
//     // 刷新列表
//     await getList()
//   } catch {}
// }

/** 导出按钮操作 */
const handleExport = async () => {
  try {
    // 导出的二次确认
    await message.exportConfirm()
    // 发起导出
    exportLoading.value = true
    const data = await UnitGridCodeApi.exportUnitGridCode(queryParams)
    download.excel(data, '单元网格编码.xls')
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
