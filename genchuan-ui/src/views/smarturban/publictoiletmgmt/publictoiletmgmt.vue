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
            v-hasPermi="['smarturban:publictoiletmgmt:create']"
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
            label="启用日期"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('启用日期')"
          >
            <template #default="scope">
              {{ tableFieldShow('启用日期', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="建筑面积"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('建筑面积')"
          >
            <template #default="scope">
              {{ tableFieldShow('建筑面积', scope.row.field) }} ㎡
            </template>
          </el-table-column>
          <el-table-column
            label="类型"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('类型')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(tableFieldShow('类型', scope.row.field), optionsLisi.类型Options)
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="分类"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('分类')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(tableFieldShow('分类', scope.row.field), optionsLisi.分类Options)
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="男蹲座位数"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('男蹲座位数')"
          >
            <template #default="scope">
              {{ tableFieldShow('男蹲座位数', scope.row.field) }} 个
            </template>
          </el-table-column>
          <el-table-column
            label="女蹲座位数"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('女蹲座位数')"
          >
            <template #default="scope">
              {{ tableFieldShow('女蹲座位数', scope.row.field) }} 个
            </template>
          </el-table-column>
          <el-table-column
            label="男站位数"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('男站位数')"
          >
            <template #default="scope">
              {{ tableFieldShow('男站位数', scope.row.field) }} 个
            </template>
          </el-table-column>
          <el-table-column
            label="服务时间"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('服务时间')"
          >
            <template #default="scope">
              {{ tableFieldShow('服务时间', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="公共厕所管理形式"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('公共厕所管理形式')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('公共厕所管理形式', scope.row.field),
                  optionsLisi.公共厕所管理形式Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="污物冲洗形式"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('污物冲洗形式')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('污物冲洗形式', scope.row.field),
                  optionsLisi.污物冲洗形式Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="保洁形式"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('保洁形式')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('保洁形式', scope.row.field),
                  optionsLisi.保洁形式Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="无障碍设施"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('无障碍设施')"
          >
            <template #default="scope">
              {{ tableFieldShow('无障碍设施', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="无障碍厕位"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('无障碍厕位')"
          >
            <template #default="scope">
              {{ tableFieldShow('无障碍厕位', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="是否有第三卫生间"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('是否有第三卫生间')"
          >
            <template #default="scope">
              {{ tableFieldShow('是否有第三卫生间', scope.row.field) }}
            </template>
          </el-table-column>
          <el-table-column
            label="收费类型"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('收费类型')"
          >
            <template #default="scope">
              {{
                handleTypeOptions(
                  tableFieldShow('收费类型', scope.row.field),
                  optionsLisi.收费类型Options
                )
              }}
            </template>
          </el-table-column>
          <el-table-column
            label="是否智能化管理"
            min-width="150"
            align="center"
            :show-overflow-tooltip="true"
            v-if="tableColumnShow('是否智能化管理')"
          >
            <template #default="scope">
              {{ tableFieldShow('是否智能化管理', scope.row.field) }}
            </template>
          </el-table-column>

          <el-table-column width="300" fixed="right" label="操作" align="center">
            <template #default="scope">
              <el-button
                type="success"
                :icon="View"
                :plain="true"
                @click="queryClick(scope.row)"
                v-hasPermi="['smarturban:publictoiletmgmt:query']"
                >详细</el-button
              >
              <el-button
                type="primary"
                :icon="EditPen"
                :plain="true"
                @click="editClick(scope.row)"
                v-hasPermi="['smarturban:publictoiletmgmt:update']"
                >编辑</el-button
              >
              <el-button
                type="danger"
                :icon="Delete"
                :plain="true"
                @click="delClick(scope.row)"
                v-hasPermi="['smarturban:publictoiletmgmt:delete']"
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
                ><span>坐标X：</span>{{ tableFieldShow('坐标X', queryData.field) }}</li
              >
              <li class="width50"
                ><span>坐标Y：</span>{{ tableFieldShow('坐标Y', queryData.field) }}</li
              >
              <li class="width50"
                ><span>启用日期：</span>{{ tableFieldShow('启用日期', queryData.field) }}</li
              >
              <li class="width50"
                ><span>建筑面积：</span>{{ tableFieldShow('建筑面积', queryData.field) }}</li
              >
              <li class="width50"
                ><span>类型：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('类型', queryData.field),
                    optionsLisi.类型Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>分类：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('分类', queryData.field),
                    optionsLisi.分类Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>男蹲座位数：</span>{{ tableFieldShow('男蹲座位数', queryData.field) }}</li
              >
              <li class="width50"
                ><span>女蹲座位数：</span>{{ tableFieldShow('女蹲座位数', queryData.field) }}</li
              >
              <li class="width50"
                ><span>男站位数：</span>{{ tableFieldShow('男站位数', queryData.field) }}</li
              >
              <li class="width50"
                ><span>服务时间：</span>{{ tableFieldShow('服务时间', queryData.field) }}</li
              >
              <li class="width50"
                ><span>公共厕所管理形式：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('公共厕所管理形式', queryData.field),
                    optionsLisi.公共厕所管理形式Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>污物冲洗形式：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('污物冲洗形式', queryData.field),
                    optionsLisi.污物冲洗形式Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>保洁形式：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('保洁形式', queryData.field),
                    optionsLisi.保洁形式Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>无障碍设施：</span>{{ tableFieldShow('无障碍设施', queryData.field) }}</li
              >
              <li class="width50"
                ><span>无障碍厕位：</span>{{ tableFieldShow('无障碍厕位', queryData.field) }}</li
              >
              <li class="width50"
                ><span>是否有第三卫生间：</span
                >{{ tableFieldShow('是否有第三卫生间', queryData.field) }}</li
              >
              <li class="width50"
                ><span>收费类型：</span
                >{{
                  handleTypeOptions(
                    tableFieldShow('收费类型', queryData.field),
                    optionsLisi.收费类型Options
                  )
                }}</li
              >
              <li class="width50"
                ><span>是否智能化管理：</span
                >{{ tableFieldShow('是否智能化管理', queryData.field) }}</li
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
                label="启用日期："
                prop="field.启用日期"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入启用日期"
                  v-model="formData.field.启用日期"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="建筑面积："
                prop="field.建筑面积"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请输入建筑面积', trigger: 'blur' }]"
              >
                <el-input placeholder="请输入建筑面积" v-model="formData.field.建筑面积" clearable>
                  <template #append>㎡</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="类型："
                prop="field.类型"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请选择类型', trigger: 'blur' }]"
              >
                <el-select
                  placeholder="请选择类型"
                  v-model="formData.field.类型"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.类型Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="分类："
                prop="field.分类"
                class="edit-content-form-li width50"
                :rules="[{ required: true, message: '请选择分类', trigger: 'blur' }]"
              >
                <el-select
                  placeholder="请选择分类"
                  v-model="formData.field.分类"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.分类Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="男蹲座位数："
                prop="field.男蹲座位数"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入男蹲座位数"
                  v-model="formData.field.男蹲座位数"
                  clearable
                >
                  <template #append>个</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="女蹲座位数："
                prop="field.女蹲座位数"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入女蹲座位数"
                  v-model="formData.field.女蹲座位数"
                  clearable
                >
                  <template #append>个</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="男站位数："
                prop="field.男站位数"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input placeholder="请输入男站位数" v-model="formData.field.男站位数" clearable>
                  <template #append>个</template>
                </el-input>
              </el-form-item>
              <el-form-item
                label="服务时间："
                prop="field.服务时间"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入服务时间"
                  v-model="formData.field.服务时间"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="公共厕所管理形式："
                prop="field.公共厕所管理形式"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择公共厕所管理形式"
                  v-model="formData.field.公共厕所管理形式"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.公共厕所管理形式Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="污物冲洗形式："
                prop="field.污物冲洗形式"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择污物冲洗形式"
                  v-model="formData.field.污物冲洗形式"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.污物冲洗形式Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="保洁形式："
                prop="field.保洁形式"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择保洁形式"
                  v-model="formData.field.保洁形式"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.保洁形式Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="无障碍设施："
                prop="field.无障碍设施"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入无障碍设施"
                  v-model="formData.field.无障碍设施"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="无障碍厕位："
                prop="field.无障碍厕位"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入无障碍厕位"
                  v-model="formData.field.无障碍厕位"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="是否有第三卫生间："
                prop="field.是否有第三卫生间"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入是否有第三卫生间"
                  v-model="formData.field.是否有第三卫生间"
                  clearable
                />
              </el-form-item>
              <el-form-item
                label="收费类型："
                prop="field.收费类型"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-select
                  placeholder="请选择收费类型"
                  v-model="formData.field.收费类型"
                  filterable
                  clearable
                >
                  <el-option
                    v-for="item in optionsLisi.收费类型Options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item
                label="是否智能化管理："
                prop="field.是否智能化管理"
                class="edit-content-form-li width50"
                :rules="[]"
              >
                <el-input
                  placeholder="请输入是否智能化管理"
                  v-model="formData.field.是否智能化管理"
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
      assetProfileId: '320bfb70-7715-11f0-bd39-2dc5ccdb39ef' //资产配置id
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
  type: '公共厕所管理', //资产配置，都是写死固定的资产配置
  field: {
    名称: '', //名称
    地址: '', //地址
    坐标X: '', //坐标X
    坐标Y: '', //坐标Y
    启用日期: '', //启用日期
    建筑面积: '', //建筑面积
    类型: '', //类型
    分类: '', //分类
    男蹲座位数: '', //男蹲座位数
    女蹲座位数: '', //女蹲座位数
    男站位数: '', //男站位数
    服务时间: '', //服务时间
    公共厕所管理形式: '', //公共厕所管理形式
    污物冲洗形式: '', //污物冲洗形式
    保洁形式: '', //保洁形式
    无障碍设施: '', //无障碍设施
    无障碍厕位: '', //无障碍厕位
    是否有第三卫生间: '', //是否有第三卫生间
    收费类型: '', //收费类型
    是否智能化管理: '' //是否智能化管理
  }
}

// 所有选项集合
const optionsLisi = ref({
  //类型 选项
  类型Options: [
    { label: '固定独立式', value: '1' },
    { label: '固定附属式', value: '2' },
    { label: '活动式', value: '3' },
    { label: '其他', value: '9' }
  ],
  //分类 选项
  分类Options: [
    { label: '一类', value: '1' },
    { label: '二类', value: '2' },
    { label: '三类', value: '3' },
    { label: '其他', value: '9' }
  ],
  //公共厕所管理形式 选项
  公共厕所管理形式Options: [
    { label: '环卫部门', value: '1' },
    { label: '非环卫部门', value: '2' },
    { label: '其他', value: '9' }
  ],
  //污物冲洗形式 选项
  污物冲洗形式Options: [
    { label: '接触式', value: '1' },
    { label: '非接触式', value: '2' },
    { label: '免水冲', value: '3' },
    { label: '其他', value: '9' }
  ],
  //保洁形式 选项
  保洁形式Options: [
    { label: '专人', value: '1' },
    { label: '巡回', value: '2' }
  ],
  //收费类型 选项
  收费类型Options: [
    { label: '收费', value: '1' },
    { label: '免费', value: '2' }
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
  drawerConfig.value.title = '公共厕所管理-详细信息'
}
// 点击编辑按钮
const editClick = async (row) => {
  let copyRow = JSON.parse(JSON.stringify(row))
  formData.value = copyRow
  formData.value.field = {}
  formData.value.field.名称 = tableFieldShow('名称', row.field) //名称
  formData.value.field.地址 = tableFieldShow('地址', row.field) //地址
  formData.value.field.坐标X = tableFieldShow('坐标X', row.field) //坐标X
  formData.value.field.坐标Y = tableFieldShow('坐标Y', row.field) //坐标Y
  formData.value.field.启用日期 = tableFieldShow('启用日期', row.field) //启用日期
  formData.value.field.建筑面积 = tableFieldShow('建筑面积', row.field) //建筑面积
  formData.value.field.类型 = tableFieldShow('类型', row.field) //类型
  formData.value.field.分类 = tableFieldShow('分类', row.field) //分类
  formData.value.field.男蹲座位数 = tableFieldShow('男蹲座位数', row.field) //男蹲座位数
  formData.value.field.女蹲座位数 = tableFieldShow('女蹲座位数', row.field) //女蹲座位数
  formData.value.field.男站位数 = tableFieldShow('男站位数', row.field) //男站位数
  formData.value.field.服务时间 = tableFieldShow('服务时间', row.field) //服务时间
  formData.value.field.公共厕所管理形式 = tableFieldShow('公共厕所管理形式', row.field) //公共厕所管理形式
  formData.value.field.污物冲洗形式 = tableFieldShow('污物冲洗形式', row.field) //污物冲洗形式
  formData.value.field.保洁形式 = tableFieldShow('保洁形式', row.field) //保洁形式
  formData.value.field.无障碍设施 = tableFieldShow('无障碍设施', row.field) //无障碍设施
  formData.value.field.无障碍厕位 = tableFieldShow('无障碍厕位', row.field) //无障碍厕位
  formData.value.field.是否有第三卫生间 = tableFieldShow('是否有第三卫生间', row.field) //是否有第三卫生间
  formData.value.field.收费类型 = tableFieldShow('收费类型', row.field) //收费类型
  formData.value.field.是否智能化管理 = tableFieldShow('是否智能化管理', row.field) //是否智能化管理

  drawerConfig.value.isDrawer = true
  drawerConfig.value.loading = false
  drawerConfig.value.type = 'edit'
  drawerConfig.value.title = '编辑公共厕所管理'
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
  drawerConfig.value.title = '新增公共厕所管理'
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
  name: '公共厕所管理', //标题名称
  addButName: '新增公共厕所管理' //新增按钮名称
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
  { label: '坐标X', visible: true },
  { label: '坐标Y', visible: true },
  { label: '启用日期', visible: true },
  { label: '建筑面积', visible: true },
  { label: '类型', visible: true },
  { label: '分类', visible: true },
  { label: '男蹲座位数', visible: true },
  { label: '女蹲座位数', visible: true },
  { label: '男站位数', visible: true },
  { label: '服务时间', visible: true },
  { label: '公共厕所管理形式', visible: true },
  { label: '污物冲洗形式', visible: true },
  { label: '保洁形式', visible: true },
  { label: '无障碍设施', visible: true },
  { label: '无障碍厕位', visible: true },
  { label: '是否有第三卫生间', visible: true },
  { label: '收费类型', visible: true },
  { label: '是否智能化管理', visible: true }
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
