package cn.iocoder.yudao.module.industry.dal.mysql.park.marketing.parkpointsrule;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrule.vo.ParkPointsRulePageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parkpointsrule.ParkPointsRuleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 积分规则 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkPointsRuleMapper extends BaseMapperX<ParkPointsRuleDO> {

    default PageResult<ParkPointsRuleDO> selectPage(ParkPointsRulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkPointsRuleDO>()
                .likeIfPresent(ParkPointsRuleDO::getRuleName, reqVO.getRuleName())
                .eqIfPresent(ParkPointsRuleDO::getTriggerType, reqVO.getTriggerType())
                .eqIfPresent(ParkPointsRuleDO::getPointsAmount, reqVO.getPointsAmount())
                .eqIfPresent(ParkPointsRuleDO::getPointsRatio, reqVO.getPointsRatio())
                .eqIfPresent(ParkPointsRuleDO::getUpperLimit, reqVO.getUpperLimit())
                .eqIfPresent(ParkPointsRuleDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ParkPointsRuleDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkPointsRuleDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkPointsRuleDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkPointsRuleDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkPointsRuleDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkPointsRuleDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkPointsRuleDO::getId));
    }

}
