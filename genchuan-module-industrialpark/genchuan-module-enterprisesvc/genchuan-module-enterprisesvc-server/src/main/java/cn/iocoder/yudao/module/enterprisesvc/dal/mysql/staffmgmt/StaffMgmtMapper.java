package cn.iocoder.yudao.module.enterprisesvc.dal.mysql.staffmgmt;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.enterprisesvc.dal.dataobject.staffmgmt.StaffMgmtDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.enterprisesvc.controller.admin.staffmgmt.vo.*;

/**
 * 企业员工 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface StaffMgmtMapper extends BaseMapperX<StaffMgmtDO> {

    default PageResult<StaffMgmtDO> selectPage(StaffMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StaffMgmtDO>()
                .likeIfPresent(StaffMgmtDO::getStaffName, reqVO.getStaffName())
                .eqIfPresent(StaffMgmtDO::getEnterpriseId, reqVO.getEnterpriseId())
                .likeIfPresent(StaffMgmtDO::getDeptName, reqVO.getDeptName())
                .likeIfPresent(StaffMgmtDO::getPostName, reqVO.getPostName())
                .eqIfPresent(StaffMgmtDO::getAuthStatus, reqVO.getAuthStatus())
                .eqIfPresent(StaffMgmtDO::getAccessArea, reqVO.getAccessArea())
                .eqIfPresent(StaffMgmtDO::getAuthUser, reqVO.getAuthUser())
                .eqIfPresent(StaffMgmtDO::getHandleUser, reqVO.getHandleUser())
                .eqIfPresent(StaffMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(StaffMgmtDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(StaffMgmtDO::getCreator, reqVO.getCreator())
                .eqIfPresent(StaffMgmtDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(StaffMgmtDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(StaffMgmtDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(StaffMgmtDO::getId));
    }

}