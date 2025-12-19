<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
    >
      <!-- 告警编号 -->
      <el-form-item label="告警编号" prop="alertCode">
        <el-input v-model="formData.alertCode" placeholder="请输入告警编号" clearable />
      </el-form-item>

      <!-- 关联对象类型 -->
      <el-form-item label="关联对象类型" prop="relatedObjectType">
        <el-select
          v-model="formData.relatedObjectType"
          placeholder="请选择关联对象类型"
          clearable
          @change="handleRelatedTypeChange"
        >
          <el-option label="设备" value="device" />
          <el-option label="部件" value="part" />
          <el-option label="行政区划" value="area" />
        </el-select>
      </el-form-item>

      <!-- 关联设备 -->
      <el-form-item
        v-if="formData.relatedObjectType === 'device'"
        label="关联设备"
        prop="relatedObjectId"
      >
        <el-select
          v-model="selectedDeviceId"
          placeholder="请选择设备"
          filterable
          clearable
          :loading="deviceLoading"
          style="width: 100%"
          @change="handleDeviceSelectChange"
        >
          <el-option
            v-for="item in deviceList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>

      <!-- 关联部件 -->
      <el-form-item
        v-if="formData.relatedObjectType === 'part'"
        label="关联部件"
        prop="relatedObjectId"
      >
        <el-select
          v-model="selectedPartId"
          placeholder="请选择部件"
          filterable
          clearable
          :loading="partLoading"
          style="width: 100%"
          @change="handlePartSelectChange"
        >
          <el-option v-for="item in partList" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </el-form-item>

      <!-- 关联行政区划 -->
      <el-form-item
        v-if="formData.relatedObjectType === 'area'"
        label="关联行政区划"
        prop="relatedObjectId"
      >
        <el-tree-select
          v-model="selectedAreaId"
          :data="areaTree"
          filterable
          clearable
          placeholder="请选择行政区划"
          :loading="areaLoading"
          style="width: 100%"
          :props="{ value: 'fullCode', label: 'name', children: 'children' }"
          @change="handleAreaTreeChange"
        />
      </el-form-item>

      <!-- 预警领域 -->
      <!--      <el-form-item label="预警领域" prop="warningField">-->
      <!--        <el-input v-model="formData.warningField" placeholder="请输入预警领域" />-->
      <!--      </el-form-item>-->

      <el-form-item label="预警领域" prop="warningField">
        <el-select v-model="formData.warningField" placeholder="请选择预警领域" clearable>
          <el-option label="网络监控" value="网络监控" />
          <el-option label="系统监控" value="系统监控" />
          <el-option label="视频监控" value="视频监控" />
        </el-select>
      </el-form-item>

      <!-- 预警类型 -->
      <el-form-item label="预警类型" prop="warningTypeId">
        <el-tree-select
          v-model="formData.warningTypeId"
          :data="warningTypeTree"
          filterable
          clearable
          placeholder="请选择预警类型"
          :loading="treeLoading"
          style="width: 240px"
          :props="{ value: 'value', label: 'label', children: 'children' }"
          @change="handleWarningTypeChange"
        />
      </el-form-item>

      <!-- 预警等级 -->
      <el-form-item label="预警等级" prop="warningLevel">
        <el-select v-model="formData.warningLevel" placeholder="请选择预警等级" clearable>
          <el-option label="一般" value="general" />
          <el-option label="重要" value="important" />
          <el-option label="紧急" value="emergency" />
        </el-select>
      </el-form-item>

      <el-form-item label="预警状态" prop="status">
        <el-select v-model="formData.status" placeholder="请选择预警状态" clearable>
          <el-option label="待派单" :value="0" />
          <el-option label="已派单" :value="1" />
        </el-select>
      </el-form-item>

      <!-- 触发原因 -->
      <el-form-item label="触发原因" prop="triggerReason">
        <el-input v-model="formData.triggerReason" placeholder="请输入触发原因" />
      </el-form-item>

      <!--      <el-form-item label="关联事件编号" prop="relatedEventCode">-->
      <!--        <el-input v-model="formData.relatedEventCode" placeholder="请输入关联事件编号" />-->
      <!--      </el-form-item>-->

      <!-- 派发部门（树形下拉） -->
      <el-form-item label="派发部门" prop="dispatchDepartment">
        <el-tree-select
          v-model="formData.dispatchDepartment"
          :data="deptTree"
          filterable
          clearable
          placeholder="请选择派发部门"
          :loading="deptLoading"
          style="width: 100%"
          :props="{ value: 'value', label: 'label', children: 'children' }"
          @change="handleDeptChange"
        />
      </el-form-item>

      <!-- 责任人（用户下拉） -->
      <el-form-item label="责任人" prop="responsiblePerson">
        <el-select
          v-model="formData.responsiblePerson"
          placeholder="请选择责任人"
          filterable
          clearable
          :loading="userLoading"
          style="width: 100%"
        >
          <el-option
            v-for="user in userList"
            :key="user.id"
            :label="`${user.nickname}${user.deptName ? ' - ' + user.deptName : ''}`"
            :value="user.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="责任人电话" prop="responsiblePersonPhone">
        <el-input v-model="formData.responsiblePersonPhone" placeholder="请输入责任人电话" />
      </el-form-item>

      <el-form-item label="触发时间" prop="triggerTime">
        <el-date-picker
          v-model="formData.triggerTime"
          type="datetime"
          value-format="x"
          placeholder="选择触发时间"
          style="width: 100%"
        />
      </el-form-item>

      <el-form-item label="要求完成时间" prop="requiredCompleteTime">
        <el-date-picker
          v-model="formData.requiredCompleteTime"
          type="datetime"
          value-format="x"
          placeholder="选择要求完成时间"
          style="width: 100%"
        />
      </el-form-item>
      <!--      这些涉及流程的置于流程处-->
      <!--      <el-form-item label="处置进展描述" prop="disposalProgressDesc">-->
      <!--        <el-input-->
      <!--          v-model="formData.disposalProgressDesc"-->
      <!--          type="textarea"-->
      <!--          placeholder="请输入处置进展描述"-->
      <!--          :rows="2"-->
      <!--        />-->
      <!--      </el-form-item>-->

      <!--      <el-form-item label="处置附件路径" prop="disposalAttachmentPath">-->
      <!--        <el-input v-model="formData.disposalAttachmentPath" placeholder="请输入附件路径" />-->
      <!--      </el-form-item>-->

      <!--      <el-form-item label="审核意见" prop="reviewOpinion">-->
      <!--        <el-input v-model="formData.reviewOpinion" placeholder="请输入审核意见" />-->
      <!--      </el-form-item>-->

      <!--      <el-form-item label="审核人" prop="reviewer">-->
      <!--        <el-input v-model="formData.reviewer" placeholder="请输入审核人" />-->
      <!--      </el-form-item>-->

      <!--      <el-form-item label="审核时间" prop="reviewTime">-->
      <!--        <el-date-picker-->
      <!--          v-model="formData.reviewTime"-->
      <!--          type="datetime"-->
      <!--          value-format="x"-->
      <!--          placeholder="选择审核时间"-->
      <!--          style="width: 100%"-->
      <!--        />-->
      <!--      </el-form-item>-->
    </el-form>

    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import { WarningAlertListTableApi, WarningAlertListTableVO } from '@/api/dataHub/warningAlarm/list'
import { ref, reactive, nextTick } from 'vue'
import { MonEvtCatApi } from '@/api/dataHub/monitorCompEventMgr/monitorEvtConfigMgr/monevtcat'
import { AreaApi } from '@/api/dataHub/gridManagement/adminDivConfig'

defineOptions({ name: 'WarningAlertListTableForm' })
const { t } = useI18n()
const message = useMessage()

const dialogVisible = ref(false)
const dialogTitle = ref('')
const formLoading = ref(false)
const formType = ref<'create' | 'update'>('create')
const formData = ref<Partial<WarningAlertListTableVO>>({})
const formRef = ref()

const formRules = reactive({
  alertCode: [{ required: true, message: '告警编号不能为空', trigger: 'blur' }],
  relatedObjectType: [{ required: true, message: '关联对象类型不能为空', trigger: 'change' }],
  relatedObjectId: [{ required: true, message: '关联对象不能为空', trigger: 'change' }],
  warningTypeId: [{ required: true, message: '预警类型不能为空', trigger: 'change' }],
  warningLevel: [{ required: true, message: '预警等级不能为空', trigger: 'change' }],
  triggerTime: [{ required: true, message: '触发时间不能为空', trigger: 'blur' }],
  requiredCompleteTime: [{ required: true, message: '要求完成时间不能为空', trigger: 'blur' }],
  warningField: [{ required: true, message: '预警领域不能为空', trigger: 'blur' }],
  status: [{ required: true, message: '预警状态不能为空', trigger: 'change' }],
  triggerReason: [{ required: true, message: '触发原因不能为空', trigger: 'blur' }]
})

/** ========== 设备、部件、行政区划数据加载 ========== */
const deviceList = ref<any[]>([])
const partList = ref<any[]>([])
const areaTree = ref<any[]>([])
const deviceLoading = ref(false)
const partLoading = ref(false)
const areaLoading = ref(false)
const selectedDeviceId = ref<number | null>(null)
const selectedPartId = ref<number | null>(null)
const selectedAreaId = ref<string | null>(null)

const handleRelatedTypeChange = async (val: string) => {
  if (val === 'device') await getDeviceList()
  if (val === 'part') await getPartList()
  if (val === 'area') await getAreaTree()
  formData.value.relatedObjectId = undefined
  formData.value.relatedObjectName = undefined
  selectedDeviceId.value = null
  selectedPartId.value = null
  selectedAreaId.value = null
}

/** 获取设备列表 */
const getDeviceList = async () => {
  deviceLoading.value = true
  try {
    const res = await WarningAlertListTableApi.getDeviceList({ pageNo: 1, pageSize: 10 })
    deviceList.value = res.list
  } finally {
    deviceLoading.value = false
  }
}

/** 获取部件列表 */
const getPartList = async () => {
  partLoading.value = true
  try {
    const res = await WarningAlertListTableApi.getPartList({ pageNo: 1, pageSize: 10 })
    partList.value = res.data?.list || []
  } finally {
    partLoading.value = false
  }
}

/** 获取行政区划树结构 */
const getAreaTree = async () => {
  areaLoading.value = true
  try {
    const res = await AreaApi.getAreaPage({ pageNo: 1, pageSize: 100 })
    const list = res.list || []
    areaTree.value = buildAreaTree(list)
  } finally {
    areaLoading.value = false
  }
}

/** 构建行政区划树结构 */
function buildAreaTree(list: any[]) {
  const map = new Map()
  const tree: any[] = []
  list.forEach((item) => {
    map.set(item.id, { ...item, children: [] })
  })
  list.forEach((item) => {
    const node = map.get(item.id)
    if (item.parentId) {
      const parent = map.get(item.parentId)
      if (parent) parent.children.push(node)
      else tree.push(node)
    } else {
      tree.push(node)
    }
  })
  return tree
}

/** 选中行政区划节点 */
const handleAreaTreeChange = (code: string) => {
  const node = findAreaNodeByCode(areaTree.value, code)
  if (node) {
    formData.value.relatedObjectId = node.fullCode
    formData.value.relatedObjectName = node.name
  }
}

function findAreaNodeByCode(list: any[], code: string): any | null {
  for (const item of list) {
    if (item.fullCode === code) return item
    if (item.children) {
      const res = findAreaNodeByCode(item.children, code)
      if (res) return res
    }
  }
  return null
}

/** 设备或部件选中 */
const handleDeviceSelectChange = (val: number) => {
  const selected = deviceList.value.find((d) => d.id === val)
  if (selected) {
    formData.value.relatedObjectId = selected.id
    formData.value.relatedObjectName = selected.name
  }
}

const handlePartSelectChange = (val: number) => {
  const selected = partList.value.find((p) => p.id === val)
  if (selected) {
    formData.value.relatedObjectId = selected.id
    formData.value.relatedObjectName = selected.name
  }
}

/** ========== 预警类型树加载与选择 ========== */
const warningTypeTree = ref<any[]>([])
const treeLoading = ref(false)

function buildTreeSelectData(list: any[]) {
  const map = new Map()
  const tree: any[] = []
  list.forEach((item) =>
    map.set(item.id, { value: item.matterCode, label: item.matterName, children: [] })
  )
  list.forEach((item) => {
    const node = map.get(item.id)
    if (item.parentId && item.parentId !== '0') {
      const parent = map.get(Number(item.parentId))
      if (parent) parent.children.push(node)
    } else tree.push(node)
  })
  return tree
}

const handleWarningTypeChange = (val: number) => {
  formData.value.warningTypeId = val
  const label = findLabelByCode(warningTypeTree.value, val)
  formData.value.warningType = label
}

function findLabelByCode(nodes: any[], code: number): string | undefined {
  for (const n of nodes) {
    if (n.value === code) return n.label
    if (n.children) {
      const label = findLabelByCode(n.children, code)
      if (label) return label
    }
  }
}

/**  状态字段格式化函数 */
function formatStatus(status: any): number {
  if (status === 0 || status === '0') return 0
  if (status === 1 || status === '1') return 1
  return -1
}

/** ========== 派发部门树形下拉 ========== */
const deptTree = ref<any[]>([])
const deptLoading = ref(false)

/** 构建部门树结构 */
function buildDeptTree(list: any[]) {
  const map = new Map()
  const tree: any[] = []
  list.forEach((item) => {
    map.set(item.id, { value: item.id, label: item.name, children: [] })
  })
  list.forEach((item) => {
    const node = map.get(item.id)
    if (item.parentId && item.parentId !== 0) {
      const parent = map.get(item.parentId)
      if (parent) parent.children.push(node)
      else tree.push(node)
    } else {
      tree.push(node)
    }
  })
  return tree
}

/** 获取部门树数据 */
const getDeptTree = async () => {
  deptLoading.value = true
  try {
    const res = await WarningAlertListTableApi.getDeptTree()
    deptTree.value = buildDeptTree(res || [])
  } finally {
    deptLoading.value = false
  }
}

/** 选中部门 */
const handleDeptChange = (val: number) => {
  const node = findDeptNodeById(deptTree.value, val)
  if (node) {
    formData.value.dispatchDepartment = node.value // 存id
  }
}

/** 根据id查找节点 */
function findDeptNodeById(list: any[], id: number): any | null {
  for (const item of list) {
    if (item.value === id) return item
    if (item.children?.length) {
      const res = findDeptNodeById(item.children, id)
      if (res) return res
    }
  }
  return null
}

/** ====================== 用户下拉列表 ====================== */
const userList = ref<{ id: number; nickname: string; deptName?: string | null }[]>([])
const userLoading = ref(false)

/** 获取用户下拉数据 */
const getUserList = async () => {
  userLoading.value = true
  try {
    const res = await WarningAlertListTableApi.getSimpleUserList()
    userList.value = res || []
  } finally {
    userLoading.value = false
  }
}

/** 根据用户ID查找昵称 */
function findUserNicknameById(id: number | string): string | undefined {
  const u = userList.value.find((item) => item.id === Number(id))
  return u?.nickname
}

/** 打开弹窗逻辑（含预警状态回显） */
const loadWarningTypeTree = async (preLabel?: string) => {
  treeLoading.value = true
  try {
    const res = await WarningAlertListTableApi.getWarningTypeTree()
    warningTypeTree.value = buildTreeSelectData(res)
    if (preLabel) {
      const code = findCodeByLabel(warningTypeTree.value, preLabel)
      if (code) formData.value.warningTypeId = code
    }
  } finally {
    treeLoading.value = false
  }
}

function findCodeByLabel(nodes: any[], label: string): number | undefined {
  for (const n of nodes) {
    if (n.label === label) return n.value
    if (n.children) {
      const code = findCodeByLabel(n.children, label)
      if (code) return code
    }
  }
}

/** ✅ 新增/修改弹窗逻辑（含网格回显与状态回显） */
const open = async (type: 'create' | 'update', id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = type === 'create' ? '新增预警告警' : '修改预警告警'
  formType.value = type
  resetForm()
  formLoading.value = true
  try {
    await getDeptTree()
    await getUserList()
    if (type === 'update' && id) {
      const data = await WarningAlertListTableApi.getWarningAlertListTable(id)

      await nextTick()
      formData.value = data
      await loadWarningTypeTree(data.warningType)

      // 修正预警状态回显
      formData.value.status = formatStatus(data.status)

      // ✅ 责任人字段回显，将 id 转为昵称（不改原值，只做展示匹配）
      if (data.responsiblePerson) {
        const nickname = findUserNicknameById(data.responsiblePerson)
        if (nickname) {
          formData.value.responsiblePerson = nickname
        }
      }

      const node = findDeptNodeById(deptTree.value, Number(data.dispatchDepartment))
      if (node) {
        formData.value.dispatchDepartment = node.value
      }

      if (data.relatedObjectType === 'area') {
        await getAreaTree()
        selectedAreaId.value = data.relatedObjectId
      } else if (data.relatedObjectType === 'device') {
        await getDeviceList()
        selectedDeviceId.value = data.relatedObjectId
      } else if (data.relatedObjectType === 'part') {
        await getPartList()
        selectedPartId.value = data.relatedObjectId
      }
    } else {
      await loadWarningTypeTree()
    }
  } finally {
    formLoading.value = false
  }
}

/** 提交表单 */
const emit = defineEmits(['success'])
const submitForm = async () => {
  await formRef.value.validate()
  formLoading.value = true
  try {
    const data = formData.value as WarningAlertListTableVO
    if (formType.value === 'create') {
      await WarningAlertListTableApi.createWarningAlertListTable(data)
      message.success(t('common.createSuccess'))
    } else {
      await WarningAlertListTableApi.updateWarningAlertListTable(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    emit('success')
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {}
  selectedDeviceId.value = null
  selectedPartId.value = null
  selectedAreaId.value = null
  formRef.value?.resetFields()
}

defineExpose({ open })
</script>
