<template>
  <div>
    <el-table :data="fields" border>
      <el-table-column prop="fieldName" label="字段名称">
        <template #default="{ row }">
          <el-input v-model="row.fieldName" :disabled="disabled" />
        </template>
      </el-table-column>
      <el-table-column prop="fieldCode" label="字段代码">
        <template #default="{ row }">
          <el-input v-model="row.fieldCode" :disabled="disabled" />
        </template>
      </el-table-column>
      <el-table-column prop="fieldType" label="类型">
        <template #default="{ row }">
          <el-select v-model="row.fieldType" :disabled="disabled">
            <el-option label="char" value="char" />
            <el-option label="varchar" value="varchar" />
            <el-option label="int" value="int" />
            <el-option label="decimal" value="decimal" />
            <el-option label="datetime" value="datetime" />
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="fieldLength" label="长度">
        <template #default="{ row }">
          <el-input v-model="row.fieldLength" :disabled="disabled" />
        </template>
      </el-table-column>
      <el-table-column prop="constraintType" label="约束条件">
        <template #default="{ row }">
          <el-select v-model="row.constraintType">
            <el-option label="必选" value="required" />
            <el-option label="可选" value="optional" />
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="valueRange" label="值域范围">
        <template #default="{ row }">
          <el-input v-model="row.valueRange" />
        </template>
      </el-table-column>
      <el-table-column label="操作" width="100">
        <template #default="{ $index }">
          <el-button
            v-if="!disabled"
            type="danger"
            size="small"
            @click="removeField($index)"
          >
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-button
      v-if="!disabled"
      class="mt-2"
      type="primary"
      plain
      @click="addField"
    >
      新增字段
    </el-button>
  </div>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue';

const props = defineProps<{
  modelValue: any[];
  disabled?: boolean;
}>();
const emit = defineEmits(['update:modelValue']);

const fields = ref<any[]>(props.modelValue || []);

watch(
  () => props.modelValue,
  val => (fields.value = val || [])
);

function addField() {
  fields.value.push({
    fieldName: '',
    fieldCode: '',
    fieldType: 'varchar',
    fieldLength: '',
    constraintType: 'required',
    valueRange: ''
  });
  emit('update:modelValue', fields.value);
}

function removeField(index: number) {
  fields.value.splice(index, 1);
  emit('update:modelValue', fields.value);
}

watch(fields, val => emit('update:modelValue', val), { deep: true });
</script>
