package cn.iocoder.yudao.module.accessmgmt.dal.mysql.accessreport.cyclereport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.accessreport.cyclereport.vo.AccessCycleReportChartRespVO;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.accessreport.cyclereport.vo.AccessCycleReportPageReqVO;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.accessreport.cyclereport.AccessCycleReportDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

/**
 * 通行周期报表 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AccessCycleReportMapper extends BaseMapperX<AccessCycleReportDO> {

    default PageResult<AccessCycleReportDO> selectPage(AccessCycleReportPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AccessCycleReportDO>()
                .likeIfPresent(AccessCycleReportDO::getReportName, reqVO.getReportName())
                .eqIfPresent(AccessCycleReportDO::getCycleType, reqVO.getCycleType())
                .betweenIfPresent(AccessCycleReportDO::getCreateTime,
                        reqVO.getStartTime() != null ? Instant.ofEpochMilli(reqVO.getStartTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null,
                        reqVO.getEndTime() != null ? Instant.ofEpochMilli(reqVO.getEndTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null)
                .orderByDesc(AccessCycleReportDO::getId));
    }

    /**
     * 查询卡片数据
     */
    AccessCycleReportChartRespVO.CardData selectCardData(@Param("id") Long id);

    /**
     * 查询通行趋势列表
     */
    List<AccessCycleReportChartRespVO.AccessTrendItem> selectAccessTrendList(@Param("id") Long id);

    /**
     * 查询车辆通行趋势列表
     */
    List<AccessCycleReportChartRespVO.VehicleTrendItem> selectVehicleTrendList(@Param("id") Long id);

    /**
     * 查询车位使用趋势列表
     */
    List<AccessCycleReportChartRespVO.SpaceTrendItem> selectSpaceTrendList(@Param("id") Long id);

    /**
     * 查询各区域通行次数统计列表
     */
    List<AccessCycleReportChartRespVO.AreaCountItem> selectAreaCountList(@Param("id") Long id);

    /**
     * 查询各车场停车次数统计列表
     */
    List<AccessCycleReportChartRespVO.ParkCountItem> selectParkCountList(@Param("id") Long id);

    /**
     * 查询人员类型分布列表
     */
    List<AccessCycleReportChartRespVO.NameValueItem> selectPersonTypeList(@Param("id") Long id);

    /**
     * 查询车辆类型分布列表
     */
    List<AccessCycleReportChartRespVO.NameValueItem> selectVehicleTypeList(@Param("id") Long id);

    /**
     * 查询支付方式分布列表
     */
    List<AccessCycleReportChartRespVO.NameValueItem> selectPayTypeList(@Param("id") Long id);

    /**
     * 查询点位热力分布列表
     */
    List<AccessCycleReportChartRespVO.PointMapItem> selectPointMapList(@Param("id") Long id);

    /**
     * 查询轨迹地图列表
     */
    List<AccessCycleReportChartRespVO.TrackMapItem> selectTrackMapList(@Param("id") Long id);

}
