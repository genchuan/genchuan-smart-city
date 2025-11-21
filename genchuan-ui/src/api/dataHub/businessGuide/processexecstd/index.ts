import request from '@/config/axios'

// 流程执行规范 VO
export interface ProcessExecStdVO {
  id: number // 主键ID
  processExecStdId: string // 规范ID
  stdName: string // 规范名称
  relatedProcessId: string // 关联流程ID
  relatedProcessName: string // 关联流程名称
  processSteps: string // 流程步骤
  deptCode: string // 责任部门代码
  deptName: string // 责任部门名称
  timeLimit: string // 时限要求
  execRequirement: string // 执行要求
  stdFileId: string // 规范文件ID
  stdFilePath: string // 规范文件路径
  stdStatus: string // 规范状态
  createUser: string // 创建人
  updateUser: string // 更新人
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
  createTimeSys: Date // 系统创建时间
  updateTimeSys: Date // 系统更新时间
}

// 流程执行规范 API
export const ProcessExecStdApi = {
  // 查询流程执行规范分页
  getProcessExecStdPage: async (params: any) => {
    return await request.get({ url: `/datacenter/process-exec-std/page`, params })
  },

  // 查询流程执行规范详情
  getProcessExecStd: async (id: number) => {
    return await request.get({ url: `/datacenter/process-exec-std/get?id=` + id })
  },

  // 新增流程执行规范
  createProcessExecStd: async (data: ProcessExecStdVO) => {
    return await request.post({ url: `/datacenter/process-exec-std/create`, data })
  },

  // 修改流程执行规范
  updateProcessExecStd: async (data: ProcessExecStdVO) => {
    return await request.put({ url: `/datacenter/process-exec-std/update`, data })
  },

  // 删除流程执行规范
  deleteProcessExecStd: async (id: number) => {
    return await request.delete({ url: `/datacenter/process-exec-std/delete?id=` + id })
  },

  // 导出流程执行规范 Excel
  exportProcessExecStd: async (params) => {
    return await request.download({ url: `/datacenter/process-exec-std/export-excel`, params })
  }
}