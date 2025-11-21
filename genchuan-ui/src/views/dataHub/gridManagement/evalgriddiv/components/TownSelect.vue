<template>
  <el-cascader
    v-model="value"
    :options="options"
    :props="cascaderProps"
    placeholder="请选择乡镇/社区"
    clearable
    filterable
    :emit-path="false"
    class="!w-240px"
  />
</template>

<script lang="ts">
import { defineComponent, ref, watch, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { AreaApi } from '@/api/dataHub/gridManagement/adminDivConfig'

export default defineComponent({
  name: 'TownSelect',
  props: {
    modelValue: { type: [String, Number], default: '' }
  },
  emits: ['update:modelValue'],
  setup(props, { emit }) {
    const value = ref(props.modelValue) // 绑定 fullCode
    const options = ref<any[]>([])

    // 单选配置（确保只选中一个节点）
    const cascaderProps = {
      value: 'fullCode',   // 绑定值字段
      label: 'name',       // 显示字段
      children: 'children',// 子节点字段
      multiple: false,     // 禁用多选
      checkStrictly: true, // 允许选择任意层级，但一次只能选一个
      emitPath: false      // 只返回一个 fullCode 值
    }

    // 加载树数据
    const loadOptions = async () => {
      try {
        const res = await AreaApi.getTakeEffect()
        // 支持多种返回结构
        const list = res?.data?.townList ?? res?.townList ?? []
        options.value = Array.isArray(list) ? list : []
      } catch (err) {
        console.error('加载乡镇树失败', err)
        ElMessage.error('乡镇数据加载失败')
      }
    }

    // 内部值变更时同步给父组件
    watch(value, (v) => emit('update:modelValue', v))

    // 父组件回显时同步更新内部值
    watch(
      () => props.modelValue,
      (v) => { value.value = v ?? '' }
    )

    onMounted(loadOptions)

    return { value, options, cascaderProps }
  }
})
</script>

<style scoped>
/* 控件宽度可根据页面风格调整 */
</style>
