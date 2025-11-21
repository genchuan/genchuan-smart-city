<template>
  <el-dialog
    v-model="dialogVisible"
    title="派单"
    width="400px"
    @close="handleClose"
  >
    <el-form :model="form" label-width="80px">
      <el-form-item label="派单人员">
        <el-input v-model="form.dispatcher" placeholder="请输入派单人员" />
      </el-form-item>
      <el-form-item label="派单说明">
        <el-input
          v-model="form.description"
          type="textarea"
          placeholder="请输入派单说明"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleDispatch">确定派单</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue';
import { ElMessage, ElDialog, ElForm, ElFormItem, ElInput, ElButton } from 'element-plus';
import { WarningAlertListTableApi,WarningAlertListTableVO } from '@/api/dataHub/warningAlarm/list';

const dialogVisible = ref(false);
const form = reactive({
  dispatcher: '',
  description: '',
});
const orderId = ref<number | null>(null);
const emit = defineEmits(['dispatch']);

const handleClose = () => {
  dialogVisible.value = false;
  form.dispatcher = '';
  form.description = '';
  orderId.value = null;
};

const handleDispatch = async () => {
  if (!form.dispatcher.trim()) {
    ElMessage.warning('请输入派单人员');
    return;
  }
  if (!orderId.value) {
    ElMessage.error('派单ID不存在');
    return;
  }

  try {
    //console.log("orderId.value",orderId.value)
    await WarningAlertListTableApi.dispatchWarningAlertListTable(orderId.value);
    ElMessage.success('派单成功');
    dialogVisible.value = false;
    emit('dispatch');
  } catch (error) {
    ElMessage.error('派单失败，请重试');
  }
};

const open = (alarmData:WarningAlertListTableVO) => {
  orderId.value = alarmData.id;
  //console.log("id",alarmData.id);
  dialogVisible.value = true;
};

// const open = () => {
//   orderId.value = 'event_010101';
//   dialogVisible.value = true;
// };
defineExpose({ open });
</script>

<style scoped>
.dialog-footer {
  text-align: right;
}
</style>
