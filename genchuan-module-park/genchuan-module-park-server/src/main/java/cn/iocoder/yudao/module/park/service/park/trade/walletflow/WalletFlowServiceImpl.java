package cn.iocoder.yudao.module.park.service.park.trade.walletflow;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.walletflow.vo.WalletFlowPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.walletflow.vo.WalletFlowSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.trade.walletflow.WalletFlowDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.trade.walletflow.WalletFlowMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.WALLET_FLOW_NOT_EXISTS;

/**
 * 钱包流水 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class WalletFlowServiceImpl implements WalletFlowService {

    @Resource
    private WalletFlowMapper walletFlowMapper;

    @Override
    public Long createWalletFlow(WalletFlowSaveReqVO createReqVO) {
        // 插入
        WalletFlowDO walletFlow = BeanUtils.toBean(createReqVO, WalletFlowDO.class);
        walletFlowMapper.insert(walletFlow);
        // 返回
        return walletFlow.getId();
    }

    @Override
    public void updateWalletFlow(WalletFlowSaveReqVO updateReqVO) {
        // 校验存在
        validateWalletFlowExists(updateReqVO.getId());
        // 更新
        WalletFlowDO updateObj = BeanUtils.toBean(updateReqVO, WalletFlowDO.class);
        walletFlowMapper.updateById(updateObj);
    }

    @Override
    public void deleteWalletFlow(Long id) {
        // 校验存在
        validateWalletFlowExists(id);
        // 删除
        walletFlowMapper.deleteById(id);
    }

    private void validateWalletFlowExists(Long id) {
        if (walletFlowMapper.selectById(id) == null) {
            throw exception(WALLET_FLOW_NOT_EXISTS);
        }
    }

    @Override
    public WalletFlowDO getWalletFlow(Long id) {
        return walletFlowMapper.selectById(id);
    }

    @Override
    public PageResult<WalletFlowDO> getWalletFlowPage(WalletFlowPageReqVO pageReqVO) {
        return walletFlowMapper.selectPage(pageReqVO);
    }

}
