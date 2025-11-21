<template>
  <div></div>
  <!--市政智慧管网服务系统（接入原有一期系统）-->
  <!--<iframe :src="url" frameborder="0" ref="iframe" width="100%" class="iframe-css"-->
  <!--        scrolling="auto"></iframe>-->
  <iframe :src="url" frameborder="0" ref="iframe" width="100%" class="iframe-css"
          scrolling="auto"></iframe>
</template>


<script lang="ts" setup>
import {ref, onMounted, onUnmounted} from 'vue'
import {useRoute} from 'vue-router'
// import {shizhenLogin} from "@/api/genchuan/shizhen";

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
  url.value = import.meta.env.VITE_PIPE_NETWORK_URL;
  // window.open(url.value, '_blank');
  addClassToBody();


/*
  let data={
    username: "daiyi",
    password:"sNyMkkkNTOKWHXH5ssXFWA=="
  }
  shizhenLogin(data).then((result) => {
    console.log(result);
  })*/

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
.custom-body-class .cc-iframe {
  padding: 0px !important;
}

.iframe-css {
  height: calc(100vh - 90px);
}
</style>
