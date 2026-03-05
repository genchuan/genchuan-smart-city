package cn.iocoder.yudao.module.evaluate.dal.mysql.appealrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealrecord.vo.AppealRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.appealrecord.AppealRecordDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 申诉复核 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AppealRecordMapper extends BaseMapperX<AppealRecordDO> {

    default PageResult<AppealRecordDO> selectPage(AppealRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AppealRecordDO>()
                .eqIfPresent(AppealRecordDO::getAppealId, reqVO.getAppealId())
                .eqIfPresent(AppealRecordDO::getCode, reqVO.getCode())
                .eqIfPresent(AppealRecordDO::getObjectId, reqVO.getObjectId())
                .eqIfPresent(AppealRecordDO::getPublicId, reqVO.getPublicId())
                .eqIfPresent(AppealRecordDO::getAuditId, reqVO.getAuditId())
                .eqIfPresent(AppealRecordDO::getAppealBy, reqVO.getAppealBy())
                .betweenIfPresent(AppealRecordDO::getSubmitTime, reqVO.getSubmitTime())
                .eqIfPresent(AppealRecordDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AppealRecordDO::getOriginalScore, reqVO.getOriginalScore())
                .eqIfPresent(AppealRecordDO::getReviewBy, reqVO.getReviewBy())
                .betweenIfPresent(AppealRecordDO::getReviewTime, reqVO.getReviewTime())
                .eqIfPresent(AppealRecordDO::getFinalResult, reqVO.getFinalResult())
                .eqIfPresent(AppealRecordDO::getCorrectScore, reqVO.getCorrectScore())
                .eqIfPresent(AppealRecordDO::getCorrectStandardId, reqVO.getCorrectStandardId())
                .eqIfPresent(AppealRecordDO::getCloseStatus, reqVO.getCloseStatus())
                .eqIfPresent(AppealRecordDO::getCloseBy, reqVO.getCloseBy())
                .betweenIfPresent(AppealRecordDO::getCloseTime, reqVO.getCloseTime())
                .eqIfPresent(AppealRecordDO::getAppealReason, reqVO.getAppealReason())
                .eqIfPresent(AppealRecordDO::getFileCount, reqVO.getFileCount())
                .eqIfPresent(AppealRecordDO::getWaitHour, reqVO.getWaitHour())
                .eqIfPresent(AppealRecordDO::getAppealType, reqVO.getAppealType())
                .eqIfPresent(AppealRecordDO::getOriginalGrade, reqVO.getOriginalGrade())
                .eqIfPresent(AppealRecordDO::getFileCheckStatus, reqVO.getFileCheckStatus())
                .eqIfPresent(AppealRecordDO::getRejectReason, reqVO.getRejectReason())
                .eqIfPresent(AppealRecordDO::getFileCheckResult, reqVO.getFileCheckResult())
                .eqIfPresent(AppealRecordDO::getNotifyStatus, reqVO.getNotifyStatus())
                .eqIfPresent(AppealRecordDO::getReviewHour, reqVO.getReviewHour())
                .eqIfPresent(AppealRecordDO::getReviewProgress, reqVO.getReviewProgress())
                .eqIfPresent(AppealRecordDO::getStageResult, reqVO.getStageResult())
                .betweenIfPresent(AppealRecordDO::getLatestOperTime, reqVO.getLatestOperTime())
                .eqIfPresent(AppealRecordDO::getWarningStatus, reqVO.getWarningStatus())
                .eqIfPresent(AppealRecordDO::getPreResult, reqVO.getPreResult())
                .eqIfPresent(AppealRecordDO::getCheckFileCount, reqVO.getCheckFileCount())
                .eqIfPresent(AppealRecordDO::getCorrectSuggest, reqVO.getCorrectSuggest())
                .eqIfPresent(AppealRecordDO::getCloseReportUrl, reqVO.getCloseReportUrl())
                .eqIfPresent(AppealRecordDO::getDataSyncStatus, reqVO.getDataSyncStatus())
                .betweenIfPresent(AppealRecordDO::getSyncTime, reqVO.getSyncTime())
                .eqIfPresent(AppealRecordDO::getFeedbackStatus, reqVO.getFeedbackStatus())
                .eqIfPresent(AppealRecordDO::getArchiveId, reqVO.getArchiveId())
                .eqIfPresent(AppealRecordDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AppealRecordDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(AppealRecordDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(AppealRecordDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(AppealRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AppealRecordDO::getId));
    }

}