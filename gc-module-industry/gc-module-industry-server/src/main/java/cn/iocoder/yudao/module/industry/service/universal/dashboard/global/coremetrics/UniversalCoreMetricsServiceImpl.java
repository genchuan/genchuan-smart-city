package cn.iocoder.yudao.module.industry.service.universal.dashboard.global.coremetrics;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremetrics.vo.UniversalCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremetrics.vo.UniversalCoreMetricsRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.global.coremetrics.UniversalCoreMetricsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 通用大屏核心指标看板 Service 实现类
 * <p>
 * 功能说明：
 * 1. 实现 UniversalCoreMetricsService 接口中的业务逻辑方法
 * 2. 调用对应的 Mapper 进行数据库查询
 * 3. 提供统一的 Service 层接口给 Controller 使用
 */
@Service
@Validated
public class UniversalCoreMetricsServiceImpl implements UniversalCoreMetricsService {

    // 注入对应的 Mapper 对象，用于数据库操作
    @Resource
    private UniversalCoreMetricsMapper universalCoreMetricsMapper;

    /**
     * 查询通用大屏核心指标看板数据
     *
     * @param reqVO 查询条件 VO 对象
     * @return UniversalCoreMetricsRespVO 查询结果 VO 对象
     */
    @Override
    public UniversalCoreMetricsRespVO getUniversalCoreMetrics(UniversalCoreMetricsQueryReqVO reqVO) {
        // 1. 当前周期
        UniversalCoreMetricsRespVO current =
                universalCoreMetricsMapper.getUniversalCoreMetrics(reqVO);

        if (current == null) {
            return new UniversalCoreMetricsRespVO();
        }

        // 2. 环比（上周期）
        UniversalCoreMetricsQueryReqVO lastPeriodReq = buildLastPeriodReq(reqVO);
        UniversalCoreMetricsRespVO lastPeriod =
                universalCoreMetricsMapper.getUniversalCoreMetrics(lastPeriodReq);

        // 3. 同比（去年同期）
        UniversalCoreMetricsQueryReqVO lastYearReq = buildLastYearReq(reqVO);
        UniversalCoreMetricsRespVO lastYear =
                universalCoreMetricsMapper.getUniversalCoreMetrics(lastYearReq);

        // 4. 计算增长率
        current.setMomGrowth(
                calcGrowthRate(current.getTotalCount(),
                        lastPeriod == null ? null : lastPeriod.getTotalCount())
        );

        current.setYoyGrowth(
                calcGrowthRate(current.getTotalCount(),
                        lastYear == null ? null : lastYear.getTotalCount())
        );

        //5.回填时间周期和地区
        current.setStatCycle(reqVO.getStatCycle());
        current.setRegionCode(reqVO.getRegionShortCode());


        return current;
    }

    /**
     * 计算增长率
     *
     * @param current 当前值
     * @param base    对比基准值（上期 / 去年）
     * @return 增长率（百分比），保留 2 位小数
     */
    private BigDecimal calcGrowthRate(Integer current, Integer base) {
        if (base == null || base == 0 || current == null) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(current - base)
                .divide(BigDecimal.valueOf(base), 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);
    }
    /**
     * 构建上周期请求 VO（环比）
     * 逻辑示例：假设 statCycle = "202509"（YYYYMM），上周期 = "202508"
     */
    private UniversalCoreMetricsQueryReqVO buildLastPeriodReq(UniversalCoreMetricsQueryReqVO currentReq) {
        String currentCycle = currentReq.getStatCycle();
        int year = Integer.parseInt(currentCycle.substring(0, 4));
        int month = Integer.parseInt(currentCycle.substring(4, 6));

        month--; // 上周期
        if (month == 0) {
            month = 12;
            year--;
        }

        String lastCycle = String.format("%04d%02d", year, month);
        UniversalCoreMetricsQueryReqVO lastReq = new UniversalCoreMetricsQueryReqVO();
        lastReq.setRegionShortCode(currentReq.getRegionShortCode());
        lastReq.setStatCycle(lastCycle);
        return lastReq;
    }

    /**
     * 构建去年同期请求 VO（同比）
     */
    private UniversalCoreMetricsQueryReqVO buildLastYearReq(UniversalCoreMetricsQueryReqVO currentReq) {
        String currentCycle = currentReq.getStatCycle();
        int year = Integer.parseInt(currentCycle.substring(0, 4));
        String lastYearCycle = String.format("%04d%s", year - 1, currentCycle.substring(4, 6));

        UniversalCoreMetricsQueryReqVO lastYearReq = new UniversalCoreMetricsQueryReqVO();
        lastYearReq.setRegionShortCode(currentReq.getRegionShortCode());
        lastYearReq.setStatCycle(lastYearCycle);
        return lastYearReq;
    }

}
