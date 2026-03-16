package cn.iocoder.yudao.module.park.service.park.user.merchant;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.merchant.vo.MerchantPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.merchant.vo.MerchantSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.merchant.MerchantDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.user.merchant.MerchantMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.MERCHANT_NOT_EXISTS;

/**
 * 商户 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MerchantServiceImpl implements MerchantService {

    @Resource
    private MerchantMapper merchantMapper;

    @Override
    public Long createMerchant(MerchantSaveReqVO createReqVO) {
        // 插入
        MerchantDO merchant = BeanUtils.toBean(createReqVO, MerchantDO.class);
        merchantMapper.insert(merchant);
        // 返回
        return merchant.getId();
    }

    @Override
    public void updateMerchant(MerchantSaveReqVO updateReqVO) {
        // 校验存在
        validateMerchantExists(updateReqVO.getId());
        // 更新
        MerchantDO updateObj = BeanUtils.toBean(updateReqVO, MerchantDO.class);
        merchantMapper.updateById(updateObj);
    }

    @Override
    public void deleteMerchant(Long id) {
        // 校验存在
        validateMerchantExists(id);
        // 删除
        merchantMapper.deleteById(id);
    }

    private void validateMerchantExists(Long id) {
        if (merchantMapper.selectById(id) == null) {
            throw exception(MERCHANT_NOT_EXISTS);
        }
    }

    @Override
    public MerchantDO getMerchant(Long id) {
        return merchantMapper.selectById(id);
    }

    @Override
    public PageResult<MerchantDO> getMerchantPage(MerchantPageReqVO pageReqVO) {
        return merchantMapper.selectPage(pageReqVO);
    }

}
