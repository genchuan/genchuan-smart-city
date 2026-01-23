package cn.iocoder.yudao.module.park.service.park.order.ordertemp;

import cn.iocoder.yudao.module.park.controller.admin.park.order.ordertemp.vo.OrderTempPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.ordertemp.vo.OrderTempSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.ordertemp.OrderTempDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.order.ordertemp.OrderTempMapper;
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
 * 临停订单 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class OrderTempServiceImpl implements OrderTempService {

    @Resource
    private OrderTempMapper orderTempMapper;

    @Override
    public Long createOrderTemp(OrderTempSaveReqVO createReqVO) {
        // 插入
        OrderTempDO orderTemp = BeanUtils.toBean(createReqVO, OrderTempDO.class);
        orderTempMapper.insert(orderTemp);
        // 返回
        return orderTemp.getId();
    }

    @Override
    public void updateOrderTemp(OrderTempSaveReqVO updateReqVO) {
        // 校验存在
        validateOrderTempExists(updateReqVO.getId());
        // 更新
        OrderTempDO updateObj = BeanUtils.toBean(updateReqVO, OrderTempDO.class);
        orderTempMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderTemp(Long id) {
        // 校验存在
        validateOrderTempExists(id);
        // 删除
        orderTempMapper.deleteById(id);
    }

    private void validateOrderTempExists(Long id) {
        if (orderTempMapper.selectById(id) == null) {
            throw exception(ORDER_TEMP_NOT_EXISTS);
        }
    }

    @Override
    public OrderTempDO getOrderTemp(Long id) {
        return orderTempMapper.selectById(id);
    }

    @Override
    public PageResult<OrderTempDO> getOrderTempPage(OrderTempPageReqVO pageReqVO) {
        return orderTempMapper.selectPage(pageReqVO);
    }

}
