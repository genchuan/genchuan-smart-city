<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="600px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="120px"
      v-loading="formLoading"
      class="form-container"
    >
      <!-- ========= 事件类型 ========= -->
      <el-form-item label="事件类型代码" prop="eventTypeId">
        <el-input v-model="formData.eventTypeId" placeholder="请选择事件类型自动填充" readonly />
      </el-form-item>

      <el-form-item label="事件类型" prop="eventTypeName">
        <el-tree-select
          v-model="selectedEventType"
          :data="eventTypeTreeData"
          :props="eventTypeTreeProps"
          placeholder="请选择事件类型"
          filterable
          :filter-method="filterEventType"
          @change="handleEventTypeChange"
          :disabled="formLoading"
        />
      </el-form-item>

      <!-- ========= 行政区划 ========= -->
      <el-form-item label="行政区划编码" prop="divisionCode">
        <el-input v-model="formData.divisionCode" placeholder="请选择行政区划自动填充" readonly />
      </el-form-item>

      <el-form-item label="行政区划名称" prop="divisionName">
        <el-tree-select
          v-model="selectedArea"
          :data="areaTreeData"
          :props="areaTreeProps"
          placeholder="请选择行政区划"
          filterable
          :filter-method="filterArea"
          @change="handleAreaChange"
          :disabled="formLoading"
        />
      </el-form-item>

      <!-- ========= 流程模型 ========= -->
      <el-form-item label="流程标识" prop="processModelId">
        <el-input v-model="formData.processModelId" placeholder="请选择流程模型自动填充" readonly />
      </el-form-item>

      <el-form-item label="流程模型" prop="processModelName">
        <el-select
          v-model="selectedProcessModel"
          placeholder="请选择流程模型"
          filterable
          :filter-method="filterProcessModel"
          @change="handleProcessModelChange"
          :disabled="formLoading"
        >
          <el-option-group
            v-for="(group, category) in processModelGroups"
            :key="category"
            :label="category || '未分类'"
          >
            <el-option
              v-for="model in group"
              :key="model.key"
              :label="model.name"
              :value="model.key"
            />
          </el-option-group>
        </el-select>
      </el-form-item>

      <!-- ========= 其他扩展字段 ========= -->
      <el-form-item label="分类扩展字段1" prop="extCat1">
        <el-input v-model="formData.extCat1" placeholder="请输入分类扩展字段1" />
      </el-form-item>
      <el-form-item label="分类扩展字段2" prop="extCat2">
        <el-input v-model="formData.extCat2" placeholder="请输入分类扩展字段2" />
      </el-form-item>
      <el-form-item label="通用扩展字段1" prop="extCommon1">
        <el-input v-model="formData.extCommon1" placeholder="请输入通用扩展字段1" />
      </el-form-item>
      <el-form-item label="通用扩展字段2" prop="extCommon2">
        <el-input v-model="formData.extCommon2" placeholder="请输入通用扩展字段2" />
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import { ref, reactive, computed } from 'vue'
import { useI18n } from '@/hooks/web/useI18n'
import { useMessage } from '@/hooks/web/useMessage'

import {
  EventDispositionApi,
  EventDispositionVO
} from '@/api/dataHub/monitorCompEventMgr/monitorEvtConfigMgr/monitorEvtRelDisposition'

import {
  MonEvtCatApi,
  MonEvtCatVO
} from '@/api/dataHub/monitorCompEventMgr/monitorEvtConfigMgr/monevtcat'

import { AreaApi, AreaVO } from '@/api/dataHub/gridManagement/adminDivConfig'
import * as ModelApi from '@/api/bpm/model'

defineOptions({ name: 'EventDispositionForm' })

const { t } = useI18n()
const message = useMessage()

/* ================== 基础状态 ================== */
const dialogVisible = ref(false)
const dialogTitle = ref('')
const formLoading = ref(false)
const formType = ref('')
const formRef = ref()

const formData = ref<Partial<EventDispositionVO>>({
  id: undefined,
  eventTypeId: '',
  eventTypeName: '',
  divisionCode: '',
  divisionName: '',
  processModelId: '',
  processModelName: '',
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

/* ================== 校验规则 ================== */
const formRules = reactive({
  eventTypeId: [{ required: true, message: '事件类型ID不能为空', trigger: 'blur' }],
  eventTypeName: [{ required: true, message: '事件类型名称不能为空', trigger: 'change' }],
  divisionCode: [{ required: true, message: '行政区划编码不能为空', trigger: 'blur' }],
  divisionName: [{ required: true, message: '行政区划名称不能为空', trigger: 'change' }],
  processModelId: [{ required: true, message: '流程模型ID不能为空', trigger: 'blur' }],
  processModelName: [{ required: true, message: '流程模型名称不能为空', trigger: 'change' }]
})

/* ================== 事件类型树 ================== */
const eventTypeList = ref<MonEvtCatVO[]>([])
const eventTypeTreeData = ref<any[]>([])
const rawEventTypeTreeData = ref<any[]>([]) // 为过滤保留原始树
const selectedEventType = ref<string>()

const eventTypeTreeProps = {
  label: 'evtCatName',
  value: 'evtCatCode',
  children: 'children'
}

/* ================== 行政区划树 ================== */
const areaList = ref<AreaVO[]>([])
const areaTreeData = ref<any[]>([])
const rawAreaTreeData = ref<any[]>([]) // 保存原始行政区划树，避免过滤污染
const selectedArea = ref<string>()

const areaTreeProps = {
  label: 'name',
  value: 'fullCode',
  children: 'children'
}

/* ================== 流程模型 ================== */
const rawProcessModelList = ref<any[]>([]) // 原始列表
const processModelList = ref<any[]>([])
const selectedProcessModel = ref<string>()

const processModelGroups = computed(() => {
  const groups: Record<string, any[]> = {}
  processModelList.value.forEach((m) => {
    const cat = m.categoryName || '未分类'
    if (!groups[cat]) groups[cat] = []
    groups[cat].push(m)
  })
  return groups
})

/* ================== 打开弹窗 ================== */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()

  await loadSelectData()

  if (id) {
    formLoading.value = true
    try {
      const detail = await EventDispositionApi.getEventDisposition(id)
      formData.value = { ...detail }

      selectedEventType.value = detail.eventTypeId
      selectedArea.value = detail.divisionCode
      selectedProcessModel.value = detail.processModelId
    } finally {
      formLoading.value = false
    }
  }
}

defineExpose({ open })

/* ================== 加载所有下拉数据 ================== */
const loadSelectData = async () => {
  formLoading.value = true
  try {
    /* ---- 事件类型平级转树 ---- */
    const eventRes = await MonEvtCatApi.getMonEvtCatPage({ pageNo: 1, pageSize: 100 })
    eventTypeList.value = eventRes.list || []
    rawEventTypeTreeData.value = buildTree(eventTypeList.value, 'monEvtCatId', 'parentEvtId')
    eventTypeTreeData.value = rawEventTypeTreeData.value

    /* ---- 行政区划：必须使用树接口 ---- */
    const areaTree = await AreaApi.getTree() //
    rawAreaTreeData.value = areaTree || []
    areaTreeData.value = rawAreaTreeData.value

    // 扁平化
    areaList.value = []
    flattenAreaList(rawAreaTreeData.value, areaList)

    /* ---- 流程模型 ---- */
    const modelRes = await ModelApi.getModelList()
    rawProcessModelList.value = modelRes || []
    processModelList.value = rawProcessModelList.value
  } catch (e) {
    console.error(e)
    message.error('下拉数据加载失败')
  } finally {
    formLoading.value = false
  }
}

/* ================== 通用树过滤函数 ================== */
const filterTree = (query: string, treeData: any[], labelKey: string) => {
  if (!query) return treeData
  const lower = query.toLowerCase()

  const filterNode = (nodes: any[]): any[] => {
    const arr: any[] = []
    nodes.forEach((node) => {
      const label = (node[labelKey] || '').toLowerCase()
      const isMatch = label.includes(lower)

      const children = node.children ? filterNode(node.children) : []
      if (isMatch || children.length > 0) {
        arr.push({ ...node, children })
      }
    })
    return arr
  }

  return filterNode(treeData)
}

/* ================== 各类型过滤方法 ================== */
const filterEventType = (val: string) => {
  eventTypeTreeData.value = filterTree(val, rawEventTypeTreeData.value, 'evtCatName')
}

const filterArea = (val: string) => {
  areaTreeData.value = filterTree(val, rawAreaTreeData.value, 'name')
}

const filterProcessModel = (val: string) => {
  if (!val) {
    processModelList.value = rawProcessModelList.value
    return
  }
  const lower = val.toLowerCase()
  processModelList.value = rawProcessModelList.value.filter((m) =>
    m.name?.toLowerCase().includes(lower)
  )
}

/* ================== 变更回填 ================== */
const handleEventTypeChange = (value: string) => {
  const item = eventTypeList.value.find((t) => t.evtCatCode === value)
  if (item) {
    formData.value.eventTypeId = item.evtCatCode
    formData.value.eventTypeName = item.evtCatName
  }
}

const handleAreaChange = (value: string) => {
  const item = areaList.value.find((a) => a.fullCode === value)
  if (item) {
    formData.value.divisionCode = item.fullCode
    formData.value.divisionName = item.name
  }
}

const handleProcessModelChange = (value: string) => {
  const item = rawProcessModelList.value.find((m) => m.key === value)
  if (item) {
    formData.value.processModelId = item.key
    formData.value.processModelName = item.name
  }
}

/* ================== 构建事件类型树 ================== */
const buildTree = (list: any[], idKey: string, parentKey: string) => {
  const map = new Map()
  const tree: any[] = []

  list.forEach((i) => map.set(i[idKey], { ...i, children: [] }))

  list.forEach((i) => {
    const parent = map.get(i[parentKey])
    if (parent) {
      parent.children.push(map.get(i[idKey]))
    } else {
      tree.push(map.get(i[idKey]))
    }
  })

  return tree
}

/* ================== 行政区划扁平化 ================== */
const flattenAreaList = (tree: any[], result: Ref<AreaVO[]>) => {
  tree.forEach((i) => {
    result.value.push(i)
    if (i.children?.length) flattenAreaList(i.children, result)
  })
}

/* ================== 提交 ================== */
const emit = defineEmits(['success'])

const submitForm = async () => {
  if (!formRef.value) return
  try {
    await formRef.value.validate()
    formLoading.value = true

    const payload = formData.value as EventDispositionVO
    if (formType.value === 'create') {
      await EventDispositionApi.createEventDisposition(payload)
      message.success(t('common.createSuccess'))
    } else {
      await EventDispositionApi.updateEventDisposition(payload)
      message.success(t('common.updateSuccess'))
    }

    dialogVisible.value = false
    emit('success')
  } catch (e) {
    console.error(e)
    message.error(t('common.operateFail'))
  } finally {
    formLoading.value = false
  }
}

/* ================== 重置 ================== */
const resetForm = () => {
  formData.value = {
    id: undefined,
    eventTypeId: '',
    eventTypeName: '',
    divisionCode: '',
    divisionName: '',
    processModelId: '',
    processModelName: '',
    extCat1: '',
    extCat2: '',
    extCommon1: '',
    extCommon2: ''
  }

  selectedEventType.value = ''
  selectedArea.value = ''
  selectedProcessModel.value = ''

  formRef.value?.resetFields()
}
</script>

<style scoped>
.form-container {
  padding: 15px 0;
}

::v-deep .el-form-item {
  margin-bottom: 18px;
}

::v-deep .el-tree-select-dropdown .el-tree {
  max-height: 400px;
  overflow-y: auto;
}

::v-deep .el-select-dropdown .el-option-group__label {
  color: #666;
  font-weight: 500;
  padding: 8px 16px;
}

::v-deep .el-select-dropdown .el-option {
  padding-left: 24px;
}
</style>
