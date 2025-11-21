<template>
  <el-dialog :title="title" v-model:visible="visible" width="780px" :destroy-on-close="true">
    <el-form ref="formRef" :model="form" :rules="rules" label-width="120px" size="small">
      <el-row :gutter="12">
        <el-col :span="12">
          <el-form-item label="事项小类" prop="matter_minor_id">
            <el-cascader :options="cascaderOptions" v-model="form.cascader" @change="onCascaderChange" clearable />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="标识码" prop="matter_code">
            <el-input v-model="form.matter_code" maxlength="16" @blur="validateCode" />
            <el-button type="primary" text @click="generateCode">自动生成</el-button>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="事项名称" prop="matter_name">
            <el-input v-model="form.matter_name" />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="事发位置" prop="incident_location">
            <el-input v-model="form.incident_location" placeholder="地图拾取或手动输入" />
            <el-button type="primary" text @click="openMapPicker">地图拾取</el-button>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="所在网格" prop="grid_id">
            <el-select v-model="form.grid_id" placeholder="请选择网格">
              <el-option v-for="g in gridList" :key="g.id" :label="g.name" :value="g.id" />
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="事项等级" prop="matter_level">
            <el-select v-model="form.matter_level">
              <el-option label="一级" value="一级" />
              <el-option label="二级" value="二级" />
              <el-option label="三级" value="三级" />
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="主管部门" prop="dept_code">
            <el-select v-model="form.dept_code" @change="onDeptChange">
              <el-option v-for="d in deptList" :key="d.code" :label="d.name" :value="d.code" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="submit">保存</el-button>
    </template>

    <!-- 内置简单地图拾取弹窗（模拟） -->
    <el-dialog title="地图拾取" v-model:visible="mapPickerVisible" width="800px">
      <div class="map-box" @click="mockPick">
        <div style="text-align:center;padding:40px">模拟地图（点击随机拾取坐标）</div>
      </div>
      <template #footer>
        <el-button @click="mapPickerVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmMapPick">确定</el-button>
      </template>
    </el-dialog>
  </el-dialog>
</template>

<script setup>
import { ref, reactive, watch, computed } from 'vue'
import { ElMessage } from 'element-plus'

const props = defineProps({
  visible: Boolean,
  initialData: { type: Object, default: null }
})
const emit = defineEmits(['update:visible', 'saved'])

const visible = ref(props.visible)
watch(() => props.visible, v => visible.value = v)
watch(visible, v => emit('update:visible', v))

const title = computed(() => props.initialData ? '编辑事项' : '新增事项')

const formRef = ref(null)
const form = reactive({
  manage_matter_id: '',
  matter_code: '',
  matter_name: '',
  cascader: [],
  matter_minor_id: '',
  matter_minor_name: '',
  incident_location: '',
  grid_id: '',
  grid_name: '',
  matter_level: '',
  dept_code: '',
  dept_name: '',
  matter_status: '待处置'
})

watch(() => props.initialData, val => {
  if (val) {
    Object.assign(form, val)
    // set cascader if possible (mock)
    form.cascader = val.matter_minor_id ? ['major', val.matter_minor_id] : []
  } else {
    Object.keys(form).forEach(k => form[k] = k === 'matter_status' ? '待处置' : '')
  }
})

// validation
const rules = {
  matter_code: [
    { required: true, message: '标识码必填', trigger: 'blur' },
    { pattern: /^[0-9A-Za-z]{16}$/, message: '标识码必须为16位字符', trigger: 'blur' }
  ],
  matter_name: [{ required: true, message: '事项名称必填', trigger: 'blur' }],
  matter_minor_id: [{ required: true, message: '请选择小类', trigger: 'change' }],
  incident_location: [{ required: true, message: '事发位置必填', trigger: 'blur' }],
  grid_id: [{ required: true, message: '请选择网格', trigger: 'change' }],
  dept_code: [{ required: true, message: '请选择主管部门', trigger: 'change' }]
}

// static lists
const cascaderOptions = [
  { value: 'major-1', label: '市容类', children: [{ value: 'minor-1', label: '私搭乱建' }, { value: 'minor-3', label: '垃圾堆放' }] },
  { value: 'major-2', label: '交通类', children: [{ value: 'minor-2', label: '占道经营' }] }
]
const gridList = [{ id: 'G001', name: '网格A' }, { id: 'G002', name: '网格B' }]
const deptList = [{ code: 'D001', name: '城管局' }, { code: 'D002', name: '建设局' }]

// map picker
const mapPickerVisible = ref(false)
let mockedCoord = null
function openMapPicker() { mapPickerVisible.value = true }
function mockPick() {
  mockedCoord = { lng: (Math.random()*180-90).toFixed(6), lat: (Math.random()*90-45).toFixed(6) }
  ElMessage.info(`模拟拾取：${mockedCoord.lng}, ${mockedCoord.lat}`)
}
function confirmMapPick() {
  if (!mockedCoord) { ElMessage.warning('请先点击地图拾取位置') ; return }
  form.incident_location = `坐标(${mockedCoord.lng},${mockedCoord.lat})`
  form.grid_id = gridList[0].id
  form.grid_name = gridList[0].name
  mapPickerVisible.value = false
  mockedCoord = null
}

// cascader change
function onCascaderChange(val) {
  // val might be [major, minor]
  if (Array.isArray(val) && val[1]) {
    form.matter_minor_id = val[1]
    const label = cascaderOptions.flatMap(m=>m.children||[]).find(c=>c.value===val[1])?.label
    form.matter_minor_name = label || ''
  }
}

// dept change
function onDeptChange(code) {
  form.dept_name = deptList.find(d=>d.code===code)?.name || ''
}

// code generate/validate
function generateCode() {
  const admin = '100001'
  const major = form.cascader && form.cascader[0] ? '01' : '00'
  const minor = form.cascader && form.cascader[1] ? '001' : '000'
  const seq = String(Math.floor(Math.random()*99999)).padStart(5,'0')
  form.matter_code = `${admin}${major}${minor}${seq}`.slice(0,16)
  ElMessage.success('标识码已生成（示例）')
}
function validateCode() {
  if (!/^[0-9A-Za-z]{16}$/.test(form.matter_code)) ElMessage.warning('标识码格式不正确（需16位）')
}

// submit
function submit() {
  formRef.value.validate(valid => {
    if (!valid) return
    // assemble payload
    const payload = {
      manage_matter_id: form.manage_matter_id || null,
      matter_code: form.matter_code,
      matter_name: form.matter_name,
      matter_minor_id: form.matter_minor_id || (form.cascader && form.cascader[1]),
      matter_minor_name: form.matter_minor_name,
      incident_location: form.incident_location,
      grid_id: form.grid_id,
      grid_name: form.grid_name,
      matter_level: form.matter_level,
      dept_code: form.dept_code,
      dept_name: form.dept_name,
      matter_status: form.matter_status
    }
    emit('saved', payload)
    visible.value = false
  })
}
</script>

<style scoped>
.map-box { height: 360px; border: 1px dashed #dcdfe6; border-radius:6px; margin-bottom:12px; display:flex; align-items:center; justify-content:center; cursor:pointer }
</style>
