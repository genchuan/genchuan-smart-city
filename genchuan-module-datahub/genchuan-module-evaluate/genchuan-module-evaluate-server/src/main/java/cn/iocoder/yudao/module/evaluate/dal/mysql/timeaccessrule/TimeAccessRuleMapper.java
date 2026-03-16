package cn.iocoder.yudao.module.evaluate.dal.mysql.timeaccessrule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.timeaccessrule.vo.TimeAccessRulePageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.timeaccessrule.TimeAccessRuleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 实时接入规则 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface TimeAccessRuleMapper extends BaseMapperX<TimeAccessRuleDO> {

    default PageResult<TimeAccessRuleDO> selectPage(TimeAccessRulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TimeAccessRuleDO>()
                .eqIfPresent(TimeAccessRuleDO::getRuleId, reqVO.getRuleId())
                .likeIfPresent(TimeAccessRuleDO::getName, reqVO.getName())
                .eqIfPresent(TimeAccessRuleDO::getCode, reqVO.getCode())
                .eqIfPresent(TimeAccessRuleDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(TimeAccessRuleDO::getIndexId, reqVO.getIndexId())
                .eqIfPresent(TimeAccessRuleDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(TimeAccessRuleDO::getSyncFreqId, reqVO.getSyncFreqId())
                .eqIfPresent(TimeAccessRuleDO::getCleanRule, reqVO.getCleanRule())
                .eqIfPresent(TimeAccessRuleDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TimeAccessRuleDO::getCreateBy, reqVO.getCreateBy())
                .eqIfPresent(TimeAccessRuleDO::getUpdateBy, reqVO.getUpdateBy())
                .betweenIfPresent(TimeAccessRuleDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(TimeAccessRuleDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .betweenIfPresent(TimeAccessRuleDO::getLastSyncTime, reqVO.getLastSyncTime())
                .eqIfPresent(TimeAccessRuleDO::getSyncSuccessRate, reqVO.getSyncSuccessRate())
                .eqIfPresent(TimeAccessRuleDO::getTodaySyncCount, reqVO.getTodaySyncCount())
                .eqIfPresent(TimeAccessRuleDO::getTotalSyncCount, reqVO.getTotalSyncCount())
                .eqIfPresent(TimeAccessRuleDO::getStopReason, reqVO.getStopReason())
                .betweenIfPresent(TimeAccessRuleDO::getStopTime, reqVO.getStopTime())
                .eqIfPresent(TimeAccessRuleDO::getStopBy, reqVO.getStopBy())
                .eqIfPresent(TimeAccessRuleDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(TimeAccessRuleDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(TimeAccessRuleDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(TimeAccessRuleDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(TimeAccessRuleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TimeAccessRuleDO::getId));
    }

}