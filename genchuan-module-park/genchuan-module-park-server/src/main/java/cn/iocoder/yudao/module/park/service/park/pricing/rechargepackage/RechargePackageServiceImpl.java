package cn.iocoder.yudao.module.park.service.park.pricing.rechargepackage;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.rechargepackage.vo.RechargePackagePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.rechargepackage.vo.RechargePackageSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.rechargepackage.RechargePackageDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.pricing.rechargepackage.RechargePackageMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.RECHARGE_PACKAGE_NOT_EXISTS;

/**
 * 充值套餐 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class RechargePackageServiceImpl implements RechargePackageService {

    @Resource
    private RechargePackageMapper rechargePackageMapper;

    @Override
    public Long createRechargePackage(RechargePackageSaveReqVO createReqVO) {
        // 插入
        RechargePackageDO rechargePackage = BeanUtils.toBean(createReqVO, RechargePackageDO.class);
        rechargePackageMapper.insert(rechargePackage);
        // 返回
        return rechargePackage.getId();
    }

    @Override
    public void updateRechargePackage(RechargePackageSaveReqVO updateReqVO) {
        // 校验存在
        validateRechargePackageExists(updateReqVO.getId());
        // 更新
        RechargePackageDO updateObj = BeanUtils.toBean(updateReqVO, RechargePackageDO.class);
        rechargePackageMapper.updateById(updateObj);
    }

    @Override
    public void deleteRechargePackage(Long id) {
        // 校验存在
        validateRechargePackageExists(id);
        // 删除
        rechargePackageMapper.deleteById(id);
    }

    private void validateRechargePackageExists(Long id) {
        if (rechargePackageMapper.selectById(id) == null) {
            throw exception(RECHARGE_PACKAGE_NOT_EXISTS);
        }
    }

    @Override
    public RechargePackageDO getRechargePackage(Long id) {
        return rechargePackageMapper.selectById(id);
    }

    @Override
    public PageResult<RechargePackageDO> getRechargePackagePage(RechargePackagePageReqVO pageReqVO) {
        return rechargePackageMapper.selectPage(pageReqVO);
    }

}
