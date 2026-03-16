package cn.iocoder.yudao.module.evaluate.dal.mysql.pushrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.pushrecord.vo.PushRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.pushrecord.PushRecordDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 结果推送记录 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PushRecordMapper extends BaseMapperX<PushRecordDO> {

    default PageResult<PushRecordDO> selectPage(PushRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PushRecordDO>()
                .eqIfPresent(PushRecordDO::getPushId, reqVO.getPushId())
                .eqIfPresent(PushRecordDO::getCode, reqVO.getCode())
                .eqIfPresent(PushRecordDO::getArchiveId, reqVO.getArchiveId())
                .eqIfPresent(PushRecordDO::getTargetId, reqVO.getTargetId())
                .eqIfPresent(PushRecordDO::getTypeId, reqVO.getTypeId())
                .eqIfPresent(PushRecordDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PushRecordDO::getContent, reqVO.getContent())
                .eqIfPresent(PushRecordDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(PushRecordDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(PushRecordDO::getPushCount, reqVO.getPushCount())
                .betweenIfPresent(PushRecordDO::getLatestPushTime, reqVO.getLatestPushTime())
                .eqIfPresent(PushRecordDO::getFailReason, reqVO.getFailReason())
                .eqIfPresent(PushRecordDO::getFeedbackStatus, reqVO.getFeedbackStatus())
                .eqIfPresent(PushRecordDO::getDataSyncNum, reqVO.getDataSyncNum())
                .eqIfPresent(PushRecordDO::getTargetAddr, reqVO.getTargetAddr())
                .eqIfPresent(PushRecordDO::getWaitReason, reqVO.getWaitReason())
                .eqIfPresent(PushRecordDO::getWaitHour, reqVO.getWaitHour())
                .eqIfPresent(PushRecordDO::getDataCheckResult, reqVO.getDataCheckResult())
                .eqIfPresent(PushRecordDO::getContentFormat, reqVO.getContentFormat())
                .eqIfPresent(PushRecordDO::getTargetStatus, reqVO.getTargetStatus())
                .eqIfPresent(PushRecordDO::getReceiverResp, reqVO.getReceiverResp())
                .eqIfPresent(PushRecordDO::getRepushCount, reqVO.getRepushCount())
                .betweenIfPresent(PushRecordDO::getLatestRepushTime, reqVO.getLatestRepushTime())
                .eqIfPresent(PushRecordDO::getFeedbackContent, reqVO.getFeedbackContent())
                .eqIfPresent(PushRecordDO::getLogUrl, reqVO.getLogUrl())
                .eqIfPresent(PushRecordDO::getDataConsistResult, reqVO.getDataConsistResult())
                .eqIfPresent(PushRecordDO::getFailTypeId, reqVO.getFailTypeId())
                .eqIfPresent(PushRecordDO::getErrorCode, reqVO.getErrorCode())
                .eqIfPresent(PushRecordDO::getFixSuggest, reqVO.getFixSuggest())
                .eqIfPresent(PushRecordDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PushRecordDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PushRecordDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(PushRecordDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(PushRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PushRecordDO::getId));
    }

}