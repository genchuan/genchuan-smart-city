package cn.iocoder.yudao.module.facility.service.manhole.disposalorder;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.facility.controller.admin.manhole.disposalorder.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.disposalorder.DisposalOrderDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.facility.dal.mysql.manhole.disposalorder.DisposalOrderMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.*;

/**
 * 处置工单 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class DisposalOrderServiceImpl implements DisposalOrderService {

    @Resource
    private DisposalOrderMapper orderMapper;

    @Override
    public Long createOrder(DisposalOrderSaveReqVO createReqVO) {
        // 插入
        DisposalOrderDO order = BeanUtils.toBean(createReqVO, DisposalOrderDO.class);
        orderMapper.insert(order);
        // 返回
        return order.getId();
    }

    @Override
    public void updateOrder(DisposalOrderSaveReqVO updateReqVO) {
        // 校验存在
        validateOrderExists(updateReqVO.getId());
        // 更新
        DisposalOrderDO updateObj = BeanUtils.toBean(updateReqVO, DisposalOrderDO.class);
        orderMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrder(Long id) {
        // 校验存在
        validateOrderExists(id);
        // 删除
        orderMapper.deleteById(id);
    }

    private void validateOrderExists(Long id) {
        if (orderMapper.selectById(id) == null) {
            throw exception(ORDER_NOT_EXISTS);
        }
    }

    @Override
    public DisposalOrderDO getOrder(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public PageResult<DisposalOrderDO> getOrderPage(DisposalOrderPageReqVO pageReqVO) {
        return orderMapper.selectPage(pageReqVO);
    }

}