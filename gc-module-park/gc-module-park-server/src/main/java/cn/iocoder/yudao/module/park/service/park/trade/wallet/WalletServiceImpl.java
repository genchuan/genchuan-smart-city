package cn.iocoder.yudao.module.park.service.park.trade.wallet;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.wallet.vo.WalletPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.wallet.vo.WalletSaveReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.walletflow.vo.WalletFlowPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.trade.wallet.WalletDO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.trade.walletflow.WalletFlowDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.trade.wallet.WalletMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.WALLET_NOT_EXISTS;

/**
 * 用户钱包 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class WalletServiceImpl implements WalletService {

    @Resource
    private WalletMapper walletMapper;

    @Override
    public Long createWallet(WalletSaveReqVO createReqVO) {
        // 插入
        WalletDO wallet = BeanUtils.toBean(createReqVO, WalletDO.class);
        walletMapper.insert(wallet);
        // 返回
        return wallet.getId();
    }

    @Override
    public void updateWallet(WalletSaveReqVO updateReqVO) {
        // 校验存在
        validateWalletExists(updateReqVO.getId());
        // 更新
        WalletDO updateObj = BeanUtils.toBean(updateReqVO, WalletDO.class);
        walletMapper.updateById(updateObj);
    }

    @Override
    public void deleteWallet(Long id) {
        // 校验存在
        validateWalletExists(id);
        // 删除
        walletMapper.deleteById(id);
    }

    private void validateWalletExists(Long id) {
        if (walletMapper.selectById(id) == null) {
            throw exception(WALLET_NOT_EXISTS);
        }
    }

    @Override
    public WalletDO getWallet(Long id) {
        return walletMapper.selectById(id);
    }

    @Override
    public PageResult<WalletDO> getWalletPage(WalletPageReqVO pageReqVO) {
        return walletMapper.selectPage(pageReqVO);
    }


    @Override
    public WalletDO getByUserId(Long id) {
        Long userId = getLoginUserId();
        if (userId == null) {
            throw exception(new ErrorCode(500,"用户ID不能为空"));
        }

        // 使用 LambdaQueryWrapper 查询钱包
        LambdaQueryWrapper<WalletDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WalletDO::getUserId, userId)
                .eq(WalletDO::getDeleted, false); // 排除已删除的

        return walletMapper.selectOne(queryWrapper);
    }



}
