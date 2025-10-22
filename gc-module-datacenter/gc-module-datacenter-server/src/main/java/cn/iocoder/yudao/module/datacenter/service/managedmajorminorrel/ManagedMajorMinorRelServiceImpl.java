package cn.iocoder.yudao.module.datacenter.service.managedmajorminorrel;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.managedmajorminorrel.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.managedmajorminorrel.ManagedMajorMinorRelDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.managedmajorminorrel.ManagedMajorMinorRelMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理部件大类小类关联 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ManagedMajorMinorRelServiceImpl implements ManagedMajorMinorRelService {

    @Resource
    private ManagedMajorMinorRelMapper managedMajorMinorRelMapper;

    @Override
    public Long createManagedMajorMinorRel(ManagedMajorMinorRelSaveReqVO createReqVO) {
        // 插入
        ManagedMajorMinorRelDO managedMajorMinorRel = BeanUtils.toBean(createReqVO, ManagedMajorMinorRelDO.class);
        managedMajorMinorRelMapper.insert(managedMajorMinorRel);
        // 返回
        return managedMajorMinorRel.getId();
    }

    @Override
    public void updateManagedMajorMinorRel(ManagedMajorMinorRelSaveReqVO updateReqVO) {
        // 校验存在
        validateManagedMajorMinorRelExists(updateReqVO.getId());
        // 更新
        ManagedMajorMinorRelDO updateObj = BeanUtils.toBean(updateReqVO, ManagedMajorMinorRelDO.class);
        managedMajorMinorRelMapper.updateById(updateObj);
    }

    @Override
    public void deleteManagedMajorMinorRel(Long id) {
        // 校验存在
        validateManagedMajorMinorRelExists(id);
        // 删除
        managedMajorMinorRelMapper.deleteById(id);
    }

    private void validateManagedMajorMinorRelExists(Long id) {
        if (managedMajorMinorRelMapper.selectById(id) == null) {
            throw exception(MANAGED_MAJOR_MINOR_REL_NOT_EXISTS);
        }
    }

    @Override
    public ManagedMajorMinorRelDO getManagedMajorMinorRel(Long id) {
        return managedMajorMinorRelMapper.selectById(id);
    }

    @Override
    public PageResult<ManagedMajorMinorRelDO> getManagedMajorMinorRelPage(ManagedMajorMinorRelPageReqVO pageReqVO) {
        return managedMajorMinorRelMapper.selectPage(pageReqVO);
    }

}