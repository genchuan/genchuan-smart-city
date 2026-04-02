package cn.iocoder.yudao.module.vehiclecharging.service.orderrefund;

import cn.hutool.core.collection.CollUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderrefund.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderrefund.OrderRefundDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.orderrefund.OrderRefundMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.*;

/**
 * 订单退款 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class OrderRefundServiceImpl implements OrderRefundService {

    @Resource
    private OrderRefundMapper orderRefundMapper;

    private static final Logger log = LoggerFactory.getLogger(OrderRefundServiceImpl.class);

    @Override
    public Long createOrderRefund(OrderRefundSaveReqVO createReqVO) {
        // 插入
        OrderRefundDO orderRefund = BeanUtils.toBean(createReqVO, OrderRefundDO.class);
        orderRefundMapper.insert(orderRefund);

        // 返回
        return orderRefund.getId();
    }

    @Override
    public void updateOrderRefund(OrderRefundSaveReqVO updateReqVO) {
        // 校验存在
        validateOrderRefundExists(updateReqVO.getId());
        // 更新
        OrderRefundDO updateObj = BeanUtils.toBean(updateReqVO, OrderRefundDO.class);
        orderRefundMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderRefund(Long id) {
        // 校验存在
        validateOrderRefundExists(id);
        // 删除
        orderRefundMapper.deleteById(id);
    }

    @Override
        public void deleteOrderRefundListByIds(List<Long> ids) {
        // 删除
        orderRefundMapper.deleteByIds(ids);
        }


    private void validateOrderRefundExists(Long id) {
        if (orderRefundMapper.selectById(id) == null) {
            throw exception(ORDER_REFUND_NOT_EXISTS);
        }
    }

    @Override
    public OrderRefundDO getOrderRefund(Long id) {
        return orderRefundMapper.selectById(id);
    }

    @Override
    public PageResult<OrderRefundDO> getOrderRefundPage(OrderRefundPageReqVO pageReqVO) {
        return orderRefundMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void auditOrderRefund(OrderRefundAuditReqVO auditReqVO) {
        // 1. 校验：所有ID对应的记录必须存在，且状态为“待审核” (状态值 0)
        List<OrderRefundDO> refundList = orderRefundMapper.selectByIdsAndRefundStatus(auditReqVO.getIds(), "0");
        if (CollUtil.isEmpty(refundList) || refundList.size() != auditReqVO.getIds().size()) {
            // 如果查出的记录数不等于传入的ID数，说明有ID不存在或状态不是“待审核”
            throw exception("有ID不存在或状态不是“待审核”");
        }

        // 2. 批量更新状态为“审核通过” (状态值 1)
        List<OrderRefundDO> updateList = convertList(refundList, refund -> {
            OrderRefundDO updateObj = new OrderRefundDO();
            updateObj.setId(refund.getId());
            updateObj.setRefundStatus("1"); // 审核通过
            updateObj.setAuditRemark(auditReqVO.getAuditRemark());
            return updateObj;
        });

        // 使用批量更新
        orderRefundMapper.updateBatch(updateList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refundOrderRefund(OrderRefundRefundReqVO refundReqVO) {
        // 1. 校验：所有ID对应的记录必须存在，且状态为"审核通过" (状态值 1)
        List<OrderRefundDO> refundList = orderRefundMapper.selectByIdsAndRefundStatus(refundReqVO.getIds(), "1");
        if (CollUtil.isEmpty(refundList) || refundList.size() != refundReqVO.getIds().size()) {
            throw exception("有ID不存在或状态不是“审核通过”");
        }

        // 2. 批量更新状态为"退款中" (状态值 2)
        List<OrderRefundDO> updateList = convertList(refundList, refund -> {
            OrderRefundDO updateObj = new OrderRefundDO();
            updateObj.setId(refund.getId());
            updateObj.setRefundStatus("2"); // 退款中
            return updateObj;
        });
        orderRefundMapper.updateBatch(updateList);

        // 3. 调用支付接口执行退款（异步处理）
        refundList.forEach(orderRefund -> {
            try {
                // 调用支付服务退款接口
//                paymentService.refund(orderRefund.getRefundAmount(), orderRefund.getOrderCode());

                // 4. 退款成功后更新状态为"已完成" (状态值 3)
                OrderRefundDO completedObj = new OrderRefundDO();
                completedObj.setId(orderRefund.getId());
                completedObj.setRefundStatus("3"); // 已完成
                completedObj.setRefundTime(LocalDateTime.now());
                orderRefundMapper.updateById(completedObj);
            } catch (Exception e) {
                // 处理退款失败情况
                log.error("退款失败，订单ID: {}", orderRefund.getId(), e);
            }
        });
    }

    // 在 OrderRefundServiceImpl.java 文件中添加以下方法
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void rejectOrderRefund(OrderRefundRejectReqVO rejectReqVO) {
        // 1. 校验：记录必须存在，且状态为"待审核" (状态值 0)
        OrderRefundDO orderRefund = orderRefundMapper.selectById(rejectReqVO.getId());
        if (orderRefund == null) {
            throw exception(ORDER_REFUND_NOT_EXISTS);
        }
        if (!"0".equals(orderRefund.getRefundStatus())) {
            throw exception("订单退款驳回失败：当前状态非'待审核'，无法驳回");
        }

        // 2. 更新状态为"已驳回" (状态值 4)，并设置驳回原因
        OrderRefundDO updateObj = new OrderRefundDO();
        updateObj.setId(rejectReqVO.getId());
        updateObj.setRefundStatus("4"); // 已驳回
        updateObj.setAuditRemark(rejectReqVO.getRejectReason()); // 使用驳回原因更新审核备注字段
        orderRefundMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reapplyOrderRefund(OrderRefundReapplyReqVO reapplyReqVO) {
        // 1. 校验：原退款申请记录必须存在，且状态为"已驳回" (状态值 4)
        OrderRefundDO originalRefund = orderRefundMapper.selectById(reapplyReqVO.getId());
        if (originalRefund == null) {
            throw exception(ORDER_REFUND_NOT_EXISTS);
        }
        if (!"4".equals(originalRefund.getRefundStatus())) {
            throw exception("订单退款重新申请失败：原退款申请状态非'已驳回'，无法重新申请");
        }

        // 2. 创建新的退款申请记录，状态为"待审核" (状态值 0)
        OrderRefundDO newRefund = new OrderRefundDO();
        // 复制原记录的部分信息
        newRefund.setOrderCode(originalRefund.getOrderCode());
        newRefund.setUserId(originalRefund.getUserId());
        newRefund.setPlateNo(originalRefund.getPlateNo());
        // 设置新的退款金额和原因
        newRefund.setRefundAmount(reapplyReqVO.getRefundAmount());
        newRefund.setRefundReason(reapplyReqVO.getRefundReason());
        // 设置状态为待审核
        newRefund.setRefundStatus("0"); // 待审核
        // 其他字段根据需要设置，例如清空审核相关的字段
        newRefund.setAuditUser(null);
        newRefund.setAuditTime(null);
        newRefund.setAuditRemark(null);
        newRefund.setRefundTime(null);
        newRefund.setRefundChannel(null);
        // 生成新的退款编号（这里需要根据业务规则生成，例如使用序列号或UUID）
        newRefund.setRefundCode(generateRefundCode());

        // 3. 保存新的退款申请记录
        orderRefundMapper.insert(newRefund);
    }

    // 生成退款编号的方法（示例）
    private String generateRefundCode() {
        // 实际项目中根据业务规则生成，例如：前缀+时间戳+序列号
        return "REFUND-" + System.currentTimeMillis();
    }

    @Override
    public OrderRefundChartRespVO getOrderRefundChart(OrderRefundChartReqVO chartReqVO) {
        OrderRefundChartRespVO respVO = new OrderRefundChartRespVO();

        // 1. 获取折线图数据（按日期统计）
        List<OrderRefundChartRespVO.LineData> lineData = orderRefundMapper.selectLineDataByDate(chartReqVO);
        respVO.setLineData(lineData);

        // 2. 获取饼图数据（按状态统计）
        List<OrderRefundChartRespVO.PieData> pieData = orderRefundMapper.selectPieDataByStatus();
        respVO.setPieData(pieData);

        // 3. 获取卡片数据（总体统计）
        OrderRefundChartRespVO.CardData cardData = orderRefundMapper.selectCardData();
        respVO.setCardData(cardData);

        return respVO;
    }

}