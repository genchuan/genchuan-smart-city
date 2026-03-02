package cn.iocoder.yudao.module.envirhealth.dal.mysql.market;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.market.vo.market.MarketPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.market.MarketDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.market.detail.MarketDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 集贸市场 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MarketMapper extends BaseMapperX<MarketDO> {

    default PageResult<MarketDO> selectPage(MarketPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MarketDO>()
                .eqIfPresent(MarketDO::getMarketId, reqVO.getMarketId())
                .likeIfPresent(MarketDO::getName, reqVO.getName())
                .eqIfPresent(MarketDO::getAddress, reqVO.getAddress())
                .eqIfPresent(MarketDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(MarketDO::getStallCount, reqVO.getStallCount())
                .eqIfPresent(MarketDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(MarketDO::getOperationStatusId, reqVO.getOperationStatusId())
                .eqIfPresent(MarketDO::getHygieneRate, reqVO.getHygieneRate())
                .eqIfPresent(MarketDO::getWasteTransferRate, reqVO.getWasteTransferRate())
                .eqIfPresent(MarketDO::getSewageRate, reqVO.getSewageRate())
                .eqIfPresent(MarketDO::getUnfinishedTaskCount, reqVO.getUnfinishedTaskCount())
                .eqIfPresent(MarketDO::getCleaningFrequency, reqVO.getCleaningFrequency())
                .betweenIfPresent(MarketDO::getCleaningTime, reqVO.getCleaningTime())
                .eqIfPresent(MarketDO::getCleaningArea, reqVO.getCleaningArea())
                .eqIfPresent(MarketDO::getStaffIds, reqVO.getStaffIds())
                .eqIfPresent(MarketDO::getCleaningStandard, reqVO.getCleaningStandard())
                .eqIfPresent(MarketDO::getGarbageTypeIds, reqVO.getGarbageTypeIds())
                .eqIfPresent(MarketDO::getGarbageContainerCount, reqVO.getGarbageContainerCount())
                .eqIfPresent(MarketDO::getWasteTransferInterval, reqVO.getWasteTransferInterval())
                .betweenIfPresent(MarketDO::getWasteTransferTime, reqVO.getWasteTransferTime())
                .eqIfPresent(MarketDO::getVehicleId, reqVO.getVehicleId())
                .eqIfPresent(MarketDO::getSewageDischargeArea, reqVO.getSewageDischargeArea())
                .eqIfPresent(MarketDO::getSewageDisposalWay, reqVO.getSewageDisposalWay())
                .eqIfPresent(MarketDO::getSewageCleaningFrequency, reqVO.getSewageCleaningFrequency())
                .eqIfPresent(MarketDO::getSewageProblemDesc, reqVO.getSewageProblemDesc())
                .betweenIfPresent(MarketDO::getLastSewageCleaningTime, reqVO.getLastSewageCleaningTime())
                .betweenIfPresent(MarketDO::getNextSewageCleaningTime, reqVO.getNextSewageCleaningTime())
                .eqIfPresent(MarketDO::getSewageDisposalLog, reqVO.getSewageDisposalLog())
                .betweenIfPresent(MarketDO::getHygieneCheckTime, reqVO.getHygieneCheckTime())
                .eqIfPresent(MarketDO::getCheckBy, reqVO.getCheckBy())
                .betweenIfPresent(MarketDO::getHygieneCheckDate, reqVO.getHygieneCheckDate())
                .eqIfPresent(MarketDO::getPreviousProblem, reqVO.getPreviousProblem())
                .eqIfPresent(MarketDO::getQualifiedItemCount, reqVO.getQualifiedItemCount())
                .eqIfPresent(MarketDO::getUnqualifiedItemCount, reqVO.getUnqualifiedItemCount())
                .eqIfPresent(MarketDO::getReformRequire, reqVO.getReformRequire())
                .eqIfPresent(MarketDO::getReformDeadline, reqVO.getReformDeadline())
                .eqIfPresent(MarketDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MarketDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(MarketDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(MarketDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(MarketDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MarketDO::getId));
    }

    List<MarketDetailDO> selectDetailPage(@Param("reqVO") MarketPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") MarketPageReqVO pageReqVO);
}