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
      <el-form-item label="属性规则ID" prop="attrRuleId">
        <el-input
          v-model="queryParams.attrRuleId"
          placeholder="请输入属性规则ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="设备分类名称" prop="deviceCatName">
        <el-input
          v-model="queryParams.deviceCatName"
          placeholder="请输入设备分类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="属性名称" prop="attrName">
        <el-input
          v-model="queryParams.attrName"
          placeholder="请输入属性名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="数据类型" prop="dataType">
        <el-input
          v-model="queryParams.dataType"
          placeholder="请输入数据类型"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="启用状态" prop="enableStatus">
        <el-select
          v-model="queryParams.enableStatus"
          placeholder="请选择启用状态"
          clearable
          class="!w-240px"
        >
          <el-option label="启用" value="1" />
          <el-option label="禁用" value="0" />
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
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['datacenter:device-attr-rule:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" />
          新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['datacenter:device-attr-rule:export']"
        >
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
      <el-table-column label="属性规则ID" align="center" prop="attrRuleId" />
      <el-table-column label="设备分类名称" align="center" prop="deviceCatName" />
      <el-table-column label="属性名称" align="center" prop="attrName" />
      <el-table-column label="属性代码" align="center" prop="attrCode" />
      <el-table-column label="数据类型" align="center" prop="dataType" />
      <el-table-column label="是否必选" align="center" prop="isRequired">
        <template #default="scope">
          <span
            class="required-tag"
            :class="scope.row.isRequired === '1' ? 'required' : 'not-required'"
          >
            {{ scope.row.isRequired === '1' ? '是' : '否' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="启用状态" align="center" prop="enableStatus">
        <template #default="scope">
          <span class="status-tag" :class="scope.row.enableStatus === '1' ? 'enabled' : 'disabled'">
            {{ scope.row.enableStatus === '1' ? '启用' : '禁用' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTimeSys"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row.id)"> 详情 </el-button>
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['datacenter:device-attr-rule:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['datacenter:device-attr-rule:delete']"
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
  <DeviceAttrRuleForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DeviceAttrRuleDetail ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DeviceAttrRuleApi,
  DeviceAttrRuleVO
} from '@/api/dataHub/deviceManage/deviceRule/deviceattrrule'
import DeviceAttrRuleForm from './DeviceAttrRuleForm.vue'
import DeviceAttrRuleDetail from './DeviceAttrRuleDetail.vue'

/** 设备属性规则配置 列表 */
defineOptions({ name: 'DeviceAttrRule' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DeviceAttrRuleVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  attrRuleId: undefined,
  deviceCatId: undefined,
  deviceCatName: undefined,
  attrName: undefined,
  attrCode: undefined,
  dataType: undefined,
  enableStatus: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DeviceAttrRuleApi.getDeviceAttrRulePage(queryParams)
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

/** 详情操作 */
const detailRef = ref()
const openDetail = (id: number) => {
  detailRef.value.open(id)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await DeviceAttrRuleApi.deleteDeviceAttrRule(id)
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
    const data = await DeviceAttrRuleApi.exportDeviceAttrRule(queryParams)
    download.excel(data, '设备属性规则配置.xls')
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

.status-tag {
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.status-tag.enabled {
  background-color: #e6f7ee;
  color: #00b42a;
}

.status-tag.disabled {
  background-color: #fff2f0;
  color: #f53f3f;
}

.required-tag {
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.required-tag.required {
  background-color: #e8f3ff;
  color: #1890ff;
}

.required-tag.not-required {
  background-color: #f2f3f5;
  color: #86909c;
}
</style>
