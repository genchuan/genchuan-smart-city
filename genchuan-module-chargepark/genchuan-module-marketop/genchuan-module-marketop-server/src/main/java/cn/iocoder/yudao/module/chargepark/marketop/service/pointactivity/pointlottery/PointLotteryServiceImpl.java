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
import java.util.ArrayList;
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
    public PointLotteryChartRespVO getChart(String timeRange) {
        // TODO: 实现图表统计逻辑
        PointLotteryChartRespVO respVO = new PointLotteryChartRespVO();
        respVO.setLotteryCount(0);
        respVO.setWinRate(BigDecimal.ZERO);
        respVO.setTrendList(new ArrayList<>());
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
