package cn.iocoder.yudao.module.industry.service.park.discount.parkfeestrategy;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeestrategy.vo.ParkFeeStrategyPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeestrategy.vo.ParkFeeStrategySaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkfeestrategy.ParkFeeStrategyDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.discount.parkfeestrategy.ParkFeeStrategyMapper;
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
 * 费率策略 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkFeeStrategyServiceImpl implements ParkFeeStrategyService {

    @Resource
    private ParkFeeStrategyMapper parkFeeStrategyMapper;

    @Override
    public Long createParkFeeStrategy(ParkFeeStrategySaveReqVO createReqVO) {
        // 插入
        ParkFeeStrategyDO parkFeeStrategy = BeanUtils.toBean(createReqVO, ParkFeeStrategyDO.class);
        parkFeeStrategyMapper.insert(parkFeeStrategy);
        // 返回
        return parkFeeStrategy.getId();
    }

    @Override
    public void updateParkFeeStrategy(ParkFeeStrategySaveReqVO updateReqVO) {
        // 校验存在
        validateParkFeeStrategyExists(updateReqVO.getId());
        // 更新
        ParkFeeStrategyDO updateObj = BeanUtils.toBean(updateReqVO, ParkFeeStrategyDO.class);
        parkFeeStrategyMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkFeeStrategy(Long id) {
        // 校验存在
        validateParkFeeStrategyExists(id);
        // 删除
        parkFeeStrategyMapper.deleteById(id);
    }

    private void validateParkFeeStrategyExists(Long id) {
        if (parkFeeStrategyMapper.selectById(id) == null) {
            throw exception(PARK_FEE_STRATEGY_NOT_EXISTS);
        }
    }

    @Override
    public ParkFeeStrategyDO getParkFeeStrategy(Long id) {
        return parkFeeStrategyMapper.selectById(id);
    }

    @Override
    public PageResult<ParkFeeStrategyDO> getParkFeeStrategyPage(ParkFeeStrategyPageReqVO pageReqVO) {
        return parkFeeStrategyMapper.selectPage(pageReqVO);
    }

}
