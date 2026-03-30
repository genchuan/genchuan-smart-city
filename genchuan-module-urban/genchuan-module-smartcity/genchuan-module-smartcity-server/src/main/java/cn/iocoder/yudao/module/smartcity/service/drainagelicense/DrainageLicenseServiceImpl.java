package cn.iocoder.yudao.module.smartcity.service.drainagelicense;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.drainagelicense.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.drainagelicense.DrainageLicenseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.drainagelicense.DrainageLicenseMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 排水电子许可证信息 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class DrainageLicenseServiceImpl implements DrainageLicenseService {

    @Resource
    private DrainageLicenseMapper drainageLicenseMapper;

    @Override
    public Long createDrainageLicense(DrainageLicenseSaveReqVO createReqVO) {
        // 插入
        DrainageLicenseDO drainageLicense = BeanUtils.toBean(createReqVO, DrainageLicenseDO.class);
        drainageLicenseMapper.insert(drainageLicense);
        // 返回
        return drainageLicense.getId();
    }

    @Override
    public void updateDrainageLicense(DrainageLicenseSaveReqVO updateReqVO) {
        // 校验存在
        validateDrainageLicenseExists(updateReqVO.getId());
        // 更新
        DrainageLicenseDO updateObj = BeanUtils.toBean(updateReqVO, DrainageLicenseDO.class);
        drainageLicenseMapper.updateById(updateObj);
    }

    @Override
    public void deleteDrainageLicense(Long id) {
        // 校验存在
        validateDrainageLicenseExists(id);
        // 删除
        drainageLicenseMapper.deleteById(id);
    }

    private void validateDrainageLicenseExists(Long id) {
        if (drainageLicenseMapper.selectById(id) == null) {
            throw exception(DRAINAGE_LICENSE_NOT_EXISTS);
        }
    }

    @Override
    public DrainageLicenseDO getDrainageLicense(Long id) {
        return drainageLicenseMapper.selectById(id);
    }

    @Override
    public PageResult<DrainageLicenseDO> getDrainageLicensePage(DrainageLicensePageReqVO pageReqVO) {
        return drainageLicenseMapper.selectPage(pageReqVO);
    }

}