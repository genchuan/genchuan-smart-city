import {
  getSceneConfig,
  MAP_DEFAULT_CONFIG,
  isMock,
  saveMapConfig,
  deleteMapConfig
} from '@/api/overview/dpzl/map.js';

export const getConfig = async (sceneKey, defaultConfig = MAP_DEFAULT_CONFIG) => {
  try {
    // 1. 优先读取本地存储
    const localConfig = localStorage.getItem(`map_config_${sceneKey}`);
    if (localConfig) {
      const parsedConfig = JSON.parse(localConfig);
      parsedConfig.orbitAnimation = parsedConfig.orbitAnimation || {
        rotateSpeed: 0.2,
        loop: 'infinity',
        pitch: 40,
        zoom: 18,
        centerType: '复用地图中心坐标',
        customCenter: { lat: 26.855237, lng: 118.000000 }
      };
      return parsedConfig;
    }
    // 2. 本地无则请求接口
    const apiConfig = await getSceneConfig(sceneKey);
    // 3. 接口返回后存入本地
    localStorage.setItem(`map_config_${sceneKey}`, JSON.stringify(apiConfig));
    return apiConfig;
  } catch (error) {
    console.error('获取配置失败，使用默认配置：', error);
    return defaultConfig;
  }
};

export const saveConfig = async (sceneKey, configData) => {
  try {
    localStorage.setItem(`map_config_${sceneKey}`, JSON.stringify(configData));
    if (!isMock) {
      await saveMapConfig(sceneKey, configData);
    }
    return true;
  } catch (error) {
    console.error('保存配置失败：', error);
    return false;
  }
};

export const clearConfig = async (sceneKey) => {
  try {
    localStorage.removeItem(`map_config_${sceneKey}`);
    if (!isMock) {
      await deleteMapConfig(sceneKey);
    }
    return true;
  } catch (error) {
    console.error('清除配置失败：', error);
    return false;
  }
};
