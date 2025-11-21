<template>
  <div class="field-editor">
    <el-button type="primary" size="mini" icon="el-icon-plus" @click="addField">添加字段</el-button>
    <el-table :data="fieldsModel" stripe style="width:100%; margin-top:8px;">
      <el-table-column prop="fieldName" label="字段名称" width="180">
        <template #default="{ row }">
          <el-input v-model="row.fieldName" :disabled="isEdit && row.existing" placeholder="如：部件名称" />
        </template>
      </el-table-column>

      <el-table-column prop="fieldCode" label="字段代码" width="180">
        <template #default="{ row }">
          <el-input
            v-model="row.fieldCode"
            :disabled="isEdit && row.existing"
            placeholder="snake_case 例如 component_name"
            @blur="validateSnakeCase(row)"
          />
        </template>
      </el-table-column>

      <el-table-column prop="fieldType" label="字段类型" width="140">
        <template #default="{ row }">
          <el-select v-model="row.fieldType" placeholder="类型" :disabled="isEdit && row.existing">
            <el-option label="char" value="char" />
            <el-option label="varchar" value="varchar" />
            <el-option label="int" value="int" />
            <el-option label="decimal" value="decimal" />
            <el-option label="datetime" value="datetime" />
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="fieldLength" label="字段长度/精度" width="160">
        <template #default="{ row }">
          <el-input v-model="row.fieldLength" :disabled="isEdit && row.existing" placeholder="如 50 或 15,2" />
        </template>
      </el-table-column>

      <el-table-column prop="constraintType" label="约束条件" width="120">
        <template #default="{ row }">
          <el-select v-model="row.constraintType" placeholder="约束">
            <el-option label="必选" value="required" />
            <el-option label="可选" value="optional" />
          </el-select>
        </template>
      </el-table-column>

      <el-table-column prop="valueRange" label="值域范围" width="200">
        <template #default="{ row }">
          <el-input v-model="row.valueRange" placeholder="如 1（启用）/0（禁用） 或 000001-999999" />
        </template>
      </el-table-column>

      <el-table-column label="操作" width="140" fixed="right">
        <template #default="{ $index }">
          <el-button type="danger" size="mini" @click="removeField($index)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import { watch, reactive } from 'vue';

const props = defineProps({
  modelValue: {
    type: Array,
    default: () => []
  },
  existingFieldsMap: {
    type: Object,
    default: () => ({})
  },
  isEdit: {
    type: Boolean,
    default: false
  }
});

const emit = defineEmits(['update:fields']);
const fieldsModel = reactive([]);

// 初始化
function normalizeField(f) {
  return {
    fieldName: f.fieldName || '',
    fieldCode: f.fieldCode || '',
    fieldType: f.fieldType || 'varchar',
    fieldLength: f.fieldLength || '',
    constraintType: f.constraintType || 'optional',
    valueRange: f.valueRange || '',
    existing: !!(f.fieldCode && props.existingFieldsMap && props.existingFieldsMap[f.fieldCode])
  };
}

function initFromProps() {
  fieldsModel.splice(0, fieldsModel.length);
  for (const f of props.modelValue || []) {
    fieldsModel.push(normalizeField(f));
  }
  if (fieldsModel.length === 0 && !props.isEdit) {
    fieldsModel.push(normalizeField({}));
  }
}

initFromProps();

watch(() => props.modelValue, initFromProps, { deep: true });

watch(
  fieldsModel,
  () => {
    emit(
      'update:fields',
      fieldsModel.map(f => ({
        fieldName: f.fieldName,
        fieldCode: f.fieldCode,
        fieldType: f.fieldType,
        fieldLength: f.fieldLength,
        constraintType: f.constraintType,
        valueRange: f.valueRange
      }))
    );
  },
  { deep: true }
);

function addField() {
  fieldsModel.push(normalizeField({}));
}

function removeField(index) {
  fieldsModel.splice(index, 1);
}

function validateSnakeCase(row) {
  if (!row.fieldCode) return;
  const snakeRegex = /^[a-z0-9]+(?:_[a-z0-9]+)*$/;
  if (!snakeRegex.test(row.fieldCode)) {
    window.$message?.warning('字段代码建议使用蛇形命名法（小写字母、数字及下划线，不能以上划线开始或结束）');
  }
}
</script>

<style scoped>
.field-editor {
  margin-top: 8px;
}
</style>
