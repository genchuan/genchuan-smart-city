package cn.iocoder.yudao.module.vehiclecharging.service.sharingreport;

import cn.hutool.core.util.StrUtil;
import cn.idev.excel.EasyExcel;
import cn.idev.excel.ExcelWriter;
import cn.idev.excel.write.metadata.WriteSheet;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.SettlementBillPrintVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.SettlementBillRespVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.settlementbill.SettlementBillDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sharingreport.SharingReportDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.sharingreport.SharingReportMapper;
import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.settlementbill.SettlementBillMapper;
import cn.iocoder.yudao.module.vehiclecharging.framework.common.utils.TimeRangeParser;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.annotation.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.*;

/**
 * 分账报表 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Slf4j
@Validated
public class SharingReportServiceImpl implements SharingReportService {

    @Resource
    private SharingReportMapper sharingReportMapper;

    @Resource
    private SettlementBillMapper settlementBillMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageResult<SharingReportPageRespVO> getSharingReportPage(SharingReportPageReqVO pageReqVO) {
        // 分页对象
        Page<SharingReportDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        LambdaQueryWrapper<SharingReportDO> queryWrapper = new LambdaQueryWrapper<>();

        // 1. 如果 reportType 不为空，则添加精确匹配条件
        if (StrUtil.isNotBlank(pageReqVO.getReportType())) {
            queryWrapper.eq(SharingReportDO::getReportType, pageReqVO.getReportType());
        }

        // 2. 如果 timeRange 不为空，则添加精确匹配条件
        if (StrUtil.isNotBlank(pageReqVO.getTimeRange())) {
            queryWrapper.eq(SharingReportDO::getTimeRange, pageReqVO.getTimeRange());
        }

        // 3. 合作方模糊查询
        if (StrUtil.isNotBlank(pageReqVO.getCooperator())) {
            queryWrapper.like(SharingReportDO::getCooperator, pageReqVO.getCooperator());
        }

        // 4. 排序
        queryWrapper.orderByDesc(SharingReportDO::getCreateTime);

        // 5. 分页查询
        Page<SharingReportDO> pageResult = sharingReportMapper.selectPage(page, queryWrapper);
        List<SharingReportPageRespVO> list = BeanUtils.toBean(pageResult.getRecords(), SharingReportPageRespVO.class);
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public void exportBatchReport(List<Long> ids, HttpServletResponse response) throws IOException {
        if (CollectionUtils.isEmpty(ids)) {
            throw new ServiceException(SHARING_REPORT_ID_REQUIRED);
        }
        List<SharingReportDO> reports = sharingReportMapper.selectBatchIds(ids);
        if (reports.isEmpty()) {
            throw new ServiceException(SHARING_REPORT_NOT_EXISTS);
        }
        List<SharingReportExportVO> exportList = BeanUtils.toBean(reports, SharingReportExportVO.class);
        // 设置响应头
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("分账报表批量导出.xls", "UTF-8"));
        // 使用 EasyExcel 写入单个 sheet
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        WriteSheet sheet = EasyExcel.writerSheet(0, "报表列表").head(SharingReportExportVO.class).build();
        excelWriter.write(exportList, sheet);
        excelWriter.finish();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createSharingReport(SharingReportCustomCreateReqVO reqVO) {
        // 1. 参数校验
        if (StrUtil.isBlank(reqVO.getReportName())) {
            throw new ServiceException(REPORT_NAME_REQUIRED);
        }
        if (reqVO.getTimeStart() == null || reqVO.getTimeEnd() == null) {
            throw new ServiceException(ILLEGAL_TIME_FORMAT);
        }

        // 2. 转换时间戳（秒级）为 LocalDateTime
        LocalDateTime start = LocalDateTime.ofInstant(Instant.ofEpochSecond(reqVO.getTimeStart()), ZoneId.systemDefault());
        LocalDateTime end = LocalDateTime.ofInstant(Instant.ofEpochSecond(reqVO.getTimeEnd()), ZoneId.systemDefault());

        // 3. 查询结算单
        LambdaQueryWrapper<SettlementBillDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(SettlementBillDO::getCreateTime, start)
                .le(SettlementBillDO::getCreateTime, end);
        if (StrUtil.isNotBlank(reqVO.getCooperator())) {
            wrapper.eq(SettlementBillDO::getCooperator, reqVO.getCooperator());
        }
        List<SettlementBillDO> bills = settlementBillMapper.selectList(wrapper);

        // 4. 聚合统计
        BigDecimal totalSettlement = bills.stream()
                .map(SettlementBillDO::getSettlementAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalSharing = bills.stream()
                .map(SettlementBillDO::getSharingAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        int billCount = bills.size();

        // 5. 生成报表编号和名称
        String reportCode = "SRP-" + System.currentTimeMillis();
        String timeRange = reqVO.getTimeStart() + "~" + reqVO.getTimeEnd();

        // 6. 插入报表
        SharingReportDO reportDO = new SharingReportDO();
        reportDO.setReportCode(reportCode);
        reportDO.setReportName(reqVO.getReportName());
        reportDO.setReportType("自定义");
        reportDO.setTimeRange(timeRange);
        reportDO.setCooperator(reqVO.getCooperator());
        reportDO.setTotalSettlementAmount(totalSettlement);
        reportDO.setTotalSharingAmount(totalSharing);
        reportDO.setBillCount(billCount);
        sharingReportMapper.insert(reportDO);
        log.info("生成自定义报表成功，reportCode={}, id={}", reportCode, reportDO.getId());

        return reportDO.getId();
    }

    @Override
    public void exportSingleReport(Long id, HttpServletResponse response) throws IOException {
        // 1. 查询报表主信息
        SharingReportDO report = sharingReportMapper.selectById(id);
        if (report == null) {
            throw new ServiceException(SHARING_REPORT_NOT_EXISTS);
        }
        // 2. 解析时间范围，获取起止日期（timeRange格式：yyyy-MM-dd~yyyy-MM-dd）
        String timeRange = report.getTimeRange();
        if (StrUtil.isBlank(timeRange) || !timeRange.contains("~")) {
            throw new ServiceException(ILLEGAL_TIME_FORMAT);
        }
        String[] dates = timeRange.split("~");
        if (dates.length != 2) {
            throw new ServiceException(ILLEGAL_TIME_FORMAT);
        }
        String startDateTime = dates[0].trim() + " 00:00:00";
        String endDateTime = dates[1].trim() + " 23:59:59";
        TimeRangeParser.TimeRangeParsed parsed = TimeRangeParser.parse(startDateTime + "~" + endDateTime);
        if (parsed == null) {
            throw new ServiceException(ILLEGAL_TIME_FORMAT);
        }
        LocalDateTime start = parsed.getStart();
        LocalDateTime end = parsed.getEnd();
        // 3. 查询关联的结算单明细
        List<SettlementBillDO> bills = settlementBillMapper.selectList(new LambdaQueryWrapper<SettlementBillDO>()
                .ge(SettlementBillDO::getCreateTime, start)
                .le(SettlementBillDO::getCreateTime, end)
                .eq(StrUtil.isNotBlank(report.getCooperator()), SettlementBillDO::getCooperator, report.getCooperator())
                .eq(SettlementBillDO::getDeleted, false));
        // 4. 转换为导出 VO
        SharingReportExportVO reportVO = BeanUtils.toBean(report, SharingReportExportVO.class);
        List<SettlementBillRespVO> billVOList = BeanUtils.toBean(bills, SettlementBillRespVO.class);

        // 5. 设置响应头
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("分账报表_" + report.getReportCode() + ".xls", "UTF-8"));

        // 6. 使用 EasyExcel 写入多个 sheet
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        WriteSheet sheet1 = EasyExcel.writerSheet(0, "报表概要").head(SharingReportExportVO.class).build();
        excelWriter.write(Collections.singletonList(reportVO), sheet1);
        WriteSheet sheet2 = EasyExcel.writerSheet(1, "结算单明细").head(SettlementBillRespVO.class).build();
        excelWriter.write(billVOList, sheet2);
        excelWriter.finish();
    }

    @Override
    public Map<String, Object> getPrintData(Long id) {
        // 1. 查询报表主信息
        SharingReportDO report = sharingReportMapper.selectById(id);
        if (report == null) {
            throw new ServiceException(SHARING_REPORT_NOT_EXISTS);
        }

        // 2. 解析 timeRange（格式：yyyy-MM-dd~yyyy-MM-dd）
        String timeRange = report.getTimeRange();
        if (StrUtil.isBlank(timeRange) || !timeRange.contains("~")) {
            throw new ServiceException(ILLEGAL_TIME_FORMAT);
        }
        String[] dates = timeRange.split("~");
        if (dates.length != 2) {
            throw new ServiceException(ILLEGAL_TIME_FORMAT);
        }
        LocalDate startDate = LocalDate.parse(dates[0].trim());
        LocalDate endDate = LocalDate.parse(dates[1].trim());
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(23, 59, 59);

        // 3. 查询关联的结算单明细
        List<SettlementBillDO> bills = settlementBillMapper.selectList(
                new LambdaQueryWrapper<SettlementBillDO>()
                        .ge(SettlementBillDO::getCreateTime, start)
                        .le(SettlementBillDO::getCreateTime, end)
                        .eq(StrUtil.isNotBlank(report.getCooperator()), SettlementBillDO::getCooperator, report.getCooperator())
                        .eq(SettlementBillDO::getDeleted, false)
        );

        // 4. 转换为打印专用 VO
        SharingReportPrintVO reportVO = BeanUtils.toBean(report, SharingReportPrintVO.class);
        List<SettlementBillPrintVO> billVOList = BeanUtils.toBean(bills, SettlementBillPrintVO.class);

        // 5. 组装返回数据
        Map<String, Object> printData = new HashMap<>();
        printData.put("report", reportVO);
        printData.put("details", billVOList);
        return printData;
    }

    @Override
    public SharingReportSummaryRespVO getChartSummary(SharingReportChartReqVO reqVO) {
        TimeRangeParser.ReportRange range = TimeRangeParser.parseReport(reqVO.getReportType(), reqVO.getTimeRange());
        if (range == null) throw new ServiceException(ILLEGAL_TIME_FORMAT);
        LocalDateTime start = range.getStart();
        LocalDateTime end = range.getEnd();
        String groupPattern = range.getGroupPattern();

        // 总体统计
        Map<String, Object> totalStats = sharingReportMapper.selectTotalStats(start, end);
        SharingReportSummaryRespVO resp = new SharingReportSummaryRespVO();
        resp.setTotalSettlementAmount((BigDecimal) totalStats.get("totalSettlementAmount"));
        resp.setTotalSharingAmount((BigDecimal) totalStats.get("totalSharingAmount"));
        resp.setTotalBillCount(((Number) totalStats.get("totalBillCount")).intValue());

        // 折线图
        resp.setLineData(sharingReportMapper.selectLineData(start, end, groupPattern));
        // 柱状图
        resp.setBarData(sharingReportMapper.selectBarData(start, end));
        // 饼图（先查金额，再计算占比）
        List<SharingReportSummaryRespVO.PieData> pieData = sharingReportMapper.selectPieData(start, end);
        BigDecimal totalSharing = resp.getTotalSharingAmount();
        for (SharingReportSummaryRespVO.PieData p : pieData) {
            if (totalSharing.compareTo(BigDecimal.ZERO) != 0) {
                p.setValue(p.getAmount().multiply(BigDecimal.valueOf(100)).divide(totalSharing, 2, RoundingMode.HALF_UP));
            } else {
                p.setValue(BigDecimal.ZERO);
            }
        }
        resp.setPieData(pieData);

        // 同比环比（需实现辅助方法）
        resp.setYoyRatio(calculateYoy(start, end, reqVO.getReportType()));
        resp.setMomRatio(calculateMom(start, end, reqVO.getReportType()));

        return resp;
    }

    @Override
    public SharingReportTimeTrendRespVO getTimeTrend(SharingReportTimeTrendReqVO reqVO) {
        TimeRangeParser.ReportRange range = TimeRangeParser.parseReport(reqVO.getReportType(), reqVO.getTimeRange());
        if (range == null) throw new ServiceException(ILLEGAL_TIME_FORMAT);
        List<SharingReportTimeTrendRespVO.TimeTrendData> list = sharingReportMapper.selectTimeTrend(range.getStart(), range.getEnd(), range.getGroupPattern());
        SharingReportTimeTrendRespVO resp = new SharingReportTimeTrendRespVO();
        resp.setList(list);
        return resp;
    }

    @Override
    public SharingReportCooperatorTimeAmountRespVO getCooperatorTimeAmount(SharingReportCooperatorTimeAmountReqVO reqVO) {
        TimeRangeParser.ReportRange range = TimeRangeParser.parseReport(reqVO.getReportType(), reqVO.getTimeRange());
        if (range == null) throw new ServiceException(ILLEGAL_TIME_FORMAT);
        List<Map<String, Object>> raw = sharingReportMapper.selectCooperatorTimeAmount(range.getStart(), range.getEnd(), range.getGroupPattern());
        Map<String, Map<String, BigDecimal>> dateMap = new LinkedHashMap<>();
        for (Map<String, Object> row : raw) {
            String date = (String) row.get("date");
            String cooperator = (String) row.get("cooperator");
            BigDecimal amount = (BigDecimal) row.get("amount");
            dateMap.computeIfAbsent(date, k -> new HashMap<>()).put(cooperator, amount);
        }
        List<SharingReportCooperatorTimeAmountRespVO.CooperatorTimeData> list = new ArrayList<>();
        for (Map.Entry<String, Map<String, BigDecimal>> entry : dateMap.entrySet()) {
            SharingReportCooperatorTimeAmountRespVO.CooperatorTimeData data = new SharingReportCooperatorTimeAmountRespVO.CooperatorTimeData();
            data.setDate(entry.getKey());
            data.setCooperatorData(entry.getValue());
            list.add(data);
        }
        SharingReportCooperatorTimeAmountRespVO resp = new SharingReportCooperatorTimeAmountRespVO();
        resp.setList(list);
        return resp;
    }

    @Override
    public SharingReportCooperatorRatioRespVO getCooperatorRatio(SharingReportCooperatorRatioReqVO reqVO) {
        TimeRangeParser.ReportRange range = TimeRangeParser.parseReport(reqVO.getReportType(), reqVO.getTimeRange());
        if (range == null) throw new ServiceException(ILLEGAL_TIME_FORMAT);
        List<SharingReportCooperatorRatioRespVO.CooperatorRatioData> list = sharingReportMapper.selectCooperatorRatio(range.getStart(), range.getEnd());
        BigDecimal total = list.stream().map(SharingReportCooperatorRatioRespVO.CooperatorRatioData::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        for (SharingReportCooperatorRatioRespVO.CooperatorRatioData d : list) {
            if (total.compareTo(BigDecimal.ZERO) != 0) {
                d.setValue(d.getAmount().multiply(BigDecimal.valueOf(100)).divide(total, 2, RoundingMode.HALF_UP));
            } else {
                d.setValue(BigDecimal.ZERO);
            }
        }
        SharingReportCooperatorRatioRespVO resp = new SharingReportCooperatorRatioRespVO();
        resp.setList(list);
        return resp;
    }

    @Override
    public SharingReportTimeCountRespVO getTimeCount(SharingReportTimeCountReqVO reqVO) {
        TimeRangeParser.ReportRange range = TimeRangeParser.parseReport(reqVO.getReportType(), reqVO.getTimeRange());
        if (range == null) throw new ServiceException(ILLEGAL_TIME_FORMAT);
        LocalDateTime start = range.getStart();
        LocalDateTime end = range.getEnd();

        Map<String, Object> totalStats = sharingReportMapper.selectTotalStats(start, end);
        SharingReportTimeCountRespVO resp = new SharingReportTimeCountRespVO();
        SharingReportTimeCountRespVO.TimeStat timeStat = new SharingReportTimeCountRespVO.TimeStat();
        timeStat.setDailyCount(sharingReportMapper.countGroupBy(start, end, "%Y-%m-%d"));
        timeStat.setWeeklyCount(sharingReportMapper.countGroupBy(start, end, "%Y-%u"));
        timeStat.setMonthlyCount(sharingReportMapper.countGroupBy(start, end, "%Y-%m"));
        resp.setTimeStat(timeStat);
        SharingReportTimeCountRespVO.TotalStat totalStat = new SharingReportTimeCountRespVO.TotalStat();
        totalStat.setTotalAmount((BigDecimal) totalStats.get("totalSharingAmount"));
        totalStat.setTotalCount(((Number) totalStats.get("totalBillCount")).intValue());
        totalStat.setYoy(calculateYoy(start, end, reqVO.getReportType()));
        totalStat.setMom(calculateMom(start, end, reqVO.getReportType()));
        resp.setTotalStat(totalStat);
        return resp;
    }

    private BigDecimal calculateYoy(LocalDateTime start, LocalDateTime end, String reportType) {
        LocalDateTime lastYearStart = start.minusYears(1);
        LocalDateTime lastYearEnd = end.minusYears(1);
        BigDecimal current = sharingReportMapper.selectTotalSharingAmount(start, end);
        BigDecimal last = sharingReportMapper.selectTotalSharingAmount(lastYearStart, lastYearEnd);
        if (last.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
        return current.subtract(last).multiply(BigDecimal.valueOf(100)).divide(last, 2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateMom(LocalDateTime start, LocalDateTime end, String reportType) {
        long days = java.time.temporal.ChronoUnit.DAYS.between(start, end) + 1;
        LocalDateTime lastStart = start.minusDays(days);
        LocalDateTime lastEnd = end.minusDays(days);
        BigDecimal current = sharingReportMapper.selectTotalSharingAmount(start, end);
        BigDecimal last = sharingReportMapper.selectTotalSharingAmount(lastStart, lastEnd);
        if (last.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
        return current.subtract(last).multiply(BigDecimal.valueOf(100)).divide(last, 2, RoundingMode.HALF_UP);
    }

}