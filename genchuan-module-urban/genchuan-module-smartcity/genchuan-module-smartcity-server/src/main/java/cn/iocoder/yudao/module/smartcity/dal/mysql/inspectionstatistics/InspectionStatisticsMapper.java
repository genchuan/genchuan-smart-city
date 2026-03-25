package cn.iocoder.yudao.module.smartcity.dal.mysql.inspectionstatistics;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionstatistics.InspectionStatisticsDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionstatistics.vo.*;

/**
 * 巡查分析统计 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface InspectionStatisticsMapper extends BaseMapperX<InspectionStatisticsDO> {

    default PageResult<InspectionStatisticsDO> selectPage(InspectionStatisticsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectionStatisticsDO>()
                .eqIfPresent(InspectionStatisticsDO::getPatrolArea, reqVO.getPatrolArea())
                .eqIfPresent(InspectionStatisticsDO::getPatrolPersonnel, reqVO.getPatrolPersonnel())
                .eqIfPresent(InspectionStatisticsDO::getSuggestionsMeasures, reqVO.getSuggestionsMeasures())
                .betweenIfPresent(InspectionStatisticsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectionStatisticsDO::getId));
    }

}