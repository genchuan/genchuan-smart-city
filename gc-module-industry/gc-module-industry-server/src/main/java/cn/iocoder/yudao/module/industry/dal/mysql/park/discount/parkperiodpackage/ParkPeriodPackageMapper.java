package cn.iocoder.yudao.module.industry.dal.mysql.park.discount.parkperiodpackage;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkperiodpackage.vo.ParkPeriodPackagePageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkperiodpackage.ParkPeriodPackageDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 期卡套餐 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkPeriodPackageMapper extends BaseMapperX<ParkPeriodPackageDO> {

    default PageResult<ParkPeriodPackageDO> selectPage(ParkPeriodPackagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkPeriodPackageDO>()
                .likeIfPresent(ParkPeriodPackageDO::getPackageName, reqVO.getPackageName())
                .eqIfPresent(ParkPeriodPackageDO::getPackageType, reqVO.getPackageType())
                .eqIfPresent(ParkPeriodPackageDO::getApplyLotIds, reqVO.getApplyLotIds())
                .eqIfPresent(ParkPeriodPackageDO::getSpaceType, reqVO.getSpaceType())
                .eqIfPresent(ParkPeriodPackageDO::getOriginalPrice, reqVO.getOriginalPrice())
                .eqIfPresent(ParkPeriodPackageDO::getSalePrice, reqVO.getSalePrice())
                .eqIfPresent(ParkPeriodPackageDO::getValidDays, reqVO.getValidDays())
                .eqIfPresent(ParkPeriodPackageDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ParkPeriodPackageDO::getSalesCount, reqVO.getSalesCount())
                .betweenIfPresent(ParkPeriodPackageDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkPeriodPackageDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkPeriodPackageDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkPeriodPackageDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkPeriodPackageDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkPeriodPackageDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkPeriodPackageDO::getId));
    }

}
