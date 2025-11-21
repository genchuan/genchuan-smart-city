<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <div class="search-container">
      <el-form
        class="search-form"
        :model="queryParams"
        ref="queryFormRef"
        :inline="true"
        label-width="80px"
      >
        <!-- 第一行查询项 -->
        <div class="form-row">
          <el-form-item label="管理事项小类ID" prop="mngMatterMinorId">
            <el-input
              v-model="queryParams.mngMatterMinorId"
              placeholder="请输入管理事项小类ID"
              clearable
              @keyup.enter="handleQuery"
              class="input-control"
            />
          </el-form-item>
          <el-form-item label="所属大类ID" prop="parentMajorId">
            <el-input
              v-model="queryParams.parentMajorId"
              placeholder="请输入所属大类ID"
              clearable
              @keyup.enter="handleQuery"
              class="input-control"
            />
          </el-form-item>
          <el-form-item label="所属大类名称" prop="parentMajorName">
            <el-input
              v-model="queryParams.parentMajorName"
              placeholder="请输入所属大类名称"
              clearable
              @keyup.enter="handleQuery"
              class="input-control"
            />
          </el-form-item>
          <el-form-item label="小类代码" prop="matterMinorCode">
            <el-input
              v-model="queryParams.matterMinorCode"
              placeholder="请输入小类代码"
              clearable
              @keyup.enter="handleQuery"
              class="input-control"
            />
          </el-form-item>
        </div>

        <!-- 第二行查询项 -->
        <div class="form-row">
          <el-form-item label="小类名称" prop="matterMinorName">
            <el-input
              v-model="queryParams.matterMinorName"
              placeholder="请输入小类名称"
              clearable
              @keyup.enter="handleQuery"
              class="input-control"
            />
          </el-form-item>
          <el-form-item label="小类说明" prop="matterMinorDesc">
            <el-input
              v-model="queryParams.matterMinorDesc"
              placeholder="请输入小类说明"
              clearable
              @keyup.enter="handleQuery"
              class="input-control"
            />
          </el-form-item>
          <el-form-item label="主管部门代码" prop="deptCode">
            <el-input
              v-model="queryParams.deptCode"
              placeholder="请输入主管部门代码"
              clearable
              @keyup.enter="handleQuery"
              class="input-control"
            />
          </el-form-item>
          <el-form-item label="主管部门名称" prop="deptName">
            <el-input
              v-model="queryParams.deptName"
              placeholder="请输入主管部门名称"
              clearable
              @keyup.enter="handleQuery"
              class="input-control"
            />
          </el-form-item>
        </div>

        <!-- 第三行查询项 -->
        <div class="form-row">
          <el-form-item label="是否扩展类" prop="isExt">
            <el-input
              v-model="queryParams.isExt"
              placeholder="请输入是否扩展类"
              clearable
              @keyup.enter="handleQuery"
              class="input-control"
            />
          </el-form-item>
          <el-form-item label="启用状态" prop="enableStatus">
            <el-select
              v-model="queryParams.enableStatus"
              placeholder="请选择启用状态"
              clearable
              class="input-control"
            >
              <el-option label="请选择字典生成" value="" />
            </el-select>
          </el-form-item>
          <el-form-item label="创建人" prop="createUser">
            <el-input
              v-model="queryParams.createUser"
              placeholder="请输入创建人"
              clearable
              @keyup.enter="handleQuery"
              class="input-control"
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
              class="date-control"
            />
          </el-form-item>
        </div>

        <!-- 第四行查询项 -->
        <div class="form-row">
          <el-form-item label="更新人" prop="updateUser">
            <el-input
              v-model="queryParams.updateUser"
              placeholder="请输入更新人"
              clearable
              @keyup.enter="handleQuery"
              class="input-control"
            />
          </el-form-item>
          <el-form-item label="系统创建时间" prop="createTimeSys">
            <el-date-picker
              v-model="queryParams.createTimeSys"
              value-format="YYYY-MM-DD"
              type="date"
              placeholder="选择系统创建时间"
              clearable
              class="input-control"
            />
          </el-form-item>
          <el-form-item label="系统更新时间" prop="updateTimeSys">
            <el-date-picker
              v-model="queryParams.updateTimeSys"
              value-format="YYYY-MM-DD"
              type="date"
              placeholder="选择系统更新时间"
              clearable
              class="input-control"
            />
          </el-form-item>
        </div>

        <!-- 按钮区域 -->
        <div class="form-actions">
          <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
          <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
          <el-button
            type="primary"
            plain
            @click="openForm('create')"
            v-hasPermi="['datacenter:biz-mng-matter-minor:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="success"
            plain
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:biz-mng-matter-minor:export']"
          >
            <Icon icon="ep:download" class="mr-5px" /> 导出
          </el-button>
        </div>
      </el-form>
    </div>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true" class="table-container">
      <el-table-column label="主键ID" align="center" prop="id" width="80" />
      <el-table-column label="管理事项小类ID" align="center" prop="mngMatterMinorId" width="140" />
      <el-table-column label="所属大类ID" align="center" prop="parentMajorId" width="120" />
      <el-table-column label="所属大类名称" align="center" prop="parentMajorName" width="160" />
      <el-table-column label="小类代码" align="center" prop="matterMinorCode" width="120" />
      <el-table-column label="小类名称" align="center" prop="matterMinorName" width="160" />
      <el-table-column label="小类说明" align="center" prop="matterMinorDesc" width="200" />
      <el-table-column label="主管部门代码" align="center" prop="deptCode" width="140" />
      <el-table-column label="主管部门名称" align="center" prop="deptName" width="160" />
      <el-table-column label="是否扩展类" align="center" prop="isExt" width="100" />
      <el-table-column label="启用状态" align="center" prop="enableStatus" width="100" />
      <el-table-column label="创建人" align="center" prop="createUser" width="120" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        :formatter="dateFormatter"
        width="180"
      />
      <el-table-column label="更新人" align="center" prop="updateUser" width="120" />
      <el-table-column
        label="系统创建时间"
        align="center"
        prop="createTimeSys"
        :formatter="dateFormatter"
        width="180"
      />
      <el-table-column
        label="系统更新时间"
        align="center"
        prop="updateTimeSys"
        :formatter="dateFormatter"
        width="180"
      />
      <el-table-column label="操作" align="center" min-width="120">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['datacenter:biz-mng-matter-minor:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['datacenter:biz-mng-matter-minor:delete']"
          >
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination-wrapper">
      <Pagination
        :total="total"
        v-model:page="queryParams.pageNo"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
      />
    </div>
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <BizMngMatterMinorForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
// 脚本部分保持不变
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { BizMngMatterMinorApi, BizMngMatterMinorVO } from '@/api/dataHub/managedComponent/bizmngmatterminor'
import BizMngMatterMinorForm from './BizMngMatterMinorForm.vue'

/** 管理事项小类 列表 */
defineOptions({ name: 'BizMngMatterMinor' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<BizMngMatterMinorVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  mngMatterMinorId: undefined,
  parentMajorId: undefined,
  parentMajorName: undefined,
  matterMinorCode: undefined,
  matterMinorName: undefined,
  matterMinorDesc: undefined,
  deptCode: undefined,
  deptName: undefined,
  isExt: undefined,
  enableStatus: undefined,
  createUser: undefined,
  createTime: [],
  updateUser: undefined,
  createTimeSys: undefined,
  updateTimeSys: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await BizMngMatterMinorApi.getBizMngMatterMinorPage(queryParams)
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
    await BizMngMatterMinorApi.deleteBizMngMatterMinor(id)
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
    const data = await BizMngMatterMinorApi.exportBizMngMatterMinor(queryParams)
    download.excel(data, '管理事项小类.xls')
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
/* 搜索区域容器 */
.search-container {
  background: #f8f9fa;
  border-radius: 8px;
  padding: 16px;
  margin-bottom: 16px;
}

/* 搜索表单样式 */
.search-form {
  width: 100%;
}

/* 行容器 - 控制每行查询项 */
.form-row {
  display: flex;
  flex-wrap: wrap;
  gap: 15px 20px;
  margin-bottom: 15px;
  align-items: center;
}

.form-row:last-child {
  margin-bottom: 0;
}

/* 输入控件样式 */
.input-control {
  width: 240px !important;
}

/* 日期范围选择器样式 */
.date-control {
  width: 340px !important;
}

/* 按钮区域样式 */
.form-actions {
  display: flex;
  gap: 10px;
  padding-top: 15px;
  margin-top: 10px;
  border-top: 1px solid #e8e8e8;
  flex-wrap: wrap;
}

/* 表格容器 */
.table-container {
  width: 100%;
  border-radius: 8px;
  border: 1px solid #ebeef5;
}

/* 分页区域 */
.pagination-wrapper {
  display: flex;
  justify-content: flex-end;
  margin-top: 16px;
  padding: 10px 0;
}

/* 调整Element组件样式 */
:deep(.el-form-item) {
  margin-bottom: 0;
  display: flex;
  align-items: center;
}

:deep(.el-form-item__label) {
  font-weight: 500;
  color: #606266;
  padding-right: 10px;
  line-height: 1;
  white-space: nowrap;
}

:deep(.el-form-item__content) {
  line-height: 1;
}

/* 响应式调整 */
@media (max-width: 1400px) {
  .input-control {
    width: 220px !important;
  }
  .date-control {
    width: 320px !important;
  }
}

@media (max-width: 1200px) {
  .form-row {
    gap: 12px 15px;
  }
  .input-control {
    width: 200px !important;
  }
  .date-control {
    width: 300px !important;
  }
}

@media (max-width: 992px) {
  .form-row {
    gap: 10px;
  }
  .input-control {
    width: 180px !important;
  }
  .date-control {
    width: 280px !important;
  }
}

@media (max-width: 768px) {
  .form-row {
    flex-direction: column;
    align-items: flex-start;
    gap: 10px;
  }
  .input-control, .date-control {
    width: 100% !important;
  }
  .form-actions {
    justify-content: flex-start;
  }
}
</style>
