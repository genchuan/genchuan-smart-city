<template>
  <ContentWrap>
    <!-- 搜索工作栏：优化排版，避免文字换行和错位 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      label-width="120px"
    >
    <!-- 使用flex布局+响应式样式控制排列 -->
    <div class="search-form-container">
      <el-form-item label="扩展ID" prop="mngMatterExtId" class="search-item">
        <el-input
          v-model="queryParams.mngMatterExtId"
          placeholder="请输入扩展ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item label="归属大类ID" prop="matterMajorId" class="search-item">
        <el-input
          v-model="queryParams.matterMajorId"
          placeholder="请输入归属大类ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item label="归属大类名称" prop="matterMajorName" class="search-item">
        <el-input
          v-model="queryParams.matterMajorName"
          placeholder="请输入归属大类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item label="扩展小类代码" prop="extMinorCode" class="search-item">
        <el-input
          v-model="queryParams.extMinorCode"
          placeholder="请输入扩展小类代码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item label="扩展小类名称" prop="extMinorName" class="search-item">
        <el-input
          v-model="queryParams.extMinorName"
          placeholder="请输入扩展小类名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item label="扩展小类说明" prop="extMinorDesc" class="search-item">
        <el-input
          v-model="queryParams.extMinorDesc"
          placeholder="请输入扩展小类说明"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item label="建议主管部门代码" prop="suggestDeptCode" class="search-item">
        <el-input
          v-model="queryParams.suggestDeptCode"
          placeholder="请输入建议主管部门代码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item label="建议主管部门名称" prop="suggestDeptName" class="search-item">
        <el-input
          v-model="queryParams.suggestDeptName"
          placeholder="请输入建议主管部门名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item label="系统创建时间" prop="createTimeSys" class="search-item">
        <el-date-picker
          v-model="queryParams.createTimeSys"
          value-format="YYYY-MM-DD"
          type="date"
          placeholder="选择系统创建时间"
          clearable
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item label="系统更新时间" prop="updateTimeSys" class="search-item">
        <el-date-picker
          v-model="queryParams.updateTimeSys"
          value-format="YYYY-MM-DD"
          type="date"
          placeholder="选择系统更新时间"
          clearable
          class="!w-240px"
        />
      </el-form-item>

      <!-- 操作按钮单独一行，避免与搜索项混杂导致错位 -->
      <el-form-item class="search-actions">
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['datacenter:biz-mng-matter-ext:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['datacenter:biz-mng-matter-ext:export']"
        >
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </div>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :stripe="true" :show-overflow-tooltip="true">
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="扩展ID" align="center" prop="mngMatterExtId" />
      <el-table-column label="归属大类ID" align="center" prop="matterMajorId" />
      <el-table-column label="归属大类名称" align="center" prop="matterMajorName" />
      <el-table-column label="扩展小类代码" align="center" prop="extMinorCode" />
      <el-table-column label="扩展小类名称" align="center" prop="extMinorName" />
      <el-table-column label="扩展小类说明" align="center" prop="extMinorDesc" />
      <el-table-column label="建议主管部门代码" align="center" prop="suggestDeptCode" />
      <el-table-column label="建议主管部门名称" align="center" prop="suggestDeptName" />
      <el-table-column
        label="系统创建时间"
        align="center"
        prop="createTimeSys"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column
        label="系统更新时间"
        align="center"
        prop="updateTimeSys"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="120px">
        <template #default="scope">
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['datacenter:biz-mng-matter-ext:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['datacenter:biz-mng-matter-ext:delete']"
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
  <BizMngMatterExtForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
// 脚本部分保持不变
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { BizMngMatterExtApi, BizMngMatterExtVO } from '@/api/dataHub/managedComponent/bizmngmatterext'
import BizMngMatterExtForm from './BizMngMatterExtForm.vue'

/** 管理事项扩展管理事项配置 列表 */
defineOptions({ name: 'BizMngMatterExt' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<BizMngMatterExtVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  mngMatterExtId: undefined,
  matterMajorId: undefined,
  matterMajorName: undefined,
  extMinorCode: undefined,
  extMinorName: undefined,
  extMinorDesc: undefined,
  suggestDeptCode: undefined,
  suggestDeptName: undefined,
  createTimeSys: [],
  updateTimeSys: [],
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await BizMngMatterExtApi.getBizMngMatterExtPage(queryParams)
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
    await BizMngMatterExtApi.deleteBizMngMatterExt(id)
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
    const data = await BizMngMatterExtApi.exportBizMngMatterExt(queryParams)
    download.excel(data, '管理事项扩展管理事项配置.xls')
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
/* 搜索栏布局样式：解决换行和错位问题 */
.search-form-container {
  display: flex;
  flex-wrap: wrap;  /* 超出宽度自动换行 */
  gap: 16px;  /* 表单项之间的间距，避免拥挤 */
  align-items: center;  /* 垂直方向对齐，避免错位 */
}

/* 单个搜索项样式 */
.search-item {
  margin-bottom: 10px;  /* 垂直方向间距 */
  white-space: nowrap;  /* 强制标签和输入框在同一行 */
}

/* 操作按钮区域样式 */
.search-actions {
  width: 100%;  /* 按钮区域占满一行 */
  padding-top: 10px;
  border-top: 1px dashed #eee;  /* 分隔线，视觉上区分搜索项和按钮 */
  margin-top: 5px;
}

/* 响应式调整：小屏幕下优化显示 */
@media (max-width: 1200px) {
  .search-form-container {
    gap: 12px;
  }
}

@media (max-width: 768px) {
  .search-item {
    width: 100%;  /* 小屏幕下单项占满一行，避免挤压 */
  }
}
</style>
