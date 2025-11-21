import request from '@/config/axios'

// 巡查巡检整改结果验收 VO
export interface InspectRectifyAcceptVO {
  id: number // 主键
  acceptId: string // 验收ID
  acceptCode: string // 验收编码
  rectifyTaskId: string // 关联整改任务ID
  rectifyTaskCode: string // 关联整改任务编码
  problemId: string // 关联问题ID
  rectifyResultDesc: string // 整改结果描述
  rectifyAfterPhotoUrls: string // 整改后照片URLs
  rectifyBeforePhotoUrls: string // 整改前照片URLs
  acceptResult: string // 验收结果
  acceptOpinion: string // 验收意见
  acceptUser: string // 验收人
  acceptTime: Date // 验收时间
  rectifySubmitUser: string // 整改提交人
  rectifySubmitTime: Date // 整改提交时间
  taskStatusChange: string // 任务状态变更
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 巡查巡检整改结果验收 API
export const InspectRectifyAcceptApi = {
  // 查询巡查巡检整改结果验收分页
  getInspectRectifyAcceptPage: async (params: any) => {
    return await request.get({ url: `/datacenter/inspect-rectify-accept/page`, params })
  },

  // 查询巡查巡检整改结果验收详情
  getInspectRectifyAccept: async (id: number) => {
    return await request.get({ url: `/datacenter/inspect-rectify-accept/get?id=` + id })
  },

  // 新增巡查巡检整改结果验收
  createInspectRectifyAccept: async (data: InspectRectifyAcceptVO) => {
    return await request.post({ url: `/datacenter/inspect-rectify-accept/create`, data })
  },

  // 修改巡查巡检整改结果验收
  updateInspectRectifyAccept: async (data: InspectRectifyAcceptVO) => {
    return await request.put({ url: `/datacenter/inspect-rectify-accept/update`, data })
  },

  // 删除巡查巡检整改结果验收
  deleteInspectRectifyAccept: async (id: number) => {
    return await request.delete({ url: `/datacenter/inspect-rectify-accept/delete?id=` + id })
  },

  // 导出巡查巡检整改结果验收 Excel
  exportInspectRectifyAccept: async (params) => {
    return await request.download({
      url: `/datacenter/inspect-rectify-accept/export-excel`,
      params
    })
  }
}
