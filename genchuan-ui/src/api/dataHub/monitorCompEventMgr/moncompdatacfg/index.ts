import request from '@/config/axios'

// 监测部件数据配置 VO
export interface MonCompDataCfgVO {
  id: number // 主键ID
  monCompDataId: string // 配置ID
  compCatId: string // 部件分类ID
  compCatName: string // 部件分类名称
  dataType: string // 数据类型
  coordSystem: string // 坐标系类型
  accuracyLevel: string // 定位精度级别
  elevationDatum: string // 高程基准
  fieldName: string // 字段名称
  fieldCode: string // 字段代码
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 监测部件数据配置 API
export const MonCompDataCfgApi = {
  // 查询监测部件数据配置分页
  getMonCompDataCfgPage: async (params: any) => {
    return await request.get({ url: `/datacenter/mon-comp-data-cfg/page`, params })
  },

  // 查询监测部件数据配置详情
  getMonCompDataCfg: async (id: number) => {
    return await request.get({ url: `/datacenter/mon-comp-data-cfg/get?id=` + id })
  },

  // 新增监测部件数据配置
  createMonCompDataCfg: async (data: MonCompDataCfgVO) => {
    return await request.post({ url: `/datacenter/mon-comp-data-cfg/create`, data })
  },

  // 修改监测部件数据配置
  updateMonCompDataCfg: async (data: MonCompDataCfgVO) => {
    return await request.put({ url: `/datacenter/mon-comp-data-cfg/update`, data })
  },

  // 删除监测部件数据配置
  deleteMonCompDataCfg: async (id: number) => {
    return await request.delete({ url: `/datacenter/mon-comp-data-cfg/delete?id=` + id })
  },

  // 导出监测部件数据配置 Excel
  exportMonCompDataCfg: async (params) => {
    return await request.download({ url: `/datacenter/mon-comp-data-cfg/export-excel`, params })
  }
}