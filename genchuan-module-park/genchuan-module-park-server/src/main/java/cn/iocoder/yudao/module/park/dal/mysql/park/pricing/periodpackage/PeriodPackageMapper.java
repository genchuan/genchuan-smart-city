package cn.iocoder.yudao.module.park.dal.mysql.park.pricing.periodpackage;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.periodpackage.vo.PeriodPackagePageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.periodpackage.PeriodPackageDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 期卡套餐 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PeriodPackageMapper extends BaseMapperX<PeriodPackageDO> {

    default PageResult<PeriodPackageDO> selectPage(PeriodPackagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PeriodPackageDO>()
                .likeIfPresent(PeriodPackageDO::getPackageName, reqVO.getPackageName())
                .eqIfPresent(PeriodPackageDO::getPackageType, reqVO.getPackageType())
                .eqIfPresent(PeriodPackageDO::getOriginalPrice, reqVO.getOriginalPrice())
                .eqIfPresent(PeriodPackageDO::getSalePrice, reqVO.getSalePrice())
                .eqIfPresent(PeriodPackageDO::getValidDays, reqVO.getValidDays())
                .eqIfPresent(PeriodPackageDO::getBindCarLimit, reqVO.getBindCarLimit())
                .eqIfPresent(PeriodPackageDO::getApplyLotIds, reqVO.getApplyLotIds())
                .eqIfPresent(PeriodPackageDO::getSpaceTypeIds, reqVO.getSpaceTypeIds())
                .eqIfPresent(PeriodPackageDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PeriodPackageDO::getSalesCount, reqVO.getSalesCount())
                .betweenIfPresent(PeriodPackageDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(PeriodPackageDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PeriodPackageDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PeriodPackageDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PeriodPackageDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(PeriodPackageDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(PeriodPackageDO::getId));
    }

}
