<template>
  <el-dialog
    v-model="dialogVisible"
    title="删除原因"
    width="300px"
    @close="handleClose"
  >
    <el-form :model="form" label-width="80px">
      <el-form-item label="删除原因">
        <el-input
          v-model="form.reason"
          type="textarea"
          placeholder="请输入删除原因"

        />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleConfirm">确定</el-button>
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
  reason: '',
});
const deleteId = ref<number | null>(null);
const emit = defineEmits(['confirm']);

const handleClose = () => {
  dialogVisible.value = false;
  form.reason = '';
  deleteId.value = null;
};

const handleConfirm = async () => {
  if (!form.reason.trim()) {
    ElMessage.warning('请输入删除原因');
    return;
  }
  if (!deleteId.value) {
    ElMessage.error('删除ID不存在');
    return;
  }

  try {
    await WarningAlertListTableApi.deleteWarningAlertListTable(deleteId.value, form.reason);
    ElMessage.success('删除成功');
    dialogVisible.value = false;
    emit('confirm');
  } catch (error) {
    ElMessage.error('删除失败，请重试');
  }
};

const open = (id: number) => {
  deleteId.value = id;
  dialogVisible.value = true;
};

defineExpose({ open });
</script>

<style scoped>
.dialog-footer {
  text-align: right;
}
</style>
