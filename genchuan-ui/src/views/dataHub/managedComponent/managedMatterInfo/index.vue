<template>
  <div class="managed-matter-page">
    <!-- 查询条件 -->
    <el-card class="mb-4">
      <el-form :inline="true" :model="searchForm" label-width="90px" class="search-form">
        <el-form-item label="事项名称" class="form-item-wide">
          <el-input v-model="searchForm.matter_name" placeholder="输入事项名称或标识码" clearable />
        </el-form-item>
        <el-form-item label="状态" class="form-item-wide">
          <el-select v-model="searchForm.matter_status" placeholder="选择状态" clearable>
            <el-option label="待处置" value="待处置" />
            <el-option label="处置中" value="处置中" />
            <el-option label="已办结" value="已办结" />
            <el-option label="已驳回" value="已驳回" />
          </el-select>
        </el-form-item>
        <el-form-item label="网格" class="form-item-wide">
          <el-select v-model="searchForm.grid_name" placeholder="选择网格" clearable>
            <el-option v-for="g in gridList" :key="g.id" :label="g.name" :value="g.name" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">查询</el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 工具栏 -->
    <div class="toolbar mb-4 flex gap-2">
      <el-button type="primary" @click="openAdd">新增</el-button>
      <el-button type="danger" @click="handleBatchReject">批量驳回</el-button>
      <el-upload
        :show-file-list="false"
        accept=".xlsx, .xls"
        :on-change="handleImportExcel"
      >
        <el-button>导入 Excel</el-button>
      </el-upload>
      <el-button type="success" @click="exportExcel">导出 Excel</el-button>
      <el-button type="info" @click="exportPDF">导出 PDF</el-button>
    </div>

    <!-- 数据表格 -->
    <el-table
      v-loading="loading"
      :data="filteredData"
      border
      stripe
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="50" />
      <el-table-column prop="matter_code" label="标识码" width="180" />
      <el-table-column prop="matter_name" label="事项名称" width="200" />
      <el-table-column prop="matter_minor_name" label="所属小类" width="120" />
      <el-table-column prop="incident_location" label="事发位置" />
      <el-table-column prop="matter_status" label="状态" width="100">
        <template #default="scope">
          <el-tag
            :type="statusTagType(scope.row.matter_status)"
            disable-transitions
          >
            {{ scope.row.matter_status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="dept_name" label="主管部门" width="160" />
      <el-table-column prop="matter_level" label="等级" width="80" />
      <el-table-column label="操作" fixed="right" width="220">
        <template #default="scope">
          <el-button size="small" type="primary" @click="openEdit(scope.row)">编辑</el-button>
          <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.row)"
            :disabled="scope.row.matter_status !== '待处置'"
          >
            删除
          </el-button>
          <el-button
            size="small"
            type="info"
            @click="showMap(scope.row)"
          >
            地图定位
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      v-model:current-page="pagination.page"
      v-model:page-size="pagination.pageSize"
      :total="filteredData.length"
      layout="total, prev, pager, next, sizes"
      class="mt-4 text-right"
    />

    <!-- 表单弹窗 -->
    <el-dialog v-model:visible="formVisible" :title="dialogTitle" width="800px">
      <ManagedMatterInfoForm
        v-if="formVisible"
        :form-data="currentRow"
        @submit="handleSave"
        @cancel="formVisible = false"
      />
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, computed } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import ManagedMatterInfoForm from './ManagedMatterInfoForm.vue'
import * as XLSX from 'xlsx'
import jsPDF from 'jspdf'
import autoTable from 'jspdf-autotable'

const loading = ref(false)
const formVisible = ref(false)
const dialogTitle = ref('新增事项')
const currentRow = ref({})
const selectedRows = ref([])

const pagination = reactive({ page: 1, pageSize: 20 })

// 模拟数据 10 条
const tableData = ref([
  { manage_matter_id:'1', matter_code:'1101010100100001', matter_name:'XX路私搭乱建', matter_minor_name:'违建', incident_location:'XX路与YY路交叉口东北侧', matter_status:'待处置', dept_name:'城管局', matter_level:'一级', grid_name:'网格一' },
  { manage_matter_id:'2', matter_code:'1101010100100002', matter_name:'YY街占道经营', matter_minor_name:'占道', incident_location:'YY街中心路段', matter_status:'处置中', dept_name:'城管局', matter_level:'二级', grid_name:'网格二' },
  { manage_matter_id:'3', matter_code:'1101010100100003', matter_name:'ZZ路违停车辆', matter_minor_name:'违停', incident_location:'ZZ路口东侧', matter_status:'已办结', dept_name:'交警队', matter_level:'一级', grid_name:'网格一' },
  { manage_matter_id:'4', matter_code:'1101010100100004', matter_name:'AA小区环境卫生', matter_minor_name:'环境', incident_location:'AA小区东门', matter_status:'待处置', dept_name:'城管局', matter_level:'三级', grid_name:'网格二' },
  { manage_matter_id:'5', matter_code:'1101010100100005', matter_name:'BB路交通拥堵', matter_minor_name:'交通', incident_location:'BB路口西侧', matter_status:'处置中', dept_name:'交警队', matter_level:'二级', grid_name:'网格一' },
  { manage_matter_id:'6', matter_code:'1101010100100006', matter_name:'CC路路灯损坏', matter_minor_name:'设施', incident_location:'CC路中段', matter_status:'已办结', dept_name:'市政局', matter_level:'一级', grid_name:'网格二' },
  { manage_matter_id:'7', matter_code:'1101010100100007', matter_name:'DD街噪音投诉', matter_minor_name:'环境', incident_location:'DD街北段', matter_status:'待处置', dept_name:'环保局', matter_level:'二级', grid_name:'网格一' },
  { manage_matter_id:'8', matter_code:'1101010100100008', matter_name:'EE小区电梯故障', matter_minor_name:'设施', incident_location:'EE小区南门', matter_status:'处置中', dept_name:'物业管理', matter_level:'一级', grid_name:'网格二' },
  { manage_matter_id:'9', matter_code:'1101010100100009', matter_name:'FF路排水堵塞', matter_minor_name:'设施', incident_location:'FF路口北侧', matter_status:'已办结', dept_name:'市政局', matter_level:'二级', grid_name:'网格一' },
  { manage_matter_id:'10', matter_code:'1101010100100010', matter_name:'GG街非法广告', matter_minor_name:'广告', incident_location:'GG街中段', matter_status:'待处置', dept_name:'城管局', matter_level:'三级', grid_name:'网格二' }
])

// 查询条件
const searchForm = reactive({ matter_name:'', matter_status:'', grid_name:'' })
const gridList = ref([{id:'g1', name:'网格一'}, {id:'g2', name:'网格二'}])

// 筛选数据
const filteredData = computed(() => {
  return tableData.value.filter(item => {
    return (
      (!searchForm.matter_name || item.matter_name.includes(searchForm.matter_name) || item.matter_code.includes(searchForm.matter_name)) &&
      (!searchForm.matter_status || item.matter_status === searchForm.matter_status) &&
      (!searchForm.grid_name || item.grid_name === searchForm.grid_name)
    )
  })
})

// 状态颜色
const statusTagType = (status) => {
  switch(status){
    case '待处置': return 'info'
    case '处置中': return 'warning'
    case '已办结': return 'success'
    case '已驳回': return 'danger'
    default: return ''
  }
}

// 方法
const handleSearch = () => { ElMessage.success('查询成功（前端筛选）') }
const handleReset = () => { searchForm.matter_name=''; searchForm.matter_status=''; searchForm.grid_name='' }
const handleSelectionChange = val => { selectedRows.value = val }
const openAdd = () => { dialogTitle.value='新增事项'; currentRow.value={}; formVisible.value=true }
const openEdit = row => { dialogTitle.value='编辑事项'; currentRow.value={...row}; formVisible.value=true }
const handleSave = formData => {
  if(formData.manage_matter_id){
    const idx = tableData.value.findIndex(d => d.manage_matter_id===formData.manage_matter_id)
    tableData.value[idx]=formData
    ElMessage.success('修改成功')
  } else {
    formData.manage_matter_id = Date.now().toString()
    tableData.value.unshift(formData)
    ElMessage.success('新增成功')
  }
  formVisible.value=false
}
const handleDelete = row => {
  ElMessageBox.confirm(`确定删除事项【${row.matter_name}】吗？`, '提示', { type:'warning' })
    .then(()=> {
      tableData.value = tableData.value.filter(d=>d.manage_matter_id!==row.manage_matter_id)
      ElMessage.success('删除成功')
    })
}
const handleBatchReject = () => {
  if(!selectedRows.value.length) return ElMessage.warning('请选择要驳回的事项')
  selectedRows.value.forEach(r=>r.matter_status='已驳回')
  ElMessage.success('批量驳回成功')
}

// 导入 Excel
const handleImportExcel = file => {
  const reader = new FileReader()
  reader.onload = e=>{
    const data = new Uint8Array(e.target.result)
    const workbook = XLSX.read(data,{type:'array'})
    const ws = workbook.Sheets[workbook.SheetNames[0]]
    const json = XLSX.utils.sheet_to_json(ws)
    json.forEach(row=>{
      row.manage_matter_id = Date.now().toString()+Math.random().toString(16).slice(2)
      tableData.value.push(row)
    })
    ElMessage.success(`成功导入 ${json.length} 条数据`)
  }
  reader.readAsArrayBuffer(file.raw)
}

// 导出 Excel
const exportExcel = () => {
  const ws = XLSX.utils.json_to_sheet(tableData.value)
  const wb = XLSX.utils.book_new()
  XLSX.utils.book_append_sheet(wb, ws, '管理事项')
  XLSX.writeFile(wb,'管理事项.xlsx')
  ElMessage.success('Excel 导出成功')
}

// 导出 PDF
const exportPDF = () => {
  const doc = new jsPDF()
  autoTable(doc,{
    head:[['标识码','事项名称','状态','主管部门','等级']],
    body: tableData.value.map(i=>[i.matter_code,i.matter_name,i.matter_status,i.dept_name,i.matter_level])
  })
  doc.save('管理事项.pdf')
  ElMessage.success('PDF 导出成功')
}

// 地图定位（模拟）
const showMap = row => { ElMessage.info(`定位至：${row.incident_location}`) }
</script>

<style scoped>
.managed-matter-page { padding:16px }
.mb-4 { margin-bottom:16px }
.flex { display:flex }
.gap-2 { gap:8px }
.search-form .form-item-wide { width:250px; margin-right:16px }
</style>
