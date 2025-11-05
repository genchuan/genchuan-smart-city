package cn.iocoder.yudao.module.datacenter.service.grid.div.evalgriddiv;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.evalgriddiv.vo.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.mnggriddiv.vo.MngGridSimpleRespVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.div.evalgriddiv.EvalGridDivDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 评价网格划分 Service 接口
 *
 * @author zcq
 */
public interface EvalGridDivService {

    /**
     * 创建评价网格划分
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalGridDiv(@Valid EvalGridDivSaveReqVO createReqVO);

    /**
     * 更新评价网格划分
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalGridDiv(@Valid EvalGridDivSaveReqVO updateReqVO);

    /**
     * 删除评价网格划分
     *
     * @param id 编号
     */
    void deleteEvalGridDiv(Long id);

    /**
     * 获得评价网格划分
     *
     * @param id 编号
     * @return 评价网格划分
     */
    EvalGridDivDO getEvalGridDiv(Long id);

    /**
     * 获得评价网格划分分页
     *
     * @param pageReqVO 分页查询
     * @return 评价网格划分分页
     */
    PageResult<EvalGridDivDO> getEvalGridDivPage(EvalGridDivPageReqVO pageReqVO);

    /**
     * 校验评价网格
     *
     * @param townStreetId 乡镇ID
     * @param includedMgIds 所含管理网格IDs
     * @return 校验结果
     */
    EvalGridValidateRespVO validateEvalGrid(String townStreetId, List<String> includedMgIds);

    /**
     * 根据乡镇ID获取管理网格列表（用于勾选）
     *
     * @param townStreetId 乡镇ID
     * @return 管理网格列表
     */
    List<MngGridSimpleRespVO> getMngGridListByTown(String townStreetId);

    /**
     * 计算评价网格面积
     *
     * @param includedMgIds 所含管理网格IDs
     * @return 总面积
     */
    Integer calculateEvalGridArea(List<String> includedMgIds);

    /**
     * 根据类型获取评价网格分布
     *
     * @return 按类型分组的评价网格数量
     */
    Map<String, Long> getEvalGridDistribution();

    /**
     * 获取评价网格详情
     *
     * @param id 评价网格ID
     * @return 评价网格详情
     */
    EvalGridDivDetailRespVO getEvalGridDivDetail(Long id);

    /**
     * 批量更新评价网格类型
     *
     * @param ids 评价网格ID列表
     * @param gridType 评价网格类型
     */
    void updateEvalGridTypeBatch(List<Long> ids, String gridType);

    /**
     * 根据区域特征推荐评价网格类型
     *
     * @param townStreetId 乡镇ID
     * @param includedMgIds 所含管理网格IDs
     * @return 推荐的类型
     */
    String recommendGridType(String townStreetId, List<String> includedMgIds);

    /**
     * 根据管理网格数量范围获取评价网格列表
     */
    List<EvalGridDivRespVO> getEvalGridDivByMngGridCount(String townStreetId, Integer minMngGrids, Integer maxMngGrids);

    /**
     * 获得评价网格划分分页（包含边界坐标信息）
     *
     * @param pageReqVO 分页查询
     * @return 评价网格划分分页（包含边界坐标）
     */
    PageResult<EvalGridDivRespVO> getEvalGridDivPageWithBoundary(EvalGridDivPageReqVO pageReqVO);
}