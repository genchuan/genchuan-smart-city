<template>
  <ContentWrap class="p-0">
    <div class="flex h-full">
      <!-- 左侧树形结构 -->
      <div class="w-64 h-[calc(100vh-120px)] overflow-auto">
        <DeviceCatRuleTree :selected-id="selectedCatRuleId" @select="handleTreeSelect" />
      </div>

      <!-- 右侧内容区域 -->
      <div class="flex-1 overflow-auto p-4">
        <!-- 搜索工作栏 -->
        <el-form
          class="-mb-15px"
          :model="queryParams"
          ref="queryFormRef"
          :inline="true"
          label-width="90px"
        >
          <!-- 原有搜索表单内容保持不变 -->
          <el-form-item label="分类规则ID" prop="catRuleId">
            <el-input
              v-model="queryParams.catRuleId"
              placeholder="请输入分类规则ID"
              clearable
              @keyup.enter="handleQuery"
              class="!w-240px"
            />
          </el-form-item>
          <el-form-item label="父类规则ID" prop="parentCatRuleId">
            <el-input
              v-model="queryParams.parentCatRuleId"
              placeholder="请输入父类规则ID"
              clearable
              @keyup.enter="handleQuery"
              class="!w-240px"
            />
          </el-form-item>
          <el-form-item label="分类名称" prop="catName">
            <el-input
              v-model="queryParams.catName"
              placeholder="请输入分类名称"
              clearable
              @keyup.enter="handleQuery"
              class="!w-240px"
            />
          </el-form-item>
          <el-form-item label="分类代码" prop="catCode">
            <el-input
              v-model="queryParams.catCode"
              placeholder="请输入分类代码"
              clearable
              @keyup.enter="handleQuery"
              class="!w-240px"
            />
          </el-form-item>
          <el-form-item label="启用状态" prop="enableStatus">
            <el-input
              v-model="queryParams.enableStatus"
              placeholder="请输入启用状态"
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
              v-hasPermi="['datacenter:device-cat-rule:create']"
            >
              <Icon icon="ep:plus" class="mr-5px" />
              新增
            </el-button>
            <el-button
              type="success"
              plain
              @click="handleExport"
              :loading="exportLoading"
              v-hasPermi="['datacenter:device-cat-rule:export']"
            >
              <Icon icon="ep:download" class="mr-5px" />
              导出
            </el-button>
          </el-form-item>
        </el-form>

        <!-- 列表 -->
        <ContentWrap>
          <el-table
            v-loading="loading"
            :data="list"
            :stripe="true"
            :show-overflow-tooltip="true"
            class="custom-table"
          >
            <!-- 表格内容保持不变 -->
            <el-table-column label="主键ID" align="center" prop="id" width="80" />
            <el-table-column label="分类规则ID" align="center" prop="catRuleId" />
            <el-table-column label="父类规则ID" align="center" prop="parentCatRuleId" />
            <el-table-column label="分类层级" align="center" prop="catLevel" width="80" />
            <el-table-column label="分类代码" align="center" prop="catCode" />
            <el-table-column
              label="分类名称"
              align="center"
              prop="catName"
              class="table-primary-text"
            />
            <el-table-column label="启用状态" align="center" prop="enableStatus">
              <template #default="scope">
                <span
                  :class="scope.row.enableStatus === '1' ? 'status-enabled' : 'status-disabled'"
                >
                  {{ scope.row.enableStatus ? '启用' : '禁用' }}
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
                <el-button link type="primary" @click="openDetail(scope.row)"> 详情</el-button>
                <el-button
                  link
                  type="primary"
                  @click="openForm('update', scope.row.id)"
                  v-hasPermi="['datacenter:device-cat-rule:update']"
                >
                  编辑
                </el-button>
                <el-button
                  link
                  type="danger"
                  @click="handleDelete(scope.row.id)"
                  v-hasPermi="['datacenter:device-cat-rule:delete']"
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
            class="mt-4"
          />
        </ContentWrap>
      </div>
    </div>
  </ContentWrap>

  <!-- 表单弹窗：添加/修改 -->
  <DeviceCatRuleForm ref="formRef" @success="handleFormSuccess" />

  <!-- 详情抽屉 -->
  <DeviceCatRuleDetailDrawer
    ref="detailDrawerRef"
    :visible="detailVisible"
    @update:visible="detailVisible = $event"
  />
</template>

<script setup lang="ts">
// 引入新增的树形组件
import DeviceCatRuleTree from './DeviceCatRuleTree.vue'
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DeviceCatRuleApi,
  DeviceCatRuleVO
} from '@/api/dataHub/deviceManage/deviceRule/devicecatrule'
import DeviceCatRuleForm from './DeviceCatRuleForm.vue'
import DeviceCatRuleDetailDrawer from './DeviceCatRuleDetailDrawer.vue'

/** 设备分类规则配置 列表 */
defineOptions({ name: 'DeviceCatRule' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DeviceCatRuleVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
// 选中的树形节点ID
const selectedCatRuleId = ref<string | null>(null)

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  catRuleId: undefined,
  parentCatRuleId: undefined,
  catLevel: undefined,
  catCode: undefined,
  catName: undefined,
  enableStatus: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
const detailVisible = ref(false)
const detailDrawerRef = ref()
const openDetail = (row: DeviceCatRuleVO) => {
  detailVisible.value = true
  detailDrawerRef.value.setData(row)
}

/** 处理树形节点选择 */
const handleTreeSelect = (catRuleId: string | null) => {
  selectedCatRuleId.value = catRuleId
  // 重置页码
  queryParams.pageNo = 1
  // 更新查询参数，根据选中的节点ID筛选
  if (catRuleId) {
    queryParams.parentCatRuleId = catRuleId
  } else {
    queryParams.parentCatRuleId = undefined
  }
  // 重新查询列表
  getList()
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DeviceCatRuleApi.getDeviceCatRulePage(queryParams)
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
  // 重置树形选择
  selectedCatRuleId.value = null
  handleQuery()
}

/** 添加/修改操作 */
const formRef = ref()
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 表单提交成功后处理 */
const handleFormSuccess = () => {
  // 重新加载列表和树形数据
  getList()
  // 如果需要刷新树形结构，可以在这里调用树形组件的刷新方法
}

/** 删除按钮操作 */
const handleDelete = async (id: number) => {
  try {
    // 删除的二次确认
    await message.delConfirm()
    // 发起删除
    await DeviceCatRuleApi.deleteDeviceCatRule(id)
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
    const data = await DeviceCatRuleApi.exportDeviceCatRule(queryParams)
    download.excel(data, '设备分类规则配置.xls')
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
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

.table-primary-text {
  color: #3b82f6;
  font-weight: 500;
}

.status-enabled {
  color: #10b981;
  background-color: rgba(16, 185, 129, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.status-disabled {
  color: #ef4444;
  background-color: rgba(239, 68, 68, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.flex {
  display: flex;
}

.h-full {
  height: 100%;
}

.overflow-auto {
  overflow: auto;
}

.p-4 {
  padding: 16px;
}

.flex-1 {
  flex: 1;
}
</style>
