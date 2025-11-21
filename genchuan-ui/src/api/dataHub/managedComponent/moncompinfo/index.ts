import request from '@/config/axios'

// 监测部件信息 VO
export interface MonCompInfoVO {
  id: number // 主键ID
  monCompId: string // 部件ID
  compCode: string // 部件标识码
  compName: string // 部件名称
  compCatId: string // 部件分类ID
  deptCode: string // 主管部门代码
  deptName: string // 主管部门名称
  regionCode: string // 所在行政区域代码
  regionName: string // 所在行政区域名称
  gridId: string // 所在网格ID
  gridName: string // 所在网格名称
  coordX: number // 坐标X，经度
  coordY: number // 坐标Y，纬度
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 监测部件信息 API
export const MonCompInfoApi = {
  // 查询监测部件信息分页
  getMonCompInfoPage: async (params: any) => {
    return await request.get({ url: `/datacenter/mon-comp-info/page`, params })
  },

  // 查询监测部件信息详情
  getMonCompInfo: async (id: number) => {
    return await request.get({ url: `/datacenter/mon-comp-info/get?id=` + id })
  },

  // 新增监测部件信息
  createMonCompInfo: async (data: MonCompInfoVO) => {
    return await request.post({ url: `/datacenter/mon-comp-info/create`, data })
  },

  // 修改监测部件信息
  updateMonCompInfo: async (data: MonCompInfoVO) => {
    return await request.put({ url: `/datacenter/mon-comp-info/update`, data })
  },

  // 删除监测部件信息
  deleteMonCompInfo: async (id: number) => {
    return await request.delete({ url: `/datacenter/mon-comp-info/delete?id=` + id })
  },

  // 导出监测部件信息 Excel
  exportMonCompInfo: async (params) => {
    return await request.download({ url: `/datacenter/mon-comp-info/export-excel`, params })
  },
}