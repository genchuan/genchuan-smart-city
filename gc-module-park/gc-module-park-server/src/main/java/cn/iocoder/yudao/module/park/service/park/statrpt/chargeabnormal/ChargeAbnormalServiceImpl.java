package cn.iocoder.yudao.module.park.service.park.statrpt.chargeabnormal;

import cn.iocoder.yudao.module.park.controller.admin.park.statrpt.chargeabnormal.vo.*;
import cn.iocoder.yudao.module.park.dal.dataobject.park.statrpt.chargeabnormal.ChargeAbnormalDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.statrpt.chargeabnormal.ChargeAbnormalMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.*;

/**
 * 收费异常 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ChargeAbnormalServiceImpl implements ChargeAbnormalService {

    @Resource
    private ChargeAbnormalMapper chargeAbnormalMapper;

    @Override
    public Long createChargeAbnormal(ChargeAbnormalSaveReqVO createReqVO) {
        // 插入
        ChargeAbnormalDO chargeAbnormal = BeanUtils.toBean(createReqVO, ChargeAbnormalDO.class);
        chargeAbnormalMapper.insert(chargeAbnormal);
        // 返回
        return chargeAbnormal.getId();
    }

    @Override
    public void updateChargeAbnormal(ChargeAbnormalSaveReqVO updateReqVO) {
        // 校验存在
        validateChargeAbnormalExists(updateReqVO.getId());
        // 更新
        ChargeAbnormalDO updateObj = BeanUtils.toBean(updateReqVO, ChargeAbnormalDO.class);
        chargeAbnormalMapper.updateById(updateObj);
    }

    @Override
    public void deleteChargeAbnormal(Long id) {
        // 校验存在
        validateChargeAbnormalExists(id);
        // 删除
        chargeAbnormalMapper.deleteById(id);
    }

    private void validateChargeAbnormalExists(Long id) {
        if (chargeAbnormalMapper.selectById(id) == null) {
            throw exception(CHARGE_ABNORMAL_NOT_EXISTS);
        }
    }

    @Override
    public ChargeAbnormalDO getChargeAbnormal(Long id) {
        return chargeAbnormalMapper.selectById(id);
    }

    @Override
    public PageResult<ChargeAbnormalDO> getChargeAbnormalPage(ChargeAbnormalPageReqVO pageReqVO) {
        return chargeAbnormalMapper.selectPage(pageReqVO);
    }

    @Override
    public StatReportRespVO statReport(StatReportReqVO reqVO) {
        Map<String, Object> statMap = chargeAbnormalMapper.selectStatReport(reqVO);

        // 兜底，防止 Mapper 返回 null
        if (statMap == null) {
            statMap = new HashMap<>();
        }

        BigDecimal totalAmount = (BigDecimal) statMap.getOrDefault(
                "totalAbnormalAmount", BigDecimal.ZERO);

        Long abnormalOrderCount = ((Number) statMap.getOrDefault(
                "abnormalOrderCount", 0)).longValue();

        Long disposedCount = ((Number) statMap.getOrDefault(
                "disposedCount", 0)).longValue();

        Long correctedCount = ((Number) statMap.getOrDefault(
                "correctedCount", 0)).longValue();

        StatReportRespVO respVO = new StatReportRespVO();
        respVO.setTotalAbnormalAmount(totalAmount);
        respVO.setAbnormalOrderCount(abnormalOrderCount);

        // 处置完成率 = 已处置 / 总异常
        if (abnormalOrderCount > 0) {
            respVO.setDisposalCompletionRate(
                    BigDecimal.valueOf(disposedCount)
                            .multiply(BigDecimal.valueOf(100))
                            .divide(BigDecimal.valueOf(abnormalOrderCount), 2, BigDecimal.ROUND_HALF_UP)
            );
        } else {
            respVO.setDisposalCompletionRate(BigDecimal.ZERO);
        }

        // 纠错成功率 = 已纠错 / 已处置
        if (disposedCount > 0) {
            respVO.setCorrectionSuccessRate(
                    BigDecimal.valueOf(correctedCount)
                            .multiply(BigDecimal.valueOf(100))
                            .divide(BigDecimal.valueOf(disposedCount), 2, BigDecimal.ROUND_HALF_UP)
            );
        } else {
            respVO.setCorrectionSuccessRate(BigDecimal.ZERO);
        }

        return respVO;
    }

    @Override
    public TrendRespVO statTrend(StatReportReqVO reqVO) {
        TrendRespVO respVO=new TrendRespVO();
        List<TrendPointVO> abnormalAmountPointList = chargeAbnormalMapper.selectAbnormalAmountTrend(reqVO);
        List<TrendPointVO> abnormalOrderCountPointList=chargeAbnormalMapper.selectAbnormalOrderCount(reqVO);

        //趋势点列表，一个月内不存在的点，填充零和日期处理


        //放入结果
        respVO.setAbnormalAmountPointList(abnormalAmountPointList);
        respVO.setAbnormalOrderCountPointList(abnormalOrderCountPointList);

        return respVO;
    }

}
