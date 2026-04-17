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
import java.util.ArrayList;

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
        if (!"正常记录".equals(receiveRecord.getStatus()) && !"异常记录".equals(receiveRecord.getStatus())) {
            throw exception(RECEIVE_RECORD_STATUS_ERROR);
        }
        receiveRecord.setStatus("已核查");
        receiveRecord.setCheckResult(checkResult);
        receiveRecordMapper.updateById(receiveRecord);
    }

    @Override
    public ReceiveRecordChartRespVO getChart(String timeRange) {
        // TODO: 实现图表统计逻辑，暂时返回空数据
        ReceiveRecordChartRespVO respVO = new ReceiveRecordChartRespVO();
        respVO.setReceiveCount(0);
        respVO.setVerifyRate(BigDecimal.ZERO);
        respVO.setTrendList(new ArrayList<>());
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
