package cn.iocoder.yudao.module.inspectop.dal.mysql.cyclereport;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo.CycleReportRespVO;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.cyclereport.CycleReportDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface CycleReportMapper extends BaseMapperX<CycleReportDO> {

    // 1. 设备监测统计
    Map<String, Object> selectDeviceMonitorReport(@Param("stationId") Long stationId,
                                                  @Param("statTimeStart") LocalDateTime statTimeStart,
                                                  @Param("statTimeEnd") LocalDateTime statTimeEnd);

    // 2. 巡检任务统计
    Map<String, Object> selectInspectTaskReport(@Param("stationId") Long stationId,
                                                @Param("statTimeStart") LocalDateTime statTimeStart,
                                                @Param("statTimeEnd") LocalDateTime statTimeEnd);

    // 3. 油车占位统计
    Map<String, Object> selectOilMonitorReport(@Param("stationId") Long stationId,
                                               @Param("statTimeStart") LocalDateTime statTimeStart,
                                               @Param("statTimeEnd") LocalDateTime statTimeEnd);

    // 4. 巡检人员统计
    Map<String, Object> selectInspectUserReport(@Param("stationId") Long stationId);

    // 5. 资产信息统计
    Map<String, Object> selectAssetInfoReport(@Param("stationId") Long stationId);

    // 6. 库存预警统计
    Map<String, Object> selectAssetStockReport(@Param("stationId") Long stationId);

    // 7. 查询场站名称
    String selectStationNameById(@Param("stationId") Long stationId);

    // 8. 实时查询分页（不存储，直接统计）- 这个方法可以保留，用于快速页面查询
    List<CycleReportRespVO> selectRealTimePage(@Param("reqVO") CycleReportPageReqVO reqVO);
}