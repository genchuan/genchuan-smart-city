import request from '@/config/axios'

// 救援设备分类配置 VO
export interface EmerDeviceCatVO {
  id: number // 自增主键ID
  deviceCatId: string // 分类ID
  parentCatId: string // 父分类ID
  catLevel: string // 分类层级
  catCode: string // 分类编码
  catName: string // 分类名称
  catDesc: string // 分类说明
  applyRegionCode: string // 适用区域代码
  applyRegionName: string // 适用区域名称
}

// 救援设备分类配置 API
export const EmerDeviceCatApi = {
  // 查询救援设备分类配置分页
  getEmerDeviceCatPage: async (params: any) => {
    return await request.get({ url: `/datacenter/emer-device-cat/page`, params })
  },

  // 查询救援设备分类配置详情
  getEmerDeviceCat: async (id: number) => {
    return await request.get({ url: `/datacenter/emer-device-cat/get?id=` + id })
  },

  // 新增救援设备分类配置
  createEmerDeviceCat: async (data: EmerDeviceCatVO) => {
    return await request.post({ url: `/datacenter/emer-device-cat/create`, data })
  },

  // 修改救援设备分类配置
  updateEmerDeviceCat: async (data: EmerDeviceCatVO) => {
    return await request.put({ url: `/datacenter/emer-device-cat/update`, data })
  },

  // 删除救援设备分类配置
  deleteEmerDeviceCat: async (id: number) => {
    return await request.delete({ url: `/datacenter/emer-device-cat/delete?id=` + id })
  },

  // 导出救援设备分类配置 Excel
  exportEmerDeviceCat: async (params) => {
    return await request.download({ url: `/datacenter/emer-device-cat/export-excel`, params })
  },
}