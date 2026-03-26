package cn.iocoder.yudao.module.facility.service.manhole.manholemonitor;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholemonitor.ManholeMonitorDO;
import cn.iocoder.yudao.module.facility.dal.mysql.manhole.manholemonitor.ManholeMonitorMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.COVER_NOT_EXISTS;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.MONITOR_NOT_EXISTS;

/**
 * 窨井盖监测 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ManholeMonitorServiceImpl implements ManholeMonitorService {

    @Resource
    private ManholeMonitorMapper monitorMapper;

    @Override
    public Long createMonitor(ManholeMonitorSaveReqVO createReqVO) {
        // 插入
        ManholeMonitorDO monitor = BeanUtils.toBean(createReqVO, ManholeMonitorDO.class);
        monitorMapper.insert(monitor);
        // 返回
        return monitor.getId();
    }

    @Override
    public void updateMonitor(ManholeMonitorSaveReqVO updateReqVO) {
        // 校验存在
        validateMonitorExists(updateReqVO.getId());
        // 更新
        ManholeMonitorDO updateObj = BeanUtils.toBean(updateReqVO, ManholeMonitorDO.class);
        monitorMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonitor(Long id) {
        // 校验存在
        validateMonitorExists(id);
        // 删除
        monitorMapper.deleteById(id);
    }

    private void validateMonitorExists(Long id) {
        if (monitorMapper.selectById(id) == null) {
            throw exception(MONITOR_NOT_EXISTS);
        }
    }

    @Override
    public ManholeMonitorDO getMonitor(String id) {
        return monitorMapper.selectById(id);
    }

    @Override
    public PageResult<ManholeMonitorDO> getMonitorPage(ManholeMonitorPageReqVO pageReqVO) {
        return monitorMapper.selectPage(pageReqVO);
    }


    @Override
    public PageResult<ManholeCoverRealTimePageRespVO> getRealTimePage(ManholeCoverRealTimePageReqVO reqVO) {
        // 创建分页对象
        IPage<ManholeCoverRealTimePageRespVO> mpPage = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());

        // 执行分页查询（MyBatis Plus 分页插件会自动拦截并添加 LIMIT/OFFSET）
        IPage<ManholeCoverRealTimePageRespVO> result = monitorMapper.selectRealTimePage(mpPage, reqVO);

        // 转换为 PageResult 返回
        return new PageResult<>(result.getRecords(), result.getTotal());
    }

    /**
     * 按井盖编号查询详情（用于钻取弹窗）
     */
//    @Override
//    public ManholeCoverRealTimePageRespVO getManholeDetailByCoverNo(String coverNo) {
//        // 1. 参数校验
//        if (coverNo == null || coverNo.trim().isEmpty()) {
//            throw exception(MONITOR_NOT_EXISTS);
//        }
//        // 2. 查询详情
//        ManholeCoverRealTimePageRespVO detail = monitorMapper.selectManholeDetailByCoverNo(coverNo);
//        // 3. 无数据兜底
//        if (detail == null) {
//            throw exception(MONITOR_NOT_EXISTS);
//        }
//        return detail;
//    }

    /**
     * 批量更新监测状态
     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public Integer batchUpdateMonitorStatus(List<Long> coverIds, String monitorStatus) {
//        // 1. 参数校验
//        if (coverIds == null || coverIds.isEmpty()) {
//            throw new IllegalArgumentException("请选择要操作的井盖");
//        }
//        // 3. 批量更新监测状态（同步更新操作人和更新时间）
//        int count = monitorMapper.batchUpdateMonitorStatus(coverIds, monitorStatus);
//
//        // 4. 同步更新关联设备状态
//        String deviceOnlineStatus = "运行中".equals(monitorStatus) ? "在线" : "离线";
//        monitorMapper.batchUpdateDeviceStatus(coverIds, deviceOnlineStatus);
//
//        return count;
//    }

//    @Override
//    public ManholeMonitorStatsRespVO get24HourStats(Long id) {
//
//        ManholeMonitorStatsRespVO stats = monitorMapper.select24HourStats(id);
//
//        if (stats == null) {
//            stats = new ManholeMonitorStatsRespVO();
//            stats.setAvgTiltAngle(BigDecimal.ZERO);
//            stats.setMaxTiltAngle(BigDecimal.ZERO);
//            stats.setMinTiltAngle(BigDecimal.ZERO);
//            stats.setAvgVibration(BigDecimal.ZERO);
//            stats.setMaxVibration(BigDecimal.ZERO);
//            stats.setMinVibration(BigDecimal.ZERO);
//        }
//
//        return stats;
//    }
//
//    @Override
//    public List<ManholeMonitorHourTrendVO> get24HourTrend(Long id) {
//
//        List<ManholeMonitorHourTrendVO> trendList = monitorMapper.select24HourTrend(id);
//
//        if (trendList == null) {
//            return new ArrayList<>();
//        }
//
//        return trendList;
//    }

    /**
     * 查询窨井盖预警监测列表（基于 sys_warn 表）
     */
    @Override
    public PageResult<ManholeMonitorWarningRespVO> getWarningMonitorPage(ManholeMonitorWarningPageReqVO pageReqVO) {

        // 2. 查询分页数据
        List<ManholeMonitorWarningRespVO> list = monitorMapper.selectWarningMonitorPageData(pageReqVO);

        // 3. 查询总条数
        Long total = monitorMapper.selectWarningMonitorPageCount(pageReqVO);

        // 4. 组装分页响应结果
        return new PageResult<ManholeMonitorWarningRespVO>(
              list, total
        );
    }

    @Override
    public ManholeCoverRealTimeDetailRespVO getRealTimeDetail(String coverId, String tenantId) {
        // 查询详情
        ManholeCoverRealTimeDetailRespVO detail = monitorMapper.selectRealTimeDetail(coverId, tenantId);
        if (detail == null) {
            throw exception(MONITOR_NOT_EXISTS);
        }
        return detail;
    }

    @Override
    public ManholeCoverRealTimeTrendRespVO getRealTimeTrend(String coverId, ManholeCoverRealTimeTrendReqVO reqVO) {
        // 1. 查询井盖基础信息
        ManholeCoverRealTimeTrendRespVO result = monitorMapper.selectCoverBaseInfo(coverId, reqVO.getTenantId());
        if (result == null) {
            throw exception(COVER_NOT_EXISTS);
        }

        // 2. 设置指标类型及名称
        result.setIndicatorType(reqVO.getIndicatorType());
        result.setIndicatorTypeName(getIndicatorTypeName(reqVO.getIndicatorType()));

        // 3. 构建趋势图表数据
        TrendChartRespVO trendChart = new TrendChartRespVO();
        // 3.1 获取小时聚合数据
        List<Map<String, Object>> hourlyData = monitorMapper.selectHourlyMonitorData(
                coverId, reqVO.getTenantId(), reqVO.getIndicatorType());
        // 3.2 生成X/Y轴（补全24小时，无数据则填0）
        List<String> xAxis = new ArrayList<>();
        List<BigDecimal> yAxis = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            String hour = String.format("%02d", i);
            xAxis.add(hour + "时");
            // 查找当前小时的数值，无则填0
            BigDecimal value = BigDecimal.ZERO;
            for (Map<String, Object> data : hourlyData) {
                if (hour.equals(data.get("hour"))) {
                    value = (BigDecimal) Optional.ofNullable(data.get("indicator_value")).orElse(BigDecimal.ZERO);
                    break;
                }
            }
            yAxis.add(value);
        }
        trendChart.setXAxis(xAxis);
        trendChart.setYAxis(yAxis);

        // 3.3 设置单位和阈值
        // 3.3 设置单位和阈值（重点修复：传递tenantId参数）
        trendChart.setUnit(getIndicatorUnit(reqVO.getIndicatorType()));
        // 修复：传入第三个参数tenantId
        BigDecimal threshold = monitorMapper.selectIndicatorThreshold(
                coverId, reqVO.getIndicatorType(), reqVO.getTenantId());
        trendChart.setThresholdValue(Optional.ofNullable(threshold).orElse(BigDecimal.ZERO));

        // 4. 组装结果
        result.setTrendChart(trendChart);
        return result;
    }

    // 指标类型名称映射
    private String getIndicatorTypeName(Integer type) {
        return switch (type) {
            case 0 -> "倾斜角度";
            case 1 -> "位移距离";
            case 2 -> "井内水位";
            default -> "未知指标";
        };
    }

    // 指标单位映射
    private String getIndicatorUnit(Integer type) {
        return switch (type) {
            case 0 -> "°";
            case 1, 2 -> "cm";
            default -> "";
        };
    }


}