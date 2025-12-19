<template>
  <div class="cc-main">
    <div class="cc-main-content">
      <!--title-->
      <!--      <div class="cc-title-css">-->
      <!--        <div class="left">-->
      <!--          <el-icon class="left-icon">-->
      <!--            <Tickets/>-->
      <!--          </el-icon>-->
      <!--标题名称-->
      <!--          <span>{{ titleConfig.name }}</span>-->
      <!--        </div>-->
      <!--右边的按钮-->
      <!--        <div class="right">-->
      <!--          <el-button type="primary" plain :icon="Plus" @click="addClick(titleConfig.addButName)" v-hasPermi="['statisticalReports:geocodedDataQuality:create']">
                  {{ titleConfig.addButName }}
                </el-button>-->
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
          <el-form-item label="统计周期" prop="statCycle">
            <el-input
              v-model="searchForm.statCycle"
              placeholder="请输入统计周期"
              clearable
              class="!w-240px"
              @change="searchClick"
            />
          </el-form-item>
          <el-form-item label="行政区划代码" prop="adminCode">
            <el-input
              v-model="searchForm.adminCode"
              placeholder="请输入行政区划代码"
              clearable
              class="!w-240px"
              @change="searchClick"
            />
          </el-form-item>
          <el-form-item label="行政区划名称" prop="adminName">
            <el-input
              v-model="searchForm.adminName"
              placeholder="请输入行政区划名称"
              clearable
              class="!w-240px"
              @change="searchClick"
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
            label="统计周期"
            min-width="150"
            align="center"
            prop="statCycle"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('统计周期')"
          >
            <template #default="scope">
              <div @click="tableOneClick(scope.row)" class="table-active">{{
                scope.row.statCycle
              }}</div>
            </template>
          </el-table-column>
          <el-table-column
            label="统计时间"
            min-width="150"
            align="center"
            prop="statTime"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('统计时间')"
          />
          <el-table-column
            label="行政区划代码"
            min-width="150"
            align="center"
            prop="adminCode"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('行政区划代码')"
          />
          <el-table-column
            label="行政区划名称"
            min-width="150"
            align="center"
            prop="adminName"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('行政区划名称')"
          />
          <el-table-column
            label="检查数据总量"
            min-width="150"
            align="center"
            prop="totalCheckCount"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('检查数据总量')"
          />
          <el-table-column
            label="属性错误数据数量"
            min-width="150"
            align="center"
            prop="attributeErrorCount"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('属性错误数据数量')"
          />
          <el-table-column
            label="位置精度不合格数据数量"
            min-width="150"
            align="center"
            prop="positionErrorCount"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('位置精度不合格数据数量')"
          />
          <el-table-column
            label="未及时更新数据数量"
            min-width="150"
            align="center"
            prop="updateDelayedCount"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('未及时更新数据数量')"
          />
          <el-table-column
            label="质量整改建议"
            min-width="150"
            align="center"
            prop="qualityImprovementSuggestion"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('质量整改建议')"
          />
          <el-table-column
            label="统计人"
            min-width="150"
            align="center"
            prop="statUser"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('统计人')"
          />
          <el-table-column
            label="备注"
            min-width="150"
            align="center"
            prop="remark"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('备注')"
          />
          <el-table-column width="100" fixed="right" label="操作" align="center">
            <template #default="scope">
              <el-button
                type="success"
                :icon="View"
                :plain="true"
                @click="queryClick(scope.row)"
                v-hasPermi="['statisticalReports:geocodedDataQuality:query']"
                >详细
              </el-button>
              <!--              <el-button type="primary" :icon="EditPen" :plain="true" @click="editClick(scope.row)" v-hasPermi="['statisticalReports:geocodedDataQuality:update']">编辑</el-button>
                            <el-button type="danger" :icon="Delete" :plain="true" @click="delClick(scope.row)" v-hasPermi="['statisticalReports:geocodedDataQuality:delete']">删除</el-button>-->
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
                  <li class="width50"><span>统计周期：</span>{{ queryData.statCycle }}</li>
                  <li class="width50"><span>统计时间：</span>{{ queryData.statTime }}</li>
                  <li class="width50"><span>行政区划代码：</span>{{ queryData.adminCode }}</li>
                  <li class="width50"><span>行政区划名称：</span>{{ queryData.adminName }}</li>
                  <li class="width50"
                    ><span>检查数据总量：</span>{{ queryData.totalCheckCount }}</li
                  >
                  <li class="width50"
                    ><span>属性错误数据数量：</span>{{ queryData.attributeErrorCount }}</li
                  >
                  <li class="width50"
                    ><span>位置精度不合格数据数量：</span>{{ queryData.positionErrorCount }}</li
                  >
                  <li class="width50"
                    ><span>未及时更新数据数量：</span>{{ queryData.updateDelayedCount }}</li
                  >
                  <li class="width50"
                    ><span>质量整改建议：</span>{{ queryData.qualityImprovementSuggestion }}</li
                  >
                  <li class="width50"><span>统计人：</span>{{ queryData.statUser }}</li>
                  <li class="width50"><span>备注：</span>{{ queryData.remark }}</li>
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
                    label="统计周期："
                    prop="statCycle"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input placeholder="请输入统计周期" v-model="formData.statCycle" clearable />
                  </el-form-item>
                  <el-form-item
                    label="统计时间："
                    prop="statTime"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input placeholder="请输入统计时间" v-model="formData.statTime" clearable />
                  </el-form-item>
                  <el-form-item
                    label="行政区划代码："
                    prop="adminCode"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input
                      placeholder="请输入行政区划代码"
                      v-model="formData.adminCode"
                      clearable
                    />
                  </el-form-item>
                  <el-form-item
                    label="行政区划名称："
                    prop="adminName"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input
                      placeholder="请输入行政区划名称"
                      v-model="formData.adminName"
                      clearable
                    />
                  </el-form-item>
                  <el-form-item
                    label="检查数据总量："
                    prop="totalCheckCount"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input
                      placeholder="请输入检查数据总量"
                      v-model="formData.totalCheckCount"
                      clearable
                    />
                  </el-form-item>
                  <el-form-item
                    label="属性错误数据数量："
                    prop="attributeErrorCount"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input
                      placeholder="请输入属性错误数据数量"
                      v-model="formData.attributeErrorCount"
                      clearable
                    />
                  </el-form-item>
                  <el-form-item
                    label="位置精度不合格数据数量："
                    prop="positionErrorCount"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input
                      placeholder="请输入位置精度不合格数据数量"
                      v-model="formData.positionErrorCount"
                      clearable
                    />
                  </el-form-item>
                  <el-form-item
                    label="未及时更新数据数量："
                    prop="updateDelayedCount"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input
                      placeholder="请输入未及时更新数据数量"
                      v-model="formData.updateDelayedCount"
                      clearable
                    />
                  </el-form-item>
                  <el-form-item
                    label="质量整改建议："
                    prop="qualityImprovementSuggestion"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input
                      placeholder="请输入质量整改建议"
                      v-model="formData.qualityImprovementSuggestion"
                      clearable
                    />
                  </el-form-item>
                  <el-form-item
                    label="统计人："
                    prop="statUser"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input placeholder="请输入统计人" v-model="formData.statUser" clearable />
                  </el-form-item>
                  <el-form-item
                    label="备注："
                    prop="remark"
                    class="edit-content-form-li width50"
                    :rules="[]"
                  >
                    <el-input placeholder="请输入备注" v-model="formData.remark" clearable />
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
import * as geocodedDataQualityApi from '@/api/dataHub/geocoding/statisticalReports/geocodedDataQuality'

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
const OptionsAll = ref({})
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
  name: '地理编码数据质量统计报表', //标题名称
  addButName: '新增地理编码数据质量统计报表' //新增按钮名称
})
// 搜索 字段
const initSearchForm = {
  statCycle: '', // 统计周期
  adminCode: '', // 行政区划代码
  adminName: '' // 行政区划名称
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
    const res = await geocodedDataQualityApi.listPage(merged)
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
  statCycle: '', // 统计周期
  statTime: '', // 统计时间
  adminCode: '', // 行政区划代码
  adminName: '', // 行政区划名称
  totalCheckCount: '', // 检查数据总量
  attributeErrorCount: '', // 属性错误数据数量
  positionErrorCount: '', // 位置精度不合格数据数量
  updateDelayedCount: '', // 未及时更新数据数量
  qualityImprovementSuggestion: '', // 质量整改建议
  statUser: '', // 统计人
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
        await geocodedDataQualityApi.createData(data)
        ElMessage.success('保存成功')
      } else {
        await geocodedDataQualityApi.updateData(data)
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
  drawerConfig.value.title = '地理编码数据质量统计报表-详细信息'
}
// 点击编辑按钮
const editClick = async (row) => {
  drawerConfig.value.active = '1'
  let copyRow = JSON.parse(JSON.stringify(row))
  formData.value = copyRow
  drawerConfig.value.isDrawer = true
  drawerConfig.value.loading = false
  drawerConfig.value.type = 'edit'
  drawerConfig.value.title = '编辑地理编码数据质量统计报表'
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
        await geocodedDataQualityApi.delData(row.id)
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
  drawerConfig.value.title = '新增地理编码数据质量统计报表'
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
  { label: '统计周期', visible: true },
  { label: '统计时间', visible: true },
  { label: '行政区划代码', visible: true },
  { label: '行政区划名称', visible: true },
  { label: '检查数据总量', visible: true },
  { label: '属性错误数据数量', visible: true },
  { label: '位置精度不合格数据数量', visible: true },
  { label: '未及时更新数据数量', visible: true },
  { label: '质量整改建议', visible: true },
  { label: '统计人', visible: true },
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
