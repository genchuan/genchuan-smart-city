package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.alarmrule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.alarmrule.vo.AlarmRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.alarmrule.AlarmRuleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警告警规则配置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface AlarmRuleMapper extends BaseMapperX<AlarmRuleDO> {

    default PageResult<AlarmRuleDO> selectPage(AlarmRulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AlarmRuleDO>()
                .likeIfPresent(AlarmRuleDO::getRuleName, reqVO.getRuleName())
                .eqIfPresent(AlarmRuleDO::getRelatedObjectType, reqVO.getRelatedObjectType())
                .eqIfPresent(AlarmRuleDO::getRelatedObjectScope, reqVO.getRelatedObjectScope())
                .eqIfPresent(AlarmRuleDO::getRelatedObjectCategoryId, reqVO.getRelatedObjectCategoryId())
                .eqIfPresent(AlarmRuleDO::getRelatedObjectIdList, reqVO.getRelatedObjectIdList())
                .eqIfPresent(AlarmRuleDO::getTriggerCondition, reqVO.getTriggerCondition())
                .eqIfPresent(AlarmRuleDO::getWarningIndicatorCode, reqVO.getWarningIndicatorCode())
                .eqIfPresent(AlarmRuleDO::getWarningThresholdUpper, reqVO.getWarningThresholdUpper())
                .eqIfPresent(AlarmRuleDO::getWarningThresholdLower, reqVO.getWarningThresholdLower())
                .eqIfPresent(AlarmRuleDO::getStatusDetermineValue, reqVO.getStatusDetermineValue())
                .eqIfPresent(AlarmRuleDO::getTriggerDuration, reqVO.getTriggerDuration())
                .eqIfPresent(AlarmRuleDO::getEarlyWarningLevel, reqVO.getEarlyWarningLevel())
                .eqIfPresent(AlarmRuleDO::getTriggerFrequency, reqVO.getTriggerFrequency())
                .eqIfPresent(AlarmRuleDO::getRepeatTriggerInterval, reqVO.getRepeatTriggerInterval())
                .eqIfPresent(AlarmRuleDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(AlarmRuleDO::getRuleDescription, reqVO.getRuleDescription())
                .eqIfPresent(AlarmRuleDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(AlarmRuleDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(AlarmRuleDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(AlarmRuleDO::getExtendCategory1, reqVO.getExtendCategory1())
                .eqIfPresent(AlarmRuleDO::getExtendCategory2, reqVO.getExtendCategory2())
                .eqIfPresent(AlarmRuleDO::getExtendCategory3, reqVO.getExtendCategory3())
                .eqIfPresent(AlarmRuleDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(AlarmRuleDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(AlarmRuleDO::getId));
    }

}