import { ref, watch, unref } from 'vue';

/**
 * 地图绕点旋转动画组合式函数（适配 TMap）
 * @param {Ref<TMap.Map>} mapInstanceRef - 地图实例的 ref 对象（必须）
 * @param {Object} options - 绕点动画配置项
 * @param {Object} options.center - 旋转中心点 { lat: 纬度, lng: 经度 }
 * @param {Number} [options.rotateSpeed=0.2] - 旋转速度（度/帧）
 * @param {Number} [options.pitch=40] - 地图俯仰角
 * @param {Number} [options.zoom=12] - 地图缩放级别
 * @param {Boolean} [options.loop=true] - 是否循环旋转
 * @returns {Object} 动画控制方法 + 状态
 */
export const useMapOrbitAnimation = (mapInstanceRef, options) => {
  // 默认配置
  const defaultConfig = {
    center: { lat: 24.900000, lng: 118.690000 },
    rotateSpeed: 0.2,
    pitch: 40,
    zoom: 12,
    loop: true
  };

  // 合并配置（用户配置覆盖默认）
  const orbitConfig = ref({ ...defaultConfig, ...options });

  // 动画状态（响应式）
  const orbitStatus = ref({
    playing: true, // 初始播放中
    currentRotation: 0, // 当前旋转角度
    animationFrameId: null, // 动画帧ID
    isInited: false // 是否已初始化
  });

  // 核心：旋转循环逻辑
  const orbitLoop = () => {
    const map = unref(mapInstanceRef); // 解包 ref
    if (!map || !orbitStatus.value.playing) return;

    const { center, rotateSpeed, loop } = orbitConfig.value;
    const orbitCenter = new TMap.LatLng(center.lat, center.lng);

    let newRotation = orbitStatus.value.currentRotation + rotateSpeed;
    // 循环/终止判断
    if (newRotation >= 360) {
      if (!loop) {
        stopOrbitAnimation();
        return;
      }
      newRotation = 0;
    }

    // 更新地图状态
    map.setCenter(orbitCenter);
    map.setRotation(newRotation);

    orbitStatus.value.currentRotation = newRotation;
    orbitStatus.value.animationFrameId = requestAnimationFrame(orbitLoop);
  };

  // 启动动画
  const startOrbitAnimation = () => {
    const map = unref(mapInstanceRef);
    if (orbitStatus.value.isInited || !map) return;

    const { center, pitch, zoom } = orbitConfig.value;
    const orbitCenter = new TMap.LatLng(center.lat, center.lng);

    // 初始化地图视角
    map.setZoom(zoom);
    map.setPitch(pitch);
    map.setCenter(orbitCenter);
    map.setRotation(orbitStatus.value.currentRotation || 0);

    orbitStatus.value.isInited = true;
    orbitLoop();

    console.log('绕点动画启动', {
      center,
      initialRotation: orbitStatus.value.currentRotation,
      speed: rotateSpeed
    });
  };

  // 停止动画
  const stopOrbitAnimation = () => {
    orbitStatus.value.playing = false;
    orbitStatus.value.isInited = false;

    // 清理动画帧
    if (orbitStatus.value.animationFrameId) {
      cancelAnimationFrame(orbitStatus.value.animationFrameId);
      orbitStatus.value.animationFrameId = null;
    }

    console.log('绕点动画停止', { currentRotation: orbitStatus.value.currentRotation });
  };

  // 切换播放/暂停
  const toggleOrbitAnimation = () => {
    if (orbitStatus.value.playing) {
      stopOrbitAnimation();
    } else {
      orbitStatus.value.playing = true;
      startOrbitAnimation();
    }
  };

  // 监听配置变化：配置更新时重启动画
  watch(
    orbitConfig,
    () => {
      if (orbitStatus.value.playing) {
        stopOrbitAnimation();
        orbitStatus.value.isInited = false;
        startOrbitAnimation();
      }
    },
    { deep: true }
  );

  // 暴露方法和状态（供外部调用）
  return {
    orbitStatus, // 动画状态
    orbitConfig, // 动画配置（可外部修改）
    startOrbitAnimation, // 启动
    stopOrbitAnimation, // 停止
    toggleOrbitAnimation // 切换播放/暂停
  };
};
