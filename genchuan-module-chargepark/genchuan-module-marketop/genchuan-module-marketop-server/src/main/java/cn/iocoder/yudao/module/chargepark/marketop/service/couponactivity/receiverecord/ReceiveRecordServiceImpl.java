package cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.receiverecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.receiverecord.vo.ReceiveRecordChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.receiverecord.vo.ReceiveRecordPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.ReceiveRecordDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity.ReceiveRecordMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;

@Service
@Validated
public class ReceiveRecordServiceImpl implements ReceiveRecordService {

    @Resource
    private ReceiveRecordMapper receiveRecordMapper;

    @Override
    public PageResult<ReceiveRecordDO> getPage(ReceiveRecordPageReqVO reqVO) {
        return receiveRecordMapper.selectPage(reqVO);
    }

    @Override
    public ReceiveRecordDO get(Long id) {
        return receiveRecordMapper.selectById(id);
    }

    @Override
    public void check(Long id, String checkResult) {
        ReceiveRecordDO receiveRecord = validateExists(id);
        if (!"0".equals(receiveRecord.getStatus()) && !"-1".equals(receiveRecord.getStatus())) {
            throw exception(RECEIVE_RECORD_STATUS_ERROR);
        }
        receiveRecord.setStatus("1");
        receiveRecord.setCheckResult(checkResult);
        receiveRecordMapper.updateById(receiveRecord);
    }

    @Override
    public ReceiveRecordChartRespVO getChart(Long startTime, Long endTime, Long stationId) {

        LocalDateTime startDateTime = startTime != null
                ? LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(startTime), ZoneId.systemDefault())
                : null;
        LocalDateTime endDateTime = endTime != null
                ? LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(endTime), ZoneId.systemDefault())
                : null;

        // 查询时间范围内的所有记录
        List<ReceiveRecordDO> records = receiveRecordMapper.selectListByTimeRange(
                startDateTime, endDateTime, stationId);

        ReceiveRecordChartRespVO respVO = new ReceiveRecordChartRespVO();
        // 总记录数
        int receiveCount = records.size();
        respVO.setReceiveCount(receiveCount);

        // 核销率 = 状态为1的记录数 / 总记录数
        long verifiedCount = records.stream().filter(r -> "1".equals(r.getStatus())).count();
        BigDecimal verifyRate = receiveCount > 0
                ? BigDecimal.valueOf(verifiedCount).divide(BigDecimal.valueOf(receiveCount), 4, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;
        respVO.setVerifyRate(verifyRate);

        // 趋势列表：按 receive_time 的日期部分分组统计
        Map<LocalDate, Long> trendMap = records.stream()
                .filter(r -> r.getReceiveTime() != null)
                .collect(Collectors.groupingBy(r -> r.getReceiveTime().toLocalDate(), Collectors.counting()));

        List<ReceiveRecordChartRespVO.TrendItem> trendList = new ArrayList<>();
        trendMap.forEach((date, count) -> {
            ReceiveRecordChartRespVO.TrendItem item = new ReceiveRecordChartRespVO.TrendItem();
            item.setDate(date.toString());
            item.setCount(count.intValue());
            trendList.add(item);
        });
        // 按日期排序
        trendList.sort((a, b) -> a.getDate().compareTo(b.getDate()));
        respVO.setTrendList(trendList);

        return respVO;
    }

    private ReceiveRecordDO validateExists(Long id) {
        ReceiveRecordDO receiveRecord = receiveRecordMapper.selectById(id);
        if (receiveRecord == null) {
            throw exception(RECEIVE_RECORD_NOT_EXISTS);
        }
        return receiveRecord;
    }

}
