<template>
  <div :class="[localDrawerConfig.fullscreenType?'cc-drawer-css-mainNo':'cc-drawer-css-main']">
    <el-drawer 
      :title="localDrawerConfig.title" 
      v-model="localDrawerConfig.isDrawer" 
      direction="rtl" 
      :modal="false" 
      class="cc-drawer-css" 
      size="100%"
    >
      <!-- 头部配置 -->
      <template #header>
        <span>{{ localDrawerConfig.title }}</span>
        <el-icon color="#409efc" class="icon-fullscreen" @click="toggleFullscreen">
          <FullScreen />
        </el-icon>
      </template>
      
      <div class="drawer-content">
        <!-- tabs选项 -->
        <el-tabs v-model="localDrawerConfig.active" class="content-top-tabs" @tab-click="handleTabClick">
          <el-tab-pane label="基础信息" name="1" v-if="localDrawerConfig.type == 'add'"/>
          <template v-if="localDrawerConfig.type == 'query'">
            <el-tab-pane v-for="item in localDrawerConfig.tabsList" :key="item.name" :label="item.label" :name="item.name"/>
          </template>
        </el-tabs>
        
        <!-- 基础信息相关内容 -->
        <template v-if="localDrawerConfig.active==1">
          <!-- 查看模式：显示所有字段的详细信息 -->
          <div class="see-content" v-loading="localDrawerConfig.loading" v-if="localDrawerConfig.type === 'query'">
            <ul>
              <li class="width50"><span>客户端属性ID：</span>{{ localQueryData.assetClientAttrId }}</li>
              <li class="width50"><span>关联资产ID：</span>{{ localQueryData.relAssetId }}</li>
              <li class="width50"><span>关联资产名称：</span>{{ localQueryData.relAssetName }}</li>
              <li class="width50"><span>属性名称：</span>{{ localQueryData.attrName }}</li>
              <li class="width50"><span>属性代码：</span>{{ localQueryData.attrCode }}</li>
              <li class="width50"><span>属性数据类型：</span>{{ localQueryData.attrDataType }}</li>
              <li class="width50"><span>属性值：</span>{{ localQueryData.attrValue }}</li>
              <li class="width50"><span>属性用途：</span>{{ localQueryData.attrPurpose }}</li>
              <li class="width50"><span>客户端IP：</span>{{ localQueryData.clientIp }}</li>
              <li class="width50"><span>配置时间：</span>{{ formatDate(localQueryData.cfgTime, 'YYYY-MM-DD') }}</li>
              <li class="width50"><span>操作人：</span>{{ localQueryData.operUser }}</li>
              <li class="width50"><span>更新人：</span>{{ localQueryData.updateUser }}</li>
              <li class="width50"><span>更新时间：</span>{{ formatDate(localQueryData.updatedTime, 'YYYY-MM-DD') }}</li>
              <li class="width100"><span>扩展字段1：</span>{{ localQueryData.extCat1 }}</li>
              <li class="width100"><span>扩展字段2：</span>{{ localQueryData.extCat2 }}</li>
              <li class="width100"><span>扩展公共字段1：</span>{{ localQueryData.extCommon1 }}</li>
              <li class="width100"><span>扩展公共字段2：</span>{{ localQueryData.extCommon2 }}</li>
            </ul>
          </div>
          
          <!-- 编辑/新增模式：显示所有可编辑字段 -->
          <div class="edit-content" v-loading="localDrawerConfig.loading" v-else>
            <el-form :model="localFormData" ref="drawerFormRef" class="edit-content-form" label-width="140px">
              <!-- 客户端属性ID -->
              <el-form-item label="客户端属性ID：" prop="assetClientAttrId" class="edit-content-form-li width50" 
                :rules="[{ required: true, message: '客户端属性ID不能为空', trigger: 'blur' }]">
                <el-input placeholder="请输入客户端属性ID" v-model="localFormData.assetClientAttrId" clearable/>
              </el-form-item>
              
              <!-- 关联资产ID -->
              <el-form-item label="关联资产ID：" prop="relAssetId" class="edit-content-form-li width50" 
                :rules="[{ required: true, message: '关联资产ID不能为空', trigger: 'blur' }]">
                <el-input placeholder="系统自动带出或手动输入" v-model="localFormData.relAssetId" clearable @change="handleDataIdInput"/>
              </el-form-item>
              
              <!-- 关联资产名称 -->
              <el-form-item label="关联资产名称：" prop="relAssetName" class="edit-content-form-li width50" 
                :rules="[{ required: true, message: '关联资产名称不能为空', trigger: 'change' }]">
                <el-select placeholder="请选择关联资产名称" v-model="localFormData.relAssetName" filterable clearable @change="handleDataNameChange">
                  <el-option v-for="op in props.dataMaps.options" :key="op.value" :label="op.label" :value="op.label"/>
                </el-select>
              </el-form-item>
              
              <!-- 属性名称 -->
              <el-form-item label="属性名称：" prop="attrName" class="edit-content-form-li width50" 
                :rules="[{ required: true, message: '属性名称不能为空', trigger: 'blur' }]">
                <el-input placeholder="请输入属性名称" v-model="localFormData.attrName" clearable/>
              </el-form-item>
              
              <!-- 属性代码 -->
              <el-form-item label="属性代码：" prop="attrCode" class="edit-content-form-li width50" 
                :rules="[{ required: true, message: '属性代码不能为空', trigger: 'blur' }]">
                <el-input placeholder="请输入属性代码" v-model="localFormData.attrCode" clearable/>
              </el-form-item>
              
              <!-- 属性数据类型 -->
              <el-form-item label="属性数据类型：" prop="attrDataType" class="edit-content-form-li width50" 
                :rules="[{ required: true, message: '属性数据类型不能为空', trigger: 'change' }]">
                <el-select placeholder="请选择属性数据类型" v-model="localFormData.attrDataType" filterable clearable>
                  <el-option v-for="item in attrDataTypeOptions" :key="item.value" :label="item.label" :value="item.value"/>
                </el-select>
              </el-form-item>
              
              <!-- 属性值 -->
              <el-form-item label="属性值：" prop="attrValue" class="edit-content-form-li width50" 
                :rules="[{ required: true, message: '属性值不能为空', trigger: 'blur' }]">
                <el-input placeholder="请输入属性值" v-model="localFormData.attrValue" clearable/>
              </el-form-item>
              
              <!-- 属性用途 -->
              <el-form-item label="属性用途：" prop="attrPurpose" class="edit-content-form-li width50">
                <el-input placeholder="请输入属性用途" v-model="localFormData.attrPurpose" clearable/>
              </el-form-item>
              
              <!-- 客户端IP -->
              <el-form-item label="客户端IP：" prop="clientIp" class="edit-content-form-li width50">
                <el-input placeholder="请输入客户端IP" v-model="localFormData.clientIp" clearable/>
              </el-form-item>
              
              <!-- 配置时间 -->
              <el-form-item label="配置时间：" prop="cfgTime" class="edit-content-form-li width50" 
                :rules="[{ required: true, message: '配置时间不能为空', trigger: 'blur' }]">
                <el-date-picker class="width100" v-model="localFormData.cfgTime" type="date" placeholder="请选择配置时间" format="YYYY-MM-DD" value-format="x"/>
              </el-form-item>
              
              <!-- 操作人 -->
              <el-form-item label="操作人：" prop="operUser" class="edit-content-form-li width50" 
                :rules="[{ required: true, message: '操作人不能为空', trigger: 'blur' }]">
                <el-input placeholder="请输入操作人" v-model="localFormData.operUser" clearable/>
              </el-form-item>
              
              <!-- 更新人 -->
              <el-form-item label="更新人：" prop="updateUser" class="edit-content-form-li width50">
                <el-input placeholder="请输入更新人" v-model="localFormData.updateUser" clearable/>
              </el-form-item>
              
              <!-- 更新时间 -->
              <el-form-item label="更新时间：" prop="updatedTime" class="edit-content-form-li width50">
                <el-date-picker class="width100" v-model="localFormData.updatedTime" type="date" placeholder="请选择更新时间" format="YYYY-MM-DD" value-format="x"/>
              </el-form-item>
              
              <!-- 扩展字段 -->
              <el-form-item label="扩展字段1：" prop="extCat1" class="edit-content-form-li width100">
                <el-input placeholder="请输入扩展字段1" v-model="localFormData.extCat1" clearable/>
              </el-form-item>
              
              <el-form-item label="扩展字段2：" prop="extCat2" class="edit-content-form-li width100">
                <el-input placeholder="请输入扩展字段2" v-model="localFormData.extCat2" clearable/>
              </el-form-item>
              
              <el-form-item label="扩展公共字段1：" prop="extCommon1" class="edit-content-form-li width100">
                <el-input placeholder="请输入扩展公共字段1" v-model="localFormData.extCommon1" clearable/>
              </el-form-item>
              
              <el-form-item label="扩展公共字段2：" prop="extCommon2" class="edit-content-form-li width100">
                <el-input placeholder="请输入扩展公共字段2" v-model="localFormData.extCommon2" clearable/>
              </el-form-item>
            </el-form>
          </div>
        </template>
      </div>
      
      <!-- 操作按钮：仅在新增和编辑模式下显示 -->
      <div class="action-button" v-if="['add', 'edit'].includes(localDrawerConfig.type) && !localDrawerConfig.loading">
        <el-button :icon="Close" @click="handleCancel">取 消</el-button>
        <el-button type="primary" :icon="Check" @click="handleSubmit">保 存</el-button>
      </div>
    </el-drawer>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, watch, computed } from 'vue'
import { Close, Check, FullScreen } from '@element-plus/icons-vue'
import { formatDate } from '@/utils/formatTime'

// 定义组件props
interface Props {
  drawerConfig: {
    isDrawer: boolean
    loading: boolean
    title: string
    type: 'add' | 'edit' | 'query'
    active: string
    fullscreenType: boolean
    tabsList: Array<{ label: string; name: string }>
  }
  formData: any
  queryData: any
  dataMaps: {
    name2Id: Record<string, string>
    id2Name: Record<string, string>
    options: Array<{ label: string; value: string }>
  }
  OptionsAll?: any
}

const props = defineProps<Props>()

// 定义组件事件
const emit = defineEmits([
  'update:drawerConfig',
  'update:formData', 
  'update:queryData',
  'submit-form',
  'tab-click',
  'data-name-change', 
  'data-id-input',
  'cancel'
])

// 使用computed创建本地副本，避免直接修改props
const localDrawerConfig = computed({
  get: () => props.drawerConfig,
  set: (value) => emit('update:drawerConfig', value)
})

const localFormData = computed({
  get: () => props.formData,
  set: (value) => emit('update:formData', value)
})

const localQueryData = computed({
  get: () => props.queryData,
  set: (value) => emit('update:queryData', value)
})

// 表单ref
const drawerFormRef = ref()

// 属性数据类型选项 - 在组件内部定义，避免与props中的OptionsAll冲突
const attrDataTypeOptions = [
  {label: '字符串', value: "字符串"},
  {label: '整数', value: "整数"},
  {label: '浮点数', value: "浮点数"},
  {label: '布尔值', value: "布尔值"},
  {label: '日期时间', value: "日期时间"}
]

/**
 * 切换全屏模式
 */
const toggleFullscreen = () => {
  localDrawerConfig.value = {
    ...localDrawerConfig.value,
    fullscreenType: !localDrawerConfig.value.fullscreenType
  }
}

/**
 * 处理表单提交
 */
const handleSubmit = async () => {
  if (!drawerFormRef.value) return
  
  // 表单验证
  const valid = await new Promise<boolean>((resolve) => {
    drawerFormRef.value.validate((valid: boolean) => {
      resolve(valid)
    })
  })
  
  if (valid) {
    emit('submit-form', drawerFormRef.value)
  }
}

/**
 * 处理取消操作
 */
const handleCancel = () => {
  emit('cancel')
}

/**
 * 处理Tab切换
 */
const handleTabClick = (tab: any) => {
  emit('tab-click', tab)
}

/**
 * 处理关联资产名称变化
 */
const handleDataNameChange = (name: string) => {
  emit('data-name-change', name)
}

/**
 * 处理关联资产ID输入
 */
const handleDataIdInput = (id: string) => {
  emit('data-id-input', id)
}
</script>

<style lang="scss" scoped>
// 抽屉内容区域样式
.drawer-content {
  padding: 20px;
  height: calc(100% - 80px);
  overflow-y: auto;
  
  // 查看模式样式
  .see-content {
    ul {
      list-style: none;
      padding: 0;
      margin: 0;
      display: flex;
      flex-wrap: wrap;
      
      li {
        margin-bottom: 16px;
        padding: 0 10px;
        box-sizing: border-box;
        
        span {
          color: #606266;
          font-weight: 500;
          margin-right: 8px;
        }
        
        // 布局类
        &.width100 {
          width: 100%;
        }
        
        &.width50 {
          width: 50%;
        }
        
        &.width33 {
          width: 33.33%;
        }
      }
    }
  }
  
  // 编辑模式样式
  .edit-content {
    .edit-content-form {
      display: flex;
      flex-wrap: wrap;
      
      .edit-content-form-li {
        padding: 0 10px;
        box-sizing: border-box;
        margin-bottom: 16px;
        
        // 布局类
        &.width100 {
          width: 100%;
        }
        
        &.width50 {
          width: 50%;
        }
        
        &.width33 {
          width: 33.33%;
        }
        
        // 表单项标签样式
        :deep(.el-form-item__label) {
          font-weight: 500;
          color: #606266;
        }
        
        // 表单项内容样式
        :deep(.el-input),
        :deep(.el-select) {
          width: 100%;
        }
      }
    }
  }
}

// 操作按钮区域样式
.action-button {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 16px;
  background: #fff;
  border-top: 1px solid #e8e8e8;
  text-align: right;
  
  .el-button {
    margin-left: 12px;
  }
}

// 全屏图标样式
.icon-fullscreen {
  cursor: pointer;
  margin-left: 12px;
  font-size: 18px;
  
  &:hover {
    color: #67c23a;
  }
}
</style>