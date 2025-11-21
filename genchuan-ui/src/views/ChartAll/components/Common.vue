<template>
  <div></div>
</template>

<script setup>
import { onMounted, onBeforeUnmount } from 'vue';

// 去掉滚动条
const removeScrollbar = () => {
  if (typeof document !== 'undefined') {
    document.documentElement.classList.add('ccc-scrollbar');
    document.body.classList.add('ccc-scrollbar');
  }
};

// 在组件销毁之前清除 class
const clearScrollbarClass = () => {
  if (typeof document !== 'undefined') {
    const body = document.body;
    body.classList.remove('ccc-scrollbar');
    document.documentElement.classList.remove('ccc-scrollbar');
  }
};

onMounted(() => {
  removeScrollbar();
});

onBeforeUnmount(() => {
  clearScrollbarClass();
});
</script>

<style>
/* 滚动条优化 start */
.ccc-scrollbar::-webkit-scrollbar {
  width: 0px !important;
  height: 0px !important;
}
</style>
<style lang="scss" scoped></style>

