package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder.vo.CardOrderPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardOrderDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardOrderMapper extends BaseMapperX<CardOrderDO> {

    default PageResult<CardOrderDO> selectPage(CardOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CardOrderDO>()
                .eqIfPresent(CardOrderDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CardOrderDO::getCardId, reqVO.getCardId())
                .eqIfPresent(CardOrderDO::getPayStatus, reqVO.getPayStatus())
                .eqIfPresent(CardOrderDO::getInvoiceStatus, reqVO.getInvoiceStatus())
                .betweenIfPresent(CardOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CardOrderDO::getId));
    }

}
