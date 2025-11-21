<template>
  <el-dialog
    v-model="dialogVisible"
    title="审核预警"
    width="400px"
    @close="handleClose"
  >
    <el-form :model="form" label-width="80px">
      <el-form-item label="审核结果">
        <el-radio-group v-model="form.result">
          <el-radio :label="true">通过</el-radio>
          <el-radio :label="false">不通过</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="审核意见" v-if="form.result === false">
        <el-input
          v-model="form.opinion"
          type="textarea"
          placeholder="请输入不通过的原因"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleReview">提交审核</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue';
import { ElMessage, ElDialog, ElForm, ElFormItem, ElRadioGroup, ElRadio, ElInput, ElButton } from 'element-plus';
import { WarningAlertListTableApi } from '@/api/dataHub/warningAlarm/list';

const dialogVisible = ref(false);
const form = reactive({
  result: true,
  opinion: '',
});
const reviewId = ref<number | null>(null);
const emit = defineEmits(['review']);

const handleClose = () => {
  dialogVisible.value = false;
  form.result = true;
  form.opinion = '';
  reviewId.value = null;
};

const handleReview = async () => {
  if (!reviewId.value) {
    ElMessage.error('审核ID不存在');
    return;
  }

  try {
    await WarningAlertListTableApi.reviewWarningAlertListTable(reviewId.value, form);
    ElMessage.success('审核成功');
    dialogVisible.value = false;
    emit('review');
  } catch (error) {
    ElMessage.error('审核失败，请重试');
  }
};

const open = (id: number) => {
  reviewId.value = id;
  dialogVisible.value = true;
};

defineExpose({ open });
</script>

<style scoped>
.dialog-footer {
  text-align: right;
}
</style>
