package cn.iocoder.yudao.module.park.dal.mysql.park.user.maintainschedule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.user.maintainschedule.vo.MaintainSchedulePageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.maintainschedule.MaintainScheduleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 运维排班 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MaintainScheduleMapper extends BaseMapperX<MaintainScheduleDO> {

    default PageResult<MaintainScheduleDO> selectPage(MaintainSchedulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MaintainScheduleDO>()
                .eqIfPresent(MaintainScheduleDO::getMaintainUserId, reqVO.getMaintainUserId())
                .eqIfPresent(MaintainScheduleDO::getDeptId, reqVO.getDeptId())
                .betweenIfPresent(MaintainScheduleDO::getScheduleDate, reqVO.getScheduleDate())
                .eqIfPresent(MaintainScheduleDO::getShiftType, reqVO.getShiftType())
                .betweenIfPresent(MaintainScheduleDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(MaintainScheduleDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(MaintainScheduleDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MaintainScheduleDO::getAdjustReason, reqVO.getAdjustReason())
                .eqIfPresent(MaintainScheduleDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(MaintainScheduleDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MaintainScheduleDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MaintainScheduleDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MaintainScheduleDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(MaintainScheduleDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(MaintainScheduleDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(MaintainScheduleDO::getId));
    }

}
