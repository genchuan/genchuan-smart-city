import request from '@/config/axios'

// 故障诊断和预警 VO
export interface DiagnosisWarningVO {
  id: number // 主键
  nameFaultyEquipment: string // 故障设备名称
  faultEquipmentNumber: string // 故障设备编号
  timeFailure: string // 故障发生时间
  descriptionSymptoms: string // 故障现象描述
  analysisPossibleMalfunction: string // 故障可能原因分析
  diagnosticMethodsBasis: string // 诊断方法与依据
  warningLevel: string // 预警级别
  warningTime: string // 预警时间
  warningInformationContent: string // 预警信息内容
  expertEvaluationOpinions: string // 专家评估意见
  systemRecoveryTime: string // 系统恢复时间
  theDegreeBusiness: string // 故障对业务的影响程度
  subsequentPreventiveMeasures: string // 后续预防措施
  backupDeploymentStatus: string // 备用设备投入情况
}

// 故障诊断和预警 API
export const DiagnosisWarningApi = {
  // 查询故障诊断和预警分页
  getDiagnosisWarningPage: async (params: any) => {
    return await request.get({ url: `/smartcity/diagnosis-warning/page`, params })
  },

  // 查询故障诊断和预警详情
  getDiagnosisWarning: async (id: number) => {
    return await request.get({ url: `/smartcity/diagnosis-warning/get?id=` + id })
  },

  // 新增故障诊断和预警
  createDiagnosisWarning: async (data: DiagnosisWarningVO) => {
    return await request.post({ url: `/smartcity/diagnosis-warning/create`, data })
  },

  // 修改故障诊断和预警
  updateDiagnosisWarning: async (data: DiagnosisWarningVO) => {
    return await request.put({ url: `/smartcity/diagnosis-warning/update`, data })
  },

  // 删除故障诊断和预警
  deleteDiagnosisWarning: async (id: number) => {
    return await request.delete({ url: `/smartcity/diagnosis-warning/delete?id=` + id })
  },

  // 导出故障诊断和预警 Excel
  exportDiagnosisWarning: async (params) => {
    return await request.download({ url: `/smartcity/diagnosis-warning/export-excel`, params })
  }
}