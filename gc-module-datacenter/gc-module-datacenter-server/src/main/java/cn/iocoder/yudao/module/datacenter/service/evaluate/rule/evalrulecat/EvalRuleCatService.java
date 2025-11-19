package cn.iocoder.yudao.module.datacenter.service.evaluate.rule.evalrulecat;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalrulecat.vo.EvalRuleCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalrulecat.vo.EvalRuleCatSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rule.evalrulecat.EvalRuleCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 规则分类管理 Service 接口
 *
 * @author zcq
 */
public interface EvalRuleCatService {

    /**
     * 创建规则分类管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalRuleCat(@Valid EvalRuleCatSaveReqVO createReqVO);

    /**
     * 更新规则分类管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalRuleCat(@Valid EvalRuleCatSaveReqVO updateReqVO);

    /**
     * 删除规则分类管理
     *
     * @param id 编号
     */
    void deleteEvalRuleCat(Long id);

    /**
     * 获得规则分类管理
     *
     * @param id 编号
     * @return 规则分类管理
     */
    EvalRuleCatDO getEvalRuleCat(Long id);

    /**
     * 获得规则分类管理分页
     *
     * @param pageReqVO 分页查询
     * @return 规则分类管理分页
     */
    PageResult<EvalRuleCatDO> getEvalRuleCatPage(EvalRuleCatPageReqVO pageReqVO);

}