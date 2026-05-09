package cn.iocoder.yudao.module.vehiclepass.service.passreport.cyclereport;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportCreateReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportCreateRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.passreport.cyclereport.CycleReportDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.passreport.cyclereport.CycleReportMapper;
import cn.iocoder.yudao.module.vehiclepass.service.passreport.cyclereport.CycleReportService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.module.vehiclepass.constants.common.CalculationConstants.*;

@Slf4j
@Service
public class CycleReportServiceImpl implements CycleReportService {

    private static final int PARALLEL_THRESHOLD = 100;

    @Resource
    private CycleReportMapper cycleReportMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CycleReportCreateRespVO createCycleReport(CycleReportCreateReqVO req) {
        long startMs = System.currentTimeMillis();
        Long tenantId = TenantContextHolder.getTenantId();

        // 1. 校验场站是否属于当前租户
        cycleReportMapper.selectStationName(req.getStationId());

        // 2. 创建初始记录
        CycleReportDO report = new CycleReportDO();
        report.setReportCycle(req.getReportCycle());
        LocalDateTime startDate = req.getStatStartTimeDate();
        LocalDateTime endDate = req.getStatEndTimeDate();
        report.setStatStartTime(startDate);
        report.setStatEndTime(endDate);
        report.setStationId(req.getStationId());
        report.setReportStatus("生成中");
        cycleReportMapper.insert(report);

        try {
            // 3. 统计指标
            Integer enterCount = cycleReportMapper.selectEnterCount(req.getStationId(), startDate, endDate, tenantId);
            Integer leaveCount = cycleReportMapper.selectLeaveCount(req.getStationId(), startDate, endDate, tenantId);
            Integer parkingCount = cycleReportMapper.selectParkingCount(req.getStationId(), endDate, tenantId);
            BigDecimal identifyRate = cycleReportMapper.selectIdentifySuccessRate(req.getStationId(), startDate, endDate, tenantId);
            BigDecimal checkRate = cycleReportMapper.selectCheckSuccessRate(req.getStationId(), startDate, endDate, tenantId);
            BigDecimal abnormalRate = cycleReportMapper.selectAbnormalHandleRate(req.getStationId(), startDate, endDate, tenantId);
            BigDecimal etcRate = cycleReportMapper.selectEtcPassSuccessRate(req.getStationId(), startDate, endDate, tenantId);

            report.setEnterCount(enterCount != null ? enterCount : 0);
            report.setLeaveCount(leaveCount != null ? leaveCount : 0);
            report.setParkingCount(parkingCount != null ? parkingCount : 0);
            report.setIdentifySuccessRate(identifyRate != null ? identifyRate : BigDecimal.ZERO);
            report.setCheckSuccessRate(checkRate != null ? checkRate : BigDecimal.ZERO);
            report.setAbnormalHandleRate(abnormalRate != null ? abnormalRate : BigDecimal.ZERO);
            report.setEtcPassSuccessRate(etcRate != null ? etcRate : BigDecimal.ZERO);
            report.setReportStatus("已生成");
        } catch (Exception e) {
            log.error("生成周期报表失败，reportId: {}", report.getId(), e);
            report.setReportStatus("生成失败");
        }

        // 4. 计算耗时
        long cost = System.currentTimeMillis() - startMs;
        report.setCreateCost((int) (cost / MILLIS_TO_SECONDS));

        // 5. 更新报表（只更新统计字段，避免覆盖自动填充字段）
        LambdaUpdateWrapper<CycleReportDO> updateWrapper = Wrappers.<CycleReportDO>lambdaUpdate()
                .set(CycleReportDO::getEnterCount, report.getEnterCount())
                .set(CycleReportDO::getLeaveCount, report.getLeaveCount())
                .set(CycleReportDO::getParkingCount, report.getParkingCount())
                .set(CycleReportDO::getIdentifySuccessRate, report.getIdentifySuccessRate())
                .set(CycleReportDO::getCheckSuccessRate, report.getCheckSuccessRate())
                .set(CycleReportDO::getAbnormalHandleRate, report.getAbnormalHandleRate())
                .set(CycleReportDO::getEtcPassSuccessRate, report.getEtcPassSuccessRate())
                .set(CycleReportDO::getReportStatus, report.getReportStatus())
                .set(CycleReportDO::getCreateCost, report.getCreateCost())
                .eq(CycleReportDO::getId, report.getId());
        cycleReportMapper.update(null, updateWrapper);

        CycleReportCreateRespVO resp = new CycleReportCreateRespVO();
        resp.setId(report.getId());
        resp.setReportStatus(report.getReportStatus());
        return resp;
    }

    @Override
    public PageResult<CycleReportRespVO> getCycleReportPage(CycleReportPageReqVO reqVO) {
        Long tenantId = TenantContextHolder.getTenantId();
        reqVO.setTenantId(tenantId);
        Page<CycleReportDO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        IPage<CycleReportDO> result = cycleReportMapper.selectPageJoin(page, reqVO);
        List<CycleReportRespVO> list = BeanUtils.toBean(result.getRecords(), CycleReportRespVO.class);
        return new PageResult<>(list, result.getTotal());
    }

    // 导出方法，复用分页查询
    @Override
    public List<CycleReportRespVO> getCycleReportList(CycleReportPageReqVO pageReqVO) {
        Long tenantId = TenantContextHolder.getTenantId();
        pageReqVO.setTenantId(tenantId);
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        Page<CycleReportDO> page = new Page<>(1, pageReqVO.getPageSize());
        IPage<CycleReportDO> result = cycleReportMapper.selectPageJoin(page, pageReqVO);
        return BeanUtils.toBean(result.getRecords(), CycleReportRespVO.class);
    }

    @Override
    public CycleReportRespVO getCycleReport(Long id) {
        CycleReportDO report = cycleReportMapper.selectByIdWithStation(id);
        if (report == null) {
            return null;
        }
        return BeanUtils.toBean(report, CycleReportRespVO.class);
    }

    @Override
    public CycleReportChartRespVO getChart(CycleReportChartReqVO reqVO) {
        Long tenantId = TenantContextHolder.getTenantId();
        LocalDate statTime = reqVO.getStatTime();
        LocalDateTime statDateTime = statTime.atStartOfDay();
        String reportCycle = reqVO.getReportCycle();

        CycleReportChartRespVO resp = new CycleReportChartRespVO();

        // CardData: 从 vp_cycle_report 表汇总
        CycleReportChartRespVO.CardData cardData = new CycleReportChartRespVO.CardData();
        List<CycleReportDO> reports = cycleReportMapper.selectChartByConditions(reqVO.getStationId(), statDateTime, tenantId, reportCycle);

        // 使用并行流进行独立的聚合计算
        int enterCount = reports.parallelStream()
            .mapToInt(report -> report.getEnterCount() != null ? report.getEnterCount() : 0)
            .sum();
        int leaveCount = reports.parallelStream()
            .mapToInt(report -> report.getLeaveCount() != null ? report.getLeaveCount() : 0)
            .sum();
        int parkingCount = reports.parallelStream()
            .mapToInt(report -> report.getParkingCount() != null ? report.getParkingCount() : 0)
            .sum();
        BigDecimal identifyRate = reports.parallelStream()
            .map(CycleReportDO::getIdentifySuccessRate)
            .filter(Objects::nonNull)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal checkRate = reports.parallelStream()
            .map(CycleReportDO::getCheckSuccessRate)
            .filter(Objects::nonNull)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal abnormalRate = reports.parallelStream()
            .map(CycleReportDO::getAbnormalHandleRate)
            .filter(Objects::nonNull)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal etcRate = reports.parallelStream()
            .map(CycleReportDO::getEtcPassSuccessRate)
            .filter(Objects::nonNull)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (!reports.isEmpty()) {
            int size = reports.size();
            identifyRate = identifyRate.divide(BigDecimal.valueOf(size), DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
            checkRate = checkRate.divide(BigDecimal.valueOf(size), DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
            abnormalRate = abnormalRate.divide(BigDecimal.valueOf(size), DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
            etcRate = etcRate.divide(BigDecimal.valueOf(size), DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
        }
        cardData.setEnterCount(enterCount);
        cardData.setLeaveCount(leaveCount);
        cardData.setParkingCount(parkingCount);
        cardData.setIdentifySuccessRate(identifyRate);
        cardData.setCheckSuccessRate(checkRate);
        cardData.setAbnormalHandleRate(abnormalRate);
        cardData.setEtcPassSuccessRate(etcRate);
        resp.setCardData(cardData);

        // MapData
        List<Map<String, Object>> mapDataList = cycleReportMapper.selectMapData(reqVO.getStationId(), statDateTime, tenantId, reportCycle);
        List<CycleReportChartRespVO.MapData> mapData = (mapDataList.size() > PARALLEL_THRESHOLD
                ? mapDataList.parallelStream()
                : mapDataList.stream())
            .map(row -> {
                CycleReportChartRespVO.MapData md = new CycleReportChartRespVO.MapData();
                md.setStationName((String) row.get("stationName"));
                md.setParkingCount(row.get("parkingCount") != null ? ((Number) row.get("parkingCount")).intValue() : 0);
                md.setPassCount(row.get("passCount") != null ? ((Number) row.get("passCount")).intValue() : 0);
                md.setSpaceUseRate(row.get("spaceUseRate") != null ? new BigDecimal(row.get("spaceUseRate").toString()) : BigDecimal.ZERO);
                return md;
            })
            .collect(Collectors.toList());
        resp.setMapData(mapData);

        // BarData
        List<Map<String, Object>> barDataList = cycleReportMapper.selectBarData(reqVO.getStationId(), statDateTime, tenantId, reportCycle);
        List<CycleReportChartRespVO.BarData> barData = (barDataList.size() > PARALLEL_THRESHOLD
                ? barDataList.parallelStream()
                : barDataList.stream())
            .map(row -> {
                CycleReportChartRespVO.BarData bd = new CycleReportChartRespVO.BarData();
                bd.setStationName((String) row.get("stationName"));
                bd.setPassCount(row.get("passCount") != null ? ((Number) row.get("passCount")).intValue() : 0);
                bd.setAbnormalCount(row.get("abnormalCount") != null ? ((Number) row.get("abnormalCount")).intValue() : 0);
                bd.setEtcPassCount(row.get("etcPassCount") != null ? ((Number) row.get("etcPassCount")).intValue() : 0);
                return bd;
            })
            .collect(Collectors.toList());
        resp.setBarData(barData);

        // LineData
        List<Map<String, Object>> lineDataList = cycleReportMapper.selectLineData(reqVO.getStationId(), statDateTime, tenantId, reportCycle);
        List<CycleReportChartRespVO.LineData> lineData = (lineDataList.size() > PARALLEL_THRESHOLD
                ? lineDataList.parallelStream()
                : lineDataList.stream())
            .map(row -> {
                CycleReportChartRespVO.LineData ld = new CycleReportChartRespVO.LineData();
                Object statTimeObj = row.get("statTime");
                ld.setStatTime(statTimeObj != null ? statTimeObj.toString() : "");
                ld.setPassCount(row.get("passCount") != null ? ((Number) row.get("passCount")).intValue() : 0);
                ld.setIdentifySuccessRate(row.get("identifySuccessRate") != null ? new BigDecimal(row.get("identifySuccessRate").toString()) : BigDecimal.ZERO);
                ld.setAbnormalHandleRate(row.get("abnormalHandleRate") != null ? new BigDecimal(row.get("abnormalHandleRate").toString()) : BigDecimal.ZERO);
                ld.setCheckSuccessRate(row.get("checkSuccessRate") != null ? new BigDecimal(row.get("checkSuccessRate").toString()) : BigDecimal.ZERO);
                return ld;
            })
            .collect(Collectors.toList());
        resp.setLineData(lineData);

        // PieData
        List<Map<String, Object>> pieDataList = cycleReportMapper.selectPieData(reqVO.getStationId(), statDateTime, tenantId, reportCycle);
        List<CycleReportChartRespVO.PieData> pieData = (pieDataList.size() > PARALLEL_THRESHOLD
                ? pieDataList.parallelStream()
                : pieDataList.stream())
            .map(row -> {
                CycleReportChartRespVO.PieData pd = new CycleReportChartRespVO.PieData();
                pd.setType((String) row.get("type"));
                pd.setCount(row.get("count") != null ? ((Number) row.get("count")).intValue() : 0);
                return pd;
            })
            .collect(Collectors.toList());
        resp.setPieData(pieData);

        return resp;
    }
}