<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="68px"
    >
      <el-form-item label="投诉ID，唯一编码，UUID生成" prop="complainId">
        <el-input
          v-model="queryParams.complainId"
          placeholder="请输入投诉ID，唯一编码，UUID生成"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="投诉单号，格式“TS+YYYYMMDD+6位流水号”" prop="complainNo">
        <el-input
          v-model="queryParams.complainNo"
          placeholder="请输入投诉单号，格式“TS+YYYYMMDD+6位流水号”"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="诉求类型编码，投诉类型，如“违章停车”，字典表（sys_dict）" prop="appealTypeCode">
        <el-input
          v-model="queryParams.appealTypeCode"
          placeholder="请输入诉求类型编码，投诉类型，如“违章停车”，字典表（sys_dict）"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="诉求类型名称，与类型编码同步，字典表（sys_dict）" prop="appealTypeName">
        <el-input
          v-model="queryParams.appealTypeName"
          placeholder="请输入诉求类型名称，与类型编码同步，字典表（sys_dict）"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="事发区域编码，事发区域国标编码，区域表（sys_area）" prop="areaCode">
        <el-input
          v-model="queryParams.areaCode"
          placeholder="请输入事发区域编码，事发区域国标编码，区域表（sys_area）"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="事发区域名称，与区域编码同步，区域表（sys_area）" prop="areaName">
        <el-input
          v-model="queryParams.areaName"
          placeholder="请输入事发区域名称，与区域编码同步，区域表（sys_area）"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="事发地址，详细地址，如“XX路12号门前”" prop="incidentAddr">
        <el-input
          v-model="queryParams.incidentAddr"
          placeholder="请输入事发地址，详细地址，如“XX路12号门前”"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="上传图片路径，多张图片用逗号分隔" prop="imgPath">
        <el-input
          v-model="queryParams.imgPath"
          placeholder="请输入上传图片路径，多张图片用逗号分隔"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="联系人姓名，投诉人姓名" prop="contactName">
        <el-input
          v-model="queryParams.contactName"
          placeholder="请输入联系人姓名，投诉人姓名"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="联系人电话，加密存储" prop="contactPhone">
        <el-input
          v-model="queryParams.contactPhone"
          placeholder="请输入联系人电话，加密存储"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="受理部门编码，受理部门编码，部门信息表（sys_org）" prop="acceptDeptCode">
        <el-input
          v-model="queryParams.acceptDeptCode"
          placeholder="请输入受理部门编码，受理部门编码，部门信息表（sys_org）"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="受理部门名称，与部门编码同步，部门信息表（sys_org）" prop="acceptDeptName">
        <el-input
          v-model="queryParams.acceptDeptName"
          placeholder="请输入受理部门名称，与部门编码同步，部门信息表（sys_org）"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="处置状态：1=待受理，2=已受理，3=处置中，4=已办结，5=已驳回，流程状态" prop="handleStatus">
        <el-select
          v-model="queryParams.handleStatus"
          placeholder="请选择处置状态：1=待受理，2=已受理，3=处置中，4=已办结，5=已驳回，流程状态"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="处置结果，处置完成后填写结果" prop="handleResult">
        <el-input
          v-model="queryParams.handleResult"
          placeholder="请输入处置结果，处置完成后填写结果"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="提交时间，市民提交时间" prop="submitTime">
        <el-date-picker
          v-model="queryParams.submitTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="受理时间，受理部门接单时间" prop="acceptTime">
        <el-date-picker
          v-model="queryParams.acceptTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="办结时间，处置完成时间" prop="finishTime">
        <el-date-picker
          v-model="queryParams.finishTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item label="分类扩展字段1，预留，标识问题等级，如一般/紧急" prop="extCat1">
        <el-input
          v-model="queryParams.extCat1"
          placeholder="请输入分类扩展字段1，预留，标识问题等级，如一般/紧急"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="分类扩展字段2，预留，标识处置方式，如现场/线上" prop="extCat2">
        <el-input
          v-model="queryParams.extCat2"
          placeholder="请输入分类扩展字段2，预留，标识处置方式，如现场/线上"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="通用扩展字段1，预留，存储投诉来源，如APP/热线" prop="extCommon1">
        <el-input
          v-model="queryParams.extCommon1"
          placeholder="请输入通用扩展字段1，预留，存储投诉来源，如APP/热线"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="通用扩展字段2，预留，存储处置人员ID" prop="extCommon2">
        <el-input
          v-model="queryParams.extCommon2"
          placeholder="请输入通用扩展字段2，预留，存储处置人员ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="系统创建时间" prop="createTimeSys">
        <el-date-picker
          v-model="queryParams.createTimeSys"
          value-format="YYYY-MM-DD"
          type="date"
          placeholder="选择系统创建时间"
          clearable
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="系统更新时间" prop="updateTimeSys">
        <el-date-picker
          v-model="queryParams.updateTimeSys"
          value-format="YYYY-MM-DD"
          type="date"
          placeholder="选择系统更新时间"
          clearable
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['datacenter:public-complain:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['datacenter:public-complain:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="投诉ID，唯一编码，UUID生成" align="center" prop="complainId" />
      <el-table-column label="投诉单号，格式“TS+YYYYMMDD+6位流水号”" align="center" prop="complainNo" />
      <el-table-column label="诉求类型编码，投诉类型，如“违章停车”，字典表（sys_dict）" align="center" prop="appealTypeCode" />
      <el-table-column label="诉求类型名称，与类型编码同步，字典表（sys_dict）" align="center" prop="appealTypeName" />
      <el-table-column label="事发区域编码，事发区域国标编码，区域表（sys_area）" align="center" prop="areaCode" />
      <el-table-column label="事发区域名称，与区域编码同步，区域表（sys_area）" align="center" prop="areaName" />
      <el-table-column label="事发地址，详细地址，如“XX路12号门前”" align="center" prop="incidentAddr" />
      <el-table-column label="投诉内容，问题描述" align="center" prop="complainContent" />
      <el-table-column label="上传图片路径，多张图片用逗号分隔" align="center" prop="imgPath" />
      <el-table-column label="联系人姓名，投诉人姓名" align="center" prop="contactName" />
      <el-table-column label="联系人电话，加密存储" align="center" prop="contactPhone" />
      <el-table-column label="受理部门编码，受理部门编码，部门信息表（sys_org）" align="center" prop="acceptDeptCode" />
      <el-table-column label="受理部门名称，与部门编码同步，部门信息表（sys_org）" align="center" prop="acceptDeptName" />
      <el-table-column label="处置状态：1=待受理，2=已受理，3=处置中，4=已办结，5=已驳回，流程状态" align="center" prop="handleStatus" />
      <el-table-column label="处置结果，处置完成后填写结果" align="center" prop="handleResult" />
      <el-table-column
        label="提交时间，市民提交时间"
        align="center"
        prop="submitTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column
        label="受理时间，受理部门接单时间"
        align="center"
        prop="acceptTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column
        label="办结时间，处置完成时间"
        align="center"
        prop="finishTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="分类扩展字段1，预留，标识问题等级，如一般/紧急" align="center" prop="extCat1" />
      <el-table-column label="分类扩展字段2，预留，标识处置方式，如现场/线上" align="center" prop="extCat2" />
      <el-table-column label="通用扩展字段1，预留，存储投诉来源，如APP/热线" align="center" prop="extCommon1" />
      <el-table-column label="通用扩展字段2，预留，存储处置人员ID" align="center" prop="extCommon2" />
      <el-table-column
        label="系统创建时间"
        align="center"
        prop="createTimeSys"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column
        label="系统更新时间"
        align="center"
        prop="updateTimeSys"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['datacenter:public-complain:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['datacenter:public-complain:delete']"
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
  <PublicComplainForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { PublicComplainApi, PublicComplainVO } from '@/api/dataHub/analysisDecision/publiccomplain'
import PublicComplainForm from './PublicComplainForm.vue'

/** 投诉举报 列表 */
defineOptions({ name: 'PublicComplain' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<PublicComplainVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  complainId: undefined,
  complainNo: undefined,
  appealTypeCode: undefined,
  appealTypeName: undefined,
  areaCode: undefined,
  areaName: undefined,
  incidentAddr: undefined,
  complainContent: undefined,
  imgPath: undefined,
  contactName: undefined,
  contactPhone: undefined,
  acceptDeptCode: undefined,
  acceptDeptName: undefined,
  handleStatus: undefined,
  handleResult: undefined,
  submitTime: [],
  acceptTime: [],
  finishTime: [],
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTimeSys: [],
  updateTimeSys: [],
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await PublicComplainApi.getPublicComplainPage(queryParams)
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
    await PublicComplainApi.deletePublicComplain(id)
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
    const data = await PublicComplainApi.exportPublicComplain(queryParams)
    download.excel(data, '投诉举报.xls')
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
