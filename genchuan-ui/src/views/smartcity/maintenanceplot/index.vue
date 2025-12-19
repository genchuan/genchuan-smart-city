<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
    >
      <el-form-item label="地块名称" prop="plotName" label-width="120px">
        <el-input
          v-model="queryParams.plotName"
          placeholder="请输入地块名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="绿化类型" prop="greeningType">
        <el-select
          v-model="queryParams.greeningType"
          placeholder="请选择绿化类型"
          clearable
          class="!w-240px"
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
      <el-form-item style="margin-left: 50px">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:maintenance-plot:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:maintenance-plot:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="地块名称" align="center" prop="plotName" />
      <el-table-column label="地块描述" align="center" prop="plotDescription" />
      <el-table-column label="归属信息" align="center" prop="ownership" />
      <el-table-column label="地块面积" align="center" prop="acreage" />
      <!--<el-table-column label="纬度" align="center" prop="latitude" />-->
      <!--<el-table-column label="经度" align="center" prop="longitude" />-->
      <el-table-column label="绿化类型" align="center">
        <template #default="scope">
          <template v-if="scope.row.greeningType === 'pureArbor'">纯乔木绿化</template>
          <template v-else-if="scope.row.greeningType === 'pureShrub'">纯灌木绿化</template>
          <template v-else-if="scope.row.greeningType === 'pureGroundcover'">纯地被/草坪绿化</template>
          <template v-else-if="scope.row.greeningType === 'arborShrubMixed'">乔灌混合绿化</template>
          <template v-else-if="scope.row.greeningType === 'arborShrubGrassMixed'">乔灌草混合绿化</template>
          <template v-else-if="scope.row.greeningType === 'aquatic'">水生绿化</template>
        </template>
      </el-table-column>
      <el-table-column
        label="上次养护的日期"
        align="center"
        prop="lastMaintenanceDate"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column
        label="下次养护的计划日期"
        align="center"
        prop="nextMaintenanceDate"
        :formatter="dateFormatter"
        width="180px"
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
            v-hasPermi="['smartcity:maintenance-plot:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:maintenance-plot:delete']"
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
  <MaintenancePlotForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { MaintenancePlotApi, MaintenancePlotVO } from '@/api/smartcity/maintenanceplot'
import MaintenancePlotForm from './MaintenancePlotForm.vue'

/** 养护地块 列表 */
defineOptions({ name: 'MaintenancePlot' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<MaintenancePlotVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  plotName: undefined,
  greeningType: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await MaintenancePlotApi.getMaintenancePlotPage(queryParams)
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
    await MaintenancePlotApi.deleteMaintenancePlot(id)
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
    const data = await MaintenancePlotApi.exportMaintenancePlot(queryParams)
    download.excel(data, '养护地块.xls')
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
