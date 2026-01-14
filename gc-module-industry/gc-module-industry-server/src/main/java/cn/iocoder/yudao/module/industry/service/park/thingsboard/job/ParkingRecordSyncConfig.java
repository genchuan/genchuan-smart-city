package cn.iocoder.yudao.module.industry.service.park.thingsboard.job;

import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.industry.service.park.thingsboard.ParkingRecordSyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * 停车记录同步配置
 *
 * @author zhucongquan
 */
@Slf4j
@Configuration
@EnableScheduling
public class ParkingRecordSyncConfig {

    @Resource
    private ParkingRecordSyncService parkingRecordSyncService;

    /**
     * 每5分钟执行一次同步任务
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void syncParkingRecordsTask() {
        log.info("开始定时同步停车记录...");

        // 在定时任务中设置租户ID为1
        try {
            // 使用TenantContextHolder设置租户ID
            TenantContextHolder.setTenantId(1L);

            parkingRecordSyncService.syncParkingRecords();
            log.info("定时同步停车记录完成");
        } catch (Exception e) {
            log.error("定时同步停车记录失败: {}", e.getMessage(), e);
        } finally {
            // 清除租户上下文
            TenantContextHolder.clear();
        }
    }
}