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
            v-hasPermi="['smartwater:rainfallmonitoring:create']"
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
          <el-form-item label="雨量站标识码" prop="name" label-width="120px">
            <el-input
              v-model="searchForm.name"
              placeholder="请输入雨量站标识码"
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
            label="雨量站标识码"
            min-width="150"
            align="center"
            prop="name"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('雨量站标识码')"
          />
          <el-table-column
            label="监测时间"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('监测时间')"
          >
            <template #default="scope">
              {{ dateFormatter(null, null, tableFieldShow('监测时间', scope.row.field)) }}
            </template>
          </el-table-column>
          <el-table-column
            label="瞬时雨量"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('瞬时雨量')"
          >
            <template #default="scope">
              {{ tableFieldShow('瞬时雨量', scope.row.field) }} 毫米每小时(mm/h)
            </template>
          </el-table-column>
          <el-table-column
            label="累计流量"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('累计流量')"
          >
            <template #default="scope">
              {{ tableFieldShow('累计流量', scope.row.field) }} 毫米(mm)
            </template>
          </el-table-column>
          <el-table-column
            label="降雨等级"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('降雨等级')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('降雨等级', scope.row.field),
                  optionsLisi.降雨等级Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="累积量清零周期"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('累积量清零周期')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('累积量清零周期', scope.row.field),
                  optionsLisi.累积量清零周期Options
                )
              }}
            </template>
          </el-table-column>

          <el-table-column width="300" fixed="right" label="操作" align="center">
            <template #default="scope">
              <el-button
                type="success"
                :icon="View"
                :plain="true"
                @click="queryClick(scope.row)"
                v-hasPermi="['smartwater:rainfallmonitoring:query']"
                >详细</el-button
              >
              <el-button
                type="primary"
                :icon="EditPen"
                :plain="true"
                @click="editClick(scope.row)"
                v-hasPermi="['smartwater:rainfallmonitoring:update']"
                >编辑</el-button
              >
              <el-button
                type="danger"
                :icon="Delete"
                :plain="true"
                @click="delClick(scope.row)"
                v-hasPermi="['smartwater:rainfallmonitoring:delete']"
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
              <li class="width50"><span>雨量站标识码：</span>{{ queryData.name }}</li>
              <li class="width50"
                ><span>监测时间：</span>{{ dateFormatter(null, null, tableFieldShow('监测时间', queryData.field)) }}</li
              >
              <li class="width50"
                ><span>瞬时雨量：</span>{{ tableFieldShow('瞬时雨量', queryData.field) }}</li
              >
              <li class="width50"
                ><span>累计流量：</span>{{ tableFieldShow('累计流量', queryData.field) }}</li
              >
              <li class="width50"
                ><span>降雨等级：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('降雨等级', queryData.field),
                    optionsLisi.降雨等级Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>累积量清零周期：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('累积量清零周期', queryData.field),
                    optionsLisi.累积量清零周期Options
                  )
                }}</li
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
                label="雨量站标识码："
                prop="name"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入雨量站标识码', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入雨量站标识码" v-model="formData.name" clearable />
              </el-form-item>
              <el-form-item
                label="监测时间："
                prop="field.监测时间"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入监测时间', trigger: 'blur' }]"
              >
                <el-date-picker
                  v-model="formData.field.监测时间"
                  type="datetime"
                  value-format="x"
                  placeholder="选择监测时间"
                />
              </el-form-item>
              <el-form-item
                label="瞬时雨量："
                prop="field.瞬时雨量"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入瞬时雨量', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入瞬时雨量" v-model="formData.field.瞬时雨量" clearable>
                  <template #append>毫米每小时(mm/h)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="累计流量："
                prop="field.累计流量"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入累计流量', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入累计流量" v-model="formData.field.累计流量" clearable>
                  <template #append>毫米(mm)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="降雨等级："
                prop="field.降雨等级"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请选择降雨等级', trigger: 'blur' }]"
              >
                <el-select
                  placeholder="请选择降雨等级"
                  v-model="formData.field.降雨等级"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.降雨等级Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="累积量清零周期："
                prop="field.累积量清零周期"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请选择累积量清零周期', trigger: 'blur' }]"
              >
                <el-select
                  placeholder="请选择累积量清零周期"
                  v-model="formData.field.累积量清零周期"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.累积量清零周期Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
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
      assetProfileId: '62f93030-78bf-11f0-bd39-2dc5ccdb39ef' //资产配置id
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
  type: '雨量监测', //资产配置，都是写死固定的资产配置
  field: {
    监测时间: '', //监测时间
    瞬时雨量: '', //瞬时雨量
    累计流量: '', //累计流量
    降雨等级: '', //降雨等级
    累积量清零周期: '' //累积量清零周期
  }
}

// 所有选项集合
const optionsLisi = ref({
  //降雨等级 选项
  降雨等级Options: [
    { label: '小雨', value: '1' },
    { label: '中雨', value: '2' },
    { label: '大雨', value: '3' },
    { label: '暴雨', value: '4' }
  ],
  //累积量清零周期 选项
  累积量清零周期Options: [
    { label: '每日', value: '1' },
    { label: '每周', value: '2' },
    { label: '每月', value: '3' },
    { label: '每季', value: '4' }
  ]
})

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
  drawerConfig.value.title = '雨量监测-详细信息'
}
// 点击编辑按钮
const editClick = async (row) => {
  let copyRow = JSON.parse(JSON.stringify(row))
  formData.value = copyRow
  formData.value.field = {}
  formData.value.field.监测时间 = tableFieldShow('监测时间', row.field) //监测时间
  formData.value.field.瞬时雨量 = tableFieldShow('瞬时雨量', row.field) //瞬时雨量
  formData.value.field.累计流量 = tableFieldShow('累计流量', row.field) //累计流量
  formData.value.field.降雨等级 = tableFieldShow('降雨等级', row.field) //降雨等级
  formData.value.field.累积量清零周期 = tableFieldShow('累积量清零周期', row.field) //累积量清零周期

  drawerConfig.value.isDrawer = true
  drawerConfig.value.loading = false
  drawerConfig.value.type = 'edit'
  drawerConfig.value.title = '编辑雨量监测'
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
  drawerConfig.value.title = '新增雨量监测'
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
  name: '雨量监测', //标题名称
  addButName: '新增雨量监测' //新增按钮名称
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
  { label: '雨量站标识码', visible: true },
  { label: '监测时间', visible: true },
  { label: '瞬时雨量', visible: true },
  { label: '累计流量', visible: true },
  { label: '降雨等级', visible: true },
  { label: '累积量清零周期', visible: true }
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
