package cn.iocoder.yudao.module.evaluate.dal.mysql.publicrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.publicrecord.vo.PublicRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.publicrecord.PublicRecordDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评价结果公示 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PublicRecordMapper extends BaseMapperX<PublicRecordDO> {

    default PageResult<PublicRecordDO> selectPage(PublicRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PublicRecordDO>()
                .eqIfPresent(PublicRecordDO::getPublicId, reqVO.getPublicId())
                .eqIfPresent(PublicRecordDO::getCode, reqVO.getCode())
                .eqIfPresent(PublicRecordDO::getAuditId, reqVO.getAuditId())
                .eqIfPresent(PublicRecordDO::getObjectId, reqVO.getObjectId())
                .eqIfPresent(PublicRecordDO::getStandardId, reqVO.getStandardId())
                .betweenIfPresent(PublicRecordDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(PublicRecordDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(PublicRecordDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PublicRecordDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(PublicRecordDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(PublicRecordDO::getPublicUrl, reqVO.getPublicUrl())
                .eqIfPresent(PublicRecordDO::getObjectionCount, reqVO.getObjectionCount())
                .betweenIfPresent(PublicRecordDO::getCompleteTime, reqVO.getCompleteTime())
                .eqIfPresent(PublicRecordDO::getAppealRecordCode, reqVO.getAppealRecordCode())
                .eqIfPresent(PublicRecordDO::getRemainHour, reqVO.getRemainHour())
                .eqIfPresent(PublicRecordDO::getVisitCount, reqVO.getVisitCount())
                .betweenIfPresent(PublicRecordDO::getLatestObjectionTime, reqVO.getLatestObjectionTime())
                .eqIfPresent(PublicRecordDO::getPublishChannel, reqVO.getPublishChannel())
                .eqIfPresent(PublicRecordDO::getAdminContact, reqVO.getAdminContact())
                .eqIfPresent(PublicRecordDO::getStopReason, reqVO.getStopReason())
                .betweenIfPresent(PublicRecordDO::getStopTime, reqVO.getStopTime())
                .eqIfPresent(PublicRecordDO::getStopBy, reqVO.getStopBy())
                .eqIfPresent(PublicRecordDO::getStopRemainHour, reqVO.getStopRemainHour())
                .eqIfPresent(PublicRecordDO::getStopObjectionCount, reqVO.getStopObjectionCount())
                .eqIfPresent(PublicRecordDO::getFollowSuggest, reqVO.getFollowSuggest())
                .eqIfPresent(PublicRecordDO::getUrlStatus, reqVO.getUrlStatus())
                .eqIfPresent(PublicRecordDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PublicRecordDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PublicRecordDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(PublicRecordDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(PublicRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PublicRecordDO::getId));
    }

}