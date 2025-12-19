<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
    >
      <el-form-item label="任务ID" prop="taskId" label-width="100px">
        <el-input
          v-model="queryParams.taskId"
          placeholder="请输入任务ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="任务名称" prop="taskName">
        <el-input
          v-model="queryParams.taskName"
          placeholder="请输入任务名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="所属行业" prop="sector">
        <el-select
          v-model="queryParams.sector"
          placeholder="请选择所属行业"
          clearable
          class="!w-240px"
          @change="handleQuery"
        >
          <el-option value="manufacturing" label="制造业" />
          <el-option value="construction" label="建筑业" />
          <el-option value="transportation" label="交通运输业" />
          <el-option value="logistics" label="物流业" />
          <el-option value="retail" label="零售业" />
          <el-option value="finance" label="金融业" />
          <el-option value="it_service" label="IT服务业" />
          <el-option value="education" label="教育行业" />
          <el-option value="medical_health" label="医疗卫生业" />
          <el-option value="hotel_catering" label="酒店餐饮业" />
          <el-option value="cultural_tourism" label="文化旅游业" />
          <el-option value="energy" label="能源行业" />
          <el-option value="environmental_protection" label="环保行业" />
          <el-option value="agriculture" label="农业" />
          <el-option value="real_estate" label="房地产业" />
          <el-option value="public_service" label="公共服务业" />
        </el-select>
      </el-form-item>
      <el-form-item style="margin-left: 60px">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:inspection-task:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:inspection-task:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="ID" align="center" prop="id" min-width="50px" />
      <el-table-column label="任务ID" align="center" prop="taskId" width="150px" />
      <el-table-column label="任务名称" align="center" prop="taskName" width="200px" />
      <el-table-column label="发起部门" align="center" prop="initiatingDepartment" />
      <el-table-column
        label="计划执行时间"
        align="center"
        prop="plannedExecutionTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column
        label="截止时间"
        align="center"
        prop="deadline"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="所属行业" align="center">
        <template #default="scope">
          <template v-if="scope.row.sector === 'manufacturing'">制造业</template>
          <template v-else-if="scope.row.sector === 'construction'">建筑业</template>
          <template v-else-if="scope.row.sector === 'transportation'">交通运输业</template>
          <template v-else-if="scope.row.sector === 'logistics'">物流业</template>
          <template v-else-if="scope.row.sector === 'retail'">零售业</template>
          <template v-else-if="scope.row.sector === 'finance'">金融业</template>
          <template v-else-if="scope.row.sector === 'it_service'">IT服务业</template>
          <template v-else-if="scope.row.sector === 'education'">教育行业</template>
          <template v-else-if="scope.row.sector === 'medical_health'">医疗卫生业</template>
          <template v-else-if="scope.row.sector === 'hotel_catering'">酒店餐饮业</template>
          <template v-else-if="scope.row.sector === 'cultural_tourism'">文化旅游业</template>
          <template v-else-if="scope.row.sector === 'energy'">能源行业</template>
          <template v-else-if="scope.row.sector === 'environmental_protection'">环保行业</template>
          <template v-else-if="scope.row.sector === 'agriculture'">农业</template>
          <template v-else-if="scope.row.sector === 'real_estate'">房地产业</template>
          <template v-else-if="scope.row.sector === 'public_service'">公共服务业</template>
          <template v-else>{{ scope.row.sector }}</template>
        </template>
      </el-table-column>
      <el-table-column label="检查对象数量" align="center" prop="numberObjects" width="120px" />
      <el-table-column label="执法人员数量" align="center" prop="numberPersonnel" width="120px" />
      <el-table-column label="检查事项" align="center" prop="inspectionItems" />
      <el-table-column label="完成情况" align="center">
        <template #default="scope">
          <template v-if="scope.row.completionStatus === 'not_started'">未开始</template>
          <template v-else-if="scope.row.completionStatus === 'in_progress'">进行中</template>
          <template v-else-if="scope.row.completionStatus === 'completed'">已完成</template>
          <template v-else-if="scope.row.completionStatus === 'overdue'">已逾期</template>
          <template v-else>{{ scope.row.completionStatus }}</template>
        </template>
      </el-table-column>
      <el-table-column label="负责人" align="center" prop="personInCharge" />
      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['smartcity:inspection-task:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:inspection-task:delete']"
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
  <InspectionTaskForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { InspectionTaskApi, InspectionTaskVO } from '@/api/smartcity/inspectiontask'
import InspectionTaskForm from './InspectionTaskForm.vue'

/** 检查任务管理 列表 */
defineOptions({ name: 'InspectionTask' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectionTaskVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  taskName: undefined,
  taskId: undefined,
  sector: undefined
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectionTaskApi.getInspectionTaskPage(queryParams)
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
    await InspectionTaskApi.deleteInspectionTask(id)
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
    const data = await InspectionTaskApi.exportInspectionTask(queryParams)
    download.excel(data, '检查任务管理.xls')
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
