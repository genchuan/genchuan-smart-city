import { MAP_DEFAULT_CONFIG } from '../map';
import {
  markerGood, markerBad, markerNormal, markerMaintain, markerAbnormal,
  markerRiskHigh, markerRiskMid, markerRiskLow, makerOpen, makerClose,
  manholeHandled, manholeNormal, manholeEmergency,
  streetlightHandled, streetlightNormal, streetlightEmergency,
  fireHydrantHandled, fireHydrantNormal, fireHydrantEmergency,
  trashCanHandled, trashCanNormal, trashCanEmergency,
  scenicNormal, scenicAbnormal, venueNormal, venueAbnormal
} from '../map';

// 各场景差异化配置
export const getMockSceneConfig = (sceneKey) => {
  let customConfig = { ...MAP_DEFAULT_CONFIG };

  // 市政设施场景
  if (sceneKey === 'urban_facility') {
    customConfig.selectedFields = ['comp_name', 'facility_type', 'maintain_cycle', 'streetlight_status', 'manhole_status', 'fireHydrant_status', 'coord'];
    customConfig.selectedFieldsConfig = [
      { key: 'comp_name', label: '设施名称', renderType: 'normal', statusMap: {} },
      { key: 'facility_type', label: '设施类型', renderType: 'normal', statusMap: {} },
      { key: 'maintain_cycle', label: '维护周期(天)', renderType: 'normal', statusMap: {} },
      { key: 'streetlight_status', label: '路灯状态', renderType: 'status', statusMap: { '正常': '正常', '故障': '故障', '维护': '维护' } },
      { key: 'manhole_status', label: '井盖状态', renderType: 'status', statusMap: { '正常': '正常', '故障': '故障', '维护': '维护' } },
      { key: 'fireHydrant_status', label: '消防栓状态', renderType: 'status', statusMap: { '正常': '正常', '故障': '故障', '维护': '维护' } },
      { key: 'coord', label: '坐标位置', renderType: 'coord', statusMap: {} }
    ];
    // 状态图标配置
    customConfig.statusIconConfigList = [
      // 路灯状态
      { fieldKey: 'streetlight_status', rawValue: '正常', icon: streetlightNormal },
      { fieldKey: 'streetlight_status', rawValue: '故障', icon: streetlightEmergency },
      { fieldKey: 'streetlight_status', rawValue: '维护', icon: streetlightHandled },
      // 井盖状态
      { fieldKey: 'manhole_status', rawValue: '正常', icon: manholeNormal },
      { fieldKey: 'manhole_status', rawValue: '故障', icon: manholeEmergency },
      { fieldKey: 'manhole_status', rawValue: '维护', icon: manholeHandled },
      // 消防栓状态
      { fieldKey: 'fireHydrant_status', rawValue: '正常', icon: fireHydrantNormal },
      { fieldKey: 'fireHydrant_status', rawValue: '故障', icon: fireHydrantEmergency },
      { fieldKey: 'fireHydrant_status', rawValue: '维护', icon: fireHydrantHandled }
    ];
  }
  // 市容秩序场景
  else if (sceneKey === 'urban_order') {
    customConfig.selectedFields = ['comp_name', 'violation_type', 'rectify_status', 'coord'];
    customConfig.selectedFieldsConfig = [
      { key: 'comp_name', label: '违规点位', renderType: 'normal', statusMap: {} },
      { key: 'violation_type', label: '违规类型', renderType: 'normal', statusMap: {} },
      { key: 'rectify_status', label: '整改状态', renderType: 'status', statusMap: { '已整改': '已整改', '整改中': '整改中', '未整改': '未整改' } },
      { key: 'coord', label: '坐标位置', renderType: 'coord', statusMap: {} }
    ];
    customConfig.statusIconConfigList = [
      { fieldKey: 'rectify_status', rawValue: '已整改', icon: markerNormal },
      { fieldKey: 'rectify_status', rawValue: '整改中', icon: markerMaintain },
      { fieldKey: 'rectify_status', rawValue: '未整改', icon: markerAbnormal }
    ];
  }
  // 文旅资源场景
  else if (sceneKey === 'tour_resource') {
    customConfig.selectedFields = ['comp_name', 'scenic_level', 'current_flow', 'open_status', 'coord'];
    customConfig.selectedFieldsConfig = [
      { key: 'comp_name', label: '景区名称', renderType: 'normal', statusMap: {} },
      { key: 'scenic_level', label: '景区等级', renderType: 'normal', statusMap: {} },
      { key: 'current_flow', label: '实时客流量', renderType: 'normal', statusMap: {} },
      { key: 'open_status', label: '开放状态', renderType: 'status', statusMap: { '正常开放': '正常开放', '临时关闭': '临时关闭' } },
      { key: 'coord', label: '坐标位置', renderType: 'coord', statusMap: {} }
    ];
    customConfig.statusIconConfigList = [
      { fieldKey: 'open_status', rawValue: '正常开放', icon: makerOpen },
      { fieldKey: 'open_status', rawValue: '临时关闭', icon: makerClose }
    ];
  }
  // 文体场馆场景
  else if (sceneKey === 'tour_venue') {
    customConfig.selectedFields = ['comp_name', 'venue_type', 'open_hours', 'open_status', 'coord'];
    customConfig.selectedFieldsConfig = [
      { key: 'comp_name', label: '场馆名称', renderType: 'normal', statusMap: {} },
      { key: 'venue_type', label: '场馆类型', renderType: 'normal', statusMap: {} },
      { key: 'open_hours', label: '开放时间', renderType: 'normal', statusMap: {} },
      { key: 'open_status', label: '运营状态', renderType: 'status', statusMap: { '正常运营': '正常运营', '暂停运营': '暂停运营' } },
      { key: 'coord', label: '坐标位置', renderType: 'coord', statusMap: {} }
    ];
    customConfig.statusIconConfigList = [
      { fieldKey: 'open_status', rawValue: '正常运营', icon: makerOpen },
      { fieldKey: 'open_status', rawValue: '暂停运营', icon: makerClose }
    ];
  }

  return customConfig;
};
