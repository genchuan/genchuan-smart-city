package cn.iocoder.yudao.module.industry.service.park.order.parkorderescape;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderescape.vo.ParkOrderEscapePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderescape.vo.ParkOrderEscapeSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkorderescape.ParkOrderEscapeDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.order.parkorderescape.ParkOrderEscapeMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;



import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.ORDER_ESCAPE_NOT_EXISTS;


/**
 * 逃费订单 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkOrderEscapeServiceImpl implements ParkOrderEscapeService {

    @Resource
    private ParkOrderEscapeMapper orderEscapeMapper;

    @Override
    public Long createOrderEscape(ParkOrderEscapeSaveReqVO createReqVO) {
        // 插入
        ParkOrderEscapeDO orderEscape = BeanUtils.toBean(createReqVO, ParkOrderEscapeDO.class);
        orderEscapeMapper.insert(orderEscape);
        // 返回
        return orderEscape.getId();
    }

    @Override
    public void updateOrderEscape(ParkOrderEscapeSaveReqVO updateReqVO) {
        // 校验存在
        validateOrderEscapeExists(updateReqVO.getId());
        // 更新
        ParkOrderEscapeDO updateObj = BeanUtils.toBean(updateReqVO, ParkOrderEscapeDO.class);
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
    public ParkOrderEscapeDO getOrderEscape(Long id) {
        return orderEscapeMapper.selectById(id);
    }

    @Override
    public PageResult<ParkOrderEscapeDO> getOrderEscapePage(ParkOrderEscapePageReqVO pageReqVO) {
        return orderEscapeMapper.selectPage(pageReqVO);
    }

}
