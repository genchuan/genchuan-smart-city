package cn.iocoder.yudao.module.inspectop.dal.mysql.sharechargemonitor;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.sharechargemonitor.ShareChargeMonitorDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.sharechargemonitor.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 共享充电监测 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface ShareChargeMonitorMapper extends BaseMapperX<ShareChargeMonitorDO> {

    default PageResult<ShareChargeMonitorDO> selectPage(ShareChargeMonitorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ShareChargeMonitorDO>()
                .eqIfPresent(ShareChargeMonitorDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(ShareChargeMonitorDO::getStationId, reqVO.getStationId())
                .betweenIfPresent(ShareChargeMonitorDO::getMonitorTime, reqVO.getMonitorTime())
                .eqIfPresent(ShareChargeMonitorDO::getMonitorStatus, reqVO.getMonitorStatus())
                .eqIfPresent(ShareChargeMonitorDO::getAlarmStatus, reqVO.getAlarmStatus())
                .betweenIfPresent(ShareChargeMonitorDO::getAlarmTime, reqVO.getAlarmTime())
                .eqIfPresent(ShareChargeMonitorDO::getAlarmRemark, reqVO.getAlarmRemark())
                .eqIfPresent(ShareChargeMonitorDO::getProcessStatus, reqVO.getProcessStatus())
                .eqIfPresent(ShareChargeMonitorDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(ShareChargeMonitorDO::getLatitude, reqVO.getLatitude())
                .eqIfPresent(ShareChargeMonitorDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(ShareChargeMonitorDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(ShareChargeMonitorDO::getCreator, reqVO.getCreator())
                .eqIfPresent(ShareChargeMonitorDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(ShareChargeMonitorDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(ShareChargeMonitorDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(ShareChargeMonitorDO::getId));
    }

    /**
     * 关联查询分页方法
     * 通过关联 station_info 表查询场站名称
     *
     * @param page  MyBatis-Plus分页参数
     * @param reqVO 查询条件
     * @return 包含场站名称的分页结果
     */
    Page<ShareChargeMonitorRespVO> selectPageWithJoin(@Param("page") Page<ShareChargeMonitorRespVO> page,
                                                      @Param("reqVO") ShareChargeMonitorPageReqVO reqVO);
    /**
     * 获取共享充电监测定位信息
     * 通过关联 station_info 表查询场站名称
     *
     * @param id 监测记录ID
     * @return 定位信息（包含经度、纬度、场站名称）
     */
    ShareChargeMonitorLocationRespVO selectLocationById(@Param("id") Long id);

    /**
     * 查询地图数据（充电设备状态分布）
     * 包含设备ID、设备名称、状态、经度、纬度
     *
     * @param reqVO 查询参数
     * @return 地图数据列表
     */
    List<ShareChargeMonitorChartRespVO.MapData> selectMapData(@Param("reqVO") ShareChargeMonitorChartReqVO reqVO);

    /**
     * 查询趋势数据（按小时统计正常和异常设备数量）
     *
     * @param reqVO 查询参数
     * @return 趋势数据列表
     */
    List<ShareChargeMonitorChartRespVO.TrendData> selectTrendData(@Param("reqVO") ShareChargeMonitorChartReqVO reqVO);

    /**
     * 查询卡片数据（正常设备和异常设备数量）
     *
     * @param reqVO 查询参数
     * @return 卡片数据
     */
    ShareChargeMonitorChartRespVO.CardData selectCardData(@Param("reqVO") ShareChargeMonitorChartReqVO reqVO);
}