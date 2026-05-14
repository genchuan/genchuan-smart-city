package cn.iocoder.yudao.module.usermerchant.service.merchantmgmt.merchantsendcoupon;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.plateauth.vo.PlateAuthChartRespVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantrecharge.MerchantRechargeDO;
import cn.iocoder.yudao.module.usermerchant.dal.mysql.merchantmgmt.merchantinfo.MerchantInfoMapper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.NameQueryHelper;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.TimeRangeParser;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantsendcoupon.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantsendcoupon.MerchantSendCouponDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.merchantmgmt.merchantsendcoupon.MerchantSendCouponMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.usermerchant.enums.LogRecordConstants.*;

/**
 * 商户发券 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MerchantSendCouponServiceImpl implements MerchantSendCouponService {

    @Resource
    private MerchantInfoMapper merchantInfoMapper;

    @Resource
    private MerchantSendCouponMapper merchantSendCouponMapper;

    @Override
    public Long createMerchantSendCoupon(MerchantSendCouponSaveReqVO createReqVO) {
        // 插入
        MerchantSendCouponDO merchantSendCoupon = BeanUtils.toBean(createReqVO, MerchantSendCouponDO.class);
        merchantSendCouponMapper.insert(merchantSendCoupon);

        // 返回
        return merchantSendCoupon.getId();
    }

    @Override
    public void updateMerchantSendCoupon(MerchantSendCouponSaveReqVO updateReqVO) {
        // 校验存在
        validateMerchantSendCouponExists(updateReqVO.getId());
        // 更新
        MerchantSendCouponDO updateObj = BeanUtils.toBean(updateReqVO, MerchantSendCouponDO.class);
        merchantSendCouponMapper.updateById(updateObj);
    }

    @Override
    public void deleteMerchantSendCoupon(Long id) {
        // 校验存在
        validateMerchantSendCouponExists(id);
        // 删除
        merchantSendCouponMapper.deleteById(id);
    }

    @Override
        public void deleteMerchantSendCouponListByIds(List<Long> ids) {
        // 删除
        merchantSendCouponMapper.deleteByIds(ids);
        }


    private void validateMerchantSendCouponExists(Long id) {
        if (merchantSendCouponMapper.selectById(id) == null) {
            throw exception(MERCHANT_SEND_COUPON_NOT_EXISTS);
        }
    }

    @Override
    public MerchantSendCouponDO getMerchantSendCoupon(Long id) {
        return merchantSendCouponMapper.selectById(id);
    }

    @Override
    public PageResult<MerchantSendCouponDO> getMerchantSendCouponPage(MerchantSendCouponPageReqVO pageReqVO) {
        if (StrUtil.isNotBlank(pageReqVO.getMerchantName())) {
            Long merchantId = merchantInfoMapper.getIdByNickname(pageReqVO.getMerchantName());
            if (merchantId == null) {
                return new PageResult<>(Collections.emptyList(), 0L);
            }
            pageReqVO.setMerchantId(merchantId);
        }
        PageResult<MerchantSendCouponDO> pageResult = merchantSendCouponMapper.selectPage(pageReqVO);
        NameQueryHelper.fillNamesByIds(
                pageResult.getList(),
                MerchantSendCouponDO::getMerchantId,
                MerchantSendCouponDO::setMerchantName,
                "merchant_info", "id", "name"
        );
        return pageResult;
    }

    @Override
    public MerchantSendCouponChartRespVO getMerchantSendCouponChart(MerchantSendCouponChartReqVO chartReqVO) {
        MerchantSendCouponChartRespVO chartRespVO = new MerchantSendCouponChartRespVO();
        String timeRange = chartReqVO.getTimeRange();

        TimeRangeParser.TimeRangeParsed parsed;
        if (StrUtil.isBlank(timeRange)) {
            // 未传时间范围：全量查询，start 和 end 为 null，粒度默认 day
            parsed = new TimeRangeParser.TimeRangeParsed(null, null, "day");
        } else {
            parsed = TimeRangeParser.parse(timeRange);
            if (parsed == null) {
                // 解析失败，返回空数据
                return chartRespVO;
            }
        }
        //折线图渲染
        List<MerchantSendCouponChartRespVO.SendCountTrendVO> sendCountTrend = merchantSendCouponMapper.selectSendCountTrend(
                parsed.getStart(), parsed.getEnd(), parsed.getGranularity());
        chartRespVO.setSendCountTrend(sendCountTrend);
        //总数统计
        chartRespVO.setSendCount(merchantSendCouponMapper.selectSendCount(parsed.getStart(), parsed.getEnd()));
        //计算比率
        chartRespVO.setUseRate(merchantSendCouponMapper.selectUseRate(parsed.getStart(), parsed.getEnd()));
        return chartRespVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = TYPE_MERCHANT_SEND_COUPON, subType = SUB_TYPE_SEND_COUPON,
            bizNo = "{{#sendReqVO.merchantId}}",
            success = SUCCESS_SEND_COUPON)
    public void sendCoupon(MerchantSendCouponSendReqVO sendReqVO) {
        // 1. 校验商户存在,忽略

        // 2. 校验优惠券存在并获取名称
        String couponName = NameQueryHelper.getNamesById("coupon_mgmt", "id", sendReqVO.getCouponId(), "name");
        if (couponName == null) {
            throw new ServiceException(MERCHANT_SEND_COUPON_NOT_EXISTS);
        }

        // 3. 创建发券记录
        MerchantSendCouponDO entity = new MerchantSendCouponDO();
        entity.setMerchantId(sendReqVO.getMerchantId());
        entity.setCouponId(sendReqVO.getCouponId());
        entity.setCouponName(couponName);
        entity.setSendCount(sendReqVO.getSendCount());
        entity.setExecTime(sendReqVO.getExecTime());
        entity.setRemark(sendReqVO.getRemark());
        entity.setUseCount(0);
        entity.setFinishTime(null);

        // 若执行时间为空，则立即执行
        if (sendReqVO.getExecTime() == null) {
            entity.setStatus("已执行");
            entity.setExecTime(LocalDateTime.now());
            entity.setFinishTime(LocalDateTime.now());
        } else {
            entity.setStatus("待执行");
        }
        // 记录操作日志上下文
        LogRecordContext.putVariable("sendReqVO", sendReqVO);
        merchantSendCouponMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = TYPE_MERCHANT_SEND_COUPON, subType = SUB_TYPE_EXECUTE_COUPON,
            bizNo = "{{{#ids}}}",
            success = SUCCESS_EXECUTE_COUPON)
    public void executeCoupon(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 查询待执行的记录
        List<MerchantSendCouponDO> records = merchantSendCouponMapper.selectBatchIds(ids);
        if (records.isEmpty()) {
            throw new ServiceException(MERCHANT_SEND_COUPON_NOT_EXISTS);
        }
        // 过滤出状态为“待执行”的记录
        List<Long> validIds = records.stream()
                .filter(r -> "待执行".equals(r.getStatus()))
                .map(MerchantSendCouponDO::getId)
                .collect(Collectors.toList());
        if (validIds.isEmpty()) {
            throw new ServiceException(ILLEGAL_STATUS);
        }
        // 更新：状态 -> 已执行，finish_time -> 当前时间
        LocalDateTime now = LocalDateTime.now();
        MerchantSendCouponDO updateEntity = new MerchantSendCouponDO();
        updateEntity.setStatus("已执行");
        updateEntity.setFinishTime(now);
        // 注意：exec_time 不更新，保持原来的计划执行时间
        LambdaUpdateWrapper<MerchantSendCouponDO> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(MerchantSendCouponDO::getId, validIds)
                .eq(MerchantSendCouponDO::getStatus, "待执行");
        merchantSendCouponMapper.update(updateEntity, wrapper);
        // 但根据需求，仅需记录执行状态，暂不处理具体发放。可后续扩展。
        // 记录操作日志上下文
        LogRecordContext.putVariable("ids", ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = TYPE_MERCHANT_SEND_COUPON, subType = SUB_TYPE_CANCEL_COUPON,
            bizNo = "{{{#ids}}}",
            success = SUCCESS_CANCEL_COUPON)
    public void cancelCoupon(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        // 查询待执行的记录
        List<MerchantSendCouponDO> records = merchantSendCouponMapper.selectBatchIds(ids);
        if (records.isEmpty()) {
            throw new ServiceException(MERCHANT_SEND_COUPON_NOT_EXISTS);
        }
        // 过滤出状态为“待执行”的记录
        List<Long> validIds = records.stream()
                .filter(r -> "待执行".equals(r.getStatus()))
                .map(MerchantSendCouponDO::getId)
                .collect(Collectors.toList());
        if (validIds.isEmpty()) {
            throw new ServiceException(ILLEGAL_STATUS);
        }
        // 更新状态为“已取消”
        MerchantSendCouponDO updateEntity = new MerchantSendCouponDO();
        updateEntity.setStatus("已取消");
        // 可保留 finish_time 为 null
        LambdaUpdateWrapper<MerchantSendCouponDO> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(MerchantSendCouponDO::getId, validIds)
                .eq(MerchantSendCouponDO::getStatus, "待执行");
        merchantSendCouponMapper.update(updateEntity, wrapper);
        // 记录操作日志上下文
        LogRecordContext.putVariable("ids", ids);
    }

}