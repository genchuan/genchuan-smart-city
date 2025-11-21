<template>
  <div></div>
  <!--集群-->
  <!--<iframe :src="url" frameborder="0" ref="iframe" width="100%" class="iframe-css"-->
  <!--        scrolling="auto"></iframe>-->
</template>


<script lang="ts" setup name="ThingsBoard">
import {ref, onMounted, onUnmounted} from 'vue'
import {useRoute} from 'vue-router'
// import {thingsBoardLogin} from "@/api/genchuan/jiqun";

const route = useRoute()
const url = ref();
// const height = 'calc(100vh - 20px)';

// 定义要添加到 body 的 class 名称
const customClass = 'custom-body-class';

const addClassToBody = () => {
  document.body.classList.add(customClass);
};

const removeClassFromBody = () => {
  document.body.classList.remove(customClass);
};


// 挂载完毕
onMounted(() => {
  console.log(123123)
  console.log(import.meta.env.VITE_THINGS_BOARD_URL)
  url.value = import.meta.env.VITE_CLUSTERED_BOARD_URL + route.name;
  window.open(url.value, '_blank');
  addClassToBody();
  /*let data={
    username: "guoyuanhong",
    encrypt:"MTAwMDEwMTAxMTEw@^Tgj_bVo_oQ2"
  }
  thingsBoardLogin(data).then((result) => {
    console.log(result);
    url.value = import.meta.env.VITE_CLUSTERED_BOARD_URL + route.name;
    // window.open(url.value, '_blank');
  })*/
})

// 在组件卸载时移除 class
onUnmounted(() => {
  removeClassFromBody();
});

</script>
<style lang="scss">
.custom-body-class .cc-iframe  {
  padding: 0px !important;
}

.iframe-css {
  height: calc(100vh - 90px);
}
</style>
