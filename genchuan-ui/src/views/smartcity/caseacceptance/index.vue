<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
    >
      <el-form-item label="案件编号" prop="caseCode" label-width="100px">
        <el-input
          v-model="queryParams.caseCode"
          placeholder="请输入案件编号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="案件名称" prop="caseName">
        <el-input
          v-model="queryParams.caseName"
          placeholder="请输入案件名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="案件时间" prop="caseTime">
        <el-date-picker
          v-model="queryParams.caseTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-220px"
        />
      </el-form-item>
      <el-form-item style="margin-left: 60px">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:case-acceptance:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:case-acceptance:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="ID" align="center" prop="id" min-width="30px" />
      <el-table-column label="案件编号" align="center" prop="caseCode" />
      <el-table-column label="案件名称" align="center" prop="caseName" />
      <el-table-column label="案件类型" align="center" prop="caseType" />
      <el-table-column label="案件来源" align="center">
        <template #default="scope">
          <template v-if="scope.row.caseSource === 'telephone'">电话举报</template>
          <template v-else-if="scope.row.caseSource === 'network'">网络举报</template>
          <template v-else-if="scope.row.caseSource === 'wechat'">微信举报</template>
          <template v-else-if="scope.row.caseSource === 'citizen_photo'">市民随手拍</template>
          <template v-else-if="scope.row.caseSource === 'online_monitor'">在线监测系统自动上报</template>
          <template v-else-if="scope.row.caseSource === 'on-site_inspection'">现场巡查发现</template>
          <template v-else-if="scope.row.caseSource === 'other_department'">其他部门移交</template>
          <template v-else-if="scope.row.caseSource === 'letter_report'">来信举报</template>
          <template v-else>{{ scope.row.caseSource }}</template>
        </template>
      </el-table-column>
      <el-table-column
        label="案件时间"
        align="center"
        prop="caseTime"
        :formatter="dateFormatter2"
        width="120px"
      />
      <el-table-column label="案件地点" align="center" prop="caseLocation" />
      <el-table-column label="报案单位" align="center" prop="reportUnit" />
      <el-table-column label="当事人信息" align="center" prop="reportPerson" />
      <el-table-column label="联系电话" align="center" prop="reportPhone" />
      <el-table-column label="案件描述" align="center" prop="caseDesc" />
      <el-table-column label="立案状态" align="center">
        <template #default="scope">
          <template v-if="scope.row.caseStatus === 'pending_filing'">待立案</template>
          <template v-else-if="scope.row.caseStatus === 'filed'">已立案</template>
          <template v-else-if="scope.row.caseStatus === 'rejected_filing'">不予立案</template>
          <template v-else-if="scope.row.caseStatus === 'rejected_review'">驳回重审</template>
          <template v-else>{{ scope.row.caseStatus }}</template>
        </template>
      </el-table-column>
      <!--<el-table-column-->
      <!--  label="创建时间"-->
      <!--  align="center"-->
      <!--  prop="createTime"-->
      <!--  :formatter="dateFormatter"-->
      <!--  width="180px"-->
      <!--/>-->
      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['smartcity:case-acceptance:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:case-acceptance:delete']"
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
  <CaseAcceptanceForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter2 } from '@/utils/formatTime'
import download from '@/utils/download'
import { CaseAcceptanceApi, CaseAcceptanceVO } from '@/api/smartcity/caseacceptance'
import CaseAcceptanceForm from './CaseAcceptanceForm.vue'

/** 案件受理 列表 */
defineOptions({ name: 'CaseAcceptance' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<CaseAcceptanceVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  caseCode: undefined,
  caseName: undefined,
  caseTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await CaseAcceptanceApi.getCaseAcceptancePage(queryParams)
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
    await CaseAcceptanceApi.deleteCaseAcceptance(id)
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
    const data = await CaseAcceptanceApi.exportCaseAcceptance(queryParams)
    download.excel(data, '案件受理.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 初始化 */
onMounted(() => {
  getList()
})
</script>
