package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.offtimerule;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 错时规则 Service 接口
 *
 * @author 亘川智城
 */
public interface OfftimeRuleService {

    /**
     * 创建错时规则
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOfftimeRule(@Valid OfftimeRuleSaveReqVO createReqVO);

    /**
     * 更新错时规则
     *
     * @param updateReqVO 更新信息
     */
    void updateOfftimeRule(@Valid OfftimeRuleSaveReqVO updateReqVO);

    /**
     * 删除错时规则
     *
     * @param id 编号
     */
    void deleteOfftimeRule(Long id);

    /**
    * 批量删除错时规则
    *
    * @param ids 编号
    */
    void deleteOfftimeRuleListByIds(List<Long> ids);

    /**
     * 获得错时规则
     *
     * @param id 编号
     * @return 错时规则
     */
    OfftimeRuleDO getOfftimeRule(Long id);

    /**
     * 获得错时规则分页
     *
     * @param pageReqVO 分页查询
     * @return 错时规则分页
     */
    PageResult<OfftimeRuleDO> getOfftimeRulePage(OfftimeRulePageReqVO pageReqVO);

}
