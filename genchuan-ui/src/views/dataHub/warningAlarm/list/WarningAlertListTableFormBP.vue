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
        <el-select v-model="formData.relatedObjectType" placeholder="请选择关联对象类型" clearable>
          <el-option label="设备" value="device" />
          <el-option label="部件" value="part" />
          <el-option label="网格" value="grid" />
        </el-select>
      </el-form-item>

      <el-form-item label="关联对象ID" prop="relatedObjectId">
        <el-input v-model="formData.relatedObjectId" placeholder="请输入关联对象ID" />
      </el-form-item>

      <el-form-item label="关联对象名称" prop="relatedObjectName">
        <el-input v-model="formData.relatedObjectName" placeholder="请输入关联对象名称" />
      </el-form-item>

      <!-- 预警领域 -->
      <el-form-item label="预警领域" prop="warningField">
        <el-input v-model="formData.warningField" placeholder="请输入预警领域" />
      </el-form-item>

      <!-- ✅ 预警类型：树形选择（id + 名称） -->
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

      <!-- 预警状态 -->
      <el-form-item label="预警状态" prop="status">
        <el-select v-model="formData.status" placeholder="请选择预警状态" clearable>
          <el-option label="待派单" value="0" />
          <el-option label="已派单" value="1" />
        </el-select>
      </el-form-item>

      <el-form-item label="触发原因" prop="triggerReason">
        <el-input v-model="formData.triggerReason" placeholder="请输入触发原因" />
      </el-form-item>

      <el-form-item label="关联事件编号" prop="relatedEventCode">
        <el-input v-model="formData.relatedEventCode" placeholder="请输入关联事件编号" />
      </el-form-item>

      <el-form-item label="派发部门" prop="dispatchDepartment">
        <el-input v-model="formData.dispatchDepartment" placeholder="请输入派发部门" />
      </el-form-item>

      <el-form-item label="责任人" prop="responsiblePerson">
        <el-input v-model="formData.responsiblePerson" placeholder="请输入责任人" />
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

      <el-form-item label="处置进展描述" prop="disposalProgressDesc">
        <el-input
          v-model="formData.disposalProgressDesc"
          type="textarea"
          placeholder="请输入处置进展描述"
          :rows="2"
        />
      </el-form-item>

      <el-form-item label="处置附件路径" prop="disposalAttachmentPath">
        <el-input v-model="formData.disposalAttachmentPath" placeholder="请输入附件路径" />
      </el-form-item>

      <el-form-item label="审核意见" prop="reviewOpinion">
        <el-input v-model="formData.reviewOpinion" placeholder="请输入审核意见" />
      </el-form-item>

      <el-form-item label="审核人" prop="reviewer">
        <el-input v-model="formData.reviewer" placeholder="请输入审核人" />
      </el-form-item>

      <el-form-item label="审核时间" prop="reviewTime">
        <el-date-picker
          v-model="formData.reviewTime"
          type="datetime"
          value-format="x"
          placeholder="选择审核时间"
          style="width: 100%"
        />
      </el-form-item>

      <!--      <el-form-item label="设备ID" prop="deviceId">-->
      <!--        <el-input v-model="formData.deviceId" placeholder="请输入设备ID" />-->
      <!--      </el-form-item>-->

    </el-form>

    <!-- 底部按钮 -->
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import { WarningAlertListTableApi, WarningAlertListTableVO } from '@/api/dataHub/warningAlarm/list'
import { ref, reactive, nextTick } from 'vue'

defineOptions({ name: 'WarningAlertListTableForm' })

const { t } = useI18n()
const message = useMessage()

/** 弹窗控制 */
const dialogVisible = ref(false)
const dialogTitle = ref('')
const formLoading = ref(false)
const formType = ref<'create' | 'update'>('create')

/** 表单绑定对象 */
const formData = ref<Partial<WarningAlertListTableVO>>({})
const formRef = ref()

/** 表单规则 */
const formRules = reactive({
  alertCode: [{ required: true, message: '告警编号不能为空', trigger: 'blur' }],
  relatedObjectType: [{ required: true, message: '关联对象类型不能为空', trigger: 'change' }],
  status: [{ required: true, message: '告警状态不能为空', trigger: 'change' }],
  triggerReason: [{ required: true, message: '触发原因不能为空', trigger: 'change' }],
  warningTypeId: [{ required: true, message: '预警类型不能为空', trigger: 'change' }],
  warningLevel: [{ required: true, message: '预警等级不能为空', trigger: 'change' }],
  triggerTime: [{ required: true, message: '触发时间不能为空', trigger: 'blur' }],
  requiredCompleteTime: [{ required: true, message: '要求完成时间不能为空', trigger: 'blur' }],
  warningField: [{ required: true, message: '预警领域不能为空', trigger: 'blur' }],
  relatedObjectId: [{ required: true, message: '关联对象ID不能为空', trigger: 'blur' }],
  relatedObjectName:[{ required: true, message: '关联对象名称不能为空', trigger: 'blur' }]
})

/** ------------------- 树形选择逻辑 ------------------- **/
const warningTypeTree = ref<any[]>([])
const treeLoading = ref(false)

/** 将接口返回的扁平结构转为树结构 */
function buildTreeSelectData(list: any[]) {
  const map = new Map()
  const tree: any[] = []
  list.forEach(item => {
    map.set(item.id, { value: item.id, label: item.matterName, children: [] })
  })
  list.forEach(item => {
    const node = map.get(item.id)
    if (item.parentId && item.parentId !== '0') {
      const parent = map.get(Number(item.parentId))
      if (parent) parent.children.push(node)
    } else {
      tree.push(node)
    }
  })
  return tree
}

/** 选择树节点时同步写入 warningType（名称）与 warningTypeId（id） */
const handleWarningTypeChange = (val: number) => {
  formData.value.warningTypeId = val
  const label = findLabelById(warningTypeTree.value, val)
  formData.value.warningType = label
}

/** 查找label */
const findLabelById = (nodes: any[], id: number): string | undefined => {
  for (const n of nodes) {
    if (n.value === id) return n.label
    if (n.children) {
      const label = findLabelById(n.children, id)
      if (label) return label
    }
  }
}

/** 加载树数据 */
const loadWarningTypeTree = async (preSelectedLabel?: string) => {
  treeLoading.value = true
  try {
    const res = await WarningAlertListTableApi.getWarningTypeTree()
    warningTypeTree.value = buildTreeSelectData(res)

    // 若已有 warningType 名称，自动匹配出对应 id
    if (preSelectedLabel) {
      const id = findIdByLabel(warningTypeTree.value, preSelectedLabel)
      if (id) formData.value.warningTypeId = id
    }
  } catch (e) {
    console.error('加载预警类型失败', e)
  } finally {
    treeLoading.value = false
  }
}

/** 根据label反查id */
const findIdByLabel = (nodes: any[], label: string): number | undefined => {
  for (const n of nodes) {
    if (n.label === label) return n.value
    if (n.children) {
      const id = findIdByLabel(n.children, label)
      if (id) return id
    }
  }
}

/** 打开弹窗 */
const open = async (type: 'create' | 'update', id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = type === 'create' ? '新增预警告警' : '修改预警告警'
  formType.value = type
  resetForm()
  formLoading.value = true

  try {
    if (type === 'update' && id) {
      const data = await WarningAlertListTableApi.getWarningAlertListTable(id)
      await nextTick()
      formData.value = data
      // 提前展示 warningType 名称
      const preLabel = data.warningType
      await loadWarningTypeTree(preLabel)
    } else {
      await loadWarningTypeTree()
    }
  } finally {
    formLoading.value = false
  }
}

/** 提交 */
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
  formRef.value?.resetFields()
}

defineExpose({ open })
</script>

<style scoped>
.el-tree-select__loading {
  opacity: 0.8;
}
</style>
