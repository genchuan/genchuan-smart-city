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
      <el-form-item label="分域ID" prop="domId">
        <el-input
          v-model="queryParams.domId"
          placeholder="请输入分域ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="分域名称" prop="domName">
        <el-input
          v-model="queryParams.domName"
          placeholder="请输入分域名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="分域编码" prop="domCode">
        <el-input
          v-model="queryParams.domCode"
          placeholder="请输入分域编码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="应用场景大类" prop="appSceneMajorName">
        <el-input
          v-model="queryParams.appSceneMajorName"
          placeholder="请输入应用场景大类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="应用场景小类" prop="appSceneMinorName">
        <el-input
          v-model="queryParams.appSceneMinorName"
          placeholder="请输入应用场景小类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px"
        />
      </el-form-item>
      <el-form-item label="分域状态" prop="domStatus">
        <el-select
          v-model="queryParams.domStatus"
          placeholder="请选择分域状态"
          clearable
          class="!w-200px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
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
      <el-table-column label="分域ID" align="center" prop="domId" width="120" />
      <el-table-column label="分域名称" align="center" prop="domName" width="150" />
      <el-table-column label="分域编码" align="center" prop="domCode" width="150" />
      <el-table-column label="应用场景大类" align="center" prop="appSceneMajorName" width="160" />
      <el-table-column label="应用场景小类" align="center" prop="appSceneMinorName" width="160" />
      <el-table-column label="关联点位数量" align="center" prop="relPtCount" width="120" />
      <el-table-column label="关联设备数量" align="center" prop="relDeviceCount" width="120" />
      <el-table-column label="场景负责人" align="center" prop="sceneMngrName" width="120" />
      <el-table-column label="主管部门" align="center" prop="deptName" width="150" />
      <el-table-column label="分域状态" align="center" prop="domStatus" width="100">
        <template #default="scope">
          <span :class="scope.row.domStatus ? 'status-active' : 'status-inactive'">
            {{ scope.row.domStatus ? '启用' : '停用' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
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
      class="mt-4"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <DomDivAppSceneForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <!-- 详情抽屉 -->
  <DomDivAppSceneDetail ref="detailRef" v-model:visible="detailVisible" :data="detailData" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DomDivAppSceneApi,
  DomDivAppSceneVO
} from '@/api/dataHub/operationMonitor/domainMonitorManage/domdivappscene'
import DomDivAppSceneForm from './DomDivAppSceneForm.vue'
import DomDivAppSceneDetail from './DomDivAppSceneDetail.vue'

/** 应用场景分域 列表 */
defineOptions({ name: 'DomDivAppScene' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DomDivAppSceneVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  domId: undefined,
  domName: undefined,
  domCode: undefined,
  appSceneMajorId: undefined,
  appSceneMajorName: undefined,
  appSceneMinorId: undefined,
  appSceneMinorName: undefined,
  relPtCount: undefined,
  relDeviceCount: undefined,
  relEvtCount: undefined,
  sceneMngrId: undefined,
  sceneMngrName: undefined,
  deptCode: undefined,
  deptName: undefined,
  domStatus: undefined,
  createTimeBiz: undefined,
  updateTimeBiz: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
const detailRef = ref()
const detailVisible = ref(false)
const detailData = ref<DomDivAppSceneVO | null>(null)

const openDetail = (row: DomDivAppSceneVO) => {
  detailData.value = row
  detailVisible.value = true
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DomDivAppSceneApi.getDomDivAppScenePage(queryParams)
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

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await DomDivAppSceneApi.deleteDomDivAppScene(id)
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
    const data = await DomDivAppSceneApi.exportDomDivAppScene(queryParams)
    download.excel(data, '应用场景分域.xls')
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
}

.status-active {
  color: #4caf50;
  background-color: rgba(76, 175, 80, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.status-inactive {
  color: #f44336;
  background-color: rgba(244, 67, 54, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}
</style>
