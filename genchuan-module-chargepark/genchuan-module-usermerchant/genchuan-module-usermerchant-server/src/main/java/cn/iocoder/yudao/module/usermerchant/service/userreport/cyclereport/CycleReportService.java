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
    Long createCycleReport(@Valid CycleReportSaveReqVO createReqVO);

    /**
     * 更新周期报表存储
     *
     * @param updateReqVO 更新信息
     */
    void updateCycleReport(@Valid CycleReportSaveReqVO updateReqVO);

    /**
     * 删除周期报表存储
     *
     * @param id 编号
     */
    void deleteCycleReport(Long id);

    /**
    * 批量删除周期报表存储
    *
    * @param ids 编号
    */
    void deleteCycleReportListByIds(List<Long> ids);

    /**
     * 获得周期报表存储
     *
     * @param id 编号
     * @return 周期报表存储
     */
    CycleReportDO getCycleReport(Long id);

    /**
     * 获得周期报表存储分页
     *
     * @param pageReqVO 分页查询
     * @return 周期报表存储分页
     */
    PageResult<CycleReportDO> getCycleReportPage(CycleReportPageReqVO pageReqVO);

    /**
     * 生成周期报表（实时统计并存储）
     *
     * @param generateReqVO 生成请求
     * @return 报表详情
     */
    CycleReportGenerateRespVO generateCycleReport(CycleReportGenerateReqVO generateReqVO);

}