package cn.iocoder.yudao.module.industry.service.park.vas.parkspaceshareorder;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshareorder.vo.ParkSpaceShareOrderPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshareorder.vo.ParkSpaceShareOrderSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkspaceshareorder.ParkSpaceShareOrderDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.vas.parkspaceshareorder.ParkSpaceShareOrderMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 车位共享订单 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkSpaceShareOrderServiceImpl implements ParkSpaceShareOrderService {

    @Resource
    private ParkSpaceShareOrderMapper parkSpaceShareOrderMapper;

    @Override
    public Long createParkSpaceShareOrder(ParkSpaceShareOrderSaveReqVO createReqVO) {
        // 插入
        ParkSpaceShareOrderDO parkSpaceShareOrder = BeanUtils.toBean(createReqVO, ParkSpaceShareOrderDO.class);
        parkSpaceShareOrderMapper.insert(parkSpaceShareOrder);
        // 返回
        return parkSpaceShareOrder.getId();
    }

    @Override
    public void updateParkSpaceShareOrder(ParkSpaceShareOrderSaveReqVO updateReqVO) {
        // 校验存在
        validateParkSpaceShareOrderExists(updateReqVO.getId());
        // 更新
        ParkSpaceShareOrderDO updateObj = BeanUtils.toBean(updateReqVO, ParkSpaceShareOrderDO.class);
        parkSpaceShareOrderMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkSpaceShareOrder(Long id) {
        // 校验存在
        validateParkSpaceShareOrderExists(id);
        // 删除
        parkSpaceShareOrderMapper.deleteById(id);
    }

    private void validateParkSpaceShareOrderExists(Long id) {
        if (parkSpaceShareOrderMapper.selectById(id) == null) {
            throw exception(PARK_SPACE_SHARE_ORDER_NOT_EXISTS);
        }
    }

    @Override
    public ParkSpaceShareOrderDO getParkSpaceShareOrder(Long id) {
        return parkSpaceShareOrderMapper.selectById(id);
    }

    @Override
    public PageResult<ParkSpaceShareOrderDO> getParkSpaceShareOrderPage(ParkSpaceShareOrderPageReqVO pageReqVO) {
        return parkSpaceShareOrderMapper.selectPage(pageReqVO);
    }

}
