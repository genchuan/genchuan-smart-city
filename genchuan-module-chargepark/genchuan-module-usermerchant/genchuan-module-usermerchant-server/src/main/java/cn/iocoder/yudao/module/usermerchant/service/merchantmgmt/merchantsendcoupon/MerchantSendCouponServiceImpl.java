package cn.iocoder.yudao.module.usermerchant.service.merchantmgmt.merchantsendcoupon;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.plateauth.vo.PlateAuthChartRespVO;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.TimeRangeParser;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantsendcoupon.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantsendcoupon.MerchantSendCouponDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.merchantmgmt.merchantsendcoupon.MerchantSendCouponMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 商户发券 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MerchantSendCouponServiceImpl implements MerchantSendCouponService {

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
        return merchantSendCouponMapper.selectPage(pageReqVO);
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

}