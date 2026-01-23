package cn.iocoder.yudao.module.park.service.park.order.orderescape;

import cn.iocoder.yudao.module.park.controller.admin.park.order.orderescape.vo.OrderEscapePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.orderescape.vo.OrderEscapeSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.orderescape.OrderEscapeDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.order.orderescape.OrderEscapeMapper;
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
 * 逃费订单 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class OrderEscapeServiceImpl implements OrderEscapeService {

    @Resource
    private OrderEscapeMapper orderEscapeMapper;

    @Override
    public Long createOrderEscape(OrderEscapeSaveReqVO createReqVO) {
        // 插入
        OrderEscapeDO orderEscape = BeanUtils.toBean(createReqVO, OrderEscapeDO.class);
        orderEscapeMapper.insert(orderEscape);
        // 返回
        return orderEscape.getId();
    }

    @Override
    public void updateOrderEscape(OrderEscapeSaveReqVO updateReqVO) {
        // 校验存在
        validateOrderEscapeExists(updateReqVO.getId());
        // 更新
        OrderEscapeDO updateObj = BeanUtils.toBean(updateReqVO, OrderEscapeDO.class);
        orderEscapeMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderEscape(Long id) {
        // 校验存在
        validateOrderEscapeExists(id);
        // 删除
        orderEscapeMapper.deleteById(id);
    }

    private void validateOrderEscapeExists(Long id) {
        if (orderEscapeMapper.selectById(id) == null) {
            throw exception(ORDER_ESCAPE_NOT_EXISTS);
        }
    }

    @Override
    public OrderEscapeDO getOrderEscape(Long id) {
        return orderEscapeMapper.selectById(id);
    }

    @Override
    public PageResult<OrderEscapeDO> getOrderEscapePage(OrderEscapePageReqVO pageReqVO) {
        return orderEscapeMapper.selectPage(pageReqVO);
    }

}
