package cn.iocoder.yudao.module.studentmgmt.dal.mysql.leavehandle;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.leavehandle.LeaveHandleDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.leavehandle.vo.*;

/**
 * 离校办理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface LeaveHandleMapper extends BaseMapperX<LeaveHandleDO> {

    default PageResult<LeaveHandleDO> selectPage(LeaveHandlePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LeaveHandleDO>()
                .eqIfPresent(LeaveHandleDO::getStudentId, reqVO.getStudentId())
                .betweenIfPresent(LeaveHandleDO::getLeaveTime, reqVO.getLeaveTime())
                .eqIfPresent(LeaveHandleDO::getLeaveAddress, reqVO.getLeaveAddress())
                .betweenIfPresent(LeaveHandleDO::getParentConfirmTime, reqVO.getParentConfirmTime())
                .eqIfPresent(LeaveHandleDO::getHandleUser, reqVO.getHandleUser())
                .betweenIfPresent(LeaveHandleDO::getHandleTime, reqVO.getHandleTime())
                .betweenIfPresent(LeaveHandleDO::getCheckoutTime, reqVO.getCheckoutTime())
                .eqIfPresent(LeaveHandleDO::getCheckoutStatus, reqVO.getCheckoutStatus())
                .eqIfPresent(LeaveHandleDO::getFinishRate, reqVO.getFinishRate())
                .eqIfPresent(LeaveHandleDO::getStatus, reqVO.getStatus())
                .eqIfPresent(LeaveHandleDO::getRemark, reqVO.getRemark())
                .eqIfPresent(LeaveHandleDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(LeaveHandleDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(LeaveHandleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(LeaveHandleDO::getId));
    }

}