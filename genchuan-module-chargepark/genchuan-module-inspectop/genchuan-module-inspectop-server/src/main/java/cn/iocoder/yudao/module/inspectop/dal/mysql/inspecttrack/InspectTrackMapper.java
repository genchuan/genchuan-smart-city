package cn.iocoder.yudao.module.inspectop.dal.mysql.inspecttrack;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspecttrack.InspectTrackDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspecttrack.vo.*;

/**
 * 巡检轨迹 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface InspectTrackMapper extends BaseMapperX<InspectTrackDO> {

    default PageResult<InspectTrackDO> selectPage(InspectTrackPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectTrackDO>()
                .eqIfPresent(InspectTrackDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(InspectTrackDO::getTrackTime, reqVO.getTrackTime())
                .eqIfPresent(InspectTrackDO::getMileage, reqVO.getMileage())
                .eqIfPresent(InspectTrackDO::getDuration, reqVO.getDuration())
                .eqIfPresent(InspectTrackDO::getArea, reqVO.getArea())
                .eqIfPresent(InspectTrackDO::getStatus, reqVO.getStatus())
                .eqIfPresent(InspectTrackDO::getPoints, reqVO.getPoints())
                .eqIfPresent(InspectTrackDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(InspectTrackDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(InspectTrackDO::getCreator, reqVO.getCreator())
                .eqIfPresent(InspectTrackDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(InspectTrackDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(InspectTrackDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(InspectTrackDO::getId));
    }

}