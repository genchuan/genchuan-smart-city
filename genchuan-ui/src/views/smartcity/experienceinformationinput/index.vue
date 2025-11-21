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
      <el-form-item label="经验主题" prop="experienceTheme" label-width="120px">
        <el-input
          v-model="queryParams.experienceTheme"
          placeholder="请输入经验主题"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="所属领域" prop="isArea">
        <el-input
          v-model="queryParams.isArea"
          placeholder="请输入所属领域"
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
      <el-form-item style="margin-left: 50px;">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:experience-information-input:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:experience-information-input:export']"
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
      <el-table-column label="经验主题" align="center" prop="experienceTheme" width="180px"/>
      <el-table-column label="所属领域" align="center" prop="isArea" >
        <template #default="scope">
          <span v-for="(item,key) in isAreaOptions" :key="key">
            <span v-if="scope.row.isArea==item.value">
              {{item.label}}
            </span>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="经验提供方" align="center" prop="experienceProvider" />
      <el-table-column
        label="实施时间"
        align="center"
        prop="implementationTime"
        :formatter="dateFormatter2"
      />
      <el-table-column label="实施地点" align="center" prop="implementationLocation" />
      <el-table-column label="详细步骤" align="center" prop="detailSteps" />
      <el-table-column label="取得成效" align="center" prop="achieveResults" />
      <el-table-column label="可借鉴要点" align="center" prop="keyPointsForReference" />
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
            v-hasPermi="['smartcity:experience-information-input:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:experience-information-input:delete']"
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
  <ExperienceInformationInputForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter,dateFormatter2 } from '@/utils/formatTime'
import download from '@/utils/download'
import { ExperienceInformationInputApi, ExperienceInformationInputVO } from '@/api/smartcity/experienceinformationinput'
import ExperienceInformationInputForm from './ExperienceInformationInputForm.vue'
import { ClassificationOfExperienceInformationApi } from '@/api/smartcity/classificationofexperienceinformation'

/** 经验信息录入 列表 */
defineOptions({ name: 'ExperienceInformationInput' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<ExperienceInformationInputVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  experienceTheme: undefined,
  isArea: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await ExperienceInformationInputApi.getExperienceInformationInputPage(queryParams)
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
    await ExperienceInformationInputApi.deleteExperienceInformationInput(id)
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
    const data = await ExperienceInformationInputApi.exportExperienceInformationInput(queryParams)
    download.excel(data, '经验信息录入.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}


let isAreaOptions = ref([]);//法规分类

const initData = async () => {
  let queryParams={
    pageNo:1,
    pageSize:100
  }
  const data = await ClassificationOfExperienceInformationApi.getClassificationOfExperienceInformationPage(queryParams);
  isAreaOptions.value = data.list.map(item => ({
    label: item.sector,
    value:item.id
  }));
}

/** 初始化 */
onMounted(() => {
  getList()
  initData();
})
</script>
