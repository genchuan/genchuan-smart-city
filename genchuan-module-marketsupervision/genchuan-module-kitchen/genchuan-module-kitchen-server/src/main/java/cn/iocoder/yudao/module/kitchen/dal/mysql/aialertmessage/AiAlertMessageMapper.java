package cn.iocoder.yudao.module.kitchen.dal.mysql.aialertmessage;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.AiAlertMessagePageReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.aialertmessage.AiAlertMessageDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AI告警消息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AiAlertMessageMapper extends BaseMapperX<AiAlertMessageDO> {

    default PageResult<AiAlertMessageDO> selectPage(AiAlertMessagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AiAlertMessageDO>()
                .eqIfPresent(AiAlertMessageDO::getUserIds, reqVO.getUserIds())
                .eqIfPresent(AiAlertMessageDO::getSceneId, reqVO.getSceneId())
                .eqIfPresent(AiAlertMessageDO::getAiAbilityCode, reqVO.getAiAbilityCode())
                .eqIfPresent(AiAlertMessageDO::getAlertType, reqVO.getAlertType())
                .betweenIfPresent(AiAlertMessageDO::getAlertCreateTime, reqVO.getAlertCreateTime())
                .eqIfPresent(AiAlertMessageDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(AiAlertMessageDO::getFeatureId, reqVO.getFeatureId())
                .eqIfPresent(AiAlertMessageDO::getAlertSource, reqVO.getAlertSource())
                .eqIfPresent(AiAlertMessageDO::getSrcUrl, reqVO.getSrcUrl())
                .eqIfPresent(AiAlertMessageDO::getSrcToken, reqVO.getSrcToken())
                .eqIfPresent(AiAlertMessageDO::getDeviceAccount, reqVO.getDeviceAccount())
                .eqIfPresent(AiAlertMessageDO::getMsgVersion, reqVO.getMsgVersion())
                .eqIfPresent(AiAlertMessageDO::getAlertId, reqVO.getAlertId())
                .eqIfPresent(AiAlertMessageDO::getAiPlatformMsgId, reqVO.getAiPlatformMsgId())
                .eqIfPresent(AiAlertMessageDO::getBbox, reqVO.getBbox())
                .eqIfPresent(AiAlertMessageDO::getRepeatAlarm, reqVO.getRepeatAlarm())
                .betweenIfPresent(AiAlertMessageDO::getLeaveTime, reqVO.getLeaveTime())
                .eqIfPresent(AiAlertMessageDO::getTimeSlotEnd, reqVO.getTimeSlotEnd())
                .betweenIfPresent(AiAlertMessageDO::getIntervalTime, reqVO.getIntervalTime())
                .eqIfPresent(AiAlertMessageDO::getAlertParams, reqVO.getAlertParams())
                .betweenIfPresent(AiAlertMessageDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(AiAlertMessageDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AiAlertMessageDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(AiAlertMessageDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(AiAlertMessageDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(AiAlertMessageDO::getId));
    }

}
