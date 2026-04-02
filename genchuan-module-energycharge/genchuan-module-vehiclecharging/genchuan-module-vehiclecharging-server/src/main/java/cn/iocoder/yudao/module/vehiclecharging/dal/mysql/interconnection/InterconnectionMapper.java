package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.interconnection;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.interconnection.InterconnectionDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.interconnection.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.time.*;

import java.util.List;
import java.util.Map;

/**
 * 互联互通表 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface InterconnectionMapper extends BaseMapperX<InterconnectionDO> {

    default PageResult<InterconnectionDO> selectPage( InterconnectionPageReqVO reqVO ) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InterconnectionDO>()
                .eqIfPresent(InterconnectionDO::getConnectCode, reqVO.getConnectCode())
                .eqIfPresent(InterconnectionDO::getThirdPlatform, reqVO.getThirdPlatform())
                .eqIfPresent(InterconnectionDO::getConnectType, reqVO.getConnectType())
                .eqIfPresent(InterconnectionDO::getApiParam, reqVO.getApiParam())
                .eqIfPresent(InterconnectionDO::getSyncFreq, reqVO.getSyncFreq())
                .eqIfPresent(InterconnectionDO::getSyncSuccessRate, reqVO.getSyncSuccessRate())
                .eqIfPresent(InterconnectionDO::getConnectStatus, reqVO.getConnectStatus())
                .eqIfPresent(InterconnectionDO::getAuditUser, reqVO.getAuditUser())
                .betweenIfPresent(InterconnectionDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(InterconnectionDO::getAuditRemark, reqVO.getAuditRemark())
                .eqIfPresent(InterconnectionDO::getCloseReason, reqVO.getCloseReason())
                .eqIfPresent(InterconnectionDO::getRemark, reqVO.getRemark())
                .eqIfPresent(InterconnectionDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(InterconnectionDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(InterconnectionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InterconnectionDO::getId));
    }

    Integer selectTotalCount();

    // 各状态独立查询（用于卡片）
    @Select("SELECT COUNT(*) FROM interconnection WHERE deleted = false AND connect_status = 'opened'")
    Integer selectOpenedCount();

    @Select("SELECT COUNT(*) FROM interconnection WHERE deleted = false AND connect_status = 'inreview'")
    Integer selectAuditingCount();

    @Select("SELECT COUNT(*) FROM interconnection WHERE deleted = false AND connect_status = 'pending'")
    Integer selectWaitApplyCount();

    @Select("SELECT COUNT(*) FROM interconnection WHERE deleted = false AND connect_status = 'closed'")
    Integer selectClosedCount();

    // 分组查询（用于饼图）
    @Select("SELECT connect_status AS status, COUNT(*) AS count FROM interconnection WHERE deleted = false GROUP BY connect_status")
    List<InterconnectionChartRespVO.InterconnectionStatusRatioVO> selectStatusRatio();

    // 合作方分组查询（用于柱状图）
    @Select("SELECT third_platform AS cooperatorName, COUNT(*) AS count FROM interconnection WHERE deleted = false GROUP BY third_platform")
    List<InterconnectionChartRespVO.InterconnectionCooperatorCountVO> selectCooperatorCount();

    @Select("SELECT third_platform AS cooperatorName, COUNT(*) AS count FROM interconnection WHERE deleted = false AND connect_status = #{status} GROUP BY third_platform")
    List<InterconnectionChartRespVO.InterconnectionCooperatorCountVO> selectCooperatorCountByStatus( @Param("status") String status );

    List<InterconnectionChartRespVO.InterconnectionStatusRatioVO> selectStatusCountByCooperator(@Param("cooperator") String cooperator);

    List<Map<String, Object>> selectDailyApplyCount(@Param("startTime") LocalDate startTime, @Param("endTime") LocalDate endTime);
}