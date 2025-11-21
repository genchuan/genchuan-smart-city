/**
 * mockGridSpatialData.ts
 * - 用于接口失败或返回空时的兜底数据
 * - 生产环境可以删除或替换
 */

import type { GridSpatialDataVO } from '@/api/dataHub/gridManagement/gridDataManage/gridspatialdata'

export const mockGridSpatialDataList: GridSpatialDataVO[] = [
  {
    id: 1001,
    spatialId: 'UUID-1001',
    gridId: 'UNIT-0001',
    gridType: '单元网格',
    boundaryCoords: '116.0000,39.0000;116.0005,39.0000;116.0005,39.0005;116.0000,39.0005;116.0000,39.0000',
    topoRelation: 'UNIT-0002,UNIT-0003',
    coordSystem: '2000国家大地',
    dataFormat: 'GeoJSON',
    importTime: '2025-01-01 10:00:00',
    updateTime: '2025-01-10 12:00:00',
    remark: 'mock: 2025 基础数据导入',
    extCat1: '矢量数据',
    extCat2: '网格划分',
    extCommon1: '2MB',
    extCommon2: '',
  },
  {
    id: 1002,
    spatialId: 'UUID-1002',
    gridId: 'MNG-0001',
    gridType: '管理网格',
    boundaryCoords: '116.001,39.001;116.0015,39.001;116.0015,39.0015;116.001,39.0015;116.001,39.001',
    topoRelation: '',
    coordSystem: '2000国家大地',
    dataFormat: 'SHP',
    importTime: '2024-12-12 09:00:00',
    updateTime: '2024-12-13 15:00:00',
    remark: 'mock sample',
    extCat1: '栅格数据',
    extCat2: '',
    extCommon1: '3MB',
    extCommon2: '',
  },
]

export const mockGridTypes = ['单元网格', '管理网格', '评价网格', '扩展网格']
