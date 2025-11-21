import request from '@/config/axios'

// 巡查巡检问题上报记录 VO
export interface InspectProblemRptVO {
  id: number // 主键
  problemId: string // 问题ID
  problemCode: string // 问题编码
  problemName: string // 问题名称
  matterTypeId: string // 问题类型ID
  matterTypeName: string // 问题类型名称
  taskId: string // 关联任务ID
  taskCode: string // 关联任务编码
  rptUserId: string // 上报人员ID
  rptUserName: string // 上报人员姓名
  gridId: string // 所属网格ID
  gridName: string // 所属网格名称
  problemLocX: number // 问题位置坐标X
  problemLocY: number // 问题位置坐标Y
  problemLocDesc: string // 问题位置描述
  problemDesc: string // 问题描述
  problemPhotoUrls: string // 问题照片URLs
  rptTime: Date // 上报时间
  problemStatus: string // 问题状态
  rejectReason: string // 驳回原因
  bizCreateUser: string // 创建人
  bizCreateTime: Date // 创建时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检问题上报记录 API
export const InspectProblemRptApi = {
  // 查询巡查巡检问题上报记录分页
  getInspectProblemRptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-problem-sb/page`, params })
  },

  // 查询巡查巡检问题上报记录详情
  getInspectProblemRpt: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-problem-sb/get?id=` + id })
  },

  // 新增巡查巡检问题上报记录
  createInspectProblemRpt: async (data: InspectProblemRptVO) => {
    return await request.post({ url: `/datacenter/inspect-problem-sb/create`, data })
  },

  // 修改巡查巡检问题上报记录
  updateInspectProblemRpt: async (data: InspectProblemRptVO) => {
    return await request.put({ url: `/datacenter/inspect-problem-sb/update`, data })
  },

  // 删除巡查巡检问题上报记录
  deleteInspectProblemRpt: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-problem-sb/delete?id=` + id })
  },

  // 导出巡查巡检问题上报记录 Excel
  exportInspectProblemRpt: async (params) => {
    return await request.download({ url: `/datacenter/inspect-problem-sb/export-excel`, params })
  }
}
