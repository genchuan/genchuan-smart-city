<template>
  <div class="managed-major-minor-rel">
    <!-- 查询工具栏 -->
    <div class="toolbar">
      <el-select v-model="filters.major_id" placeholder="选择大类" clearable style="width: 200px">
        <el-option v-for="item in majorList" :key="item.id" :label="item.name" :value="item.id" />
      </el-select>
      <el-select v-model="filters.rel_status" placeholder="关联状态" clearable style="width: 150px">
        <el-option label="有效" :value="1" />
        <el-option label="无效" :value="0" />
      </el-select>
      <el-button type="primary" @click="handleQuery">查询</el-button>
      <el-button type="success" @click="handleExport">导出</el-button>
    </div>

    <!-- 数据表格 -->
    <el-table :data="tableData" style="width: 100%" stripe>
      <el-table-column prop="major_name" label="大类名称" width="180" />
      <el-table-column prop="minor_name" label="小类名称" width="180" />
      <el-table-column prop="rel_status" label="关联状态" width="120">
        <template #default="scope">
          <el-tag :type="scope.row.rel_status === 1 ? 'success' : 'info'">
            {{ scope.row.rel_status === 1 ? '有效' : '无效' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="rel_time" label="关联时间" width="200" />
      <el-table-column prop="operate_user" label="操作人" width="120" />
      <el-table-column prop="remark" label="备注" />
      <el-table-column label="操作" width="220">
        <template #default="scope">
          <el-button v-if="scope.row.rel_status === 1" size="small" type="danger" @click="openUnrelDialog(scope.row)">解除关联</el-button>
          <el-button v-else size="small" type="primary" @click="handleRelink(scope.row)">重新关联</el-button>
          <el-button size="small" type="info" @click="openDetailDrawer(scope.row)">查看</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      v-model:current-page="pagination.page"
      v-model:page-size="pagination.pageSize"
      :total="pagination.total"
      layout="total, sizes, prev, pager, next, jumper"
      @current-change="handlePageChange"
      @size-change="handlePageSizeChange"
    />

    <!-- 解除关联表单 -->
    <el-dialog v-model:visible="unrelDialogVisible" title="解除关联" width="400px">
      <ManagedMajorMinorRelForm v-model:formData="formData" />
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="unrelDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleUnrelConfirm">确定</el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 抽屉详情页 -->
    <el-drawer
      v-model="detailVisible"
      title="关联详情"
      direction="rtl"
      size="40%"
      :with-header="true"
      :close-on-click-modal="true"
    >
      <el-descriptions :column="1" border>
        <el-descriptions-item label="大类名称">{{ detailData.major_name }}</el-descriptions-item>
        <el-descriptions-item label="小类名称">{{ detailData.minor_name }}</el-descriptions-item>
        <el-descriptions-item label="关联状态">{{ detailData.rel_status === 1 ? '有效' : '无效' }}</el-descriptions-item>
        <el-descriptions-item label="关联时间">{{ detailData.rel_time }}</el-descriptions-item>
        <el-descriptions-item label="解除时间">{{ detailData.unrel_time || '—' }}</el-descriptions-item>
        <el-descriptions-item label="操作人">{{ detailData.operate_user }}</el-descriptions-item>
        <el-descriptions-item label="备注">{{ detailData.remark || '—' }}</el-descriptions-item>
      </el-descriptions>
    </el-drawer>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import * as XLSX from 'xlsx'
import { saveAs } from 'file-saver'
import ManagedMajorMinorRelForm from './ManagedMajorMinorRelForm.vue'

const filters = reactive({ major_id: '', rel_status: '' })
const allData = ref([])
const tableData = ref([])
const majorList = ref([])
const pagination = reactive({ page: 1, pageSize: 10, total: 0 })

const unrelDialogVisible = ref(false)
const detailVisible = ref(false)
const formData = reactive({})
const detailData = reactive({})

// 加载模拟数据
const loadMajorList = () => {
  majorList.value = [
    { id: 'major_001', name: '电机系统' },
    { id: 'major_002', name: '电控系统' },
    { id: 'major_003', name: '传动系统' }
  ]
}

const loadAllData = () => {
  allData.value = [
    { major_id: 'major_001', major_name: '电机系统', minor_name: '电机轴承', rel_status: 1, rel_time: '2025-10-12 09:15:00', operate_user: 'admin', remark: '初始化关联' },
    { major_id: 'major_002', major_name: '电控系统', minor_name: '传感模块', rel_status: 0, rel_time: '2025-09-01 15:00:00', operate_user: 'system', remark: '调整归属' },
    { major_id: 'major_003', major_name: '传动系统', minor_name: '离合组件', rel_status: 1, rel_time: '2025-08-18 10:00:00', operate_user: 'admin', remark: '系统自动关联' }
  ]
}

// 查询逻辑
const handleQuery = () => {
  const { major_id, rel_status } = filters
  let result = [...allData.value]
  if (major_id) result = result.filter(item => item.major_id === major_id)
  if (rel_status !== '' && rel_status !== null && rel_status !== undefined) result = result.filter(item => item.rel_status === rel_status)

  pagination.total = result.length
  const start = (pagination.page - 1) * pagination.pageSize
  const end = start + pagination.pageSize
  tableData.value = result.slice(start, end)

  if (!result.length) ElMessage.info('未查询到符合条件的记录')
}

// 分页
const handlePageChange = page => { pagination.page = page; handleQuery() }
const handlePageSizeChange = size => { pagination.pageSize = size; handleQuery() }

// 导出
const handleExport = () => {
  if (!tableData.value.length) { ElMessage.warning('暂无可导出数据'); return }
  const exportData = tableData.value.map(item => ({
    '大类名称': item.major_name,
    '小类名称': item.minor_name,
    '关联状态': item.rel_status === 1 ? '有效' : '无效',
    '关联时间': item.rel_time,
    '操作人': item.operate_user,
    '备注': item.remark || ''
  }))
  const worksheet = XLSX.utils.json_to_sheet(exportData)
  const workbook = XLSX.utils.book_new()
  XLSX.utils.book_append_sheet(workbook, worksheet, '大类小类关联记录')
  const wbout = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' })
  saveAs(new Blob([wbout], { type: 'application/octet-stream' }), `管理部件大类小类关联记录_${new Date().toLocaleDateString()}.xlsx`)
  ElMessage.success('导出成功')
}

// 解除关联（演示效果）
const openUnrelDialog = row => { Object.assign(formData, row); unrelDialogVisible.value = true }
const handleUnrelConfirm = () => {
  const item = tableData.value.find(i => i.major_id === formData.major_id && i.minor_name === formData.minor_name)
  if (item) {
    item.rel_status = 0
    item.remark = formData.remark || '演示备注'
    item.unrel_time = new Date().toLocaleString()
  }
  unrelDialogVisible.value = false
  ElMessage.success('已解除关联（演示效果）')
}

// 重新关联（演示效果）
const handleRelink = row => {
  const item = tableData.value.find(i => i.major_id === row.major_id && i.minor_name === row.minor_name)
  if (item) { item.rel_status = 1; item.unrel_time = ''; item.remark = '演示重新关联' }
  ElMessage.success(`已重新关联小类：${row.minor_name}（演示效果）`)
}

// 查看抽屉
const openDetailDrawer = row => { Object.assign(detailData, row); detailVisible.value = true }

onMounted(() => { loadMajorList(); loadAllData(); handleQuery() })
</script>

<style scoped>
.managed-major-minor-rel { padding: 20px; }
.toolbar { margin-bottom: 20px; display: flex; gap: 10px; align-items: center; }
</style>
