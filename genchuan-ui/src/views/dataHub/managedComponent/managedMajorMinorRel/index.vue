<template>
  <div class="managed-major-minor-rel">
    <!-- 查询工具栏：包含排序选择器 -->
    <div class="toolbar">
      <el-select
        v-model="filters.major_id"
        placeholder="选择大类"
        clearable
        size="medium"
        style="width: 220px"
      >
        <el-option v-for="item in majorList" :key="item.id" :label="item.name" :value="item.id" />
      </el-select>

      <el-select
        v-model="filters.rel_status"
        placeholder="关联状态"
        clearable
        size="medium"
        style="width: 180px"
      >
        <el-option label="有效" :value="1" />
        <el-option label="无效" :value="0" />
      </el-select>

      <!-- 排序规则选择器：默认关联时间降序 -->
      <el-select
        v-model="filters.sortRule"
        placeholder="排序规则"
        size="medium"
        style="width: 220px"
      >
        <el-option label="关联时间（最新优先）" :value="1" />
        <el-option label="解除关联时间（最新优先）" :value="2" />
      </el-select>

      <el-button type="primary" size="medium" @click="handleQuery">查询</el-button>
      <el-button type="success" size="medium" @click="handleExport">导出</el-button>
      <el-button type="primary" size="medium" @click="handleAdd">新增</el-button>
    </div>

    <!-- 数据表格：新增“解除时间”列 -->
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
      <el-table-column prop="unrel_time" label="解除时间" width="200" />
      <el-table-column prop="operate_user" label="操作人" width="120" />
      <el-table-column prop="remark" label="备注" />
      <el-table-column label="操作" width="220">
        <template #default="scope">
          <el-button
            v-if="scope.row.rel_status === 1"
            size="small"
            type="danger"
            @click="handleUnrel(scope.row)"
          >
            解除关联
          </el-button>
          <el-button
            v-else
            size="small"
            type="primary"
            @click="handleRelink(scope.row)"
          >
            重新关联
          </el-button>
          <el-button size="small" type="info" @click="openDetailDrawer(scope.row)">
            查看
          </el-button>
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
      style="margin-top: 20px; text-align: right"
    />

    <!-- 详情抽屉 -->
    <el-drawer
      v-model="detailVisible"
      title="关联详情"
      direction="rtl"
      size="40%"
      :with-header="true"
      :close-on-click-modal="true"
    >
      <el-descriptions :column="1" border>
        <el-descriptions-item label="大类名称">{{ detailData.major_name || '—' }}</el-descriptions-item>
        <el-descriptions-item label="小类名称">{{ detailData.minor_name || '—' }}</el-descriptions-item>
        <el-descriptions-item label="关联状态">{{ detailData.rel_status === 1 ? '有效' : detailData.rel_status === 0 ? '无效' : '—' }}</el-descriptions-item>
        <el-descriptions-item label="关联时间">{{ detailData.rel_time || '—' }}</el-descriptions-item>
        <el-descriptions-item label="解除时间">{{ detailData.unrel_time || '—' }}</el-descriptions-item>
        <el-descriptions-item label="操作人">{{ detailData.operate_user || '—' }}</el-descriptions-item>
        <el-descriptions-item label="备注">{{ detailData.remark || '—' }}</el-descriptions-item>
      </el-descriptions>
    </el-drawer>

    <!-- 新增关联抽屉 -->
    <el-drawer
      v-model="addVisible"
      title="新增大类小类关联"
      direction="rtl"
      size="40%"
      :with-header="true"
      :close-on-click-modal="true"
    >
      <el-form :model="addForm" label-width="120px" style="margin-top: 20px;" :rules="addRules" ref="addFormRef">
        <el-form-item label="选择大类" prop="major_id">
          <el-select
            v-model="addForm.major_id"
            placeholder="请选择大类"
            style="width: 100%"
            clearable
          >
            <el-option v-for="item in majorList" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="小类名称" prop="minor_name">
          <el-input
            v-model="addForm.minor_name"
            placeholder="请输入小类名称"
            maxlength="50"
            show-word-limit
          />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input
            v-model="addForm.remark"
            placeholder="可选：输入关联备注信息"
            type="textarea"
            rows="3"
            maxlength="200"
            show-word-limit
          />
        </el-form-item>
        <el-form-item style="text-align: right; margin-top: 30px;">
          <el-button @click="addVisible.value = false">取消</el-button>
          <el-button type="primary" @click="handleAddSubmit">提交新增</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, getCurrentInstance } from 'vue'
import { ElMessage, ElForm } from 'element-plus'
import * as XLSX from 'xlsx'
import { saveAs } from 'file-saver'

// 表单引用（用于新增表单校验）
const { proxy } = getCurrentInstance()
const addFormRef = ref(null)

// 筛选条件：新增sortRule（1=关联时间降序，2=解除关联时间降序）
const filters = reactive({
  major_id: '',
  rel_status: '',
  sortRule: 1 // 默认按“关联时间降序”
})

// 数据源与表格数据
const allData = ref([])
const tableData = ref([])
const majorList = ref([])

// 分页配置
const pagination = reactive({
  page: 1,
  pageSize: 10,
  total: 0
})

// 详情抽屉配置
const detailVisible = ref(false)
const detailData = reactive({})

// 新增抽屉配置
const addVisible = ref(false)
const addForm = reactive({
  major_id: '',
  minor_name: '',
  remark: ''
})

// 新增表单校验规则
const addRules = reactive({
  major_id: [
    { required: true, message: '请选择大类', trigger: 'change' }
  ],
  minor_name: [
    { required: true, message: '请输入小类名称', trigger: 'blur' },
    { min: 2, max: 50, message: '小类名称长度需在2-50字符之间', trigger: 'blur' }
  ]
})

// 加载大类列表（模拟）
const loadMajorList = () => {
  majorList.value = [
    { id: 'major_001', name: '电机系统' },
    { id: 'major_002', name: '电控系统' },
    { id: 'major_003', name: '传动系统' },
    { id: 'major_004', name: '制动系统' },
    { id: 'major_005', name: '液压系统' },
    { id: 'major_006', name: '冷却系统' },
    { id: 'major_007', name: '燃油系统' },
    { id: 'major_008', name: '照明系统' },
    { id: 'major_009', name: '车身电子' },
    { id: 'major_010', name: '安全系统' }
  ]
}

// 加载初始数据（模拟：补全数据，包含不同解除时间的无效条目）
const loadAllData = () => {
  allData.value = [
    { major_id: 'major_001', major_name: '电机系统', minor_name: '电机轴承', rel_status: 1, rel_time: '2025-10-12 09:15:00', operate_user: 'admin', remark: '初始化关联', unrel_time: '' },
    { major_id: 'major_002', major_name: '电控系统', minor_name: '传感模块', rel_status: 0, rel_time: '2025-09-01 15:00:00', operate_user: 'system', remark: '调整归属', unrel_time: '2025-10-10 11:30:00' },
    { major_id: 'major_003', major_name: '传动系统', minor_name: '离合组件', rel_status: 1, rel_time: '2025-08-18 10:00:00', operate_user: 'admin', remark: '系统自动关联', unrel_time: '' },
    { major_id: 'major_004', major_name: '制动系统', minor_name: '刹车片', rel_status: 1, rel_time: '2025-10-01 14:00:00', operate_user: 'admin', remark: '', unrel_time: '' },
    { major_id: 'major_005', major_name: '液压系统', minor_name: '油泵', rel_status: 0, rel_time: '2025-09-15 10:30:00', operate_user: 'system', remark: '手动调整', unrel_time: '2025-10-15 09:20:00' },
    { major_id: 'major_006', major_name: '冷却系统', minor_name: '散热器', rel_status: 1, rel_time: '2025-08-22 11:20:00', operate_user: 'admin', remark: '', unrel_time: '' },
    { major_id: 'major_007', major_name: '燃油系统', minor_name: '燃油泵', rel_status: 1, rel_time: '2025-07-10 09:50:00', operate_user: 'admin', remark: '自动生成', unrel_time: '' },
    { major_id: 'major_008', major_name: '照明系统', minor_name: 'LED大灯', rel_status: 0, rel_time: '2025-06-05 15:10:00', operate_user: 'system', remark: '替换型号', unrel_time: '2025-10-08 16:40:00' },
    { major_id: 'major_009', major_name: '车身电子', minor_name: '车窗控制模块', rel_status: 1, rel_time: '2025-05-12 10:00:00', operate_user: 'admin', remark: '', unrel_time: '' },
    { major_id: 'major_010', major_name: '安全系统', minor_name: '安全气囊', rel_status: 0, rel_time: '2025-04-20 14:40:00', operate_user: 'admin', remark: '召回更换', unrel_time: '2025-10-12 13:15:00' },
    { major_id: 'major_001', major_name: '电机系统', minor_name: '电机定子', rel_status: 1, rel_time: '2025-10-14 08:30:00', operate_user: 'admin', remark: '新增配件', unrel_time: '' },
    { major_id: 'major_002', major_name: '电控系统', minor_name: 'ECU模块', rel_status: 0, rel_time: '2025-09-20 11:00:00', operate_user: 'system', remark: '版本迭代', unrel_time: '2025-10-14 10:50:00' }
  ]
}

// 核心：查询+筛选+排序逻辑
const handleQuery = () => {
  let result = [...allData.value]

  // 1. 筛选逻辑（大类+关联状态）
  if (filters.major_id) {
    result = result.filter(item => item.major_id === filters.major_id)
  }
  if (filters.rel_status !== '' && filters.rel_status !== null && filters.rel_status !== undefined) {
    result = result.filter(item => item.rel_status === filters.rel_status)
  }

  // 2. 排序逻辑（根据选择的规则排序）
  result.sort((a, b) => {
    // 规则1：关联时间降序（最新的关联时间排在前面）
    if (filters.sortRule === 1) {
      return new Date(b.rel_time) - new Date(a.rel_time)
    }
    // 规则2：解除关联时间降序（有解除时间的优先，且最新的排在前面）
    else if (filters.sortRule === 2) {
      // 处理“无解除时间”的情况（有效状态），默认排在后面
      if (!a.unrel_time) return 1
      if (!b.unrel_time) return -1
      // 有解除时间的，按时间降序
      return new Date(b.unrel_time) - new Date(a.unrel_time)
    }
    return 0
  })

  // 3. 分页逻辑（基于排序后的结果）
  pagination.total = result.length
  const start = (pagination.page - 1) * pagination.pageSize
  const end = start + pagination.pageSize
  tableData.value = result.slice(start, end)

  // 无数据提示
  if (!result.length) {
    ElMessage.info('未查询到符合条件的记录')
  }
}

// 分页切换（联动查询）
const handlePageChange = (page) => {
  pagination.page = page
  handleQuery()
}

// 每页条数切换（重置页码+联动查询）
const handlePageSizeChange = (size) => {
  pagination.pageSize = size
  pagination.page = 1 // 切换每页条数时，默认回到第一页
  handleQuery()
}

// 导出功能（基于当前筛选+排序后的表格数据）
const handleExport = () => {
  if (!tableData.value.length) {
    ElMessage.warning('暂无可导出数据')
    return
  }

  // 格式化导出数据（包含解除时间）
  const exportData = tableData.value.map(item => ({
    '大类名称': item.major_name,
    '小类名称': item.minor_name,
    '关联状态': item.rel_status === 1 ? '有效' : '无效',
    '关联时间': item.rel_time,
    '解除时间': item.unrel_time || '—',
    '操作人': item.operate_user,
    '备注': item.remark || ''
  }))

  // 生成Excel并下载
  const worksheet = XLSX.utils.json_to_sheet(exportData)
  const workbook = XLSX.utils.book_new()
  XLSX.utils.book_append_sheet(workbook, worksheet, '大类小类关联记录')
  const wbout = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' })
  saveAs(
    new Blob([wbout], { type: 'application/octet-stream' }),
    `管理部件大类小类关联记录_${new Date().toLocaleDateString()}.xlsx`
  )

  ElMessage.success('导出成功')
}

// 解除关联（更新解除时间，排序会自动联动）
const handleUnrel = (row) => {
  const targetItem = allData.value.find(
    item => item.major_id === row.major_id && item.minor_name === row.minor_name
  )
  if (targetItem) {
    targetItem.rel_status = 0
    targetItem.remark = '手动解除关联'
    targetItem.unrel_time = new Date().toLocaleString() // 记录当前解除时间
    targetItem.operate_user = 'admin'
  }

  handleQuery() // 重新查询，触发排序更新
  ElMessage.success(`小类「${row.minor_name}」已解除关联`)
}

// 重新关联（清空解除时间，排序会自动联动）
const handleRelink = (row) => {
  const targetItem = allData.value.find(
    item => item.major_id === row.major_id && item.minor_name === row.minor_name
  )
  if (targetItem) {
    targetItem.rel_status = 1
    targetItem.remark = '手动重新关联'
    targetItem.unrel_time = '' // 清空解除时间
    targetItem.rel_time = new Date().toLocaleString() // 更新关联时间
    targetItem.operate_user = 'admin'
  }

  handleQuery() // 重新查询，触发排序更新
  ElMessage.success(`已重新关联小类「${row.minor_name}」`)
}

// 打开详情抽屉
const openDetailDrawer = (row) => {
  Object.assign(detailData, JSON.parse(JSON.stringify(row))) // 深拷贝避免修改源数据
  detailVisible.value = true
}

// 打开新增抽屉（重置表单）
const handleAdd = () => {
  addForm.major_id = ''
  addForm.minor_name = ''
  addForm.remark = ''
  if (addFormRef.value) {
    addFormRef.value.clearValidate()
  }
  addVisible.value = true
}

// 提交新增（新增后自动排序）
const handleAddSubmit = () => {
  proxy.$refs.addFormRef.validate((isValid) => {
    if (!isValid) return

    // 避免重复添加（同一大类下小类名称唯一）
    const isDuplicate = allData.value.some(
      item => item.major_id === addForm.major_id && item.minor_name === addForm.minor_name
    )
    if (isDuplicate) {
      ElMessage.warning(`当前大类下已存在「${addForm.minor_name}」小类，请勿重复添加`)
      return
    }

    // 生成新增数据
    const newRelItem = {
      major_id: addForm.major_id,
      major_name: majorList.value.find(item => item.id === addForm.major_id).name,
      minor_name: addForm.minor_name,
      rel_status: 1, // 新增默认有效
      rel_time: new Date().toLocaleString(), // 当前关联时间
      operate_user: 'admin',
      remark: addForm.remark || '',
      unrel_time: '' // 初始无解除时间
    }

    allData.value.unshift(newRelItem)
    handleQuery() // 重新查询，触发排序
    addVisible.value = false
    ElMessage.success('新增大类小类关联成功')
  })
}

// 初始化加载（数据+大类+默认查询排序）
onMounted(() => {
  loadMajorList()
  loadAllData()
  handleQuery()
})
</script>

<style scoped>
.managed-major-minor-rel {
  padding: 20px;
  background-color: #fff;
  min-height: calc(100vh - 40px);
}

.toolbar {
  margin-bottom: 20px;
  display: flex;
  gap: 15px;
  align-items: center;
  flex-wrap: wrap;
}
</style>
