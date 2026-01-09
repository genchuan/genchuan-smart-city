// DataOverviewServiceImpl.java
package cn.iocoder.yudao.module.industry.service.businessservices.dpzl.dataview;

import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.dataview.vo.BusiDataViewRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.businessservices.dpzl.dataoverview.BusiDataViewMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class BusiDataViewServiceImpl implements BusiDataViewService {

    @Resource
    private BusiDataViewMapper BusiDataViewMapper;

    @Override
    public BusiDataViewRespVO getDataOverview() {
        BusiDataViewRespVO respVO = new BusiDataViewRespVO();

        try {
            // 1. 企业总数
            Integer totalEntCount = BusiDataViewMapper.selectTotalEntCount();
            respVO.setTotalEntCount(totalEntCount != null ? totalEntCount : 0);

            // 2. 当日办件量
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Integer dailyHandleCount = BusiDataViewMapper.selectDailyHandleCount(today);
            respVO.setDailyHandleCount(dailyHandleCount != null ? dailyHandleCount : 0);

            // 3. 政策兑现总额
            java.math.BigDecimal totalPolAmount = BusiDataViewMapper.selectTotalPolAmount();
            respVO.setTotalPolAmount(totalPolAmount != null ? totalPolAmount : java.math.BigDecimal.ZERO);

            // 4. 诉求响应率
            java.math.BigDecimal appealRespRate = BusiDataViewMapper.selectAppealRespRate();
            respVO.setAppealRespRate(appealRespRate != null ? appealRespRate : java.math.BigDecimal.ZERO);

            // 5. 区域企业分布
            respVO.setRegionEntDistributions(BusiDataViewMapper.selectRegionEntDistribution());

            // 6. 近7天办件趋势
            LocalDate endDate = LocalDate.now();
            LocalDate beginDate = endDate.minusDays(6);
            String beginDateStr = beginDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String endDateStr = endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            respVO.setDailyHandleTrends(BusiDataViewMapper.selectDailyHandleTrend(beginDateStr, endDateStr));

        } catch (Exception e) {
            log.error("获取数据概览异常", e);
            // 设置默认值避免空指针
            if (respVO.getTotalEntCount() == null) respVO.setTotalEntCount(0);
            if (respVO.getDailyHandleCount() == null) respVO.setDailyHandleCount(0);
            if (respVO.getTotalPolAmount() == null) respVO.setTotalPolAmount(java.math.BigDecimal.ZERO);
            if (respVO.getAppealRespRate() == null) respVO.setAppealRespRate(java.math.BigDecimal.ZERO);
        }

        return respVO;
    }

}