import request from '@/config/axios'

// 附件地址存储 VO
export interface AttachmentVO {
  id: number // 序号
  tbId: string // TB_ID
  attachmentUrl: string // 附件地址
  status: string // 状态
}

// 附件地址存储 API
export const AttachmentApi = {
  // 查询附件地址存储分页
  getAttachmentPage: async (params: any) => {
    return await request.get({ url: `/system/attachment/page`, params })
  },

  // 查询附件地址存储详情
  getAttachment: async (id: number) => {
    return await request.get({ url: `/system/attachment/get?id=` + id })
  },

  // 新增附件地址存储
  createAttachment: async (data: AttachmentVO) => {
    return await request.post({ url: `/system/attachment/create`, data })
  },

  // 修改附件地址存储
  updateAttachment: async (data: AttachmentVO) => {
    return await request.put({ url: `/system/attachment/update`, data })
  },

  // 删除附件地址存储
  deleteAttachment: async (id: number) => {
    return await request.delete({ url: `/system/attachment/delete?id=` + id })
  },

  // 导出附件地址存储 Excel
  exportAttachment: async (params) => {
    return await request.download({ url: `/system/attachment/export-excel`, params })
  }
}