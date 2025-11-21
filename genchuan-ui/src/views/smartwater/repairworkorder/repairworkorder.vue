<template>
  <div class="cc-main">
    <div class="cc-main-content">
      <!--title-->
      <div class="cc-title-css">
        <div class="left">
          <el-icon class="left-icon">
            <Tickets />
          </el-icon>
          <!--标题名称-->
          <span>{{ titleConfig.name }}</span></div
        >
        <!--右边的按钮-->
        <div class="right">
          <el-button
            type="primary"
            plain
            :icon="Plus"
            @click="addClick(titleConfig.addButName)"
            v-hasPermi="['smartwater:repairworkorder:create']"
          >
            {{ titleConfig.addButName }}</el-button
          >
        </div>
      </div>
      <!--搜索内容字段-->
      <div class="cc-search-css" ref="searchRef">
        <el-form
          class="-mb-15px"
          :model="searchForm"
          ref="searchFormRef"
          :inline="true"
          label-width="68px"
        >
          <!--@keyup.enter="handleQuery"-->
          <el-form-item label="工单ID" prop="name">
            <el-input
              v-model="searchForm.name"
              placeholder="请输入工单ID"
              clearable
              class="!w-240px"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="searchClick">
              <Icon icon="ep:search" class="mr-5px" />
              搜索
            </el-button>
            <el-button @click="searchResetClick">
              <Icon icon="ep:refresh" class="mr-5px" />
              重置
            </el-button>
            <!--表格字段是否显示-->
            <el-dropdown trigger="click" :hide-on-click="false" style="padding-left: 12px">
              <el-button circle :icon="Menu" />
              <template #dropdown>
                <el-dropdown-menu>
                  <template v-for="item in tableColumns" :key="item.visible">
                    <el-dropdown-item>
                      <el-checkbox v-model="item.visible" :label="item.label" />
                    </el-dropdown-item>
                  </template>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </el-form-item>
        </el-form>
      </div>
      <!--table-->
      <div class="cc-table-css">
        <el-table
          ref="tableRef"
          :data="tableData"
          border
          style="width: 100%"
          :header-cell-style="getHeaderCellStyle"
          v-loading="tableConfig.loading"
          :max-height="tableConfig.height"
        >
          <el-table-column label="序号" type="index" width="55" align="center" fixed="left" />
          <el-table-column
            label="工单ID"
            min-width="150"
            align="center"
            prop="name"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('工单ID')"
          />
          <el-table-column
            label="设施编码"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('设施编码')"
          >
            <template #default="scope">
              {{ tableFieldShow('设施编码', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="缺陷类型"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('缺陷类型')"
          >
            <template #default="scope">
              {{ tableFieldShow('缺陷类型', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="维修方案"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('维修方案')"
          >
            <template #default="scope">
              {{ tableFieldShow('维修方案', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="施工负责人"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('施工负责人')"
          >
            <template #default="scope">
              {{ tableFieldShow('施工负责人', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="验收结果"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('验收结果')"
          >
            <template #default="scope">
              {{ tableFieldShow('验收结果', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="验收照片"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('验收照片')"
          >
            <template #default="scope">
              {{ tableFieldShow('验收照片', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="验收人"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('验收人')"
          >
            <template #default="scope">
              {{ tableFieldShow('验收人', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="维修开始时间"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('维修开始时间')"
          >
            <template #default="scope">
              {{ dateFormatter(null, null, tableFieldShow('维修开始时间', scope.row.field)) }}
            </template>
          </el-table-column>
          <el-table-column
            label="维修结束时间"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('维修结束时间')"
          >
            <template #default="scope">
              {{ dateFormatter(null, null, tableFieldShow('维修结束时间', scope.row.field)) }}
            </template>
          </el-table-column>
          <el-table-column
            label="验收时间"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('验收时间')"
          >
            <template #default="scope">
              {{ dateFormatter(null, null, tableFieldShow('验收时间', scope.row.field)) }}
            </template>
          </el-table-column>

          <el-table-column width="300" fixed="right" label="操作" align="center">
            <template #default="scope">
              <el-button
                type="success"
                :icon="View"
                :plain="true"
                @click="queryClick(scope.row)"
                v-hasPermi="['smartwater:repairworkorder:query']"
                >详细</el-button
              >
              <el-button
                type="primary"
                :icon="EditPen"
                :plain="true"
                @click="editClick(scope.row)"
                v-hasPermi="['smartwater:repairworkorder:update']"
                >编辑</el-button
              >
              <el-button
                type="danger"
                :icon="Delete"
                :plain="true"
                @click="delClick(scope.row)"
                v-hasPermi="['smartwater:repairworkorder:delete']"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
        <!--分页-->
        <div class="cc-table-css-page">
          <el-pagination
            @size-change="pageSizeChange"
            @current-change="pageCurrentChange"
            :page-size="tableConfig.pageSize"
            :page-sizes="[10, 20, 50, 100, 500, 1000]"
            layout="total,sizes, prev, pager, next, jumper"
            :current-page="tableConfig.currentPage"
            :total="tableConfig.total"
          />
        </div>
      </div>
      <!--抽屉-->
      <el-drawer
        :title="drawerConfig.title"
        v-model="drawerConfig.isDrawer"
        direction="rtl"
        :append-to-body="true"
        class="cc-drawer-css"
        size="1200px"
      >
        <!--头部配置-->
        <template header>
          <span>{{ drawerConfig.title }}</span>
        </template>

        <div class="drawer-content">
          <!--tabs选项-->
          <el-tabs v-model="drawerConfig.active" class="content-top-tabs" @tab-click="tabsClick">
            <el-tab-pane label="基础信息" name="1" v-if="drawerConfig.type == 'add'" />
            <el-tab-pane
              v-else
              v-for="item in drawerConfig.tabsList"
              :key="item.value"
              :label="item.label"
              :name="item.value"
            />
          </el-tabs>

          <!--查看 li添加class width100 width50 width33 就可以一列 二列 三列-->
          <div
            class="see-content"
            v-loading="drawerConfig.loading"
            v-if="drawerConfig.type === 'query'"
          >
            <ul>
              <li class="width50"><span>工单ID：</span>{{ queryData.name }}</li>
              <li class="width50"
                ><span>设施编码：</span>{{ tableFieldShow('设施编码', queryData.field) }}</li
              >
              <li class="width50"
                ><span>缺陷类型：</span>{{ tableFieldShow('缺陷类型', queryData.field) }}</li
              >
              <li class="width50"
                ><span>维修方案：</span>{{ tableFieldShow('维修方案', queryData.field) }}</li
              >
              <li class="width50"
                ><span>施工负责人：</span>{{ tableFieldShow('施工负责人', queryData.field) }}</li
              >
              <li class="width50"
                ><span>验收结果：</span>{{ tableFieldShow('验收结果', queryData.field) }}</li
              >
              <li class="width50"
                ><span>验收照片：</span>{{ tableFieldShow('验收照片', queryData.field) }}</li
              >
              <li class="width50"
                ><span>验收人：</span>{{ tableFieldShow('验收人', queryData.field) }}</li
              >
              <li class="width50"
                ><span>维修开始时间：</span
                >{{ dateFormatter(null, null, tableFieldShow('维修开始时间', queryData.field)) }}</li
              >
              <li class="width50"
                ><span>维修结束时间：</span
                >{{ dateFormatter(null, null, tableFieldShow('维修结束时间', queryData.field)) }}</li
              >
              <li class="width50"
                ><span>验收时间：</span
                >{{ dateFormatter(null, null, tableFieldShow('验收时间', queryData.field)) }}</li
              >
            </ul>
          </div>

          <!--编辑 edit-content-form-li 里面添加 width100 width50 width33 就可以一列 二列 三列-->
          <div class="edit-content" v-loading="drawerConfig.loading" v-else>
            <el-form
              :model="formData"
              ref="drawerFromRef"
              class="edit-content-form"
              label-width="140px"
            >
              <el-form-item
                label="工单ID："
                prop="name"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入工单ID', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入工单ID" v-model="formData.name" clearable />
              </el-form-item>
              <el-form-item
                label="设施编码："
                prop="field.设施编码"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入设施编码', trigger: 'blur' }]"
              >
                <el-input
                  placeholder="请输入设施编码"
                  v-model="formData.field.设施编码"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="缺陷类型："
                prop="field.缺陷类型"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入缺陷类型', trigger: 'blur' }]"
              >
                <el-input
                  placeholder="请输入缺陷类型"
                  v-model="formData.field.缺陷类型"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="维修方案："
                prop="field.维修方案"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入维修方案', trigger: 'blur' }]"
              >
                <el-input
                  placeholder="请输入维修方案"
                  v-model="formData.field.维修方案"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="施工负责人："
                prop="field.施工负责人"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入施工负责人', trigger: 'blur' }]"
              >
                <el-input
                  placeholder="请输入施工负责人"
                  v-model="formData.field.施工负责人"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="验收结果："
                prop="field.验收结果"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入验收结果', trigger: 'blur' }]"
              >
                <el-input
                  placeholder="请输入验收结果"
                  v-model="formData.field.验收结果"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="验收照片："
                prop="field.验收照片"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入验收照片', trigger: 'blur' }]"
              >
                <el-input
                  placeholder="请输入验收照片"
                  v-model="formData.field.验收照片"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="验收人："
                prop="field.验收人"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入验收人', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入验收人" v-model="formData.field.验收人" clearable />
              </el-form-item>
              <el-form-item
                label="维修开始时间："
                prop="field.维修开始时间"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入维修开始时间', trigger: 'blur' }]"
              >
                <el-date-picker
                  v-model="formData.field.维修开始时间"
                  type="datetime"
                  value-format="x"
                  placeholder="选择维修开始时间"
                />
              </el-form-item>
              <el-form-item
                label="维修结束时间："
                prop="field.维修结束时间"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入维修结束时间', trigger: 'blur' }]"
              >
                <el-date-picker
                  v-model="formData.field.维修结束时间"
                  type="datetime"
                  value-format="x"
                  placeholder="选择维修结束时间"
                />
              </el-form-item>
              <el-form-item
                label="验收时间："
                prop="field.验收时间"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入验收时间', trigger: 'blur' }]"
              >
                <el-date-picker
                  v-model="formData.field.验收时间"
                  type="datetime"
                  value-format="x"
                  placeholder="选择验收时间"
                />
              </el-form-item>
            </el-form>
          </div>
        </div>

        <!--新增的时候-->
        <div
          class="action-button"
          v-if="['add', 'edit'].includes(drawerConfig.type) && !drawerConfig.loading"
        >
          <el-button :icon="Close" @click="drawerConfig.isDrawer = false">取 消</el-button>
          <el-button type="primary" :icon="Check" @click="drawerSubmitForm(drawerFromRef)"
            >保 存</el-button
          >
        </div>
      </el-drawer>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from 'vue'
import { dateFormatter } from '@/utils/formatTime'
import { Tickets, Plus, EditPen, Delete, View, Menu, Close, Check } from '@element-plus/icons-vue'
import { handleTypeOptions, tableFieldShow } from '@/utils/thingsBoardUtils'
import {
  assetsDel,
  assetsEdit,
  assetsEditField,
  assetsList,
  assetsListField
} from '@/api/genchuan/thingsBoard/assets'
//table 初始化
const tableInitData = async () => {
  tableConfig.value.loading = true
  try {
    let params = {
      pageSize: tableConfig.value.pageSize,
      page: tableConfig.value.currentPage - 1,
      textSearch: searchForm.value.name,
      sortProperty: 'createdTime',
      sortOrder: 'DESC',
      assetProfileId: '0951b6c0-799a-11f0-bd39-2dc5ccdb39ef' //资产配置id
    }
    let res = await assetsList(params)
    tableData.value = res.data
    for (let i = 0; i < tableData.value.length; i++) {
      let params = { id: tableData.value[i].id.id }
      let resField = await assetsListField(params)
      tableData.value[i].field = resField
    }
    tableConfig.value.total = res.totalElements
    tableConfig.value.loading = false
  } catch (error) {
    tableConfig.value.loading = false
    // 处理错误
    console.log('初始化出错了')
  }
}

// 表单字段
const initFormData = {
  name: '', //资产名称 这个用一个字段区代表就行了
  type: '修复工单管理', //资产配置，都是写死固定的资产配置
  field: {
    设施编码: '', //设施编码
    缺陷类型: '', //缺陷类型
    维修方案: '', //维修方案
    施工负责人: '', //施工负责人
    验收结果: '', //验收结果
    验收照片: '', //验收照片
    验收人: '', //验收人
    维修开始时间: '', //维修开始时间
    维修结束时间: '', //维修结束时间
    验收时间: '' //验收时间
  }
}

// 所有选项集合
const optionsLisi = ref({})

// 表单初始化数据
const formData = ref({ ...initFormData })

//表单保存
const drawerFromRef = ref<FormInstance>()
const drawerSubmitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  const valid = await new Promise<boolean>((resolve) => {
    formEl.validate((valid) => {
      resolve(valid)
    })
  })
  if (valid) {
    try {
      let res = await assetsEdit(formData.value)
      await assetsEditField(res.id.id, formData.value.field)
      if (drawerConfig.value.type === 'add') {
        tableConfig.value.currentPage = 1
      }
      drawerConfig.value.isDrawer = false
      ElMessage.success('保存成功')
      await tableInitData()
    } catch (error) {
      if (drawerConfig.value.type === 'add') {
        ElMessage.error('保存失败，数据重复')
      }
      console.log('新增出错!', error)
    }
  } else {
    console.log('表单验证出错!')
    // ElMessage.warning('请完善表单信息');
  }
}

// 点击详细按钮
const queryData = ref({})
const queryClick = async (row) => {
  queryData.value = row
  console.log(row)
  drawerConfig.value.isDrawer = true
  drawerConfig.value.loading = false
  drawerConfig.value.type = 'query'
  drawerConfig.value.title = '修复工单管理-详细信息'
}
// 点击编辑按钮
const editClick = async (row) => {
  let copyRow = JSON.parse(JSON.stringify(row))
  formData.value = copyRow
  formData.value.field = {}
  formData.value.field.设施编码 = tableFieldShow('设施编码', row.field) //设施编码
  formData.value.field.缺陷类型 = tableFieldShow('缺陷类型', row.field) //缺陷类型
  formData.value.field.维修方案 = tableFieldShow('维修方案', row.field) //维修方案
  formData.value.field.施工负责人 = tableFieldShow('施工负责人', row.field) //施工负责人
  formData.value.field.验收结果 = tableFieldShow('验收结果', row.field) //验收结果
  formData.value.field.验收照片 = tableFieldShow('验收照片', row.field) //验收照片
  formData.value.field.验收人 = tableFieldShow('验收人', row.field) //验收人
  formData.value.field.维修开始时间 = tableFieldShow('维修开始时间', row.field) //维修开始时间
  formData.value.field.维修结束时间 = tableFieldShow('维修结束时间', row.field) //维修结束时间
  formData.value.field.验收时间 = tableFieldShow('验收时间', row.field) //验收时间

  drawerConfig.value.isDrawer = true
  drawerConfig.value.loading = false
  drawerConfig.value.type = 'edit'
  drawerConfig.value.title = '编辑修复工单管理'
}
// 点击删除按钮
const delClick = (row) => {
  ElMessageBox.confirm(
    '请注意：确认后，所有相关数据将不可恢复。',
    `确定要删除 '${row.name}' 吗？`,
    {
      confirmButtonText: '确 定',
      cancelButtonText: '取 消',
      type: 'warning'
    }
  )
    .then(async () => {
      try {
        await assetsDel(row.id.id)
        await tableInitData()
        ElMessage({
          type: 'success',
          message: '删除成功'
        })
      } catch (error) {
        // 处理错误
        console.log(error)
      }
    })
    .catch(() => {
      console.log('取消删除')
    })
}
// title点击新增
const addClick = async () => {
  formData.value = {
    ...initFormData,
    field: { ...initFormData.field }
  }
  drawerConfig.value.isDrawer = true
  drawerConfig.value.loading = false
  drawerConfig.value.type = 'add'
  drawerConfig.value.title = '新增修复工单管理'
}

//drawer 配置
const drawerConfig = ref({
  isDrawer: false, //是否打开抽屉 true 打开 false 关闭
  loading: true, //还没加载完成 true 在加载 false加载完成
  title: '抽屉标题', //标题名称
  type: 'add', //add新增 edit编辑 query详细
  active: '1', //当前选择了哪个
  // tabs轮动
  tabsList: [{ label: '基础信息', name: '1' }]
})
//drawer tabs 点击切换
const tabsClick = (tab) => {
  tableConfig.value.currentPage = 1 //当前第几页
  tableInitData()
  console.log('tabs点击切换', tab.props.name)
}
// title配置
const titleConfig = ref({
  name: '修复工单管理', //标题名称
  addButName: '新增修复工单管理' //新增按钮名称
})
// 搜索 字段
const initSearchForm = {
  name: '' //配置名称
}
// 搜索初始化数据
const searchForm = ref({ ...initSearchForm })
// 搜索 点击
const searchClick = () => {
  tableConfig.value.currentPage = 1
  tableInitData()
}
//搜索 重置
const searchResetClick = () => {
  searchForm.value = { ...initSearchForm }
  tableConfig.value.currentPage = 1
  tableInitData()
}
//table 列显隐信息
const tableColumns = ref([
  { label: '工单ID', visible: true },
  { label: '设施编码', visible: true },
  { label: '缺陷类型', visible: true },
  { label: '维修方案', visible: true },
  { label: '施工负责人', visible: true },
  { label: '验收结果', visible: true },
  { label: '验收照片', visible: true },
  { label: '验收人', visible: true },
  { label: '维修开始时间', visible: true },
  { label: '维修结束时间', visible: true },
  { label: '验收时间', visible: true }
])
// table表格数据
const tableData = ref([])
//table 配置
const tableConfig = ref({
  height: 0, //表格高度
  loading: true, //是否加载完成
  currentPage: 1, //当前第几页
  pageSize: 10, //每页几条
  total: 0 //总数
})
//table 列是否显示
const tableColumnShow = (label) => {
  for (let i = 0; i < tableColumns.value.length; i++) {
    if (tableColumns.value[i].label === label) {
      return tableColumns.value[i].visible
    }
  }
}
//table 设置高度
const searchRef = ref(null)
const getTableHeight = () => {
  setTimeout(() => {
    if (searchRef.value) {
      const searchHeight = searchRef.value.clientHeight
      let height = document.documentElement.clientHeight - searchHeight - 52 - 107 - 70
      tableConfig.value.height = height
    }
  }, 300)
}
//table 给表头加样式
const getHeaderCellStyle = () => {
  return {
    background: '#f5f7fa',
    color: '#081333',
    fontWeight: 'normal'
  }
}
//table 分页 选择了每页几条
const pageSizeChange = (val) => {
  tableConfig.value.currentPage = 1 //当前第几页
  tableConfig.value.pageSize = val //每页几条
  tableInitData()
}
//table 分页 点击了跳转到第几页
const pageCurrentChange = (val) => {
  tableConfig.value.currentPage = val //当前第几页
  tableInitData()
}

// 定义要添加到 body 的 class 名称
const customClass = 'thingsBoard-body-class'
const addClassToBody = () => {
  document.body.classList.add(customClass)
}
const removeClassFromBody = () => {
  document.body.classList.remove(customClass)
}

// 挂载完毕
onMounted(() => {
  console.log('挂载完毕2')
  addClassToBody() //给body加class控制样式
  getTableHeight() //给table获取高度
  tableInitData() //数据初始化
})
// 在组件卸载时移除 class
onUnmounted(() => {
  removeClassFromBody()
})
</script>
<style lang="scss" scoped></style>
