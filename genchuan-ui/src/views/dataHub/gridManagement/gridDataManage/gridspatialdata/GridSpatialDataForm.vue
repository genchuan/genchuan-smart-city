<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="720px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="140px"
      v-loading="formLoading"
    >
      <el-row :gutter="16">
        <el-col :span="12">
          <el-form-item label="空间数据ID" prop="spatialId">
            <el-input v-model="formData.spatialId" placeholder="请输入空间数据ID" />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="关联网格ID" prop="gridId">
            <el-input v-model="formData.gridId" placeholder="请输入关联网格ID" />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="网格类型" prop="gridType">
            <el-select v-model="formData.gridType" placeholder="请选择网格类型" clearable>
              <el-option v-for="t in gridTypes" :key="t" :label="t" :value="t" />
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="数据格式" prop="dataFormat">
            <el-select v-model="formData.dataFormat" placeholder="请选择数据格式" clearable>
              <el-option label="GeoJSON" value="GeoJSON" />
              <el-option label="SHP" value="SHP" />
              <el-option label="文本" value="文本" />
            </el-select>
          </el-form-item>
        </el-col>

        <!-- 边界字段与绘制占位 -->
        <el-col :span="24">
          <el-form-item label="边界坐标" prop="boundaryCoords" label-width="120px">
            <el-input
              type="textarea"
              :rows="3"
              v-model="formData.boundaryCoords"
              placeholder='格式："x1,y1;x2,y2;...;x1,y1"'
            />
            <div class="mt-8px flex items-center gap-8px">
              <el-button @click="openDraw">绘制/编辑边界</el-button>
              <el-button @click="checkBoundary">校验边界</el-button>
              <el-button @click="calcTopo">生成拓扑关系</el-button>
            </div>
            <div class="text-muted text-sm mt-6px">
              绘制占位：集成 GIS 时在 openDraw 中打开绘图工具。若不集成，直接粘坐标文本并点击校验。
            </div>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="坐标系" prop="coordSystem">
            <el-input v-model="formData.coordSystem" disabled placeholder="2000国家大地" />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="拓扑关系" prop="topoRelation">
            <el-input v-model="formData.topoRelation" placeholder="系统生成或手动填写邻接ID，逗号分隔" />
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="备注" prop="remark">
            <el-input v-model="formData.remark" />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="分类扩展字段1" prop="extCat1">
            <el-input v-model="formData.extCat1" />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="分类扩展字段2" prop="extCat2">
            <el-input v-model="formData.extCat2" />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="通用扩展字段1" prop="extCommon1">
            <el-input v-model="formData.extCommon1" />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="通用扩展字段2" prop="extCommon2">
            <el-input v-model="formData.extCommon2" />
          </el-form-item>
        </el-col>

        <!-- 附件（前端mock） 提交时不会持久化到后端-->
        <el-col :span="24">
          <el-form-item label="附件">
            <el-upload
              action=""
              :auto-upload="false"
              :file-list="attachments"
              :on-remove="onRemoveAttachment"
              :before-upload="beforeAttachUpload"
              list-type="text"
            >
              <el-button type="primary">添加附件</el-button>
            </el-upload>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <!-- ✅ 按钮靠右 -->
    <template #footer>
      <div class="flex justify-end gap-2">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm" :loading="formLoading">确 定</el-button>
      </div>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import GridSpatialDataApi, { GridSpatialDataVO } from '@/api/dataHub/gridManagement/gridDataManage/gridspatialdata'
import { mockGridTypes } from '@/api/dataHub/gridManagement/gridDataManage/gridspatialdata/mockGridSpatialData'

const { t } = useI18n()
const message = useMessage()

// 状态
const dialogVisible = ref(false)
const dialogTitle = ref('')
const formLoading = ref(false)
const formType = ref<'create' | 'update'>('create')
const gridTypes = ref<string[]>([])

// 表单数据
const formData = reactive<GridSpatialDataVO>({
  id: undefined,
  spatialId: undefined,
  gridId: undefined,
  gridType: undefined,
  boundaryCoords: undefined,
  topoRelation: undefined,
  coordSystem: '2000国家大地',
  dataFormat: undefined,
  remark: undefined,
  extCat1: undefined,
  extCat2: undefined,
  extCommon1: undefined,
  extCommon2: undefined,
})

// 表单规则
const formRules = reactive({
  gridId: [{ required: true, message: '关联网格ID不能为空', trigger: 'blur' }],
  gridType: [{ required: true, message: '网格类型不能为空', trigger: 'blur' }],
  boundaryCoords: [{ required: true, message: '边界坐标不能为空', trigger: 'blur' }],
  coordSystem: [{ required: true, message: '坐标系不能为空', trigger: 'blur' }],
  dataFormat: [{ required: true, message: '数据格式不能为空', trigger: 'blur' }],
})

const formRef = ref()
const attachments = ref<any[]>([])

// 初始化网格类型
onMounted(async () => {
  // try {
  //   const res = await GridSpatialDataApi.getGridTypes()
  //   gridTypes.value = Array.isArray(res) ? res : mockGridTypes
  // } catch {
    gridTypes.value = mockGridTypes
  // }
})

/** 打开弹窗 (create | update) */
const open = async (type: 'create' | 'update', id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = type === 'create' ? '新增网格空间数据' : '编辑网格空间数据'
  formType.value = type
  resetForm()
  if (id) {
    formLoading.value = true
    try {
      const res = await GridSpatialDataApi.getGridSpatialData(id)
      if (res && res.id) Object.assign(formData, res)
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open })

/** 表单提交 */
const emit = defineEmits(['success'])
const submitForm = async () => {
  await formRef.value.validate()
  formLoading.value = true
  try {
    const data = { ...formData } as GridSpatialDataVO
    if (formType.value === 'create') {
      await GridSpatialDataApi.createGridSpatialData(data)
      message.success(t('common.createSuccess'))
    } else {
      await GridSpatialDataApi.updateGridSpatialData(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    emit('success')
  } catch {
    message.error('保存失败：未知错误')
  } finally {
    formLoading.value = false
  }
}

/** 重置 */
const resetForm = () => {
  Object.assign(formData, {
    id: undefined,
    spatialId: undefined,
    gridId: undefined,
    gridType: undefined,
    boundaryCoords: undefined,
    topoRelation: undefined,
    coordSystem: '2000国家大地',
    dataFormat: undefined,
    remark: undefined,
    extCat1: undefined,
    extCat2: undefined,
    extCommon1: undefined,
    extCommon2: undefined,
  })
  attachments.value = []
  formRef.value?.resetFields?.()
}

/** 绘制占位 */
const openDraw = () => {
  message.info('打开绘制工具（当前为占位）')
}

/** 边界校验 */
const checkBoundary = async () => {
  const coords = formData.boundaryCoords
  if (!coords) {
    message.warning('请先输入/绘制边界坐标')
    return
  }
  try {
    const res: any = await GridSpatialDataApi.validateBoundary({
      boundaryCoords: coords,
      coordSystem: formData.coordSystem,
    })
    if (res?.ok) message.success('边界校验通过')
    else message.error(res?.message || '边界校验未通过')
  } catch {
    const closed = localCheckClosed(coords)
    message[closed ? 'success' : 'error'](
      closed ? '边界通过本地闭合校验' : '边界不闭合（本地校验）'
    )
  }
}

/** 计算拓扑关系 */
const calcTopo = async () => {
  try {
    const res: any = await GridSpatialDataApi.computeTopoRelation({
      boundaryCoords: formData.boundaryCoords,
      gridId: formData.gridId,
    })
    if (res?.topo) {
      formData.topoRelation = res.topo
      message.success('拓扑关系已生成')
    } else message.info('拓扑接口未返回拓扑，请手动编辑')
  } catch {
    message.info('后端拓扑接口不可用，请手动填写拓扑关系')
  }
}

/** 本地闭合校验 */
const localCheckClosed = (coordsStr: string) => {
  try {
    const points = coordsStr.split(';').map(s => s.trim()).filter(Boolean)
    if (points.length < 4) return false
    const first = points[0].split(',').map(Number)
    const last = points.at(-1)?.split(',').map(Number)
    if (!first || !last) return false
    const dx = Math.abs(first[0] - last[0])
    const dy = Math.abs(first[1] - last[1])
    return dx <= 1e-5 && dy <= 1e-5
  } catch {
    return false
  }
}

/** 附件 mock */
const beforeAttachUpload = (file: any) => {
  attachments.value.push(file)
  return false
}
const onRemoveAttachment = (file: any) => {
  const idx = attachments.value.indexOf(file)
  if (idx >= 0) attachments.value.splice(idx, 1)
}
</script>

<style scoped>
.text-muted {
  color: var(--el-text-color-placeholder);
}
.mt-8px {
  margin-top: 8px;
}
.mt-6px {
  margin-top: 6px;
}
</style>
