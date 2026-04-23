package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo.CouponMgmtPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.CouponMgmtDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponMgmtMapper extends BaseMapperX<CouponMgmtDO> {

    default PageResult<CouponMgmtDO> selectPage(CouponMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CouponMgmtDO>()
                .likeIfPresent(CouponMgmtDO::getName, reqVO.getName())
                .eqIfPresent(CouponMgmtDO::getType, reqVO.getType())
                .eqIfPresent(CouponMgmtDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(CouponMgmtDO::getValidTime, reqVO.getValidTime())
                .orderByDesc(CouponMgmtDO::getId));
    }

}
