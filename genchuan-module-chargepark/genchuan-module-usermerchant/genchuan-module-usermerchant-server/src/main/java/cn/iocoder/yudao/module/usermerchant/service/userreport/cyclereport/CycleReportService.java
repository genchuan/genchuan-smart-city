package cn.iocoder.yudao.module.usermerchant.service.userreport.cyclereport;

import java.util.*;

import cn.iocoder.yudao.module.usermerchant.controller.admin.userreport.cyclereport.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.userreport.cyclereport.CycleReportDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 周期报表存储 Service 接口
 *
 * @author 亘川智城
 */
public interface CycleReportService {

    /**
     * 创建周期报表存储
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    CycleReportCreateRespVO createCycleReport(@Valid CycleReportCreateReqVO createReqVO);

    /**
     * 获得周期报表存储
     *
     * @param id 编号
     * @return 周期报表存储
     */
    CycleReportGetRespVO getCycleReport(Long id);

    /**
     * 获得周期报表存储分页
     *
     * @param pageReqVO 分页查询
     * @return 周期报表存储分页
     */
    PageResult<CycleReportDO> getCycleReportPage(CycleReportPageReqVO pageReqVO);

    /**
     * 实时获取周期报表图表数据（不存储）
     *
     * @param reqVO 请求参数
     * @return 图表数据
     */
    CycleReportChartRespVO getChartData(CycleReportChartReqVO reqVO);

    void incrementExportCountByIds(List<Long> ids);
}