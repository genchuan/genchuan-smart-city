<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
    >
      <el-form-item label="执法公示编号" prop="publicityNumber" label-width="140px">
        <el-input
          v-model="queryParams.publicityNumber"
          placeholder="请输入执法公示编号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="公示标题" prop="publicityTitle">
        <el-input
          v-model="queryParams.publicityTitle"
          placeholder="请输入公示标题"
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
      <el-form-item style="margin-left: 50px">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:enforcement-publicity:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:enforcement-publicity:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="主键" align="center" prop="id" min-width="30px" />
      <el-table-column label="执法公示编号" align="center" prop="publicityNumber" />
      <el-table-column label="公示标题" align="center" prop="publicityTitle" />
      <el-table-column label="公示内容" align="center" prop="publicityContent" />
      <el-table-column label="公示类型" align="center">
        <template #default="scope">
          <template v-if="scope.row.publicityType === 'administrative_penalty'">行政处罚公示</template>
          <template v-else-if="scope.row.publicityType === 'administrative_permit'">行政许可公示</template>
          <template v-else-if="scope.row.publicityType === 'administrative_enforcement'">行政强制公示</template>
          <template v-else-if="scope.row.publicityType === 'law_enforcement_basis'">执法依据公示</template>
          <template v-else-if="scope.row.publicityType === 'law_enforcement_process'">执法流程公示</template>
          <template v-else-if="scope.row.publicityType === 'law_enforcement_supervision'">执法监督公示</template>
          <template v-else>{{ scope.row.publicityType }}</template>
        </template>
      </el-table-column>
      <el-table-column label="执法部门" align="center" prop="enforcement" />
      <el-table-column label="执法地点" align="center" prop="enforcementLocation" />
      <el-table-column label="执法依据" align="center" prop="enforcementBasis" />
      <el-table-column label="执法结果" align="center" prop="enforcementResults" />
      <el-table-column
        label="公示开始时间"
        align="center"
        prop="announcementStartTime"
        :formatter="dateFormatter2"
      />
      <el-table-column
        label="公示截止时间"
        align="center"
        prop="announcementDeadline"
        :formatter="dateFormatter2"
      />
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
            v-hasPermi="['smartcity:enforcement-publicity:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:enforcement-publicity:delete']"
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
  <EnforcementPublicityForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter,dateFormatter2 } from '@/utils/formatTime'
import download from '@/utils/download'
import { EnforcementPublicityApi, EnforcementPublicityVO } from '@/api/smartcity/enforcementpublicity'
import EnforcementPublicityForm from './EnforcementPublicityForm.vue'

/** 执法公示 列表 */
defineOptions({ name: 'EnforcementPublicity' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EnforcementPublicityVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  publicityNumber: undefined,
  publicityTitle: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EnforcementPublicityApi.getEnforcementPublicityPage(queryParams)
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
    await EnforcementPublicityApi.deleteEnforcementPublicity(id)
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
    const data = await EnforcementPublicityApi.exportEnforcementPublicity(queryParams)
    download.excel(data, '执法公示.xls')
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
