<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-card class="search-card" shadow="never">
      <template #header>
        <div class="card-header">
          <span class="card-title">查询条件</span>
          <el-button
            type="text"
            @click="toggleSearchForm"
            class="toggle-btn"
          >
            {{ showFullSearch ? '简化搜索' : '展开搜索' }}
            <Icon :icon="showFullSearch ? 'ep:arrow-up' : 'ep:arrow-down'" class="ml-2" />
          </el-button>
        </div>
      </template>
      <el-form
        class="search-form"
        :model="queryParams"
        ref="queryFormRef"
        :inline="true"
        label-width="140px"
      >
        <!-- 基础信息 -->
        <div class="form-section">
          <div class="section-title">基础信息</div>
          <div class="form-row">
            <el-form-item label="队伍ID" prop="teamId">
              <el-input
                v-model="queryParams.teamId"
                placeholder="请输入队伍ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="队伍编码" prop="teamCode">
              <el-input
                v-model="queryParams.teamCode"
                placeholder="请输入队伍编码"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="队伍名称" prop="teamName">
              <el-input
                v-model="queryParams.teamName"
                placeholder="请输入队伍名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 分类信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">分类信息</div>
          <div class="form-row">
            <el-form-item label="分类ID" prop="catId">
              <el-input
                v-model="queryParams.catId"
                placeholder="请输入分类ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="分类名称" prop="catName">
              <el-input
                v-model="queryParams.catName"
                placeholder="请输入分类名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 区域信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">区域信息</div>
          <div class="form-row">
            <el-form-item label="适用区域代码" prop="applyRegionCode">
              <el-input
                v-model="queryParams.applyRegionCode"
                placeholder="请输入适用区域代码"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="适用区域名称" prop="applyRegionName">
              <el-input
                v-model="queryParams.applyRegionName"
                placeholder="请输入适用区域名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 人员信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">人员信息</div>
          <div class="form-row">
            <el-form-item label="队伍人数" prop="teamMemberCount">
              <el-input
                v-model="queryParams.teamMemberCount"
                placeholder="请输入队伍人数"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="可用人数" prop="availableMemberCount">
              <el-input
                v-model="queryParams.availableMemberCount"
                placeholder="请输入可用人数"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="队伍状态" prop="teamStatus">
              <el-select
                v-model="queryParams.teamStatus"
                placeholder="请选择队伍状态"
                clearable
                class="!w-200px"
              >
                <el-option label="全部" value="" />
                <el-option label="正常" value="1" />
                <el-option label="异常" value="0" />
              </el-select>
            </el-form-item>
          </div>
        </div>

        <!-- 创建信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">创建信息</div>
          <div class="form-row">
            <el-form-item label="创建人" prop="createUser">
              <el-input
                v-model="queryParams.createUser"
                placeholder="请输入创建人"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
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
            <el-form-item label="更新人" prop="updateUser">
              <el-input
                v-model="queryParams.updateUser"
                placeholder="请输入更新人"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <div class="form-actions">
          <el-button type="primary" @click="handleQuery">
            <Icon icon="ep:search" class="mr-5px" /> 搜索
          </el-button>
          <el-button @click="resetQuery">
            <Icon icon="ep:refresh" class="mr-5px" /> 重置
          </el-button>
          <el-button
            type="success"
            @click="openForm('create')"
            v-hasPermi="['datacenter:emer-team-info:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:emer-team-info:export']"
          >
            <Icon icon="ep:download" class="mr-5px" /> 导出
          </el-button>
        </div>
      </el-form>
    </el-card>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-card class="table-card" shadow="never">
      <template #header>
        <div class="card-header">
          <span class="card-title">救援队伍信息列表</span>
          <div class="table-info">
            共 <span class="info-highlight">{{ total }}</span> 条记录
          </div>
        </div>
      </template>

      <el-table
        v-loading="loading"
        :data="list"
        :stripe="true"
        :show-overflow-tooltip="true"
        style="width: 100%"
        :header-cell-style="{
          background: '#f5f7fa',
          color: '#606266',
          fontWeight: '600'
        }"
      >
        <el-table-column label="队伍信息" align="center" min-width="240">
          <template #default="scope">
            <div class="team-info">
              <div class="team-code">{{ scope.row.teamCode }}</div>
              <div class="team-name">{{ scope.row.teamName }}</div>
              <div class="team-id">ID: {{ scope.row.teamId }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="分类信息" align="center" width="140">
          <template #default="scope">
            <div class="category-info">
              <div v-if="scope.row.catName" class="cat-name">
                {{ scope.row.catName }}
              </div>
              <div v-if="scope.row.catId" class="cat-id">
                ID: {{ scope.row.catId }}
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="区域信息" align="center" width="160">
          <template #default="scope">
            <div class="region-info">
              <div v-if="scope.row.applyRegionName" class="region-name">
                {{ scope.row.applyRegionName }}
              </div>
              <div v-if="scope.row.applyRegionCode" class="region-code">
                代码: {{ scope.row.applyRegionCode }}
              </div>
              <div v-else class="no-region">
                未指定区域
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="人员信息" align="center" width="120">
          <template #default="scope">
            <div class="member-info">
              <div class="total-members">
                总数: <span class="member-count">{{ scope.row.teamMemberCount || 0 }}</span>
              </div>
              <div class="available-members">
                可用: <span class="member-count">{{ scope.row.availableMemberCount || 0 }}</span>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="状态" align="center" width="80">
          <template #default="scope">
            <el-tag
              :type="scope.row.teamStatus === '1' ? 'success' : 'danger'"
              size="small"
            >
              {{ getStatusText(scope.row.teamStatus) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="创建信息" align="center" width="180">
          <template #default="scope">
            <div class="create-info">
              <div v-if="scope.row.createUser" class="create-user">
                {{ scope.row.createUser }}
              </div>
              <div v-if="scope.row.createTime" class="create-time">
                {{ formatDate(scope.row.createTime) }}
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="主键ID" align="center" width="100">
          <template #default="scope">
            <div class="primary-id">
              {{ scope.row.id }}
            </div>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="120" fixed="right">
          <template #default="scope">
            <el-button
              size="small"
              type="primary"
              link
              @click="openForm('update', scope.row.id)"
              v-hasPermi="['datacenter:emer-team-info:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:emer-team-info:delete']"
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
    </el-card>
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <EmerTeamInfoForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { EmerTeamInfoApi, EmerTeamInfoVO } from '@/api/dataHub/commandAndCoordination/emerteaminfo'
import EmerTeamInfoForm from './EmerTeamInfoForm.vue'

/** 救援队伍信息 列表 */
defineOptions({ name: 'EmerTeamInfo' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EmerTeamInfoVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  teamId: undefined,
  teamCode: undefined,
  teamName: undefined,
  catId: undefined,
  catName: undefined,
  applyRegionCode: undefined,
  applyRegionName: undefined,
  teamMemberCount: undefined,
  availableMemberCount: undefined,
  teamStatus: undefined,
  createUser: undefined,
  createTime: [],
  updateUser: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 获取状态文本 */
const getStatusText = (status: string) => {
  const statusMap: Record<string, string> = {
    '1': '正常',
    '0': '异常'
  }
  return statusMap[status] || '未知'
}

/** 格式化日期 */
const formatDate = (dateString: string) => {
  if (!dateString) return '-'
  return dateString.split(' ')[0] // 只显示日期部分
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EmerTeamInfoApi.getEmerTeamInfoPage(queryParams)
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
    await EmerTeamInfoApi.deleteEmerTeamInfo(id)
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
    const data = await EmerTeamInfoApi.exportEmerTeamInfo(queryParams)
    download.excel(data, '救援队伍信息.xls')
  } catch {
  } finally {
    exportLoading.value = false
  }
}

/** 切换搜索表单显示 */
const toggleSearchForm = () => {
  showFullSearch.value = !showFullSearch.value
}

/** 初始化 **/
onMounted(() => {
  getList()
})
</script>

<style scoped>
.search-card {
  margin-bottom: 16px;
  border-radius: 8px;
}

.table-card {
  border-radius: 8px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card-title {
  font-size: 16px;
  font-weight: 600;
  color: #303133;
}

.toggle-btn {
  color: #409eff;
  font-size: 13px;
}

.table-info {
  font-size: 14px;
  color: #606266;
}

.info-highlight {
  color: #409eff;
  font-weight: 600;
}

.search-form {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.form-section {
  border: 1px solid #ebeef5;
  border-radius: 6px;
  padding: 16px;
  background: #fafbfc;
}

.section-title {
  font-size: 14px;
  font-weight: 600;
  color: #409eff;
  margin-bottom: 12px;
  padding-bottom: 8px;
  border-bottom: 1px dashed #e1e4e8;
}

.form-row {
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
}

.form-actions {
  display: flex;
  justify-content: center;
  gap: 12px;
  padding-top: 16px;
  border-top: 1px solid #ebeef5;
}

.pagination-wrapper {
  display: flex;
  justify-content: flex-end;
  margin-top: 16px;
  padding-top: 16px;
  border-top: 1px solid #ebeef5;
}

/* 表格内容样式 */
.team-info {
  line-height: 1.4;
  text-align: left;
}

.team-code {
  font-family: monospace;
  color: #e6a23c;
  background: #fdf6ec;
  padding: 2px 6px;
  border-radius: 3px;
  display: inline-block;
  margin-bottom: 4px;
  font-size: 12px;
}

.team-name {
  font-weight: 600;
  color: #303133;
  font-size: 14px;
  margin-bottom: 4px;
}

.team-id {
  font-size: 12px;
  color: #909399;
}

.category-info {
  line-height: 1.4;
  text-align: center;
}

.cat-name {
  font-weight: 500;
  color: #303133;
  margin-bottom: 4px;
}

.cat-id {
  font-size: 11px;
  color: #909399;
}

.region-info {
  line-height: 1.4;
  text-align: left;
  font-size: 12px;
}

.region-name {
  font-weight: 500;
  color: #303133;
  margin-bottom: 4px;
}

.region-code {
  color: #606266;
}

.no-region {
  color: #c0c4cc;
  font-style: italic;
}

.member-info {
  line-height: 1.4;
  text-align: center;
  font-size: 12px;
}

.total-members,
.available-members {
  margin-bottom: 4px;
}

.member-count {
  font-weight: 600;
  color: #409eff;
}

.create-info {
  line-height: 1.4;
  text-align: left;
  font-size: 12px;
}

.create-user {
  font-weight: 500;
  color: #303133;
  margin-bottom: 4px;
}

.create-time {
  color: #909399;
}

.primary-id {
  font-family: monospace;
  color: #67c23a;
  font-weight: 500;
}

:deep(.el-card__header) {
  padding: 12px 20px;
  border-bottom: 1px solid #ebeef5;
}

:deep(.el-table .cell) {
  padding: 8px 12px;
}

:deep(.el-table th) {
  font-weight: 600;
}

:deep(.el-table .el-table__row:hover) {
  background-color: #f5f7fa;
}

.ml-2 {
  margin-left: 8px;
}

.mr-5px {
  margin-right: 5px;
}
</style>
