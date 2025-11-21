<template>
  <el-dialog v-model:visible="visible" title="导入 Excel" width="700px" :destroy-on-close="true">
    <el-upload
      ref="uploadRef"
      :auto-upload="false"
      :before-upload="beforeUpload"
      :on-change="onChange"
      accept=".xlsx,.xls,.csv"
      drag
      style="width:100%"
    >
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或点击上传</div>
      <template #tip>
        <div class="el-upload__tip">支持 .xlsx/.xls/.csv；必须包含列：matter_code,matter_minor_id,grid_id（其他列可选）</div>
      </template>
    </el-upload>

    <div v-if="preview.length" style="margin-top:12px">
      <h4>导入预览（前 20 行）</h4>
      <el-table :data="preview" size="small" style="width:100%">
        <el-table-column prop="matter_code" label="标识码" width="180" />
        <el-table-column prop="matter_minor_id" label="小类ID" width="140" />
        <el-table-column prop="grid_id" label="网格ID" width="140" />
        <el-table-column prop="matter_name" label="事项名称" />
      </el-table>
    </div>

    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" :disabled="!preview.length" @click="confirmImport">确认导入</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, watch } from 'vue'
import * as XLSX from 'xlsx'
import { ElMessage } from 'element-plus'

const props = defineProps({
  visible: Boolean
})
const emit = defineEmits(['update:visible', 'imported'])

const visible = ref(props.visible)
watch(() => props.visible, v => visible.value = v)
watch(visible, v => emit('update:visible', v))

const uploadRef = ref(null)
const preview = ref([])

// read file and parse
function beforeUpload(file) {
  // accept check
  const name = file.name.toLowerCase()
  const ok = name.endsWith('.xlsx') || name.endsWith('.xls') || name.endsWith('.csv')
  if (!ok) {
    ElMessage.error('仅支持 .xlsx/.xls/.csv 文件')
    return false
  }
  return true
}

function onChange(file, fileList) {
  const raw = file.raw
  const reader = new FileReader()
  reader.onload = (e) => {
    const data = e.target.result
    let workbook
    if (file.name.toLowerCase().endsWith('.csv')) {
      // CSV: read as text
      const text = data
      const rows = text.split(/\r\n|\n/).filter(Boolean).map(r => r.split(','))
      // naive: use header row
      const header = rows[0].map(h=>h.trim())
      const arr = rows.slice(1).map(cols=>{
        const obj = {}
        cols.forEach((c,i)=>obj[header[i] ? header[i].trim() : `col${i}`] = c)
        return obj
      })
      preview.value = arr.slice(0,20)
    } else {
      // XLSX/XLS: read as binary string
      workbook = XLSX.read(data, { type: 'binary' })
      const firstSheetName = workbook.SheetNames[0]
      const ws = workbook.Sheets[firstSheetName]
      const json = XLSX.utils.sheet_to_json(ws, { defval: '' })
      preview.value = json.slice(0, 20)
    }
  }
  // read as text for csv, binary for xlsx
  if (raw.name.toLowerCase().endsWith('.csv')) reader.readAsText(raw, 'utf-8')
  else reader.readAsBinaryString(raw)
}

// when confirm: emit parsed data (full sheet)
function confirmImport() {
  const fileList = uploadRef.value ? uploadRef.value.uploadFiles : []
  if (!fileList || !fileList.length) { ElMessage.warning('请先选择文件') ; return }

  const file = fileList[fileList.length - 1].raw
  const reader = new FileReader()
  reader.onload = (e) => {
    let workbook, json
    if (file.name.toLowerCase().endsWith('.csv')) {
      const text = e.target.result
      const rows = text.split(/\r\n|\n/).filter(Boolean).map(r => r.split(','))
      const header = rows[0].map(h=>h.trim())
      json = rows.slice(1).map(cols=>{
        const obj = {}
        cols.forEach((c,i)=>obj[header[i] ? header[i].trim() : `col${i}`] = c)
        return obj
      })
    } else {
      workbook = XLSX.read(e.target.result, { type: 'binary' })
      const firstSheetName = workbook.SheetNames[0]
      const ws = workbook.Sheets[firstSheetName]
      json = XLSX.utils.sheet_to_json(ws, { defval: '' })
    }

    // normalize rows: expect keys matter_code, matter_minor_id, grid_id; allow alternate names
    const rows = json.map((row, idx) => {
      // try to find keys case-insensitively
      const keyMap = {}
      Object.keys(row).forEach(k => { keyMap[k.toLowerCase().replace(/\s/g,'')] = k })
      const get = (names) => {
        for (const n of names) {
          const k = keyMap[n.toLowerCase().replace(/\s/g,'')]
          if (k) return row[k]
        }
        return ''
      }
      return {
        matter_code: get(['matter_code','标识码','code'])?.toString().trim(),
        matter_minor_id: get(['matter_minor_id','小类id','minorid'])?.toString().trim(),
        matter_minor_name: get(['matter_minor_name','小类名称','minorname'])?.toString().trim(),
        grid_id: get(['grid_id','网格id','gridid'])?.toString().trim(),
        grid_name: get(['grid_name','网格名称','gridname'])?.toString().trim(),
        matter_name: get(['matter_name','事项名称','name'])?.toString().trim(),
        incident_location: get(['incident_location','事发位置','location'])?.toString().trim(),
        dept_code: get(['dept_code','主管部门代码','deptcode'])?.toString().trim(),
        dept_name: get(['dept_name','主管部门','deptname'])?.toString().trim()
      }
    })

    // simple validation: remove totally empty rows
    const filtered = rows.filter(r => r.matter_code || r.matter_name)
    if (!filtered.length) { ElMessage.error('未解析到有效数据，请检查文件格式') ; return }

    // emit parsed rows
    emit('imported', filtered)
    visible.value = false
    // reset upload component
    if (uploadRef.value) uploadRef.value.clearFiles()
    ElMessage.success(`已解析 ${filtered.length} 条数据（预览已显示）`)
  }

  if (file.name.toLowerCase().endsWith('.csv')) reader.readAsText(file, 'utf-8')
  else reader.readAsBinaryString(file)
}
</script>

<style scoped>
.filter-card { margin-bottom: 12px }
.filter-form .el-form-item { margin-right: 8px }
.map-box { height: 400px; border: 1px dashed #dcdfe6; border-radius:6px; display:flex; align-items:center; justify-content:center; }
</style>
