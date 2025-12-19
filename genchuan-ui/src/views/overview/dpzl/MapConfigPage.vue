<template>
  <div>
    <el-card shadow="never" class="config-card">
      <el-form
        ref="configFormRef"
        :model="formData"
        label-width="120px"
        :rules="formRules"
      >
        <!-- 选择应用场景 -->
        <el-form-item label="应用场景" prop="area">
          <el-cascader
            v-model="formData.area"
            placeholder="选择应用场景"
            size="small"
            :options="sceneList"
            separator="/"
            collapse-tags
            @change="handleCascaderChange"
            style="width: 100%; max-width: 400px;"
          />
        </el-form-item>

        <!-- 展示字段筛选 -->
        <el-form-item
          label="展示字段筛选"
          v-if="formData.sceneKey && sceneFieldList.length"
        >
          <div class="field-select-group">
            <el-checkbox-group
              v-model="formData.selectedFields"
              @change="handleFieldChange"
            >
              <el-space direction="vertical" wrap>
                <el-checkbox
                  v-for="field in sceneFieldList"
                  :key="field.key"
                  :label="field.key"
                  class="field-checkbox"
                >
                  {{ field.label }}（{{ field.type }}）
                </el-checkbox>
              </el-space>
            </el-checkbox-group>
          </div>
          <div class="field-config-group" style="margin-top: 20px; width: 500px">
            <el-divider content="已选字段配置" />
            <div class="field-draggable">
              <div
                v-for="(config, index) in formData.selectedFieldsConfig"
                :key="config.key"
                class="field-config-item"
              >
                <el-row :gutter="10" align="middle">
                  <el-col :span="6">
                    <span class="field-label">{{ config.label }}</span>
                  </el-col>
                  <el-col :span="7">
                    <el-select
                      v-model="config.renderType"
                      size="small"
                      @change="handleRenderTypeChange(config)"
                    >
                      <el-option label="普通文本" value="normal" />
                      <el-option label="坐标拼接" value="coord" />
                      <el-option label="状态文本" value="status" />
                    </el-select>
                  </el-col>
                  <el-col :span="6">
                    <el-button
                      type="text"
                      size="small"
                      v-if="config.renderType === 'status'"
                      @click="openStatusMapConfig(config)"
                    >
                      配置状态映射
                    </el-button>
                    <span v-else>-</span>
                  </el-col>
                  <el-col :span="5">
                    <el-button
                      type="danger"
                      size="small"
                      icon="Delete"
                      @click="deleteFieldConfig(index)"
                    >
                      删除
                    </el-button>
                  </el-col>
                </el-row>
              </div>
            </div>
          </div>
        </el-form-item>

        <!-- 标记点样式配置 -->
        <el-form-item
          label="标记点样式配置"
          v-if="formData.sceneKey"
        >
          <el-divider content="默认状态样式" />
          <div class="icon-select-group">
            <div class="selected-icon-preview" v-if="defaultIconFileList.length">
              <img :src="defaultIconFileList[0].url" alt="已选图标" class="preview-img" />
              <span class="preview-tip">{{ defaultIconFileList[0].name || '默认图标' }}</span>
            </div>
            <div class="selected-icon-preview placeholder" v-else>
              <span>未选择图标</span>
            </div>
            <el-button
              type="primary"
              size="small"
              icon="Picture"
              @click="openIconSelect('default')"
              style="margin-top: 10px;"
            >
              选择默认图标
            </el-button>
          </div>
          <el-form-item label="图标尺寸(px)">
            <el-input-number
              v-model="formData.defaultIconSize.width"
              :min="10"
              :max="100"
              style="margin-right: 10px;"
            />
            <span>×</span>
            <el-input-number
              v-model="formData.defaultIconSize.height"
              :min="10"
              :max="100"
              style="margin-left: 10px;"
            />
          </el-form-item>
          <el-divider content="地图中心坐标配置" />
          <el-form-item label="地图中心纬度">
            <el-input-number
              v-model="formData.mapCenter.lat"
              :min="-90"
              :max="90"
              :precision="6"
              size="small"
              style="width: 200px;"
              placeholder="如：26.855237"
            />
            <span class="config-tip">（WGS84坐标系，保留6位小数）</span>
          </el-form-item>
          <el-form-item label="地图中心经度">
            <el-input-number
              v-model="formData.mapCenter.lng"
              :min="-180"
              :max="180"
              :precision="6"
              size="small"
              style="width: 200px;"
              placeholder="如：118.001000"
            />
            <span class="config-tip">（WGS84坐标系，保留6位小数）</span>
          </el-form-item>
          <el-divider content="多状态标记点样式" />
          <el-button
            type="text"
            size="small"
            @click="addStatusIconConfig"
            icon="Plus"
          >
            新增状态图标
          </el-button>
          <el-table
            :data="formData.statusIconConfigList"
            border
            size="small"
            style="margin-top: 10px;"
          >
            <el-table-column label="关联状态字段">
              <template #default="scope">
                <el-select
                  v-model="scope.row.fieldKey"
                  placeholder="选择状态字段"
                  filterable
                  size="small"
                >
                  <el-option
                    v-for="field in formData.selectedFieldsConfig.filter(f => f.renderType === 'status')"
                    :key="field.key"
                    :label="field.label"
                    :value="field.key"
                  />
                </el-select>
              </template>
            </el-table-column>
            <el-table-column label="原始状态值">
              <template #default="scope">
                <el-input v-model="scope.row.rawValue" placeholder="如：故障" size="small" />
              </template>
            </el-table-column>
            <el-table-column label="状态图标">
              <template #default="scope">
                <div class="status-icon-select">
                  <div class="icon-preview-small" v-if="scope.row.icon">
                    <img :src="scope.row.icon" alt="状态图标" class="preview-img-small" />
                  </div>
                  <div class="icon-preview-small placeholder" v-else>
                    <span>-</span>
                  </div>
                  <el-button
                    type="text"
                    size="small"
                    @click="openIconSelect('status', scope.row)"
                  >
                    选择图标
                  </el-button>
                </div>
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template #default="scope">
                <el-button
                  type="danger"
                  size="small"
                  icon="Delete"
                  @click="deleteStatusIconConfig(scope.$index)"
                >
                  删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>

        <!-- 图例配置 -->
        <el-form-item
          label="图例配置"
          v-if="formData.sceneKey && formData.selectedFieldsConfig.some(f => f.renderType === 'status')"
        >
          <el-divider content="图例样式配置" />
          <el-form-item label="图例显示开关">
            <el-switch v-model="formData.legendConfig.show" active-text="显示" inactive-text="隐藏" />
          </el-form-item>
          <el-form-item label="图例位置">
            <el-select v-model="formData.legendConfig.position" size="small" style="min-width: 120px;">
              <el-option label="左下角" value="bottomLeft" />
              <el-option label="右下角" value="bottomRight" />
              <el-option label="左上角" value="topLeft" />
              <el-option label="右上角" value="topRight" />
            </el-select>
          </el-form-item>
          <el-form-item label="图例">
            <div class="legend-preview-group" v-if="formData.legendConfig.items.length">
              <div class="legend-preview-item" v-for="item in formData.legendConfig.items" :key="item.rawValue">
                <img :src="item.icon" alt="图例图标" class="preview-img-small" />
                <span>{{ item.text || item.rawValue }}</span>
              </div>
            </div>
            <span class="config-tip" v-else>暂无状态图标配置，暂无图例</span>
          </el-form-item>
        </el-form-item>

        <!-- 绕点环绕配置 -->
        <el-form-item label="绕点环绕配置" v-if="formData.sceneKey">
          <el-divider content="动画基础配置" />
          <el-form-item label="旋转速度(°/帧)">
            <el-input-number
              v-model="formData.orbitAnimation.rotateSpeed"
              :min="0.1"
              :max="0.5"
              step="0.1"
              size="small"
              style="width: 120px;"
              placeholder="如：0.2（值越小旋转越慢）"
            />
            <span class="config-tip">（0.1-0.5）</span>
          </el-form-item>
          <el-form-item label="循环次数">
            <el-select
              v-model="formData.orbitAnimation.loop"
              size="small"
              style="min-width: 120px;"
            >
              <el-option label="无限循环" value="infinity" />
              <el-option label="1次" value="1" />
              <el-option label="3次" value="3" />
              <el-option label="5次" value="5" />
            </el-select>
          </el-form-item>
          <el-form-item label="地图俯仰角">
            <el-input-number
              v-model="formData.orbitAnimation.pitch"
              :min="0"
              :max="80"
              step="5"
              size="small"
              style="width: 120px;"
              placeholder="如：40（增强3D效果）"
            />
            <span class="config-tip">（0=2D平面，值越大3D效果越强）</span>
          </el-form-item>
          <el-form-item label="环绕缩放级别">
            <el-input-number
              v-model="formData.orbitAnimation.zoom"
              :min="10"
              :max="20"
              step="1"
              size="small"
              style="width: 120px;"
              placeholder="如：18（放大级别）"
            />
            <span class="config-tip">（值越大地图越放大）</span>
          </el-form-item>
          <el-form-item label="环绕中心点">
            <el-radio-group v-model="formData.orbitAnimation.centerType" size="small">
              <el-radio label="复用地图中心坐标" />
              <el-radio label="自定义中心点" />
            </el-radio-group>
            <div v-if="formData.orbitAnimation.centerType === '自定义中心点'" style="margin-top: 10px;">
              <el-form-item label="自定义纬度" inline>
                <el-input-number
                  v-model="formData.orbitAnimation.customCenter.lat"
                  :min="-90"
                  :max="90"
                  :precision="6"
                  size="small"
                  style="width: 150px;"
                />
              </el-form-item>
              <el-form-item label="自定义经度" inline>
                <el-input-number
                  v-model="formData.orbitAnimation.customCenter.lng"
                  :min="-180"
                  :max="180"
                  :precision="6"
                  size="small"
                  style="width: 150px;"
                />
              </el-form-item>
            </div>
          </el-form-item>
        </el-form-item>
      </el-form>

      <div style="margin-top: 10vh; text-align: center;">
        <el-button type="danger" @click="resetForm">重置表单</el-button>
        <el-button type="warning" @click="handleClearConfig" :disabled="!formData.sceneKey">
          清除已保存配置
        </el-button>
        <el-button type="primary" @click="previewConfig">预览效果</el-button>
        <el-button type="success" @click="handleSaveConfig">保存配置</el-button>
      </div>
    </el-card>

    <!-- 预览弹窗 -->
    <el-dialog
      v-model="previewVisible"
      title="配置效果预览"
      width="60%"
      append-to-body
      destroy-on-close
      @close="handlePreviewClose"
    >
      <!-- 强制约束预览容器尺寸+溢出隐藏 -->
      <div style="width: 100%; height: 60vh; overflow: hidden; position: relative;">
        <MapTemplate
          ref="previewMapRef"
          idName="preview-map"
          :sceneKey="formData.sceneKey"
          :previewMode="true"
          :previewConfig="previewConfigData"
        />
      </div>
      <template #footer>
        <el-button @click="previewVisible = false">关闭预览</el-button>
      </template>
    </el-dialog>

    <!-- 状态映射配置弹窗 -->
    <el-dialog
      v-model="statusMapVisible"
      title="状态文本映射配置"
      width="400px"
      :close-on-click-modal="false"
      append-to-body
    >
      <el-form :model="statusMapForm" label-width="100px">
        <el-form-item label="原始状态值">
          <el-input
            v-model="statusMapForm.rawValue"
            placeholder="如：破损/完好"
          />
        </el-form-item>
        <el-form-item label="展示文本">
          <el-input
            v-model="statusMapForm.displayText"
            placeholder="如：设备破损/设备完好"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="statusMapVisible = false">取消</el-button>
        <el-button
          type="primary"
          @click="saveStatusMapConfig"
        >
          添加映射
        </el-button>
      </template>
    </el-dialog>

    <!-- 图标选择弹窗 -->
    <el-dialog
      v-model="iconSelectVisible"
      title="选择图标"
      width="600px"
      append-to-body
    >
      <el-tabs v-model="currentIconType" type="card">
        <el-tab-pane label="默认点位图标" name="default">
          <div class="icon-library-grid">
            <div
              class="icon-item"
              v-for="icon in PRESET_ICON_LIBRARY.default"
              :key="icon.id"
              :class="{ active: selectedIconUrl === icon.url }"
              @click="selectIcon(icon)"
            >
              <img :src="icon.url" alt="icon.name" class="icon-item-img" />
              <span class="icon-item-name">{{ icon.name }}</span>
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane label="状态图标" name="status">
          <div class="icon-library-grid">
            <div
              class="icon-item"
              v-for="icon in PRESET_ICON_LIBRARY.status"
              :key="icon.id"
              :class="{ active: selectedIconUrl === icon.url }"
              @click="selectIcon(icon)"
            >
              <img :src="icon.url" alt="icon.name" class="icon-item-img" />
              <span class="icon-item-name">{{ icon.name }}</span>
            </div>
          </div>
        </el-tab-pane>
      </el-tabs>

      <template #footer>
        <el-button @click="iconSelectVisible = false">取消</el-button>
        <el-button
          type="primary"
          @click="confirmIconSelect"
          :disabled="!selectedIconUrl"
        >
          确认选择
        </el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, watch, nextTick } from 'vue';
import { ElMessage } from 'element-plus';
import MapTemplate from '@/views/overview/dpzl/components/MapTemplate.vue';
import {
  getSceneList,
  getSceneFieldList,
  MAP_DEFAULT_CONFIG,
  PRESET_ICON_LIBRARY,
  getSceneKeyByCascader,
  getCascaderValueBySceneKey // 确保导入该函数
} from '@/api/overview/dpzl/map.js';
import { saveConfig, clearConfig, getConfig } from '@/utils/mapConfigStorage.js';

const configFormRef = ref(null);
const sceneList = ref([]);
const sceneFieldList = ref([]);
const previewVisible = ref(false);
const statusMapVisible = ref(false);
const iconSelectVisible = ref(false);
const currentIconType = ref('');
const currentIconRow = ref(null);
const selectedIconUrl = ref('');
const defaultIconFileList = ref([]);
const previewConfigData = ref({});
const previewMapRef = ref(null);

// 表单数据
const formData = reactive({
  area: [],
  sceneKey: '',
  sceneName: '',
  sceneDesc: '',
  selectedFields: [],
  selectedFieldsConfig: [],
  defaultIconSize: { ...MAP_DEFAULT_CONFIG.defaultIconSize },
  defaultIconUrl: MAP_DEFAULT_CONFIG.defaultIconUrl,
  statusIconConfigList: [],
  legendConfig: { ...MAP_DEFAULT_CONFIG.legendConfig },
  mapCenter: { ...MAP_DEFAULT_CONFIG.mapCenter },
  orbitAnimation: { ...MAP_DEFAULT_CONFIG.orbitAnimation }
});

const statusMapForm = reactive({
  rawValue: '',
  displayText: '',
  currentField: null
});

const formRules = ref({
  area: [
    {
      required: true,
      trigger: 'change',
      validator: (rule, value, callback) => {
        if (value && value.length >= 2) {
          callback();
        } else {
          callback(new Error('请选择完整的应用场景（需选到二级分类，如城管住建/市政设施）'));
        }
      }
    }
  ]
});

// 监听状态图标变化更新图例
watch(
  () => formData.statusIconConfigList,
  (newList) => {
    formData.legendConfig.items = [];
    newList.forEach(item => {
      if (item.fieldKey && item.rawValue && item.icon) {
        const fieldConfig = formData.selectedFieldsConfig.find(f => f.key === item.fieldKey);
        const displayText = fieldConfig?.statusMap?.[item.rawValue] || item.rawValue;

        formData.legendConfig.items.push({
          fieldKey: item.fieldKey,
          rawValue: item.rawValue,
          text: displayText,
          icon: item.icon
        });
      }
    });
  },
  { deep: true, immediate: true }
);

// 加载场景列表
onMounted(async () => {
  try {
    const res = await getSceneList();
    sceneList.value = res || [];
  } catch (error) {
    // ElMessage.error(`加载场景列表失败：${error.message}`);
  }
});

// 场景选择变化处理（核心修改：添加级联选择器回显逻辑）
const handleCascaderChange = async () => {
  const cascaderValue = formData.area;
  if (!cascaderValue || cascaderValue.length < 2) {
    // 重置所有配置
    formData.sceneKey = '';
    formData.sceneName = '';
    formData.sceneDesc = '';
    formData.selectedFields = [];
    formData.selectedFieldsConfig = [];
    formData.statusIconConfigList = [];
    formData.legendConfig = { ...MAP_DEFAULT_CONFIG.legendConfig };
    formData.defaultIconUrl = MAP_DEFAULT_CONFIG.defaultIconUrl;
    formData.defaultIconSize = { ...MAP_DEFAULT_CONFIG.defaultIconSize };
    formData.mapCenter = { ...MAP_DEFAULT_CONFIG.mapCenter };
    formData.orbitAnimation = {
      rotateSpeed: 0.2,
      loop: 'infinity',
      pitch: 40,
      zoom: 18,
      centerType: '复用地图中心坐标',
      customCenter: { lat: 26.855237, lng: 118.000000 }
    };
    defaultIconFileList.value = [];
    sceneFieldList.value = [];
    return;
  }

  const sceneKey = getSceneKeyByCascader(cascaderValue);
  let sceneName = '';
  let sceneDesc = '';
  for (const parent of sceneList.value) {
    if (parent.value === cascaderValue[0]) {
      const child = parent.children.find(item => item.value === cascaderValue[1]);
      if (child) {
        sceneName = child.label;
        sceneDesc = child.desc;
        break;
      }
    }
  }

  formData.sceneKey = sceneKey;
  formData.sceneName = `${sceneList.value.find(p => p.value === cascaderValue[0])?.label}-${sceneName}`;
  formData.sceneDesc = sceneDesc;

  try {
    const res = await getSceneFieldList(sceneKey);
    sceneFieldList.value = res || [];
    ElMessage.success(`已加载【${formData.sceneName}】的专属字段`);

    // 加载保存的配置
    const savedConfig = await getConfig(sceneKey, MAP_DEFAULT_CONFIG);
    if (savedConfig) {
      formData.selectedFields = savedConfig.selectedFields || [];
      formData.selectedFieldsConfig = savedConfig.selectedFieldsConfig || [];
      formData.defaultIconSize = savedConfig.defaultIconSize || { ...MAP_DEFAULT_CONFIG.defaultIconSize };
      formData.defaultIconUrl = savedConfig.defaultIconUrl || MAP_DEFAULT_CONFIG.defaultIconUrl;
      formData.statusIconConfigList = savedConfig.statusIconConfigList || [];
      formData.legendConfig = savedConfig.legendConfig || { ...MAP_DEFAULT_CONFIG.legendConfig };
      formData.mapCenter = savedConfig.mapCenter || { ...MAP_DEFAULT_CONFIG.mapCenter };
      // 兼容旧配置，保留loop原始值
      formData.orbitAnimation = savedConfig.orbitAnimation || {
        rotateSpeed: 0.2,
        loop: 'infinity',
        pitch: 40,
        zoom: 18,
        centerType: '复用地图中心坐标',
        customCenter: { lat: 26.855237, lng: 118.000000 }
      };
      if (formData.defaultIconUrl) {
        defaultIconFileList.value = [{
          name: '已保存的默认图标',
          url: formData.defaultIconUrl,
          status: 'success'
        }];
      }
      // ========== 核心修改：添加级联选择器回显逻辑 ==========
      formData.area = getCascaderValueBySceneKey(formData.sceneKey);
      // =====================================================
      ElMessage.info('已加载该场景的本地保存配置');
    }
  } catch (error) {
    formData.mapCenter = { ...MAP_DEFAULT_CONFIG.mapCenter };
    ElMessage.info(`使用默认配置加载【${formData.sceneName}】地图`);
  }

  if (!formData.selectedFieldsConfig.length) {
    formData.selectedFields = [];
    formData.selectedFieldsConfig = [];
    formData.statusIconConfigList = [];
    formData.legendConfig = { ...MAP_DEFAULT_CONFIG.legendConfig };
  }
};

// 字段选择变化
const handleFieldChange = (selectedKeys) => {
  formData.selectedFieldsConfig = selectedKeys.map(key => {
    const field = sceneFieldList.value.find(item => item.key === key);
    return {
      key,
      label: field?.label || key,
      renderType: 'normal',
      statusMap: {}
    };
  });
};

// 渲染类型变化
const handleRenderTypeChange = (config) => {
  if (config.renderType === 'status' && !config.statusMap) {
    config.statusMap = {};
  }
};

// 删除字段配置
const deleteFieldConfig = (index) => {
  formData.selectedFieldsConfig.splice(index, 1);
  formData.selectedFields = formData.selectedFieldsConfig.map(item => item.key);
};

// 打开状态映射配置
const openStatusMapConfig = (fieldConfig) => {
  statusMapForm.currentField = fieldConfig;
  statusMapForm.rawValue = '';
  statusMapForm.displayText = '';
  statusMapVisible.value = true;
};

// 保存状态映射
const saveStatusMapConfig = () => {
  if (!statusMapForm.rawValue || !statusMapForm.displayText) {
    ElMessage.warning('原始状态值和展示文本不能为空');
    return;
  }
  const { currentField } = statusMapForm;
  currentField.statusMap[statusMapForm.rawValue] = statusMapForm.displayText;
  statusMapVisible.value = false;
  ElMessage.success('状态映射配置成功');
};

// 新增状态图标
const addStatusIconConfig = () => {
  formData.statusIconConfigList.push({
    fieldKey: '',
    rawValue: '',
    icon: ''
  });
};

// 删除状态图标
const deleteStatusIconConfig = (index) => {
  formData.statusIconConfigList.splice(index, 1);
};

// 打开图标选择
const openIconSelect = (type, row = null) => {
  currentIconType.value = type;
  currentIconRow.value = row;
  selectedIconUrl.value = '';
  iconSelectVisible.value = true;
};

// 选择图标
const selectIcon = (icon) => {
  selectedIconUrl.value = icon.url;
};

// 确认选择图标
const confirmIconSelect = () => {
  if (!selectedIconUrl.value) return;
  if (currentIconType.value === 'default') {
    defaultIconFileList.value = [{
      name: PRESET_ICON_LIBRARY[currentIconType.value].find(i => i.url === selectedIconUrl.value)?.name || '默认图标',
      url: selectedIconUrl.value,
      status: 'success'
    }];
    formData.defaultIconUrl = selectedIconUrl.value;
  } else if (currentIconRow.value) {
    currentIconRow.value.icon = selectedIconUrl.value;
  }
  iconSelectVisible.value = false;
  ElMessage.success('图标选择成功');
};

// 预览配置（核心：修复loop参数传递）
const previewConfig = () => {
  if (!formData.sceneKey) {
    ElMessage.warning('请先选择应用场景');
    return;
  }
  // 构建预览配置（适配手动动画，保留loop原始值）
  const orbitAnimation = {
    center: formData.orbitAnimation.centerType === '复用地图中心坐标'
      ? {...formData.mapCenter}
      : {...formData.orbitAnimation.customCenter},
    rotateSpeed: formData.orbitAnimation.rotateSpeed || 0.2,
    // 核心修复：不转布尔值，保留原始循环次数（infinity/1/3/5）
    loop: formData.orbitAnimation.loop === 'infinity' ? Infinity : Number(formData.orbitAnimation.loop),
    pitch: formData.orbitAnimation.pitch || 40,
    zoom: formData.orbitAnimation.zoom || 18
  };

  previewConfigData.value = {
    selectedFields: formData.selectedFields,
    selectedFieldsConfig: formData.selectedFieldsConfig,
    defaultIconSize: formData.defaultIconSize,
    defaultIconUrl: formData.defaultIconUrl || MAP_DEFAULT_CONFIG.defaultIconUrl,
    statusIconConfigList: formData.statusIconConfigList,
    legendConfig: formData.legendConfig,
    mapCenter: formData.mapCenter,
    orbitAnimation: orbitAnimation
  };
  previewVisible.value = true;

  // 弹窗打开后初始化，不自动播放（靠按钮触发）
  nextTick(() => {
    if (previewMapRef.value) {
      previewMapRef.value.orbitStatus.playing = false;
    }
  });
};

// 关闭预览
const handlePreviewClose = () => {
  previewConfigData.value = {};
  // 停止预览动画
  if (previewMapRef.value) {
    previewMapRef.value.stopOrbitAnimation();
  }
};

// 保存配置（原handleSaveConfig，命名保持不变，功能完整）
const handleSaveConfig = async () => {
  if (!formData.sceneKey) {
    ElMessage.warning('请先选择应用场景');
    return;
  }
  const configToSave = {
    selectedFields: formData.selectedFields,
    selectedFieldsConfig: formData.selectedFieldsConfig,
    defaultIconSize: formData.defaultIconSize,
    defaultIconUrl: formData.defaultIconUrl || MAP_DEFAULT_CONFIG.defaultIconUrl,
    statusIconConfigList: formData.statusIconConfigList,
    legendConfig: formData.legendConfig,
    mapCenter: formData.mapCenter,
    orbitAnimation: formData.orbitAnimation // 保存原始loop值
  };
  try {
    const success = await saveConfig(formData.sceneKey, configToSave);
    if (success) {
      ElMessage.success('配置保存成功');
    } else {
      // ElMessage.error('配置保存失败');
    }
  } catch (error) {
    ElMessage.error(`保存配置出错：${error.message}`);
  }
};

// 清除配置
const handleClearConfig = async () => {
  try {
    const success = await clearConfig(formData.sceneKey);
    if (success) {
      ElMessage.success('已清除该场景的保存配置');
      formData.selectedFields = [];
      formData.selectedFieldsConfig = [];
      formData.defaultIconSize = {...MAP_DEFAULT_CONFIG.defaultIconSize};
      formData.defaultIconUrl = MAP_DEFAULT_CONFIG.defaultIconUrl;
      formData.statusIconConfigList = [];
      formData.legendConfig = {...MAP_DEFAULT_CONFIG.legendConfig};
      formData.mapCenter = {...MAP_DEFAULT_CONFIG.mapCenter};
      formData.orbitAnimation = { ...MAP_DEFAULT_CONFIG.orbitAnimation };
      defaultIconFileList.value = [];
    } else {
      // ElMessage.error('清除配置失败');
    }
  } catch (error) {
    ElMessage.error(`清除配置出错：${error.message}`);
  }
};

// 重置表单
const resetForm = () => {
  formData.area = [];
  formData.sceneKey = '';
  formData.sceneName = '';
  formData.sceneDesc = '';
  formData.selectedFields = [];
  formData.selectedFieldsConfig = [];
  formData.defaultIconSize = {...MAP_DEFAULT_CONFIG.defaultIconSize};
  formData.defaultIconUrl = MAP_DEFAULT_CONFIG.defaultIconUrl;
  formData.statusIconConfigList = [];
  formData.legendConfig = {...MAP_DEFAULT_CONFIG.legendConfig};
  formData.mapCenter = {...MAP_DEFAULT_CONFIG.mapCenter};
  formData.orbitAnimation = { ...MAP_DEFAULT_CONFIG.orbitAnimation };
  defaultIconFileList.value = [];
  sceneFieldList.value = [];
  ElMessage.info('表单已重置');
};
</script>

<style scoped>
.config-card {
  background: #fff;
  border-radius: 8px;
  padding: 20px;
}

.field-select-group {
  width: 300px;
  padding: 1vw;
  background: #f9f9f9;
  border-radius: 4px;
}

:deep(.field-select-group .el-checkbox-group .el-space) {
  justify-content: flex-start !important;
  align-items: flex-start !important;
  width: 100%;
}

.field-checkbox {
  font-size: 14px;
}

/* 通用样式穿透 */
:deep(.el-form-item__label) {
  font-size: 14px;
}

:deep(.el-cascader), :deep(.el-cascader__placeholder) {
  font-size: 14px;
}

:deep(.el-select), :deep(.el-select__placeholder), :deep(.el-option) {
  font-size: 14px;
}

:deep(.el-input), :deep(.el-input__placeholder), :deep(.el-input-number) {
  font-size: 14px;
}

:deep(.el-button) {
  font-size: 14px;
}

:deep(.el-button--small) {
  font-size: 12px;
}

:deep(.el-table), :deep(.el-table-column__label) {
  font-size: 14px;
}

:deep(.el-dialog__title), :deep(.el-dialog__body), :deep(.el-dialog__footer) {
  font-size: 14px;
}

:deep(.el-divider__text), :deep(.el-switch__label) {
  font-size: 14px;
}

.field-config-group {
  background: #f9f9f9;
  padding: 10px;
  border-radius: 4px;
}

.field-config-item {
  padding: 8px;
  background: #fff;
  border-radius: 4px;
  margin-bottom: 8px;
  border: 1px solid #e6e6e6;
}

.field-label {
  font-size: 14px;
  color: #333;
  font-weight: 500;
}

/* 图标选择样式 */
.icon-select-group {
  margin-bottom: 15px;
}

.selected-icon-preview {
  width: 100px;
  height: 100px;
  border: 1px dashed #e6e6e6;
  border-radius: 4px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background: #f9f9f9;
}

.selected-icon-preview.placeholder {
  color: #999;
  font-size: 12px;
}

.preview-img {
  width: 60px;
  height: 60px;
  object-fit: contain;
  margin-bottom: 5px;
}

.preview-tip {
  font-size: 12px;
  color: #666;
  text-align: center;
}

.config-tip {
  font-size: 12px;
  color: #999;
  margin-left: 10px;
}

.status-icon-select, .legend-icon-select {
  display: flex;
  align-items: center;
  gap: 8px;
}

.icon-preview-small {
  width: 40px;
  height: 40px;
  border: 1px solid #e6e6e6;
  border-radius: 4px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #f9f9f9;
}

.icon-preview-small.placeholder {
  color: #999;
  font-size: 12px;
}

.preview-img-small {
  width: 30px;
  height: 30px;
  object-fit: contain;
}

.icon-library-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(100px, 1fr));
  gap: 15px;
  margin-top: 10px;
  max-height: 300px;
  overflow-y: auto;
  padding: 10px 0;
}

.icon-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 10px;
  border: 2px solid #e6e6e6;
  border-radius: 6px;
  cursor: pointer;
  transition: all 0.2s ease;
  background: #f9f9f9;
}

.icon-item:hover {
  border-color: #c0c4cc;
  background: #f0f0f0;
}

.icon-item.active {
  border-color: #409eff;
  background-color: #f0f7ff;
}

.icon-item-img {
  width: 60px;
  height: 60px;
  object-fit: contain;
  margin-bottom: 8px;
}

.icon-item-name {
  font-size: 12px;
  color: #666;
  text-align: center;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  width: 100%;
}

/* 图例预览 */
.legend-preview-group {
  display: flex;
  flex-wrap: wrap;
  gap: 0.8vw;
  padding: 0.8vw;
  background: #f9f9f9;
  border-radius: 4px;
}

.legend-preview-item {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 5px 10px;
  background: #fff;
  border-radius: 4px;
  border: 1px solid #e6e6e6;
  font-size: 14px;
}

/* 弹窗样式 + 预览容器防溢出 */
:deep(.el-dialog) {
  border-radius: 8px;
}

:deep(.el-dialog__header) {
  border-bottom: 1px solid #e6e6e6;
  padding-bottom: 10px;
}

:deep(.el-tabs--card) {
  --el-tabs-card-border-color: #e6e6e6;
}

:deep(.el-tabs__item) {
  font-size: 14px;
}

/* 预览弹窗地图容器强制不溢出 */
:deep(.el-dialog__body) {
  padding: 20px !important;
}

:deep(#preview-map) {
  width: 100% !important;
  height: 100% !important;
  overflow: hidden !important;
  position: relative !important;
}
</style>
