<template>
    <iframe :src="url" frameborder="0" ref="iframe" width="100%" class="iframe-css" scrolling="auto"></iframe>
</template>

<script lang="ts" setup>
import {ref, onMounted, onUnmounted} from 'vue'
import {useRoute} from 'vue-router'
import {TabMenu} from "@/layout/components/TabMenu";

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
  url.value = `${import.meta.env.VITE_THINGS_BOARD_URL}Lstatic/ceshi/index.html?username=1197558781@qq.com&password=123456`;
  addClassToBody();
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
