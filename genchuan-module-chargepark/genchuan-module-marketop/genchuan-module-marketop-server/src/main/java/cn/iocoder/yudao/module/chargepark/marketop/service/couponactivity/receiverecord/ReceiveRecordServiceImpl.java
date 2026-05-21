package cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.receiverecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.receiverecord.vo.ReceiveRecordChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.receiverecord.vo.ReceiveRecordPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.receiverecord.vo.ReceiveRecordRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.ReceiveRecordDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity.ReceiveRecordMapper;
import cn.iocoder.yudao.module.chargepark.marketop.enums.ReceiveRecordStatusEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
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
import static cn.iocoder.yudao.module.chargepark.marketop.enums.LogRecordConstants.*;

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
    @LogRecord(type = RECEIVE_RECORD_TYPE, subType = RECEIVE_RECORD_CHECK_SUB_TYPE, bizNo = "{{#id}}",
            success = RECEIVE_RECORD_CHECK_SUCCESS)
    public void check(Long id, String checkResult) {
        ReceiveRecordDO receiveRecord = validateExists(id);
//        if (!"0".equals(receiveRecord.getStatus()) && !"-1".equals(receiveRecord.getStatus())) {
//            throw exception(RECEIVE_RECORD_STATUS_ERROR);
//        }
        receiveRecord.setStatus(ReceiveRecordStatusEnum.CHECKED.getValue());
        receiveRecord.setCheckResult(checkResult);
        receiveRecordMapper.updateById(receiveRecord);
        // 记录操作日志上下文
        LogRecordContext.putVariable("receiveRecord", receiveRecord);
    }

    @Override
    public ReceiveRecordChartRespVO getChart() {
        ReceiveRecordChartRespVO respVO = new ReceiveRecordChartRespVO();

        // 总记录数
        Long receiveCount = receiveRecordMapper.selectCount(new LambdaQueryWrapperX<>());
        respVO.setReceiveCount(receiveCount.intValue());

        // 核销率 = 状态为1的记录数 / 总记录数
        Long verifiedCount = receiveRecordMapper.selectCount(new LambdaQueryWrapperX<ReceiveRecordDO>()
                .eq(ReceiveRecordDO::getStatus, "1"));
        BigDecimal verifyRate = receiveCount > 0
                ? BigDecimal.valueOf(verifiedCount).divide(BigDecimal.valueOf(receiveCount), 4, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;
        respVO.setVerifyRate(verifyRate);

        // 近30天按天统计，补全缺失日期
        LocalDateTime startTime = LocalDateTime.now().minusDays(30);
        List<Map<String, Object>> countByDay = receiveRecordMapper.selectCountByDay(startTime);
        Map<String, Integer> dayCountMap = new java.util.LinkedHashMap<>();
        LocalDate today = LocalDate.now();
        for (int i = 29; i >= 0; i--) {
            dayCountMap.put(today.minusDays(i).toString(), 0);
        }
        for (Map<String, Object> row : countByDay) {
            String date = row.get("date").toString();
            int count = ((Number) row.get("count")).intValue();
            dayCountMap.put(date, count);
        }
        List<ReceiveRecordChartRespVO.TrendItem> trendList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : dayCountMap.entrySet()) {
            ReceiveRecordChartRespVO.TrendItem item = new ReceiveRecordChartRespVO.TrendItem();
            item.setDate(entry.getKey());
            item.setCount(entry.getValue());
            trendList.add(item);
        }
        respVO.setTrendList(trendList);

        return respVO;
    }

    @Override
    public ReceiveRecordRespVO getWithJoin(Long id) {
        return receiveRecordMapper.selectByIdJoin(id);
    }

    @Override
    public PageResult<ReceiveRecordRespVO> getPageWithJoin(ReceiveRecordPageReqVO reqVO) {
        Page<ReceiveRecordRespVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        IPage<ReceiveRecordRespVO> pageResult = receiveRecordMapper.selectPageJoin(page, reqVO);
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    private ReceiveRecordDO validateExists(Long id) {
        ReceiveRecordDO receiveRecord = receiveRecordMapper.selectById(id);
        if (receiveRecord == null) {
            throw exception(RECEIVE_RECORD_NOT_EXISTS);
        }
        return receiveRecord;
    }

}
