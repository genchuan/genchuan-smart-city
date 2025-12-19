import request from '@/config/axios'

/**
 * 筛选条件时间范围配置
 */
export interface TimeRangeConfig {
  type: 'today' | 'last7' | 'last30' | 'currentMonth' | 'lastMonth' | 'custom' | 'natural' | 'lastNHours';
  value?: [string, string] | number | string;
  startTime?: string;
  endTime?: string;
  unit?: 'hour' | 'day' | 'week' | 'month' | 'year';
  count?: number;
}

/**
 * 筛选条件区域范围配置
 */
export interface AreaRangeConfig {
  type: 'all' | 'district' | 'street' | 'community' | 'grid' | 'customPolygon' | 'adminBoundary' | 'beidouGrid';
  value?: string | number | any[] | Record<string, any>;
  codes?: string[];
  name?: string;
  polygon?: Array<[number, number]>;
}

/**
 * 筛选条件核心配置
 */
export interface FilterConditions {
  timeRange: TimeRangeConfig;
  areaRange: AreaRangeConfig;
  dataType: string[];
  statusType: string[];
  industryParams?: Record<string, any>;
  customParams?: Record<string, any>;
}

/**
 * 筛选条件实体
 */
export interface FilterCondition {
  id?: string;
  name: string;
  category: string;
  isShared: boolean;
  isPrivate: boolean;
  isTop: boolean;
  conditions: FilterConditions;
  createTime: string;
  useCount: number;
  updateTime?: string;
  description?: string;
  createdBy?: string;
}

/**
 * 分页查询参数
 */
export interface FilterQueryParams {
  category?: string;
  isTop?: boolean;
  search?: string;
  page?: number;
  pageSize?: number;
  sortBy?: string;
  isShared?: boolean;
  isPrivate?: boolean;
}

/**
 * 分页响应
 */
export interface FilterPageResponse {
  data: FilterCondition[];
  total: number;
  page: number;
  pageSize: number;
}

/**
 * 筛选结果项
 */
export interface FilterResultItem {
  id: string;
  name: string;
  dataType: string;
  status: string;
  area: string;
  time: string;
  value?: number;
  unit?: string;
  icon?: string;
  color?: string;
  link?: string;
  tags?: string[];
}

/**
 * 筛选结果组
 */
export interface FilterResultGroup {
  type: string;
  label: string;
  icon: string;
  color: string;
  count: number;
  items: FilterResultItem[];
}

/**
 * 筛选结果
 */
export interface FilterResults {
  total: number;
  groups: FilterResultGroup[];
  summary?: Record<string, number>;
  timestamp: string;
}

/**
 * 获取筛选条件列表
 */
export const getFilterConditions = async (params?: FilterQueryParams): Promise<FilterPageResponse> => {
  const response = await request.get('/api/system/filter/conditions', {
    params: {
      page: params?.page || 1,
      pageSize: params?.pageSize || 50,
      sortBy: params?.sortBy || 'isTop_desc,useCount_desc,updateTime_desc',
      ...params
    }
  });
  return response.data;
};

/**
 * 保存筛选条件
 */
export const saveFilterCondition = async (
  data: Omit<FilterCondition, 'id' | 'createTime' | 'useCount' | 'updateTime'>
): Promise<FilterCondition> => {
  const response = await request.post('/api/system/filter/save', data);
  return response.data;
};

/**
 * 更新筛选条件
 */
export const updateFilterCondition = async (id: string, data: Partial<FilterCondition>): Promise<FilterCondition> => {
  const response = await request.put(`/api/system/filter/update/${id}`, data);
  return response.data;
};

/**
 * 召回筛选条件
 */
export const recallFilterCondition = async (id: string): Promise<FilterCondition> => {
  const response = await request.post('/api/system/filter/recall', { id });
  return response.data;
};

/**
 * 更新筛选条件使用次数
 */
export const updateFilterUseCount = async (id: string): Promise<void> => {
  await request.post('/api/system/filter/updateUseCount', { id });
};

/**
 * 置顶/取消置顶筛选条件
 */
export const toggleFilterTop = async (id: string, isTop: boolean): Promise<void> => {
  await request.post('/api/system/filter/toggleTop', { id, isTop });
};

/**
 * 清空当前筛选条件
 */
export const clearFilterConditions = async (): Promise<void> => {
  await request.post('/api/system/filter/clear');
};

/**
 * 批量删除筛选条件
 */
export const batchDeleteFilters = async (ids: string[]): Promise<void> => {
  await request.post('/api/system/filter/batchDelete', { ids });
};

/**
 * 导入筛选条件
 */
export const importFilterConditions = async (file: FormData): Promise<{ success: number; failure: number }> => {
  const response = await request.post('/api/system/filter/import', file, {
    headers: { 'Content-Type': 'multipart/form-data' }
  });
  return response.data;
};

/**
 * 导出筛选条件
 */
export const exportFilterConditions = async (ids?: string[]): Promise<Blob> => {
  const response = await request.post('/api/system/filter/export', { ids }, {
    responseType: 'blob'
  });
  return response.data;
};

/**
 * 获取筛选结果计数
 */
export const getFilterResultCount = async (conditions: FilterConditions): Promise<{ count: number }> => {
  const response = await request.post('/api/system/filter/resultCount', conditions);
  return response.data;
};

/**
 * 验证筛选条件有效性
 */
export const validateFilterConditions = async (conditions: FilterConditions): Promise<{ valid: boolean; message?: string }> => {
  try {
    const response = await request.post('/api/system/filter/validate', conditions);
    return response.data;
  } catch {
    return { valid: true };
  }
};

/**
 * 获取筛选结果数据
 */
export const getFilterResults = async (conditions: FilterConditions): Promise<FilterResults> => {
  const response = await request.post('/api/system/filter/results', conditions);
  return response.data;
};

/**
 * 筛选条件收藏夹项
 */
export interface FilterFavorite {
  id: string;
  name: string;
  conditions: FilterConditions;
  createTime: string;
  lastUsed?: string;
}

/**
 * 会话级筛选条件记忆
 */
export const sessionStorageFilter = {
  /**
   * 保存当前筛选条件到会话存储
   */
  save: (conditions: FilterConditions, timestamp: number = Date.now()): void => {
    try {
      const data = {
        conditions,
        timestamp,
        version: '1.0'
      };
      sessionStorage.setItem('global_filter_current', JSON.stringify(data));
    } catch (error) {
      console.warn('Failed to save filter to session storage:', error);
    }
  },

  /**
   * 获取会话存储中的筛选条件
   */
  get: (): FilterConditions | null => {
    try {
      const data = sessionStorage.getItem('global_filter_current');
      if (!data) return null;

      const parsed = JSON.parse(data);

      // 检查数据是否过期（24小时）
      if (Date.now() - parsed.timestamp > 24 * 60 * 60 * 1000) {
        sessionStorage.removeItem('global_filter_current');
        return null;
      }

      // 验证数据结构
      if (!parsed.conditions || typeof parsed.conditions !== 'object') {
        sessionStorage.removeItem('global_filter_current');
        return null;
      }

      return parsed.conditions;
    } catch (error) {
      console.warn('Failed to get filter from session storage:', error);
      sessionStorage.removeItem('global_filter_current');
      return null;
    }
  },

  /**
   * 清空会话存储中的筛选条件
   */
  clear: (): void => {
    sessionStorage.removeItem('global_filter_current');
  },

  /**
   * 保存筛选条件到收藏夹
   */
  saveFavorite: (name: string, conditions: FilterConditions): FilterFavorite => {
    const favorites = sessionStorageFilter.getFavorites();
    const favorite: FilterFavorite = {
      id: `fav_${Date.now()}_${Math.random().toString(36).substr(2, 9)}`,
      name,
      conditions,
      createTime: new Date().toISOString(),
      lastUsed: new Date().toISOString()
    };

    favorites.unshift(favorite);

    // 限制收藏夹数量
    const trimmedFavorites = favorites.slice(0, 20);
    localStorage.setItem('global_filter_favorites', JSON.stringify(trimmedFavorites));

    return favorite;
  },

  /**
   * 获取收藏夹列表
   */
  getFavorites: (): FilterFavorite[] => {
    try {
      const data = localStorage.getItem('global_filter_favorites');
      return data ? JSON.parse(data) : [];
    } catch (error) {
      console.warn('Failed to get filter favorites:', error);
      return [];
    }
  },

  /**
   * 从收藏夹中删除项目
   */
  removeFavorite: (id: string): boolean => {
    try {
      const favorites = sessionStorageFilter.getFavorites();
      const filtered = favorites.filter(fav => fav.id !== id);
      localStorage.setItem('global_filter_favorites', JSON.stringify(filtered));
      return true;
    } catch (error) {
      console.warn('Failed to remove filter favorite:', error);
      return false;
    }
  },

  /**
   * 更新收藏夹项目使用时间
   */
  updateFavoriteUsage: (id: string): void => {
    try {
      const favorites = sessionStorageFilter.getFavorites();
      const index = favorites.findIndex(fav => fav.id === id);
      if (index !== -1) {
        favorites[index].lastUsed = new Date().toISOString();
        localStorage.setItem('global_filter_favorites', JSON.stringify(favorites));
      }
    } catch (error) {
      console.warn('Failed to update filter favorite usage:', error);
    }
  }
};

/**
 * 防抖函数
 */
export const debounce = <T extends (...args: any[]) => any>(
  func: T,
  wait: number
): ((...args: Parameters<T>) => void) => {
  let timeout: NodeJS.Timeout | null = null;

  return (...args: Parameters<T>) => {
    if (timeout) {
      clearTimeout(timeout);
    }

    timeout = setTimeout(() => {
      func(...args);
      timeout = null;
    }, wait);
  };
};

/**
 * 节流函数
 */
export const throttle = <T extends (...args: any[]) => any>(
  func: T,
  limit: number
): ((...args: Parameters<T>) => void) => {
  let inThrottle: boolean = false;

  return (...args: Parameters<T>) => {
    if (!inThrottle) {
      func(...args);
      inThrottle = true;
      setTimeout(() => {
        inThrottle = false;
      }, limit);
    }
  };
};

/**
 * 筛选条件常量定义
 */
export const FILTER_CONSTANTS = {
  TIME_RANGE_TYPES: [
    { value: 'today', label: '今日', unit: 'day' },
    { value: 'last7', label: '近7天', unit: 'day' },
    { value: 'last30', label: '近30天', unit: 'day' },
    { value: 'currentMonth', label: '本月', unit: 'month' },
    { value: 'lastMonth', label: '上月', unit: 'month' },
    { value: 'custom', label: '自定义日期', unit: 'day' },
    { value: 'natural', label: '自然周期', unit: 'week' },
    { value: 'lastNHours', label: '近N小时', unit: 'hour' }
  ],

  AREA_RANGE_TYPES: [
    { value: 'all', label: '全域', level: 0 },
    { value: 'district', label: '区县', level: 1 },
    { value: 'street', label: '街道', level: 2 },
    { value: 'community', label: '社区', level: 3 },
    { value: 'grid', label: '网格', level: 4 },
    { value: 'beidouGrid', label: '北斗网格', level: 4 },
    { value: 'customPolygon', label: '自定义区域', level: -1 },
    { value: 'adminBoundary', label: '行政边界', level: -1 }
  ],

  DATA_TYPES: [
    { value: 'all', label: '全部', category: 'all' },
    { value: 'operating', label: '运行数据', category: 'operation' },
    { value: 'resource', label: '资源数据', category: 'resource' },
    { value: 'service', label: '服务数据', category: 'service' },
    { value: 'warning', label: '预警数据', category: 'monitor' },
    { value: 'event', label: '事件数据', category: 'event' },
    { value: 'statistical', label: '统计数据', category: 'statistics' }
  ],

  STATUS_TYPES: [
    { value: 'all', label: '全部', category: 'all' },
    { value: 'normal', label: '正常', category: 'status' },
    { value: 'abnormal', label: '异常', category: 'status' },
    { value: 'warning', label: '预警', category: 'monitor' },
    { value: 'handling', label: '处置中', category: 'process' },
    { value: 'completed', label: '已办结', category: 'process' },
    { value: 'untreated', label: '未处置', category: 'process' },
    { value: 'transit', label: '在途', category: 'status' }
  ],

  // 数据类型与状态类型的关联关系
  DATA_STATUS_MAPPING: {
    event: ['handling', 'completed', 'untreated', 'all'],
    warning: ['normal', 'abnormal', 'warning', 'all'],
    operating: ['normal', 'abnormal', 'all'],
    service: ['normal', 'abnormal', 'all'],
    resource: ['normal', 'abnormal', 'all'],
    statistical: ['all'],
    default: ['all', 'normal', 'abnormal', 'warning', 'handling', 'completed', 'untreated', 'transit']
  },

  // 默认筛选条件
  DEFAULT_CONDITIONS: {
    timeRange: { type: 'today' as const },
    areaRange: { type: 'all' as const },
    dataType: ['all'],
    statusType: ['all'],
    industryParams: {},
    customParams: {}
  },

  // 分类选项
  CATEGORIES: [
    { value: 'common', label: '通用' },
    { value: 'personal', label: '个人' },
    { value: 'department', label: '部门' },
    { value: 'project', label: '项目' },
    { value: 'temporary', label: '临时' }
  ],

  // 行业维度选项
  INDUSTRY_DIMENSIONS: [
    { key: 'assetType', label: '资产类型', options: ['设备', '车辆', '设施', '其他'] },
    { key: 'deviceModel', label: '设备型号', options: ['型号A', '型号B', '型号C'] },
    { key: 'warningLevel', label: '预警等级', options: ['一级', '二级', '三级', '四级'] },
    { key: 'priority', label: '优先级', options: ['高', '中', '低'] }
  ],

  // 结果状态颜色映射
  STATUS_COLORS: {
    normal: '#67c23a',
    abnormal: '#f56c6c',
    warning: '#e6a23c',
    handling: '#409eff',
    completed: '#909399',
    untreated: '#f56c6c',
    transit: '#e6a23c'
  },

  // 结果分组颜色
  GROUP_COLORS: {
    operating: '#409eff',
    warning: '#e6a23c',
    event: '#f56c6c',
    resource: '#67c23a',
    service: '#9c27b0',
    statistical: '#795548'
  }
} as const;

/**
 * 筛选条件工具函数
 */
export const FilterUtils = {
  /**
   * 生成筛选条件唯一标识
   */
  generateFilterId: (): string => {
    return `filter_${Date.now()}_${Math.random().toString(36).substr(2, 9)}`;
  },

  /**
   * 格式化时间范围显示
   */
  formatTimeRange: (timeRange: TimeRangeConfig): string => {
    const typeConfig = FILTER_CONSTANTS.TIME_RANGE_TYPES.find(t => t.value === timeRange.type);
    const baseLabel = typeConfig?.label || timeRange.type;

    if (timeRange.type === 'lastNHours' && timeRange.value) {
      return `${baseLabel} (${timeRange.value}小时)`;
    }

    if (timeRange.type === 'custom' && timeRange.value) {
      const [start, end] = timeRange.value as [string, string];
      return `${start} 至 ${end}`;
    }

    return baseLabel;
  },

  /**
   * 格式化区域范围显示
   */
  formatAreaRange: (areaRange: AreaRangeConfig): string => {
    const typeConfig = FILTER_CONSTANTS.AREA_RANGE_TYPES.find(t => t.value === areaRange.type);
    const baseLabel = typeConfig?.label || areaRange.type;

    if (areaRange.name) {
      return `${baseLabel}: ${areaRange.name}`;
    }

    if (areaRange.codes && areaRange.codes.length > 0) {
      return `${baseLabel} (${areaRange.codes.length}个区域)`;
    }

    return baseLabel;
  },

  /**
   * 检查筛选条件是否为空
   */
  isEmptyConditions: (conditions: FilterConditions): boolean => {
    const { timeRange, areaRange, dataType, statusType, industryParams } = conditions;

    return (
      timeRange.type === 'today' &&
      areaRange.type === 'all' &&
      dataType.length === 1 && dataType[0] === 'all' &&
      statusType.length === 1 && statusType[0] === 'all' &&
      (!industryParams || Object.keys(industryParams).length === 0)
    );
  },

  /**
   * 克隆筛选条件
   */
  cloneConditions: (conditions: FilterConditions): FilterConditions => {
    return JSON.parse(JSON.stringify(conditions));
  },

  /**
   * 合并筛选条件
   */
  mergeConditions: (base: FilterConditions, override: Partial<FilterConditions>): FilterConditions => {
    return {
      ...base,
      ...override,
      timeRange: { ...base.timeRange, ...override.timeRange },
      areaRange: { ...base.areaRange, ...override.areaRange },
      dataType: override.dataType || base.dataType,
      statusType: override.statusType || base.statusType,
      industryParams: { ...base.industryParams, ...override.industryParams },
      customParams: { ...base.customParams, ...override.customParams }
    };
  },

  /**
   * 验证筛选条件
   */
  validateConditions: (conditions: FilterConditions): string[] => {
    const errors: string[] = [];

    // 验证时间范围
    if (!conditions.timeRange.type) {
      errors.push('时间范围类型不能为空');
    }

    // 验证区域范围
    if (!conditions.areaRange.type) {
      errors.push('区域范围类型不能为空');
    }

    // 验证数据类型
    if (!conditions.dataType || conditions.dataType.length === 0) {
      errors.push('至少选择一种数据类型');
    }

    // 验证状态类型
    if (!conditions.statusType || conditions.statusType.length === 0) {
      errors.push('至少选择一种状态类型');
    }

    return errors;
  },

  /**
   * 获取状态标签
   */
  getStatusLabel: (status: string): string => {
    const statusConfig = FILTER_CONSTANTS.STATUS_TYPES.find(t => t.value === status);
    return statusConfig?.label || status;
  },

  /**
   * 获取状态颜色
   */
  getStatusColor: (status: string): string => {
    return FILTER_CONSTANTS.STATUS_COLORS[status as keyof typeof FILTER_CONSTANTS.STATUS_COLORS] || '#909399';
  },

  /**
   * 获取数据类型标签
   */
  getDataTypeLabel: (type: string): string => {
    const typeConfig = FILTER_CONSTANTS.DATA_TYPES.find(t => t.value === type);
    return typeConfig?.label || type;
  }
};
