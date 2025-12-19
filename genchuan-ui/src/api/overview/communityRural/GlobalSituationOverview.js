// 乡村全域数据概览相关接口
const BASE_URL = '/api/rural';

// 模拟芋道平台数据库表结构
const MOCK_TABLES = {
  // 行政区划表
  sys_area: [
    { id: 'fujian', name: '福建省', level: 'province', parent_id: null },
    { id: 'fuzhou', name: '福州市', level: 'city', parent_id: 'fujian' },
    { id: 'xiamen', name: '厦门市', level: 'city', parent_id: 'fujian' },
    { id: 'gulou', name: '鼓楼区', level: 'district', parent_id: 'fuzhou' },
    { id: 'minhou', name: '闽侯县', level: 'county', parent_id: 'fuzhou' }
  ],

  // 单元网格划分表
  biz_unit_grid_div: [
    { id: 'grid-001', name: '网格001', code: 'G001', area_id: 'gulou' },
    { id: 'grid-002', name: '网格002', code: 'G002', area_id: 'gulou' },
    { id: 'grid-003', name: '网格003', code: 'G003', area_id: 'minhou' }
  ],

  // 乡村资源分布表
  biz_rural_res: [
    { id: 'res-001', name: '高标准农田A区', type: 'land', x: 26.855227, y: 117.650114, area_id: 'minhou', status: 'using', usage_rate: 85 },
    { id: 'res-002', name: '水利设施A', type: 'water', x: 26.783227, y: 117.720114, area_id: 'minhou', status: 'using', usage_rate: 90 },
    { id: 'res-003', name: '古村落遗址', type: 'culture', x: 26.815227, y: 117.680114, area_id: 'gulou', status: 'using', usage_rate: 65 },
    { id: 'res-004', name: '闲置土地B区', type: 'land', x: 26.795227, y: 117.710114, area_id: 'gulou', status: 'idle', usage_rate: 20 },
    { id: 'res-005', name: '维护水利设施', type: 'water', x: 26.765227, y: 117.690114, area_id: 'minhou', status: 'maintenance', usage_rate: 0 }
  ],

  // 监测事件信息表
  biz_mon_evt_info: [
    { id: 'evt-001', name: '环境监测异常', level: 1, status: 'active', x: 26.815227, y: 117.750114, area_id: 'gulou', event_type: 'environment' },
    { id: 'evt-002', name: '基础设施维护', level: 2, status: 'processing', x: 26.795227, y: 117.710114, area_id: 'minhou', event_type: 'facility' },
    { id: 'evt-003', name: '一般安全事件', level: 3, status: 'resolved', x: 26.765227, y: 117.690114, area_id: 'gulou', event_type: 'security' },
    { id: 'evt-004', name: '紧急安全事件', level: 1, status: 'active', x: 26.745227, y: 117.670114, area_id: 'minhou', event_type: 'security' }
  ],

  // 乡村人口总览表
  stat_rural_pop: [
    { area_id: 'fujian', total: 12560, male: 6420, female: 6140, age_0_18: 2150, age_19_60: 7850, age_60_plus: 2560, resident: 10240, floating: 1560, household: 760 },
    { area_id: 'fuzhou', total: 8560, male: 4380, female: 4180, age_0_18: 1500, age_19_60: 5350, age_60_plus: 1710, resident: 6980, floating: 1050, household: 530 }
  ],

  // 乡村经济统计表
  stat_rural_economy: [
    { area_id: 'fujian', month: '2025-01', total_income: 2850, yoy_growth: 8.2, mom_growth: 3.5 },
    { area_id: 'fuzhou', month: '2025-01', total_income: 1850, yoy_growth: 7.8, mom_growth: 2.9 }
  ],

  // 监测事件统计表
  stat_mon_evt_rpt: [
    { area_id: 'fujian', month: '2025-01', total: 156, pending: 45, completed: 111, completion_rate: 71.2 },
    { area_id: 'fuzhou', month: '2025-01', total: 89, pending: 25, completed: 64, completion_rate: 71.9 }
  ],

  // 预警规则表
  sys_warn_rule: [
    { indicator_type: 'population', max_threshold: 13000, min_threshold: 12000, warn_level: 'high' },
    { indicator_type: 'economy', max_threshold: 3000, min_threshold: 2500, warn_level: 'medium' },
    { indicator_type: 'event', max_threshold: 200, min_threshold: 100, warn_level: 'medium' },
    { indicator_type: 'resource', max_threshold: 1000, min_threshold: 800, warn_level: 'low' }
  ]
};

// 1.1.1 乡村全域数据地图相关接口
export const fetchRuralMapData = async (params = {}) => {
  try {
    console.log('调用芋道GIS接口获取地图数据，参数:', params);

    // 模拟从芋道平台数据库查询数据
    const { area = '', dataTypes = [], timeRange = [] } = params;

    // 行政区划数据查询
    const areaData = MOCK_TABLES.sys_area.map(areaItem => ({
      id: areaItem.id,
      name: areaItem.name,
      level: areaItem.level,
      coordinates: generateAreaCoordinates(areaItem.id),
      boundaryType: 'administrative'
    }));

    // 网格数据查询
    const gridData = MOCK_TABLES.biz_unit_grid_div.map(grid => ({
      id: grid.id,
      name: grid.name,
      code: grid.code,
      coordinates: generateGridCoordinates(grid.id),
      boundaryType: 'grid'
    }));

    // 资源点位数据查询
    let resourceData = MOCK_TABLES.biz_rural_res.map(resource => ({
      id: resource.id,
      name: resource.name,
      type: resource.type,
      x: resource.x,
      y: resource.y,
      areaIds: [resource.area_id],
      gridId: getGridByArea(resource.area_id),
      createTime: '2025-01-15T08:00:00Z',
      status: resource.status,
      usageRate: resource.usage_rate
    }));

    // 事件点位数据查询
    let eventData = MOCK_TABLES.biz_mon_evt_info.map(event => ({
      id: event.id,
      name: event.name,
      level: event.level,
      status: event.status,
      x: event.x,
      y: event.y,
      areaIds: [event.area_id],
      gridId: getGridByArea(event.area_id),
      createTime: '2025-01-25T14:20:00Z',
      eventType: event.event_type
    }));

    // 应用筛选条件
    if (area) {
      resourceData = resourceData.filter(resource => resource.areaIds.includes(area));
      eventData = eventData.filter(event => event.areaIds.includes(area));
    }

    if (timeRange.length > 0) {
      const [start, end] = timeRange;
      resourceData = resourceData.filter(resource =>
        new Date(resource.createTime) >= new Date(start) &&
        new Date(resource.createTime) <= new Date(end)
      );
      eventData = eventData.filter(event =>
        new Date(event.createTime) >= new Date(start) &&
        new Date(event.createTime) <= new Date(end)
      );
    }

    if (!dataTypes.includes('resource')) {
      resourceData = [];
    }

    if (!dataTypes.includes('event')) {
      eventData = [];
    }

    return {
      areaData,
      gridData,
      resourceData,
      eventData
    };

  } catch (error) {
    console.warn('乡村地图数据接口调用失败，使用模拟数据:', error.message);
    return getMockMapData();
  }
};

// 1.1.2 乡村核心指标相关接口
export const fetchCoreIndicators = async (params = {}) => {
  try {
    console.log('调用芋道统计接口获取核心指标，参数:', params);

    const { timeRange = 'month' } = params;

    // 从芋道平台数据库查询指标数据
    const populationData = MOCK_TABLES.stat_rural_pop.find(item => item.area_id === 'fujian');
    const economyData = MOCK_TABLES.stat_rural_economy.find(item => item.area_id === 'fujian' && item.month === '2025-01');
    const eventData = MOCK_TABLES.stat_mon_evt_rpt.find(item => item.area_id === 'fujian' && item.month === '2025-01');
    const resourceCount = MOCK_TABLES.biz_rural_res.length;

    const baseData = [
      {
        id: '1',
        name: '乡村总人口',
        value: populationData?.total || 12560,
        unit: '人',
        change: 2.5,
        status: 'normal',
        warning: false,
        trendData: generateTrendData(12560, 7, 50)
      },
      {
        id: '2',
        name: '集体经济总收入',
        value: economyData?.total_income || 2850,
        unit: '万元',
        change: economyData?.yoy_growth || 8.2,
        status: 'normal',
        warning: false,
        trendData: generateTrendData(2850, 7, 50)
      },
      {
        id: '3',
        name: '事件总数',
        value: eventData?.total || 156,
        unit: '件',
        change: -3.1,
        status: 'normal',
        warning: false,
        trendData: generateTrendData(156, 7, -5)
      },
      {
        id: '4',
        name: '资源总数',
        value: resourceCount,
        unit: '个',
        change: 5.8,
        status: resourceCount > 1000 ? 'warning' : 'normal',
        warning: resourceCount > 1000,
        trendData: generateTrendData(resourceCount, 7, 10)
      }
    ];

    // 根据时间范围调整数据
    return adjustDataByTimeRange(baseData, timeRange);

  } catch (error) {
    console.warn('核心指标接口调用失败，使用模拟数据:', error.message);
    return getMockIndicators();
  }
};

// 1.1.3 乡村人口总览相关接口
export const fetchPopulationOverview = async (params = {}) => {
  try {
    console.log('调用芋道人口统计接口，参数:', params);

    const { timeRange = '12months' } = params;

    // 从芋道平台数据库查询人口数据
    const populationData = MOCK_TABLES.stat_rural_pop.find(item => item.area_id === 'fujian') || MOCK_TABLES.stat_rural_pop[0];

    const total = populationData.total;
    const male = populationData.male;
    const female = populationData.female;

    return {
      gender: {
        male,
        female,
        malePercentage: Math.round((male / total) * 1000) / 10,
        femalePercentage: Math.round((female / total) * 1000) / 10
      },
      age: [
        { range: '0-18岁', count: populationData.age_0_18, percentage: Math.round((populationData.age_0_18 / total) * 1000) / 10, color: '#10B981' },
        { range: '19-60岁', count: populationData.age_19_60, percentage: Math.round((populationData.age_19_60 / total) * 1000) / 10, color: '#F59E0B' },
        { range: '60岁以上', count: populationData.age_60_plus, percentage: Math.round((populationData.age_60_plus / total) * 1000) / 10, color: '#EF4444' }
      ],
      household: [
        { type: '常住人口', count: populationData.resident, percentage: Math.round((populationData.resident / total) * 1000) / 10, color: '#06B6D4' },
        { type: '流动人口', count: populationData.floating, percentage: Math.round((populationData.floating / total) * 1000) / 10, color: '#8B5CF6' },
        { type: '户籍人口', count: populationData.household, percentage: Math.round((populationData.household / total) * 1000) / 10, color: '#6B7280' }
      ],
      villageDistribution: generateVillageDistribution(),
      dynamic: generatePopulationDynamic(timeRange)
    };

  } catch (error) {
    console.warn('人口总览接口调用失败，使用模拟数据:', error.message);
    return getMockPopulationData();
  }
};

// 1.1.4 乡村资源分布相关接口
export const fetchResourceDistribution = async (params = {}) => {
  try {
    console.log('调用芋道资源管理接口，参数:', params);

    const { type = '', status = '' } = params;

    // 从芋道平台数据库查询资源数据
    let resources = MOCK_TABLES.biz_rural_res.map(resource => {
      const area = MOCK_TABLES.sys_area.find(a => a.id === resource.area_id);
      return {
        id: resource.id,
        name: resource.name,
        type: resource.type,
        status: resource.status,
        usageRate: resource.usage_rate,
        location: area ? `${area.name}` : '未知位置',
        ownership: getOwnership(resource.type),
        createTime: '2024-01-15',
        coordinates: { x: resource.x, y: resource.y }
      };
    });

    // 应用筛选条件
    if (type) {
      resources = resources.filter(resource => resource.type === type);
    }

    if (status) {
      resources = resources.filter(resource => resource.status === status);
    }

    return { resources };

  } catch (error) {
    console.warn('资源分布接口调用失败，使用模拟数据:', error.message);
    return getMockResourceData();
  }
};

// 获取阈值配置
export const fetchThresholdConfig = async () => {
  try {
    // 从芋道预警规则表获取阈值配置
    const rules = MOCK_TABLES.sys_warn_rule;

    return {
      population: {
        max: rules.find(r => r.indicator_type === 'population')?.max_threshold || 13000,
        min: rules.find(r => r.indicator_type === 'population')?.min_threshold || 12000
      },
      economy: {
        max: rules.find(r => r.indicator_type === 'economy')?.max_threshold || 3000,
        min: rules.find(r => r.indicator_type === 'economy')?.min_threshold || 2500
      },
      event: {
        max: rules.find(r => r.indicator_type === 'event')?.max_threshold || 200,
        min: rules.find(r => r.indicator_type === 'event')?.min_threshold || 100
      },
      resource: {
        max: rules.find(r => r.indicator_type === 'resource')?.max_threshold || 1000,
        min: rules.find(r => r.indicator_type === 'resource')?.min_threshold || 800
      }
    };
  } catch (error) {
    console.warn('阈值配置获取失败，使用默认配置');
    return {
      population: { max: 13000, min: 12000 },
      economy: { max: 3000, min: 2500 },
      event: { max: 200, min: 100 },
      resource: { max: 1000, min: 800 }
    };
  }
};

// 导出数据
export const exportData = async (dataType, params) => {
  try {
    console.log(`芋道平台导出${dataType}数据，参数:`, params);

    // 模拟芋道平台导出功能
    const timestamp = new Date().toISOString().split('T')[0];
    const fileNameMap = {
      'population': `乡村人口数据_${timestamp}.xlsx`,
      'village': `村庄数据_${timestamp}.xlsx`,
      'indicator': `核心指标数据_${timestamp}.xlsx`,
      'resource': `资源分布数据_${timestamp}.xlsx`,
      'map': `地图数据_${timestamp}.xlsx`
    };

    const fileName = fileNameMap[dataType] || `乡村数据_${timestamp}.xlsx`;

    // 模拟文件下载
    const blob = new Blob(['芋道平台导出数据'], { type: 'application/vnd.ms-excel' });
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = fileName;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    window.URL.revokeObjectURL(url);

    return { success: true, message: '导出成功', fileName };
  } catch (error) {
    console.error('导出失败:', error);
    throw new Error('导出功能暂不可用');
  }
};

// 辅助函数
function generateAreaCoordinates(areaId) {
  // 模拟生成行政区域坐标
  const baseCoords = {
    'fujian': [{ lat: 26.5, lng: 117.0 }, { lat: 26.5, lng: 118.5 }, { lat: 25.5, lng: 118.5 }, { lat: 25.5, lng: 117.0 }],
    'fuzhou': [{ lat: 26.3, lng: 119.0 }, { lat: 26.3, lng: 119.5 }, { lat: 25.8, lng: 119.5 }, { lat: 25.8, lng: 119.0 }],
    'gulou': [{ lat: 26.8, lng: 119.2 }, { lat: 26.8, lng: 119.3 }, { lat: 26.7, lng: 119.3 }, { lat: 26.7, lng: 119.2 }],
    'minhou': [{ lat: 26.1, lng: 119.0 }, { lat: 26.1, lng: 119.2 }, { lat: 25.9, lng: 119.2 }, { lat: 25.9, lng: 119.0 }]
  };

  return baseCoords[areaId] || baseCoords.fujian;
}

function generateGridCoordinates(gridId) {
  // 模拟生成网格坐标
  const baseCoords = {
    'grid-001': [{ lat: 26.8, lng: 117.6 }, { lat: 26.8, lng: 117.7 }, { lat: 26.7, lng: 117.7 }, { lat: 26.7, lng: 117.6 }],
    'grid-002': [{ lat: 26.7, lng: 117.7 }, { lat: 26.7, lng: 117.8 }, { lat: 26.6, lng: 117.8 }, { lat: 26.6, lng: 117.7 }],
    'grid-003': [{ lat: 26.1, lng: 119.1 }, { lat: 26.1, lng: 119.15 }, { lat: 26.05, lng: 119.15 }, { lat: 26.05, lng: 119.1 }]
  };

  return baseCoords[gridId] || baseCoords['grid-001'];
}

function getGridByArea(areaId) {
  const grid = MOCK_TABLES.biz_unit_grid_div.find(g => g.area_id === areaId);
  return grid ? grid.id : 'grid-001';
}

function generateTrendData(baseValue, count, change) {
  const data = [];
  const today = new Date();

  for (let i = count - 1; i >= 0; i--) {
    const date = new Date(today);
    date.setDate(date.getDate() - i);

    data.push({
      date: `${date.getMonth() + 1}-${date.getDate()}`,
      value: Math.round(baseValue + (change * i))
    });
  }

  return data;
}

function adjustDataByTimeRange(data, timeRange) {
  if (timeRange === 'day') {
    return data.map(indicator => ({
      ...indicator,
      change: parseFloat((indicator.change / 30).toFixed(1)),
      value: Math.round(indicator.value / 30),
      trendData: indicator.trendData.slice(-3)
    }));
  } else if (timeRange === 'week') {
    return data.map(indicator => ({
      ...indicator,
      change: parseFloat((indicator.change / 4).toFixed(1)),
      value: Math.round(indicator.value / 4),
      trendData: indicator.trendData.slice(-7)
    }));
  }

  return data;
}

function generateVillageDistribution() {
  const villages = ['张村', '李村', '王村', '赵村', '刘村', '陈村', '杨村', '黄村'];
  const colors = ['#3B82F6', '#EF4444', '#10B981', '#F59E0B', '#8B5CF6', '#06B6D4', '#F97316', '#84CC16'];

  return villages.map((name, index) => ({
    name,
    population: Math.floor(Math.random() * 1000) + 1000,
    trend: (Math.random() * 6 - 3).toFixed(1),
    color: colors[index]
  }));
}

function generatePopulationDynamic(timeRange) {
  const months = timeRange === '3months' ? ['10月', '11月', '12月'] :
    timeRange === '6months' ? ['7月', '8月', '9月', '10月', '11月', '12月'] :
      ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'];

  const baseValue = 12420;

  return {
    monthlyIncrease: 45,
    monthlyDecrease: 32,
    netGrowth: 13,
    trend: months.map((month, index) => ({
      month,
      value: baseValue + (index * 30),
      increase: 38 + (index * 2),
      decrease: 25 + (index * 1)
    }))
  };
}

function getOwnership(type) {
  const ownershipMap = {
    'land': '集体所有',
    'water': '集体所有',
    'culture': type === 'culture' ? '文物保护' : '个体经营'
  };
  return ownershipMap[type] || '集体所有';
}

// 模拟数据生成函数（备用）
const getMockMapData = () => ({
  areaData: [],
  gridData: [],
  resourceData: [],
  eventData: []
});

const getMockIndicators = () => [
  {
    id: '1',
    name: '乡村总人口',
    value: 12560,
    unit: '人',
    change: 2.5,
    status: 'normal',
    warning: false
  },
  {
    id: '2',
    name: '集体经济总收入',
    value: 2850,
    unit: '万元',
    change: 8.2,
    status: 'normal',
    warning: false
  },
  {
    id: '3',
    name: '事件总数',
    value: 156,
    unit: '件',
    change: -3.1,
    status: 'normal',
    warning: false
  },
  {
    id: '4',
    name: '资源总数',
    value: 892,
    unit: '个',
    change: 5.8,
    status: 'normal',
    warning: false
  }
];

const getMockPopulationData = () => ({
  gender: { male: 6420, female: 6140, malePercentage: 51.1, femalePercentage: 48.9 },
  age: [
    { range: '0-18岁', count: 2150, percentage: 17.1 },
    { range: '19-60岁', count: 7850, percentage: 62.5 },
    { range: '60岁以上', count: 2560, percentage: 20.4 }
  ],
  household: [
    { type: '常住人口', count: 10240, percentage: 81.5 },
    { type: '流动人口', count: 1560, percentage: 12.4 },
    { type: '户籍人口', count: 760, percentage: 6.1 }
  ],
  villageDistribution: [
    { name: '张村', population: 2150, trend: 2.1 },
    { name: '李村', population: 1890, trend: 1.5 }
  ],
  dynamic: {
    monthlyIncrease: 45,
    monthlyDecrease: 32,
    netGrowth: 13,
    trend: [
      { month: '6月', value: 12420 },
      { month: '7月', value: 12450 }
    ]
  }
});

const getMockResourceData = () => ({
  resources: [
    {
      id: 'land-001',
      name: '高标准农田A区',
      type: 'land',
      status: 'using',
      usageRate: 85,
      location: '张村',
      ownership: '集体所有',
      createTime: '2024-01-15'
    }
  ]
});

// 全局态势总览接口（兼容旧代码）
export const fetchRuralGlobalOverview = async (params = {}) => {
  return {
    stats: [],
    region_case_distribution: [],
    case_type_distribution: []
  };
};
