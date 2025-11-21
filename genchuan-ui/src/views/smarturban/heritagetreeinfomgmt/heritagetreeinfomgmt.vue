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
            v-hasPermi="['smarturban:heritagetreeinfomgmt:create']"
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
          <el-form-item label="标识码" prop="name">
            <el-input
              v-model="searchForm.name"
              placeholder="请输入标识码"
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
            label="标识码"
            min-width="150"
            align="center"
            prop="name"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('标识码')"
          />
          <el-table-column
            label="名称"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('名称')"
          >
            <template #default="scope">
              {{ tableFieldShow('名称', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="地址"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('地址')"
          >
            <template #default="scope">
              {{ tableFieldShow('地址', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="拉丁名"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('拉丁名')"
          >
            <template #default="scope">
              {{ tableFieldShow('拉丁名', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="科"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('科')"
          >
            <template #default="scope">
              {{ tableFieldShow('科', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="属"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('属')"
          >
            <template #default="scope">
              {{ tableFieldShow('属', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="种"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('种')"
          >
            <template #default="scope">
              {{ tableFieldShow('种', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="等级"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('等级')"
          >
            <template #default="scope">
              {{ tableFieldShow('等级', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="冠幅"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('冠幅')"
          >
            <template #default="scope">
              {{ tableFieldShow('冠幅', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="胸径"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('胸径')"
          >
            <template #default="scope">
              {{ tableFieldShow('胸径', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="树高"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('树高')"
          >
            <template #default="scope">
              {{ tableFieldShow('树高', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="树龄"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('树龄')"
          >
            <template #default="scope">
              {{ tableFieldShow('树龄', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="管理单位"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('管理单位')"
          >
            <template #default="scope">
              {{ tableFieldShow('管理单位', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="生长势"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('生长势')"
          >
            <template #default="scope">
              {{ tableFieldShow('生长势', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="保护措施"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('保护措施')"
          >
            <template #default="scope">
              {{ tableFieldShow('保护措施', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="日常养护"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('日常养护')"
          >
            <template #default="scope">
              {{ tableFieldShow('日常养护', scope.row.field) }}
            </template>
          </el-table-column>

          <el-table-column width="300" fixed="right" label="操作" align="center">
            <template #default="scope">
              <el-button
                type="success"
                :icon="View"
                :plain="true"
                @click="queryClick(scope.row)"
                v-hasPermi="['smarturban:heritagetreeinfomgmt:query']"
                >详细</el-button
              >
              <el-button
                type="primary"
                :icon="EditPen"
                :plain="true"
                @click="editClick(scope.row)"
                v-hasPermi="['smarturban:heritagetreeinfomgmt:update']"
                >编辑</el-button
              >
              <el-button
                type="danger"
                :icon="Delete"
                :plain="true"
                @click="delClick(scope.row)"
                v-hasPermi="['smarturban:heritagetreeinfomgmt:delete']"
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
              <li class="width50"><span>标识码：</span>{{ queryData.name }}</li>
              <li class="width50"
                ><span>名称：</span>{{ tableFieldShow('名称', queryData.field) }}</li
              >
              <li class="width50"
                ><span>地址：</span>{{ tableFieldShow('地址', queryData.field) }}</li
              >
              <li class="width50"
                ><span>拉丁名：</span>{{ tableFieldShow('拉丁名', queryData.field) }}</li
              >
              <li class="width50"><span>科：</span>{{ tableFieldShow('科', queryData.field) }}</li>
              <li class="width50"><span>属：</span>{{ tableFieldShow('属', queryData.field) }}</li>
              <li class="width50"><span>种：</span>{{ tableFieldShow('种', queryData.field) }}</li>
              <li class="width50"
                ><span>等级：</span>{{ tableFieldShow('等级', queryData.field) }}</li
              >
              <li class="width50"
                ><span>冠幅：</span>{{ tableFieldShow('冠幅', queryData.field) }}</li
              >
              <li class="width50"
                ><span>胸径：</span>{{ tableFieldShow('胸径', queryData.field) }}</li
              >
              <li class="width50"
                ><span>树高：</span>{{ tableFieldShow('树高', queryData.field) }}</li
              >
              <li class="width50"
                ><span>树龄：</span>{{ tableFieldShow('树龄', queryData.field) }}</li
              >
              <li class="width50"
                ><span>管理单位：</span>{{ tableFieldShow('管理单位', queryData.field) }}</li
              >
              <li class="width50"
                ><span>生长势：</span>{{ tableFieldShow('生长势', queryData.field) }}</li
              >
              <li class="width50"
                ><span>保护措施：</span>{{ tableFieldShow('保护措施', queryData.field) }}</li
              >
              <li class="width50"
                ><span>日常养护：</span>{{ tableFieldShow('日常养护', queryData.field) }}</li
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
                label="标识码："
                prop="name"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入标识码', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入标识码" v-model="formData.name" clearable />
              </el-form-item>
              <el-form-item
                label="名称："
                prop="field.名称"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入名称', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入名称" v-model="formData.field.名称" clearable />
              </el-form-item>
              <el-form-item
                label="地址："
                prop="field.地址"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入地址', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入地址" v-model="formData.field.地址" clearable />
              </el-form-item>
              <el-form-item
                label="拉丁名："
                prop="field.拉丁名"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入拉丁名', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入拉丁名" v-model="formData.field.拉丁名" clearable />
              </el-form-item>
              <el-form-item
                label="科："
                prop="field.科"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入科', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入科" v-model="formData.field.科" clearable />
              </el-form-item>
              <el-form-item
                label="属："
                prop="field.属"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入属', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入属" v-model="formData.field.属" clearable />
              </el-form-item>
              <el-form-item
                label="种："
                prop="field.种"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入种', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入种" v-model="formData.field.种" clearable />
              </el-form-item>
              <el-form-item
                label="等级："
                prop="field.等级"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入等级', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入等级" v-model="formData.field.等级" clearable />
              </el-form-item>
              <el-form-item
                label="冠幅："
                prop="field.冠幅"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入冠幅', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入冠幅" v-model="formData.field.冠幅" clearable />
              </el-form-item>
              <el-form-item
                label="胸径："
                prop="field.胸径"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入胸径', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入胸径" v-model="formData.field.胸径" clearable />
              </el-form-item>
              <el-form-item
                label="树高："
                prop="field.树高"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入树高" v-model="formData.field.树高" clearable />
              </el-form-item>
              <el-form-item
                label="树龄："
                prop="field.树龄"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入树龄', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入树龄" v-model="formData.field.树龄" clearable />
              </el-form-item>
              <el-form-item
                label="管理单位："
                prop="field.管理单位"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入管理单位"
                  v-model="formData.field.管理单位"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="生长势："
                prop="field.生长势"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入生长势" v-model="formData.field.生长势" clearable />
              </el-form-item>
              <el-form-item
                label="保护措施："
                prop="field.保护措施"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入保护措施"
                  v-model="formData.field.保护措施"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="日常养护："
                prop="field.日常养护"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入日常养护"
                  v-model="formData.field.日常养护"
                  clearable
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
      assetProfileId: 'df926bb0-771c-11f0-bd39-2dc5ccdb39ef' //资产配置id
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
  type: '古树名木信息管理', //资产配置，都是写死固定的资产配置
  field: {
    名称: '', //名称
    地址: '', //地址
    拉丁名: '', //拉丁名
    科: '', //科
    属: '', //属
    种: '', //种
    等级: '', //等级
    冠幅: '', //冠幅
    胸径: '', //胸径
    树高: '', //树高
    树龄: '', //树龄
    管理单位: '', //管理单位
    生长势: '', //生长势
    保护措施: '', //保护措施
    日常养护: '' //日常养护
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
  drawerConfig.value.title = '古树名木信息管理-详细信息'
}
// 点击编辑按钮
const editClick = async (row) => {
  let copyRow = JSON.parse(JSON.stringify(row))
  formData.value = copyRow
  formData.value.field = {}
  formData.value.field.名称 = tableFieldShow('名称', row.field) //名称
  formData.value.field.地址 = tableFieldShow('地址', row.field) //地址
  formData.value.field.拉丁名 = tableFieldShow('拉丁名', row.field) //拉丁名
  formData.value.field.科 = tableFieldShow('科', row.field) //科
  formData.value.field.属 = tableFieldShow('属', row.field) //属
  formData.value.field.种 = tableFieldShow('种', row.field) //种
  formData.value.field.等级 = tableFieldShow('等级', row.field) //等级
  formData.value.field.冠幅 = tableFieldShow('冠幅', row.field) //冠幅
  formData.value.field.胸径 = tableFieldShow('胸径', row.field) //胸径
  formData.value.field.树高 = tableFieldShow('树高', row.field) //树高
  formData.value.field.树龄 = tableFieldShow('树龄', row.field) //树龄
  formData.value.field.管理单位 = tableFieldShow('管理单位', row.field) //管理单位
  formData.value.field.生长势 = tableFieldShow('生长势', row.field) //生长势
  formData.value.field.保护措施 = tableFieldShow('保护措施', row.field) //保护措施
  formData.value.field.日常养护 = tableFieldShow('日常养护', row.field) //日常养护

  drawerConfig.value.isDrawer = true
  drawerConfig.value.loading = false
  drawerConfig.value.type = 'edit'
  drawerConfig.value.title = '编辑古树名木信息管理'
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
  drawerConfig.value.title = '新增古树名木信息管理'
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
  name: '古树名木信息管理', //标题名称
  addButName: '新增古树名木信息管理' //新增按钮名称
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
  { label: '标识码', visible: true },
  { label: '名称', visible: true },
  { label: '地址', visible: true },
  { label: '拉丁名', visible: true },
  { label: '科', visible: true },
  { label: '属', visible: true },
  { label: '种', visible: true },
  { label: '等级', visible: true },
  { label: '冠幅', visible: true },
  { label: '胸径', visible: true },
  { label: '树高', visible: true },
  { label: '树龄', visible: true },
  { label: '管理单位', visible: true },
  { label: '生长势', visible: true },
  { label: '保护措施', visible: true },
  { label: '日常养护', visible: true }
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
