<template>
  <el-select
    v-model="internalSelectedIds"
    multiple
    filterable
    clearable
    placeholder="请选择管理网格"
    style="width: 100%"
    :disabled="!townStreetId"
    @change="handleChange"
  >
    <el-option
      v-for="item in options"
      :key="item.id"
      :label="item.mngGridName"
      :value="item.id"
    />
  </el-select>
</template>

<script lang="ts">
import { defineComponent, ref, watch } from 'vue'
import { MngGridDivApi } from '@/api/dataHub/gridManagement/divideConfig/mnggriddiv'
import { ElMessage } from 'element-plus'

export default defineComponent({
  name: 'MgSelectDialog',
  props: {
    selectedIds: { type: Array as () => string[], required: true },
    townStreetId: { type: [String, Number], required: false }
  },
  emits: ['update:selectedIds'],
  setup(props, { emit }) {
    const options = ref<any[]>([])
    const internalSelectedIds = ref<string[]>([])

    // 把父传入的 selectedIds 同步到内部 v-model
    watch(
      () => props.selectedIds,
      (v) => {
        internalSelectedIds.value = Array.isArray(v) ? v.map(String) : []
      },
      { immediate: true }
    )

    // 当乡镇改变时加载管理网格列表
    watch(
      () => props.townStreetId,
      async (newTown) => {
        if (!newTown) {
          options.value = []
          internalSelectedIds.value = []
          return
        }
        try {
          const res = await MngGridDivApi.listByTown(String(newTown))
          // 把返回的 mngGridId 作为 option.value —— 与 selectedIds 保持一致
          options.value = (res || []).map((item: any) => ({
            id: String(item.mngGridId),
            mngGridName: item.mngGridName
          }))
          // 若父已传 selectedIds（回显），保持选中（option 加载后，el-select 会显示对应 label）
        } catch (err) {
          ElMessage.error('管理网格列表加载失败')
        }
      },
      { immediate: true }
    )

    const handleChange = (val: string[]) => {
      emit('update:selectedIds', val)
    }

    return { options, internalSelectedIds, handleChange }
  }
})
</script>
