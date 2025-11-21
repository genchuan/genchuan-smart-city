import request from '@/config/axios'

// 管廊安全 VO
export interface PipeGallerySafetyVO {
  id: number // 主键
  pipeGalleryName: string // 管廊名称
  locationGallery: string // 管廊位置
  pipeGalleryNumber: string // 管廊编号
  typePipeline: string // 入廊管线类型
  numberOfEntrancePipelines: string // 入廊管线数量
  safetyStatusStructure: string // 管廊结构安全状况
  leakagePipeGallery: string // 管廊渗漏水情况
  temperatureAndHumidity: string // 温湿度
  harmfulGasConcentration: string // 有害气体浓度
  oxygenContent: string // 氧气含量
  operationVentilationEquipment: string // 通风设备运行状态
  warningLevel: string // 预警级别
  maintenanceRecord: string // 维修记录
  maintenanceTime: Date // 维修时间
  emergencyMaterialSituation: string // 应急物资储备情况
}

// 管廊安全 API
export const PipeGallerySafetyApi = {
  // 查询管廊安全分页
  getPipeGallerySafetyPage: async (params: any) => {
    return await request.get({ url: `/smartcity/pipe-gallery-safety/page`, params })
  },

  // 查询管廊安全详情
  getPipeGallerySafety: async (id: number) => {
    return await request.get({ url: `/smartcity/pipe-gallery-safety/get?id=` + id })
  },

  // 新增管廊安全
  createPipeGallerySafety: async (data: PipeGallerySafetyVO) => {
    return await request.post({ url: `/smartcity/pipe-gallery-safety/create`, data })
  },

  // 修改管廊安全
  updatePipeGallerySafety: async (data: PipeGallerySafetyVO) => {
    return await request.put({ url: `/smartcity/pipe-gallery-safety/update`, data })
  },

  // 删除管廊安全
  deletePipeGallerySafety: async (id: number) => {
    return await request.delete({ url: `/smartcity/pipe-gallery-safety/delete?id=` + id })
  },

  // 导出管廊安全 Excel
  exportPipeGallerySafety: async (params) => {
    return await request.download({ url: `/smartcity/pipe-gallery-safety/export-excel`, params })
  }
}