package cn.iocoder.yudao.module.accessmgmt.service.visitormgmt.visitorappoint;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitorappoint.vo.*;

import java.util.List;

public interface VisitorAppointService {

    PageResult<VisitorAppointRespVO> getVisitorAppointPage(VisitorAppointPageReqVO pageReqVO);

    VisitorAppointRespVO getVisitorAppoint(Long id);

    Boolean createVisitorAppoint(VisitorAppointCreateReqVO createReqVO);

    Boolean auditVisitorAppoint(VisitorAppointAuditReqVO reqVO);

    Boolean rejectVisitorAppoint(VisitorAppointRejectReqVO reqVO);

    VisitorAppointGenerateRespVO generateVisitorAppoint(VisitorAppointGenerateReqVO reqVO);

    VisitorAppointVerifyRespVO verifyVisitorAppoint(VisitorAppointVerifyReqVO reqVO);

    Boolean cancelVisitorAppoint(VisitorAppointCancelReqVO reqVO);

    Boolean leaveVisitorAppoint(VisitorAppointLeaveReqVO reqVO);

    List<VisitorAppointRespVO> getVisitorAppointList(VisitorAppointPageReqVO pageReqVO);

    VisitorAppointChartRespVO getVisitorAppointChart(String startTime, String endTime);

}
