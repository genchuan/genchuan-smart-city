package cn.iocoder.yudao.module.usermerchant.service.merchantmgmt.merchantrecharge;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantlink.MerchantLinkDO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.userinfo.UserInfoDO;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.merchantmgmt.merchantinfo.MerchantInfoMapper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.NameQueryHelper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.TimeRangeParser;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantrecharge.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantrecharge.MerchantRechargeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.merchantmgmt.merchantrecharge.MerchantRechargeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 商户充值 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MerchantRechargeServiceImpl implements MerchantRechargeService {

    @Resource
    private MerchantRechargeMapper merchantRechargeMapper;

    @Resource
    private MerchantInfoMapper merchantInfoMapper;

    @Override
    public Boolean payMerchantRecharge(MerchantRechargePayReqVO payReqVO) {
        if (CollectionUtils.isEmpty(payReqVO.getIds())) {
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        // 使用 UpdateWrapper 批量更新状态
        UpdateWrapper<MerchantRechargeDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", payReqVO.getIds())
                .set("pay_channel", payReqVO.getPayChannel())
                .set("status","已支付")
                .set("pay_time", now);
        int rows = merchantRechargeMapper.update(null, updateWrapper);
        return rows > 0;
    }

    @Override
    public Boolean createMerchantRecharge(MerchantRechargeCreateReqVO createReqVO) {
        // 插入
        MerchantRechargeDO merchantRecharge = BeanUtils.toBean(createReqVO, MerchantRechargeDO.class);
        merchantRecharge.setOrderNo(generateOrderNo());
        merchantRecharge.setStatus("待支付");
        merchantRecharge.setCreator("admin");
        merchantRecharge.setUpdater("admin");
        int rows = merchantRechargeMapper.insert(merchantRecharge);
        // 返回
        return rows > 0;
    }

    @Override
    public void updateMerchantRecharge(MerchantRechargeSaveReqVO updateReqVO) {
        // 校验存在
        validateMerchantRechargeExists(updateReqVO.getId());
        // 更新
        MerchantRechargeDO updateObj = BeanUtils.toBean(updateReqVO, MerchantRechargeDO.class);
        merchantRechargeMapper.updateById(updateObj);
    }

    @Override
    public void deleteMerchantRecharge(Long id) {
        // 校验存在
        validateMerchantRechargeExists(id);
        // 删除
        merchantRechargeMapper.deleteById(id);
    }

    @Override
        public void deleteMerchantRechargeListByIds(List<Long> ids) {
        // 删除
        merchantRechargeMapper.deleteByIds(ids);
        }


    private void validateMerchantRechargeExists(Long id) {
        if (merchantRechargeMapper.selectById(id) == null) {
            throw exception(MERCHANT_RECHARGE_NOT_EXISTS);
        }
    }

    @Override
    public MerchantRechargeDO getMerchantRecharge(Long id) {
        return merchantRechargeMapper.selectById(id);
    }

    @Override
    public PageResult<MerchantRechargeDO> getMerchantRechargePage(MerchantRechargePageReqVO pageReqVO) {
        if (StrUtil.isNotBlank(pageReqVO.getMerchantName())) {
            Long merchantId = merchantInfoMapper.getIdByNickname(pageReqVO.getMerchantName());
            if (merchantId == null) {
                return new PageResult<>(Collections.emptyList(), 0L);
            }
            pageReqVO.setMerchantId(merchantId);
        }
        PageResult<MerchantRechargeDO> pageResult = merchantRechargeMapper.selectPage(pageReqVO);
        NameQueryHelper.fillNamesByIds(
                pageResult.getList(),
                MerchantRechargeDO::getMerchantId,
                MerchantRechargeDO::setMerchantName,
                "merchant_info", "id", "name"
        );
        return pageResult;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean cashMerchantRecharge(MerchantRechargePayReqVO payReqVO, String code) {
        List<Long> ids = payReqVO.getIds();
        if (CollectionUtils.isEmpty(ids)) {
            return false;
        }

        List<MerchantRechargeDO> recharges = merchantRechargeMapper.selectBatchIds(ids);
        if (recharges.size() != ids.size()) {
            throw new ServiceException(MERCHANT_RECHARGE_NOT_EXISTS);
        }

        if ("确认".equals(code)) {
            // 校验状态和确认时间
            for (MerchantRechargeDO recharge : recharges) {
                if (!"已支付".equals(recharge.getStatus())) {
                    throw new ServiceException(ILLEGAL_STATUS);
                }
                if (recharge.getConfirmTime() != null) {
                    throw new ServiceException(ILLEGAL_STATUS); // 已确认过
                }
            }
            // 汇总每个商户的充值金额
            Map<Long, BigDecimal> merchantAmountMap = new HashMap<>();
            for (MerchantRechargeDO recharge : recharges) {
                merchantAmountMap.merge(recharge.getMerchantId(), recharge.getAmount(), BigDecimal::add);
            }

            LocalDateTime now = LocalDateTime.now();
            // 批量更新状态为“已支付”并设置确认时间
            UpdateWrapper<MerchantRechargeDO> wrapper = new UpdateWrapper<>();
            wrapper.in("id", ids)
                    .set("confirm_time", now);
            merchantRechargeMapper.update(null, wrapper);

            // 批量增加商户余额
            for (Map.Entry<Long, BigDecimal> entry : merchantAmountMap.entrySet()) {
                merchantInfoMapper.increaseWalletBalance(entry.getKey(), entry.getValue());
            }
        } else if ("取消".equals(code)) {
            // 校验状态必须为“待支付”
            for (MerchantRechargeDO recharge : recharges) {
                if (!"待支付".equals(recharge.getStatus())) {
                    throw new ServiceException(ILLEGAL_STATUS);
                }
            }
            // 批量更新状态为“已取消”
            UpdateWrapper<MerchantRechargeDO> wrapper = new UpdateWrapper<>();
            wrapper.in("id", ids)
                    .set("status", "已取消");
            merchantRechargeMapper.update(null, wrapper);
        } else {
            throw new ServiceException(ILLEGAL_STATUS);
        }

        return true;
    }

    @Override
    public MerchantRechargeChartRespVO getMerchantRechargeChart(MerchantRechargeChartReqVO chartReqVO) {
        MerchantRechargeChartRespVO chartRespVO = new MerchantRechargeChartRespVO();
        String timeRange = chartReqVO.getTimeRange();

        TimeRangeParser.TimeRangeParsed parsed;
        if (StrUtil.isBlank(timeRange)) {
            parsed = new TimeRangeParser.TimeRangeParsed(null, null, "day");
        } else {
            parsed = TimeRangeParser.parse(timeRange);
            if (parsed == null) {
                return chartRespVO;
            }
        }

        // 折线图：按时间聚合充值金额
        List<MerchantRechargeChartRespVO.RechargeAmountTrendVO> trendList =
                merchantRechargeMapper.selectRechargeAmountTrend(parsed.getStart(), parsed.getEnd(), parsed.getGranularity());
        chartRespVO.setRechargeAmountTrend(trendList);

        // 总充值金额
        BigDecimal totalAmount = merchantRechargeMapper.selectTotalRechargeAmount(parsed.getStart(), parsed.getEnd());
        chartRespVO.setRechargeAmount(totalAmount != null ? totalAmount : BigDecimal.ZERO);

        // 充值成功率（已支付订单数 / 总订单数）
        BigDecimal successRate = merchantRechargeMapper.selectRechargeSuccessRate(parsed.getStart(), parsed.getEnd());
        chartRespVO.setRechargeSuccessRate(successRate != null ? successRate : BigDecimal.ZERO);

        return chartRespVO;
    }

    private String generateOrderNo() {
        // 当前日期格式：yyyyMMdd
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String prefix = "RECHARGE" + datePart;

        // 查询当天已生成的最大序号
        LambdaQueryWrapper<MerchantRechargeDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(MerchantRechargeDO::getOrderNo)
                .likeRight(MerchantRechargeDO::getOrderNo, prefix)   // 匹配前缀，如 "RECHARGE20250415"
                .orderByDesc(MerchantRechargeDO::getOrderNo)
                .last("LIMIT 1");
        MerchantRechargeDO last = merchantRechargeMapper.selectOne(wrapper);

        int seq = 1;
        if (last != null && last.getOrderNo() != null) {
            String lastNo = last.getOrderNo();
            // 提取后面的数字部分
            String seqStr = lastNo.substring(prefix.length());
            try {
                seq = Integer.parseInt(seqStr) + 1;
            } catch (NumberFormatException e) {
                seq = 1;
            }
        }
        // 超过 9999 可以重置或抛出异常，根据业务决定
        if (seq > 9999) {
            throw exception(ORDER_NO_REACHED_LIMIT);
        }
        return prefix + String.format("%04d", seq);
    }

}