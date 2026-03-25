package cn.iocoder.yudao.module.smartcity.dal.mysql.eventreporting;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventreporting.EventReportingDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventreporting.vo.*;

/**
 * 事件上报 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface EventReportingMapper extends BaseMapperX<EventReportingDO> {

    default PageResult<EventReportingDO> selectPage(EventReportingPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EventReportingDO>()
                .eqIfPresent(EventReportingDO::getEventNumber, reqVO.getEventNumber())
                .likeIfPresent(EventReportingDO::getEventName, reqVO.getEventName())
                .eqIfPresent(EventReportingDO::getEventType, reqVO.getEventType())
                .betweenIfPresent(EventReportingDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EventReportingDO::getId));
    }

}