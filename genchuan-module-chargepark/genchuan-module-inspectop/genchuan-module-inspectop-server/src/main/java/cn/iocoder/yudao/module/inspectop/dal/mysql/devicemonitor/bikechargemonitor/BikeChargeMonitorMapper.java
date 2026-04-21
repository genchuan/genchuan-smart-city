package cn.iocoder.yudao.module.inspectop.dal.mysql.devicemonitor.bikechargemonitor;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.bikechargemonitor.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.devicemonitor.bikechargemonitor.BikeChargeMonitorDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 两轮充电监测 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface BikeChargeMonitorMapper extends BaseMapperX<BikeChargeMonitorDO> {

    default PageResult<BikeChargeMonitorDO> selectPage(BikeChargeMonitorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BikeChargeMonitorDO>()
                .eqIfPresent(BikeChargeMonitorDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(BikeChargeMonitorDO::getStationId, reqVO.getStationId())
                .betweenIfPresent(BikeChargeMonitorDO::getMonitorTime, reqVO.getMonitorTime())
                .eqIfPresent(BikeChargeMonitorDO::getMonitorStatus, reqVO.getMonitorStatus())
                .eqIfPresent(BikeChargeMonitorDO::getAlarmStatus, reqVO.getAlarmStatus())
                .betweenIfPresent(BikeChargeMonitorDO::getAlarmTime, reqVO.getAlarmTime())
                .eqIfPresent(BikeChargeMonitorDO::getAlarmRemark, reqVO.getAlarmRemark())
                .eqIfPresent(BikeChargeMonitorDO::getProcessStatus, reqVO.getProcessStatus())
                .eqIfPresent(BikeChargeMonitorDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(BikeChargeMonitorDO::getLatitude, reqVO.getLatitude())
                .eqIfPresent(BikeChargeMonitorDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(BikeChargeMonitorDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(BikeChargeMonitorDO::getCreator, reqVO.getCreator())
                .eqIfPresent(BikeChargeMonitorDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(BikeChargeMonitorDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(BikeChargeMonitorDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(BikeChargeMonitorDO::getId));
    }


    /**
     * 关联查询分页方法
     * 通过关联 station_info 表查询场站名称
     *
     * @param page  MyBatis-Plus分页参数
     * @param reqVO 查询条件
     * @return 包含场站名称的分页结果
     */
    Page<BikeChargeMonitorRespVO> selectPageWithJoin(@Param("page") Page<BikeChargeMonitorRespVO> page,
                                                     @Param("reqVO") BikeChargeMonitorPageReqVO reqVO);

    /**
     * 获取两轮充电监测定位信息
     * 通过关联 station_info 表查询场站名称
     *
     * @param id 监测记录ID
     * @return 定位信息（包含经度、纬度、场站名称）
     */
    BikeChargeMonitorLocationRespVO selectLocationById(@Param("id") Long id);


    /**
     * 查询地图数据（充电设备状态分布）
     * 包含设备ID、设备名称、状态、经度、纬度
     *
     * @param reqVO 查询参数
     * @return 地图数据列表
     */
    List<BikeChargeMonitorChartRespVO.MapData> selectMapData(@Param("reqVO") BikeChargeMonitorChartReqVO reqVO);

    /**
     * 查询趋势数据（按小时统计正常和异常设备数量）
     *
     * @param reqVO 查询参数
     * @return 趋势数据列表
     */
    List<BikeChargeMonitorChartRespVO.TrendData> selectTrendData(@Param("reqVO") BikeChargeMonitorChartReqVO reqVO);

    /**
     * 查询卡片数据（正常设备和异常设备数量）
     *
     * @param reqVO 查询参数
     * @return 卡片数据
     */
    BikeChargeMonitorChartRespVO.CardData selectCardData(@Param("reqVO") BikeChargeMonitorChartReqVO reqVO);
}