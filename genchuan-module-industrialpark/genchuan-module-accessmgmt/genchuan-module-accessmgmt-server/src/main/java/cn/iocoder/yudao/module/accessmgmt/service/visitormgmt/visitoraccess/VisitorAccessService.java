package cn.iocoder.yudao.module.accessmgmt.service.visitormgmt.visitoraccess;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitoraccess.vo.*;

import java.util.List;

/**
 * 访客通行 Service 接口
 *
 * @author 亘川智城
 */
public interface VisitorAccessService {

    PageResult<VisitorAccessRespVO> getVisitorAccessPage(VisitorAccessPageReqVO pageReqVO);

    VisitorAccessRespVO getVisitorAccess(Long id);

    VisitorAccessCheckRespVO checkVisitorAccess(VisitorAccessCheckReqVO reqVO);

    Boolean passVisitorAccess(VisitorAccessPassReqVO reqVO);

    Boolean blockVisitorAccess(VisitorAccessBlockReqVO reqVO);

    Boolean remindVisitorAccess(VisitorAccessRemindReqVO reqVO);

    List<VisitorAccessRespVO> getVisitorAccessList(VisitorAccessPageReqVO pageReqVO);

    VisitorAccessChartRespVO getVisitorAccessChart(Long startTime, Long endTime);

}
