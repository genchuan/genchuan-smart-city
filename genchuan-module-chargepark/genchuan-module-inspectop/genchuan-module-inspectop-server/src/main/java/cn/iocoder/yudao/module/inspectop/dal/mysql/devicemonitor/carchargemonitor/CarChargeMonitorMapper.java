package cn.iocoder.yudao.module.inspectop.dal.mysql.devicemonitor.carchargemonitor;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.carchargemonitor.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.devicemonitor.carchargemonitor.CarChargeMonitorDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 汽车充电监测 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface CarChargeMonitorMapper extends BaseMapperX<CarChargeMonitorDO> {

    default PageResult<CarChargeMonitorDO> selectPage(CarChargeMonitorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CarChargeMonitorDO>()
                .eqIfPresent(CarChargeMonitorDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(CarChargeMonitorDO::getStationId, reqVO.getStationId())
                .betweenIfPresent(CarChargeMonitorDO::getMonitorTime, reqVO.getMonitorTime())
                .eqIfPresent(CarChargeMonitorDO::getMonitorStatus, reqVO.getMonitorStatus())
                .eqIfPresent(CarChargeMonitorDO::getAlarmStatus, reqVO.getAlarmStatus())
                .betweenIfPresent(CarChargeMonitorDO::getAlarmTime, reqVO.getAlarmTime())
                .eqIfPresent(CarChargeMonitorDO::getAlarmRemark, reqVO.getAlarmRemark())
                .eqIfPresent(CarChargeMonitorDO::getProcessStatus, reqVO.getProcessStatus())
                .eqIfPresent(CarChargeMonitorDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(CarChargeMonitorDO::getLatitude, reqVO.getLatitude())
                .eqIfPresent(CarChargeMonitorDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(CarChargeMonitorDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(CarChargeMonitorDO::getCreator, reqVO.getCreator())
                .eqIfPresent(CarChargeMonitorDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(CarChargeMonitorDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(CarChargeMonitorDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(CarChargeMonitorDO::getId));
    }

    /**
     * 关联查询分页方法
     * 通过关联 station_info 表查询场站名称
     *
     * @param page  MyBatis-Plus分页参数
     * @param reqVO 查询条件
     * @return 包含场站名称的分页结果
     */
    Page<CarChargeMonitorRespVO> selectPageWithJoin(@Param("page") Page<CarChargeMonitorRespVO> page,
                                                    @Param("reqVO") CarChargeMonitorPageReqVO reqVO);


    /**
     * 获取汽车充电监测定位信息
     * 通过关联 station_info 表查询场站名称
     *
     * @param id 监测记录ID
     * @return 定位信息（包含经度、纬度、场站名称）
     */
    CarChargeMonitorLocationRespVO selectLocationById(@Param("id") Long id);


    /**
     * 查询地图数据（充电设备状态分布）
     * 包含设备ID、设备名称、状态、经度、纬度
     *
     * @param reqVO 查询参数
     * @return 地图数据列表
     */
    List<CarChargeMonitorChartRespVO.MapData> selectMapData(@Param("reqVO") CarChargeMonitorChartReqVO reqVO);

    /**
     * 查询趋势数据（按小时统计正常和异常设备数量）
     *
     * @param reqVO 查询参数
     * @return 趋势数据列表
     */
    List<CarChargeMonitorChartRespVO.TrendData> selectTrendData(@Param("reqVO") CarChargeMonitorChartReqVO reqVO);

    /**
     * 查询卡片数据（正常设备和异常设备数量）
     *
     * @param reqVO 查询参数
     * @return 卡片数据
     */
    CarChargeMonitorChartRespVO.CardData selectCardData(@Param("reqVO") CarChargeMonitorChartReqVO reqVO);

}