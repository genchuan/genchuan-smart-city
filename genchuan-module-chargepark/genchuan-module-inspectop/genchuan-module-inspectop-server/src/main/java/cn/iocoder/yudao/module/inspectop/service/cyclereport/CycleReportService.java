package cn.iocoder.yudao.module.inspectop.service.cyclereport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.cyclereport.CycleReportDO;
import jakarta.validation.Valid;

/**
 * 巡检运维报表 Service 接口
 */
public interface CycleReportService {

    /**
     * 获得巡检运维报表分页
     */
    PageResult<CycleReportRespVO> getCycleReportPage(CycleReportPageReqVO pageReqVO);

    /**
     * 实时生成巡检运维报表（不存储）
     * 修改：移除了@Valid注解，因为不存储时不需要验证某些字段
     * 返回：直接返回报表数据，不返回生成状态
     */
    CycleReportRespVO generateCycleReport(CycleReportGenerateReqVO generateReqVO);
}