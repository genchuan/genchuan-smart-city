package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.feerule;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 收费规则 Service 接口
 *
 * @author 亘川智城
 */
public interface FeeRuleService {

    /**
     * 创建收费规则
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFeeRule(@Valid FeeRuleSaveReqVO createReqVO);

    /**
     * 更新收费规则
     *
     * @param updateReqVO 更新信息
     */
    void updateFeeRule(@Valid FeeRuleSaveReqVO updateReqVO);

    /**
     * 删除收费规则
     *
     * @param id 编号
     */
    void deleteFeeRule(Long id);

    /**
    * 批量删除收费规则
    *
    * @param ids 编号
    */
    void deleteFeeRuleListByIds(List<Long> ids);

    /**
     * 获得收费规则
     *
     * @param id 编号
     * @return 收费规则
     */
    FeeRuleDO getFeeRule(Long id);

    /**
     * 获得收费规则分页
     *
     * @param pageReqVO 分页查询
     * @return 收费规则分页
     */
    PageResult<FeeRuleDO> getFeeRulePage(FeeRulePageReqVO pageReqVO);

}
