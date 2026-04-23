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
import java.time.LocalDateTime;
import java.util.List;

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

        // 折线图、柱状图、饼图数据（TODO：后续通过 XML 聚合查询实现）
        respVO.setLineData(List.of());
        respVO.setBarData(List.of());
        respVO.setPieData(List.of());

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

}
