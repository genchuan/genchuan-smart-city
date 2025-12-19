<template>
  <div class="cc-main">
    <div class="cc-main-content">
      <!--搜索内容字段-->
      <div class="cc-search-css" ref="searchRef">
        <el-form
          class="-mb-15px"
          :model="searchForm"
          ref="searchFormRef"
          :inline="true"
          label-width="110px"
        >
          <el-form-item label="名称" prop="poiName">
            <el-input
              v-model="searchForm.poiName"
              placeholder="请输入名称"
              clearable
              class="!w-240px"
              @change="searchClick"
            />
          </el-form-item>
          <el-form-item label="标识码" prop="poiCode">
            <el-input
              v-model="searchForm.poiCode"
              placeholder="请输入标识码"
              clearable
              class="!w-240px"
              @change="searchClick"
            />
          </el-form-item>
          <el-form-item label="内部编码" prop="intraCode">
            <el-input
              v-model="searchForm.intraCode"
              placeholder="请输入内部编码"
              clearable
              class="!w-240px"
              @change="searchClick"
            />
          </el-form-item>
          <el-form-item label="北斗网格位置码" prop="bdGridCode">
            <el-input
              v-model="searchForm.bdGridCode"
              placeholder="请输入北斗网格位置码"
              clearable
              class="!w-240px"
              @change="searchClick"
            />
          </el-form-item>
          <el-form-item label="曾用名" prop="oldName">
            <el-input
              v-model="searchForm.oldName"
              placeholder="请输入曾用名"
              clearable
              class="!w-240px"
              @change="searchClick"
            />
          </el-form-item>
          <el-form-item label="所在层" prop="floor">
            <el-input
              v-model="searchForm.floor"
              placeholder="请输入所在层"
              clearable
              class="!w-240px"
              @change="searchClick"
            />
          </el-form-item>
          <el-form-item label="所属类型" prop="poiType">
            <el-select
              v-model="searchForm.poiType"
              placeholder="请选择所属类型"
              class="!w-240px"
              clearable
              @change="searchClick"
            >
              <el-option
                v-for="(item, key) in OptionsAll.poiTypeOptions"
                :key="key"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
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
            <el-button
              type="primary"
              plain
              :icon="Plus"
              @click="addClick(titleConfig.addButName)"
              v-hasPermi="['basicLocationData:pointOfInterestData:create']"
            >
              {{ titleConfig.addButName }}
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
            label="名称"
            min-width="150"
            align="center"
            prop="poiName"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('名称')"
          >
            <template #default="scope">
              <div @click="tableOneClick(scope.row)" class="table-active">{{
                scope.row.poiName
              }}</div>
            </template>
          </el-table-column>
          <el-table-column
            label="标识码"
            min-width="150"
            align="center"
            prop="poiCode"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('标识码')"
          />
          <el-table-column
            label="内部编码"
            min-width="150"
            align="center"
            prop="intraCode"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('内部编码')"
          />
          <el-table-column
            label="北斗网格位置码"
            min-width="150"
            align="center"
            prop="bdGridCode"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('北斗网格位置码')"
          />
          <el-table-column
            label="曾用名"
            min-width="150"
            align="center"
            prop="oldName"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('曾用名')"
          />
          <el-table-column
            label="所在层"
            min-width="150"
            align="center"
            prop="floor"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('所在层')"
          />
          <el-table-column
            label="所属类型"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('所属类型')"
          >
            <template #default="scope">
              {{ handleTypeOptions(scope.row.poiType, OptionsAll.poiTypeOptions) }}
            </template>
          </el-table-column>
          <el-table-column
            label="数据来源"
            min-width="150"
            align="center"
            prop="dataSource"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('数据来源')"
          />
          <el-table-column
            label="初始日期"
            min-width="150"
            align="center"
            :formatter="dateFormatter2"
            prop="initDate"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('初始日期')"
          />
          <el-table-column
            label="变更日期"
            min-width="150"
            align="center"
            :formatter="dateFormatter2"
            prop="updateDate"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('变更日期')"
          />
          <el-table-column
            label="备注"
            min-width="150"
            align="center"
            prop="remark"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('备注')"
          />
          <el-table-column width="300" fixed="right" label="操作" align="center">
            <template #default="scope">
              <el-button
                type="success"
                :icon="View"
                :plain="true"
                @click="queryClick(scope.row)"
                v-hasPermi="['basicLocationData:pointOfInterestData:query']"
                >详细
              </el-button>
              <el-button
                type="primary"
                :icon="EditPen"
                :plain="true"
                @click="editClick(scope.row)"
                v-hasPermi="['basicLocationData:pointOfInterestData:update']"
                >编辑
              </el-button>
              <el-button
                type="danger"
                :icon="Delete"
                :plain="true"
                @click="delClick(scope.row)"
                v-hasPermi="['basicLocationData:pointOfInterestData:delete']"
                >删除
              </el-button>
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
      <div :class="[drawerConfig.fullscreenType ? 'cc-drawer-css-mainNo' : 'cc-drawer-css-main']">
        <el-drawer
          :title="drawerConfig.title"
          v-model="drawerConfig.isDrawer"
          direction="rtl"
          :modal="false"
          class="cc-drawer-css"
          size="100%"
        >
          <!--头部配置-->
          <template #header>
            <span>{{ drawerConfig.title }}</span>
            <el-icon
              color="#409efc"
              class="icon-fullscreen"
              @click="drawerConfig.fullscreenType = !drawerConfig.fullscreenType"
            >
              <FullScreen />
            </el-icon>
          </template>
          <div class="drawer-content">
            <!--tabs选项-->
            <el-tabs v-model="drawerConfig.active" class="content-top-tabs" @tab-click="tabsClick">
              <el-tab-pane label="基础信息" name="1" v-if="drawerConfig.type == 'add'" />
              <template v-if="drawerConfig.type == 'query'">
                <el-tab-pane
                  v-for="item in drawerConfig.tabsList"
                  :key="item.value"
                  :label="item.label"
                  :name="item.name"
                />
              </template>
            </el-tabs>
            <!--基础信息相关内容-->
            <template v-if="drawerConfig.active == 1">
              <!--查看 li添加class width100 width50 width33 就可以一列 二列 三列-->
              <div
                class="see-content"
                v-loading="drawerConfig.loading"
                v-if="drawerConfig.type === 'query'"
              >
                <ul>
                  <li class="width50"><span>名称：</span>{{ queryData.poiName }}</li>
                  <li class="width50"><span>标识码：</span>{{ queryData.poiCode }}</li>
                  <li class="width50"><span>内部编码：</span>{{ queryData.intraCode }}</li>
                  <li class="width50"><span>北斗网格位置码：</span>{{ queryData.bdGridCode }}</li>
                  <li class="width50"><span>曾用名：</span>{{ queryData.oldName }}</li>
                  <li class="width50"><span>所在层：</span>{{ queryData.floor }}</li>
                  <li class="width50">
                    <span>所属类型：</span
                    >{{ handleTypeOptions(queryData.poiType, OptionsAll.poiTypeOptions) }}
                  </li>
                  <li class="width50"><span>数据来源：</span>{{ queryData.dataSource }}</li>
                  <li class="width50"
                    ><span>初始日期：</span>{{ formatDate(queryData.initDate, 'YYYY-MM-DD') }}</li
                  >
                  <li class="width50"
                    ><span>变更日期：</span>{{ formatDate(queryData.updateDate, 'YYYY-MM-DD') }}</li
                  >
                  <li class="width100"><span>备注：</span>{{ queryData.remark }}</li>
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
                    label="名称："
                    prop="poiName"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input placeholder="请输入名称" v-model="formData.poiName" clearable />
                  </el-form-item>
                  <el-form-item
                    label="标识码："
                    prop="poiCode"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input placeholder="请输入标识码" v-model="formData.poiCode" clearable />
                  </el-form-item>
                  <el-form-item
                    label="内部编码："
                    prop="intraCode"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input placeholder="请输入内部编码" v-model="formData.intraCode" clearable />
                  </el-form-item>
                  <el-form-item
                    label="北斗网格位置码："
                    prop="bdGridCode"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input
                      placeholder="请输入北斗网格位置码"
                      v-model="formData.bdGridCode"
                      clearable
                    />
                  </el-form-item>
                  <el-form-item
                    label="曾用名："
                    prop="oldName"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input placeholder="请输入曾用名" v-model="formData.oldName" clearable />
                  </el-form-item>
                  <el-form-item
                    label="所在层："
                    prop="floor"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input placeholder="请输入所在层" v-model="formData.floor" clearable />
                  </el-form-item>
                  <el-form-item
                    label="所属类型："
                    prop="poiType"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-select
                      placeholder="请选择所属类型"
                      v-model="formData.poiType"
                      filterable
                      clearable
                    >
                      <el-option
                        v-for="item in OptionsAll.poiTypeOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item
                    label="数据来源："
                    prop="dataSource"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input
                      placeholder="请输入数据来源"
                      v-model="formData.dataSource"
                      clearable
                    />
                  </el-form-item>
                  <el-form-item
                    label="初始日期："
                    prop="initDate"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-date-picker
                      class="width100"
                      v-model="formData.initDate"
                      type="date"
                      placeholder="请选择初始日期"
                      format="YYYY-MM-DD"
                      value-format="x"
                    />
                  </el-form-item>
                  <el-form-item
                    label="变更日期："
                    prop="updateDate"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-date-picker
                      class="width100"
                      v-model="formData.updateDate"
                      type="date"
                      placeholder="请选择变更日期"
                      format="YYYY-MM-DD"
                      value-format="x"
                    />
                  </el-form-item>
                  <el-form-item
                    label="备注："
                    prop="remark"
                    class="edit-content-form-li width100"
                    :rules="[]"
                  >
                    <el-input
                      placeholder="请输入备注"
                      v-model="formData.remark"
                      type="textarea"
                      :autosize="{ minRows: 2, maxRows: 6 }"
                      clearable
                    />
                  </el-form-item>
                </el-form>
              </div>
            </template>
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
  </div>
</template>
<script lang="ts" setup>
import { ref, reactive, onMounted } from 'vue'
import {
  Tickets,
  Plus,
  EditPen,
  Delete,
  View,
  Menu,
  Close,
  Check,
  FullScreen
} from '@element-plus/icons-vue'
import { handleTypeOptions } from '@/utils/thingsBoardUtils'
import { handleTree } from '@/utils/tree'
import { dateFormatter, dateFormatter2, formatDate } from '@/utils/formatTime'
import * as DeptApi from '@/api/system/dept' // 部门树形结构
import * as UserApi from '@/api/system/user' //用户列表
import * as pointOfInterestDataApi from '@/api/dataHub/geocoding/basicLocationData/pointOfInterestData'

const deptList = ref<Tree[]>([]) //部门属性数据

// 获得用户列表
const userOptions = ref([]) // 用户列表

// 所属部门开始
const defaultProps = {
  children: 'children',
  label: 'name',
  value: 'id',
  isLeaf: 'leaf',
  emitPath: false // 用于 cascader 组件：在选中节点改变时，是否返回由该节点所在的各级菜单的值所组成的数组，若设置 false，则只返回该节点的值
}
// 所有选项集合
const OptionsAll = ref({
  // 所属类型
  poiTypeOptions: [
    { label: '交通枢纽', value: '1' },
    { label: '公共建筑', value: '2' },
    { label: '雕塑', value: '3' }
  ]
})
// 接口下拉选项
const OptionsAllInit = async () => {
  const res = await UserApi.getSimpleUserList()
  userOptions.value = res.map((item) => ({
    id: item.id.toString(),
    nickname: item.nickname,
    deptId: item.deptId,
    deptName: item.deptName
  }))
}
// title配置
const titleConfig = ref({
  name: '兴趣点数据管理', //标题名称
  addButName: '新增兴趣点数据' //新增按钮名称
})
// 搜索 字段
const initSearchForm = {
  poiName: '', // 名称
  poiCode: '', // 标识码
  intraCode: '', // 内部编码
  bdGridCode: '', // 北斗网格位置码
  oldName: '', // 曾用名
  floor: '', // 所在层
  poiType: '' // 所属类型
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
//table 初始化
const tableInitData = async () => {
  tableConfig.value.loading = true
  try {
    let params = {
      pageSize: tableConfig.value.pageSize,
      pageNo: tableConfig.value.currentPage
    }
    const merged = { ...params, ...searchForm.value }
    const res = await pointOfInterestDataApi.listPage(merged)
    tableData.value = res.list
    tableConfig.value.total = res.total
    tableConfig.value.loading = false
  } catch (error) {
    tableConfig.value.loading = false
    // 处理错误
    console.log('初始化出错了')
  }
}
// 表单字段
const initFormData = {
  id: '',
  poiName: '', // 名称
  poiCode: '', // 标识码
  intraCode: '', // 内部编码
  bdGridCode: '', // 北斗网格位置码
  oldName: '', // 曾用名
  floor: '', // 所在层
  poiType: '1', // 所属类型
  dataSource: '', // 数据来源
  initDate: '', // 初始日期
  updateDate: '', // 变更日期
  remark: '' // 备注
}
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
      const data = formData.value
      if (drawerConfig.value.type === 'add' && !formData.value.id) {
        await pointOfInterestDataApi.createData(data)
        ElMessage.success('保存成功')
      } else {
        await pointOfInterestDataApi.updateData(data)
        ElMessage.success('修改成功')
      }
      drawerConfig.value.isDrawer = false
      await tableInitData()
    } finally {
      console.log('表单验证出错!')
    }
  }
}
// 表格点击切换抽屉 正常放在表格第一个字段上就可以了
const tableOneClick = (row) => {
  switch (drawerConfig.value.type) {
    case 'query':
      queryClick(row)
      break
    case 'edit':
      editClick(row)
      break
    default:
      queryClick(row)
  }
}
// 点击详细按钮
const queryData = ref({})
const queryClick = async (row) => {
  drawerConfig.value.active = '1'
  let copyRow = JSON.parse(JSON.stringify(row))
  queryData.value = copyRow

  drawerConfig.value.isDrawer = true
  drawerConfig.value.loading = false
  drawerConfig.value.type = 'query'
  drawerConfig.value.title = '兴趣点数据-详细信息'
}
// 点击编辑按钮
const editClick = async (row) => {
  drawerConfig.value.active = '1'
  let copyRow = JSON.parse(JSON.stringify(row))
  formData.value = copyRow
  drawerConfig.value.isDrawer = true
  drawerConfig.value.loading = false
  drawerConfig.value.type = 'edit'
  drawerConfig.value.title = '编辑兴趣点数据'
}
// 点击删除按钮
const delClick = (row) => {
  ElMessageBox.confirm('请注意：确认后，所有相关数据将不可恢复。', `确定要删除吗？`, {
    confirmButtonText: '确 定',
    cancelButtonText: '取 消',
    type: 'warning'
  })
    .then(async () => {
      try {
        await pointOfInterestDataApi.delData(row.id)
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
  formData.value = { ...initFormData }
  drawerConfig.value.isDrawer = true
  drawerConfig.value.loading = false
  drawerConfig.value.type = 'add'
  drawerConfig.value.title = '新增兴趣点数据'
  drawerConfig.value.active = '1'
}
//drawer 配置
const drawerConfig = ref({
  isDrawer: false, //是否打开抽屉 true 打开 false 关闭
  loading: true, //还没加载完成 true 在加载 false加载完成
  title: '抽屉标题', //标题名称
  type: 'add', //add新增 edit编辑 query详细
  active: '1', //当前选择了哪个
  fullscreenType: false, // 是否全屏显示
  // tabs轮动
  tabsList: [
    { label: '基础信息', name: '1' },
    { label: '预留', name: '2' }
  ]
})
//drawer tabs 点击切换
const tabsClick = (tab) => {
  tableConfig.value.currentPage = 1 //当前第几页
  tableInitData()
  console.log('tabs点击切换', tab.props.name)
}
//table 列显隐信息
const tableColumns = ref([
  { label: '名称', visible: true },
  { label: '标识码', visible: true },
  { label: '内部编码', visible: true },
  { label: '北斗网格位置码', visible: true },
  { label: '曾用名', visible: true },
  { label: '所在层', visible: true },
  { label: '所属类型', visible: true },
  { label: '数据来源', visible: true },
  { label: '初始日期', visible: true },
  { label: '变更日期', visible: true },
  { label: '备注', visible: true }
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
onMounted(async () => {
  console.log('挂载完毕2')
  deptList.value = handleTree(await DeptApi.getSimpleDeptList()) // 部门树形
  addClassToBody() //给body加class控制样式
  getTableHeight() //给table获取高度
  await tableInitData() //数据初始化
  await OptionsAllInit()
})
// 在组件卸载时移除 class
onUnmounted(() => {
  removeClassFromBody()
})
</script>
<style lang="scss" scoped></style>
