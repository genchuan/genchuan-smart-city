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
        label-width="120px"
      >
        <!-- 基础信息 -->
        <div class="form-section">
          <div class="section-title">基础信息</div>
          <div class="form-row">
            <el-form-item label="分类ID" prop="deviceCatId">
              <el-input
                v-model="queryParams.deviceCatId"
                placeholder="请输入分类ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="分类编码" prop="catCode">
              <el-input
                v-model="queryParams.catCode"
                placeholder="请输入分类编码"
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

        <!-- 层级关系 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">层级关系</div>
          <div class="form-row">
            <el-form-item label="父分类ID" prop="parentCatId">
              <el-input
                v-model="queryParams.parentCatId"
                placeholder="请输入父分类ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="分类层级" prop="catLevel">
              <el-select
                v-model="queryParams.catLevel"
                placeholder="请选择分类层级"
                clearable
                class="!w-200px"
              >
                <el-option label="全部" value="" />
                <el-option label="大类" value="1" />
                <el-option label="中类" value="2" />
                <el-option label="小类" value="3" />
              </el-select>
            </el-form-item>
          </div>
        </div>

        <!-- 详细信息 -->
        <div class="form-section" v-show="showFullSearch">
          <div class="section-title">详细信息</div>
          <div class="form-row">
            <el-form-item label="分类说明" prop="catDesc">
              <el-input
                v-model="queryParams.catDesc"
                placeholder="请输入分类说明"
                clearable
                @keyup.enter="handleQuery"
                class="!w-480px"
              />
            </el-form-item>
          </div>
          <div class="form-row">
            <el-form-item label="适用区域" prop="applyRegionName">
              <el-input
                v-model="queryParams.applyRegionName"
                placeholder="请输入适用区域名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-200px"
              />
            </el-form-item>
            <el-form-item label="区域代码" prop="applyRegionCode">
              <el-input
                v-model="queryParams.applyRegionCode"
                placeholder="请输入适用区域代码"
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
            v-hasPermi="['datacenter:emer-device-cat:create']"
          >
            <Icon icon="ep:plus" class="mr-5px" /> 新增
          </el-button>
          <el-button
            type="warning"
            @click="handleExport"
            :loading="exportLoading"
            v-hasPermi="['datacenter:emer-device-cat:export']"
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
          <span class="card-title">救援设备分类配置列表</span>
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
        <el-table-column label="分类信息" align="center" min-width="220">
          <template #default="scope">
            <div class="category-info">
              <div class="cat-code">{{ scope.row.catCode }}</div>
              <div class="cat-name">{{ scope.row.catName }}</div>
              <div v-if="scope.row.catDesc" class="cat-desc">{{ scope.row.catDesc }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="层级关系" align="center" width="120">
          <template #default="scope">
            <div class="hierarchy-info">
              <el-tag
                :type="getLevelTagType(scope.row.catLevel)"
                size="small"
              >
                {{ getLevelText(scope.row.catLevel) }}
              </el-tag>
              <div v-if="scope.row.parentCatId" class="parent-info">
                父ID: {{ scope.row.parentCatId }}
              </div>
              <div v-else class="root-node">
                根节点
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="分类ID" align="center" width="100">
          <template #default="scope">
            <div class="device-cat-id">
              {{ scope.row.deviceCatId }}
            </div>
          </template>
        </el-table-column>
        <el-table-column label="适用区域" align="center" width="160">
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
              v-hasPermi="['datacenter:emer-device-cat:update']"
            >
              编辑
            </el-button>
            <el-button
              size="small"
              type="danger"
              link
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['datacenter:emer-device-cat:delete']"
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
  <EmerDeviceCatForm ref="formRef" @success="getList" />
</template>

<script setup lang="ts">
import download from '@/utils/download'
import { EmerDeviceCatApi, EmerDeviceCatVO } from '@/api/dataHub/commandAndCoordination/emerdevicecat'
import EmerDeviceCatForm from './EmerDeviceCatForm.vue'

/** 救援设备分类配置 列表 */
defineOptions({ name: 'EmerDeviceCat' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<EmerDeviceCatVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const showFullSearch = ref(false) // 是否显示完整搜索表单

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  deviceCatId: undefined,
  parentCatId: undefined,
  catLevel: undefined,
  catCode: undefined,
  catName: undefined,
  catDesc: undefined,
  applyRegionCode: undefined,
  applyRegionName: undefined,
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

/** 获取分类层级标签样式 */
const getLevelTagType = (level: string) => {
  const levelMap: Record<string, string> = {
    '1': '',      // 大类 - 默认
    '2': 'info',  // 中类 - 信息
    '3': 'warning', // 小类 - 警告
  }
  return levelMap[level] || ''
}

/** 获取分类层级文本 */
const getLevelText = (level: string) => {
  const levelTextMap: Record<string, string> = {
    '1': '大类',
    '2': '中类',
    '3': '小类'
  }
  return levelTextMap[level] || level || '未知'
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await EmerDeviceCatApi.getEmerDeviceCatPage(queryParams)
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
    await EmerDeviceCatApi.deleteEmerDeviceCat(id)
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
    const data = await EmerDeviceCatApi.exportEmerDeviceCat(queryParams)
    download.excel(data, '救援设备分类配置.xls')
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
.category-info {
  line-height: 1.4;
  text-align: left;
}

.cat-code {
  font-family: monospace;
  color: #e6a23c;
  background: #fdf6ec;
  padding: 2px 6px;
  border-radius: 3px;
  display: inline-block;
  margin-bottom: 4px;
  font-size: 12px;
}

.cat-name {
  font-weight: 600;
  color: #303133;
  font-size: 14px;
}

.cat-desc {
  color: #909399;
  font-size: 12px;
  margin-top: 2px;
  font-style: italic;
}

.hierarchy-info {
  line-height: 1.4;
  text-align: center;
}

.parent-info {
  font-size: 11px;
  color: #909399;
  margin-top: 4px;
}

.root-node {
  font-size: 11px;
  color: #67c23a;
  margin-top: 4px;
  font-weight: 500;
}

.device-cat-id {
  font-family: monospace;
  color: #409eff;
  font-weight: 500;
}

.primary-id {
  font-family: monospace;
  color: #67c23a;
  font-weight: 500;
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
