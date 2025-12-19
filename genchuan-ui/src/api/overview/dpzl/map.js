import axios from 'axios';

// 默认点位图标
import markerGrayBasic from '@/assets/chart/images/marker-gray.png';
import markerBlueBasic from '@/assets/chart/images/marker-blue.png';
import markerGreenBasic from '@/assets/chart/images/marker-green.png';
import markerRedBasic from '@/assets/chart/images/marker-red.png';

// 状态图标
import markerGood from '@/assets/chart/images/good.png';
import markerBad from '@/assets/chart/images/bad.png';
import markerNormal from '@/assets/chart/images/normal.png';
import markerMaintain from '@/assets/chart/images/maintain.png';
import markerAbnormal from '@/assets/chart/images/abnormal.png';
import markerRiskHigh from '@/assets/chart/images/risk-high.png';
import markerRiskMid from '@/assets/chart/images/risk-mid.png';
import markerRiskLow from '@/assets/chart/images/risk-low.png';

// 业务图标
import businessLarge from '@/assets/chart/images/big-business.png';
import businessMedium from '@/assets/chart/images/mid-business.png';
import businessSmall from '@/assets/chart/images/small-business.png';
import factory from '@/assets/chart/images/factory.png';
import office from '@/assets/chart/images/office.png';
import dormitory from '@/assets/chart/images/dormitory.png';
import parking from '@/assets/chart/images/parking.png';
import makerOpen from '@/assets/chart/images/maker-open.png';
import makerClose from '@/assets/chart/images/maker-close.png';
import manholeHandled from '@/assets/chart/images/manhole-handled.png';
import manholeNormal from '@/assets/chart/images/manhole-normal.png';
import manholeEmergency from '@/assets/chart/images/manhole-emergency.png';
import streetlightHandled from '@/assets/chart/images/streetlight-handled.png';
import streetlightNormal from '@/assets/chart/images/streetlight-normal.png';
import streetlightEmergency from '@/assets/chart/images/streetlight-emergency.png';
import fireHydrantHandled from '@/assets/chart/images/fireHydrant-handled.png';
import fireHydrantNormal from '@/assets/chart/images/fireHydrant-normal.png';
import fireHydrantEmergency from '@/assets/chart/images/fireHydrant-emergency.png';
import trashCanHandled from '@/assets/chart/images/trashCan-handled.png';
import trashCanNormal from '@/assets/chart/images/trashCan-normal.png';
import trashCanEmergency from '@/assets/chart/images/trashCan-emergency.png';
import scenicNormal from '@/assets/chart/images/mountain_normal.png';
import scenicAbnormal from '@/assets/chart/images/mountain_abnormal.png';
import venueNormal from '@/assets/chart/images/building_normal.png';
import venueAbnormal from '@/assets/chart/images/building_abnormal.png';

// ===================== 全局开关：true=用模拟数据，false=调真实接口 =====================
export const isMock = true;

// ===================== 真实接口基础配置 =====================
const baseURL = 'https://your-real-api-domain.com';
export const axiosInstance = axios.create({
  baseURL,
  timeout: 10000,
  headers: { 'Content-Type': 'application/json' }
});

// ===================== 全局默认配置（基础模板，各场景可覆盖） =====================
export const MAP_DEFAULT_CONFIG = {
  selectedFields: ['comp_name', 'coord'], // 移除device_status
  selectedFieldsConfig: [
    { key: 'comp_name', label: '点位名称', renderType: 'normal', statusMap: {} },
    { key: 'coord', label: '坐标位置', renderType: 'coord', statusMap: {} }
  ],
  defaultIconSize: { width: 40, height: 40 },
  defaultIconUrl: markerBad,
  statusIconConfigList: [], // 清空默认状态图标（由场景专属配置覆盖）
  legendConfig: {
    show: true,
    position: 'topLeft',
    items: []
  },
  mapCenter: { lat: 26.855237, lng: 118.000000 },
  orbitAnimation: {
    rotateSpeed: 0.2,        // 每帧旋转角度
    loop: 'infinity',        // 字符串类型（适配配置页的下拉选择）
    pitch: 40,               // 俯仰角
    zoom: 18,                // 缩放级别
    centerType: '复用地图中心坐标', // 中心点类型
    customCenter: { lat: 26.855237, lng: 118.000000 }, // 自定义中心点
    enable: true,            // 兼容MapTemplate的默认值
    center: null             // 兼容MapTemplate的动画中心
  }
};

// ===================== 预置图标库（仅修改这里：业务图标合并到status，删除business分类） =====================
export const PRESET_ICON_LIBRARY = {
  default: [
    { id: 'legend1', name: '默认灰', url: markerGrayBasic },
    { id: 'legend2', name: '默认蓝', url: markerBlueBasic },
    { id: 'legend3', name: '默认绿', url: markerGreenBasic },
    { id: 'legend4', name: '默认红', url: markerRedBasic },
  ],
  status: [
    // 原有状态图标
    { id: 'status1', name: '正常', url: markerGood },
    { id: 'status2', name: '故障', url: markerBad },
    { id: 'status3', name: '正常', url: markerNormal },
    { id: 'status4', name: '维护', url: markerMaintain },
    { id: 'status5', name: '异常', url: markerAbnormal },
    { id: 'status6', name: '低风险', url: markerRiskLow },
    { id: 'status7', name: '中风险', url: markerRiskMid },
    { id: 'status8', name: '高风险', url: markerRiskHigh },
    { id: 'status9', name: '开放', url: makerOpen },
    { id: 'status10', name: '关闭', url: makerClose },
    { id: 'biz1', name: '大型企业', url: businessLarge },
    { id: 'biz2', name: '中型企业', url: businessMedium },
    { id: 'biz3', name: '小型企业', url: businessSmall },
    { id: 'biz4', name: '工厂', url: factory },
    { id: 'biz5', name: '办公楼', url: office },
    { id: 'biz6', name: '宿舍', url: dormitory },
    { id: 'biz7', name: '停车场', url: parking },
    { id: 'biz9', name: '井盖-正常', url: manholeHandled },
    { id: 'biz10', name: '井盖-维护', url: manholeNormal },
    { id: 'biz11', name: '井盖-故障', url: manholeEmergency },
    { id: 'biz12', name: '垃圾桶-正常', url: trashCanHandled },
    { id: 'biz13', name: '垃圾桶-维护', url: trashCanNormal },
    { id: 'biz14', name: '垃圾桶-故障', url: trashCanEmergency },
    { id: 'biz15', name: '路灯-正常', url: streetlightHandled },
    { id: 'biz16', name: '路灯-维护', url: streetlightNormal },
    { id: 'biz17', name: '路灯-故障', url: streetlightEmergency },
    { id: 'biz18', name: '消防栓-正常', url: fireHydrantHandled },
    { id: 'biz19', name: '消防栓-维护', url: fireHydrantNormal },
    { id: 'biz20', name: '消防栓-故障', url: fireHydrantEmergency },
    { id: 'biz21', name: '景区-正常', url: scenicNormal },
    { id: 'biz22', name: '景区-异常', url: scenicAbnormal },
    { id: 'biz23', name: '场馆-正常', url: venueNormal },
    { id: 'biz24', name: '场馆-异常', url: venueAbnormal },
  ]
};

// ===================== ESM 适配：动态加载 mock 数据 =====================
let mockSceneList = [];
let mockSceneFieldConfig = {};
let mockSceneMapData = {};
let getMockSceneConfig = () => ({ ...MAP_DEFAULT_CONFIG });

// 动态导入 mock 数据（仅 isMock=true 时执行）
const loadMockData = async () => {
  if (!isMock) return;
  try {
    const sceneListModule = await import('./mock/sceneList.js');
    const sceneFieldConfigModule = await import('./mock/sceneFieldConfig.js');
    const sceneMapDataModule = await import('./mock/sceneMapData.js');
    const sceneConfigModule = await import('./mock/sceneConfig.js');

    mockSceneList = sceneListModule.mockSceneList;
    mockSceneFieldConfig = sceneFieldConfigModule.mockSceneFieldConfig;
    mockSceneMapData = sceneMapDataModule.mockSceneMapData;
    getMockSceneConfig = sceneConfigModule.getMockSceneConfig;
  } catch (e) {
    console.warn('模拟数据加载失败，请检查mock目录文件是否存在：', e);
  }
};

// 初始化加载 mock 数据（页面加载时执行）
loadMockData();

// ===================== 辅助函数：级联值 ↔ sceneKey 转换 =====================
export const getSceneKeyByCascader = (cascaderValue) => {
  if (!cascaderValue || cascaderValue.length < 2) return '';
  const findSceneKey = (options, value1, value2) => {
    const parent = options.find(item => item.value === value1);
    if (!parent || !parent.children) return '';
    const child = parent.children.find(item => item.value === value2);
    return child?.sceneKey || '';
  };
  return findSceneKey(mockSceneList, cascaderValue[0], cascaderValue[1]);
};

export const getCascaderValueBySceneKey = (sceneKey) => {
  for (const parent of mockSceneList) {
    if (!parent.children) continue;
    const child = parent.children.find(item => item.sceneKey === sceneKey);
    if (child) {
      return [parent.value, child.value];
    }
  }
  return [];
};

// ===================== 接口函数（适配 ESM 动态导入） =====================
export const getSceneList = async () => {
  if (isMock) {
    // 确保 mock 数据加载完成
    await loadMockData();
    return new Promise(resolve => setTimeout(() => resolve(mockSceneList), 500));
  } else {
    const res = await axiosInstance.get('/api/scene/list');
    return res.data;
  }
};

export const getSceneFieldList = async (sceneKey) => {
  if (isMock) {
    await loadMockData();
    return new Promise(resolve => setTimeout(() => {
      resolve(mockSceneFieldConfig[sceneKey] || [
        { key: 'comp_name', label: '点位名称', type: 'normal' },
        { key: 'coord', label: '坐标位置', type: 'coord' }
      ]);
    }, 500));
  } else {
    const res = await axiosInstance.get(`/api/scene/field/${sceneKey}`);
    return res.data;
  }
};

export const getSceneMapData = async (sceneKey) => {
  if (isMock) {
    await loadMockData();
    return new Promise(resolve => {
      setTimeout(() => {
        resolve(mockSceneMapData[sceneKey] || { list: [] });
      }, 500);
    });
  } else {
    const res = await axiosInstance.get(`/api/scene/map/${sceneKey}`);
    return res.data;
  }
};

export const saveMapConfig = async (sceneKey, config) => {
  if (isMock) {
    await loadMockData();
    return new Promise(resolve => setTimeout(() => resolve({ success: true, msg: '配置保存成功' }), 500));
  } else {
    const res = await axiosInstance.post(`/api/scene/config/${sceneKey}`, config);
    return res.data;
  }
};

export const deleteMapConfig = async (sceneKey) => {
  if (isMock) {
    await loadMockData();
    return new Promise(resolve => setTimeout(() => resolve({ success: true, msg: '配置删除成功' }), 500));
  } else {
    const res = await axiosInstance.delete(`/api/scene/config/${sceneKey}`);
    return res.data;
  }
};

export const getSceneConfig = async (sceneKey) => {
  if (isMock) {
    await loadMockData();
    return new Promise(resolve => {
      setTimeout(() => {
        resolve(getMockSceneConfig(sceneKey));
      }, 500);
    });
  } else {
    const res = await axiosInstance.get(`/api/scene/config/${sceneKey}`);
    return res.data;
  }
};

// 导出所有图标（供mock/sceneConfig.js使用）
export {
  markerGood, markerBad, markerNormal, markerMaintain, markerAbnormal,
  markerRiskHigh, markerRiskMid, markerRiskLow, makerOpen, makerClose,
  manholeHandled, manholeNormal, manholeEmergency,
  streetlightHandled, streetlightNormal, streetlightEmergency,
  fireHydrantHandled, fireHydrantNormal, fireHydrantEmergency,
  trashCanHandled, trashCanNormal, trashCanEmergency,
  scenicNormal, scenicAbnormal, venueNormal, venueAbnormal
};
