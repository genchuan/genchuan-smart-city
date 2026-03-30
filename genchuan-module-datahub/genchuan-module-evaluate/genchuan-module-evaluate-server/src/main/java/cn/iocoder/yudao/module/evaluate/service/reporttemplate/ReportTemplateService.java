package cn.iocoder.yudao.module.evaluate.service.reporttemplate;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.reporttemplate.vo.ReportTemplatePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.reporttemplate.vo.ReportTemplateSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.reporttemplate.ReportTemplateDO;
import jakarta.validation.Valid;

/**
 * 报告模板 Service 接口
 *
 * @author 亘川智城
 */
public interface ReportTemplateService {

    /**
     * 创建报告模板
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createReportTemplate(@Valid ReportTemplateSaveReqVO createReqVO);

    /**
     * 更新报告模板
     *
     * @param updateReqVO 更新信息
     */
    void updateReportTemplate(@Valid ReportTemplateSaveReqVO updateReqVO);

    /**
     * 删除报告模板
     *
     * @param id 编号
     */
    void deleteReportTemplate(Long id);

    /**
     * 获得报告模板
     *
     * @param id 编号
     * @return 报告模板
     */
    ReportTemplateDO getReportTemplate(Long id);

    /**
     * 获得报告模板分页
     *
     * @param pageReqVO 分页查询
     * @return 报告模板分页
     */
    PageResult<ReportTemplateDO> getReportTemplatePage(ReportTemplatePageReqVO pageReqVO);

}