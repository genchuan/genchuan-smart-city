package cn.iocoder.yudao.module.vehiclecharging.service.orderlist;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
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

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public Boolean refundApply(OrderListRefundApplyReqVO reqVO) {
//        Long orderId = reqVO.getId();
//
//        // 1. 查询订单
//        OrderListDO order = orderListMapper.selectById(orderId);
//        if (order == null) {
//            throw new RuntimeException("订单不存在");
//        }
//
//        // 2. 状态校验：仅“已支付”支付状态，且订单状态为“已支付”或“已完成”
//        // 注意：字典值需根据实际配置调整，以下假设“已支付”对应字典码 "PAID"，订单状态“已完成”对应 "COMPLETED"
//        String payStatus = order.getPayStatus();     // 支付状态
//        String orderStatus = order.getOrderStatus(); // 订单状态
//        if (!"PAID".equals(payStatus) || (!"PAID".equals(orderStatus) && !"COMPLETED".equals(orderStatus))) {
//            throw new RuntimeException("只有已支付或已完成状态的订单才能申请退款");
//        }
//
//        // 3. 校验退款金额不超过订单金额（可选）
//        if (reqVO.getRefundAmount().compareTo(order.getChargeMoney()) > 0) {
//            throw new RuntimeException("退款金额不能超过订单金额");
//        }
//
//        // 4. 创建退款记录，等待接口
//        OrderRefundDO refund = new OrderRefundDO();
//        refund.setOrderId(orderId);
//        refund.setOrderCode(order.getOrderCode());
//        refund.setRefundAmount(reqVO.getRefundAmount());
//        refund.setRefundReason(reqVO.getRefundReason());
//        refund.setRefundStatus("待审核");      // 待审核
//        refund.setTenantId(order.getTenantId()); // 继承租户
//        refund.setCreateTime(LocalDateTime.now());
//        refund.setCreator(SecurityUtils.getLoginUser().getUsername()); // 获取当前登录用户
//
//        orderRefundMapper.insert(refund);
//
//        // 5. 可选：更新订单状态为“退款中”（根据业务决定）
//        order.setOrderStatus("REFUNDING");
//        orderListMapper.updateById(order);
//
//        log.info("退款申请成功，订单ID：{}，退款单ID：{}", orderId, refund.getId());//等待接口
//        return true;
//    }

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

}