<template>
  <el-dialog
    :title="editData ? '编辑配置' : '新增配置'"
    :model-value="visible"
    width="700px"
    @close="$emit('close')"
  >
    <el-form :model="form" label-width="120px">
      <el-form-item label="部件分类">
        <el-select v-model="form.componentCategoryId" placeholder="请选择">
          <el-option
            v-for="c in categoryList"
            :key="c.id"
            :label="c.name"
            :value="c.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="数据类型">
        <el-select v-model="form.dataType" placeholder="请选择类型">
          <el-option label="空间数据" value="spatial" />
          <el-option label="属性数据" value="attribute" />
        </el-select>
      </el-form-item>

      <!-- 空间数据表单 -->
      <template v-if="form.dataType === 'spatial'">
        <el-form-item label="坐标系类型">
          <el-input v-model="form.coordSystem" disabled />
        </el-form-item>
        <el-form-item label="平面定位精度级别">
          <el-select v-model="form.accuracyLevel">
            <el-option label="A类（≤±0.5m）" value="A" />
            <el-option label="B类（≤±1.0m）" value="B" />
          </el-select>
        </el-form-item>
        <el-form-item label="高程基准">
          <el-input v-model="form.elevationDatum" disabled />
        </el-form-item>
      </template>

      <!-- 属性数据表单 -->
      <AttributeFieldForm
        v-else
        v-model="form.fields"
        :disabled="!!editData"
      />

      <el-form-item label="说明">
        <el-input v-model="form.summary" />
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button @click="$emit('close')">取消</el-button>
      <el-button type="primary" @click="handleSave">保存</el-button>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, onMounted, watch } from 'vue';
import {
  fetchCategoryList,
  createRequirement,
  updateRequirement
} from '@/api/dataHub/monitorCompEventMgr/monitorCompConfigMgr/monitorCompDataReqConfig';
import AttributeFieldForm from './AttributeFieldForm.vue';

const props = defineProps<{
  visible: boolean;
  editData?: any;
}>();
const emit = defineEmits(['close']);

const categoryList = ref<any[]>([]);
const form = ref<any>({
  componentCategoryId: '',
  dataType: 'spatial',
  coordSystem: '2000国家大地坐标系',
  accuracyLevel: 'A',
  elevationDatum: '1985国家高程基准',
  fields: [],
  summary: ''
});

onMounted(async () => {
  categoryList.value = await fetchCategoryList();
  if (props.editData) form.value = { ...props.editData };
});

watch(
  () => props.visible,
  val => {
    if (!val) return;
    if (!props.editData) {
      form.value = {
        componentCategoryId: '',
        dataType: 'spatial',
        coordSystem: '2000国家大地坐标系',
        accuracyLevel: 'A',
        elevationDatum: '1985国家高程基准',
        fields: [],
        summary: ''
      };
    }
  }
);

async function handleSave() {
  if (props.editData) await updateRequirement(form.value);
  else await createRequirement(form.value);
  emit('close');
}
</script>
