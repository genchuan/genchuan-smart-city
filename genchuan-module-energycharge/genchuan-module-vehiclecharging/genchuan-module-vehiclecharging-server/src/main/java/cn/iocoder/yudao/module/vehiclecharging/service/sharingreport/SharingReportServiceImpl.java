package cn.iocoder.yudao.module.vehiclecharging.service.sharingreport;

import cn.hutool.core.date.DateRange;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.settlementbill.SettlementBillDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sharingreport.SharingReportDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.sharingreport.SharingReportMapper;
import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.settlementbill.SettlementBillMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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
        // 1. 尝试根据精确条件（报表类型、时间范围、合作方精确匹配）查找是否已存在对应的报表记录
        LambdaQueryWrapper<SharingReportDO> exactWrapper = new LambdaQueryWrapper<>();
        exactWrapper.eq(SharingReportDO::getReportType, pageReqVO.getReportType())
                .eq(SharingReportDO::getTimeRange, pageReqVO.getTimeRange());
        if (StrUtil.isNotBlank(pageReqVO.getCooperator())) {
            exactWrapper.eq(SharingReportDO::getCooperator, pageReqVO.getCooperator());
        } else {
            exactWrapper.isNull(SharingReportDO::getCooperator);
        }
        SharingReportDO existingReport = sharingReportMapper.selectOne(exactWrapper);

        // 2. 如果不存在，则实时生成并存储
        if (existingReport == null) {
            generateAndSaveReport(pageReqVO);
        }

        // 3. 分页查询（支持合作方模糊查询）
        Page<SharingReportDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        LambdaQueryWrapper<SharingReportDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SharingReportDO::getReportType, pageReqVO.getReportType())
                .eq(SharingReportDO::getTimeRange, pageReqVO.getTimeRange());
        if (StrUtil.isNotBlank(pageReqVO.getCooperator())) {
            wrapper.like(SharingReportDO::getCooperator, pageReqVO.getCooperator());
        }
        wrapper.orderByDesc(SharingReportDO::getCreateTime);
        Page<SharingReportDO> reportPage = sharingReportMapper.selectPage(page, wrapper);

        // 4. 转换返回
        List<SharingReportPageRespVO> list = BeanUtils.toBean(reportPage.getRecords(), SharingReportPageRespVO.class);
// 不需要手动转换 createTime，框架会自动处理
        return new PageResult<>(list, reportPage.getTotal());
    }

    /**
     * 根据请求参数生成报表并存储
     */
    private void generateAndSaveReport(SharingReportPageReqVO reqVO) {
        // 2. 从 settlement_bill 表聚合统计数据
        LambdaQueryWrapper<SettlementBillDO> billWrapper = new LambdaQueryWrapper<>();
        billWrapper.eq(SettlementBillDO::getSettlementCycle, reqVO.getTimeRange()); // 直接匹配周期
        if (StrUtil.isNotBlank(reqVO.getCooperator())) {
            billWrapper.eq(SettlementBillDO::getCooperator, reqVO.getCooperator());
        }
        List<SettlementBillDO> bills = settlementBillMapper.selectList(billWrapper);
        BigDecimal totalSettlement = bills.stream()
                .map(SettlementBillDO::getSettlementAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalSharing = bills.stream()
                .map(SettlementBillDO::getSharingAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        int billCount = bills.size();

        // 3. 生成报表编号和名称
        String reportCode = generateReportCode();
        String reportName = generateReportName(reqVO.getReportType(), reqVO.getTimeRange(), reqVO.getCooperator());

        // 4. 插入 sharing_report 表
        SharingReportDO reportDO = new SharingReportDO();
        reportDO.setReportCode(reportCode);
        reportDO.setReportName(reportName);
        reportDO.setReportType(reqVO.getReportType());
        reportDO.setTimeRange(reqVO.getTimeRange());
        reportDO.setCooperator(reqVO.getCooperator());
        reportDO.setTotalSettlementAmount(totalSettlement);
        reportDO.setTotalSharingAmount(totalSharing);
        reportDO.setBillCount(billCount);
        sharingReportMapper.insert(reportDO);
        log.info("生成并存储分账报表，reportCode={}", reportCode);
    }

    /**
     * 解析时间范围，返回 [startDate, endDate)，endDate 为下个周期的开始
     */
    private Date[] parseTimeRange(String reportType, String timeRange) {
        try {
            switch (reportType) {
                case "日":
                    DateTime day = DateUtil.parseDate(timeRange);
                    return new Date[]{day, DateUtil.offsetDay(day, 1)};
                case "周":
                    DateTime weekStart = DateUtil.beginOfWeek(DateUtil.parseDate(timeRange));
                    return new Date[]{weekStart, DateUtil.offsetWeek(weekStart, 1)};
                case "月":
                    DateTime monthStart = DateUtil.beginOfMonth(DateUtil.parseDate(timeRange + "-01"));
                    return new Date[]{monthStart, DateUtil.offsetMonth(monthStart, 1)};
                case "季":
                    // timeRange 格式如 "2025-Q1"
                    String[] parts = timeRange.split("-Q");
                    int year = Integer.parseInt(parts[0]);
                    int quarter = Integer.parseInt(parts[1]);
                    DateTime quarterStart = DateUtil.parseDate(year + "-" + (quarter * 3 - 2) + "-01");
                    return new Date[]{quarterStart, DateUtil.offsetMonth(quarterStart, 3)};
                case "半年":
                    // 简化：上半年或下半年，格式如 "2025-H1"
                    String[] halfParts = timeRange.split("-H");
                    int halfYear = Integer.parseInt(halfParts[0]);
                    int half = Integer.parseInt(halfParts[1]);
                    if (half == 1) {
                        DateTime halfStart = DateUtil.parseDate(halfYear + "-01-01");
                        return new Date[]{halfStart, DateUtil.offsetMonth(halfStart, 6)};
                    } else {
                        DateTime halfStart = DateUtil.parseDate(halfYear + "-07-01");
                        return new Date[]{halfStart, DateUtil.offsetMonth(halfStart, 6)};
                    }
                case "年":
                    DateTime yearStart = DateUtil.parseDate(timeRange + "-01-01");
                    return new Date[]{yearStart, DateUtil.offsetYear(yearStart, 1)};
                case "自定义":
                    // 自定义时间范围，timeRange 格式为 "开始时间戳~结束时间戳"
                    String[] custom = timeRange.split("~");
                    long startSec = Long.parseLong(custom[0]);
                    long endSec = Long.parseLong(custom[1]);
                    return new Date[]{new Date(startSec * 1000), new Date(endSec * 1000)};
                default:
                    return null;
            }
        } catch (Exception e) {
            log.error("解析时间范围失败，reportType={}, timeRange={}", reportType, timeRange, e);
            return null;
        }
    }

    private String generateReportCode() {
        return "SRP-" + System.currentTimeMillis();
    }

    private String generateReportName(String reportType, String timeRange, String cooperator) {
        // 简单生成，可按需美化
        if (StrUtil.isNotBlank(cooperator)) {
            return timeRange + " " + cooperator + " " + reportType + "分账报表";
        } else {
            return timeRange + " " + reportType + "分账报表";
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createCustomReport(SharingReportCustomCreateReqVO reqVO) {
        // 1. 转换时间戳为 LocalDateTime
        LocalDateTime startDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(reqVO.getTimeStart()), ZoneId.systemDefault());
        LocalDateTime endDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(reqVO.getTimeEnd()), ZoneId.systemDefault());

        // 2. 查询 settlement_bill 表统计数据
        LambdaQueryWrapper<SettlementBillDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(SettlementBillDO::getCreateTime, startDateTime)
                .le(SettlementBillDO::getCreateTime, endDateTime);
        if (StrUtil.isNotBlank(reqVO.getCooperator())) {
            wrapper.eq(SettlementBillDO::getCooperator, reqVO.getCooperator());
        }
        List<SettlementBillDO> bills = settlementBillMapper.selectList(wrapper);
        BigDecimal totalSettlement = bills.stream()
                .map(SettlementBillDO::getSettlementAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalSharing = bills.stream()
                .map(SettlementBillDO::getSharingAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        int billCount = bills.size();

        // 3. 生成报表编号和名称
        String reportCode = "SRP-" + System.currentTimeMillis();
        // timeRange 格式：开始时间戳~结束时间戳
        String timeRange = reqVO.getTimeStart() + "~" + reqVO.getTimeEnd();

        // 4. 插入 sharing_report 表
        SharingReportDO reportDO = new SharingReportDO();
        reportDO.setReportCode(reportCode);
        reportDO.setReportName(reqVO.getReportName());
        reportDO.setReportType("自定义");
        reportDO.setTimeRange(timeRange);
        reportDO.setCooperator(reqVO.getCooperator()); // 若为空则为 null
        reportDO.setTotalSettlementAmount(totalSettlement);
        reportDO.setTotalSharingAmount(totalSharing);
        reportDO.setBillCount(billCount);
        sharingReportMapper.insert(reportDO);
        log.info("生成自定义报表成功，reportCode={}", reportCode);
        return reportDO.getId();
    }

    @Override
    public SharingReportDO getSharingReport(Long id) {
        return sharingReportMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long recreateCustomReport(Long id) {
        // 1. 查询原报表
        SharingReportDO report = sharingReportMapper.selectById(id);
        if (report == null || report.getDeleted()) {
            throw new RuntimeException("报表不存在或已删除");
        }
        // 2. 仅支持自定义报表
        if (!"自定义".equals(report.getReportType())) {
            throw new RuntimeException("仅支持重新生成自定义报表");
        }
        // 3. 解析时间范围（格式：开始时间戳~结束时间戳）
        String timeRange = report.getTimeRange();
        String[] times = timeRange.split("~");
        if (times.length != 2) {
            throw new RuntimeException("报表时间范围格式错误");
        }
        long startSec = Long.parseLong(times[0]);
        long endSec = Long.parseLong(times[1]);
        LocalDateTime start = LocalDateTime.ofInstant(Instant.ofEpochSecond(startSec), ZoneId.systemDefault());
        LocalDateTime end = LocalDateTime.ofInstant(Instant.ofEpochSecond(endSec), ZoneId.systemDefault());

        // 4. 重新统计结算单数据
        LambdaQueryWrapper<SettlementBillDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(SettlementBillDO::getCreateTime, start)
                .le(SettlementBillDO::getCreateTime, end);
        if (report.getCooperator() != null) {
            wrapper.eq(SettlementBillDO::getCooperator, report.getCooperator());
        }
        List<SettlementBillDO> bills = settlementBillMapper.selectList(wrapper);
        BigDecimal totalSettlement = bills.stream()
                .map(SettlementBillDO::getSettlementAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalSharing = bills.stream()
                .map(SettlementBillDO::getSharingAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        int billCount = bills.size();

        // 5. 更新原报表
        SharingReportDO updateObj = new SharingReportDO();
        updateObj.setId(id);
        updateObj.setTotalSettlementAmount(totalSettlement);
        updateObj.setTotalSharingAmount(totalSharing);
        updateObj.setBillCount(billCount);
        sharingReportMapper.updateById(updateObj);
        log.info("重新生成自定义报表成功，id={}", id);
        return id;
    }

}