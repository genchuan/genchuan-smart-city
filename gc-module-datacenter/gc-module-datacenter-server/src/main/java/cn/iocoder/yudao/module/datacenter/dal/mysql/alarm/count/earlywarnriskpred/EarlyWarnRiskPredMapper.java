package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.count.earlywarnriskpred;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnriskpred.vo.EarlyWarnRiskPredPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarnriskpred.EarlyWarnRiskPredDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警告警风险趋势预测 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnRiskPredMapper extends BaseMapperX<EarlyWarnRiskPredDO> {

    default PageResult<EarlyWarnRiskPredDO> selectPage(EarlyWarnRiskPredPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnRiskPredDO>()
                .eqIfPresent(EarlyWarnRiskPredDO::getTrendForecastId, reqVO.getTrendForecastId())
                .eqIfPresent(EarlyWarnRiskPredDO::getForecastObject, reqVO.getForecastObject())
                .eqIfPresent(EarlyWarnRiskPredDO::getForecastObjectValue, reqVO.getForecastObjectValue())
                .eqIfPresent(EarlyWarnRiskPredDO::getHistoryDataCycle, reqVO.getHistoryDataCycle())
                .eqIfPresent(EarlyWarnRiskPredDO::getHistoryDataRange, reqVO.getHistoryDataRange())
                .eqIfPresent(EarlyWarnRiskPredDO::getForecastCycle, reqVO.getForecastCycle())
                .eqIfPresent(EarlyWarnRiskPredDO::getForecastTimeRange, reqVO.getForecastTimeRange())
                .eqIfPresent(EarlyWarnRiskPredDO::getForecastIndicators, reqVO.getForecastIndicators())
                .eqIfPresent(EarlyWarnRiskPredDO::getForecastModel, reqVO.getForecastModel())
                .eqIfPresent(EarlyWarnRiskPredDO::getForecastAccuracy, reqVO.getForecastAccuracy())
                .eqIfPresent(EarlyWarnRiskPredDO::getForecastUserId, reqVO.getForecastUserId())
                .likeIfPresent(EarlyWarnRiskPredDO::getForecastUserName, reqVO.getForecastUserName())
                .betweenIfPresent(EarlyWarnRiskPredDO::getForecastTime, reqVO.getForecastTime())
                .eqIfPresent(EarlyWarnRiskPredDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnRiskPredDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnRiskPredDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnRiskPredDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnRiskPredDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnRiskPredDO::getId));
    }

}