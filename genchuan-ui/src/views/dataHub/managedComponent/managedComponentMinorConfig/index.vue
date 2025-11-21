<template>
  <ContentWrap>
    <!-- 🔍 搜索栏 -->
    <el-form
      class="-mb-1px"
      :model="queryParams"
      ref="queryFormRef"
      :inline="true"
      label-width="130px"
    >
      <el-form-item label="大类ID">
        <el-input
          v-model="queryParams.majorId"
          placeholder="请输入大类ID"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item label="小类名称/代码">
        <el-input
          v-model="queryParams.keyword"
          placeholder="请输入小类名称或代码"
          clearable
          @keyup.enter="handleQuery"
          class="!w-240px"
        />
      </el-form-item>

      <el-form-item>
        <el-button @click="handleQuery"><Icon icon="ep:search" class="mr-5px" /> 搜索</el-button>
        <el-button @click="resetQuery"><Icon icon="ep:refresh" class="mr-5px" /> 重置</el-button>

        <el-button type="primary" plain @click="openForm('create')">
          <Icon icon="ep:plus" class="mr-5px" /> 新增
        </el-button>

        <el-upload
          :show-file-list="false"
          :before-upload="handleImport"
          accept=".xlsx, .xls"
        >
          <el-button type="warning" plain>
            <Icon icon="ep:upload" class="mr-5px" /> 导入
          </el-button>
        </el-upload>

        <el-button type="success" plain @click="handleExport" :loading="exportLoading">
          <Icon icon="ep:download" class="mr-5px" /> 导出
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 📋 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" stripe show-overflow-tooltip>
      <el-table-column label="小类ID" prop="minorId" align="center">
        <template #default="scope">
          <span
            class="text-blue-500 cursor-pointer hover:underline"
            @click="openDetail(scope.row)"
          >
            {{ scope.row.minorId }}
          </span>
        </template>
      </el-table-column>
<!--      <el-table-column label="大类ID" prop="majorId" align="center" />-->
      <el-table-column label="小类代码" prop="minorCode" align="center" />
      <el-table-column label="小类名称" prop="minorName" align="center" />
      <el-table-column label="小类说明" prop="minorDesc" align="center" />
      <el-table-column label="主管部门" align="center">
        <template #default="scope">{{ scope.row.deptName }} ({{ scope.row.deptCode }})</template>
      </el-table-column>
      <el-table-column label="是否扩展类" align="center">
        <template #default="scope">
          <el-tag :type="scope.row.isExtend === '1' ? 'success' : 'info'">
            {{ scope.row.isExtend === '1' ? '是' : '否' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建人" prop="createUser" align="center" />
      <el-table-column label="创建时间" prop="createTime" align="center" width="160" />
      <el-table-column label="操作" align="center" width="220">
        <template #default="scope">
          <el-button link type="primary" @click="openForm('update', scope.row)">编辑</el-button>
          <el-button link type="info" @click="openDetail(scope.row)">详情</el-button>
          <el-button link type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
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

  <!-- ✏️ 弹窗表单（修改核心：下拉框+联动） -->
  <el-dialog v-model="dialogVisible" :title="dialogTitle" width="600px">
    <el-form
      :model="formData"
      ref="formRef"
      label-width="100px"
      :rules="formRules"
    >
      <!-- 1. 大类ID：下拉选择 -->
<!--      <el-form-item label="大类ID" prop="majorId">-->
<!--        <el-select-->
<!--          v-model="formData.majorId"-->
<!--          placeholder="请选择大类ID"-->
<!--          class="!w-240px"-->
<!--        >-->
<!--          <el-option-->
<!--            v-for="item in majorOptions"-->
<!--            :key="item.id"-->
<!--            :label="`${item.id}（${item.desc}）`"-->
<!--            :value="item.id"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->

      <!-- 2. 小类代码：下拉选择（联动小类名称） -->
      <el-form-item label="小类代码" prop="minorCode">
        <el-select
          v-model="formData.minorCode"
          placeholder="请选择小类代码"
          class="!w-240px"
          @change="handleMinorCodeChange"
        >
          <el-option
            v-for="item in minorOptions"
            :key="item.code"
            :label="`${item.code}（${item.name}）`"
            :value="item.code"
          />
        </el-select>
      </el-form-item>

      <!-- 3. 小类名称：联动填充（禁用下拉，避免手动修改） -->
      <el-form-item label="小类名称" prop="minorName">
        <el-select
          v-model="formData.minorName"
          placeholder="请先选择小类代码"
          class="!w-240px"
          disabled
        >
          <el-option
            v-for="item in minorOptions"
            :key="item.name"
            :label="item.name"
            :value="item.name"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="小类说明" prop="minorDesc">
        <el-input v-model="formData.minorDesc" placeholder="请输入小类说明" class="!w-240px" />
      </el-form-item>

      <!-- 4. 部门代码：下拉选择（联动部门名称） -->
      <el-form-item label="部门代码" prop="deptCode">
        <el-select
          v-model="formData.deptCode"
          placeholder="请选择部门代码"
          class="!w-240px"
          @change="handleDeptCodeChange"
        >
          <el-option
            v-for="item in deptOptions"
            :key="item.code"
            :label="`${item.code}（${item.name}）`"
            :value="item.code"
          />
        </el-select>
      </el-form-item>

      <!-- 5. 部门名称：联动填充（禁用下拉，确保一致性） -->
      <el-form-item label="部门名称" prop="deptName">
        <el-select
          v-model="formData.deptName"
          placeholder="请先选择部门代码"
          class="!w-240px"
          disabled
        >
          <el-option
            v-for="item in deptOptions"
            :key="item.name"
            :label="item.name"
            :value="item.name"
          />
        </el-select>
      </el-form-item>

      <!-- 6. 是否扩展：优化下拉选项文案 -->
      <el-form-item label="是否扩展" prop="isExtend">
        <el-select v-model="formData.isExtend" placeholder="请选择是否扩展" class="!w-240px">
          <el-option label="是（支持扩展字段配置）" value="1" />
          <el-option label="否（固定字段结构）" value="0" />
        </el-select>
      </el-form-item>

      <el-form-item label="扩展字段1" prop="extField1">
        <el-input v-model="formData.extField1" placeholder="请输入扩展字段1" class="!w-240px" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="dialogVisible = false">取消</el-button>
      <el-button type="primary" @click="submitForm">保存</el-button>
    </template>
  </el-dialog>

  <!-- 🧾 伪抽屉详情页 -->
  <transition name="slide-left">
    <div
      v-if="showDetail"
      class="fixed top-0 right-0 h-full bg-white shadow-lg border-l border-gray-200 z-40"
      style="width: 75%; overflow-y: auto;"
      @keyup.esc="closeDetail"
      tabindex="0"
    >
      <div class="p-6">
        <div class="flex justify-between items-center mb-4">
          <h2 class="text-xl font-bold text-gray-700">
            小类详情：{{ detailData.minorName || detailData.minorId }}
          </h2>
          <el-button link type="danger" @click="closeDetail">关闭</el-button>
        </div>

        <el-descriptions :column="2" border>
          <el-descriptions-item label="小类ID">{{ detailData.minorId }}</el-descriptions-item>
          <el-descriptions-item label="大类ID">{{ detailData.majorId }}</el-descriptions-item>
          <el-descriptions-item label="小类代码">{{ detailData.minorCode }}</el-descriptions-item>
          <el-descriptions-item label="小类名称">{{ detailData.minorName }}</el-descriptions-item>
          <el-descriptions-item label="小类说明" :span="2">{{ detailData.minorDesc }}</el-descriptions-item>
          <el-descriptions-item label="部门代码">{{ detailData.deptCode }}</el-descriptions-item>
          <el-descriptions-item label="部门名称">{{ detailData.deptName }}</el-descriptions-item>
          <el-descriptions-item label="是否扩展">
            {{ detailData.isExtend === '1' ? '是' : '否' }}
          </el-descriptions-item>
          <el-descriptions-item label="创建人">{{ detailData.createUser }}</el-descriptions-item>
          <el-descriptions-item label="创建时间">{{ detailData.createTime }}</el-descriptions-item>
          <el-descriptions-item label="扩展字段1" :span="2">{{ detailData.extField1 }}</el-descriptions-item>
        </el-descriptions>
      </div>
    </div>
  </transition>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, onBeforeUnmount } from 'vue'
import { ElMessage, ElMessageBox, ElForm } from 'element-plus'
import * as XLSX from 'xlsx'
import { saveAs } from 'file-saver'

// === 1. 新增：下拉框选项数据（可根据实际业务扩展） ===
// 大类选项（含ID和描述，方便识别）
const majorOptions = ref([
  { id: 'MAJOR001', desc: '机械部件大类' },
  { id: 'MAJOR002', desc: '电气部件大类' },
  { id: 'MAJOR003', desc: '液压系统大类' },
  { id: 'MAJOR004', desc: '安全设备大类' }
])

// 小类选项（代码+名称，用于联动）
const minorOptions = ref([
  { code: 'ELEC_001', name: '电气设备' },
  { code: 'ELEC_002', name: '电气配件' },
  { code: 'MECH_001', name: '机械设备' },
  { code: 'MECH_002', name: '机械配件' },
  { code: 'SAFE_001', name: '安全设备' },
  { code: 'SAFE_002', name: '防护用品' }
])

// 部门选项（代码+名称，用于联动）
const deptOptions = ref([
  { code: 'DEPT_ENG', name: '工程部' },
  { code: 'DEPT_SAFE', name: '安全部' },
  { code: 'DEPT_MAINT', name: '维护部' },
  { code: 'DEPT_PUR', name: '采购部' }
])

// === 2. 新增：表单验证规则（适配下拉框） ===
const formRules = reactive({
  majorId: [{ required: true, message: '请选择大类ID', trigger: 'change' }],
  minorCode: [{ required: true, message: '请选择小类代码', trigger: 'change' }],
  minorName: [{ required: true, message: '小类名称不能为空', trigger: 'change' }],
  deptCode: [{ required: true, message: '请选择部门代码', trigger: 'change' }],
  deptName: [{ required: true, message: '部门名称不能为空', trigger: 'change' }],
  isExtend: [{ required: true, message: '请选择是否扩展', trigger: 'change' }],
  minorDesc: [{ required: false, message: '请输入小类说明', trigger: 'blur' }],
  extField1: [{ required: false, message: '请输入扩展字段1', trigger: 'blur' }]
})

// === 模拟数据源 ===
const mockData = ref<any[]>([
  { id: 1, minorId: 'MINOR001', majorId: 'MAJOR001', minorCode: 'ELEC_001', minorName: '电气设备', minorDesc: '变压器、开关柜', deptCode: 'DEPT_ENG', deptName: '工程部', isExtend: '0', createUser: 'admin', createTime: '2024-01-15', updateUser: 'admin', extField1: '' },
  { id: 2, minorId: 'MINOR002', majorId: 'MAJOR002', minorCode: 'SAFE_001', minorName: '安全设备', minorDesc: '消防设备', deptCode: 'DEPT_SAFE', deptName: '安全部', isExtend: '1', createUser: 'admin', createTime: '2024-02-01', updateUser: 'admin', extField1: '' }
])

// === 状态 ===
const loading = ref(false)
const exportLoading = ref(false)
const list = ref<any[]>([])
const total = ref(0)
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  majorId: '',
  keyword: ''
})

// === 表单弹窗 ===
const dialogVisible = ref(false)
const dialogTitle = ref('新增')
const formData = reactive<any>({ isExtend: '0' }) // 默认“否”
const formRef = ref<InstanceType<typeof ElForm>>()

/** === 获取列表 === */
const getList = () => {
  loading.value = true
  let data = [...mockData.value]

  if (queryParams.majorId)
    data = data.filter(d => d.majorId.includes(queryParams.majorId))

  if (queryParams.keyword)
    data = data.filter(d =>
      d.minorName.includes(queryParams.keyword) || d.minorCode.includes(queryParams.keyword)
    )

  total.value = data.length
  const start = (queryParams.pageNo - 1) * queryParams.pageSize
  list.value = data.slice(start, start + queryParams.pageSize)
  loading.value = false
}

/** === 搜索 & 重置 === */
const handleQuery = () => { queryParams.pageNo = 1; getList() }
const resetQuery = () => { queryParams.majorId = ''; queryParams.keyword = ''; handleQuery() }

// === 新增：下拉框联动方法 ===
/** 选择小类代码后，自动填充小类名称 */
const handleMinorCodeChange = (code: string) => {
  const matchedMinor = minorOptions.value.find(item => item.code === code)
  formData.minorName = matchedMinor ? matchedMinor.name : ''
}

/** 选择部门代码后，自动填充部门名称 */
const handleDeptCodeChange = (code: string) => {
  const matchedDept = deptOptions.value.find(item => item.code === code)
  formData.deptName = matchedDept ? matchedDept.name : ''
}

/** === 新增 / 编辑（优化：回显时触发联动） === */
const openForm = (type: string, row?: any) => {
  dialogTitle.value = type === 'create' ? '新增小类' : '编辑小类'
  // 重置表单+赋值
  formRef.value?.resetFields()
  Object.assign(formData, row || {
    id: Date.now(),
    isExtend: '0',
    createUser: 'admin',
    createTime: new Date().toISOString().slice(0,10)
  })
  // 编辑回显时触发联动（确保名称与代码一致）
  if (row) {
    handleMinorCodeChange(row.minorCode)
    handleDeptCodeChange(row.deptCode)
  }
  dialogVisible.value = true
}

/** === 提交表单（优化：添加表单验证） === */
const submitForm = async () => {
  if (!formRef.value) return
  try {
    // 先执行表单验证
    await formRef.value.validate()
    // 验证通过后保存数据
    const index = mockData.value.findIndex(d => d.id === formData.id)
    if (index > -1) mockData.value[index] = { ...formData }
    else mockData.value.push({ ...formData })

    ElMessage.success('保存成功')
    dialogVisible.value = false
    getList()
  } catch (error) {
    // 验证失败不提交
    ElMessage.warning('请完善必填项信息')
  }
}

/** === 删除 === */
const handleDelete = async (id: number) => {
  await ElMessageBox.confirm('确定删除该条数据吗？', '提示', { type: 'warning' })
  mockData.value = mockData.value.filter(i => i.id !== id)
  ElMessage.success('删除成功')
  getList()
}

/** === 导出 Excel === */
const handleExport = () => {
  exportLoading.value = true
  const sheet = XLSX.utils.json_to_sheet(list.value)
  const wb = XLSX.utils.book_new()
  XLSX.utils.book_append_sheet(wb, sheet, '小类配置')
  const wbout = XLSX.write(wb, { bookType: 'xlsx', type: 'array' })
  saveAs(new Blob([wbout], { type: 'application/octet-stream' }), '小类配置数据.xlsx')
  ElMessage.success('导出成功')
  exportLoading.value = false
}

/** === 导入 Excel === */
const handleImport = (file: File) => {
  const reader = new FileReader()
  reader.onload = (e: any) => {
    const workbook = XLSX.read(e.target.result, { type: 'binary' })
    const sheet = workbook.Sheets[workbook.SheetNames[0]]
    const imported = XLSX.utils.sheet_to_json(sheet)
    imported.forEach((item: any) => {
      if (!item.id) item.id = Date.now() + Math.random()
      // 导入时补全联动字段（避免名称为空）
      const matchedMinor = minorOptions.value.find(m => m.code === item.minorCode)
      const matchedDept = deptOptions.value.find(d => d.code === item.deptCode)
      if (matchedMinor) item.minorName = matchedMinor.name
      if (matchedDept) item.deptName = matchedDept.name
      mockData.value.push(item)
    })
    ElMessage.success(`成功导入 ${imported.length} 条数据`)
    getList()
  }
  reader.readAsBinaryString(file)
  return false
}

/** === 伪抽屉详情 === */
const showDetail = ref(false)
const detailData = reactive<any>({})

const openDetail = (row: any) => {
  Object.assign(detailData, row)
  showDetail.value = true
  setTimeout(() => {
    const drawer = document.querySelector('.fixed.right-0') as HTMLElement
    drawer?.focus()
  }, 50)
}

const closeDetail = () => { showDetail.value = false }

onMounted(() => {
  getList()
  window.addEventListener('keydown', (e) => {
    if (e.key === 'Escape') closeDetail()
  })
})
onBeforeUnmount(() => {
  window.removeEventListener('keydown', (e) => {
    if (e.key === 'Escape') closeDetail()
  })
})
</script>

<style scoped>
.slide-left-enter-active,
.slide-left-leave-active {
  transition: all 0.3s ease;
}
.slide-left-enter-from,
.slide-left-leave-to {
  transform: translateX(100%);
  opacity: 0;
}
</style>
