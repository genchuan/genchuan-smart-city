package cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.comprehensiveReport.trendanalysisrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.trendanalysisrpt.vo.TrendAnalysisRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.trendanalysisrpt.TrendAnalysisRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 综合趋势分析报表 Mapper
 *
 * @author zcq
 */
@Mapper
public interface TrendAnalysisRptMapper extends BaseMapperX<TrendAnalysisRptDO> {

    default PageResult<TrendAnalysisRptDO> selectPage(TrendAnalysisRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TrendAnalysisRptDO>()
                .eqIfPresent(TrendAnalysisRptDO::getRptId, reqVO.getRptId())
                .eqIfPresent(TrendAnalysisRptDO::getTrendDimType, reqVO.getTrendDimType())
                .eqIfPresent(TrendAnalysisRptDO::getTrendDimId, reqVO.getTrendDimId())
                .likeIfPresent(TrendAnalysisRptDO::getTrendDimName, reqVO.getTrendDimName())
                .eqIfPresent(TrendAnalysisRptDO::getTimeGranularity, reqVO.getTimeGranularity())
                .betweenIfPresent(TrendAnalysisRptDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(TrendAnalysisRptDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(TrendAnalysisRptDO::getCycle1Label, reqVO.getCycle1Label())
                .eqIfPresent(TrendAnalysisRptDO::getCycle1Value, reqVO.getCycle1Value())
                .eqIfPresent(TrendAnalysisRptDO::getCycle2Label, reqVO.getCycle2Label())
                .eqIfPresent(TrendAnalysisRptDO::getCycle2Value, reqVO.getCycle2Value())
                .eqIfPresent(TrendAnalysisRptDO::getOverallChangeRate, reqVO.getOverallChangeRate())
                .eqIfPresent(TrendAnalysisRptDO::getTrendType, reqVO.getTrendType())
                .betweenIfPresent(TrendAnalysisRptDO::getRptCreateTime, reqVO.getRptCreateTime())
                .eqIfPresent(TrendAnalysisRptDO::getRptCreateUser, reqVO.getRptCreateUser())
                .eqIfPresent(TrendAnalysisRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(TrendAnalysisRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(TrendAnalysisRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(TrendAnalysisRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(TrendAnalysisRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(TrendAnalysisRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TrendAnalysisRptDO::getId));
    }

}