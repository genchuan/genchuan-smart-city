<template>
  <!-- 导入弹窗 -->
  <Dialog :title="upload.title" v-model="upload.open" width="400px" draggable>
    <el-upload
      ref="uploadRef"
      :limit="1"
      accept=".xlsx, .xls"
      :disabled="upload.isUploading"
      :on-progress="handleFileUploadProgress"
      :on-success="handleFileSuccess"
      :on-change="handleFileChange"
      :on-remove="handleFileRemove"
      :auto-upload="false"
      drag
    >
      <Icon icon="ep:upload-filled" class="text-40px text-primary" />
      <div class="mt-10px text-14px text-gray-500">
        将文件拖到此处，或<em class="text-primary">点击上传</em>
      </div>
      <template #tip>
        <div class="mt-10px text-center">
          <span class="text-12px text-gray-500">仅允许导入xls、xlsx格式文件。</span>
          <el-link type="primary" :underline="false" style="font-size:12px" @click="downloadTemplate">
            下载模板
          </el-link>
        </div>
      </template>
    </el-upload>
    
    <!-- 显示已选择的文件 -->
    <div v-if="selectedFile" class="mt-15px p-10px bg-gray-50 rounded">
      <div class="flex items-center justify-between">
        <span class="text-14px text-gray-600">已选择文件：{{ selectedFile.name }}</span>
        <el-button type="danger" link @click="handleFileRemove">移除</el-button>
      </div>
    </div>
    
    <!-- 更新支持选项 -->
    <el-checkbox v-model="upload.isUpdateSupport" class="mt-15px">
      是否更新已经存在的数据
    </el-checkbox>
    
    <template #footer>
      <el-button type="primary" @click="submitFileForm" :disabled="upload.isUploading || !selectedFile">
        {{ upload.isUploading ? '导入中...' : '确 定' }}
      </el-button>
      <el-button @click="upload.open = false">取 消</el-button>
    </template>
  </Dialog>

  <!-- 导入结果弹窗 -->
  <Dialog title="导入结果" v-model="uploadResult.open" width="600px">
    <el-descriptions :column="2" border>
      <el-descriptions-item label="新增条数">
        <el-tag type="success">{{ uploadResult.createCount }}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item label="更新条数">
        <el-tag type="warning">{{ uploadResult.updateCount }}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item label="成功条数">
        <el-tag type="success">{{ uploadResult.successCount }}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item label="失败条数">
        <el-tag type="danger">{{ uploadResult.failureCount }}</el-tag>
      </el-descriptions-item>
    </el-descriptions>

    <div v-if="uploadResult.failureCount > 0" class="mt-20px">
      <h4 class="mb-10px text-red-500">失败详情：</h4>
      <el-table :data="failureList" height="200" border>
        <el-table-column prop="assetCatCode" label="资产分类编码" min-width="120" />
        <el-table-column prop="reason" label="失败原因" min-width="200" show-overflow-tooltip />
      </el-table>
    </div>

    <template #footer>
      <el-button @click="uploadResult.open = false">关 闭</el-button>
      <el-button v-if="uploadResult.failureCount > 0" type="primary" @click="downloadFailureData">
        下载失败数据
      </el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import { ref, reactive, computed, nextTick } from 'vue'
import { useI18n } from '@/hooks/web/useI18n'
import { useMessage } from '@/hooks/web/useMessage'
import { AssetServerAttrCfgApi } from '@/api/dataHub/assetManagement/assetDataMng/assetServerAttrCfg'
import download from '@/utils/download'

/** 资产服务端属性配置导入组件 */
defineOptions({ name: 'AssetServerAttrCfgImport' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

// 定义 uploadRef
const uploadRef = ref()

// 导入参数
const upload = reactive({
  // 是否显示弹出层
  open: false,
  // 弹出层标题
  title: '资产分类导入',
  // 是否禁用上传
  isUploading: false,
  // 是否更新已经存在的用户数据
  isUpdateSupport: false
})

// 导入结果
const uploadResult = reactive({
  open: false,
  createCount: 0,
  updateCount: 0,
  successCount: 0,
  failureCount: 0,
  failureAssetCatCodes: {} as Record<string, string>
})

// 选择的文件
const selectedFile = ref<File | null>(null)

// 计算失败列表
const failureList = computed(() => {
  return Object.entries(uploadResult.failureAssetCatCodes).map(([assetCatCode, reason]) => ({
    assetCatCode,
    reason
  }))
})

/** 下载模板操作 */
const downloadTemplate = async () => {
  try {
    const data = await AssetServerAttrCfgApi.importAssetServerAttrCfgTemplate()
    download.excel(data, '资产服务端属性配置管理模板.xls')
    message.success('模板下载成功')
  } catch {
    message.error('模板下载失败')
  }
}

/** 文件选择变化 */
const handleFileChange = (file: any, fileList: any[]) => {
  if (fileList.length > 0) {
    selectedFile.value = file.raw
  } else {
    selectedFile.value = null
  }
}

/** 文件移除 */
const handleFileRemove = () => {
  selectedFile.value = null
  if (uploadRef.value) {
    uploadRef.value.clearFiles()
  }
}

/** 文件上传中处理 */
const handleFileUploadProgress = () => {
  upload.isUploading = true
}

/** 文件上传成功处理 */
const handleFileSuccess = (response: any) => {
  upload.isUploading = false
  selectedFile.value = null
  if (uploadRef.value) {
    uploadRef.value.clearFiles()
  }
  
  if (response.code !== 0) {
    message.error(response.msg || '导入失败')
    return
  }

  // 显示导入结果
  const result = response.data
  uploadResult.createCount = result.createCount || 0
  uploadResult.updateCount = result.updateCount || 0
  uploadResult.successCount = result.successCount || 0
  uploadResult.failureCount = result.failureCount || 0
  uploadResult.failureAssetCatCodes = result.failureAssetCatCodes || {}

  // 关闭导入弹窗，打开结果弹窗
  upload.open = false
  uploadResult.open = true

  // 提示成功信息
  if (uploadResult.failureCount === 0) {
    message.success(`导入成功！新增 ${uploadResult.createCount} 条，更新 ${uploadResult.updateCount} 条`)
  } else {
    message.warning(`导入完成！成功 ${uploadResult.successCount} 条，失败 ${uploadResult.failureCount} 条`)
  }

  // 触发导入完成事件
  emit('success')
}

/** 提交上传文件 */
const submitFileForm = () => {
  if (!selectedFile.value) {
    message.error('请选择要上传的文件')
    return
  }

  // 创建 FormData
  const formData = new FormData()
  formData.append('file', selectedFile.value)
  formData.append('updateSupport', upload.isUpdateSupport.toString())

  // 使用 upload 方法进行导入
  upload.isUploading = true
  AssetServerAttrCfgApi.importAssetServerAttrCfg(formData)
    .then(response => {
      handleFileSuccess(response)
    })
    .catch(error => {
      upload.isUploading = false
      message.error('导入失败：' + (error.message || '未知错误'))
    })
    .finally(() => {
      upload.isUploading = false
    })
}

/** 下载失败数据 */
const downloadFailureData = () => {
  if (failureList.value.length === 0) {
    message.warning('没有失败数据可下载')
    return
  }

  // 将失败数据转换为 Excel 格式并下载
  const failureData = failureList.value.map(item => ({
    '资产分类编码': item.assetCatCode,
    '失败原因': item.reason
  }))

  download.excel(failureData, '资产分类导入失败数据.xls', '失败数据')
  message.success('失败数据下载成功')
}

/** 打开导入弹窗 */
const openDialog = () => {
  upload.open = true
  upload.isUpdateSupport = false
  selectedFile.value = null
  // 清空上传文件
  if (uploadRef.value) {
    uploadRef.value.clearFiles()
  }
}

const emit = defineEmits<{
  success: [] // 导入成功事件
}>()

// 暴露方法
defineExpose({
  openDialog
})
</script>