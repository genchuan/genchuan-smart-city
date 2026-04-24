package cn.iocoder.yudao.module.chargepark.marketop.service.pointactivity.pointlottery;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointlottery.vo.PointLotteryChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointlottery.vo.PointLotteryPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointLotteryDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.PointLotteryMapper;
import cn.iocoder.yudao.module.chargepark.marketop.enums.PointLotteryStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;

@Service
@Validated
public class PointLotteryServiceImpl implements PointLotteryService {

    @Resource
    private PointLotteryMapper pointLotteryMapper;

    @Override
    public PageResult<PointLotteryDO> getPage(PointLotteryPageReqVO reqVO) {
        return pointLotteryMapper.selectPage(reqVO);
    }

    @Override
    public PointLotteryDO get(Long id) {
        return pointLotteryMapper.selectById(id);
    }

    @Override
    public void check(Long id, String checkResult) {
        PointLotteryDO lottery = validateExists(id);
        if (Objects.equals(lottery.getStatus(), PointLotteryStatusEnum.NORMAL.getValue())) {
            throw exception(POINT_LOTTERY_STATUS_ERROR);
        }
        lottery.setStatus(PointLotteryStatusEnum.CHECKED.getValue());
        lottery.setCheckResult(checkResult);
        pointLotteryMapper.updateById(lottery);
    }

    @Override
    public PointLotteryChartRespVO getChart() {
        // 总抽奖量
        Long totalCount = pointLotteryMapper.selectTotalCount();
        // 中奖数 (prize_id = 0)
        Long winCount = pointLotteryMapper.selectWinCount();
        // 中奖率
        BigDecimal winRate = totalCount > 0
                ? new BigDecimal(winCount).divide(new BigDecimal(totalCount), 4, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;

        // 近30天按天统计抽奖总数，补全缺失日期
        LocalDateTime startTime = LocalDateTime.now().minusDays(30);
        List<Map<String, Object>> lotteryByDay = pointLotteryMapper.selectCountByDay(startTime);
        Map<String, Integer> dayCountMap = new LinkedHashMap<>();
        LocalDate today = LocalDate.now();
        for (int i = 29; i >= 0; i--) {
            dayCountMap.put(today.minusDays(i).toString(), 0);
        }
        for (Map<String, Object> row : lotteryByDay) {
            String date = row.get("date").toString();
            int count = ((Number) row.get("count")).intValue();
            dayCountMap.put(date, count);
        }
        List<PointLotteryChartRespVO.TrendItem> trendList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : dayCountMap.entrySet()) {
            PointLotteryChartRespVO.TrendItem item = new PointLotteryChartRespVO.TrendItem();
            item.setLotteryTime(entry.getKey());
            item.setCount(entry.getValue());
            trendList.add(item);
        }

        PointLotteryChartRespVO respVO = new PointLotteryChartRespVO();
        respVO.setLotteryCount(totalCount.intValue());
        respVO.setWinRate(winRate);
        respVO.setTrendList(trendList);
        return respVO;
    }

    private PointLotteryDO validateExists(Long id) {
        PointLotteryDO lottery = pointLotteryMapper.selectById(id);
        if (lottery == null) {
            throw exception(POINT_LOTTERY_NOT_EXISTS);
        }
        return lottery;
    }

}
