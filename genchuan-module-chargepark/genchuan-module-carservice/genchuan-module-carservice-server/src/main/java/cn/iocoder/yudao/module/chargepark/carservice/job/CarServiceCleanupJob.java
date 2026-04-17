package cn.iocoder.yudao.module.chargepark.carservice.job;

import cn.iocoder.yudao.framework.tenant.core.job.TenantJob;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.ChargeParkMapDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.NearStationDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.SpacePushDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint.DisputeMediateDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint.SuggestionDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint.UserAppealDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.findcar.PathPlanDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.findcar.SpaceLocationDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.rescue.RescueInfoDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.reserve.ReserveListDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide.ChargeParkMapMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide.NearStationMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide.SpacePushMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.complaint.DisputeMediateMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.complaint.SuggestionMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.complaint.UserAppealMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.findcar.PathPlanMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.findcar.SpaceLocationMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.rescue.RescueInfoMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.reserve.ReserveListMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.xxl.job.core.handler.annotation.XxlJob;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 车务服务-数据保留清理定时任务
 *
 * 客户需求：所有业务表数据保留 3 年（>= 1095 天），超期物理删除。
 * cron 建议：0 0 2 * * ? （每日凌晨 02:00 执行，避开报表 Job）
 *
 * 加 {@link TenantJob} 以多租户上下文逐租户清理。
 *
 * @author carservice
 */
@Slf4j
@Component
public class CarServiceCleanupJob {

    private static final int RETENTION_YEARS = 3;

    @Resource
    private RescueInfoMapper rescueInfoMapper;
    @Resource
    private ReserveListMapper reserveListMapper;
    @Resource
    private ChargeParkMapMapper chargeParkMapMapper;
    @Resource
    private NearStationMapper nearStationMapper;
    @Resource
    private SpacePushMapper spacePushMapper;
    @Resource
    private SpaceLocationMapper spaceLocationMapper;
    @Resource
    private PathPlanMapper pathPlanMapper;
    @Resource
    private SuggestionMapper suggestionMapper;
    @Resource
    private UserAppealMapper userAppealMapper;
    @Resource
    private DisputeMediateMapper disputeMediateMapper;

    @XxlJob("carserviceCleanupJob")
    @TenantJob
    public void cleanup() {
        LocalDateTime threshold = LocalDateTime.now().minusYears(RETENTION_YEARS);
        long startNanos = System.nanoTime();
        long total = 0;
        total += deleteExpired(rescueInfoMapper,      RescueInfoDO::getCreateTime,     threshold, "rescue_info");
        total += deleteExpired(reserveListMapper,     ReserveListDO::getCreateTime,    threshold, "reserve_list");
        total += deleteExpired(chargeParkMapMapper,   ChargeParkMapDO::getCreateTime,  threshold, "charge_park_map");
        total += deleteExpired(nearStationMapper,     NearStationDO::getCreateTime,    threshold, "near_station");
        total += deleteExpired(spacePushMapper,       SpacePushDO::getCreateTime,      threshold, "space_push");
        total += deleteExpired(spaceLocationMapper,   SpaceLocationDO::getCreateTime,  threshold, "space_location");
        total += deleteExpired(pathPlanMapper,        PathPlanDO::getCreateTime,       threshold, "path_plan");
        total += deleteExpired(suggestionMapper,      SuggestionDO::getCreateTime,     threshold, "suggestion");
        total += deleteExpired(userAppealMapper,      UserAppealDO::getCreateTime,     threshold, "user_appeal");
        total += deleteExpired(disputeMediateMapper,  DisputeMediateDO::getCreateTime, threshold, "dispute_mediate");
        long elapsedMs = (System.nanoTime() - startNanos) / 1_000_000;
        log.info("[CleanupJob] 完成, 阈值={}, 共清理={}行, 耗时={}ms", threshold, total, elapsedMs);
    }

    private <T> long deleteExpired(BaseMapper<T> mapper,
                                   SFunction<T, LocalDateTime> createTimeGetter,
                                   LocalDateTime threshold,
                                   String tableLabel) {
        try {
            LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
            wrapper.lt(createTimeGetter, threshold);
            int rows = mapper.delete(wrapper);
            if (rows > 0) {
                log.info("[CleanupJob] {} 清理 {} 行", tableLabel, rows);
            }
            return rows;
        } catch (Exception ex) {
            log.error("[CleanupJob] {} 清理失败", tableLabel, ex);
            return 0;
        }
    }

}
