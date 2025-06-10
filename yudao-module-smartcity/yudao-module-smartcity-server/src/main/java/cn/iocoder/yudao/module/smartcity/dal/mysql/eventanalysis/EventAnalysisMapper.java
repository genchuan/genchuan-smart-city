package cn.iocoder.yudao.module.smartcity.dal.mysql.eventanalysis;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventanalysis.EventAnalysisDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventanalysis.vo.*;

/**
 * 事件关联分析 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface EventAnalysisMapper extends BaseMapperX<EventAnalysisDO> {

    default PageResult<EventAnalysisDO> selectPage(EventAnalysisPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EventAnalysisDO>()
                .likeIfPresent(EventAnalysisDO::getAnalysisNumber, reqVO.getAnalysisNumber())
                .likeIfPresent(EventAnalysisDO::getMainEventNumber, reqVO.getMainEventNumber())
                .betweenIfPresent(EventAnalysisDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EventAnalysisDO::getId));
    }

}