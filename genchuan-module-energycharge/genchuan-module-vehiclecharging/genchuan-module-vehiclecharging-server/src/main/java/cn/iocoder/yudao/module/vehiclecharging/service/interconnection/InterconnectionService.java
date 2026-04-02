package cn.iocoder.yudao.module.vehiclecharging.service.interconnection;

import java.time.*;
import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.interconnection.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.interconnection.InterconnectionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 互联互通表 Service 接口
 *
 * @author 亘川智城
 */
public interface InterconnectionService {

    /**
     * 获得互联互通表
     *
     * @param id 编号
     * @return 互联互通表
     */
    InterconnectionDO getInterconnection(Long id);

    /**
     * 获得互联互通表分页
     *
     * @param pageReqVO 分页查询
     * @return 互联互通表分页
     */
    PageResult<InterconnectionDO> getInterconnectionPage(InterconnectionPageReqVO pageReqVO);

    /**
     * 申请互联互通表
     *
     * @param applyReqVO 申请信息
     * @return 编号
     */
    Long applyInterconnection( @Valid InterconnectionApplyReqVO applyReqVO );

    /**
     * 审核互联互通表
     *
     * @param auditReqVO 审核信息
     */
    void auditInterconnection( @Valid InterconnectionAuditReqVO auditReqVO );

    /**
     * 关闭互联互通表
     *
     * @param closeReqVO 关闭信息
     */
    void closeInterconnection( @Valid InterconnectionCloseReqVO closeReqVO );

    /**
     * 重新申请互联互通表
     *
     * @param id 编号
     */
    void reapplyInterconnection( Long id );

    /**
     * 获得互联互通表图表数据
     *
     * @return 互联互通表图表数据
     */
    InterconnectionChartRespVO getInterconnectionChart();

    /**
     * 获得互联互通状态占比钻取（按合作方分布）
     *
     * @param status 状态
     * @return 合作方对接数量统计
     */
    List<InterconnectionChartRespVO.InterconnectionCooperatorCountVO> getCooperatorCountByStatus( String status );

    /**
     * 获得互联互通状态占比钻取（按合作方分布）
     *
     * @param cooperator 合作方
     * @return 合作方对接数量统计
     */
    List<InterconnectionChartRespVO.InterconnectionStatusRatioVO> getStatusCountByCooperator( String cooperator );

    /**
     * 获得互联互通申请每日数量统计
     *
     * @return 每日申请数量统计
     */
    List<InterconnectionApplyDailyCountVO> getDailyApplyCount( LocalDate startTime, LocalDate endTime);
}