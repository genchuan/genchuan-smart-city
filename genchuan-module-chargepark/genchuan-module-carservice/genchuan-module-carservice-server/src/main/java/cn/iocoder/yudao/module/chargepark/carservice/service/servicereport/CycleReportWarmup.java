package cn.iocoder.yudao.module.chargepark.carservice.service.servicereport;

import cn.iocoder.yudao.framework.tenant.core.util.TenantUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportChartReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportPageReqVO;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 周期报表冷启动 + 周期预热。
 *
 * 策略:
 * - Cache TTL = 10 分钟(#600s);
 * - 启动后立即预热 6 个常用周期(日报/周报/月报/季报/半年报/年报),不含自定义报表(时段无固定默认);
 * - 每 8 分钟重跑一次预热(略短于 TTL,保证缓存永远温热,用户无论何时进入页面都直接命中);
 * - 后台线程跑,不阻塞启动;必须 TenantUtils.execute 包裹(MyBatis 租户拦截器需要 tenantId)。
 */
@Slf4j
@Component
public class CycleReportWarmup {

    private static final Long DEFAULT_TENANT_ID = 1L;

    /** 预热集:6 个常用周期(自定义报表不纳入,因时段不可固定) */
    private static final List<String> CYCLES = Arrays.asList("日报", "周报", "月报", "季报", "半年报", "年报");

    /** 每 8 分钟重预热一次(略短于 10 分钟 TTL) */
    private static final long REFRESH_PERIOD_MINUTES = 8;

    @Resource
    private CycleReportService cycleReportService;

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread t = new Thread(r, "cycle-report-warmup");
        t.setDaemon(true);
        return t;
    });

    @EventListener(ApplicationReadyEvent.class)
    public void schedule() {
        scheduler.scheduleAtFixedRate(this::doWarmup, 0, REFRESH_PERIOD_MINUTES, TimeUnit.MINUTES);
    }

    @PreDestroy
    public void shutdown() {
        scheduler.shutdownNow();
    }

    private void doWarmup() {
        long t0 = System.currentTimeMillis();
        int ok = 0, fail = 0;
        for (String cycle : CYCLES) {
            try {
                TenantUtils.execute(DEFAULT_TENANT_ID, () -> {
                    // /page 只传 reportCycle,Service 内部 resolveDefaultRange 自动取当期窗口
                    CycleReportPageReqVO page = new CycleReportPageReqVO();
                    page.setReportCycle(cycle);
                    cycleReportService.pageCycleReport(page);

                    // /chart 必须完整入参(@Valid)
                    LocalDateTime[] range = currentRange(cycle);
                    CycleReportChartReqVO chart = new CycleReportChartReqVO();
                    chart.setReportCycle(cycle);
                    chart.setStatStartTime(range[0]);
                    chart.setStatEndTime(range[1]);
                    cycleReportService.chartCycleReport(chart);
                });
                ok++;
            } catch (Exception ex) {
                fail++;
                log.warn("[周期报表] 预热 {} 失败: {}", cycle, ex.getMessage());
            }
        }
        log.info("[周期报表] 预热一轮完成 ok={} fail={}, 耗时 {}ms", ok, fail, System.currentTimeMillis() - t0);
    }

    /** 取 cycle 对应的"当期"完整窗口(必须与 Service.resolveDefaultRange 口径一致,否则 cache key 错位) */
    private LocalDateTime[] currentRange(String cycle) {
        LocalDate today = LocalDate.now();
        switch (cycle) {
            case "日报":
                return new LocalDateTime[]{today.atStartOfDay(), today.atTime(23, 59, 59)};
            case "周报": {
                LocalDate m = today.with(DayOfWeek.MONDAY);
                return new LocalDateTime[]{m.atStartOfDay(), m.plusDays(6).atTime(23, 59, 59)};
            }
            case "月报": {
                LocalDate f = today.withDayOfMonth(1);
                return new LocalDateTime[]{f.atStartOfDay(), f.plusMonths(1).minusDays(1).atTime(23, 59, 59)};
            }
            case "季报": {
                int qStart = ((today.getMonthValue() - 1) / 3) * 3 + 1;
                LocalDate f = LocalDate.of(today.getYear(), qStart, 1);
                return new LocalDateTime[]{f.atStartOfDay(), f.plusMonths(3).minusDays(1).atTime(23, 59, 59)};
            }
            case "半年报": {
                int hStart = today.getMonthValue() <= 6 ? 1 : 7;
                LocalDate f = LocalDate.of(today.getYear(), hStart, 1);
                return new LocalDateTime[]{f.atStartOfDay(), f.plusMonths(6).minusDays(1).atTime(23, 59, 59)};
            }
            case "年报":
            default: {
                LocalDate f = LocalDate.of(today.getYear(), 1, 1);
                return new LocalDateTime[]{f.atStartOfDay(), LocalDate.of(today.getYear(), 12, 31).atTime(23, 59, 59)};
            }
        }
    }
}
