package cn.iocoder.yudao.module.inspectop.dal.mysql.cyclereport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo.CycleReportChartReqVO;
import cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo.CycleReportChartRespVO;
import cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo.CycleReportRespVO;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.cyclereport.CycleReportDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface CycleReportMapper extends BaseMapperX<CycleReportDO> {

    default PageResult<CycleReportDO> selectPage(CycleReportPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CycleReportDO>()
                .eqIfPresent(CycleReportDO::getReportCycle, reqVO.getReportCycle())
                .eqIfPresent(CycleReportDO::getStationId, reqVO.getStationId())
                .likeIfPresent(CycleReportDO::getStationName, reqVO.getStationName())
                .eqIfPresent(CycleReportDO::getStatTimeStart, reqVO.getStatTimeStart())
                .eqIfPresent(CycleReportDO::getStatTimeEnd, reqVO.getStatTimeEnd())
                .eqIfPresent(CycleReportDO::getNormalDeviceNum, reqVO.getNormalDeviceNum())
                .eqIfPresent(CycleReportDO::getAbnormalDeviceNum, reqVO.getAbnormalDeviceNum())
                .eqIfPresent(CycleReportDO::getInspectTaskNum, reqVO.getInspectTaskNum())
                .eqIfPresent(CycleReportDO::getTaskCompleteRate, reqVO.getTaskCompleteRate())
                .eqIfPresent(CycleReportDO::getOilWaitHandleNum, reqVO.getOilWaitHandleNum())
                .eqIfPresent(CycleReportDO::getOilHandleCompleteRate, reqVO.getOilHandleCompleteRate())
                .eqIfPresent(CycleReportDO::getInspectUserOnlineNum, reqVO.getInspectUserOnlineNum())
                .eqIfPresent(CycleReportDO::getAssetNormalNum, reqVO.getAssetNormalNum())
                .eqIfPresent(CycleReportDO::getStockWarnNum, reqVO.getStockWarnNum())
                .eqIfPresent(CycleReportDO::getGenerateStatus, reqVO.getGenerateStatus())
                .betweenIfPresent(CycleReportDO::getGenerateTime, reqVO.getGenerateTime())
                .eqIfPresent(CycleReportDO::getOperator, reqVO.getOperator())
                .eqIfPresent(CycleReportDO::getExportCount, reqVO.getExportCount())
                .eqIfPresent(CycleReportDO::getYearOnYearData, reqVO.getYearOnYearData())
                .eqIfPresent(CycleReportDO::getChainRatioData, reqVO.getChainRatioData())
                .eqIfPresent(CycleReportDO::getCreator, reqVO.getCreator())
                .eqIfPresent(CycleReportDO::getUpdater, reqVO.getUpdater())
                .eqIfPresent(CycleReportDO::getCreateTime, reqVO.getTrendTime())
                .betweenIfPresent(CycleReportDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(CycleReportDO::getId));
    }


// 文档10：CycleReportMapper.java
// 修改原有的统计方法，添加@Param注解并支持null值查询

    // 1. 设备监测统计 - 修改为支持全站统计
    Map<String, Object> selectDeviceMonitorReport(@Param("stationId") Long stationId,
                                                  @Param("statTimeStart") LocalDateTime statTimeStart,
                                                  @Param("statTimeEnd") LocalDateTime statTimeEnd);

    // 2. 巡检任务统计 - 修改为支持全时间统计
    Map<String, Object> selectInspectTaskReport(@Param("statTimeStart") LocalDateTime statTimeStart,
                                                @Param("statTimeEnd") LocalDateTime statTimeEnd);

    // 3. 油车占位统计 - 修改为支持全站统计
    Map<String, Object> selectOilMonitorReport(@Param("stationId") Long stationId,
                                               @Param("statTimeStart") LocalDateTime statTimeStart,
                                               @Param("statTimeEnd") LocalDateTime statTimeEnd);

    // 4. 巡检人员统计 - 修改为支持全站统计
    Map<String, Object> selectInspectUserReport(@Param("stationId") Long stationId);

    // 5. 资产信息统计 - 修改为支持全站统计
    Map<String, Object> selectAssetInfoReport(@Param("stationId") Long stationId);

    // 6. 库存预警统计 - 修改为支持全站统计
    Map<String, Object> selectAssetStockReport(@Param("stationId") Long stationId);

    // 7. 查询场站名称
    String selectStationNameById(@Param("stationId") Long stationId);

    // 8. 实时查询分页（不存储，直接统计）- 这个方法可以保留，用于快速页面查询
    List<CycleReportRespVO> selectRealTimePage(@Param("reqVO") CycleReportPageReqVO reqVO);

    /**
     * 根据周期、场站、时间判断报表是否存在
     * 用于实现更新或插入逻辑
     */
    default CycleReportDO selectByUniqueCondition(String reportCycle, Long stationId,
                                                  LocalDateTime statTimeStart, LocalDateTime statTimeEnd) {
        return selectOne(new LambdaQueryWrapperX<CycleReportDO>()
                .eq(CycleReportDO::getReportCycle, reportCycle)
                .eq(CycleReportDO::getStationId, stationId)
                .eq(CycleReportDO::getStatTimeStart, statTimeStart)
                .eq(CycleReportDO::getStatTimeEnd, statTimeEnd)
                .orderByDesc(CycleReportDO::getId) // 获取最新的一条
                .last("LIMIT 1")
        );
    }

    /**
     * 查询地图数据
     */
    List<CycleReportChartRespVO.MapData> selectMapData(@Param("reqVO") CycleReportChartReqVO reqVO);

    /**
     * 查询柱状图数据
     */
    List<CycleReportChartRespVO.BarData> selectBarData(@Param("reqVO") CycleReportChartReqVO reqVO);

    /**
     * 查询折线图数据
     */
    List<CycleReportChartRespVO.LineData> selectLineData(@Param("reqVO") CycleReportChartReqVO reqVO);

    // 在 CycleReportMapper.java 中添加
    /**
     * 增加报表导出次数（原子操作）
     *
     * @param id 报表主键ID
     * @return 更新行数
     */
    default int incrementExportCount(Long id) {
        if (id == null) {
            return 0;
        }
        return this.update(null,
                new LambdaUpdateWrapper<CycleReportDO>()
                        .setSql("export_count = export_count + 1")
                        .eq(CycleReportDO::getId, id));
    }
}