package cn.iocoder.yudao.module.kitchen.dal.mysql.rectifyreview;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.RectifyReviewLedgerPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.RectifyReviewLedgerRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.RectifyReviewPageReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifyreview.RectifyReviewDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 整改通知书复审台账 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface RectifyReviewMapper extends BaseMapperX<RectifyReviewDO> {

    default PageResult<RectifyReviewDO> selectPage(RectifyReviewPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RectifyReviewDO>()
                .eqIfPresent(RectifyReviewDO::getLedgerCode, reqVO.getLedgerCode())
                .eqIfPresent(RectifyReviewDO::getEntId, reqVO.getEntId())
                .eqIfPresent(RectifyReviewDO::getIllegalTypeId, reqVO.getIllegalTypeId())
                .eqIfPresent(RectifyReviewDO::getIllegalLevelId, reqVO.getIllegalLevelId())
                .eqIfPresent(RectifyReviewDO::getEvidenceUrl, reqVO.getEvidenceUrl())
                .betweenIfPresent(RectifyReviewDO::getDraftTime, reqVO.getDraftTime())
                .eqIfPresent(RectifyReviewDO::getReviewStatus, reqVO.getReviewStatus())
                .eqIfPresent(RectifyReviewDO::getReviewBy, reqVO.getReviewBy())
                .betweenIfPresent(RectifyReviewDO::getReviewTime, reqVO.getReviewTime())
                .betweenIfPresent(RectifyReviewDO::getCancelTime, reqVO.getCancelTime())
                .eqIfPresent(RectifyReviewDO::getCancelReasonId, reqVO.getCancelReasonId())
                .eqIfPresent(RectifyReviewDO::getLawLedgerCode, reqVO.getLawLedgerCode())
                .eqIfPresent(RectifyReviewDO::getRectifyNoticeCode,reqVO.getRectifyNoticeCode())
                .betweenIfPresent(RectifyReviewDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(RectifyReviewDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RectifyReviewDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RectifyReviewDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RectifyReviewDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(RectifyReviewDO::getId));
    }

    List<RectifyReviewLedgerRespVO> selectLedgerPage(RectifyReviewLedgerPageReqVO reqVO);

    Long selectLedgerPageCount(RectifyReviewLedgerPageReqVO reqVO);


    List<RectifyReviewDO> selectBatchEvidence(List<Long> ledgerIdList, int offset, Long pageSize);
}
