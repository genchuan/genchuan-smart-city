package cn.iocoder.yudao.module.park.service.park.trade.wallet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.wallet.vo.WalletPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.wallet.vo.WalletSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.trade.wallet.WalletDO;
import jakarta.validation.Valid;

/**
 * 用户钱包 Service 接口
 *
 * @author 亘川智城
 */
public interface WalletService {

    /**
     * 创建用户钱包
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWallet(@Valid WalletSaveReqVO createReqVO);

    /**
     * 更新用户钱包
     *
     * @param updateReqVO 更新信息
     */
    void updateWallet(@Valid WalletSaveReqVO updateReqVO);

    /**
     * 删除用户钱包
     *
     * @param id 编号
     */
    void deleteWallet(Long id);

    /**
     * 获得用户钱包
     *
     * @param id 编号
     * @return 用户钱包
     */
    WalletDO getWallet(Long id);

    /**
     * 获得用户钱包分页
     *
     * @param pageReqVO 分页查询
     * @return 用户钱包分页
     */
    PageResult<WalletDO> getWalletPage(WalletPageReqVO pageReqVO);

    WalletDO getByUserId(Long id);
}
