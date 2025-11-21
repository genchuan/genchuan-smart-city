package cn.iocoder.yudao.module.datacenter.service.evaluate.task.evaltasktemplate;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltasktemplate.vo.EvalTaskTemplatePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltasktemplate.vo.EvalTaskTemplateSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.task.evaltasktemplate.EvalTaskTemplateDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 任务模板管理 Service 接口
 *
 * @author zcq
 */
public interface EvalTaskTemplateService {

    /**
     * 创建任务模板管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalTaskTemplate(@Valid EvalTaskTemplateSaveReqVO createReqVO);

    /**
     * 更新任务模板管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalTaskTemplate(@Valid EvalTaskTemplateSaveReqVO updateReqVO);

    /**
     * 删除任务模板管理
     *
     * @param id 编号
     */
    void deleteEvalTaskTemplate(Long id);

    /**
     * 获得任务模板管理
     *
     * @param id 编号
     * @return 任务模板管理
     */
    EvalTaskTemplateDO getEvalTaskTemplate(Long id);

    /**
     * 获得任务模板管理分页
     *
     * @param pageReqVO 分页查询
     * @return 任务模板管理分页
     */
    PageResult<EvalTaskTemplateDO> getEvalTaskTemplatePage(EvalTaskTemplatePageReqVO pageReqVO);

}