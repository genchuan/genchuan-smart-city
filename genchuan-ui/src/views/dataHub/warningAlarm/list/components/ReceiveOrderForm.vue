<template>
  <el-dialog
    v-model="dialogVisible"
    title="接单"
    width="400px"
    @close="handleClose"
  >
    <el-form :model="form" label-width="80px">
      <el-form-item label="接单说明">
        <el-input
          v-model="form.description"
          type="textarea"
          placeholder="请输入接单说明"

        />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleReceive">确认接单</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue';
//import { dateFormatter } from '@/utils/formatTime';
import { ElMessage, ElDialog, ElForm, ElFormItem, ElInput, ElButton } from 'element-plus';
import { WarningAlertListTableApi } from '@/api/dataHub/warningAlarm/list';

const dialogVisible = ref(false);
const form = reactive({
  description: '',
});
const receiveId = ref<number | null>(null);
const emit = defineEmits(['receive']);

const handleClose = () => {
  dialogVisible.value = false;
  form.description = '';
  receiveId.value = null;
};

const handleReceive = async () => {
  if (!form.description.trim()) {
    ElMessage.warning('请输入接单说明');
    return;
  }
  if (!receiveId.value) {
    ElMessage.error('接单ID不存在');
    return;
  }

  try {
    await WarningAlertListTableApi.receiveWarningAlertListTable(receiveId.value, form);
    ElMessage.success('接单成功');
    dialogVisible.value = false;
    emit('receive');
  } catch (error) {
    ElMessage.error('接单失败，请重试');
  }
};

const open = (id: number) => {
  receiveId.value = id;
  dialogVisible.value = true;
};

defineExpose({ open });
</script>

<style scoped>
.dialog-footer {
  text-align: right;
}
</style>
