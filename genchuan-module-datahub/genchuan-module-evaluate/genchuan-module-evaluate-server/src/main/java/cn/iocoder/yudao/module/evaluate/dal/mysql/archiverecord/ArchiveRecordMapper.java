package cn.iocoder.yudao.module.evaluate.dal.mysql.archiverecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.archiverecord.vo.ArchiveRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.archiverecord.ArchiveRecordDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评价结果存档 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ArchiveRecordMapper extends BaseMapperX<ArchiveRecordDO> {

    default PageResult<ArchiveRecordDO> selectPage(ArchiveRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ArchiveRecordDO>()
                .eqIfPresent(ArchiveRecordDO::getArchiveId, reqVO.getArchiveId())
                .eqIfPresent(ArchiveRecordDO::getCode, reqVO.getCode())
                .eqIfPresent(ArchiveRecordDO::getPublicId, reqVO.getPublicId())
                .eqIfPresent(ArchiveRecordDO::getObjectId, reqVO.getObjectId())
                .eqIfPresent(ArchiveRecordDO::getAuditId, reqVO.getAuditId())
                .eqIfPresent(ArchiveRecordDO::getStandardId, reqVO.getStandardId())
                .eqIfPresent(ArchiveRecordDO::getEvalScore, reqVO.getEvalScore())
                .eqIfPresent(ArchiveRecordDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ArchiveRecordDO::getApplyTime, reqVO.getApplyTime())
                .eqIfPresent(ArchiveRecordDO::getArchiveBy, reqVO.getArchiveBy())
                .betweenIfPresent(ArchiveRecordDO::getActualTime, reqVO.getActualTime())
                .eqIfPresent(ArchiveRecordDO::getAttachmentCount, reqVO.getAttachmentCount())
                .eqIfPresent(ArchiveRecordDO::getStoreLocation, reqVO.getStoreLocation())
                .eqIfPresent(ArchiveRecordDO::getTraceUrl, reqVO.getTraceUrl())
                .eqIfPresent(ArchiveRecordDO::getQueryCount, reqVO.getQueryCount())
                .betweenIfPresent(ArchiveRecordDO::getLatestQueryTime, reqVO.getLatestQueryTime())
                .eqIfPresent(ArchiveRecordDO::getWaitReason, reqVO.getWaitReason())
                .eqIfPresent(ArchiveRecordDO::getWaitHour, reqVO.getWaitHour())
                .eqIfPresent(ArchiveRecordDO::getCheckResult, reqVO.getCheckResult())
                .eqIfPresent(ArchiveRecordDO::getAppealId, reqVO.getAppealId())
                .eqIfPresent(ArchiveRecordDO::getAppealStatus, reqVO.getAppealStatus())
                .eqIfPresent(ArchiveRecordDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ArchiveRecordDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ArchiveRecordDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ArchiveRecordDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ArchiveRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ArchiveRecordDO::getId));
    }

}