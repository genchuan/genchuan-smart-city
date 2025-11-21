<template>
  <el-dialog
    :model-value="visible"
    :fullscreen="isFullscreen"
    width="700px"
    @close="emit('update:visible', false)"
  >
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full">
        <span class="font-bold text-lg">{{ id ? '编辑评价网格' : '新增评价网格' }}</span>
        <el-button type="text" @click="toggleFullscreen" class="text-gray-500 hover:text-blue-500">
          <el-icon><FullScreen /></el-icon>
          <span class="ml-1">{{ isFullscreen ? '退出全屏' : '全屏' }}</span>
        </el-button>
      </div>
    </template>

    <!--表单 -->
    <el-form :model="formData" label-width="120px">
      <el-form-item label="评价网格名称" prop="evalGridName">
        <el-input v-model="formData.evalGridName" placeholder="请输入网格名称" />
      </el-form-item>

      <el-form-item label="乡镇" prop="townStreetId">
        <TownSelect v-model="formData.townStreetId" />
      </el-form-item>

      <el-form-item label="网格类型" prop="gridType">
        <GridTypeSelect v-model="formData.gridType" />
      </el-form-item>

      <el-form-item label="管理网格">
        <MgSelectDialog
          v-model:selectedIds="selectedMgIds"
          :townStreetId="formData.townStreetId"
        />
      </el-form-item>

      <el-form-item label="备注">
        <el-input type="textarea" v-model="formData.remark" />
      </el-form-item>
    </el-form>

    <!-- 地图展示 -->
    <el-card class="mt-3">
      <template #header>
        <div class="font-bold">地图展示</div>
      </template>
      <EvalGridMap
        ref="evalGridMapRef"
        :includedMgIds="selectedMgIds"
      />
    </el-card>


    <template #footer>
      <span class="dialog-footer">
        <el-button @click="emit('update:visible', false)">取消</el-button>
        <el-button type="primary" @click="save">保存</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts">
import { defineComponent, reactive, ref, watch, nextTick } from 'vue'
import { EvalGridDivApi, EvalGridDivVO } from '@/api/dataHub/gridManagement/divideConfig/evalgriddiv'
import TownSelect from './components/TownSelect.vue'
import GridTypeSelect from './components/GridTypeSelect.vue'
import MgSelectDialog from './components/MgSelectDialog.vue'
import EvalGridMap from './EvalGridMap.vue'
import { ElMessage } from 'element-plus'
import { FullScreen } from '@element-plus/icons-vue'

export default defineComponent({
  name: 'EvalGridDivForm',
  components: { TownSelect, GridTypeSelect, MgSelectDialog, EvalGridMap, FullScreen },
  props: {
    visible: { type: Boolean, required: true },
    id: { type: Number, required: false }
  },
  emits: ['update:visible', 'refresh'],
  setup(props, { emit }) {
    const formData = reactive<Partial<EvalGridDivVO>>({})
    const selectedMgIds = ref<string[]>([]) // 始终数组
    const isFullscreen = ref(false)
    const evalGridMapRef = ref<any>(null)

    /** 加载详情并回显 */
    const loadDetail = async () => {
      if (!props.id) {
        Object.assign(formData, { evalGridName: '', townStreetId: '', gridType: '', remark: '' })
        selectedMgIds.value = []
        return
      }
      const resp = await EvalGridDivApi.getEvalGridDetail(props.id)
      // resp 可能包在 data 中或直接返回，根据你的 API 封装调整：
      const res = resp?.data ?? resp

      Object.assign(formData, res || {})
      // townStreetId 先赋值（触发 MgSelectDialog 加载列表）
      formData.townStreetId = res?.townStreetId

      // 统一把 includedMgIds / includedMgIdList 转成数组
      if (Array.isArray(res?.includedMgIdList) && res.includedMgIdList.length) {
        selectedMgIds.value = res.includedMgIdList.map(String)
      } else if (typeof res?.includedMgIds === 'string' && res.includedMgIds.trim()) {
        selectedMgIds.value = res.includedMgIds.split(',').map((s: string) => s.trim())
      } else if (Array.isArray(res?.includedMgIds)) {
        selectedMgIds.value = res.includedMgIds.map(String)
      } else {
        selectedMgIds.value = []
      }

      // 等 DOM/update 完成后，再强制让地图尝试加载（地图内部会根据 includedMgIds 调接口）
      await nextTick()
      evalGridMapRef.value?.loadEvalGrids?.()
    }

    /** 保存 */
    const save = async () => {
      if (!formData.townStreetId) return ElMessage.warning('请选择乡镇')
      if (!selectedMgIds.value.length) return ElMessage.warning('请选择管理网格')

      formData.includedMgIds = selectedMgIds.value.join(',')
      if (props.id) {
        await EvalGridDivApi.updateEvalGridDiv(formData as EvalGridDivVO)
        ElMessage.success('修改成功')
      } else {
        await EvalGridDivApi.createEvalGridDiv(formData as EvalGridDivVO)
        ElMessage.success('新增成功')
      }
      emit('update:visible', false)
      emit('refresh')
    }
    /** 保存带校验版  */
    // const save = async () => {
    //   if (!formData.townStreetId) return ElMessage.warning('请选择乡镇')
    //   if (!selectedMgIds.value.length) return ElMessage.warning('请选择管理网格')
    //
    //   try {
    //     // 1. 校验评价网格
    //     const resp = await EvalGridDivApi.validateEvalGrid({
    //         townStreetId:formData.townStreetId,
    //         mngGridIds:selectedMgIds.value
    //     }
    //     )
    //     console.log("===============resp")
    //     console.log(resp)
    //     const data = resp?.data?.data ?? resp?.data
    //     if (!data?.valid) {
    //       // 校验失败，提示错误信息
    //       return ElMessage.error(data?.message || '评价网格校验未通过')
    //     }
    //
    //     // 2. 校验通过，将面积写入表单字段
    //     formData.area = data.calculatedArea
    //
    //     // 3. 拼接 includedMgIds
    //     formData.includedMgIds = selectedMgIds.value.join(',')
    //
    //     // 4. 调用新增/修改接口
    //     if (props.id) {
    //       await EvalGridDivApi.updateEvalGridDiv(formData as EvalGridDivVO)
    //       ElMessage.success('修改成功')
    //     } else {
    //       await EvalGridDivApi.createEvalGridDiv(formData as EvalGridDivVO)
    //       ElMessage.success('新增成功')
    //     }
    //
    //     emit('update:visible', false)
    //     emit('refresh')
    //   } catch (err: any) {
    //     console.error(err)
    //     ElMessage.error(err?.message || '保存失败')
    //   }
    // }


    const toggleFullscreen = async () => {
      isFullscreen.value = !isFullscreen.value
      await nextTick()
      evalGridMapRef.value?.resizeMap?.()
    }

    // 加载详情（编辑时）
    watch(() => props.id, loadDetail, { immediate: true })

    // 当 townStreetId 与 selectedMgIds 都有值时，刷新地图（避免顺序问题）
    watch([selectedMgIds, () => formData.townStreetId], async ([mgIds, townId]) => {
      if (Array.isArray(mgIds) && mgIds.length && townId) {
        await nextTick()
        evalGridMapRef.value?.loadEvalGrids?.()
      } else {
        // 若 mgIds 清空或 town 切换，主动让地图显示空地图（触发 map 初始化）
        await nextTick()
        evalGridMapRef.value?.loadEvalGrids?.()
      }
    }, { deep: true })

    return {
      formData,
      selectedMgIds,
      save,
      emit,
      isFullscreen,
      toggleFullscreen,
      evalGridMapRef
    }
  }
})
</script>

<style scoped>
.dialog-footer {
  text-align: right;
}
</style>
