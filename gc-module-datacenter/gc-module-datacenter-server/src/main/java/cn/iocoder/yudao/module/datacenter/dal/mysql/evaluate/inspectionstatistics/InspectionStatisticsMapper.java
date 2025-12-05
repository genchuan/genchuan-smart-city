package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.inspectionstatistics;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspectionstatistics.vo.InspectionStatisticsPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.inspectionstatistics.InspectionStatisticsDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 环卫考核统计结果 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface InspectionStatisticsMapper extends BaseMapperX<InspectionStatisticsDO> {

    default PageResult<InspectionStatisticsDO> selectPage(InspectionStatisticsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectionStatisticsDO>()
                .betweenIfPresent(InspectionStatisticsDO::getInspectionDate, reqVO.getInspectionDate())
                .eqIfPresent(InspectionStatisticsDO::getAreaType, reqVO.getAreaType())
                .likeIfPresent(InspectionStatisticsDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(InspectionStatisticsDO::getTotalScore, reqVO.getTotalScore())
                .eqIfPresent(InspectionStatisticsDO::getMaxScore, reqVO.getMaxScore())
                .eqIfPresent(InspectionStatisticsDO::getWeight, reqVO.getWeight())
                .eqIfPresent(InspectionStatisticsDO::getScoreWeighted, reqVO.getScoreWeighted())
                .eqIfPresent(InspectionStatisticsDO::getSampleCount, reqVO.getSampleCount())
                .eqIfPresent(InspectionStatisticsDO::getInspectionStatus, reqVO.getInspectionStatus())
                .betweenIfPresent(InspectionStatisticsDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(InspectionStatisticsDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(InspectionStatisticsDO::getId));
    }

}