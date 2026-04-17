package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.exchangemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.ExchangeOrderPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeOrderDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExchangeOrderMapper extends BaseMapperX<ExchangeOrderDO> {

    default PageResult<ExchangeOrderDO> selectPage(ExchangeOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ExchangeOrderDO>()
                .eqIfPresent(ExchangeOrderDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(ExchangeOrderDO::getCategoryId, reqVO.getCategoryId())
                .eqIfPresent(ExchangeOrderDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ExchangeOrderDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ExchangeOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ExchangeOrderDO::getId));
    }

}
