package cn.iocoder.yudao.module.waterdetection.dal.mysql.watersupplyagreement;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersupplyagreement.WaterSupplyAgreementDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.watersupplyagreement.vo.*;

/**
 * 供水协议管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface WaterSupplyAgreementMapper extends BaseMapperX<WaterSupplyAgreementDO> {

    default PageResult<WaterSupplyAgreementDO> selectPage(WaterSupplyAgreementPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WaterSupplyAgreementDO>()
                .eqIfPresent(WaterSupplyAgreementDO::getAgreementNo, reqVO.getAgreementNo())
                .likeIfPresent(WaterSupplyAgreementDO::getSupplierName, reqVO.getSupplierName())
                .likeIfPresent(WaterSupplyAgreementDO::getConsumerName, reqVO.getConsumerName())
                .eqIfPresent(WaterSupplyAgreementDO::getSupplyScope, reqVO.getSupplyScope())
                .eqIfPresent(WaterSupplyAgreementDO::getWaterPriceStandard, reqVO.getWaterPriceStandard())
                .eqIfPresent(WaterSupplyAgreementDO::getResponsibilityTerms, reqVO.getResponsibilityTerms())
                .betweenIfPresent(WaterSupplyAgreementDO::getSignDate, reqVO.getSignDate())
                .betweenIfPresent(WaterSupplyAgreementDO::getValidDate, reqVO.getValidDate())
                .betweenIfPresent(WaterSupplyAgreementDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WaterSupplyAgreementDO::getId));
    }

}