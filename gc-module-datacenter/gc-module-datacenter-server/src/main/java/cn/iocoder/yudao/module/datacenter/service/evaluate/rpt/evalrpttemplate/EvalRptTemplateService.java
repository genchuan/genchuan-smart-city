package cn.iocoder.yudao.module.datacenter.service.evaluate.rpt.evalrpttemplate;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpttemplate.vo.EvalRptTemplatePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpttemplate.vo.EvalRptTemplateSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rpt.evalrpttemplate.EvalRptTemplateDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 报告模板配置 Service 接口
 *
 * @author zcq
 */
public interface EvalRptTemplateService {

    /**
     * 创建报告模板配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalRptTemplate(@Valid EvalRptTemplateSaveReqVO createReqVO);

    /**
     * 更新报告模板配置
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalRptTemplate(@Valid EvalRptTemplateSaveReqVO updateReqVO);

    /**
     * 删除报告模板配置
     *
     * @param id 编号
     */
    void deleteEvalRptTemplate(Long id);

    /**
     * 获得报告模板配置
     *
     * @param id 编号
     * @return 报告模板配置
     */
    EvalRptTemplateDO getEvalRptTemplate(Long id);

    /**
     * 获得报告模板配置分页
     *
     * @param pageReqVO 分页查询
     * @return 报告模板配置分页
     */
    PageResult<EvalRptTemplateDO> getEvalRptTemplatePage(EvalRptTemplatePageReqVO pageReqVO);

}