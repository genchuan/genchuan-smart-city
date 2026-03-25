package cn.iocoder.yudao.module.kitchen.dal.mysql.punishreviewledger;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.PunishReviewLedgerPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.PunishReviewLedgerRespVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.punishreviewledger.PunishReviewLedgerDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 处罚通知书复审台账 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PunishReviewLedgerMapper extends BaseMapperX<PunishReviewLedgerDO> {

    default PageResult<PunishReviewLedgerDO> selectPage(PunishReviewLedgerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PunishReviewLedgerDO>()
                .eqIfPresent(PunishReviewLedgerDO::getLedgerCode, reqVO.getLedgerCode())
                .eqIfPresent(PunishReviewLedgerDO::getEntId, reqVO.getEntId())
                .eqIfPresent(PunishReviewLedgerDO::getIllegalTypeId, reqVO.getIllegalTypeId())
                .eqIfPresent(PunishReviewLedgerDO::getIllegalLevelId, reqVO.getIllegalLevelId())
                .eqIfPresent(PunishReviewLedgerDO::getEntRectifyRecordId, reqVO.getEntRectifyRecordId())
                .eqIfPresent(PunishReviewLedgerDO::getPunishNoticeId, reqVO.getPunishNoticeId())
                .eqIfPresent(PunishReviewLedgerDO::getLawLedgerCode, reqVO.getLawLedgerCode())
                .eqIfPresent(PunishReviewLedgerDO::getEvidenceUrl, reqVO.getEvidenceUrl())
                .eqIfPresent(PunishReviewLedgerDO::getDraftPunishAmt, reqVO.getDraftPunishAmt())
                .eqIfPresent(PunishReviewLedgerDO::getLegalBasis, reqVO.getLegalBasis())
                .eqIfPresent(PunishReviewLedgerDO::getReviewStatus, reqVO.getReviewStatus())
                .eqIfPresent(PunishReviewLedgerDO::getReviewBy, reqVO.getReviewBy())
                .eqIfPresent(PunishReviewLedgerDO::getCancelReasonId, reqVO.getCancelReasonId())
                .betweenIfPresent(PunishReviewLedgerDO::getDraftTime, reqVO.getDraftTime())
                .betweenIfPresent(PunishReviewLedgerDO::getReviewTime, reqVO.getReviewTime())
                .betweenIfPresent(PunishReviewLedgerDO::getCancelTime, reqVO.getCancelTime())
                .betweenIfPresent(PunishReviewLedgerDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(PunishReviewLedgerDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PunishReviewLedgerDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PunishReviewLedgerDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(PunishReviewLedgerDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(PunishReviewLedgerDO::getId));
    }

    List<PunishReviewLedgerRespVO> selectLedgerPage(PunishReviewLedgerPageReqVO pageReqVO);

    Long selectLedgerPageCount(PunishReviewLedgerPageReqVO pageReqVO);
}
