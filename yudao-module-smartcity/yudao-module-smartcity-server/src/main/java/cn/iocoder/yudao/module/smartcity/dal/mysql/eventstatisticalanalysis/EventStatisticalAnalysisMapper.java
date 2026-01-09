package cn.iocoder.yudao.module.smartcity.dal.mysql.eventstatisticalanalysis;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventstatisticalanalysis.EventStatisticalAnalysisDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventstatisticalanalysis.vo.*;

/**
 * 事件统计分析 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface EventStatisticalAnalysisMapper extends BaseMapperX<EventStatisticalAnalysisDO> {

    default PageResult<EventStatisticalAnalysisDO> selectPage(EventStatisticalAnalysisPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EventStatisticalAnalysisDO>()
                .eqIfPresent(EventStatisticalAnalysisDO::getStatisticalCycle, reqVO.getStatisticalCycle())
                .eqIfPresent(EventStatisticalAnalysisDO::getEventType, reqVO.getEventType())
                .eqIfPresent(EventStatisticalAnalysisDO::getPlace, reqVO.getPlace())
                .betweenIfPresent(EventStatisticalAnalysisDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EventStatisticalAnalysisDO::getId));
    }

}