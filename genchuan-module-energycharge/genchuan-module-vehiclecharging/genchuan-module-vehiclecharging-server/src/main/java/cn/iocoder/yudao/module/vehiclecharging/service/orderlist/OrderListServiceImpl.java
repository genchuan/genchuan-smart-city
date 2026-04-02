package cn.iocoder.yudao.module.vehiclecharging.service.orderlist;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderlist.OrderListDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.orderlist.OrderListMapper;

import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.*;

/**
 * 订单列表 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class OrderListServiceImpl implements OrderListService {

    @Resource
    private OrderListMapper orderListMapper;

    @Override
    public Long createOrderList(OrderListSaveReqVO createReqVO) {
        // 插入
        OrderListDO orderList = BeanUtils.toBean(createReqVO, OrderListDO.class);
        orderListMapper.insert(orderList);

        // 返回
        return orderList.getId();
    }

    @Override
    public void updateOrderList(OrderListSaveReqVO updateReqVO) {
        // 校验存在
        validateOrderListExists(updateReqVO.getId());
        // 更新
        OrderListDO updateObj = BeanUtils.toBean(updateReqVO, OrderListDO.class);
        orderListMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderList(Long id) {
        // 校验存在
        validateOrderListExists(id);
        // 删除
        orderListMapper.deleteById(id);
    }

    @Override
        public void deleteOrderListListByIds(List<Long> ids) {
        // 删除
        orderListMapper.deleteByIds(ids);
        }


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

}