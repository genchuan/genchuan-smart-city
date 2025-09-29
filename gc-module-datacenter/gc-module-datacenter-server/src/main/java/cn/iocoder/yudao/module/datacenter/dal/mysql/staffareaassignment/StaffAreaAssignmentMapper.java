package cn.iocoder.yudao.module.datacenter.dal.mysql.staffareaassignment;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.staffareaassignment.StaffAreaAssignmentDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.staffareaassignment.vo.*;

/**
 * 人员区域分配 Mapper
 *
 * @author zcq
 */
@Mapper
public interface StaffAreaAssignmentMapper extends BaseMapperX<StaffAreaAssignmentDO> {

    default PageResult<StaffAreaAssignmentDO> selectPage(StaffAreaAssignmentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StaffAreaAssignmentDO>()
                .eqIfPresent(StaffAreaAssignmentDO::getAssignmentId, reqVO.getAssignmentId())
                .eqIfPresent(StaffAreaAssignmentDO::getStaffId, reqVO.getStaffId())
                .likeIfPresent(StaffAreaAssignmentDO::getStaffName, reqVO.getStaffName())
                .eqIfPresent(StaffAreaAssignmentDO::getAreaId, reqVO.getAreaId())
                .likeIfPresent(StaffAreaAssignmentDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(StaffAreaAssignmentDO::getAssignmentType, reqVO.getAssignmentType())
                .eqIfPresent(StaffAreaAssignmentDO::getAssignmentCycle, reqVO.getAssignmentCycle())
                .betweenIfPresent(StaffAreaAssignmentDO::getEffectiveTime, reqVO.getEffectiveTime())
                .betweenIfPresent(StaffAreaAssignmentDO::getExpiryTime, reqVO.getExpiryTime())
                .eqIfPresent(StaffAreaAssignmentDO::getAssignmentStatus, reqVO.getAssignmentStatus())
                .betweenIfPresent(StaffAreaAssignmentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(StaffAreaAssignmentDO::getId));
    }

}