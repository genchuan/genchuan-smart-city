package cn.iocoder.yudao.module.chargepark.marketop.service.decisionanalysis.cyclereport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.cyclereport.vo.CycleReportChartReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.cyclereport.vo.CycleReportChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.cyclereport.vo.CycleReportCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.cyclereport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.decisionanalysis.CycleReportDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.decisionanalysis.CycleReportMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.CYCLE_REPORT_NOT_EXISTS;

@Service
@Validated
public class CycleReportServiceImpl implements CycleReportService {

    @Resource
    private CycleReportMapper cycleReportMapper;

    @Override
    public PageResult<CycleReportDO> getPage(CycleReportPageReqVO reqVO) {
        return cycleReportMapper.selectPage(reqVO);
    }

    @Override
    public CycleReportDO get(Long id) {
        return cycleReportMapper.selectById(id);
    }

    @Override
    public Long create(CycleReportCreateReqVO reqVO) {
        long startTime = System.currentTimeMillis();

        // 1. 构建报表记录
        CycleReportDO report = BeanUtils.toBean(reqVO, CycleReportDO.class);
        report.setGenerateStatus("生成中");
        cycleReportMapper.insert(report);

        // 2. 聚合核心指标（基于13张业务表实时统计）
        LocalDateTime statStart = reqVO.getStatStartTime();
        LocalDateTime statEnd = reqVO.getStatEndTime();
        Long tenantId = reqVO.getTenantId();

        Integer activityCount = cycleReportMapper.selectActivityCount(statStart, statEnd, tenantId);
        Integer joinUserCount = cycleReportMapper.selectJoinUserCount(statStart, statEnd, tenantId);
        Integer lotteryCount = cycleReportMapper.selectLotteryCount(statStart, statEnd, tenantId);
        Integer winCount = cycleReportMapper.selectWinCount(statStart, statEnd, tenantId);
        Integer couponSendCount = cycleReportMapper.selectCouponSendCount(statStart, statEnd, tenantId);
        Integer couponVerifyCount = cycleReportMapper.selectCouponVerifyCount(statStart, statEnd, tenantId);
        Integer cardOrderCount = cycleReportMapper.selectCardOrderCount(statStart, statEnd, tenantId);
        BigDecimal revenue = cycleReportMapper.selectRevenue(statStart, statEnd, tenantId);
        Integer exchangeCount = cycleReportMapper.selectExchangeCount(statStart, statEnd, tenantId);
        Integer totalStock = cycleReportMapper.selectTotalStock(tenantId);
        Integer warnStockCount = cycleReportMapper.selectWarnStockCount(tenantId);

        // 3. 计算率值指标
        BigDecimal winningRate = (lotteryCount != null && lotteryCount > 0)
                ? BigDecimal.valueOf(winCount).divide(BigDecimal.valueOf(lotteryCount), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))
                : BigDecimal.ZERO;
        BigDecimal couponVerifyRate = (couponSendCount != null && couponSendCount > 0)
                ? BigDecimal.valueOf(couponVerifyCount).divide(BigDecimal.valueOf(couponSendCount), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))
                : BigDecimal.ZERO;

        // 4. 更新报表记录
        report.setActivityCount(activityCount != null ? activityCount : 0);
        report.setJoinUserCount(joinUserCount != null ? joinUserCount : 0);
        report.setLotteryCount(lotteryCount != null ? lotteryCount : 0);
        report.setWinningRate(winningRate);
        report.setCouponSendCount(couponSendCount != null ? couponSendCount : 0);
        report.setCouponVerifyRate(couponVerifyRate);
        report.setCardOrderCount(cardOrderCount != null ? cardOrderCount : 0);
        report.setRevenue(revenue != null ? revenue : BigDecimal.ZERO);
        report.setExchangeCount(exchangeCount != null ? exchangeCount : 0);
        report.setTotalStock(totalStock != null ? totalStock : 0);
        report.setWarnStockCount(warnStockCount != null ? warnStockCount : 0);
        report.setGenerateStatus("已生成");
        report.setGenerateTime(LocalDateTime.now());
        report.setGenerateCost((int) (System.currentTimeMillis() - startTime));
        report.setExportCount(0);
        cycleReportMapper.updateById(report);

        return report.getId();
    }

    @Override
    public CycleReportChartRespVO getChart(CycleReportChartReqVO reqVO) {
        // 图表数据基于业务表实时聚合
        CycleReportChartRespVO respVO = new CycleReportChartRespVO();

        // 卡片数据
        CycleReportChartRespVO.CardData cardData = new CycleReportChartRespVO.CardData();
        LocalDateTime statStart = reqVO.getStatStartTime();
        LocalDateTime statEnd = reqVO.getStatEndTime();
        Long tenantId = reqVO.getTenantId();

        Integer activityCount = cycleReportMapper.selectActivityCount(statStart, statEnd, tenantId);
        Integer joinUserCount = cycleReportMapper.selectJoinUserCount(statStart, statEnd, tenantId);
        Integer lotteryCount = cycleReportMapper.selectLotteryCount(statStart, statEnd, tenantId);
        Integer winCount = cycleReportMapper.selectWinCount(statStart, statEnd, tenantId);
        Integer couponSendCount = cycleReportMapper.selectCouponSendCount(statStart, statEnd, tenantId);
        Integer couponVerifyCount = cycleReportMapper.selectCouponVerifyCount(statStart, statEnd, tenantId);
        Integer cardOrderCount = cycleReportMapper.selectCardOrderCount(statStart, statEnd, tenantId);
        BigDecimal revenue = cycleReportMapper.selectRevenue(statStart, statEnd, tenantId);
        Integer exchangeCount = cycleReportMapper.selectExchangeCount(statStart, statEnd, tenantId);
        Integer totalStock = cycleReportMapper.selectTotalStock(tenantId);
        Integer warnStockCount = cycleReportMapper.selectWarnStockCount(tenantId);

        BigDecimal winningRate = (lotteryCount != null && lotteryCount > 0)
                ? BigDecimal.valueOf(winCount).divide(BigDecimal.valueOf(lotteryCount), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))
                : BigDecimal.ZERO;
        BigDecimal couponVerifyRate = (couponSendCount != null && couponSendCount > 0)
                ? BigDecimal.valueOf(couponVerifyCount).divide(BigDecimal.valueOf(couponSendCount), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))
                : BigDecimal.ZERO;

        cardData.setActivityCount(activityCount != null ? activityCount : 0);
        cardData.setJoinUserCount(joinUserCount != null ? joinUserCount : 0);
        cardData.setLotteryCount(lotteryCount != null ? lotteryCount : 0);
        cardData.setWinningRate(winningRate);
        cardData.setCouponSendCount(couponSendCount != null ? couponSendCount : 0);
        cardData.setCouponVerifyRate(couponVerifyRate);
        cardData.setCardOrderCount(cardOrderCount != null ? cardOrderCount : 0);
        cardData.setRevenue(revenue != null ? revenue : BigDecimal.ZERO);
        cardData.setExchangeCount(exchangeCount != null ? exchangeCount : 0);
        cardData.setTotalStock(totalStock != null ? totalStock : 0);
        cardData.setWarnStockCount(warnStockCount != null ? warnStockCount : 0);
        respVO.setCardData(cardData);

        // ========== lineData: 近30天按日期统计数量 ==========
        LocalDateTime lineStart = LocalDateTime.now().minusDays(30);
        Map<String, Map<String, Object>> lineDataMap = buildLineDataMap(
                "活动参与趋势", cycleReportMapper.selectPointActivityCountByDay(lineStart),
                "抽奖里趋势", cycleReportMapper.selectPointLotteryCountByDay(lineStart),
                "优惠券发放趋势", cycleReportMapper.selectReceiveRecordCountByDay(lineStart),
                "订单量趋势", cycleReportMapper.selectCardOrderCountByDay(lineStart),
//                "exchange_order", cycleReportMapper.selectExchangeOrderCountByDay(lineStart),
                "库存趋势", cycleReportMapper.selectStockControlCountByDay(lineStart)
        );
        List<CycleReportChartRespVO.ChartLineData> lineDataList = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Map.Entry<String, Map<String, Object>> entry : lineDataMap.entrySet()) {
            CycleReportChartRespVO.ChartLineData lineData = new CycleReportChartRespVO.ChartLineData();
            lineData.setName(entry.getKey());
            // 补全30天日期
            Map<String, Object> dayMap = entry.getValue();
            List<CycleReportChartRespVO.ChartLineItemData> items = new ArrayList<>();
            for (int i = 29; i >= 0; i--) {
                String date = today.minusDays(i).toString();
                CycleReportChartRespVO.ChartLineItemData item = new CycleReportChartRespVO.ChartLineItemData();
                item.setDate(date);
                item.setCount(dayMap.getOrDefault(date, 0) instanceof Number ? ((Number) dayMap.getOrDefault(date, 0)).intValue() : 0);
                items.add(item);
            }
            lineData.setData(items);
            lineDataList.add(lineData);
        }
        respVO.setLineData(lineDataList);

        // ========== barData: 按type分类统计数量 ==========
        List<CycleReportChartRespVO.ChartBarData> barDataList = new ArrayList<>();
        barDataList.add(buildBarData("活动类型分布", cycleReportMapper.selectActivityConfigTypeCount()));
//        barDataList.add(buildBarData("point_activity", cycleReportMapper.selectPointActivityTypeCount()));
        barDataList.add(buildBarData("优惠券类型分布", cycleReportMapper.selectCouponMgmtTypeCount()));
        barDataList.add(buildBarData("奖品类型分布", cycleReportMapper.selectPrizeMgmtTypeCount()));
        barDataList.add(buildBarData("卡种类型分布", cycleReportMapper.selectCardConfigTypeCount()));
        barDataList.add(buildBarData("兑换类目订单分布", cycleReportMapper.selectExchangeCategoryTypeCount()));
        respVO.setBarData(barDataList);

        // ========== pieData: 按type统计占比 ==========
        List<CycleReportChartRespVO.ChartPieData> pieDataList = new ArrayList<>();
        pieDataList.add(buildPieData("规则类型占比", cycleReportMapper.selectRuleConfigTypeCount()));
        pieDataList.add(buildPieData("券包类型占比", cycleReportMapper.selectPackageConfigTypeCount()));
        pieDataList.add(buildPieData("配置类型占比", cycleReportMapper.selectActivityConfigTypeCountForPie()));
        respVO.setPieData(pieDataList);

        return respVO;
    }

    @Override
    public List<CycleReportDO> getList(CycleReportPageReqVO reqVO) {
        return cycleReportMapper.selectList(
                new LambdaQueryWrapperX<CycleReportDO>()
                        .eqIfPresent(CycleReportDO::getReportCycle, reqVO.getReportCycle())
                        .geIfPresent(CycleReportDO::getStatStartTime, reqVO.getStatStartTime())
                        .leIfPresent(CycleReportDO::getStatEndTime, reqVO.getStatEndTime())
                        .eqIfPresent(CycleReportDO::getGenerateStatus, reqVO.getGenerateStatus())
                        .eqIfPresent(CycleReportDO::getTenantId, reqVO.getTenantId())
                        .orderByDesc(CycleReportDO::getId));
    }

    private CycleReportDO validateExists(Long id) {
        CycleReportDO report = cycleReportMapper.selectById(id);
        if (report == null) {
            throw exception(CYCLE_REPORT_NOT_EXISTS);
        }
        return report;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Map<String, Object>> buildLineDataMap(Object... nameAndData) {
        Map<String, Map<String, Object>> result = new LinkedHashMap<>();
        for (int i = 0; i < nameAndData.length; i += 2) {
            String name = (String) nameAndData[i];
            List<Map<String, Object>> data = (List<Map<String, Object>>) nameAndData[i + 1];
            Map<String, Object> dayMap = new LinkedHashMap<>();
            for (Map<String, Object> row : data) {
                String date = row.get("date").toString();
                int count = ((Number) row.get("count")).intValue();
                dayMap.put(date, count);
            }
            result.put(name, dayMap);
        }
        return result;
    }

    private CycleReportChartRespVO.ChartBarData buildBarData(String name, List<Map<String, Object>> typeCountList) {
        CycleReportChartRespVO.ChartBarData barData = new CycleReportChartRespVO.ChartBarData();
        barData.setName(name);
        List<CycleReportChartRespVO.ChartDataItem> items = new ArrayList<>();
        for (Map<String, Object> m : typeCountList) {
            CycleReportChartRespVO.ChartDataItem item = new CycleReportChartRespVO.ChartDataItem();
            item.setType(m.get("type") != null ? m.get("type").toString() : "unknown");
            item.setCount(((Number) m.get("count")).intValue());
            items.add(item);
        }
        barData.setData(items);
        return barData;
    }

    private CycleReportChartRespVO.ChartPieData buildPieData(String name, List<Map<String, Object>> typeCountList) {
        CycleReportChartRespVO.ChartPieData pieData = new CycleReportChartRespVO.ChartPieData();
        pieData.setName(name);
        int total = typeCountList.stream()
                .mapToInt(m -> ((Number) m.get("count")).intValue())
                .sum();
        List<CycleReportChartRespVO.ChartRatioItem> items = new ArrayList<>();
        for (Map<String, Object> m : typeCountList) {
            CycleReportChartRespVO.ChartRatioItem item = new CycleReportChartRespVO.ChartRatioItem();
            item.setType(m.get("type") != null ? m.get("type").toString() : "unknown");
            int count = ((Number) m.get("count")).intValue();
            BigDecimal ratio = total > 0
                    ? BigDecimal.valueOf(count).divide(BigDecimal.valueOf(total), 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))
                    : BigDecimal.ZERO;
            item.setRatio(ratio);
            items.add(item);
        }
        pieData.setData(items);
        return pieData;
    }

}
