package cn.iocoder.yudao.module.datacenter.service.evalgriddiv;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.mnggriddiv.vo.MngGridSimpleRespVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mnggriddiv.MngGridDivDO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.mnggriddiv.MngGridDivMapper;
import cn.iocoder.yudao.module.datacenter.service.area.AreaService;
import cn.iocoder.yudao.module.datacenter.service.mnggriddiv.MngGridDivService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.datacenter.controller.admin.evalgriddiv.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evalgriddiv.EvalGridDivDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evalgriddiv.EvalGridDivMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 评价网格划分 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalGridDivServiceImpl implements EvalGridDivService {

    @Resource
    private EvalGridDivMapper evalGridDivMapper;

    @Resource
    private AreaService areaService; // 用于获取区域特征数据
    @Resource
    private MngGridDivMapper mngGridDivMapper;
    @Resource
    private MngGridDivService mngGridDivService;

    @Override
    public Long createEvalGridDiv(EvalGridDivSaveReqVO createReqVO) {
        // 插入
        EvalGridDivDO evalGridDiv = BeanUtils.toBean(createReqVO, EvalGridDivDO.class);
        evalGridDivMapper.insert(evalGridDiv);
        // 返回
        return evalGridDiv.getId();
    }

    @Override
    public void updateEvalGridDiv(EvalGridDivSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalGridDivExists(updateReqVO.getId());
        // 更新
        EvalGridDivDO updateObj = BeanUtils.toBean(updateReqVO, EvalGridDivDO.class);
        evalGridDivMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalGridDiv(Long id) {
        // 校验存在
        validateEvalGridDivExists(id);
        // 删除
        evalGridDivMapper.deleteById(id);
    }

    private void validateEvalGridDivExists(Long id) {
        if (evalGridDivMapper.selectById(id) == null) {
            throw exception(EVAL_GRID_DIV_NOT_EXISTS);
        }
    }

    @Override
    public EvalGridDivDO getEvalGridDiv(Long id) {
        return evalGridDivMapper.selectById(id);
    }

    @Override
    public PageResult<EvalGridDivDO> getEvalGridDivPage(EvalGridDivPageReqVO pageReqVO) {
        return evalGridDivMapper.selectPage(pageReqVO);
    }

    @Override
    public EvalGridValidateRespVO validateEvalGrid(String townStreetId, List<String> includedMgIds) {
        EvalGridValidateRespVO respVO = new EvalGridValidateRespVO();

        try {
            // 1. 校验管理网格数量
            if (includedMgIds.size() < 1) {
                respVO.setValid(false);
                respVO.setMessage("至少需要选择一个管理网格");
                return respVO;
            }

            // 2. 校验是否属于同一乡镇
            boolean sameTown = validateSameTown(includedMgIds, townStreetId);
            respVO.setSameTown(sameTown);

            if (!sameTown) {
                respVO.setValid(false);
                respVO.setMessage("所选管理网格不属于同一乡镇");
                return respVO;
            }

            // 3. 计算面积
            Integer calculatedArea = calculateEvalGridArea(includedMgIds);
            respVO.setCalculatedArea(calculatedArea);
            respVO.setMngGridCount(includedMgIds.size());

            // 4. 校验是否相邻（模拟实现）
            boolean adjacent = validateAdjacent(includedMgIds);
            respVO.setAdjacent(adjacent);

            // 5. 检查冲突的评价网格
            List<Long> conflictGrids = findConflictEvalGrids(includedMgIds);
            respVO.setConflictEvalGridIds(conflictGrids);

            // 综合校验结果
            boolean isValid = sameTown && adjacent && conflictGrids.isEmpty();
            respVO.setValid(isValid);
            respVO.setMessage(isValid ? "校验通过" : "存在校验问题，请检查管理网格选择");

        } catch (Exception e) {
            respVO.setValid(false);
            respVO.setMessage("校验过程中发生错误");
        }

        return respVO;
    }

    @Override
    public List<MngGridSimpleRespVO> getMngGridListByTown(String townStreetId) {
        return mngGridDivService.getMngGridDivListByTown(townStreetId).stream()
                .map(mngGrid -> {
                    MngGridSimpleRespVO simpleVO = new MngGridSimpleRespVO();
                    simpleVO.setId(mngGrid.getId());
                    simpleVO.setMngGridId(mngGrid.getMngGridId());
                    simpleVO.setMngGridName(mngGrid.getMngGridName());
                    simpleVO.setTownStreetId(mngGrid.getTownStreetId());
                    simpleVO.setArea(mngGrid.getArea());

                    // 计算单元网格数量
                    if (mngGrid.getIncludedUnitIds() != null) {
                        int unitCount = mngGrid.getIncludedUnitIds().split(",").length;
                        simpleVO.setUnitGridCount(unitCount);
                    }

                    return simpleVO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Integer calculateEvalGridArea(List<String> includedMgIds) {
        if (includedMgIds == null || includedMgIds.isEmpty()) {
            return 0;
        }

        // 查询管理网格数据获取实际面积
        List<MngGridDivDO> mngGrids = mngGridDivMapper.selectList(
                new LambdaQueryWrapperX<MngGridDivDO>()
                        .in(MngGridDivDO::getMngGridId, includedMgIds)
        );

        // 计算总面积
        return mngGrids.stream()
                .map(MngGridDivDO::getArea)
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public Map<String, Long> getEvalGridDistribution() {
        List<EvalGridDivDO> allEvalGrids = evalGridDivMapper.selectList();

        return allEvalGrids.stream()
                .collect(Collectors.groupingBy(EvalGridDivDO::getGridType, Collectors.counting()));
    }

    @Override
    public EvalGridDivDetailRespVO getEvalGridDivDetail(Long id) {
        EvalGridDivDO evalGridDiv = evalGridDivMapper.selectById(id);
        if (evalGridDiv == null) {
            return null;
        }

        EvalGridDivDetailRespVO detailRespVO = new EvalGridDivDetailRespVO();
        // 手动复制字段
        detailRespVO.setId(evalGridDiv.getId());
        detailRespVO.setEvalGridId(evalGridDiv.getEvalGridId());
        detailRespVO.setEvalGridName(evalGridDiv.getEvalGridName());
        detailRespVO.setTownStreetId(evalGridDiv.getTownStreetId());
        detailRespVO.setGridType(evalGridDiv.getGridType());
        detailRespVO.setIncludedMgIds(evalGridDiv.getIncludedMgIds());
        detailRespVO.setArea(evalGridDiv.getArea());
        detailRespVO.setDivTime(evalGridDiv.getDivTime());
        detailRespVO.setUpdateTime(evalGridDiv.getUpdateTime());
        detailRespVO.setRemark(evalGridDiv.getRemark());
        detailRespVO.setExtCat1(evalGridDiv.getExtCat1());
        detailRespVO.setExtCat2(evalGridDiv.getExtCat2());
        detailRespVO.setExtCommon1(evalGridDiv.getExtCommon1());
        detailRespVO.setExtCommon2(evalGridDiv.getExtCommon2());
        detailRespVO.setCreateTime(evalGridDiv.getCreateTime());
        detailRespVO.setUpdateTimeSys(evalGridDiv.getUpdateTimeSys());

        // 解析所含管理网格IDs，获取管理网格详情
        if (evalGridDiv.getIncludedMgIds() != null) {
            List<String> mgIds = Arrays.asList(evalGridDiv.getIncludedMgIds().split(","));
            detailRespVO.setIncludedMgIdList(mgIds);

            // 获取管理网格详情列表
            List<MngGridSimpleRespVO> mgList = getMngGridListByTown(evalGridDiv.getTownStreetId()).stream()
                    .filter(mg -> mgIds.contains(mg.getMngGridId()))
                    .collect(Collectors.toList());
            detailRespVO.setMngGridList(mgList);
        }

        return detailRespVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEvalGridTypeBatch(List<Long> ids, String gridType) {
        if (ids == null || ids.isEmpty()) {
            return;
        }

        for (Long id : ids) {
            EvalGridDivDO evalGridDiv = evalGridDivMapper.selectById(id);
            if (evalGridDiv != null) {
                evalGridDiv.setGridType(gridType);
                evalGridDivMapper.updateById(evalGridDiv);
            }
        }
    }

    @Override
    public String recommendGridType(String townStreetId, List<String> includedMgIds) {
        // 模拟实现：根据区域特征推荐类型
        // 实际项目中需要查询区域特征数据（商业区/景区等）

        // 1. 获取管理网格详情
        List<MngGridDivDO> mngGrids = mngGridDivMapper.selectList(
                new LambdaQueryWrapperX<MngGridDivDO>()
                        .in(MngGridDivDO::getMngGridId, includedMgIds)
        );

        // 2. 模拟逻辑：如果包含商业区特征，推荐A类；普通城区B类；其他C类
        boolean hasCommercial = mngGrids.stream()
                .anyMatch(mg -> mg.getExtCat1() != null && mg.getExtCat1().contains("商业区"));

        boolean hasScenic = mngGrids.stream()
                .anyMatch(mg -> mg.getExtCat1() != null && mg.getExtCat1().contains("景区"));

        if (hasCommercial || hasScenic) {
            return "A"; // 核心区
        } else if (mngGrids.size() >= 3) {
            return "B"; // 普通城区
        } else {
            return "C"; // 城乡结合部
        }
    }

// ========== 私有方法 ==========

    /**
     * 校验是否属于同一乡镇
     */
    private boolean validateSameTown(List<String> includedMgIds, String expectedTownId) {
        if (includedMgIds.isEmpty()) {
            return true;
        }

        List<MngGridDivDO> mngGrids = mngGridDivMapper.selectList(
                new LambdaQueryWrapperX<MngGridDivDO>()
                        .in(MngGridDivDO::getMngGridId, includedMgIds)
        );

        return mngGrids.stream()
                .allMatch(mg -> expectedTownId.equals(mg.getTownStreetId()));
    }

    /**
     * 校验是否相邻（模拟实现）
     */
    private boolean validateAdjacent(List<String> includedMgIds) {
        // 实际项目中需要GIS空间分析来判断管理网格是否相邻
        // 这里简化处理：假设都相邻
        return true;
    }

    /**
     * 查找冲突的评价网格
     */
    private List<Long> findConflictEvalGrids(List<String> includedMgIds) {
        List<EvalGridDivDO> allEvalGrids = evalGridDivMapper.selectList();
        List<Long> conflictIds = new ArrayList<>();

        for (EvalGridDivDO evalGrid : allEvalGrids) {
            if (evalGrid.getIncludedMgIds() != null) {
                String[] existingMgs = evalGrid.getIncludedMgIds().split(",");
                for (String mgId : includedMgIds) {
                    if (Arrays.asList(existingMgs).contains(mgId)) {
                        conflictIds.add(evalGrid.getId());
                        break;
                    }
                }
            }
        }

        return conflictIds;
    }

}