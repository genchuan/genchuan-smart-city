<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible" width="700px">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="160px"
      v-loading="formLoading"
      class="form-container"
    >
      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="父级分类名称" prop="parentCatName">
            <el-tree-select
              v-model="selectedParentId"
              :data="treeData"
              :props="treeProps"
              filterable

              placeholder="请选择父级分类"
              style="width: 100%"
              :disabled="formType.value === 'update' && formData.catLevel === '3'"
              value-key="id"
              :key="`tree-select-${JSON.stringify(treeData)}`"
              :click-node-to-select="true"
              :check-strictly="true"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="应用场景分类代码" prop="sceneCatCode">
            <el-input v-model="formData.sceneCatCode" placeholder="请输入应用场景分类代码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="应用场景分类名称" prop="sceneCatName">
            <el-input v-model="formData.sceneCatName" placeholder="请输入应用场景分类名称" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <!--        <el-col :span="12">-->
        <!--          <el-form-item label="是否扩展类" prop="isExt">-->
        <!--            <el-select v-model="formData.isExt" placeholder="请选择是否扩展类">-->
        <!--              <el-option label="标准类" value="0" />-->
        <!--              <el-option label="扩展类" value="1" />-->
        <!--            </el-select>-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <el-col :span="12">
          <el-form-item label="启用状态" prop="enableStatus">
            <el-radio-group v-model="formData.enableStatus">
              <el-radio value="1">启用</el-radio>
              <el-radio value="0">禁用</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="菜单配置" prop="menuIds" class="menu-config-item">
            <el-card class="w-full min-h-[200px] max-h-[600px] !overflow-y-auto" shadow="never">
              <template #header>
                全选/全不选:
                <el-switch
                  v-model="menuAllChecked"
                  active-text="是"
                  inactive-text="否"
                  inline-prompt
                  @change="handleMenuAllChecked"
                />
                全部展开/折叠:
                <el-switch
                  v-model="menuExpand"
                  active-text="展开"
                  inactive-text="折叠"
                  inline-prompt
                  @change="handleMenuExpand"
                />
              </template>
              <el-tree
                ref="menuTreeRef"
                :data="menuOptions"
                :props="defaultMenuProps"
                empty-text="暂无菜单数据"
                node-key="id"
                show-checkbox
                class="menu-tree"
              />
            </el-card>
          </el-form-item>
        </el-col>
      </el-row>
      <!--      <el-row :gutter="20">-->
      <!--        <el-col :span="12">-->
      <!--          <el-form-item label="创建人(业务)" prop="createUserBiz">-->
      <!--            <el-input v-model="formData.createUserBiz" placeholder="请输入创建人(业务)" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->
      <!--        <el-col :span="12">-->
      <!--          <el-form-item label="创建时间(业务)" prop="createTimeBiz">-->
      <!--            <el-date-picker-->
      <!--              v-model="formData.createTimeBiz"-->
      <!--              type="date"-->
      <!--              value-format="x"-->
      <!--              placeholder="选择创建时间(业务)"-->
      <!--            />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->
      <!--      </el-row>-->
      <!--      <el-row :gutter="20">-->
      <!--        <el-col :span="12">-->
      <!--          <el-form-item label="更新人(业务)" prop="updateUserBiz">-->
      <!--            <el-input v-model="formData.updateUserBiz" placeholder="请输入更新人(业务)" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->
      <!--        <el-col :span="12">-->
      <!--          <el-form-item label="更新时间(业务)" prop="updateTimeBiz">-->
      <!--            <el-date-picker-->
      <!--              v-model="formData.updateTimeBiz"-->
      <!--              type="date"-->
      <!--              value-format="x"-->
      <!--              placeholder="选择更新时间(业务)"-->
      <!--            />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->
      <!--      </el-row>-->
      <el-form-item label="应用场景分类说明" prop="sceneCatDesc">
        <el-input
          v-model="formData.sceneCatDesc"
          placeholder="请输入应用场景分类说明"
          type="textarea"
          rows="3"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { AppSceneCategoryApi, AppSceneCategoryVO } from '@/api/dataHub/sceneCategory/appCateInfo'
import { nextTick } from 'vue'
// import { usePermissionStore } from '@/store/modules/permission'
import { getInfo } from '@/api/login' // 导入获取用户权限信息接口
defineOptions({ name: 'AppSceneCategoryForm' })
const { t } = useI18n()
const message = useMessage()
const dialogVisible = ref(false)
const dialogTitle = ref('')
const formLoading = ref(false)
const formType = ref('')
const formRef = ref()

// 根节点代号（用于处理 parentCatId=null 的情况）
const ROOT_ID = 'root'
// TreeSelect 的 v-model
const selectedParentId = ref<string | null>(ROOT_ID)
const treeData = ref<any[]>([])
const treeProps = reactive({
  label: 'sceneCatName',
  value: 'id',
  children: 'children',
  disabled: 'disabled'
})
// 菜单配置相关响应式变量
const menuTreeRef = ref()
const menuOptions = ref<any[]>([]) // 菜单树形数据（仅数据中枢及其子菜单）
const menuAllChecked = ref(false) // 菜单全选状态
const menuExpand = ref(false) // 菜单展开/折叠状态
const defaultMenuProps = reactive({ // 菜单树形配置
  label: 'name',
  value: 'id',
  children: 'children'
})
/** 统一树节点 ID 为字符串 */
const normalizeTreeIds = (nodes: any[]) => {
  nodes.forEach((n) => {
    n.id = n.id === null ? ROOT_ID : String(n.id)
    if (n.children?.length) normalizeTreeIds(n.children)
  })
}
const formData = ref<Partial<AppSceneCategoryVO>>({
  id: undefined,
  sceneCatCode: '',
  sceneCatName: '',
  catLevel: undefined,
  parentCatId: null,
  parentCatName: '',
  sceneCatDesc: '',
  isExt: '0',
  enableStatus: '1',
  menuIds: '', // 存储格式：逗号分隔的菜单ID字符串
  createUserBiz: '',
  createTimeBiz: '',
  updateUserBiz: '',
  updateTimeBiz: ''
})
const formRules = reactive({
  sceneCatCode: [{ required: true, message: '应用场景分类代码不能为空', trigger: 'blur' }],
  sceneCatName: [{ required: true, message: '应用场景分类名称不能为空', trigger: 'blur' }],
  parentCatName:[{ required: true, message: '父级分类名称不能为空', trigger: 'change' }]
})
/** 加载树数据 */
const fetchTreeData = async () => {
  formLoading.value = true
  try {
    const res = await AppSceneCategoryApi.getAppSceneCategoryTree()
    const rootNode = {
      id: ROOT_ID,
      sceneCatCode: '',
      sceneCatName: '主场景',
      catLevel: '0',
      disabled: false,
      children: res
    }
    // 禁用 catLevel=3
    const markDisable = (n: any) => {
      if (n.catLevel === '3') n.disabled = true
      n.children?.forEach(markDisable)
    }
    markDisable(rootNode)
    normalizeTreeIds([rootNode])
    treeData.value = [rootNode]
  } catch {
    message.error('获取树形数据失败')
    treeData.value = []
  } finally {
    formLoading.value = false
  }
}
/** 加载菜单数据（仅保留数据中枢及其子菜单） */
const fetchMenuData = async () => {
  try {
    const  permissionInfo  = await getInfo()
    const menus = permissionInfo?.menus || []
    // 过滤数据中枢菜单（通过名称和ID双重匹配，确保准确性）
    const dataHubMenu = menus.find(menu => menu.name === '数据中枢' && menu.id === 5200)
    console.log("dataHubMenu")
    console.log(dataHubMenu)
    menuOptions.value = dataHubMenu ? [dataHubMenu] : []
  } catch (error) {
    message.error('获取菜单数据失败')
    menuOptions.value = []
  }
}


/** 查找树节点 */
const findNode = (node: any, id: string | null): any => {
  if (!id && node.id === ROOT_ID) return node
  if (node.id === id) return node
  for (const child of node.children ?? []) {
    const r = findNode(child, id)
    if (r) return r
  }
  return null
}
/** 自动填充分类代码（新增/编辑时均触发，供用户补全） */
const autoFillSceneCatCode = (node: any) => {
  if (node.id === ROOT_ID) {
    formData.value.sceneCatCode = ''
  } else {
    // 编辑模式保留原逻辑：仅填充父级代码，不覆盖用户已输入的完整代码
    formData.value.sceneCatCode = formData.value.sceneCatCode.startsWith(node.sceneCatCode)
      ? formData.value.sceneCatCode
      : node.sceneCatCode
  }
}
/** 父级切换 */
const handleParentChange = (parentId: string | null) => {
  if (!treeData.value.length) return
  const root = treeData.value[0]
  const node = findNode(root, parentId)
  if (!node) return
  formData.value.parentCatId = node.id === ROOT_ID ? null : node.id
  formData.value.parentCatName = node.sceneCatName
  formData.value.catLevel = String(Number(node.catLevel) + 1)
  autoFillSceneCatCode(node)
}
/** 菜单全选/全不选处理 */
const handleMenuAllChecked = () => {
  if (!menuTreeRef.value) return
  menuTreeRef.value.setCheckedNodes(menuAllChecked.value ? menuOptions.value : [])
}
/** 菜单全部展开/折叠处理 */
const handleMenuExpand = () => {
  if (!menuTreeRef.value) return
  const nodes = menuTreeRef.value.store.nodesMap
  for (const nodeKey in nodes) {
    nodes[nodeKey].expanded = menuExpand.value
  }
}
/** 递归获取所有菜单ID（用于校验全选状态） */
const getAllMenuIds = (menus: any[]): number[] => {
  let ids: number[] = []
  menus.forEach(menu => {
    ids.push(menu.id)
    if (menu.children && menu.children.length) {
      ids = [...ids, ...getAllMenuIds(menu.children)]
    }
  })
  return ids
}
watch(selectedParentId, (nv) => handleParentChange(nv))
/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()
  await fetchTreeData()
  await fetchMenuData() // 加载菜单数据
  if (id) {
    formLoading.value = true
    try {
      const detail = await AppSceneCategoryApi.getAppSceneCategory(id)
      formData.value = { ...detail }
      const parentId = detail.parentCatId == null ? ROOT_ID : String(detail.parentCatId)
      await nextTick()
      await nextTick()
      selectedParentId.value = parentId
      // 回显菜单选中状态（将字符串转为数组）
      const menuIdsArr = detail.menuIds ? detail.menuIds.split(',').map(Number) : []
      await nextTick()
      menuIdsArr.forEach(menuId => {
        menuTreeRef.value?.setChecked(menuId, true, false)
      })
      // 更新全选状态
      const allMenuIds = getAllMenuIds(menuOptions.value)
      menuAllChecked.value = menuIdsArr.length === allMenuIds.length && allMenuIds.length > 0
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open })
/** 提交 */
// 扩展emit事件定义，添加refreshTree 便于刷新表格左侧树形结构
const emit = defineEmits(['success', 'refreshTree'])
const submitForm = async () => {
  await formRef.value.validate()
  formLoading.value = true
  try {
    const submitData = { ...formData.value } as AppSceneCategoryVO
    submitData.parentCatId = selectedParentId.value === ROOT_ID ? null : selectedParentId.value

    /**  修复关键点：提交时自动补齐所有父级菜单ID，但不包含 5200 **/
    const checkedKeys = menuTreeRef.value?.getCheckedKeys(false) || []
    const halfCheckedKeys = menuTreeRef.value?.getHalfCheckedKeys() || []

    let allSelected = new Set([...checkedKeys, ...halfCheckedKeys])

    const appendParents = (id: number) => {
      let node = menuTreeRef.value.getNode(id)
      while (node && node.parent) {
        const pid = node.parent.data?.id
        if (pid && pid !== 5200) {
          allSelected.add(pid)
        }
        node = node.parent
      }
    }

    checkedKeys.forEach(id => appendParents(id))

    submitData.menuIds = Array.from(allSelected).join(',')

    /** 父级分类为“主场景” 且由于打开表单默认选中 用户未切换树形结构 需填充其层级 **/
    if (!submitData.parentCatId) {
      submitData.catLevel = '1'
    }
    if (formType.value === 'create') {
      console.log('create:', submitData)
      await AppSceneCategoryApi.createAppSceneCategory(submitData)
    } else {
      console.log('update:', submitData)
      await AppSceneCategoryApi.updateAppSceneCategory(submitData)
    }
    dialogVisible.value = false

    emit('success')
    emit('refreshTree')

  } finally {
    formLoading.value = false
  }
}

/** 重置 */
const resetForm = () => {
  formData.value = {
    sceneCatCode: '',
    sceneCatName: '',
    parentCatId: null,
    parentCatName: '',
    catLevel: '',
    sceneCatDesc: '',
    isExt: '0',
    enableStatus: '1',
    menuIds: ''
  }
  selectedParentId.value = ROOT_ID
  formRef.value?.resetFields()
  // 重置菜单相关状态
  menuAllChecked.value = false
  menuExpand.value = false
  menuTreeRef.value?.setCheckedNodes([])
}
</script>
<style scoped>
.form-container {
  padding: 10px 0;
}
.el-row {
  margin-bottom: 15px;
}
::v-deep .el-form-item__label {
  font-weight: 500;
}
::v-deep .el-textarea__inner {
  resize: vertical;
}
/* 菜单树形卡片样式优化 */
::v-deep .el-card__header {
  padding: 12px 16px;
  border-bottom: 1px solid #f0f0f0;
  display: flex;
  align-items: center;
  gap: 20px;
  flex-wrap: wrap;
}
::v-deep .menu-config-item .el-form-item__content {
  margin-left: 0 !important;
}
::v-deep .menu-tree {
  padding: 16px;
  min-height: 150px;
}
/* 优化滚动条样式 */
::v-deep .max-h-\[600px\]::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}
::v-deep .max-h-\[600px\]::-webkit-scrollbar-thumb {
  background-color: #e5e7eb;
  border-radius: 3px;
}
::v-deep .max-h-\[600px\]::-webkit-scrollbar-track {
  background-color: #f9fafb;
}
/* 空数据状态样式优化 */
::v-deep .el-tree__empty-text {
  padding: 40px 0;
  color: #9ca3af;
}
</style>
