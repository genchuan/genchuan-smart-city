<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      class="-mb-15px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="90px"
    >
      <!-- 保留常用搜索字段，隐藏部分不常用字段 -->
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
      <el-form-item label="设备类型" prop="deviceType">
        <el-select
          v-model="queryParams.deviceType"
          placeholder="请选择设备类型"
          clearable
          class="!w-240px"
        >
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="设备状态" prop="deviceStatus">
        <el-select
          v-model="queryParams.deviceStatus"
          placeholder="请选择设备状态"
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
        <el-button
          type="primary"
          plain
          @click="openForm('create')"
          v-hasPermi="['datacenter:device-info:create']"
        >
          <Icon icon="ep:plus" class="mr-5px" />
          新增
        </el-button>
        <el-button
          type="success"
          plain
          @click="handleExport"
          :loading="exportLoading"
          v-hasPermi="['datacenter:device-info:export']"
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
      <!-- 表格列只保留常用字段，其余隐藏 -->
      <el-table-column label="设备ID" align="center" prop="deviceId" />
      <el-table-column label="设备名称" align="center" prop="deviceName" />
      <el-table-column label="设备编码" align="center" prop="deviceCode" />
      <el-table-column label="设备类型" align="center" prop="deviceType" />
      <el-table-column label="设备型号" align="center" prop="deviceModel" />
      <el-table-column label="生产厂商" align="center" prop="manufacturer" />
      <el-table-column
        label="安装日期"
        align="center"
        prop="installDate"
        :formatter="dateFormatter"
      />
      <el-table-column label="设备状态" align="center" prop="deviceStatus">
        <template #default="scope">
          <span :class="scope.row.deviceStatus === '正常' ? 'status-normal' : 'status-abnormal'">
            {{ scope.row.deviceStatus }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="运行状态" align="center" prop="runStatus">
        <template #default="scope">
          <span :class="scope.row.runStatus === 'online' ? 'status-running' : 'status-stopped'">
            {{ scope.row.runStatus }}
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="最后在线时间"
        align="center"
        prop="lastOnlineTime"
        :formatter="dateFormatter"
        width="180px"
      />
      <el-table-column label="操作" align="center" min-width="150px">
        <template #default="scope">
          <el-button link type="primary" @click="openDetail(scope.row)"> 详情</el-button>
          <el-button
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
            v-hasPermi="['datacenter:device-info:update']"
          >
            编辑
          </el-button>
          <el-button
            link
            type="danger"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['datacenter:device-info:delete']"
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
  <DeviceInfoForm ref="formRef" @success="getList" />

  <!-- 详情抽屉 -->
  <DeviceInfoDetail
    v-model:visible="detailVisible"
    :device-info="currentDevice"
    @close="detailVisible = false"
  />
</template>

<script setup lang="ts">
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { DeviceInfoApi, DeviceInfoVO } from '@/api/dataHub/deviceManage/deviceinfo'
import DeviceInfoForm from './DeviceInfoForm.vue'
import DeviceInfoDetail from './DeviceInfoDetail.vue' // 引入详情组件

/** 设备信息 列表 */
defineOptions({ name: 'DeviceInfo' })

const message = useMessage() // 消息弹窗
const { t } = useI18n() // 国际化

const loading = ref(true) // 列表的加载中
const list = ref<DeviceInfoVO[]>([]) // 列表的数据
const total = ref(0) // 列表的总页数
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  deviceId: undefined,
  deviceName: undefined,
  deviceCode: undefined,
  deviceType: undefined,
  deviceStatus: undefined,
  createTime: []
})
const queryFormRef = ref() // 搜索的表单
const exportLoading = ref(false) // 导出的加载中

// 详情抽屉相关
const detailVisible = ref(false)
const currentDevice = ref<DeviceInfoVO | null>(null)

// 打开详情抽屉
const openDetail = (row: DeviceInfoVO) => {
  currentDevice.value = { ...row }
  console.log(row)
  detailVisible.value = true
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await DeviceInfoApi.getDeviceInfoPage(queryParams)
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
    await DeviceInfoApi.deleteDeviceInfo(id)
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
    const data = await DeviceInfoApi.exportDeviceInfo(queryParams)
    download.excel(data, '设备信息.xls')
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
/* 表格样式美化 */
.custom-table {
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

/* 状态标签样式 */
.status-normal {
  color: #4caf50;
  background-color: rgba(76, 175, 80, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.status-abnormal {
  color: #f44336;
  background-color: rgba(244, 67, 54, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.status-running {
  color: #2196f3;
  background-color: rgba(33, 150, 243, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.status-stopped {
  color: #ff9800;
  background-color: rgba(255, 152, 0, 0.1);
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}
</style>
