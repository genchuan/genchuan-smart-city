import request from '@/config/axios'

// 评价主体管理 VO
export interface EvalSubjectVO {
  id: number // 主键ID
  evalSubjectId: string // 评价主体ID
  evalSubjectName: string // 评价主体名称
  subjectCode: string // 主体编码
  subjectType: string // 主体类型
  deptId: string // 所属部门ID
  deptName: string // 所属部门名称
  memberIds: string // 成员IDs
  memberNames: string // 成员姓名
  contactPerson: string // 联系人
  contactPhone: string // 联系电话
  subjectStatus: string // 主体状态
  createUserBiz: string // 创建人(业务)
  createTimeBiz: Date // 创建时间(业务)
  updateUserBiz: string // 更新人(业务)
  updateTimeBiz: Date // 更新时间(业务)
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 评价主体管理 API
export const EvalSubjectApi = {
  // 查询评价主体管理分页
  getEvalSubjectPage: async (params: any) => {
    return await request.get({ url: `/datacenter/eval-subject/page`, params })
  },

  // 查询评价主体管理详情
  getEvalSubject: async (id: number) => {
    return await request.get({ url: `/datacenter/eval-subject/get?id=` + id })
  },

  // 新增评价主体管理
  createEvalSubject: async (data: EvalSubjectVO) => {
    return await request.post({ url: `/datacenter/eval-subject/create`, data })
  },

  // 修改评价主体管理
  updateEvalSubject: async (data: EvalSubjectVO) => {
    return await request.put({ url: `/datacenter/eval-subject/update`, data })
  },

  // 删除评价主体管理
  deleteEvalSubject: async (id: number) => {
    return await request.delete({ url: `/datacenter/eval-subject/delete?id=` + id })
  },

  // 导出评价主体管理 Excel
  exportEvalSubject: async (params) => {
    return await request.download({ url: `/datacenter/eval-subject/export-excel`, params })
  }
}
