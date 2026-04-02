package cn.iocoder.yudao.module.vehiclecharging.service.orderrefund;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderrefund.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderrefund.OrderRefundDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 订单退款 Service 接口
 *
 * @author zhucongquan
 */
public interface OrderRefundService {

    /**
     * 创建订单退款
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrderRefund(@Valid OrderRefundSaveReqVO createReqVO);

    /**
     * 更新订单退款
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderRefund(@Valid OrderRefundSaveReqVO updateReqVO);

    /**
     * 删除订单退款
     *
     * @param id 编号
     */
    void deleteOrderRefund(Long id);

    /**
    * 批量删除订单退款
    *
    * @param ids 编号
    */
    void deleteOrderRefundListByIds(List<Long> ids);

    /**
     * 获得订单退款
     *
     * @param id 编号
     * @return 订单退款
     */
    OrderRefundDO getOrderRefund(Long id);

    /**
     * 获得订单退款分页
     *
     * @param pageReqVO 分页查询
     * @return 订单退款分页
     */
    PageResult<OrderRefundDO> getOrderRefundPage(OrderRefundPageReqVO pageReqVO);

    /**
     * 批量审核订单
     *
     * @param auditReqVO 审核信息
     */
    void auditOrderRefund(@Valid OrderRefundAuditReqVO auditReqVO);

    /**
     * 批量退款
     *
     * @param refundReqVO 退款信息
     */
    void refundOrderRefund(@Valid OrderRefundRefundReqVO refundReqVO);

    /**
     * 驳回订单退款申请
     *
     * @param rejectReqVO 驳回信息
     */
    void rejectOrderRefund(@Valid OrderRefundRejectReqVO rejectReqVO);

    /**
     * 重新申请退款
     *
     * @param reapplyReqVO 重新申请信息
     */
    void reapplyOrderRefund(@Valid OrderRefundReapplyReqVO reapplyReqVO);

    /**
     * 获取订单退款统计图表
     *
     * @param chartReqVO 查询条件
     * @return 图表数据
     */
    OrderRefundChartRespVO getOrderRefundChart(OrderRefundChartReqVO chartReqVO);
}