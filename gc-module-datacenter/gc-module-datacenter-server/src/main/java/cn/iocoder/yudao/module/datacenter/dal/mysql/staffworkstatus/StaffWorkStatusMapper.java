package cn.iocoder.yudao.module.datacenter.dal.mysql.staffworkstatus;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.staffworkstatus.StaffWorkStatusDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.staffworkstatus.vo.*;

/**
 * 人员作业状态 Mapper
 *
 * @author zcq
 */
@Mapper
public interface StaffWorkStatusMapper extends BaseMapperX<StaffWorkStatusDO> {

    default PageResult<StaffWorkStatusDO> selectPage(StaffWorkStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StaffWorkStatusDO>()
                .eqIfPresent(StaffWorkStatusDO::getMonitorId, reqVO.getMonitorId())
                .eqIfPresent(StaffWorkStatusDO::getStaffId, reqVO.getStaffId())
                .likeIfPresent(StaffWorkStatusDO::getStaffName, reqVO.getStaffName())
                .eqIfPresent(StaffWorkStatusDO::getWorkStatus, reqVO.getWorkStatus())
                .eqIfPresent(StaffWorkStatusDO::getCurrentTaskId, reqVO.getCurrentTaskId())
                .likeIfPresent(StaffWorkStatusDO::getTaskName, reqVO.getTaskName())
                .eqIfPresent(StaffWorkStatusDO::getGpsCoordinates, reqVO.getGpsCoordinates())
                .betweenIfPresent(StaffWorkStatusDO::getLocationTime, reqVO.getLocationTime())
                .eqIfPresent(StaffWorkStatusDO::getCompletedTaskCount, reqVO.getCompletedTaskCount())
                .eqIfPresent(StaffWorkStatusDO::getRemainingTaskCount, reqVO.getRemainingTaskCount())
                .eqIfPresent(StaffWorkStatusDO::getAbnormalStatus, reqVO.getAbnormalStatus())
                .betweenIfPresent(StaffWorkStatusDO::getAbnormalStartTime, reqVO.getAbnormalStartTime())
                .eqIfPresent(StaffWorkStatusDO::getAreaId, reqVO.getAreaId())
                .betweenIfPresent(StaffWorkStatusDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(StaffWorkStatusDO::getId));
    }

}