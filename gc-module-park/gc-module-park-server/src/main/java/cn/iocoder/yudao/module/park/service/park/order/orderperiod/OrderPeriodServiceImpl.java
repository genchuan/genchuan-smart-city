package cn.iocoder.yudao.module.park.service.park.order.orderperiod;

import cn.iocoder.yudao.module.park.controller.admin.park.order.orderperiod.vo.OrderPeriodPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.orderperiod.vo.OrderPeriodSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.orderperiod.OrderPeriodDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.order.orderperiod.OrderPeriodMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.*;

/**
 * 期卡订单 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class OrderPeriodServiceImpl implements OrderPeriodService {

    @Resource
    private OrderPeriodMapper orderPeriodMapper;

    @Override
    public Long createOrderPeriod(OrderPeriodSaveReqVO createReqVO) {
        // 插入
        OrderPeriodDO orderPeriod = BeanUtils.toBean(createReqVO, OrderPeriodDO.class);
        orderPeriodMapper.insert(orderPeriod);
        // 返回
        return orderPeriod.getId();
    }

    @Override
    public void updateOrderPeriod(OrderPeriodSaveReqVO updateReqVO) {
        // 校验存在
        validateOrderPeriodExists(updateReqVO.getId());
        // 更新
        OrderPeriodDO updateObj = BeanUtils.toBean(updateReqVO, OrderPeriodDO.class);
        orderPeriodMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderPeriod(Long id) {
        // 校验存在
        validateOrderPeriodExists(id);
        // 删除
        orderPeriodMapper.deleteById(id);
    }

    private void validateOrderPeriodExists(Long id) {
        if (orderPeriodMapper.selectById(id) == null) {
            throw exception(ORDER_PERIOD_NOT_EXISTS);
        }
    }

    @Override
    public OrderPeriodDO getOrderPeriod(Long id) {
        return orderPeriodMapper.selectById(id);
    }

    @Override
    public PageResult<OrderPeriodDO> getOrderPeriodPage(OrderPeriodPageReqVO pageReqVO) {
        return orderPeriodMapper.selectPage(pageReqVO);
    }

}
