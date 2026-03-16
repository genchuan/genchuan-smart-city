package cn.iocoder.yudao.module.evaluate.service.reporttemplate;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.reporttemplate.vo.ReportTemplatePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.reporttemplate.vo.ReportTemplateSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.reporttemplate.ReportTemplateDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.reporttemplate.ReportTemplateMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.REPORT_TEMPLATE_NOT_EXISTS;

/**
 * 报告模板 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ReportTemplateServiceImpl implements ReportTemplateService {

    @Resource
    private ReportTemplateMapper reportTemplateMapper;

    @Override
    public Long createReportTemplate(ReportTemplateSaveReqVO createReqVO) {
        // 插入
        ReportTemplateDO reportTemplate = BeanUtils.toBean(createReqVO, ReportTemplateDO.class);
        reportTemplateMapper.insert(reportTemplate);
        // 返回
        return reportTemplate.getId();
    }

    @Override
    public void updateReportTemplate(ReportTemplateSaveReqVO updateReqVO) {
        // 校验存在
        validateReportTemplateExists(updateReqVO.getId());
        // 更新
        ReportTemplateDO updateObj = BeanUtils.toBean(updateReqVO, ReportTemplateDO.class);
        reportTemplateMapper.updateById(updateObj);
    }

    @Override
    public void deleteReportTemplate(Long id) {
        // 校验存在
        validateReportTemplateExists(id);
        // 删除
        reportTemplateMapper.deleteById(id);
    }

    private void validateReportTemplateExists(Long id) {
        if (reportTemplateMapper.selectById(id) == null) {
            throw exception(REPORT_TEMPLATE_NOT_EXISTS);
        }
    }

    @Override
    public ReportTemplateDO getReportTemplate(Long id) {
        return reportTemplateMapper.selectById(id);
    }

    @Override
    public PageResult<ReportTemplateDO> getReportTemplatePage(ReportTemplatePageReqVO pageReqVO) {
        return reportTemplateMapper.selectPage(pageReqVO);
    }

}