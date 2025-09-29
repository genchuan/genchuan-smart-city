package cn.iocoder.yudao.module.datacenter.dal.mysql.staffalert;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.staffalert.StaffAlertDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.staffalert.vo.*;

/**
 * 人员异常报警 Mapper
 *
 * @author zcq
 */
@Mapper
public interface StaffAlertMapper extends BaseMapperX<StaffAlertDO> {

    default PageResult<StaffAlertDO> selectPage(StaffAlertPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StaffAlertDO>()
                .eqIfPresent(StaffAlertDO::getAlertId, reqVO.getAlertId())
                .eqIfPresent(StaffAlertDO::getStaffId, reqVO.getStaffId())
                .likeIfPresent(StaffAlertDO::getStaffName, reqVO.getStaffName())
                .eqIfPresent(StaffAlertDO::getAlertType, reqVO.getAlertType())
                .eqIfPresent(StaffAlertDO::getAlertDescription, reqVO.getAlertDescription())
                .betweenIfPresent(StaffAlertDO::getAlertTime, reqVO.getAlertTime())
                .eqIfPresent(StaffAlertDO::getAlertLevel, reqVO.getAlertLevel())
                .eqIfPresent(StaffAlertDO::getProcessStatus, reqVO.getProcessStatus())
                .eqIfPresent(StaffAlertDO::getProcessorId, reqVO.getProcessorId())
                .likeIfPresent(StaffAlertDO::getProcessorName, reqVO.getProcessorName())
                .eqIfPresent(StaffAlertDO::getProcessMeasure, reqVO.getProcessMeasure())
                .betweenIfPresent(StaffAlertDO::getProcessTime, reqVO.getProcessTime())
                .eqIfPresent(StaffAlertDO::getProcessResult, reqVO.getProcessResult())
                .betweenIfPresent(StaffAlertDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(StaffAlertDO::getId));
    }

}