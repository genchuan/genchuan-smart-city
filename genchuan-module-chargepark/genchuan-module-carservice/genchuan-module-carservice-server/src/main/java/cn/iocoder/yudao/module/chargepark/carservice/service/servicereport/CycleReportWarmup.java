package cn.iocoder.yudao.module.chargepark.carservice.service.servicereport;

/**
 * @deprecated 周期报表改为"冻结快照"模式(cycle_report 表),查询走 DB,不再需要 Redis 预热。
 *             本文件保留为空,等后续清理 PR 删除。
 */
@Deprecated
public final class CycleReportWarmup {
    private CycleReportWarmup() {}
}
