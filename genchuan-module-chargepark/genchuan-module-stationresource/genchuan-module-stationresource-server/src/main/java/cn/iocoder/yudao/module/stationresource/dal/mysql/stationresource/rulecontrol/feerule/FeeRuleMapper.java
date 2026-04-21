package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.feerule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.FeeRulePageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.ops.FeeRuleChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.feerule.FeeRuleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 收费规则 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface FeeRuleMapper extends BaseMapperX<FeeRuleDO> {

    default PageResult<FeeRuleDO> selectPage(FeeRulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FeeRuleDO>()
                .eqIfPresent(FeeRuleDO::getStationId, reqVO.getStationId())
                .eqIfPresent(FeeRuleDO::getRateType, reqVO.getRateType())
                .betweenIfPresent(FeeRuleDO::getFreeTime, reqVO.getFreeTime())
                .eqIfPresent(FeeRuleDO::getChargeUnit, reqVO.getChargeUnit())
                .eqIfPresent(FeeRuleDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(FeeRuleDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(FeeRuleDO::getAuditUserId, reqVO.getAuditUserId())
                .eqIfPresent(FeeRuleDO::getMatchRate, reqVO.getMatchRate())
                .eqIfPresent(FeeRuleDO::getFirstHourPrice, reqVO.getFirstHourPrice())
                .eqIfPresent(FeeRuleDO::getStepPrice, reqVO.getStepPrice())
                .eqIfPresent(FeeRuleDO::getMaxPrice, reqVO.getMaxPrice())
                .eqIfPresent(FeeRuleDO::getPeakValleyConfig, reqVO.getPeakValleyConfig())
                .eqIfPresent(FeeRuleDO::getMemberConfig, reqVO.getMemberConfig())
                .eqIfPresent(FeeRuleDO::getRemark, reqVO.getRemark())
                .eqIfPresent(FeeRuleDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(FeeRuleDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(FeeRuleDO::getCreator, reqVO.getCreator())
                .eqIfPresent(FeeRuleDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(FeeRuleDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(FeeRuleDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(FeeRuleDO::getId));
    }

    FeeRuleChartRespVO.CardDataVO selectCardData();

    List<FeeRuleChartRespVO.StationBarVO> selectStationBarList();
}
