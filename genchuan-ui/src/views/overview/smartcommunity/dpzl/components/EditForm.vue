<template>
  <div class="edit-form">
    <div class="form-header">
      <h3>{{ title }}</h3>
    </div>
    <div class="form-content">
      <!-- 指标阈值编辑 -->
      <div v-if="type === 'indicator'" class="indicator-edit">
        <el-form :model="formData" :rules="rules" ref="formRef" label-width="120px">
          <el-form-item label="指标名称">
            <el-input v-model="formData.name" disabled />
          </el-form-item>
          <el-form-item label="预警阈值" prop="warningThreshold">
            <el-input v-model="formData.warningThreshold" placeholder="请输入预警阈值">
              <template #append>%</template>
            </el-input>
          </el-form-item>
          <el-form-item label="严重阈值" prop="criticalThreshold">
            <el-input v-model="formData.criticalThreshold" placeholder="请输入严重阈值">
              <template #append>%</template>
            </el-input>
          </el-form-item>
          <el-form-item label="自动刷新">
            <el-switch v-model="formData.autoRefresh" />
          </el-form-item>
          <el-form-item label="刷新间隔" v-if="formData.autoRefresh">
            <el-select v-model="formData.refreshInterval">
              <el-option label="5分钟" value="5m" />
              <el-option label="10分钟" value="10m" />
              <el-option label="30分钟" value="30m" />
            </el-select>
          </el-form-item>
          <el-form-item label="通知方式">
            <el-checkbox-group v-model="formData.notificationMethods">
              <el-checkbox label="system">系统消息</el-checkbox>
              <el-checkbox label="email">邮件通知</el-checkbox>
              <el-checkbox label="sms">短信通知</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="备注">
            <el-input
              v-model="formData.remark"
              type="textarea"
              :rows="3"
              placeholder="请输入备注信息"
            />
          </el-form-item>
        </el-form>
      </div>

      <!-- 地图点位编辑 -->
      <div v-else-if="type === 'mapPoint'" class="mappoint-edit">
        <el-form :model="formData" :rules="rules" ref="formRef" label-width="100px">
          <el-form-item label="点位名称" prop="name">
            <el-input v-model="formData.name" placeholder="请输入点位名称" />
          </el-form-item>
          <el-form-item label="点位类型" prop="type">
            <el-select v-model="formData.type" placeholder="请选择点位类型">
              <el-option label="资源点位" value="resource" />
              <el-option label="异常点位" value="abnormal" />
              <el-option label="重点区域" value="keyArea" />
            </el-select>
          </el-form-item>
          <el-form-item label="详细位置" prop="location">
            <el-input v-model="formData.location" placeholder="请输入详细位置" />
          </el-form-item>
          <el-form-item label="所属网格" prop="grid">
            <el-select v-model="formData.grid" placeholder="请选择所属网格">
              <el-option label="网格一" value="grid1" />
              <el-option label="网格二" value="grid2" />
              <el-option label="网格三" value="grid3" />
            </el-select>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-radio-group v-model="formData.status">
              <el-radio label="normal">正常</el-radio>
              <el-radio label="warning">告警</el-radio>
              <el-radio label="offline">离线</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="负责人" prop="manager">
            <el-input v-model="formData.manager" placeholder="请输入负责人" />
          </el-form-item>
          <el-form-item label="联系电话" prop="phone">
            <el-input v-model="formData.phone" placeholder="请输入联系电话" />
          </el-form-item>
          <el-form-item label="描述信息">
            <el-input
              v-model="formData.description"
              type="textarea"
              :rows="3"
              placeholder="请输入描述信息"
            />
          </el-form-item>
        </el-form>
      </div>

      <!-- 资源信息编辑 -->
      <div v-else-if="type === 'resource'" class="resource-edit">
        <el-form :model="formData" :rules="rules" ref="formRef" label-width="100px">
          <el-form-item label="资源名称" prop="name">
            <el-input v-model="formData.name" placeholder="请输入资源名称" />
          </el-form-item>
          <el-form-item label="资源类型" prop="resourceType">
            <el-select v-model="formData.resourceType" placeholder="请选择资源类型">
              <el-option label="路灯" value="light" />
              <el-option label="监控" value="camera" />
              <el-option label="消防设施" value="fire" />
              <el-option label="服务点" value="service" />
            </el-select>
          </el-form-item>
          <el-form-item label="设备型号" prop="model">
            <el-input v-model="formData.model" placeholder="请输入设备型号" />
          </el-form-item>
          <el-form-item label="序列号" prop="serialNumber">
            <el-input v-model="formData.serialNumber" placeholder="请输入序列号" />
          </el-form-item>
          <el-form-item label="安装位置" prop="location">
            <el-input v-model="formData.location" placeholder="请输入安装位置" />
          </el-form-item>
          <el-form-item label="所属网格" prop="grid">
            <el-select v-model="formData.grid" placeholder="请选择所属网格">
              <el-option label="网格一" value="grid1" />
              <el-option label="网格二" value="grid2" />
              <el-option label="网格三" value="grid3" />
            </el-select>
          </el-form-item>
          <el-form-item label="负责人" prop="manager">
            <el-input v-model="formData.manager" placeholder="请输入负责人" />
          </el-form-item>
          <el-form-item label="联系电话" prop="phone">
            <el-input v-model="formData.phone" placeholder="请输入联系电话" />
          </el-form-item>
          <el-form-item label="维护单位" prop="maintenanceUnit">
            <el-input v-model="formData.maintenanceUnit" placeholder="请输入维护单位" />
          </el-form-item>
          <el-form-item label="安装时间" prop="installTime">
            <el-date-picker
              v-model="formData.installTime"
              type="datetime"
              placeholder="选择安装时间"
              value-format="YYYY-MM-DD HH:mm:ss"
            />
          </el-form-item>
          <el-form-item label="备注信息">
            <el-input
              v-model="formData.remark"
              type="textarea"
              :rows="3"
              placeholder="请输入备注信息"
            />
          </el-form-item>
        </el-form>
      </div>

      <!-- 通用编辑表单 -->
      <div v-else class="generic-edit">
        <el-form :model="formData" :rules="rules" ref="formRef" label-width="100px">
          <el-form-item
            v-for="field in formFields"
            :key="field.prop"
            :label="field.label"
            :prop="field.prop"
            :required="field.required"
          >
            <component
              :is="getFieldComponent(field.type)"
              v-model="formData[field.prop]"
              :placeholder="field.placeholder"
              :options="field.options"
              :type="field.inputType"
            />
          </el-form-item>
        </el-form>
      </div>
    </div>

    <div class="form-actions">
      <el-button @click="$emit('cancel')">取消</el-button>
      <el-button type="primary" @click="handleSave" :loading="loading">保存</el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import GlobalSituationOverviewAPI from '@/api/overview/smartcommunity/GlobalSituationOverview.js'

// 定义各类表单数据类型接口
interface IndicatorFormData {
  name: string;
  warningThreshold: number;
  criticalThreshold: number;
  autoRefresh: boolean;
  refreshInterval: '5m' | '10m' | '30m';
  notificationMethods: ('system' | 'email' | 'sms')[];
  remark: string;
  description?: string;
}

interface MapPointFormData {
  name: string;
  type: 'resource' | 'abnormal' | 'keyArea';
  coords: [number, number];
  isPermanent: boolean;
  location: string;
  grid: 'grid1' | 'grid2' | 'grid3';
  status: 'normal' | 'warning' | 'offline';
  manager: string;
  phone: string;
  description: string;
}

interface ResourceFormData {
  name: string;
  phone: string;
  installTime: string; // 匹配日期选择器的value-format（字符串类型）
  resourceType: 'light' | 'camera' | 'fire' | 'service';
  status: 'normal' | 'fault' | 'maintenance';
  model: string;
  serialNumber: string;
  location: string;
  grid: 'grid1' | 'grid2' | 'grid3';
  maintenanceUnit: string;
  remark: string;
  manager: string;
}

// 联合表单数据类型
type FormData = IndicatorFormData | MapPointFormData | ResourceFormData;

// 定义组件Props类型
const props = defineProps<{
  data: Partial<FormData>;
  type: 'indicator' | 'mapPoint' | 'resource' | string;
}>();

// 定义API返回值类型
interface ApiResponse<T> {
  code: number;
  message: string;
  data: T;
}

// 表单数据初始化
const formData = reactive<Partial<FormData>>({
  name: '',
  warningThreshold: 0,
  criticalThreshold: 0,
  autoRefresh: false,
  refreshInterval: '5m',
  notificationMethods: [],
  remark: '',
  type: '',
  location: '',
  grid: '',
  status: 'normal',
  manager: '',
  phone: '',
  description: '',
  resourceType: 'light',
  model: '',
  serialNumber: '',
  maintenanceUnit: '',
  installTime: '',
  coords: [0, 0],
  isPermanent: true
});

// 表单验证规则
const rules = computed(() => {
  switch (props.type) {
    case 'indicator':
      return {
        warningThreshold: [
          { required: true, message: '请输入预警阈值', trigger: 'blur' },
          { type: 'number', message: '请输入有效的数字', trigger: 'blur' },
          { min: 0, max: 100, message: '阈值必须在0-100之间', trigger: 'blur' }
        ],
        criticalThreshold: [
          { required: true, message: '请输入严重阈值', trigger: 'blur' },
          { type: 'number', message: '请输入有效的数字', trigger: 'blur' },
          { min: 0, max: 100, message: '阈值必须在0-100之间', trigger: 'blur' }
        ]
      };
    case 'mapPoint':
      return {
        name: [
          { required: true, message: '请输入点位名称', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择点位类型', trigger: 'change' }
        ],
        location: [
          { required: true, message: '请输入详细位置', trigger: 'blur' }
        ],
        grid: [
          { required: true, message: '请选择所属网格', trigger: 'change' }
        ],
        manager: [
          { required: true, message: '请输入负责人', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入联系电话', trigger: 'blur' },
          { pattern: /^1[3-9]\d{9}$/, message: '请输入有效的手机号码', trigger: 'blur' }
        ]
      };
    case 'resource':
      return {
        name: [
          { required: true, message: '请输入资源名称', trigger: 'blur' }
        ],
        resourceType: [
          { required: true, message: '请选择资源类型', trigger: 'change' }
        ],
        model: [
          { required: true, message: '请输入设备型号', trigger: 'blur' }
        ],
        serialNumber: [
          { required: true, message: '请输入序列号', trigger: 'blur' }
        ],
        location: [
          { required: true, message: '请输入安装位置', trigger: 'blur' }
        ],
        grid: [
          { required: true, message: '请选择所属网格', trigger: 'change' }
        ],
        manager: [
          { required: true, message: '请输入负责人', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入联系电话', trigger: 'blur' },
          { pattern: /^1[3-9]\d{9}$/, message: '请输入有效的手机号码', trigger: 'blur' }
        ],
        maintenanceUnit: [
          { required: true, message: '请输入维护单位', trigger: 'blur' }
        ],
        installTime: [
          { required: true, message: '请选择安装时间', trigger: 'change' }
        ]
      };
    default:
      return {};
  }
});

// 表单标题
const title = computed(() => {
  const titleMap = {
    indicator: '指标阈值设置',
    mapPoint: '地图点位编辑',
    resource: '资源信息编辑'
  }
  return titleMap[props.type as keyof typeof titleMap] || '编辑信息'
});

// 动态表单字段配置（预留通用表单扩展）
const formFields = ref([])

// 方法：获取动态表单组件
const getFieldComponent = (type: string) => {
  const componentMap = {
    input: 'el-input',
    select: 'el-select',
    radio: 'el-radio-group',
    checkbox: 'el-checkbox-group',
    date: 'el-date-picker',
    textarea: 'el-input'
  }
  return componentMap[type as keyof typeof componentMap] || 'el-input'
}

// 方法：保存表单
const formRef = ref(null)
const loading = ref(false)
const emit = defineEmits(['save', 'cancel'])

const handleSave = async () => {
  try {
    const valid = await formRef.value.validate()
    if (valid) {
      loading.value = true
      try {
        let result: ApiResponse<any>;
        switch (props.type) {
          case 'indicator':
            result = await GlobalSituationOverviewAPI.updateIndicatorThreshold(formData);
            if (result.code === 200) {
              localStorage.setItem('indicatorCache', JSON.stringify(formData));
            } else {
              throw new Error(result.message || '指标阈值更新失败');
            }
            break;
          case 'resource':
            result = await GlobalSituationOverviewAPI.updateResourceInfo(formData);
            if (result.code === 200) {
              localStorage.setItem('resourceCache', JSON.stringify(formData));
            } else {
              throw new Error(result.message || '资源信息更新失败');
            }
            break;
          case 'mapPoint':
            result = await GlobalSituationOverviewAPI.updateMapPoint(formData);
            if (result.code === 200) {
              localStorage.setItem('mapPointCache', JSON.stringify(formData));
            } else {
              throw new Error(result.message || '地图点位更新失败');
            }
            break;
          default:
            throw new Error('表单类型不支持');
        }
        emit('save', { ...formData, type: props.type })
      } catch (error) {
        ElMessage.error(error.message);
      } finally {
        loading.value = false;
      }
    }
  } catch (error) {
    ElMessage.error('表单验证失败，请检查必填项');
  }
}

// 初始化表单数据
const initFormData = () => {
  // 清空表单
  Object.keys(formData).forEach(key => {
    formData[key] = '';
  });

  // 重置特定类型的默认值
  if (props.type === 'indicator') {
    formData.autoRefresh = false;
    formData.refreshInterval = '5m';
    formData.notificationMethods = [];
  } else if (props.type === 'mapPoint') {
    formData.status = 'normal';
    formData.coords = [0, 0];
    formData.isPermanent = true;
  } else if (props.type === 'resource') {
    formData.resourceType = 'light';
    formData.status = 'normal';
  }

  // 填充传入的初始数据
  if (props.data) {
    Object.keys(props.data).forEach(key => {
      if (formData.hasOwnProperty(key)) {
        formData[key] = props.data[key];
      }
    });
  }
}

// 组件挂载时初始化表单
onMounted(() => {
  initFormData()
})
</script>

<style lang="scss" scoped>
.edit-form {
  .form-header {
    margin-bottom: 20px;
    padding-bottom: 15px;
    border-bottom: 1px solid rgba(255, 255, 255, 0.1);

    h3 {
      margin: 0;
      font-size: 16px;
      color: #fff;
    }
  }

  .form-content {
    max-height: 400px;
    overflow-y: auto;
    padding-right: 10px;

    &::-webkit-scrollbar {
      width: 6px;
    }

    &::-webkit-scrollbar-thumb {
      background: rgba(255, 255, 255, 0.2);
      border-radius: 3px;
    }
  }

  .form-actions {
    margin-top: 20px;
    display: flex;
    justify-content: flex-end;
    gap: 10px;
    padding-top: 15px;
    border-top: 1px solid rgba(255, 255, 255, 0.1);
  }

  // 表单项目间距
  :deep(.el-form-item) {
    margin-bottom: 15px;

    .el-form-item__label {
      color: rgba(255, 255, 255, 0.85);
      font-size: 13px;
    }

    .el-input__wrapper,
    .el-select__wrapper,
    .el-textarea__wrapper {
      background-color: rgba(255, 255, 255, 0.05);
      border-color: rgba(255, 255, 255, 0.1);

      input, textarea {
        color: #fff;
        font-size: 13px;
      }
    }

    .el-radio__label,
    .el-checkbox__label {
      color: rgba(255, 255, 255, 0.85);
      font-size: 13px;
    }
  }

  // 适配不同表单类型的特殊样式
  .indicator-edit,
  .mappoint-edit,
  .resource-edit,
  .generic-edit {
    :deep(.el-select) {
      width: 100%;
    }

    :deep(.el-date-picker) {
      width: 100%;
    }

    :deep(.el-checkbox-group) {
      display: flex;
      gap: 15px;
      flex-wrap: wrap;
    }
  }
}
</style>
