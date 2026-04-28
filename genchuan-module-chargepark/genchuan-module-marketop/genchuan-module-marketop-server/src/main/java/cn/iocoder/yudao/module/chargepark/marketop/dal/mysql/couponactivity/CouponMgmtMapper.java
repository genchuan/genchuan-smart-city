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
        LambdaQueryWrapperX<CouponMgmtDO> wrapper = new LambdaQueryWrapperX<CouponMgmtDO>()
                .likeIfPresent(CouponMgmtDO::getName, reqVO.getName())
                .eqIfPresent(CouponMgmtDO::getType, reqVO.getType())
                .eqIfPresent(CouponMgmtDO::getStatus, reqVO.getStatus())
                .orderByDesc(CouponMgmtDO::getId);
        // validTime 范围
        if (reqVO.getValidStartTime() != null && reqVO.getValidEndTime() != null) {
            wrapper.between(CouponMgmtDO::getValidTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getValidStartTime()), java.time.ZoneId.systemDefault()),
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getValidEndTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getValidStartTime() != null) {
            wrapper.ge(CouponMgmtDO::getValidTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getValidStartTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getValidEndTime() != null) {
            wrapper.le(CouponMgmtDO::getValidTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getValidEndTime()), java.time.ZoneId.systemDefault()));
        }
        // createTime 范围
        if (reqVO.getStartTime() != null && reqVO.getEndTime() != null) {
            wrapper.between(CouponMgmtDO::getCreateTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getStartTime()), java.time.ZoneId.systemDefault()),
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEndTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getStartTime() != null) {
            wrapper.ge(CouponMgmtDO::getCreateTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getStartTime()), java.time.ZoneId.systemDefault()));
        } else if (reqVO.getEndTime() != null) {
            wrapper.le(CouponMgmtDO::getCreateTime,
                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEndTime()), java.time.ZoneId.systemDefault()));
        }
        return selectPage(reqVO, wrapper);
    }

}
