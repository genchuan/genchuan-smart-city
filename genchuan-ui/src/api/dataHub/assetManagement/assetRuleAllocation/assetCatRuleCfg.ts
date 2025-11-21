import request from '@/config/axios'

// 资产分类规则配置 VO
export interface AssetCatRuleCfgVO {
  id: number // 主键ID
  assetCatRuleId: string // 分类规则ID
  ruleName: string // 规则名称
  majorCodeLength: number // 大类编码位数
  midCodeLength: number // 中类编码位数
  minorCodeLength: number // 小类编码位数
  codeGenLogic: string // 编码生成逻辑
  assetDom: string // 适用资产领域
  enableStatus: string // 启用状态
  createUser: string // 创建人
  createdTime: Date // 创建时间
  updateUser: string // 更新人
  updatedTime: Date // 更新时间
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 资产分类规则配置 API
export const AssetCatRuleCfgApi = {
  // 查询资产分类规则配置分页
  getAssetCatRuleCfgPage: async (params: any) => {
    return await request.get({ url: `/datacenter/asset-cat-rule-cfg/page`, params })
  },

  // 查询资产分类规则配置详情
  getAssetCatRuleCfg: async (id: number) => {
    return await request.get({ url: `/datacenter/asset-cat-rule-cfg/get?id=` + id })
  },

  // 新增资产分类规则配置
  createAssetCatRuleCfg: async (data: AssetCatRuleCfgVO) => {
    return await request.post({ url: `/datacenter/asset-cat-rule-cfg/create`, data })
  },

  // 修改资产分类规则配置
  updateAssetCatRuleCfg: async (data: AssetCatRuleCfgVO) => {
    return await request.put({ url: `/datacenter/asset-cat-rule-cfg/update`, data })
  },

  // 删除资产分类规则配置
  deleteAssetCatRuleCfg: async (id: number) => {
    return await request.delete({ url: `/datacenter/asset-cat-rule-cfg/delete?id=` + id })
  },

  // 导出资产分类规则配置 Excel
  exportAssetCatRuleCfg: async (params) => {
    return await request.download({ url: `/datacenter/asset-cat-rule-cfg/export-excel`, params })
  },
  
  // 获取启用的资产分类规则列表（用于字典）
  getEnabledAssetCatRuleList: async () => {
    return await request.get({ url: `/datacenter/asset-cat-rule-cfg/enabled-list` })
  },
  // 获取资产分类规则列表
  getAssetCatRuleList: async () => {
    return await request.get({ url: `/datacenter/asset-cat-rule-cfg/list` })
  },
}


/* ===================== 双向映射 & 下拉选项 ===================== */
export function buildCatRuleMaps(list: AssetCatRuleCfgVO[]) {
  const name2Id: Record<string, string> = {}   // 名称 → ID
  const id2Name: Record<string, string> = {}   // ID → 名称

  list.forEach((item) => {
    const { ruleName, assetCatRuleId } = item
    if (ruleName && assetCatRuleId) {
      name2Id[ruleName] = assetCatRuleId
      id2Name[assetCatRuleId] = ruleName
    }
  })

  // 下拉选项（el-option 直接循环）
  const options = list.map((item) => ({
    label: item.ruleName,
    value: item.assetCatRuleId,
  }))

  return { name2Id, id2Name, options }
}

/**
 * 快捷函数：先调接口，再拿到映射结果
 * 用法：
 *   const { name2Id, id2Name, options } = await getCatRuleMaps()
 */
export async function getCatRuleMaps() {
  const list = await AssetCatRuleCfgApi.getEnabledAssetCatRuleList()
  return buildCatRuleMaps(list)
}
