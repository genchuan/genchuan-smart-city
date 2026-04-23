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
        LambdaQueryWrapperX<ExchangeOrderDO> wrapper = new LambdaQueryWrapperX<ExchangeOrderDO>()
                .likeIfPresent(ExchangeOrderDO::getNo, reqVO.getNo())
                .eqIfPresent(ExchangeOrderDO::getCategoryId, reqVO.getCategoryId())
                .eqIfPresent(ExchangeOrderDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ExchangeOrderDO::getPayStatus, reqVO.getPayStatus())
                .orderByDesc(ExchangeOrderDO::getId);

        if (reqVO.getStartTime() != null && reqVO.getEndTime() != null) {
            wrapper.between(ExchangeOrderDO::getCreateTime, reqVO.getStartTime(), reqVO.getEndTime());
        }
        return selectPage(reqVO, wrapper);
    }

}
