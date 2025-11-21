<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
    >
      <el-form-item label="监督编号" prop="supervisionId" label-width="100px">
        <el-input
          v-model="queryParams.supervisionId"
          placeholder="请输入监督编号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="执法事件编号" prop="eventNumber">
        <el-input
          v-model="queryParams.eventNumber"
          placeholder="请输入执法事件编号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="执法人员" prop="officials">
        <el-input
          v-model="queryParams.officials"
          placeholder="请输入执法人员"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <!--<el-form-item label="创建时间" prop="createTime">-->
      <!--  <el-date-picker-->
      <!--    v-model="queryParams.createTime"-->
      <!--    value-format="YYYY-MM-DD HH:mm:ss"-->
      <!--    type="daterange"-->
      <!--    start-placeholder="开始日期"-->
      <!--    end-placeholder="结束日期"-->
      <!--    :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"-->
      <!--    class="!w-220px"-->
      <!--  />-->
      <!--</el-form-item>-->
      <el-form-item style="margin-left: 60px">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:law-enforcement-supervision:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:law-enforcement-supervision:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="主键" align="center" prop="id" min-width="50px" />
      <el-table-column label="监督编号" align="center" prop="supervisionId" width="140px" />
      <el-table-column label="执法事件编号" align="center" prop="eventNumber" width="140px" />
      <el-table-column label="执法人员" align="center" prop="officials" />
      <el-table-column label="监督人员" align="center" prop="personnel" />
      <el-table-column label="监督时间" align="center" prop="time" :formatter="dateFormatter2"/>
      <el-table-column label="监督类型" align="center" width="150px">
        <template #default="scope">
          <template v-if="scope.row.method === 'internal_recorder'">执法记录仪核查</template>
          <template v-else-if="scope.row.method === 'internal_replay'">执法过程回放</template>
          <template v-else-if="scope.row.method === 'internal_document'">执法文书审查</template>
          <template v-else-if="scope.row.method === 'internal_case_review'">执法案件评查</template>
          <template v-else-if="scope.row.method === 'internal_performance'">执法绩效考核</template>
          <template v-else-if="scope.row.method === 'external_complaint'">投诉举报核查</template>
          <template v-else-if="scope.row.method === 'external_satisfaction'">满意度调查</template>
          <template v-else-if="scope.row.method === 'external_information'">信息公开检查</template>
          <template v-else>{{ scope.row.method }}</template>
        </template>
      </el-table-column>
      <el-table-column label="监督证据" min-width="120" align="center">
        <template #default="scope">
          <template v-if="scope.row.integrityCollection === 'recorder_video'">执法记录仪视频</template>
          <template v-else-if="scope.row.integrityCollection === 'law_document'">执法文书</template>
          <template v-else-if="scope.row.integrityCollection === 'complaint_evidence'">投诉举报证据</template>
          <template v-else-if="scope.row.integrityCollection === 'satisfaction_survey'">满意度调查问卷</template>
          <template v-else-if="scope.row.integrityCollection === 'information_public'">信息公开材料</template>
          <template v-else-if="scope.row.integrityCollection === 'no_evidence'">无监督证据</template>
          <template v-else>{{ scope.row.integrityCollection }}</template>
        </template>
      </el-table-column>
      <el-table-column label="群众满意度" align="center" prop="satisfactionInvolved" />
      <el-table-column label="监督结果" align="center">
        <template #default="scope">
          <el-tag type="success" effect="light" v-if="scope.row.resultEvaluation === 'qualified'">合格</el-tag>
          <el-tag type="warning" effect="light" v-else-if="scope.row.resultEvaluation === 'minor_violation'">轻微违规</el-tag>
          <el-tag type="danger" effect="light" v-else-if="scope.row.resultEvaluation === 'serious_violation'">严重违规</el-tag>
          <el-tag type="info" effect="light" v-else-if="scope.row.resultEvaluation === 'rectified'">已完成整改</el-tag>
          <el-tag type="default" effect="light" v-else-if="scope.row.resultEvaluation === 'no_need_rectify'">无需整改</el-tag>
          <el-tag type="default" effect="light" v-else>{{ scope.row.resultEvaluation }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="监督问题描述" align="center" prop="violationDescription" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['smartcity:law-enforcement-supervision:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:law-enforcement-supervision:delete']"
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
  <LawEnforcementSupervisionForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter,dateFormatter2 } from '@/utils/formatTime'
import download from '@/utils/download'
import { LawEnforcementSupervisionApi, LawEnforcementSupervisionVO } from '@/api/smartcity/lawenforcementsupervision'
import LawEnforcementSupervisionForm from './LawEnforcementSupervisionForm.vue'

/** 执法监督 列表 */
defineOptions({ name: 'LawEnforcementSupervision' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<LawEnforcementSupervisionVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  supervisionId: undefined,
  eventNumber: undefined,
  officials: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await LawEnforcementSupervisionApi.getLawEnforcementSupervisionPage(queryParams)
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
    await LawEnforcementSupervisionApi.deleteLawEnforcementSupervision(id)
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
    const data = await LawEnforcementSupervisionApi.exportLawEnforcementSupervision(queryParams)
    download.excel(data, '执法监督.xls')
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
