package cn.iocoder.yudao.module.smartcity.dal.mysql.eventprocessing;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventprocessing.EventProcessingDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventprocessing.vo.*;

/**
 * 事件处理 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface EventProcessingMapper extends BaseMapperX<EventProcessingDO> {

    default PageResult<EventProcessingDO> selectPage(EventProcessingPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EventProcessingDO>()
                .eqIfPresent(EventProcessingDO::getEventHandlingNumber, reqVO.getEventHandlingNumber())
                .eqIfPresent(EventProcessingDO::getRelatedEventReportingId, reqVO.getRelatedEventReportingId())
                .eqIfPresent(EventProcessingDO::getProcessingDepartment, reqVO.getProcessingDepartment())
                .betweenIfPresent(EventProcessingDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EventProcessingDO::getId));
    }

}