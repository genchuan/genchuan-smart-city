package cn.iocoder.yudao.module.waterdetection.dal.mysql.issuetracking;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.issuetracking.IssueTrackingDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.issuetracking.vo.*;

/**
 * 问题上报与闭环跟踪 Mapper
 *
 * @author zcq
 */
@Mapper
public interface IssueTrackingMapper extends BaseMapperX<IssueTrackingDO> {

    default PageResult<IssueTrackingDO> selectPage(IssueTrackingPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<IssueTrackingDO>()
                .eqIfPresent(IssueTrackingDO::getIssueId, reqVO.getIssueId())
                .eqIfPresent(IssueTrackingDO::getIssueType, reqVO.getIssueType())
                .betweenIfPresent(IssueTrackingDO::getReportTime, reqVO.getReportTime())
                .betweenIfPresent(IssueTrackingDO::getDispatchTime, reqVO.getDispatchTime())
                .eqIfPresent(IssueTrackingDO::getRepairStaffId, reqVO.getRepairStaffId())
                .betweenIfPresent(IssueTrackingDO::getRepairTime, reqVO.getRepairTime())
                .eqIfPresent(IssueTrackingDO::getInspectionResult, reqVO.getInspectionResult())
                .eqIfPresent(IssueTrackingDO::getClosureStatus, reqVO.getClosureStatus())
                .betweenIfPresent(IssueTrackingDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(IssueTrackingDO::getId));
    }

}