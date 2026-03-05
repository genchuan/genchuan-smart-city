package cn.iocoder.yudao.module.industry.service.park.discount.parkperiodpackage;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkperiodpackage.vo.ParkPeriodPackagePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkperiodpackage.vo.ParkPeriodPackageSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkperiodpackage.ParkPeriodPackageDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.discount.parkperiodpackage.ParkPeriodPackageMapper;
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
 * 期卡套餐 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkPeriodPackageServiceImpl implements ParkPeriodPackageService {

    @Resource
    private ParkPeriodPackageMapper parkPeriodPackageMapper;

    @Override
    public Long createParkPeriodPackage(ParkPeriodPackageSaveReqVO createReqVO) {
        // 插入
        ParkPeriodPackageDO parkPeriodPackage = BeanUtils.toBean(createReqVO, ParkPeriodPackageDO.class);
        parkPeriodPackageMapper.insert(parkPeriodPackage);
        // 返回
        return parkPeriodPackage.getId();
    }

    @Override
    public void updateParkPeriodPackage(ParkPeriodPackageSaveReqVO updateReqVO) {
        // 校验存在
        validateParkPeriodPackageExists(updateReqVO.getId());
        // 更新
        ParkPeriodPackageDO updateObj = BeanUtils.toBean(updateReqVO, ParkPeriodPackageDO.class);
        parkPeriodPackageMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkPeriodPackage(Long id) {
        // 校验存在
        validateParkPeriodPackageExists(id);
        // 删除
        parkPeriodPackageMapper.deleteById(id);
    }

    private void validateParkPeriodPackageExists(Long id) {
        if (parkPeriodPackageMapper.selectById(id) == null) {
            throw exception(PARK_PERIOD_PACKAGE_NOT_EXISTS);
        }
    }

    @Override
    public ParkPeriodPackageDO getParkPeriodPackage(Long id) {
        return parkPeriodPackageMapper.selectById(id);
    }

    @Override
    public PageResult<ParkPeriodPackageDO> getParkPeriodPackagePage(ParkPeriodPackagePageReqVO pageReqVO) {
        return parkPeriodPackageMapper.selectPage(pageReqVO);
    }

}
