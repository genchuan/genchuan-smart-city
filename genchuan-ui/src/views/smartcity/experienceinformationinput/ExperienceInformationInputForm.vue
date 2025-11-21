<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="经验主题" prop="experienceTheme">
        <el-input v-model="formData.experienceTheme" placeholder="请输入经验主题" />
      </el-form-item>
      <el-form-item label="所属领域" prop="isArea">
        <el-select v-model="formData.isArea" placeholder="请选择所属领域">
          <el-option
            v-for="item in isAreaOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
        <!--<el-input v-model="formData.isArea" placeholder="请输入所属领域" />-->
      </el-form-item>
      <el-form-item label="经验提供方" prop="experienceProvider">
        <el-input v-model="formData.experienceProvider" placeholder="请输入经验提供方" />
      </el-form-item>
      <el-form-item label="实施时间" prop="implementationTime">
        <el-date-picker
          v-model="formData.implementationTime"
          type="date"
          value-format="x"
          placeholder="选择实施时间"
          class="common-Width100"
        />
      </el-form-item>
      <el-form-item label="实施地点" prop="implementationLocation">
        <el-input v-model="formData.implementationLocation" placeholder="请输入实施地点" />
      </el-form-item>
      <el-form-item label="详细步骤" prop="detailSteps">
        <el-input v-model="formData.detailSteps" placeholder="请输入详细步骤" />
      </el-form-item>
      <el-form-item label="取得成效" prop="achieveResults">
        <el-input v-model="formData.achieveResults" placeholder="请输入取得成效" />
      </el-form-item>
      <el-form-item label="可借鉴要点" prop="keyPointsForReference">
        <el-input v-model="formData.keyPointsForReference" placeholder="请输入可借鉴要点" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { ExperienceInformationInputApi, ExperienceInformationInputVO } from '@/api/smartcity/experienceinformationinput'
import { ClassificationOfExperienceInformationApi } from '@/api/smartcity/classificationofexperienceinformation'


/** 经验信息录入 表单 */
defineOptions({ name: 'ExperienceInformationInputForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  experienceTheme: undefined,
  isArea: undefined,
  experienceProvider: undefined,
  implementationTime: undefined,
  implementationLocation: undefined,
  detailSteps: undefined,
  achieveResults: undefined,
  keyPointsForReference: undefined
})
const formRules = reactive({
})
const formRef = ref() // 表单 ref


let isAreaOptions = ref([]);//法规分类

const initData = async () => {
  let queryParams={
    pageNo:1,
    pageSize:100
  }
  const data = await ClassificationOfExperienceInformationApi.getClassificationOfExperienceInformationPage(queryParams);
  isAreaOptions.value = data.list.map(item => ({
    label: item.sector,
    value:item.id
  }));
}

/** 初始化 **/
onMounted(() => {
  initData()
})



/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()
  // 修改时，设置数据
  if (id) {
    formLoading.value = true
    try {
      formData.value = await ExperienceInformationInputApi.getExperienceInformationInput(id)
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open }) // 提供 open 方法，用于打开弹窗

/** 提交表单 */
const emit = defineEmits(['success']) // 定义 success 事件，用于操作成功后的回调
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as unknown as ExperienceInformationInputVO
    if (formType.value === 'create') {
      await ExperienceInformationInputApi.createExperienceInformationInput(data)
      message.success(t('common.createSuccess'))
    } else {
      await ExperienceInformationInputApi.updateExperienceInformationInput(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    // 发送操作成功的事件
    emit('success')
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    experienceTheme: undefined,
    isArea: undefined,
    experienceProvider: undefined,
    implementationTime: undefined,
    implementationLocation: undefined,
    detailSteps: undefined,
    achieveResults: undefined,
    keyPointsForReference: undefined
  }
  formRef.value?.resetFields()
}
</script>
