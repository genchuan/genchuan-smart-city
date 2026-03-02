package cn.iocoder.yudao.module.evaluate.dal.mysql.auditrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.auditrecord.vo.AuditRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.auditrecord.AuditRecordDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评价结果审核 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AuditRecordMapper extends BaseMapperX<AuditRecordDO> {

    default PageResult<AuditRecordDO> selectPage(AuditRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AuditRecordDO>()
                .eqIfPresent(AuditRecordDO::getAuditId, reqVO.getAuditId())
                .eqIfPresent(AuditRecordDO::getCode, reqVO.getCode())
                .eqIfPresent(AuditRecordDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(AuditRecordDO::getObjectId, reqVO.getObjectId())
                .eqIfPresent(AuditRecordDO::getEvalScore, reqVO.getEvalScore())
                .eqIfPresent(AuditRecordDO::getStandardId, reqVO.getStandardId())
                .eqIfPresent(AuditRecordDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(AuditRecordDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(AuditRecordDO::getAuditBy, reqVO.getAuditBy())
                .betweenIfPresent(AuditRecordDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(AuditRecordDO::getDataSource, reqVO.getDataSource())
                .eqIfPresent(AuditRecordDO::getRejectCheckResult, reqVO.getRejectCheckResult())
                .eqIfPresent(AuditRecordDO::getRejectReason, reqVO.getRejectReason())
                .eqIfPresent(AuditRecordDO::getAuditOpinion, reqVO.getAuditOpinion())
                .eqIfPresent(AuditRecordDO::getDataSourceDetail, reqVO.getDataSourceDetail())
                .eqIfPresent(AuditRecordDO::getIndexScore, reqVO.getIndexScore())
                .eqIfPresent(AuditRecordDO::getAssignBy, reqVO.getAssignBy())
                .eqIfPresent(AuditRecordDO::getTaskCreateBy, reqVO.getTaskCreateBy())
                .eqIfPresent(AuditRecordDO::getWaitHour, reqVO.getWaitHour())
                .eqIfPresent(AuditRecordDO::getErrorDataSource, reqVO.getErrorDataSource())
                .eqIfPresent(AuditRecordDO::getRecalcCount, reqVO.getRecalcCount())
                .betweenIfPresent(AuditRecordDO::getRecalcTime, reqVO.getRecalcTime())
                .eqIfPresent(AuditRecordDO::getCorrectGuide, reqVO.getCorrectGuide())
                .eqIfPresent(AuditRecordDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AuditRecordDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(AuditRecordDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(AuditRecordDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(AuditRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AuditRecordDO::getId));
    }

}