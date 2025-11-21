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
            v-hasPermi="['smarturban:lawenforcecasehandling:create']"
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
            label="执法程序类别"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('执法程序类别')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('执法程序类别', scope.row.field),
                  optionsLisi.执法程序类别Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="案件来源"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('案件来源')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('案件来源', scope.row.field),
                  optionsLisi.案件来源Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="立案文号"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('立案文号')"
          >
            <template #default="scope">
              {{ tableFieldShow('立案文号', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="案件上报日期"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('案件上报日期')"
          >
            <template #default="scope">
              {{ tableFieldShow('案件上报日期', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="立案批准日期"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('立案批准日期')"
          >
            <template #default="scope">
              {{ tableFieldShow('立案批准日期', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="执法领域名称"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('执法领域名称')"
          >
            <template #default="scope">
              {{ tableFieldShow('执法领域名称', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="违法事实"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('违法事实')"
          >
            <template #default="scope">
              {{ tableFieldShow('违法事实', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="罚款金额"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('罚款金额')"
          >
            <template #default="scope">
              {{ tableFieldShow('罚款金额', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="是否责令改正"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('是否责令改正')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('是否责令改正', scope.row.field),
                  optionsLisi.是否责令改正Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="责令改正内容"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('责令改正内容')"
          >
            <template #default="scope">
              {{ tableFieldShow('责令改正内容', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="责令改正期限"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('责令改正期限')"
          >
            <template #default="scope">
              {{ tableFieldShow('责令改正期限', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="是否涉刑"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('是否涉刑')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('是否涉刑', scope.row.field),
                  optionsLisi.是否涉刑Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="是否重大"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('是否重大')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('是否重大', scope.row.field),
                  optionsLisi.是否重大Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="是否法制审核"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('是否法制审核')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('是否法制审核', scope.row.field),
                  optionsLisi.是否法制审核Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="法制审核日期"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('法制审核日期')"
          >
            <template #default="scope">
              {{ tableFieldShow('法制审核日期', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="是否集体讨论"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('是否集体讨论')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('是否集体讨论', scope.row.field),
                  optionsLisi.是否集体讨论Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="集体讨论日期"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('集体讨论日期')"
          >
            <template #default="scope">
              {{ tableFieldShow('集体讨论日期', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="处罚决定日期"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('处罚决定日期')"
          >
            <template #default="scope">
              {{ tableFieldShow('处罚决定日期', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="送达日期"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('送达日期')"
          >
            <template #default="scope">
              {{ tableFieldShow('送达日期', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="送达方式"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('送达方式')"
          >
            <template #default="scope">
              {{ tableFieldShow('送达方式', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="处罚决定书文号"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('处罚决定书文号')"
          >
            <template #default="scope">
              {{ tableFieldShow('处罚决定书文号', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="是否执行处罚决定"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('是否执行处罚决定')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('是否执行处罚决定', scope.row.field),
                  optionsLisi.是否执行处罚决定Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="执行方式"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('执行方式')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('执行方式', scope.row.field),
                  optionsLisi.执行方式Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="是否分期"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('是否分期')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('是否分期', scope.row.field),
                  optionsLisi.是否分期Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="是否延期"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('是否延期')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('是否延期', scope.row.field),
                  optionsLisi.是否延期Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="执行完成日期"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('执行完成日期')"
          >
            <template #default="scope">
              {{ tableFieldShow('执行完成日期', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="结案类型"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('结案类型')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('结案类型', scope.row.field),
                  optionsLisi.结案类型Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="结案日期"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('结案日期')"
          >
            <template #default="scope">
              {{ tableFieldShow('结案日期', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="非正常结案原因"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('非正常结案原因')"
          >
            <template #default="scope">
              {{ tableFieldShow('非正常结案原因', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="主办人"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('主办人')"
          >
            <template #default="scope">
              {{ tableFieldShow('主办人', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="主办人证件号码"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('主办人证件号码')"
          >
            <template #default="scope">
              {{ tableFieldShow('主办人证件号码', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="协办人"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('协办人')"
          >
            <template #default="scope">
              {{ tableFieldShow('协办人', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="协办人证件号码"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('协办人证件号码')"
          >
            <template #default="scope">
              {{ tableFieldShow('协办人证件号码', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="当前案件阶段"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('当前案件阶段')"
          >
            <template #default="scope">
              {{ tableFieldShow('当前案件阶段', scope.row.field) }}
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
            label="坐标X"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('坐标X')"
          >
            <template #default="scope">
              {{ tableFieldShow('坐标X', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="坐标Y"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('坐标Y')"
          >
            <template #default="scope">
              {{ tableFieldShow('坐标Y', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="执法机关名称"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('执法机关名称')"
          >
            <template #default="scope">
              {{ tableFieldShow('执法机关名称', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="实施机构名称"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('实施机构名称')"
          >
            <template #default="scope">
              {{ tableFieldShow('实施机构名称', scope.row.field) }}
            </template>
          </el-table-column>

          <el-table-column width="300" fixed="right" label="操作" align="center">
            <template #default="scope">
              <el-button
                type="success"
                :icon="View"
                :plain="true"
                @click="queryClick(scope.row)"
                v-hasPermi="['smarturban:lawenforcecasehandling:query']"
                >详细</el-button
              >
              <el-button
                type="primary"
                :icon="EditPen"
                :plain="true"
                @click="editClick(scope.row)"
                v-hasPermi="['smarturban:lawenforcecasehandling:update']"
                >编辑</el-button
              >
              <el-button
                type="danger"
                :icon="Delete"
                :plain="true"
                @click="delClick(scope.row)"
                v-hasPermi="['smarturban:lawenforcecasehandling:delete']"
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
                ><span>执法程序类别：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('执法程序类别', queryData.field),
                    optionsLisi.执法程序类别Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>案件来源：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('案件来源', queryData.field),
                    optionsLisi.案件来源Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>立案文号：</span>{{ tableFieldShow('立案文号', queryData.field) }}</li
              >
              <li class="width50"
                ><span>案件上报日期：</span
                >{{ tableFieldShow('案件上报日期', queryData.field) }}</li
              >
              <li class="width50"
                ><span>立案批准日期：</span
                >{{ tableFieldShow('立案批准日期', queryData.field) }}</li
              >
              <li class="width50"
                ><span>执法领域名称：</span
                >{{ tableFieldShow('执法领域名称', queryData.field) }}</li
              >
              <li class="width50"
                ><span>违法事实：</span>{{ tableFieldShow('违法事实', queryData.field) }}</li
              >
              <li class="width50"
                ><span>罚款金额：</span>{{ tableFieldShow('罚款金额', queryData.field) }}</li
              >
              <li class="width50"
                ><span>是否责令改正：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('是否责令改正', queryData.field),
                    optionsLisi.是否责令改正Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>责令改正内容：</span
                >{{ tableFieldShow('责令改正内容', queryData.field) }}</li
              >
              <li class="width50"
                ><span>责令改正期限：</span
                >{{ tableFieldShow('责令改正期限', queryData.field) }}</li
              >
              <li class="width50"
                ><span>是否涉刑：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('是否涉刑', queryData.field),
                    optionsLisi.是否涉刑Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>是否重大：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('是否重大', queryData.field),
                    optionsLisi.是否重大Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>是否法制审核：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('是否法制审核', queryData.field),
                    optionsLisi.是否法制审核Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>法制审核日期：</span
                >{{ tableFieldShow('法制审核日期', queryData.field) }}</li
              >
              <li class="width50"
                ><span>是否集体讨论：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('是否集体讨论', queryData.field),
                    optionsLisi.是否集体讨论Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>集体讨论日期：</span
                >{{ tableFieldShow('集体讨论日期', queryData.field) }}</li
              >
              <li class="width50"
                ><span>处罚决定日期：</span
                >{{ tableFieldShow('处罚决定日期', queryData.field) }}</li
              >
              <li class="width50"
                ><span>送达日期：</span>{{ tableFieldShow('送达日期', queryData.field) }}</li
              >
              <li class="width50"
                ><span>送达方式：</span>{{ tableFieldShow('送达方式', queryData.field) }}</li
              >
              <li class="width50"
                ><span>处罚决定书文号：</span
                >{{ tableFieldShow('处罚决定书文号', queryData.field) }}</li
              >
              <li class="width50"
                ><span>是否执行处罚决定：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('是否执行处罚决定', queryData.field),
                    optionsLisi.是否执行处罚决定Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>执行方式：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('执行方式', queryData.field),
                    optionsLisi.执行方式Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>是否分期：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('是否分期', queryData.field),
                    optionsLisi.是否分期Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>是否延期：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('是否延期', queryData.field),
                    optionsLisi.是否延期Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>执行完成日期：</span
                >{{ tableFieldShow('执行完成日期', queryData.field) }}</li
              >
              <li class="width50"
                ><span>结案类型：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('结案类型', queryData.field),
                    optionsLisi.结案类型Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>结案日期：</span>{{ tableFieldShow('结案日期', queryData.field) }}</li
              >
              <li class="width50"
                ><span>非正常结案原因：</span
                >{{ tableFieldShow('非正常结案原因', queryData.field) }}</li
              >
              <li class="width50"
                ><span>主办人：</span>{{ tableFieldShow('主办人', queryData.field) }}</li
              >
              <li class="width50"
                ><span>主办人证件号码：</span
                >{{ tableFieldShow('主办人证件号码', queryData.field) }}</li
              >
              <li class="width50"
                ><span>协办人：</span>{{ tableFieldShow('协办人', queryData.field) }}</li
              >
              <li class="width50"
                ><span>协办人证件号码：</span
                >{{ tableFieldShow('协办人证件号码', queryData.field) }}</li
              >
              <li class="width50"
                ><span>当前案件阶段：</span
                >{{ tableFieldShow('当前案件阶段', queryData.field) }}</li
              >
              <li class="width50"
                ><span>地址：</span>{{ tableFieldShow('地址', queryData.field) }}</li
              >
              <li class="width50"
                ><span>坐标X：</span>{{ tableFieldShow('坐标X', queryData.field) }}</li
              >
              <li class="width50"
                ><span>坐标Y：</span>{{ tableFieldShow('坐标Y', queryData.field) }}</li
              >
              <li class="width50"
                ><span>执法机关名称：</span
                >{{ tableFieldShow('执法机关名称', queryData.field) }}</li
              >
              <li class="width50"
                ><span>实施机构名称：</span
                >{{ tableFieldShow('实施机构名称', queryData.field) }}</li
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
                label="执法程序类别："
                prop="field.执法程序类别"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请选择执法程序类别', trigger: 'blur' }]"
              >
                <el-select
                  placeholder="请选择执法程序类别"
                  v-model="formData.field.执法程序类别"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.执法程序类别Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="案件来源："
                prop="field.案件来源"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请选择案件来源', trigger: 'blur' }]"
              >
                <el-select
                  placeholder="请选择案件来源"
                  v-model="formData.field.案件来源"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.案件来源Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="立案文号："
                prop="field.立案文号"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入立案文号"
                  v-model="formData.field.立案文号"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="案件上报日期："
                prop="field.案件上报日期"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入案件上报日期', trigger: 'blur' }]"
              >
                <el-input
                  placeholder="请输入案件上报日期"
                  v-model="formData.field.案件上报日期"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="立案批准日期："
                prop="field.立案批准日期"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入立案批准日期"
                  v-model="formData.field.立案批准日期"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="执法领域名称："
                prop="field.执法领域名称"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入执法领域名称"
                  v-model="formData.field.执法领域名称"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="违法事实："
                prop="field.违法事实"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入违法事实"
                  v-model="formData.field.违法事实"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="罚款金额："
                prop="field.罚款金额"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入罚款金额"
                  v-model="formData.field.罚款金额"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="是否责令改正："
                prop="field.是否责令改正"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择是否责令改正"
                  v-model="formData.field.是否责令改正"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.是否责令改正Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="责令改正内容："
                prop="field.责令改正内容"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入责令改正内容"
                  v-model="formData.field.责令改正内容"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="责令改正期限："
                prop="field.责令改正期限"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入责令改正期限"
                  v-model="formData.field.责令改正期限"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="是否涉刑："
                prop="field.是否涉刑"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择是否涉刑"
                  v-model="formData.field.是否涉刑"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.是否涉刑Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="是否重大："
                prop="field.是否重大"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择是否重大"
                  v-model="formData.field.是否重大"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.是否重大Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="是否法制审核："
                prop="field.是否法制审核"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择是否法制审核"
                  v-model="formData.field.是否法制审核"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.是否法制审核Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="法制审核日期："
                prop="field.法制审核日期"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入法制审核日期"
                  v-model="formData.field.法制审核日期"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="是否集体讨论："
                prop="field.是否集体讨论"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择是否集体讨论"
                  v-model="formData.field.是否集体讨论"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.是否集体讨论Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="集体讨论日期："
                prop="field.集体讨论日期"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入集体讨论日期"
                  v-model="formData.field.集体讨论日期"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="处罚决定日期："
                prop="field.处罚决定日期"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入处罚决定日期"
                  v-model="formData.field.处罚决定日期"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="送达日期："
                prop="field.送达日期"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入送达日期"
                  v-model="formData.field.送达日期"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="送达方式："
                prop="field.送达方式"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入送达方式"
                  v-model="formData.field.送达方式"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="处罚决定书文号："
                prop="field.处罚决定书文号"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入处罚决定书文号"
                  v-model="formData.field.处罚决定书文号"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="是否执行处罚决定："
                prop="field.是否执行处罚决定"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择是否执行处罚决定"
                  v-model="formData.field.是否执行处罚决定"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.是否执行处罚决定Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="执行方式："
                prop="field.执行方式"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择执行方式"
                  v-model="formData.field.执行方式"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.执行方式Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="是否分期："
                prop="field.是否分期"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择是否分期"
                  v-model="formData.field.是否分期"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.是否分期Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="是否延期："
                prop="field.是否延期"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择是否延期"
                  v-model="formData.field.是否延期"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.是否延期Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="执行完成日期："
                prop="field.执行完成日期"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入执行完成日期"
                  v-model="formData.field.执行完成日期"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="结案类型："
                prop="field.结案类型"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择结案类型"
                  v-model="formData.field.结案类型"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.结案类型Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="结案日期："
                prop="field.结案日期"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入结案日期"
                  v-model="formData.field.结案日期"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="非正常结案原因："
                prop="field.非正常结案原因"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入非正常结案原因"
                  v-model="formData.field.非正常结案原因"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="主办人："
                prop="field.主办人"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入主办人" v-model="formData.field.主办人" clearable />
              </el-form-item>
              <el-form-item
                label="主办人证件号码："
                prop="field.主办人证件号码"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入主办人证件号码"
                  v-model="formData.field.主办人证件号码"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="协办人："
                prop="field.协办人"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入协办人" v-model="formData.field.协办人" clearable />
              </el-form-item>
              <el-form-item
                label="协办人证件号码："
                prop="field.协办人证件号码"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入协办人证件号码"
                  v-model="formData.field.协办人证件号码"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="当前案件阶段："
                prop="field.当前案件阶段"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入当前案件阶段"
                  v-model="formData.field.当前案件阶段"
                  clearable
                />
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
                label="坐标X："
                prop="field.坐标X"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入坐标X" v-model="formData.field.坐标X" clearable />
              </el-form-item>
              <el-form-item
                label="坐标Y："
                prop="field.坐标Y"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入坐标Y" v-model="formData.field.坐标Y" clearable />
              </el-form-item>
              <el-form-item
                label="执法机关名称："
                prop="field.执法机关名称"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入执法机关名称"
                  v-model="formData.field.执法机关名称"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="实施机构名称："
                prop="field.实施机构名称"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入实施机构名称"
                  v-model="formData.field.实施机构名称"
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
      assetProfileId: '799c4310-7723-11f0-bd39-2dc5ccdb39ef' //资产配置id
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
  type: '执法办案', //资产配置，都是写死固定的资产配置
  field: {
    执法程序类别: '', //执法程序类别
    案件来源: '', //案件来源
    立案文号: '', //立案文号
    案件上报日期: '', //案件上报日期
    立案批准日期: '', //立案批准日期
    执法领域名称: '', //执法领域名称
    违法事实: '', //违法事实
    罚款金额: '', //罚款金额
    是否责令改正: '', //是否责令改正
    责令改正内容: '', //责令改正内容
    责令改正期限: '', //责令改正期限
    是否涉刑: '', //是否涉刑
    是否重大: '', //是否重大
    是否法制审核: '', //是否法制审核
    法制审核日期: '', //法制审核日期
    是否集体讨论: '', //是否集体讨论
    集体讨论日期: '', //集体讨论日期
    处罚决定日期: '', //处罚决定日期
    送达日期: '', //送达日期
    送达方式: '', //送达方式
    处罚决定书文号: '', //处罚决定书文号
    是否执行处罚决定: '', //是否执行处罚决定
    执行方式: '', //执行方式
    是否分期: '', //是否分期
    是否延期: '', //是否延期
    执行完成日期: '', //执行完成日期
    结案类型: '', //结案类型
    结案日期: '', //结案日期
    非正常结案原因: '', //非正常结案原因
    主办人: '', //主办人
    主办人证件号码: '', //主办人证件号码
    协办人: '', //协办人
    协办人证件号码: '', //协办人证件号码
    当前案件阶段: '', //当前案件阶段
    地址: '', //地址
    坐标X: '', //坐标X
    坐标Y: '', //坐标Y
    执法机关名称: '', //执法机关名称
    实施机构名称: '' //实施机构名称
  }
}

// 所有选项集合
const optionsLisi = ref({
  //执法程序类别 选项
  执法程序类别Options: [
    { label: '普通程序', value: '1' },
    { label: '责令改正', value: '2' },
    { label: '当场处罚', value: '3' },
    { label: '温馨提示', value: '4' },
    { label: '投诉受理', value: '5' }
  ],
  //案件来源 选项
  案件来源Options: [
    { label: '日常检查', value: '1' },
    { label: '投诉', value: '2' },
    { label: '举报', value: '3' },
    { label: '上级机关交办', value: '4' },
    { label: '其他机关移送', value: '5' },
    { label: '媒体曝光', value: '6' },
    { label: '其他', value: '9' }
  ],
  //是否责令改正 选项
  是否责令改正Options: [
    { label: '是', value: '1' },
    { label: '否', value: '0' }
  ],
  //是否涉刑 选项
  是否涉刑Options: [
    { label: '是', value: '1' },
    { label: '否', value: '0' }
  ],
  //是否重大 选项
  是否重大Options: [
    { label: '是', value: '1' },
    { label: '否', value: '0' }
  ],
  //是否法制审核 选项
  是否法制审核Options: [
    { label: '是', value: '1' },
    { label: '否', value: '0' }
  ],
  //是否集体讨论 选项
  是否集体讨论Options: [
    { label: '是', value: '1' },
    { label: '否', value: '0' }
  ],
  //是否执行处罚决定 选项
  是否执行处罚决定Options: [
    { label: '是', value: '1' },
    { label: '否', value: '0' }
  ],
  //执行方式 选项
  执行方式Options: [
    { label: '主动履行', value: '1' },
    { label: '自主强制执行', value: '2' },
    { label: '申请法院强制执行', value: '3' }
  ],
  //是否分期 选项
  是否分期Options: [
    { label: '是', value: '1' },
    { label: '否', value: '0' }
  ],
  //是否延期 选项
  是否延期Options: [
    { label: '是', value: '1' },
    { label: '否', value: '0' }
  ],
  //结案类型 选项
  结案类型Options: [
    { label: '正常结案', value: '1' },
    { label: '撤销立案', value: '2' },
    { label: '不予处罚', value: '3' },
    { label: '撤销原处罚决定', value: '4' },
    { label: '终结', value: '5' }
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
  drawerConfig.value.title = '执法办案-详细信息'
}
// 点击编辑按钮
const editClick = async (row) => {
  let copyRow = JSON.parse(JSON.stringify(row))
  formData.value = copyRow
  formData.value.field = {}
  formData.value.field.执法程序类别 = tableFieldShow('执法程序类别', row.field) //执法程序类别
  formData.value.field.案件来源 = tableFieldShow('案件来源', row.field) //案件来源
  formData.value.field.立案文号 = tableFieldShow('立案文号', row.field) //立案文号
  formData.value.field.案件上报日期 = tableFieldShow('案件上报日期', row.field) //案件上报日期
  formData.value.field.立案批准日期 = tableFieldShow('立案批准日期', row.field) //立案批准日期
  formData.value.field.执法领域名称 = tableFieldShow('执法领域名称', row.field) //执法领域名称
  formData.value.field.违法事实 = tableFieldShow('违法事实', row.field) //违法事实
  formData.value.field.罚款金额 = tableFieldShow('罚款金额', row.field) //罚款金额
  formData.value.field.是否责令改正 = tableFieldShow('是否责令改正', row.field) //是否责令改正
  formData.value.field.责令改正内容 = tableFieldShow('责令改正内容', row.field) //责令改正内容
  formData.value.field.责令改正期限 = tableFieldShow('责令改正期限', row.field) //责令改正期限
  formData.value.field.是否涉刑 = tableFieldShow('是否涉刑', row.field) //是否涉刑
  formData.value.field.是否重大 = tableFieldShow('是否重大', row.field) //是否重大
  formData.value.field.是否法制审核 = tableFieldShow('是否法制审核', row.field) //是否法制审核
  formData.value.field.法制审核日期 = tableFieldShow('法制审核日期', row.field) //法制审核日期
  formData.value.field.是否集体讨论 = tableFieldShow('是否集体讨论', row.field) //是否集体讨论
  formData.value.field.集体讨论日期 = tableFieldShow('集体讨论日期', row.field) //集体讨论日期
  formData.value.field.处罚决定日期 = tableFieldShow('处罚决定日期', row.field) //处罚决定日期
  formData.value.field.送达日期 = tableFieldShow('送达日期', row.field) //送达日期
  formData.value.field.送达方式 = tableFieldShow('送达方式', row.field) //送达方式
  formData.value.field.处罚决定书文号 = tableFieldShow('处罚决定书文号', row.field) //处罚决定书文号
  formData.value.field.是否执行处罚决定 = tableFieldShow('是否执行处罚决定', row.field) //是否执行处罚决定
  formData.value.field.执行方式 = tableFieldShow('执行方式', row.field) //执行方式
  formData.value.field.是否分期 = tableFieldShow('是否分期', row.field) //是否分期
  formData.value.field.是否延期 = tableFieldShow('是否延期', row.field) //是否延期
  formData.value.field.执行完成日期 = tableFieldShow('执行完成日期', row.field) //执行完成日期
  formData.value.field.结案类型 = tableFieldShow('结案类型', row.field) //结案类型
  formData.value.field.结案日期 = tableFieldShow('结案日期', row.field) //结案日期
  formData.value.field.非正常结案原因 = tableFieldShow('非正常结案原因', row.field) //非正常结案原因
  formData.value.field.主办人 = tableFieldShow('主办人', row.field) //主办人
  formData.value.field.主办人证件号码 = tableFieldShow('主办人证件号码', row.field) //主办人证件号码
  formData.value.field.协办人 = tableFieldShow('协办人', row.field) //协办人
  formData.value.field.协办人证件号码 = tableFieldShow('协办人证件号码', row.field) //协办人证件号码
  formData.value.field.当前案件阶段 = tableFieldShow('当前案件阶段', row.field) //当前案件阶段
  formData.value.field.地址 = tableFieldShow('地址', row.field) //地址
  formData.value.field.坐标X = tableFieldShow('坐标X', row.field) //坐标X
  formData.value.field.坐标Y = tableFieldShow('坐标Y', row.field) //坐标Y
  formData.value.field.执法机关名称 = tableFieldShow('执法机关名称', row.field) //执法机关名称
  formData.value.field.实施机构名称 = tableFieldShow('实施机构名称', row.field) //实施机构名称

  drawerConfig.value.isDrawer = true
  drawerConfig.value.loading = false
  drawerConfig.value.type = 'edit'
  drawerConfig.value.title = '编辑执法办案'
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
  drawerConfig.value.title = '新增执法办案'
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
  name: '执法办案', //标题名称
  addButName: '新增执法办案' //新增按钮名称
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
  { label: '执法程序类别', visible: true },
  { label: '案件来源', visible: true },
  { label: '立案文号', visible: true },
  { label: '案件上报日期', visible: true },
  { label: '立案批准日期', visible: true },
  { label: '执法领域名称', visible: true },
  { label: '违法事实', visible: true },
  { label: '罚款金额', visible: true },
  { label: '是否责令改正', visible: true },
  { label: '责令改正内容', visible: true },
  { label: '责令改正期限', visible: true },
  { label: '是否涉刑', visible: true },
  { label: '是否重大', visible: true },
  { label: '是否法制审核', visible: true },
  { label: '法制审核日期', visible: true },
  { label: '是否集体讨论', visible: true },
  { label: '集体讨论日期', visible: true },
  { label: '处罚决定日期', visible: true },
  { label: '送达日期', visible: true },
  { label: '送达方式', visible: true },
  { label: '处罚决定书文号', visible: true },
  { label: '是否执行处罚决定', visible: true },
  { label: '执行方式', visible: true },
  { label: '是否分期', visible: true },
  { label: '是否延期', visible: true },
  { label: '执行完成日期', visible: true },
  { label: '结案类型', visible: true },
  { label: '结案日期', visible: true },
  { label: '非正常结案原因', visible: true },
  { label: '主办人', visible: true },
  { label: '主办人证件号码', visible: true },
  { label: '协办人', visible: true },
  { label: '协办人证件号码', visible: true },
  { label: '当前案件阶段', visible: true },
  { label: '地址', visible: true },
  { label: '坐标X', visible: true },
  { label: '坐标Y', visible: true },
  { label: '执法机关名称', visible: true },
  { label: '实施机构名称', visible: true }
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
