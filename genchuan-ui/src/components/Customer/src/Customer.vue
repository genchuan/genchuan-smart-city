<template>
  <div class="Customer-css">


    <el-popover
      placement="left"
      trigger="click"
      popper-style="width:170px"
      popper-class="Customer-content"
      v-if="VITE_LOGO_SHOW"
    >
      <template #default>
          <img src="@/assets/imgs/kefu.png" style="width: 150px"/>
      </template>
      <template #reference>
        <div class="customer-kefu">
          <img src="@/assets/imgs/kefulogo.png"/>
        </div>
      </template>
    </el-popover>

    <!--<img src="@/assets/imgs/kefu.png"/>-->
  </div>
</template>

<script lang="ts" setup name="test">
import { ref, onMounted, onUpdated, onBeforeUnmount } from 'vue'

// logo 是否显示
const VITE_LOGO_SHOW=JSON.parse(import.meta.env.VITE_LOGO_SHOW);

// 挂载完毕
onMounted(() => {
  console.log('挂载完毕')
})
// 更新完毕
onUpdated(() => {
  console.log('更新完毕')
})
// 卸载之前
onBeforeUnmount(() => {
  console.log('卸载之前')
})
</script>

<style lang="scss" scoped>
.Customer-content{
  width: 350px !important;
}
.Customer-css{
  position: fixed;
  right: 0;
  top: 52%;
  z-index: 1200;

    //.el-popover.el-popper

  .customer-kefu{
    background: #67C23A;
    border-radius: 6px 0 0 6px;
    width:40px;
    height:40px;
    text-align: center;
    line-height: 45px;
    img{
      padding-top: 8px;
      width: 20px;
    }
  }
}
</style>
