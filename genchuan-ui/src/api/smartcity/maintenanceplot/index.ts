import request from '@/config/axios'

// 养护地块 VO
export interface MaintenancePlotVO {
  id: number // 主键
  plotName: string // 地块名称
  plotDescription: string // 地块描述
  ownership: string // 归属信息
  acreage: string // 地块面积
  latitude: string // 纬度
  longitude: string // 经度
  greeningType: string // 绿化类型
  lastMaintenanceDate: Date // 上次养护的日期
  nextMaintenanceDate: Date // 下次养护的计划日期
}

// 养护地块 API
export const MaintenancePlotApi = {
  // 查询养护地块分页
  getMaintenancePlotPage: async (params: any) => {
    return await request.get({ url: `/smartcity/maintenance-plot/page`, params })
  },

  // 查询养护地块详情
  getMaintenancePlot: async (id: number) => {
    return await request.get({ url: `/smartcity/maintenance-plot/get?id=` + id })
  },

  // 新增养护地块
  createMaintenancePlot: async (data: MaintenancePlotVO) => {
    return await request.post({ url: `/smartcity/maintenance-plot/create`, data })
  },

  // 修改养护地块
  updateMaintenancePlot: async (data: MaintenancePlotVO) => {
    return await request.put({ url: `/smartcity/maintenance-plot/update`, data })
  },

  // 删除养护地块
  deleteMaintenancePlot: async (id: number) => {
    return await request.delete({ url: `/smartcity/maintenance-plot/delete?id=` + id })
  },

  // 导出养护地块 Excel
  exportMaintenancePlot: async (params) => {
    return await request.download({ url: `/smartcity/maintenance-plot/export-excel`, params })
  }
}