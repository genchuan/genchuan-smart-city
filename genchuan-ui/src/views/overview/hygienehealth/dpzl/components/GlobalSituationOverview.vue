<template>
  <div class="page-container">
    <!-- 页面内容将在这里展示，布局固定，超出部分可滚动 -->
  </div>
</template>

<script setup lang="ts">
</script>

<style lang="scss" scoped>
// 最外层容器：固定宽度，支持横向滚动，避免黑色区域
.page-container {
  width: 1920px; // 固定设计稿宽度（根据实际设计稿调整，如1920px为常见大屏尺寸）
  height: 100vh; // 垂直方向占满屏幕
  overflow-x: auto; // 横向超出时允许滚动（支持鼠标拖动/触屏滑动）
  overflow-y: hidden; // 禁止垂直滚动
  background: url("@/assets/chart/images/bg.jpg") no-repeat;
  background-size: cover; // 背景图完整覆盖容器，避免露出黑色区域
  background-position: center; // 背景图居中，确保缩放时显示完整
  color: #fff;
  padding: 0 20px;
  box-sizing: border-box; // 确保padding不影响整体宽度计算
  -webkit-overflow-scrolling: touch; /* 让iOS设备支持平滑触摸滚动 */
  touch-action: pan-x; /* 明确允许横向触摸滑动（兼容安卓/ios） */
}

// 隐藏滚动条但保留滚动功能（可选，根据设计需求）
::-webkit-scrollbar {
  height: 0; // 横向滚动条隐藏
}
</style>
