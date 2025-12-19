// 场景列表（级联结构，适配el-cascader）
export const mockSceneList = [
  {
    label: '城管住建', // 一级分类
    value: 'urban_admin',
    children: [
      { label: '市政设施', value: 'urban_admin_facility', sceneKey: 'urban_facility', desc: '城管住建-市政设施点位（路灯/井盖等）' },
      { label: '市容秩序', value: 'urban_admin_order', sceneKey: 'urban_order', desc: '城管住建-市容秩序点位（广告牌/占道经营等）' },
      { label: '环境卫生', value: 'urban_admin_env', sceneKey: 'urban_env', desc: '城管住建-环境卫生点位（垃圾桶/公厕等）' }
    ]
  },
  {
    label: '文体旅游', // 一级分类
    value: 'culture_tourism',
    children: [
      { label: '文旅资源', value: 'culture_tourism_resource', sceneKey: 'tour_resource', desc: '文体旅游-文旅资源点位（景区/古迹等）' },
      { label: '文体场馆', value: 'culture_tourism_venue', sceneKey: 'tour_venue', desc: '文体旅游-文体场馆点位（体育馆/图书馆等）' }
    ]
  },
  // 保留原有园区设备场景
  {
    label: '园区管理',
    value: 'park_manage',
    children: [
      { label: '园区设备', value: 'park_device', sceneKey: 'park_device', desc: '园区内监控/电力/消防设备点位' }
    ]
  }
];
