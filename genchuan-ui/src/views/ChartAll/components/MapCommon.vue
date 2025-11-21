<template>
  <div :id="idName" :style="{width: '100%', height: height, marginTop: marginTop}"
       class="map-common-css">
    <!--<img src="@/assets/ditu/img.png" style="width:100%; height: 100%" v-if="userStore.headerTopActive=='1'"/>-->
    <!--<img src="@/assets/ditu/img_1.png" style="width:100%; height: 100%" v-if="userStore.headerTopActive=='2'"/>-->
    <!--<img src="@/assets/ditu/img_2.png" style="width:100%; height: 100%" v-if="userStore.headerTopActive=='3'"/>-->
    <!--<img src="@/assets/ditu/img_3.png" style="width:100%; height: 100%" v-if="userStore.headerTopActive=='5'"/>-->
  </div>
</template>

<script setup>
import {onMounted, defineProps} from 'vue';
import { useUserStore } from '@/store/modules/user';
const userStore = useUserStore();


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
  geometriesArray: {
    type: Array,
    default: () => [
      // {x:'',y:''}
    ]
  }
});


const initMap = () => {
  // 异步加载腾讯地图API
  const script = document.createElement('script');
  script.src = 'https://map.qq.com/api/gljs?v=1.exp&key=OHCBZ-7BPC3-J7E3H-OA62K-Y3ZFZ-JQBPD&callback=init';
  // script.src = 'http://localhost/src/assets/ditu/gljs.js'; 没用还是要联网
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



  //创建并初始化MultiMarker
  let geometriesData = [];
  for (let i = 0; i < props.geometriesArray.length; i++) {
    let jsonData = {
      "id": i,   //点标记唯一标识，后续如果有删除、修改位置等操作，都需要此id
      "styleId": 'marker',  //指定样式id
      "position": new TMap.LatLng(props.geometriesArray[i].x, props.geometriesArray[i].y),  //点标记坐标位置
      "properties": {//自定义属性
        "title": "marker2"
      }
    }
    geometriesData.push(jsonData)
  }

  var markerLayer = new TMap.MultiMarker({
    map: map,  //指定地图容器
    //样式定义
    styles: {
      //创建一个styleId为"myStyle"的样式（styles的子属性名即为styleId）
      "myStyle": new TMap.MarkerStyle({
        "width": 25,  // 点标记样式宽度（像素）
        "height": 35, // 点标记样式高度（像素）
        "src": '../img/marker.png',  //图片路径
        //焦点在图片中的像素位置，一般大头针类似形式的图片以针尖位置做为焦点，圆形点以圆心位置为焦点
        "anchor": {x: 16, y: 32}
      })
    },
    //点标记数据数组
    geometries:geometriesData
  });

  // [{
  //   "id": "1",   //点标记唯一标识，后续如果有删除、修改位置等操作，都需要此id
  //   "styleId": 'marker',  //指定样式id
  //   "position": new TMap.LatLng(26.793227, 117.810114),  //点标记坐标位置
  //   "properties": {//自定义属性
  //     "title": "marker2"
  //   }
  // }]

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
