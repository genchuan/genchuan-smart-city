import request from '@/config/axios'

// 设备信息 VO
export interface DeviceInfoVO {
  id: number // 主键ID
  deviceId: string // 设备ID
  deviceName: string // 设备名称
  deviceCode: string // 设备编码
  deviceType: string // 设备类型
  deviceModel: string // 设备型号
  deviceSpec: string // 设备规格
  manufacturer: string // 生产厂商
  productionDate: Date // 生产日期
  purchaseDate: Date // 采购日期
  installDate: Date // 安装日期
  warrantyPeriod: number // 质保期（月）
  deviceStatus: string // 设备状态
  runStatus: string // 运行状态
  lastOnlineTime: Date // 最后在线时间
  deviceMajorId: string // 设备大类ID
  deviceMajorName: string // 设备大类名称
  deviceMidId: string // 设备中类ID
  deviceMidName: string // 设备中类名称
  deviceMinorId: string // 设备小类ID
  deviceMinorName: string // 设备小类名称
  regionCode: string // 行政区划代码
  regionName: string // 行政区划名称
  gridId: string // 网格ID
  gridName: string // 网格名称
  gridCode: string // 网格编码
  monCompId: string // 监测部件ID
  monCompName: string // 监测部件名称
  monCompCode: string // 监测部件编码
  appSceneId: string // 应用场景ID
  appSceneName: string // 应用场景名称
  appSceneCode: string // 应用场景编码
  assetId: string // 资产ID
  assetName: string // 资产名称
  assetCode: string // 资产编码
  mainDeviceId: string // 主设备ID
  mainDeviceName: string // 主设备名称
  mainDeviceCode: string // 主设备编码
  installLocation: string // 安装位置描述
  coordX: number // 坐标X（经度）
  coordY: number // 坐标Y（纬度）
  elevation: number // 高程
  positionAccuracy: number // 定位精度（米）
  ipAddress: string // IP地址
  macAddress: string // MAC地址
  serialNumber: string // 序列号
  firmwareVersion: string // 固件版本
  softwareVersion: string // 软件版本
  communicationProtocol: string // 通信协议
  dataReportInterval: number // 数据上报间隔（秒）
  samplingFrequency: number // 采样频率（秒）
  voltageLevel: string // 电压等级
  powerSupply: string // 供电方式
  batteryCapacity: number // 电池容量（mAh）
  batteryLevel: number // 电池电量（%）
  signalStrength: number // 信号强度
  environmentTemp: number // 环境温度（℃）
  environmentHumidity: number // 环境湿度（%）
  maintenanceCycle: number // 维护周期（天）
  lastMaintenanceDate: Date // 最后维护日期
  nextMaintenanceDate: Date // 下次维护日期
  maintenanceOrg: string // 维护单位
  maintenancePerson: string // 维护人
  maintenancePhone: string // 维护电话
  remark: string // 备注
  extCat1: string // 分类扩展字段1
  extCat2: string // 分类扩展字段2
  extCommon1: string // 通用扩展字段1
  extCommon2: string // 通用扩展字段2
}

// 设备信息 API
export const DeviceInfoApi = {
  // 查询设备信息分页
  getDeviceInfoPage: async (params: any) => {
    return await request.get({ url: `/datacenter/device-info/page`, params })
  },

  // 查询设备信息详情
  getDeviceInfo: async (id: number) => {
    return await request.get({ url: `/datacenter/device-info/get?id=` + id })
  },

  // 新增设备信息
  createDeviceInfo: async (data: DeviceInfoVO) => {
    return await request.post({ url: `/datacenter/device-info/create`, data })
  },

  // 修改设备信息
  updateDeviceInfo: async (data: DeviceInfoVO) => {
    return await request.put({ url: `/datacenter/device-info/update`, data })
  },

  // 删除设备信息
  deleteDeviceInfo: async (id: number) => {
    return await request.delete({ url: `/datacenter/device-info/delete?id=` + id })
  },

  // 导出设备信息 Excel
  exportDeviceInfo: async (params) => {
    return await request.download({ url: `/datacenter/device-info/export-excel`, params })
  }
}
