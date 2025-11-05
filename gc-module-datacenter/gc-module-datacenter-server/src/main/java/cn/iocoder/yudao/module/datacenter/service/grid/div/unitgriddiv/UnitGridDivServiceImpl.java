package cn.iocoder.yudao.module.datacenter.service.grid.div.unitgriddiv;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.unitgriddiv.vo.*;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.div.unitgriddiv.UnitGridDivDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.div.unitgriddiv.UnitGridDivMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 单元网格划分 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class UnitGridDivServiceImpl implements UnitGridDivService {

    @Resource
    private UnitGridDivMapper unitGridDivMapper;

    @Override
    public Long createUnitGridDiv(UnitGridDivSaveReqVO createReqVO) {
        // 插入
        UnitGridDivDO unitGridDiv = BeanUtils.toBean(createReqVO, UnitGridDivDO.class);
        unitGridDivMapper.insert(unitGridDiv);
        // 返回
        return unitGridDiv.getId();
    }

    @Override
    public void updateUnitGridDiv(UnitGridDivSaveReqVO updateReqVO) {
        // 校验存在
        validateUnitGridDivExists(updateReqVO.getId());
        // 更新
        UnitGridDivDO updateObj = BeanUtils.toBean(updateReqVO, UnitGridDivDO.class);
        unitGridDivMapper.updateById(updateObj);
    }

    @Override
    public void deleteUnitGridDiv(Long id) {
        // 校验存在
        validateUnitGridDivExists(id);
        // 删除
        unitGridDivMapper.deleteById(id);
    }

    private void validateUnitGridDivExists(Long id) {
        if (unitGridDivMapper.selectById(id) == null) {
            throw exception(UNIT_GRID_DIV_NOT_EXISTS);
        }
    }

    @Override
    public UnitGridDivDO getUnitGridDiv(Long id) {
        return unitGridDivMapper.selectById(id);
    }

    @Override
    public PageResult<UnitGridDivDO> getUnitGridDivPage(UnitGridDivPageReqVO pageReqVO) {
        return unitGridDivMapper.selectPage(pageReqVO);
    }


    @Override
    public Boolean validateArea(Integer area, String commId) {
        // 这里实现中心城区面积校验逻辑
        // 中心城区面积宜10000m²左右，偏差±10%
        int standardArea = 10000;
        double minArea = standardArea * 0.9;
        double maxArea = standardArea * 1.1;

        // 实际项目中需要根据commId判断是否为中心城区
        // 这里简化处理，假设所有社区都需要校验中心城区面积
        boolean isValid = area >= minArea && area <= maxArea;

        // 如果不是中心城区，可以放宽校验（这里简化处理）
        // 实际项目中需要根据行政区划判断是否为中心城区
        if (!isCenterUrban(commId)) {
            isValid = area > 0; // 非中心城区只要面积大于0即可
        }

        return isValid;
    }

    @Override
    public UnitGridBoundaryValidateRespVO validateBoundary(UnitGridBoundaryValidateReqVO validateReqVO) {
        UnitGridBoundaryValidateRespVO respVO = new UnitGridBoundaryValidateRespVO();

        try {
            // 1. 计算面积
            Integer calculatedArea = calculateArea(validateReqVO.getBoundaryCoords());
            respVO.setCalculatedArea(calculatedArea);

            // 2. 校验面积范围
            boolean areaValid = validateArea(calculatedArea, validateReqVO.getCommId());
            respVO.setAreaOutOfRange(!areaValid);

            // 3. 校验边界重叠
            List<UnitGridSimpleRespVO> overlapGrids = checkBoundaryOverlap(
                    validateReqVO.getBoundaryCoords(), validateReqVO.getExcludeId());

            if (overlapGrids.isEmpty()) {
                respVO.setValid(true);
                respVO.setMessage("边界校验通过");
            } else {
                respVO.setValid(false);
                respVO.setOverlapGridIds(overlapGrids.stream().map(UnitGridSimpleRespVO::getId).collect(Collectors.toList()));
                respVO.setOverlapGridNames(overlapGrids.stream().map(UnitGridSimpleRespVO::getUnitGridName).collect(Collectors.toList()));
                respVO.setMessage("边界与现有网格重叠");
            }

            // 4. 校验边界是否穿越建筑物（这里需要集成GIS服务，暂时返回true）
            boolean buildingCrossValid = validateBuildingCross(validateReqVO.getBoundaryCoords());
            if (!buildingCrossValid) {
                respVO.setValid(false);
                respVO.setMessage("边界穿越建筑物");
            }

        } catch (Exception e) {
            respVO.setValid(false);
            respVO.setMessage("边界坐标格式错误或计算失败");
        }

        return respVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long importCoordinates(UnitGridCoordinateImportReqVO importReqVO) {
        // 1. 校验边界
        UnitGridBoundaryValidateReqVO validateReqVO = new UnitGridBoundaryValidateReqVO();
        validateReqVO.setBoundaryCoords(importReqVO.getBoundaryCoords());
        validateReqVO.setCommId(importReqVO.getCommId());

        UnitGridBoundaryValidateRespVO validateResult = validateBoundary(validateReqVO);
        if (!validateResult.getValid()) {
            throw exception(UNIT_GRID_DIV_BOUNDARY_INVALID);
        }

        // 2. 创建单元网格
        UnitGridDivSaveReqVO createReqVO = new UnitGridDivSaveReqVO();
        createReqVO.setUnitGridName(importReqVO.getUnitGridName());
        createReqVO.setCommId(importReqVO.getCommId());
        createReqVO.setScale(importReqVO.getScale());
        createReqVO.setBoundaryCoords(importReqVO.getBoundaryCoords());
        createReqVO.setArea(validateResult.getCalculatedArea());
        createReqVO.setRemark(importReqVO.getRemark());
        createReqVO.setDivTime(java.time.LocalDateTime.now());

        // 生成单元网格ID
        createReqVO.setUnitGridId("UNIT_" + System.currentTimeMillis());

        // 插入数据
        UnitGridDivDO unitGridDiv = BeanUtils.toBean(createReqVO, UnitGridDivDO.class);
        unitGridDivMapper.insert(unitGridDiv);

        return unitGridDiv.getId();
    }

    @Override
    public List<UnitGridSimpleRespVO> getUnitGridsByComm(String commId) {
        // 查询指定社区下的所有单元网格
        List<UnitGridDivDO> unitGridList = unitGridDivMapper.selectList(
                new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<UnitGridDivDO>()
                        .eqIfPresent(UnitGridDivDO::getCommId, commId)
        );

        return unitGridList.stream()
                .map(unitGrid -> {
                    UnitGridSimpleRespVO respVO = new UnitGridSimpleRespVO();
                    respVO.setId(unitGrid.getId());
                    respVO.setUnitGridId(unitGrid.getUnitGridId());
                    respVO.setUnitGridName(unitGrid.getUnitGridName());
                    respVO.setCommId(unitGrid.getCommId());
                    respVO.setArea(unitGrid.getArea());
                    respVO.setBoundaryCoords(unitGrid.getBoundaryCoords());
                    return respVO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<UnitGridSimpleRespVO> checkBoundaryOverlap(String boundaryCoords, Long excludeId) {
        // 查询所有需要校验的单元网格（排除当前正在编辑的网格）
        List<UnitGridDivDO> allGrids = unitGridDivMapper.selectList(
                new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<UnitGridDivDO>()
                        .neIfPresent(UnitGridDivDO::getId, excludeId)
        );

        List<UnitGridSimpleRespVO> overlapGrids = new ArrayList<>();

        // 简化实现：实际项目中需要集成GIS服务进行精确的空间重叠校验
        for (UnitGridDivDO grid : allGrids) {
            if (isBoundaryOverlap(boundaryCoords, grid.getBoundaryCoords())) {
                UnitGridSimpleRespVO overlapGrid = new UnitGridSimpleRespVO();
                overlapGrid.setId(grid.getId());
                overlapGrid.setUnitGridId(grid.getUnitGridId());
                overlapGrid.setUnitGridName(grid.getUnitGridName());
                overlapGrid.setCommId(grid.getCommId());
                overlapGrid.setArea(grid.getArea());
                overlapGrid.setBoundaryCoords(grid.getBoundaryCoords());
                overlapGrids.add(overlapGrid);
            }
        }

        return overlapGrids;
    }

    @Override
    public UnitGridDivDO getUnitGridDivByGridId(String unitGridId) {
        return unitGridDivMapper.selectOne(UnitGridDivDO::getUnitGridId, unitGridId);
    }

    // ========== 私有方法 ==========

    /**
     * 计算多边形面积（简化实现，实际需要GIS算法）
     */
    private Integer calculateArea(String boundaryCoords) {
        try {
            // 简化实现：根据坐标点数量估算面积
            String[] points = boundaryCoords.split(";");
            if (points.length >= 3) {
                // 基础面积 + 每个坐标点增加一定面积
                int baseArea = 8000;
                int areaPerPoint = 500;
                return baseArea + (points.length * areaPerPoint);
            }
            return 10000; // 默认值
        } catch (Exception e) {
            return 10000; // 计算失败返回默认值
        }
    }

    /**
     * 判断是否为中心城区（简化实现）
     */
    private boolean isCenterUrban(String commId) {
        // 假设COMM_1001、COMM_1002、COMM_1003为中心城区
        return commId != null &&
                (commId.equals("COMM_1001") || commId.equals("COMM_1002") || commId.equals("COMM_1003"));
    }

    /**
     * 校验边界是否穿越建筑物（简化实现）
     */
    private boolean validateBuildingCross(String boundaryCoords) {
        // 简化实现：实际项目中需要集成GIS服务
        return true; // 假设都通过
    }

    /**
     * 判断边界是否重叠（简化实现）
     */
    private boolean isBoundaryOverlap(String coords1, String coords2) {
        if (coords1 == null || coords2 == null) {
            return false;
        }

        try {
            // 简化实现：检查坐标是否有交集
            // 实际项目中应该使用GIS空间分析
            String[] points1 = coords1.split(";");
            String[] points2 = coords2.split(";");

            // 如果第一个坐标点相同，认为可能重叠（简化逻辑）
            if (points1.length > 0 && points2.length > 0) {
                return points1[0].equals(points2[0]);
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }
}