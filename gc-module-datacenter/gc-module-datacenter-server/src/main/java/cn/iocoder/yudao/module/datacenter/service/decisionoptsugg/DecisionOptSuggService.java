package cn.iocoder.yudao.module.datacenter.service.decisionoptsugg;


import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.decisionoptsugg.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.decisionoptsugg.DecisionOptSuggDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;


/**
 * 决策优化建议 Service 接口
 *
 * @author 亘川智城
 */
public interface DecisionOptSuggService {

    /**
     * 创建决策优化建议
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDecisionOptSugg(@Valid DecisionOptSuggSaveReqVO createReqVO);

    /**
     * 更新决策优化建议
     *
     * @param updateReqVO 更新信息
     */
    void updateDecisionOptSugg(@Valid DecisionOptSuggSaveReqVO updateReqVO);

    /**
     * 删除决策优化建议
     *
     * @param id 编号
     */
    void deleteDecisionOptSugg(Long id);

    /**
     * 获得决策优化建议
     *
     * @param id 编号
     * @return 决策优化建议
     */
    DecisionOptSuggDO getDecisionOptSugg(Long id);

    /**
     * 获得决策优化建议分页
     *
     * @param pageReqVO 分页查询
     * @return 决策优化建议分页
     */
    PageResult<DecisionOptSuggDO> getDecisionOptSuggPage(DecisionOptSuggPageReqVO pageReqVO);

}