package cn.iocoder.yudao.module.evaluate.service.ruleitem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.ruleitem.vo.RuleItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.ruleitem.vo.RuleItemSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO;
import jakarta.validation.Valid;

/**
 * 规则项 Service 接口
 *
 * @author 亘川智城
 */
public interface RuleItemService {

    /**
     * 创建规则项
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRuleItem(@Valid RuleItemSaveReqVO createReqVO);

    /**
     * 更新规则项
     *
     * @param updateReqVO 更新信息
     */
    void updateRuleItem(@Valid RuleItemSaveReqVO updateReqVO);

    /**
     * 删除规则项
     *
     * @param id 编号
     */
    void deleteRuleItem(Long id);

    /**
     * 获得规则项
     *
     * @param id 编号
     * @return 规则项
     */
    RuleItemDO getRuleItem(Long id);

    /**
     * 获得规则项分页
     *
     * @param pageReqVO 分页查询
     * @return 规则项分页
     */
    PageResult<RuleItemDO> getRuleItemPage(RuleItemPageReqVO pageReqVO);

}