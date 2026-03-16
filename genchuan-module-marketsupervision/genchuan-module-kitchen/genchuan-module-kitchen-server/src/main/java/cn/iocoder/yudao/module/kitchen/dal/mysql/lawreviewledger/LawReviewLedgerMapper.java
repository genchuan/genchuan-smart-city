package cn.iocoder.yudao.module.kitchen.dal.mysql.lawreviewledger;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.kitchen.controller.admin.lawreviewledger.vo.LawReviewLedgerPageReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.lawreviewledger.LawReviewLedgerDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 执法复审总台账 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface LawReviewLedgerMapper extends BaseMapperX<LawReviewLedgerDO> {

    default PageResult<LawReviewLedgerDO> selectPage(LawReviewLedgerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LawReviewLedgerDO>()
                .eqIfPresent(LawReviewLedgerDO::getLedgerCode, reqVO.getLedgerCode())
                .eqIfPresent(LawReviewLedgerDO::getRectifyReviewId, reqVO.getRectifyReviewId())
                .eqIfPresent(LawReviewLedgerDO::getPunishReviewId, reqVO.getPunishReviewId())
                .eqIfPresent(LawReviewLedgerDO::getEntId, reqVO.getEntId())
                .eqIfPresent(LawReviewLedgerDO::getLawAreaCode, reqVO.getLawAreaCode())
                .betweenIfPresent(LawReviewLedgerDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(LawReviewLedgerDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(LawReviewLedgerDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(LawReviewLedgerDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(LawReviewLedgerDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(LawReviewLedgerDO::getId));
    }

}
