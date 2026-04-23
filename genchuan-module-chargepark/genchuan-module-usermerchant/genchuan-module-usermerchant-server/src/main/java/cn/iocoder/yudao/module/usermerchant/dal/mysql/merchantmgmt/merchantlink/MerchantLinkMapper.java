package cn.iocoder.yudao.module.usermerchant.dal.mysql.merchantmgmt.merchantlink;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantlink.MerchantLinkDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantlink.vo.*;

/**
 * 商户对接 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MerchantLinkMapper extends BaseMapperX<MerchantLinkDO> {

    default PageResult<MerchantLinkDO> selectPage(MerchantLinkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MerchantLinkDO>()
                .eqIfPresent(MerchantLinkDO::getMerchantId, reqVO.getMerchantId())
                .eqIfPresent(MerchantLinkDO::getLinkType, reqVO.getLinkType())
                .eqIfPresent(MerchantLinkDO::getApiUrl, reqVO.getApiUrl())
                .eqIfPresent(MerchantLinkDO::getApiKey, reqVO.getApiKey())
                .eqIfPresent(MerchantLinkDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(MerchantLinkDO::getEffectTime, reqVO.getEffectTime())
                .betweenIfPresent(MerchantLinkDO::getLastSyncTime, reqVO.getLastSyncTime())
                .eqIfPresent(MerchantLinkDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MerchantLinkDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(MerchantLinkDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(MerchantLinkDO::getCreator, reqVO.getCreator())
                .eqIfPresent(MerchantLinkDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(MerchantLinkDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(MerchantLinkDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(MerchantLinkDO::getId));
    }

}