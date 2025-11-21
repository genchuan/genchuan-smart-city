<template>
  <div class="apiDoc-css">
    <ul>
      <li v-for="(item,key) in apiArray" :key="key">
        <div class="header" @click="headerShowClick(item.show,key)">
          <div class="n">
            <span class="s1">{{ item.name }}</span><span class="s2">{{ item.description }}</span>
          </div>
          <el-icon v-show="item.show">
            <ArrowUpBold/>
          </el-icon>
          <el-icon v-show="!item.show">
            <ArrowDownBold/>
          </el-icon>
        </div>
        <div class="content" v-show="item.show">
          <el-collapse>


            <!--delete-->
            <!--class="collapse-item-css collapse-item-post"-->
            <el-collapse-item  v-for="(item2,key2) in item.operations" :key="key2"
                              :class="[
            'collapse-item-css',
            item2.method === 'get' ? 'collapse-item-get' :
            item2.method === 'post' ? 'collapse-item-post' :
            item2.method === 'put' ? 'collapse-item-put' :
            item2.method === 'delete' ? 'collapse-item-delete' : ''
            ]">
              <template #title="{ isActive }">
                <div :class="['collapse-title', { 'is-active': isActive }]">
                  <span class="n100 n1">{{ item2.method?.toUpperCase() }}</span>
                  <span class="n2">{{ item2.path }}</span>
                  <span class="n3">{{ item2.summary }}</span>
                </div>
              </template>
              <div class="collapse-item-content">
                <div class="main">
                  <div class="n1" style="white-space: pre-line;">
                   {{item2.description}}
                  </div>
                  <div class="n2">
                    <span>Parameters</span>
                  </div>
                  <div class="n3">
                    <ul v-if="item2.parameters">
                      <li class="c1">
                        <div>Name</div>
                        <div>Description</div>
                      </li>
                      <li v-for="(item3,key3) in item2.parameters" :key="key3">
                        <div>{{ item3.name }} <span class="red" v-if="item3.required">*</span></div>
                        <div>{{ item3.description }}
                        </div>
                      </li>
                    </ul>
                    <div v-else>No parameters</div>
                  </div>
                  <div class="n4" v-if="item2.requestBody">
                    Request body
                  </div>
                  <!--body案例-->
<!--                  <div class="n5" v-if="item2.requestBody">
                    <div>
                      {
                      "comment": {}
                      }
                    </div>
                  </div>-->
                  <div class="n4">
                    Possible responses
                  </div>
                  <div class="n3">
                    <ul>
                      <li class="c1">
                        <div>Code</div>
                        <div>Description</div>
                      </li>
                      <li  v-for="([status, response], index) in Object.entries(item2.responses)" :key="index" >
                        <div>{{ status }}</div>
                        <div>
                          <div>{{ response.description }}</div>
<!--                          <div class="c2">
装案例
                          </div>-->
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </el-collapse-item>
          </el-collapse>
        </div>
      </li>

      <!--      <li>
              <div class="header">
                <div class="n">
                  <span class="s1">admin-controller</span><span class="s2">Admin Controller</span>
                </div>
                <el-icon :size="20">
                  <ArrowRight/>
                </el-icon>
                <el-icon>
                  <ArrowDownBold/>
                </el-icon>
                &lt;!&ndash;<el-icon><ArrowUpBold /></el-icon>&ndash;&gt;
              </div>
              <div class="content">
                <el-collapse>
                  <el-collapse-item name="1" class="collapse-item-css collapse-item-post">
                    <template #title="{ isActive }">
                      <div :class="['collapse-title', { 'is-active': isActive }]">
                        <span class="n100 n1">POST</span>
                        <span class="n2">/api/alarm</span>
                        <span class="n3">Create or Update Alarm (saveAlarm)</span>
                      </div>
                    </template>
                    <div class="collapse-item-content">
                      <div class="main">
                        <div class="n1">
                          Deletes the Alarm comment. Referencing non-existing Alarm comment Id will cause
                          an error.
                          Available for users with 'TENANT_ADMIN' or 'CUSTOMER_USER' authority.
                        </div>
                        <div class="n2">
                          <span>Parameters</span>
                        </div>
                        <div class="n3">
                          <ul>
                            <li class="c1">
                              <div>Name</div>
                              <div>Description</div>
                            </li>
                            <li>
                              <div>alarmId <span class="red">*</span></div>
                              <div>A string value representing the alarm id. For example,
                                '784f394c-42b6-435a-983c-b7beff2784f9'
                              </div>
                            </li>
                          </ul>
                        </div>
                        <div class="n4">
                          Request body
                        </div>
                        <div class="n5">
                          <div>
                            {
                            "comment": {}
                            }
                          </div>
                        </div>
                        <div class="n4">
                          Possible responses
                        </div>
                        <div class="n3">
                          <ul>
                            <li class="c1">
                              <div>Code</div>
                              <div>Description</div>
                            </li>
                            <li>
                              <div>200</div>
                              <div>
                                <div>OK</div>
                                <div class="c2">{
                                  "alarmId": {
                                  "id": "784f394c-42b6-435a-983c-b7beff2784f9",
                                  "entityType": "ALARM"
                                  },
                                  "userId": {
                                  "id": "784f394c-42b6-435a-983c-b7beff2784f9",
                                  "entityType": "USER"
                                  },
                                  "type": "SYSTEM/OTHER",
                                  "comment": {},
                                  "id": {
                                  "id": "784f394c-42b6-435a-983c-b7beff2784f9"
                                  },
                                  "createdTime": 1634058704567,
                                  "name": "Please take a look"
                                  }
                                </div>
                              </div>
                            </li>
                            <li>
                              <div>200</div>
                              <div>
                                <div>OK</div>
                                <div class="c2">{
                                  "alarmId": {
                                  "id": "784f394c-42b6-435a-983c-b7beff2784f9",
                                  "entityType": "ALARM"
                                  },
                                  "userId": {
                                  "id": "784f394c-42b6-435a-983c-b7beff2784f9",
                                  "entityType": "USER"
                                  },
                                  "type": "SYSTEM/OTHER",
                                  "comment": {},
                                  "id": {
                                  "id": "784f394c-42b6-435a-983c-b7beff2784f9"
                                  },
                                  "createdTime": 1634058704567,
                                  "name": "Please take a look"
                                  }
                                </div>
                              </div>
                            </li>
                          </ul>
                        </div>
                      </div>
                    </div>
                  </el-collapse-item>

                  <el-collapse-item name="2" class="collapse-item-css collapse-item-put">
                    <template #title="{ isActive }">
                      <div :class="['collapse-title', { 'is-active': isActive }]">
                        <span class="n100 n1">POST</span>
                        <span class="n2">/api/alarm</span>
                        <span class="n3">Create or Update Alarm (saveAlarm)</span>
                      </div>
                    </template>
                    <div class="collapse-item-content">
                      <div class="main">
                        <div class="n1">
                          Deletes the Alarm comment. Referencing non-existing Alarm comment Id will cause
                          an error.
                          Available for users with 'TENANT_ADMIN' or 'CUSTOMER_USER' authority.
                        </div>
                        <div class="n2">
                          <span>Parameters</span>
                        </div>
                        <div class="n3">
                          <ul>
                            <li class="c1">
                              <div>Name</div>
                              <div>Description</div>
                            </li>
                            <li>
                              <div>alarmId <span class="red">*</span></div>
                              <div>A string value representing the alarm id. For example,
                                '784f394c-42b6-435a-983c-b7beff2784f9'
                              </div>
                            </li>
                          </ul>
                        </div>
                        <div class="n4">
                          Request body
                        </div>
                        <div class="n5">
                          <div>
                            {
                            "comment": {}
                            }
                          </div>
                        </div>
                        <div class="n4">
                          Possible responses
                        </div>
                        <div class="n3">
                          <ul>
                            <li class="c1">
                              <div>Code</div>
                              <div>Description</div>
                            </li>
                            <li>
                              <div>200</div>
                              <div>
                                <div>OK</div>
                                <div class="c2">{
                                  "alarmId": {
                                  "id": "784f394c-42b6-435a-983c-b7beff2784f9",
                                  "entityType": "ALARM"
                                  },
                                  "userId": {
                                  "id": "784f394c-42b6-435a-983c-b7beff2784f9",
                                  "entityType": "USER"
                                  },
                                  "type": "SYSTEM/OTHER",
                                  "comment": {},
                                  "id": {
                                  "id": "784f394c-42b6-435a-983c-b7beff2784f9"
                                  },
                                  "createdTime": 1634058704567,
                                  "name": "Please take a look"
                                  }
                                </div>
                              </div>
                            </li>
                            <li>
                              <div>200</div>
                              <div>
                                <div>OK</div>
                                <div class="c2">{
                                  "alarmId": {
                                  "id": "784f394c-42b6-435a-983c-b7beff2784f9",
                                  "entityType": "ALARM"
                                  },
                                  "userId": {
                                  "id": "784f394c-42b6-435a-983c-b7beff2784f9",
                                  "entityType": "USER"
                                  },
                                  "type": "SYSTEM/OTHER",
                                  "comment": {},
                                  "id": {
                                  "id": "784f394c-42b6-435a-983c-b7beff2784f9"
                                  },
                                  "createdTime": 1634058704567,
                                  "name": "Please take a look"
                                  }
                                </div>
                              </div>
                            </li>
                          </ul>
                        </div>
                      </div>
                    </div>
                  </el-collapse-item>

                </el-collapse>
              </div>
            </li>-->
    </ul>
  </div>
</template>

<!-- vue3写法 -->
<script lang="ts" setup name="test">
import {ref, reactive, onMounted} from 'vue'
import {ArrowDownBold, ArrowUpBold} from "@element-plus/icons-vue";
import {thingsboardApiDoc} from "@/api/genchuan/thingsBoard";

let apiArray = ref([]);

let headerShowClick = (show1, key) => {
  apiArray.value[key].show = !show1;
}

// 获取接口数据
thingsboardApiDoc().then((result) => {
  let tags = result.tags;
  let paths = result.paths;
  let schemas=result.components.schemas;//
  const res = tags.map(tag => {
    const pathEntries = Object.entries(paths).filter(([path, methods]) => {
      return Object.values(methods).some(operation => {
        return operation.tags && operation.tags.includes(tag.name);
      });
    });

    const operations = pathEntries.flatMap(([path, methods]) => {
      return Object.entries(methods).map(([method, operation]) => ({
        path,
        method,
        ...operation
      }));
    });

    return {
      ...tag,
      show: true, // 新增属性
      operations
    };
  });
  apiArray.value = res;
  console.log(res)
})


// 挂载完毕
onMounted(() => {
  console.log('挂载完毕')
})
</script>

<style lang="scss" scoped>
.apiDoc-css {
  background: #fff;
  padding: 16px;
  border-radius: 15px;

  ul {
    list-style: none;
  }

  & > ul {

    color: rgb(59, 65, 81);

    & > li {
      .header {
        cursor: pointer;
        display: flex;
        flex-wrap: nowrap;
        justify-content: space-between;
        align-items: center;
        flex-direction: row;
        padding-bottom: 15px;
        padding-top: 15px;

        .n {
          display: flex;
          flex-wrap: nowrap;
          justify-content: flex-start;
          align-items: center;
          flex-direction: row;

          .s1 {
            font-size: 24px;
            font-weight: 700;
          }

          .s2 {
            font-size: 14px;
            margin-left: 18px;
          }
        }

      }

      .content {
        //公用
        :deep(.collapse-item-css>button:nth-of-type(1)) {
          padding: 8px;
          //border-radius: 8px;

          .n100 {
            padding: 8px 16px;
            color: #fff;
            border-radius: 3px;
          }

          .n2 {
            color: rgb(59, 65, 81);
            font-size: 18px;
            padding-left: 18px;
            display: inline-block;
          }

          .n3 {
            color: rgb(59, 65, 81);
            font-size: 13px;
            padding-left: 18px;
            display: inline-block;
          }
        }

        .collapse-item-css {
          margin-top: 12px;

          .collapse-item-content {
            .main {
              //border-radius: 0 0 8px 8px;

              .n1 {
                padding: 15px 20px;
              }

              .n2 {
                padding: 15px 20px;

                & > span {
                  display: inline-block;
                  font-size: 14px;
                  font-weight: 600;
                  padding: 0 5px 5px 5px;
                }
              }

              .n3 {
                padding: 15px 20px;

                li {
                  padding-top: 12px;
                  color: rgb(59, 65, 81);
                  font-size: 16px;
                  display: flex;
                  flex-wrap: nowrap;
                  justify-content: flex-start;
                  align-items: flex-start;
                  flex-direction: row;

                  & > div:nth-of-type(1) {
                    width: 150px;
                    flex: 0 0 auto;
                  }

                  .red {
                    color: red;
                  }
                }

                .c1 {
                  border-bottom: 1px solid rgb(51, 51, 51);
                  padding-bottom: 12px;
                }

                .c2 {
                  background: rgb(51, 51, 51);
                  padding: 15px 20px;
                  color: #fff;
                  border-radius: 3px;
                }
              }

              .n4 {
                padding: 15px 20px;
                font-size: 14px;
                font-weight: 600;
              }

              .n5 {
                color: #fff;
                padding: 0 20px 0;

                & > div {
                  background: rgb(51, 51, 51);
                  padding: 15px 20px;
                  border-radius: 3px;
                }
              }
            }
          }
        }

        //post
        .collapse-item-post {
          .collapse-item-content {
            .main {
              border: 1px solid rgb(73, 204, 144);
              border-top: 0;

              .n1 {
                background: rgb(232, 246, 240);
              }

              .n2 {
                & > span {
                  color: rgb(59, 65, 81);
                  border-bottom: 3px solid rgb(73, 204, 144);
                }
              }

              .n3 {
                background: rgb(232, 246, 240);
              }
            }
          }
        }

        :deep(.collapse-item-post>button:nth-of-type(1)) {
          background: rgb(232, 246, 240);
          border: 1px solid rgb(73, 204, 144);

          .collapse-title {
            .n1 {
              background: rgb(73, 204, 144);
            }
          }
        }

        //get
        .collapse-item-get {
          .collapse-item-content {
            .main {
              border: 1px solid rgb(97, 175, 254);
              border-top: 0;

              .n1 {
                background: rgb(235, 243, 251);
              }

              .n2 {
                & > span {
                  color: rgb(59, 65, 81);
                  border-bottom: 3px solid rgb(97, 175, 254);
                }
              }

              .n3 {
                background: rgb(235, 243, 251);
              }
            }
          }
        }

        :deep(.collapse-item-get>button:nth-of-type(1)) {
          background: rgb(235, 243, 251);
          border: 1px solid rgb(97, 175, 254);

          .collapse-title {
            .n1 {
              background: rgb(97, 175, 254);
            }
          }
        }


        //del
        .collapse-item-delete {
          .collapse-item-content {
            .main {
              border: 1px solid rgb(249, 62, 62);
              border-top: 0;

              .n1 {
                background: rgb(250, 231, 231);
              }

              .n2 {
                & > span {
                  color: rgb(59, 65, 81);
                  border-bottom: 3px solid rgb(249, 62, 62);
                }
              }

              .n3 {
                background: rgb(250, 231, 231);
              }
            }
          }
        }

        :deep(.collapse-item-delete>button:nth-of-type(1)) {
          background: rgb(250, 231, 231);
          border: 1px solid rgb(249, 62, 62);

          .collapse-title {
            .n1 {
              background: rgb(249, 62, 62);
            }
          }
        }

        //put
        .collapse-item-put {
          .collapse-item-content {
            .main {
              border: 1px solid rgb(252, 161, 48);
              border-top: 0;

              .n1 {
                background: rgb(251, 241, 230);
              }

              .n2 {
                & > span {
                  color: rgb(59, 65, 81);
                  border-bottom: 3px solid rgb(252, 161, 48);
                }
              }

              .n3 {
                background: rgb(251, 241, 230);
              }
            }
          }
        }

        :deep(.collapse-item-put>button:nth-of-type(1)) {
          background: rgb(251, 241, 230);
          border: 1px solid rgb(252, 161, 48);

          .collapse-title {
            .n1 {
              background: rgb(252, 161, 48);
            }
          }
        }


      }
    }
  }
}
</style>
