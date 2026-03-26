package cn.iocoder.yudao.module.waterdetection.service.waterbalance;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.waterbalance.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterbalance.WaterBalanceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.waterbalance.WaterBalanceMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 水量平衡与漏损分析 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class WaterBalanceServiceImpl implements WaterBalanceService {

    @Resource
    private WaterBalanceMapper waterBalanceMapper;

    @Override
    public Long createWaterBalance(WaterBalanceSaveReqVO createReqVO) {
        // 插入
        WaterBalanceDO waterBalance = BeanUtils.toBean(createReqVO, WaterBalanceDO.class);
        waterBalanceMapper.insert(waterBalance);
        // 返回
        return waterBalance.getId();
    }

    @Override
    public void updateWaterBalance(WaterBalanceSaveReqVO updateReqVO) {
        // 校验存在
        validateWaterBalanceExists(updateReqVO.getId());
        // 更新
        WaterBalanceDO updateObj = BeanUtils.toBean(updateReqVO, WaterBalanceDO.class);
        waterBalanceMapper.updateById(updateObj);
    }

    @Override
    public void deleteWaterBalance(Long id) {
        // 校验存在
        validateWaterBalanceExists(id);
        // 删除
        waterBalanceMapper.deleteById(id);
    }

    private void validateWaterBalanceExists(Long id) {
        if (waterBalanceMapper.selectById(id) == null) {
            throw exception(WATER_BALANCE_NOT_EXISTS);
        }
    }

    @Override
    public WaterBalanceDO getWaterBalance(Long id) {
        return waterBalanceMapper.selectById(id);
    }

    @Override
    public PageResult<WaterBalanceDO> getWaterBalancePage(WaterBalancePageReqVO pageReqVO) {
        return waterBalanceMapper.selectPage(pageReqVO);
    }

}