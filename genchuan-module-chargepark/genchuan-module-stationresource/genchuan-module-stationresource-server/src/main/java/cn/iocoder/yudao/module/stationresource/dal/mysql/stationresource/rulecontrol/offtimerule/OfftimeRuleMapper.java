package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.offtimerule;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

/**
 * 错时规则 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface OfftimeRuleMapper extends BaseMapperX<OfftimeRuleDO> {

    default PageResult<OfftimeRuleDO> selectPage(OfftimeRulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OfftimeRuleDO>()
                .eqIfPresent(OfftimeRuleDO::getStationId, reqVO.getStationId())
                .betweenIfPresent(OfftimeRuleDO::getOffTime, reqVO.getOffTime())
                .eqIfPresent(OfftimeRuleDO::getOffFee, reqVO.getOffFee())
                .eqIfPresent(OfftimeRuleDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(OfftimeRuleDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(OfftimeRuleDO::getAuditUserId, reqVO.getAuditUserId())
                .eqIfPresent(OfftimeRuleDO::getOffOrderCount, reqVO.getOffOrderCount())
                .eqIfPresent(OfftimeRuleDO::getRemark, reqVO.getRemark())
                .eqIfPresent(OfftimeRuleDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(OfftimeRuleDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(OfftimeRuleDO::getCreator, reqVO.getCreator())
                .eqIfPresent(OfftimeRuleDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(OfftimeRuleDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(OfftimeRuleDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(OfftimeRuleDO::getId));
    }

}
