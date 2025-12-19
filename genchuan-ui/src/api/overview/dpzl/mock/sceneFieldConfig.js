// 各场景差异化字段配置
export const mockSceneFieldConfig = {
  // 城管住建-市政设施：基础+设施类型+维护周期 + 路灯/井盖/消防栓状态
  urban_facility: [
    { key: 'comp_name', label: '设施名称', type: 'normal' },
    { key: 'facility_type', label: '设施类型', type: 'normal' },
    { key: 'maintain_cycle', label: '维护周期(天)', type: 'normal' },
    { key: 'streetlight_status', label: '路灯状态', type: 'status' },
    { key: 'manhole_status', label: '井盖状态', type: 'status' },
    { key: 'fireHydrant_status', label: '消防栓状态', type: 'status' },
    { key: 'coord', label: '坐标位置', type: 'coord' }
  ],
  // 城管住建-市容秩序：基础+违规类型+整改状态
  urban_order: [
    { key: 'comp_name', label: '违规点位', type: 'normal' },
    { key: 'violation_type', label: '违规类型', type: 'normal' },
    { key: 'rectify_status', label: '整改状态', type: 'status' },
    { key: 'coord', label: '坐标位置', type: 'coord' }
  ],
  // 城管住建-环境卫生：基础+清洁频次+所属区域
  urban_env: [
    { key: 'comp_name', label: '环卫点位', type: 'normal' },
    { key: 'clean_frequency', label: '清洁频次(次/天)', type: 'normal' },
    { key: 'area', label: '所属区域', type: 'normal' },
    { key: 'device_status', label: '使用状态', type: 'status' },
    { key: 'coord', label: '坐标位置', type: 'coord' }
  ],
  // 文体旅游-文旅资源：基础+景区等级+实时客流量
  tour_resource: [
    { key: 'comp_name', label: '景区名称', type: 'normal' },
    { key: 'scenic_level', label: '景区等级', type: 'normal' },
    { key: 'current_flow', label: '实时客流量', type: 'normal' },
    { key: 'open_status', label: '开放状态', type: 'status' },
    { key: 'coord', label: '坐标位置', type: 'coord' }
  ],
  // 文体旅游-文体场馆：基础+场馆类型+开放状态
  tour_venue: [
    { key: 'comp_name', label: '场馆名称', type: 'normal' },
    { key: 'venue_type', label: '场馆类型', type: 'normal' },
    { key: 'open_hours', label: '开放时间', type: 'normal' },
    { key: 'open_status', label: '运营状态', type: 'status' },
    { key: 'coord', label: '坐标位置', type: 'coord' }
  ],
  // 园区管理-园区设备：基础+设备类型+所属楼栋
  park_device: [
    { key: 'comp_name', label: '设备名称', type: 'normal' },
    { key: 'device_type', label: '设备类型', type: 'normal' },
    { key: 'building', label: '所属楼栋', type: 'normal' },
    { key: 'device_status', label: '设备状态', type: 'status' },
    { key: 'device_code', label: '设备编码', type: 'normal' },
    { key: 'coord', label: '坐标位置', type: 'coord' }
  ]
};
