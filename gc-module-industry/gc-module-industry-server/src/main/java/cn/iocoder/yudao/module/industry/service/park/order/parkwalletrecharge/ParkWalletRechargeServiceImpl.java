package cn.iocoder.yudao.module.industry.service.park.order.parkwalletrecharge;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkwalletrecharge.vo.ParkWalletRechargePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkwalletrecharge.vo.ParkWalletRechargeSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkwalletrecharge.ParkWalletRechargeDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.order.parkwalletrecharge.ParkWalletRechargeMapper;
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
 * 钱包充值 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkWalletRechargeServiceImpl implements ParkWalletRechargeService {

    @Resource
    private ParkWalletRechargeMapper parkWalletRechargeMapper;

    @Override
    public Long createParkWalletRecharge(ParkWalletRechargeSaveReqVO createReqVO) {
        // 插入
        ParkWalletRechargeDO parkWalletRecharge = BeanUtils.toBean(createReqVO, ParkWalletRechargeDO.class);
        parkWalletRechargeMapper.insert(parkWalletRecharge);
        // 返回
        return parkWalletRecharge.getId();
    }

    @Override
    public void updateParkWalletRecharge(ParkWalletRechargeSaveReqVO updateReqVO) {
        // 校验存在
        validateParkWalletRechargeExists(updateReqVO.getId());
        // 更新
        ParkWalletRechargeDO updateObj = BeanUtils.toBean(updateReqVO, ParkWalletRechargeDO.class);
        parkWalletRechargeMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkWalletRecharge(Long id) {
        // 校验存在
        validateParkWalletRechargeExists(id);
        // 删除
        parkWalletRechargeMapper.deleteById(id);
    }

    private void validateParkWalletRechargeExists(Long id) {
        if (parkWalletRechargeMapper.selectById(id) == null) {
            throw exception(PARK_WALLET_RECHARGE_NOT_EXISTS);
        }
    }

    @Override
    public ParkWalletRechargeDO getParkWalletRecharge(Long id) {
        return parkWalletRechargeMapper.selectById(id);
    }

    @Override
    public PageResult<ParkWalletRechargeDO> getParkWalletRechargePage(ParkWalletRechargePageReqVO pageReqVO) {
        return parkWalletRechargeMapper.selectPage(pageReqVO);
    }

}
