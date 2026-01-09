package cn.iocoder.yudao.module.industry.service.park.order.parkwalletrecharge;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkwalletrecharge.vo.ParkWalletRechargePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkwalletrecharge.vo.ParkWalletRechargeSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkwalletrecharge.ParkWalletRechargeDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 钱包充值 Service 接口
 *
 * @author lxs
 */
public interface ParkWalletRechargeService {

    /**
     * 创建钱包充值
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkWalletRecharge(@Valid ParkWalletRechargeSaveReqVO createReqVO);

    /**
     * 更新钱包充值
     *
     * @param updateReqVO 更新信息
     */
    void updateParkWalletRecharge(@Valid ParkWalletRechargeSaveReqVO updateReqVO);

    /**
     * 删除钱包充值
     *
     * @param id 编号
     */
    void deleteParkWalletRecharge(Long id);

    /**
     * 获得钱包充值
     *
     * @param id 编号
     * @return 钱包充值
     */
    ParkWalletRechargeDO getParkWalletRecharge(Long id);

    /**
     * 获得钱包充值分页
     *
     * @param pageReqVO 分页查询
     * @return 钱包充值分页
     */
    PageResult<ParkWalletRechargeDO> getParkWalletRechargePage(ParkWalletRechargePageReqVO pageReqVO);

}
