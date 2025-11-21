<template>
  <!--没有域名传进来-->
  <iframe :src="url" frameborder="0" ref="iframe" width="100%" class="iframe-css" scrolling="auto"></iframe>
</template>


<script lang="ts" setup name="ThingsBoard">
import {ref, onMounted, onUnmounted} from 'vue'
import {useRoute} from 'vue-router'

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
  url.value = import.meta.env.VITE_THINGS_BOARD_URL + route.name;
  addClassToBody();
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
