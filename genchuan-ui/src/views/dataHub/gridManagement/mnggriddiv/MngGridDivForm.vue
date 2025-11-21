<template>
  <el-dialog
    v-model="visible"
    :title="title"
    width="60%"
    destroy-on-close
  >
    <el-form
      ref="formRef"
      :model="form"
      label-width="120px"
      :rules="showValidation ? rules : {}"
    >
      <!-- 管理网格ID -->
      <el-form-item label="管理网格ID" prop="mngGridId">
        <el-input v-model="form.mngGridId" placeholder="请输入管理网格ID" />
      </el-form-item>

      <!-- 管理网格名称 -->
      <el-form-item label="管理网格名称" prop="mngGridName">
        <el-input v-model="form.mngGridName" placeholder="请输入名称" />
      </el-form-item>

      <!-- ✅ 所属乡镇（树形下拉） -->
      <el-form-item label="所属乡镇" prop="townStreetId">
        <el-tree-select
          v-model="form.townStreetId"
          :data="townList"
          node-key="fullCode"
          :props="treeProps"
          placeholder="请选择所属乡镇"
          check-strictly
          filterable
          clearable
          class="!w-240px"
          @change="handleTownChange"
        />
      </el-form-item>

      <!-- 网格员多选 -->
      <el-form-item label="网格员" prop="selectedGridPersonIds">
        <el-select
          v-model="selectedGridPersonIds"
          multiple
          filterable
          placeholder="请选择网格员"
          @change="handleGridPersonChange"
          style="width: 100%"
        >
          <el-option
            v-for="user in gridPersonList"
            :key="user.id"
            :label="user.nickname"
            :value="user.id"
          />
        </el-select>
        <div class="text-sm text-gray-500 mt-1">
          已选 {{ selectedGridPersonIds.length }} 位网格员
        </div>
      </el-form-item>

      <!-- 划分时间 -->
      <el-form-item label="划分时间" prop="divTime">
        <el-date-picker
          v-model="form.divTime"
          type="datetime"
          placeholder="请选择划分时间"
          value-format="x"
        />
      </el-form-item>

      <!-- 所含单元网格 -->
      <el-form-item label="所含单元网格" prop="includedUnitIds">
        <el-input v-model="form.includedUnitIds" readonly placeholder="点击右侧选择">
          <template #append>
            <el-button @click="openSelectGrid">选择</el-button>
          </template>
        </el-input>
        <div class="text-sm text-gray-500 mt-1">
          已选 {{ selectedCount }} 个单元，总面积 {{ form.area || 0 }} m²
        </div>
      </el-form-item>

      <el-form-item label="备注">
        <el-input v-model="form.remark" type="textarea" />
      </el-form-item>
    </el-form>

    <!--  底部按钮 -->
    <template #footer>
      <el-button @click="handleCancel">取消</el-button>
      <el-button type="primary" @click="handleSubmit">保存</el-button>
    </template>

    <!--  地图多选弹窗 -->
    <SelectUnitGridDialog ref="selectGridRef" @confirm="handleSelectGridConfirm" />
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, onMounted, watch, nextTick } from 'vue'
import { ElMessage } from 'element-plus'
import { MngGridDivApi } from '@/api/dataHub/gridManagement/divideConfig/mnggriddiv'
import SelectUnitGridDialog from './SelectUnitGridDialog.vue'
import { AreaApi } from '@/api/dataHub/gridManagement/adminDivConfig'

const emit = defineEmits(['refresh'])

/** 状态 */
const visible = ref(false)
const mode = ref<'create' | 'edit'>('create')
const title = ref('')
const formRef = ref<any>(null)
const form = ref<any>({}) // 表单 model，始终替换整个对象以避免残留
const showValidation = ref(false)

const townList = ref<any[]>([])
const gridPersonList = ref<any[]>([])
const selectedGridPersonIds = ref<string[]>([])
const selectedCount = ref(0)
const selectGridRef = ref<any>(null)

const treeProps = {
  label: 'name',
  children: 'children',
  value: 'fullCode'
}

const rules = {
  mngGridId: [{ required: true, message: '请输入管理网格ID', trigger: 'blur' }],
  mngGridName: [{ required: true, message: '请输入管理网格名称', trigger: 'blur' }],
  townStreetId: [{ required: true, message: '请选择所属乡镇', trigger: 'change' }],
  includedUnitIds: [{ required: true, message: '请选择单元网格', trigger: 'blur' }]
}

/** 加载下拉树数据 */
const loadTownList = async () => {
  try {
    const res = await AreaApi.getTakeEffect()
    townList.value = res?.data?.townList ?? res?.townList ?? []
  } catch (err) {
    console.error(err)
    ElMessage.error('乡镇数据加载失败')
  }
}

/** 加载网格员 */
const loadGridPersons = async () => {
  try {
    const res = await MngGridDivApi.getGridPersons()
    gridPersonList.value = Array.isArray(res) ? res : (res?.data || [])
  } catch (err) {
    console.error(err)
    ElMessage.error('网格员加载失败')
  }
}

/** 乡镇选择变化 */
const handleTownChange = (val: string) => {
  form.value.townStreetId = val
}

/** 网格员选择变化（顶层定义，模板可见） */
const handleGridPersonChange = (ids: string[]) => {
  form.value.extCommon1 = ids.join(',')
  const names = ids
    .map(id => gridPersonList.value.find(u => u.id === id)?.nickname || id)
    .join(',')
  form.value.extCommon2 = names
}

/** 打开地图选择单元网格 */
const openSelectGrid = () => {
  if (!form.value.townStreetId) {
    ElMessage.warning('请先选择所属乡镇')
    return
  }
  selectGridRef.value.open(form.value.townStreetId)
}

/** 接收地图选择结果 */
const handleSelectGridConfirm = async (ids: string[]) => {
  const idsStr = ids.join(',')
  form.value.includedUnitIds = idsStr
  selectedCount.value = ids.length

  await MngGridDivApi.validateUnitGrids({
    unitGridIds: ids,
    townStreetId: form.value.townStreetId
  })

  const res = await MngGridDivApi.calculateArea(idsStr)
  form.value.area = res
}

/** 清空表单（彻底） */
const clearFormState = () => {
  form.value = {
    mngGridId: '',
    mngGridName: '',
    townStreetId: '',
    includedUnitIds: '',
    area: 0,
    remark: '',
    divTime: '',
    extCommon1: '',
    extCommon2: ''
  }
  selectedGridPersonIds.value = []
  selectedCount.value = 0
  showValidation.value = false
}

/**
 * open: 注意时序 —— 先准备好 form 数据，再设置 visible = true，
 *          避免表单在挂载时读取旧的 model 引起残留。
 */
const open = async (m: 'create' | 'edit', row?: any) => {
  mode.value = m
  title.value = m === 'create' ? '新增管理网格' : '编辑管理网格'
  showValidation.value = false

  // 先加载下拉数据（可以并行加载，但不应覆盖 form）
  // We load lists in background; loading doesn't affect initial model
  loadTownList().catch(() => {})
  loadGridPersons().catch(() => {})

  if (m === 'edit' && row) {
    // 编辑：先把后端 row 赋给 form（回显）
    form.value = { ...row }
    selectedGridPersonIds.value = row.extCommon2
      ? String(row.extCommon2).split(',').filter(Boolean)
      : []
    selectedCount.value = row.includedUnitIds
      ? String(row.includedUnitIds).split(',').length
      : 0
  } else {
    // 新增：彻底清空（重要）
    clearFormState()
  }

  // 现在再显示对话框，确保 el-form 挂载时拿到期望的 model
  visible.value = true

  // 等 DOM 更新后清除可能残留的校验（不覆盖数据）
  await nextTick()
  formRef.value?.clearValidate?.()
}

/** 取消（关闭弹窗），并彻底清理，确保下次打开干净 */
const handleCancel = async () => {
  visible.value = false
  await nextTick()
  clearFormState()
  formRef.value?.clearValidate?.()
}

/** 提交（先开启校验，再 validate） */
const handleSubmit = async () => {
  showValidation.value = true
  try {
    await formRef.value.validate()
  } catch {
    return
  }

  try {
    if (mode.value === 'create') {
      await MngGridDivApi.createMngGridDiv(form.value)
      ElMessage.success('新增成功')
    } else {
      await MngGridDivApi.updateMngGridDiv(form.value)
      ElMessage.success('修改成功')
    }
    visible.value = false
    emit('refresh')

    // 清理状态，保证下次打开干净
    await nextTick()
    clearFormState()
  } catch (err) {
    console.error(err)
    ElMessage.error('保存失败')
  }
}

/** onMounted 预加载数据（可选） */
onMounted(() => {
  // 预加载乡镇列表以优化 UX（非必须）
  loadTownList().catch(() => {})
})

/** 当对话框被外部关闭（visible 变 false）时，确保 showValidation 重置 */
watch(visible, (v) => {
  if (!v) {
    showValidation.value = false
  }
})

defineExpose({ open })
</script>
