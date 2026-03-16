package cn.iocoder.yudao.module.evaluate.service.report;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.report.vo.ReportPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.report.vo.ReportSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.report.ReportDO;
import jakarta.validation.Valid;

/**
 * 评价报告 Service 接口
 *
 * @author 亘川智城
 */
public interface ReportService {

    /**
     * 创建评价报告
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createReport(@Valid ReportSaveReqVO createReqVO);

    /**
     * 更新评价报告
     *
     * @param updateReqVO 更新信息
     */
    void updateReport(@Valid ReportSaveReqVO updateReqVO);

    /**
     * 删除评价报告
     *
     * @param id 编号
     */
    void deleteReport(Long id);

    /**
     * 获得评价报告
     *
     * @param id 编号
     * @return 评价报告
     */
    ReportDO getReport(Long id);

    /**
     * 获得评价报告分页
     *
     * @param pageReqVO 分页查询
     * @return 评价报告分页
     */
    PageResult<ReportDO> getReportPage(ReportPageReqVO pageReqVO);

}