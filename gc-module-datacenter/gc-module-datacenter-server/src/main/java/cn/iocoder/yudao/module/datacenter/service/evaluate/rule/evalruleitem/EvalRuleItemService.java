package cn.iocoder.yudao.module.datacenter.service.evaluate.rule.evalruleitem;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalruleitem.vo.EvalRuleItemPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalruleitem.vo.EvalRuleItemSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rule.evalruleitem.EvalRuleItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 规则项管理 Service 接口
 *
 * @author zcq
 */
public interface EvalRuleItemService {

    /**
     * 创建规则项管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalRuleItem(@Valid EvalRuleItemSaveReqVO createReqVO);

    /**
     * 更新规则项管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalRuleItem(@Valid EvalRuleItemSaveReqVO updateReqVO);

    /**
     * 删除规则项管理
     *
     * @param id 编号
     */
    void deleteEvalRuleItem(Long id);

    /**
     * 获得规则项管理
     *
     * @param id 编号
     * @return 规则项管理
     */
    EvalRuleItemDO getEvalRuleItem(Long id);

    /**
     * 获得规则项管理分页
     *
     * @param pageReqVO 分页查询
     * @return 规则项管理分页
     */
    PageResult<EvalRuleItemDO> getEvalRuleItemPage(EvalRuleItemPageReqVO pageReqVO);

}