package cn.iocoder.yudao.module.studentmgmt.dal.mysql.behaviormgmt;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.behaviormgmt.BehaviorMgmtDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.behaviormgmt.vo.*;

/**
 * 行为管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface BehaviorMgmtMapper extends BaseMapperX<BehaviorMgmtDO> {

    default PageResult<BehaviorMgmtDO> selectPage(BehaviorMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BehaviorMgmtDO>()
                .eqIfPresent(BehaviorMgmtDO::getStudentId, reqVO.getStudentId())
                .eqIfPresent(BehaviorMgmtDO::getLeaveType, reqVO.getLeaveType())
                .betweenIfPresent(BehaviorMgmtDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(BehaviorMgmtDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(BehaviorMgmtDO::getLeaveReason, reqVO.getLeaveReason())
                .eqIfPresent(BehaviorMgmtDO::getAuditLevel, reqVO.getAuditLevel())
                .eqIfPresent(BehaviorMgmtDO::getAuditUser, reqVO.getAuditUser())
                .betweenIfPresent(BehaviorMgmtDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(BehaviorMgmtDO::getAttendanceSync, reqVO.getAttendanceSync())
                .eqIfPresent(BehaviorMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(BehaviorMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(BehaviorMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(BehaviorMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(BehaviorMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BehaviorMgmtDO::getId));
    }

}