<template>

  <div class="head-top">
    <ul class="left-but nav-lise">
      <li v-for="(item, key) in leftNavList" :key="key" @click="routerClick(item)">
        <dv-border-box-8>{{ item.name }}</dv-border-box-8>
      </li>
    </ul>
    <span class="head-name">{{ name }}</span>
    <ul class="right-but nav-lise">
      <template v-for="(item, key) in rightNavList" :key="key">
        <li @click="routerClick(item)" v-if="item.tag!== '6'">
          <dv-border-box-8 :reverse="true"><span>{{ item.name }}</span></dv-border-box-8>
        </li>
        <!--专项管理-->
        <li v-else>
          <el-dropdown class="cc-header-dropdown" popper-class="cc-header-dropdown-popper">
            <dv-border-box-8 :reverse="true" class="cc-header-dropdown-box8"><span
              class="cc-header-dropdown-span">{{ item.name }}</span>
            </dv-border-box-8>
            <template #dropdown>
              <ul class="cc-header-dropdown-popper-ul">
                <li v-for="(item2, key2) in item.list" :key="key2"
                    @click="dropdownMenuItem(item2, key2)"
                    :class="dropdownActive === key2 + 1? 'dropdownActive' : ''"
                >
                  <span>{{ item2.name }}</span>
                </li>
              </ul>
            </template>
          </el-dropdown>
        </li>
      </template>
    </ul>
    <el-icon color="#00ccff" size="32" class="fullScreenBut" @click="clickFullscreen">
      <FullScreen/>
    </el-icon>
  </div>
</template>

<script setup>
import {ref, onMounted, watch} from 'vue';
import screenFull from 'screenfull';
import {useUserStore} from '@/store/modules/user';

const userStore = useUserStore();
import {useRouter} from 'vue-router';
import {FullScreen} from "@element-plus/icons-vue";

const router = useRouter();


const dropdownActive = ref(0); // 专项管理 下拉选了哪个
const leftNavList = ref([
  {name: '管网排口', path: '', tag: '1'},
  {name: '市政公用', path: '', tag: '2'},
  {name: '市容环卫', path: '', tag: '3'}
  // {name:'智慧城市一网统管',path:'',tag:'home',active:''},
  // {name:'全部部件',path:'/cChartAll',tag:'4',active:''},
]);
const rightNavList = ref([
  {name: '园林绿化', path: '', tag: '5'},
  {
    name: '专项管理',
    path: '',
    tag: '6',
    active: '',
    list: [
      {name: '智能井盖管理系统', path: '', tag: '61'},
      {name: '排水防涝管理系统', path: '', tag: '62'},
      {name: '停车管理系统', path: '', tag: '63'},
      {name: '扬尘监测系统', path: '', tag: '64'},
      {name: '城市渣土车监管系统', path: '', tag: '65'},
      {name: '餐饮油烟监测系统', path: '', tag: '66'},
      {name: '违建巡查管理系统', path: '', tag: '67'}
    ]
  },
  {
    name: '后台菜单',
    path: '/index',
    tag: '7',
    active: 'admin'
  }
]);
const name = ref('智慧城市一网统管');

const routerClick = (item) => {
  if (item.path) {
    router.push(item.path);
  } else {
    userStore.headerTopActive = item.tag;
  }
  name.value = item.name;
  dropdownActive.value = 0;
};

onMounted(()=>{
  let navList= [...rightNavList.value, ...leftNavList.value]
  for (let i = 0; i < navList.length; i++) {
    if(navList[i].tag==userStore.headerTopActive){
      name.value = navList[i].name;
      return false
    }
  }
  for (let j = 0; j < rightNavList.value[1].list.length; j++) {
    if(rightNavList.value[1].list[j].tag==userStore.headerTopActive){
      name.value = rightNavList.value[1].list[j].name;
      dropdownActive.value = j + 1;
    }
  }
})

const clickFullscreen = () => {
  // 是否全屏  false 没有全屏 true 全屏
  if (!screenFull.isFullscreen) {
    screenFull.request(); // 全屏
  } else {
    screenFull.exit(); // 退出全屏
  }
};

const dropdownMenuItem = (item, key) => {
  dropdownActive.value = key + 1;
  userStore.headerTopActive = item.tag;
  name.value = item.name;
};
</script>

<style lang="scss">
//专项管理
.cc-header-dropdown {
  padding: 0 !important;
  margin: 0 !important;
  height: 42px !important;
  line-height: 42px !important;
  padding-left: 12px !important;

  &:focus-visible {
    /* 键盘操作聚焦时的样式 */
    outline: 2px solid blue;
    background-color: #d0d0d0;
  }

  .cc-header-dropdown-box8 {
    padding: 0 !important;
    margin: 0 !important;

    &:focus-visible {
      /* 键盘操作聚焦时的样式 */
      outline: 0px;
    }
  }

  .border-box-content {
    padding: 0 12px !important;
  }
}

.cc-header-dropdown-popper-ul {
  background: #001529;
  color: rgba(255, 255, 255, 0.65);
}

.cc-header-dropdown-popper {
  border: 0 !important;
  background: #001529 !important;

  .el-popper__arrow {
    display: none;
  }
}

.cc-header-dropdown-popper-ul {
  li {
    width: 170px !important;
    height: 65px !important;
    line-height: 65px;
    text-align: center;
    color: rgba(255, 255, 255, 0.65);
    font-size: 16px;
    background: url("@/assets/chart/images/dropdown.png") no-repeat;
    background-size: 100% 100%;
    margin-bottom: 8px;
    cursor: pointer;
  }

  li:nth-last-child(1) {
    margin-bottom: 0;
  }

  .dropdownActive {
    color: #00ccff !important;
    font-weight: bold;
    font-size: 16px;
    //background: transparent;
  }
}
</style>

<style lang="scss" scoped>
//头部
.head-top {
  background: url("@/assets/chart/images/dadaV-head.jpg") no-repeat;
  background-size: 100% 100%;
  height: 93px;
  text-align: center;
  color: #00ccff;
  font-size: 41px;
  font-weight: bold;

  .head-name {
    line-height: 93px;
  }

  .left-but {
    position: absolute;
    left: 0;
    top: 6px;
  }

  .right-but {
    position: absolute;
    right: 60px;
    top: 6px;
  }

  .nav-lise {
    display: flex;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
    flex-direction: row;
    font-size: 22px;

    li:nth-of-type(2) {
      margin: 0 8px;
    }

    li {
      div {
        padding: 5px;
        margin: 5px;
      }

      cursor: pointer;
    }
  }

  .fullScreenBut {
    position: absolute;
    top: 10px;
    right: 15px;
    font-size: 30px;
    z-index: 99999;
    color: #00ccff;
  }
}

.cc-header-dropdown-box8 {
  //height:35px
  padding: 15px;
}

.cc-header-dropdown-span {
  color: #00ccff;
  font-size: 22px;

}

</style>
