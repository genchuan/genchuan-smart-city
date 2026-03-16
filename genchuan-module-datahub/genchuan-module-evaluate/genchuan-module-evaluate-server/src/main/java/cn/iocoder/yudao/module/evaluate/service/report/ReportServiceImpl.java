package cn.iocoder.yudao.module.evaluate.service.report;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.report.vo.ReportPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.report.vo.ReportSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.report.ReportDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.report.ReportMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.REPORT_NOT_EXISTS;

/**
 * 评价报告 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ReportServiceImpl implements ReportService {

    @Resource
    private ReportMapper reportMapper;

    @Override
    public Long createReport(ReportSaveReqVO createReqVO) {
        // 插入
        ReportDO report = BeanUtils.toBean(createReqVO, ReportDO.class);
        reportMapper.insert(report);
        // 返回
        return report.getId();
    }

    @Override
    public void updateReport(ReportSaveReqVO updateReqVO) {
        // 校验存在
        validateReportExists(updateReqVO.getId());
        // 更新
        ReportDO updateObj = BeanUtils.toBean(updateReqVO, ReportDO.class);
        reportMapper.updateById(updateObj);
    }

    @Override
    public void deleteReport(Long id) {
        // 校验存在
        validateReportExists(id);
        // 删除
        reportMapper.deleteById(id);
    }

    private void validateReportExists(Long id) {
        if (reportMapper.selectById(id) == null) {
            throw exception(REPORT_NOT_EXISTS);
        }
    }

    @Override
    public ReportDO getReport(Long id) {
        return reportMapper.selectById(id);
    }

    @Override
    public PageResult<ReportDO> getReportPage(ReportPageReqVO pageReqVO) {
        return reportMapper.selectPage(pageReqVO);
    }

}