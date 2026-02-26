package cn.iocoder.yudao.module.envir.service.user;

import cn.iocoder.yudao.module.envir.dal.dataobject.user.UserDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.vehicle.VehicleDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.user.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.user.UserDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.user.UserMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

/**
 * 系统用户 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Long createUser(UserSaveReqVO createReqVO) {
        // 插入
        UserDO user = BeanUtils.toBean(createReqVO, UserDO.class);
        userMapper.insert(user);
        // 返回
        return user.getId();
    }

    @Override
    public void updateUser(UserSaveReqVO updateReqVO) {
        // 校验存在
        validateUserExists(updateReqVO.getId());
        // 更新
        UserDO updateObj = BeanUtils.toBean(updateReqVO, UserDO.class);
        userMapper.updateById(updateObj);
    }

    @Override
    public void deleteUser(Long id) {
        // 校验存在
        validateUserExists(id);
        // 删除
        userMapper.deleteById(id);
    }

    private void validateUserExists(Long id) {
        if (userMapper.selectById(id) == null) {
            throw exception(USER_NOT_EXISTS);
        }
    }

    @Override
    public UserDO getUser(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public PageResult<UserDO> getUserPage(UserPageReqVO pageReqVO) {
        return userMapper.selectPage(pageReqVO);
    }

    @Override
    public List<UserDetailDO> getUserListDetail() {
        return userMapper.selectListDetail();
    }
}