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
      <!-- 保留常用搜索字段 -->
      <el-form-item label="资产ID" prop="assetId">
        <el-input
          v-model="queryParams.assetId"
          placeholder="请输入资产ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="资产名称" prop="assetName">
        <el-input
          v-model="queryParams.assetName"
          placeholder="请输入资产名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="资产类型" prop="assetType">
        <el-select
          v-model="queryParams.assetType"
          placeholder="请选择资产类型"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="资产档案名称" prop="assetProfileName">
        <el-input
          v-model="queryParams.assetProfileName"
          placeholder="请输入资产档案名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker
          v-model="queryParams.createTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          class="mr-2"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
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

  <!-- 列表（保留常用字段，隐藏系统内部字段） -->
  <ContentWrap>
    <el-table
      v-loading="loading"
      :data="list"
      :stripe="true"
      :show-overflow-tooltip="true"
      class="custom-table"
    >
      <el-table-column label="主键ID" align="center" prop="id" width="80" />
      <el-table-column label="资产ID" align="center" prop="assetId" width="140" />
      <el-table-column label="资产名称" align="center" prop="assetName" min-width="160" />
      <el-table-column label="资产类型" align="center" prop="assetType" width="120" />
      <el-table-column label="资产标签" align="center" prop="assetLabel" width="120" />
      <el-table-column label="外部ID" align="center" prop="externalId" width="140" />
      <el-table-column label="版本号" align="center" prop="version" width="80" />
      <el-table-column label="资产档案名称" align="center" prop="assetProfileName" min-width="160" />
      <el-table-column label="客户是否公开" align="center" prop="customerIsPublic" width="120">
        <template #default="scope">
          <el-tag :type="scope.row.customerIsPublic ? 'success' : 'danger'">
            {{ scope.row.customerIsPublic ? '是' : '否' }}
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
      <el-table-column label="操作" align="center" min-width="180px" fixed="right">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openDetail(scope.row)"
            class="mr-2"
          >
            详情
          </el-button>
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
          >
            删除
          </el-button>
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
  <AssetInfoForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <AssetInfoDetailDrawer
    :visible="detailVisible"
    :detail-data="detailData"
    @update:visible="(val) => detailVisible = val"
    @close="handleDetailClose"
  />
</template>
<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { AssetInfoApi, AssetInfoVO } from '@/api/dataHub/assetManagement/assetinfo'
import AssetInfoForm from './AssetInfoForm.vue'
import AssetInfoDetailDrawer from './AssetInfoDetailDrawer.vue'

/** 资产信息 列表 */
defineOptions({ name: 'AssetInfo' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<AssetInfoVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  assetId: undefined,
  assetName: undefined,
  assetType: undefined,
  assetProfileName: undefined,
  createTime: [],
  // 隐藏字段不展示在搜索栏，保留字段用于后端查询
  entityType: undefined,
  createdTime: [],
  tenantEntityType: undefined,
  customerId: undefined,
  customerEntityType: undefined,
  assetProfileId: undefined,
  assetProfileEntityType: undefined,
  assetLabel: undefined,
  externalId: undefined,
  version: undefined,
  customerTitle: undefined,
  customerIsPublic: undefined,
  additionalInfo: undefined,
  attributes: undefined,
  contextDevices: undefined,
  tenantIdSys: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
const detailVisible = ref(false)
const detailData = ref<AssetInfoVO>({} as AssetInfoVO)
const formRef = ref()

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await AssetInfoApi.getAssetInfoPage(queryParams)
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

/** 打开表单（新增/编辑） */
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 打开详情抽屉 */
const openDetail = (row: AssetInfoVO) => {
  detailData.value = { ...row }
  detailVisible.value = true
}

/** 关闭详情抽屉（重置数据） */
const handleDetailClose = () => {
  detailData.value = {} as AssetInfoVO
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    await message.delConfirm()
    await AssetInfoApi.deleteAssetInfo(id)
    message.success(t('common.delSuccess'))
    await getList()
  } catch {}
}

/** 导出按钮操作 */
const handleExport = async () => {
  try {
    await message.exportConfirm()
    exportLoading.value = true
    const data = await AssetInfoApi.exportAssetInfo(queryParams)
    download.excel(data, '资产信息.xls')
  } catch {} finally {
    exportLoading.value = false
  }
}

/** 初始化 */
onMounted(() => {
  getList()
})
</script>

<style scoped>
/* 表格样式美化 */
.custom-table {
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

::v-deep .el-table__header-wrapper th {
  background-color: #f5f7fa;
  font-weight: 500;
  color: #333;
}

::v-deep .el-table__body tr:hover > td {
  background-color: #f9fafb !important;
}
</style>
