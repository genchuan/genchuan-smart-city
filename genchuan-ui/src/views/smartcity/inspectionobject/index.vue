<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
    >
      <el-form-item label="企业名称" prop="entName" label-width="100px">
        <el-input
          v-model="queryParams.entName"
          placeholder="请输入企业名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="统一社会信用代码" prop="creditCode">
        <el-input
          v-model="queryParams.creditCode"
          placeholder="请输入统一社会信用代码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="法定代表人" prop="legalPerson">
        <el-input
          v-model="queryParams.legalPerson"
          placeholder="请输入法定代表人"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item style="margin-left: 60px">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['smartcity:inspection-object:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['smartcity:inspection-object:export']"
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
      <el-table-column label="企业名称" align="center" prop="entName" />
      <el-table-column label="统一社会信用代码" align="center" prop="creditCode" width="150px" />
      <el-table-column label="法定代表人" align="center" prop="legalPerson" />
      <el-table-column label="注册地址" align="center" prop="regAddress" />
      <el-table-column label="经营范围" align="center" prop="businessScope" />
      <el-table-column label="行业类型" align="center">
        <template #default="scope">
          <template v-if="scope.row.industryType === 'manufacturing'">制造业</template>
          <template v-else-if="scope.row.industryType === 'construction'">建筑业</template>
          <template v-else-if="scope.row.industryType === 'transportation'">交通运输业</template>
          <template v-else-if="scope.row.industryType === 'logistics'">物流业</template>
          <template v-else-if="scope.row.industryType === 'retail'">零售业</template>
          <template v-else-if="scope.row.industryType === 'finance'">金融业</template>
          <template v-else-if="scope.row.industryType === 'it_service'">IT服务业</template>
          <template v-else-if="scope.row.industryType === 'education'">教育行业</template>
          <template v-else-if="scope.row.industryType === 'medical_health'">医疗卫生业</template>
          <template v-else-if="scope.row.industryType === 'hotel_catering'">酒店餐饮业</template>
          <template v-else-if="scope.row.industryType === 'cultural_tourism'">文化旅游业</template>
          <template v-else-if="scope.row.industryType === 'energy'">能源行业</template>
          <template v-else-if="scope.row.industryType === 'environmental_protection'">环保行业</template>
          <template v-else-if="scope.row.industryType === 'agriculture'">农业</template>
          <template v-else-if="scope.row.industryType === 'real_estate'">房地产业</template>
          <template v-else-if="scope.row.industryType === 'public_service'">公共服务业</template>
          <template v-else>{{ scope.row.industryType }}</template>
        </template>
      </el-table-column>
      <el-table-column label="风险等级" align="center">
        <template #default="scope">
          <template v-if="scope.row.riskLevel === 'level1'">低风险</template>
          <template v-else-if="scope.row.riskLevel === 'level2'">中低风险</template>
          <template v-else-if="scope.row.riskLevel === 'level3'">中风险</template>
          <template v-else-if="scope.row.riskLevel === 'level4'">中高风险</template>
          <template v-else-if="scope.row.riskLevel === 'level5'">高风险</template>
          <template v-else-if="scope.row.riskLevel === 'level0'">未评定</template>
          <template v-else>{{ scope.row.riskLevel }}</template>
        </template>
      </el-table-column>
      <el-table-column label="联系人" align="center" prop="contactPerson" />
      <el-table-column label="联系电话" align="center" prop="contactPhone" />
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
            v-hasPermi="['smartcity:inspection-object:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['smartcity:inspection-object:delete']"
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
  <InspectionObjectForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { InspectionObjectApi, InspectionObjectVO } from '@/api/smartcity/inspectionobject'
import InspectionObjectForm from './InspectionObjectForm.vue'

/** 双随机行政检查 列表 */
defineOptions({ name: 'InspectionObject' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<InspectionObjectVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  entName: undefined,
  creditCode: undefined,
  legalPerson: undefined
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await InspectionObjectApi.getInspectionObjectPage(queryParams)
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
    await InspectionObjectApi.deleteInspectionObject(id)
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
    const data = await InspectionObjectApi.exportInspectionObject(queryParams)
    download.excel(data, '双随机行政检查.xls')
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
