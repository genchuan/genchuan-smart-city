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
      <!-- 只保留常用搜索字段，隐藏部分不常用的 -->
      <el-form-item label="事件ID" prop="evtId">
        <el-input
          v-model="queryParams.evtId"
          placeholder="请输入事件ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="事件编码" prop="evtCode">
        <el-input
          v-model="queryParams.evtCode"
          placeholder="请输入事件编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="事件类型" prop="evtType">
        <el-select
          v-model="queryParams.evtType"
          placeholder="请选择事件类型"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="关联点位名称" prop="relPtName">
        <el-input
          v-model="queryParams.relPtName"
          placeholder="请输入关联点位名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联设备名称" prop="relDeviceName">
        <el-input
          v-model="queryParams.relDeviceName"
          placeholder="请输入关联设备名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="事发时间" prop="evtTime">
        <el-date-picker
          v-model="queryParams.evtTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="处置状态" prop="dealStatus">
        <el-select
          v-model="queryParams.dealStatus"
          placeholder="请选择处置状态"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
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

  <!-- 列表 -->
  <ContentWrap>
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      class="custom-table"
    >
      <el-table-column label="主键ID" align="center" prop="id" width="80" />
      <el-table-column label="事件ID" align="center" prop="evtId" />
      <el-table-column label="事件编码" align="center" prop="evtCode" />
      <el-table-column label="事件类型" align="center" prop="evtType" />
      <el-table-column label="关联点位名称" align="center" prop="relPtName" />
      <el-table-column label="关联设备名称" align="center" prop="relDeviceName" />
      <el-table-column label="事件数值" align="center" prop="evtValue" />
      <el-table-column
        label="事发时间"
        align="center"
        prop="evtTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="处置状态" align="center" min-width="100px">
        <template #default="scope">
          <el-tag :type="getDealStatusTagType(scope.row.dealStatus)">
            {{ scope.row.dealStatus || '未设置' }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column
        label="处置时间"
        align="center"
        prop="dealTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTimeSys"
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
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <BasicMonEvtForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <BasicMonEvtDetail ref="detailRef" :visible="detailVisible" @close="detailVisible = false" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  BasicMonEvtApi,
  BasicMonEvtVO
} from '@/api/dataHub/operationMonitor/basicDataManage/basicmonevt'
import BasicMonEvtForm from './BasicMonEvtForm.vue'
import BasicMonEvtDetail from './BasicMonEvtDetail.vue'

/** 基础监测事件 列表 */
defineOptions({ name: 'BasicMonEvt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<BasicMonEvtVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  evtId: undefined,
  evtCode: undefined,
  evtType: undefined,
  relPtId: undefined,
  relPtName: undefined,
  relIdxId: undefined,
  relIdxName: undefined,
  relDeviceCode: undefined,
  relDeviceName: undefined,
  evtValue: undefined,
  evtTime: [],
  dealStatus: undefined,
  deptCode: undefined,
  deptName: undefined,
  dealUserId: undefined,
  dealUserName: undefined,
  dealResult: undefined,
  dealTime: [],
  createTimeSys: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中
const formRef = ref()
const detailRef = ref()
const detailVisible = ref(false)

/** 格式化处置状态 */
// 处理处置状态标签样式
const getDealStatusTagType = (status: string) => {
  // 根据实际状态值配置对应的标签类型，这里仅为示例
  switch (status) {
    case '已处置':
      return 'success' // 成功状态-绿色
    case '处置中':
      return 'warning' // 警告状态-黄色
    case '未处置':
      return 'danger' // 危险状态-红色
    default:
      return 'info' // 默认状态-蓝色
  }
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await BasicMonEvtApi.getBasicMonEvtPage(queryParams)
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
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 查看详情 */
const openDetail = (row: BasicMonEvtVO) => {
  detailVisible.value = true
  detailRef.value.setData(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await BasicMonEvtApi.deleteBasicMonEvt(id)
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
    const data = await BasicMonEvtApi.exportBasicMonEvt(queryParams)
    download.excel(data, '基础监测事件.xls')
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

<style scoped>
.custom-table {
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

::v-deep .el-table__header-wrapper th {
  background-color: #f9fafb;
  font-weight: 500;
}

::v-deep .el-table tr:hover > td {
  background-color: #f5f7fa !important;
}
</style>
