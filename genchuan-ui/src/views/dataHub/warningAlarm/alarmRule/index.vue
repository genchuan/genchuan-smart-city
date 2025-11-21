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
      <el-form-item label="规则名称" prop="ruleName">
        <el-input
          v-model="queryParams.ruleName"
          placeholder="请输入规则名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>
      <el-form-item label="关联对象类型" prop="relatedObjectType">
        <el-select
          v-model="queryParams.relatedObjectType"
          placeholder="请选择关联对象类型"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="预警等级" prop="earlyWarningLevel">
        <el-input
          v-model="queryParams.earlyWarningLevel"
          placeholder="请输入预警等级(1-5)"
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
      <el-form-item>
        <el-button @click="handleQuery">
          <Icon icon="ep:search" class="mr-5px" />
          搜索
        </el-button>
        <el-button @click="resetQuery">
          <Icon icon="ep:refresh" class="mr-5px" />
          重置
        </el-button>
        <el-button type="primary" plain @click="openForm('create')">
          <Icon icon="ep:plus" class="mr-5px" />
          新增
        </el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading">
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
      <el-table-column label="规则ID" align="center" prop="id" width="80" />
      <el-table-column label="规则名称" align="center" prop="ruleName" min-width="120" />
      <el-table-column
        label="关联对象类型"
        align="center"
        prop="relatedObjectType"
        min-width="120"
      />
      <el-table-column
        label="预警指标编码"
        align="center"
        prop="warningIndicatorCode"
        min-width="120"
      />
      <el-table-column label="预警等级" align="center" prop="earlyWarningLevel" width="80">
        <template #default="scope">
          <span :class="getLevelClass(scope.row.earlyWarningLevel)">{{
            scope.row.earlyWarningLevel
          }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="触发频率(次/小时)"
        align="center"
        prop="triggerFrequency"
        width="120"
      />
      <el-table-column label="启用状态" align="center" prop="enableStatus" width="100">
        <template #default="scope">
          <el-tag :type="scope.row.enableStatus === 1 ? 'success' : 'danger'">
            {{ scope.row.enableStatus === 1 ? '启用' : '禁用' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="180px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row.id)"> 详情 </el-button>
          <el-button link type="primary" @click="openForm('update', scope.row.id)">
            编辑
          </el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)"> 删除 </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <Pagination
      :total="total"
      v-model:page="queryParams.pageNo"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
      class="mt-4"
    />
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <AlarmRuleForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <AlarmRuleDetailDrawer ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { AlarmRuleApi, AlarmRuleVO } from '@/api/dataHub/warningAlarm/warnAlarmRuleMgr'
import AlarmRuleForm from './AlarmRuleForm.vue'
import AlarmRuleDetailDrawer from './AlarmRuleDetailDrawer.vue'

/** 预警告警规则配置 列表 */
defineOptions({ name: 'AlarmRule' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<AlarmRuleVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  ruleName: undefined,
  relatedObjectType: undefined,
  earlyWarningLevel: undefined,
  enableStatus: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉引用
const detailRef = ref()

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await AlarmRuleApi.getAlarmRulePage(queryParams)
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
const openDetail = (id: number) => {
  detailRef.value.open(id)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await AlarmRuleApi.deleteAlarmRule(id)
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
    const data = await AlarmRuleApi.exportAlarmRule(queryParams)
    download.excel(data, '预警告警规则配置.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 获取预警等级样式 */
const getLevelClass = (level: number) => {
  const classes = [
    'text-pink-500',
    'text-red-500',
    'text-orange-500',
    'text-blue-500',
    'text-green-500'
  ]
  return level && level >= 1 && level <= 5 ? classes[level - 1] : ''
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
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

::v-deep .el-table__header-wrapper th {
  background-color: #f9fafb;
  font-weight: 500;
}

::v-deep .el-table__body tr:hover > td {
  background-color: #f7f8fa !important;
}
</style>
