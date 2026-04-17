package cn.iocoder.yudao.module.usermerchant.dal.mysql.usermgmt.plateauth;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.plateauth.PlateAuthDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.plateauth.vo.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 车牌认证 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PlateAuthMapper extends BaseMapperX<PlateAuthDO> {

    default PageResult<PlateAuthDO> selectPage(PlateAuthPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PlateAuthDO>()
                .eqIfPresent(PlateAuthDO::getUserId, reqVO.getUserId())
                .eqIfPresent(PlateAuthDO::getCarId, reqVO.getCarId())
                .eqIfPresent(PlateAuthDO::getPlateNo, reqVO.getPlateNo())
                .betweenIfPresent(PlateAuthDO::getApplyTime, reqVO.getApplyTime())
                .eqIfPresent(PlateAuthDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PlateAuthDO::getAuditorId, reqVO.getAuditorId())
                .betweenIfPresent(PlateAuthDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(PlateAuthDO::getAuditRemark, reqVO.getAuditRemark())
                .eqIfPresent(PlateAuthDO::getRemark, reqVO.getRemark())
                .orderByDesc(PlateAuthDO::getId));
    }

    List<PlateAuthChartRespVO.AuthTrendVO> selectAuthTrend(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("granularity") String granularity
    );

    Long selectAuthCount(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    BigDecimal selectAuthPassRate(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}