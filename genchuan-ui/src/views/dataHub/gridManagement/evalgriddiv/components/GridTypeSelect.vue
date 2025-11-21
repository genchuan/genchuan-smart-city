<template>
  <el-select
    v-model="value"
    placeholder="请选择网格类型"
    clearable
  >
    <el-option v-for="type in types" :key="type.value" :label="type.label" :value="type.value" />
  </el-select>
</template>

<script lang="ts">
import { defineComponent, ref, watch } from 'vue'

export default defineComponent({
  name: 'GridTypeSelect',
  props: { modelValue: String },
  emits: ['update:modelValue'],
  setup(props, { emit }) {
    const value = ref(props.modelValue)
    const types = [
      { value: 'A', label: 'A类型' },
      { value: 'B', label: 'B类型' },
      { value: 'C', label: 'C类型' }
    ]

    watch(value, (v) => emit('update:modelValue', v))
    watch(() => props.modelValue, (v) => value.value = v)

    return { value, types }
  }
})
</script>
