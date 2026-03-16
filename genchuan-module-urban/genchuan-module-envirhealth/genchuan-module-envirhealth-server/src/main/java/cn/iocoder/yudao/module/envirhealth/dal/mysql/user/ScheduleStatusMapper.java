package cn.iocoder.yudao.module.envirhealth.dal.mysql.user;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.schedulestatus.ScheduleStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.ScheduleStatusDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 排班状态字典表【通用复用】 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ScheduleStatusMapper extends BaseMapperX<ScheduleStatusDO> {

    default PageResult<ScheduleStatusDO> selectPage(ScheduleStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ScheduleStatusDO>()
                .eqIfPresent(ScheduleStatusDO::getScheduleStatusId, reqVO.getScheduleStatusId())
                .likeIfPresent(ScheduleStatusDO::getScheduleStatusName, reqVO.getScheduleStatusName())
                .eqIfPresent(ScheduleStatusDO::getDescription, reqVO.getDescription())
                .eqIfPresent(ScheduleStatusDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ScheduleStatusDO::getSort, reqVO.getSort())
                .eqIfPresent(ScheduleStatusDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ScheduleStatusDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ScheduleStatusDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ScheduleStatusDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ScheduleStatusDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ScheduleStatusDO::getId));
    }

}