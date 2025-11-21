<template>
  <el-dialog :title="dialogTitle" :visible="visible" width="820px" @close="onClose">
    <el-form ref="formRef" :model="formModel" :rules="rules" label-width="140px">
      <el-form-item label="部件小类" prop="componentCategoryId" required>
        <el-select v-model="formModel.componentCategoryId" placeholder="请选择部件小类" :disabled="isEdit" filterable>
          <el-option
            v-for="cat in categoryOptions"
            :key="cat.id"
            :label="cat.name"
            :value="cat.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="数据类型" prop="dataType" required>
        <el-select v-model="formModel.dataType" placeholder="请选择" :disabled="isEdit">
          <el-option label="空间数据" value="spatial" />
          <el-option label="属性数据" value="attribute" />
        </el-select>
      </el-form-item>

      <div v-if="formModel.dataType === 'spatial'">
        <el-alert title="空间数据：坐标系与高程基准为固定值（国家标准），仅选择平面定位精度级别。" type="info" show-icon />
        <el-form-item label="坐标系类型" prop="coordSystem">
          <el-input v-model="formModel.coordSystem" disabled />
        </el-form-item>

        <el-form-item label="平面定位精度级别" prop="accuracyLevel" required>
          <el-select v-model="formModel.accuracyLevel" placeholder="选择精度等级">
            <el-option label="A类（中误差≤±0.5m）" value="A" />
            <el-option label="B类（中误差≤±1.0m）" value="B" />
          </el-select>
        </el-form-item>

        <el-form-item label="高程基准" prop="elevationDatum">
          <el-input v-model="formModel.elevationDatum" disabled />
        </el-form-item>
      </div>

      <div v-if="formModel.dataType === 'attribute'">
        <el-alert title="属性数据：请逐条填写字段要求（字段代码采用蛇形命名），创建后不可修改字段名称/代码/类型/长度，仅可修改约束和值域。" type="info" show-icon />
        <attribute-field-editor
          v-model:fields="formModel.fields"
          :existing-fields-map="existingFieldsMap"
          :is-edit="isEdit"
        />
      </div>

      <el-form-item label="说明/摘要" prop="summary">
        <el-input type="textarea" v-model="formModel.summary" placeholder="可以填写该配置的简要说明（可选）" />
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button @click="onClose">取消</el-button>
      <el-button type="primary" @click="onSave">保存</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive, watch } from 'vue';
import AttributeFieldEditor from './AttributeFieldEditor.vue';
import { createRequirement, updateRequirement } from '@/api/dataHub/monitorCompEventMgr/monitorCompConfigMgr/monitorCompDataReqConfig';;
import { ElMessage } from 'element-plus';

const props = defineProps({
  categoryOptions: {
    type: Array,
    default: () => []
  }
});

const emit = defineEmits(['saved']);

const visible = ref(false);
const mode = ref('create'); // create | edit
const isEdit = ref(false);
const dialogTitle = ref('新增配置');

const defaultForm = () => ({
  dataReqId: '',
  componentCategoryId: '',
  dataType: '',
  // spatial fields
  coordSystem: '2000国家大地坐标系',
  accuracyLevel: '',
  elevationDatum: '1985国家高程基准',
  // attribute fields
  fields: [],
  summary: ''
});

const formModel = reactive(defaultForm());
const formRef = ref(null);

const rules = {
  componentCategoryId: [{ required: true, message: '请选择部件小类', trigger: 'change' }],
  dataType: [{ required: true, message: '请选择数据类型', trigger: 'change' }],
  accuracyLevel: [
    { validator: (rule, value, cb) => {
        if (formModel.dataType === 'spatial' && !value) return cb(new Error('请选择平面定位精度级别'));
        cb();
      }, trigger: 'change' }
  ],
  // fields 校验由子组件负责
};

const existingFieldsMap = ref({}); // 用于校验字段代码在同一分类下唯一性（edit 时允许保留当前记录的字段代码）

function openDialog({ mode: m = 'create', record = null }) {
  mode.value = m;
  isEdit.value = m === 'edit';
  dialogTitle.value = isEdit.value ? '编辑配置' : '新增配置';
  resetForm();

  if (isEdit.value && record) {
    loadRecord(record.dataReqId);
  } else {
    visible.value = true;
  }
}

// 编辑时仅允许编辑约束条件 & 值域范围：因此要把其他字段设为只读或禁用（在子组件中判断 isEdit）
async function loadRecord(dataReqId) {
  // TODO: 调用后端获取单条记录
  const record = await fetchRequirementById(dataReqId);

  // 赋值（保持表单字段结构）
  formModel.dataReqId = record.dataReqId;
  formModel.componentCategoryId = record.componentCategoryId;
  formModel.dataType = record.dataType;
  formModel.coordSystem = record.coordSystem || '2000国家大地坐标系';
  formModel.accuracyLevel = record.accuracyLevel || '';
  formModel.elevationDatum = record.elevationDatum || '1985国家高程基准';
  formModel.fields = (record.fields || []).map(f => ({ ...f })); // 深拷贝
  formModel.summary = record.summary || '';

  // 生成 existingFieldsMap：用于子组件在校验唯一性时允许当前记录字段代码
  existingFieldsMap.value = {};
  if (record.fields) {
    for (const f of record.fields) {
      existingFieldsMap.value[f.fieldCode] = true;
    }
  }

  visible.value = true;
}

function resetForm() {
  Object.assign(formModel, defaultForm());
  existingFieldsMap.value = {};
}

async function onSave() {
  // 表单基础校验
  await formRef.value.validate().catch(() => { throw new Error('表单校验不通过'); });

  // 子组件字段校验（通过触发事件或校验函数，这里假设子组件在 v-model:fields 时保证字段完整性）
  if (formModel.dataType === 'attribute') {
    if (!formModel.fields || formModel.fields.length === 0) {
      ElMessage.error('请至少添加一条属性字段要求');
      return;
    }
    // 进一步校验：字段代码唯一性（同一分类）
    const duplicates = findDuplicateFieldCodes(formModel.fields);
    if (duplicates.length > 0) {
      ElMessage.error(`存在重复的字段代码：${duplicates.join(', ')}`);
      return;
    }
    // TODO: 还可以校验字段类型/长度格式（例如 decimal 需要 format '15,2'）
  } else if (formModel.dataType === 'spatial') {
    if (!formModel.accuracyLevel) {
      ElMessage.error('请选择平面定位精度级别');
      return;
    }
  }

  // 提交：create or update
  const payload = {
    ...formModel,
    createTime: new Date().toISOString()
  };

  if (mode.value === 'create') {
    // TODO: 调用后端 createRequirement 接口
    const ok = await createRequirement(payload);
    if (ok) {
      ElMessage.success('新增成功');
      visible.value = false;
      emit('saved');
    } else {
      ElMessage.error('新增失败（模拟）');
    }
  } else {
    // TODO: 调用后端 updateRequirement（编辑时只允许修改约束条件和值域）
    const ok = await updateRequirement(payload);
    if (ok) {
      ElMessage.success('更新成功');
      visible.value = false;
      emit('saved');
    } else {
      ElMessage.error('更新失败（模拟）');
    }
  }
}

function onClose() {
  visible.value = false;
}

function findDuplicateFieldCodes(fields) {
  const seen = {};
  const dup = [];
  for (const f of fields) {
    const code = f.fieldCode;
    if (!code) continue;
    if (seen[code]) {
      if (!dup.includes(code)) dup.push(code);
    } else {
      seen[code] = 1;
    }
  }
  return dup;
}

defineExpose({ openDialog });

</script>

<style scoped>
.el-dialog__body {
  max-height: 60vh;
  overflow: auto;
}
</style>
