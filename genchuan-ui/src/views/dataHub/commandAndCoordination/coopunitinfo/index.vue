<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-card class="search-card" shadow="never">
      <template #header>
        <div class="card-header">
          <span class="card-title">
            <Icon icon="ep:search" class="title-icon" />
            查询条件
          </span>
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
        label-width="120px"
      >
        <!-- 基础信息 -->
        <div class="form-section">
          <div class="section-title">
            <Icon icon="ep:info-filled" class="section-icon" />
            基础信息
          </div>
          <div class="form-row">
            <el-form-item label="单位ID" prop="unitId">
              <el-input
                v-model="queryParams.unitId"
                placeholder="请输入单位ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="单位编码" prop="unitCode">
              <el-input
                v-model="queryParams.unitCode"
                placeholder="请输入单位编码"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="单位名称" prop="unitName">
              <el-input
                v-model="queryParams.unitName"
                placeholder="请输入单位名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="单位类型" prop="unitType">
              <el-select
                v-model="queryParams.unitType"
                placeholder="请选择单位类型"
                clearable
                class="!w-200px"
              >
                <el-option label="政府部门" value="1" />
                <el-option label="企业" value="2" />
                <el-option label="社会组织" value="3" />
              </el-select>
            </el-form-item>
          </div>
        </div>

        <!-- 区域信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">
            <Icon icon="ep:map-location" class="section-icon" />
            区域信息
          </div>
          <div class="form-row">
            <el-form-item label="行政区划代码" prop="regionCode">
              <el-input
                v-model="queryParams.regionCode"
                placeholder="请输入行政区划代码"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="行政区划名称" prop="regionName">
              <el-input
                v-model="queryParams.regionName"
                placeholder="请输入行政区划名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 负责人信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">
            <Icon icon="ep:user" class="section-icon" />
            负责人信息
          </div>
          <div class="form-row">
            <el-form-item label="负责人姓名" prop="leaderName">
              <el-input
                v-model="queryParams.leaderName"
                placeholder="请输入负责人姓名"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="负责人联系方式" prop="leaderContact">
              <el-input
                v-model="queryParams.leaderContact"
                placeholder="请输入负责人联系方式"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <!-- 单位详细信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">
            <Icon icon="ep:office-building" class="section-icon" />
            单位详细信息
          </div>
          <div class="form-row">
            <el-form-item label="单位详细地址" prop="contactAddress">
              <el-input
                v-model="queryParams.contactAddress"
                placeholder="请输入单位详细地址"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="联动范围" prop="coopScope">
              <el-input
                v-model="queryParams.coopScope"
                placeholder="请输入联动范围"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="单位状态" prop="unitStatus">
              <el-select
                v-model="queryParams.unitStatus"
                placeholder="请选择单位状态"
                clearable
                class="!w-200px"
              >
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </el-form-item>
          </div>
        </div>

        <!-- 系统信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">
            <Icon icon="ep:setting" class="section-icon" />
            系统信息
          </div>
          <div class="form-row">
            <el-form-item label="创建人账号" prop="createUser">
              <el-input
                v-model="queryParams.createUser"
                placeholder="请输入创建人账号"
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
            <el-form-item label="更新人账号" prop="updateUser">
              <el-input
                v-model="queryParams.updateUser"
                placeholder="请输入更新人账号"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
          </div>
        </div>

        <div class="form-actions">
          <el-button type="primary" @click="handleQuery" class="action-btn">
            <Icon icon="ep:search" class="btn-icon" />
            搜索
          </el-button>
          <el-button @click="resetQuery" class="action-btn">
            <Icon icon="ep:refresh" class="btn-icon" />
            重置
          </el-button>
          <el-button
            type="success"
            @click="openForm('create')"
            v-hasPermi="['datacenter:coop-unit-info:create']"
            class="action-btn"
          >
            <Icon icon="ep:plus" class="btn-icon" />
            新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:coop-unit-info:export']"
            class="action-btn"
          >
            <Icon icon="ep:download" class="btn-icon" />
            导出
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
          <span class="card-title">
            <Icon icon="ep:list" class="title-icon" />
            联动单位信息列表
          </span>
          <div class="table-info">
            <Icon icon="ep:document" class="info-icon" />
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
          background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
          color: '#fff',
          fontWeight: '600',
          fontSize: '14px'
        }"
        class="custom-table"
      >
        <el-table-column label="单位基本信息" align="center" min-width="200">
          <template #default="scope">
            <div class="unit-info">
              <div class="unit-code">{{ scope.row.unitCode }}</div>
              <div class="unit-name">{{ scope.row.unitName }}</div>
              <div class="unit-id">ID: {{ scope.row.unitId }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="单位类型" align="center" width="120">
          <template #default="scope">
            <el-tag
              v-if="scope.row.unitType"
              type="primary"
              size="small"
              class="unit-type-tag"
            >
              {{ scope.row.unitType }}
            </el-tag>
            <span v-else class="no-data">-</span>
          </template>
        </el-table-column>
        <el-table-column label="行政区划" align="center" width="160">
          <template #default="scope">
            <div class="region-info">
              <div v-if="scope.row.regionName" class="region-name">
                <Icon icon="ep:location" class="region-icon" />
                {{ scope.row.regionName }}
              </div>
              <div v-if="scope.row.regionCode" class="region-code">
                代码: {{ scope.row.regionCode }}
              </div>
              <div v-else class="no-region">
                <Icon icon="ep:warning" class="warning-icon" />
                未指定区域
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="负责人信息" align="center" width="180">
          <template #default="scope">
            <div class="leader-info">
              <div v-if="scope.row.leaderName" class="leader-name">
                <Icon icon="ep:user" class="leader-icon" />
                {{ scope.row.leaderName }}
              </div>
              <div v-if="scope.row.leaderContact" class="leader-contact">
                <Icon icon="ep:phone" class="contact-icon" />
                {{ scope.row.leaderContact }}
              </div>
              <div v-else class="no-contact">
                <Icon icon="ep:close" class="close-icon" />
                无联系方式
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="单位地址" align="center" width="200">
          <template #default="scope">
            <div class="address-info">
              <Icon icon="ep:location" class="address-icon" />
              {{ scope.row.contactAddress || '-' }}
            </div>
          </template>
        </el-table-column>
        <el-table-column label="联动范围" align="center" width="120">
          <template #default="scope">
            <div class="coop-scope">
              <el-tag
                v-if="scope.row.coopScope"
                type="success"
                size="small"
                class="scope-tag"
              >
                {{ scope.row.coopScope }}
              </el-tag>
              <span v-else class="no-data">-</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="单位状态" align="center" width="100">
          <template #default="scope">
            <div class="unit-status">
              <el-tag
                v-if="scope.row.unitStatus"
                :type="getStatusType(scope.row.unitStatus)"
                size="small"
                class="status-tag"
              >
                {{ scope.row.unitStatus }}
              </el-tag>
              <span v-else class="no-data">-</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="系统信息" align="center" width="180">
          <template #default="scope">
            <div class="system-info">
              <div v-if="scope.row.createUser" class="create-user">
                <Icon icon="ep:user" class="system-icon" />
                创建: {{ scope.row.createUser }}
              </div>
              <div v-if="scope.row.createTime" class="create-time">
                <Icon icon="ep:clock" class="system-icon" />
                {{ dateFormatter(scope.row.createTime) }}
              </div>
              <div v-if="scope.row.updateUser" class="update-user">
                <Icon icon="ep:edit" class="system-icon" />
                更新: {{ scope.row.updateUser }}
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="140" fixed="right">
          <template #default="scope">
            <div class="action-buttons">
              <el-button
                size="small"
                type="primary"
                link
                @click="openForm('update', scope.row.id)"
                v-hasPermi="['datacenter:coop-unit-info:update']"
                class="action-button"
              >
                <Icon icon="ep:edit" class="button-icon" />
                编辑
              </el-button>
              <el-button
                size="small"
                type="danger"
                link
                @click="handleDelete(scope.row.id)"
                v-hasPermi="['datacenter:coop-unit-info:delete']"
                class="action-button"
              >
                <Icon icon="ep:delete" class="button-icon" />
                删除
              </el-button>
            </div>
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
  <CoopUnitInfoForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { CoopUnitInfoApi, CoopUnitInfoVO } from '@/api/dataHub/commandAndCoordination/coopunitinfo'
import CoopUnitInfoForm from './CoopUnitInfoForm.vue'

/** 联动单位信息 列表 */
defineOptions({ name: 'CoopUnitInfo' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<CoopUnitInfoVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  unitId: undefined,
  unitCode: undefined,
  unitName: undefined,
  unitType: undefined,
  regionCode: undefined,
  regionName: undefined,
  leaderName: undefined,
  leaderContact: undefined,
  contactAddress: undefined,
  coopScope: undefined,
  unitStatus: undefined,
  createUser: undefined,
  createTime: [],
  updateUser: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 获取状态标签类型 */
const getStatusType = (status: string) => {
  const statusMap: Record<string, any> = {
    '正常': 'success',
    '启用': 'success',
    '禁用': 'danger',
    '停用': 'warning',
    '异常': 'danger'
  }
  return statusMap[status] || 'info'
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await CoopUnitInfoApi.getCoopUnitInfoPage(queryParams)
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
    await CoopUnitInfoApi.deleteCoopUnitInfo(id)
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
    const data = await CoopUnitInfoApi.exportCoopUnitInfo(queryParams)
    download.excel(data, '联动单位信息.xls')
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
  margin-bottom: 20px;
  border-radius: 12px;
  border: 1px solid #e1e8ff;
  background: linear-gradient(135deg, #f8faff 0%, #f0f4ff 100%);
}

.table-card {
  border-radius: 12px;
  border: 1px solid #e1e8ff;
  background: #fff;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 8px 0;
}

.card-title {
  font-size: 16px;
  font-weight: 700;
  color: #2c3e50;
  display: flex;
  align-items: center;
  gap: 8px;
}

.title-icon {
  color: #409eff;
  font-size: 18px;
}

.toggle-btn {
  color: #409eff;
  font-size: 13px;
  font-weight: 500;
  padding: 4px 8px;
  border-radius: 6px;
  transition: all 0.3s ease;
}

.toggle-btn:hover {
  background: #ecf5ff;
  transform: translateY(-1px);
}

.table-info {
  font-size: 14px;
  color: #606266;
  display: flex;
  align-items: center;
  gap: 6px;
}

.info-icon {
  color: #909399;
  font-size: 16px;
}

.info-highlight {
  color: #409eff;
  font-weight: 700;
  font-size: 16px;
}

.search-form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.form-section {
  border: 1px solid #e1e8ff;
  border-radius: 10px;
  padding: 20px;
  background: #fff;
  box-shadow: 0 2px 8px rgba(64, 158, 255, 0.08);
  transition: all 0.3s ease;
}

.form-section:hover {
  box-shadow: 0 4px 12px rgba(64, 158, 255, 0.12);
  transform: translateY(-1px);
}

.section-title {
  font-size: 15px;
  font-weight: 700;
  color: #409eff;
  margin-bottom: 16px;
  padding-bottom: 10px;
  border-bottom: 2px solid #e1e8ff;
  display: flex;
  align-items: center;
  gap: 8px;
}

.section-icon {
  font-size: 16px;
}

.form-row {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  align-items: flex-start;
}

.form-actions {
  display: flex;
  justify-content: center;
  gap: 16px;
  padding-top: 20px;
  border-top: 1px solid #e1e8ff;
  margin-top: 10px;
}

.action-btn {
  padding: 10px 20px;
  border-radius: 8px;
  font-weight: 500;
  transition: all 0.3s ease;
  display: flex;
  align-items: center;
  gap: 6px;
}

.action-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.btn-icon {
  font-size: 14px;
}

.pagination-wrapper {
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
  padding-top: 20px;
  border-top: 1px solid #e1e8ff;
}

/* 表格内容样式 */
.custom-table {
  border-radius: 8px;
  overflow: hidden;
}

.unit-info {
  line-height: 1.5;
  text-align: left;
  padding: 8px 0;
}

.unit-code {
  font-family: 'Monaco', 'Consolas', monospace;
  color: #e6a23c;
  background: linear-gradient(135deg, #fdf6ec 0%, #faecd8 100%);
  padding: 4px 8px;
  border-radius: 6px;
  display: inline-block;
  margin-bottom: 6px;
  font-size: 12px;
  font-weight: 600;
  border: 1px solid #f5dab1;
}

.unit-name {
  font-weight: 700;
  color: #2c3e50;
  font-size: 14px;
  margin-bottom: 4px;
}

.unit-id {
  color: #909399;
  font-size: 12px;
  font-weight: 500;
}

.unit-type-tag {
  font-weight: 600;
  border-radius: 6px;
  padding: 4px 8px;
}

.region-info {
  line-height: 1.5;
  text-align: left;
  font-size: 13px;
}

.region-name {
  font-weight: 600;
  color: #2c3e50;
  margin-bottom: 6px;
  display: flex;
  align-items: center;
  gap: 6px;
}

.region-icon {
  color: #409eff;
  font-size: 14px;
}

.region-code {
  color: #606266;
  font-size: 12px;
  background: #f5f7fa;
  padding: 2px 6px;
  border-radius: 4px;
  display: inline-block;
}

.no-region {
  color: #c0c4cc;
  font-style: italic;
  font-size: 12px;
  display: flex;
  align-items: center;
  gap: 4px;
}

.warning-icon {
  font-size: 12px;
}

.leader-info {
  line-height: 1.5;
  text-align: left;
  font-size: 13px;
}

.leader-name {
  font-weight: 600;
  color: #2c3e50;
  margin-bottom: 6px;
  display: flex;
  align-items: center;
  gap: 6px;
}

.leader-icon {
  color: #67c23a;
  font-size: 14px;
}

.leader-contact {
  color: #606266;
  display: flex;
  align-items: center;
  gap: 6px;
  font-size: 12px;
}

.contact-icon {
  color: #909399;
  font-size: 12px;
}

.no-contact {
  color: #c0c4cc;
  font-style: italic;
  font-size: 12px;
  display: flex;
  align-items: center;
  gap: 4px;
}

.close-icon {
  font-size: 12px;
}

.address-info {
  font-size: 13px;
  line-height: 1.5;
  color: #606266;
  display: flex;
  align-items: flex-start;
  gap: 6px;
  text-align: left;
}

.address-icon {
  color: #e6a23c;
  font-size: 14px;
  margin-top: 2px;
  flex-shrink: 0;
}

.scope-tag {
  font-weight: 600;
  border-radius: 6px;
  padding: 4px 8px;
}

.status-tag {
  font-weight: 600;
  border-radius: 6px;
  padding: 4px 8px;
}

.system-info {
  line-height: 1.5;
  text-align: left;
  font-size: 12px;
  color: #909399;
}

.create-user, .update-user {
  margin-bottom: 4px;
  display: flex;
  align-items: center;
  gap: 4px;
}

.create-time {
  font-style: italic;
  display: flex;
  align-items: center;
  gap: 4px;
  color: #67c23a;
}

.system-icon {
  font-size: 12px;
}

.action-buttons {
  display: flex;
  flex-direction: column;
  gap: 6px;
  align-items: center;
}

.action-button {
  padding: 4px 8px;
  border-radius: 6px;
  transition: all 0.3s ease;
  display: flex;
  align-items: center;
  gap: 4px;
  font-weight: 500;
}

.action-button:hover {
  background: #f5f7fa;
  transform: translateX(2px);
}

.button-icon {
  font-size: 12px;
}

.no-data {
  color: #c0c4cc;
  font-style: italic;
}

:deep(.el-card__header) {
  padding: 16px 24px;
  border-bottom: 1px solid #e1e8ff;
  background: linear-gradient(135deg, #f8faff 0%, #f0f4ff 100%);
}

:deep(.el-table .cell) {
  padding: 12px 16px;
  line-height: 1.5;
}

:deep(.el-table th) {
  font-weight: 700;
  font-size: 14px;
}

:deep(.el-table .el-table__row) {
  transition: all 0.3s ease;
}

:deep(.el-table .el-table__row:hover) {
  background-color: #f8faff !important;
  transform: scale(1.01);
  box-shadow: 0 2px 8px rgba(64, 158, 255, 0.15);
}

:deep(.el-table--striped .el-table__body tr.el-table__row--striped td) {
  background: #fafbfc;
}

:deep(.el-table--striped .el-table__body tr.el-table__row--striped:hover td) {
  background-color: #f0f4ff !important;
}

.ml-2 {
  margin-left: 8px;
}

.mr-5px {
  margin-right: 5px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .form-row {
    gap: 12px;
  }

  .form-actions {
    flex-wrap: wrap;
    gap: 12px;
  }

  .action-btn {
    flex: 1;
    min-width: 120px;
    justify-content: center;
  }
}
</style>
