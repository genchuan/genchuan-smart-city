package cn.iocoder.yudao.module.industry.dal.mysql.culturesportstourism.dpzl.coremetrics;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CultureCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CultureCoreMetricsRespVO.CoreMetricVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CultureCoreMetricsMapper {

    CoreMetricVO selectTotalSceneCount(CultureCoreMetricsQueryReqVO reqVO);

    CoreMetricVO selectMaxCount(CultureCoreMetricsQueryReqVO reqVO);

    CoreMetricVO selectCompleteRate(CultureCoreMetricsQueryReqVO reqVO);

    CoreMetricVO selectFacilityGoodRate(CultureCoreMetricsQueryReqVO reqVO);

    CoreMetricVO selectNewSceneCount(CultureCoreMetricsQueryReqVO reqVO);
}
