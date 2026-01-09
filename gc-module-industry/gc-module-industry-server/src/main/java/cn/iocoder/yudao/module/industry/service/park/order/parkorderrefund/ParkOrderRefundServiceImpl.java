package cn.iocoder.yudao.module.industry.service.park.order.parkorderrefund;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderrefund.vo.ParkOrderRefundPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderrefund.vo.ParkOrderRefundSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkorderrefund.ParkOrderRefundDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.order.parkorderrefund.ParkOrderRefundMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;



import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.ORDER_REFUND_NOT_EXISTS;


/**
 * 退款订单 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkOrderRefundServiceImpl implements ParkOrderRefundService {

    @Resource
    private ParkOrderRefundMapper orderRefundMapper;

    @Override
    public Long createOrderRefund(ParkOrderRefundSaveReqVO createReqVO) {
        // 插入
        ParkOrderRefundDO orderRefund = BeanUtils.toBean(createReqVO, ParkOrderRefundDO.class);
        orderRefundMapper.insert(orderRefund);
        // 返回
        return orderRefund.getId();
    }

    @Override
    public void updateOrderRefund(ParkOrderRefundSaveReqVO updateReqVO) {
        // 校验存在
        validateOrderRefundExists(updateReqVO.getId());
        // 更新
        ParkOrderRefundDO updateObj = BeanUtils.toBean(updateReqVO, ParkOrderRefundDO.class);
        orderRefundMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderRefund(Long id) {
        // 校验存在
        validateOrderRefundExists(id);
        // 删除
        orderRefundMapper.deleteById(id);
    }

    private void validateOrderRefundExists(Long id) {
        if (orderRefundMapper.selectById(id) == null) {
            throw exception(ORDER_REFUND_NOT_EXISTS);
        }
    }

    @Override
    public ParkOrderRefundDO getOrderRefund(Long id) {
        return orderRefundMapper.selectById(id);
    }

    @Override
    public PageResult<ParkOrderRefundDO> getOrderRefundPage(ParkOrderRefundPageReqVO pageReqVO) {
        return orderRefundMapper.selectPage(pageReqVO);
    }

}
