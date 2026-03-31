package cn.iocoder.yudao.module.evaluate.service.ruledetail;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.ruledetail.vo.RuleDetailPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.ruledetail.vo.RuleDetailSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.ruledetail.RuleDetailDO;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.util.List;

/**
 * 评分规则明细 Service 接口
 *
 * @author 亘川智城
 */
public interface RuleDetailService {

    /**
     * 创建评分规则明细
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRuleDetail(@Valid RuleDetailSaveReqVO createReqVO);

    /**
     * 更新评分规则明细
     *
     * @param updateReqVO 更新信息
     */
    void updateRuleDetail(@Valid RuleDetailSaveReqVO updateReqVO);

    /**
     * 删除评分规则明细
     *
     * @param id 编号
     */
    void deleteRuleDetail(Long id);

    /**
    * 批量删除评分规则明细
    *
    * @param ids 编号
    */
    void deleteRuleDetailListByIds(List<Long> ids);

    /**
     * 获得评分规则明细
     *
     * @param id 编号
     * @return 评分规则明细
     */
    RuleDetailDO getRuleDetail(Long id);

    /**
     * 获得评分规则明细分页
     *
     * @param pageReqVO 分页查询
     * @return 评分规则明细分页
     */
    PageResult<RuleDetailDO> getRuleDetailPage(RuleDetailPageReqVO pageReqVO);

    /**
     * 根据规则ID获取明细列表
     *
     * @param ruleId 规则ID
     * @return 明细列表
     */
    List<RuleDetailDO> getRuleDetailListByRuleId(Long ruleId);

    /**
     * 根据 count 值计算分数
     * 根据 sortOrder 排序，匹配第一个满足条件的区间
     *
     * @param ruleId 规则ID
     * @param count 统计数量
     * @return 匹配的分数，如果没有匹配返回 null
     */
    BigDecimal calculateScoreByCount(Long ruleId, Long count);

}