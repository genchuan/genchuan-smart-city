package cn.iocoder.yudao.module.datacenter.service.grid.div.mnggriddiv;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.mnggriddiv.vo.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.unitgriddiv.vo.UnitGridBoundaryInfo;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.div.unitgriddiv.UnitGridDivDO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.div.unitgriddiv.UnitGridDivMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.div.mnggriddiv.MngGridDivDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.div.mnggriddiv.MngGridDivMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理网格划分 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class MngGridDivServiceImpl implements MngGridDivService {

    @Resource
    private MngGridDivMapper mngGridDivMapper;

    @Resource
    private UnitGridDivMapper unitGridDivMapper; // 新增注入

    @Override
    public Long createMngGridDiv(MngGridDivSaveReqVO createReqVO) {
        // 插入
        MngGridDivDO mngGridDiv = BeanUtils.toBean(createReqVO, MngGridDivDO.class);
        mngGridDivMapper.insert(mngGridDiv);
        // 返回
        return mngGridDiv.getId();
    }

    @Override
    public void updateMngGridDiv(MngGridDivSaveReqVO updateReqVO) {
        // 校验存在
        validateMngGridDivExists(updateReqVO.getId());
        // 更新
        MngGridDivDO updateObj = BeanUtils.toBean(updateReqVO, MngGridDivDO.class);
        mngGridDivMapper.updateById(updateObj);
    }

    @Override
    public void deleteMngGridDiv(Long id) {
        // 校验存在
        validateMngGridDivExists(id);
        // 删除
        mngGridDivMapper.deleteById(id);
    }

    private void validateMngGridDivExists(Long id) {
        if (mngGridDivMapper.selectById(id) == null) {
            throw exception(MNG_GRID_DIV_NOT_EXISTS);
        }
    }

    @Override
    public MngGridDivDO getMngGridDiv(Long id) {
        return mngGridDivMapper.selectById(id);
    }

    @Override
    public PageResult<MngGridDivRespVO> getMngGridDivPage(MngGridDivPageReqVO pageReqVO) {
        // 查询管理网格数据
        PageResult<MngGridDivDO> pageResult = mngGridDivMapper.selectPage(pageReqVO);

        if (pageResult.getList().isEmpty()) {
            return new PageResult<>(Collections.emptyList(), pageResult.getTotal());
        }

        // 处理边界坐标信息
        List<MngGridDivRespVO> voList = pageResult.getList().stream()
                .map(mngGrid -> {
                    MngGridDivRespVO respVO = BeanUtils.toBean(mngGrid, MngGridDivRespVO.class);

                    // 设置边界坐标信息
                    if (StringUtils.isNotBlank(mngGrid.getIncludedUnitIds())) {
                        List<UnitGridBoundaryInfo> boundaryInfos = getUnitGridBoundaries(mngGrid.getIncludedUnitIds());
                        respVO.setBoundaryCoords(boundaryInfos);
                    } else {
                        respVO.setBoundaryCoords(Collections.emptyList());
                    }

                    return respVO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(voList, pageResult.getTotal());
    }

    /**
     * 根据单元网格ID字符串获取边界坐标信息
     */
    private List<UnitGridBoundaryInfo> getUnitGridBoundaries(String includedUnitIds) {
        if (StringUtils.isBlank(includedUnitIds)) {
            return Collections.emptyList();
        }

        String[] unitGridIds = includedUnitIds.split(",");
        List<String> unitGridIdList = Arrays.asList(unitGridIds);

        // 查询单元网格边界信息
        List<UnitGridDivDO> unitGrids = unitGridDivMapper.selectList(
                new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<UnitGridDivDO>()
                        .in(UnitGridDivDO::getUnitGridId, unitGridIdList)
                        .select(UnitGridDivDO::getUnitGridId, UnitGridDivDO::getBoundaryCoords)
        );

        // 创建单元网格ID到边界坐标的映射
        Map<String, String> unitGridBoundaryMap = unitGrids.stream()
                .collect(Collectors.toMap(
                        UnitGridDivDO::getUnitGridId,
                        UnitGridDivDO::getBoundaryCoords,
                        (v1, v2) -> v1
                ));

        // 构建边界信息列表，保持原始顺序
        return Arrays.stream(unitGridIds)
                .map(unitGridId -> {
                    UnitGridBoundaryInfo info = new UnitGridBoundaryInfo();
                    info.setUnitGridId(unitGridId);
                    info.setBoundaryCoords(unitGridBoundaryMap.get(unitGridId));
                    return info;
                })
                .filter(info -> info.getBoundaryCoords() != null)
                .collect(Collectors.toList());
    }

    // ========== 新增接口方法实现 ==========

    @Override
    public List<MngGridDivRespVO> getMngGridDivListByTown(String townStreetId) {
        // 查询指定乡镇下的所有管理网格
        List<MngGridDivDO> mngGridList = mngGridDivMapper.selectList(
                new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<MngGridDivDO>()
                        .eqIfPresent(MngGridDivDO::getTownStreetId, townStreetId)
                        .orderByAsc(MngGridDivDO::getMngGridName)
        );

        return mngGridList.stream()
                .map(mngGrid -> {
                    MngGridDivRespVO respVO = BeanUtils.toBean(mngGrid, MngGridDivRespVO.class);
                    // 计算单元网格数量
                    if (mngGrid.getIncludedUnitIds() != null) {
                        int unitCount = mngGrid.getIncludedUnitIds().split(",").length;
                        // 这里可以设置额外的显示字段（如果需要）
                    }
                    return respVO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public MngGridValidateRespVO validateUnitGrids(List<String> unitGridIds, String townStreetId) {
        MngGridValidateRespVO respVO = new MngGridValidateRespVO();

        try {
            // 1. 校验单元网格数量（1-5个）
            if (unitGridIds.size() < 1 || unitGridIds.size() > 5) {
                respVO.setValid(false);
                respVO.setMessage("单元网格数量必须在1-5个之间");
                return respVO;
            }

            // 2. 计算面积（模拟实现）
            Integer calculatedArea = calculateArea(unitGridIds);
            respVO.setCalculatedArea(calculatedArea);
            respVO.setUnitGridCount(unitGridIds.size());

            // 3. 校验是否跨社区（模拟实现）
            boolean crossCommunity = validateCrossCommunity(unitGridIds, townStreetId);
            respVO.setCrossCommunity(crossCommunity);

            // 4. 校验是否相邻（模拟实现）
            boolean adjacent = validateAdjacent(unitGridIds);
            respVO.setAdjacent(adjacent);

            // 5. 推荐网格员数量（1人负责3-5个单元网格）
            int recommendedStaff = (int) Math.ceil(unitGridIds.size() / 3.0);
            respVO.setRecommendedStaffCount(recommendedStaff);

            // 6. 检查冲突的管理网格
            List<Long> conflictGrids = findConflictMngGrids(unitGridIds);
            respVO.setConflictMngGridIds(conflictGrids);

            // 综合校验结果
            boolean isValid = !crossCommunity && adjacent && conflictGrids.isEmpty();
            respVO.setValid(isValid);
            respVO.setMessage(isValid ? "校验通过" : "存在校验问题，请检查单元网格选择");

        } catch (Exception e) {
            respVO.setValid(false);
            respVO.setMessage("校验过程中发生错误");
        }

        return respVO;
    }

    @Override
    public Integer calculateArea(List<String> unitGridIds) {
        if (unitGridIds == null || unitGridIds.isEmpty()) {
            return 0;
        }

        // 查询单元网格数据获取实际面积
        List<UnitGridDivDO> unitGrids = unitGridDivMapper.selectList(
                new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<UnitGridDivDO>()
                        .in(UnitGridDivDO::getUnitGridId, unitGridIds)
        );

        // 计算总面积
        Integer totalArea = unitGrids.stream()
                .map(UnitGridDivDO::getArea)
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .sum();

        return totalArea;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long importUnitGrids(MngGridImportReqVO importReqVO) {
        // 1. 校验单元网格
        MngGridValidateRespVO validateResult = validateUnitGrids(
                importReqVO.getUnitGridIds(), importReqVO.getTownStreetId());

        if (!validateResult.getValid()) {
            throw exception(MNG_GRID_DIV_VALIDATION_FAILED);
        }

        // 2. 生成管理网格ID（UUID）
        String mngGridId = "MNG_" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);

        // 3. 创建管理网格
        MngGridDivSaveReqVO createReqVO = new MngGridDivSaveReqVO();
        createReqVO.setMngGridId(mngGridId);
        createReqVO.setMngGridName(importReqVO.getMngGridName());
        createReqVO.setTownStreetId(importReqVO.getTownStreetId());
        createReqVO.setIncludedUnitIds(String.join(",", importReqVO.getUnitGridIds()));
        createReqVO.setArea(validateResult.getCalculatedArea());
        createReqVO.setDivTime(java.time.LocalDateTime.now());
        createReqVO.setRemark(importReqVO.getRemark());

        // 存储网格员ID到扩展字段
        if (importReqVO.getStaffId() != null) {
            createReqVO.setExtCommon1(importReqVO.getStaffId());
        }

        // 4. 保存数据
        MngGridDivDO mngGridDiv = BeanUtils.toBean(createReqVO, MngGridDivDO.class);
        mngGridDivMapper.insert(mngGridDiv);

        return mngGridDiv.getId();
    }

    @Override
    public List<MngGridDivRespVO> getMngGridDivByUnitCount(String townStreetId, Integer minUnits, Integer maxUnits) {
        // 查询指定乡镇下的所有管理网格
        List<MngGridDivDO> allMngGrids = mngGridDivMapper.selectList(
                new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<MngGridDivDO>()
                        .eqIfPresent(MngGridDivDO::getTownStreetId, townStreetId)
        );

        // 根据单元网格数量过滤
        return allMngGrids.stream()
                .filter(mngGrid -> {
                    if (mngGrid.getIncludedUnitIds() == null) return false;

                    int unitCount = mngGrid.getIncludedUnitIds().split(",").length;
                    boolean minValid = minUnits == null || unitCount >= minUnits;
                    boolean maxValid = maxUnits == null || unitCount <= maxUnits;

                    return minValid && maxValid;
                })
                .map(mngGrid -> BeanUtils.toBean(mngGrid, MngGridDivRespVO.class))
                .collect(Collectors.toList());
    }


    /**
     * 获取单元网格详情（用于详情展示）
     */
    public List<UnitGridSimpleInfo> getUnitGridDetails(List<String> unitGridIds) {
        if (unitGridIds == null || unitGridIds.isEmpty()) {
            return Collections.emptyList();
        }

        // 查询单元网格数据
        List<UnitGridDivDO> unitGrids = unitGridDivMapper.selectList(
                new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<UnitGridDivDO>()
                        .in(UnitGridDivDO::getUnitGridId, unitGridIds)
        );

        return unitGrids.stream()
                .map(unitGrid -> {
                    UnitGridSimpleInfo info = new UnitGridSimpleInfo();
                    info.setUnitGridId(unitGrid.getUnitGridId());
                    info.setUnitGridName(unitGrid.getUnitGridName());
                    info.setArea(unitGrid.getArea());
                    info.setBoundaryCoords(unitGrid.getBoundaryCoords());
                    return info;
                })
                .collect(Collectors.toList());
    }

    // ========== 私有方法 ==========

    /**
     * 校验是否跨社区（模拟实现）
     */
    private boolean validateCrossCommunity(List<String> unitGridIds, String townStreetId) {
        if (unitGridIds.isEmpty()) {
            return false;
        }

        // 查询所有单元网格
        List<UnitGridDivDO> unitGrids = unitGridDivMapper.selectList(
                new cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX<UnitGridDivDO>()
                        .in(UnitGridDivDO::getUnitGridId, unitGridIds)
        );

        if (unitGrids.isEmpty()) {
            throw new RuntimeException("未找到对应的单元网格数据");
        }

        // 检查所有单元网格是否属于同一个乡镇
        // 实际项目中需要根据 commId 查询对应的乡镇ID，这里简化处理
        String firstCommId = unitGrids.get(0).getCommId();

        return unitGrids.stream()
                .anyMatch(unit -> !unit.getCommId().equals(firstCommId));
    }

    /**
     * 校验是否相邻（模拟实现）
     */
    private boolean validateAdjacent(List<String> unitGridIds) {
        // 实际项目中需要GIS空间分析来判断单元网格是否相邻
        // 这里简化处理：假设都相邻
        return true;
    }

    /**
     * 查找冲突的管理网格
     */
    private List<Long> findConflictMngGrids(List<String> unitGridIds) {
        // 查询所有管理网格，检查是否有包含相同单元网格的
        List<MngGridDivDO> allMngGrids = mngGridDivMapper.selectList();
        List<Long> conflictIds = new ArrayList<>();

        for (MngGridDivDO mngGrid : allMngGrids) {
            if (mngGrid.getIncludedUnitIds() != null) {
                String[] existingUnits = mngGrid.getIncludedUnitIds().split(",");
                for (String unitId : unitGridIds) {
                    if (Arrays.asList(existingUnits).contains(unitId)) {
                        conflictIds.add(mngGrid.getId());
                        break;
                    }
                }
            }
        }

        return conflictIds;
    }

}