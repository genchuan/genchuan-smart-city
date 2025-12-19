<template>
  <ContentWrap class="eval-grid-page">
    <!-- 查询条件区 -->
    <el-form :inline="true" :model="queryParams" class="mb-3 flex-wrap">
      <el-form-item label="评价网格名称">
        <el-input
          v-model="queryParams.evalGridName"
          placeholder="请输入评价网格名称"
          clearable
          class="!w-220px"
        />
      </el-form-item>

      <el-form-item label="乡镇">
        <TownSelect v-model="queryParams.townStreetId" clearable class="!w-200px" />
      </el-form-item>

      <el-form-item label="类型">
        <GridTypeSelect v-model="queryParams.gridType" clearable class="!w-150px" />
      </el-form-item>

      <el-form-item label="所含管理网格数">
        <el-select v-model="queryParams.manaRange" placeholder="请选择" class="!w-160px">
          <el-option label="全部" value="" />
          <el-option label="1-3个" value="1-3" />
          <el-option label="4-6个" value="4-6" />
          <el-option label="7个及以上" value="7+" />
        </el-select>
      </el-form-item>

      <el-form-item label="划分时间">
        <el-date-picker
          v-model="queryParams.divTime"
          type="datetime"
          placeholder="请选择划分时间"
          value-format="YYYY-MM-DD HH:mm:ss"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" plain @click="handleQuery">查询</el-button>
        <el-button plain @click="resetQuery">重置</el-button>
        <el-button type="primary" plain @click="openFormDialog()">新增网格</el-button>
        <el-button type="success" plain @click="handleExport" :loading="exportLoading"
          >导出
        </el-button>
      </el-form-item>
    </el-form>

    <!-- 操作按钮区 -->
    <!--    <div class="mb-4 flex justify-end space-x-2">-->
    <!--      <el-button type="primary" plain @click="openFormDialog()">新增网格</el-button>-->
    <!--      <el-button type="success" plain @click="handleExport" :loading="exportLoading"-->
    <!--        >导出-->
    <!--      </el-button>-->
    <!--    </div>-->

    <!-- 数据表格区域 -->
    <div class="table-container">
      <el-table
        v-loading="loading"
        :data="tableData"
        stripe
        border
        height="calc(100vh - 360px)"
        style="width: 100%; table-layout: fixed"
        @sort-change="handleSortChange"
      >
        <el-table-column prop="evalGridName" label="网格名称" min-width="160" />
        <el-table-column prop="townStreetId" label="所属乡镇" width="150" />
        <el-table-column prop="gridType" label="类型" width="120" />
        <el-table-column prop="area" label="面积(㎡)" width="140" sortable="custom" />
        <el-table-column prop="divTime" label="划分时间" width="200" :formatter="dateFormatter" />
        <el-table-column label="操作" width="220" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link @click="openDetail(row.id)">详情</el-button>
            <el-button type="primary" link @click="openFormDialog(row.id)">编辑</el-button>
            <el-button type="danger" link @click="handleDelete(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 分页 -->
    <!--    <div class="pagination-container">-->
    <!--      <el-pagination-->
    <!--          v-model:current-page="queryParams.pageNo"-->
    <!--          v-model:page-size="queryParams.pageSize"-->
    <!--          :page-sizes="[10, 20, 50, 100]"-->
    <!--          layout="total, sizes, prev, pager, next, jumper"-->
    <!--          :total="total"-->
    <!--          @size-change="loadData"-->
    <!--          @current-change="loadData"-->
    <!--      />-->
    <!--    </div>-->

    <!-- 分页 -->
    <Pagination
      v-model:page="queryParams.pageNo"
      v-model:limit="queryParams.pageSize"
      :total="total"
      @pagination="loadData"
    />

    <!-- 弹窗组件 -->
    <EvalGridDivForm
      v-if="formVisible"
      v-model:visible="formVisible"
      :id="currentId"
      :key="currentId"
      @refresh="loadData"
    />
    <EvalGridDetail v-if="detailVisible" v-model:visible="detailVisible" :id="detailId" />
  </ContentWrap>
</template>

<script lang="ts">
import { defineComponent, reactive, ref, onMounted } from 'vue'
import { ElMessageBox, ElMessage } from 'element-plus'
import { EvalGridDivApi } from '@/api/dataHub/gridManagement/divideConfig/evalgriddiv'
import EvalGridDivForm from './EvalGridDivForm.vue'
import EvalGridDetail from './EvalGridDetail.vue'
import TownSelect from './components/TownSelect.vue'
import GridTypeSelect from './components/GridTypeSelect.vue'
import { dateFormatter } from '@/utils/formatTime'
import download from '@/utils/download'

export default defineComponent({
  name: 'EvalGridDivIndex',
  components: { TownSelect, GridTypeSelect, EvalGridDivForm, EvalGridDetail },
  setup() {
    const queryParams = reactive({
      evalGridName: '',
      townStreetId: '',
      gridType: '',
      manaRange: '',
      divTime: '',
      sortField: '',
      sortOrder: '',
      pageNo: 1,
      pageSize: 10
    })

    const loading = ref(false)
    const tableData = ref<any[]>([])
    const total = ref(0)
    const formVisible = ref(false)
    const detailVisible = ref(false)
    const currentId = ref<number | undefined>(undefined)
    const detailId = ref<number | undefined>(undefined)
    const exportLoading = ref(false) // 导出的加载中

    const parseManaRange = (range: string) => {
      if (!range) return { minUnits: undefined, maxUnits: undefined }
      if (range === '7+') return { minUnits: 7, maxUnits: undefined }
      const [min, max] = range.split('-').map(Number)
      return { minUnits: min, maxUnits: max }
    }

    const loadData = async () => {
      loading.value = true
      try {
        if (queryParams.manaRange) {
          const { minUnits, maxUnits } = parseManaRange(queryParams.manaRange)
          const res = await EvalGridDivApi.listByManaCount({
            townStreetId: queryParams.townStreetId,
            minUnits,
            maxUnits
          })
          tableData.value = res || []
          total.value = res.length || 0
        } else {
          const params: any = { ...queryParams }
          if (params.divTime) params.divTime = [params.divTime]
          else params.divTime = undefined
          const res = await EvalGridDivApi.getEvalGridDivPage(params)
          tableData.value = res.list || []
          total.value = res.total || 0
        }
      } finally {
        loading.value = false
      }
    }

    const handleQuery = () => {
      queryParams.pageNo = 1
      loadData()
    }

    const resetQuery = () => {
      queryParams.evalGridName = ''
      queryParams.townStreetId = ''
      queryParams.gridType = ''
      queryParams.manaRange = ''
      queryParams.divTime = ''
      queryParams.sortField = ''
      queryParams.sortOrder = ''
      handleQuery()
    }

    const handleSortChange = ({ prop, order }: any) => {
      if (prop === 'area') {
        queryParams.sortField = prop
        queryParams.sortOrder = order === 'ascending' ? 'asc' : order === 'descending' ? 'desc' : ''
        loadData()
      }
    }

    const openFormDialog = (id?: number) => {
      currentId.value = id
      formVisible.value = true
    }

    const handleDelete = async (id: number) => {
      try {
        await ElMessageBox.confirm('确认删除该评价网格吗？', '提示', { type: 'warning' })
        await EvalGridDivApi.deleteEvalGridDiv(id)
        ElMessage.success('删除成功')
        await loadData()
      } catch (err) {
        if (err !== 'cancel') console.error(err)
      }
    }

    const openDetail = (id: number) => {
      detailId.value = id
      detailVisible.value = true
    }
    const message = useMessage() // 消息弹窗
    const handleExport = async () => {
      try {
        // 导出的二次确认
        await message.exportConfirm()
        // 发起导出
        exportLoading.value = true
        const data = await EvalGridDivApi.exportEvalGridDiv(queryParams)
        download.excel(data, '评价网格划分.xls')
      } catch {
      } finally {
        exportLoading.value = false
      }
    }

    onMounted(loadData)

    return {
      queryParams,
      tableData,
      total,
      loading,
      loadData,
      resetQuery,
      handleQuery,
      openFormDialog,
      handleDelete,
      handleExport,
      formVisible,
      currentId,
      detailVisible,
      detailId,
      openDetail,
      handleSortChange,
      dateFormatter
    }
  }
})
</script>

<style scoped>
.eval-grid-page {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.table-container {
  flex: 1;
  overflow: auto;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 0 6px rgba(0, 0, 0, 0.05);
}

.el-table {
  width: 100%;
  font-size: 14px;
  table-layout: fixed !important;
  word-break: break-all;
}

.el-table th,
.el-table td {
  text-align: center;
  white-space: normal !important;
  word-wrap: break-word;
  padding: 10px 6px;
}

.pagination-container {
  display: flex;
  justify-content: flex-end;
  margin-top: 16px;
  padding-bottom: 8px;
}

.mb-3 {
  margin-bottom: 0;
}

.mb-4 {
  margin-bottom: 16px;
}
</style>
