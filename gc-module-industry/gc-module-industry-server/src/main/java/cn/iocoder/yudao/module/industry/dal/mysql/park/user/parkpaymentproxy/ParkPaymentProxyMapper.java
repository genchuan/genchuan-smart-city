package cn.iocoder.yudao.module.industry.dal.mysql.park.user.parkpaymentproxy;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkpaymentproxy.vo.ParkPaymentProxyPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkpaymentproxy.ParkPaymentProxyDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 代付规则 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkPaymentProxyMapper extends BaseMapperX<ParkPaymentProxyDO> {

    default PageResult<ParkPaymentProxyDO> selectPage(ParkPaymentProxyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkPaymentProxyDO>()
                .likeIfPresent(ParkPaymentProxyDO::getProxyName, reqVO.getProxyName())
                .eqIfPresent(ParkPaymentProxyDO::getProxyType, reqVO.getProxyType())
                .eqIfPresent(ParkPaymentProxyDO::getPayerId, reqVO.getPayerId())
                .eqIfPresent(ParkPaymentProxyDO::getPayeeType, reqVO.getPayeeType())
                .eqIfPresent(ParkPaymentProxyDO::getAssetIds, reqVO.getAssetIds())
                .eqIfPresent(ParkPaymentProxyDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ParkPaymentProxyDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkPaymentProxyDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkPaymentProxyDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkPaymentProxyDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkPaymentProxyDO::getExtCommon4, reqVO.getExtCommon4())
                .eqIfPresent(ParkPaymentProxyDO::getRemark, reqVO.getRemark())
                .orderByDesc(ParkPaymentProxyDO::getId));
    }

}
