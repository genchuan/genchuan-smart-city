package cn.iocoder.yudao.module.datacenter.dal.mysql.inspectionstaff;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspectionstaff.InspectionStaffDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspectionstaff.vo.*;

/**
 * 巡查人员信息 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectionStaffMapper extends BaseMapperX<InspectionStaffDO> {

    default PageResult<InspectionStaffDO> selectPage(InspectionStaffPageReqVO reqVO) {//, Set<Long> deptIds
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectionStaffDO>()
                .eqIfPresent(InspectionStaffDO::getStaffId, reqVO.getStaffId())
                .likeIfPresent(InspectionStaffDO::getStaffName, reqVO.getStaffName())
                .eqIfPresent(InspectionStaffDO::getGender, reqVO.getGender())
                .eqIfPresent(InspectionStaffDO::getContactPhone, reqVO.getContactPhone())
                .inIfPresent(InspectionStaffDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(InspectionStaffDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(InspectionStaffDO::getStaffType, reqVO.getStaffType())
                .eqIfPresent(InspectionStaffDO::getQualificationPath, reqVO.getQualificationPath())
                .eqIfPresent(InspectionStaffDO::getWorkPermission, reqVO.getWorkPermission())
                .betweenIfPresent(InspectionStaffDO::getEntryTime, reqVO.getEntryTime())
                .eqIfPresent(InspectionStaffDO::getDimissionStatus, reqVO.getDimissionStatus())
                .betweenIfPresent(InspectionStaffDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectionStaffDO::getId));
    }

}