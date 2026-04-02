package cn.iocoder.yudao.module.vehiclecharging.service.orderlist;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderlist.OrderListDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 订单列表 Service 接口
 *
 * @author 亘川智城
 */
public interface OrderListService {

    /**
     * 创建订单列表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrderList(@Valid OrderListSaveReqVO createReqVO);

    /**
     * 更新订单列表
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderList(@Valid OrderListSaveReqVO updateReqVO);

    /**
     * 删除订单列表
     *
     * @param id 编号
     */
    void deleteOrderList(Long id);

    /**
    * 批量删除订单列表
    *
    * @param ids 编号
    */
    void deleteOrderListListByIds(List<Long> ids);

    /**
     * 获得订单列表
     *
     * @param id 编号
     * @return 订单列表
     */
    OrderListDO getOrderList(Long id);

    /**
     * 获得订单列表分页
     *
     * @param pageReqVO 分页查询
     * @return 订单列表分页
     */
    PageResult<OrderListDO> getOrderListPage(OrderListPageReqVO pageReqVO);

}