package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo.CouponMgmtPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.CouponMgmtDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface CouponMgmtMapper extends BaseMapperX<CouponMgmtDO> {

    default PageResult<CouponMgmtDO> selectPage(CouponMgmtPageReqVO reqVO) {
        LambdaQueryWrapperX<CouponMgmtDO> wrapper = new LambdaQueryWrapperX<CouponMgmtDO>()
                .likeIfPresent(CouponMgmtDO::getName, reqVO.getName())
                .eqIfPresent(CouponMgmtDO::getType, reqVO.getType())
                .eqIfPresent(CouponMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CouponMgmtDO::getAmount, reqVO.getAmount())
                .betweenIfPresent(CouponMgmtDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(CouponMgmtDO::getValidTime, reqVO.getValidTime())
                .betweenIfPresent(CouponMgmtDO::getVerifyTime, reqVO.getVerifyTime())
                .betweenIfPresent(CouponMgmtDO::getSendTime, reqVO.getSendTime())
                .orderByDesc(CouponMgmtDO::getId);
        // validTime 范围
//        if (reqVO.getValidStartTime() != null && reqVO.getValidEndTime() != null) {
//            wrapper.between(CouponMgmtDO::getValidTime,
//                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getValidStartTime()), java.time.ZoneId.systemDefault()),
//                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getValidEndTime()), java.time.ZoneId.systemDefault()));
//        } else if (reqVO.getValidStartTime() != null) {
//            wrapper.ge(CouponMgmtDO::getValidTime,
//                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getValidStartTime()), java.time.ZoneId.systemDefault()));
//        } else if (reqVO.getValidEndTime() != null) {
//            wrapper.le(CouponMgmtDO::getValidTime,
//                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getValidEndTime()), java.time.ZoneId.systemDefault()));
//        }
        // createTime 范围
//        if (reqVO.getStartTime() != null && reqVO.getEndTime() != null) {
//            wrapper.between(CouponMgmtDO::getCreateTime,
//                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getStartTime()), java.time.ZoneId.systemDefault()),
//                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEndTime()), java.time.ZoneId.systemDefault()));
//        } else if (reqVO.getStartTime() != null) {
//            wrapper.ge(CouponMgmtDO::getCreateTime,
//                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getStartTime()), java.time.ZoneId.systemDefault()));
//        } else if (reqVO.getEndTime() != null) {
//            wrapper.le(CouponMgmtDO::getCreateTime,
//                    java.time.LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(reqVO.getEndTime()), java.time.ZoneId.systemDefault()));
//        }

        if (StringUtils.isNotBlank(reqVO.getDate())){
            java.time.LocalDate startDate = java.time.LocalDate.parse(reqVO.getDate());
            LocalDate endDate = startDate.plusDays(1);
            wrapper.between(CouponMgmtDO::getSendTime, startDate, endDate);
        }

        return selectPage(reqVO, wrapper);
    }

    @Select("SELECT type, COUNT(*) AS count FROM coupon_mgmt GROUP BY type")
    List<Map<String, Object>> selectTypeCountList();

    List<Map<String, Object>> selectStationIdsByNames(@Param("names") List<String> names);

}
