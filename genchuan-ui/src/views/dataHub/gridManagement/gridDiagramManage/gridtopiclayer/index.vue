<template>
  <ContentWrap>
    <!-- 搜索表单 -->
    <el-form :model="queryParams" ref="queryFormRef" :inline="true" label-width="68px">
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
        <el-select v-model="queryParams.gridType" placeholder="全部" clearable class="!w-240px">
          <el-option v-for="item in gridTypeOptions" :key="item" :label="item" :value="item" />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button plain type="primary" @click="handleQuery">搜索</el-button>
        <el-button plain @click="resetQuery">重置</el-button>
        <el-button plain type="success" @click="() => openForm('create')">新增图层</el-button>
        <el-button
          plain
          type="warning"
          @click="openBatchShowHide"
          :disabled="selectedRows.length === 0"
        >
          批量显示/隐藏
        </el-button>
        <el-button plain :loading="exportLoading" type="success" @click="handleExport">
          导出
        </el-button>
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
          <el-switch
            v-model="row.displayStatus"
            active-value="1"
            inactive-value="0"
            @change="() => toggleDisplay(row)"
          />
        </template>
      </el-table-column>
      <el-table-column prop="layerWo" label="顺序" width="80" />
      <el-table-column prop="createUserId" label="创建人" />
      <el-table-column prop="createTime" label="创建时间" :formatter="dateFormatter" />
      <el-table-column fixed="right" label="操作" width="180">
        <template #default="{ row }">
          <el-button link type="primary" @click="() => openDrawer(row)">详情</el-button>
          <el-button link type="primary" @click="() => openForm('update', row.id)">编辑</el-button>
          <el-button link type="danger" @click="() => handleDelete(row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 地图预览组件 -->
    <div class="mt-4 h-800px">
      <GridPreviewMap :layers="filteredLayers" />
    </div>

    <!-- 分页 -->
    <Pagination
      :total="total"
      v-model:page="queryParams.pageNo"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 表单弹窗 -->
    <GridTopicLayerForm ref="formRef" @success="getList" />

    <!-- 抽屉 -->
    <GridTopicLayerDrawer ref="drawerRef" />
  </ContentWrap>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, computed } from 'vue'
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'
import {
  GridTopicLayerApi,
  GridTopicLayerVO
} from '@/api/dataHub/gridManagement/gridDiagramManage/gridtopiclayer'
import GridTopicLayerForm from './GridTopicLayerForm.vue'
import GridTopicLayerDrawer from './GridTopicLayerDrawer.vue'
import GridPreviewMap from './GridPreviewMap.vue'
import { useMessage } from '@/hooks/web/useMessage'
import { useI18n } from 'vue-i18n'
import { ElMessageBox } from 'element-plus'

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
  createTime: []
})
const gridTypeOptions = ['UNIT_GRID', 'MANAGE_GRID', 'EVAL_GRID']

const queryFormRef = ref()
const exportLoading = ref(false)
const formRef = ref()
const drawerRef = ref()
const selectedRows = ref<GridTopicLayerVO[]>([])

// 过滤并排序要显示的图层，确保返回新数组触发更新
const filteredLayers = computed(() => {
  return [...list.value]
    .filter((layer) => layer.displayStatus === '1')
    .sort((a, b) => (a.layerWo || 0) - (b.layerWo || 0))
})

/** 获取列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await GridTopicLayerApi.getGridTopicLayerPage(queryParams)
    list.value = data?.list ?? []
    total.value = data?.total ?? 0
  } catch (err) {
    console.error('获取图层列表失败', err)
    message.error(t('common.getListFailed'))
    list.value = []
    total.value = 0
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
  } catch (err) {
    console.error('删除图层失败', err)
    message.error(t('common.delFailed'))
  }
}

/** 导出 */
const handleExport = async () => {
  try {
    await message.exportConfirm()
    exportLoading.value = true
    const data = await GridTopicLayerApi.exportGridTopicLayer(queryParams)
    if (data instanceof Blob) {
      download.excel(data, '网格专题图层管理.xls')
    } else {
      const blob = new Blob([JSON.stringify(data)], { type: 'application/json' })
      download.excel(blob, '网格专题图层管理.xls')
    }
    message.success(t('common.exportSuccess'))
  } catch (err) {
    console.error('导出失败', err)
    message.error(t('common.exportFailed'))
  } finally {
    exportLoading.value = false
  }
}

const onSelectionChange = (rows: GridTopicLayerVO[]) => (selectedRows.value = rows)

/** 显示状态切换 */
const toggleDisplay = async (row: GridTopicLayerVO) => {
  try {
    await GridTopicLayerApi.updateGridTopicLayer(row)
    // 更新列表数据触发视图更新
    list.value = list.value.map((item) => (item.id === row.id ? { ...row } : item))
    message.success(`已设置为 ${row.displayStatus === '1' ? '显示' : '隐藏'}`)
  } catch (err) {
    console.error('更新显示状态失败', err)
    message.error(t('common.updateFailed'))
    // 失败时回滚状态
    list.value = list.value.map((item) => (item.id === row.id ? { ...item } : item))
  }
}

/** 批量显示/隐藏 */
const openBatchShowHide = async () => {
  let targetStatus = '1'
  try {
    await ElMessageBox.confirm('将所选图层设置为“显示”？', '批量设置', {
      confirmButtonText: '显示',
      cancelButtonText: '隐藏',
      distinguishCancelAndClose: true
    })
    targetStatus = '1'
  } catch (action) {
    if (action === 'cancel') {
      targetStatus = '0'
    } else {
      return
    }
  }

  try {
    // 批量更新状态
    for (const r of selectedRows.value) {
      await GridTopicLayerApi.updateGridTopicLayer({ ...r, displayStatus: targetStatus })
    }
    // 更新列表数据
    list.value = list.value.map((item) =>
      selectedRows.value.some((r) => r.id === item.id)
        ? { ...item, displayStatus: targetStatus }
        : item
    )
    message.success(`批量设置为 ${targetStatus === '1' ? '显示' : '隐藏'} 完成`)
  } catch (err) {
    console.error('批量更新失败', err)
    message.error(t('common.updateFailed'))
    getList() // 失败时重新获取列表
  }
}

/** 打开抽屉 */
const openDrawer = (row: GridTopicLayerVO) => drawerRef.value.open(row)

onMounted(getList)
</script>
