package cn.iocoder.yudao.module.datacenter.service.decisionscheme;


import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.decisionscheme.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.decisionscheme.DecisionSchemeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;


/**
 * 决策方案生成 Service 接口
 *
 * @author 亘川智城
 */
public interface DecisionSchemeService {

    /**
     * 创建决策方案生成
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDecisionScheme(@Valid DecisionSchemeSaveReqVO createReqVO);

    /**
     * 更新决策方案生成
     *
     * @param updateReqVO 更新信息
     */
    void updateDecisionScheme(@Valid DecisionSchemeSaveReqVO updateReqVO);

    /**
     * 删除决策方案生成
     *
     * @param id 编号
     */
    void deleteDecisionScheme(Long id);

    /**
     * 获得决策方案生成
     *
     * @param id 编号
     * @return 决策方案生成
     */
    DecisionSchemeDO getDecisionScheme(Long id);

    /**
     * 获得决策方案生成分页
     *
     * @param pageReqVO 分页查询
     * @return 决策方案生成分页
     */
    PageResult<DecisionSchemeDO> getDecisionSchemePage(DecisionSchemePageReqVO pageReqVO);

}