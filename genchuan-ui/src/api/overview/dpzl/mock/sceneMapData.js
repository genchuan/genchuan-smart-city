// 1. 市政设施数据
const mockUrbanFacilityData = {
  list: [
    {
      uniqueId: 'urban_001',
      lat: 26.855237,
      lng: 118.001000,
      comp_name: '市政路灯001',
      facility_type: '路灯',
      maintain_cycle: 90,
      streetlight_status: '正常',
      manhole_status: '',
      fireHydrant_status: '',
      coord: { lat: 26.855237, lng: 118.001000 }
    },
    {
      uniqueId: 'urban_002',
      lat: 26.855350,
      lng: 118.001120,
      comp_name: '市政井盖002',
      facility_type: '井盖',
      maintain_cycle: 180,
      streetlight_status: '',
      manhole_status: '故障',
      fireHydrant_status: '',
      coord: { lat: 26.855350, lng: 118.001120 }
    },
    {
      uniqueId: 'urban_003',
      lat: 26.855480,
      lng: 118.001250,
      comp_name: '市政消防栓003',
      facility_type: '消防栓',
      maintain_cycle: 30,
      streetlight_status: '',
      manhole_status: '',
      fireHydrant_status: '维护',
      coord: { lat: 26.855480, lng: 118.001250 }
    }
  ]
};

// 2. 市容秩序数据
const mockUrbanOrderData = {
  list: [
    { uniqueId: 'order_001', lat: 26.856237, lng: 118.002000, comp_name: '主干道广告牌001', violation_type: '违规设置', rectify_status: '已整改', coord: { lat: 26.856237, lng: 118.002000 } },
    { uniqueId: 'order_002', lat: 26.856350, lng: 118.002120, comp_name: '步行街占道经营点002', violation_type: '占道经营', rectify_status: '整改中', coord: { lat: 26.856350, lng: 118.002120 } },
    { uniqueId: 'order_003', lat: 26.856480, lng: 118.002250, comp_name: '小区门口违规摆摊003', violation_type: '违规摆摊', rectify_status: '未整改', coord: { lat: 26.856480, lng: 118.002250 } }
  ]
};

// 3. 环境卫生数据
const mockUrbanEnvData = {
  list: [
    { uniqueId: 'env_001', lat: 26.857237, lng: 118.003000, comp_name: '公园垃圾桶001', clean_frequency: 4, area: '西湖公园', device_status: '正常', coord: { lat: 26.857237, lng: 118.003000 } },
    { uniqueId: 'env_002', lat: 26.857350, lng: 118.003120, comp_name: '市政公厕002', clean_frequency: 2, area: '市中心商圈', device_status: '维护', coord: { lat: 26.857350, lng: 118.003120 } },
    { uniqueId: 'env_003', lat: 26.857480, lng: 118.003250, comp_name: '街道垃圾桶003', clean_frequency: 3, area: '城东街道', device_status: '故障', coord: { lat: 26.857480, lng: 118.003250 } }
  ]
};

// 4. 文旅资源数据
const mockTourResourceData = {
  list: [
    { uniqueId: 'tour_001', lat: 26.858237, lng: 118.004000, comp_name: 'AAAA级景区-东湖景区', scenic_level: 'AAAA级', current_flow: 1250, open_status: '正常开放', coord: { lat: 26.858237, lng: 118.004000 } },
    { uniqueId: 'tour_002', lat: 26.858350, lng: 118.004120, comp_name: '省级文物保护单位-古楼', scenic_level: '省级', current_flow: 320, open_status: '正常开放', coord: { lat: 26.858350, lng: 118.004120 } },
    { uniqueId: 'tour_003', lat: 26.858480, lng: 118.004250, comp_name: 'AAA级景区-湿地公园', scenic_level: 'AAA级', current_flow: 860, open_status: '临时关闭', coord: { lat: 26.858480, lng: 118.004250 } }
  ]
};

// 5. 文体场馆数据
const mockTourVenueData = {
  list: [
    { uniqueId: 'venue_001', lat: 26.859237, lng: 118.005000, comp_name: '市体育馆', venue_type: '体育场馆', open_hours: '08:00-22:00', open_status: '正常运营', coord: { lat: 26.859237, lng: 118.005000 } },
    { uniqueId: 'venue_002', lat: 26.859350, lng: 118.005120, comp_name: '市图书馆', venue_type: '文化场馆', open_hours: '09:00-18:00', open_status: '正常运营', coord: { lat: 26.859350, lng: 118.005120 } },
    { uniqueId: 'venue_003', lat: 26.859480, lng: 118.005250, comp_name: '市大剧院', venue_type: '演艺场馆', open_hours: '10:00-21:00', open_status: '暂停运营', coord: { lat: 26.859480, lng: 118.005250 } }
  ]
};

// 6. 园区设备数据
const mockParkDeviceData = {
  list: [
    { uniqueId: 'park_001', lat: 26.855237, lng: 118.000000, comp_name: '园区东门监控', device_type: '监控设备', building: '1号楼', device_status: '正常', device_code: 'PARK-001', coord: { lat: 26.855237, lng: 118.000000 } },
    { uniqueId: 'park_002', lat: 26.855350, lng: 118.000120, comp_name: '园区西门电力箱', device_type: '电力设备', building: '2号楼', device_status: '故障', device_code: 'PARK-002', coord: { lat: 26.855350, lng: 118.000120 } },
    { uniqueId: 'park_003', lat: 26.855480, lng: 118.000250, comp_name: '园区主楼消防栓', device_type: '消防设备', building: '主楼', device_status: '维护', device_code: 'PARK-003', coord: { lat: 26.855480, lng: 118.000250 } },
    { uniqueId: 'park_004', lat: 26.855120, lng: 118.000300, comp_name: '园区食堂监控', device_type: '监控设备', building: '食堂', device_status: '正常', device_code: 'PARK-004', coord: { lat: 26.855120, lng: 118.000300 } }
  ]
};

// 整合所有场景点位数据
export const mockSceneMapData = {
  urban_facility: mockUrbanFacilityData,
  urban_order: mockUrbanOrderData,
  urban_env: mockUrbanEnvData,
  tour_resource: mockTourResourceData,
  tour_venue: mockTourVenueData,
  park_device: mockParkDeviceData
};
