package cn.iocoder.yudao.module.smartcity.service.drainageuser;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.drainageuser.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.drainageuser.DrainageUserDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.drainageuser.DrainageUserMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 排水户信息 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class DrainageUserServiceImpl implements DrainageUserService {

    @Resource
    private DrainageUserMapper drainageUserMapper;

    @Override
    public Long createDrainageUser(DrainageUserSaveReqVO createReqVO) {
        // 插入
        DrainageUserDO drainageUser = BeanUtils.toBean(createReqVO, DrainageUserDO.class);
        drainageUserMapper.insert(drainageUser);
        // 返回
        return drainageUser.getId();
    }

    @Override
    public void updateDrainageUser(DrainageUserSaveReqVO updateReqVO) {
        // 校验存在
        validateDrainageUserExists(updateReqVO.getId());
        // 更新
        DrainageUserDO updateObj = BeanUtils.toBean(updateReqVO, DrainageUserDO.class);
        drainageUserMapper.updateById(updateObj);
    }

    @Override
    public void deleteDrainageUser(Long id) {
        // 校验存在
        validateDrainageUserExists(id);
        // 删除
        drainageUserMapper.deleteById(id);
    }

    private void validateDrainageUserExists(Long id) {
        if (drainageUserMapper.selectById(id) == null) {
            throw exception(DRAINAGE_USER_NOT_EXISTS);
        }
    }

    @Override
    public DrainageUserDO getDrainageUser(Long id) {
        return drainageUserMapper.selectById(id);
    }

    @Override
    public PageResult<DrainageUserDO> getDrainageUserPage(DrainageUserPageReqVO pageReqVO) {
        return drainageUserMapper.selectPage(pageReqVO);
    }

}