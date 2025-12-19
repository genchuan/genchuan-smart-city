<template>
  <el-form-item label="应用场景" prop="sceneCatCodes">
    <AppSceneTree
      v-model="formData.sceneCatCodes"
      @change="handleSceneChange"
      :disabled="formLoading"
    />
  </el-form-item>
</template>

<script setup lang="ts">
import AppSceneTree from './AppSceneTree.vue'

const formData = reactive({
  sceneCatCodes: [] // 存储选中的sceneCatCode数组
})

// 编辑回显时，从接口数据中提取sceneCatCodes赋值即可
const openForm = async (type: string, id?: number) => {
  if (id) {
    const detail = await AppSceneCategoryApi.getAppSceneCategory(id)
    // 假设接口返回的选中场景代码存在 detail.sceneCatCodes 中
    formData.sceneCatCodes = detail.sceneCatCodes || []
  }
}
</script>
