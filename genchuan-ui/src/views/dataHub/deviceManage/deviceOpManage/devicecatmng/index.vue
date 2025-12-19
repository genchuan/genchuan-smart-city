<template>
  <ContentWrap class="flex flex-col h-full">
    <div class="flex h-full overflow-hidden">
      <!-- 左侧树形分类筛选 -->
      <div class="w-64 h-full border-r">
        <DeviceCategoryTree @select="handleCategorySelect" :selectedId="selectedCategoryId" />
      </div>

      <!-- 右侧内容区 -->
      <div class="flex-1 flex flex-col overflow-hidden">
        <!-- 搜索工作栏 -->
        <div class="p-4 border-b">
          <el-form
            class="-mb-15px"
            :model="queryParams"
            ref="queryFormRef"
            :inline="true"
            label-width="100px"
          >
            <el-form-item label="设备ID" prop="deviceId">
              <el-input
                v-model="queryParams.deviceId"
                placeholder="请输入设备ID"
                clearable
                @keyup.enter="handleQuery"
                class="!w-240px"
              />
            </el-form-item>
            <el-form-item label="设备名称" prop="deviceName">
              <el-input
                v-model="queryParams.deviceName"
                placeholder="请输入设备名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-240px"
              />
            </el-form-item>
            <el-form-item label="设备编码" prop="deviceCode">
              <el-input
                v-model="queryParams.deviceCode"
                placeholder="请输入设备编码"
                clearable
                @keyup.enter="handleQuery"
                class="!w-240px"
              />
            </el-form-item>
            <el-form-item label="设备大类名称" prop="deviceMajorName">
              <el-input
                v-model="queryParams.deviceMajorName"
                placeholder="请输入设备大类名称"
                clearable
                @keyup.enter="handleQuery"
                class="!w-240px"
              />
            </el-form-item>
            <el-form-item label="分类时间" prop="catTime">
              <el-date-picker
                v-model="queryParams.catTime"
                value-format="YYYY-MM-DD HH:mm:ss"
                type="daterange"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
                class="!w-220px"
              />
            </el-form-item>
            <el-form-item label="分类状态" prop="catStatus">
              <el-select
                v-model="queryParams.catStatus"
                placeholder="请选择分类状态"
                clearable
                class="!w-240px"
              >
                <el-option label="请选择字典生成" value="" />
              </el-select>
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
        </div>

        <!-- 列表 -->
        <div class="flex-1 overflow-auto p-4">
          <el-table
            v-loading="loading"
            :data="list"
            :stripe="true"
            :show-overflow-tooltip="true"
            class="custom-table"
          >
            <el-table-column label="主键ID" align="center" prop="id" width="80" />
            <el-table-column label="设备ID" align="center" prop="deviceId" />
            <el-table-column label="设备名称" align="center" prop="deviceName" min-width="120" />
            <el-table-column label="设备编码" align="center" prop="deviceCode" />
            <el-table-column label="设备大类" align="center" min-width="120">
              <template #default="scope">
                <span>{{ scope.row.deviceMajorName }}</span>
                <span class="text-gray-400 text-sm ml-2">({{ scope.row.deviceMajorId }})</span>
              </template>
            </el-table-column>
            <el-table-column label="设备中类" align="center" min-width="120">
              <template #default="scope">
                <span>{{ scope.row.deviceMidName }}</span>
                <span class="text-gray-400 text-sm ml-2">({{ scope.row.deviceMidId }})</span>
              </template>
            </el-table-column>
            <el-table-column label="设备小类" align="center" min-width="120">
              <template #default="scope">
                <span>{{ scope.row.deviceMinorName }}</span>
                <span class="text-gray-400 text-sm ml-2">({{ scope.row.deviceMinorId }})</span>
              </template>
            </el-table-column>
            <el-table-column
              label="分类时间"
              align="center"
              prop="catTime"
              :formatter="dateFormatter"
              width="180px"
            />
            <el-table-column label="分类人" align="center" prop="catUser" />
            <el-table-column label="分类状态" align="center" prop="catStatus">
              <template #default="scope">
                <el-tag :type="scope.row.catStatus === '1' ? 'success' : 'info'" size="small">
                  {{ scope.row.catStatus === '1' ? '已分类' : '未分类' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" min-width="180px">
              <template #default="scope">
                <el-button link type="primary" @click="openDetail(scope.row)" class="mr-2">
                  详情
                </el-button>
                <el-button
                  link
                  type="primary"
                  @click="openForm('update', scope.row.id)"
                  class="mr-2"
                >
                  编辑
                </el-button>
                <el-button link type="danger" @click="handleDelete(scope.row.id)"> 删除</el-button>
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
        </div>
      </div>
    </div>

    <!-- 表单弹窗：添加/修改 -->
    <DeviceCatMngForm ref="formRef" @success="getList" />

    <!-- 详情抽屉 -->
    <DeviceCatMngDetail
      ref="detailRef"
      :visible="detailVisible"
      :data="detailData"
      @close="detailVisible = false"
    />
  </ContentWrap>
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  DeviceCatMngApi,
  DeviceCatMngVO
} from '@/api/dataHub/deviceManage/deviceOpManage/devicecatmng'
import DeviceCatMngForm from './DeviceCatMngForm.vue'
import DeviceCatMngDetail from './DeviceCatMngDetail.vue'
import DeviceCategoryTree from './DeviceCategoryTree.vue' // 引入树形组件

/** 设备分类管理 列表 */
defineOptions({ name: 'DeviceCatMng' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DeviceCatMngVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const selectedCategoryId = ref<string | null>(null) // 选中的分类ID
const selectedCategoryLevel = ref<'major' | 'mid' | 'minor' | null>(null) // 选中的分类层级

const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  catMngId: undefined,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  deviceMajorId: undefined,
  deviceMajorName: undefined,
  deviceMidId: undefined,
  deviceMidName: undefined,
  deviceMinorId: undefined,
  deviceMinorName: undefined,
  catTime: [],
  catUser: undefined,
  catStatus: undefined,
  adjustTime: [],
  adjustUser: undefined,
  adjustReason: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
const detailRef = ref()
const detailVisible = ref(false)
const detailData = ref<DeviceCatMngVO | null>(null)

const openDetail = (row: DeviceCatMngVO) => {
  detailData.value = row
  detailVisible.value = true
}

/** 处理分类选择 */
const handleCategorySelect = (
  categoryId: string | null,
  level: 'major' | 'mid' | 'minor' | null
) => {
  selectedCategoryId.value = categoryId
  selectedCategoryLevel.value = level

  // 根据选择的分类层级设置查询参数
  queryParams.deviceMajorId = level === 'major' ? categoryId : undefined
  queryParams.deviceMidId = level === 'mid' ? categoryId : undefined
  queryParams.deviceMinorId = level === 'minor' ? categoryId : undefined

  // 重置页码并查询
  queryParams.pageNo = 1
  getList()
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DeviceCatMngApi.getDeviceCatMngPage(queryParams)
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
  // 重置分类筛选
  selectedCategoryId.value = null
  selectedCategoryLevel.value = null
  queryParams.deviceMajorId = undefined
  queryParams.deviceMidId = undefined
  queryParams.deviceMinorId = undefined
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
    await DeviceCatMngApi.deleteDeviceCatMng(id)
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
    const data = await DeviceCatMngApi.exportDeviceCatMng(queryParams)
    download.excel(data, '设备分类管理.xls')
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

::v-deep .el-table__header {
  background-color: #f9fafb;
}

::v-deep .el-table th {
  background-color: #f9fafb;
  font-weight: 500;
  color: #4e5969;
}

::v-deep .el-table tr:hover > td {
  background-color: #f5f7fa !important;
}
</style>
