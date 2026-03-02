package cn.iocoder.yudao.module.park.service.park.trade.walletflow;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.walletflow.vo.WalletFlowPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.walletflow.vo.WalletFlowSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.trade.walletflow.WalletFlowDO;
import jakarta.validation.Valid;

/**
 * 钱包流水 Service 接口
 *
 * @author 亘川智城
 */
public interface WalletFlowService {

    /**
     * 创建钱包流水
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWalletFlow(@Valid WalletFlowSaveReqVO createReqVO);

    /**
     * 更新钱包流水
     *
     * @param updateReqVO 更新信息
     */
    void updateWalletFlow(@Valid WalletFlowSaveReqVO updateReqVO);

    /**
     * 删除钱包流水
     *
     * @param id 编号
     */
    void deleteWalletFlow(Long id);

    /**
     * 获得钱包流水
     *
     * @param id 编号
     * @return 钱包流水
     */
    WalletFlowDO getWalletFlow(Long id);

    /**
     * 获得钱包流水分页
     *
     * @param pageReqVO 分页查询
     * @return 钱包流水分页
     */
    PageResult<WalletFlowDO> getWalletFlowPage(WalletFlowPageReqVO pageReqVO);

}
