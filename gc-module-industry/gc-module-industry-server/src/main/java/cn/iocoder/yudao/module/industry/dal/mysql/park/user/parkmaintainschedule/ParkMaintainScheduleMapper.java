package cn.iocoder.yudao.module.industry.dal.mysql.park.user.parkmaintainschedule;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainschedule.vo.ParkMaintainSchedulePageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmaintainschedule.ParkMaintainScheduleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 运维排班 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkMaintainScheduleMapper extends BaseMapperX<ParkMaintainScheduleDO> {

    default PageResult<ParkMaintainScheduleDO> selectPage(ParkMaintainSchedulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkMaintainScheduleDO>()
                .eqIfPresent(ParkMaintainScheduleDO::getMaintainUserId, reqVO.getMaintainUserId())
                .betweenIfPresent(ParkMaintainScheduleDO::getScheduleDate, reqVO.getScheduleDate())
                .eqIfPresent(ParkMaintainScheduleDO::getShiftType, reqVO.getShiftType())
                .betweenIfPresent(ParkMaintainScheduleDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(ParkMaintainScheduleDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(ParkMaintainScheduleDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ParkMaintainScheduleDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkMaintainScheduleDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkMaintainScheduleDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkMaintainScheduleDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkMaintainScheduleDO::getExtCommon4, reqVO.getExtCommon4())
                .eqIfPresent(ParkMaintainScheduleDO::getRemark, reqVO.getRemark())
                .orderByDesc(ParkMaintainScheduleDO::getId));
    }

}
