package cn.iocoder.yudao.module.industry.service.park.discount.parkrechargepackage;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkrechargepackage.vo.ParkRechargePackagePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkrechargepackage.vo.ParkRechargePackageSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkrechargepackage.ParkRechargePackageDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.discount.parkrechargepackage.ParkRechargePackageMapper;
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
 * 充值套餐 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkRechargePackageServiceImpl implements ParkRechargePackageService {

    @Resource
    private ParkRechargePackageMapper parkRechargePackageMapper;

    @Override
    public Long createParkRechargePackage(ParkRechargePackageSaveReqVO createReqVO) {
        // 插入
        ParkRechargePackageDO parkRechargePackage = BeanUtils.toBean(createReqVO, ParkRechargePackageDO.class);
        parkRechargePackageMapper.insert(parkRechargePackage);
        // 返回
        return parkRechargePackage.getId();
    }

    @Override
    public void updateParkRechargePackage(ParkRechargePackageSaveReqVO updateReqVO) {
        // 校验存在
        validateParkRechargePackageExists(updateReqVO.getId());
        // 更新
        ParkRechargePackageDO updateObj = BeanUtils.toBean(updateReqVO, ParkRechargePackageDO.class);
        parkRechargePackageMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkRechargePackage(Long id) {
        // 校验存在
        validateParkRechargePackageExists(id);
        // 删除
        parkRechargePackageMapper.deleteById(id);
    }

    private void validateParkRechargePackageExists(Long id) {
        if (parkRechargePackageMapper.selectById(id) == null) {
            throw exception(PARK_RECHARGE_PACKAGE_NOT_EXISTS);
        }
    }

    @Override
    public ParkRechargePackageDO getParkRechargePackage(Long id) {
        return parkRechargePackageMapper.selectById(id);
    }

    @Override
    public PageResult<ParkRechargePackageDO> getParkRechargePackagePage(ParkRechargePackagePageReqVO pageReqVO) {
        return parkRechargePackageMapper.selectPage(pageReqVO);
    }

}
