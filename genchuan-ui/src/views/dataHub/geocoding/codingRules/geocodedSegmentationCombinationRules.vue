<template>
  <div class="cc-main">
    <div class="cc-main-content">
      <!--title-->
      <!--      <div class="cc-title-css">-->
      <!--        <div class="left">-->
      <!--          <el-icon class="left-icon">-->
      <!--            <Tickets/>-->
      <!--          </el-icon>-->
      <!--          &lt;!&ndash;标题名称&ndash;&gt;-->
      <!--          <span>{{ titleConfig.name }}</span>-->
      <!--        </div>-->
      <!--        &lt;!&ndash;右边的按钮&ndash;&gt;-->
      <!--        <div class="right">-->
      <!--          <el-button type="primary" plain :icon="Plus" @click="addClick(titleConfig.addButName)" v-hasPermi="['codingRules:geocodedSegmentationCombinationRules:create']">-->
      <!--            {{ titleConfig.addButName }}-->
      <!--          </el-button>-->
      <!--        </div>-->
      <!--      </div>-->
      <!--搜索内容字段-->
      <div class="cc-search-css" ref="searchRef">
        <el-form
          class="-mb-15px"
          :model="searchForm"
          ref="searchFormRef"
          :inline="true"
          label-width="110px"
        >
          <el-form-item label="定位对象类型" prop="locationType">
            <el-select
              v-model="searchForm.locationType"
              placeholder="请选择定位对象类型"
              class="!w-240px"
              clearable
              @change="searchClick"
            >
              <el-option
                v-for="(item, key) in OptionsAll.locationTypeOptions"
                :key="key"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="分段规则" prop="segmentRule">
            <el-input
              v-model="searchForm.segmentRule"
              placeholder="请输入分段规则"
              clearable
              class="!w-240px"
              @change="searchClick"
            />
          </el-form-item>
          <el-form-item label="组合规则" prop="combineRule">
            <el-input
              v-model="searchForm.combineRule"
              placeholder="请输入组合规则"
              clearable
              class="!w-240px"
              @change="searchClick"
            />
          </el-form-item>
          <el-form-item label="方位" prop="directionOptions">
            <el-select
              v-model="searchForm.directionOptions"
              placeholder="请选择方位"
              class="!w-240px"
              clearable
              @change="searchClick"
            >
              <el-option
                v-for="(item, key) in OptionsAll.directionOptionsOptions"
                :key="key"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="启用状态" prop="enableStatus">
            <el-select
              v-model="searchForm.enableStatus"
              placeholder="请选择启用状态"
              class="!w-240px"
              clearable
              @change="searchClick"
            >
              <el-option
                v-for="(item, key) in OptionsAll.enableStatusOptions"
                :key="key"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="配置人" prop="configUser">
            <el-select
              v-model="searchForm.configUser"
              placeholder="请选择配置人"
              class="!w-240px"
              clearable
              @change="searchClick"
            >
              <el-option
                v-for="item in userOptions"
                :key="item.id"
                :label="item.nickname"
                :value="item.id"
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
              v-hasPermi="['codingRules:geocodedSegmentationCombinationRules:create']"
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
            label="定位对象类型"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('定位对象类型')"
          >
            <template #default="scope">
              <div @click="tableOneClick(scope.row)" class="table-active">
                {{ handleTypeOptions(scope.row.locationType, OptionsAll.locationTypeOptions) }}
              </div>
            </template>
          </el-table-column>
          <el-table-column
            label="分段规则"
            min-width="150"
            align="center"
            prop="segmentRule"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('分段规则')"
          />
          <el-table-column
            label="组合规则"
            min-width="150"
            align="center"
            prop="combineRule"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('组合规则')"
          />
          <el-table-column
            label="方位"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('方位')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(scope.row.directionOptions, OptionsAll.directionOptionsOptions)
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="启用状态"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('启用状态')"
          >
            <template #default="scope">
              {{ handleTypeOptions(scope.row.enableStatus, OptionsAll.enableStatusOptions) }}
            </template>
          </el-table-column>
          <el-table-column
            label="配置人"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('配置人')"
          >
            <template #default="scope">
              {{ handleTypeOptions(scope.row.configUser, userOptions, 2) }}
            </template>
          </el-table-column>
          <el-table-column
            label="配置时间"
            min-width="150"
            align="center"
            :formatter="dateFormatter2"
            prop="configTime"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('配置时间')"
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
                v-hasPermi="['codingRules:geocodedSegmentationCombinationRules:query']"
                >详细
              </el-button>
              <el-button
                type="primary"
                :icon="EditPen"
                :plain="true"
                @click="editClick(scope.row)"
                v-hasPermi="['codingRules:geocodedSegmentationCombinationRules:update']"
                >编辑
              </el-button>
              <el-button
                type="danger"
                :icon="Delete"
                :plain="true"
                @click="delClick(scope.row)"
                v-hasPermi="['codingRules:geocodedSegmentationCombinationRules:delete']"
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
                  <li class="width50"
                    ><span>定位对象类型：</span
                    >{{ handleTypeOptions(queryData.locationType, OptionsAll.locationTypeOptions) }}
                  </li>
                  <li class="width50"><span>分段规则：</span>{{ queryData.segmentRule }}</li>
                  <li class="width50"><span>组合规则：</span>{{ queryData.combineRule }}</li>
                  <li class="width50"
                    ><span>方位：</span
                    >{{
                      handleTypeOptions(
                        queryData.directionOptions,
                        OptionsAll.directionOptionsOptions
                      )
                    }}
                  </li>
                  <li class="width50"
                    ><span>启用状态：</span
                    >{{ handleTypeOptions(queryData.enableStatus, OptionsAll.enableStatusOptions) }}
                  </li>
                  <li class="width50"
                    ><span>配置人：</span
                    >{{ handleTypeOptions(queryData.configUser, userOptions, 2) }}
                  </li>
                  <li class="width50"
                    ><span>配置时间：</span>{{ formatDate(queryData.configTime, 'YYYY-MM-DD') }}</li
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
                    label="定位对象类型："
                    prop="locationType"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-select
                      placeholder="请选择定位对象类型"
                      v-model="formData.locationType"
                      filterable
                      clearable
                    >
                      <el-option
                        v-for="item in OptionsAll.locationTypeOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item
                    label="分段规则："
                    prop="segmentRule"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input
                      placeholder="请输入分段规则"
                      v-model="formData.segmentRule"
                      clearable
                    />
                  </el-form-item>
                  <el-form-item
                    label="组合规则："
                    prop="combineRule"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input
                      placeholder="请输入组合规则"
                      v-model="formData.combineRule"
                      clearable
                    />
                  </el-form-item>
                  <el-form-item
                    label="方位："
                    prop="directionOptions"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-select
                      placeholder="请选择方位"
                      v-model="formData.directionOptions"
                      filterable
                      clearable
                    >
                      <el-option
                        v-for="item in OptionsAll.directionOptionsOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item
                    label="启用状态："
                    prop="enableStatus"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-select
                      placeholder="请选择启用状态"
                      v-model="formData.enableStatus"
                      filterable
                      clearable
                    >
                      <el-option
                        v-for="item in OptionsAll.enableStatusOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item
                    label="配置人："
                    prop="configUser"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-select
                      placeholder="请选择配置人"
                      v-model="formData.configUser"
                      filterable
                      clearable
                    >
                      <el-option
                        v-for="item in userOptions"
                        :key="item.id"
                        :label="item.nickname"
                        :value="item.id"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item
                    label="配置时间："
                    prop="configTime"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-date-picker
                      class="width100"
                      v-model="formData.configTime"
                      type="date"
                      placeholder="请选择配置时间"
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
import * as geocodedSegmentationCombinationRulesApi from '@/api/dataHub/geocoding/codingRules/geocodedSegmentationCombinationRules'

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
  // 定位对象类型
  locationTypeOptions: [
    { label: '区域', value: '1' },
    { label: '地片与区片', value: '2' },
    { label: '街巷', value: '3' },
    { label: '门（楼）牌', value: '4' }
  ],
  // 方位
  directionOptionsOptions: [
    { label: '东', value: '1' },
    { label: '南', value: '2' },
    { label: '西', value: '3' },
    { label: '北', value: '4' },
    { label: '东南', value: '5' },
    { label: '西北', value: '6' },
    { label: '东北', value: '7' },
    { label: '西南', value: '8' },
    { label: '前', value: '9' },
    { label: '后', value: '10' },
    { label: '左', value: '11' },
    { label: '右', value: '12' },
    { label: '上', value: '13' },
    { label: '下', value: '14' },
    { label: '内', value: '15' },
    { label: '外', value: '16' },
    { label: '旁', value: '17' },
    { label: '相向', value: '18' },
    { label: '相邻', value: '19' }
  ],
  // 启用状态
  enableStatusOptions: [
    { label: '启用', value: 1 },
    { label: '禁用', value: 2 }
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
  name: '地理编码分段组合规则配置', //标题名称
  addButName: '新增地理编码分段组合规则' //新增按钮名称
})
// 搜索 字段
const initSearchForm = {
  locationType: '', // 定位对象类型
  segmentRule: '', // 分段规则
  combineRule: '', // 组合规则
  directionOptions: '', // 方位
  enableStatus: '', // 启用状态
  configUser: '' // 配置人
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
    const res = await geocodedSegmentationCombinationRulesApi.listPage(merged)
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
  locationType: '1', // 定位对象类型
  segmentRule: '', // 分段规则
  combineRule: '', // 组合规则
  directionOptions: '1', // 方位
  enableStatus: 1, // 启用状态
  configUser: '', // 配置人
  configTime: '', // 配置时间
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
        await geocodedSegmentationCombinationRulesApi.createData(data)
        ElMessage.success('保存成功')
      } else {
        await geocodedSegmentationCombinationRulesApi.updateData(data)
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
  drawerConfig.value.title = '地理编码分段组合规则-详细信息'
}
// 点击编辑按钮
const editClick = async (row) => {
  drawerConfig.value.active = '1'
  let copyRow = JSON.parse(JSON.stringify(row))
  formData.value = copyRow
  drawerConfig.value.isDrawer = true
  drawerConfig.value.loading = false
  drawerConfig.value.type = 'edit'
  drawerConfig.value.title = '编辑地理编码分段组合规则'
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
        await geocodedSegmentationCombinationRulesApi.delData(row.id)
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
  drawerConfig.value.title = '新增地理编码分段组合规则'
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
  { label: '定位对象类型', visible: true },
  { label: '分段规则', visible: true },
  { label: '组合规则', visible: true },
  { label: '方位', visible: true },
  { label: '启用状态', visible: true },
  { label: '配置人', visible: true },
  { label: '配置时间', visible: true },
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
