package cn.iocoder.yudao.module.industry.service.park.order.parkorderperiod;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderperiod.vo.ParkOrderPeriodPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderperiod.vo.ParkOrderPeriodSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkorderperiod.ParkOrderPeriodDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.order.parkorderperiod.ParkOrderPeriodMapper;
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
 * 期卡订单 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkOrderPeriodServiceImpl implements ParkOrderPeriodService {

    @Resource
    private ParkOrderPeriodMapper parkOrderPeriodMapper;

    @Override
    public Long createParkOrderPeriod(ParkOrderPeriodSaveReqVO createReqVO) {
        // 插入
        ParkOrderPeriodDO parkOrderPeriod = BeanUtils.toBean(createReqVO, ParkOrderPeriodDO.class);
        parkOrderPeriodMapper.insert(parkOrderPeriod);
        // 返回
        return parkOrderPeriod.getId();
    }

    @Override
    public void updateParkOrderPeriod(ParkOrderPeriodSaveReqVO updateReqVO) {
        // 校验存在
        validateParkOrderPeriodExists(updateReqVO.getId());
        // 更新
        ParkOrderPeriodDO updateObj = BeanUtils.toBean(updateReqVO, ParkOrderPeriodDO.class);
        parkOrderPeriodMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkOrderPeriod(Long id) {
        // 校验存在
        validateParkOrderPeriodExists(id);
        // 删除
        parkOrderPeriodMapper.deleteById(id);
    }

    private void validateParkOrderPeriodExists(Long id) {
        if (parkOrderPeriodMapper.selectById(id) == null) {
            throw exception(PARK_ORDER_PERIOD_NOT_EXISTS);
        }
    }

    @Override
    public ParkOrderPeriodDO getParkOrderPeriod(Long id) {
        return parkOrderPeriodMapper.selectById(id);
    }

    @Override
    public PageResult<ParkOrderPeriodDO> getParkOrderPeriodPage(ParkOrderPeriodPageReqVO pageReqVO) {
        return parkOrderPeriodMapper.selectPage(pageReqVO);
    }

}
