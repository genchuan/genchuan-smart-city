import request from '@/config/axios'

// 人员作业 VO
export interface PersonnelHomeworkVO {
  id: number // 主键
  jobNumber: string // 作业编号
  operatorId: string // 作业人员编号
  assignmentAreaNumber: string // 作业区域编号
  operationTime: Date // 作业时间
  homeworkContent: string // 作业内容
  operationMode: string // 作业方式
  notes: string // 备注
}

// 人员作业 API
export const PersonnelHomeworkApi = {
  // 查询人员作业分页
  getPersonnelHomeworkPage: async (params: any) => {
    return await request.get({ url: `/smartcity/personnel-homework/page`, params })
  },

  // 查询人员作业详情
  getPersonnelHomework: async (id: number) => {
    return await request.get({ url: `/smartcity/personnel-homework/get?id=` + id })
  },

  // 新增人员作业
  createPersonnelHomework: async (data: PersonnelHomeworkVO) => {
    return await request.post({ url: `/smartcity/personnel-homework/create`, data })
  },

  // 修改人员作业
  updatePersonnelHomework: async (data: PersonnelHomeworkVO) => {
    return await request.put({ url: `/smartcity/personnel-homework/update`, data })
  },

  // 删除人员作业
  deletePersonnelHomework: async (id: number) => {
    return await request.delete({ url: `/smartcity/personnel-homework/delete?id=` + id })
  },

  // 导出人员作业 Excel
  exportPersonnelHomework: async (params) => {
    return await request.download({ url: `/smartcity/personnel-homework/export-excel`, params })
  }
}