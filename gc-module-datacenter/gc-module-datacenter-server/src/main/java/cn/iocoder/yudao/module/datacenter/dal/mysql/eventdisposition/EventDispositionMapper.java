package cn.iocoder.yudao.module.datacenter.dal.mysql.eventdisposition;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.eventdisposition.EventDispositionDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.eventdisposition.vo.*;

/**
 * 监测事件关联处置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EventDispositionMapper extends BaseMapperX<EventDispositionDO> {

    default PageResult<EventDispositionDO> selectPage(EventDispositionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EventDispositionDO>()
                .eqIfPresent(EventDispositionDO::getEventTypeId, reqVO.getEventTypeId())
                .likeIfPresent(EventDispositionDO::getEventTypeName, reqVO.getEventTypeName())
                .eqIfPresent(EventDispositionDO::getDivisionCode, reqVO.getDivisionCode())
                .likeIfPresent(EventDispositionDO::getDivisionName, reqVO.getDivisionName())
                .eqIfPresent(EventDispositionDO::getProcessModelId, reqVO.getProcessModelId())
                .likeIfPresent(EventDispositionDO::getProcessModelName, reqVO.getProcessModelName())
                .eqIfPresent(EventDispositionDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EventDispositionDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EventDispositionDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EventDispositionDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EventDispositionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EventDispositionDO::getId));
    }

    default EventDispositionDO selectByEventTypeIdAndDivisionCode(String eventTypeId, String divisionCode) {
        return selectOne(new LambdaQueryWrapperX<EventDispositionDO>()
                .eq(EventDispositionDO::getEventTypeId, eventTypeId)
                .eq(EventDispositionDO::getDivisionCode, divisionCode));
    }

    default EventDispositionDO selectByEventTypeId(String eventTypeId) {
        return selectOne(EventDispositionDO::getEventTypeId, eventTypeId);
    }

}