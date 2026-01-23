package cn.iocoder.yudao.module.park.dal.mysql.park.order.settlement;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.order.settlement.vo.SettlementPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.settlement.SettlementDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分账结算 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface SettlementMapper extends BaseMapperX<SettlementDO> {

    default PageResult<SettlementDO> selectPage(SettlementPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SettlementDO>()
                .eqIfPresent(SettlementDO::getMerchantId, reqVO.getMerchantId())
                .eqIfPresent(SettlementDO::getLotId, reqVO.getLotId())
                .eqIfPresent(SettlementDO::getStatCycle, reqVO.getStatCycle())
                .betweenIfPresent(SettlementDO::getStartDate, reqVO.getStartDate())
                .betweenIfPresent(SettlementDO::getEndDate, reqVO.getEndDate())
                .eqIfPresent(SettlementDO::getTotalAmount, reqVO.getTotalAmount())
                .eqIfPresent(SettlementDO::getRefundAmount, reqVO.getRefundAmount())
                .eqIfPresent(SettlementDO::getPlatformAmount, reqVO.getPlatformAmount())
                .eqIfPresent(SettlementDO::getMerchantAmount, reqVO.getMerchantAmount())
                .eqIfPresent(SettlementDO::getTaxAmount, reqVO.getTaxAmount())
                .eqIfPresent(SettlementDO::getSettlementStatus, reqVO.getSettlementStatus())
                .betweenIfPresent(SettlementDO::getPayTime, reqVO.getPayTime())
                .eqIfPresent(SettlementDO::getPayType, reqVO.getPayType())
                .betweenIfPresent(SettlementDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SettlementDO::getRemark, reqVO.getRemark())
                .eqIfPresent(SettlementDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SettlementDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(SettlementDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(SettlementDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(SettlementDO::getId));
    }

}
