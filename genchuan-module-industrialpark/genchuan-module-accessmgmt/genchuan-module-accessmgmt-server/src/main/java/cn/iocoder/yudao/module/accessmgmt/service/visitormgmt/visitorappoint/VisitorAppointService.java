package cn.iocoder.yudao.module.accessmgmt.service.visitormgmt.visitorappoint;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitorappoint.vo.*;

import java.util.List;

/**
 * 访客预约 Service 接口
 *
 * @author 亘川智城
 */
public interface VisitorAppointService {

    /**
     * 获得访客预约分页
     */
    PageResult<VisitorAppointRespVO> getVisitorAppointPage(VisitorAppointPageReqVO pageReqVO);

    /**
     * 获得访客预约
     */
    VisitorAppointRespVO getVisitorAppoint(Long id);

    /**
     * 预约申请 —— 初始状态为"待审核"
     */
    Boolean createVisitorAppoint(VisitorAppointCreateReqVO createReqVO);

    /**
     * 审核通过 —— 状态变更为"已通过"，写入审核结果
     */
    Boolean auditVisitorAppoint(VisitorAppointAuditReqVO reqVO);

    /**
     * 审核驳回 —— 状态变更为"已驳回"，写入驳回原因
     */
    Boolean rejectVisitorAppoint(VisitorAppointRejectReqVO reqVO);

    /**
     * 凭证生成 —— 生成 VST-日期-序号 格式的通行凭证号
     */
    VisitorAppointGenerateRespVO generateVisitorAppoint(VisitorAppointGenerateReqVO reqVO);

    /**
     * 到访验证 —— 根据凭证号 ticket 校验访客身份
     */
    VisitorAppointVerifyRespVO verifyVisitorAppoint(VisitorAppointVerifyReqVO reqVO);

    /**
     * 取消预约 —— 状态变更为"已取消"
     */
    Boolean cancelVisitorAppoint(VisitorAppointCancelReqVO reqVO);

    /**
     * 确认离园 —— 状态变更为"已离园"，记录离开时间
     */
    Boolean leaveVisitorAppoint(VisitorAppointLeaveReqVO reqVO);

    List<VisitorAppointRespVO> getVisitorAppointList(VisitorAppointPageReqVO pageReqVO);

    /**
     * 访客预约态势 —— 每日趋势 + 企业分布统计
     */
    VisitorAppointChartRespVO getVisitorAppointChart(Long startTime, Long endTime);

}
