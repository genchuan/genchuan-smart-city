<template>
  <el-dialog
    v-model="dialogVisible"
    title="提交审核"
    width="400px"
    @close="handleClose"
  >
    <el-form :model="form" label-width="80px">
      <el-form-item label="审核说明">
        <el-input
          v-model="form.description"
          type="textarea"
          placeholder="请输入审核说明"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">提交审核</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue';
import { ElMessage, ElDialog, ElForm, ElFormItem, ElInput, ElButton } from 'element-plus';
import { WarningAlertListTableApi } from '@/api/dataHub/warningAlarm/list';

const dialogVisible = ref(false);
const form = reactive({
  description: '',
});
const submitId = ref<number | null>(null);
const emit = defineEmits(['submit']);

const handleClose = () => {
  dialogVisible.value = false;
  form.description = '';
  submitId.value = null;
};

const handleSubmit = async () => {
  if (!form.description.trim()) {
    ElMessage.warning('请输入审核说明');
    return;
  }
  if (!submitId.value) {
    ElMessage.error('提交ID不存在');
    return;
  }

  try {
    await WarningAlertListTableApi.submitReviewWarningAlertListTable(submitId.value, form);
    ElMessage.success('提交审核成功');
    dialogVisible.value = false;
    emit('submit');
  } catch (error) {
    ElMessage.error('提交审核失败，请重试');
  }
};

const open = (id: number) => {
  submitId.value = id;
  dialogVisible.value = true;
};

defineExpose({ open });
</script>

<style scoped>
.dialog-footer {
  text-align: right;
}
</style>
