package cn.iocoder.yudao.module.vehiclecharging.service.orderlist;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderrefund.OrderRefundDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.orderrefund.OrderRefundMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderlist.OrderListDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.orderlist.OrderListMapper;

/**
 * 订单列表 Service 实现类
 *
 * @author 亘川智城
 */
@Slf4j
@Service
@Validated
public class OrderListServiceImpl implements OrderListService {

    @Resource
    private OrderListMapper orderListMapper;

    @Resource
    private OrderRefundMapper orderRefundMapper;

//    @Override
//    public Long createOrderList(OrderListSaveReqVO createReqVO) {
//        // 插入
//        OrderListDO orderList = BeanUtils.toBean(createReqVO, OrderListDO.class);
//        orderListMapper.insert(orderList);
//
//        // 返回
//        return orderList.getId();
//    }

    @Override
    public void updateOrderList(OrderListSaveReqVO updateReqVO) {
        //校验存在
        validateOrderListExists(updateReqVO.getId());
        // 更新
        OrderListDO updateObj = new OrderListDO();
        if (updateReqVO.getCancelReason() != null && !updateReqVO.getCancelReason().trim().isEmpty()) {
            updateObj.setId(updateReqVO.getId());
            updateObj.setOrderStatus("已取消");
            updateObj.setCancelTime(LocalDateTime.now());
            updateObj.setRemark(updateReqVO.getCancelReason());
            orderListMapper.updateById(updateObj);
        } else {
            updateObj = BeanUtils.toBean(updateReqVO, OrderListDO.class);
            orderListMapper.updateById(updateObj);
        }
    }

//    @Override
//    public void deleteOrderList(Long id) {
//        // 校验存在
//        validateOrderListExists(id);
//        // 删除
//        orderListMapper.deleteById(id);
//    }
//
//    @Override
//        public void deleteOrderListListByIds(List<Long> ids) {
//        // 删除
//        orderListMapper.deleteByIds(ids);
//    }


    private void validateOrderListExists(Long id) {
        if (orderListMapper.selectById(id) == null) {
//            throw exception(ORDER_LIST_NOT_EXISTS);
            throw new RuntimeException("订单不存在，id=" + id);
        }
    }

    @Override
    public OrderListDO getOrderList(Long id) {
        return orderListMapper.selectById(id);
    }

    @Override
    public PageResult<OrderListDO> getOrderListPage(OrderListPageReqVO pageReqVO) {
        return orderListMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OrderListDO> getOrderListByIds(List<Long> ids) {
        return orderListMapper.selectList(new LambdaQueryWrapper<OrderListDO>().in(OrderListDO::getId, ids));
    }

    // 假设有一个消息服务客户端
    // @Autowired
    // private MessageServiceApi messageServiceApi;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean payRemind(Long id) {
        // 1. 查询订单
        OrderListDO order = orderListMapper.selectById(id);
        if (order == null) {
//                throw exception(ORDER_LIST_NOT_EXISTS);
            throw new RuntimeException("订单不存在，id=" + id);
        }

        // 2. 状态校验：仅待支付可提醒（这里使用字典code，实际请根据业务调整）
        if (!"待支付".equals(order.getOrderStatus())) {
            throw new RuntimeException("只有待支付状态的订单才能发起支付提醒");
        }

        // 3. 发送提醒（调用消息服务）
        try {
            // 实际调用微信/短信推送，例如：
            // messageServiceApi.sendPayRemind(order.getUserId(), order.getOrderCode());
            log.info("发送支付提醒成功，订单ID：{}", id);
            return true;
        } catch (Exception e) {
            log.error("发送支付提醒失败，订单ID：{}", id, e);
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean refundApply(OrderListRefundApplyReqVO reqVO) {
        Long orderId = reqVO.getId();

        // 1. 查询订单
        OrderListDO order = orderListMapper.selectById(orderId);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }

        // 2. 状态校验：仅“已支付”支付状态，且订单状态为“已支付”或“已完成”
        // 注意：字典值需根据实际配置调整，以下假设“已支付”对应字典码 "PAID"，订单状态“已完成”对应 "COMPLETED"
        String orderCode = order.getOrderCode();     // 订单编号
        String payStatus = order.getPayStatus();     // 支付状态
        String orderStatus = order.getOrderStatus(); // 订单状态
        if (!"已支付".equals(payStatus) || (!"已支付".equals(orderStatus) && !"已完成".equals(orderStatus))) {
            throw new RuntimeException("只有已支付或已完成状态的订单才能申请退款");
        }

        // 3. 校验退款金额不超过订单金额（可选）
        if (reqVO.getRefundAmount().compareTo(order.getChargeMoney()) > 0) {
            throw new RuntimeException("退款金额不能超过订单金额");
        }

        // 4. 创建退款记录，等待接口
        OrderRefundDO refund = new OrderRefundDO();
        refund.setOrderCode(orderCode);
        refund.setOrderCode(order.getOrderCode());
        refund.setRefundAmount(reqVO.getRefundAmount());
        refund.setRefundReason(reqVO.getRefundReason());
        refund.setRefundStatus("待审核");      // 待审核
        refund.setCreateTime(LocalDateTime.now());
//        refund.setCreator(SecurityUtils.getLoginUser().getUsername()); // 获取当前登录用户
        refund.setCreator("admin");

        orderRefundMapper.insert(refund);

        // 5. 可选：更新订单状态为“退款中”（根据业务决定）
//        order.setOrderStatus("REFUNDING");
        orderListMapper.updateById(order);

        log.info("退款申请成功，订单ID：{}，退款单ID：{}", orderId, refund.getId());//等待接口
        return true;
    }

    @Override
    public Boolean evaluateOrderList(OrderListEvaluateReqVO reqVO) {
        // 校验存在
        validateOrderListExists(reqVO.getId());
        // 更新
        OrderListDO updateObj = new OrderListDO();
        updateObj.setId(reqVO.getId());
        updateObj.setEvaluate(reqVO.getEvaluate());
        updateObj.setEvaluateTime(LocalDateTime.now());
        int rows = orderListMapper.updateById(updateObj);
        return rows > 0;
    }

    @Override
    public Boolean stopCharge(OrderListStopChargeReqVO reqVO) {
        // 校验存在
        validateOrderListExists(reqVO.getId());
        // 更新
        OrderListDO updateObj = new OrderListDO();
        updateObj.setId(reqVO.getId());

        // 充电量：如果前端没传或为null，则使用默认值10
        if (reqVO.getChargeAmount() != null) {
            updateObj.setChargeAmount(reqVO.getChargeAmount());
        } else {
            updateObj.setChargeAmount(new BigDecimal("10"));
        }

        // 充电金额：如果前端没传或为null，则使用默认值10
        if (reqVO.getChargeMoney() != null) {
            updateObj.setChargeMoney(reqVO.getChargeMoney());
        } else {
            updateObj.setChargeMoney(new BigDecimal("10"));
        }
        updateObj.setOrderStatus("已完成");
        updateObj.setStopReason(reqVO.getStopReason());
        int rows = orderListMapper.updateById(updateObj);
        return rows > 0;
    }

    @Override
    public OrderListChartRespVO getOrderChart(OrderListChartReqVO reqVO) {
        // 1. 解析时间范围
        LocalDate startDate = reqVO.getStartTime();
        LocalDate endDate = reqVO.getEndTime();
        String timeRange = reqVO.getTimeRange();

        if (startDate == null || endDate == null) {
            LocalDate today = LocalDate.now();
            if ("近7天".equals(timeRange)) {
                startDate = today.minusDays(7);
                endDate = today;
            } else if ("近30天".equals(timeRange)) {
                startDate = today.minusDays(30);
                endDate = today;
            } else if ("本月".equals(timeRange)) {
                startDate = today.withDayOfMonth(1);
                endDate = today;
            } else {
                // 默认近7天
                startDate = today.minusDays(7);
                endDate = today;
            }
        }

        // 2. 查询折线图数据（每日订单数、交易金额）
        List<OrderListChartRespVO.LineData> lineDataList = orderListMapper.selectDailyOrderStats(startDate, endDate);

        // 3. 查询饼图数据（各订单状态数量）
        List<OrderListChartRespVO.PieData> pieDataList = orderListMapper.selectStatusStats();

        // 4. 查询卡片数据
        OrderListChartRespVO.CardData cardData = orderListMapper.selectCardStats(startDate, endDate);

        // 5. 组装返回
        OrderListChartRespVO respVO = new OrderListChartRespVO();
        respVO.setLineData(lineDataList);
        respVO.setPieData(pieDataList);
        respVO.setCardData(cardData);
        return respVO;
    }

    @Override
    public List<OrderListDailyTrendRespVO> getDailyTrend(OrderListDailyTrendReqVO reqVO) {
        LocalDate startDate = reqVO.getStartTime();
        LocalDate endDate = reqVO.getEndTime();
        Long stationId = reqVO.getStationId();
        return orderListMapper.selectDailyTrend(startDate, endDate, stationId);
    }

    @Override
    public List<OrderListStatusRatioRespVO> getStatusRatio(OrderListStatusRatioReqVO reqVO) {
        LocalDate startDate = reqVO.getStartTime();
        LocalDate endDate = reqVO.getEndTime();
        Long stationId = reqVO.getStationId();

        // 1. 查询各状态数量
        List<OrderListStatusRatioRespVO> list = orderListMapper.selectStatusCount(startDate, endDate, stationId);

        // 2. 计算总订单数
        int totalCount = list.stream().mapToInt(OrderListStatusRatioRespVO::getCount).sum();

        // 3. 计算占比（保留两位小数）
        if (totalCount > 0) {
            for (OrderListStatusRatioRespVO vo : list) {
                BigDecimal ratio = BigDecimal.valueOf(vo.getCount())
                        .multiply(BigDecimal.valueOf(100))
                        .divide(BigDecimal.valueOf(totalCount), 2, BigDecimal.ROUND_HALF_UP);
                vo.setRatio(ratio);
            }
        } else {
            // 无数据时，可返回空列表或各状态占比为0
            list.forEach(vo -> vo.setRatio(BigDecimal.ZERO));
        }

        return list;
    }

    // OrderListServiceImpl.java
    @Override
    public TradeCountRespVO getTradeCount(OrderListTradeCountReqVO reqVO) {
        LocalDate startDate = reqVO.getStartTime();
        LocalDate endDate = reqVO.getEndTime();
        Long stationId = reqVO.getStationId();

        // 1. 查询总体统计数据
        TradeCountRespVO respVO = orderListMapper.selectTradeStats(startDate, endDate, stationId);

        // 2. 查询已完成订单数（用于计算完成率）
        Integer completeCount = orderListMapper.selectCompleteOrderCount(startDate, endDate, stationId);
        respVO.setCompleteOrderCount(completeCount != null ? completeCount : 0);

        // 3. 计算完成率
        Integer totalCount = respVO.getTotalOrderCount();
        if (totalCount != null && totalCount > 0) {
            BigDecimal ratio = BigDecimal.valueOf(completeCount)
                    .multiply(BigDecimal.valueOf(100))
                    .divide(BigDecimal.valueOf(totalCount), 2, BigDecimal.ROUND_HALF_UP);
            respVO.setCompleteRatio(ratio);
        } else {
            respVO.setCompleteRatio(BigDecimal.ZERO);
        }

        return respVO;
    }

}