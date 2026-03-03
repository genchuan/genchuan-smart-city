package cn.iocoder.yudao.module.evaluate.dal.mysql.record;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.inspection.record.vo.RecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.record.RecordDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考察记录 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface RecordMapper extends BaseMapperX<RecordDO> {

    default PageResult<RecordDO> selectPage(RecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RecordDO>()
                .eqIfPresent(RecordDO::getRecordId, reqVO.getRecordId())
                .eqIfPresent(RecordDO::getCode, reqVO.getCode())
                .eqIfPresent(RecordDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(RecordDO::getObjectId, reqVO.getObjectId())
                .eqIfPresent(RecordDO::getInspectBy, reqVO.getInspectBy())
                .betweenIfPresent(RecordDO::getInspectTime, reqVO.getInspectTime())
                .eqIfPresent(RecordDO::getTotalScore, reqVO.getTotalScore())
                .eqIfPresent(RecordDO::getFinalScore, reqVO.getFinalScore())
                .eqIfPresent(RecordDO::getProblemDesc, reqVO.getProblemDesc())
                .eqIfPresent(RecordDO::getPhotoCount, reqVO.getPhotoCount())
                .eqIfPresent(RecordDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(RecordDO::getSubmitTime, reqVO.getSubmitTime())
                .eqIfPresent(RecordDO::getAuditBy, reqVO.getAuditBy())
                .betweenIfPresent(RecordDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(RecordDO::getRejectOpinion, reqVO.getRejectOpinion())
                .betweenIfPresent(RecordDO::getDraftTime, reqVO.getDraftTime())
                .betweenIfPresent(RecordDO::getLastEditTime, reqVO.getLastEditTime())
                .eqIfPresent(RecordDO::getLastEditBy, reqVO.getLastEditBy())
                .eqIfPresent(RecordDO::getPhotoStatus, reqVO.getPhotoStatus())
                .eqIfPresent(RecordDO::getRecallCount, reqVO.getRecallCount())
                .betweenIfPresent(RecordDO::getLastRecallTime, reqVO.getLastRecallTime())
                .eqIfPresent(RecordDO::getWaitAuditHour, reqVO.getWaitAuditHour())
                .eqIfPresent(RecordDO::getDataSyncStatus, reqVO.getDataSyncStatus())
                .betweenIfPresent(RecordDO::getSyncTime, reqVO.getSyncTime())
                .eqIfPresent(RecordDO::getResubmitCount, reqVO.getResubmitCount())
                .eqIfPresent(RecordDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RecordDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RecordDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RecordDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(RecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RecordDO::getId));
    }

}