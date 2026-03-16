package cn.iocoder.yudao.module.park.dal.mysql.park.user.paymentproxy;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.user.paymentproxy.vo.PaymentProxyPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.paymentproxy.PaymentProxyDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 代付规则 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PaymentProxyMapper extends BaseMapperX<PaymentProxyDO> {

    default PageResult<PaymentProxyDO> selectPage(PaymentProxyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PaymentProxyDO>()
                .likeIfPresent(PaymentProxyDO::getProxyName, reqVO.getProxyName())
                .eqIfPresent(PaymentProxyDO::getProxyType, reqVO.getProxyType())
                .eqIfPresent(PaymentProxyDO::getPayerId, reqVO.getPayerId())
                .eqIfPresent(PaymentProxyDO::getPayeeType, reqVO.getPayeeType())
                .eqIfPresent(PaymentProxyDO::getPayeeId, reqVO.getPayeeId())
                .eqIfPresent(PaymentProxyDO::getAssetIds, reqVO.getAssetIds())
                .eqIfPresent(PaymentProxyDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(PaymentProxyDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(PaymentProxyDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PaymentProxyDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PaymentProxyDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PaymentProxyDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(PaymentProxyDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(PaymentProxyDO::getId));
    }

}
