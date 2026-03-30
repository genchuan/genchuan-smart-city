package cn.iocoder.yudao.module.park.service.park.pricing.periodpackage;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.periodpackage.vo.PeriodPackagePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.periodpackage.vo.PeriodPackageSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.periodpackage.PeriodPackageDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.pricing.periodpackage.PeriodPackageMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.PERIOD_PACKAGE_NOT_EXISTS;

/**
 * 期卡套餐 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PeriodPackageServiceImpl implements PeriodPackageService {

    @Resource
    private PeriodPackageMapper periodPackageMapper;

    @Override
    public Long createPeriodPackage(PeriodPackageSaveReqVO createReqVO) {
        // 插入
        PeriodPackageDO periodPackage = BeanUtils.toBean(createReqVO, PeriodPackageDO.class);
        periodPackageMapper.insert(periodPackage);
        // 返回
        return periodPackage.getId();
    }

    @Override
    public void updatePeriodPackage(PeriodPackageSaveReqVO updateReqVO) {
        // 校验存在
        validatePeriodPackageExists(updateReqVO.getId());
        // 更新
        PeriodPackageDO updateObj = BeanUtils.toBean(updateReqVO, PeriodPackageDO.class);
        periodPackageMapper.updateById(updateObj);
    }

    @Override
    public void deletePeriodPackage(Long id) {
        // 校验存在
        validatePeriodPackageExists(id);
        // 删除
        periodPackageMapper.deleteById(id);
    }

    private void validatePeriodPackageExists(Long id) {
        if (periodPackageMapper.selectById(id) == null) {
            throw exception(PERIOD_PACKAGE_NOT_EXISTS);
        }
    }

    @Override
    public PeriodPackageDO getPeriodPackage(Long id) {
        return periodPackageMapper.selectById(id);
    }

    @Override
    public PageResult<PeriodPackageDO> getPeriodPackagePage(PeriodPackagePageReqVO pageReqVO) {
        return periodPackageMapper.selectPage(pageReqVO);
    }

}
