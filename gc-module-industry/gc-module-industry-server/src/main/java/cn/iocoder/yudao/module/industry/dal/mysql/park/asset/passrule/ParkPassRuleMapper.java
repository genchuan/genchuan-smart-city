package cn.iocoder.yudao.module.industry.dal.mysql.park.asset.passrule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.passrule.vo.ParkPassRulePageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.passrule.ParkPassRuleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 通行规则 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ParkPassRuleMapper extends BaseMapperX<ParkPassRuleDO> {

    default PageResult<ParkPassRuleDO> selectPage(ParkPassRulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkPassRuleDO>()
                .eqIfPresent(ParkPassRuleDO::getPassRuleId, reqVO.getPassRuleId())
                .likeIfPresent(ParkPassRuleDO::getRuleName, reqVO.getRuleName())
                .eqIfPresent(ParkPassRuleDO::getEntryExitId, reqVO.getEntryExitId())
                .eqIfPresent(ParkPassRuleDO::getAllowCarTypes, reqVO.getAllowCarTypes())
                .eqIfPresent(ParkPassRuleDO::getForbidCarTypes, reqVO.getForbidCarTypes())
                .eqIfPresent(ParkPassRuleDO::getPeakTimeRule, reqVO.getPeakTimeRule())
                .eqIfPresent(ParkPassRuleDO::getOffPeakTimeRule, reqVO.getOffPeakTimeRule())
                .eqIfPresent(ParkPassRuleDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ParkPassRuleDO::getPassRuleCreateTime, reqVO.getPassRuleCreateTime())
                .betweenIfPresent(ParkPassRuleDO::getPassRuleUpdateTime, reqVO.getPassRuleUpdateTime())
                .eqIfPresent(ParkPassRuleDO::getPassRuleRemark, reqVO.getPassRuleRemark())
                .betweenIfPresent(ParkPassRuleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkPassRuleDO::getId));
    }

}