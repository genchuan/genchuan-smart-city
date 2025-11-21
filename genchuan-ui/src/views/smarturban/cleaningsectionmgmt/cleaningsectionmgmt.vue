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
            v-hasPermi="['smarturban:cleaningsectionmgmt:create']"
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
          <el-form-item label="路段编号" prop="name">
            <el-input
              v-model="searchForm.name"
              placeholder="请输入路段编号"
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
            label="行政区划代码"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('行政区划代码')"
          >
            <template #default="scope">
              {{ tableFieldShow('行政区划代码', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="路段编号"
            min-width="150"
            align="center"
            prop="name"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('路段编号')"
          />
          <el-table-column
            label="路段名称"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('路段名称')"
          >
            <template #default="scope">
              {{ tableFieldShow('路段名称', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="起点"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('起点')"
          >
            <template #default="scope">
              {{ tableFieldShow('起点', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="终点"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('终点')"
          >
            <template #default="scope">
              {{ tableFieldShow('终点', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="道路走向"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('道路走向')"
          >
            <template #default="scope">
              {{ tableFieldShow('道路走向', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="道路等级"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('道路等级')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('道路等级', scope.row.field),
                  optionsLisi.道路等级Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="路段长度"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('路段长度')"
          >
            <template #default="scope">
              {{ tableFieldShow('路段长度', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="路面铺设材料"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('路面铺设材料')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('路面铺设材料', scope.row.field),
                  optionsLisi.路面铺设材料Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="总面积"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('总面积')"
          >
            <template #default="scope">
              {{ tableFieldShow('总面积', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="主路面积"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('主路面积')"
          >
            <template #default="scope">
              {{ tableFieldShow('主路面积', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="辅路面积"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('辅路面积')"
          >
            <template #default="scope">
              {{ tableFieldShow('辅路面积', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="人行道面积"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('人行道面积')"
          >
            <template #default="scope">
              {{ tableFieldShow('人行道面积', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="绿化带面积"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('绿化带面积')"
          >
            <template #default="scope">
              {{ tableFieldShow('绿化带面积', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="隔离带面积"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('隔离带面积')"
          >
            <template #default="scope">
              {{ tableFieldShow('隔离带面积', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="是否有护栏"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('是否有护栏')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('是否有护栏', scope.row.field),
                  optionsLisi.是否有护栏Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="是否有隔音屏"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('是否有隔音屏')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('是否有隔音屏', scope.row.field),
                  optionsLisi.是否有隔音屏Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="保洁类型"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('保洁类型')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('保洁类型', scope.row.field),
                  optionsLisi.保洁类型Options
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
                v-hasPermi="['smarturban:cleaningsectionmgmt:query']"
                >详细</el-button
              >
              <el-button
                type="primary"
                :icon="EditPen"
                :plain="true"
                @click="editClick(scope.row)"
                v-hasPermi="['smarturban:cleaningsectionmgmt:update']"
                >编辑</el-button
              >
              <el-button
                type="danger"
                :icon="Delete"
                :plain="true"
                @click="delClick(scope.row)"
                v-hasPermi="['smarturban:cleaningsectionmgmt:delete']"
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
              <li class="width50"
                ><span>行政区划代码：</span
                >{{ tableFieldShow('行政区划代码', queryData.field) }}</li
              >
              <li class="width50"><span>路段编号：</span>{{ queryData.name }}</li>
              <li class="width50"
                ><span>路段名称：</span>{{ tableFieldShow('路段名称', queryData.field) }}</li
              >
              <li class="width50"
                ><span>起点：</span>{{ tableFieldShow('起点', queryData.field) }}</li
              >
              <li class="width50"
                ><span>终点：</span>{{ tableFieldShow('终点', queryData.field) }}</li
              >
              <li class="width50"
                ><span>道路走向：</span>{{ tableFieldShow('道路走向', queryData.field) }}</li
              >
              <li class="width50"
                ><span>道路等级：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('道路等级', queryData.field),
                    optionsLisi.道路等级Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>路段长度：</span>{{ tableFieldShow('路段长度', queryData.field) }}</li
              >
              <li class="width50"
                ><span>路面铺设材料：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('路面铺设材料', queryData.field),
                    optionsLisi.路面铺设材料Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>总面积：</span>{{ tableFieldShow('总面积', queryData.field) }}</li
              >
              <li class="width50"
                ><span>主路面积：</span>{{ tableFieldShow('主路面积', queryData.field) }}</li
              >
              <li class="width50"
                ><span>辅路面积：</span>{{ tableFieldShow('辅路面积', queryData.field) }}</li
              >
              <li class="width50"
                ><span>人行道面积：</span>{{ tableFieldShow('人行道面积', queryData.field) }}</li
              >
              <li class="width50"
                ><span>绿化带面积：</span>{{ tableFieldShow('绿化带面积', queryData.field) }}</li
              >
              <li class="width50"
                ><span>隔离带面积：</span>{{ tableFieldShow('隔离带面积', queryData.field) }}</li
              >
              <li class="width50"
                ><span>是否有护栏：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('是否有护栏', queryData.field),
                    optionsLisi.是否有护栏Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>是否有隔音屏：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('是否有隔音屏', queryData.field),
                    optionsLisi.是否有隔音屏Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>保洁类型：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('保洁类型', queryData.field),
                    optionsLisi.保洁类型Options
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
                label="行政区划代码："
                prop="field.行政区划代码"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入行政区划代码', trigger: 'blur' }]"
              >
                <el-input
                  placeholder="请输入行政区划代码"
                  v-model="formData.field.行政区划代码"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="路段编号："
                prop="name"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入路段编号', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入路段编号" v-model="formData.name" clearable />
              </el-form-item>
              <el-form-item
                label="路段名称："
                prop="field.路段名称"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入路段名称', trigger: 'blur' }]"
              >
                <el-input
                  placeholder="请输入路段名称"
                  v-model="formData.field.路段名称"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="起点："
                prop="field.起点"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入起点', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入起点" v-model="formData.field.起点" clearable />
              </el-form-item>
              <el-form-item
                label="终点："
                prop="field.终点"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入终点', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入终点" v-model="formData.field.终点" clearable />
              </el-form-item>
              <el-form-item
                label="道路走向："
                prop="field.道路走向"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入道路走向"
                  v-model="formData.field.道路走向"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="道路等级："
                prop="field.道路等级"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择道路等级"
                  v-model="formData.field.道路等级"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.道路等级Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="路段长度："
                prop="field.路段长度"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入路段长度', trigger: 'blur' }]"
              >
                <el-input
                  placeholder="请输入路段长度"
                  v-model="formData.field.路段长度"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="路面铺设材料："
                prop="field.路面铺设材料"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请选择路面铺设材料', trigger: 'blur' }]"
              >
                <el-select
                  placeholder="请选择路面铺设材料"
                  v-model="formData.field.路面铺设材料"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.路面铺设材料Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="总面积："
                prop="field.总面积"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入总面积', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入总面积" v-model="formData.field.总面积" clearable />
              </el-form-item>
              <el-form-item
                label="主路面积："
                prop="field.主路面积"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入主路面积"
                  v-model="formData.field.主路面积"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="辅路面积："
                prop="field.辅路面积"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入辅路面积"
                  v-model="formData.field.辅路面积"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="人行道面积："
                prop="field.人行道面积"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入人行道面积"
                  v-model="formData.field.人行道面积"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="绿化带面积："
                prop="field.绿化带面积"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入绿化带面积"
                  v-model="formData.field.绿化带面积"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="隔离带面积："
                prop="field.隔离带面积"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入隔离带面积"
                  v-model="formData.field.隔离带面积"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="是否有护栏："
                prop="field.是否有护栏"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择是否有护栏"
                  v-model="formData.field.是否有护栏"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.是否有护栏Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="是否有隔音屏："
                prop="field.是否有隔音屏"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择是否有隔音屏"
                  v-model="formData.field.是否有隔音屏"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.是否有隔音屏Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="保洁类型："
                prop="field.保洁类型"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择保洁类型"
                  v-model="formData.field.保洁类型"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.保洁类型Options"
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
      assetProfileId: '6c1b4c40-768d-11f0-bd39-2dc5ccdb39ef' //资产配置id
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
  type: '清扫保洁路段管理', //资产配置，都是写死固定的资产配置
  field: {
    行政区划代码: '', //行政区划代码
    路段名称: '', //路段名称
    起点: '', //起点
    终点: '', //终点
    道路走向: '', //道路走向
    道路等级: '', //道路等级
    路段长度: '', //路段长度
    路面铺设材料: '', //路面铺设材料
    总面积: '', //总面积
    主路面积: '', //主路面积
    辅路面积: '', //辅路面积
    人行道面积: '', //人行道面积
    绿化带面积: '', //绿化带面积
    隔离带面积: '', //隔离带面积
    是否有护栏: '', //是否有护栏
    是否有隔音屏: '', //是否有隔音屏
    保洁类型: '' //保洁类型
  }
}

// 所有选项集合
const optionsLisi = ref({
  //道路等级 选项
  道路等级Options: [
    { label: '一级', value: '1' },
    { label: '二级', value: '2' },
    { label: '三级', value: '3' },
    { label: '四级', value: '4' }
  ],
  //路面铺设材料 选项
  路面铺设材料Options: [
    { label: '柏油', value: '1' },
    { label: '混凝土', value: '2' },
    { label: '砖块', value: '3' }
  ],
  //是否有护栏 选项
  是否有护栏Options: [
    { label: '是', value: '1' },
    { label: '否', value: '0' }
  ],
  //是否有隔音屏 选项
  是否有隔音屏Options: [
    { label: '是', value: '1' },
    { label: '否', value: '0' }
  ],
  //保洁类型 选项
  保洁类型Options: [
    { label: '机械', value: '1' },
    { label: '人工', value: '2' },
    { label: '机械+人工', value: '3' }
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
  drawerConfig.value.title = '清扫保洁路段管理-详细信息'
}
// 点击编辑按钮
const editClick = async (row) => {
  let copyRow = JSON.parse(JSON.stringify(row))
  formData.value = copyRow
  formData.value.field = {}
  formData.value.field.行政区划代码 = tableFieldShow('行政区划代码', row.field) //行政区划代码
  formData.value.field.路段名称 = tableFieldShow('路段名称', row.field) //路段名称
  formData.value.field.起点 = tableFieldShow('起点', row.field) //起点
  formData.value.field.终点 = tableFieldShow('终点', row.field) //终点
  formData.value.field.道路走向 = tableFieldShow('道路走向', row.field) //道路走向
  formData.value.field.道路等级 = tableFieldShow('道路等级', row.field) //道路等级
  formData.value.field.路段长度 = tableFieldShow('路段长度', row.field) //路段长度
  formData.value.field.路面铺设材料 = tableFieldShow('路面铺设材料', row.field) //路面铺设材料
  formData.value.field.总面积 = tableFieldShow('总面积', row.field) //总面积
  formData.value.field.主路面积 = tableFieldShow('主路面积', row.field) //主路面积
  formData.value.field.辅路面积 = tableFieldShow('辅路面积', row.field) //辅路面积
  formData.value.field.人行道面积 = tableFieldShow('人行道面积', row.field) //人行道面积
  formData.value.field.绿化带面积 = tableFieldShow('绿化带面积', row.field) //绿化带面积
  formData.value.field.隔离带面积 = tableFieldShow('隔离带面积', row.field) //隔离带面积
  formData.value.field.是否有护栏 = tableFieldShow('是否有护栏', row.field) //是否有护栏
  formData.value.field.是否有隔音屏 = tableFieldShow('是否有隔音屏', row.field) //是否有隔音屏
  formData.value.field.保洁类型 = tableFieldShow('保洁类型', row.field) //保洁类型

  drawerConfig.value.isDrawer = true
  drawerConfig.value.loading = false
  drawerConfig.value.type = 'edit'
  drawerConfig.value.title = '编辑清扫保洁路段管理'
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
  drawerConfig.value.title = '新增清扫保洁路段管理'
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
  name: '清扫保洁路段管理', //标题名称
  addButName: '新增清扫保洁路段管理' //新增按钮名称
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
  { label: '行政区划代码', visible: true },
  { label: '路段编号', visible: true },
  { label: '路段名称', visible: true },
  { label: '起点', visible: true },
  { label: '终点', visible: true },
  { label: '道路走向', visible: true },
  { label: '道路等级', visible: true },
  { label: '路段长度', visible: true },
  { label: '路面铺设材料', visible: true },
  { label: '总面积', visible: true },
  { label: '主路面积', visible: true },
  { label: '辅路面积', visible: true },
  { label: '人行道面积', visible: true },
  { label: '绿化带面积', visible: true },
  { label: '隔离带面积', visible: true },
  { label: '是否有护栏', visible: true },
  { label: '是否有隔音屏', visible: true },
  { label: '保洁类型', visible: true }
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
