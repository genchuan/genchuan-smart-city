package cn.iocoder.yudao.module.park.service.park.order.orderrefund;

import java.util.*;

import cn.iocoder.yudao.module.park.controller.admin.park.order.orderrefund.vo.OrderRefundPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.orderrefund.vo.OrderRefundSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.orderrefund.OrderRefundDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 退款订单 Service 接口
 *
 * @author 亘川智城
 */
public interface OrderRefundService {

    /**
     * 创建退款订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrderRefund(@Valid OrderRefundSaveReqVO createReqVO);

    /**
     * 更新退款订单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderRefund(@Valid OrderRefundSaveReqVO updateReqVO);

    /**
     * 删除退款订单
     *
     * @param id 编号
     */
    void deleteOrderRefund(Long id);

    /**
     * 获得退款订单
     *
     * @param id 编号
     * @return 退款订单
     */
    OrderRefundDO getOrderRefund(Long id);

    /**
     * 获得退款订单分页
     *
     * @param pageReqVO 分页查询
     * @return 退款订单分页
     */
    PageResult<OrderRefundDO> getOrderRefundPage(OrderRefundPageReqVO pageReqVO);

}
