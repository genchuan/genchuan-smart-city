package cn.iocoder.yudao.module.park.service.park.user.paymentproxy;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.user.paymentproxy.vo.PaymentProxyPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.paymentproxy.vo.PaymentProxySaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.paymentproxy.PaymentProxyDO;
import jakarta.validation.Valid;

/**
 * 代付规则 Service 接口
 *
 * @author 亘川智城
 */
public interface PaymentProxyService {

    /**
     * 创建代付规则
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPaymentProxy(@Valid PaymentProxySaveReqVO createReqVO);

    /**
     * 更新代付规则
     *
     * @param updateReqVO 更新信息
     */
    void updatePaymentProxy(@Valid PaymentProxySaveReqVO updateReqVO);

    /**
     * 删除代付规则
     *
     * @param id 编号
     */
    void deletePaymentProxy(Long id);

    /**
     * 获得代付规则
     *
     * @param id 编号
     * @return 代付规则
     */
    PaymentProxyDO getPaymentProxy(Long id);

    /**
     * 获得代付规则分页
     *
     * @param pageReqVO 分页查询
     * @return 代付规则分页
     */
    PageResult<PaymentProxyDO> getPaymentProxyPage(PaymentProxyPageReqVO pageReqVO);

}
