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
      <el-form-item label="对接编号" prop="resultDockCode">
        <el-input
          v-model="queryParams.resultDockCode"
          placeholder="请输入对接编号"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px rounded-md"
        />
      </el-form-item>
      <el-form-item label="外部系统名称" prop="externalSystemName">
        <el-input
          v-model="queryParams.externalSystemName"
          placeholder="请输入外部系统名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-200px rounded-md"
        />
      </el-form-item>
      <el-form-item label="对接状态" prop="dockStatus">
        <el-select
          v-model="queryParams.dockStatus"
          placeholder="请选择对接状态"
          clearable
          class="!w-200px rounded-md"
        >
          <el-option label="成功" value="SUCCESS" />
          <el-option label="失败" value="FAILED" />
          <el-option label="对接中" value="PENDING" />
        </el-select>
      </el-form-item>
      <el-form-item label="对接时间" prop="dockTime">
        <el-date-picker
          v-model="queryParams.dockTime"
          value-format="YYYY-MM-DD HH:mm:ss"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-300px rounded-md"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleQuery" class="mr-2">
          <Icon icon="ep:search" class="mr-5px" />
          搜索
        </el-button>
        <el-button @click="resetQuery" class="mr-2">
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
  <ContentWrap class="mt-4">
    <el-card shadow="hover" class="rounded-lg">
      <el-table
        v-loading="loading"
        :data="list"
        :stripe="true"
        :show-overflow-tooltip="true"
        class="table-beautify"
        border
      >
        <el-table-column label="主键ID" align="center" prop="id" width="80" />
        <el-table-column label="对接编号" align="center" prop="resultDockCode" width="150" />
        <el-table-column
          label="外部系统名称"
          align="center"
          prop="externalSystemName"
          width="180"
        />
        <el-table-column label="对接方式" align="center" prop="dockMethod" width="120" />
        <el-table-column label="对接频率" align="center" prop="dockFrequency" width="120" />
        <el-table-column label="对接状态" align="center" prop="dockStatus" width="120">
          <template #default="scope">
            <el-tag
              :type="
                scope.row.dockStatus === 'SUCCESS'
                  ? 'success'
                  : scope.row.dockStatus === 'PENDING'
                    ? 'warning'
                    : 'danger'
              "
            >
              {{
                scope.row.dockStatus === 'SUCCESS'
                  ? '成功'
                  : scope.row.dockStatus === 'PENDING'
                    ? '对接中'
                    : '失败'
              }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          label="对接时间"
          align="center"
          prop="dockTime"
          :formatter="dateFormatter"
          width="180px"
        />
        <el-table-column label="操作" align="center" min-width="180px">
          <template #default="scope">
            <el-button link type="primary" @click="openDetail(scope.row)">
              <Icon icon="ep:detail" class="mr-2" />
              详情
            </el-button>
            <el-button link type="primary" @click="openForm('update', scope.row.id)">
              <Icon icon="ep:edit" class="mr-2" />
              编辑
            </el-button>
            <el-button link type="danger" @click="handleDelete(scope.row.id)">
              <Icon icon="ep:delete" class="mr-2" />
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <div class="mt-4">
        <Pagination
          :total="total"
          v-model:page="queryParams.pageNo"
          v-model:limit="queryParams.pageSize"
          @pagination="getList"
        />
      </div>
    </el-card>
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <EvalResultSystemDockForm ref="formRef" @success="getList" />
  <!-- 详情抽屉 -->
  <EvalResultSystemDockDetailDrawer ref="detailRef" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  EvalResultSystemDockApi,
  EvalResultSystemDockVO
} from '@/api/dataHub/comprehensiveEval/evalResultApply/evalResultSysInt'
import EvalResultSystemDockForm from './EvalResultSystemDockForm.vue'
import EvalResultSystemDockDetailDrawer from './EvalResultSystemDockDetailDrawer.vue'

/** 结果系统对接 列表 */
defineOptions({ name: 'EvalResultSystemDock' })
const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化
const loading = ref(true) // 列表的加载中
const list = ref<EvalResultSystemDockVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  resultDockCode: undefined,
  externalSystemName: undefined,
  dockStatus: undefined,
  dockTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 组件Ref */
const formRef = ref()
const detailRef = ref()

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EvalResultSystemDockApi.getEvalResultSystemDockPage(queryParams)
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
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 详情操作 */
const openDetail = (row: EvalResultSystemDockVO) => {
  detailRef.value.open(row)
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await EvalResultSystemDockApi.deleteEvalResultSystemDock(id)
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
    const data = await EvalResultSystemDockApi.exportEvalResultSystemDock(queryParams)
    download.excel(data, '结果系统对接.xls')
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
.table-beautify {
  --el-table-row-hover-bg-color: #f0f9ff;
  --el-table-header-text-color: #333;
  --el-table-header-bg-color: #f8f9fa;
  --el-table-border-color: #e4e7ed;
}

.el-card {
  border: none;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
}

.el-form-item {
  margin-bottom: 15px;
}
</style>
