package cn.iocoder.yudao.module.vehiclecharging.service.orderlist;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderlist.OrderListDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 订单列表 Service 接口
 *
 * @author 亘川智城
 */
public interface OrderListService {

//    /**
//     * 创建订单列表
//     *
//     * @param createReqVO 创建信息
//     * @return 编号
//     */
//    Long createOrderList(@Valid OrderListSaveReqVO createReqVO);

    /**
     * 更新订单列表
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderList(@Valid OrderListSaveReqVO updateReqVO);

//    /**
//     * 删除订单列表
//     *
//     * @param id 编号
//     */
//    void deleteOrderList(Long id);
//
//    /**
//    * 批量删除订单列表
//    *
//    * @param ids 编号
//    */
//    void deleteOrderListListByIds(List<Long> ids);

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

    /**
     * 批量获得订单列表
     *
     * @param ids 批量id
     * @return 批量订单列表
     */
    List<OrderListDO> getOrderListByIds(List<Long> ids);

    /**
     * 订单支付提醒
     *
     * @param id id
     * @return 布尔值
     */
    Boolean payRemind(Long id);

    /**
     * 申请退款
     *
     * @param reqVO 退款申请
     * @return 布尔值
     */
    Boolean refundApply(@Valid OrderListRefundApplyReqVO reqVO);

    /**
     * 评价订单
     * @param reqVO 订单评价
     * @return 布尔值
     */
    Boolean evaluateOrderList(@Valid OrderListEvaluateReqVO reqVO);

    /**
     * 终止充电
     * @param reqVO 终止原因
     * @return 布尔值
     */
    Boolean stopCharge(@Valid OrderListStopChargeReqVO reqVO);

    /**
     * 趋势图
     * @param reqVO data
     * @return 趋势数据
     */
    OrderListChartRespVO getOrderChart(OrderListChartReqVO reqVO);

    /**
     * 钻取趋势图
     * @param reqVO data
     * @return 内容
     */
    List<OrderListDailyTrendRespVO> getDailyTrend(OrderListDailyTrendReqVO reqVO);

    /**
     *
     * @param reqVO data
     * @return 内容
     */
    List<OrderListStatusRatioRespVO> getStatusRatio(OrderListStatusRatioReqVO reqVO);

    /**
     *
     * @param reqVO data
     * @return 内容
     */
    TradeCountRespVO getTradeCount(OrderListTradeCountReqVO reqVO);
}