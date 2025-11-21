<template>
  <el-dialog v-model="visible" title="导入坐标创建单元网格" width="600px" :destroy-on-close="true">
    <el-form ref="formRef" :model="form" label-width="120px" :rules="rules">
      <el-form-item label="网格名称" prop="unitGridName">
        <el-input v-model="form.unitGridName" placeholder="请输入网格名称" />
      </el-form-item>

      <el-form-item label="社区ID" prop="commId">
        <el-input v-model="form.commId" placeholder="请输入社区ID" />
      </el-form-item>

      <el-form-item label="比例尺" prop="scale">
        <el-input v-model="form.scale" placeholder="比例尺，如 1:2000" />
      </el-form-item>

      <el-form-item label="备注">
        <el-input v-model="form.remark" placeholder="可选备注信息" />
      </el-form-item>

      <el-form-item label="边界坐标" prop="boundaryCoords">
        <el-input
          type="textarea"
          v-model="form.boundaryCoords"
          placeholder="请输入或粘贴坐标，每个点用分号分隔，例如：116.3974,39.9093;116.3975,39.9094"
        />
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" :loading="loading" @click="handleSubmit">导入</el-button>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { UnitGridDivApi } from '@/api/dataHub/gridManagement/divideConfig/unitgriddiv'
const emit = defineEmits(['success'])
interface ImportOptions {
  commId?: string
}

const visible = ref(false)
const loading = ref(false)
const formRef = ref()

const form = reactive({
  unitGridName: '',
  commId: '',
  scale: '',
  remark: '',
  boundaryCoords: ''
})

const rules = {
  unitGridName: [{ required: true, message: '请输入网格名称', trigger: 'blur' }],
  commId: [{ required: true, message: '请输入社区ID', trigger: 'blur' }],
  scale: [{ required: true, message: '请输入比例尺', trigger: 'blur' }],
  boundaryCoords: [{ required: true, message: '请填写边界坐标', trigger: 'blur' }]
}

/** 打开弹窗，支持传入默认社区ID */
const open = (options?: ImportOptions) => {
  visible.value = true
  Object.assign(form, {
    unitGridName: '',
    commId: options?.commId || '',
    scale: '',
    remark: '',
    boundaryCoords: ''
  })
}

/** 提交导入 */
const handleSubmit = async () => {
  try {
    await formRef.value.validate()
    loading.value = true

    // 调用导入接口
    const res = await UnitGridDivApi.importCoordinates({
      unitGridName: form.unitGridName,
      commId: form.commId,
      scale: form.scale,
      boundaryCoords: form.boundaryCoords,
      remark: form.remark
    })
    console.log("res=====================")
    console.log(res)
    if (res!==null) {
      ElMessage.success('导入成功')
      emit('success')
      visible.value = false
    }
    // } else {//此判断分支未进入 如果校验失败，便直接进入catch，不返回res
    //   console.log("============")
    //   console.log(res.msg)
    //   ElMessage.error(res.msg || '导入失败')
    // }
  } catch (e: any) {
    console.log("e=======================")
    console.log(e)
    ElMessage.error('操作失败：' + (e.message || ''))
  } finally {
    loading.value = false
  }
}

defineExpose({ open })
</script>

<style scoped>
.el-input textarea {
  font-family: monospace;
}
</style>
