import request from '@/config/axios'

// 执法公示 VO
export interface EnforcementPublicityVO {
  id: number // 主键
  publicityNumber: string // 执法公示编号
  publicityTitle: string // 公示标题
  publicityContent: string // 公示内容
  publicityType: string // 公示类型
  enforcement: string // 执法部门
  enforcementLocation: string // 执法地点
  enforcementBasis: string // 执法依据
  enforcementResults: string // 执法结果
  announcementStartTime: Date // 公示开始时间
  announcementDeadline: Date // 公示截止时间
}

// 执法公示 API
export const EnforcementPublicityApi = {
  // 查询执法公示分页
  getEnforcementPublicityPage: async (params: any) => {
    return await request.get({ url: `/smartcity/enforcement-publicity/page`, params })
  },

  // 查询执法公示详情
  getEnforcementPublicity: async (id: number) => {
    return await request.get({ url: `/smartcity/enforcement-publicity/get?id=` + id })
  },

  // 新增执法公示
  createEnforcementPublicity: async (data: EnforcementPublicityVO) => {
    return await request.post({ url: `/smartcity/enforcement-publicity/create`, data })
  },

  // 修改执法公示
  updateEnforcementPublicity: async (data: EnforcementPublicityVO) => {
    return await request.put({ url: `/smartcity/enforcement-publicity/update`, data })
  },

  // 删除执法公示
  deleteEnforcementPublicity: async (id: number) => {
    return await request.delete({ url: `/smartcity/enforcement-publicity/delete?id=` + id })
  },

  // 导出执法公示 Excel
  exportEnforcementPublicity: async (params) => {
    return await request.download({ url: `/smartcity/enforcement-publicity/export-excel`, params })
  }
}