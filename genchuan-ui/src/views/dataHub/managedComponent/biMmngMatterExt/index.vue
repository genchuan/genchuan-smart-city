<template>
  <div class="biMmngMatterExt-container">
    <!-- 搜索区域 -->
    <div class="filter-container">
      <el-form :model="queryParams" ref="queryFormRef" :inline="true" label-width="80px">
        <el-form-item label="审核状态" prop="auditStatus">
          <el-select v-model="queryParams.auditStatus" placeholder="请选择审核状态" clearable>
            <el-option label="待审核" value="待审核" />
            <el-option label="已通过" value="已通过" />
            <el-option label="已驳回" value="已驳回" />
          </el-select>
        </el-form-item>
        <el-form-item label="所属大类" prop="matterMajorId">
          <el-select v-model="queryParams.matterMajorId" placeholder="请选择大类" clearable filterable>
            <el-option
              v-for="item in majorOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="申请人" prop="applyUser">
          <el-select v-model="queryParams.applyUser" placeholder="请选择申请人" clearable filterable>
            <el-option
              v-for="user in userOptions"
              :key="user.userId"
              :label="user.userName"
              :value="user.userId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="申请时间" prop="applyTimeRange">
          <el-date-picker
            v-model="queryParams.applyTimeRange"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            value-format="YYYY-MM-DD"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleQuery" :icon="Search">搜索</el-button>
          <el-button @click="resetQuery" :icon="Refresh">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="operation-container">
      <el-button type="primary" @click="handleAdd" :icon="Plus">新增申请</el-button>
      <el-button @click="handleExport" :icon="Download">导出Excel</el-button>
      <el-button @click="handleExportPdf" :icon="Document">导出PDF</el-button>
    </div>

    <!-- 数据表格 -->
    <el-table
      v-loading="loading"
      :data="dataList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="扩展小类代码" prop="extMinorCode" width="120" align="center" />
      <el-table-column label="扩展小类名称" prop="extMinorName" min-width="180" show-overflow-tooltip />
      <el-table-column label="所属大类" prop="matterMajorName" width="120" align="center" />
      <el-table-column label="审核状态" prop="auditStatus" width="100" align="center">
        <template #default="scope">
          <el-tag
            :type="scope.row.auditStatus === '待审核' ? 'primary' : scope.row.auditStatus === '已通过' ? 'success' : 'danger'"
          >
            {{ scope.row.auditStatus }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="建议主管部门" prop="suggestDeptName" width="150" show-overflow-tooltip />
      <el-table-column label="申请人" prop="applyUserName" width="100" align="center" />
      <el-table-column label="申请时间" prop="applyTime" width="160" align="center" />
      <el-table-column label="审核人" prop="auditUserName" width="100" align="center" />
      <el-table-column label="审核时间" prop="auditTime" width="160" align="center" />
      <el-table-column label="操作" align="center" width="280" fixed="right">
        <template #default="scope">
          <el-button link type="primary" @click="handleDetail(scope.row)" :icon="View">详情</el-button>

          <!-- 待审核状态操作 -->
          <template v-if="scope.row.auditStatus === '待审核'">
            <el-button link type="warning" @click="handleWithdraw(scope.row)" :icon="Delete">撤回</el-button>
            <el-button link type="success" @click="handleAudit(scope.row)" :icon="EditPen">审核</el-button>
          </template>

          <!-- 已驳回状态操作 -->
          <el-button
            v-if="scope.row.auditStatus === '已驳回'"
            link type="primary"
            @click="handleEdit(scope.row)"
            :icon="Edit"
          >
            重新编辑
          </el-button>

          <!-- 已通过状态操作 -->
          <el-button
            v-if="scope.row.auditStatus === '已通过'"
            link type="info"
            @click="handleSyncResult(scope.row)"
            :icon="Link"
          >
            查看同步结果
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <div class="pagination-container">
      <el-pagination
        v-model:current-page="queryParams.pageNum"
        v-model:page-size="queryParams.pageSize"
        :page-sizes="[10, 20, 50, 100]"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>

    <!-- 新增/编辑对话框 -->
    <el-dialog
      :title="dialog.title"
      v-model="dialog.visible"
      width="800px"
      append-to-body
      :close-on-click-modal="false"
    >
      <el-form
        ref="addEditFormRef"
        :model="formData"
        :rules="formRules"
        label-width="120px"
        :disabled="dialog.mode === 'edit' && formData.auditStatus !== '已驳回'"
      >
        <el-form-item label="所属大类" prop="matterMajorId">
          <el-select
            v-model="formData.matterMajorId"
            placeholder="请选择所属大类"
            style="width: 100%"
            @change="handleMajorChange"
          >
            <el-option
              v-for="item in majorOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="所属大类名称" prop="matterMajorName">
          <el-input v-model="formData.matterMajorName" placeholder="自动同步大类名称" readonly />
        </el-form-item>

        <el-form-item label="扩展小类代码" prop="extMinorCode">
          <el-input
            v-model="formData.extMinorCode"
            placeholder="系统推荐080-999，可手动修改"
            :maxlength="3"
            show-word-limit
          >
            <template #append>
              <el-button @click="generateCode">生成代码</el-button>
            </template>
          </el-input>
          <div class="form-tip">从080开始倒序编码，避免与标准类冲突</div>
        </el-form-item>

        <el-form-item label="扩展小类名称" prop="extMinorName">
          <el-input
            v-model="formData.extMinorName"
            placeholder="请输入扩展小类名称"
            :maxlength="50"
            show-word-limit
          />
          <div class="form-tip">系统会自动添加"（自定义）"后缀</div>
        </el-form-item>

        <el-form-item label="扩展小类说明" prop="extMinorDesc">
          <el-input
            v-model="formData.extMinorDesc"
            type="textarea"
            :rows="3"
            placeholder="请描述扩展小类的用途和场景"
            :maxlength="255"
            show-word-limit
          />
        </el-form-item>

        <el-form-item label="建议主管部门" prop="suggestDeptCode">
          <el-select
            v-model="formData.suggestDeptCode"
            placeholder="请选择主管部门"
            style="width: 100%"
            filterable
            @change="handleDeptChange"
          >
            <el-option
              v-for="dept in deptOptions"
              :key="dept.deptCode"
              :label="dept.deptName"
              :value="dept.deptCode"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="主管部门名称" prop="suggestDeptName">
          <el-input v-model="formData.suggestDeptName" placeholder="自动同步部门名称" readonly />
        </el-form-item>

        <el-form-item label="申请原因" prop="applyReason">
          <el-input
            v-model="formData.applyReason"
            type="textarea"
            :rows="3"
            placeholder="请详细说明扩展的必要性和背景情况"
            :maxlength="255"
            show-word-limit
          />
        </el-form-item>

        <!-- 扩展字段 -->
        <el-form-item label="分类扩展字段1" prop="extCat1">
          <el-input v-model="formData.extCat1" placeholder="如：预计启用时间" />
        </el-form-item>

        <el-form-item label="分类扩展字段2" prop="extCat2">
          <el-input v-model="formData.extCat2" placeholder="预留扩展字段" />
        </el-form-item>

        <el-form-item label="通用扩展字段1" prop="extCommon1">
          <el-input v-model="formData.extCommon1" placeholder="如：参考政策文件" />
        </el-form-item>

        <el-form-item label="通用扩展字段2" prop="extCommon2">
          <el-input v-model="formData.extCommon2" placeholder="预留扩展字段" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="handleSubmit">提交申请</el-button>
          <el-button @click="dialog.visible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!-- 审核对话框 -->
    <el-dialog
      title="审核扩展申请"
      v-model="auditDialog.visible"
      width="600px"
      append-to-body
      :close-on-click-modal="false"
    >
      <el-form
        ref="auditFormRef"
        :model="auditFormData"
        :rules="auditFormRules"
        label-width="100px"
      >
        <el-form-item label="申请信息">
          <div class="apply-info">
            <p><strong>扩展小类：</strong>{{ auditDialog.row?.extMinorName }}</p>
            <p><strong>所属大类：</strong>{{ auditDialog.row?.matterMajorName }}</p>
            <p><strong>申请原因：</strong>{{ auditDialog.row?.applyReason }}</p>
          </div>
        </el-form-item>

        <el-form-item label="审核结果" prop="auditStatus">
          <el-radio-group v-model="auditFormData.auditStatus">
            <el-radio label="已通过">通过</el-radio>
            <el-radio label="已驳回">驳回</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="审核意见" prop="auditOpinion">
          <el-input
            v-model="auditFormData.auditOpinion"
            type="textarea"
            :rows="4"
            placeholder="请详细说明审核意见，如：同意归街面秩序大类，主管部门为城管"
            :maxlength="255"
            show-word-limit
          />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="handleAuditSubmit">提交审核</el-button>
          <el-button @click="auditDialog.visible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!-- 详情对话框 -->
    <el-dialog
      title="扩展申请详情"
      v-model="detailDialog.visible"
      width="900px"
      append-to-body
    >
      <el-descriptions :column="2" border>
        <el-descriptions-item label="扩展小类代码">{{ detailDialog.row?.extMinorCode }}</el-descriptions-item>
        <el-descriptions-item label="扩展小类名称">{{ detailDialog.row?.extMinorName }}</el-descriptions-item>
        <el-descriptions-item label="所属大类" :span="2">
          {{ detailDialog.row?.matterMajorName }}
        </el-descriptions-item>
        <el-descriptions-item label="扩展小类说明" :span="2">
          {{ detailDialog.row?.extMinorDesc }}
        </el-descriptions-item>
        <el-descriptions-item label="建议主管部门">{{ detailDialog.row?.suggestDeptName }}</el-descriptions-item>
        <el-descriptions-item label="申请原因" :span="2">
          {{ detailDialog.row?.applyReason }}
        </el-descriptions-item>
      </el-descriptions>

      <h4 class="section-title">申请信息</h4>
      <el-descriptions :column="2" border>
        <el-descriptions-item label="申请人">{{ detailDialog.row?.applyUserName }}</el-descriptions-item>
        <el-descriptions-item label="申请时间">{{ detailDialog.row?.applyTime }}</el-descriptions-item>
      </el-descriptions>

      <h4 class="section-title" v-if="detailDialog.row?.auditStatus !== '待审核'">审核信息</h4>
      <el-descriptions v-if="detailDialog.row?.auditStatus !== '待审核'" :column="2" border>
        <el-descriptions-item label="审核状态">
          <el-tag :type="detailDialog.row?.auditStatus === '已通过' ? 'success' : 'danger'">
            {{ detailDialog.row?.auditStatus }}
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="审核人">{{ detailDialog.row?.auditUserName }}</el-descriptions-item>
        <el-descriptions-item label="审核时间">{{ detailDialog.row?.auditTime }}</el-descriptions-item>
        <el-descriptions-item label="审核意见" :span="2">
          {{ detailDialog.row?.auditOpinion }}
        </el-descriptions-item>
      </el-descriptions>

      <!-- 审核轨迹 -->
      <h4 class="section-title">审核轨迹</h4>
      <el-timeline>
        <el-timeline-item
          v-for="(track, index) in auditTracks"
          :key="index"
          :timestamp="track.time"
          :type="track.type"
        >
          {{ track.content }}
        </el-timeline-item>
      </el-timeline>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import {
  Search, Refresh, Plus, Download, Document, View, Delete, EditPen, Edit, Link
} from '@element-plus/icons-vue'

const router = useRouter()

// 数据状态
const loading = ref(false)
const dataList = ref([])
const total = ref(0)
const selectedRows = ref([])

// 查询参数
const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  auditStatus: undefined,
  matterMajorId: undefined,
  applyUser: undefined,
  applyTimeRange: []
})

// 选项数据
const majorOptions = ref([
  { value: '1', label: '街面秩序' },
  { value: '2', label: '环境卫生' },
  { value: '3', label: '市政设施' },
  { value: '4', label: '其他管理事项' }
])

const userOptions = ref([
  { userId: '1', userName: '张三' },
  { userId: '2', userName: '李四' },
  { userId: '3', userName: '王五' }
])

const deptOptions = ref([
  { deptCode: '001', deptName: '城市管理局' },
  { deptCode: '002', deptName: '市场监督管理局' },
  { deptCode: '003', deptName: '交通运输局' },
  { deptCode: '004', deptName: '卫生健康委员会' }
])

// 对话框状态
const dialog = reactive({
  visible: false,
  mode: 'add',
  title: '',
  row: null
})

const auditDialog = reactive({
  visible: false,
  row: null
})

const detailDialog = reactive({
  visible: false,
  row: null
})

// 表单引用
const queryFormRef = ref()
const addEditFormRef = ref()
const auditFormRef = ref()

// 表单数据
const formData = reactive({
  mngMatterExtId: '',
  matterMajorId: '',
  matterMajorName: '',
  extMinorCode: '',
  extMinorName: '',
  extMinorDesc: '',
  suggestDeptCode: '',
  suggestDeptName: '',
  applyReason: '',
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

const auditFormData = reactive({
  auditStatus: '已通过',
  auditOpinion: ''
})

// 表单验证规则
const formRules = {
  matterMajorId: [
    { required: true, message: '请选择所属大类', trigger: 'change' }
  ],
  extMinorCode: [
    { required: true, message: '请输入扩展小类代码', trigger: 'blur' },
    { pattern: /^[0-9]{3}$/, message: '代码必须为3位数字', trigger: 'blur' },
    { validator: validateMinorCode, trigger: 'blur' }
  ],
  extMinorName: [
    { required: true, message: '请输入扩展小类名称', trigger: 'blur' }
  ],
  extMinorDesc: [
    { required: true, message: '请输入扩展小类说明', trigger: 'blur' }
  ],
  applyReason: [
    { required: true, message: '请输入申请原因', trigger: 'blur' }
  ]
}

const auditFormRules = {
  auditStatus: [
    { required: true, message: '请选择审核结果', trigger: 'change' }
  ],
  auditOpinion: [
    { required: true, message: '请输入审核意见', trigger: 'blur' }
  ]
}

// 计算属性 - 审核轨迹
const auditTracks = computed(() => {
  if (!detailDialog.row) return []

  const tracks = [
    {
      time: detailDialog.row.applyTime,
      content: `用户 ${detailDialog.row.applyUserName} 提交扩展申请`,
      type: 'primary'
    }
  ]

  if (detailDialog.row.auditStatus !== '待审核') {
    tracks.push({
      time: detailDialog.row.auditTime,
      content: `审核人 ${detailDialog.row.auditUserName} ${detailDialog.row.auditStatus}，意见：${detailDialog.row.auditOpinion}`,
      type: detailDialog.row.auditStatus === '已通过' ? 'success' : 'danger'
    })
  }

  return tracks
})

// 表单验证函数
function validateMinorCode(rule, value, callback) {
  if (value && (value < '080' || value > '999')) {
    callback(new Error('代码范围必须在080-999之间'))
  } else {
    callback()
  }
}

// 方法定义
const getList = async () => {
  loading.value = true
  try {
    // 模拟API调用
    await new Promise(resolve => setTimeout(resolve, 500))

    // 模拟数据
    dataList.value = [
      {
        mngMatterExtId: '1',
        extMinorCode: '080',
        extMinorName: '共享单车乱停放（自定义）',
        matterMajorId: '1',
        matterMajorName: '街面秩序',
        extMinorDesc: '共享单车未停指定区域，影响市容',
        suggestDeptCode: '001',
        suggestDeptName: '城市管理局',
        applyReason: '市区共享单车超5万辆，乱停放频发需分类',
        auditStatus: '待审核',
        applyUser: '1',
        applyUserName: '张三',
        applyTime: '2024-01-15 10:30:00',
        auditUser: '',
        auditUserName: '',
        auditTime: '',
        auditOpinion: '',
        extCat1: '',
        extCat2: '',
        extCommon1: '',
        extCommon2: ''
      },
      {
        mngMatterExtId: '2',
        extMinorCode: '081',
        extMinorName: '餐饮油烟扰民（自定义）',
        matterMajorId: '2',
        matterMajorName: '环境卫生',
        extMinorDesc: '餐饮业油烟排放超标，影响居民生活',
        suggestDeptCode: '002',
        suggestDeptName: '市场监督管理局',
        applyReason: '近期餐饮油烟投诉增多，需要专项管理',
        auditStatus: '已通过',
        applyUser: '2',
        applyUserName: '李四',
        applyTime: '2024-01-10 14:20:00',
        auditUser: '3',
        auditUserName: '王五',
        auditTime: '2024-01-12 09:15:00',
        auditOpinion: '同意归环境卫生大类，主管部门为市场监督管理局',
        extCat1: '2024-02-01',
        extCat2: '',
        extCommon1: '《餐饮业油烟排放标准》',
        extCommon2: ''
      },
      {
        mngMatterExtId: '3',
        extMinorCode: '082',
        extMinorName: '道路积水处理（自定义）',
        matterMajorId: '3',
        matterMajorName: '市政设施',
        extMinorDesc: '道路积水影响交通和市民出行',
        suggestDeptCode: '003',
        suggestDeptName: '交通运输局',
        applyReason: '雨季道路积水问题突出，需要专项分类',
        auditStatus: '已驳回',
        applyUser: '1',
        applyUserName: '张三',
        applyTime: '2024-01-08 16:45:00',
        auditUser: '3',
        auditUserName: '王五',
        auditTime: '2024-01-09 11:30:00',
        auditOpinion: '道路积水已属于现有市政设施维护范围，无需单独分类',
        extCat1: '',
        extCat2: '',
        extCommon1: '',
        extCommon2: ''
      }
    ]
    total.value = dataList.value.length
  } catch (error) {
    console.error('获取数据失败:', error)
    ElMessage.error('获取数据失败')
  } finally {
    loading.value = false
  }
}

const handleQuery = () => {
  queryParams.pageNum = 1
  getList()
}

const resetQuery = () => {
  queryFormRef.value?.resetFields()
  queryParams.pageNum = 1
  getList()
}

const handleSizeChange = (val) => {
  queryParams.pageSize = val
  queryParams.pageNum = 1
  getList()
}

const handleCurrentChange = (val) => {
  queryParams.pageNum = val
  getList()
}

const handleAdd = () => {
  dialog.mode = 'add'
  dialog.title = '新增扩展申请'
  dialog.row = null
  resetFormData()
  generateCode()
  dialog.visible = true
}

const handleEdit = (row) => {
  dialog.mode = 'edit'
  dialog.title = '编辑扩展申请'
  dialog.row = row
  Object.assign(formData, row)
  dialog.visible = true
}

const handleAudit = (row) => {
  auditDialog.row = row
  auditFormData.auditStatus = '已通过'
  auditFormData.auditOpinion = ''
  auditDialog.visible = true
}

const handleDetail = (row) => {
  detailDialog.row = row
  detailDialog.visible = true
}

const handleWithdraw = async (row) => {
  try {
    await ElMessageBox.confirm('确认撤回该扩展申请吗？', '提示', {
      type: 'warning'
    })

    // 模拟撤回API调用
    loading.value = true
    await new Promise(resolve => setTimeout(resolve, 500))

    const index = dataList.value.findIndex(item => item.mngMatterExtId === row.mngMatterExtId)
    if (index !== -1) {
      dataList.value.splice(index, 1)
    }

    ElMessage.success('撤回成功')
    getList()
  } catch (error) {
    console.log('取消撤回')
  }
}

const handleSyncResult = (row) => {
  // 跳转到小类配置页面
  router.push('/dataHub/managedComponent/smallClassConfig')
}

const handleExport = async () => {
  try {
    loading.value = true
    // 模拟导出API调用
    await new Promise(resolve => setTimeout(resolve, 1000))
    ElMessage.success('导出Excel成功')
  } catch (error) {
    ElMessage.error('导出失败')
  } finally {
    loading.value = false
  }
}

const handleExportPdf = async () => {
  try {
    loading.value = true
    // 模拟导出API调用
    await new Promise(resolve => setTimeout(resolve, 1000))
    ElMessage.success('导出PDF成功')
  } catch (error) {
    ElMessage.error('导出失败')
  } finally {
    loading.value = false
  }
}

const handleSelectionChange = (selection) => {
  selectedRows.value = selection
}

const handleMajorChange = (value) => {
  const major = majorOptions.value.find(item => item.value === value)
  if (major) {
    formData.matterMajorName = major.label
  }
}

const handleDeptChange = (value) => {
  const dept = deptOptions.value.find(item => item.deptCode === value)
  if (dept) {
    formData.suggestDeptName = dept.deptName
  }
}

const generateCode = async () => {
  try {
    // 模拟获取下一个可用代码
    await new Promise(resolve => setTimeout(resolve, 200))

    // 从现有数据中找到最大的代码，然后加1
    const codes = dataList.value.map(item => parseInt(item.extMinorCode)).filter(code => !isNaN(code))
    const maxCode = codes.length > 0 ? Math.max(...codes) : 79
    const nextCode = maxCode + 1

    if (nextCode > 999) {
      ElMessage.warning('代码已达到最大值999')
      return
    }

    formData.extMinorCode = nextCode.toString().padStart(3, '0')
  } catch (error) {
    ElMessage.error('生成代码失败')
  }
}

const resetFormData = () => {
  Object.keys(formData).forEach(key => {
    formData[key] = ''
  })
}

const handleSubmit = async () => {
  if (!addEditFormRef.value) return

  try {
    await addEditFormRef.value.validate()

    // 自动添加自定义后缀
    if (formData.extMinorName && !formData.extMinorName.endsWith('（自定义）')) {
      formData.extMinorName += '（自定义）'
    }

    // 模拟API调用
    loading.value = true
    await new Promise(resolve => setTimeout(resolve, 500))

    if (dialog.mode === 'add') {
      // 新增
      formData.mngMatterExtId = Date.now().toString()
      formData.auditStatus = '待审核'
      formData.applyUser = '1' // 模拟当前用户
      formData.applyUserName = '张三' // 模拟当前用户
      formData.applyTime = new Date().toLocaleString('sv').replace('T', ' ').substring(0, 19)

      dataList.value.unshift({ ...formData })
      ElMessage.success('提交申请成功')
    } else {
      // 编辑
      const index = dataList.value.findIndex(item => item.mngMatterExtId === formData.mngMatterExtId)
      if (index !== -1) {
        // 重置审核状态为待审核
        formData.auditStatus = '待审核'
        formData.auditUser = ''
        formData.auditUserName = ''
        formData.auditTime = ''
        formData.auditOpinion = ''

        dataList.value[index] = { ...formData }
        ElMessage.success('重新提交成功')
      }
    }

    dialog.visible = false
    getList()
  } catch (error) {
    console.log('表单验证失败')
  } finally {
    loading.value = false
  }
}

const handleAuditSubmit = async () => {
  if (!auditFormRef.value) return

  try {
    await auditFormRef.value.validate()

    // 模拟API调用
    loading.value = true
    await new Promise(resolve => setTimeout(resolve, 500))

    const index = dataList.value.findIndex(item => item.mngMatterExtId === auditDialog.row.mngMatterExtId)
    if (index !== -1) {
      dataList.value[index].auditStatus = auditFormData.auditStatus
      dataList.value[index].auditOpinion = auditFormData.auditOpinion
      dataList.value[index].auditUser = '3' // 模拟审核人
      dataList.value[index].auditUserName = '王五' // 模拟审核人
      dataList.value[index].auditTime = new Date().toLocaleString('sv').replace('T', ' ').substring(0, 19)

      ElMessage.success('审核提交成功')
    }

    auditDialog.visible = false
    getList()
  } catch (error) {
    console.log('表单验证失败')
  } finally {
    loading.value = false
  }
}

// 初始化
onMounted(() => {
  getList()
})
</script>

<style scoped>
.biMmngMatterExt-container {
  padding: 20px;
}

.filter-container {
  background: #fff;
  padding: 20px;
  margin-bottom: 20px;
  border-radius: 4px;
}

.operation-container {
  background: #fff;
  padding: 20px;
  margin-bottom: 20px;
  border-radius: 4px;
}

.pagination-container {
  background: #fff;
  padding: 20px;
  margin-top: 20px;
  border-radius: 4px;
  text-align: right;
}

.form-tip {
  font-size: 12px;
  color: #909399;
  margin-top: 4px;
}

.apply-info {
  background: #f8f9fa;
  padding: 15px;
  border-radius: 4px;
  border-left: 4px solid #409eff;
}

.apply-info p {
  margin: 5px 0;
  line-height: 1.5;
}

.section-title {
  margin: 25px 0 15px 0;
  padding-bottom: 10px;
  border-bottom: 1px solid #eaeaea;
  color: #303133;
}
</style>
