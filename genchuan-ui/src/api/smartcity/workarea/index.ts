import request from '@/config/axios'

// 作业区域 VO
export interface WorkAreaVO {
  id: number // 主键
  areaNumber: string // 区域编号
  areaName: string // 区域名称
  regionalLocation: string // 区域位置
  regionalArea: string // 区域面积
  personnelCapacity: string // 人员容量
  environmentalFactors: string // 环境风险因素
  requirementsMeasures: string // 防护措施要求
}

// 作业区域 API
export const WorkAreaApi = {
  // 查询作业区域分页
  getWorkAreaPage: async (params: any) => {
    return await request.get({ url: `/smartcity/work-area/page`, params })
  },

  // 查询作业区域详情
  getWorkArea: async (id: number) => {
    return await request.get({ url: `/smartcity/work-area/get?id=` + id })
  },

  // 新增作业区域
  createWorkArea: async (data: WorkAreaVO) => {
    return await request.post({ url: `/smartcity/work-area/create`, data })
  },

  // 修改作业区域
  updateWorkArea: async (data: WorkAreaVO) => {
    return await request.put({ url: `/smartcity/work-area/update`, data })
  },

  // 删除作业区域
  deleteWorkArea: async (id: number) => {
    return await request.delete({ url: `/smartcity/work-area/delete?id=` + id })
  },

  // 导出作业区域 Excel
  exportWorkArea: async (params) => {
    return await request.download({ url: `/smartcity/work-area/export-excel`, params })
  }
}