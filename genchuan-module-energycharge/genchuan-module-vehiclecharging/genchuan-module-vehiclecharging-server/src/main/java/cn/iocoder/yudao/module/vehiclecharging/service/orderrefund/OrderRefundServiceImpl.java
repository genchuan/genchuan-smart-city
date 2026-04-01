package cn.iocoder.yudao.module.vehiclecharging.service.orderrefund;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

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

}