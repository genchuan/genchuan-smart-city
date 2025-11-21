import request from '@/config/axios'

// 报警处置类 VO

// 报警处置类 API
export const SceneCategoryApi = {
  // 查询报警处置类树形
  getSceneCategoryTree: async (params: any) => {
    return await request.get({ url: `/datacenter/scene-config/tree`, params })
  },

  // 查询报警处置类树形
  getSceneCategoryPage: async (params: any) => {
    return await request.get({ url: `/datacenter/scene-config/list`, params })
  },

  // 查询报警处置类详情
  getSceneCategory: async (id: number) => {
    return await request.get({ url: `/datacenter/scene-config/get?id=` + id })
  },

  // 新增报警处置类
  createSceneCategory: async (data) => {
    return await request.post({ url: `/datacenter/scene-config/create`, data })
  },

  // 修改报警处置类
  updateSceneCategory: async (data) => {
    return await request.put({ url: `/datacenter/scene-config/update`, data })
  },

  // 删除报警处置类
  deleteSceneCategory: async (id: number) => {
    return await request.delete({ url: `/datacenter/scene-config/delete?id=` + id })
  },

  // 导出报警处置类 Excel
  exportSceneCategory: async (params) => {
    return await request.download({ url: `/datacenter/scene-config/export-excel`, params })
  }
}
