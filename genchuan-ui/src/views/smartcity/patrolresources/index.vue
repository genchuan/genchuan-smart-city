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
      <el-form-item label="资源名称" prop="resourceName" label-width="120px">
        <el-input
          v-model="queryParams.resourceName"
          placeholder="请输入资源名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="资源编号" prop="resourceNumber">
        <el-input
          v-model="queryParams.resourceNumber"
          placeholder="请输入资源编号"
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
          v-hasPermi="['smartcity:patrol-resources:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:patrol-resources:export']"
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
      <el-table-column label="资源名称" align="center" prop="resourceName" width="200px" />
      <el-table-column label="资源编号" align="center" prop="resourceNumber" width="150px" />
      <el-table-column label="资源类型" align="center">
        <template #default="scope">
          <template v-if="scope.row.resourceType === 'building_facility'">建筑设施</template>
          <template v-else-if="scope.row.resourceType === 'municipal_facility'">市政设施</template>
          <template v-else-if="scope.row.resourceType === 'security_facility'">安防设施</template>
          <template v-else-if="scope.row.resourceType === 'fire_facility'">消防设施</template>
          <template v-else-if="scope.row.resourceType === 'electrical_equipment'">电气设备</template>
          <template v-else-if="scope.row.resourceType === 'special_equipment'">特种设备</template>
          <template v-else-if="scope.row.resourceType === 'production_equipment'">生产设备</template>
          <template v-else-if="scope.row.resourceType === 'environmental_equipment'">环保设备</template>
          <template v-else-if="scope.row.resourceType === 'power_line'">电力线路</template>
          <template v-else-if="scope.row.resourceType === 'water_pipeline'">给排水管线</template>
          <template v-else-if="scope.row.resourceType === 'gas_pipeline'">燃气管线</template>
          <template v-else-if="scope.row.resourceType === 'communication_line'">通信线路</template>
          <template v-else-if="scope.row.resourceType === 'patrol_area'">巡查区域</template>
          <template v-else-if="scope.row.resourceType === 'key_area'">重点区域</template>
          <template v-else-if="scope.row.resourceType === 'emergency_materials'">应急物资</template>
          <template v-else-if="scope.row.resourceType === 'tools_equipment'">工具器材</template>
          <template v-else>{{ scope.row.resourceType }}</template>
        </template>
      </el-table-column>
      <el-table-column label="地理位置" align="center" prop="geographicLocation" width="240px" />
      <el-table-column label="区域划分" align="center" prop="regionalDivision" />
      <el-table-column label="规格型号" align="center" prop="specificationAndModel" />
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
            v-hasPermi="['smartcity:patrol-resources:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:patrol-resources:delete']"
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
  <PatrolResourcesForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { PatrolResourcesApi, PatrolResourcesVO } from '@/api/smartcity/patrolresources'
import PatrolResourcesForm from './PatrolResourcesForm.vue'

/** 巡查资源 列表 */
defineOptions({ name: 'PatrolResources' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<PatrolResourcesVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  resourceName: undefined,
  resourceNumber: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await PatrolResourcesApi.getPatrolResourcesPage(queryParams)
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
    await PatrolResourcesApi.deletePatrolResources(id)
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
    const data = await PatrolResourcesApi.exportPatrolResources(queryParams)
    download.excel(data, '巡查资源.xls')
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
