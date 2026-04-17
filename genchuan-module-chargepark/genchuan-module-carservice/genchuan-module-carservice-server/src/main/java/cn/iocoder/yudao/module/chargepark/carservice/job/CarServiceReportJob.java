package cn.iocoder.yudao.module.chargepark.carservice.job;

import cn.iocoder.yudao.framework.tenant.core.job.TenantJob;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo.TimeReportRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.enums.ReportPeriodEnum;
import cn.iocoder.yudao.module.chargepark.carservice.service.decision.ServiceOpReportService;
import com.xxl.job.core.handler.annotation.XxlJob;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * 车务服务-报表自动生成定时任务
 *
 * 6 个时间尺度，每个尺度对应一个 @XxlJob handler，需在 XXL-Job admin 配置 cron 调度：
 *
 * <pre>
 *   carserviceDailyReportJob       0 5 0 * * ?    每日 00:05
 *   carserviceWeeklyReportJob      0 10 0 ? * 2   每周一 00:10
 *   carserviceMonthlyReportJob     0 15 0 1 * ?   每月 1 日 00:15
 *   carserviceQuarterlyReportJob   0 20 0 1 1,4,7,10 ?   每季度第一月 1 日 00:20
 *   carserviceSemiAnnualReportJob  0 25 0 1 1,7 ?  每半年第一月 1 日 00:25
 *   carserviceAnnualReportJob      0 30 0 1 1 ?   每年 1 月 1 日 00:30
 * </pre>
 *
 * 加 {@link TenantJob} 注解，框架会循环每个租户上下文执行一次，自动隔离。
 *
 * @author carservice
 */
@Slf4j
@Component
public class CarServiceReportJob {

    @Resource
    private ServiceOpReportService serviceOpReportService;

    @XxlJob("carserviceDailyReportJob")
    @TenantJob
    public void daily() {
        executeAndLog(ReportPeriodEnum.DAILY);
    }

    @XxlJob("carserviceWeeklyReportJob")
    @TenantJob
    public void weekly() {
        executeAndLog(ReportPeriodEnum.WEEKLY);
    }

    @XxlJob("carserviceMonthlyReportJob")
    @TenantJob
    public void monthly() {
        executeAndLog(ReportPeriodEnum.MONTHLY);
    }

    @XxlJob("carserviceQuarterlyReportJob")
    @TenantJob
    public void quarterly() {
        executeAndLog(ReportPeriodEnum.QUARTERLY);
    }

    @XxlJob("carserviceSemiAnnualReportJob")
    @TenantJob
    public void semiAnnual() {
        executeAndLog(ReportPeriodEnum.SEMI_ANNUAL);
    }

    @XxlJob("carserviceAnnualReportJob")
    @TenantJob
    public void annual() {
        executeAndLog(ReportPeriodEnum.ANNUAL);
    }

    private void executeAndLog(ReportPeriodEnum period) {
        long startNanos = System.nanoTime();
        try {
            TimeReportRespVO report = serviceOpReportService.generateReport(period, LocalDate.now());
            long elapsedMs = (System.nanoTime() - startNanos) / 1_000_000;
            log.info("[{}-Job] 生成成功, 模块数={}, 耗时={}ms", period.getLabel(), report.getModules().size(), elapsedMs);
        } catch (Exception ex) {
            log.error("[{}-Job] 生成失败", period.getLabel(), ex);
            throw ex;
        }
    }

}
