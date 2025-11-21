<template>
  <div :id="idName" :style="{width: '100%', height: height, marginTop: marginTop}" class="map-common-css"></div>
</template>

<script setup>
import { onMounted, defineProps } from 'vue';

const props = defineProps({
  idName: {
    type: String,
    default: 'chinaEcharts',
  },
  height: {
    type: String,
    default: 'calc(100% - 40px - 20px)',
  },
  marginTop: {
    type: String,
    default: '10px',
  },
});

const initMap = () => {
  // 异步加载腾讯地图API
  const script = document.createElement('script');
  script.src = 'https://map.qq.com/api/gljs?v=1.exp&key=OHCBZ-7BPC3-J7E3H-OA62K-Y3ZFZ-JQBPD&callback=init';
  script.async = true;
  window.init = mapCallback;
  document.head.appendChild(script);
};

const mapCallback = () => {
  let map = new TMap.Map(document.getElementById(props.idName), {
    center: new TMap.LatLng(26.793227, 117.810114),
    zoom: 11,
    mapStyleId: 'style1我的自定义样式'
  });
};

onMounted(() => {
  initMap();
});
</script>

<style lang="scss" scoped>
.map-common-css {
  border-radius: 8px;
  overflow: hidden;
}
</style>
