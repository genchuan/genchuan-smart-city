package cn.iocoder.yudao.module.park.service.park.user.paymentproxy;

import cn.iocoder.yudao.module.park.controller.admin.park.user.paymentproxy.vo.PaymentProxyPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.paymentproxy.vo.PaymentProxySaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.paymentproxy.PaymentProxyDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.user.paymentproxy.PaymentProxyMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.*;

/**
 * 代付规则 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PaymentProxyServiceImpl implements PaymentProxyService {

    @Resource
    private PaymentProxyMapper paymentProxyMapper;

    @Override
    public Long createPaymentProxy(PaymentProxySaveReqVO createReqVO) {
        // 插入
        PaymentProxyDO paymentProxy = BeanUtils.toBean(createReqVO, PaymentProxyDO.class);
        paymentProxyMapper.insert(paymentProxy);
        // 返回
        return paymentProxy.getId();
    }

    @Override
    public void updatePaymentProxy(PaymentProxySaveReqVO updateReqVO) {
        // 校验存在
        validatePaymentProxyExists(updateReqVO.getId());
        // 更新
        PaymentProxyDO updateObj = BeanUtils.toBean(updateReqVO, PaymentProxyDO.class);
        paymentProxyMapper.updateById(updateObj);
    }

    @Override
    public void deletePaymentProxy(Long id) {
        // 校验存在
        validatePaymentProxyExists(id);
        // 删除
        paymentProxyMapper.deleteById(id);
    }

    private void validatePaymentProxyExists(Long id) {
        if (paymentProxyMapper.selectById(id) == null) {
            throw exception(PAYMENT_PROXY_NOT_EXISTS);
        }
    }

    @Override
    public PaymentProxyDO getPaymentProxy(Long id) {
        return paymentProxyMapper.selectById(id);
    }

    @Override
    public PageResult<PaymentProxyDO> getPaymentProxyPage(PaymentProxyPageReqVO pageReqVO) {
        return paymentProxyMapper.selectPage(pageReqVO);
    }

}
