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
            v-hasPermi="['smarturban:bridgemgmt:create']"
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
          <el-form-item label="桥梁名称" prop="name">
            <el-input
              v-model="searchForm.name"
              placeholder="请输入桥梁名称"
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
            label="桥梁名称"
            min-width="150"
            align="center"
            prop="name"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥梁名称')"
          />
          <el-table-column
            label="桥梁编号"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥梁编号')"
          >
            <template #default="scope">
              {{ tableFieldShow('桥梁编号', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="桥梁类型"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥梁类型')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('桥梁类型', scope.row.field),
                  optionsLisi.桥梁类型Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="桥梁跨数"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥梁跨数')"
          >
            <template #default="scope">
              {{ tableFieldShow('桥梁跨数', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="桥梁跨径"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥梁跨径')"
          >
            <template #default="scope">
              {{ tableFieldShow('桥梁跨径', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="桥梁面积"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥梁面积')"
          >
            <template #default="scope">
              {{ tableFieldShow('桥梁面积', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="桥梁长度"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥梁长度')"
          >
            <template #default="scope">
              {{ tableFieldShow('桥梁长度', scope.row.field) }} 米(m)
            </template>
          </el-table-column>
          <el-table-column
            label="桥梁宽度"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥梁宽度')"
          >
            <template #default="scope">
              {{ tableFieldShow('桥梁宽度', scope.row.field) }} 米(m)
            </template>
          </el-table-column>
          <el-table-column
            label="引桥面积"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('引桥面积')"
          >
            <template #default="scope">
              {{ tableFieldShow('引桥面积', scope.row.field) }} 米(m)
            </template>
          </el-table-column>
          <el-table-column
            label="引桥长度"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('引桥长度')"
          >
            <template #default="scope">
              {{ tableFieldShow('引桥长度', scope.row.field) }} 米(m)
            </template>
          </el-table-column>
          <el-table-column
            label="引桥宽度"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('引桥宽度')"
          >
            <template #default="scope">
              {{ tableFieldShow('引桥宽度', scope.row.field) }} 米(m)
            </template>
          </el-table-column>
          <el-table-column
            label="设计荷载"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('设计荷载')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('设计荷载', scope.row.field),
                  optionsLisi.设计荷载Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="主桥横坡"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('主桥横坡')"
          >
            <template #default="scope">
              {{ tableFieldShow('主桥横坡', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="主桥纵坡"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('主桥纵坡')"
          >
            <template #default="scope">
              {{ tableFieldShow('主桥纵坡', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="梁底标高"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('梁底标高')"
          >
            <template #default="scope">
              {{ tableFieldShow('梁底标高', scope.row.field) }} 米(m)
            </template>
          </el-table-column>
          <el-table-column
            label="桥面铺装"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥面铺装')"
          >
            <template #default="scope">
              {{ tableFieldShow('桥面铺装', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="主梁型式"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('主梁型式')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('主梁型式', scope.row.field),
                  optionsLisi.主梁型式Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="主梁数量"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('主梁数量')"
          >
            <template #default="scope">
              {{ tableFieldShow('主梁数量', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="桥塔类型"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥塔类型')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('桥塔类型', scope.row.field),
                  optionsLisi.桥塔类型Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="吊杆类型"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('吊杆类型')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('吊杆类型', scope.row.field),
                  optionsLisi.吊杆类型Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="主缆类型"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('主缆类型')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('主缆类型', scope.row.field),
                  optionsLisi.主缆类型Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="桥台型式"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥台型式')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('桥台型式', scope.row.field),
                  optionsLisi.桥台型式Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="桥墩型式"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥墩型式')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('桥墩型式', scope.row.field),
                  optionsLisi.桥墩型式Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="桥梁伸缩缝类型"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥梁伸缩缝类型')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('桥梁伸缩缝类型', scope.row.field),
                  optionsLisi.桥梁伸缩缝类型Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="支座类型"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('支座类型')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('支座类型', scope.row.field),
                  optionsLisi.支座类型Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="桥梁限高"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥梁限高')"
          >
            <template #default="scope">
              {{ tableFieldShow('桥梁限高', scope.row.field) }} 米(m)
            </template>
          </el-table-column>
          <el-table-column
            label="桥梁限载"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥梁限载')"
          >
            <template #default="scope">
              {{ tableFieldShow('桥梁限载', scope.row.field) }} 吨(t)
            </template>
          </el-table-column>
          <el-table-column
            label="隧道名称"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('隧道名称')"
          >
            <template #default="scope">
              {{ tableFieldShow('隧道名称', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="隧道编号"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('隧道编号')"
          >
            <template #default="scope">
              {{ tableFieldShow('隧道编号', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="洞门类型"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('洞门类型')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('洞门类型', scope.row.field),
                  optionsLisi.洞门类型Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="洞门尺寸"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('洞门尺寸')"
          >
            <template #default="scope">
              {{ tableFieldShow('洞门尺寸', scope.row.field) }} 米(m)
            </template>
          </el-table-column>
          <el-table-column
            label="洞身长度"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('洞身长度')"
          >
            <template #default="scope">
              {{ tableFieldShow('洞身长度', scope.row.field) }} 米(m)
            </template>
          </el-table-column>
          <el-table-column
            label="占地面积"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('占地面积')"
          >
            <template #default="scope">
              {{ tableFieldShow('占地面积', scope.row.field) }} 平方米(m²)
            </template>
          </el-table-column>
          <el-table-column
            label="洞身宽度"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('洞身宽度')"
          >
            <template #default="scope">
              {{ tableFieldShow('洞身宽度', scope.row.field) }} 米(m)
            </template>
          </el-table-column>
          <el-table-column
            label="洞身高度"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('洞身高度')"
          >
            <template #default="scope">
              {{ tableFieldShow('洞身高度', scope.row.field) }} 米(m)
            </template>
          </el-table-column>
          <el-table-column
            label="衬砌型式"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('衬砌型式')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('衬砌型式', scope.row.field),
                  optionsLisi.衬砌型式Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="隧道路面类型"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('隧道路面类型')"
          >
            <template #default="scope">
              {{ tableFieldShow('隧道路面类型', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="应急通道数量"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('应急通道数量')"
          >
            <template #default="scope">
              {{ tableFieldShow('应急通道数量', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="隧道伸缩缝类型"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('隧道伸缩缝类型')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('隧道伸缩缝类型', scope.row.field),
                  optionsLisi.隧道伸缩缝类型Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="隧道限高"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('隧道限高')"
          >
            <template #default="scope">
              {{ tableFieldShow('隧道限高', scope.row.field) }} 米(m)
            </template>
          </el-table-column>
          <el-table-column
            label="积水点名称"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('积水点名称')"
          >
            <template #default="scope">
              {{ tableFieldShow('积水点名称', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="积水点编号"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('积水点编号')"
          >
            <template #default="scope">
              {{ tableFieldShow('积水点编号', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="积水水位"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('积水水位')"
          >
            <template #default="scope">
              {{ tableFieldShow('积水水位', scope.row.field) }} 米(m)
            </template>
          </el-table-column>
          <el-table-column
            label="积水告警"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('积水告警')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('积水告警', scope.row.field),
                  optionsLisi.积水告警Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="桥梁温度"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥梁温度')"
          >
            <template #default="scope">
              {{ tableFieldShow('桥梁温度', scope.row.field) }} 摄氏度(℃)
            </template>
          </el-table-column>
          <el-table-column
            label="桥梁湿度"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥梁湿度')"
          >
            <template #default="scope">
              {{ tableFieldShow('桥梁湿度', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="桥梁应力"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥梁应力')"
          >
            <template #default="scope">
              {{ tableFieldShow('桥梁应力', scope.row.field) }} 帕（Pa）
            </template>
          </el-table-column>
          <el-table-column
            label="桥梁振动频率"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥梁振动频率')"
          >
            <template #default="scope">
              {{ tableFieldShow('桥梁振动频率', scope.row.field) }} 赫兹(Hz)
            </template>
          </el-table-column>
          <el-table-column
            label="桥体倾斜"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥体倾斜')"
          >
            <template #default="scope">
              {{ tableFieldShow('桥体倾斜', scope.row.field) }} 毫米(mm)
            </template>
          </el-table-column>
          <el-table-column
            label="桥梁基础沉降"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥梁基础沉降')"
          >
            <template #default="scope">
              {{ tableFieldShow('桥梁基础沉降', scope.row.field) }} 毫米(mm)
            </template>
          </el-table-column>
          <el-table-column
            label="活载"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('活载')"
          >
            <template #default="scope">
              {{ tableFieldShow('活载', scope.row.field) }} 吨(t)
            </template>
          </el-table-column>
          <el-table-column
            label="裂缝尺寸"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('裂缝尺寸')"
          >
            <template #default="scope">
              {{ tableFieldShow('裂缝尺寸', scope.row.field) }} 毫米(mm)
            </template>
          </el-table-column>
          <el-table-column
            label="吊杆拉力"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('吊杆拉力')"
          >
            <template #default="scope">
              {{ tableFieldShow('吊杆拉力', scope.row.field) }} 千牛(kN)
            </template>
          </el-table-column>
          <el-table-column
            label="吊杆索力"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('吊杆索力')"
          >
            <template #default="scope">
              {{ tableFieldShow('吊杆索力', scope.row.field) }} 千牛(kN)
            </template>
          </el-table-column>
          <el-table-column
            label="桥梁状况指数"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥梁状况指数')"
          >
            <template #default="scope">
              {{ tableFieldShow('桥梁状况指数', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="桥梁结构状况指数"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('桥梁结构状况指数')"
          >
            <template #default="scope">
              {{ tableFieldShow('桥梁结构状况指数', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="人行地下通道状况指数"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('人行地下通道状况指数')"
          >
            <template #default="scope">
              {{ tableFieldShow('人行地下通道状况指数', scope.row.field) }}
            </template>
          </el-table-column>

          <el-table-column width="300" fixed="right" label="操作" align="center">
            <template #default="scope">
              <el-button
                type="success"
                :icon="View"
                :plain="true"
                @click="queryClick(scope.row)"
                v-hasPermi="['smarturban:bridgemgmt:query']"
                >详细</el-button
              >
              <el-button
                type="primary"
                :icon="EditPen"
                :plain="true"
                @click="editClick(scope.row)"
                v-hasPermi="['smarturban:bridgemgmt:update']"
                >编辑</el-button
              >
              <el-button
                type="danger"
                :icon="Delete"
                :plain="true"
                @click="delClick(scope.row)"
                v-hasPermi="['smarturban:bridgemgmt:delete']"
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
              <li class="width50"><span>桥梁名称：</span>{{ queryData.name }}</li>
              <li class="width50"
                ><span>桥梁编号：</span>{{ tableFieldShow('桥梁编号', queryData.field) }}</li
              >
              <li class="width50"
                ><span>桥梁类型：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('桥梁类型', queryData.field),
                    optionsLisi.桥梁类型Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>桥梁跨数：</span>{{ tableFieldShow('桥梁跨数', queryData.field) }}</li
              >
              <li class="width50"
                ><span>桥梁跨径：</span>{{ tableFieldShow('桥梁跨径', queryData.field) }}</li
              >
              <li class="width50"
                ><span>桥梁面积：</span>{{ tableFieldShow('桥梁面积', queryData.field) }}</li
              >
              <li class="width50"
                ><span>桥梁长度：</span>{{ tableFieldShow('桥梁长度', queryData.field) }}</li
              >
              <li class="width50"
                ><span>桥梁宽度：</span>{{ tableFieldShow('桥梁宽度', queryData.field) }}</li
              >
              <li class="width50"
                ><span>引桥面积：</span>{{ tableFieldShow('引桥面积', queryData.field) }}</li
              >
              <li class="width50"
                ><span>引桥长度：</span>{{ tableFieldShow('引桥长度', queryData.field) }}</li
              >
              <li class="width50"
                ><span>引桥宽度：</span>{{ tableFieldShow('引桥宽度', queryData.field) }}</li
              >
              <li class="width50"
                ><span>设计荷载：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('设计荷载', queryData.field),
                    optionsLisi.设计荷载Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>主桥横坡：</span>{{ tableFieldShow('主桥横坡', queryData.field) }}</li
              >
              <li class="width50"
                ><span>主桥纵坡：</span>{{ tableFieldShow('主桥纵坡', queryData.field) }}</li
              >
              <li class="width50"
                ><span>梁底标高：</span>{{ tableFieldShow('梁底标高', queryData.field) }}</li
              >
              <li class="width50"
                ><span>桥面铺装：</span>{{ tableFieldShow('桥面铺装', queryData.field) }}</li
              >
              <li class="width50"
                ><span>主梁型式：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('主梁型式', queryData.field),
                    optionsLisi.主梁型式Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>主梁数量：</span>{{ tableFieldShow('主梁数量', queryData.field) }}</li
              >
              <li class="width50"
                ><span>桥塔类型：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('桥塔类型', queryData.field),
                    optionsLisi.桥塔类型Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>吊杆类型：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('吊杆类型', queryData.field),
                    optionsLisi.吊杆类型Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>主缆类型：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('主缆类型', queryData.field),
                    optionsLisi.主缆类型Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>桥台型式：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('桥台型式', queryData.field),
                    optionsLisi.桥台型式Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>桥墩型式：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('桥墩型式', queryData.field),
                    optionsLisi.桥墩型式Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>桥梁伸缩缝类型：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('桥梁伸缩缝类型', queryData.field),
                    optionsLisi.桥梁伸缩缝类型Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>支座类型：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('支座类型', queryData.field),
                    optionsLisi.支座类型Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>桥梁限高：</span>{{ tableFieldShow('桥梁限高', queryData.field) }}</li
              >
              <li class="width50"
                ><span>桥梁限载：</span>{{ tableFieldShow('桥梁限载', queryData.field) }}</li
              >
              <li class="width50"
                ><span>隧道名称：</span>{{ tableFieldShow('隧道名称', queryData.field) }}</li
              >
              <li class="width50"
                ><span>隧道编号：</span>{{ tableFieldShow('隧道编号', queryData.field) }}</li
              >
              <li class="width50"
                ><span>洞门类型：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('洞门类型', queryData.field),
                    optionsLisi.洞门类型Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>洞门尺寸：</span>{{ tableFieldShow('洞门尺寸', queryData.field) }}</li
              >
              <li class="width50"
                ><span>洞身长度：</span>{{ tableFieldShow('洞身长度', queryData.field) }}</li
              >
              <li class="width50"
                ><span>占地面积：</span>{{ tableFieldShow('占地面积', queryData.field) }}</li
              >
              <li class="width50"
                ><span>洞身宽度：</span>{{ tableFieldShow('洞身宽度', queryData.field) }}</li
              >
              <li class="width50"
                ><span>洞身高度：</span>{{ tableFieldShow('洞身高度', queryData.field) }}</li
              >
              <li class="width50"
                ><span>衬砌型式：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('衬砌型式', queryData.field),
                    optionsLisi.衬砌型式Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>隧道路面类型：</span
                >{{ tableFieldShow('隧道路面类型', queryData.field) }}</li
              >
              <li class="width50"
                ><span>应急通道数量：</span
                >{{ tableFieldShow('应急通道数量', queryData.field) }}</li
              >
              <li class="width50"
                ><span>隧道伸缩缝类型：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('隧道伸缩缝类型', queryData.field),
                    optionsLisi.隧道伸缩缝类型Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>隧道限高：</span>{{ tableFieldShow('隧道限高', queryData.field) }}</li
              >
              <li class="width50"
                ><span>积水点名称：</span>{{ tableFieldShow('积水点名称', queryData.field) }}</li
              >
              <li class="width50"
                ><span>积水点编号：</span>{{ tableFieldShow('积水点编号', queryData.field) }}</li
              >
              <li class="width50"
                ><span>积水水位：</span>{{ tableFieldShow('积水水位', queryData.field) }}</li
              >
              <li class="width50"
                ><span>积水告警：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('积水告警', queryData.field),
                    optionsLisi.积水告警Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>桥梁温度：</span>{{ tableFieldShow('桥梁温度', queryData.field) }}</li
              >
              <li class="width50"
                ><span>桥梁湿度：</span>{{ tableFieldShow('桥梁湿度', queryData.field) }}</li
              >
              <li class="width50"
                ><span>桥梁应力：</span>{{ tableFieldShow('桥梁应力', queryData.field) }}</li
              >
              <li class="width50"
                ><span>桥梁振动频率：</span
                >{{ tableFieldShow('桥梁振动频率', queryData.field) }}</li
              >
              <li class="width50"
                ><span>桥体倾斜：</span>{{ tableFieldShow('桥体倾斜', queryData.field) }}</li
              >
              <li class="width50"
                ><span>桥梁基础沉降：</span
                >{{ tableFieldShow('桥梁基础沉降', queryData.field) }}</li
              >
              <li class="width50"
                ><span>活载：</span>{{ tableFieldShow('活载', queryData.field) }}</li
              >
              <li class="width50"
                ><span>裂缝尺寸：</span>{{ tableFieldShow('裂缝尺寸', queryData.field) }}</li
              >
              <li class="width50"
                ><span>吊杆拉力：</span>{{ tableFieldShow('吊杆拉力', queryData.field) }}</li
              >
              <li class="width50"
                ><span>吊杆索力：</span>{{ tableFieldShow('吊杆索力', queryData.field) }}</li
              >
              <li class="width50"
                ><span>桥梁状况指数：</span
                >{{ tableFieldShow('桥梁状况指数', queryData.field) }}</li
              >
              <li class="width50"
                ><span>桥梁结构状况指数：</span
                >{{ tableFieldShow('桥梁结构状况指数', queryData.field) }}</li
              >
              <li class="width50"
                ><span style="width: 30%">人行地下通道状况指数：</span
                >{{ tableFieldShow('人行地下通道状况指数', queryData.field) }}</li
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
                label="桥梁名称："
                prop="name"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入桥梁名称', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入桥梁名称" v-model="formData.name" clearable />
              </el-form-item>
              <el-form-item
                label="桥梁编号："
                prop="field.桥梁编号"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入桥梁编号', trigger: 'blur' }]"
              >
                <el-input
                  placeholder="请输入桥梁编号"
                  v-model="formData.field.桥梁编号"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="桥梁类型："
                prop="field.桥梁类型"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请选择桥梁类型', trigger: 'blur' }]"
              >
                <el-select
                  placeholder="请选择桥梁类型"
                  v-model="formData.field.桥梁类型"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.桥梁类型Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="桥梁跨数："
                prop="field.桥梁跨数"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入桥梁跨数"
                  v-model="formData.field.桥梁跨数"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="桥梁跨径："
                prop="field.桥梁跨径"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入桥梁跨径"
                  v-model="formData.field.桥梁跨径"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="桥梁面积："
                prop="field.桥梁面积"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入桥梁面积"
                  v-model="formData.field.桥梁面积"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="桥梁长度："
                prop="field.桥梁长度"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入桥梁长度', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入桥梁长度" v-model="formData.field.桥梁长度" clearable>
                  <template #append>米(m)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="桥梁宽度："
                prop="field.桥梁宽度"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入桥梁宽度', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入桥梁宽度" v-model="formData.field.桥梁宽度" clearable>
                  <template #append>米(m)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="引桥面积："
                prop="field.引桥面积"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入引桥面积" v-model="formData.field.引桥面积" clearable>
                  <template #append>米(m)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="引桥长度："
                prop="field.引桥长度"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入引桥长度" v-model="formData.field.引桥长度" clearable>
                  <template #append>米(m)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="引桥宽度："
                prop="field.引桥宽度"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入引桥宽度" v-model="formData.field.引桥宽度" clearable>
                  <template #append>米(m)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="设计荷载："
                prop="field.设计荷载"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请选择设计荷载', trigger: 'blur' }]"
              >
                <el-select
                  placeholder="请选择设计荷载"
                  v-model="formData.field.设计荷载"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.设计荷载Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="主桥横坡："
                prop="field.主桥横坡"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入主桥横坡"
                  v-model="formData.field.主桥横坡"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="主桥纵坡："
                prop="field.主桥纵坡"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入主桥纵坡"
                  v-model="formData.field.主桥纵坡"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="梁底标高："
                prop="field.梁底标高"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入梁底标高" v-model="formData.field.梁底标高" clearable>
                  <template #append>米(m)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="桥面铺装："
                prop="field.桥面铺装"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入桥面铺装"
                  v-model="formData.field.桥面铺装"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="主梁型式："
                prop="field.主梁型式"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择主梁型式"
                  v-model="formData.field.主梁型式"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.主梁型式Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="主梁数量："
                prop="field.主梁数量"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入主梁数量"
                  v-model="formData.field.主梁数量"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="桥塔类型："
                prop="field.桥塔类型"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择桥塔类型"
                  v-model="formData.field.桥塔类型"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.桥塔类型Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="吊杆类型："
                prop="field.吊杆类型"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择吊杆类型"
                  v-model="formData.field.吊杆类型"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.吊杆类型Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="主缆类型："
                prop="field.主缆类型"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择主缆类型"
                  v-model="formData.field.主缆类型"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.主缆类型Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="桥台型式："
                prop="field.桥台型式"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择桥台型式"
                  v-model="formData.field.桥台型式"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.桥台型式Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="桥墩型式："
                prop="field.桥墩型式"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择桥墩型式"
                  v-model="formData.field.桥墩型式"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.桥墩型式Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="桥梁伸缩缝类型："
                prop="field.桥梁伸缩缝类型"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择桥梁伸缩缝类型"
                  v-model="formData.field.桥梁伸缩缝类型"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.桥梁伸缩缝类型Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="支座类型："
                prop="field.支座类型"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择支座类型"
                  v-model="formData.field.支座类型"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.支座类型Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="桥梁限高："
                prop="field.桥梁限高"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入桥梁限高" v-model="formData.field.桥梁限高" clearable>
                  <template #append>米(m)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="桥梁限载："
                prop="field.桥梁限载"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入桥梁限载" v-model="formData.field.桥梁限载" clearable>
                  <template #append>吨(t)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="隧道名称："
                prop="field.隧道名称"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入隧道名称"
                  v-model="formData.field.隧道名称"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="隧道编号："
                prop="field.隧道编号"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入隧道编号"
                  v-model="formData.field.隧道编号"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="洞门类型："
                prop="field.洞门类型"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择洞门类型"
                  v-model="formData.field.洞门类型"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.洞门类型Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="洞门尺寸："
                prop="field.洞门尺寸"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入洞门尺寸" v-model="formData.field.洞门尺寸" clearable>
                  <template #append>米(m)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="洞身长度："
                prop="field.洞身长度"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入洞身长度" v-model="formData.field.洞身长度" clearable>
                  <template #append>米(m)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="占地面积："
                prop="field.占地面积"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入占地面积" v-model="formData.field.占地面积" clearable>
                  <template #append>平方米(m²)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="洞身宽度："
                prop="field.洞身宽度"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入洞身宽度" v-model="formData.field.洞身宽度" clearable>
                  <template #append>米(m)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="洞身高度："
                prop="field.洞身高度"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入洞身高度" v-model="formData.field.洞身高度" clearable>
                  <template #append>米(m)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="衬砌型式："
                prop="field.衬砌型式"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择衬砌型式"
                  v-model="formData.field.衬砌型式"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.衬砌型式Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="隧道路面类型："
                prop="field.隧道路面类型"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入隧道路面类型"
                  v-model="formData.field.隧道路面类型"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="应急通道数量："
                prop="field.应急通道数量"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入应急通道数量"
                  v-model="formData.field.应急通道数量"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="隧道伸缩缝类型："
                prop="field.隧道伸缩缝类型"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择隧道伸缩缝类型"
                  v-model="formData.field.隧道伸缩缝类型"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.隧道伸缩缝类型Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="隧道限高："
                prop="field.隧道限高"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入隧道限高" v-model="formData.field.隧道限高" clearable>
                  <template #append>米(m)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="积水点名称："
                prop="field.积水点名称"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入积水点名称"
                  v-model="formData.field.积水点名称"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="积水点编号："
                prop="field.积水点编号"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入积水点编号"
                  v-model="formData.field.积水点编号"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="积水水位："
                prop="field.积水水位"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入积水水位" v-model="formData.field.积水水位" clearable>
                  <template #append>米(m)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="积水告警："
                prop="field.积水告警"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择积水告警"
                  v-model="formData.field.积水告警"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.积水告警Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="桥梁温度："
                prop="field.桥梁温度"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入桥梁温度" v-model="formData.field.桥梁温度" clearable>
                  <template #append>摄氏度(℃)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="桥梁湿度："
                prop="field.桥梁湿度"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入桥梁湿度"
                  v-model="formData.field.桥梁湿度"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="桥梁应力："
                prop="field.桥梁应力"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入桥梁应力" v-model="formData.field.桥梁应力" clearable>
                  <template #append>帕（Pa）</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="桥梁振动频率："
                prop="field.桥梁振动频率"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入桥梁振动频率"
                  v-model="formData.field.桥梁振动频率"
                  clearable
                >
                  <template #append>赫兹(Hz)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="桥体倾斜："
                prop="field.桥体倾斜"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入桥体倾斜" v-model="formData.field.桥体倾斜" clearable>
                  <template #append>毫米(mm)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="桥梁基础沉降："
                prop="field.桥梁基础沉降"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入桥梁基础沉降"
                  v-model="formData.field.桥梁基础沉降"
                  clearable
                >
                  <template #append>毫米(mm)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="活载："
                prop="field.活载"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入活载" v-model="formData.field.活载" clearable>
                  <template #append>吨(t)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="裂缝尺寸："
                prop="field.裂缝尺寸"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入裂缝尺寸" v-model="formData.field.裂缝尺寸" clearable>
                  <template #append>毫米(mm)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="吊杆拉力："
                prop="field.吊杆拉力"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入吊杆拉力" v-model="formData.field.吊杆拉力" clearable>
                  <template #append>千牛(kN)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="吊杆索力："
                prop="field.吊杆索力"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入吊杆索力" v-model="formData.field.吊杆索力" clearable>
                  <template #append>千牛(kN)</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="桥梁状况指数："
                prop="field.桥梁状况指数"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入桥梁状况指数"
                  v-model="formData.field.桥梁状况指数"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="桥梁结构状况指数："
                prop="field.桥梁结构状况指数"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入桥梁结构状况指数"
                  v-model="formData.field.桥梁结构状况指数"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="人行地下通道状况指数："
                prop="field.人行地下通道状况指数"
                class="edit-content-form-li width50"
                :rules="[]"
                label-width="180px"
              >
                <el-input
                  placeholder="请输入人行地下通道状况指数"
                  v-model="formData.field.人行地下通道状况指数"
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
      assetProfileId: 'ba617d40-765e-11f0-bd39-2dc5ccdb39ef' //资产配置id
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
  type: '桥梁管理', //资产配置，都是写死固定的资产配置
  field: {
    桥梁编号: '', //桥梁编号
    桥梁类型: '', //桥梁类型
    桥梁跨数: '', //桥梁跨数
    桥梁跨径: '', //桥梁跨径
    桥梁面积: '', //桥梁面积
    桥梁长度: '', //桥梁长度
    桥梁宽度: '', //桥梁宽度
    引桥面积: '', //引桥面积
    引桥长度: '', //引桥长度
    引桥宽度: '', //引桥宽度
    设计荷载: '', //设计荷载
    主桥横坡: '', //主桥横坡
    主桥纵坡: '', //主桥纵坡
    梁底标高: '', //梁底标高
    桥面铺装: '', //桥面铺装
    主梁型式: '', //主梁型式
    主梁数量: '', //主梁数量
    桥塔类型: '', //桥塔类型
    吊杆类型: '', //吊杆类型
    主缆类型: '', //主缆类型
    桥台型式: '', //桥台型式
    桥墩型式: '', //桥墩型式
    桥梁伸缩缝类型: '', //桥梁伸缩缝类型
    支座类型: '', //支座类型
    桥梁限高: '', //桥梁限高
    桥梁限载: '', //桥梁限载
    隧道名称: '', //隧道名称
    隧道编号: '', //隧道编号
    洞门类型: '', //洞门类型
    洞门尺寸: '', //洞门尺寸
    洞身长度: '', //洞身长度
    占地面积: '', //占地面积
    洞身宽度: '', //洞身宽度
    洞身高度: '', //洞身高度
    衬砌型式: '', //衬砌型式
    隧道路面类型: '', //隧道路面类型
    应急通道数量: '', //应急通道数量
    隧道伸缩缝类型: '', //隧道伸缩缝类型
    隧道限高: '', //隧道限高
    积水点名称: '', //积水点名称
    积水点编号: '', //积水点编号
    积水水位: '', //积水水位
    积水告警: '', //积水告警
    桥梁温度: '', //桥梁温度
    桥梁湿度: '', //桥梁湿度
    桥梁应力: '', //桥梁应力
    桥梁振动频率: '', //桥梁振动频率
    桥体倾斜: '', //桥体倾斜
    桥梁基础沉降: '', //桥梁基础沉降
    活载: '', //活载
    裂缝尺寸: '', //裂缝尺寸
    吊杆拉力: '', //吊杆拉力
    吊杆索力: '', //吊杆索力
    桥梁状况指数: '', //桥梁状况指数
    桥梁结构状况指数: '', //桥梁结构状况指数
    人行地下通道状况指数: '' //人行地下通道状况指数
  }
}

// 所有选项集合
const optionsLisi = ref({
  //桥梁类型 选项
  桥梁类型Options: [
    { label: '特大桥', value: '1' },
    { label: '大桥', value: '2' },
    { label: '中桥', value: '3' },
    { label: '小桥', value: '4' }
  ],
  //设计荷载 选项
  设计荷载Options: [
    { label: '汽-10', value: '1' },
    { label: '汽-15', value: '2' },
    { label: '汽超20', value: '3' },
    { label: '汽-6', value: '4' },
    { label: '汽-13', value: '5' },
    { label: '汽-8', value: '6' },
    { label: '汽-13', value: '7' },
    { label: '汽-18', value: '8' },
    { label: '汽-26', value: '9' },
    { label: '城-A', value: '10' },
    { label: '城-B', value: '11' },
    { label: '其他', value: '99' }
  ],
  //主梁型式 选项
  主梁型式Options: [
    { label: '板梁', value: '1' },
    { label: '空心板梁', value: '2' },
    { label: 'Ⅱ型梁', value: '3' },
    { label: 'T形梁', value: '4' },
    { label: 'Ⅰ形梁', value: '5' },
    { label: '箱形梁', value: '6' },
    { label: '组合梁', value: '7' },
    { label: '桁架梁', value: '8' },
    { label: '刚架桥梁', value: '9' },
    { label: '斜腿刚构梁', value: '10' },
    { label: '其他', value: '99' }
  ],
  //桥塔类型 选项
  桥塔类型Options: [
    { label: '刚构式（框架式）', value: '1' },
    { label: '桁架式', value: '2' },
    { label: '混合式', value: '3' },
    { label: '其他', value: '4' }
  ],
  //吊杆类型 选项
  吊杆类型Options: [
    { label: '刚性吊杆', value: '1' },
    { label: '柔性吊索', value: '2' },
    { label: '其他', value: '9' }
  ],
  //主缆类型 选项
  主缆类型Options: [
    { label: '钢丝绳主缆', value: '1' },
    { label: '平行丝股主缆', value: '2' },
    { label: '其他', value: '9' }
  ],
  //桥台型式 选项
  桥台型式Options: [
    { label: '钢筋混凝土', value: '1' },
    { label: '其他', value: '2' }
  ],
  //桥墩型式 选项
  桥墩型式Options: [
    { label: '钢筋混凝土', value: '1' },
    { label: '钢', value: '2' },
    { label: '其他', value: '9' }
  ],
  //桥梁伸缩缝类型 选项
  桥梁伸缩缝类型Options: [
    { label: '毛勒缝', value: '1' },
    { label: '梳形钢板', value: '2' },
    { label: 'TST', value: '3' },
    { label: '橡胶板', value: '4' },
    { label: '钢板', value: '5' },
    { label: '自然缝', value: '6' },
    { label: '型钢伸缩缝', value: '7' },
    { label: '其他', value: '9' }
  ],
  //支座类型 选项
  支座类型Options: [
    { label: '板式橡胶', value: '1' },
    { label: '盆式橡胶', value: '2' },
    { label: '橡胶', value: '3' },
    { label: '球形', value: '4' },
    { label: '聚四氟乙烯滑板式', value: '7' }
  ],
  //洞门类型 选项
  洞门类型Options: [
    { label: '端墙式洞门', value: '1' },
    { label: '翼墙式洞门', value: '2' },
    { label: '环框式洞门', value: '3' },
    { label: '遮光棚式洞门', value: '4' },
    { label: '其他', value: '9' }
  ],
  //衬砌型式 选项
  衬砌型式Options: [
    { label: '整体式衬砌', value: '1' },
    { label: '复合式衬砌', value: '2' },
    { label: '喷锚衬砌', value: '3' },
    { label: '其他', value: '9' }
  ],
  //隧道伸缩缝类型 选项
  隧道伸缩缝类型Options: [
    { label: '毛勒缝', value: '1' },
    { label: '梳形钢板', value: '2' },
    { label: 'TST', value: '3' },
    { label: '橡胶板', value: '4' },
    { label: '钢板', value: '5' },
    { label: '自然留缝', value: '6' },
    { label: '型钢伸缩缝', value: '7' },
    { label: '其他', value: '9' }
  ],
  //积水告警 选项
  积水告警Options: [
    { label: '是', value: '1' },
    { label: '否', value: '0' }]
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
  drawerConfig.value.title = '桥梁管理-详细信息'
}
// 点击编辑按钮
const editClick = async (row) => {
  let copyRow = JSON.parse(JSON.stringify(row))
  formData.value = copyRow
  formData.value.field = {}
  formData.value.field.桥梁编号 = tableFieldShow('桥梁编号', row.field) //桥梁编号
  formData.value.field.桥梁类型 = tableFieldShow('桥梁类型', row.field) //桥梁类型
  formData.value.field.桥梁跨数 = tableFieldShow('桥梁跨数', row.field) //桥梁跨数
  formData.value.field.桥梁跨径 = tableFieldShow('桥梁跨径', row.field) //桥梁跨径
  formData.value.field.桥梁面积 = tableFieldShow('桥梁面积', row.field) //桥梁面积
  formData.value.field.桥梁长度 = tableFieldShow('桥梁长度', row.field) //桥梁长度
  formData.value.field.桥梁宽度 = tableFieldShow('桥梁宽度', row.field) //桥梁宽度
  formData.value.field.引桥面积 = tableFieldShow('引桥面积', row.field) //引桥面积
  formData.value.field.引桥长度 = tableFieldShow('引桥长度', row.field) //引桥长度
  formData.value.field.引桥宽度 = tableFieldShow('引桥宽度', row.field) //引桥宽度
  formData.value.field.设计荷载 = tableFieldShow('设计荷载', row.field) //设计荷载
  formData.value.field.主桥横坡 = tableFieldShow('主桥横坡', row.field) //主桥横坡
  formData.value.field.主桥纵坡 = tableFieldShow('主桥纵坡', row.field) //主桥纵坡
  formData.value.field.梁底标高 = tableFieldShow('梁底标高', row.field) //梁底标高
  formData.value.field.桥面铺装 = tableFieldShow('桥面铺装', row.field) //桥面铺装
  formData.value.field.主梁型式 = tableFieldShow('主梁型式', row.field) //主梁型式
  formData.value.field.主梁数量 = tableFieldShow('主梁数量', row.field) //主梁数量
  formData.value.field.桥塔类型 = tableFieldShow('桥塔类型', row.field) //桥塔类型
  formData.value.field.吊杆类型 = tableFieldShow('吊杆类型', row.field) //吊杆类型
  formData.value.field.主缆类型 = tableFieldShow('主缆类型', row.field) //主缆类型
  formData.value.field.桥台型式 = tableFieldShow('桥台型式', row.field) //桥台型式
  formData.value.field.桥墩型式 = tableFieldShow('桥墩型式', row.field) //桥墩型式
  formData.value.field.桥梁伸缩缝类型 = tableFieldShow('桥梁伸缩缝类型', row.field) //桥梁伸缩缝类型
  formData.value.field.支座类型 = tableFieldShow('支座类型', row.field) //支座类型
  formData.value.field.桥梁限高 = tableFieldShow('桥梁限高', row.field) //桥梁限高
  formData.value.field.桥梁限载 = tableFieldShow('桥梁限载', row.field) //桥梁限载
  formData.value.field.隧道名称 = tableFieldShow('隧道名称', row.field) //隧道名称
  formData.value.field.隧道编号 = tableFieldShow('隧道编号', row.field) //隧道编号
  formData.value.field.洞门类型 = tableFieldShow('洞门类型', row.field) //洞门类型
  formData.value.field.洞门尺寸 = tableFieldShow('洞门尺寸', row.field) //洞门尺寸
  formData.value.field.洞身长度 = tableFieldShow('洞身长度', row.field) //洞身长度
  formData.value.field.占地面积 = tableFieldShow('占地面积', row.field) //占地面积
  formData.value.field.洞身宽度 = tableFieldShow('洞身宽度', row.field) //洞身宽度
  formData.value.field.洞身高度 = tableFieldShow('洞身高度', row.field) //洞身高度
  formData.value.field.衬砌型式 = tableFieldShow('衬砌型式', row.field) //衬砌型式
  formData.value.field.隧道路面类型 = tableFieldShow('隧道路面类型', row.field) //隧道路面类型
  formData.value.field.应急通道数量 = tableFieldShow('应急通道数量', row.field) //应急通道数量
  formData.value.field.隧道伸缩缝类型 = tableFieldShow('隧道伸缩缝类型', row.field) //隧道伸缩缝类型
  formData.value.field.隧道限高 = tableFieldShow('隧道限高', row.field) //隧道限高
  formData.value.field.积水点名称 = tableFieldShow('积水点名称', row.field) //积水点名称
  formData.value.field.积水点编号 = tableFieldShow('积水点编号', row.field) //积水点编号
  formData.value.field.积水水位 = tableFieldShow('积水水位', row.field) //积水水位
  formData.value.field.积水告警 = tableFieldShow('积水告警', row.field) //积水告警
  formData.value.field.桥梁温度 = tableFieldShow('桥梁温度', row.field) //桥梁温度
  formData.value.field.桥梁湿度 = tableFieldShow('桥梁湿度', row.field) //桥梁湿度
  formData.value.field.桥梁应力 = tableFieldShow('桥梁应力', row.field) //桥梁应力
  formData.value.field.桥梁振动频率 = tableFieldShow('桥梁振动频率', row.field) //桥梁振动频率
  formData.value.field.桥体倾斜 = tableFieldShow('桥体倾斜', row.field) //桥体倾斜
  formData.value.field.桥梁基础沉降 = tableFieldShow('桥梁基础沉降', row.field) //桥梁基础沉降
  formData.value.field.活载 = tableFieldShow('活载', row.field) //活载
  formData.value.field.裂缝尺寸 = tableFieldShow('裂缝尺寸', row.field) //裂缝尺寸
  formData.value.field.吊杆拉力 = tableFieldShow('吊杆拉力', row.field) //吊杆拉力
  formData.value.field.吊杆索力 = tableFieldShow('吊杆索力', row.field) //吊杆索力
  formData.value.field.桥梁状况指数 = tableFieldShow('桥梁状况指数', row.field) //桥梁状况指数
  formData.value.field.桥梁结构状况指数 = tableFieldShow('桥梁结构状况指数', row.field) //桥梁结构状况指数
  formData.value.field.人行地下通道状况指数 = tableFieldShow('人行地下通道状况指数', row.field) //人行地下通道状况指数

  drawerConfig.value.isDrawer = true
  drawerConfig.value.loading = false
  drawerConfig.value.type = 'edit'
  drawerConfig.value.title = '编辑桥梁管理'
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
  drawerConfig.value.title = '新增桥梁管理'
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
  name: '桥梁管理', //标题名称
  addButName: '新增桥梁管理' //新增按钮名称
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
  { label: '桥梁名称', visible: true },
  { label: '桥梁编号', visible: true },
  { label: '桥梁类型', visible: true },
  { label: '桥梁跨数', visible: true },
  { label: '桥梁跨径', visible: true },
  { label: '桥梁面积', visible: true },
  { label: '桥梁长度', visible: true },
  { label: '桥梁宽度', visible: true },
  { label: '引桥面积', visible: true },
  { label: '引桥长度', visible: true },
  { label: '引桥宽度', visible: true },
  { label: '设计荷载', visible: true },
  { label: '主桥横坡', visible: true },
  { label: '主桥纵坡', visible: true },
  { label: '梁底标高', visible: true },
  { label: '桥面铺装', visible: true },
  { label: '主梁型式', visible: true },
  { label: '主梁数量', visible: true },
  { label: '桥塔类型', visible: true },
  { label: '吊杆类型', visible: true },
  { label: '主缆类型', visible: true },
  { label: '桥台型式', visible: true },
  { label: '桥墩型式', visible: true },
  { label: '桥梁伸缩缝类型', visible: true },
  { label: '支座类型', visible: true },
  { label: '桥梁限高', visible: true },
  { label: '桥梁限载', visible: true },
  { label: '隧道名称', visible: true },
  { label: '隧道编号', visible: true },
  { label: '洞门类型', visible: true },
  { label: '洞门尺寸', visible: true },
  { label: '洞身长度', visible: true },
  { label: '占地面积', visible: true },
  { label: '洞身宽度', visible: true },
  { label: '洞身高度', visible: true },
  { label: '衬砌型式', visible: true },
  { label: '隧道路面类型', visible: true },
  { label: '应急通道数量', visible: true },
  { label: '隧道伸缩缝类型', visible: true },
  { label: '隧道限高', visible: true },
  { label: '积水点名称', visible: true },
  { label: '积水点编号', visible: true },
  { label: '积水水位', visible: true },
  { label: '积水告警', visible: true },
  { label: '桥梁温度', visible: true },
  { label: '桥梁湿度', visible: true },
  { label: '桥梁应力', visible: true },
  { label: '桥梁振动频率', visible: true },
  { label: '桥体倾斜', visible: true },
  { label: '桥梁基础沉降', visible: true },
  { label: '活载', visible: true },
  { label: '裂缝尺寸', visible: true },
  { label: '吊杆拉力', visible: true },
  { label: '吊杆索力', visible: true },
  { label: '桥梁状况指数', visible: true },
  { label: '桥梁结构状况指数', visible: true },
  { label: '人行地下通道状况指数', visible: true }
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
