package cn.iocoder.yudao.module.park.service.park.order.orderperiod;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.order.orderperiod.vo.OrderPeriodPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.orderperiod.vo.OrderPeriodSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.orderperiod.OrderPeriodDO;
import jakarta.validation.Valid;

/**
 * 期卡订单 Service 接口
 *
 * @author lxs
 */
public interface OrderPeriodService {

    /**
     * 创建期卡订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrderPeriod(@Valid OrderPeriodSaveReqVO createReqVO);

    /**
     * 更新期卡订单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderPeriod(@Valid OrderPeriodSaveReqVO updateReqVO);

    /**
     * 删除期卡订单
     *
     * @param id 编号
     */
    void deleteOrderPeriod(Long id);

    /**
     * 获得期卡订单
     *
     * @param id 编号
     * @return 期卡订单
     */
    OrderPeriodDO getOrderPeriod(Long id);

    /**
     * 获得期卡订单分页
     *
     * @param pageReqVO 分页查询
     * @return 期卡订单分页
     */
    PageResult<OrderPeriodDO> getOrderPeriodPage(OrderPeriodPageReqVO pageReqVO);

}
