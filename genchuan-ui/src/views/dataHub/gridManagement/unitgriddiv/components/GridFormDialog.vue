<template>
  <el-dialog
    v-model="visible"
    width="90%"
    destroy-on-close
    class="grid-dialog"
    :fullscreen="isFullScreen"
    :before-close="handleClose"
  >
    <!-- ✅ 自定义 header -->
    <template #header>
      <div class="flex items-center justify-between w-full">
        <span class="text-lg font-bold">{{ dialogTitle }}</span>
        <el-button type="primary" link @click="toggleFullScreen">
          <el-icon>
            <i :class="isFullScreen ? 'el-icon-crop' : 'el-icon-full-screen'"></i>
          </el-icon>
          {{ isFullScreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <!-- ✅ 表单部分 -->
    <div class="dialog-body" :class="{ fullscreen: isFullScreen }">
      <el-form
        ref="formRef"
        :model="form"
        label-width="120px"
        :rules="rules"
        class="grid-form"
      >

        <el-form-item label="网格id" prop="unitGridId">
          <el-input v-model="form.unitGridId" placeholder="请输入网格id" />
        </el-form-item>

        <el-form-item label="网格名称" prop="unitGridName">
          <el-input v-model="form.unitGridName" placeholder="请输入网格名称" />
        </el-form-item>

        <!--        <el-form-item label="所属社区ID" prop="commId">-->
        <!--          <el-input v-model="form.commId" placeholder="请输入社区ID" />-->
        <!--        </el-form-item>-->

        <el-form-item label="所属社区" prop="commId">
          <el-tree-select
            v-model="form.commId"
            :data="commList"
            node-key="fullCode"
            :props="treeProps"
            placeholder="请选择所属社区"
            check-strictly
            filterable
            clearable
            class="!w-240px"
            @change="handleCommChange"
          />
        </el-form-item>


        <el-form-item label="比例尺" prop="scale">
          <el-input v-model="form.scale" placeholder="比例尺，如 1:2000" />
        </el-form-item>

        <el-form-item label="划分时间" prop="divTime">
          <el-date-picker
            v-model="form.divTime"
            type="datetime"
            placeholder="请选择划分时间"
            value-format="x"
          />
        </el-form-item>

        <el-form-item label="面积(m²)">
          <el-input-number v-model="form.area" :min="0" :disabled="true" />
        </el-form-item>

        <el-form-item label="备注">
          <el-input v-model="form.remark" placeholder="备注信息" />
        </el-form-item>

        <!-- ✅ 地图容器 -->
        <el-form-item label="边界绘制">
          <div class="map-wrapper">
            <div id="map-container" class="map-container"></div>
            <div id="toolControl">
              <div
                v-for="tool in tools"
                :key="tool.id"
                class="toolItem"
                :title="tool.title"
                :class="{ active: activeTool === tool.id }"
                @click="setActive(tool.id)"
                :style="{ backgroundImage: `url(${tool.icon})` }"
              ></div>
            </div>
          </div>
          <!-- ✅ 说明文字独立一行 -->
          <p class="map-hint">
            点击地图绘制边界顶点或调整图形（双击鼠标左键结束绘制）后保存。
          </p>
        </el-form-item>
      </el-form>
    </div>

    <!-- ✅ 底部按钮 -->
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="handleClose">取消</el-button>
        <el-button type="primary" :loading="loading" @click="handleSubmit">保存</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, reactive, computed, nextTick } from 'vue'
import { ElMessage } from 'element-plus'
import { UnitGridDivApi, UnitGridDivVO } from '@/api/dataHub/gridManagement/divideConfig/unitgriddiv'
import { AreaApi } from '@/api/dataHub/gridManagement/adminDivConfig'
import { loadTMap } from '@/utils/useTMap'

const emit = defineEmits(['success'])
const visible = ref(false)
const loading = ref(false)
const formRef = ref()
const isFullScreen = ref(false)
const mode = ref<'add' | 'edit'>('add')
const isBoundaryChanged = ref(false)

const dialogTitle = computed(() => (mode.value === 'add' ? '新增单元网格' : '编辑单元网格'))

//处理下拉 社区树数据
const commList = ref<any[]>([])
const treeProps = {
  label: 'name',
  children: 'children',
  value: 'fullCode'
}
/** 加载社区树数据 */
const loadCommList = async () => {
  try {
    const res = await AreaApi.getTakeEffect()
    // 若接口结构相同，可以直接取 communityList
    commList.value = res?.data?.communityList ?? res?.data?.townList ?? res?.communityList ?? res?.townList ?? []
  } catch (err) {
    console.error(err)
    ElMessage.error('社区数据加载失败')
  }
}

const handleCommChange = (val: string) => {
  form.commId = val
}


const form = reactive<UnitGridDivVO>({
  id: 0,
  unitGridId: '',
  unitGridName: '',
  commId: '',
  area: 0,
  scale: '',
  boundaryCoords: '',
  divTime: '',
  remark: ''
})

const rules = {
  unitGridId:[{required: true, message: '请输入网格id', trigger: 'blur'}],
  unitGridName: [{ required: true, message: '请输入网格名称', trigger: 'blur' }],
  commId: [{ required: true, message: '请输入社区ID', trigger: 'change' }],
  scale: [{ required: true, message: '请输入比例尺', trigger: 'blur' }],
  divTime: [{ required: true, message: '请选择划分时间', trigger: 'change' }]
}

// 腾讯地图相关变量
let map: any = null
let editor: any = null
let polygon: any = null
const activeTool = ref('interact')

const tools = [
  { id: 'draw', title: '绘制多边形', icon: 'https://mapapi.qq.com/web/lbs/javascriptGL/demo/img/polygon.png' },
  { id: 'delete', title: '删除图形', icon: 'https://mapapi.qq.com/web/lbs/javascriptGL/demo/img/delete.png' }
]

// ✅ 初始化地图
const initMap = async () => {
  const TMap = (window as any).TMap || (await loadTMap())
  if (!TMap) {
    ElMessage.error('地图加载失败，请检查网络或Key配置')
    return
  }

  map = new TMap.Map('map-container', {
    zoom: 16,
    center: new TMap.LatLng(39.9093, 116.3974)
  })

  polygon = new TMap.MultiPolygon({
    map,
    geometries: form.boundaryCoords
      ? [
        {
          id: 'edit-polygon',
          styleId: 'style_blue',
          paths: form.boundaryCoords.split(';').map((p: string) => {
            const [lng, lat] = p.split(',').map(Number)
            return new TMap.LatLng(lat, lng)
          })
        }
      ]
      : [],
    styles: {
      style_blue: new TMap.PolygonStyle({
        color: 'rgba(0,150,255,0.4)',
        showBorder: true,
        borderColor: '#0066cc',
        borderWidth: 2
      })
    }
  })

  if (!TMap.tools?.GeometryEditor) {
    console.error('TMap.tools.GeometryEditor 未加载，请确认引入工具库')
    return
  }

  editor = new TMap.tools.GeometryEditor({
    map,
    overlayList: [{ overlay: polygon, id: 'polygon' }],
    actionMode: TMap.tools.constants.EDITOR_ACTION.INTERACT,
    activeOverlayId: 'polygon',
    selectable: true,
    snappable: true
  })

  editor.on('draw_complete', (geometry: any) => {
    extractCoords(geometry)
    activeTool.value = 'interact'
    isBoundaryChanged.value = true
    editor.setActionMode(TMap.tools.constants.EDITOR_ACTION.INTERACT)
  })

  editor.on('adjust_complete', (geometry: any) => {
    extractCoords(geometry)
    isBoundaryChanged.value = true
  })
}

const extractCoords = (geometry: any) => {
  const geo = polygon.geometries.find((g: any) => g.id === geometry.id) || polygon.geometries[0]
  if (!geo?.paths) return
  const coords = geo.paths.map((p: any) => `${p.lng},${p.lat}`)
  form.boundaryCoords = coords.join(';')
}

const setActive = (id: string) => {
  if (!editor) return
  activeTool.value = id
  const TMap = (window as any).TMap
  if (id === 'draw') {
    editor.setActionMode(TMap.tools.constants.EDITOR_ACTION.DRAW)
    editor.setActiveOverlay('polygon')
  } else if (id === 'delete') {
    polygon.setGeometries([])
    form.boundaryCoords = ''
    isBoundaryChanged.value = true
  } else {
    editor.setActionMode(TMap.tools.constants.EDITOR_ACTION.INTERACT)
  }
}

const open = async (type: 'add' | 'edit', data?: UnitGridDivVO) => {
  mode.value = type
  isBoundaryChanged.value = false

  Object.assign(
    form,
    type === 'edit' && data
      ? {
        ...data,
        divTime: data.divTime ? data.divTime : ''
      }
      : {
        id: 0,
        unitGridId: '',
        unitGridName: '',
        commId: '',
        area: 0,
        scale: '',
        boundaryCoords: '',
        divTime: '',
        remark: ''
      }
  )

  visible.value = true
  await loadCommList()
  await nextTick()
  await initMap()
}

const handleSubmit = async () => {
  await formRef.value.validate()
  if (!form.boundaryCoords) {
    ElMessage.warning('请先绘制或编辑边界')
    return
  }

  loading.value = true
  try {
    // ✅ 仅在修改了边界时才校验
    if (isBoundaryChanged.value) {
      const res: any = await UnitGridDivApi.validateBoundary({
        boundaryCoords: form.boundaryCoords,
        commId: form.commId
      })
      if (!res.valid) {
        ElMessage.error(res.message || '边界不合法')
        return
      }
      form.area = res.calculatedArea
    }

    if (mode.value === 'add') {
      await UnitGridDivApi.createUnitGridDiv(form)
      ElMessage.success('新增成功')
    } else {
      await UnitGridDivApi.updateUnitGridDiv(form)
      ElMessage.success('修改成功')
    }

    emit('success')
    visible.value = false
  } catch (e: any) {
    ElMessage.error('保存失败：' + (e.message || ''))
  } finally {
    loading.value = false
  }
}

const toggleFullScreen = async () => {
  isFullScreen.value = !isFullScreen.value
  nextTick(() => {
    map?.resize?.()
  })
}

const handleClose = () => {
  isFullScreen.value = false
  visible.value = false
}

defineExpose({ open })
</script>

<style scoped>
.dialog-body {
  max-height: 70vh;
  overflow-y: auto;
  background-color: #fff;
  padding: 16px;
  border-radius: 8px;
}

.dialog-body.fullscreen {
  height: calc(100vh - 120px);
  overflow-y: auto;
  background: #fff !important;
}

.map-wrapper {
  position: relative;
  width: 100%;
  height: 65vh;
}

.map-container {
  width: 100%;
  height: 100%;
  border: 1px solid #dcdfe6;
  border-radius: 8px;
}

#toolControl {
  position: absolute;
  top: 10px;
  left: 50%;
  transform: translateX(-50%);
  z-index: 1001;
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}

.toolItem {
  width: 32px;
  height: 32px;
  margin: 4px;
  border-radius: 6px;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  background-color: #fff;
  border: 1px solid #e5e5e5;
  cursor: pointer;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);
}

.toolItem.active {
  border-color: #0066cc;
  background-color: #dce8ff;
}

.map-hint {
  margin-top: 8px;
  font-size: 13px;
  color: #666;
  line-height: 1.5;
}

.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
}
</style>
