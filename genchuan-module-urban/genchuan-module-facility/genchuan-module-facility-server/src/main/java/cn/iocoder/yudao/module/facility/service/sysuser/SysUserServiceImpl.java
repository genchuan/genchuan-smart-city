package cn.iocoder.yudao.module.facility.service.sysuser;

import cn.iocoder.yudao.module.facility.controller.admin.sysuser.vo.SysUserPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.sysuser.vo.SysUserSaveReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.sysuser.SysUserDO;
import cn.iocoder.yudao.module.facility.dal.mysql.sysuser.SysUserMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.*;

/**
 * 系统用户 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public Long createSysUser(SysUserSaveReqVO createReqVO) {
        // 插入
        SysUserDO sysUser = BeanUtils.toBean(createReqVO, SysUserDO.class);
        sysUserMapper.insert(sysUser);
        // 返回
        return sysUser.getId();
    }

    @Override
    public void updateSysUser(SysUserSaveReqVO updateReqVO) {
        // 校验存在
        validateSysUserExists(updateReqVO.getId());
        // 更新
        SysUserDO updateObj = BeanUtils.toBean(updateReqVO, SysUserDO.class);
        sysUserMapper.updateById(updateObj);
    }

    @Override
    public void deleteSysUser(Long id) {
        // 校验存在
        validateSysUserExists(id);
        // 删除
        sysUserMapper.deleteById(id);
    }

    private void validateSysUserExists(Long id) {
        if (sysUserMapper.selectById(id) == null) {
            throw exception(SYS_USER_NOT_EXISTS);
        }
    }

    @Override
    public SysUserDO getSysUser(Long id) {
        return sysUserMapper.selectById(id);
    }

    @Override
    public PageResult<SysUserDO> getSysUserPage(SysUserPageReqVO pageReqVO) {
        return sysUserMapper.selectPage(pageReqVO);
    }

}
