<template>
  <ContentWrap>
    <!-- 搜索表单 -->
    <!-- 搜索表单 -->
    <el-form
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="68px"
    >
      <el-form-item label="图层名称" prop="layerName">
        <el-input
          v-model="queryParams.layerName"
          placeholder="请输入图层名称"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item label="网格类型" prop="gridType">
        <el-select
          v-model="queryParams.gridType"
          placeholder="全部"
          clearable
          class="!w-240px"
        >
          <el-option
            v-for="item in gridTypeOptions"
            :key="item"
            :label="item"
            :value="item"
          />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button plain type="primary" @click="handleQuery">搜索</el-button>
        <el-button plain @click="resetQuery">重置</el-button>
        <el-button plain type="success" @click="() => openForm('create')">新增图层</el-button>
        <el-button plain type="warning" @click="openBatchShowHide" :disabled="selectedRows.length === 0">
          批量显示/隐藏
        </el-button>
        <el-button plain :loading="exportLoading" type="success" @click="handleExport">导出</el-button>
      </el-form-item>
    </el-form>


    <!-- 表格 -->
    <el-table ref="tableRef" v-loading="loading" :data="list" @selection-change="onSelectionChange">
      <el-table-column type="selection" width="50" />
      <el-table-column prop="layerName" label="图层名称" />
      <el-table-column prop="gridType" label="网格类型" />
      <el-table-column prop="scale" label="比例尺" />
      <el-table-column prop="displayStatus" label="显示状态">
        <template #default="{ row }">
          <el-switch v-model="row.displayStatus" active-value="1" inactive-value="0" @change="() => toggleDisplay(row)" />
        </template>
      </el-table-column>
      <el-table-column prop="layerWo" label="顺序" width="80" />
      <el-table-column prop="createUserId" label="创建人" />
      <el-table-column prop="createTime" label="创建时间" :formatter="dateFormatter" />
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="{ row }">
          <el-button link type="primary" @click="() => openDrawer(row)">详情</el-button>
          <el-button link type="success" @click="() => openForm('update', row.id)">编辑</el-button>
          <el-button link type="danger" @click="() => handleDelete(row.id)">删除</el-button>
          <el-button link type="success" @click="() => previewLayer(row)">预览</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <Pagination :total="total" v-model:page="queryParams.pageNo" v-model:limit="queryParams.pageSize" @pagination="getList" />

    <!-- 表单弹窗 -->
    <GridTopicLayerForm ref="formRef" @success="getList" />

    <!-- 抽屉 -->
    <GridTopicLayerDrawer ref="drawerRef" />

    <!-- 预览 -->
    <el-dialog v-model="previewDialogVisible" title="图层预览" width="80%">
      <GridPreviewMap :layers="previewLayers" />
      <template #footer>
        <el-button @click="downloadPreviewAsJson">导出预览为JSON</el-button>
        <el-button @click="previewDialogVisible = false">关闭</el-button>
      </template>
    </el-dialog>
  </ContentWrap>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import { GridTopicLayerApi, GridTopicLayerVO } from '@/api/dataHub/gridManagement/gridDiagramManage/gridtopiclayer'
import GridTopicLayerForm from './GridTopicLayerForm.vue'
import GridTopicLayerDrawer from './GridTopicLayerDrawer.vue'
import GridPreviewMap from './GridPreviewMap.vue'

defineOptions({ name: 'GridTopicLayer' })

const message = useMessage()
const { t } = useI18n()

const loading = ref(true)
const list = ref<GridTopicLayerVO[]>([])
const total = ref(0)
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  layerName: undefined,
  gridType: undefined,
  scale: undefined,
  createTime: [],
})
const gridTypeOptions = ['UNIT_GRID', 'MANAGE_GRID', 'EVAL_GRID']

const queryFormRef = ref()
const exportLoading = ref(false)
const formRef = ref()
const drawerRef = ref()
const selectedRows = ref<GridTopicLayerVO[]>([])

const previewDialogVisible = ref(false)
const previewLayers = ref<GridTopicLayerVO[]>([])

/** 获取列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await GridTopicLayerApi.getGridTopicLayerPage(queryParams)
    list.value = data?.list ?? data
    total.value = data?.total ?? list.value.length
  } catch (err) {
    console.warn('接口异常，使用模拟数据', err)
    list.value = mockService.getMockList()
    total.value = list.value.length
  } finally {
    loading.value = false
  }
}

/** 查询与重置 */
const handleQuery = () => {
  queryParams.pageNo = 1
  getList()
}
const resetQuery = () => {
  queryFormRef.value?.resetFields?.()
  queryParams.layerName = undefined
  queryParams.gridType = undefined
  handleQuery()
  handleQuery()
}

/** 表单 */
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 删除 */
const handleDelete = async (id: number) => {
  try {
    await message.delConfirm()
    await GridTopicLayerApi.deleteGridTopicLayer(id)
    message.success(t('common.delSuccess'))
    getList()
  } catch {
    mockService.deleteMock(id)
    message.success('已使用模拟删除')
    getList()
  }
}

/** 导出 */
const handleExport = async () => {
  try {
    await message.exportConfirm()
    exportLoading.value = true
    // ✅ 真实导出
    const data = await GridTopicLayerApi.exportGridTopicLayer(queryParams)
    if (data instanceof Blob) {
      download.excel(data, '网格专题图层管理.xls')
    } else {
      // ✅ 如果接口返回 JSON 而不是 Blob，则手动转换
      const blob = new Blob([JSON.stringify(data)], { type: 'application/json' })
      download.excel(blob, '网格专题图层管理.xls')
    }
  } catch (err) {
    console.warn('导出接口异常，导出JSON', err)
    // ✅ 兜底逻辑：模拟导出 JSON 文件
    const blob = new Blob([JSON.stringify(list.value)], { type: 'application/json' })
    download.json(blob, '网格专题图层管理.json')
  } finally {
    exportLoading.value = false
  }
}

const onSelectionChange = (rows: GridTopicLayerVO[]) => (selectedRows.value = rows)

/** 显示状态切换 */
/** 显示状态切换 */
const toggleDisplay = async (row: GridTopicLayerVO) => {
  try {
    await GridTopicLayerApi.updateGridTopicLayer(row)
    message.success(`已设置为 ${row.displayStatus === '1' ? '显示' : '隐藏'}`)
  } catch (err) {
    console.warn('接口异常，使用模拟更新', err)
    mockService.updateMock(row.id, row)
    message.success(`模拟更新为 ${row.displayStatus === '1' ? '显示' : '隐藏'}`)
  }
}


/** 批量显示/隐藏 */
/** 批量显示/隐藏 */
const openBatchShowHide = async () => {
  let targetStatus = '1' // 默认显示
  try {
    await ElMessageBox.confirm('将所选图层设置为“显示”？', '批量设置', {
      confirmButtonText: '显示',
      cancelButtonText: '隐藏',
      distinguishCancelAndClose: true,
    })
    targetStatus = '1' // 点击确认
  } catch (action) {
    // 如果点击取消按钮
    if (action === 'cancel') {
      targetStatus = '0'
    } else {
      return // 点击关闭直接退出
    }
  }

  // 批量更新状态
  for (const r of selectedRows.value) {
    r.displayStatus = targetStatus
    try {
      await GridTopicLayerApi.updateGridTopicLayer(r)
    } catch {
      mockService.updateMock(r.id, r)
    }
  }
  message.success(`批量设置为 ${targetStatus === '1' ? '显示' : '隐藏'} 完成`)
  await getList()
}


/** 打开抽屉 */
const openDrawer = (row: GridTopicLayerVO) => drawerRef.value.open(row)

/** 预览 */
const previewLayer = (row?: GridTopicLayerVO) => {
  previewLayers.value = row ? [row] : list.value.filter((r) => r.displayStatus === '1')
  previewLayers.value.sort((a, b) => (a.layerWo ?? 0) - (b.layerWo ?? 0))
  previewDialogVisible.value = true
}
const downloadPreviewAsJson = () => {
  const blob = new Blob([JSON.stringify(previewLayers.value)], { type: 'application/json' })
  download.json(blob, `preview_layers_${Date.now()}.json`)
}

/** 模拟数据服务 */
const mockService = {
  _list: [
    {
      id: 1,
      layerId: 'L1',
      layerName: '单元图层A',
      gridType: '单元',
      scale: '1:1000',
      boundaryStyleId: 'b1',
      annotateStyleId: 'a1',
      displayStatus: '1',
      layerWo: 10,
      createUserId: '张三',
      remark: '',
      extCat1: '',
      extCat2: '',
      extCommon1: '',
      extCommon2: '',
      createTime: new Date(),
    },
  ],
  getMockList() {
    return this._list
  },
  deleteMock(id: number) {
    this._list = this._list.filter((i) => i.id !== id)
  },
  updateMock(id: number, data: any) {
    this._list = this._list.map((i) => (i.id === id ? { ...i, ...data } : i))
  },
}

onMounted(getList)
</script>
